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

package eu.aniketos.securebpmn.xacml.finder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNProperties;

import eu.aniketos.securebpmn.xacml.SVNPDPConfig;
import eu.aniketos.securebpmn.xacml.support.finder.FilePolicyModule;

import com.sun.xacml.AbstractPolicy;
import com.sun.xacml.PDPConfig;
import com.sun.xacml.ParsingException;
import com.sun.xacml.finder.PolicyFinder;
import com.sun.xacml.support.finder.PolicyReader;

public class SVNPolicyFinderModule extends FilePolicyModule  {


    protected String svn_url;
    protected long version;

    private static Logger logger = Logger.getLogger(SVNPolicyFinderModule.class);

    public static final String XACML_FOLDER = "xacml";

    protected MySVNClient svn;

    public SVNPolicyFinderModule(List<String> args) {
        super(args);
    }


    public SVNPolicyFinderModule(String svn_url, String username, String password, int version) {
        svn = new MySVNClient(svn_url, username, password, version);
    }

    public SVNPolicyFinderModule(MySVNClient svnClient) {
        svn = svnClient;
    }

    public SVNPolicyFinderModule() {

    }


    @Override
    public void init(PolicyFinder finder) {

        logger.debug("Initializing SVNPolicyFinderModule");

        svnInit(finder);

        this.svn_url = svn.getSvn_url();
        this.version = svn.getVersion();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        List<String> policyFiles = null;
        try {
            policyFiles = getPolicyFileNames(version, buffer);
        } catch (SVNException e) {
            logger.error("SVNException when reading file " + SVNPDPConfig.POLICY_FILE + " from repository: " + e.getMessage() + ", will not be able to retrieve policies from SVN storage");
            policyFiles = new Vector<String>();
        }

        List<AbstractPolicy> policies = getPolicies(finder, policyFiles, version, buffer);
        for ( AbstractPolicy policy : policies ) {
            super.addPolicy(policy);
        }
        // svn client is not required any more, release resources locally
        svn = null;
    }

    /**
     * Does some initialization of for a SVN finder, i.e., getting
     * a SVN client and setting other parameters such as useLines
     * @param finder
     */
    protected void svnInit(PolicyFinder finder) {
        PDPConfig pdpConfig = finder.getPDPConfiguration();

        // if we did not get the svn client with the constructor
        if ( svn != null ) {
            svn = getSVNClient(pdpConfig, finder);
        }

        pdpConfig.setCustomAttr(SVNPDPConfig.SVN_CLIENT, svn);

        //check if we have to use lines parser
        if ( super.enf_useLines ) {
            useLines = true;
        }
        // check use lines configuration
        else if ( confParams.containsKey(CONF_USELINES)) {
            useLines = getBool(confParams.get(CONF_USELINES));
            logger.debug("Use lines: " + useLines + " (loaded from FinderModule configuration)");
        } else if ( pdpConfig.getCustomAttr(CONF_USELINES) != null )  {
            useLines = getBool( (String) pdpConfig.getCustomAttr(CONF_USELINES));
            logger.debug("Use lines: " + useLines + " (loaded from PDP wide configuration)");
        }
    }

    protected MySVNClient getSVNClient(PDPConfig pdpConfig, PolicyFinder finder) {

        // first, check if there is a local configuration
        List<String> confArgs = null;
        if ( confParams.containsKey(SVNPDPConfig.SVN_URL) && confParams.containsKey(SVNPDPConfig.USERNAME) &&
                confParams.containsKey(SVNPDPConfig.PASSWORD) ) {
            confArgs = new Vector<String>();
            confArgs.add(confParams.get(SVNPDPConfig.SVN_URL));
            confArgs.add(confParams.get(SVNPDPConfig.USERNAME));
            confArgs.add(confParams.get(SVNPDPConfig.PASSWORD));
            if (confParams.get(SVNPDPConfig.VERSION) != null ) {
                confArgs.add(confParams.get(SVNPDPConfig.VERSION));
            }
            logger.info("Loading SVN configuration from FinderModule configuration: " +
                        confParams.get(SVNPDPConfig.SVN_URL) +" in version " + confParams.get(SVNPDPConfig.VERSION));
        }
        // check if we can reuse an existing client
        else if (pdpConfig.getCustomAttr(SVNPDPConfig.SVN_CLIENT) != null) {
            MySVNClient client = (MySVNClient) pdpConfig.getCustomAttr(SVNPDPConfig.SVN_CLIENT);
            logger.info("Using PDP wide SVN client and configuration: " + svn.getSvn_url() +
                        " in version " + svn.getVersion());
            return client;
        }
        // check if there is a pdp wide svn configuration
        else if (pdpConfig.getCustomAttr(SVNPDPConfig.SVN_URL) != null && pdpConfig.getCustomAttr(SVNPDPConfig.USERNAME) != null &&
                 pdpConfig.getCustomAttr(SVNPDPConfig.PASSWORD) != null ) {
            confArgs = new Vector<String>();
            confArgs.add( (String) pdpConfig.getCustomAttr(SVNPDPConfig.SVN_URL));
            confArgs.add( (String) pdpConfig.getCustomAttr(SVNPDPConfig.USERNAME));
            confArgs.add( (String) pdpConfig.getCustomAttr(SVNPDPConfig.PASSWORD));
            if ( pdpConfig.getCustomAttr(SVNPDPConfig.VERSION) != null ) {
                confArgs.add( (String) pdpConfig.getCustomAttr(SVNPDPConfig.VERSION));
            }
            logger.info("Loading SVN configuration from PDP wide configuration: " +
                        pdpConfig.getCustomAttr(SVNPDPConfig.SVN_URL) +" in version " + pdpConfig.getCustomAttr(SVNPDPConfig.VERSION));
        } else {
            logger.fatal("No configuration for SVN access found; No policies will be loaded");
        }

        if (confArgs != null ) {
            return new MySVNClient(confArgs);
        } else {
            return null;
        }
    }



    protected List<String> getPolicyFileNames(long version, ByteArrayOutputStream buffer) throws SVNException {
        List<String> fileNames = new Vector<String>();

        String policiesFile = svn.getTextFile(XACML_FOLDER + "/" + SVNPDPConfig.POLICY_FILE, version, null, buffer);
        if ( policiesFile == null ) {
            logger.error("Could not find policies file (" + svn_url + "/" + XACML_FOLDER + "/" + SVNPDPConfig.POLICY_FILE);
            throw new RuntimeException("Could not find policies file (" + svn_url + "/" + XACML_FOLDER + "/" + SVNPDPConfig.POLICY_FILE);
        }

        BufferedReader reader = new BufferedReader(new StringReader(policiesFile));
        try {
            String filePolicy = null;
            while ( (filePolicy = reader.readLine()) != null ) {
                if ( ! ( filePolicy.startsWith("#") || filePolicy.length() == 0) ) {
                    fileNames.add(filePolicy.trim());
                }
            }
        } catch (IOException e) {
            // must not happen as stream is got from a string...
        }
        return fileNames;
    }

    protected List<AbstractPolicy> getPolicies(PolicyFinder finder,
            List<String> policyFileNames, long version, ByteArrayOutputStream buffer) {
        List<AbstractPolicy> policies = new Vector<AbstractPolicy>();

        SVNProperties props = new SVNProperties();
        PolicyReader reader = new PolicyReader(finder,
                                               java.util.logging.Logger.getLogger(this.getClass().getName()), null);
        if ( buffer == null ) {
            buffer = new ByteArrayOutputStream();
        }

        for ( String policyFile : policyFileNames ) {
            String policy;
            try {
                policy = svn.getTextFile(XACML_FOLDER + "/" + policyFile, version, props, buffer);

                if ( policy == null ) {
                    logger.error("Could not retreive " + policyFile + " from SVN repository. WARNING: This policy will not be loaded");
                } else {
                    policies.add(reader.readPolicy(new ByteArrayInputStream(policy.getBytes())));
                    logger.info("Loaded policies from " + policyFile + " (checked in with version " +
                                props.getStringValue(MySVNClient.COMMIT_VERSION) + " on " +
                                props.getStringValue(MySVNClient.COMMIT_DATE) + ")"); //
                    props.clear();
                }
            } catch (SVNException e) {
                logger.error("SVN Error at retreiving " + policyFile + ": " + e.getMessage() + ". WARNING: This policy will not be loaded");
            } catch (ParsingException e) {
                logger.error("ParsingException at parsing " + policyFile + ": " + e.getMessage() + ". WARNING: This policy will not be loaded");
                e.printStackTrace();
            }
        }
        return policies;
    }






//	try {
//		String policiesFile = svn.getTextFile(XACML_FOLDER + "/" + SVNPDPConfig.POLICY_FILE, version, props, buffer);
//
//		if ( policiesFile == null ) {
//			logger.error("Could not find policies file (" + svn_url + "/" + XACML_FOLDER + "/" + SVNPDPConfig.POLICY_FILE);
//			throw new RuntimeException("Could not find policies file (" + svn_url + "/" + XACML_FOLDER + "/" + SVNPDPConfig.POLICY_FILE);
//		}
//
//		BufferedReader reader = new BufferedReader(new StringReader(policiesFile));
//
//
//		String filePolicy = null;
//		try {
//			while ( (filePolicy = reader.readLine()) != null ) {
//				if ( ! ( filePolicy.startsWith("#") || filePolicy.length() == 0) ) {
//					policyFiles.add(filePolicy.trim());
//				}
//			}
//		} catch (IOException e) {
//			logger.error("IOExeption when reading file " + SVNPDPConfig.POLICY_FILE + " from repository: " + e.getMessage());
//			e.printStackTrace();
//		}
//	} catch (SVNException e) {
//		logger.error("SVNException when reading file " + SVNPDPConfig.POLICY_FILE + " from repository: " + e.getMessage());
//		e.printStackTrace();
//	}

//	SVNProperties props = new SVNProperties();
//  PolicyReader reader = new PolicyReader(finder,
//  		java.util.logging.Logger.getLogger(this.getClass().getName()), null);
//
//  for ( String policyFile : policyFiles ) {
//		String policy;
//		try {
//			policy = svn.getTextFile(XACML_FOLDER + "/" + policyFile, version, props, buffer);
//
//			if ( policy == null ) {
//				logger.error("Could not retreive " + policyFile + " from SVN repository. WARNING: This policy will not be loaded");
//			} else {
//				super.addPolicy(reader.readPolicy(new ByteArrayInputStream(policy.getBytes())));
//				logger.info("Loaded policies from " + policyFile + " (checked in with version " +
//						props.getStringValue(MySVNClient.COMMIT_VERSION) + " on " +
//						props.getStringValue(MySVNClient.COMMIT_DATE) + ")"); //
//				props.clear();
//			}
//		} catch (SVNException e) {
//			logger.error("SVN Error at retreiving " + policyFile + ": " + e.getMessage() + ". WARNING: This policy will not be loaded");
//		} catch (ParsingException e) {
//			logger.error("ParsingException at parsing " + policyFile + ": " + e.getMessage() + ". WARNING: This policy will not be loaded");
//			e.printStackTrace();
//		}
//  }


//	private void checkForConfig() {
//
//	}

}
