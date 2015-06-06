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

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNException;

import eu.aniketos.securebpmn.xacml.SVNPDPConfig;
import eu.aniketos.securebpmn.xacml.support.finder.IPDPStateEvaluationContext;

import com.sun.xacml.AbstractPolicy;
import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.PolicyMetaData;
import com.sun.xacml.VersionConstraints;
import com.sun.xacml.ctx.Status;
import com.sun.xacml.finder.PolicyFinder;
import com.sun.xacml.finder.PolicyFinderResult;

/**
 *
 * This class retrieves, in contrast to the super class SVNPolicyFinderModule,
 * the information which svn version to use from the context, which has to be
 * a IPDPStateEvaluationContext. For this, a "main" instance manages all "sub"
 * instances which hold a specific version.
 * <br/>
 * This class may be enhanced for productive usage, i.e., check if a new policy
 * version will become active in a specific time frame, remove policies which will
 * not be used any more from the cache, allow to trigger a load operation to
 * circumvent delays for requests first using a new policy version.
 *
  */
public class SVNStatePolicyFinderModule  extends SVNPolicyFinderModule {

    /*
     * there typ "types" of this class: the main instance manages
     * all sub instances, i.e., a instance has either the policyCache
     * or the version set to a reasonable value
     */
    private Map<Long, SVNStatePolicyFinderModule> policyCache = null;
    private PolicyFinder finder;

    private long version = -1;

    private static final Logger logger = Logger.getLogger(SVNStatePolicyFinderModule.class);


    @Override
    public void init(PolicyFinder finder) {
        logger.debug("Creating " + SVNStatePolicyFinderModule.class.getSimpleName() + " as main instance");
        this.finder = finder;
        policyCache = new HashMap<Long, SVNStatePolicyFinderModule>();
        // do the init, e.g., get svn client
        super.svnInit(finder);
    }


    protected synchronized SVNStatePolicyFinderModule loadVersion(Long version) {
        if ( policyCache.containsKey(version)) {
            return policyCache.get(version);
        } else {
            SVNStatePolicyFinderModule module = new SVNStatePolicyFinderModule(finder, svn, version.longValue());
            policyCache.put(version, module);
            return module;
        }
    }


    protected SVNStatePolicyFinderModule(PolicyFinder finder, MySVNClient client, long version) {
        logger.debug("Creating " + SVNStatePolicyFinderModule.class.getSimpleName() + " as sub instance");
        super.svn = client;

        super.svnInit(finder);
        // create a buffer which can be used for all coming read operations
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        List<String> policyFileNames = null;
        try {
            policyFileNames = super.getPolicyFileNames(version, buffer);
        } catch (SVNException e) {
            logger.error("SVNException when reading file " + SVNPDPConfig.POLICY_FILE + " from repository: " + e.getMessage() + ", will not be able to retrieve policies from SVN storage");
            policyFileNames = new Vector<String>();
        }

        List<AbstractPolicy> policies = getPolicies(finder, policyFileNames, version, buffer);
        for ( AbstractPolicy policy : policies ) {
            super.addPolicy(policy);
        }
        super.svn = null;
    }


    @Override
    public PolicyFinderResult findPolicy(EvaluationCtx context) {
        // check if we are the "main" finder which manages the policy cache
        if ( policyCache != null ) {
            if ( context instanceof IPDPStateEvaluationContext ) {
                IPDPStateEvaluationContext stateContext = (IPDPStateEvaluationContext) context;

                SVNStatePolicyFinderModule module = policyCache.get(new Long(stateContext.getVersion()));
                if ( module == null ) {
                    module = loadVersion(new Long(version));
                }
                return module.findPolicy(context);
            } else {
                return new PolicyFinderResult(Status.createStatus(Status.STATUS_PROCESSING_ERROR, "SVNStatePolicyFinderModule requires IPDPStateEvaluationContext"));
            }
        } else {
            // we are a "sub" finder, i.e., we can use our super class impl to find what we neeed
            return super.findPolicy(context);
        }
    }

    @Override
    public PolicyFinderResult findPolicy(EvaluationCtx context,
                                         URI idReference, int type,
                                         VersionConstraints constraints,
                                         PolicyMetaData parentMetaData) {

        // check if we are the "main" finder which manages the policy cache
        if ( policyCache != null ) {
            // we do not need to do checks any more - they must have been failed when searching the main policy
            return policyCache.get(new Long( ((IPDPStateEvaluationContext) context).getVersion())).
                   findPolicy(context, idReference, type, constraints, parentMetaData);
        } else {
            return super.findPolicy(context, idReference, type, constraints, parentMetaData);
        }
    }


//	private long getCurrentPolicyVersion(AttributeFinder finder) {
//		try {
//			BasicEvaluationCtx ctx = new BasicEvaluationCtx(new RequestCtx(new HashSet<RequestElement>(), null, null));
//			EvaluationResult evalResult = finder.findAttribute(Constants.ENVIRONMENT_CAT, TypeIdentifierConstants.INTEGER_URI, PDPSTATE_URI, null, ctx);
//
//			return getCurrentPolicyVersion(evalResult);
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (ParsingException e) {
//			e.printStackTrace();
//		}
//		return -1;
//	}
//
//	public static long getCurrentPolicyVersion(EvaluationCtx context) {
//		return getCurrentPolicyVersion(
//				context.getAttribute(
//						Constants.ENVIRONMENT_CAT, TypeIdentifierConstants.INTEGER_URI, PDPSTATE_URI, null));
//	}
//
//	private static long getCurrentPolicyVersion(EvaluationResult evalResult) {
//		if ( !evalResult.getAttributeValue().isBag() ||
//				((BagAttribute) evalResult.getAttributeValue()).size() != 1 ) {
//			logger.error("Did not retreive a bag with one (" +((BagAttribute) evalResult.getAttributeValue()).size() +
//					") entry after attribute search for current svn policy version number " +
//					"SVNStatePolicyFinderModule requires exactly one attribute to be defined");
//			return -1;
//		}
//		IntegerAttribute attrVal = (IntegerAttribute) ((BagAttribute) evalResult.getAttributeValue()).iterator().next();
//
//		return attrVal.getValue();
//	}
//

    //TODO create policy cache
    // get policies in correct version from policy cache - key is svn ID
    //
    // TODO create a time stamp for when the active policy version was checked
}
