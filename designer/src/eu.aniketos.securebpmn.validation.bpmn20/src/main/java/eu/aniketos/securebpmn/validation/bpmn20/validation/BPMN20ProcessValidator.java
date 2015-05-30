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

package eu.aniketos.securebpmn.validation.bpmn20.validation;

import java.util.List;
import java.util.Map;

import org.activiti.designer.eclipse.common.ActivitiBPMNDiagramConstants;
import org.activiti.designer.eclipse.extension.validation.AbstractProcessValidator;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.impl.UserTaskImpl;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.securebpmn2.ActivityAction;

/**
 * Checks if every UserTask has a SecureBPMN Action of the type assign, claim
 * and execute, i.e., if the UserTask can be properly executed.
 *
 *
 */
public class BPMN20ProcessValidator extends AbstractProcessValidator {

    private boolean overallResult;

    public BPMN20ProcessValidator() {
    }

    /*
     * (non-Javadoc)
     *
     * @see org.activiti.designer.eclipse.extension.validation.ProcessValidator#
     * getValidatorId()
     */
    @Override
    public String getValidatorId() {
        return ActivitiBPMNDiagramConstants.ASLAN_VALIDATOR_ID;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.activiti.designer.eclipse.extension.validation.ProcessValidator#
     * getValidatorName()
     */
    @Override
    public String getValidatorName() {
        return ActivitiBPMNDiagramConstants.ASLAN_VALIDATOR_NAME;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.activiti.designer.eclipse.extension.validation.ProcessValidator#
     * getFormatName()
     */
    @Override
    public String getFormatName() {
        return "SecureBPMN: ASLan";
    }

    /*
     * (non-Javadoc)
     *
     * @see org.activiti.designer.eclipse.extension.validation.ProcessValidator#
     * validateDiagram(org.eclipse.graphiti.mm.pictograms.Diagram,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public boolean validateDiagram(Diagram diagram, IProgressMonitor monitor) {

        this.overallResult = true;

        monitor.beginTask("Validating Diagram for ASLan export", 5);

        // Clear problems for this diagram first
        clearMarkers(getResource(diagram.eResource().getURI()));

        final Map<String, List<EObject>> processNodes = extractProcessConstructs(
                    getResourceForDiagram(diagram).getContents(),
                    new SubProgressMonitor(monitor, 2));

        final List<EObject> userTasks = processNodes.get(UserTaskImpl.class
                                        .getCanonicalName());

        for (EObject object : userTasks) {

            UserTask userTask = (UserTask) object;

            boolean assignNotSet = true;
            boolean claimNotSet = true;
            boolean completeNotSet = true;

            for (ActivityAction a : userTask.getActivityActions()) {
                if (a.getActionName() != null) {
                    if (a.getActionName().equals("Assign")) {
                        assignNotSet = false;
                    } else if (a.getActionName().equals("Claim")) {
                        claimNotSet = false;
                    } else if (a.getActionName().equals("Complete")) {
                        completeNotSet = false;
                    } else if (a.getActionName().equals("Full Access")) {
                        assignNotSet = false;
                        claimNotSet = false;
                        completeNotSet = false;
                    }
                }
            }

            if (assignNotSet) {
                addProblemToDiagram(diagram,
                                    "[SCVM-VAL-001] Missing Assign Action on UserTask \""
                                    + userTask.getId() + "\".", userTask.getId());
            }

            if (claimNotSet) {
                addProblemToDiagram(diagram,
                                    "[SCVM-VAL-002] Missing Claim Action on UserTask \""
                                    + userTask.getId() + "\".", userTask.getId());
            }

            if (completeNotSet) {
                addProblemToDiagram(diagram,
                                    "[SCVM-VAL-003] Missing Complete Action on UserTask \""
                                    + userTask.getId() + "\".", userTask.getId());
            }

        }

        monitor.worked(3);

        monitor.done();
        return overallResult;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.activiti.designer.eclipse.extension.AbstractDiagramWorker#
     * addProblemToDiagram(org.eclipse.graphiti.mm.pictograms.Diagram,
     * java.lang.String, java.lang.String)
     */
    @Override
    protected void addProblemToDiagram(Diagram diagram, String message,
                                       String nodeId) {
        super.addProblemToDiagram(diagram, message, nodeId);
        overallResult = false;
    }

}
