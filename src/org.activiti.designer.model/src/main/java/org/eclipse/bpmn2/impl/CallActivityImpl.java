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

import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.IOParameter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.CallActivityImpl#getCalledElementRef <em>Called Element Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.CallActivityImpl#getCalledElement <em>Called Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.CallActivityImpl#getInParameters <em>In Parameters</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.CallActivityImpl#getOutParameters <em>Out Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CallActivityImpl extends ActivityImpl implements CallActivity {
	/**
	 * The cached value of the '{@link #getCalledElementRef() <em>Called Element Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledElementRef()
	 * @generated
	 * @ordered
	 */
	protected CallableElement calledElementRef;

	/**
	 * The default value of the '{@link #getCalledElement() <em>Called Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledElement()
	 * @generated
	 * @ordered
	 */
	protected static final String CALLED_ELEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCalledElement() <em>Called Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledElement()
	 * @generated
	 * @ordered
	 */
	protected String calledElement = CALLED_ELEMENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInParameters() <em>In Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<IOParameter> inParameters;

	/**
	 * The cached value of the '{@link #getOutParameters() <em>Out Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<IOParameter> outParameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CallActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Bpmn2Package.Literals.CALL_ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallableElement getCalledElementRef() {
		if (calledElementRef != null && calledElementRef.eIsProxy()) {
			InternalEObject oldCalledElementRef = (InternalEObject) calledElementRef;
			calledElementRef = (CallableElement) eResolveProxy(oldCalledElementRef);
			if (calledElementRef != oldCalledElementRef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							Bpmn2Package.CALL_ACTIVITY__CALLED_ELEMENT_REF,
							oldCalledElementRef, calledElementRef));
			}
		}
		return calledElementRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallableElement basicGetCalledElementRef() {
		return calledElementRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCalledElementRef(CallableElement newCalledElementRef) {
		CallableElement oldCalledElementRef = calledElementRef;
		calledElementRef = newCalledElementRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.CALL_ACTIVITY__CALLED_ELEMENT_REF,
					oldCalledElementRef, calledElementRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCalledElement() {
		return calledElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCalledElement(String newCalledElement) {
		String oldCalledElement = calledElement;
		calledElement = newCalledElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.CALL_ACTIVITY__CALLED_ELEMENT,
					oldCalledElement, calledElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<IOParameter> getInParameters() {
		if (inParameters == null) {
			inParameters = new EObjectContainmentEList<IOParameter>(
					IOParameter.class, this,
					Bpmn2Package.CALL_ACTIVITY__IN_PARAMETERS);
		}
		return inParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<IOParameter> getOutParameters() {
		if (outParameters == null) {
			outParameters = new EObjectContainmentEList<IOParameter>(
					IOParameter.class, this,
					Bpmn2Package.CALL_ACTIVITY__OUT_PARAMETERS);
		}
		return outParameters;
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
		case Bpmn2Package.CALL_ACTIVITY__IN_PARAMETERS:
			return ((InternalEList<?>) getInParameters()).basicRemove(otherEnd,
					msgs);
		case Bpmn2Package.CALL_ACTIVITY__OUT_PARAMETERS:
			return ((InternalEList<?>) getOutParameters()).basicRemove(
					otherEnd, msgs);
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
		case Bpmn2Package.CALL_ACTIVITY__CALLED_ELEMENT_REF:
			if (resolve)
				return getCalledElementRef();
			return basicGetCalledElementRef();
		case Bpmn2Package.CALL_ACTIVITY__CALLED_ELEMENT:
			return getCalledElement();
		case Bpmn2Package.CALL_ACTIVITY__IN_PARAMETERS:
			return getInParameters();
		case Bpmn2Package.CALL_ACTIVITY__OUT_PARAMETERS:
			return getOutParameters();
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
		case Bpmn2Package.CALL_ACTIVITY__CALLED_ELEMENT_REF:
			setCalledElementRef((CallableElement) newValue);
			return;
		case Bpmn2Package.CALL_ACTIVITY__CALLED_ELEMENT:
			setCalledElement((String) newValue);
			return;
		case Bpmn2Package.CALL_ACTIVITY__IN_PARAMETERS:
			getInParameters().clear();
			getInParameters().addAll(
					(Collection<? extends IOParameter>) newValue);
			return;
		case Bpmn2Package.CALL_ACTIVITY__OUT_PARAMETERS:
			getOutParameters().clear();
			getOutParameters().addAll(
					(Collection<? extends IOParameter>) newValue);
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
		case Bpmn2Package.CALL_ACTIVITY__CALLED_ELEMENT_REF:
			setCalledElementRef((CallableElement) null);
			return;
		case Bpmn2Package.CALL_ACTIVITY__CALLED_ELEMENT:
			setCalledElement(CALLED_ELEMENT_EDEFAULT);
			return;
		case Bpmn2Package.CALL_ACTIVITY__IN_PARAMETERS:
			getInParameters().clear();
			return;
		case Bpmn2Package.CALL_ACTIVITY__OUT_PARAMETERS:
			getOutParameters().clear();
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
		case Bpmn2Package.CALL_ACTIVITY__CALLED_ELEMENT_REF:
			return calledElementRef != null;
		case Bpmn2Package.CALL_ACTIVITY__CALLED_ELEMENT:
			return CALLED_ELEMENT_EDEFAULT == null ? calledElement != null
					: !CALLED_ELEMENT_EDEFAULT.equals(calledElement);
		case Bpmn2Package.CALL_ACTIVITY__IN_PARAMETERS:
			return inParameters != null && !inParameters.isEmpty();
		case Bpmn2Package.CALL_ACTIVITY__OUT_PARAMETERS:
			return outParameters != null && !outParameters.isEmpty();
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
		result.append(" (calledElement: ");
		result.append(calledElement);
		result.append(')');
		return result.toString();
	}

} //CallActivityImpl
