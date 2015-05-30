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

package eu.aniketos.securebpmn.export.aslan.export;

import org.activiti.designer.eclipse.preferences.PreferencesUtil;
import org.activiti.designer.util.preferences.Preferences;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.securebpmn2.ActivityAction;
import org.eclipse.securebpmn2.Permission;
import org.eclipse.securebpmn2.Role;

/**
 * Creates the ASLan representation of a BPMN 2.0 Task element.
 *
 */
public class TaskExport {

    /**
     * Generates the ASLan representation of the provided Task element and sends
     * the output to the provided AslanFileBuilder.
     *
     * @param task
     *            The Task element for which the representation should be
     *            generated.
     * @param afb
     *            The AslanFileBuilder the output is sent to.
     */
    public static void createTaskElements(Task task, AslanFileBuilder afb) {
        // task_to_data assignments
        afb.addTaskType(task);

        final String taskID = task.getId();

        afb.addType("set", "in_" + taskID);
        afb.addType("set", "out_" + taskID);
        afb.addInit("task_to_data(" + taskID + ",in_" + taskID + ",out_"
                    + taskID + ")");

        boolean isHumanTask = false;

        if (task instanceof UserTask
                || PreferencesUtil
                .getBooleanPreference(Preferences.ALL_TASKS_AS_HUMANTASKS))
            isHumanTask = true;

        if (isHumanTask) {

            // SecureBPMN RBAC
            for (ActivityAction a : task.getActivityActions()) {
                if (a.getActionName() != null
                        && (a.getActionName().equals("Assign") || a
                            .getActionName().equals("Full Access"))) {

                    for (Permission p : a.getPermissions()) {
                        for (Role r : p.getRoles()) {

                            afb.addHornClause("hc poto_" + taskID + ":= poto("
                                              + r.getName().toLowerCase() + "," + taskID
                                              + ")");

                        }
                    }

                }
            }
        }

        // control flow
        if (task.getIncoming().size() == 0) {
            // no predecessor, ignore
            return;
        }

        String rule = "step w_" + taskID + "(";
        String natVar1 = afb.addNatVar();

        final FlowElement predecessor = task.getIncoming().get(0)
                                        .getSourceRef();

        if (predecessor instanceof Task) {

            String natVar2 = afb.addNatVar();

            // sequence: Task to Task
            rule += natVar2 + "," + natVar1 + ") := done(task("
                    + predecessor.getId() + "," + natVar2 + "))";

        } else if (predecessor instanceof Gateway) {

            // AND-split, AND-join, XOR-split, XOR-join
            rule += natVar1 + ") := " + predecessor.getId() + "_to_" + taskID;

        } else if (predecessor instanceof StartEvent) {

            // sequence: StartEvent to Task
            rule += natVar1 + ") := start_event_" + predecessor.getId();

        } else {
            // predecessor is a not supported FlowElement
            return;
        }

        rule += "=[exists " + natVar1 + "] => ready(task(" + taskID + ","
                + natVar1 + "))";

        afb.addRule(rule);
    }

}
