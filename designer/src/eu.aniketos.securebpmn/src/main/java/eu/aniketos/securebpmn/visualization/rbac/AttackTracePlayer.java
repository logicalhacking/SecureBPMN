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

package eu.aniketos.securebpmn.visualization.rbac;

import java.util.ArrayList;
import java.util.List;

import org.activiti.designer.util.eclipse.ActivitiUiUtil;
import org.eclipse.bpmn2.Task;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import eu.aniketos.securebpmn.visualization.ActionType;
import eu.aniketos.securebpmn.visualization.HighlightVisualizationElementsRunnable;
import eu.aniketos.securebpmn.visualization.VisualizationElement;

/**
 * Player class that controls the attack trace visualization.
 *
 *
 */
public class AttackTracePlayer {

    private Diagram diagram;
    private TransactionalEditingDomain domain;
    private List<AttackTraceStep> traceList;
    private int pos;

    /**
     * Default constructor.
     *
     * @param diagram
     *            The diagram in which the visualization takes place.
     * @param traceList
     *            The conditioned List of steps in the attack trace.
     */
    public AttackTracePlayer(Diagram diagram, List<AttackTraceStep> traceList) {
        this.diagram = diagram;
        this.traceList = traceList;
        pos = 0;
        domain = TransactionUtil.getEditingDomain(diagram);

        highlightShapes(traceList.get(0).getInvolvedElements(), false);
    }

    /**
     * Returns if the attack trace has a previous step.
     *
     * @return true if it has a previous step, false if not.
     */
    public boolean hasPreviousStep() {
        return pos > 0;
    }

    /**
     * Returns if the attack trace has a next step.
     *
     * @return true if it has a next step, false if not.
     */
    public boolean hasNextStep() {
        return pos < traceList.size() - 1;
    }

    /**
     * Advances the visualization to the first step in the attack trace.
     */
    public void firstStep() {
        // revert all shapes from current to first element
        while (hasPreviousStep()) {
            previousStep();
        }
    }

    /**
     * Rewinds the visualization to the last step in the attack trace.
     */
    public void lastStep() {
        // change all shapes from current to last element
        while (hasNextStep()) {
            nextStep();
        }
    }

    /**
     * Advances the visualization to the next step.
     *
     * @return The new position in the attack trace.
     */
    public int nextStep() {
        // highlight shapes in next step
        if (hasNextStep()) {
            pos++;
            highlightShapes(traceList.get(pos).getInvolvedElements(), false);
        }
        return pos;
    }

    /**
     * Rewinds the visualization to the previous step. Notice that this
     * implementation does not support the visualization of assign/claim attacks
     * at the moment.
     *
     * @return The new position in the attack trace.
     */
    public int previousStep() {
        // revert shapes of current step
        if (hasPreviousStep()) {
            // revert changes done in current step
            final List<VisualizationElement> currentElements = traceList.get(
                        pos).getInvolvedElements();
            highlightShapes(currentElements, true);
            pos--;

            // check if previous step was violation & highlight again
            final List<VisualizationElement> previousElements = traceList.get(
                        pos).getInvolvedElements();

            List<VisualizationElement> tasksToHighlight = new ArrayList<VisualizationElement>();

            for (VisualizationElement element : currentElements) {
                // no violation
                if (!(element.getAction() == ActionType.VIOLATION))
                    continue;

                if (element.getbObject() instanceof Task) {
                    tasksToHighlight.add(new VisualizationElement(element
                                         .getId(), element.getbObject(), element
                                         .getpElement(), ActionType.EXECUTE));
                }

            }

            // check if previos step was claim & highlight again
            boolean previousClaim = false;
            for (VisualizationElement element : previousElements) {
                if (element.getAction() == ActionType.CLAIM) {
                    previousClaim = true;
                    break;
                }
            }

            if (previousClaim) {
                highlightShapes(previousElements, false);
            }
            if (tasksToHighlight.size() > 0) {
                highlightShapes(tasksToHighlight, false);
            }
        }
        return pos;
    }

    /**
     * Retrieves the information text of the current step in the attack trace.
     *
     * @return The information text of the current step.
     */
    public String getStepInfo() {
        return traceList.get(pos).getDescription();
    }

    /**
     * Performs the highlighting of the elements provided.
     *
     * @param elements
     *            The elements to be highlighted.
     * @param revert
     *            true if the highlighting should be reversed, false if not.
     */
    private void highlightShapes(List<VisualizationElement> elements,
                                 boolean revert) {
        ActivitiUiUtil.runModelChange(
            new HighlightVisualizationElementsRunnable(diagram, elements,
                    revert), domain, "SCVM RBAC Visualization");
    }

    /**
     * Method that must be called before the visualization view is disposed.
     * Resets the Diagram colors.
     */
    public void prepareDisposal() {
        firstStep();
        highlightShapes(traceList.get(0).getInvolvedElements(), true);
    }

}
