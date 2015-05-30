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

import org.eclipse.bpmn2.Bpmn2Package;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.securebpmn2.CompositeProcessAction;
import org.eclipse.securebpmn2.ProcessAction;
import org.eclipse.securebpmn2.Securebpmn2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.impl.ProcessActionImpl#getCompositeProcessActions <em>Composite Process Actions</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.ProcessActionImpl#getProcess <em>Process</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessActionImpl extends ActionImpl implements ProcessAction {
	/**
	 * The cached value of the '{@link #getCompositeProcessActions() <em>Composite Process Actions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompositeProcessActions()
	 * @generated
	 * @ordered
	 */
	protected EList<CompositeProcessAction> compositeProcessActions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Securebpmn2Package.Literals.PROCESS_ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<CompositeProcessAction> getCompositeProcessActions() {
		if (compositeProcessActions == null) {
			compositeProcessActions = new EObjectWithInverseResolvingEList.ManyInverse<CompositeProcessAction>(
					CompositeProcessAction.class,
					this,
					Securebpmn2Package.PROCESS_ACTION__COMPOSITE_PROCESS_ACTIONS,
					Securebpmn2Package.COMPOSITE_PROCESS_ACTION__PROCESS_ACTIONS);
		}
		return compositeProcessActions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.bpmn2.Process getProcess() {
		if (eContainerFeatureID() != Securebpmn2Package.PROCESS_ACTION__PROCESS)
			return null;
		return (org.eclipse.bpmn2.Process) eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProcess(
			org.eclipse.bpmn2.Process newProcess, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newProcess,
				Securebpmn2Package.PROCESS_ACTION__PROCESS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcess(org.eclipse.bpmn2.Process newProcess) {
		if (newProcess != eInternalContainer()
				|| (eContainerFeatureID() != Securebpmn2Package.PROCESS_ACTION__PROCESS && newProcess != null)) {
			if (EcoreUtil.isAncestor(this, newProcess))
				throw new IllegalArgumentException(
						"Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newProcess != null)
				msgs = ((InternalEObject) newProcess).eInverseAdd(this,
						Bpmn2Package.PROCESS__PROCESS_ACTIONS,
						org.eclipse.bpmn2.Process.class, msgs);
			msgs = basicSetProcess(newProcess, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Securebpmn2Package.PROCESS_ACTION__PROCESS, newProcess,
					newProcess));
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
		case Securebpmn2Package.PROCESS_ACTION__COMPOSITE_PROCESS_ACTIONS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getCompositeProcessActions())
					.basicAdd(otherEnd, msgs);
		case Securebpmn2Package.PROCESS_ACTION__PROCESS:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetProcess((org.eclipse.bpmn2.Process) otherEnd, msgs);
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
		case Securebpmn2Package.PROCESS_ACTION__COMPOSITE_PROCESS_ACTIONS:
			return ((InternalEList<?>) getCompositeProcessActions())
					.basicRemove(otherEnd, msgs);
		case Securebpmn2Package.PROCESS_ACTION__PROCESS:
			return basicSetProcess(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case Securebpmn2Package.PROCESS_ACTION__PROCESS:
			return eInternalContainer().eInverseRemove(this,
					Bpmn2Package.PROCESS__PROCESS_ACTIONS,
					org.eclipse.bpmn2.Process.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Securebpmn2Package.PROCESS_ACTION__COMPOSITE_PROCESS_ACTIONS:
			return getCompositeProcessActions();
		case Securebpmn2Package.PROCESS_ACTION__PROCESS:
			return getProcess();
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
		case Securebpmn2Package.PROCESS_ACTION__COMPOSITE_PROCESS_ACTIONS:
			getCompositeProcessActions().clear();
			getCompositeProcessActions().addAll(
					(Collection<? extends CompositeProcessAction>) newValue);
			return;
		case Securebpmn2Package.PROCESS_ACTION__PROCESS:
			setProcess((org.eclipse.bpmn2.Process) newValue);
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
		case Securebpmn2Package.PROCESS_ACTION__COMPOSITE_PROCESS_ACTIONS:
			getCompositeProcessActions().clear();
			return;
		case Securebpmn2Package.PROCESS_ACTION__PROCESS:
			setProcess((org.eclipse.bpmn2.Process) null);
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
		case Securebpmn2Package.PROCESS_ACTION__COMPOSITE_PROCESS_ACTIONS:
			return compositeProcessActions != null
					&& !compositeProcessActions.isEmpty();
		case Securebpmn2Package.PROCESS_ACTION__PROCESS:
			return getProcess() != null;
		}
		return super.eIsSet(featureID);
	}

} //ProcessActionImpl
