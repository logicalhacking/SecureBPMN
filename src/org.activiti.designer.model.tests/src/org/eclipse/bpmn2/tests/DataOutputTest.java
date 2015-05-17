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
package org.eclipse.bpmn2.tests;

import junit.textui.TestRunner;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.DataOutput;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Data Output</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.DataOutput#getOutputSetWithOptional() <em>Output Set With Optional</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataOutput#getOutputSetWithWhileExecuting() <em>Output Set With While Executing</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataOutput#getOutputSetRefs() <em>Output Set Refs</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class DataOutputTest extends ItemAwareElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(DataOutputTest.class);
	}

	/**
	 * Constructs a new Data Output test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataOutputTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Data Output test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected DataOutput getFixture() {
		return (DataOutput) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(Bpmn2Factory.eINSTANCE.createDataOutput());
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

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DataOutput#getOutputSetWithOptional() <em>Output Set With Optional</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DataOutput#getOutputSetWithOptional()
	 * @generated
	 */
	public void testGetOutputSetWithOptional() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DataOutput#getOutputSetWithWhileExecuting() <em>Output Set With While Executing</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DataOutput#getOutputSetWithWhileExecuting()
	 * @generated
	 */
	public void testGetOutputSetWithWhileExecuting() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DataOutput#getOutputSetRefs() <em>Output Set Refs</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DataOutput#getOutputSetRefs()
	 * @generated
	 */
	public void testGetOutputSetRefs() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

} //DataOutputTest
