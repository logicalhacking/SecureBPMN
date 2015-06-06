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

package eu.aniketos.securebpmn.xacml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import eu.aniketos.securebpmn.xacml.api.SecurityError;
import eu.aniketos.securebpmn.xacml.api.ErrorType;
import eu.aniketos.securebpmn.xacml.api.ReasonType;
import eu.aniketos.securebpmn.xacml.finder.MySVNClient;
import eu.aniketos.securebpmn.xacml.finder.SVNPolicyFinderModule;

import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.PDPConfig;
import com.sun.xacml.finder.PolicyFinderModule;

/**
 *
 * This class allows to load the pdp configuration from the svn server
 *
 */
public abstract class SVNPDPConfig  {

    private static Logger logger = Logger.getLogger(SVNPDPConfig.class);

    public static final String SVN_CLIENT = "SVN_CLIENT",
                               ANALYSIS_ENGINE = "analysis_engine",
                               PASSWORD = "password",
                               POLICY_FILE = "policies",
                               SVN_URL = "svn_url",
                               UPDATE_JARS = "updateJars",
                               USE_SVN = "use_svn",
                               USERNAME = "username",
                               VERSION = "version";


//	public SVNPDPConfig(PDPConfig pdpConfig) {
//		super(pdpConfig.getAttributeFinder(),
//				pdpConfig.getPolicyFinder(),
//				pdpConfig.getResourceFinder(),
//				pdpConfig.getRevocationFinder());
//		super.setCutomAttrs(pdpConfig.);
//	}

//	public SVNPDPConfig() {
//
//	}

//    public SVNPDPConfig(AttributeFinder attributeFinder,
//            PolicyFinder policyFinder,
//            ResourceFinder resourceFinder,
//            RevocationFinder revocationFinder) {
//    	super ()
//    }


    public static PDPConfig getSVNPDPConfig(File confFile) throws SecurityError {

        URL svn_url = null;
        String username, password;
        long version;
        //boolean updateJars;

        //create DOM builder
        DocumentBuilderFactory dbFactory =
            DocumentBuilderFactory.newInstance();

        dbFactory.setIgnoringComments(true);
        dbFactory.setNamespaceAware(false);
        dbFactory.setValidating(false);

        DocumentBuilder db = null;
        try {
            db = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Could not load DocumentBuilder: ParserConfigurationException: " + e.getMessage());
            throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR, "Could not load DocumentBuilder", e);
        }
        //read conf file
        Document doc = null;
        try {
            doc = db.parse(new FileInputStream(confFile));
        } catch (IOException ioe) {
            logger.error("Failed to load svn configuration from file: IOException: " + ioe.getMessage());
            throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR, "failed to load the configuration from string ", ioe);
        } catch (SAXException saxe) {
            logger.error("Could not parse configuration: SAXException: " + saxe.getMessage());
            throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR, "Could not parse configuration: SAXException: " + saxe.getMessage(), saxe);
        } catch (IllegalArgumentException iae) {
            logger.error("Failed to load the configuration from file: IllegalArgumentException: " + iae.getMessage());
            throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR, "Failed to load the configuration from file: IllegalArgumentException: " + iae.getMessage(), iae);
        }

        Element confNode = doc.getDocumentElement();

        if ( ! confNode.getTagName().equals("config")) {
            throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR, "configuration requires <config> elemement als root!");
        }

        Map<String, String> attrs = new HashMap<String, String>();
        Node n;
        NodeList list = confNode.getChildNodes();
        for ( int i = 0; i < list.getLength(); ++i) {
            n = list.item(i);

            if ( n.getNodeType() == Node.ELEMENT_NODE ) {
                String key = null, value = null;
                if ( n.getFirstChild() != null ) {
                    key = n.getNodeName();
                    if ( n.getFirstChild().getNodeType() == Node.TEXT_NODE ) {
                        value = n.getFirstChild().getNodeValue().trim();
                    }
                } else {
                    NamedNodeMap foo = n.getAttributes();
                    if (foo != null ) {
                        key = foo.getNamedItem("key").getNodeValue();
                        value = foo.getNamedItem("value").getNodeValue();
                    }
                }
                if ( key != null && value != null ) {
//					if (logger.isDebugEnabled()) {
//						logger.debug("Read custom attribute " + key + " with value " + value);
//					}
                    attrs.put(key, value);
                }
            }
        }

        if ( ! attrs.containsKey(SVN_URL)) {
            logger.error(SVN_URL + " is missing in " + confFile.getAbsolutePath());
            throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR,
                                    "SVNPDPConfig requires the property " + SVN_URL);
        }

        try {
            svn_url = new URL(attrs.get(SVN_URL));
        } catch (MalformedURLException e) {
            logger.error(SVN_URL + " is not a valid URL: " + e.getMessage() + " in " + confFile.getAbsolutePath());
            throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR,
                                    "SVNPDPConfig: " + SVN_URL + " has to be a valid URL", e);
        }

        if ( ! attrs.containsKey(USERNAME)) {
            logger.error(USERNAME + " is missing in " + confFile.getAbsolutePath());
            throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR,
                                    "SVNPDPConfig requires the property " + USERNAME);
        } else {
            username = attrs.get(USERNAME);
        }

        if ( ! attrs.containsKey(PASSWORD)) {
            logger.error(PASSWORD + " is missing in " + confFile.getAbsolutePath());
            throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR,
                                    "SVNPDPConfig requires the property " + PASSWORD);
        } else {
            password = attrs.get(PASSWORD);
        }


        if ( ! attrs.containsKey(VERSION)) {
            logger.warn(VERSION + " is missing in " + confFile.getAbsolutePath() + " using -1 as default");
            version = -1;
        } else {
            String tmpNr = attrs.get(VERSION).trim();
            try {
                version = Long.parseLong(tmpNr);
            } catch (NumberFormatException e) {
                version = -1;
                logger.warn("Could not parse " + tmpNr + " to a Long value (" + e.getMessage() + "); using -1 as default");
            }
        }

        logger.info("SVN config: svn-url: " + svn_url.toString() + ", username: " + username + ", version: " + version);

        return getSVNPDPConfig(svn_url.toString(), username, password, version);

//		MySVNClient svnClient = new MySVNClient(svn_url.toString(), username, password, version);
//
//		try {
//			String configuration = svnClient.getTextFile(SVNPolicyFinderModule.XACML_FOLDER + "/policy-config.xml");
//			ConfigurationStore confStore = new ConfigurationStore(configuration);
//			PDPConfig pdpConfig = confStore.getDefaultPDPConfig();
////			Set<PolicyFinderModule> policyFinders = pdpConfig.getPolicyFinder().getModules();
////			for ( PolicyFinderModule module : policyFinders ) {
////				if ( module instanceof SVNPolicyFinderModule ) {
////
////				}
////			}
//
//			return new SVNPDPConfig(pdpConfig);
//
//		} catch (SVNException e) {
//			logger.error("Could not load configuration from SVN Repository: SVNException:" + e.getMessage() + " (" +e.getErrorMessage()  + ")");
//			e.printStackTrace();
//			throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR, "Could not load configuration from SVN Repository: " + e.getMessage(), e);
//		} catch (Exception e) {
//			logger.error("Could not load configuration: " + e.getClass() + ": " + e.getMessage());
//			throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR, "Could not load configuration from SVN Repository: " + e.getClass() + ": " + e.getMessage(), e);
//		}
    }

    public static PDPConfig getSVNPDPConfig(String svn_url,
                                            String username, String password, long version) throws SecurityError {

        MySVNClient svnClient = new MySVNClient(svn_url, username, password, version);
        Map<String, Object> customAttrs = new HashMap<String, Object>();
        customAttrs.put(SVN_CLIENT, svnClient);
        customAttrs.put(SVN_URL, svn_url);
        customAttrs.put(USERNAME, username);
        customAttrs.put(PASSWORD, password);
        customAttrs.put(VERSION, version);

        try {
            String configuration = svnClient.getTextFile(SVNPolicyFinderModule.XACML_FOLDER + "/policy-config.xml");
            logger.debug("Creating ConfigurationStore from svn policy-config.xml");
            //System.out.println("####\n" + configuration + "\n######");
            ConfigurationStore confStore = new ConfigurationStore(configuration, customAttrs);
            PDPConfig pdpConfig = confStore.getDefaultPDPConfig();
            //pdpConfig.getPolicyFinder().addPolicyFinderModule(new SVNPolicyFinderModule(svnClient));
            Set<PolicyFinderModule> modules = pdpConfig.getPolicyFinder().getModules();
            modules.add(new SVNPolicyFinderModule(svnClient));
            pdpConfig.getPolicyFinder().setModules(modules);
            logger.debug("Created SVNPolicyFinderModule and added to PDPConfig");
            //return new SVNPDPConfig(pdpConfig);
            pdpConfig.setCustomAttr(SVN_CLIENT, svnClient);
            return pdpConfig;
        } catch (SVNException e) {
            logger.error("Could not load configuration from SVN Repository: SVNException:" + e.getMessage() + " (" +e.getErrorMessage()  + ")");
            e.printStackTrace();
            throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR, "Could not load configuration from SVN Repository: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Could not load configuration: " + e.getClass() + ": " + e.getMessage());
            throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR, "Could not load configuration from SVN Repository: " + e.getClass() + ": " + e.getMessage(), e);
        }
    }
}
