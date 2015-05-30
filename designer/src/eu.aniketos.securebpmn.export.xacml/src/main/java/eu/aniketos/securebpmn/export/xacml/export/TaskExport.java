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

package eu.aniketos.securebpmn.export.xacml.export;

import java.net.URI;
import java.net.URISyntaxException;


import org.activiti.designer.eclipse.preferences.PreferencesUtil;
import org.activiti.designer.util.preferences.Preferences;

import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.UserTask;

import org.eclipse.securebpmn2.ActivityAction;
import org.eclipse.securebpmn2.Permission;
import org.eclipse.securebpmn2.Role;

import com.sun.xacml.combine.DenyOverridesRuleAlg;
import com.sun.xacml.combine.RuleCombiningAlgorithm;

/**
 * Creates the RBAC XACML-Rules for each HumanTask in the Diagram
 *
 */
public class TaskExport {

    /**
     * Gathers information needed for the XACML-Policy rules section.
     *
     * @param task
     *            The {@link Task} element for which the information should be
     *            gathered
     *
     * @param xfb
     *            The {@link XacmlFileBuilder} used to create the rules
     *
     */
    public static void gatherTaskInfo(Task task, XacmlFileBuilder xfb) {

        final String taskID = task.getId();

        boolean isHumanTask = false;
        //boolean isSodTask = false;

        if (task instanceof UserTask
                || PreferencesUtil
                .getBooleanPreference(Preferences.ALL_TASKS_AS_HUMANTASKS))
            isHumanTask = true;

        if (isHumanTask) {

            /*
            UserTask userTask = (UserTask) task;
            List<CandidateGroup> candidateGroups = userTask.getCandidateGroups();
            List<String> groups = new ArrayList<String>();

            for (Iterator<CandidateGroup> iterator = candidateGroups.iterator(); iterator.hasNext();) {
            	CandidateGroup candidateGroup = (CandidateGroup) iterator.next();
            	groups.add(candidateGroup.getGroup());
            }

            for (Iterator<String> iterator = groups.iterator(); iterator.hasNext();) {
            	String string = (String) iterator.next();
            	try {
            		xfb.createTargetMatch(1, string);
            		xfb.createTargetMatch(2, taskID);
            		xfb.createTargetMatch(3, "Full Access");

            		if (task.getIncomingSecurityFlow().isEmpty()) {
            			xfb.createRule(
            					URI.create("rule_for_" + taskID), 0,
            					task.getName(), xfb.createTarget(),
            					null);
            			RuleCombiningAlgorithm ruleCombiningAlgorithm = new DenyOverridesRuleAlg();
            			xfb.createPolicy(
            					URI.create("policy_for_" + taskID),
            					ruleCombiningAlgorithm,
            					xfb.createTarget(), xfb.getRules());
            		}
            	} catch (URISyntaxException e) {
            		// TODO Auto-generated catch block
            		e.printStackTrace();
            	}
            }
            */

            // SecureBPMN RBAC
            for (ActivityAction a : task.getActivityActions()) {
                if (a.getActionName() != null
                        && (a.getActionName().equals("Assign") || a
                            .getActionName().equals("Full Access"))) {
                    try {
                        xfb.createTargetMatch(3, a.getActionName());
                    } catch (URISyntaxException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    for (Permission p : a.getPermissions()) {
                        for (Role r : p.getRoles()) {
                            try {
                                xfb.createTargetMatch(2, taskID);

                                xfb.createTargetMatch(1, r.getName().toLowerCase());

                            } catch (URISyntaxException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            // TODO see todo below
                            /*
                            if(!task.getIncomingSecurityFlow().isEmpty()) {
                            isSodTask = task
                            		.getIncomingSecurityFlow().get(0)
                            		.getSourceRefNode().getId()
                            		.startsWith("securitySod");
                            }*/


                            // simple human task
                            //if (task.getIncomingSecurityFlow().isEmpty()) {
                            xfb.createRule(
                                URI.create("rule_for_" + taskID), 0,
                                task.getName(), xfb.createTarget(),
                                null);
                            RuleCombiningAlgorithm ruleCombiningAlgorithm = new DenyOverridesRuleAlg();
                            xfb.createPolicy(
                                URI.create("policy_for_" + taskID),
                                ruleCombiningAlgorithm,
                                xfb.createTarget(), xfb.getRules());
                            //}// TODO insert condition for SoD-Task
                            /*
                            else if (isSodTask) {

                            	List<Task> connectedTasks = new ArrayList<Task>();
                            	for (Iterator<SecurityFlow> it = task
                            			.getIncomingSecurityFlow().iterator(); it
                            			.hasNext();) {
                            		SecurityFlow securityFlow = (SecurityFlow) it
                            				.next();
                            		Task connectedTask = (Task) securityFlow.getTargetRefNode();
                            		connectedTasks.add(connectedTask);

                            	}

                            	xfb.createRule(
                            			URI.create("rule_for_" + taskID), 0,
                            			task.getName(), xfb.createTarget(),
                            			xfb.createSodCondition(task, connectedTasks));
                            }*/
                        }
                    }
                }
            }
        }
    }
}
