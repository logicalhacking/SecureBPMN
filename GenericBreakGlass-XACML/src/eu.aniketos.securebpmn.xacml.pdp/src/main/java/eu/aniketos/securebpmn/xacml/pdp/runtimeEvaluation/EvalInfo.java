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

import com.sun.xacml.ctx.Result;

/**
 *
 * Saves information about one policy tree element for abstract evaluation:
 * <ul>
 * <li>Result of the PolicyTreeElement at evaluation</li>
 * <li>Has there been at least one attribute be missing during
 * 		evaluation, and where (in the target, in the condition or in
 * 		a subelement (e.g., for a policy, in a rule)</li>
 * </ul>
 *
 */
public class EvalInfo {

    /**
     * indicates, where the a (missing) attribute is
     */
    public enum MissingAttrType {
        Target,
        Condition,
        subElement
    }


    private Result result;
    private boolean[] missingAttribute = new boolean[MissingAttrType.values().length];
    //private String condition;

    public EvalInfo() {
        for ( int i = 0; i < MissingAttrType.values().length; ++i)  {
            missingAttribute[i] = false;
        }
    }

    public void setMissingAttribtue(MissingAttrType type) {
        missingAttribute[type.ordinal()] = true;
    }

    public boolean isMissingAttribute() {
        for ( int i = 0; i < MissingAttrType.values().length; ++i)  {
            if (missingAttribute[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean isMissingAttribute(MissingAttrType type) {
        return missingAttribute[type.ordinal()];
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
