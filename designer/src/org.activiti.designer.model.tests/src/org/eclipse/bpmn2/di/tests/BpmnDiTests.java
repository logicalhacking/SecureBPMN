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
package org.eclipse.bpmn2.di.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>di</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class BpmnDiTests extends TestSuite {

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
		TestSuite suite = new BpmnDiTests("di Tests");
		suite.addTestSuite(DocumentRootTest.class);
		suite.addTestSuite(BPMNDiagramTest.class);
		suite.addTestSuite(BPMNEdgeTest.class);
		suite.addTestSuite(BPMNLabelTest.class);
		suite.addTestSuite(BPMNPlaneTest.class);
		suite.addTestSuite(BPMNShapeTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnDiTests(String name) {
		super(name);
	}

} //BpmnDiTests
