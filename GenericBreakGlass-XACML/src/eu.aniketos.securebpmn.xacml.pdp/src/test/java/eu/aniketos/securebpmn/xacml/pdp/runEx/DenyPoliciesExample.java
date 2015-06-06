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

package eu.aniketos.securebpmn.xacml.pdp.runEx;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import eu.aniketos.securebpmn.xacml.pdp.PDPServer;
import eu.aniketos.securebpmn.xacml.pdpstate.PDPStateManagement;

import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;

public class DenyPoliciesExample {

    private static PDPServer pdp;
    private static PDPStateManagement pdpStateMgt;

    private static final String nurse = "nurse",
                                physician = "physician";

    /**
     * @param args
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParsingException
     * @throws UnknownIdentifierException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParsingException, UnknownIdentifierException {

        Properties log4jProps = new Properties();
        log4jProps.load(new BufferedInputStream(new FileInputStream(new File("src/test/log4j.properties"))));
        PropertyConfigurator.configure(log4jProps);

        init();
        setupSzenario();

    }

    private static void setupSzenario() {
        //pdpStateMgt.addRole("", ")
    }


    private static void init() throws ParsingException, UnknownIdentifierException {
        ConfigurationStore config = new ConfigurationStore(new File("src/test/runningExample/pdp-config-denyPolicies.xml"));


        pdp = new PDPServer(config.getDefaultPDPConfig());
        pdpStateMgt = PDPStateManagement.getInstance();
    }


}
