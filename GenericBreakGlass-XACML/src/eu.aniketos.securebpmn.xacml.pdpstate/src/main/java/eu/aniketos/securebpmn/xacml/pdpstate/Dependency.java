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

package eu.aniketos.securebpmn.xacml.pdpstate;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.api.SecurityError;
import eu.aniketos.securebpmn.xacml.api.ErrorType;
import eu.aniketos.securebpmn.xacml.api.ReasonType;
import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoAttribute;
import eu.aniketos.securebpmn.xacml.api.autho.DesignatorAttribute;
import eu.aniketos.securebpmn.xacml.api.log.AccessControlRequest;
import eu.aniketos.securebpmn.xacml.pdpstate.db.AttributeDBIdentifier;
import eu.aniketos.securebpmn.xacml.pdpstate.db.AttributeType;
import eu.aniketos.securebpmn.xacml.pdpstate.db.ContextAttribute;
import eu.aniketos.securebpmn.xacml.pdpstate.db.HibernateUtil;

/**
 * 
 * This class stores the types of known assignments and how to retrieve
 * the required information from an XACML request. For example, consider a role
 * assignment. The configuration might look like this:<br/>
 * dependency:runEx:role:assignment		<br/>
 * urn:custom:subject:role -> urn:oasis:names:tc:xacml:1.0:subject:subject-id<br/>
 * urn:custom:subject:role -> attribute:urn:custom:resource:role<br/>
 * urn:oasis:names:tc:xacml:1.0:subject:subject-id -> urn:custom:resource:subject-id<br/>
 * <br/>
 * This config says:
 * <ul>
 * <li> define a dependency for the resource (in the XACML policy) runEx:role:assignment</li>
 * <li> to retrieve a role, one needs the subject-id (those are keys
 * 		 which have to be defined in the configuration file first)</li>
 * <li> the attributes for role (urn:custom:subject:role) can be retrieved by getting
 * 		all attribute:urn:custom:resource:role attributes from the XACML request</li>
 * <li> the attribute for subject-Id (urn:oasis:names:tc:xacml:1.0:subject:subject-id) can be retrieved
 * 		by getting all urn:custom:resource:subject-id attribues from the XACML request</li>
 * </ul>
 * 
 */
public class Dependency {
	
	/**
	 * resource for which this assignment is valid (as defined in the configuration file)
	 * e.g., urn:role:assignment
	 */
	private String resourceKey;
	

	private AttributeType type;
	
	/**
	 * all pdpStateDependencies have to be retrieved from an XACML request;
	 * this map defines which attribute has to be retrieved from the XACML request (value)
	 * and assigned to which attribute stored in the db (key).
	 * 
	 * Note that the attributes in the XACML have to be different, e.g., when
	 * assigning a role, one cannot use the role attribute to define the to be assigned roles,
	 * i.e., the roles have to treated as resource; so is the subject-id for the role assignment.
	 */
	private Map<AttributeDBIdentifier, AttributeDBIdentifier> requestDependencies;
	
	
	private static Logger logger = Logger.getLogger(Dependency.class);
	
	
	public Dependency(String key, AttributeType type, 
			Map<AttributeDBIdentifier, AttributeDBIdentifier> requestDependencies) {
		this.resourceKey = key;
		this.type = type;
		this.requestDependencies = requestDependencies;
	}

	
	public List<ContextAttribute> getDependingAttributes() {
		return type.getCtxTypes();
	}
	
	public AttributeDBIdentifier getDependingAttributeIdentifier(int index) {
		return type.getCtxTypes().get(index).getAttrId();
	}
	
	public AttributeDBIdentifier getAttributeIdentifier() {
		return type.getAttrType();
	}
	
	public String getResourceKey() {
		return resourceKey;
	}

	/**
	 * creates a new Assignment based on the configuration file and assures that
	 * there are no conflicting definitions within the database.
	 * 
	 * @param resource
	 * @param reader
	 * @param attributes
	 * @return
	 * @throws IOException
	 * @throws SyntaxError
	 */
	public static Dependency readAssignment(String resource, BufferedReader reader, 
			Map<String, AttributeDBIdentifier> attributes, HibernateUtil dbUtil) throws IOException, SyntaxError {
		// get the dependency definition line, e.g., urn:custom:subject:role -> urn:oasis:names:tc:xacml:1.0:subject:subject-id
		String dependency = reader.readLine();
		int m = dependency.indexOf("->");
		if ( m == -1 ) {
			throw new SyntaxError("Missing assignment -> ");
		}
		// get the attribute for which the dependency is defined - key points to a attribute: definition
		String key = dependency.substring(0, m).trim();
		AttributeDBIdentifier dbKey = attributes.get(key);
		if ( dbKey == null ) {
			logger.error("Creating Dependency " + resource +", but Attribute with key " + key + " is not defined so far");
			throw new SyntaxError("Attribute with key " + key + " is not defined so far");
		}
		if (logger.isDebugEnabled() ) {
			logger.debug("Creating Dependency " + resource + " for attribute " + key + " -> " + dbKey.toString());
		}
		
		// read all dependencies for this attribute and create or get AttributeType based on those definitions
		List<AttributeIdentifier> dependencies = new Vector<AttributeIdentifier>(); //bullshit, but cannot cast from List<AttributeDBIdentifier> to List<AttributeIdentifier>
		// also keep track of all defined dependencies, as they are needed afterwards
		List<String> dependencyKeys = new Vector<String>();
		dependencyKeys.add(key);
		boolean noDependency = false;
		StringTokenizer tokenizer = new StringTokenizer(dependency.substring(m + 2), ",");
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken().trim();
			if ( ! (token.length() == 0) ) {
				if ( dependencies.size() == 0 && "null".equals(token) ) {
					// some attributes may not depend on any other attribute, but they have to define this, i.e., write attributeKey -> null
					noDependency = true; 
				} else {
					AttributeDBIdentifier dependent = attributes.get(token);
					if ( dependent == null ) {
						logger.error("Creating Dependency " + resource +", but Attribute with key " + dependent + " is not defined so far");
						throw new SyntaxError("Attribute with key " + dependent + " is not defined so far");
					} else {
//						dependencies_delete.add(dependent);
						dependencies.add(dependent);
						dependencyKeys.add(token);
						if ( logger.isDebugEnabled() ) {
							logger.debug("Found dependency to attribute " + token + ", referencing to " + dependent.toString());
						}

					}
				}
			} 
		}
		if ( dependencies.size() == 0 && ! noDependency) {
			logger.error("For dependency " + resource + " (attribute " + key + " no dependencies are defined");
			throw new SyntaxError("No dependency is defined");
		}
		
		AttributeType type = dbUtil.addAttributeType(dbKey,  dependencies);
		if ( type == null ) {
			logger.warn("Could not retrieve AttributeType for configuration of assignment " + dbKey + ": different definition already exists");
			throw new SyntaxError("Invalid configuration: Different definition already exists");
		}  else if (logger.isDebugEnabled() ) {
			logger.debug("Got AttributeType with id " + type.getId() + " for dependency:" + resource);
		}
		/* ok, we got the AttributeType definition for this dependency, now
		 * we have to get the information which element from a XACML request has to be retrieved
		 * to update the PDPState accordingly
		 */
		Map<AttributeDBIdentifier, AttributeDBIdentifier> requestDependencies = new HashMap<AttributeDBIdentifier, AttributeDBIdentifier>();
		for ( String dep : dependencyKeys ) {
			String confLine = readValueLine(reader);
			m = confLine.indexOf("->");
			String dbAttr, requAttr;
			dbAttr = confLine.substring(0, m).trim();
			requAttr = confLine.substring(m + 2, confLine.length()).trim();
			
			if ( ! dbAttr.equals(dep)) {
				throw new SyntaxError("Invalid assignment: expected assignment for " + dep + " (line: " + confLine + ")");
			} else {
				AttributeDBIdentifier requDBAttr = attributes.get(requAttr);
				if ( requDBAttr == null ) {
					throw new SyntaxError("Attribute with key " + requAttr + " is not defined so far");
				} else {
					requestDependencies.put(attributes.get(dbAttr), requDBAttr);
					if ( logger.isDebugEnabled() ) {
						logger.debug("XACML request attr " + requAttr + " is mapped to " + dbAttr + " (" + requDBAttr + " -> " + attributes.get(dbAttr) + ")");
					}
				}
			}
		}
		return new Dependency(resource, type, requestDependencies);
	}
	
	private static String readValueLine(BufferedReader reader) throws IOException, SyntaxError {
		String line = reader.readLine().trim();
		if ( line.startsWith("#") ) {
			return readValueLine(reader);
		} else {
			return line;
		}
	}
	
	private List<String> getValues(AccessControlRequest request, AttributeDBIdentifier attr) {
		List<String> values = new Vector<String>();
		
		for ( DesignatorAttribute designAttr : request.getDesignatorAttributes()) {
			if ( designAttr.getAttrId().equals(attr)) {
				values.addAll(designAttr.getValues());
				// break; // should be ok, as only one entry for one attr should be there.. anyhow  
			}
		}
		for ( AuthoAttribute authoAttr : request.getAttributes()) {
			if ( authoAttr.getAttributeIdentifier().equals(attr)) {
				values.add(authoAttr.getValue());
			}
		}
		return values;
	}
	
	
	/**
	 * This functions receives an executed and recorded AccessControlRequest and 
	 * reads all needed values from the xacml request and stores the according
	 * state changes to the PDPState 
	 * @param request
	 * @param pdpState
	 * @throws SecurityError
	 */
	public void writeStateChange(AccessControlRequest request, PDPState pdpState) throws SecurityError {
		
		// get the values we should assign from the request
		List<String> assignValues = getValues(request, requestDependencies.get(type.getAttrType()));
		if ( assignValues.size() == 0 ) {
			logger.error("Nothing found in the request " + request.getEvaluationId().longValue());
			throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR, 
					"Request " + request.getEvaluationId() + " did not contain the required information");
		}
		
		if ( logger.isDebugEnabled() ) {
			StringBuffer message = new StringBuffer("Found " + assignValues.size() + " value(s) (");
			for (String value : assignValues ) {
				message.append(value + ", ");
			}
			message.append(") to write to the PDP state for " + this.resourceKey + " (" + this.type.getCtxTypes().size() + " depending ctx Attribute(s))");
			logger.debug(message.toString());
		}
		
		// depending on the number of context attributes, we have to retrieve those values from the request
		// and create the according assignment for the PDP state
		if ( type.getCtxTypes().size() == 0 ) {
			for ( String value : assignValues ) {
				//pdpState.addAssignment(type, value, null, null, null);
				pdpState.addAssignment(new AuthoAttribute(type.getAttrType(), value), null, null, null);
			}
			logger.info("Added " + assignValues.size() + " assignments for " + type.getAttrType() + " to the PDPState");
		} else if ( type.getCtxTypes().size() == 1 ) {
			// we have the AttributeType which tells us how much context Attributes we need; 
			// from this, we can get the attribute identifier which identifies the attributes in the xacml request 
			AttributeDBIdentifier ctxAttrType = requestDependencies.get(type.getCtxTypes().get(0).getAttrId());
			// get the according values
			List<String> ctxValues = getValues(request, ctxAttrType);
			// if there are no values, there is something wrong; terminate
			if ( ctxValues == null || ctxValues.size() == 0 ) {
				logger.error("Could not find values for contextAttribute " + ctxAttrType + " in the request");
				throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR, 
						"Request " + request.getEvaluationId() + " did not contain the required information");
			}  else if ( logger.isDebugEnabled() ) {
				StringBuffer message = new StringBuffer("Found " + ctxValues.size() + " value(s) (");
				for (String value : ctxValues ) {
					message.append(value + ", ");
				}
				message.append(") for " + ctxAttrType.toString());
				logger.debug(message.toString());
			}
			
			for ( String value: assignValues ) {
				AuthoAttribute valueAttr = new AuthoAttribute(type.getAttrType(), value);
				for ( String ctxValue : ctxValues ) {
					List<AuthoAttribute> ctxAttr = new Vector<AuthoAttribute>();
					ctxAttr.add(new AuthoAttribute(type.getCtxTypes().get(0).getAttrId(), ctxValue));
					pdpState.addAssignment(valueAttr, null, null, ctxAttr);
				}
			}
			logger.info("Added " + (assignValues.size()*ctxValues.size()) + " assignments for " + type.getAttrType() + " to the PDPState");
		} else {
			// here, we do for now only allow one value for every context attribute
			List<AuthoAttribute> ctxAttrs = new Vector<AuthoAttribute>();
			List<String> ctxValues;
			
			for ( ContextAttribute ctxAttr : type.getCtxTypes() ) {
					ctxValues = getValues(request, ctxAttr.getAttrId());
					if ( ctxValues.size() != 1) {
						logger.error("Expected 1 value for " + ctxAttr.getAttrId() + " in request " + 
								request.getEvaluationId() + ", but found " + ctxValues.size());
						throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR, 
								"Request " + request.getEvaluationId() + " did not contain the required information");
					} else {
						ctxAttrs.add(new AuthoAttribute(ctxAttr.getAttrId(), ctxValues.get(0)));
					}
			}
			
			for ( String value : assignValues ) {
				pdpState.addAssignment(new AuthoAttribute(type.getAttrType(), value), null, null, ctxAttrs);
			}
			logger.info("Added " + assignValues.size() + " assignments for " + type.getAttrType() + " to the PDPState");
		}
	}
}
