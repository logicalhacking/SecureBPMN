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

package eu.aniketos.securebpmn.xacml.pdp.abtractEval;

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

import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.Constants;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.attr.TypeIdentifierConstants;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;

import eu.aniketos.securebpmn.xacml.api.SecurityError;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoAttribute;
import eu.aniketos.securebpmn.xacml.api.idm.IdInfo;
import eu.aniketos.securebpmn.xacml.pdp.PDPServer;
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

public class AndOr {

    /*
     * TODO modify the current infrastructure => do not evaluate or treat the result as
     * abstract if a non-abstract true was found
     */


    private PDPServer pdp;

    private KnownAttributeResolver knownAttrs;
    private AnalysisConfig conf;
    private EvaluationEventHub eventHub;
    private MissingAttrCapture attrCapt;

    /**
     * @param args
     * @throws IOException
     * @throws FileNotFoundException
     * @throws URISyntaxException
     * @throws SecurityError
     * @throws UnknownIdentifierException
     * @throws ParsingException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParsingException, UnknownIdentifierException, SecurityError, URISyntaxException {
        Properties log4jProps = new Properties();
        log4jProps.load(new BufferedInputStream(new FileInputStream(new File("src/test/log4j.properties"))));
        PropertyConfigurator.configure(log4jProps);


        AndOr test = new AndOr();
        test.setup();

        test.testv1();
        //test.testv2();
        //test.testRoleAssignment();
    }


    private void testRoleAssignment() throws SecurityError, URISyntaxException, ParsingException {
        RequestCtx request = XACMLDecoder.decodeRequestCtx(
                                 new IdInfo("admin@aniketos.eu"),
                                 new URI("urn:runEx:role:assignment"),
                                 "add", null);
        analyze(request);

    }



    private void testv2() throws SecurityError, URISyntaxException, ParsingException {
        List<AuthoAttribute> attributes = new Vector<AuthoAttribute>();

        //add some attribute directly to the request
        attributes.add(
            new AuthoAttribute(
                Constants.SUBJECT_CAT,
                URI.create("urn:test:true"),
                TypeIdentifierConstants.BOOLEAN_URI,
                "true"));

        attributes.add(
            new AuthoAttribute(
                Constants.SUBJECT_CAT,
                URI.create("urn:test:false"),
                TypeIdentifierConstants.BOOLEAN_URI,
                "false"));

        RequestCtx request = XACMLDecoder.decodeRequestCtx(
                                 new IdInfo("foo", null, null),
                                 new URI("AndOrTest"),
                                 "bar", attributes);

        analyze(request);
    }

    private void testv1() throws SecurityError, URISyntaxException, ParsingException {
        List<AuthoAttribute> attributes = new Vector<AuthoAttribute>();

        //add some attribute directly to the request
        attributes.add(
            new AuthoAttribute(
                Constants.SUBJECT_CAT,
                URI.create("urn:test:true"),
                TypeIdentifierConstants.BOOLEAN_URI,
                "true"));

        attributes.add(
            new AuthoAttribute(
                Constants.SUBJECT_CAT,
                URI.create("urn:test:false"),
                TypeIdentifierConstants.BOOLEAN_URI,
                "false"));

        RequestCtx request = XACMLDecoder.decodeRequestCtx(
                                 new IdInfo("foo", null, null),
                                 new URI("AndOrTestv1"),
                                 "bar", attributes);

        analyze(request);
    }


    private void setup() throws FileNotFoundException, ParsingException, UnknownIdentifierException, SecurityError, URISyntaxException {
        ConfigurationStore config = new ConfigurationStore(new File("src/test/abstractEval/abstractEval-config.xml"));
        //ConfigurationStore config = new ConfigurationStore(new File("src/test/runningExample/pdp-config.xml"));
        conf = new AnalysisConfig(config.getDefaultPDPConfig());

        // first add attribute resolvers which will resolve known attributes
        // can/should be replaced for workbench version with versioned policy state module
        knownAttrs = new KnownAttributeResolver();
        conf.addAnalysisAttributeResolver(knownAttrs);

        //only if no attribute can be found, use abstract value
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

    private void analyze(RequestCtx request) throws ParsingException {
        // print XACML request
        String requestString = XACMLEncoder.encodeRequestCtx(request);
        System.out.println("XACML REQUEST:\n" + requestString);

        // evaluate request
        ResponseCtx resp = pdp.analyze(new AnalysisCtx(request, conf,
                                       EvaluationIdAttribute.INVALID, eventHub));

        // print XACML response
        String responseString = XACMLEncoder.encodeResponseCtx(resp);
        System.out.println("RESPONE:\n" + responseString);

        ReportGenerator repGen = new ReportGenerator(attrCapt.getKnownAttributes(),
                eventHub.getEvalInfo().getTreeElemTree());

        System.out.println("REPORT:::");
        System.out.println( repGen.reportMissingAttr());
    }

}
