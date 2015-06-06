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
import com.sun.xacml.debug.Locatable;
import com.sun.xacml.debug.RuntimeInfo;

/**
 * This class is automatically loaded and registered when the
 * EvaluationEventHub is loaded (as it is required for several
 * other classes and analysis techniques, e.g, the Analysis
 * Combining Algorithms)
 *
 */
public class EvalInfoProvider implements EvaluationEvents {


    /**
     * saves the tree of policyTreeElements
     */
    private InfoTree<PolicyTreeElement, EvalInfo> treeElemTree;


    private InfoTree<Locatable, EvalInfo> xacmlTree;

    private static final Logger logger = Logger.getLogger(EvalInfoProvider.class);


    private boolean evalAllIfUnresolved;


    public EvalInfoProvider() {
        //RootPDPElement root = new RootPDPElement();
        treeElemTree = new InfoTree<PolicyTreeElement, EvalInfo>();
        //treeElemTree.addChild(root, new EvalInfo());

        xacmlTree = new InfoTree<Locatable, EvalInfo>();
        //xacmlTree.addChild(root, new EvalInfo());
    }

    public void clear() {
        treeElemTree.clear();
        xacmlTree.clear();
    }

    public boolean isEvalAllIfUnresolved() {
        return evalAllIfUnresolved;
    }

    public void setEvalAllIfUnresolved(boolean evalAllIfUnresolved) {
        this.evalAllIfUnresolved = evalAllIfUnresolved;
    }


    public InfoTree<Locatable, EvalInfo> getCurrent() {
        return this.xacmlTree.getCurrent();
    }

    public Locatable getCurrentXACMLObject() {
        Locatable loc = getCurrent().getElement();
        if ( loc == null ) {
            logger.fatal("No runtime information available - " +
                         "probably you are using the wrong XACML jar " +
                         "(i.e., not patched with aspectJ)");
        }
        return loc;
    }

    public RuntimeInfo getCurrentRuntimeInfo() {
        return getCurrentXACMLObject().getRuntimeInfo();
    }


    public InfoTree<PolicyTreeElement, EvalInfo> getCurrentTreeElem() {
        return treeElemTree.getCurrent();
    }

    public InfoTree<PolicyTreeElement, EvalInfo> getTreeElemTree() {
        return treeElemTree;
    }

    public boolean isMissingAttribute() {
        return getCurrentTreeElem().getInfo().isMissingAttribute();
    }

    public boolean isCurrentlyAbstract() {
        //TODO: check is abstract values got "reduced", e.g., $unkown or #true => result is true, not abstract
        return isMissingAttribute();
    }



    private void hitTreeElement(PolicyTreeElement target) {
        treeElemTree.addChild(target, new EvalInfo());
    }

    private void closeTreeElement(PolicyTreeElement target) {
        treeElemTree.close(target);
    }



    public void beforePolicyTreeElement(PolicyTreeElement target,
                                        EvaluationCtx context) {
        hitTreeElement(target);
        xacmlTree.addChild(target, new EvalInfo());
    }


    public void beforeCombiningAlg(CombiningAlgorithm target,
                                   EvaluationCtx context, List<CombinerParameter> parameters,
                                   List<CombinerElement> inputs) {
        xacmlTree.addChild(target, new EvalInfo());
    }
    public void beforeEvaluatable(Evaluatable target, EvaluationCtx context) {
        xacmlTree.addChild(target, new EvalInfo());
    }
    public void beforeFunction(Function target, List<Expression> inputs,
                               EvaluationCtx context) {
        xacmlTree.addChild(target, new EvalInfo());
    }
    public void beforeMatch(MatchElement target, EvaluationCtx context) {
        xacmlTree.addChild(target, new EvalInfo());
    }


    public void afterPolicyTreeElement(PolicyTreeElement target,
                                       EvaluationCtx context, Result result) {
        closeTreeElement(target);
        xacmlTree.close(target);
    }

    public void afterCombiningAlg(CombiningAlgorithm target,
                                  EvaluationCtx context, List<CombinerParameter> parameters,
                                  List<CombinerElement> inputs, Result result) {
        xacmlTree.close(target);
    }

    public void afterEvaluatable(Evaluatable target, EvaluationCtx context,
                                 EvaluationResult result) {
        xacmlTree.close(target);
    }

    public void afterFunction(Function target, List<Expression> inputs,
                              EvaluationCtx context, EvaluationResult result) {
        xacmlTree.close(target);
    }
    public void afterMatch(MatchElement target, EvaluationCtx context,
                           MatchResult result) {
        xacmlTree.close(target);
    }




}
