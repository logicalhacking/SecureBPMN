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

import java.net.URI;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.attributes.AnalysisAttributeResolver;
import eu.aniketos.securebpmn.xacml.AnalysisCtx;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.attr.BagAttribute;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.debug.RuntimeInfo;
import com.sun.xacml.finder.AttributeFinderModule;

/**
 *
 * This is an AttributeFinderModule which is used for the analysis
 * PDP to control which attributes can or are be resolved. This
 * is the AttributeFinderModule which is used by the Workbench,
 * whereas one can provide several AnalysisAttributeResolvers
 * which are queried according to their registration ordering to
 * provide a value (see doc at AnalysisAttributeResolver).
 * <br/>
 * If no of the AnalysisAttributeResolvers provide such a value (or no
 * one is registered), an empty bag is returned (i.e. resolution failded)
 *
 */
public class AnalysisFinderModule extends AttributeFinderModule {

    private List<AnalysisAttributeResolver> attrResolvers = new Vector<AnalysisAttributeResolver>();


    @Override
    public boolean isDesignatorSupported() {
        return true;
    }

    private static Logger logger = Logger.getLogger(AnalysisFinderModule.class);

    public AnalysisFinderModule() {

    }




    public int addAnalysisAttributeResolver(AnalysisAttributeResolver attrResolver) {
        this.attrResolvers.add(attrResolver);
        return this.attrResolvers.size();
    }

    public boolean removeAttrResolver(AnalysisAttributeResolver attrResolver) {
        return this.attrResolvers.remove(attrResolver);
    }

    public void clearAttrResolver() {
        this.attrResolvers.clear();
    }

    @Override
    public EvaluationResult findAttribute(URI category, URI attributeType,
                                          URI attributeId, URI issuer,
                                          EvaluationCtx context) {
        AttributeIdentifier attrId = new AttributeIdentifier(category, attributeType, attributeId, issuer);
        RuntimeInfo runtimeInfo = ((AnalysisCtx) context).getEvalInfo().getCurrentRuntimeInfo();
        //context.get

        for (AnalysisAttributeResolver attrResolver : attrResolvers ) {
            BagAttribute bagAttr = attrResolver.resolveAttribute(attrId, context, runtimeInfo);
            if ( bagAttr != null ) {
                return new EvaluationResult(bagAttr);
            }
        }
        if ( logger.isDebugEnabled() ) {
            logger.debug("Did not find an AttributeResolver providing a value: returning empty bag for "
                         + attributeType + " type " + attributeType);
        }
        return new EvaluationResult(BagAttribute.createEmptyBag(attributeType));
    }
}
