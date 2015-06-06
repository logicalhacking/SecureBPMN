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

package eu.aniketos.securebpmn.xacml.pdp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public static void main(String[] args) throws IOException, ParsingException, UnknownIdentifierException {
        Properties log4jProps = new Properties();
        log4jProps.load(new BufferedInputStream(new FileInputStream(new File("src/test/log4j.properties"))));
        PropertyConfigurator.configure(log4jProps);
        //		//AppTest test = new AppTest("pdpServer test");
        //test.testApp();
        foo();
    }

    private static void foo() throws FileNotFoundException, ParsingException, UnknownIdentifierException {
        //PDPServer pdpServer = new PDPServer(new File("src/main/webapp/WEB-INF/policy-config.xml"), "src/main/webapp/webapp/WEB-INF/");


    }
//	private static final String SVN_URL = "https://projects.brucker.ch/soknos-dev/svn/trunk/examples/versionedPDP_data";
//	private static final String USERNAME = "pdp";
//	private static final String PASSWORD = "HJeSnelw";

//	private static void bar() {
//
////
////		Properties log4jProps = new Properties();
////		try {
////			log4jProps.load(new BufferedInputStream(new FileInputStream(new File(PDPServer.LOG4J))));
////			PropertyConfigurator.configure(log4jProps);
////			logger.info("Loaded log4j configuration from " + PDPServer.LOG4J);
////		} catch (IOException e) {
////			logger.error("Could not load log4j configuration from " + PDPServer.LOG4J + " IOException: " + e.getMessage());
////			System.err.println("Could not load log4j configuration from log4j.properties IOException: " + e.getMessage());
////		}
//
//		new SVNPolicyFinderModule(SVN_URL, USERNAME, PASSWORD, -1);
//	}
    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {

        assertTrue( true );
    }



}
