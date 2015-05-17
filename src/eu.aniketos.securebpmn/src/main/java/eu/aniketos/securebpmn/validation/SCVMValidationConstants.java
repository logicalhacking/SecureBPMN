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

package eu.aniketos.securebpmn.validation;

/**
 * Contains some values used in the attack trace visualization.
 *
 *
 */
public final class SCVMValidationConstants {

    // ASLan rule names
    public static final String HUMAN_TASK_RULE_NAME = "h_taskExecution";
    public static final String AUTOMATED_TASK_RULE_NAME = "atask_execution";
    public static final String TASK_AUTHORIZATION_RULE_NAME = "authorizeTaskExecution";
    public static final String GOAL_SOD_PREFIX = "sod_";
    public static final String GOAL_BOD_PREFIX = "bod_";

    // Highlighting colors
    public static final int[] COLOR_HL_EXEC_FG = { 0, 255, 255 };
    public static final int[] COLOR_HL_EXEC_BG = { 0, 230, 230 };

    public static final int[] COLOR_HL_VIOL_FG = { 255, 0, 0 };
    public static final int[] COLOR_HL_VIOL_BG = { 230, 0, 0 };

    public static final int[] COLOR_HL_CLAIM_FG = { 0, 255, 0 };
    public static final int[] COLOR_HL_CLAIM_BG = { 0, 230, 0 };

    public static final int[] COLOR_HL_ASSIGN_FG = { 255, 0, 255 };
    public static final int[] COLOR_HL_ASSIGN_BG = { 230, 0, 230 };

    public static final int[] COLOR_HL_WORK_FG = { 0, 0, 255 };
    public static final int[] COLOR_HL_WORK_BG = { 0, 0, 230 };

    public static final int[] COLOR_DEF_SEQFLOW = { 0, 0, 0 };

    private SCVMValidationConstants() {

    }
}
