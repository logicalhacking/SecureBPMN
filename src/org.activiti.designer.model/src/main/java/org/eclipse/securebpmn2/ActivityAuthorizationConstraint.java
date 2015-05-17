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
 * A representation of the model object '<em><b>Activity Authorization Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.ActivityAuthorizationConstraint#getActivities <em>Activities</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.securebpmn2.Securebpmn2Package#getActivityAuthorizationConstraint()
 * @model
 * @generated
 */
public interface ActivityAuthorizationConstraint extends
		AuthorizationConstraint {
	/**
	 * Returns the value of the '<em><b>Activities</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.Activity}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.Activity#getActivityAuthorizationConstraints <em>Activity Authorization Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activities</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getActivityAuthorizationConstraint_Activities()
	 * @see org.eclipse.bpmn2.Activity#getActivityAuthorizationConstraints
	 * @model opposite="activityAuthorizationConstraints" required="true"
	 * @generated
	 */
	List<Activity> getActivities();

} // ActivityAuthorizationConstraint
