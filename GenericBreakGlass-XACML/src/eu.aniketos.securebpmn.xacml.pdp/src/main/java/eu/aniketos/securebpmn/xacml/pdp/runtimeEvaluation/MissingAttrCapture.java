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

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.EvalInfo.MissingAttrType;
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.attributes.AttributeHelper;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.MatchElement;
import com.sun.xacml.MatchResult;
import com.sun.xacml.PolicyTreeElement;
import com.sun.xacml.Rule;
import com.sun.xacml.Target;
import com.sun.xacml.attr.AttributeDesignator;
import com.sun.xacml.combine.CombinerElement;
import com.sun.xacml.combine.CombinerParameter;
import com.sun.xacml.combine.CombiningAlgorithm;
import com.sun.xacml.cond.Condition;
import com.sun.xacml.cond.Evaluatable;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.cond.Expression;
import com.sun.xacml.cond.Function;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.debug.RuntimeInfo;

/**
 *
 * <ul>
 * <li>retrieve events from the evaluation</li>
 * <li>keep track of all successful resovled attributes</li>
 * <li>keep track of missing attributes</li>
 * </ul>
 *
 */
public class MissingAttrCapture implements EvaluationEvents {

    private EvalInfoProvider infoProv;
    private KnownAttrStore knownAttributes;

    private static final Logger logger = Logger.getLogger(MissingAttrCapture.class);


    public MissingAttrCapture(EvalInfoProvider infoProv) {
        this.knownAttributes = new RuntimeAttrCollector();
        this.infoProv = infoProv;
    }

    public MissingAttrCapture(EvalInfoProvider infoProv, KnownAttrStore knownAttributes) {
        this.knownAttributes = knownAttributes;
        this.infoProv = infoProv;
    }

    public KnownAttrStore getKnownAttributes() {
        return knownAttributes;
    }

//	public MissingAttrCapture() {
//		knownAttributes = new HashMap<AttributeIdentifier, AttributeValue>();
//	}
//
//	public MissingAttrCapture(Map<AttributeIdentifier, AttributeValue> knownAttributes) {
//		this.knownAttributes = knownAttributes;
//	}




    public void afterEvaluatable(Evaluatable target, EvaluationCtx context,
                                 EvaluationResult result) {
        if ( target instanceof AttributeDesignator ) {
            AttributeDesignator attrDsgn = ( AttributeDesignator ) target;
            // if this attribute is not known, mark
            if (AttributeHelper.containsAbstractValue(result) ) {

                InfoTree<PolicyTreeElement, EvalInfo> current = infoProv.getCurrentTreeElem();
                PolicyTreeElement father = AttributeHelper.getPolicyTreeElement(attrDsgn.getRuntimeInfo());
                //<check> may be removed later on
                if ( father != current.getElement()) {
                    logger.warn("Current element from tree and father PolicyTreeElement do not match! " +
                                father.toString() + " (" + father.getId()  + ")  != " +
                                current.getElement().toString() + " (" + current.getElement().getId() + ")");
                }
                //</check>
                if ( ! (father instanceof Rule) ) {
                    logger.error("Currently, only attributes within rules may be missing! " +
                                 "TargetMatches of Policies and PolicySets are not supported!");
                    throw new RuntimeException("Currently, only attributes within rules may be missing!");
                }
                //TODO set if condition or target match
                current.getInfo().setMissingAttribtue(getFatherType(attrDsgn.getRuntimeInfo()));
            }
            // otherwise, save resolved value
            else {
                AttributeIdentifier attrId = AttributeHelper.getAttributeIdentifier(attrDsgn);
                knownAttributes.setAttributeValue(attrId, result.getAttributeValue());
            }
        }
    }

    public void afterPolicyTreeElement(PolicyTreeElement target,
                                       EvaluationCtx context, Result result) {
        //InfoTree<PolicyTreeElement, EvalInfo> current = tree.getCurrent();
        InfoTree<PolicyTreeElement, EvalInfo> current = infoProv.getCurrentTreeElem();
        //"tell" the upper element (if available) that sub element is missing some attribute
        if ( current.getInfo().isMissingAttribute() && current.getFather().getInfo() != null ) {
            current.getFather().getInfo().setMissingAttribtue(MissingAttrType.subElement);
        }
        current.getInfo().setResult(result);
        //tree.close(target);
        //infoProv.closeElement(target); // moved to evalInfoProvider
    }

    private MissingAttrType getFatherType(RuntimeInfo runtimeInfo) {
        Object xacml = runtimeInfo.getXACMLObject();
        if ( xacml != null ) {
            if ( xacml instanceof Target ) {
                return MissingAttrType.Target;
            } else if ( xacml instanceof Condition ) {
                return MissingAttrType.Condition;
            } else {
                return getFatherType(runtimeInfo.getCalledFrom());
            }
        } else {
            throw new RuntimeException("Could not determine calledFrom object from " + runtimeInfo);
        }
    }

//	private static int foo = 0;
//
//	private MissingAttrType getFatherType(Locatable l) {
//
//		Locatable l2 = (Locatable) l.getRuntimeInfo().getCallFromXACML();
//		if ( l2 != null ) {
//			if ( l2 instanceof Target ) {
//				foo =0;
//				return MissingAttrType.Target;
//			} else if ( l2 instanceof Condition ) {
//				foo = 0;
//				return MissingAttrType.Condition;
//			} else {
//				if ( ++foo > 15 ) {
//					foo = 0;
//					throw new RuntimeException("xxxx");
//				}
//				return getFatherType(l2);
//			}
//		} else {
//			throw new RuntimeException("Could not determine calledFrom object from " + l.getClass());
//		}
//	}

    //

    public void beforePolicyTreeElement(PolicyTreeElement target,
                                        EvaluationCtx context) {
        //tree.addChild(target, new EvalInfo());
        //infoProv.hitElement(target);
    }

    public void afterCombiningAlg(CombiningAlgorithm target,
                                  EvaluationCtx context, List<CombinerParameter> parameters,
                                  List<CombinerElement> inputs, Result result) {}
    public void afterFunction(Function target, List<Expression> inputs,
                              EvaluationCtx context, EvaluationResult result) {}
    public void afterMatch(MatchElement target, EvaluationCtx context,
                           MatchResult result) {}

    public void beforeCombiningAlg(CombiningAlgorithm target,
                                   EvaluationCtx context, List<CombinerParameter> parameters,
                                   List<CombinerElement> inputs) {}
    public void beforeEvaluatable(Evaluatable target, EvaluationCtx context) {}
    public void beforeFunction(Function target, List<Expression> inputs,
                               EvaluationCtx context) {}
    public void beforeMatch(MatchElement target, EvaluationCtx context) {}

//	if ( target instanceof Rule ) {
//		// print match and/or condition (depends, both if not knowing) +
//	} else {
//		//<check> remove later
//		if ( ! ( target instanceof Policy || target instanceof PolicySet)) {
//			throw new RuntimeException();
//		}
//		//</check>
//	}


}
