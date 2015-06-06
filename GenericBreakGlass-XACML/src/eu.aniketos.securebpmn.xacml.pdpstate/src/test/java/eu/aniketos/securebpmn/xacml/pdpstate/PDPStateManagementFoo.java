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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.log4j.PropertyConfigurator;

public class PDPStateManagementFoo {

	
	private static PDPStateManagementFoo pdpStateMgt;
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		
//		Properties log4jProps = new Properties();
//		log4jProps.load(new BufferedInputStream(new FileInputStream(new File("src/test/log4j.properties"))));
//		PropertyConfigurator.configure(log4jProps);
//		
//		pdpStateMgt = PDPStateManagement.getInstance();
//		
//		pdpStateMgt.addRole("Alice", "Nurse");
//		pdpStateMgt.addRole("Bob", "Doctor");
//		pdpStateMgt.addRole("Bob", "Nurse");
//		pdpStateMgt.addRole("Bob", "Admin");
//		printRoles("Bob");
//		
//		pdpStateMgt.removeRole("Bob", "Admin");
//		
//		printRoles("Bob");
//		printRoles("Alice");
//		pdpStateMgt.addRole("Bob", "Admin");
//		
//		printRoles("Bob");
		
		

	}
	
	private static void printRoles(String subjectId) {
//		List<String> roles = pdpStateMgt.getRoles(subjectId);
//		System.out.print("Roles of " + subjectId + ": ");
//		for (String role : roles) {
//			System.out.print(role + ", ");
//		}
//		System.out.println("");
	}

	

}
