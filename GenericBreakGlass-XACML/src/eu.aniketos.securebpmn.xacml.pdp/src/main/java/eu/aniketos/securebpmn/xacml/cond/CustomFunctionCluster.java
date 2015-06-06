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

package eu.aniketos.securebpmn.xacml.cond;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.cond.Function;
import com.sun.xacml.cond.cluster.FunctionCluster;

public class CustomFunctionCluster implements FunctionCluster {

    private static Logger logger = Logger.getLogger(CustomFunctionCluster.class);

    public CustomFunctionCluster() {
        logger.debug("Loading CustomFunctionCluster");
    }

    public Set<Function> getSupportedFunctions() {
        Set<Function> set = new HashSet<Function>();

        Iterator<String> it = CustomCompareFunction.getSupportedIdentifiers().iterator();
        while (it.hasNext()) {
            set.add(new CustomCompareFunction(it.next()));
        }
        return set;
    }

    public void setConfigurationStore(ConfigurationStore confStore) {

    }
}
