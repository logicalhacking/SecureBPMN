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

import java.net.URI;

import com.sun.xacml.combine.OrderedPermitOverridesPolicyAlg;

public class AnalysisOrderedPermitOverridesPolicyAlg extends
    AnalysisPermitOverridesPolicyAlg {
    // a URI form of the identifier
    private static URI identifierURI;
    // exception if the URI was invalid, which should never be a problem
    private static RuntimeException earlyException;

    public static final String algId = OrderedPermitOverridesPolicyAlg.algId;

    static {
        try {
            identifierURI = URI.create(OrderedPermitOverridesPolicyAlg.algId);
        } catch (IllegalArgumentException e) {
            earlyException = e;
        }
    }

    /**
     * Standard constructor.
     */
    public AnalysisOrderedPermitOverridesPolicyAlg() {
        super(identifierURI);

        if (earlyException != null) {
            throw earlyException;
        }
    }
}
