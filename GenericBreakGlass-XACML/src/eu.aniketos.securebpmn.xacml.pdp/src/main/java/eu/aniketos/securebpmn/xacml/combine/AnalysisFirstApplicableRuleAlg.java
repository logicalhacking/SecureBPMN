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

import java.util.Iterator;
import java.util.List;

import eu.aniketos.securebpmn.xacml.AnalysisCtx;

import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.Rule;
import com.sun.xacml.combine.CombinerElement;
import com.sun.xacml.combine.CombinerParameter;
import com.sun.xacml.combine.FirstApplicableRuleAlg;
import com.sun.xacml.combine.RuleCombinerElement;
import com.sun.xacml.ctx.Result;


public class AnalysisFirstApplicableRuleAlg extends FirstApplicableRuleAlg {

    @Override
    public Result combine(EvaluationCtx context, List<CombinerParameter> parameters,
                          List<CombinerElement> ruleElements) {

        Result finalResult = null;

        Iterator<CombinerElement> it = ruleElements.iterator();


        while (it.hasNext()) {
            Rule rule = ((RuleCombinerElement)(it.next())).getRule();
            Result result = rule.evaluate(context);
            int value = result.getDecision();

            // in the case of PERMIT, DENY, or INDETERMINATE, we always
            // just return that result, so only on a rule that doesn't
            // apply do we keep going...
            if (value != Result.DECISION_NOT_APPLICABLE) {
                if ( ((AnalysisCtx)context).getEvalInfo().isMissingAttribute()) {
                    // and it was within "this round", thus we will return the result of this
                    if ( finalResult == null ) {
                        finalResult = result;
                    }
                } else {
                    return result;
                }
            }
        }

        if ( finalResult != null ) {
            return finalResult;
        }

        // if we got here, then none of the rules applied
        return new Result(Result.DECISION_NOT_APPLICABLE,
                          context);
    }

    public void setConfigurationStore(ConfigurationStore conf) {
        //this.conf = conf;
    }

}
