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

package eu.aniketos.securebpmn.xacml.support.comb;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.support.AttributeResolver;

import com.sun.xacml.AbstractPolicy;
import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.MatchResult;
import com.sun.xacml.Obligation;
import com.sun.xacml.PolicyTreeElement;
import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.combine.CombinerElement;
import com.sun.xacml.combine.CombinerParameter;
import com.sun.xacml.combine.PolicyCombiningAlgorithm;
import com.sun.xacml.ctx.Result;

public class LatticeCombiningAlgorithm extends PolicyCombiningAlgorithm {


    public static final String algId =
        "urn:policy-combining-algorithm:policy-lattice";

    private static URI identifierURI = URI.create(algId);

    private static final String LATTICE_IDENTIFIER = "urn:policyLattice:latticeIdentifier";

    private static Logger logger = Logger.getLogger(LatticeCombiningAlgorithm.class);

    private Map<String, Map<Long, PolicyLattice>> latticeCache = new HashMap<String, Map<Long, PolicyLattice>>();

    public LatticeCombiningAlgorithm() {
        super(identifierURI);
    }

    @Override
    public Result combine(EvaluationCtx evalCtx, List<CombinerParameter> combParam,
                          List<CombinerElement> combElem) {

        // store the first deny we get, we will return it in case we do not find a permit
        Result firstDeny = null;
        // store the first indeterminate
        Result firstIndeterminate = null;

        // create a cache where all permits are stored;
        // we may need the result itself to get the obligations
        Map<String, Result> permitCache = new HashMap<String, Result>();
        // create a cache where the results of all deny policies are stored
        // deny policies could be executed several time, i.e.,
        // remember both the result and if we executed it
        Map<String, Result> denyCache = new HashMap<String, Result>();

        // get the active policies from the context once
        Set<String> activePolicies = getActivePolicies(evalCtx);

        // create (or get from cache) the policy lattice we want to evaluate
        PolicyLattice lattice = getLattice(evalCtx, combParam, combElem);

        // iterate over the lattice (i.e., level per level)
        for ( LatticeElem elem : lattice ) {
            // check if current element is an active policy
            if ( isActive(elem, lattice, activePolicies) ) {
                boolean permitted = false, denied = false;

                // store a permit if we find it in this policy
                Result permit = null;
                // first, check for an inherited permit, then for a permit
                LatticeElem inhPermit = getInheritedPermit(elem, permitCache);
                if ( inhPermit != null ) {
                    permitted = true;
                } else if (elem.getPermitPolicy() != null ) {
                    // no inherited permit, we have to evaluate the current permit policy
                    Result res = evaluate(elem.getPermitPolicy(), evalCtx);
                    if ( res.getDecision() == Result.DECISION_INDETERMINATE ) {
                        if ( firstIndeterminate == null ) {
                            firstIndeterminate = res;
                        }
                        continue; // this policy will not provide a result, go to next
                    } else if ( res.getDecision() == Result.DECISION_PERMIT ) {
                        // we found a permit, store it to cache (in the current policy it may be overwritten by a deny)
                        permitCache.put(elem.getIdentifier(), res);
                        permit = res;
                        permitted = true;
                    } else if ( res.getDecision() == Result.DECISION_DENY && firstDeny == null) {
                        firstDeny = res;
                        continue; // we do not need to evaluate the deny policies if we do not have a permit
                    }
                }

                if ( permitted ) {
                    // check if there is an inherited deny: iterate over all extending policies, i.e, downwards
                    for ( LatticeElem extending : elem.downwards() ) {
                        // check if extending policy has a deny policy defined
                        if ( extending.getDenyPolicy() != null ) {
                            Result res;
                            // check if we already evaluated this policy
                            if ( denyCache.containsKey(extending.getIdentifier()) ) {
                                res = denyCache.get(extending.getIdentifier());
                            } else {
                                // else, evaluate it and store it into the cache
                                res = evaluate(extending.getDenyPolicy(), evalCtx);
                                denyCache.put(extending.getIdentifier(), res);
                            }
                            // check if the current deny policy denies this request
                            if ( res.getDecision() == Result.DECISION_INDETERMINATE ) {
                                if ( firstIndeterminate == null ) {
                                    firstIndeterminate = res;
                                }
                            } else if ( res.getDecision() == Result.DECISION_DENY ) {
                                denied = true;
                                if ( firstDeny == null ) {
                                    firstDeny = res;
                                }
                                break; // we have found a deny, do not need to look further
                            }
                        }
                    }
                    // we end up here only if we found a permit, check if we also found a deny
                    if ( ! denied) {
                        // if we did not find a permit for this policy
                        if ( permit == null ) {
                            // we got an inherited permit: we have to remove the lattice obligations
                            // from this permit and attach the lattice obligations of the current policy
                            permit = permitCache.get(inhPermit.getIdentifier());
                            AbstractPolicy curPolicy = (AbstractPolicy) elem.getPermitPolicy();
                            AbstractPolicy inhPolicy = (AbstractPolicy) inhPermit.getPermitPolicy();

                            Set<Obligation> obligations = permit.getObligations();

                            // remove obligations defined by inhPolicy
                            for ( Obligation oblgRm : inhPolicy.getObligations() ) {
                                for ( Obligation oblg : obligations ) {
                                    if ( oblg.getId().equals(oblgRm) ) {
                                        obligations.remove(oblg);
                                        break;
                                    }
                                }
                            }
                            // add obligations from current policy
                            for ( Obligation oblg : curPolicy.getObligations() ) {
                                obligations.add(oblg.evaluate(evalCtx));
                            }
                            permit = new Result(permit.getDecision(), evalCtx, obligations);
                        }
                        return permit;
                    }
                }
            }
        }
        if ( firstIndeterminate != null ) {
            return firstIndeterminate;
        } else if ( firstDeny != null ) {
            return firstDeny;
        } else {
            return new Result(Result.DECISION_DENY, evalCtx);
        }
    }

    private Result evaluate(PolicyTreeElement policy, EvaluationCtx evalCtx) {

        evalCtx.newEvent(policy);

        MatchResult match = policy.match(evalCtx);
        Result res = null;

        if (match.getResult() == MatchResult.INDETERMINATE) {
            res = new Result(Result.DECISION_INDETERMINATE, evalCtx);
        } else if (match.getResult() == MatchResult.NO_MATCH) {
            res = new Result(Result.DECISION_NOT_APPLICABLE, evalCtx);
        } else if (match.getResult() == MatchResult.MATCH) {
            res = policy.evaluate(evalCtx);
        }
        evalCtx.closeCurrentEvent(res);
        return res;
    }

    private boolean isActive(LatticeElem elem, PolicyLattice lattice, Set<String> activePolicies) {
        if ( activePolicies.contains(elem.getIdentifier())) {
            return true;
        } else {
            for ( LatticeElem extending : elem.downwards() ) {
                if ( activePolicies.contains(extending.getIdentifier())) {
                    return true;
                }
            }
        }
        return false;
    }

    private LatticeElem getInheritedPermit(LatticeElem elem, Map<String, Result> permitCache) {
        for ( LatticeElem extended : elem.upwards() ) {
            if ( permitCache.containsKey(extended)) {
                return extended;
            }
        }
        return null;
    }



    private PolicyLattice getLattice(EvaluationCtx evalCtx, List<CombinerParameter> combParam,
                                     List<CombinerElement> combElem) {


        String latticeId = getLatticeIdentifier(combParam);

        PolicyLattice lattice = null;
        if ( latticeId != null) {
            Map<Long, PolicyLattice> latticeVerCache = null;
            if ( latticeCache.containsKey(latticeId) ) {
                latticeVerCache = latticeCache.get(latticeId);
            } else {
                latticeVerCache = new HashMap<Long, PolicyLattice>();
                latticeCache.put(latticeId, latticeVerCache);
            }

            Long policyVers = getPolicyVersion(evalCtx);
            if ( latticeVerCache.containsKey(policyVers)) {
                return latticeVerCache.get(policyVers);
            } else {
                lattice =  new PolicyLattice(latticeId, combElem);
                latticeVerCache.put(policyVers, lattice);
                return lattice;
            }
        } else {
            return new PolicyLattice(null, combElem);
        }

//		if ( latticeCache.containsKey(policyVers) &&
//			 latticeCache.get(policyVers).containsKey(latticeId) ) {
//			lattice = latticeCache.get(policyVers).get(latticeId);
//		} else {
//			Map<String, PolicyLattice> latticeVerCache = null;
//			if ( latticeCache.containsKey(policyVers) ) {
//				latticeVerCache = latticeCache.get(policyVers);
//			} else {
//				latticeVerCache = new HashMap<String, PolicyLattice>();
//				latticeCache.put(policyVers, latticeVerCache);
//			}
//			lattice =  new PolicyLattice(latticeId, combElem);
//			latticeVerCache.put(latticeId, lattice);
//		}
//		return lattice;
    }


    private String getLatticeIdentifier(List<CombinerParameter> combParam) {

        if ( combParam != null && combParam.size() > 0 ) {
            for ( CombinerParameter param : combParam ) {
                if ( LATTICE_IDENTIFIER.equals(param.getName()) ) {
                    if ( param.getValue() instanceof StringAttribute ) {
                        return ((StringAttribute)param.getValue()).getValue();
                    } else {
                        logger.warn("Found " + LATTICE_IDENTIFIER + " which is not of type string (" + param.getValue().encode() + ")");
                    }
                }
            }
        }

        logger.warn("Did not find a " + LATTICE_IDENTIFIER + " for evaluating the LatticeCombiningAlgorithm " +
                    (getRuntimeInfo() == null ? "" : getRuntimeInfo().getLocationMsgRuntime()));
        return null;
    }

    private Long getPolicyVersion(EvaluationCtx evalCtx) {
        return new Long(AttributeResolver.getPDPStatePolicyVersion(evalCtx));
    }


    private Set<String> getActivePolicies(EvaluationCtx evalCtx) {
        return AttributeResolver.getActivePolicies(evalCtx);
    }


}
