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

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.BagAttribute;
import com.sun.xacml.debug.RuntimeInfo;


/**
 * This class can be used to resolve attributes at analysis time
 * to specific values, e.g., as provided by the user beforehand
 *
 */
public class KnownAttributeResolver implements AnalysisAttributeResolver {

    private static final Logger logger = Logger.getLogger(KnownAttributeResolver.class);

    private Map<AttributeIdentifier, BagAttribute> attributeValues =
        new HashMap<AttributeIdentifier, BagAttribute>();



    public BagAttribute resolveAttribute(AttributeIdentifier attrId,
                                         EvaluationCtx context, RuntimeInfo resolver) {

        if ( attributeValues.containsKey(attrId)) {
            BagAttribute attr = attributeValues.get(attrId);
            if ( logger.isDebugEnabled() ) {
                logger.debug("return " + attr.size() + " values for attribute "
                             + attrId.getAttributeId() + " type " + attrId.getAttributeType());
            }
            return attr;
        } else {
            return null;
        }
    }

    /**
     * <b>Overwrites</b> all existing attributes!
     *
     * @param attributeValues as returned by the designator module, i.e., must be a
     * BagAttribute! may be null to unset all attributeValues
     */
    public void setAttributeBagValues(Map<AttributeIdentifier, BagAttribute> attributeValues) {
        if ( attributeValues == null ) {
            this.attributeValues.clear();
        }
        this.attributeValues = attributeValues;
    }

    /**
     * <b>Overwrites</b> all existing attributes!
     *
     * @param attributeValues as single value, i.e., must NOT be a BagAttribute
     */
    public void setAttributeValues(Map<AttributeIdentifier, AttributeValue> attributeValues) {
        this.attributeValues.clear();
        if ( attributeValues != null ) {
            for ( AttributeIdentifier attrId : attributeValues.keySet()) {
                this.attributeValues.put(attrId,
                                         AttributeHelper.createSingleBag(attributeValues.get(attrId)));
            }
        }
    }

    public void addAttributeValue(AttributeIdentifier key, AttributeValue value) {
        this.attributeValues.put(key, AttributeHelper.createSingleBag(value));
    }

    public void clear() {
        this.attributeValues.clear();
    }

}
