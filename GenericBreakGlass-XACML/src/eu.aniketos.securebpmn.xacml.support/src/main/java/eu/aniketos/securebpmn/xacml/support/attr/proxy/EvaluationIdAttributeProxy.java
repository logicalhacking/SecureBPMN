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

package eu.aniketos.securebpmn.xacml.support.attr.proxy;

import org.w3c.dom.Node;

import eu.aniketos.securebpmn.xacml.support.attr.EvaluationIdAttribute;

import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.attr.AttributeProxy;
import com.sun.xacml.attr.AttributeValue;

public class EvaluationIdAttributeProxy  implements AttributeProxy  {
    //private static Logger logger = Logger.getLogger(EvaluationIdAttributeProxy.class);

    public AttributeValue getInstance(Node root) throws Exception {
        return EvaluationIdAttribute.getInstance(root);
    }

    public AttributeValue getInstance(String value) throws Exception {
        return EvaluationIdAttribute.getInstance(value);
    }

    public EvaluationIdAttributeProxy()	{

    }

    public void setConfigurationStore(ConfigurationStore confStore) {

    }
}
