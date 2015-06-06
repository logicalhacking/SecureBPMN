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
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.support.finder.IPDPStateEvaluationContext;

import com.sun.xacml.Constants;
import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.BagAttribute;
import com.sun.xacml.attr.IntegerAttribute;
import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.attr.TypeIdentifierConstants;
import com.sun.xacml.cond.EvaluationResult;

public class AttributeResolver {

    private static final Logger logger = Logger.getLogger(AttributeResolver.class);

    public static long getPDPStatePolicyVersion(EvaluationCtx ctx) {
        EvaluationResult evalResult = ctx.getAttribute(IPDPStateEvaluationContext.PDPSTATE_CATEGORY,
                                      IPDPStateEvaluationContext.PDPSTATE_ATTRIBUTETYPE,
                                      IPDPStateEvaluationContext.PDPSTATE_URI,
                                      IPDPStateEvaluationContext.PDPSTATE_ISSUER);

        if ( ((BagAttribute) evalResult.getAttributeValue()).size() > 1 ) {
            logger.error("Did not retreive a bag with one (" +((BagAttribute) evalResult.getAttributeValue()).size() +
                         ") entry after attribute search for current svn policy version number; " +
                         "PDP Dtate requires exactly one attribute to be defined");
            return -1;
        } else if ( ((BagAttribute) evalResult.getAttributeValue()).size() == 1 ) {
            IntegerAttribute attrVal = (IntegerAttribute) ((BagAttribute) evalResult.getAttributeValue()).iterator().next();
            if ( logger.isDebugEnabled() && ctx instanceof EvaluationIdContext)
                logger.debug("Request " + ((EvaluationIdContext) ctx).getCurrentEvaluationId() + " is executed under policy " + attrVal.getValue());
            return attrVal.getValue();
        } else {
            logger.debug("Could not resolve current policy version");
            return -1;
        }
    }

    public static final URI ACTIVEPOLICY_CATEGORY = Constants.ENVIRONMENT_CAT;
    public static final URI ACTIVEPOLICY_ATTRIBUTETYPE = TypeIdentifierConstants.STRING_URI;
    public static final String ACTIVEPOLICY = "urn:activePolicies";
    public static final URI ACTIVEPOLICY_URI = URI.create(ACTIVEPOLICY);
    public static final URI ACTIVEPOLICY_ISSUER = null;

    public static Set<String> getActivePolicies(EvaluationCtx ctx) {
        EvaluationResult evalResult = ctx.getAttribute(ACTIVEPOLICY_CATEGORY,
                                      ACTIVEPOLICY_ATTRIBUTETYPE,
                                      ACTIVEPOLICY_URI,
                                      ACTIVEPOLICY_ISSUER);

        Set<String> policies = new HashSet<String>();

        for (AttributeValue value  : ((BagAttribute) evalResult.getAttributeValue()).iterable()) {
            policies.add( ((StringAttribute)value).getValue());
        }
        return policies;
    }
}
