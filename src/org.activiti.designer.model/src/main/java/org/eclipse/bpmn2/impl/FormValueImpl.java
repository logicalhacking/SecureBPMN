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
package org.eclipse.bpmn2.impl;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.FormValue;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Form Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.FormValueImpl#getValueId <em>Value Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.FormValueImpl#getValueName <em>Value Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormValueImpl extends BaseElementImpl implements FormValue {
	/**
	 * The default value of the '{@link #getValueId() <em>Value Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueId()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValueId() <em>Value Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueId()
	 * @generated
	 * @ordered
	 */
	protected String valueId = VALUE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getValueName() <em>Value Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueName()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValueName() <em>Value Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueName()
	 * @generated
	 * @ordered
	 */
	protected String valueName = VALUE_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Bpmn2Package.Literals.FORM_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValueId() {
		return valueId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueId(String newValueId) {
		String oldValueId = valueId;
		valueId = newValueId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.FORM_VALUE__VALUE_ID, oldValueId, valueId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValueName() {
		return valueName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueName(String newValueName) {
		String oldValueName = valueName;
		valueName = newValueName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.FORM_VALUE__VALUE_NAME, oldValueName,
					valueName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Bpmn2Package.FORM_VALUE__VALUE_ID:
			return getValueId();
		case Bpmn2Package.FORM_VALUE__VALUE_NAME:
			return getValueName();
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
		case Bpmn2Package.FORM_VALUE__VALUE_ID:
			setValueId((String) newValue);
			return;
		case Bpmn2Package.FORM_VALUE__VALUE_NAME:
			setValueName((String) newValue);
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
		case Bpmn2Package.FORM_VALUE__VALUE_ID:
			setValueId(VALUE_ID_EDEFAULT);
			return;
		case Bpmn2Package.FORM_VALUE__VALUE_NAME:
			setValueName(VALUE_NAME_EDEFAULT);
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
		case Bpmn2Package.FORM_VALUE__VALUE_ID:
			return VALUE_ID_EDEFAULT == null ? valueId != null
					: !VALUE_ID_EDEFAULT.equals(valueId);
		case Bpmn2Package.FORM_VALUE__VALUE_NAME:
			return VALUE_NAME_EDEFAULT == null ? valueName != null
					: !VALUE_NAME_EDEFAULT.equals(valueName);
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
		result.append(" (valueId: ");
		result.append(valueId);
		result.append(", valueName: ");
		result.append(valueName);
		result.append(')');
		return result.toString();
	}

} //FormValueImpl
