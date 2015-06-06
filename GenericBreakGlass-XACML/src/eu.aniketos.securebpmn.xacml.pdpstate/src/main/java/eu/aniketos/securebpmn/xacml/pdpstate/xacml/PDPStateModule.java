/* Copyright 2012-2015 SAP SE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package eu.aniketos.securebpmn.xacml.pdpstate.xacml;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;
import eu.aniketos.securebpmn.xacml.pdpstate.PDPState;
import eu.aniketos.securebpmn.xacml.pdpstate.db.AttributeDBIdentifier;
import eu.aniketos.securebpmn.xacml.pdpstate.db.AttributeType;
import eu.aniketos.securebpmn.xacml.pdpstate.db.ContextAttribute;
import eu.aniketos.securebpmn.xacml.pdpstate.db.ContextAttributeAssignment;
import eu.aniketos.securebpmn.xacml.pdpstate.db.HibernateUtil;
import eu.aniketos.securebpmn.xacml.support.finder.IPDPStateEvaluationContext;
     
import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.attr.AttributeFactory;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.BagAttribute;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.finder.AttributeFinderModule;

/**
 * PDPState modules uses the backend database to check if it is responsible;
 * if yes, it retrieves the information which attributes have to be resolved
 * from the context form the database (which is more or less configuration and meta data).
 * After retrieving the required data from the context, the actual assignments
 * are queried from the database, the result is returned to the PDP 
 *
 */
public class PDPStateModule extends AttributeFinderModule {
	
	private static PDPState pdpState = PDPState.getInstance();
	private static HibernateUtil dbUtil = pdpState.getHibernateUtil();
	
	private static final Logger logger = Logger.getLogger(PDPStateModule.class);
	
	protected ConfigurationStore conf;
	
    public boolean isDesignatorSupported() {
        return true;
    }
    
	public void setConfigurationStore(ConfigurationStore conf) {
		this.conf = conf;
	}
	
	@Override
    public EvaluationResult findAttribute(URI category, URI attributeType,
			   URI attributeId, URI issuer,
           EvaluationCtx context) {
		
		AttributeIdentifier attrId = new AttributeIdentifier(category, attributeType, attributeId, issuer);
		logger.debug("Got request for " + attrId.toString());

		// check if we could have some value for this attribute
		if ( dbUtil.contains(attrId)) {
			//we are responsible, get what is there
			AttributeType attrType = dbUtil.getAttributeType(attrId);
			if ( logger.isDebugEnabled() ) {
				logger.debug("Found AttributeType (id " + attrType.getId() + ") with " + 
						( attrType.getCtxTypes() == null ? "0" : attrType.getCtxTypes().size()) + 
						" dependencies");
			}

			//check if we have to resolve some context information and if yes, resolve it
			List<ContextAttribute> contextAttrs = attrType.getCtxTypes();
			List<ContextAttributeAssignment> contextAttrValues = null; 
			if ( contextAttrs != null && contextAttrs.size() > 0 ) {
				contextAttrValues = new Vector<ContextAttributeAssignment>();

				for ( ContextAttribute contextAttr : contextAttrs ) {
					AttributeDBIdentifier id = contextAttr.getAttrId();
					EvaluationResult result = context.getAttribute(id.getCategory(), id.getAttributeType(), 
							id.getAttributeId(), id.getIssuer());

					if ( !result.getAttributeValue().isBag() || 
							((BagAttribute) result.getAttributeValue()).size() != 1 ) {
						logger.error("Did not retreive a bag with one (" +((BagAttribute) result.getAttributeValue()).size() + 
								") entry after attribute search! " +
						"PDPStateModule allows only one value of dependent context attributes");
						//TODO throw indeterminate message
						return new EvaluationResult(BagAttribute.createEmptyBag(attributeType));
					} else {
						AttributeValue attrVal = ((BagAttribute) result.getAttributeValue()).iterator().next();
						//X1 contextAttrValues.add(new ContextAttributeAssignment(contextAttr, attrVal.toString()));
						// the 
						contextAttrValues.add(new ContextAttributeAssignment(attrVal.encode(), contextAttr, null));
						logger.debug("Resolved " +attrVal.encode() + " for attribute " + id.toString() + " from the context"); 
					}
				}
			}
			// check if we have a specific context which tells us the exact point in time for which 
			// the attributes have to be resolved
			Date queryTime = null;
			if ( context instanceof IPDPStateEvaluationContext ) {
				queryTime = ((IPDPStateEvaluationContext)context).getExecTime();
			} else {
				queryTime = new Date();
			}
			// execute query on pdpState
			List<String> values = pdpState.getAttribute(attrType, contextAttrValues, new Timestamp(queryTime.getTime()));
			if ( logger.isDebugEnabled() ) {
				StringBuffer buff = new StringBuffer("Found " + values.size() + " value(s): ");
				for (String value : values ) {
					buff.append(value + ", ");
				}
				logger.debug(buff.toString());
			}

			if ( values == null || values.size() == 0 ){
				return new EvaluationResult(BagAttribute.createEmptyBag(attributeType));
			} else {
				Collection<AttributeValue> attrValues = new Vector<AttributeValue>();
				AttributeFactory attrFactory = AttributeFactory.getInstance();
				for ( String value : values) {
					try {
						attrValues.add(attrFactory.createValue(attributeType, value));
					} catch (UnknownIdentifierException e) {
						logger.error("UnknownIdentifierException - " + e.getMessage() + ": cannot transform " + value + " to an attributeValue of type" + attributeType, e);
					} catch (ParsingException e) {
						logger.error("ParsingException - " + e.getMessage() + ": cannot transform " + value + " to an attributeValue of type" + attributeType, e);
					}
				}
				return new EvaluationResult(new BagAttribute(attributeType, attrValues));
			}
		} else {
			logger.debug("DBState does not have any value for this AttributeType");
			// we are not responsible.. DBState does not have any value...
			return new EvaluationResult(BagAttribute.createEmptyBag(attributeType));
		}
    }
}
