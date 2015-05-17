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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Separation Of Duty</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.SeparationOfDuty#getMinimumUsers <em>Minimum Users</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.SeparationOfDuty#getMaxUserActionsPermitted <em>Max User Actions Permitted</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.securebpmn2.Securebpmn2Package#getSeparationOfDuty()
 * @model
 * @generated
 */
public interface SeparationOfDuty extends AuthorizationConstraint {
	/**
	 * Returns the value of the '<em><b>Minimum Users</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Minimum Users</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Minimum Users</em>' attribute.
	 * @see #setMinimumUsers(Integer)
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getSeparationOfDuty_MinimumUsers()
	 * @model
	 * @generated
	 */
	Integer getMinimumUsers();

	/**
	 * Sets the value of the '{@link org.eclipse.securebpmn2.SeparationOfDuty#getMinimumUsers <em>Minimum Users</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minimum Users</em>' attribute.
	 * @see #getMinimumUsers()
	 * @generated
	 */
	void setMinimumUsers(Integer value);

	/**
	 * Returns the value of the '<em><b>Max User Actions Permitted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max User Actions Permitted</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max User Actions Permitted</em>' attribute.
	 * @see #setMaxUserActionsPermitted(Integer)
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getSeparationOfDuty_MaxUserActionsPermitted()
	 * @model
	 * @generated
	 */
	Integer getMaxUserActionsPermitted();

	/**
	 * Sets the value of the '{@link org.eclipse.securebpmn2.SeparationOfDuty#getMaxUserActionsPermitted <em>Max User Actions Permitted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max User Actions Permitted</em>' attribute.
	 * @see #getMaxUserActionsPermitted()
	 * @generated
	 */
	void setMaxUserActionsPermitted(Integer value);

} // SeparationOfDuty
