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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.securebpmn2.ActivityAction;
import org.eclipse.securebpmn2.Permission;
import org.eclipse.securebpmn2.SeparationOfDuty;

/**
 * Creates the XACML representation of a SecureBPMN SeparationOfDuty element.
 *
 *
 */
public class SeparationOfDutyExport {

    /**
     * Generates the XACML representation of the provided SeparationOfDuty element
     * and sends the output to the provided XacmlFileBuilder.
     *
     * @param sod
     *            The SeparationOfDuty element for which the representation should
     *            be generated.
     * @param afb
     *            The XacmlFileBuilder the output is sent to.
     */
    public static void createSeparationOfDutyElements(SeparationOfDuty sod, XacmlFileBuilder xfb) {

        if (sod.isDynamicEnforcement()) return;

        List<String> assignTasks = new ArrayList<String>();
        List<String> claimTasks = new ArrayList<String>();
        List<String> completeTasks = new ArrayList<String>();

        for (Permission p : sod.getPermissions()) {

            if (p.getActions().size() == 0) {
                System.err.println("[WARNING] The permission with name=\"" + p.getPName() + "\" and id=\"" + p.getId() + "\" has no action. This should never happen! Check your .activiti diagram file.");
                continue;
            }

            // Permissions only have one Action
            if (p.getActions().get(0) instanceof ActivityAction) {
                ActivityAction a = (ActivityAction) p.getActions().get(0);

                if (a.getActionName().equals("Complete")) {

                    completeTasks.add(a.getActivity().getId());

                } else if (a.getActionName().equals("Claim")) {

                    claimTasks.add(a.getActivity().getId());

                } else if (a.getActionName().equals("Assign")) {

                    assignTasks.add(a.getActivity().getId());

                } else if (a.getActionName().equals("Full Access")) {

                    assignTasks.add(a.getActivity().getId());
                    claimTasks.add(a.getActivity().getId());
                    completeTasks.add(a.getActivity().getId());

                }

            }
        }

        if (assignTasks.size() > 1) {
            // TODO what to check? only executed() is persistent...
        }

        if (claimTasks.size() > 1) {
            // TODO what to check? only executed() is persistent...
        }

        if (completeTasks.size() > 1) {
            // safe default assumptions
            int minUsers = completeTasks.size();
            int maxActions = 1;

            if (sod.getMinimumUsers() == null) {
                System.out.println("[SCVM-EXPORT-XACML] minimum users for SoD \"" + sod.getId() + "\" not set, using " + minUsers + ".");
            } else {
                if (sod.getMinimumUsers() > completeTasks.size() || sod.getMinimumUsers() < 1) {
                    System.err.println("[SCVM-EXPORT-XACML] invalid input for minimum users at SoD \"" + sod.getId() + "\", using " + minUsers + ".");
                } else {
                    minUsers = sod.getMinimumUsers();
                }
            }

            if (sod.getMaxUserActionsPermitted() == null) {
                System.out.println("[SCVM-EXPORT-XACML] maximum permitted actions for SoD \"" + sod.getId() + "\" not set, using " + maxActions + ".");
            } else {
                if (sod.getMaxUserActionsPermitted() < 1) {
                    System.err.println("[SCVM-EXPORT-XACML] invalid input for maximum permitted actions at SoD \"" + sod.getId() + "\", using " + maxActions + ".");
                } else {
                    maxActions = sod.getMaxUserActionsPermitted();
                }
            }

            // calculate numeric partitions
            final List<int[]> allPartitions = ExportUtil.generateIntegerPartitions(completeTasks.size());

            // select invalid partitions
            List<int[]> selectedPartitions = new ArrayList<int[]>();
            for (int[] part : allPartitions) {
                if (part.length > 0 && (part.length < minUsers
                                        || part[0] > maxActions)) {
                    selectedPartitions.add(part);
                }
            }

            // create goals for partitions
            String[] tasks = new String[completeTasks.size()];
            for (int i = 0; i < completeTasks.size(); i++) {
                tasks[i] = completeTasks.get(i);
            }
            List<int[]> permList = ExportUtil.generateLocationPermutations(tasks.length);

            for (int[] part : selectedPartitions) {
                // we assume that tasks.lengh equals the sum of each partition
                List<List<Set<String>>> sets = new ArrayList<List<Set<String>>>();

                // loop over index permutations
                for (int[] perm : permList) {
                    int indexStart = 0;
                    // list of sets (parts of the partition) for the current permutation
                    List<Set<String>> setList = new ArrayList<Set<String>>();
                    // create the sets
                    for (int partLength : part) {
                        Set<String> partTasks = new HashSet<String>();
                        // get the tasks and add them to the set
                        for (int i = 0; i < partLength; i++) {
                            partTasks.add(tasks[perm[indexStart + i]-1]);
                        }
                        indexStart += partLength;
                        setList.add(partTasks);
                    }
                    // check if partition config already exists
                    boolean newSet = true;
                    for (List<Set<String>> presentSetList : sets) {
                        if (presentSetList.equals(setList)) {
                            newSet = false;
                            break;
                        }
                    }
                    // add partition config if new
                    if (newSet) {
                        sets.add(setList);
                    }

                }

                /*
                				// create goals for this partition
                				for (List<Set<String>> setArray : sets) {
                					// generate goal
                					int numUsers = setArray.size();
                					int numTasks = tasks.length;
                					List<String> natVars = new ArrayList<String>();
                					StringBuilder goalString = new StringBuilder();
                					goalString.append("(");

                					// generate user variables
                					for (int i = 0; i < numUsers; i++) {
                						afb.addType("user", "U" + i);
                						if (i > 0) goalString.append(",");
                						goalString.append("U" + i);
                					}

                					// generate task nat variables
                					for (int i = 0; i < numTasks; i++) {
                						String currentVar = afb.addNatVar();
                						natVars.add(currentVar);
                						goalString.append("," + currentVar);
                					}

                					goalString.append("):=");

                					// generate facts
                					int natVarPos = 0;
                					for (int i = 0; i < numUsers; i++) {
                						for (String currentTask : setArray.get(i)) {
                							goalString.append("  executed(U" + i + ",task(" + currentTask + "," + natVars.get(natVarPos) + ")).");
                							natVarPos++;
                						}
                					}

                					// add goal
                					String finalGoalString = goalString.toString();
                					afb.addGoal("sod_" + sod.getId() + "_", finalGoalString.substring(0, finalGoalString.length()-1));
                				}
                */
            }
        }
    }
}
