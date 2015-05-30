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
 * This Class represents a step in the attack trace of the SATMC output.
 *
 *
 */
public class SatmcTraceStep {
    public List<SatmcFunction> clauses;
    public List<SatmcFunction> rules;

    public SatmcTraceStep(List<SatmcFunction> clauses, List<SatmcFunction> rules) {
        this.clauses = clauses;
        this.rules = rules;
    }
}
