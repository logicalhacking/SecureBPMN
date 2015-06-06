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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.sun.xacml.Constants;
import com.sun.xacml.attr.TypeIdentifierConstants;

import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoAttribute;
import eu.aniketos.securebpmn.xacml.pdpstate.db.AttributeAssignment;
import eu.aniketos.securebpmn.xacml.pdpstate.db.AttributeType;
import eu.aniketos.securebpmn.xacml.pdpstate.db.HibernateUtil;


public class PDPStateStartup {
	
	private static final Logger logger = Logger.getLogger(PDPStateStartup.class);

	public static PDPState pdpState;
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		
		Properties log4jProps = new Properties();
		log4jProps.load(new BufferedInputStream(new FileInputStream(new File("src/test/log4j.properties"))));
		PropertyConfigurator.configure(log4jProps);
		

		copyConfForTest();
		
		pdpState = PDPState.getInstance();
		
		//HibernateUtil hUtil = pdpState.getHibernateUtil();
		List<String> values;
		
		//checkRoleAttrType(hUtil);
		//checkRoleAttrType(hUtil);
		
		addRoleAssignment("alice", "Nurse");
		addRoleAssignment("dave", "Doctor");
		addRoleAssignment("dave", "Nurse");
		addSubjectDepartment("alice", "Interne");
		addSubjectDepartment("dave", "Chirurgie");
		
		//pdpState.test();
		
		values = getRoles("alice");
		System.out.print("roles for alice: ");
		for ( String value : values) {
			System.out.print(value + ", ");
		}
		System.out.println("");
		
		
		values = getRoles("dave");
		System.out.print("roles for dave: ");
		for ( String value : values) {
			System.out.print(value + ", ");
		}
		System.out.println("");
		
		
		addDummyAssignment("val1", "key1_1", "key2_1");
		addDummyAssignment("val2", "key1_1", "key2_1");

		addDummyAssignment("val3", "key1_2", "key2_1");
		addDummyAssignment("val4", "key1_1", "key2_2");

		
		values = getDummyAssignment("key1_2", "key2_1");
		System.out.print("dummy assignment for key1_1/key2_1: ");
		for ( String value : values) {
			System.out.print(value + ", ");
		}
		
		List<AuthoAttribute> contextAttrs = new Vector<AuthoAttribute>();
		contextAttrs.add(new AuthoAttribute(subjetIdentifier, "alice"));
		//return pdpState.getAttribute(roleIdentifier, contextAttrs)
		
		List<Long> assigns = pdpState.getAssignmentIds(roleIdentifier, "Nurse", contextAttrs, new Date(), new Date());
		for ( Long id : assigns ) {
			System.out.println("assigned id " + id);
		}
		
		
	}
	
	/**
	 * checks if the role assignment is already defined => subject-roles requiring subject-id
	 * @param hUtil
	 */
	public static void checkRoleAttrType(HibernateUtil hUtil) {
		logger.debug("Create role AttrId");
		
		AttributeIdentifier roleId = new AttributeIdentifier(Constants.SUBJECT_CAT, URI.create("subject-roles"), TypeIdentifierConstants.STRING_URI, null);
		
		AttributeType attrType = hUtil.getAttributeType(roleId);
		if ( attrType != null ) {
			logger.debug("attrType role already exisits and has " + attrType.getCtxTypes().size() + " contextTypes");
		} else {
			List<AttributeIdentifier> contextAttrs = new Vector<AttributeIdentifier>();
			contextAttrs.add(new AttributeIdentifier(Constants.SUBJECT_CAT, Constants.SUBJECT_ID, TypeIdentifierConstants.STRING_URI, null));
			attrType = hUtil.addAttributeType(roleId, contextAttrs);
			logger.debug("added role");
		}
		
		logger.debug("attrType role has id " + attrType.getId());
	}
	
	public static AttributeIdentifier roleIdentifier = new AttributeIdentifier(Constants.SUBJECT_CAT, URI.create("subject-roles"), TypeIdentifierConstants.STRING_URI, null);
	public static AttributeIdentifier subjetIdentifier = new AttributeIdentifier(Constants.SUBJECT_CAT, Constants.SUBJECT_ID, TypeIdentifierConstants.STRING_URI, null);
	public static AttributeIdentifier subjDep = new AttributeIdentifier(Constants.SUBJECT_CAT, URI.create("department"), TypeIdentifierConstants.STRING_URI, null);
	public static AttributeIdentifier fooIdentifier = new AttributeIdentifier(Constants.SUBJECT_CAT, URI.create("foo"), TypeIdentifierConstants.STRING_URI, null);
	
	
	public static AttributeAssignment addRoleAssignment(String userId, String role) {
		
		List<AuthoAttribute> contextAttrs = new Vector<AuthoAttribute>();
		contextAttrs.add(new AuthoAttribute(subjetIdentifier, userId));
		return pdpState.addAssignment(new AuthoAttribute(roleIdentifier, role), null, null, contextAttrs);
	}
	
	public static List<String> getRoles(String userId) {
		List<AuthoAttribute> contextAttrs = new Vector<AuthoAttribute>();
		contextAttrs.add(new AuthoAttribute(subjetIdentifier, userId));
		return pdpState.getAttribute(roleIdentifier, contextAttrs);
	}
	
	public static AttributeAssignment addSubjectDepartment(String userId, String role) {
		
		List<AuthoAttribute> contextAttrs = new Vector<AuthoAttribute>();
		contextAttrs.add(new AuthoAttribute(subjetIdentifier, userId));
		return pdpState.addAssignment(new AuthoAttribute(subjDep, role), null, null, contextAttrs);
	}
	
	public static AttributeAssignment addDummyAssignment(String foo, String bar, String baz) {
		List<AuthoAttribute> contextAttrs = new Vector<AuthoAttribute>();
		contextAttrs.add(new AuthoAttribute(roleIdentifier, bar));
		contextAttrs.add(new AuthoAttribute(subjDep, baz));
		return pdpState.addAssignment(new AuthoAttribute(subjetIdentifier, foo), null, null, contextAttrs);
	}
	
	public static List<String> getDummyAssignment(String bar, String baz) {
		List<AuthoAttribute> contextAttrs = new Vector<AuthoAttribute>();
		contextAttrs.add(new AuthoAttribute(roleIdentifier, bar));
		contextAttrs.add(new AuthoAttribute(subjDep, baz));
		return pdpState.getAttribute(subjetIdentifier, contextAttrs);
	}
	
	
	public static void copyConfForTest() throws IOException {
		
		BufferedInputStream bIS = new BufferedInputStream(new FileInputStream(new File("src/main/resources/hibernate.cfg.xml")));
		BufferedOutputStream bOS = new BufferedOutputStream(new FileOutputStream(new File("target/classes/hibernate.cfg.xml")));
		
		byte[] buffer = new byte[4096];
		int read = -1;
		
		while ( (read = bIS.read(buffer)) != -1 ) {
			bOS.write(buffer, 0, read);
		}
		bOS.flush();
		bOS.close();
		
		
		bIS = new BufferedInputStream(new FileInputStream(new File("src/main/resources/eu.aniketos.pdpState.xml")));
		bOS = new BufferedOutputStream(new FileOutputStream(new File("target/classes/eu.aniketos.pdpState.xml")));
		
		while ( (read = bIS.read(buffer)) != -1 ) {
			bOS.write(buffer, 0, read);
		}
		bOS.flush();
		bOS.close();
	}
}
