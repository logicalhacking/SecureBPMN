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

package eu.aniketos.securebpmn.satmc;

import java.util.List;

/**
 * This Class represents a function in the SATMC output.
 *
 *
 */
public class SatmcFunction implements SatmcFact {
    public String name;
    public List<SatmcFact> args;

    public SatmcFunction(String name, List<SatmcFact> args) {
        this.name = name;
        this.args = args;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String res = name + "(";
        boolean first = true;
        for (SatmcFact sf : args) {
            if (first) {
                first = false;
            } else {
                res += ",";
            }
            res += sf.toString();
        }
        return res + ")";
    }
}
