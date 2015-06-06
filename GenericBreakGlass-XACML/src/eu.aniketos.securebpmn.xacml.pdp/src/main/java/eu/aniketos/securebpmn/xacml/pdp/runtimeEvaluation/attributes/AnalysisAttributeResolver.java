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

package eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.attributes;


import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.attr.BagAttribute;
import com.sun.xacml.debug.RuntimeInfo;

public interface AnalysisAttributeResolver {

    /**
     * may return three different "types" of results
     * <ul>
     * <li>null, indicating that this object cannot resolve the attribute.
     * 		the next attributeResolver in the list will be queried</li>
     * <li>an empty bag, indicating that the resolution of the attribute
     *  	is simulated to fail: the empty bag is returned to the
     *  	evaluation engine, no further attributeResolver will be
     *  	queried </li>
     * <li>a bag with one or several attribute values. the bag is returned
     * 		to the evaluation engine, no further attributeResolver will be
     *  	queried</li>
     * </ul>
     *
     * @param attrId
     * @param context
     * @param resolver
     * @return
     */
    BagAttribute resolveAttribute(AttributeIdentifier attrId,
                                  EvaluationCtx context, RuntimeInfo runtimeInfo);


}
