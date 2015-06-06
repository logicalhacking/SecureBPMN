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

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.attributes.AttributeHelper;

import com.sun.xacml.PolicyTreeElement;
import com.sun.xacml.Rule;
import com.sun.xacml.Target;
import com.sun.xacml.TargetMatch;
import com.sun.xacml.TargetMatchGroup;
import com.sun.xacml.TargetSection;
import com.sun.xacml.attr.AttributeDesignator;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.cond.Apply;
import com.sun.xacml.cond.Condition;
import com.sun.xacml.cond.Evaluatable;
import com.sun.xacml.cond.Expression;
import com.sun.xacml.cond.Function;

public class PraefixEncoder implements ExtToolEncoder {

    private ExtToolTypeEncoder typeEnc;
    private KnownAttrStore attrResolver;

    private static Logger logger = Logger.getLogger(PraefixEncoder.class);

    public PraefixEncoder(ExtToolTypeEncoder typeEnc, KnownAttrStore attrResolver) {
        this.typeEnc = typeEnc;
        this.attrResolver = attrResolver;
    }


    public void printApply(Apply apply, PrintStream out) {
        typeEnc.printApply(apply, out);
    }

    public void printRule(Rule rule, PrintStream out, boolean printTarget) {
        boolean enclosingAnd = printTarget && rule.getCondition() != null;
        if (enclosingAnd ) {
            out.print(typeEnc.getAND() + "(");
        }


        if ( printTarget ) {
            printTarget(rule.getTarget(), out);
        }

        if ( rule.getCondition() != null ) {
            if ( printTarget ) {
                out.print(typeEnc.getAND() + "(");
            }
            printCondition(rule.getCondition(), out);
            if ( printTarget ) {
                out.print(")");
            }
        }
        if (enclosingAnd ) {
            out.print(")");
        }
    }


    public void printCondition(Condition cond, PrintStream out) {
        Expression expr = cond.getExpression();
        if ( ! ( expr instanceof Apply )) {
            throw new RuntimeException("Currently expression for Conditions not being an Apply are not support");
        }
        printApply( (Apply) expr, out);
    }

    public void printTarget(Target target, PrintStream out) {
        if ( target.getTargetSections().size() > 0 ) {
            if ( target.getTargetSections().size() > 1 ) {
                out.print(typeEnc.getAND() + "(");
            }
            for ( TargetSection section : target.getTargetSections() ) {
                printTargetSection(section, out);
            }
            if ( target.getTargetSections().size() > 1 ) {
                out.print(") ");
            }
        }
    }

    private void printTargetSection(TargetSection section, PrintStream out) {
        if ( section.getMatchGroups().size() > 0 ) {
            if ( section.getMatchGroups().size() > 1 ) {
                out.print(typeEnc.getOR() + "(");
            }
            for ( TargetMatchGroup group :  section.getMatchGroups()) {
                printTargetGroup(group, out);
            }
            if ( section.getMatchGroups().size() > 1 ) {
                out.print(") ");
            }
        }

    }

    private void printTargetGroup(TargetMatchGroup group, PrintStream out) {
        if ( group.getMatches().size() > 0 ) {
            if ( group.getMatches().size() > 1 ) {
                out.print(typeEnc.getAND() + "(");
            }
            for ( TargetMatch match :  group.getMatches()) {
                printTargetMatch(match, out);
            }
            if ( group.getMatches().size() > 1 ) {
                out.print(") ");
            }
        }
    }


    /**
     * recursively print AND combined target matches of all enclosing PolicyTreeElements
     * @param treeElement
     * @param out
     */
    public void printTargetMatches(Map<PolicyTreeElement, Map<URI, List<TargetMatch>>> policyMatches,
                                   XACMLTree<PolicyTreeElement> treeElement, PrintStream out) {
        printTargetMatches(policyMatches, treeElement, out, true);
    }

    private boolean printTargetMatches(Map<PolicyTreeElement, Map<URI, List<TargetMatch>>> policyMatches,
                                       XACMLTree<PolicyTreeElement> treeElement, PrintStream out, boolean isFirst) {
        // print in format
        // AND (treeElement.category1.match OR rule.category1.match) AND (treeElement.category2.match OR ...)

        if (treeElement.getFather() != null && treeElement.getFather().getElement() != null) {
            isFirst = printTargetMatches(policyMatches, treeElement.getFather(), out, isFirst);
        }

        PolicyTreeElement element = treeElement.getElement();
        Map<URI, List<TargetMatch>> matches =  policyMatches.get(element);

        for ( URI category : matches.keySet()) {
            if ( isFirst ) {
                isFirst = false;
            } else {
                out.print(typeEnc.getAND());
            }
            out.print(" ( ");
            List<TargetMatch> matches2 = matches.get(category);
            for ( int i = 0;  i < matches2.size(); ++i ) {
                if ( i > 0 ) {
                    out.print(" "+ typeEnc.getOR() + " ");
                }
                printTargetMatch(matches2.get(i), out);
            }
            out.print(" ) ");
        }
        return isFirst;
    }

    /**
     * print target match
     * @param match
     * @param out
     */
    public void printTargetMatch(TargetMatch match, PrintStream out) {
        AttributeValue attrVal = match.getMatchValue();
        Function func = match.getMatchFunction();
        Evaluatable eval = match.getMatchEvaluatable();
        AttributeDesignator attr = null;
        if ( ! (eval instanceof AttributeDesignator) ) {
            logger.error("Currently, only AttributeDesignators are supported for TargetMatches");
            throw new RuntimeException("Currently, only AttributeDesignators are supported for TargetMatches");
        } else {
            attr = (AttributeDesignator) eval;
        }
        AttributeIdentifier attrId = AttributeHelper.getAttributeIdentifier(attr);
        AttributeValue attrValRes = attrResolver.getAttributeValue(attrId);

        out.print(typeEnc.getFunctionEnc(func) +
                  "(" + typeEnc.getAttrValueEnc(attrVal) + " " +
                  typeEnc.getAttrDesignatorEnc(attr, attrValRes) + ") ");
    }


    public void printConditionAndMatches(Condition cond,
                                         Map<PolicyTreeElement, Map<URI, List<TargetMatch>>> policyMatches,
                                         XACMLTree<PolicyTreeElement> treeElement, PrintStream out) {

        boolean isFirst = printTargetMatches(policyMatches, treeElement, out, true);

        if ( ! isFirst ) {
            out.print(typeEnc.getAND() + "( ");
        }

        printCondition(cond, out);

        if ( ! isFirst ) {
            out.print(")");
        }
    }
}
