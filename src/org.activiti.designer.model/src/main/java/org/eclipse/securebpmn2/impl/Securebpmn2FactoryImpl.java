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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.securebpmn2.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Securebpmn2FactoryImpl extends EFactoryImpl implements
		Securebpmn2Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Securebpmn2Factory init() {
		try {
			Securebpmn2Factory theSecurebpmn2Factory = (Securebpmn2Factory) EPackage.Registry.INSTANCE
					.getEFactory("http://securebpmn");
			if (theSecurebpmn2Factory != null) {
				return theSecurebpmn2Factory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Securebpmn2FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Securebpmn2FactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case Securebpmn2Package.SECURITY_FLOW_NODE:
			return createSecurityFlowNode();
		case Securebpmn2Package.SECURITY_FLOW:
			return createSecurityFlow();
		case Securebpmn2Package.ROLE:
			return createRole();
		case Securebpmn2Package.USER:
			return createUser();
		case Securebpmn2Package.GROUP:
			return createGroup();
		case Securebpmn2Package.ACTIVITY_ACTION:
			return createActivityAction();
		case Securebpmn2Package.ATOMIC_ACTIVITY_ACTION:
			return createAtomicActivityAction();
		case Securebpmn2Package.COMPOSITE_ACTIVITY_ACTION:
			return createCompositeActivityAction();
		case Securebpmn2Package.PERMISSION:
			return createPermission();
		case Securebpmn2Package.NEED_TO_KNOW:
			return createNeedToKnow();
		case Securebpmn2Package.SEPARATION_OF_DUTY:
			return createSeparationOfDuty();
		case Securebpmn2Package.BINDING_OF_DUTY:
			return createBindingOfDuty();
		case Securebpmn2Package.AUTHORIZATION_CONSTRAINT:
			return createAuthorizationConstraint();
		case Securebpmn2Package.ACTIVITY_AUTHORIZATION_CONSTRAINT:
			return createActivityAuthorizationConstraint();
		case Securebpmn2Package.PROCESS_ACTION:
			return createProcessAction();
		case Securebpmn2Package.ATOMIC_PROCESS_ACTION:
			return createAtomicProcessAction();
		case Securebpmn2Package.COMPOSITE_PROCESS_ACTION:
			return createCompositeProcessAction();
		case Securebpmn2Package.ITEM_AWARE_ELEMENT_ACTION:
			return createItemAwareElementAction();
		case Securebpmn2Package.ATOMIC_ITEM_AWARE_ELEMENT_ACTION:
			return createAtomicItemAwareElementAction();
		case Securebpmn2Package.COMPOSITE_ITEM_AWARE_ELEMENT_ACTION:
			return createCompositeItemAwareElementAction();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName()
					+ "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityFlowNode createSecurityFlowNode() {
		SecurityFlowNodeImpl securityFlowNode = new SecurityFlowNodeImpl();
		return securityFlowNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityFlow createSecurityFlow() {
		SecurityFlowImpl securityFlow = new SecurityFlowImpl();
		return securityFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Role createRole() {
		RoleImpl role = new RoleImpl();
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User createUser() {
		UserImpl user = new UserImpl();
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Group createGroup() {
		GroupImpl group = new GroupImpl();
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityAction createActivityAction() {
		ActivityActionImpl activityAction = new ActivityActionImpl();
		return activityAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtomicActivityAction createAtomicActivityAction() {
		AtomicActivityActionImpl atomicActivityAction = new AtomicActivityActionImpl();
		return atomicActivityAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeActivityAction createCompositeActivityAction() {
		CompositeActivityActionImpl compositeActivityAction = new CompositeActivityActionImpl();
		return compositeActivityAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Permission createPermission() {
		PermissionImpl permission = new PermissionImpl();
		return permission;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NeedToKnow createNeedToKnow() {
		NeedToKnowImpl needToKnow = new NeedToKnowImpl();
		return needToKnow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SeparationOfDuty createSeparationOfDuty() {
		SeparationOfDutyImpl separationOfDuty = new SeparationOfDutyImpl();
		return separationOfDuty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingOfDuty createBindingOfDuty() {
		BindingOfDutyImpl bindingOfDuty = new BindingOfDutyImpl();
		return bindingOfDuty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AuthorizationConstraint createAuthorizationConstraint() {
		AuthorizationConstraintImpl authorizationConstraint = new AuthorizationConstraintImpl();
		return authorizationConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityAuthorizationConstraint createActivityAuthorizationConstraint() {
		ActivityAuthorizationConstraintImpl activityAuthorizationConstraint = new ActivityAuthorizationConstraintImpl();
		return activityAuthorizationConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessAction createProcessAction() {
		ProcessActionImpl processAction = new ProcessActionImpl();
		return processAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtomicProcessAction createAtomicProcessAction() {
		AtomicProcessActionImpl atomicProcessAction = new AtomicProcessActionImpl();
		return atomicProcessAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeProcessAction createCompositeProcessAction() {
		CompositeProcessActionImpl compositeProcessAction = new CompositeProcessActionImpl();
		return compositeProcessAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ItemAwareElementAction createItemAwareElementAction() {
		ItemAwareElementActionImpl itemAwareElementAction = new ItemAwareElementActionImpl();
		return itemAwareElementAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtomicItemAwareElementAction createAtomicItemAwareElementAction() {
		AtomicItemAwareElementActionImpl atomicItemAwareElementAction = new AtomicItemAwareElementActionImpl();
		return atomicItemAwareElementAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeItemAwareElementAction createCompositeItemAwareElementAction() {
		CompositeItemAwareElementActionImpl compositeItemAwareElementAction = new CompositeItemAwareElementActionImpl();
		return compositeItemAwareElementAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Securebpmn2Package getSecurebpmn2Package() {
		return (Securebpmn2Package) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Securebpmn2Package getPackage() {
		return Securebpmn2Package.eINSTANCE;
	}

} //Securebpmn2FactoryImpl
