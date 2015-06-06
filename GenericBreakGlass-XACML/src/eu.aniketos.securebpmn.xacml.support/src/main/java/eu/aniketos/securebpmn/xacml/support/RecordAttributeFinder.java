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
package eu.aniketos.securebpmn.xacml.support;

import java.net.URI;

import org.apache.log4j.Logger;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.finder.AttributeFinder;

public class RecordAttributeFinder extends AttributeFinder {

    private static final Logger logger = Logger.getLogger(RecordEvaluationContext.class);

    public RecordAttributeFinder(AttributeFinder attrFinder) {
        super(attrFinder);
    }

    public EvaluationResult findAttribute(URI category, URI attributeType,
                                          URI attributeId, URI issuer,
                                          EvaluationCtx context) {
        EvaluationResult evalResult = super.findAttribute(category, attributeType, attributeId, issuer, context);

        if ( context instanceof RecordEvaluationContext ) {
            ((RecordEvaluationContext) context).retreiveDesignatorAttributeSearch(category, attributeType, attributeId, issuer, evalResult);
        } else {
            logger.warn("RecordAttributeFinder used without RecordEvaluationContext");
        }
        return evalResult;
    }
}
