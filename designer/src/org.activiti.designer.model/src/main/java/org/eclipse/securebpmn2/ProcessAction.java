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
package org.eclipse.securebpmn2;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.ProcessAction#getCompositeProcessActions <em>Composite Process Actions</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.ProcessAction#getProcess <em>Process</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.securebpmn2.Securebpmn2Package#getProcessAction()
 * @model
 * @generated
 */
public interface ProcessAction extends Action {
	/**
	 * Returns the value of the '<em><b>Composite Process Actions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.CompositeProcessAction}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.CompositeProcessAction#getProcessActions <em>Process Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Composite Process Actions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composite Process Actions</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getProcessAction_CompositeProcessActions()
	 * @see org.eclipse.securebpmn2.CompositeProcessAction#getProcessActions
	 * @model opposite="processActions"
	 * @generated
	 */
	List<CompositeProcessAction> getCompositeProcessActions();

	/**
	 * Returns the value of the '<em><b>Process</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.Process#getProcessActions <em>Process Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process</em>' container reference.
	 * @see #setProcess(org.eclipse.bpmn2.Process)
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getProcessAction_Process()
	 * @see org.eclipse.bpmn2.Process#getProcessActions
	 * @model opposite="processActions" required="true" transient="false"
	 * @generated
	 */
	org.eclipse.bpmn2.Process getProcess();

	/**
	 * Sets the value of the '{@link org.eclipse.securebpmn2.ProcessAction#getProcess <em>Process</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process</em>' container reference.
	 * @see #getProcess()
	 * @generated
	 */
	void setProcess(org.eclipse.bpmn2.Process value);

} // ProcessAction
