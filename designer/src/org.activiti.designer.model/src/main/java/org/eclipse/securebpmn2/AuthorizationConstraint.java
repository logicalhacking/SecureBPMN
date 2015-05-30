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
 * A representation of the model object '<em><b>Authorization Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.AuthorizationConstraint#getConstraintName <em>Constraint Name</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.AuthorizationConstraint#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.AuthorizationConstraint#getPermissions <em>Permissions</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.AuthorizationConstraint#isDynamicEnforcement <em>Dynamic Enforcement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.securebpmn2.Securebpmn2Package#getAuthorizationConstraint()
 * @model
 * @generated
 */
public interface AuthorizationConstraint extends SecurityFlowNode {
	/**
	 * Returns the value of the '<em><b>Constraint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint Name</em>' attribute.
	 * @see #setConstraintName(String)
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getAuthorizationConstraint_ConstraintName()
	 * @model
	 * @generated
	 */
	String getConstraintName();

	/**
	 * Sets the value of the '{@link org.eclipse.securebpmn2.AuthorizationConstraint#getConstraintName <em>Constraint Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint Name</em>' attribute.
	 * @see #getConstraintName()
	 * @generated
	 */
	void setConstraintName(String value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' attribute.
	 * @see #setExpression(String)
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getAuthorizationConstraint_Expression()
	 * @model
	 * @generated
	 */
	String getExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.securebpmn2.AuthorizationConstraint#getExpression <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' attribute.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(String value);

	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.Permission}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.Permission#getAuthorizationConstraints <em>Authorization Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Permissions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getAuthorizationConstraint_Permissions()
	 * @see org.eclipse.securebpmn2.Permission#getAuthorizationConstraints
	 * @model opposite="authorizationConstraints" required="true"
	 * @generated
	 */
	List<Permission> getPermissions();

	/**
	 * Returns the value of the '<em><b>Dynamic Enforcement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Enforcement</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Enforcement</em>' attribute.
	 * @see #setDynamicEnforcement(boolean)
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getAuthorizationConstraint_DynamicEnforcement()
	 * @model
	 * @generated
	 */
	boolean isDynamicEnforcement();

	/**
	 * Sets the value of the '{@link org.eclipse.securebpmn2.AuthorizationConstraint#isDynamicEnforcement <em>Dynamic Enforcement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic Enforcement</em>' attribute.
	 * @see #isDynamicEnforcement()
	 * @generated
	 */
	void setDynamicEnforcement(boolean value);

} // AuthorizationConstraint
