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
import com.sun.xacml.combine.PermitOverridesPolicyAlg;
import com.sun.xacml.combine.PolicyCombinerElement;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.ctx.Status;

public class AnalysisPermitOverridesPolicyAlg extends PermitOverridesPolicyAlg {

    public AnalysisPermitOverridesPolicyAlg() {
        super();
    }

    public void setConfigurationStore(ConfigurationStore conf) {
        //this.conf = conf;
    }

    public AnalysisPermitOverridesPolicyAlg(URI identifier) {
        super(identifier);
    }

    public Result combine(EvaluationCtx context, List<CombinerParameter> parameters,
                          List<CombinerElement> policyElements) {
        boolean atLeastOneError = false;
        boolean atLeastOneDeny = false;
        Set<Obligation> denyObligations = new HashSet<Obligation>();
        Status firstIndeterminateStatus = null;
        Iterator<CombinerElement> it = policyElements.iterator();

        Result finalResult = null;

        while (it.hasNext()) {
            AbstractPolicy policy =
                ((PolicyCombinerElement)(it.next())).getPolicy();

            // make sure that the policy matches the context
            context.newEvent(policy);
            MatchResult match = policy.match(context);

            if (match.getResult() == MatchResult.INDETERMINATE) {
                context.closeCurrentEvent(
                    new Result(Result.DECISION_INDETERMINATE, context));
                atLeastOneError = true;

                // keep track of the first error, regardless of cause
                if (firstIndeterminateStatus == null) {
                    firstIndeterminateStatus = match.getStatus();
                }
            } else if (match.getResult() == MatchResult.NO_MATCH) {
                context.closeCurrentEvent(
                    new Result(Result.DECISION_NOT_APPLICABLE));
            } else if (match.getResult() == MatchResult.MATCH) {
                // now we evaluate the policy
                Result result = policy.evaluate(context);
                context.closeCurrentEvent(result);

                // do not treat the discarded values
                if (result != null) {
                    int effect = result.getDecision();

                    // this is a little different from DenyOverrides...
                    if (effect == Result.DECISION_PERMIT) {
                        if ( ((AnalysisCtx)context).getEvalInfo().isMissingAttribute() ) {
                            if ( finalResult == null ) {
                                finalResult = result;
                            }
                        } else {
                            return result;
                        }
                    }

                    if (effect == Result.DECISION_DENY) {
                        atLeastOneDeny = true;
                        denyObligations.addAll(result.getObligations());
                    } else if (effect == Result.DECISION_INDETERMINATE) {
                        atLeastOneError = true;

                        // keep track of the first error, regardless of cause
                        if (firstIndeterminateStatus == null) {
                            firstIndeterminateStatus = result.getStatus();
                        }
                    }
                }
            }
        }

        if ( finalResult != null ) {
            return finalResult;
        }

        // if we got a DENY, return it
        if (atLeastOneDeny) {
            return new Result(Result.DECISION_DENY,
                              context,
                              denyObligations);
        }

        // if we got an INDETERMINATE, return it
        if (atLeastOneError) {
            return new Result(Result.DECISION_INDETERMINATE,
                              firstIndeterminateStatus,
                              context);
        }

        // if we got here, then nothing applied to us
        return new Result(Result.DECISION_NOT_APPLICABLE,
                          context);
    }
}
