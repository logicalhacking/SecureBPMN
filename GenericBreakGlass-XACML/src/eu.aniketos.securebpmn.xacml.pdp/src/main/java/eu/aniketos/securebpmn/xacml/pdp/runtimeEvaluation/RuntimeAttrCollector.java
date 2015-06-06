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

import java.util.HashMap;
import java.util.Map;

import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;

import com.sun.xacml.attr.AttributeValue;

/**
 * this is a simple implementation of the KnownAttrProvider interface
 * which does not require a backend to resolve known attributes but collects
 * them at runtime. This is only intended for testing and development
 * <br/>
 */
public class RuntimeAttrCollector implements KnownAttrStore {

    /**
     * saves all attributes which are are "known", i.e., where a fix value for
     * this evaluation is given.
     */
    private Map<AttributeIdentifier, AttributeValue> knownAttributes =
        new HashMap<AttributeIdentifier, AttributeValue>();

    public AttributeValue getAttributeValue(AttributeIdentifier attrId) {
        return knownAttributes.get(attrId);
    }

    public void setAttributeValue(AttributeIdentifier attrId,
                                  AttributeValue attr) {
        if ( knownAttributes.containsKey(attrId)) {
            // TODO make check? compare
        } else {
            knownAttributes.put(attrId, attr);
        }
    }

}
