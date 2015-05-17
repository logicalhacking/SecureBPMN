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

package eu.aniketos.securebpmn.features;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import eu.aniketos.securebpmn.satmc.SatmcFact;
import eu.aniketos.securebpmn.satmc.SatmcFunction;
import eu.aniketos.securebpmn.satmc.SatmcMessage;
import eu.aniketos.securebpmn.satmc.SatmcTraceStep;
import eu.aniketos.securebpmn.satmc.Summary;
import eu.aniketos.securebpmn.util.DialogUtil;
import eu.aniketos.securebpmn.validation.SCVMValidationConstants;
import eu.aniketos.securebpmn.validation.ValidateAslanRunnable;
import eu.aniketos.securebpmn.visualization.ActionType;
import eu.aniketos.securebpmn.visualization.VisualizationElement;
import eu.aniketos.securebpmn.visualization.rbac.AttackTracePlayer;
import eu.aniketos.securebpmn.visualization.rbac.AttackTraceStep;
import eu.aniketos.securebpmn.visualization.rbac.RbacVisualization;

/**
 * This feature performs an analysis of the access control specification and
 * constraints contained in the process and notifies the user, if violations
 * were found or not. This feature is not intended for direct use, you should
 * use the class ValidateAslanLocalFeature or ValidateAslanWebFeature, depending
 * on how you want SATMC to be executed.
 *
 *
 */
public class ValidateAslanFeature extends AbstractCustomFeature {

    public ValidateAslanFeature(IFeatureProvider fp) {
        super(fp);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.graphiti.features.impl.AbstractFeature#getName()
     */
    @Override
    public String getName() {
        return "Validate ASLan"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.graphiti.features.custom.AbstractCustomFeature#getDescription
     * ()
     */
    @Override
    public String getDescription() {
        return "Validate ASLan using SATMC"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.graphiti.features.custom.AbstractCustomFeature#canExecute
     * (org.eclipse.graphiti.features.context.ICustomContext)
     */
    @Override
    public boolean canExecute(ICustomContext context) {
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.graphiti.features.custom.ICustomFeature#execute(org.eclipse
     * .graphiti.features.context.ICustomContext)
     */
    public void execute(ICustomContext context) {
        this.execute(true);
    }

    /**
     * Starts the analysis of the ASLan representation and reacts according to
     * the result, either displaying a message to the user or starting the
     * attack trace visualization if an attack was found.
     *
     * @param local
     *            true if the local SATMC binary should be used, false if the
     *            SATMC web service should be used.
     */
    public void execute(boolean local) {

        // check for unsupported elements.
        List<String> unsupportedElements = getUnsupportedElementTypes();

        if (unsupportedElements.size() > 0) {
            // ask user what to do
            StringBuilder ueMessage = new StringBuilder();
            ueMessage
            .append("The diagram contains the following currently unsupported elements:\n");
            for (String ue : unsupportedElements) {
                ueMessage.append(ue.endsWith("Impl") ? ue.substring(0,
                                 ue.length() - 4) : ue);
                ueMessage.append("\n");
            }
            ueMessage
            .append("\nThe analysis result might be incorrect. Continue anyway?");
            String[] ueButtons = { "Continue", "Cancel" };

            int ueContinue = DialogUtil.openMessageDialog(
                                 "Unsupported elements found!", ueMessage.toString(),
                                 DialogUtil.WARNING, ueButtons, 1);

            if (ueContinue == 1)
                return;
        }

        // get & parse result
        SatmcMessage result = new SatmcMessage(null, null, null, null);

        try {

            final IProgressService progressService = PlatformUI.getWorkbench()
                    .getProgressService();

            final ValidateAslanRunnable runnable = new ValidateAslanRunnable(
                getDiagram(), result, local);

            progressService.busyCursorWhile(runnable);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // check if validation/parsing was successful
        if (result.summary == null) {
            return;

        } else if (result.summary == Summary.ERROR) {
            DialogUtil.openMessageDialog("Error", "SATMC Error",
                                         DialogUtil.ERROR);
            return;

        } else if (result.summary == Summary.INCONCLUSIVE) {
            DialogUtil.openMessageDialog("Validation inconclusive",
                                         "SATMC analysis was inconclusive.", DialogUtil.INFO);
            return;

        } else if (result.summary == Summary.NO_ATTACK_FOUND) {
            DialogUtil.openMessageDialog("No attack found", "No attack found!",
                                         DialogUtil.INFO);
            return;

        } else if (result.summary == Summary.UNKNOWN) {
            DialogUtil.openMessageDialog("Unknown result",
                                         "SATMC result is unknown!", DialogUtil.WARNING);
            return;

        } else if (result.summary == Summary.ATTACK_FOUND) {

            List<AttackTraceStep> traceList = extractElementIDsAndDescription(result);

            DialogUtil.openMessageDialog("Attack found",
                                         "Attack found!\n\nViolated goal: " + result.goal,
                                         DialogUtil.ERROR);

            // set result in storage
            RbacVisualization.getInstance().setResult(result);

            findBusinessObjectsAndPictogramElements(traceList);

            // create & store player
            RbacVisualization.getInstance().setPlayer(
                new AttackTracePlayer(getDiagram(), traceList));

            // open view
            try {
                PlatformUI
                .getWorkbench()
                .getActiveWorkbenchWindow()
                .getActivePage()
                .showView(
                    "eu.aniketos.securebpmn.validation.view");
            } catch (PartInitException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Replaces the task instance IDs with the task IDs in the violated goal of
     * the result. Extracts the attack trace steps and for each step the task
     * ID, the type of the step (claim, execute or violation) and the
     * description of the step.
     *
     * @param result
     *            The SATMC result that should be analyzed and modified.
     * @return A List of AttackTraceSteps, each step containing the task ID,
     *         type and description.
     */
    private List<AttackTraceStep> extractElementIDsAndDescription(
        SatmcMessage result) {

        List<AttackTraceStep> traceList = new ArrayList<AttackTraceStep>();

        // dummy step for StartEvent
        traceList.add(new AttackTraceStep());

        // get number of tasks in goal
        int tasksInGoal = 0;
        for (SatmcFact fact : result.goal.args) {
            if (fact instanceof SatmcFunction) {
                SatmcFunction func = (SatmcFunction) fact;
                if (func.name.equals("n") && func.args.size() == 1) {
                    tasksInGoal++;
                }
            }
        }
        int goalTasksStartIndex = result.goal.args.size() - tasksInGoal;

        // Goals:
        // name: sod; args: 0. user 1. task1 2. task2
        // name: bod; args: 0. user1 1. user2 2. task1 3. task2

        // Rules:
        // name: h_TaskExecution; args: 0. user 1. role 2. task 3. tInstance
        // 4. inData 5. outData
        // name: atask_execution; args: 0. task 1. tInstance 2. inData 3.
        // outData
        // name: authorizeTaskExecution; args: 0. user 1. role 2. task 3.
        // tInstance
        for (SatmcTraceStep sts : result.trace) {

            AttackTraceStep traceElement = new AttackTraceStep();
            String stepText = "";

            for (SatmcFunction rule : sts.rules) {

                if (rule.args.size() > 5
                        && rule.name
                        .equals(SCVMValidationConstants.HUMAN_TASK_RULE_NAME)) {

                    // UserTask

                    // replace taskInstances with taskIDs in goal
                    if (result.goal.args.size() > 2
                            && result.goal.name
                            .startsWith(SCVMValidationConstants.GOAL_SOD_PREFIX)) {

                        for (int i = goalTasksStartIndex; i < result.goal.args
                                .size(); i++) {
                            if (rule.args.get(3).toString()
                                    .equals(result.goal.args.get(i).toString())) {
                                result.goal.args.set(i, rule.args.get(2));
                            }
                        }
                    } else if (result.goal.args.size() > 3
                               && result.goal.name
                               .startsWith(SCVMValidationConstants.GOAL_BOD_PREFIX)) {

                        for (int i = goalTasksStartIndex; i < result.goal.args
                                .size(); i++) {
                            if (rule.args.get(3).toString()
                                    .equals(result.goal.args.get(i).toString())) {
                                result.goal.args.set(i, rule.args.get(2));
                            }
                        }
                    }

                    traceElement.addInvolvedElement(new VisualizationElement(
                                                        rule.args.get(2).toString(), ActionType.EXECUTE));
                    stepText += "Human task \"" + rule.args.get(2).toString()
                                + "\" executed by user \""
                                + rule.args.get(0).toString() + "\" with role \""
                                + rule.args.get(1).toString() + "\".\n";

                } else if (rule.args.size() > 3
                           && rule.name
                           .equals(SCVMValidationConstants.AUTOMATED_TASK_RULE_NAME)) {

                    // any other Task

                    traceElement.addInvolvedElement(new VisualizationElement(
                                                        rule.args.get(0).toString(), ActionType.EXECUTE));
                    stepText += "Automated task \""
                                + rule.args.get(0).toString() + "\" executed.\n";

                } else if (rule.args.size() > 3
                           && rule.name
                           .equals(SCVMValidationConstants.TASK_AUTHORIZATION_RULE_NAME)) {

                    // claiming of a Task

                    traceElement.addInvolvedElement(new VisualizationElement(
                                                        rule.args.get(2).toString(), ActionType.CLAIM));
                    stepText += "User \"" + rule.args.get(0).toString()
                                + "\" claimed task \""
                                + rule.args.get(2).toString() + "\" using role \""
                                + rule.args.get(1).toString() + "\".\n";

                } else {
                    // check for gateway

                    String[] ruleNameParts = rule.name.split("_");

                    if (ruleNameParts.length > 1) {

                        if (ruleNameParts[0].equals("w")
                                && ruleNameParts[1].contains("gateway")) {

                            // AND-split or AND-join
                            traceElement
                            .addInvolvedElement(new VisualizationElement(
                                                    ruleNameParts[1],
                                                    ActionType.WORKFLOW));
                            stepText += "Passing parallel gateway \""
                                        + ruleNameParts[1] + "\".\n";

                        } else if (ruleNameParts[0].contains("gateway")
                                   && ruleNameParts[1].startsWith("branch")) {

                            // XOR-split or XOR-join
                            traceElement
                            .addInvolvedElement(new VisualizationElement(
                                                    ruleNameParts[0],
                                                    ActionType.WORKFLOW));
                            stepText += "Passing exclusive gateway \""
                                        + ruleNameParts[0] + "\".\n";

                        }

                    }

                }
            }

            if (traceElement.getInvolvedElements().size() > 0) {
                traceElement.setDescription(stepText);
                traceList.add(traceElement);
            }
        }

        // step for goal violation highlighting
        AttackTraceStep goalStep = new AttackTraceStep();
        goalStep.setDescription("Violation of goal \"" + result.goal.toString()
                                + "\".");

        if (result.goal.args.size() > 2
                && result.goal.name
                .startsWith(SCVMValidationConstants.GOAL_SOD_PREFIX)) {
            // SoD
            for (int i = goalTasksStartIndex; i < result.goal.args.size(); i++) {
                goalStep.addInvolvedElement(new VisualizationElement(
                                                result.goal.args.get(i).toString(),
                                                ActionType.VIOLATION));
            }

            String sodElementID = result.goal.name.substring(
                                      SCVMValidationConstants.GOAL_SOD_PREFIX.length(),
                                      result.goal.name.lastIndexOf("_"));
            goalStep.addInvolvedElement(new VisualizationElement(sodElementID,
                                        ActionType.VIOLATION));

        } else if (result.goal.args.size() > 3
                   && result.goal.name
                   .startsWith(SCVMValidationConstants.GOAL_BOD_PREFIX)) {
            // BoD
            for (int i = goalTasksStartIndex; i < result.goal.args.size(); i++) {
                goalStep.addInvolvedElement(new VisualizationElement(
                                                result.goal.args.get(i).toString(),
                                                ActionType.VIOLATION));
            }

            String bodElementID = result.goal.name.substring(
                                      SCVMValidationConstants.GOAL_BOD_PREFIX.length(),
                                      result.goal.name.lastIndexOf("_"));
            goalStep.addInvolvedElement(new VisualizationElement(bodElementID,
                                        ActionType.VIOLATION));
        }

        traceList.add(goalStep);

        return traceList;

    }

    /**
     * Finds the BusinessObjects and PictogramElements for each task ID in the
     * provided lists and adds these to the single elements in the list.
     *
     * @param traceList
     *            The List of AttackTraceSteps for which the BOs and PEs should
     *            be found.
     */
    private void findBusinessObjectsAndPictogramElements(
        List<AttackTraceStep> traceList) {

        // get diagram elements
        List<FlowElement> diagramElements = new ArrayList<FlowElement>();
        for (EObject object : getDiagram().eResource().getContents()) {
            if (object instanceof FlowElement)
                diagramElements.add((FlowElement) object);
        }

        for (AttackTraceStep traceStep : traceList) {
            for (VisualizationElement element : traceStep.getInvolvedElements()) {

                // find BO
                for (FlowElement bo : diagramElements) {
                    if (bo.getId().equals(element.getId())) {
                        element.setbObject(bo);
                        break;
                    }
                }

                if (element.getbObject() == null) {
                    System.err
                    .println("[SCVM-RBAC] No FlowElement found for ID \""
                             + element.getId() + "\"!");
                }

                // find corresponding PE
                List<PictogramElement> pElements = Graphiti.getLinkService()
                                                   .getPictogramElements(getDiagram(),
                                                           element.getbObject());

                for (PictogramElement pElement : pElements) {

                    if (pElement instanceof ContainerShape) {

                        ContainerShape cs = (ContainerShape) pElement;

                        element.setpElement(cs);

                    }
                }

                if (element.getpElement() == null) {
                    System.err
                    .println("[SCVM-RBAC] No PictogramElement found for ID \""
                             + element.getId() + "\"!");
                }

            }
        }

        // get and add start event
        // there should only be one FlowNode after a StartEvent

        StartEvent startEvent = null;

        // find start event
        if (traceList.size() > 1) {

            List<VisualizationElement> firstElements = traceList.get(1)
                    .getInvolvedElements();

            if (firstElements.size() > 0) {

                FlowNode firstNode = (FlowNode) firstElements.get(0)
                                     .getbObject();

                if (firstNode.getIncoming().size() > 0) {
                    FlowNode start = firstNode.getIncoming().get(0)
                                     .getSourceRef();

                    if (start instanceof StartEvent) {

                        startEvent = (StartEvent) start;

                    }
                }
            }

        }

        // create entry if we found it
        if (startEvent != null) {

            VisualizationElement startElement = new VisualizationElement(
                startEvent.getId(), ActionType.WORKFLOW);
            startElement.setbObject(startEvent);

            // find corresponding pictogram element
            List<PictogramElement> pElements = Graphiti.getLinkService()
                                               .getPictogramElements(getDiagram(), startEvent);

            for (PictogramElement pElement : pElements) {

                // change element representation
                if (pElement instanceof ContainerShape) {

                    ContainerShape cs = (ContainerShape) pElement;
                    startElement.setpElement(cs);

                }
            }

            traceList.get(0).addInvolvedElement(startElement);
            traceList.get(0).setDescription("The process has been started.");
        }

    }

    /**
     * Returns a list with the names of the BPMN 2.0 elements that are currently
     * not supported by the analysis.
     *
     * @return A List with the names of the unsupported elements as Strings.
     */
    private List<String> getUnsupportedElementTypes() {
        List<String> res = new ArrayList<String>();

        for (EObject object : getDiagram().eResource().getContents()) {

            if (object instanceof SubProcess || object instanceof CallActivity
                    || object instanceof InclusiveGateway
                    || object instanceof BoundaryEvent) {
                res.add(object.getClass().getSimpleName());
            }

        }

        // remove duplicates
        HashSet<String> h = new HashSet<String>(res);
        res.clear();
        res.addAll(h);

        return res;
    }
}
