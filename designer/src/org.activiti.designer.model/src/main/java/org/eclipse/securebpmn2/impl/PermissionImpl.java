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

import org.eclipse.bpmn2.impl.BaseElementImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.securebpmn2.Action;
import org.eclipse.securebpmn2.AuthorizationConstraint;
import org.eclipse.securebpmn2.Permission;
import org.eclipse.securebpmn2.Role;
import org.eclipse.securebpmn2.Securebpmn2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Permission</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.impl.PermissionImpl#getRoles <em>Roles</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.PermissionImpl#getActions <em>Actions</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.PermissionImpl#getAuthorizationConstraints <em>Authorization Constraints</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.PermissionImpl#getPName <em>PName</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PermissionImpl extends BaseElementImpl implements Permission {
	/**
	 * The cached value of the '{@link #getRoles() <em>Roles</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> roles;

	/**
	 * The cached value of the '{@link #getActions() <em>Actions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActions()
	 * @generated
	 * @ordered
	 */
	protected EList<Action> actions;

	/**
	 * The cached value of the '{@link #getAuthorizationConstraints() <em>Authorization Constraints</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthorizationConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<AuthorizationConstraint> authorizationConstraints;

	/**
	 * The default value of the '{@link #getPName() <em>PName</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPName()
	 * @generated
	 * @ordered
	 */
	protected static final String PNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPName() <em>PName</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPName()
	 * @generated
	 * @ordered
	 */
	protected String pName = PNAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PermissionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Securebpmn2Package.Literals.PERMISSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Role> getRoles() {
		if (roles == null) {
			roles = new EObjectWithInverseResolvingEList.ManyInverse<Role>(
					Role.class, this, Securebpmn2Package.PERMISSION__ROLES,
					Securebpmn2Package.ROLE__PERMISSIONS);
		}
		return roles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Action> getActions() {
		if (actions == null) {
			actions = new EObjectWithInverseResolvingEList.ManyInverse<Action>(
					Action.class, this, Securebpmn2Package.PERMISSION__ACTIONS,
					Securebpmn2Package.ACTION__PERMISSIONS);
		}
		return actions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<AuthorizationConstraint> getAuthorizationConstraints() {
		if (authorizationConstraints == null) {
			authorizationConstraints = new EObjectWithInverseResolvingEList.ManyInverse<AuthorizationConstraint>(
					AuthorizationConstraint.class, this,
					Securebpmn2Package.PERMISSION__AUTHORIZATION_CONSTRAINTS,
					Securebpmn2Package.AUTHORIZATION_CONSTRAINT__PERMISSIONS);
		}
		return authorizationConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPName() {
		return pName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPName(String newPName) {
		String oldPName = pName;
		pName = newPName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Securebpmn2Package.PERMISSION__PNAME, oldPName, pName));
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
		case Securebpmn2Package.PERMISSION__ROLES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getRoles())
					.basicAdd(otherEnd, msgs);
		case Securebpmn2Package.PERMISSION__ACTIONS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getActions())
					.basicAdd(otherEnd, msgs);
		case Securebpmn2Package.PERMISSION__AUTHORIZATION_CONSTRAINTS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getAuthorizationConstraints())
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
		case Securebpmn2Package.PERMISSION__ROLES:
			return ((InternalEList<?>) getRoles()).basicRemove(otherEnd, msgs);
		case Securebpmn2Package.PERMISSION__ACTIONS:
			return ((InternalEList<?>) getActions())
					.basicRemove(otherEnd, msgs);
		case Securebpmn2Package.PERMISSION__AUTHORIZATION_CONSTRAINTS:
			return ((InternalEList<?>) getAuthorizationConstraints())
					.basicRemove(otherEnd, msgs);
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
		case Securebpmn2Package.PERMISSION__ROLES:
			return getRoles();
		case Securebpmn2Package.PERMISSION__ACTIONS:
			return getActions();
		case Securebpmn2Package.PERMISSION__AUTHORIZATION_CONSTRAINTS:
			return getAuthorizationConstraints();
		case Securebpmn2Package.PERMISSION__PNAME:
			return getPName();
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
		case Securebpmn2Package.PERMISSION__ROLES:
			getRoles().clear();
			getRoles().addAll((Collection<? extends Role>) newValue);
			return;
		case Securebpmn2Package.PERMISSION__ACTIONS:
			getActions().clear();
			getActions().addAll((Collection<? extends Action>) newValue);
			return;
		case Securebpmn2Package.PERMISSION__AUTHORIZATION_CONSTRAINTS:
			getAuthorizationConstraints().clear();
			getAuthorizationConstraints().addAll(
					(Collection<? extends AuthorizationConstraint>) newValue);
			return;
		case Securebpmn2Package.PERMISSION__PNAME:
			setPName((String) newValue);
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
		case Securebpmn2Package.PERMISSION__ROLES:
			getRoles().clear();
			return;
		case Securebpmn2Package.PERMISSION__ACTIONS:
			getActions().clear();
			return;
		case Securebpmn2Package.PERMISSION__AUTHORIZATION_CONSTRAINTS:
			getAuthorizationConstraints().clear();
			return;
		case Securebpmn2Package.PERMISSION__PNAME:
			setPName(PNAME_EDEFAULT);
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
		case Securebpmn2Package.PERMISSION__ROLES:
			return roles != null && !roles.isEmpty();
		case Securebpmn2Package.PERMISSION__ACTIONS:
			return actions != null && !actions.isEmpty();
		case Securebpmn2Package.PERMISSION__AUTHORIZATION_CONSTRAINTS:
			return authorizationConstraints != null
					&& !authorizationConstraints.isEmpty();
		case Securebpmn2Package.PERMISSION__PNAME:
			return PNAME_EDEFAULT == null ? pName != null : !PNAME_EDEFAULT
					.equals(pName);
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
		result.append(" (pName: ");
		result.append(pName);
		result.append(')');
		return result.toString();
	}

} //PermissionImpl
