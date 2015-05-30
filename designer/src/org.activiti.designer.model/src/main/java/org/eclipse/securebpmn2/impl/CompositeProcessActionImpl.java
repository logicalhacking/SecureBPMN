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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.securebpmn2.CompositeProcessAction;
import org.eclipse.securebpmn2.ProcessAction;
import org.eclipse.securebpmn2.Securebpmn2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Composite Process Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.impl.CompositeProcessActionImpl#getProcessActions <em>Process Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompositeProcessActionImpl extends ProcessActionImpl implements
		CompositeProcessAction {
	/**
	 * The cached value of the '{@link #getProcessActions() <em>Process Actions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessActions()
	 * @generated
	 * @ordered
	 */
	protected EList<ProcessAction> processActions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompositeProcessActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Securebpmn2Package.Literals.COMPOSITE_PROCESS_ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ProcessAction> getProcessActions() {
		if (processActions == null) {
			processActions = new EObjectWithInverseResolvingEList.ManyInverse<ProcessAction>(
					ProcessAction.class,
					this,
					Securebpmn2Package.COMPOSITE_PROCESS_ACTION__PROCESS_ACTIONS,
					Securebpmn2Package.PROCESS_ACTION__COMPOSITE_PROCESS_ACTIONS);
		}
		return processActions;
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
		case Securebpmn2Package.COMPOSITE_PROCESS_ACTION__PROCESS_ACTIONS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getProcessActions())
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
		case Securebpmn2Package.COMPOSITE_PROCESS_ACTION__PROCESS_ACTIONS:
			return ((InternalEList<?>) getProcessActions()).basicRemove(
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
		case Securebpmn2Package.COMPOSITE_PROCESS_ACTION__PROCESS_ACTIONS:
			return getProcessActions();
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
		case Securebpmn2Package.COMPOSITE_PROCESS_ACTION__PROCESS_ACTIONS:
			getProcessActions().clear();
			getProcessActions().addAll(
					(Collection<? extends ProcessAction>) newValue);
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
		case Securebpmn2Package.COMPOSITE_PROCESS_ACTION__PROCESS_ACTIONS:
			getProcessActions().clear();
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
		case Securebpmn2Package.COMPOSITE_PROCESS_ACTION__PROCESS_ACTIONS:
			return processActions != null && !processActions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CompositeProcessActionImpl
