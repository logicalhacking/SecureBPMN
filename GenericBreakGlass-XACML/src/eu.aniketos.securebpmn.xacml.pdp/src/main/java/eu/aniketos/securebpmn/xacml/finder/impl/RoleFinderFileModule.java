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

package eu.aniketos.securebpmn.xacml.finder.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNException;

import eu.aniketos.securebpmn.xacml.SVNPDPConfig;
import eu.aniketos.securebpmn.xacml.finder.MySVNClient;
import eu.aniketos.securebpmn.xacml.finder.SVNPolicyFinderModule;

import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.Constants;
import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.BagAttribute;
import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.attr.TypeIdentifierConstants;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.finder.AttributeFinderModule;

/**
 *
 * This module selects a bag of roles which are assigned
 * to the subject in the current evaluation context.
 * The role assignment is read from a configuration
 * file roles.config at startup of the PDP. This is
 * intended for simple tests, for a more sohpisticated
 * setup use PDPStateModule which retrieves the current
 * state of the assignment from a database at runtime.
 *
 */
public class RoleFinderFileModule extends AttributeFinderModule {

    public static final String USER_ROLES = "subject-roles",
                               CONFIG_FILE = "roles.config";


    public static final URI USER_ROLES_URI = URI.create(USER_ROLES);

    private Map<String, Collection<AttributeValue>> roleAssignments;

    private ConfigurationStore conf;

    private static final Logger logger = Logger.getLogger(RoleFinderFileModule.class);

    public RoleFinderFileModule() {
//		roleAssignments = readConfig(
//				"burli:Role1;MasterGuru;UseCaseCaptain\n" +
//				"maxi:fuzzi;Ruzzi;bulli\n" +
//				"\n" +
//				"     \n" +
//				"root:Role1;MasterGuru;Nurse;asdf"
//				);
    }


    public boolean isDesignatorSupported() {
        return true;
    }


    public void setConfigurationStore(ConfigurationStore conf) {
        this.conf = conf;
        if (this.conf != null) {
            String config = null;
            //check, if there is a svn client
            MySVNClient svn = (MySVNClient) conf.getCustomAttr(SVNPDPConfig.SVN_CLIENT);

            if ( svn != null ) {
                try {
                    roleAssignments = readConfig(svn.getTextFile(SVNPolicyFinderModule.XACML_FOLDER + "/" + CONFIG_FILE));
                } catch (SVNException e) {
                    logger.error("Could not retreive " + CONFIG_FILE + " for role configuration from SVN storage");
                }
            } else

                //if there is no config from the svn
                if ( config == null ) {
                    String baseDir = conf.getBaseDir();
                    File confFile = new File(baseDir + CONFIG_FILE);
                    logger.debug("Try to load role configuration from file: " + confFile.getAbsolutePath() + " (baseDir: " + baseDir + ")");
                    if ( confFile.exists() ) {
                        try {
                            roleAssignments = readConfig(new InputStreamReader(new FileInputStream(confFile)));
                        } catch (FileNotFoundException e) {
                            logger.error("File (role configuration) not found: " + confFile.getAbsolutePath() + ": " + e.getMessage(), e);
                        }
                    } else {
                        logger.error("File (role configuration) not found: " + confFile.getAbsolutePath());
                    }
                }
        } else {
            logger.error("Did not retreive a valid configuration!");
        }
    }


    private Map<String, Collection<AttributeValue>> readConfig(Reader in) {

        Map<String, Collection<AttributeValue>> roleConfig
            = new HashMap<String, Collection<AttributeValue>>();
        BufferedReader reader = new BufferedReader(in);

        String line = null, user, roles;
        try {
            for ( int i = 0; (line = reader.readLine()) != null ; ++i) {
                if ( line.startsWith("#") || line.trim().length() == 0) {
                    //ignore comments;
                    continue;
                }
                int a = line.indexOf(":");
                if ( a < 1 ) {
                    logger.warn("Error in line " + i + ": Did not find \":\"");
                    continue;
                }
                else {
                    user = line.substring(0, a).trim();
                    roles = line.substring(a + 1, line.length());
                    StringTokenizer tokenizer = new StringTokenizer(roles, ";");
                    Collection<AttributeValue> tmpCol = new Vector<AttributeValue>();

                    while ( tokenizer.hasMoreElements()) {
                        tmpCol.add(StringAttribute.getInstance(tokenizer.nextToken().trim()));
                    }
                    roleConfig.put(user, tmpCol);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return roleConfig;
    }

    /**
     * helper function to read the configuration and save it into the local map
     * @param config
     * @return
     */
    private Map<String, Collection<AttributeValue>> readConfig(String config) {
        return readConfig(new StringReader(config));
    }

    @Override
    public EvaluationResult findAttribute(URI category, URI attributeType,
                                          URI attributeId, URI issuer,
                                          EvaluationCtx context) {

        if ( USER_ROLES_URI.equals(attributeId) &&
                TypeIdentifierConstants.STRING_URI.equals(attributeType) ) {
            String user = getUserID(context);
            if ( user == null || roleAssignments.get(user) == null
                    || roleAssignments.get(user).size() == 0 ) {
                logger.warn("Found no role assignments for user " + user);
            } else {
                return new EvaluationResult(new BagAttribute(attributeType, roleAssignments.get(user)));
            }
        }
        // if found nothing or not responsible, return empty bag
        return new EvaluationResult(BagAttribute.createEmptyBag(attributeType));
    }


    private String getUserID(EvaluationCtx context) {

//		if ( context instanceof AttrCachingEvaluationCtx ) {
//			AttrCachingEvaluationCtx c_context = (AttrCachingEvaluationCtx) context;
//		}

        // if ( context instanceof EvaluationIdContext )
        // more efficient impl
        // else get "classical"

        // if user == null
        //logger.warn("Could not retreive the subject-id from the current context");

        EvaluationResult result = context.getAttribute(Constants.SUBJECT_CAT, TypeIdentifierConstants.STRING_URI,
                                  Constants.SUBJECT_ID, null);

        if (! result.getAttributeValue().isBag()) {
            logger.error("Did not retreive a bag after attribute search");
            return null;
        } else {
            Iterator<AttributeValue> it = ((BagAttribute) result.getAttributeValue()).iterator();
            String firstUser = null;
            for ( int i = 0; it.hasNext(); ++i) {
                String user = ((StringAttribute)it.next()).getValue();
                if ( i > 0) {
                    logger.warn("Retreived more than subjectID: " + user + " which will be ignored");
                } else {
                    firstUser = user;
                }
            }
            return firstUser;
        }
    }
}
