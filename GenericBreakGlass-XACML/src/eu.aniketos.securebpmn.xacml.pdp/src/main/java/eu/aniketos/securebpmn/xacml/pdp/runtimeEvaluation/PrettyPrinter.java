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
import com.sun.xacml.combine.CombinerElement;
import com.sun.xacml.combine.CombinerParameter;
import com.sun.xacml.combine.CombiningAlgorithm;
import com.sun.xacml.cond.Apply;
import com.sun.xacml.cond.Evaluatable;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.cond.Expression;
import com.sun.xacml.cond.Function;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.debug.Locatable;
import com.sun.xacml.debug.RuntimeInfo;

/**
 *
 * Simple class which receives all events from the EvaluationEventHub
 * and prints them in a readable form.
 *
 */
public class PrettyPrinter implements EvaluationEvents {

    private int depth = 0;

    private static String[] foo;

    private static final String ret = "ret:  ", call = "call: ";


    static {
        foo = new String[64];

        foo[0] = "";
        for ( int i = 1; i < 64 ; ++i) {
            foo[i] = foo[i-1] + " ";
        }
    }

    public void reset() {
        depth = 0;
    }

    public void afterEvaluatable(Evaluatable target, EvaluationCtx context,
                                 EvaluationResult result) {
        --depth;
        RuntimeInfo src = target.getRuntimeInfo();
        System.out.println(foo[depth] + ret + target.getClass().getSimpleName() + (src != null ? src.getLocationMsgRuntime() : " (no src)"));
    }

    public void afterFunction(Function target, List<Expression> inputs,
                              EvaluationCtx context, EvaluationResult result) {
        --depth;
        RuntimeInfo src = target.getRuntimeInfo();
        System.out.println(foo[depth] + ret + target.getClass().getSimpleName() + (src != null ? src.getLocationMsgRuntime() : " (no src)"));
    }

    public void afterPolicyTreeElement(PolicyTreeElement target,
                                       EvaluationCtx context, Result result) {
        --depth;
        RuntimeInfo src = ((Locatable) target).getRuntimeInfo();
        System.out.println(foo[depth] + ret + target.getClass().getSimpleName() + (src != null ? src.getLocationMsgRuntime() : " (no src)"));
    }

    public void afterMatch(MatchElement target, EvaluationCtx context,
                           MatchResult result) {
        --depth;
        RuntimeInfo src = ((Locatable) target).getRuntimeInfo();
        System.out.println(foo[depth] + ret + target.getClass().getSimpleName() + (src != null ? src.getLocationMsgRuntime() : " (no src)"));
    }

    public void beforeEvaluatable(Evaluatable target, EvaluationCtx context) {
        RuntimeInfo src = target.getRuntimeInfo();
        System.out.println(foo[depth] + call + target.getClass().getSimpleName() + " " +
                           ( target instanceof Apply ?  ((Apply)target).getFunction().getIdentifier() : target.getType() )+
                           (src != null ? src.getLocationMsgRuntime() : " (no src)"));
        ++depth;
    }

    public void beforeFunction(Function target, List<Expression> inputs,
                               EvaluationCtx context) {
        RuntimeInfo src = target.getRuntimeInfo();
        System.out.println(foo[depth] + call + target.getClass().getSimpleName() + " " +  target.getIdentifier() + (src != null ? src.getLocationMsgRuntime() : " (no src)"));
        ++depth;
    }

    public void beforePolicyTreeElement(PolicyTreeElement target,
                                        EvaluationCtx context) {
        RuntimeInfo src = ((Locatable) target).getRuntimeInfo();
        System.out.println(foo[depth] + call + target.getClass().getSimpleName() + " " +  target.getId() + (src != null ? src.getLocationMsgRuntime() : " (no src)"));
        ++depth;
    }

    public void beforeMatch(MatchElement target, EvaluationCtx context) {
        RuntimeInfo src = ((Locatable) target).getRuntimeInfo();
        System.out.println(foo[depth] + call + target.getClass().getSimpleName() + (src != null ? src.getLocationMsgRuntime() : " (no src)"));
        ++depth;
    }

    public void afterCombiningAlg(CombiningAlgorithm target, EvaluationCtx context,
                                  List<CombinerParameter> parameters, List<CombinerElement> inputs,
                                  Result result) {
        --depth;
        RuntimeInfo src = target.getRuntimeInfo();
        System.out.println(foo[depth] + ret + target.getClass().getSimpleName() + (src != null ? src.getLocationMsgRuntime() : " (no src)"));
    }

    public void beforeCombiningAlg(CombiningAlgorithm target, EvaluationCtx context,
                                   List<CombinerParameter> parameters, List<CombinerElement> inputs) {
        RuntimeInfo src = target.getRuntimeInfo();
        System.out.println(foo[depth] + call + target.getClass().getSimpleName() + (src != null ? src.getLocationMsgRuntime() : " (no src)"));
        ++depth;
    }

}
