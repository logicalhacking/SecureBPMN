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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sun.xacml.Constants;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.combine.BaseCombiningAlgFactory;
import com.sun.xacml.combine.CombiningAlgFactory;
import com.sun.xacml.combine.CombiningAlgorithm;



public class AnalysisCombiningAlgFactory extends BaseCombiningAlgFactory {

    // the single factory instance
    private static AnalysisCombiningAlgFactory factoryInstance = null;

    // the algorithms supported by this factory
    private static Set<CombiningAlgorithm> supportedAlgorithms = null;

    // identifiers for the supported algorithms
    private static Set<String> supportedAlgIds;

    private static Logger logger = Logger.getLogger(AnalysisCombiningAlgFactory.class);


    private AnalysisCombiningAlgFactory() {
        super(supportedAlgorithms);
    }

//    private static void initAlgorithms() {
//        logger.debug("Initializing standard combining algorithms");
//
//        supportedAlgorithms = new HashSet<CombiningAlgorithm>();
//        supportedAlgIds = new HashSet<String>();
//
////        supportedAlgorithms.add(new DenyOverridesRuleAlg());
////        supportedAlgIds.add(DenyOverridesRuleAlg.algId);
////        supportedAlgorithms.add(new DenyOverridesPolicyAlg());
////        supportedAlgIds.add(DenyOverridesPolicyAlg.algId);
////
////        supportedAlgorithms.add(new OrderedDenyOverridesRuleAlg());
////        supportedAlgIds.add(OrderedDenyOverridesRuleAlg.algId);
////        supportedAlgorithms.add(new OrderedDenyOverridesPolicyAlg());
////        supportedAlgIds.add(OrderedDenyOverridesPolicyAlg.algId);
////
////        supportedAlgorithms.add(new PermitOverridesRuleAlg());
////        supportedAlgIds.add(PermitOverridesRuleAlg.algId);
////        supportedAlgorithms.add(new PermitOverridesPolicyAlg());
////        supportedAlgIds.add(PermitOverridesPolicyAlg.algId);
////
////        supportedAlgorithms.add(new OrderedPermitOverridesRuleAlg());
////        supportedAlgIds.add(OrderedPermitOverridesRuleAlg.algId);
////        supportedAlgorithms.add(new OrderedPermitOverridesPolicyAlg());
////        supportedAlgIds.add(OrderedPermitOverridesPolicyAlg.algId);
//
//        supportedAlgorithms.add(new AnalysisFirstApplicableRuleAlg());
//        supportedAlgIds.add(AnalysisFirstApplicableRuleAlg.algId);
//        supportedAlgorithms.add(new AnalysisFirstApplicableRuleAlg());
//        supportedAlgIds.add(AnalysisFirstApplicableRuleAlg.algId);
//
////        supportedAlgorithms.add(new OnlyOneApplicablePolicyAlg());
////        supportedAlgIds.add(OnlyOneApplicablePolicyAlg.algId);
//
//        supportedAlgIds = Collections.unmodifiableSet(supportedAlgIds);
//    }

    private static void initAlgorithms() {
        logger.debug("Initializing analysis versions of standard combining algorithms");

        supportedAlgorithms = new HashSet<CombiningAlgorithm>();
        supportedAlgIds = new HashSet<String>();

        supportedAlgorithms.add(new AnalysisDenyOverridesRuleAlg());
        supportedAlgIds.add(AnalysisDenyOverridesRuleAlg.algId);
        supportedAlgorithms.add(new AnalysisDenyOverridesPolicyAlg());
        supportedAlgIds.add(AnalysisDenyOverridesPolicyAlg.algId);

        supportedAlgorithms.add(new AnalysisFirstApplicableRuleAlg());
        supportedAlgIds.add(AnalysisFirstApplicableRuleAlg.algId);
        supportedAlgorithms.add(new AnalysisFirstApplicablePolicyAlg());
        supportedAlgIds.add(AnalysisFirstApplicablePolicyAlg.algId);

        supportedAlgorithms.add(new AnalysisOnlyOneApplicablePolicyAlg());
        supportedAlgIds.add(AnalysisOnlyOneApplicablePolicyAlg.algId);

        supportedAlgorithms.add(new AnalysisOrderedDenyOverridesRuleAlg());
        supportedAlgIds.add(AnalysisOrderedDenyOverridesRuleAlg.algId);
        supportedAlgorithms.add(new AnalysisOrderedDenyOverridesPolicyAlg());
        supportedAlgIds.add(AnalysisOrderedDenyOverridesPolicyAlg.algId);

        supportedAlgorithms.add(new AnalysisOrderedPermitOverridesRuleAlg());
        supportedAlgIds.add(AnalysisOrderedPermitOverridesRuleAlg.algId);
        supportedAlgorithms.add(new AnalysisOrderedPermitOverridesPolicyAlg());
        supportedAlgIds.add(AnalysisOrderedPermitOverridesPolicyAlg.algId);

        supportedAlgorithms.add(new AnalysisPermitOverridesRuleAlg());
        supportedAlgIds.add(AnalysisPermitOverridesRuleAlg.algId);
        supportedAlgorithms.add(new AnalysisPermitOverridesPolicyAlg());
        supportedAlgIds.add(AnalysisPermitOverridesPolicyAlg.algId);

        supportedAlgIds = Collections.unmodifiableSet(supportedAlgIds);

        logger.debug("Created " + supportedAlgIds.size() + " analysis combining algorithms");
    }


    public static synchronized AnalysisCombiningAlgFactory getFactory() {
        if (factoryInstance == null) {
            initAlgorithms();
            factoryInstance = new AnalysisCombiningAlgFactory();
        }

        return factoryInstance;
    }

    public static CombiningAlgFactory getNewFactory() {
        // first we make sure everything's been initialized...
        getFactory();

        // ...then we create the new instance
        return new BaseCombiningAlgFactory(supportedAlgorithms);
    }


    public static Set<String> getStandardAlgorithms(String xacmlVersion)
    throws UnknownIdentifierException
    {
        if ((xacmlVersion.equals(Constants.XACML_1_0_IDENTIFIER)) ||
                (xacmlVersion.equals(Constants.XACML_2_0_IDENTIFIER)) ||
                (xacmlVersion.equals(Constants.XACML_3_0_IDENTIFIER))) {
            return supportedAlgIds;
        }

        throw new UnknownIdentifierException("Unknown XACML version: " +
                                             xacmlVersion);
    }


    public void addAlgorithm(CombiningAlgorithm alg) {
        throw new UnsupportedOperationException("this factory cannot " +
                                                "support new algorithms");
    }

}
