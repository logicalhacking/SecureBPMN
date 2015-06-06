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

import java.io.PrintStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

import com.sun.xacml.PolicyTreeElement;
import com.sun.xacml.Rule;
import com.sun.xacml.Target;
import com.sun.xacml.TargetMatch;
import com.sun.xacml.cond.Apply;
import com.sun.xacml.cond.Condition;

/**
 *
 * This interface provides some functions to print detected XACML elements to a PrintStream.
 * <br/>
 * Implementing classes may decide which notation (e.g., präfix) is most suitable for backend tools
 *
 */
public interface ExtToolEncoder {
    public void printCondition(Condition cond, PrintStream out);

    public void printApply(Apply apply, PrintStream out);

    public void printTargetMatches(Map<PolicyTreeElement, Map<URI, List<TargetMatch>>> policyMatches,
                                   XACMLTree<PolicyTreeElement> treeElement, PrintStream out);

    public void printTargetMatch(TargetMatch match, PrintStream out);

    public void printConditionAndMatches(Condition cond,
                                         Map<PolicyTreeElement, Map<URI, List<TargetMatch>>> policyMatches,
                                         XACMLTree<PolicyTreeElement> treeElement, PrintStream out);

    public void printRule(Rule rule, PrintStream out, boolean printTarget);

    public void printTarget(Target target, PrintStream out);
}
