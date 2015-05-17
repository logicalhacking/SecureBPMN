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

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Bpmn2Package;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.securebpmn2.ActivityAction;
import org.eclipse.securebpmn2.CompositeActivityAction;
import org.eclipse.securebpmn2.Securebpmn2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.impl.ActivityActionImpl#getCompositeActivityActions <em>Composite Activity Actions</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.ActivityActionImpl#getActivity <em>Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityActionImpl extends ActionImpl implements ActivityAction {
	/**
	 * The cached value of the '{@link #getCompositeActivityActions() <em>Composite Activity Actions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompositeActivityActions()
	 * @generated
	 * @ordered
	 */
	protected EList<CompositeActivityAction> compositeActivityActions;

	/**
	 * The cached value of the '{@link #getActivity() <em>Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivity()
	 * @generated
	 * @ordered
	 */
	protected Activity activity;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Securebpmn2Package.Literals.ACTIVITY_ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<CompositeActivityAction> getCompositeActivityActions() {
		if (compositeActivityActions == null) {
			compositeActivityActions = new EObjectWithInverseResolvingEList.ManyInverse<CompositeActivityAction>(
					CompositeActivityAction.class,
					this,
					Securebpmn2Package.ACTIVITY_ACTION__COMPOSITE_ACTIVITY_ACTIONS,
					Securebpmn2Package.COMPOSITE_ACTIVITY_ACTION__ACTIVITY_ACTIONS);
		}
		return compositeActivityActions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity getActivity() {
		if (activity != null && activity.eIsProxy()) {
			InternalEObject oldActivity = (InternalEObject) activity;
			activity = (Activity) eResolveProxy(oldActivity);
			if (activity != oldActivity) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							Securebpmn2Package.ACTIVITY_ACTION__ACTIVITY,
							oldActivity, activity));
			}
		}
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity basicGetActivity() {
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivity(Activity newActivity,
			NotificationChain msgs) {
		Activity oldActivity = activity;
		activity = newActivity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					Securebpmn2Package.ACTIVITY_ACTION__ACTIVITY, oldActivity,
					newActivity);
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
	public void setActivity(Activity newActivity) {
		if (newActivity != activity) {
			NotificationChain msgs = null;
			if (activity != null)
				msgs = ((InternalEObject) activity).eInverseRemove(this,
						Bpmn2Package.ACTIVITY__ACTIVITY_ACTIONS,
						Activity.class, msgs);
			if (newActivity != null)
				msgs = ((InternalEObject) newActivity).eInverseAdd(this,
						Bpmn2Package.ACTIVITY__ACTIVITY_ACTIONS,
						Activity.class, msgs);
			msgs = basicSetActivity(newActivity, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Securebpmn2Package.ACTIVITY_ACTION__ACTIVITY, newActivity,
					newActivity));
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
		case Securebpmn2Package.ACTIVITY_ACTION__COMPOSITE_ACTIVITY_ACTIONS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getCompositeActivityActions())
					.basicAdd(otherEnd, msgs);
		case Securebpmn2Package.ACTIVITY_ACTION__ACTIVITY:
			if (activity != null)
				msgs = ((InternalEObject) activity).eInverseRemove(this,
						Bpmn2Package.ACTIVITY__ACTIVITY_ACTIONS,
						Activity.class, msgs);
			return basicSetActivity((Activity) otherEnd, msgs);
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
		case Securebpmn2Package.ACTIVITY_ACTION__COMPOSITE_ACTIVITY_ACTIONS:
			return ((InternalEList<?>) getCompositeActivityActions())
					.basicRemove(otherEnd, msgs);
		case Securebpmn2Package.ACTIVITY_ACTION__ACTIVITY:
			return basicSetActivity(null, msgs);
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
		case Securebpmn2Package.ACTIVITY_ACTION__COMPOSITE_ACTIVITY_ACTIONS:
			return getCompositeActivityActions();
		case Securebpmn2Package.ACTIVITY_ACTION__ACTIVITY:
			if (resolve)
				return getActivity();
			return basicGetActivity();
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
		case Securebpmn2Package.ACTIVITY_ACTION__COMPOSITE_ACTIVITY_ACTIONS:
			getCompositeActivityActions().clear();
			getCompositeActivityActions().addAll(
					(Collection<? extends CompositeActivityAction>) newValue);
			return;
		case Securebpmn2Package.ACTIVITY_ACTION__ACTIVITY:
			setActivity((Activity) newValue);
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
		case Securebpmn2Package.ACTIVITY_ACTION__COMPOSITE_ACTIVITY_ACTIONS:
			getCompositeActivityActions().clear();
			return;
		case Securebpmn2Package.ACTIVITY_ACTION__ACTIVITY:
			setActivity((Activity) null);
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
		case Securebpmn2Package.ACTIVITY_ACTION__COMPOSITE_ACTIVITY_ACTIONS:
			return compositeActivityActions != null
					&& !compositeActivityActions.isEmpty();
		case Securebpmn2Package.ACTIVITY_ACTION__ACTIVITY:
			return activity != null;
		}
		return super.eIsSet(featureID);
	}

} //ActivityActionImpl
