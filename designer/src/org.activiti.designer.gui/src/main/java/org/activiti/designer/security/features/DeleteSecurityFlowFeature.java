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



import java.util.List;

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ILinkService;
import org.eclipse.securebpmn2.SecurityFlow;

/**
 *
 */
public class DeleteSecurityFlowFeature extends AbstractCustomFeature {

    public DeleteSecurityFlowFeature(IFeatureProvider fp) {
        super(fp);
    }

    @Override
    public String getName() {
        return "Delete security flow"; //$NON-NLS-1$
    }

    @Override
    public String getDescription() {
        return "Delete security flow"; //$NON-NLS-1$
    }

    @Override
    public boolean canExecute(ICustomContext context) {
        if(context.getPictogramElements() == null) return false;
        for (PictogramElement pictogramElement : context.getPictogramElements()) {
            if(pictogramElement.getLink() == null) continue;
            Object boObject = getBusinessObjectForPictogramElement(pictogramElement);
            if(boObject instanceof SecurityFlow == false) {
                return false;
            }
        }
        return true;
    }

    public void execute(ICustomContext context) {
        if(context.getPictogramElements() == null) return;
        ILinkService linkService = Graphiti.getLinkService();
        for (final PictogramElement pictogramElement : context.getPictogramElements()) {
            if(pictogramElement.getLink() == null) continue;
            final Object boObject = getBusinessObjectForPictogramElement(pictogramElement);
            if(boObject instanceof SecurityFlow == true) {
                final SecurityFlow securityFlow = (SecurityFlow) boObject;
                for(Shape shape : getDiagram().getChildren()) {
                    FlowElement flowElement = (FlowElement) getBusinessObjectForPictogramElement(shape.getGraphicsAlgorithm().getPictogramElement());

                    if(flowElement instanceof SubProcess) {
                        List<PictogramElement> pictoList = linkService.getPictogramElements(getDiagram(), flowElement);
                        if(pictoList != null && pictoList.size() > 0) {
                            ContainerShape parent = (ContainerShape) pictoList.get(0);
                            for(Shape subShape : parent.getChildren()) {
                                for(FlowElement subFlowElement : ((SubProcess) flowElement).getFlowElements()) {
                                    removeAnchors(securityFlow, subFlowElement, subShape);
                                }
                            }
                            ((SubProcess) flowElement).getFlowElements().remove(securityFlow);
                        }
                    } else {
                        removeAnchors(securityFlow, flowElement, shape);
                    }
                }

                getDiagram().getPictogramLinks().remove(pictogramElement.getLink());
                getDiagram().getConnections().remove(pictogramElement);
                if(securityFlow.getSourceRefNode() != null) {
                    securityFlow.getSourceRefNode().getOutgoingSecurityFlow().remove(securityFlow);
                }
                if(securityFlow.getTargetRefNode() != null) {
                    securityFlow.getTargetRefNode().getIncomingSecurityFlow().remove(securityFlow);
                }
                getDiagram().eResource().getContents().remove(securityFlow);
            }
        }
    }

    private void removeAnchors(SecurityFlow securityFlow, FlowElement flowElement, Shape shape) {
        if(flowElement.getId().equals(securityFlow.getSourceRefNode().getId())) {
            EList<Anchor> anchorList = shape.getAnchors();
            for (Anchor anchor : anchorList) {
                if(anchor instanceof ChopboxAnchor) {
                    Connection toDeletedConnection = null;
                    for (Connection connection : anchor.getOutgoingConnections()) {
                        EObject bo =(EObject) getBusinessObjectForPictogramElement(connection);
                        if(bo instanceof SecurityFlow) {
                            SecurityFlow outFlow = (SecurityFlow) getBusinessObjectForPictogramElement(connection);
                            if(outFlow.getId().equals(securityFlow.getId())) {
                                toDeletedConnection = connection;
                            }
                        }
                    }
                    if(toDeletedConnection != null) {
                        anchor.getOutgoingConnections().remove(toDeletedConnection);
                    }
                }
            }
        }
        if(flowElement.getId().equals(securityFlow.getTargetRefNode().getId())) {
            EList<Anchor> anchorList = shape.getAnchors();
            for (Anchor anchor : anchorList) {
                if(anchor instanceof ChopboxAnchor) {
                    Connection toDeletedConnection = null;
                    for (Connection connection : anchor.getIncomingConnections()) {
                        EObject bo =(EObject) getBusinessObjectForPictogramElement(connection);
                        if(bo instanceof SecurityFlow) {
                            SecurityFlow outFlow = (SecurityFlow) getBusinessObjectForPictogramElement(connection);
                            if(outFlow.getId().equals(securityFlow.getId())) {
                                toDeletedConnection = connection;
                            }
                        }
                    }
                    if(toDeletedConnection != null) {
                        anchor.getIncomingConnections().remove(toDeletedConnection);
                    }
                }
            }
        }
    }
}

