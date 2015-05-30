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
package org.eclipse.securebpmn2.tests;

import junit.textui.TestRunner;

import org.eclipse.bpmn2.tests.FlowNodeTest;

import org.eclipse.securebpmn2.Securebpmn2Factory;
import org.eclipse.securebpmn2.SecurityFlowNode;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Security Flow Node</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class SecurityFlowNodeTest extends FlowNodeTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(SecurityFlowNodeTest.class);
	}

	/**
	 * Constructs a new Security Flow Node test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityFlowNodeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Security Flow Node test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected SecurityFlowNode getFixture() {
		return (SecurityFlowNode) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(Securebpmn2Factory.eINSTANCE.createSecurityFlowNode());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //SecurityFlowNodeTest
