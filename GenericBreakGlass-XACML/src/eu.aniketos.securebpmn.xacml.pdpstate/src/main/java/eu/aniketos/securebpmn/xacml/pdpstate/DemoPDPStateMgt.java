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

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.api.autho.AuthoAttribute;
import eu.aniketos.securebpmn.xacml.pdpstate.db.AttributeDBIdentifier;

/**
 * 
 * This class allows to modify the PDP State directly, i.e., 
 * without creating an access control request, getting an 
 * urn:custom:notifyPDPState obligation and sending it to
 * the PDPServer. <br/>
 * It is intended as class for setting demos, but can also be used
 * to implement simple scenarios where, e.g., the assignment of roles
 * is not considered and not policies are therefore available
 * 
 */
public class DemoPDPStateMgt {
	
	/*
	 * to modify the pdpState; 
	 */
	private PDPState pdpState;
	private Map<String, Dependency> dependencies;
	
	private static final String ROLEASSIGNMENT = "urn:runEx:role:assignment";
	private AttributeDBIdentifier roleIdentifier, role_dep1;
	
	private static final String POLICIES = "urn:runEx:activePolicies";
	private AttributeDBIdentifier policyIdentifier;
	
	private static DemoPDPStateMgt instance;
	
	private static final Logger logger = Logger.getLogger(DemoPDPStateMgt.class);
	
	
	public static DemoPDPStateMgt getInstance() {
		return instance;
	}
	
	public DemoPDPStateMgt() {
		instance = this;
		dependencies = PDPStateManagement.getInstance().getDependencies();
		pdpState = PDPState.getInstance();
		
		Dependency roleAssignment = dependencies.get(ROLEASSIGNMENT);
		if ( roleAssignment == null ) {
			logger.error("Could not find role assignment definition in the " +
					"configuration of PDPStateManagement; you have to define " +
					"the role assignment with key " + ROLEASSIGNMENT  + " in " +
					"the configuration file " + PDPStateManagement.CONFFILE_NAME);
			throw new RuntimeException("Missing configuration for dependency " + ROLEASSIGNMENT);
		} else {
			roleIdentifier = roleAssignment.getAttributeIdentifier();
			role_dep1 = roleAssignment.getDependingAttributeIdentifier(0);
		}
		
		Dependency policiesAssignment = dependencies.get(POLICIES);
		if ( policiesAssignment == null ) {
			logger.error("Could not find policy assignment definition in the " +
					"configuration of PDPStateManagement; you have to define " +
					"the role assignment with key " + POLICIES  + " in " +
					"the configuration file " + PDPStateManagement.CONFFILE_NAME);
			throw new RuntimeException("Missing configuration for dependency " + POLICIES);			
		} else {
			policyIdentifier = policiesAssignment.getAttributeIdentifier();
		}
	
	}
	
	
	public List<String> getRoles(String subjectId) {
		List<AuthoAttribute> contextAttrs = new Vector<AuthoAttribute>();
		contextAttrs.add(new AuthoAttribute(role_dep1, subjectId));
		return pdpState.getAttribute(roleIdentifier, contextAttrs);
	}
	
	public void addRole(String subjectId, String role) {
		List<AuthoAttribute> contextAttrs = new Vector<AuthoAttribute>();
		contextAttrs.add(new AuthoAttribute(role_dep1, subjectId));
		AuthoAttribute roleAttr = new AuthoAttribute(roleIdentifier, role);
		// use default dates assigned by PDPState: now for from, ever for to
		pdpState.addAssignment(roleAttr, null, null, contextAttrs); 
	}
	
	public void removeRole(String subjectId, String role) {
		List<AuthoAttribute> contextAttrs = new Vector<AuthoAttribute>();
		contextAttrs.add(new AuthoAttribute(role_dep1, subjectId));
		AuthoAttribute roleAttr = new AuthoAttribute(roleIdentifier, role);

		pdpState.endAssignment(roleAttr, null, contextAttrs);
	}
	
	public List<String> getActivePolicies() {
		return pdpState.getAttribute(policyIdentifier, null);
	}
	
	public void addActivePolicy(String policyId) {
		AuthoAttribute policyAttr = new AuthoAttribute(policyIdentifier, policyId);
		pdpState.addAssignment(policyAttr, null, null, null); 
	}
	
	public void removeActivePolicy(String policyId) {
		AuthoAttribute policyAttr = new AuthoAttribute(policyIdentifier, policyId);
		pdpState.endAssignment(policyAttr, null, null);
	}
	
//	public List<String> getRoles(String subjectId) {
//		List<AuthoAttribute> contextAttrs = new Vector<AuthoAttribute>();
//		contextAttrs.add(new AuthoAttribute(role_dep1, subjectId));
//		return pdpState.getAttribute(roleIdentifier, contextAttrs);
//	}
	
//	public List<String> getQualifications(String subjectId) {
//	//TODO
//	return null;
//}
//
//public void addQualifications(String subjectId, String qualification) {
//	//TODO qualification
//}
//
//public void removeQualification(String subjectId, String qualification) {
//	//TODO 
//}
//
//public void createResource(URI resource, String creator) {
//	
//}
//
//public String getCreator(URI resource) {
//	return null;
//}
//
//public List<String> getDepartments(String subjectId) {
//	//TODO
//	return null;
//}
//
//public void addDepartment(String subjectId, String department) {
//	//TODO qualification
//}
//
//public void removeDepartment(String subjectId, String department) {
//	//TODO 
//}

}
