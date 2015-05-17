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

package eu.aniketos.securebpmn.visualization;

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * A Class representing an element for visualization.
 * 
 * 
 */
public class VisualizationElement {

	private String id;
	private FlowElement bObject;
	private PictogramElement pElement;
	private ActionType action;

	/**
	 * Default constructor.
	 * 
	 * @param id
	 *            The ID of the element.
	 * @param action
	 *            The type of action the element is involved in. Used for
	 *            determining the type of highlighting.
	 */
	public VisualizationElement(String id, ActionType action) {
		this.id = id;
		this.action = action;
		this.bObject = null;
		this.pElement = null;
	}

	/**
	 * Default constructor.
	 * 
	 * @param id
	 *            The ID of the element.
	 * @param bObject
	 *            The BO of the element.
	 * @param pElement
	 *            The PE of the element.
	 * @param action
	 *            The type of action the element is involved in. Used for
	 *            determining the type of highlighting.
	 */
	public VisualizationElement(String id, FlowElement bObject,
			PictogramElement pElement, ActionType action) {
		this.id = id;
		this.bObject = bObject;
		this.pElement = pElement;
		this.action = action;
	}

	/**
	 * Retrieves the BusinessObject of the element.
	 * 
	 * @return The corresponding BO.
	 */
	public FlowElement getbObject() {
		return bObject;
	}

	/**
	 * Sets the BusinessObject of the element.
	 * 
	 * @param bObject
	 *            The BO to be set.
	 */
	public void setbObject(FlowElement bObject) {
		this.bObject = bObject;
	}

	/**
	 * Retrieves the PictogramElement of the element.
	 * 
	 * @return The corresponding PE.
	 */
	public PictogramElement getpElement() {
		return pElement;
	}

	/**
	 * Sets the PictogramElement of the element.
	 * 
	 * @param pElement
	 *            The PE to be set.
	 */
	public void setpElement(PictogramElement pElement) {
		this.pElement = pElement;
	}

	/**
	 * Retrieves the ID of the element.
	 * 
	 * @return The corresponding ID.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Retrieves the type of action the element is involved in.
	 * 
	 * @return The corresponding ActionType.
	 */
	public ActionType getAction() {
		return action;
	}

}
