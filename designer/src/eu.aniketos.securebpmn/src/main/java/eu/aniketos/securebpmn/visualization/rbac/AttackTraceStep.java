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

import eu.aniketos.securebpmn.visualization.VisualizationElement;

/**
 * Represents a step in the attack trace visualization.
 *
 *
 */
public class AttackTraceStep {

    private List<VisualizationElement> involvedElements;
    private String description;

    /**
     * Default constructor.
     */
    public AttackTraceStep() {
        this.involvedElements = new ArrayList<VisualizationElement>();
        this.description = "";
    }

    /**
     * Retrieves the description text of the step.
     *
     * @return The description text.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description text of the step.
     *
     * @param description
     *            The new description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the VisualizationElements involved in the step.
     *
     * @return The List of involved VisualizationElements.
     */
    public List<VisualizationElement> getInvolvedElements() {
        return involvedElements;
    }

    /**
     * Adds a new VisualizationElement to the involved elements in the step.
     *
     * @param element
     *            The new VisualizationElement to be added.
     */
    public void addInvolvedElement(VisualizationElement element) {
        this.involvedElements.add(element);
    }

}
