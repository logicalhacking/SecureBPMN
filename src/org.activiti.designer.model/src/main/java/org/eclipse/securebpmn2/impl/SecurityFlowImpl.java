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

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.FlowNode;

import org.eclipse.bpmn2.impl.FlowElementImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.securebpmn2.Securebpmn2Package;
import org.eclipse.securebpmn2.SecurityFlow;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Security Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.impl.SecurityFlowImpl#getSourceRefNode <em>Source Ref Node</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.SecurityFlowImpl#getTargetRefNode <em>Target Ref Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SecurityFlowImpl extends FlowElementImpl implements SecurityFlow {
	/**
	 * The cached value of the '{@link #getSourceRefNode() <em>Source Ref Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceRefNode()
	 * @generated
	 * @ordered
	 */
	protected FlowNode sourceRefNode;

	/**
	 * The cached value of the '{@link #getTargetRefNode() <em>Target Ref Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetRefNode()
	 * @generated
	 * @ordered
	 */
	protected FlowNode targetRefNode;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SecurityFlowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Securebpmn2Package.Literals.SECURITY_FLOW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowNode getSourceRefNode() {
		return sourceRefNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSourceRefNode(FlowNode newSourceRefNode,
			NotificationChain msgs) {
		FlowNode oldSourceRefNode = sourceRefNode;
		sourceRefNode = newSourceRefNode;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					Securebpmn2Package.SECURITY_FLOW__SOURCE_REF_NODE,
					oldSourceRefNode, newSourceRefNode);
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
	public void setSourceRefNode(FlowNode newSourceRefNode) {
		if (newSourceRefNode != sourceRefNode) {
			NotificationChain msgs = null;
			if (sourceRefNode != null)
				msgs = ((InternalEObject) sourceRefNode).eInverseRemove(this,
						Bpmn2Package.FLOW_NODE__OUTGOING_SECURITY_FLOW,
						FlowNode.class, msgs);
			if (newSourceRefNode != null)
				msgs = ((InternalEObject) newSourceRefNode).eInverseAdd(this,
						Bpmn2Package.FLOW_NODE__OUTGOING_SECURITY_FLOW,
						FlowNode.class, msgs);
			msgs = basicSetSourceRefNode(newSourceRefNode, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Securebpmn2Package.SECURITY_FLOW__SOURCE_REF_NODE,
					newSourceRefNode, newSourceRefNode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowNode getTargetRefNode() {
		return targetRefNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTargetRefNode(FlowNode newTargetRefNode,
			NotificationChain msgs) {
		FlowNode oldTargetRefNode = targetRefNode;
		targetRefNode = newTargetRefNode;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					Securebpmn2Package.SECURITY_FLOW__TARGET_REF_NODE,
					oldTargetRefNode, newTargetRefNode);
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
	public void setTargetRefNode(FlowNode newTargetRefNode) {
		if (newTargetRefNode != targetRefNode) {
			NotificationChain msgs = null;
			if (targetRefNode != null)
				msgs = ((InternalEObject) targetRefNode).eInverseRemove(this,
						Bpmn2Package.FLOW_NODE__INCOMING_SECURITY_FLOW,
						FlowNode.class, msgs);
			if (newTargetRefNode != null)
				msgs = ((InternalEObject) newTargetRefNode).eInverseAdd(this,
						Bpmn2Package.FLOW_NODE__INCOMING_SECURITY_FLOW,
						FlowNode.class, msgs);
			msgs = basicSetTargetRefNode(newTargetRefNode, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Securebpmn2Package.SECURITY_FLOW__TARGET_REF_NODE,
					newTargetRefNode, newTargetRefNode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Securebpmn2Package.SECURITY_FLOW__SOURCE_REF_NODE:
			if (sourceRefNode != null)
				msgs = ((InternalEObject) sourceRefNode).eInverseRemove(this,
						Bpmn2Package.FLOW_NODE__OUTGOING_SECURITY_FLOW,
						FlowNode.class, msgs);
			return basicSetSourceRefNode((FlowNode) otherEnd, msgs);
		case Securebpmn2Package.SECURITY_FLOW__TARGET_REF_NODE:
			if (targetRefNode != null)
				msgs = ((InternalEObject) targetRefNode).eInverseRemove(this,
						Bpmn2Package.FLOW_NODE__INCOMING_SECURITY_FLOW,
						FlowNode.class, msgs);
			return basicSetTargetRefNode((FlowNode) otherEnd, msgs);
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
		case Securebpmn2Package.SECURITY_FLOW__SOURCE_REF_NODE:
			return basicSetSourceRefNode(null, msgs);
		case Securebpmn2Package.SECURITY_FLOW__TARGET_REF_NODE:
			return basicSetTargetRefNode(null, msgs);
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
		case Securebpmn2Package.SECURITY_FLOW__SOURCE_REF_NODE:
			return getSourceRefNode();
		case Securebpmn2Package.SECURITY_FLOW__TARGET_REF_NODE:
			return getTargetRefNode();
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
		case Securebpmn2Package.SECURITY_FLOW__SOURCE_REF_NODE:
			setSourceRefNode((FlowNode) newValue);
			return;
		case Securebpmn2Package.SECURITY_FLOW__TARGET_REF_NODE:
			setTargetRefNode((FlowNode) newValue);
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
		case Securebpmn2Package.SECURITY_FLOW__SOURCE_REF_NODE:
			setSourceRefNode((FlowNode) null);
			return;
		case Securebpmn2Package.SECURITY_FLOW__TARGET_REF_NODE:
			setTargetRefNode((FlowNode) null);
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
		case Securebpmn2Package.SECURITY_FLOW__SOURCE_REF_NODE:
			return sourceRefNode != null;
		case Securebpmn2Package.SECURITY_FLOW__TARGET_REF_NODE:
			return targetRefNode != null;
		}
		return super.eIsSet(featureID);
	}

} //SecurityFlowImpl
