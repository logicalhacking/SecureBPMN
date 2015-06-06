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

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.attributes.AttributeHelper;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.MatchElement;
import com.sun.xacml.MatchResult;
import com.sun.xacml.PolicyTreeElement;
import com.sun.xacml.Rule;
import com.sun.xacml.TargetMatch;
import com.sun.xacml.attr.AttributeDesignator;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.combine.CombinerElement;
import com.sun.xacml.combine.CombinerParameter;
import com.sun.xacml.combine.CombiningAlgorithm;
import com.sun.xacml.cond.Evaluatable;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.cond.Expression;
import com.sun.xacml.cond.Function;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.ctx.Status;
import com.sun.xacml.debug.Locatable;


/*
 * Rule: TM => TRUE && Condition => fehlt attribut:
 *    => condition -> effect
 * alles andere: wenn matched + effect eindeutig (=> keni fehlendes attribute in sub element)
 *   => TRUE -> effect
 */

/**
 *
 * This class reports all attributes of a rule
 * <ul>
 * <li>when the target of the rule matches</li>
 * <li>if evaluated or not</li>
 * </ul>
 *
 */
public class ConditionTargetCapture implements EvaluationEvents, KnownAttrStore {

    /**
     * hashmap which allows to retreive all targetMatches for a policy, policyset or rule
     */
    private Map<PolicyTreeElement, Map<URI, List<TargetMatch>>> policyMatches
        = new HashMap<PolicyTreeElement, Map<URI, List<TargetMatch>>>();

    /**
     * keeps track of the current trace through the policy evauation
     */
    private XACMLTree<PolicyTreeElement> policyTree = new XACMLTree<PolicyTreeElement>();

    /**
     * saves all attributes which are are "known", i.e., where a fix value for
     * this evaluation is given.
     */
    private Map<AttributeIdentifier, AttributeValue> knownAttributes;

    /**
     * keeps track of all attributes which are requested but in the set of knownAttributes
     */
    private Set<AttributeIdentifier> missingAttribtues = new HashSet<AttributeIdentifier>();

//	private static final Logger logger = Logger.getLogger(ConditionTargetCapture.class);

    private static ExtToolTypeEncoder typeEnc = HOLEncoder.getInstance();
    private ExtToolEncoder enc = new PraefixEncoder(typeEnc, this);

    private PolicyTreeElement missingTargetAttr = null;

    //private int policyTreeLevel = 0;

    public ConditionTargetCapture(Map<AttributeIdentifier, AttributeValue> knownAttributes) {
        /* should contain all known attributes, may be needed for attributes which are contained
         * within a condition but not evaluated at runtime
         */
        this.knownAttributes = knownAttributes;
    }

    public void afterEvaluatable(Evaluatable target, EvaluationCtx context,
                                 EvaluationResult result) {
        // record all evaluated attributes
        if ( target instanceof AttributeDesignator ) {
            AttributeDesignator attrDsgn = (AttributeDesignator) target;
            AttributeIdentifier attrId = AttributeHelper.getAttributeIdentifier(attrDsgn);

            if ( AttributeHelper.containsAbstractValue(result) ||
                    ( result.getStatus() != null && result.getStatus().getCode()!= null
                      && result.getStatus().getCode().contains(Status.STATUS_MISSING_ATTRIBUTE))) { // missing attribute
                missingAttribtues.add(attrId);
                PolicyTreeElement fatherEle = AttributeHelper.getPolicyTreeElement( ((Locatable) target).getRuntimeInfo());
                // assure that we do not overwrite an existing value
                if ( missingTargetAttr != null && missingTargetAttr != fatherEle &&
                        ! policyTree.getCurrent().isFather(missingTargetAttr)) {
                    System.err.println("Overwriting missingTargetAttr element!");
                    //for now / test cases, throw exception
                    throw new RuntimeException("Overwriting missingTargetAttr element!");
                }
                // remember that we have to print the target/condition for this policy tree element
                missingTargetAttr = fatherEle;
            } else {
                if ( knownAttributes.containsKey(attrId)) {
                    //TODO compare values
                } else {
                    knownAttributes.put(attrId, result.getAttributeValue());
                }
            }
        }
//		else if ( target instanceof Condition ) {
//			System.err.print(" CONDITION TARGETS: ");
//			enc.printConditionAndMatches((Condition) target, policyMatches, this.policyTree.getCurrent(), System.err);
//			System.err.println("");
//		}
    }

    public void beforeFunction(Function target, List<Expression> inputs,
                               EvaluationCtx context) {

    }


    public void beforeMatch(MatchElement target, EvaluationCtx context) {
        if ( target instanceof TargetMatch) {
            PolicyTreeElement container = AttributeHelper.getPolicyTreeElement( ((Locatable) target).getRuntimeInfo());
            addMatchElement(container, (TargetMatch) target );
        }
    }

    private void addMatchElement(PolicyTreeElement container, TargetMatch match) {
        initMatches(container);
        if ( ! (policyMatches.get(container).containsKey(match.getCategory()))) {
            List<TargetMatch> matches = new Vector<TargetMatch>();
            matches.add(match);
            policyMatches.get(container).put(match.getCategory(), matches);
        } else {
            policyMatches.get(container).get(match.getCategory()).add(match);
        }
    }

    private void initMatches(PolicyTreeElement container) {
        if ( ! policyMatches.containsKey(container) ) {
            policyMatches.put(container, new HashMap<URI, List<TargetMatch>>());
        }
    }

    public void beforePolicyTreeElement(PolicyTreeElement target,
                                        EvaluationCtx context) {

        policyTree.addChild(target);
        initMatches(target);
    }
    public void afterPolicyTreeElement(PolicyTreeElement target,
                                       EvaluationCtx context, Result result) {
        if ( missingTargetAttr == target ) {
            //this policytree element contained an unresolved attribute designator
            // => relevant to print!

            if ( target instanceof Rule &&  ((Rule)target).getCondition() != null ) {
                System.err.print(" CONDITION TARGETS: ");
                enc.printConditionAndMatches(((Rule) target).getCondition(), policyMatches, this.policyTree.getCurrent(), System.err);
                System.err.println("");
            } else {
                System.err.print(" MATCH TARGETS: ");
                enc.printTargetMatches(policyMatches, this.policyTree.getCurrent(), System.err);
                System.err.println("");
            }
            missingTargetAttr = null;
        }
        policyTree.close(target);
    }

    public AttributeValue getAttributeValue(AttributeIdentifier attrId) {
        return this.knownAttributes.get(attrId);
    }




    /*
     * EMPTY - NON USED STUB FUNCTIONS
     */
    public void afterCombiningAlg(CombiningAlgorithm target,
                                  EvaluationCtx context, List<CombinerParameter> parameters,
                                  List<CombinerElement> inputs, Result result) {	}
    public void afterFunction(Function target, List<Expression> inputs,
                              EvaluationCtx context, EvaluationResult result) {	}
    public void afterMatch(MatchElement target, EvaluationCtx context,
                           MatchResult result) {	}
    public void beforeCombiningAlg(CombiningAlgorithm target,
                                   EvaluationCtx context, List<CombinerParameter> parameters,
                                   List<CombinerElement> inputs) {	}
    public void beforeEvaluatable(Evaluatable target, EvaluationCtx context) {	}

    public void setAttributeValue(AttributeIdentifier attrId,
                                  AttributeValue attr) {
        // TODO Auto-generated method stub

    }
}
