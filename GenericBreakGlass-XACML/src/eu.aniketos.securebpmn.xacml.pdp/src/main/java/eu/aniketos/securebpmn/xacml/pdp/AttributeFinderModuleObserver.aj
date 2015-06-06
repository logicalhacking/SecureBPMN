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

import eu.aniketos.securebpmn.xacml.finder.IRecordEvaluationContext;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.finder.AttributeFinderModule;

aspect AttributeFinderModuleObserver {

	pointcut findAttributeCall(URI category, URI attributeType, URI attributeId, URI issuer, EvaluationCtx context): 
		call (EvaluationResult AttributeFinderModule.findAttribute(URI, URI, URI, URI, EvaluationCtx))
			&& args (category, attributeType, attributeId, issuer, context);

	after(URI category, URI attributeType, URI attributeId, URI issuer, EvaluationCtx context) 
		returning (EvaluationResult result): 
		findAttributeCall(category, attributeType, attributeId, issuer, context) {

		if ( context instanceof IRecordEvaluationContext ) {
			((IRecordEvaluationContext) context).retreiveDesignatorAttributeSearch(
				category, attributeType, attributeId, issuer, result);
		}
	}
}

