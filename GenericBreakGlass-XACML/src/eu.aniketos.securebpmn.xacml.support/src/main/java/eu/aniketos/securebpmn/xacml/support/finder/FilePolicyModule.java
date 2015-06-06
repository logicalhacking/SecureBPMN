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

package eu.aniketos.securebpmn.xacml.support.finder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.sun.xacml.AbstractPolicy;
import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.MatchResult;
import com.sun.xacml.ParsingException;
import com.sun.xacml.PolicyMetaData;
import com.sun.xacml.VersionConstraints;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.ctx.Status;
import com.sun.xacml.finder.PolicyFinder;
import com.sun.xacml.finder.PolicyFinderModule;
import com.sun.xacml.finder.PolicyFinderResult;
import com.sun.xacml.support.finder.PolicyReader;

/**
 *
 * PolicyFinderModule which allows to load policies by filename and folder
 * <br/>
 * When searching for policies, only main (entry) policies are searched,
 * i.e., only policies with an "main" at the end are loaded
 *
 */
public class FilePolicyModule extends PolicyFinderModule {

    private File schemaFile;

    // the filenames for the files we'll load
    private Set<String> fileNames;
    private Set<String> folders;

    private Map<URI, AbstractPolicy> policies;

    private Map<URI, AbstractPolicy> mainPolicies;

    private static final Logger logger = Logger.getLogger(FilePolicyModule.class);

    // configuration: keep track of lines when loadig policies (slow! use only for debugging!)
    protected boolean useLines = false, enf_useLines = false;

    protected Map<String, String> confParams = new HashMap<String, String>();


    protected static final String CONF_PREFIX = "conf:",
                                  FOLDER_PREFIX = "folder:",
                                  FILE_PREFIX = "file:",
                                  CONF_USELINES = "useLines";


    public FilePolicyModule() {
        fileNames = new HashSet<String>();
        folders = new HashSet<String>();
        policies = new HashMap<URI, AbstractPolicy>();
        mainPolicies = new HashMap<URI, AbstractPolicy>();

        String schemaName =
            System.getProperty(PolicyReader.POLICY_SCHEMA_PROPERTY);

        if (schemaName != null) {
            this.schemaFile = new File(schemaName);
        }
    }

    public FilePolicyModule(List<String> fileNames) {
        this();

        if (fileNames != null) {
            for ( String fileName : fileNames ) {
                if ( fileName.startsWith(CONF_PREFIX) ) {
                    String tmp = fileName.substring(5);
                    try {
                        String confId = tmp.substring(0, tmp.indexOf(":"));
                        String value = tmp.substring(tmp.indexOf(":") + 1);
                        confParams.put(confId, value);
                    } catch(Exception e) {
                        logger.warn("Could not add configuration: " + tmp);
                    }
                } else if ( fileName.startsWith(FOLDER_PREFIX)) {
                    this.folders.add(fileName.substring(7));
                } else if ( fileName.startsWith(FILE_PREFIX) ) {
                    this.fileNames.add(fileName.substring(5));
                } else {
                    this.fileNames.add(fileName);
                }
            }
        }
        if ( enf_useLines ) {
            useLines = true;
        } else if ( confParams.containsKey(CONF_USELINES))  {
            useLines = getBool(confParams.get(CONF_USELINES));
        }
    }

    @Override
    public boolean isRequestSupported() {
        return true;
    }

    @Override
    public void init(PolicyFinder finder) {
        Object o = finder.getPDPConfiguration().getCustomAttr(ConfigurationStore.BASEDIR);
        String baseDir = "";
        if ( o != null) {
            baseDir = (String) o;
        }

        PolicyReader reader = new PolicyReader(finder,
                                               java.util.logging.Logger.getLogger(FilePolicyModule.class.getName()),
                                               this.schemaFile, this.useLines);

        for (String fname : this.fileNames ) {
            try {
                AbstractPolicy policy =
                    reader.readPolicy(new FileInputStream(baseDir + fname), fname);
                addPolicy(policy);
            } catch (FileNotFoundException fnfe) {
                logger.warn("File couldn't be read: "+ fname, fnfe);
            } catch (ParsingException e) {
                logger.warn("Error reading policy from file " + fname + ": " + e.getMessage(), e);
            }
        }

        for ( String sFolder : this.folders ) {
            File folder = new File(baseDir + sFolder);
            File[] files = folder.listFiles();
            for ( File f : files ) {
                String name_lower = f.getName().toLowerCase();
                if ( name_lower.endsWith(".xacml")) {
                    try {
                        AbstractPolicy policy = reader.readPolicy(new FileInputStream(f), f.getName());
                        addPolicy(policy);
                    } catch (FileNotFoundException e) {
                        logger.warn("File couldn't be read: "+ f.getName(), e);
                    } catch (ParsingException e) {
                        logger.warn("Error reading policy from file " + f.getName(), e);
                    }
                }
            }
        }
        logger.info("Loaded " + policies.size() + " policies, " + mainPolicies.size() + " main policies");
    }

    @Override
    public PolicyFinderResult findPolicy(EvaluationCtx context) {
        logger.debug("FilePolicyModule.findPolicy");

        context.newEvent(this);
        AbstractPolicy match = null;
        for ( AbstractPolicy policy : this.mainPolicies.values() ) {
            context.newEvent(policy);
            logger.debug("Check match for Policy "+ policy.getId());

            MatchResult mResult = policy.match(context);
            int iResult = mResult.getResult();
            if ( iResult == MatchResult.NO_MATCH ) {
                context.closeCurrentEvent(new Result(Result.DECISION_NOT_APPLICABLE));
            } else if ( iResult == MatchResult.MATCH ) {
                context.closeCurrentEvent();
                if ( match == null ) {
                    match = policy;
                } else {
                    logger.error("Multiple main policies are matching: first: " + match.getId() + " - " + policy.getId());
                    List<String> statusCodes = new Vector<String>();
                    statusCodes.add(Status.STATUS_PROCESSING_ERROR);
                    Status errorStatus = new Status(statusCodes, "Error in PDP Configuration: Multiple matching main policies");
                    context.closeCurrentEvent(Result.INDETERMINATE);
                    return new PolicyFinderResult(errorStatus);
                    //throw new TopLevelPolicyException(mResult.getStatus(), "Multiple main policies are matching");
                }
            } else if ( iResult == MatchResult.INDETERMINATE ) {
                context.closeCurrentEvent();
                context.closeCurrentEvent(new Result(Result.DECISION_INDETERMINATE, context));
                return new PolicyFinderResult(mResult.getStatus());
            }
        }

        if (match == null) {
            context.closeCurrentEvent();
            logger.debug("Found no matching main policy");
            return new PolicyFinderResult();
        }
        context.closeCurrentEvent(match.getId().toString());
        logger.debug("Found one matching main policy: " + match.getId());
        return new PolicyFinderResult(match);
//        } catch (TopLevelPolicyException e) {
//        	logger.error("Could not find Policy (TopLevelPolicyException): " + e.getMessage());
//        	e.printStackTrace();
//            context.closeCurrentEvent();
//            return new PolicyFinderResult(e.getStatus());
//        }
    }

    public boolean addPolicy(String filename) {
        return this.fileNames.add(filename);
    }


    protected void addPolicy(AbstractPolicy policy) {
        URI policyId = policy.getId();
        this.policies.put(policyId,policy);
        if ( policyId.toString().endsWith("main") ) {
            this.mainPolicies.put(policyId, policy);
        }
    }



    protected static boolean getBool(String value) {
        try {
            Boolean val = new Boolean(value);
            return val.booleanValue();
        } catch (Exception e) {
            logger.warn( "Could not read boolean value " + value + "; Using false as default");
            return false;
        }
    }

    @Override
    public boolean isIdReferenceSupported() {
        return true;
    }

    @Override
    public PolicyFinderResult findPolicy(EvaluationCtx context,
                                         URI idReference, int type,
                                         VersionConstraints constraints,
                                         PolicyMetaData parentMetaData) {

        if ( policies.containsKey(idReference)) {
            AbstractPolicy policy = policies.get(idReference);

            if ( constraints.meetsConstraint(policy.getVersion())) {
                return new PolicyFinderResult(policy);
            } else {
                logger.warn("Found policy with right ID " + idReference + ", but version contraints do not match");
            }
        }
        return new PolicyFinderResult();
    }

    /**
     * this method can be used to enforce the usage of lines parsing
     * programatically
     * @param useLines
     */
    public void enforceUseLines() {
        this.enf_useLines = true;
        this.useLines = true;
    }

    public void setConfigurationStore(ConfigurationStore confStore) {
        // save confStore if needed
    }
}
