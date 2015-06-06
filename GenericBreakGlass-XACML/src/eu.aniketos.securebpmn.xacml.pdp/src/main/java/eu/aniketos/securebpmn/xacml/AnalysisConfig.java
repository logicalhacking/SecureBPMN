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

package eu.aniketos.securebpmn.xacml;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.pdp.PDPServer;
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.AnalysisFinderModule;
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.attributes.AnalysisAttributeResolver;
import eu.aniketos.securebpmn.xacml.combine.AnalysisCombiningAlgFactory;
import eu.aniketos.securebpmn.xacml.combine.AnalysisCombiningAlgFactoryProxy;
import eu.aniketos.securebpmn.xacml.cond.AnalysisLogicalFunction;

import com.sun.xacml.PDPConfig;
import com.sun.xacml.combine.CombiningAlgFactory;
import com.sun.xacml.combine.CombiningAlgFactoryProxy;
import com.sun.xacml.cond.FunctionFactory;
import com.sun.xacml.cond.LogicalFunction;
import com.sun.xacml.finder.AttributeFinder;
import com.sun.xacml.finder.AttributeFinderModule;
import com.sun.xacml.finder.PolicyFinderModule;

/**
 * This configuration should be used when using the PDP in
 * analysis mode: Create an AnalysisConfig by using an existing
 * PDPConfig.<br/>
 *
 * Code sample for creating an Analysis PDP:
 * <ul>
 * <li>Create the normal Configuration, e.g., <br/>
 * 		ConfigurationStore config = new ConfigurationStore(new File("some/url/config.xml"));<br/>
		PDPConfig pdpConfig = config.getDefaultPDPConfig();</li>
 * <li>Create the AnalysisConfig<br/>
 *      AnalysisConfig analysisConfig = new AnalysisConfig(pdpConfig);</li>
 * <li>Create the PDPServer<br/>
 *     PDPServer pdp = new PDPServer(analysisConfig);</li>
 * <ul>
 *
 */
public class AnalysisConfig extends PDPConfig {

    private static Logger logger = Logger.getLogger(AnalysisConfig.class);

    private AnalysisFinderModule attrFinder = null;

    /**
     * this is more or less a copy constructor which modifies the PDPConfig
     * for analysis purposes
     * @param config
     */
    public AnalysisConfig(PDPConfig config) {
        //remove all attributeFinderModules and set to
        // eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.EvaluationFinderModule
        super(getAnalysisAttrFinder(), config.getPolicyFinder(),
              config.getResourceFinder(), config.getRevocationFinder());
        //get EvaluationFinderModule "back" - cannot save it to the instance as created during super constructor call
        attrFinder = (AnalysisFinderModule) super.getAttributeFinder().getModules().get(0);
        logger.info("Created Analysis Configuration with " + attrFinder.getClass().getName() + " attributeFinder");
        if ( super.getAttributeFinder().getModules().size() != 1 ) {
            //should not be the case as we want to retrieve all attributes via our simulated environment
            logger.warn("More than the " + attrFinder.getClass().getName()  + " attributeFinders are in use!");
        }

        init(config, true);

    }

    private void init(PDPConfig config, boolean abstractEval) {

        /*
         *  set option conf:useLines:true of all loaded policyFinderModules
         *  this will give a more detailed information during load of policies
         *  and during evaluation
         */
        boolean finderModuleError = false;
        int patchCount = 0;
        for ( PolicyFinderModule policyModule : config.getPolicyFinder().getModules() ) {
            try {
                Method enfUseLines = policyModule.getClass().getMethod("enforceUseLines", (Class<?>[]) null);
                enfUseLines.invoke(policyModule, (Object[]) null);
                logger.debug("Enforced useLines on class " + policyModule.getClass());
                ++patchCount;
            } catch (NoSuchMethodException e) {
                logger.warn("Class " + policyModule.getClass() + " does not support useLines feature");
            } catch (Exception e) {
                logger.warn("Error when setting useLines to class " + policyModule.getClass() + " "
                            + e.getClass() + ": " + e.getMessage());
            }
        }
        if ( ! finderModuleError ) {
            logger.info("Successfully set option \"useLines\" to true for " + patchCount + " finderModules");
        }

        /*
         * copy constructor: copy custom attrs which are not created
         * with the super() constructor
         * but assure that no logServer is used
         */
        Map<String, Object> customAttr = config.getCustomAttrs();
        if ( customAttr.containsKey(PDPServer.LOG_SERVER)) {
            customAttr.remove(PDPServer.LOG_SERVER);
            logger.info("Removed " + PDPServer.LOG_SERVER + " from configuration (must not be use for non-productive usage)");
        }
        super.setCutomAttrs(customAttr);



        /*
         * if abstract evaluation is enabled, replace standard algorithms with
         * analysis versions
         */
        if ( abstractEval ) {
//			CombiningAlgFactory defaultCombAlg = CombiningAlgFactory.getInstance();
//			Set<String> algs = defaultCombAlg.getSupportedAlgorithms();

            AnalysisCombiningAlgFactory analysisAlgFactory = AnalysisCombiningAlgFactory.getFactory();
            CombiningAlgFactoryProxy analysisAlgProxy = new AnalysisCombiningAlgFactoryProxy(analysisAlgFactory);

            CombiningAlgFactory.setDefaultFactory(analysisAlgProxy);
            logger.info("Replaced default combining algorithms with analysis versions");

            FunctionFactory func = FunctionFactory.getGeneralInstance();
            func.addFunction(new AnalysisLogicalFunction(LogicalFunction.NAME_OR) , true);
            func.addFunction(new AnalysisLogicalFunction(LogicalFunction.NAME_AND) , true);
        }
    }


    public AnalysisFinderModule getEvaluationFinderModule() {
        return this.attrFinder;
    }

    public int addAnalysisAttributeResolver(AnalysisAttributeResolver attrResolver) {
        return attrFinder.addAnalysisAttributeResolver(attrResolver);
    }

    public boolean removeAttrResolver(AnalysisAttributeResolver attrResolver) {
        return attrFinder.removeAttrResolver(attrResolver);
    }

    public void clearAttrResolver() {
        attrFinder.clearAttrResolver();
    }


    /**
     * returns an AttributeFinder which only contains only
     * the attributeFinderModule required for analysis
     * @return
     */
    private static AttributeFinder getAnalysisAttrFinder() {
        AttributeFinder attrFinder = new AttributeFinder();
        List<AttributeFinderModule> attrModules = new Vector<AttributeFinderModule>();
        attrModules.add(new AnalysisFinderModule());
        attrFinder.setModules(attrModules);
        return attrFinder;
    }

}
