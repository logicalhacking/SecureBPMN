/**
 * <copyright>
 * 
 * Copyright (c) 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Reiner Hille-Doering (SAP AG) - initial API and implementation and/or initial documentation
 * 
 * </copyright>
 */
package org.eclipse.bpmn2;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Start Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.StartEvent#isIsInterrupting <em>Is Interrupting</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.StartEvent#getFormKey <em>Form Key</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.StartEvent#getFormProperties <em>Form Properties</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.StartEvent#getInitiator <em>Initiator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getStartEvent()
 * @model extendedMetaData="name='tStartEvent' kind='elementOnly'"
 * @generated
 */
public interface StartEvent extends CatchEvent {
	/**
	 * Returns the value of the '<em><b>Is Interrupting</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Interrupting</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Interrupting</em>' attribute.
	 * @see #setIsInterrupting(boolean)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getStartEvent_IsInterrupting()
	 * @model default="true" required="true" ordered="false"
	 *        extendedMetaData="kind='attribute' name='isInterrupting'"
	 * @generated
	 */
	boolean isIsInterrupting();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.StartEvent#isIsInterrupting <em>Is Interrupting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Interrupting</em>' attribute.
	 * @see #isIsInterrupting()
	 * @generated
	 */
	void setIsInterrupting(boolean value);

	/**
	 * Returns the value of the '<em><b>Form Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Form Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Form Key</em>' attribute.
	 * @see #setFormKey(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getStartEvent_FormKey()
	 * @model
	 * @generated
	 */
	String getFormKey();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.StartEvent#getFormKey <em>Form Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Form Key</em>' attribute.
	 * @see #getFormKey()
	 * @generated
	 */
	void setFormKey(String value);

	/**
	 * Returns the value of the '<em><b>Form Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.FormProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Form Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Form Properties</em>' containment reference list.
	 * @see org.eclipse.bpmn2.Bpmn2Package#getStartEvent_FormProperties()
	 * @model containment="true"
	 * @generated
	 */
	List<FormProperty> getFormProperties();

	/**
	 * Returns the value of the '<em><b>Initiator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initiator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initiator</em>' attribute.
	 * @see #setInitiator(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getStartEvent_Initiator()
	 * @model
	 * @generated
	 */
	String getInitiator();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.StartEvent#getInitiator <em>Initiator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initiator</em>' attribute.
	 * @see #getInitiator()
	 * @generated
	 */
	void setInitiator(String value);

} // StartEvent
