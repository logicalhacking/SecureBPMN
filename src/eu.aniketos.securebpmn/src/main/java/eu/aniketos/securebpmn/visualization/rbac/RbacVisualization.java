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

import eu.aniketos.securebpmn.satmc.SatmcFunction;
import eu.aniketos.securebpmn.satmc.SatmcMessage;
import eu.aniketos.securebpmn.satmc.SatmcTraceStep;
import eu.aniketos.securebpmn.satmc.Summary;
import eu.aniketos.securebpmn.validation.SCVMValidationConstants;

/**
 * Singleton Class that holds the parsed SATMC result and the player class for
 * automated attack trace visualization playback.
 *
 *
 */
public class RbacVisualization {

    private static RbacVisualization _instance;

    private SatmcMessage parsedResult;
    private AttackTracePlayer player;

    /**
     * Default constructor.
     */
    private RbacVisualization() {
        parsedResult = null;
        player = null;
    }

    /**
     * Retrieves the singleton instance.
     *
     * @return The class instance.
     */
    public static synchronized RbacVisualization getInstance() {
        if (_instance == null) {
            _instance = new RbacVisualization();
        }
        return _instance;
    }

    /**
     * Returns if the parsed result is set.
     *
     * @return true if it is set, false if not.
     */
    public boolean isResultSet() {
        return parsedResult == null ? false : true;
    }

    /**
     * Sets the parsed result.
     *
     * @param result
     *            The new result to be set.
     */
    public void setResult(SatmcMessage result) {
        parsedResult = result;
    }

    /**
     * Returns if the visualization is currently running.
     *
     * @return true if it is running, false if not.
     */
    public boolean isVisualizationRunning() {
        return player == null ? false : true;
    }

    /**
     * Sets the player for automated playback.
     *
     * @param player
     *            The new player to be set.
     */
    public void setPlayer(AttackTracePlayer player) {
        this.player = player;
    }

    /**
     * Retrieves the current player.
     *
     * @return The current player.
     */
    public AttackTracePlayer getPlayer() {
        return this.player;
    }

    /**
     * Retrieves the rules of the attack trace as a String.
     *
     * @return The attack trace as a String.
     */
    public String getAttackTrace() {
        if (parsedResult == null)
            return "No result present!";

        String result = "";

        int stepCount = 1;
        for (SatmcTraceStep sts : parsedResult.trace) {

            if (sts.rules.toString().length() < 3)
                continue;

            result += stepCount + ": " + sts.rules.toString() + "\n";
            stepCount++;
        }

        return result;
    }

    /**
     * Retrieves a filtered attack trace of the parsed result as a String. Only
     * rules for taks claiming and execution are included.
     *
     * @return The filtered attack trace as a String.
     */
    public String getFilteredAttackTrace() {
        if (parsedResult == null)
            return "No result present!";

        String result = "";

        int stepCount = 1;
        for (SatmcTraceStep sts : parsedResult.trace) {

            boolean first = true;
            String resultLine = "";

            for (SatmcFunction rule : sts.rules) {
                if (rule.name
                        .equals(SCVMValidationConstants.HUMAN_TASK_RULE_NAME)
                        || rule.name
                        .equals(SCVMValidationConstants.AUTOMATED_TASK_RULE_NAME)
                        || rule.name
                        .equals(SCVMValidationConstants.TASK_AUTHORIZATION_RULE_NAME)) {

                    if (first) {
                        resultLine += stepCount + ": ";
                        first = false;
                    } else {
                        resultLine += ", ";
                    }

                    resultLine += rule.toString();

                }
            }
            if (resultLine.length() > 0) {
                result += resultLine + "\n";
                stepCount++;
            }
        }

        return result;
    }

    /**
     * Retrieves the violated goal as a String.
     *
     * @return The violated goal as a string.
     */
    public String getViolatedGoal() {
        if (parsedResult == null
                || parsedResult.summary != Summary.ATTACK_FOUND) {
            return "";
        } else {
            return parsedResult.goal.toString();
        }

    }

    /**
     * Resets the class: Disposes the player and deletes the result.
     */
    public void reset() {
        if (player != null)
            player.prepareDisposal();
        player = null;
        parsedResult = null;
    }

}
