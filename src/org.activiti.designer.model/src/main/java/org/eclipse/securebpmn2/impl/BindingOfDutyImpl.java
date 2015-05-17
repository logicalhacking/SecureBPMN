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

import org.eclipse.securebpmn2.BindingOfDuty;
import org.eclipse.securebpmn2.Securebpmn2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binding Of Duty</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.impl.BindingOfDutyImpl#getMaxUsers <em>Max Users</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.BindingOfDutyImpl#getSameUserActionCount <em>Same User Action Count</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BindingOfDutyImpl extends AuthorizationConstraintImpl implements
		BindingOfDuty {
	/**
	 * The default value of the '{@link #getMaxUsers() <em>Max Users</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxUsers()
	 * @generated
	 * @ordered
	 */
	protected static final Integer MAX_USERS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxUsers() <em>Max Users</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxUsers()
	 * @generated
	 * @ordered
	 */
	protected Integer maxUsers = MAX_USERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getSameUserActionCount() <em>Same User Action Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSameUserActionCount()
	 * @generated
	 * @ordered
	 */
	protected static final Integer SAME_USER_ACTION_COUNT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSameUserActionCount() <em>Same User Action Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSameUserActionCount()
	 * @generated
	 * @ordered
	 */
	protected Integer sameUserActionCount = SAME_USER_ACTION_COUNT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BindingOfDutyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Securebpmn2Package.Literals.BINDING_OF_DUTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getMaxUsers() {
		return maxUsers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxUsers(Integer newMaxUsers) {
		Integer oldMaxUsers = maxUsers;
		maxUsers = newMaxUsers;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Securebpmn2Package.BINDING_OF_DUTY__MAX_USERS, oldMaxUsers,
					maxUsers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getSameUserActionCount() {
		return sameUserActionCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSameUserActionCount(Integer newSameUserActionCount) {
		Integer oldSameUserActionCount = sameUserActionCount;
		sameUserActionCount = newSameUserActionCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Securebpmn2Package.BINDING_OF_DUTY__SAME_USER_ACTION_COUNT,
					oldSameUserActionCount, sameUserActionCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Securebpmn2Package.BINDING_OF_DUTY__MAX_USERS:
			return getMaxUsers();
		case Securebpmn2Package.BINDING_OF_DUTY__SAME_USER_ACTION_COUNT:
			return getSameUserActionCount();
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
		case Securebpmn2Package.BINDING_OF_DUTY__MAX_USERS:
			setMaxUsers((Integer) newValue);
			return;
		case Securebpmn2Package.BINDING_OF_DUTY__SAME_USER_ACTION_COUNT:
			setSameUserActionCount((Integer) newValue);
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
		case Securebpmn2Package.BINDING_OF_DUTY__MAX_USERS:
			setMaxUsers(MAX_USERS_EDEFAULT);
			return;
		case Securebpmn2Package.BINDING_OF_DUTY__SAME_USER_ACTION_COUNT:
			setSameUserActionCount(SAME_USER_ACTION_COUNT_EDEFAULT);
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
		case Securebpmn2Package.BINDING_OF_DUTY__MAX_USERS:
			return MAX_USERS_EDEFAULT == null ? maxUsers != null
					: !MAX_USERS_EDEFAULT.equals(maxUsers);
		case Securebpmn2Package.BINDING_OF_DUTY__SAME_USER_ACTION_COUNT:
			return SAME_USER_ACTION_COUNT_EDEFAULT == null ? sameUserActionCount != null
					: !SAME_USER_ACTION_COUNT_EDEFAULT
							.equals(sameUserActionCount);
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
		result.append(" (maxUsers: ");
		result.append(maxUsers);
		result.append(", sameUserActionCount: ");
		result.append(sameUserActionCount);
		result.append(')');
		return result.toString();
	}

} //BindingOfDutyImpl
