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
 * A representation of the model object '<em><b>Composite Activity Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.CompositeActivityAction#getActivityActions <em>Activity Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.securebpmn2.Securebpmn2Package#getCompositeActivityAction()
 * @model
 * @generated
 */
public interface CompositeActivityAction extends ActivityAction {
	/**
	 * Returns the value of the '<em><b>Activity Actions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.ActivityAction}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.ActivityAction#getCompositeActivityActions <em>Composite Activity Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Actions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Actions</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getCompositeActivityAction_ActivityActions()
	 * @see org.eclipse.securebpmn2.ActivityAction#getCompositeActivityActions
	 * @model opposite="compositeActivityActions"
	 * @generated
	 */
	List<ActivityAction> getActivityActions();

} // CompositeActivityAction
