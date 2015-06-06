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
import java.util.Iterator;
import java.util.List;

import eu.aniketos.securebpmn.xacml.AnalysisCtx;

import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.Rule;
import com.sun.xacml.combine.CombinerElement;
import com.sun.xacml.combine.CombinerParameter;
import com.sun.xacml.combine.PermitOverridesRuleAlg;
import com.sun.xacml.combine.RuleCombinerElement;
import com.sun.xacml.ctx.Result;

public class AnalysisPermitOverridesRuleAlg extends PermitOverridesRuleAlg {

    public AnalysisPermitOverridesRuleAlg() {
        super();

    }

    public void setConfigurationStore(ConfigurationStore conf) {
        //this.conf = conf;
    }

    /**
     * Protected constructor used by the ordered version of this algorithm.
     *
     * @param identifier the algorithm's identifier
     */
    protected AnalysisPermitOverridesRuleAlg(URI identifier) {
        super(identifier);
    }

    public Result combine(EvaluationCtx context, List<CombinerParameter> parameters,
                          List<CombinerElement> ruleElements) {
        boolean atLeastOneError = false;
        boolean potentialPermit = false;
        boolean atLeastOneDeny = false;
        Result firstIndeterminateResult = null;
        Iterator<CombinerElement> it = ruleElements.iterator();

        Result finalResult = null;

        while (it.hasNext()) {
            Rule rule = ((RuleCombinerElement)(it.next())).getRule();
            Result result = rule.evaluate(context);
            int value = result.getDecision();

            // if there was a value of PERMIT, then regardless of what
            // else we've seen, we always return PERMIT
            if (value == Result.DECISION_PERMIT) {
                if ( ((AnalysisCtx)context).getEvalInfo().isMissingAttribute() ) {
                    if ( finalResult == null) {
                        finalResult = result;
                    }
                } else {
                    return result;
                }
            }

            // if it was INDETERMINATE, then we couldn't figure something
            // out, so we keep track of these cases...
            if (value == Result.DECISION_INDETERMINATE) {
                atLeastOneError = true;

                // there are no rules about what to do if multiple cases
                // cause errors, so we'll just return the first one
                if (firstIndeterminateResult == null) {
                    firstIndeterminateResult = result;
                }

                // if the Rule's effect is PERMIT, then we can't let this
                // alg return DENY, since this Rule might have permitted
                // if it could do its stuff
                if (rule.getEffect() == Result.DECISION_PERMIT) {
                    potentialPermit = true;
                }
            } else {
                // keep track of whether we had at least one rule that
                // actually pertained to the request
                if (value == Result.DECISION_DENY) {
                    atLeastOneDeny = true;
                }
            }
        }

        if ( finalResult != null ) {
            return finalResult;
        }

        // we didn't explicitly PERMIT, but we might have had some Rule
        // been evaluated, so we have to return INDETERMINATE
        if (potentialPermit) {
            return firstIndeterminateResult;
        }

        // some Rule said DENY, so since nothing could have permitted,
        // we return DENY
        if (atLeastOneDeny) {
            return new Result(Result.DECISION_DENY,
                              context);
        }

        // we didn't find anything that said DENY, but if we had a
        // problem with one of the Rules, then we're INDETERMINATE
        if (atLeastOneError) {
            return firstIndeterminateResult;
        }

        // if we hit this point, then none of the rules actually applied
        // to us, so we return NOT_APPLICABLE
        return new Result(Result.DECISION_NOT_APPLICABLE,
                          context);
    }

}
