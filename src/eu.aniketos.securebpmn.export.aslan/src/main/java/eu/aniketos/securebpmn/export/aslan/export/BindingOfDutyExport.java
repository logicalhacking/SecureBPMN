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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.securebpmn2.ActivityAction;
import org.eclipse.securebpmn2.BindingOfDuty;
import org.eclipse.securebpmn2.Permission;

/**
 * Creates the ASLan representation of a SecureBPMN BindingOfDuty element.
 *
 *
 */
public class BindingOfDutyExport {

    /**
     * Generates the ASLan representation of the provided BindingOfDuty element
     * and sends the output to the provided AslanFileBuilder.
     *
     * @param bod
     *            The BindingOfDuty element for which the representation should
     *            be generated.
     * @param afb
     *            The AslanFileBuilder the output is sent to.
     */
    public static void createBindingOfDutyElements(BindingOfDuty bod,
            AslanFileBuilder afb) {

        if (bod.isDynamicEnforcement())
            return;

        List<String> assignTasks = new ArrayList<String>();
        List<String> claimTasks = new ArrayList<String>();
        List<String> completeTasks = new ArrayList<String>();

        for (Permission p : bod.getPermissions()) {

            if (p.getActions().size() == 0) {
                System.err
                .println("[WARNING] The permission with name=\""
                         + p.getPName()
                         + "\" and id=\""
                         + p.getId()
                         + "\" has no action. This should never happen! Check your .activiti diagram file.");
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
            int maxUsers = 1;
            int numActions = completeTasks.size();

            if (bod.getMaxUsers() == null) {
                System.out
                .println("[SCVM-EXPORT-ASLAN] maximum users for BoD \""
                         + bod.getId() + "\" not set, using " + maxUsers
                         + ".");
            } else {
                if (bod.getMaxUsers() < 1) {
                    System.err
                    .println("[SCVM-EXPORT-ASLAN] invalid input for maximum users at BoD \""
                             + bod.getId()
                             + "\", using "
                             + maxUsers
                             + ".");
                } else {
                    maxUsers = bod.getMaxUsers();
                }
            }

            if (bod.getSameUserActionCount() == null) {
                System.out
                .println("[SCVM-EXPORT-ASLAN] number of actions for BoD \""
                         + bod.getId()
                         + "\" not set, using "
                         + numActions + ".");
            } else {
                if (bod.getSameUserActionCount() > completeTasks.size()
                        || bod.getSameUserActionCount() < 1) {
                    System.err
                    .println("[SCVM-EXPORT-ASLAN] invalid input for number of actions at BoD \""
                             + bod.getId()
                             + "\", using "
                             + numActions
                             + ".");
                } else {
                    numActions = bod.getSameUserActionCount();
                }
            }

            // calculate numeric partitions
            final List<int[]> allPartitions = ExportUtil
                                              .generateIntegerPartitions(completeTasks.size());

            // select invalid partitions
            List<int[]> selectedPartitions = new ArrayList<int[]>();
            for (int[] part : allPartitions) {
                boolean invalidSummandFound = false;
                for (int i : part) {
                    if (i != numActions)
                        invalidSummandFound = true;
                }
                if (part.length > 0
                        && (part.length > maxUsers || invalidSummandFound)) {
                    selectedPartitions.add(part);
                }
            }

            // create goals for partitions
            String[] tasks = new String[completeTasks.size()];
            for (int i = 0; i < completeTasks.size(); i++) {
                tasks[i] = completeTasks.get(i);
            }
            List<int[]> permList = ExportUtil
                                   .generateLocationPermutations(tasks.length);

            for (int[] part : selectedPartitions) {
                // we assume that tasks.lengh equals the sum of each partition
                List<List<Set<String>>> sets = new ArrayList<List<Set<String>>>();

                // loop over index permutations
                for (int[] perm : permList) {
                    int indexStart = 0;
                    // list of sets (parts of the partition) for the current
                    // permutation
                    List<Set<String>> setList = new ArrayList<Set<String>>();
                    // create the sets
                    for (int partLength : part) {
                        Set<String> partTasks = new HashSet<String>();
                        // get the tasks and add them to the set
                        for (int i = 0; i < partLength; i++) {
                            partTasks.add(tasks[perm[indexStart + i] - 1]);
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
                        if (i > 0)
                            goalString.append(",");
                        goalString.append("U" + i);
                    }

                    // generate task nat variables
                    for (int i = 0; i < numTasks; i++) {
                        String currentVar = afb.addNatVar();
                        natVars.add(currentVar);
                        goalString.append("," + currentVar);
                    }

                    goalString.append("):=");

                    // generate "executed" facts
                    int natVarPos = 0;
                    for (int i = 0; i < numUsers; i++) {
                        for (String currentTask : setArray.get(i)) {
                            goalString.append("  executed(U" + i + ",task("
                                              + currentTask + ","
                                              + natVars.get(natVarPos) + ")).");
                            natVarPos++;
                        }
                    }

                    // generate "not equal" facts
                    StringBuilder neqString = new StringBuilder();
                    for (int i = 0; i < numUsers - 1; i++) {
                        for (int j = i + 1; j < numUsers; j++) {
                            neqString.append("& not(equal(U" + i + ",U" + j
                                             + "))");
                        }
                    }

                    // add goal
                    String finalGoalString = goalString.toString();
                    afb.addGoal(
                        "bod_" + bod.getId() + "_",
                        finalGoalString.substring(0,
                                                  finalGoalString.length() - 1)
                        + neqString.toString());
                }
            }

        }

    }

}
