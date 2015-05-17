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

import org.eclipse.bpmn2.Activity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.ActivityAction#getCompositeActivityActions <em>Composite Activity Actions</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.ActivityAction#getActivity <em>Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.securebpmn2.Securebpmn2Package#getActivityAction()
 * @model
 * @generated
 */
public interface ActivityAction extends Action {
	/**
	 * Returns the value of the '<em><b>Composite Activity Actions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.CompositeActivityAction}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.CompositeActivityAction#getActivityActions <em>Activity Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Composite Activity Actions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composite Activity Actions</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getActivityAction_CompositeActivityActions()
	 * @see org.eclipse.securebpmn2.CompositeActivityAction#getActivityActions
	 * @model opposite="activityActions"
	 * @generated
	 */
	List<CompositeActivityAction> getCompositeActivityActions();

	/**
	 * Returns the value of the '<em><b>Activity</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.Activity#getActivityActions <em>Activity Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity</em>' reference.
	 * @see #setActivity(Activity)
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getActivityAction_Activity()
	 * @see org.eclipse.bpmn2.Activity#getActivityActions
	 * @model opposite="activityActions" required="true"
	 * @generated
	 */
	Activity getActivity();

	/**
	 * Sets the value of the '{@link org.eclipse.securebpmn2.ActivityAction#getActivity <em>Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity</em>' reference.
	 * @see #getActivity()
	 * @generated
	 */
	void setActivity(Activity value);

} // ActivityAction
