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

package eu.aniketos.securebpmn.visualization;

import java.util.List;

import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.Task;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.ILinkService;
import org.eclipse.securebpmn2.BindingOfDuty;
import org.eclipse.securebpmn2.SeparationOfDuty;

import eu.aniketos.securebpmn.validation.SCVMValidationConstants;

/**
 * A runnable for highlighting the elements in on step of the attack trace.
 * 
 * 
 */
public class HighlightVisualizationElementsRunnable implements Runnable {

	private Diagram diagram;
	private List<VisualizationElement> elements;
	private boolean revert;
	private IGaService gaService;

	/**
	 * Default constructor.
	 * 
	 * @param diagram
	 *            The diagram in which the visualization takes place.
	 * @param elements
	 *            The List of elements that should be highlighted.
	 * @param revert
	 *            true if the reverse visualization should be applied, false if
	 *            not.
	 */
	public HighlightVisualizationElementsRunnable(Diagram diagram,
			List<VisualizationElement> elements, boolean revert) {
		this.diagram = diagram;
		this.elements = elements;
		this.revert = revert;
		this.gaService = Graphiti.getGaService();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		for (VisualizationElement element : elements) {

			if (element.getbObject() instanceof Task) {
				highlightTask(element.getpElement(), element.getAction());
			} else if (element.getbObject() instanceof Gateway) {
				highlightGateway(element.getpElement(), element.getAction());
			} else if (element.getbObject() instanceof Event) {
				highlightEvent(element.getpElement(), element.getAction());
			} else if (element.getbObject() instanceof SeparationOfDuty
					|| element.getbObject() instanceof BindingOfDuty) {
				highlightTaskLikeElement(element.getpElement(),
						element.getAction(), false);
			}

		}

	}

	/**
	 * Performs the highlighting for a Task.
	 * 
	 * @param pElement
	 *            The corresponding PictogramElement.
	 * @param action
	 *            The action that defines the highlighting.
	 */
	private void highlightTask(PictogramElement pElement, ActionType action) {
		highlightTaskLikeElement(pElement, action, true);
	}

	/**
	 * Performs the highlighting for a Task and the incoming SequenceFlow.
	 * 
	 * @param pElement
	 *            The corresponding PictogramElement.
	 * @param action
	 *            The action that defines the highlighting.
	 * @param withSequenceFlow
	 *            true if the incoming SequenceFlow should be highlighted, false
	 *            if not.
	 */
	private void highlightTaskLikeElement(PictogramElement pElement,
			ActionType action, boolean withSequenceFlow) {
		// highlighting for tasks
		ContainerShape cs = (ContainerShape) pElement;
		Shape removeShape = null;

		int overlayWidth = 105;
		int overlayHeight = 55;

		if (cs.getGraphicsAlgorithm() instanceof Rectangle) {
			Rectangle rect = (Rectangle) cs.getGraphicsAlgorithm();
			overlayWidth = rect.getWidth();
			overlayHeight = rect.getHeight();
		}

		for (Shape shape : cs.getChildren()) {

			final GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();

			if (ga instanceof RoundedRectangle) {
				// overlay already exists, remove it
				removeShape = shape;
			}
		}

		if (removeShape != null)
			// finally remove overlay
			cs.getChildren().remove(removeShape);

		if (!revert) {
			// create overlay
			final Shape s = Graphiti.getPeCreateService()
					.createShape(cs, false);
			final RoundedRectangle rr = gaService.createRoundedRectangle(s, 20,
					20);
			rr.setLineVisible(true);
			rr.setFilled(true);
			rr.setLineWidth(2);
			rr.setTransparency(0.7);
			if (action == ActionType.VIOLATION) {
				rr.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_VIOL_FG[0],
						SCVMValidationConstants.COLOR_HL_VIOL_FG[1],
						SCVMValidationConstants.COLOR_HL_VIOL_FG[2]));
				rr.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_VIOL_BG[0],
						SCVMValidationConstants.COLOR_HL_VIOL_BG[1],
						SCVMValidationConstants.COLOR_HL_VIOL_BG[2]));

			} else if (action == ActionType.EXECUTE) {
				rr.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_EXEC_FG[0],
						SCVMValidationConstants.COLOR_HL_EXEC_FG[1],
						SCVMValidationConstants.COLOR_HL_EXEC_FG[2]));
				rr.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_EXEC_BG[0],
						SCVMValidationConstants.COLOR_HL_EXEC_BG[1],
						SCVMValidationConstants.COLOR_HL_EXEC_BG[2]));

			} else if (action == ActionType.CLAIM) {
				rr.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_CLAIM_FG[0],
						SCVMValidationConstants.COLOR_HL_CLAIM_FG[1],
						SCVMValidationConstants.COLOR_HL_CLAIM_FG[2]));
				rr.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_CLAIM_BG[0],
						SCVMValidationConstants.COLOR_HL_CLAIM_BG[1],
						SCVMValidationConstants.COLOR_HL_CLAIM_BG[2]));

			} else if (action == ActionType.ASSIGN) {
				rr.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_ASSIGN_FG[0],
						SCVMValidationConstants.COLOR_HL_ASSIGN_FG[1],
						SCVMValidationConstants.COLOR_HL_ASSIGN_FG[2]));
				rr.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_ASSIGN_BG[0],
						SCVMValidationConstants.COLOR_HL_ASSIGN_BG[1],
						SCVMValidationConstants.COLOR_HL_ASSIGN_BG[2]));

			} else if (action == ActionType.WORKFLOW) {
				rr.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_WORK_FG[0],
						SCVMValidationConstants.COLOR_HL_WORK_FG[1],
						SCVMValidationConstants.COLOR_HL_WORK_FG[2]));
				rr.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_WORK_BG[0],
						SCVMValidationConstants.COLOR_HL_WORK_BG[1],
						SCVMValidationConstants.COLOR_HL_WORK_BG[2]));
			}
			gaService.setLocationAndSize(rr, 0, 0, overlayWidth, overlayHeight);
		}
		if (withSequenceFlow)
			highlightIncomingSequenceFlow(cs);
	}

	/**
	 * Performs the highlighting for a Gateway.
	 * 
	 * @param pElement
	 *            The corresponding PictogramElement.
	 * @param action
	 *            The action that defines the highlighting.
	 */
	private void highlightGateway(PictogramElement pElement, ActionType action) {
		// highlighting for gateways
		ContainerShape cs = (ContainerShape) pElement;
		Shape removeShape = null;

		for (Shape shape : cs.getChildren()) {

			final GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();

			if (ga instanceof Polygon) {
				// overlay already exists, remove it
				removeShape = shape;
			}
		}

		if (removeShape != null)
			// finally remove overlay
			cs.getChildren().remove(removeShape);

		if (!revert) {
			// create overlay
			final Shape s = Graphiti.getPeCreateService()
					.createShape(cs, false);
			int xy[] = new int[] { 0, 20, 20, 0, 40, 20, 20, 40, 0, 20 };
			final Polygon p = gaService.createPolygon(s, xy);

			p.setLineVisible(true);
			p.setFilled(true);
			p.setLineWidth(2);
			p.setTransparency(0.7);
			if (action == ActionType.VIOLATION) {
				p.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_VIOL_FG[0],
						SCVMValidationConstants.COLOR_HL_VIOL_FG[1],
						SCVMValidationConstants.COLOR_HL_VIOL_FG[2]));
				p.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_VIOL_BG[0],
						SCVMValidationConstants.COLOR_HL_VIOL_BG[1],
						SCVMValidationConstants.COLOR_HL_VIOL_BG[2]));

			} else if (action == ActionType.EXECUTE) {
				p.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_EXEC_FG[0],
						SCVMValidationConstants.COLOR_HL_EXEC_FG[1],
						SCVMValidationConstants.COLOR_HL_EXEC_FG[2]));
				p.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_EXEC_BG[0],
						SCVMValidationConstants.COLOR_HL_EXEC_BG[1],
						SCVMValidationConstants.COLOR_HL_EXEC_BG[2]));

			} else if (action == ActionType.CLAIM) {
				p.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_CLAIM_FG[0],
						SCVMValidationConstants.COLOR_HL_CLAIM_FG[1],
						SCVMValidationConstants.COLOR_HL_CLAIM_FG[2]));
				p.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_CLAIM_BG[0],
						SCVMValidationConstants.COLOR_HL_CLAIM_BG[1],
						SCVMValidationConstants.COLOR_HL_CLAIM_BG[2]));

			} else if (action == ActionType.ASSIGN) {
				p.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_ASSIGN_FG[0],
						SCVMValidationConstants.COLOR_HL_ASSIGN_FG[1],
						SCVMValidationConstants.COLOR_HL_ASSIGN_FG[2]));
				p.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_ASSIGN_BG[0],
						SCVMValidationConstants.COLOR_HL_ASSIGN_BG[1],
						SCVMValidationConstants.COLOR_HL_ASSIGN_BG[2]));

			} else if (action == ActionType.WORKFLOW) {
				p.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_WORK_FG[0],
						SCVMValidationConstants.COLOR_HL_WORK_FG[1],
						SCVMValidationConstants.COLOR_HL_WORK_FG[2]));
				p.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_WORK_BG[0],
						SCVMValidationConstants.COLOR_HL_WORK_BG[1],
						SCVMValidationConstants.COLOR_HL_WORK_BG[2]));
			}
			gaService.setLocationAndSize(p, 0, 0, 40, 40);
		}
		highlightIncomingSequenceFlow(cs);
	}

	/**
	 * Performs the highlighting for an Event.
	 * 
	 * @param pElement
	 *            The corresponding PictogramElement.
	 * @param action
	 *            The action that defines the highlighting.
	 */
	private void highlightEvent(PictogramElement pElement, ActionType action) {
		// highlighting for events
		ContainerShape cs = (ContainerShape) pElement;
		Shape removeShape = null;

		for (Shape shape : cs.getChildren()) {

			final GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();

			if (ga instanceof Ellipse) {
				// overlay already exists, remove it
				removeShape = shape;
			}
		}

		if (removeShape != null)
			// finally remove overlay
			cs.getChildren().remove(removeShape);

		if (!revert) {
			// create overlay
			final Shape s = Graphiti.getPeCreateService()
					.createShape(cs, false);
			final Ellipse e = gaService.createEllipse(s);
			e.setLineVisible(true);
			e.setFilled(true);
			e.setLineWidth(4);
			e.setTransparency(0.7);
			if (action == ActionType.VIOLATION) {
				e.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_VIOL_FG[0],
						SCVMValidationConstants.COLOR_HL_VIOL_FG[1],
						SCVMValidationConstants.COLOR_HL_VIOL_FG[2]));
				e.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_VIOL_BG[0],
						SCVMValidationConstants.COLOR_HL_VIOL_BG[1],
						SCVMValidationConstants.COLOR_HL_VIOL_BG[2]));

			} else if (action == ActionType.EXECUTE) {
				e.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_EXEC_FG[0],
						SCVMValidationConstants.COLOR_HL_EXEC_FG[1],
						SCVMValidationConstants.COLOR_HL_EXEC_FG[2]));
				e.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_EXEC_BG[0],
						SCVMValidationConstants.COLOR_HL_EXEC_BG[1],
						SCVMValidationConstants.COLOR_HL_EXEC_BG[2]));

			} else if (action == ActionType.CLAIM) {
				e.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_CLAIM_FG[0],
						SCVMValidationConstants.COLOR_HL_CLAIM_FG[1],
						SCVMValidationConstants.COLOR_HL_CLAIM_FG[2]));
				e.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_CLAIM_BG[0],
						SCVMValidationConstants.COLOR_HL_CLAIM_BG[1],
						SCVMValidationConstants.COLOR_HL_CLAIM_BG[2]));

			} else if (action == ActionType.ASSIGN) {
				e.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_ASSIGN_FG[0],
						SCVMValidationConstants.COLOR_HL_ASSIGN_FG[1],
						SCVMValidationConstants.COLOR_HL_ASSIGN_FG[2]));
				e.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_ASSIGN_BG[0],
						SCVMValidationConstants.COLOR_HL_ASSIGN_BG[1],
						SCVMValidationConstants.COLOR_HL_ASSIGN_BG[2]));

			} else if (action == ActionType.WORKFLOW) {
				e.setForeground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_WORK_FG[0],
						SCVMValidationConstants.COLOR_HL_WORK_FG[1],
						SCVMValidationConstants.COLOR_HL_WORK_FG[2]));
				e.setBackground(gaService.manageColor(diagram,
						SCVMValidationConstants.COLOR_HL_WORK_BG[0],
						SCVMValidationConstants.COLOR_HL_WORK_BG[1],
						SCVMValidationConstants.COLOR_HL_WORK_BG[2]));
			}
			gaService.setLocationAndSize(e, 0, 0, 35, 35);
		}
		highlightIncomingSequenceFlow(cs);
	}

	/**
	 * Performs the highlighting for a SequenceFlow.
	 * 
	 * @param cs
	 *            The corresponding ContainerShape.
	 */
	private void highlightIncomingSequenceFlow(ContainerShape cs) {
		// highlighting of incoming sequence flow
		ILinkService linkService = Graphiti.getLinkService();

		// loop over incoming connections
		for (Anchor anchor : cs.getAnchors()) {
			for (Connection connection : anchor.getIncomingConnections()) {

				EObject linkedObject = linkService
						.getBusinessObjectForLinkedPictogramElement(connection);

				if (!(linkedObject instanceof SequenceFlow)) {
					continue;
				}

				// determine if connection comes from highlighted element
				boolean sourceIsHighlighted = false;

				PictogramElement sourcePictogramElement = connection.getStart()
						.getParent().getGraphicsAlgorithm()
						.getPictogramElement();

				if (sourcePictogramElement instanceof ContainerShape) {
					for (Shape shape : ((ContainerShape) sourcePictogramElement)
							.getChildren()) {

						final GraphicsAlgorithm ga = shape
								.getGraphicsAlgorithm();

						if (ga instanceof RoundedRectangle
								|| ga instanceof Ellipse
								|| ga instanceof Polygon) {
							sourceIsHighlighted = true;
							break;
						}
					}
				}

				if (sourceIsHighlighted) {

					GraphicsAlgorithm connectionGA = connection
							.getGraphicsAlgorithm();

					if (revert) {
						// remove highlighting of sequenceFlow
						connectionGA.setLineWidth(1);
						connectionGA.setForeground(gaService.manageColor(
								diagram,
								SCVMValidationConstants.COLOR_DEF_SEQFLOW[0],
								SCVMValidationConstants.COLOR_DEF_SEQFLOW[1],
								SCVMValidationConstants.COLOR_DEF_SEQFLOW[2]));
					} else {
						// highlight sequenceFlow
						// TODO make highlighting more beautiful (overlay?)
						connectionGA.setLineWidth(2);
						connectionGA.setForeground(gaService.manageColor(
								diagram,
								SCVMValidationConstants.COLOR_HL_WORK_FG[0],
								SCVMValidationConstants.COLOR_HL_WORK_FG[1],
								SCVMValidationConstants.COLOR_HL_WORK_FG[2]));
					}
				}
			}
		}
	}
}
