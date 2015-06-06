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

package eu.aniketos.securebpmn.xacml.combine;

import java.net.URI;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import eu.aniketos.securebpmn.xacml.AnalysisCtx;

import com.sun.xacml.AbstractPolicy;
import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.MatchResult;
import com.sun.xacml.Obligation;
import com.sun.xacml.combine.CombinerElement;
import com.sun.xacml.combine.CombinerParameter;
import com.sun.xacml.combine.DenyOverridesPolicyAlg;
import com.sun.xacml.combine.PolicyCombinerElement;
import com.sun.xacml.ctx.Result;

public class AnalysisDenyOverridesPolicyAlg extends DenyOverridesPolicyAlg {


    public AnalysisDenyOverridesPolicyAlg() {
        super();
    }

    public void setConfigurationStore(ConfigurationStore conf) {
        //this.conf = conf;
    }

    public AnalysisDenyOverridesPolicyAlg(URI identifier) {
        super(identifier);
    }

    public Result combine(EvaluationCtx context, List<CombinerParameter> parameters,
                          List<CombinerElement> policyElements) {
        boolean atLeastOnePermit = false;
        Set<Obligation> permitObligations = new HashSet<Obligation>();
        Iterator<CombinerElement> it = policyElements.iterator();

        Result finalResult = null;

        while (it.hasNext()) {
            AbstractPolicy policy = ((PolicyCombinerElement) it.next()).getPolicy();

            // make sure that the policy matches the context
            context.newEvent(policy);
            MatchResult match = policy.match(context);

            if (match.getResult() == MatchResult.INDETERMINATE) {
                Result result = new Result(Result.DECISION_DENY, context);
                context.closeCurrentEvent(result);
                if ( ((AnalysisCtx)context).getEvalInfo().isMissingAttribute() ) {
                    if ( finalResult == null ) {
                        finalResult = result;
                    }
                } else {
                    return result;
                }
            }

            if (match.getResult() == MatchResult.NO_MATCH) {
                context.closeCurrentEvent(
                    new Result(Result.DECISION_NOT_APPLICABLE));
            }

            if (match.getResult() == MatchResult.MATCH) {
                // evaluate the policy
                Result result = policy.evaluate(context);
                context.closeCurrentEvent(result);
                // do not treat the discarded values
                if (result != null) {
                    int effect = result.getDecision();

                    // unlike in the RuleCombining version of this alg, we
                    // always return DENY if any Policy returns DENY or
                    // INDETERMINATE
                    if ((effect == Result.DECISION_DENY) ||
                            (effect == Result.DECISION_INDETERMINATE)) {

                        result = new Result(Result.DECISION_DENY,
                                            context,
                                            result.getObligations());

                        if ( ((AnalysisCtx)context).getEvalInfo().isMissingAttribute() ) {
                            if ( finalResult == null ) {
                                finalResult = result;
                            }
                        } else {
                            return result;
                        }

                    }

                    // remember if at least one Policy said PERMIT
                    if (effect == Result.DECISION_PERMIT) {
                        atLeastOnePermit = true;
                        permitObligations.addAll(result.getObligations());
                    }
                }
            }
        }

        if ( finalResult != null ) {
            return finalResult;
        }

        // if we got a PERMIT, return it, otherwise it's NOT_APPLICABLE
        if (atLeastOnePermit) {
            return new Result(Result.DECISION_PERMIT,
                              context,
                              permitObligations);
        }
        return new Result(Result.DECISION_NOT_APPLICABLE,
                          context);
    }
}
