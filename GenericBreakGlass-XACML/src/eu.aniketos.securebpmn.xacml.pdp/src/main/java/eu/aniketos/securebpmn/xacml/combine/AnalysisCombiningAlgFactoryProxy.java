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

package eu.aniketos.securebpmn.xacml.combine;

import com.sun.xacml.combine.CombiningAlgFactory;
import com.sun.xacml.combine.CombiningAlgFactoryProxy;

public class AnalysisCombiningAlgFactoryProxy implements
    CombiningAlgFactoryProxy {

    private AnalysisCombiningAlgFactory factory;

    public AnalysisCombiningAlgFactoryProxy(AnalysisCombiningAlgFactory factory) {
        this.factory = factory;
    }

    public CombiningAlgFactory getFactory() {
        return factory;
    }

}
