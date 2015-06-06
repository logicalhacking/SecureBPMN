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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.PropertyConfigurator;

import eu.aniketos.securebpmn.xacml.api.SecurityError;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoAttribute;
import eu.aniketos.securebpmn.xacml.support.XACMLDecoder;
import eu.aniketos.securebpmn.xacml.support.XACMLEncoder;

import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.Constants;
import com.sun.xacml.PDP;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.attr.TypeIdentifierConstants;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * tests the plain (com.sun.xacml) PDP
 *
 */
public class PlainPDPTest extends TestCase {

    private PDP pdp;

    public static void main(String[] args) throws FileNotFoundException, IOException, ParsingException, UnknownIdentifierException, SecurityError, URISyntaxException {
        Properties log4jProps = new Properties();
        log4jProps.load(new BufferedInputStream(new FileInputStream(new File("src/test/log4j.properties"))));
        PropertyConfigurator.configure(log4jProps);

        PlainPDPTest test = new PlainPDPTest();
        test.setup();
        test.foo();
    }

    private void setup() throws FileNotFoundException, ParsingException, UnknownIdentifierException {
        ConfigurationStore config = new ConfigurationStore(
            new FileInputStream(new File("src/test/productive-config.xml")), "src/test/");
        pdp = new PDP(config.getDefaultPDPConfig());
    }




//	private static void bar() throws FileNotFoundException, ParsingException, UnknownIdentifierException, SecurityError, URISyntaxException {
//
//
//		ConfigurationStore config = new ConfigurationStore(
//				new FileInputStream(new File("src/test/productive-config.xml")), "src/test/");
//		PDP pdp = new PDP(config.getDefaultPDPConfig());

//		PDPServer pdp = new PDPServer(new File("src/test/productive-config.xml"), "src/test/");
////
////
////		exec(pdp);
//
//
//
//
//
//	}

    private void foo() throws FileNotFoundException, ParsingException, UnknownIdentifierException, SecurityError, URISyntaxException {

        List<AuthoAttribute> attributes = new Vector<AuthoAttribute>();
        attributes.add(
            new AuthoAttribute(
                Constants.SUBJECT_CAT,
                Constants.SUBJECT_ID,
                TypeIdentifierConstants.STRING_URI,
                "root"));

//		attributes.add(
//				new AuthoAttribute(
//						Constants.RESOURCE_CAT,
//						URI.create("urn:owner"),
//						TypeIdentifierConstants.STRING_URI,
//						"helmut"));

        RequestCtx request = XACMLDecoder.decodeRequestCtx(null, new URI("MedicalRecord"), "read", attributes);


        System.out.println("REQUEST:\n" + XACMLEncoder.encodeRequestCtx(request));

        ResponseCtx response = pdp.evaluate(request);
        System.out.println("RESPONE:\n" + XACMLEncoder.encodeResponseCtx(response));

    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PlainPDPTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {

        assertTrue( true );
    }

}
