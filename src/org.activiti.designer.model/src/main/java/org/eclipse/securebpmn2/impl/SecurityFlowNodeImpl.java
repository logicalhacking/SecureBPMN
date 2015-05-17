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

import org.eclipse.bpmn2.impl.FlowNodeImpl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.securebpmn2.Securebpmn2Package;
import org.eclipse.securebpmn2.SecurityFlowNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Security Flow Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class SecurityFlowNodeImpl extends FlowNodeImpl implements
		SecurityFlowNode {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SecurityFlowNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Securebpmn2Package.Literals.SECURITY_FLOW_NODE;
	}

} //SecurityFlowNodeImpl
