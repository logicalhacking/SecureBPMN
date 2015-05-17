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
package org.eclipse.securebpmn2;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.securebpmn2.Securebpmn2Package
 * @generated
 */
public interface Securebpmn2Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Securebpmn2Factory eINSTANCE = org.eclipse.securebpmn2.impl.Securebpmn2FactoryImpl
			.init();

	/**
	 * Returns a new object of class '<em>Security Flow Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Security Flow Node</em>'.
	 * @generated
	 */
	SecurityFlowNode createSecurityFlowNode();

	/**
	 * Returns a new object of class '<em>Security Flow</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Security Flow</em>'.
	 * @generated
	 */
	SecurityFlow createSecurityFlow();

	/**
	 * Returns a new object of class '<em>Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role</em>'.
	 * @generated
	 */
	Role createRole();

	/**
	 * Returns a new object of class '<em>User</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User</em>'.
	 * @generated
	 */
	User createUser();

	/**
	 * Returns a new object of class '<em>Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Group</em>'.
	 * @generated
	 */
	Group createGroup();

	/**
	 * Returns a new object of class '<em>Activity Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activity Action</em>'.
	 * @generated
	 */
	ActivityAction createActivityAction();

	/**
	 * Returns a new object of class '<em>Atomic Activity Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Atomic Activity Action</em>'.
	 * @generated
	 */
	AtomicActivityAction createAtomicActivityAction();

	/**
	 * Returns a new object of class '<em>Composite Activity Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composite Activity Action</em>'.
	 * @generated
	 */
	CompositeActivityAction createCompositeActivityAction();

	/**
	 * Returns a new object of class '<em>Permission</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Permission</em>'.
	 * @generated
	 */
	Permission createPermission();

	/**
	 * Returns a new object of class '<em>Need To Know</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Need To Know</em>'.
	 * @generated
	 */
	NeedToKnow createNeedToKnow();

	/**
	 * Returns a new object of class '<em>Separation Of Duty</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Separation Of Duty</em>'.
	 * @generated
	 */
	SeparationOfDuty createSeparationOfDuty();

	/**
	 * Returns a new object of class '<em>Binding Of Duty</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binding Of Duty</em>'.
	 * @generated
	 */
	BindingOfDuty createBindingOfDuty();

	/**
	 * Returns a new object of class '<em>Authorization Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Authorization Constraint</em>'.
	 * @generated
	 */
	AuthorizationConstraint createAuthorizationConstraint();

	/**
	 * Returns a new object of class '<em>Activity Authorization Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activity Authorization Constraint</em>'.
	 * @generated
	 */
	ActivityAuthorizationConstraint createActivityAuthorizationConstraint();

	/**
	 * Returns a new object of class '<em>Process Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Action</em>'.
	 * @generated
	 */
	ProcessAction createProcessAction();

	/**
	 * Returns a new object of class '<em>Atomic Process Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Atomic Process Action</em>'.
	 * @generated
	 */
	AtomicProcessAction createAtomicProcessAction();

	/**
	 * Returns a new object of class '<em>Composite Process Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composite Process Action</em>'.
	 * @generated
	 */
	CompositeProcessAction createCompositeProcessAction();

	/**
	 * Returns a new object of class '<em>Item Aware Element Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Item Aware Element Action</em>'.
	 * @generated
	 */
	ItemAwareElementAction createItemAwareElementAction();

	/**
	 * Returns a new object of class '<em>Atomic Item Aware Element Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Atomic Item Aware Element Action</em>'.
	 * @generated
	 */
	AtomicItemAwareElementAction createAtomicItemAwareElementAction();

	/**
	 * Returns a new object of class '<em>Composite Item Aware Element Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composite Item Aware Element Action</em>'.
	 * @generated
	 */
	CompositeItemAwareElementAction createCompositeItemAwareElementAction();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Securebpmn2Package getSecurebpmn2Package();

} //Securebpmn2Factory
