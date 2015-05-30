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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.securebpmn2.Securebpmn2Package;
import org.eclipse.securebpmn2.SeparationOfDuty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Separation Of Duty</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.impl.SeparationOfDutyImpl#getMinimumUsers <em>Minimum Users</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.SeparationOfDutyImpl#getMaxUserActionsPermitted <em>Max User Actions Permitted</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SeparationOfDutyImpl extends AuthorizationConstraintImpl implements
		SeparationOfDuty {
	/**
	 * The default value of the '{@link #getMinimumUsers() <em>Minimum Users</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumUsers()
	 * @generated
	 * @ordered
	 */
	protected static final Integer MINIMUM_USERS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMinimumUsers() <em>Minimum Users</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumUsers()
	 * @generated
	 * @ordered
	 */
	protected Integer minimumUsers = MINIMUM_USERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxUserActionsPermitted() <em>Max User Actions Permitted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxUserActionsPermitted()
	 * @generated
	 * @ordered
	 */
	protected static final Integer MAX_USER_ACTIONS_PERMITTED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxUserActionsPermitted() <em>Max User Actions Permitted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxUserActionsPermitted()
	 * @generated
	 * @ordered
	 */
	protected Integer maxUserActionsPermitted = MAX_USER_ACTIONS_PERMITTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SeparationOfDutyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Securebpmn2Package.Literals.SEPARATION_OF_DUTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getMinimumUsers() {
		return minimumUsers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinimumUsers(Integer newMinimumUsers) {
		Integer oldMinimumUsers = minimumUsers;
		minimumUsers = newMinimumUsers;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Securebpmn2Package.SEPARATION_OF_DUTY__MINIMUM_USERS,
					oldMinimumUsers, minimumUsers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getMaxUserActionsPermitted() {
		return maxUserActionsPermitted;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxUserActionsPermitted(Integer newMaxUserActionsPermitted) {
		Integer oldMaxUserActionsPermitted = maxUserActionsPermitted;
		maxUserActionsPermitted = newMaxUserActionsPermitted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
					this,
					Notification.SET,
					Securebpmn2Package.SEPARATION_OF_DUTY__MAX_USER_ACTIONS_PERMITTED,
					oldMaxUserActionsPermitted, maxUserActionsPermitted));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Securebpmn2Package.SEPARATION_OF_DUTY__MINIMUM_USERS:
			return getMinimumUsers();
		case Securebpmn2Package.SEPARATION_OF_DUTY__MAX_USER_ACTIONS_PERMITTED:
			return getMaxUserActionsPermitted();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case Securebpmn2Package.SEPARATION_OF_DUTY__MINIMUM_USERS:
			setMinimumUsers((Integer) newValue);
			return;
		case Securebpmn2Package.SEPARATION_OF_DUTY__MAX_USER_ACTIONS_PERMITTED:
			setMaxUserActionsPermitted((Integer) newValue);
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
		case Securebpmn2Package.SEPARATION_OF_DUTY__MINIMUM_USERS:
			setMinimumUsers(MINIMUM_USERS_EDEFAULT);
			return;
		case Securebpmn2Package.SEPARATION_OF_DUTY__MAX_USER_ACTIONS_PERMITTED:
			setMaxUserActionsPermitted(MAX_USER_ACTIONS_PERMITTED_EDEFAULT);
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
		case Securebpmn2Package.SEPARATION_OF_DUTY__MINIMUM_USERS:
			return MINIMUM_USERS_EDEFAULT == null ? minimumUsers != null
					: !MINIMUM_USERS_EDEFAULT.equals(minimumUsers);
		case Securebpmn2Package.SEPARATION_OF_DUTY__MAX_USER_ACTIONS_PERMITTED:
			return MAX_USER_ACTIONS_PERMITTED_EDEFAULT == null ? maxUserActionsPermitted != null
					: !MAX_USER_ACTIONS_PERMITTED_EDEFAULT
							.equals(maxUserActionsPermitted);
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
		result.append(" (minimumUsers: ");
		result.append(minimumUsers);
		result.append(", maxUserActionsPermitted: ");
		result.append(maxUserActionsPermitted);
		result.append(')');
		return result.toString();
	}

} //SeparationOfDutyImpl
