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


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import eu.aniketos.securebpmn.xacml.AnalysisCtx;

import com.sun.xacml.AbstractPolicy;
import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.MatchResult;
import com.sun.xacml.combine.CombinerElement;
import com.sun.xacml.combine.CombinerParameter;
import com.sun.xacml.combine.OnlyOneApplicablePolicyAlg;
import com.sun.xacml.combine.PolicyCombinerElement;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.ctx.Status;

public class AnalysisOnlyOneApplicablePolicyAlg extends
    OnlyOneApplicablePolicyAlg {


    public AnalysisOnlyOneApplicablePolicyAlg() {
        super();
    }

    public void setConfigurationStore(ConfigurationStore conf) {
        //this.conf = conf;
    }

    public Result combine(EvaluationCtx context, List<CombinerParameter> parameters,
                          List<CombinerElement> policyElements) {
        boolean atLeastOne = false;
        AbstractPolicy selectedPolicy = null;
        Iterator<CombinerElement> it = policyElements.iterator();

        Result finalResult = null;

        while (it.hasNext()) {
            AbstractPolicy policy =
                ((PolicyCombinerElement)(it.next())).getPolicy();

            // see if the policy matches the context
            context.newEvent(policy);
            MatchResult match = policy.match(context);

            // if there is an error in trying to match any of the targets,
            // we always return INDETERMINATE immediately
            if (match.getResult() == MatchResult.INDETERMINATE) {
                Result result = new Result(Result.DECISION_INDETERMINATE,
                                           match.getStatus(),
                                           context);
                context.closeCurrentEvent(result);
                if ( ((AnalysisCtx)context).getEvalInfo().isMissingAttribute() ) {
                    if ( finalResult == null) {
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
                // if this isn't the first match, then this is an error
                if (atLeastOne) {
                    List<String> code = new ArrayList<String>();
                    code.add(Status.STATUS_PROCESSING_ERROR);
                    String message = "Too many applicable policies";
                    Result result = new Result(Result.DECISION_INDETERMINATE,
                                               new Status(code, message),
                                               context);
                    context.closeCurrentEvent(result);
                    if ( ((AnalysisCtx)context).getEvalInfo().isMissingAttribute() ) {
                        if ( finalResult == null) {
                            finalResult = result;
                        }
                    } else {
                        return result;
                    }
                } else {
                    // if this was the first applicable policy in the set, then
                    // remember it for later
                    atLeastOne = true;
                    selectedPolicy = policy;
                    context.closeCurrentEvent("Evaluated later");
                }
            }
        }

        if ( finalResult != null ) {
            return finalResult;
        }

        // if we got through the loop and found exactly one match, then
        // we return the evaluation result of that policy
        if (atLeastOne) {
            context.newEvent(selectedPolicy);
            Result result = selectedPolicy.evaluate(context);
            context.closeCurrentEvent(result);
            // do not treat the discarded values
            if (result != null) {
                return result;
            }
        }

        // if we didn't find a matching policy, then we don't apply
        return new Result(Result.DECISION_NOT_APPLICABLE,
                          context);
    }


}
