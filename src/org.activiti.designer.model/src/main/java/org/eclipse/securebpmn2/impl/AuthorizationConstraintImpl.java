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
package org.eclipse.securebpmn2.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.securebpmn2.AuthorizationConstraint;
import org.eclipse.securebpmn2.Permission;
import org.eclipse.securebpmn2.Securebpmn2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Authorization Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.impl.AuthorizationConstraintImpl#getConstraintName <em>Constraint Name</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.AuthorizationConstraintImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.AuthorizationConstraintImpl#getPermissions <em>Permissions</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.AuthorizationConstraintImpl#isDynamicEnforcement <em>Dynamic Enforcement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AuthorizationConstraintImpl extends SecurityFlowNodeImpl implements
		AuthorizationConstraint {
	/**
	 * The default value of the '{@link #getConstraintName() <em>Constraint Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintName()
	 * @generated
	 * @ordered
	 */
	protected static final String CONSTRAINT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConstraintName() <em>Constraint Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintName()
	 * @generated
	 * @ordered
	 */
	protected String constraintName = CONSTRAINT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected String expression = EXPRESSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPermissions() <em>Permissions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPermissions()
	 * @generated
	 * @ordered
	 */
	protected EList<Permission> permissions;

	/**
	 * The default value of the '{@link #isDynamicEnforcement() <em>Dynamic Enforcement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDynamicEnforcement()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DYNAMIC_ENFORCEMENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDynamicEnforcement() <em>Dynamic Enforcement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDynamicEnforcement()
	 * @generated
	 * @ordered
	 */
	protected boolean dynamicEnforcement = DYNAMIC_ENFORCEMENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AuthorizationConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Securebpmn2Package.Literals.AUTHORIZATION_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConstraintName() {
		return constraintName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraintName(String newConstraintName) {
		String oldConstraintName = constraintName;
		constraintName = newConstraintName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
					this,
					Notification.SET,
					Securebpmn2Package.AUTHORIZATION_CONSTRAINT__CONSTRAINT_NAME,
					oldConstraintName, constraintName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(String newExpression) {
		String oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Securebpmn2Package.AUTHORIZATION_CONSTRAINT__EXPRESSION,
					oldExpression, expression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Permission> getPermissions() {
		if (permissions == null) {
			permissions = new EObjectWithInverseResolvingEList.ManyInverse<Permission>(
					Permission.class, this,
					Securebpmn2Package.AUTHORIZATION_CONSTRAINT__PERMISSIONS,
					Securebpmn2Package.PERMISSION__AUTHORIZATION_CONSTRAINTS);
		}
		return permissions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDynamicEnforcement() {
		return dynamicEnforcement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDynamicEnforcement(boolean newDynamicEnforcement) {
		boolean oldDynamicEnforcement = dynamicEnforcement;
		dynamicEnforcement = newDynamicEnforcement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
					this,
					Notification.SET,
					Securebpmn2Package.AUTHORIZATION_CONSTRAINT__DYNAMIC_ENFORCEMENT,
					oldDynamicEnforcement, dynamicEnforcement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__PERMISSIONS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getPermissions())
					.basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__PERMISSIONS:
			return ((InternalEList<?>) getPermissions()).basicRemove(otherEnd,
					msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__CONSTRAINT_NAME:
			return getConstraintName();
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__EXPRESSION:
			return getExpression();
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__PERMISSIONS:
			return getPermissions();
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__DYNAMIC_ENFORCEMENT:
			return isDynamicEnforcement();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__CONSTRAINT_NAME:
			setConstraintName((String) newValue);
			return;
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__EXPRESSION:
			setExpression((String) newValue);
			return;
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__PERMISSIONS:
			getPermissions().clear();
			getPermissions()
					.addAll((Collection<? extends Permission>) newValue);
			return;
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__DYNAMIC_ENFORCEMENT:
			setDynamicEnforcement((Boolean) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__CONSTRAINT_NAME:
			setConstraintName(CONSTRAINT_NAME_EDEFAULT);
			return;
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__EXPRESSION:
			setExpression(EXPRESSION_EDEFAULT);
			return;
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__PERMISSIONS:
			getPermissions().clear();
			return;
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__DYNAMIC_ENFORCEMENT:
			setDynamicEnforcement(DYNAMIC_ENFORCEMENT_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__CONSTRAINT_NAME:
			return CONSTRAINT_NAME_EDEFAULT == null ? constraintName != null
					: !CONSTRAINT_NAME_EDEFAULT.equals(constraintName);
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__EXPRESSION:
			return EXPRESSION_EDEFAULT == null ? expression != null
					: !EXPRESSION_EDEFAULT.equals(expression);
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__PERMISSIONS:
			return permissions != null && !permissions.isEmpty();
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT__DYNAMIC_ENFORCEMENT:
			return dynamicEnforcement != DYNAMIC_ENFORCEMENT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (constraintName: ");
		result.append(constraintName);
		result.append(", expression: ");
		result.append(expression);
		result.append(", dynamicEnforcement: ");
		result.append(dynamicEnforcement);
		result.append(')');
		return result.toString();
	}

} //AuthorizationConstraintImpl
