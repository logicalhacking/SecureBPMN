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

package org.activiti.designer.security.features;

import org.activiti.designer.features.AbstractCreateFastBPMNFeature;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.securebpmn2.Securebpmn2Factory;
import org.eclipse.securebpmn2.SeparationOfDuty;

/**
 *
 */
public class CreateSecuritySodFeature extends AbstractCreateFastBPMNFeature {

    public static final String FEATURE_ID_KEY = "securitySod";

    public CreateSecuritySodFeature(IFeatureProvider fp) {
        super(fp, "SeparationOfDuty", "Add Separation of Duty");
    }

    @Override
    public boolean canCreate(ICreateContext context) {
        Object parentObject = getBusinessObjectForPictogramElement(context.getTargetContainer());
        return (context.getTargetContainer() instanceof Diagram || parentObject instanceof SubProcess);
    }

    @Override
    public Object[] create(ICreateContext context) {
        SeparationOfDuty sod = Securebpmn2Factory.eINSTANCE.createSeparationOfDuty();

        sod.setId(getNextId());
        sod.setName("sod");
        sod.setDynamicEnforcement(false);
        Object parentObject = getBusinessObjectForPictogramElement(context.getTargetContainer());
        if (parentObject instanceof SubProcess) {
            ((SubProcess) parentObject).getFlowElements().add(sod);
        } else {
            getDiagram().eResource().getContents().add(sod);
        }

        addGraphicalContent(sod, context);

        // activate direct editing after object creation
        getFeatureProvider().getDirectEditingInfo().setActive(true);

        return new Object[] { sod };
    }

    @Override
    public String getCreateImageId() {
        return "org.activiti.designer.security.sod";
    }

    @Override
    protected String getFeatureIdKey() {
        return FEATURE_ID_KEY;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Class getFeatureClass() {
        return Securebpmn2Factory.eINSTANCE.createSeparationOfDuty().getClass();
    }
}
