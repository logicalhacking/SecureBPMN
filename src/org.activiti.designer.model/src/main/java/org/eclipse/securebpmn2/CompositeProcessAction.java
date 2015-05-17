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
 * A representation of the model object '<em><b>Composite Process Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.CompositeProcessAction#getProcessActions <em>Process Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.securebpmn2.Securebpmn2Package#getCompositeProcessAction()
 * @model
 * @generated
 */
public interface CompositeProcessAction extends ProcessAction {
	/**
	 * Returns the value of the '<em><b>Process Actions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.ProcessAction}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.ProcessAction#getCompositeProcessActions <em>Composite Process Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Actions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process Actions</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getCompositeProcessAction_ProcessActions()
	 * @see org.eclipse.securebpmn2.ProcessAction#getCompositeProcessActions
	 * @model opposite="compositeProcessActions"
	 * @generated
	 */
	List<ProcessAction> getProcessActions();

} // CompositeProcessAction
