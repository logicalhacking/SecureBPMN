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

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.designer.eclipse.preferences.PreferencesUtil;
import org.activiti.designer.util.preferences.Preferences;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.UserTask;

/**
 * Writes an ASLan system file to the provided Writer.
 *
 */
public class AslanFileBuilder {

    public static final int NO_ERRORS = 0;
    public static final int NO_GOALS = 1;

    private Writer writer;

    private List<String> signatures;
    private Map<String, List<String>> types;
    private List<String> inits;
    private List<String> hornClauses;
    private List<String> rules;
    private List<String> goals;
    private Map<String, Integer> goalCounter;

    private int goalNatVars = 0;

    private final String lineSeparator = System.getProperty("line.separator");;

    public AslanFileBuilder(Writer writer) {
        this.writer = writer;
        signatures = new ArrayList<String>();
        types = new HashMap<String, List<String>>();
        inits = new ArrayList<String>();
        hornClauses = new ArrayList<String>();
        rules = new ArrayList<String>();
        goals = new ArrayList<String>();
        goalCounter = new HashMap<String, Integer>();
    }

    /**
     * Writes the contents to the given Writer.
     *
     * @throws IOException
     *             on IO error of the Writer
     */
    public int writeOutput() throws IOException {
        writer.write("section signature:" + lineSeparator + lineSeparator);
        for (String signature : signatures) {
            writer.write("\t" + signature + lineSeparator);
        }

        writer.write(lineSeparator + "section types:" + lineSeparator
                     + lineSeparator);

        for (String type : types.keySet()) {
            writer.write("\t");
            boolean first = true;
            for (String var : types.get(type)) {
                if (first)
                    first = false;
                else
                    writer.write(",");

                writer.write(var);
            }
            writer.write(": " + type + lineSeparator);
        }

        writer.write(lineSeparator + "section inits:" + lineSeparator
                     + lineSeparator + "\tinitial_state init_1 :=" + lineSeparator);
        boolean firstInitFact = true;
        for (String init : inits) {
            if (firstInitFact) {
                firstInitFact = false;
            } else {
                writer.write(".");
            }
            writer.write(lineSeparator + "\t\t" + init);
        }

        writer.write(lineSeparator + lineSeparator + "section hornClauses:"
                     + lineSeparator + lineSeparator);
        for (String hornClause : hornClauses) {
            writer.write("\t" + hornClause + lineSeparator);
        }

        writer.write(lineSeparator + "section rules:" + lineSeparator
                     + lineSeparator);
        for (String rule : rules) {
            writer.write("\t" + rule + lineSeparator);
        }

        writer.write(lineSeparator + "section goals:" + lineSeparator
                     + lineSeparator);
        for (String goal : goals) {
            writer.write("\t" + goal + lineSeparator);
        }

        if (goals.size() == 0) {
            return NO_GOALS;
        } else {
            return NO_ERRORS;
        }
    }

    /**
     * Adds a line to the SignatureSection
     *
     * @param signature
     *            signature line
     */
    public void addSignature(String signature) {
        signatures.add(signature);
    }

    /**
     * Adds type definitions to the TypesSection, duplicates won't be added
     *
     * @param type
     *            variable/constant type
     * @param name
     *            name of the variable/constant
     */
    public void addType(String type, String name) {
        List<String> list;

        if (types.containsKey(type)) {
            list = types.get(type);

            for (String var : list) {
                if (var.equals(name))
                    return;
            }

        } else {
            list = new ArrayList<String>();
        }

        list.add(name);
        types.put(type, list);
    }

    /**
     * Adds a constant in the TypesSection for a given Task. The subtype is
     * automatically detected.
     *
     * @param task
     *            task the constant should be generated for
     */
    public void addTaskType(Task task) {

        boolean isHumanTask = false;

        if (task instanceof UserTask
                || PreferencesUtil
                .getBooleanPreference(Preferences.ALL_TASKS_AS_HUMANTASKS))
            isHumanTask = true;

        if (isHumanTask) {
            addType("humanTaskName", task.getId());
        } else {
            addType("automatedTaskName", task.getId());
        }
    }

    /**
     * Adds a variable in the TypesSection with the type "nat" and returns its
     * name. Using this function ensures that the "nat" variables are globally
     * unique.
     *
     * @return The name of the newly created variable.
     */
    public String addNatVar() {
        String newNatVar = "N" + goalNatVars;
        addType("nat", newNatVar);
        goalNatVars++;

        return newNatVar;
    }

    /**
     * Adds a fact to the initial state.
     *
     * @param initFact
     *            fact that gets added
     */
    public void addInit(String initFact) {
        inits.add(initFact);
    }

    /**
     * Adds a line to the HornClausesSection.
     *
     * @param hornClause
     *            line to be added
     */
    public void addHornClause(String hornClause) {
        hornClauses.add(hornClause);
    }

    /**
     * Adds a line to the RulesSection.
     *
     * @param ruleDecl
     *            line to be added
     */
    public void addRule(String ruleDecl) {
        rules.add(ruleDecl);
    }

    /**
     * Adds a line to the GoalsSection
     *
     * @param goalDecl
     *            line to be added
     */
    public void addGoalLine(String goalDecl) {
        goals.add(goalDecl);
    }

    public void addGoal(String name, String param) {

        if (goalCounter.get(name) == null)
            goalCounter.put(name, 1);

        goals.add("attack_state " + name + goalCounter.get(name) + param);
        goalCounter.put(name, goalCounter.get(name) + 1);
    }

}
