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

import org.eclipse.bpmn2.BaseElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Permission</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.Permission#getRoles <em>Roles</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.Permission#getActions <em>Actions</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.Permission#getAuthorizationConstraints <em>Authorization Constraints</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.Permission#getPName <em>PName</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.securebpmn2.Securebpmn2Package#getPermission()
 * @model
 * @generated
 */
public interface Permission extends BaseElement {
	/**
	 * Returns the value of the '<em><b>Roles</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.Role}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.Role#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roles</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getPermission_Roles()
	 * @see org.eclipse.securebpmn2.Role#getPermissions
	 * @model opposite="permissions" required="true"
	 * @generated
	 */
	List<Role> getRoles();

	/**
	 * Returns the value of the '<em><b>Actions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.Action}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.Action#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actions</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getPermission_Actions()
	 * @see org.eclipse.securebpmn2.Action#getPermissions
	 * @model opposite="permissions" required="true"
	 * @generated
	 */
	List<Action> getActions();

	/**
	 * Returns the value of the '<em><b>Authorization Constraints</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.AuthorizationConstraint}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.AuthorizationConstraint#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Authorization Constraints</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Authorization Constraints</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getPermission_AuthorizationConstraints()
	 * @see org.eclipse.securebpmn2.AuthorizationConstraint#getPermissions
	 * @model opposite="permissions"
	 * @generated
	 */
	List<AuthorizationConstraint> getAuthorizationConstraints();

	/**
	 * Returns the value of the '<em><b>PName</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>PName</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>PName</em>' attribute.
	 * @see #setPName(String)
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getPermission_PName()
	 * @model
	 * @generated
	 */
	String getPName();

	/**
	 * Sets the value of the '{@link org.eclipse.securebpmn2.Permission#getPName <em>PName</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PName</em>' attribute.
	 * @see #getPName()
	 * @generated
	 */
	void setPName(String value);

} // Permission
