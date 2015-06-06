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
import java.util.Vector;

import org.apache.log4j.Logger;

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
 * This class is responsible for retrieving events from within
 * the evaluation engine and distribute it to registered classes.
 *
 *
 */
public class EvaluationEventHub implements EvaluationEvents {

    private List<EvaluationEvents> registered = new Vector<EvaluationEvents>();

    private EvalInfoProvider evalInfo;

    private static Logger logger = Logger.getLogger(EvaluationEventHub.class);

    public EvaluationEventHub() {
        evalInfo = new EvalInfoProvider();
        this.register(evalInfo);
    }


    /**
     * Registers a new listener which will receive notifications about
     * the evaluation of events. This notifications are synchron, i.e.,
     * first, the systems halts as long as the functions are called and,
     * second, the order the elements are registered defines how they
     * are called: the first registered object is notified as first before
     * and as last after the evaluation
     *
     *
     */
    public int register(EvaluationEvents e) {
        registered.add(e);

        int pos = -1;
        for ( int i = 0; i < registered.size(); ++i ) {
            if ( registered.get(i) == e ) {
                pos = i;
                break;
            }
        }
        logger.debug("Added EvaluationEvents to EvaluationEventHub at position " + pos + " (" + e.getClass() + ")");
        return pos;
    }

    public boolean remove(EvaluationEvents e) {
        if ( e == evalInfo ) {
            return false;
        } else {
            return registered.remove(e);
        }
    }

    public void clear() {
        for ( int i = 1; i < registered.size(); ++i) {
            registered.remove(i);
        }
    }

    public EvalInfoProvider getEvalInfo() {
        return this.evalInfo;
    }

    public void clearEvalInfo() {
        this.evalInfo.clear();
    }



    /*
     * BEFORE events
     *
     */

    public void beforeEvaluatable(Evaluatable target, EvaluationCtx context) {
        for ( int i = 0 ; i < registered.size(); ++i ) {
            try {
                registered.get(i).beforeEvaluatable(target, context);
            } catch (Exception e) {
                logger.warn("Evaluation Client (index " + i + ", class "
                            + registered.get(i).getClass() + " throw an excpetion "
                            + " when executing beforeEvaluatable ("
                            + e.getClass() + "): " + e.getMessage());
                e.printStackTrace();
            }
        }
//		for ( EvaluationEvents e : registered) {
//			e.beforeEvaluatable(target, context);
//		}
    }

    public void beforeFunction(Function target, List<Expression> inputs,
                               EvaluationCtx context) {
        for ( int i = 0 ; i < registered.size(); ++i ) {
            try {
                registered.get(i).beforeFunction(target, inputs, context);
            } catch (Exception e) {
                logger.warn("Evaluation Client (index " + i + ", class "
                            + registered.get(i).getClass() + " throw an excpetion "
                            + " when executing beforeFunction ("
                            + e.getClass() + "): " + e.getMessage());
                e.printStackTrace();
            }
        }
//		for ( EvaluationEvents e : registered) {
//			e.beforeFunction(target, inputs, context);
//		}
    }

    public void beforePolicyTreeElement(PolicyTreeElement target,
                                        EvaluationCtx context) {
        for ( int i = 0 ; i < registered.size(); ++i ) {
            try {
                registered.get(i).beforePolicyTreeElement(target, context);
            } catch (Exception e) {
                logger.warn("Evaluation Client (index " + i + ", class "
                            + registered.get(i).getClass() + " throw an excpetion "
                            + " when executing beforePolicyTreeElement ("
                            + e.getClass() + "): " + e.getMessage());
                e.printStackTrace();
            }
        }
//		for ( EvaluationEvents e : registered) {
//			e.beforePolicyTreeElement(target, context);
//		}
    }

    public void beforeMatch(MatchElement target, EvaluationCtx context) {

        for ( int i = 0 ; i < registered.size(); ++i ) {
            try {
                registered.get(i).beforeMatch(target, context);
            } catch (Exception e) {
                logger.warn("Evaluation Client (index " + i + ", class "
                            + registered.get(i).getClass() + " throw an excpetion "
                            + " when executing beforeMatch ("
                            + e.getClass() + "): " + e.getMessage());
                e.printStackTrace();
            }
        }
//		for ( EvaluationEvents e : registered) {
//			e.beforeMatch(target, context);
//		}
    }

    public void beforeCombiningAlg(CombiningAlgorithm target, EvaluationCtx context,
                                   List<CombinerParameter> parameters, List<CombinerElement> inputs) {

        for ( int i = 0 ; i < registered.size(); ++i ) {
            try {
                registered.get(i).beforeCombiningAlg(target, context, parameters, inputs);
            } catch (Exception e) {
                logger.warn("Evaluation Client (index " + i + ", class "
                            + registered.get(i).getClass() + " throw an excpetion "
                            + " when executing beforeCombiningAlg ("
                            + e.getClass() + "): " + e.getMessage());
                e.printStackTrace();
            }
        }

//		for ( EvaluationEvents e : registered) {
//			e.beforeCombiningAlg(target, context, parameters, inputs);
//		}
    }





    /*
     * AFTER events
     *
     */


    public void afterEvaluatable(Evaluatable target, EvaluationCtx context,
                                 EvaluationResult result) {
        for ( int i = registered.size() -1 ; i > -1; --i ) {
            try {
                registered.get(i).afterEvaluatable(target, context, result);
            } catch (Exception e) {
                logger.warn("Evaluation Client (index " + i + ", class "
                            + registered.get(i).getClass() + " throw an excpetion "
                            + " when executing afterEvaluatable ("
                            + e.getClass() + "): " + e.getMessage());
                e.printStackTrace();
            }
        }
//		for ( EvaluationEvents e : registered) {
//			e.afterEvaluatable(target, context, result);
//		}
    }

    public void afterFunction(Function target, List<Expression> inputs,
                              EvaluationCtx context, EvaluationResult result) {
        for ( int i = registered.size() -1 ; i > -1; --i ) {
            try {
                registered.get(i).afterFunction(target, inputs, context, result);
            } catch (Exception e) {
                logger.warn("Evaluation Client (index " + i + ", class "
                            + registered.get(i).getClass() + " throw an excpetion "
                            + " when executing afterFunction ("
                            + e.getClass() + "): " + e.getMessage());
                e.printStackTrace();
            }
        }
//		for ( EvaluationEvents e : registered) {
//			e.afterFunction(target, inputs, context, result);
//		}
    }

    public void afterPolicyTreeElement(PolicyTreeElement target,
                                       EvaluationCtx context, Result result) {

        for ( int i = registered.size() -1 ; i > -1; --i ) {
            try {
                registered.get(i).afterPolicyTreeElement(target, context, result);
            } catch (Exception e) {
                logger.warn("Evaluation Client (index " + i + ", class "
                            + registered.get(i).getClass() + " throw an excpetion "
                            + " when executing afterPolicyTreeElement ("
                            + e.getClass() + "): " + e.getMessage());
                e.printStackTrace();
            }
        }

//		for ( EvaluationEvents e : registered) {
//			e.afterPolicyTreeElement(target, context, result);
//		}
    }

    public void afterMatch(MatchElement target, EvaluationCtx context,
                           MatchResult result) {
        for ( int i = registered.size() -1 ; i > -1; --i ) {
            try {
                registered.get(i).afterMatch(target, context, result);
            } catch (Exception e) {
                logger.warn("Evaluation Client (index " + i + ", class "
                            + registered.get(i).getClass() + " throw an excpetion "
                            + " when executing afterMatch ("
                            + e.getClass() + "): " + e.getMessage());
                e.printStackTrace();
            }
        }
//		for ( EvaluationEvents e : registered) {
//			e.afterMatch(target, context, result);
//		}
    }

    public void afterCombiningAlg(CombiningAlgorithm target, EvaluationCtx context,
                                  List<CombinerParameter> parameters, List<CombinerElement> inputs,
                                  Result result) {
        for ( int i = registered.size() -1 ; i > -1; --i ) {
            try {
                registered.get(i).afterCombiningAlg(target, context, parameters, inputs, result);
            } catch (Exception e) {
                logger.warn("Evaluation Client (index " + i + ", class "
                            + registered.get(i).getClass() + " throw an excpetion "
                            + " when executing afterCombiningAlg ("
                            + e.getClass() + "): " + e.getMessage());
                e.printStackTrace();
            }
        }
//		for ( EvaluationEvents e : registered) {
//			e.afterCombiningAlg(target, context, parameters, inputs, result);
//		}
    }

}
