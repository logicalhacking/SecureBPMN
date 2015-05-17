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
import org.eclipse.bpmn2.ComplexDataType;
import org.eclipse.bpmn2.CustomProperty;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.CustomPropertyImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.CustomPropertyImpl#getSimpleValue <em>Simple Value</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.CustomPropertyImpl#getComplexValue <em>Complex Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomPropertyImpl extends BaseElementImpl implements
		CustomProperty {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSimpleValue() <em>Simple Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSimpleValue()
	 * @generated
	 * @ordered
	 */
	protected static final String SIMPLE_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSimpleValue() <em>Simple Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSimpleValue()
	 * @generated
	 * @ordered
	 */
	protected String simpleValue = SIMPLE_VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getComplexValue() <em>Complex Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComplexValue()
	 * @generated
	 * @ordered
	 */
	protected ComplexDataType complexValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Bpmn2Package.Literals.CUSTOM_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.CUSTOM_PROPERTY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSimpleValue() {
		return simpleValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSimpleValue(String newSimpleValue) {
		String oldSimpleValue = simpleValue;
		simpleValue = newSimpleValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.CUSTOM_PROPERTY__SIMPLE_VALUE, oldSimpleValue,
					simpleValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComplexDataType getComplexValue() {
		return complexValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComplexValue(
			ComplexDataType newComplexValue, NotificationChain msgs) {
		ComplexDataType oldComplexValue = complexValue;
		complexValue = newComplexValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					Bpmn2Package.CUSTOM_PROPERTY__COMPLEX_VALUE,
					oldComplexValue, newComplexValue);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComplexValue(ComplexDataType newComplexValue) {
		if (newComplexValue != complexValue) {
			NotificationChain msgs = null;
			if (complexValue != null)
				msgs = ((InternalEObject) complexValue).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- Bpmn2Package.CUSTOM_PROPERTY__COMPLEX_VALUE,
						null, msgs);
			if (newComplexValue != null)
				msgs = ((InternalEObject) newComplexValue).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- Bpmn2Package.CUSTOM_PROPERTY__COMPLEX_VALUE,
						null, msgs);
			msgs = basicSetComplexValue(newComplexValue, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.CUSTOM_PROPERTY__COMPLEX_VALUE,
					newComplexValue, newComplexValue));
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
		case Bpmn2Package.CUSTOM_PROPERTY__COMPLEX_VALUE:
			return basicSetComplexValue(null, msgs);
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
		case Bpmn2Package.CUSTOM_PROPERTY__NAME:
			return getName();
		case Bpmn2Package.CUSTOM_PROPERTY__SIMPLE_VALUE:
			return getSimpleValue();
		case Bpmn2Package.CUSTOM_PROPERTY__COMPLEX_VALUE:
			return getComplexValue();
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
		case Bpmn2Package.CUSTOM_PROPERTY__NAME:
			setName((String) newValue);
			return;
		case Bpmn2Package.CUSTOM_PROPERTY__SIMPLE_VALUE:
			setSimpleValue((String) newValue);
			return;
		case Bpmn2Package.CUSTOM_PROPERTY__COMPLEX_VALUE:
			setComplexValue((ComplexDataType) newValue);
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
		case Bpmn2Package.CUSTOM_PROPERTY__NAME:
			setName(NAME_EDEFAULT);
			return;
		case Bpmn2Package.CUSTOM_PROPERTY__SIMPLE_VALUE:
			setSimpleValue(SIMPLE_VALUE_EDEFAULT);
			return;
		case Bpmn2Package.CUSTOM_PROPERTY__COMPLEX_VALUE:
			setComplexValue((ComplexDataType) null);
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
		case Bpmn2Package.CUSTOM_PROPERTY__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT
					.equals(name);
		case Bpmn2Package.CUSTOM_PROPERTY__SIMPLE_VALUE:
			return SIMPLE_VALUE_EDEFAULT == null ? simpleValue != null
					: !SIMPLE_VALUE_EDEFAULT.equals(simpleValue);
		case Bpmn2Package.CUSTOM_PROPERTY__COMPLEX_VALUE:
			return complexValue != null;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", simpleValue: ");
		result.append(simpleValue);
		result.append(')');
		return result.toString();
	}

} //CustomPropertyImpl
