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
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.EvaluationEventHub;
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.MissingAttrCapture;
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.PrettyPrinter;
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.ReportGenerator;
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.attributes.AbstractAttributeResolver;
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.attributes.KnownAttributeResolver;
import eu.aniketos.securebpmn.xacml.AnalysisConfig;
import eu.aniketos.securebpmn.xacml.AnalysisCtx;
import eu.aniketos.securebpmn.xacml.support.XACMLDecoder;
import eu.aniketos.securebpmn.xacml.support.XACMLEncoder;
import eu.aniketos.securebpmn.xacml.support.attr.EvaluationIdAttribute;

import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.Constants;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.attr.TypeIdentifierConstants;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * code to test the PDPServer as it is used for analysis purposes;
 * configuration is loaded from a local file
 * <br/>
 * <b>Note:</b> Only works when pdp project is build with analysis-pdp.pom.xml
 *
 */
public class AnalysisPDPTest extends TestCase {


    private PDPServer pdp;

    private KnownAttributeResolver knownAttrs;
    private AnalysisConfig conf;
    private EvaluationEventHub eventHub;
    private MissingAttrCapture attrCapt;

    public static void main(String[] args) throws IOException, ParsingException, UnknownIdentifierException, SecurityError, URISyntaxException {
        //when using the PDPServer not within tomcat
        Properties log4jProps = new Properties();
        log4jProps.load(new BufferedInputStream(new FileInputStream(new File("src/test/log4j.properties"))));
        PropertyConfigurator.configure(log4jProps);

        AnalysisPDPTest test = new AnalysisPDPTest();

        test.setup();
        test.exec();
    }


    private void setup() throws FileNotFoundException, ParsingException, UnknownIdentifierException, SecurityError, URISyntaxException {
        ConfigurationStore config = new ConfigurationStore(new File("src/test/productive-config.xml"));
        conf = new AnalysisConfig(config.getDefaultPDPConfig());

        knownAttrs = new KnownAttributeResolver();
        conf.addAnalysisAttributeResolver(knownAttrs);
        conf.addAnalysisAttributeResolver(new AbstractAttributeResolver());

        //create PDP
        pdp = new PDPServer(conf);

        //for analysis/evaluation create required classes
        eventHub = new EvaluationEventHub();

        // keep track of missing and resovled attributes
        attrCapt = new MissingAttrCapture(eventHub.getEvalInfo());
        eventHub.register(attrCapt);

        // print the call stack
        eventHub.register(new PrettyPrinter());
    }






    private void exec() throws SecurityError, URISyntaxException, ParsingException {
        List<AuthoAttribute> attributes = new Vector<AuthoAttribute>();
        attributes.add(
            new AuthoAttribute(
                Constants.SUBJECT_CAT,
                Constants.SUBJECT_ID,
                TypeIdentifierConstants.STRING_URI,
                "root"));
        attributes.add(
            new AuthoAttribute(
                Constants.SUBJECT_CAT,
                URI.create("urn:subject:department"),
                TypeIdentifierConstants.STRING_URI,
                "test1"));
//		attributes.add(
//				new AuthoAttribute(
//						Constants.RESOURCE_CAT,
//						URI.create("urn:patient:department"),
//						TypeIdentifierConstants.STRING_URI,
//						"test1"));

        attributes.add(
            new AuthoAttribute(
                Constants.SUBJECT_CAT,
                URI.create("subject-roles"),
                TypeIdentifierConstants.STRING_URI,
                "Nurse"));

        //urn:nhs:becker:health-record    MedicalRecord
        RequestCtx request = XACMLDecoder.decodeRequestCtx(null, new URI("urn:nhs:becker:health-record"), "read", attributes);

        String requestString = XACMLEncoder.encodeRequestCtx(request);
        System.out.println("REQUEST:\n" + requestString);

        ResponseCtx resp = pdp.analyze(new AnalysisCtx(request, conf,
                                       EvaluationIdAttribute.INVALID, eventHub));

        String responseString = XACMLEncoder.encodeResponseCtx(resp);

        //String responseString = pdp.evaluateXACML(requestString);
        System.out.println("RESPONE:\n" + responseString); //XACMLEncoder.encodeResponseCtx(response));

        ReportGenerator repGen = new ReportGenerator(attrCapt.getKnownAttributes(),
                eventHub.getEvalInfo().getTreeElemTree());

        System.out.println("REPORT:::");
        System.out.println( repGen.reportMissingAttr());


    }



    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AnalysisPDPTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

}
