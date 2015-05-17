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


import org.activiti.designer.ActivitiImageProvider;
import org.activiti.designer.features.AbstractCreateBPMNConnectionFeature;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.securebpmn2.Securebpmn2Factory;
import org.eclipse.securebpmn2.SecurityFlow;
import org.eclipse.securebpmn2.SecurityFlowNode;

/**
 *
 */
public class CreateSecurityFlowFeature extends AbstractCreateBPMNConnectionFeature {

    public static final String FEATURE_ID_KEY = "sf";

    public CreateSecurityFlowFeature(IFeatureProvider fp) {
        // provide name and description for the UI, e.g. the palette
        super(fp, "SecurityFlow", "Create SecurityFlow"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public boolean canCreate(ICreateConnectionContext context) {
        FlowNode source = getFlowNode(context.getSourceAnchor());
        FlowNode target = getFlowNode(context.getTargetAnchor());
        if (source != null && target != null && source != target) {
            if(source instanceof SecurityFlowNode && target instanceof Activity) {
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean canStartConnection(ICreateConnectionContext context) {
        // return true if source anchor isn't undefined
        if (getFlowNode(context.getSourceAnchor()) != null) {
            return true;
        }
        return false;
    }

    public Connection create(ICreateConnectionContext context) {
        Connection newConnection = null;

        FlowNode source = getFlowNode(context.getSourceAnchor());
        FlowNode target = getFlowNode(context.getTargetAnchor());

        if (source != null && target != null) {
            // create new business object
            SecurityFlow securityFlow = createSecurityFlow(source, target, context);

            // add connection for business object
            AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(),
                    context.getTargetAnchor());
            addContext.setNewObject(securityFlow);
            newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
        }
        return newConnection;
    }

    /**
     * Returns the FlowNode belonging to the anchor, or null if not available.
     */
    private FlowNode getFlowNode(Anchor anchor) {
        if (anchor != null) {
            Object obj = getBusinessObjectForPictogramElement(anchor.getParent());
            if (obj instanceof FlowNode) {
                return (FlowNode) obj;
            }
        }
        return null;
    }



    private SecurityFlow createSecurityFlow(FlowNode source, FlowNode target, ICreateConnectionContext context) {
        SecurityFlow securityFlow = Securebpmn2Factory.eINSTANCE.createSecurityFlow();

        securityFlow.setId(getNextId());
        securityFlow.setSourceRefNode(source);
        securityFlow.setTargetRefNode(target);
        securityFlow.setName(securityFlow.getId());


        Object parentObject = null;
        if(context.getSourcePictogramElement().eContainer() instanceof ContainerShape) {
            ContainerShape parentShape = (ContainerShape) context.getSourcePictogramElement().eContainer();
            parentObject = getBusinessObjectForPictogramElement(parentShape.getGraphicsAlgorithm().getPictogramElement());
            if(parentObject != null && parentObject instanceof SubProcess == false) {
                parentShape = (ContainerShape) context.getTargetPictogramElement().eContainer();
                parentObject = getBusinessObjectForPictogramElement(parentShape.getGraphicsAlgorithm().getPictogramElement());
            }
        }

        if (parentObject != null && parentObject instanceof SubProcess) {
            ((SubProcess) parentObject).getFlowElements().add(securityFlow);
        } else {
            getDiagram().eResource().getContents().add(securityFlow);
        }

        source.getOutgoingSecurityFlow().add(securityFlow);
        target.getIncomingSecurityFlow().add(securityFlow);
        return securityFlow;
    }



    @Override
    public String getCreateImageId() {
        return ActivitiImageProvider.IMG_EREFERENCE;
    }

    @Override
    protected String getFeatureIdKey() {
        return FEATURE_ID_KEY;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Class getFeatureClass() {
        return Securebpmn2Factory.eINSTANCE.createSecurityFlow().getClass();
    }

}
