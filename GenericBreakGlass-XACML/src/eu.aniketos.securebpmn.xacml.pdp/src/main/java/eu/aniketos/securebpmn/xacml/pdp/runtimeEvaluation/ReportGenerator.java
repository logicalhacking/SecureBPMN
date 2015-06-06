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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.EvalInfo.MissingAttrType;

import com.sun.xacml.AbstractPolicy;
import com.sun.xacml.PolicyTreeElement;
import com.sun.xacml.Rule;
import com.sun.xacml.ctx.Result;

public class ReportGenerator {

    private static final Logger logger = Logger.getLogger(ReportGenerator.class);

    private ExtToolTypeEncoder typeEnc = HOLEncoder.getInstance();
    private InfoTree<PolicyTreeElement, EvalInfo> tree;
    //private KnownAttrStore attrProv;
    private ExtToolEncoder enc;

    private int depth;
    private static String[] spaces;
    static {
        spaces = new String[16];
        spaces[0] = "";
        for (int i = 1; i < 16; ++i ) {
            spaces[i] = spaces[i-1] + " ";
        }
    }

    public ReportGenerator(KnownAttrStore attrProv, InfoTree<PolicyTreeElement, EvalInfo> tree) {
        typeEnc.setKnownAttrStore(attrProv);
        //this.attrProv = attrProv;
        this.tree = tree;
        this.enc = new PraefixEncoder(typeEnc, attrProv);
    }

    public String reportMissingAttr() {

        ByteArrayOutputStream bOS = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bOS);

        depth = 0;
        //InfoTree<PolicyTreeElement, EvalInfo> tree = infoProv.getTree();
        if ( tree.getChilds() != null ) {
            for ( InfoTree<PolicyTreeElement, EvalInfo> child : tree.getChilds() ) {
                report(child, enc, out);
            }
        }
        out.flush();
        return bOS.toString();
    }

    private void report(InfoTree<PolicyTreeElement, EvalInfo> tree, ExtToolEncoder enc, PrintStream out) {
        PolicyTreeElement elem = tree.getElement();
        EvalInfo info = tree.getInfo();
        if ( info.isMissingAttribute() ) {
            // for rules, we print the condition (with the missing attribute)
            if ( elem instanceof Rule ) {
                Rule rule = (Rule) elem;
                out.print(spaces[depth]);
                enc.printRule(rule, out, info.isMissingAttribute(MissingAttrType.Target));
                out.println(" -> " + typeEnc.getDecision(rule.getEffect()));
            }
            // for policies, we print the used comibing alg + all sub elements
            else if ( elem instanceof AbstractPolicy ) {
                AbstractPolicy policy = (AbstractPolicy) elem;
                out.println(spaces[depth] + typeEnc.getCombiningAlg(policy.getCombiningAlg()) + " (");

                ++depth;
                for ( InfoTree<PolicyTreeElement, EvalInfo> child : tree.getChilds() ) {
                    report(child, enc, out);
                }
                --depth;
                out.println(spaces[depth] + ")");
            }
            else {
                logger.warn("Unkown PolicyTreeElement: " + elem.getClass());
            }
        } else {
            // did this element match?
            if ( isMatch(info.getResult()) ) {
                out.println(spaces[depth] + "true -> " + typeEnc.getDecision(info.getResult().getDecision()));
            } else {
                out.println(spaces[depth] + typeEnc.getDecision(info.getResult().getDecision()));
            }
            //if no attribute was missing, we take the given decision
        }
    }

    private boolean isMatch(Result result) {
        if ( result.getDecision() == Result.DECISION_PERMIT || result.getDecision() == Result.DECISION_DENY) {
            return true;
        } else {
            return false;
        }

    }
}
