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
import com.sun.xacml.cond.Evaluatable;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.cond.Expression;
import com.sun.xacml.cond.Function;
import com.sun.xacml.ctx.Result;

/**
 *
 * This class defines the interface for retrieving events from the evaluation engine.
 * Retrieved are "evaluate" and "match" Functions of objects, which implement the
 * Locatable Interface.
 * <br/>
 * There are 5 types of elements for which events are retrieved, whereas all those "target"
 * types implement the com.sun.xacml.finder.Locatable interface
 * <ul>
 * <li><b>PolicyTreeElement:</b> which includes Policy, PolicySet, PolicyReference, Rule</li>
 * <li><b>Evaluatable:</b> which includes AttributeDesignator, AttributeSelector,
 * 		AttributeValue, VariableReference, Condition, Apply</li>
 * <li><b>Target:</b> includes Target, TargetMatch</li>
 * <li><b>Function:</li> includes all registered XACML functions
 * <li><b>Obligation:</b> TODO</li>
 * </ul>
 *
 */
public interface EvaluationEvents {

    // POLICY TREE ELEMENT
    // including Policy, PolicySet, PolicyReference, Rule

    void beforePolicyTreeElement(PolicyTreeElement target, EvaluationCtx context);

    void afterPolicyTreeElement(PolicyTreeElement target, EvaluationCtx context, Result result);


    // EVALUATEABLE

    void beforeEvaluatable(Evaluatable target, EvaluationCtx context);

    void afterEvaluatable(Evaluatable target, EvaluationCtx context, EvaluationResult result);


    // TARGET

    void beforeMatch(MatchElement target, EvaluationCtx context);

    void afterMatch(MatchElement target, EvaluationCtx context, MatchResult result);


    // FUNCTION

    void beforeFunction(Function target, List<Expression> inputs, EvaluationCtx context);

    void afterFunction(Function target, List<Expression> inputs, EvaluationCtx context, EvaluationResult result);

    // COMBINING_ALG

    void beforeCombiningAlg(CombiningAlgorithm target, EvaluationCtx context, List<CombinerParameter> parameters, List<CombinerElement> inputs);

    void afterCombiningAlg(CombiningAlgorithm target, EvaluationCtx context, List<CombinerParameter> parameters, List<CombinerElement> inputs, Result result);


}
