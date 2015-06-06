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

package eu.aniketos.securebpmn.xacml.support;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.sun.xacml.ParsingException;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.BagAttribute;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.finder.AttributeFinder;
import com.sun.xacml.finder.RevocationFinder;

import eu.aniketos.securebpmn.xacml.api.autho.DesignatorAttribute;
import eu.aniketos.securebpmn.xacml.support.finder.IRecordEvaluationContext;

/**
 *
 * RecordEvaluationContext is able to retreive events of collected attributes at runtime and store them
 *
 *
 */
public class RecordEvaluationContext extends EvaluationIdContext implements IRecordEvaluationContext {

    private static Logger logger = Logger.getLogger(RecordEvaluationContext.class);

    /**
     * contains all attributes which have been resolved at runtime
     */
    private List<DesignatorAttribute> attrs;

    /**
     * defines the time when the policies have been resolved
     */
    private Date execTime;

    /**
     * defines (if applicable) which policy version has been used
     */
    private long version;


    public RecordEvaluationContext(RequestCtx request, AttributeFinder aFinder,
                                   RevocationFinder rFinder, Long evaluationId) throws ParsingException {
        super(request, aFinder, rFinder, true, evaluationId);
        this.execTime = new Date();
        version = AttributeResolver.getPDPStatePolicyVersion(this);
    }


//	protected long getPDPStatePolicyVersion() {
//		EvaluationResult evalResult = this.getAttribute(IPDPStateEvaluationContext.PDPSTATE_CATEGORY,
//				IPDPStateEvaluationContext.PDPSTATE_ATTRIBUTETYPE,
//				IPDPStateEvaluationContext.PDPSTATE_URI,
//				IPDPStateEvaluationContext.PDPSTATE_ISSUER);
//
//		if ( ((BagAttribute) evalResult.getAttributeValue()).size() > 1 ) {
//			logger.error("Did not retreive a bag with one (" +((BagAttribute) evalResult.getAttributeValue()).size() +
//					") entry after attribute search for current svn policy version number; " +
//					"PDP Dtate requires exactly one attribute to be defined");
//			return -1;
//		} else if ( ((BagAttribute) evalResult.getAttributeValue()).size() == 1 ) {
//			IntegerAttribute attrVal = (IntegerAttribute) ((BagAttribute) evalResult.getAttributeValue()).iterator().next();
//			logger.debug("Request " + super.getCurrentEvaluationId() + " will be executed under policy " + attrVal.getValue());
//			return attrVal.getValue();
//		} else {
//			logger.debug("Could not resolve current policy version");
//			return -1;
//		}
//	}

    public void retreiveDesignatorAttributeSearch(URI category, URI attributeType,
            URI attributeId, URI issuer, EvaluationResult evalResult) {

        //TODO why not only store the stuff at runtime and do the transformation aftwards?

        if ( ! (evalResult.getAttributeValue() instanceof BagAttribute) ) {
            logger.warn("RecordEvaluationContext received a non-bag attribute");
        } else {
            BagAttribute bAttr = (BagAttribute) evalResult.getAttributeValue();

            if ( attrs == null ) {
                attrs = new Vector<DesignatorAttribute>();
            }
            if (bAttr.isEmpty()) {
                attrs.add(new DesignatorAttribute(attributeId, attributeType, category));
                if ( logger.isDebugEnabled() ) {
                    logger.debug("Result for attributeId " + attributeId + " of type " + attributeType +
                                 ", category " + category + " is empty");
                }
            } else {
                DesignatorAttribute attr = new DesignatorAttribute(attributeId, attributeType, category);

                for ( AttributeValue value :  bAttr.iterable()) {
                    attr.addBagValue(value.encode());
                }
                attrs.add(attr);
                if ( logger.isDebugEnabled() ) {
                    logger.debug("Added designatorAttr (" + attributeId + " of type " + attributeType +
                                 ", category " + category +") with " + attr.getBagValues().size() + " values added to EvaluationContext");
                }
            }
        }
    }

    /**
     *
     * @return the list of collected designators or null, if non have been collected
     */
    public List<DesignatorAttribute> getDesignatorAttributes() {
        return attrs;
    }

    public Date getExecTime() {
        return execTime;
    }

    public long getVersion() {
        return version;
    }

}
