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
 * A representation of the model object '<em><b>Binding Of Duty</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.BindingOfDuty#getMaxUsers <em>Max Users</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.BindingOfDuty#getSameUserActionCount <em>Same User Action Count</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.securebpmn2.Securebpmn2Package#getBindingOfDuty()
 * @model
 * @generated
 */
public interface BindingOfDuty extends AuthorizationConstraint {
	/**
	 * Returns the value of the '<em><b>Max Users</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Users</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Users</em>' attribute.
	 * @see #setMaxUsers(Integer)
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getBindingOfDuty_MaxUsers()
	 * @model
	 * @generated
	 */
	Integer getMaxUsers();

	/**
	 * Sets the value of the '{@link org.eclipse.securebpmn2.BindingOfDuty#getMaxUsers <em>Max Users</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Users</em>' attribute.
	 * @see #getMaxUsers()
	 * @generated
	 */
	void setMaxUsers(Integer value);

	/**
	 * Returns the value of the '<em><b>Same User Action Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Same User Action Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Same User Action Count</em>' attribute.
	 * @see #setSameUserActionCount(Integer)
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getBindingOfDuty_SameUserActionCount()
	 * @model
	 * @generated
	 */
	Integer getSameUserActionCount();

	/**
	 * Sets the value of the '{@link org.eclipse.securebpmn2.BindingOfDuty#getSameUserActionCount <em>Same User Action Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Same User Action Count</em>' attribute.
	 * @see #getSameUserActionCount()
	 * @generated
	 */
	void setSameUserActionCount(Integer value);

} // BindingOfDuty
