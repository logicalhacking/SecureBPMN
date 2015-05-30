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
import org.eclipse.bpmn2.DataState;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ItemDefinition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.securebpmn2.ItemAwareElementAction;
import org.eclipse.securebpmn2.Securebpmn2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Item Aware Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.ItemAwareElementImpl#getDataState <em>Data State</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ItemAwareElementImpl#getItemSubjectRef <em>Item Subject Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ItemAwareElementImpl#getItemAwareElementActions <em>Item Aware Element Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ItemAwareElementImpl extends BaseElementImpl implements
		ItemAwareElement {
	/**
	 * The cached value of the '{@link #getDataState() <em>Data State</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataState()
	 * @generated
	 * @ordered
	 */
	protected DataState dataState;

	/**
	 * The cached value of the '{@link #getItemSubjectRef() <em>Item Subject Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getItemSubjectRef()
	 * @generated
	 * @ordered
	 */
	protected ItemDefinition itemSubjectRef;

	/**
	 * The cached value of the '{@link #getItemAwareElementActions() <em>Item Aware Element Actions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getItemAwareElementActions()
	 * @generated
	 * @ordered
	 */
	protected EList<ItemAwareElementAction> itemAwareElementActions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ItemAwareElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Bpmn2Package.Literals.ITEM_AWARE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataState getDataState() {
		return dataState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataState(DataState newDataState,
			NotificationChain msgs) {
		DataState oldDataState = dataState;
		dataState = newDataState;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_STATE, oldDataState,
					newDataState);
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
	public void setDataState(DataState newDataState) {
		if (newDataState != dataState) {
			NotificationChain msgs = null;
			if (dataState != null)
				msgs = ((InternalEObject) dataState).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_STATE,
						null, msgs);
			if (newDataState != null)
				msgs = ((InternalEObject) newDataState).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_STATE,
						null, msgs);
			msgs = basicSetDataState(newDataState, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_STATE, newDataState,
					newDataState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ItemDefinition getItemSubjectRef() {
		if (itemSubjectRef != null && itemSubjectRef.eIsProxy()) {
			InternalEObject oldItemSubjectRef = (InternalEObject) itemSubjectRef;
			itemSubjectRef = (ItemDefinition) eResolveProxy(oldItemSubjectRef);
			if (itemSubjectRef != oldItemSubjectRef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_SUBJECT_REF,
							oldItemSubjectRef, itemSubjectRef));
			}
		}
		return itemSubjectRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ItemDefinition basicGetItemSubjectRef() {
		return itemSubjectRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setItemSubjectRef(ItemDefinition newItemSubjectRef) {
		ItemDefinition oldItemSubjectRef = itemSubjectRef;
		itemSubjectRef = newItemSubjectRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_SUBJECT_REF,
					oldItemSubjectRef, itemSubjectRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ItemAwareElementAction> getItemAwareElementActions() {
		if (itemAwareElementActions == null) {
			itemAwareElementActions = new EObjectContainmentWithInverseEList<ItemAwareElementAction>(
					ItemAwareElementAction.class,
					this,
					Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_AWARE_ELEMENT_ACTIONS,
					Securebpmn2Package.ITEM_AWARE_ELEMENT_ACTION__ITEM_AWARE_ELEMENT);
		}
		return itemAwareElementActions;
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
		case Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_AWARE_ELEMENT_ACTIONS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getItemAwareElementActions())
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
		case Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_STATE:
			return basicSetDataState(null, msgs);
		case Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_AWARE_ELEMENT_ACTIONS:
			return ((InternalEList<?>) getItemAwareElementActions())
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
		case Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_STATE:
			return getDataState();
		case Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_SUBJECT_REF:
			if (resolve)
				return getItemSubjectRef();
			return basicGetItemSubjectRef();
		case Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_AWARE_ELEMENT_ACTIONS:
			return getItemAwareElementActions();
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
		case Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_STATE:
			setDataState((DataState) newValue);
			return;
		case Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_SUBJECT_REF:
			setItemSubjectRef((ItemDefinition) newValue);
			return;
		case Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_AWARE_ELEMENT_ACTIONS:
			getItemAwareElementActions().clear();
			getItemAwareElementActions().addAll(
					(Collection<? extends ItemAwareElementAction>) newValue);
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
		case Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_STATE:
			setDataState((DataState) null);
			return;
		case Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_SUBJECT_REF:
			setItemSubjectRef((ItemDefinition) null);
			return;
		case Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_AWARE_ELEMENT_ACTIONS:
			getItemAwareElementActions().clear();
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
		case Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_STATE:
			return dataState != null;
		case Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_SUBJECT_REF:
			return itemSubjectRef != null;
		case Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_AWARE_ELEMENT_ACTIONS:
			return itemAwareElementActions != null
					&& !itemAwareElementActions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ItemAwareElementImpl
