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

package eu.aniketos.securebpmn.xacml.pdp.state;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.PropertyConfigurator;

import eu.aniketos.securebpmn.xacml.api.SecurityError;
import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoAttribute;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoResult;
import eu.aniketos.securebpmn.xacml.api.idm.IdInfo;
import eu.aniketos.securebpmn.xacml.pdp.PDPServer;
import eu.aniketos.securebpmn.xacml.pdpstate.DemoPDPStateMgt;
import eu.aniketos.securebpmn.xacml.pdpstate.PDPStateManagement;

import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;

public class PDPStateMgt {

    private static long start, setup;

    private static PDPServer pdp;
    private static PDPStateManagement pdpStateMgt;
    private static DemoPDPStateMgt demoMgt;

    private static final String ADMIN_USER = "admin@aniketos.eu",
                                ADMIN_ROLE = "admin";

    private static AttributeIdentifier resource_subject = new AttributeIdentifier(
        URI.create("urn:oasis:names:tc:xacml:3.0:attribute-category:resource"),
        URI.create("http://www.w3.org/2001/XMLSchema#string"),
        URI.create("urn:custom:resource:subject-id"), null);

    private static AttributeIdentifier resource_role = new AttributeIdentifier(
        URI.create("urn:oasis:names:tc:xacml:3.0:attribute-category:resource"),
        URI.create("http://www.w3.org/2001/XMLSchema#string"),
        URI.create("urn:custom:resource:role"), null);

    /**
     * @param args
     * @throws IOException
     * @throws FileNotFoundException
     * @throws UnknownIdentifierException
     * @throws ParsingException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParsingException, UnknownIdentifierException {
        start = new Date().getTime();
        Properties log4jProps = new Properties();
        log4jProps.load(new BufferedInputStream(new FileInputStream(new File("src/test/log4j.properties"))));
        PropertyConfigurator.configure(log4jProps);

        init();
        demoSetup();
        setup = new Date().getTime();

        System.out.println("STARTUP TIME: " + ( setup - start));

        test1();
        long test1 = new Date().getTime();

        System.out.println("TEST TIME: " + (test1 - setup));

//
//		test2();
//		long test2 = new Date().getTime();
//
//		System.out.println("TEST TIME2: " + (test2 - test1));

        List<String> roles = demoMgt.getRoles("helmut@aniketos.eu");
        System.out.print("roles for helmut@aniketos.eu: ");
        for(String s : roles ) {
            System.out.print(s +", ");
        }
        System.out.println("");
    }

    private static void init() throws ParsingException, UnknownIdentifierException {
        //ConfigurationStore config = new ConfigurationStore(new File("src/test/runningExample/pdp-config.xml"));
        ConfigurationStore config = new ConfigurationStore(new File("src/test/runningExample/pdp-config-denyPolicies.xml"));


        pdp = new PDPServer(config.getDefaultPDPConfig());


        pdpStateMgt = PDPStateManagement.getInstance();
        demoMgt = new DemoPDPStateMgt();

    }

    private static void demoSetup() {
        demoMgt.addRole(ADMIN_USER, ADMIN_ROLE);

        List<String> roles = demoMgt.getRoles(ADMIN_USER);
        System.out.print("### TEST ### roles for " + ADMIN_USER + ": ");
        for(String s : roles ) {
            System.out.print(s +", ");
        }
        System.out.println("");

        demoMgt.addActivePolicy("preg");
        List<String> polices = demoMgt.getActivePolicies();
        System.out.print("### TEST ### active policies: ");
        for(String s : polices ) {
            System.out.print(s +", ");
        }
        System.out.println("");
    }


    private static void test1() {

        List<String> roles = demoMgt.getRoles("helmut@aniketos.eu");
        System.out.print("### TEST ### roles for helmut@aniketos.eu: ");
        for(String s : roles ) {
            System.out.print(s +", ");
        }
        System.out.println("");

        List<AuthoAttribute> attributes = new Vector<AuthoAttribute>();
        attributes.add(new AuthoAttribute(resource_subject, "helmut@aniketos.eu"));
        attributes.add(new AuthoAttribute(resource_role, "employee"));

        try {
            AuthoResult res = pdp.evaluate(new IdInfo(ADMIN_USER), "urn:runEx:role:assignment", "add", attributes);
            System.out.println("result: " + res.toString());

            long evalId = Long.parseLong(res.getObligations().get(0).getParameters().iterator().next().getValue());
            System.out.println("evalId: " + evalId);

//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

            pdp.notifyStateChange(evalId);
        } catch (SecurityError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        roles = demoMgt.getRoles("helmut@aniketos.eu");
        System.out.print("### TEST ### roles for helmut@aniketos.eu: ");
        for(String s : roles ) {
            System.out.print(s +", ");
        }
        System.out.println("");

    }


    private static void test2() {

        List<AuthoAttribute> attributes = new Vector<AuthoAttribute>();
        attributes.add(new AuthoAttribute(resource_subject, "helmut@aniketos.eu"));
        attributes.add(new AuthoAttribute(resource_role, "anotherRole"));

        try {
            AuthoResult res = pdp.evaluate(new IdInfo(ADMIN_USER), "urn:runEx:role:assignment", "add", attributes);
            System.out.println("result: " + res.toString());

            long evalId = Long.parseLong(res.getObligations().get(0).getParameters().iterator().next().getValue());
            System.out.println("evalId: " + evalId);

//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

            pdp.notifyStateChange(evalId);
        } catch (SecurityError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//		List<String> roles = demoMgt.getRoles("helmut@aniketos.eu");
//		System.out.print("roles for helmut@aniketos.eu: ");
//		for(String s : roles ) {
//			System.out.print(s +", ");
//		}
//		System.out.println("");

    }


}
