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
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import eu.aniketos.securebpmn.xacml.api.SecurityError;
import eu.aniketos.securebpmn.xacml.SVNPDPConfig;

import com.sun.xacml.PDPConfig;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ProductiveSVNTest extends ProductiveTest {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParsingException, UnknownIdentifierException, SecurityError, URISyntaxException {

        Properties log4jProps = new Properties();
        log4jProps.load(new BufferedInputStream(new FileInputStream(new File("src/test/log4j.properties"))));
        PropertyConfigurator.configure(log4jProps);

        ProductiveSVNTest test = new ProductiveSVNTest();
        test.setup();
        test.foo();

    }

    private void setup() throws FileNotFoundException, ParsingException, UnknownIdentifierException, SecurityError {
        PDPConfig conf  = SVNPDPConfig.getSVNPDPConfig(new File("src/main/webapp/WEB-INF/svn-config.xml"));
        pdp = new PDPServer(conf);

    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ProductiveSVNTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {

        assertTrue( true );
    }

}
