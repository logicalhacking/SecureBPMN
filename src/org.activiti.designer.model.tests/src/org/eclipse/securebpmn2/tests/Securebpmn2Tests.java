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

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>securebpmn2</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class Securebpmn2Tests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new Securebpmn2Tests("securebpmn2 Tests");
		suite.addTestSuite(SecurityFlowNodeTest.class);
		suite.addTestSuite(SecurityFlowTest.class);
		suite.addTestSuite(RoleTest.class);
		suite.addTestSuite(UserTest.class);
		suite.addTestSuite(GroupTest.class);
		suite.addTestSuite(ActivityActionTest.class);
		suite.addTestSuite(AtomicActivityActionTest.class);
		suite.addTestSuite(CompositeActivityActionTest.class);
		suite.addTestSuite(PermissionTest.class);
		suite.addTestSuite(NeedToKnowTest.class);
		suite.addTestSuite(SeparationOfDutyTest.class);
		suite.addTestSuite(BindingOfDutyTest.class);
		suite.addTestSuite(AuthorizationConstraintTest.class);
		suite.addTestSuite(ActivityAuthorizationConstraintTest.class);
		suite.addTestSuite(ProcessActionTest.class);
		suite.addTestSuite(AtomicProcessActionTest.class);
		suite.addTestSuite(CompositeProcessActionTest.class);
		suite.addTestSuite(ItemAwareElementActionTest.class);
		suite.addTestSuite(AtomicItemAwareElementActionTest.class);
		suite.addTestSuite(CompositeItemAwareElementActionTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Securebpmn2Tests(String name) {
		super(name);
	}

} //Securebpmn2Tests
