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

package eu.aniketos.securebpmn.xacml.cond;

import java.util.Iterator;
import java.util.List;

import eu.aniketos.securebpmn.xacml.AnalysisCtx;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.BooleanAttribute;
import com.sun.xacml.cond.Evaluatable;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.cond.Expression;
import com.sun.xacml.cond.LogicalFunction;

/**
 * todo define and/or
 *
 */
public class AnalysisLogicalFunction extends LogicalFunction {

    public AnalysisLogicalFunction(String functionName) {
        super(functionName);
    }
    // internal identifiers for each of the supported functions
    private static final int ID_OR = 0;
    private static final int ID_AND = 1;

    /**
     * Private helper that looks up the private id based on the function name.
     */
    protected static int getId(String functionName) {
        if (functionName.equals(NAME_OR)) {
            return ID_OR;
        } else if (functionName.equals(NAME_AND)) {
            return ID_AND;
        } else {
            throw new IllegalArgumentException("unknown logical function: " +
                                               functionName);
        }
    }

    /**
     * Evaluate the function, using the specified parameters.
     *
     * @param inputs a <code>List</code> of <code>Evaluatable</code>
     *               objects representing the arguments passed to the function
     * @param context an <code>EvaluationCtx</code> so that the
     *                <code>Evaluatable</code> objects can be evaluated
     * @return an <code>EvaluationResult</code> representing the
     *         function's result
     */
    @Override
    public EvaluationResult evaluate(List<Expression> inputs, EvaluationCtx context) {

        // Evaluate the arguments one by one. As soon as we can
        // return a result, do so. Return Indeterminate if any argument
        // evaluated is indeterminate.
        Iterator<Expression> it = inputs.iterator();
        while (it.hasNext()) {
            Evaluatable eval = (Evaluatable)(it.next());

            // Evaluate the argument
            EvaluationResult result = eval.evaluate(context);
            if (result.indeterminate()) {
                return result;
            }

            AttributeValue value = result.getAttributeValue();
            boolean argBooleanValue = ((BooleanAttribute)value).getValue();

            if (getFunctionId() == ID_OR) {
                if (argBooleanValue &&
                        ! ((AnalysisCtx)context).getEvalInfo().isMissingAttribute()) {
                    // TODO unmark super-element as not abstract
                    return EvaluationResult.getTrueInstance();
                }
            } else if (getFunctionId() == ID_AND) {
                if (!argBooleanValue &&
                        ! ((AnalysisCtx)context).getEvalInfo().isMissingAttribute()) {
                    // TODO unmark super-element as not abstract
                    return EvaluationResult.getFalseInstance();
                }
            }
        }

        if (getFunctionId() == ID_OR) {
            return EvaluationResult.getFalseInstance();
        }
        return EvaluationResult.getTrueInstance();
    }

}
