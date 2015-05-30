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
 * A representation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.Role#getRoleName <em>Role Name</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.Role#getParentRoles <em>Parent Roles</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.Role#getSubjects <em>Subjects</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.Role#getChildRoles <em>Child Roles</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.Role#getPermissions <em>Permissions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.securebpmn2.Securebpmn2Package#getRole()
 * @model
 * @generated
 */
public interface Role extends SecurityFlowNode {
	/**
	 * Returns the value of the '<em><b>Role Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Name</em>' attribute.
	 * @see #setRoleName(String)
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getRole_RoleName()
	 * @model
	 * @generated
	 */
	String getRoleName();

	/**
	 * Sets the value of the '{@link org.eclipse.securebpmn2.Role#getRoleName <em>Role Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Name</em>' attribute.
	 * @see #getRoleName()
	 * @generated
	 */
	void setRoleName(String value);

	/**
	 * Returns the value of the '<em><b>Parent Roles</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.Role}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.Role#getChildRoles <em>Child Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Roles</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getRole_ParentRoles()
	 * @see org.eclipse.securebpmn2.Role#getChildRoles
	 * @model opposite="childRoles"
	 * @generated
	 */
	List<Role> getParentRoles();

	/**
	 * Returns the value of the '<em><b>Subjects</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.Subject}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.Subject#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subjects</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subjects</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getRole_Subjects()
	 * @see org.eclipse.securebpmn2.Subject#getRoles
	 * @model opposite="roles" transient="true"
	 * @generated
	 */
	List<Subject> getSubjects();

	/**
	 * Returns the value of the '<em><b>Child Roles</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.Role}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.Role#getParentRoles <em>Parent Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child Roles</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getRole_ChildRoles()
	 * @see org.eclipse.securebpmn2.Role#getParentRoles
	 * @model opposite="parentRoles"
	 * @generated
	 */
	List<Role> getChildRoles();

	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.Permission}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.Permission#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Permissions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getRole_Permissions()
	 * @see org.eclipse.securebpmn2.Permission#getRoles
	 * @model opposite="roles"
	 * @generated
	 */
	List<Permission> getPermissions();

} // Role
