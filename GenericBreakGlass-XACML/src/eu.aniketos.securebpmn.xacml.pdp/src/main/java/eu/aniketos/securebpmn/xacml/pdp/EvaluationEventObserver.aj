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


package eu.aniketos.securebpmn.xacml.pdp;

import java.net.URI;
import java.util.List;

import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.EvaluationEventHub;
import eu.aniketos.securebpmn.xacml.AnalysisCtx;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.MatchResult;
import com.sun.xacml.PolicyTreeElement;
import com.sun.xacml.MatchElement;
import com.sun.xacml.Rule; // TODO remove
import com.sun.xacml.combine.CombiningAlgorithm;
import com.sun.xacml.combine.CombinerElement;
import com.sun.xacml.combine.CombinerParameter;
import com.sun.xacml.cond.Evaluatable;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.cond.Expression;
import com.sun.xacml.cond.Function;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.finder.AttributeFinderModule;
import com.sun.xacml.debug.Locatable;
import com.sun.xacml.debug.RuntimeInfo;


/** 
 * This aspect has two tasks
 * <ul>
 * <li>Report all events to the EvaluationEventHub</li>
 * <li>Report the information required for creating the call stack to the
 *		RuntimeInfor Object</li>
 * </ul>
 * 
 */
aspect EvaluationEventObserver {

	//Policy, PolicySet, PolicyReference, Rule
	pointcut evaluateCalled(EvaluationCtx context):
		call ( public Result PolicyTreeElement.evaluate(EvaluationCtx)  ) 
			&& (target(Locatable))
			&& args (context);

	before(EvaluationCtx context) : evaluateCalled(context) {
		RuntimeInfo src = ((Locatable) thisJoinPoint.getTarget()).getRuntimeInfo();
		if ( src != null ) {
			if ( thisJoinPoint.getThis() instanceof Locatable ) {
				src.setCalledFrom( ((Locatable) thisJoinPoint.getThis()).getRuntimeInfo() );
			} 
		}
		if ( context instanceof AnalysisCtx ) {
			((AnalysisCtx) context).getEvalHub().beforePolicyTreeElement( (PolicyTreeElement) thisJoinPoint.getTarget(), context);
		}
		//AnalysisCtx ctx = (AnalysisCtx) context;
		//ctx.getEvalHub().beforePolicyTreeElement( (PolicyTreeElement) thisJoinPoint.getTarget(), context);
	} 

	after(EvaluationCtx context) returning (Result result) : evaluateCalled(context) {
		//AnalysisCtx ctx = (AnalysisCtx) context;
		//ctx.getEvalHub().afterPolicyTreeElement( (PolicyTreeElement) thisJoinPoint.getTarget(), context, result);
		if ( context instanceof AnalysisCtx ) {
			((AnalysisCtx) context).getEvalHub().afterPolicyTreeElement( (PolicyTreeElement) thisJoinPoint.getTarget(), context, result);
		}
		RuntimeInfo src = ((Locatable) thisJoinPoint.getTarget()).getRuntimeInfo();
		if ( src != null ) {
			if ( thisJoinPoint.getThis() instanceof Locatable ) {
				src.unsetCalledFrom( ((Locatable) thisJoinPoint.getThis()).getRuntimeInfo() );
			} 
		}
	} 

  
	// AttributeDesignator, AttributeSelector, AttributeValue, VariableReference, Condition, Apply
	pointcut evaluateCall2(EvaluationCtx context):
		call ( public EvaluationResult Evaluatable.evaluate(EvaluationCtx) )
			&& (target(Locatable))	
			&& args (context);

	before(EvaluationCtx context) : evaluateCall2(context) {
		RuntimeInfo src = ((Locatable) thisJoinPoint.getTarget()).getRuntimeInfo();
		if ( src != null ) {
			src.setCalledFrom( ((Locatable) thisJoinPoint.getThis()).getRuntimeInfo() );
		}
		if ( context instanceof AnalysisCtx ) {
			((AnalysisCtx) context).getEvalHub().beforeEvaluatable((Evaluatable) thisJoinPoint.getTarget(), context);
		}
		//AnalysisCtx ctx = (AnalysisCtx) context;
		//ctx.getEvalHub().beforeEvaluatable((Evaluatable) thisJoinPoint.getTarget(), context);
	}
 
	after(EvaluationCtx context) returning (EvaluationResult result) : evaluateCall2(context) {
		//AnalysisCtx ctx = (AnalysisCtx) context;
		//ctx.getEvalHub().afterEvaluatable( (Evaluatable) thisJoinPoint.getTarget(), context, result);
		if ( context instanceof AnalysisCtx ) {
			((AnalysisCtx) context).getEvalHub().afterEvaluatable( (Evaluatable) thisJoinPoint.getTarget(), context, result);
		}
		RuntimeInfo src = ((Locatable) thisJoinPoint.getTarget()).getRuntimeInfo();
		if ( src != null ) {
			src.unsetCalledFrom( ((Locatable) thisJoinPoint.getThis()).getRuntimeInfo() );
		}
	} 

 
	//Target, TargetMatch, TargetMatchGroup, TargetSection
	pointcut matchCall(EvaluationCtx context):
		call ( public MatchResult MatchElement.match(EvaluationCtx ) )
			&& (target(Locatable))
			&& args (context);

	before(EvaluationCtx context) : matchCall(context) {
		RuntimeInfo src = ((Locatable) thisJoinPoint.getTarget()).getRuntimeInfo();
		if ( src != null ) {
			src.setCalledFrom( ((Locatable) thisJoinPoint.getThis()).getRuntimeInfo() );
		}
		if ( context instanceof AnalysisCtx ) {
			((AnalysisCtx) context).getEvalHub().beforeMatch((MatchElement) thisJoinPoint.getTarget(), context);
		}
		//AnalysisCtx ctx = (AnalysisCtx) context;
		//ctx.getEvalHub().beforeMatch((MatchElement) thisJoinPoint.getTarget(), context);
	}

	after(EvaluationCtx context) returning (MatchResult result) : matchCall(context) {
		//AnalysisCtx ctx = (AnalysisCtx) context;
		//ctx.getEvalHub().afterMatch( (MatchElement) thisJoinPoint.getTarget(), context, result);
		if ( context instanceof AnalysisCtx ) {
			((AnalysisCtx) context).getEvalHub().afterMatch( (MatchElement) thisJoinPoint.getTarget(), context, result);
		}
		RuntimeInfo src = ((Locatable) thisJoinPoint.getTarget()).getRuntimeInfo();
		if ( src != null ) {
			src.unsetCalledFrom( ((Locatable) thisJoinPoint.getThis()).getRuntimeInfo() );
		}  
	}
 
	//Function 
	pointcut evaluateFunction(List<Expression> inputs, EvaluationCtx context):
		call ( public EvaluationResult Function.evaluate(List<Expression>, EvaluationCtx) )
			&& (target(Locatable))	
			&& args (inputs, context);

	before(List<Expression> inputs, EvaluationCtx context) : evaluateFunction(inputs, context) {
		RuntimeInfo src = ((Locatable) thisJoinPoint.getTarget()).getRuntimeInfo();
		if ( src != null ) {
			src.setCalledFrom( ((Locatable) thisJoinPoint.getThis()).getRuntimeInfo() );
		}
		if ( context instanceof AnalysisCtx ) {
			((AnalysisCtx) context).getEvalHub().beforeFunction((Function) thisJoinPoint.getTarget( ), inputs, context);
		}
		//AnalysisCtx ctx = (AnalysisCtx) context;
		//ctx.getEvalHub().beforeFunction((Function) thisJoinPoint.getTarget( ), inputs, context);
	} 

	after(List<Expression> inputs, EvaluationCtx context) returning (EvaluationResult result) : evaluateFunction(inputs, context) {
		//AnalysisCtx ctx = (AnalysisCtx) context;
		//ctx.getEvalHub().afterFunction( (Function) thisJoinPoint.getTarget(), inputs, context, result);
		if ( context instanceof AnalysisCtx ) {
			((AnalysisCtx) context).getEvalHub().afterFunction( (Function) thisJoinPoint.getTarget(), inputs, context, result);
		}
		RuntimeInfo src = ((Locatable) thisJoinPoint.getTarget()).getRuntimeInfo();
		if ( src != null ) {
			src.unsetCalledFrom( ((Locatable) thisJoinPoint.getThis()).getRuntimeInfo() );
		}
	}

	//CombiningAlgorithm
	pointcut evaluateCombiningAlg(EvaluationCtx context, List<CombinerParameter> parameters, List<CombinerElement> inputs):
		call ( public Result CombiningAlgorithm.combine(EvaluationCtx, List<CombinerParameter>, List<CombinerElement>) )
			&& (target(Locatable))	
			&& args (context, parameters, inputs);

	before(EvaluationCtx context, List<CombinerParameter> parameters, List<CombinerElement> inputs) : evaluateCombiningAlg(context, parameters, inputs) {
		RuntimeInfo src = ((Locatable) thisJoinPoint.getTarget()).getRuntimeInfo();
		if ( src != null ) {
			src.setCalledFrom( ((Locatable) thisJoinPoint.getThis()).getRuntimeInfo() );
		}
		if ( context instanceof AnalysisCtx ) {
			((AnalysisCtx) context).getEvalHub().beforeCombiningAlg((CombiningAlgorithm) thisJoinPoint.getTarget( ), context, parameters, inputs);
 		}
		//AnalysisCtx ctx = (AnalysisCtx) context;
		//ctx.getEvalHub().beforeCombiningAlg((CombiningAlgorithm) thisJoinPoint.getTarget( ), context, parameters, inputs);
	}

	after(EvaluationCtx context, List<CombinerParameter> parameters, List<CombinerElement> inputs) returning (Result result) : evaluateCombiningAlg(context, parameters, inputs) {
		//AnalysisCtx ctx = (AnalysisCtx) context;
		//ctx.getEvalHub().afterCombiningAlg( (CombiningAlgorithm) thisJoinPoint.getTarget(), context, parameters, inputs, result);
		if ( context instanceof AnalysisCtx ) {
			((AnalysisCtx) context).getEvalHub().afterCombiningAlg( (CombiningAlgorithm) thisJoinPoint.getTarget(), context, parameters, inputs, result);
		}
		RuntimeInfo src = ((Locatable) thisJoinPoint.getTarget()).getRuntimeInfo();
		if ( src != null ) {
			src.unsetCalledFrom( ((Locatable) thisJoinPoint.getThis()).getRuntimeInfo() );
		}
	}

	//TODO: Obligation?
}



