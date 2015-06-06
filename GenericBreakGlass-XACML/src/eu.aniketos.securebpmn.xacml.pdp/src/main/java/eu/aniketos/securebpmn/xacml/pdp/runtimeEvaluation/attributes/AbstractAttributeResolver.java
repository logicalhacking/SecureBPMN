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

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.PolicyTreeElement;
import com.sun.xacml.Rule;
import com.sun.xacml.attr.BagAttribute;
import com.sun.xacml.debug.RuntimeInfo;



/**
 * This class can be used to register at XXX and will return abstract
 * values of attributes, i.e., enforcing an abstract evaluation of the policy.
 * <br/>
 * <b>NOTE</b> This class will return a value if the missing attributes is part
 * of a rule, i.e., if the attribute is not part of a match from a policy or policyset.
 *
 * For attributes within rules, it will always return an (abstract) value.
 * If an attribute should be treated as missing, another attribute resolver has to return an
 * empty bag before!
 *
 *
 */
public class AbstractAttributeResolver implements AnalysisAttributeResolver {

    private static Logger logger = Logger.getLogger(AbstractAttributeResolver.class);

    public BagAttribute resolveAttribute(AttributeIdentifier attrId,
                                         EvaluationCtx context, RuntimeInfo runtimeInfo) {
        PolicyTreeElement father = AttributeHelper.getPolicyTreeElement(runtimeInfo);
        if ( ! (father instanceof Rule) && logger.isDebugEnabled() ) {
            logger.debug("Currently, only attributes within rules may be missing! " +
                         "TargetMatches of Policies and PolicySets are not supported!");
            return null;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("return abstract value for attribute "
                         +  attrId.getAttributeId() + " type " + attrId.getAttributeType());
        }


        return AttributeHelper.createSingleBag(
                   AttributeHelper.getAbstractAttribute(attrId.getAttributeType()));
    }

}
