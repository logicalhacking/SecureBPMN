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

package eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation;

import java.util.List;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.MatchElement;
import com.sun.xacml.MatchResult;
import com.sun.xacml.PolicyTreeElement;
import com.sun.xacml.Target;
import com.sun.xacml.combine.CombinerElement;
import com.sun.xacml.combine.CombinerParameter;
import com.sun.xacml.combine.CombiningAlgorithm;
import com.sun.xacml.cond.Evaluatable;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.cond.Expression;
import com.sun.xacml.cond.Function;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.debug.RuntimeInfo.ELEMENT_TYPE;

/**
 * currently empty stub class... will in some future provide a more
 * or less sophisticated interface for retrieving evaluation events.
 * For now, use EvaluationEventHub
 *
 */
public class EvaluationEventRegistry implements EvaluationEvents {

    enum EVAL_TIME {
        BEFORE,
        AFTER,
        BOTH
    }


    public EvaluationEventRegistry(EvaluationEventHub eventHub) {
        eventHub.register(this);
    }



    public void subscribe(EVAL_TIME time, ELEMENT_TYPE type) {

    }




    public void afterEvaluatable(Evaluatable target, EvaluationCtx context,
                                 EvaluationResult result) {
        // TODO Auto-generated method stub

    }

    public void afterFunction(Function target, List<Expression> inputs,
                              EvaluationCtx context, EvaluationResult result) {
        // TODO Auto-generated method stub

    }

    public void afterPolicyTreeElement(PolicyTreeElement target,
                                       EvaluationCtx context, Result result) {
        // TODO Auto-generated method stub

    }

    public void afterTarget(Target target, EvaluationCtx context,
                            MatchResult result) {
        // TODO Auto-generated method stub

    }

    public void beforeEvaluatable(Evaluatable target, EvaluationCtx context) {
        // TODO Auto-generated method stub

    }

    public void beforeFunction(Function target, List<Expression> inputs,
                               EvaluationCtx context) {
        // TODO Auto-generated method stub

    }

    public void beforePolicyTreeElement(PolicyTreeElement target,
                                        EvaluationCtx context) {
        // TODO Auto-generated method stub

    }

    public void beforeTarget(Target target, EvaluationCtx context) {
        // TODO Auto-generated method stub

    }

    public void afterCombiningAlg(CombiningAlgorithm target, EvaluationCtx context,
                                  List<CombinerParameter> parameters, List<CombinerElement> inputs,
                                  Result result) {
        // TODO Auto-generated method stub

    }

    public void beforeCombiningAlg(CombiningAlgorithm target, EvaluationCtx context,
                                   List<CombinerParameter> parameters, List<CombinerElement> inputs) {
        // TODO Auto-generated method stub

    }

    public void afterMatch(MatchElement target, EvaluationCtx context,
                           MatchResult result) {
        // TODO Auto-generated method stub

    }

    public void beforeMatch(MatchElement target, EvaluationCtx context) {
        // TODO Auto-generated method stub

    }


}
