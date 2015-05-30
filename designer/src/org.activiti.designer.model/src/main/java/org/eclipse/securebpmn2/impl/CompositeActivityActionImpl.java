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

import org.eclipse.securebpmn2.ActivityAction;
import org.eclipse.securebpmn2.CompositeActivityAction;
import org.eclipse.securebpmn2.Securebpmn2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Composite Activity Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.impl.CompositeActivityActionImpl#getActivityActions <em>Activity Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompositeActivityActionImpl extends ActivityActionImpl implements
		CompositeActivityAction {
	/**
	 * The cached value of the '{@link #getActivityActions() <em>Activity Actions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityActions()
	 * @generated
	 * @ordered
	 */
	protected EList<ActivityAction> activityActions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompositeActivityActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Securebpmn2Package.Literals.COMPOSITE_ACTIVITY_ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ActivityAction> getActivityActions() {
		if (activityActions == null) {
			activityActions = new EObjectWithInverseResolvingEList.ManyInverse<ActivityAction>(
					ActivityAction.class,
					this,
					Securebpmn2Package.COMPOSITE_ACTIVITY_ACTION__ACTIVITY_ACTIONS,
					Securebpmn2Package.ACTIVITY_ACTION__COMPOSITE_ACTIVITY_ACTIONS);
		}
		return activityActions;
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
		case Securebpmn2Package.COMPOSITE_ACTIVITY_ACTION__ACTIVITY_ACTIONS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getActivityActions())
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
		case Securebpmn2Package.COMPOSITE_ACTIVITY_ACTION__ACTIVITY_ACTIONS:
			return ((InternalEList<?>) getActivityActions()).basicRemove(
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
		case Securebpmn2Package.COMPOSITE_ACTIVITY_ACTION__ACTIVITY_ACTIONS:
			return getActivityActions();
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
		case Securebpmn2Package.COMPOSITE_ACTIVITY_ACTION__ACTIVITY_ACTIONS:
			getActivityActions().clear();
			getActivityActions().addAll(
					(Collection<? extends ActivityAction>) newValue);
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
		case Securebpmn2Package.COMPOSITE_ACTIVITY_ACTION__ACTIVITY_ACTIONS:
			getActivityActions().clear();
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
		case Securebpmn2Package.COMPOSITE_ACTIVITY_ACTION__ACTIVITY_ACTIONS:
			return activityActions != null && !activityActions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CompositeActivityActionImpl
