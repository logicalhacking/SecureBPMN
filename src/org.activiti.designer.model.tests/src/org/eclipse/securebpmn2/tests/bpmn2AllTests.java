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

import org.eclipse.bpmn2.di.tests.BpmnDiTests;

import org.eclipse.bpmn2.tests.Bpmn2Tests;

import org.eclipse.dd.dc.tests.DcTests;

import org.eclipse.dd.di.tests.DiTests;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>bpmn2</b></em>' model.
 * <!-- end-user-doc -->
 * @generated
 */
public class bpmn2AllTests extends TestSuite {

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
		TestSuite suite = new bpmn2AllTests("bpmn2 Tests");
		suite.addTest(Securebpmn2Tests.suite());
		suite.addTest(Bpmn2Tests.suite());
		suite.addTest(BpmnDiTests.suite());
		suite.addTest(DiTests.suite());
		suite.addTest(DcTests.suite());
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public bpmn2AllTests(String name) {
		super(name);
	}

} //bpmn2AllTests
