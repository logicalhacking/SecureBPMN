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

import java.io.IOException;

import java.net.URL;

import org.eclipse.bpmn2.Bpmn2Package;

import org.eclipse.bpmn2.di.BpmnDiPackage;

import org.eclipse.bpmn2.di.impl.BpmnDiPackageImpl;

import org.eclipse.bpmn2.impl.Bpmn2PackageImpl;

import org.eclipse.dd.dc.DcPackage;

import org.eclipse.dd.dc.impl.DcPackageImpl;

import org.eclipse.dd.di.DiPackage;

import org.eclipse.dd.di.impl.DiPackageImpl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import org.eclipse.securebpmn2.Securebpmn2Factory;
import org.eclipse.securebpmn2.Securebpmn2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Securebpmn2PackageImpl extends EPackageImpl implements
		Securebpmn2Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected String packageFilename = "securebpmn2.ecore";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass securityFlowNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass securityFlowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass groupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass atomicActivityActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeActivityActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass permissionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass needToKnowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass separationOfDutyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bindingOfDutyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass authorizationConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityAuthorizationConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass atomicProcessActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeProcessActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass itemAwareElementActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass atomicItemAwareElementActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeItemAwareElementActionEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Securebpmn2PackageImpl() {
		super(eNS_URI, Securebpmn2Factory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link Securebpmn2Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @generated
	 */
	public static Securebpmn2Package init() {
		if (isInited)
			return (Securebpmn2Package) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI);

		// Obtain or create and register package
		Securebpmn2PackageImpl theSecurebpmn2Package = (Securebpmn2PackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof Securebpmn2PackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new Securebpmn2PackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		Bpmn2PackageImpl theBpmn2Package = (Bpmn2PackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(Bpmn2Package.eNS_URI) instanceof Bpmn2PackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(Bpmn2Package.eNS_URI) : Bpmn2Package.eINSTANCE);
		BpmnDiPackageImpl theBpmnDiPackage = (BpmnDiPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(BpmnDiPackage.eNS_URI) instanceof BpmnDiPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(BpmnDiPackage.eNS_URI) : BpmnDiPackage.eINSTANCE);
		DiPackageImpl theDiPackage = (DiPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(DiPackage.eNS_URI) instanceof DiPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(DiPackage.eNS_URI) : DiPackage.eINSTANCE);
		DcPackageImpl theDcPackage = (DcPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(DcPackage.eNS_URI) instanceof DcPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(DcPackage.eNS_URI) : DcPackage.eINSTANCE);

		// Load packages
		theSecurebpmn2Package.loadPackage();
		theBpmn2Package.loadPackage();

		// Create package meta-data objects
		theBpmnDiPackage.createPackageContents();
		theDiPackage.createPackageContents();
		theDcPackage.createPackageContents();

		// Initialize created meta-data
		theBpmnDiPackage.initializePackageContents();
		theDiPackage.initializePackageContents();
		theDcPackage.initializePackageContents();

		// Fix loaded packages
		theSecurebpmn2Package.fixPackageContents();
		theBpmn2Package.fixPackageContents();

		// Mark meta-data to indicate it can't be changed
		theSecurebpmn2Package.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Securebpmn2Package.eNS_URI,
				theSecurebpmn2Package);
		return theSecurebpmn2Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSecurityFlowNode() {
		if (securityFlowNodeEClass == null) {
			securityFlowNodeEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(0);
		}
		return securityFlowNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSecurityFlow() {
		if (securityFlowEClass == null) {
			securityFlowEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(1);
		}
		return securityFlowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityFlow_SourceRefNode() {
		return (EReference) getSecurityFlow().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityFlow_TargetRefNode() {
		return (EReference) getSecurityFlow().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRole() {
		if (roleEClass == null) {
			roleEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(2);
		}
		return roleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRole_RoleName() {
		return (EAttribute) getRole().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRole_ParentRoles() {
		return (EReference) getRole().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRole_Subjects() {
		return (EReference) getRole().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRole_ChildRoles() {
		return (EReference) getRole().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRole_Permissions() {
		return (EReference) getRole().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSubject() {
		if (subjectEClass == null) {
			subjectEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(3);
		}
		return subjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubject_Roles() {
		return (EReference) getSubject().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubject_Groups() {
		return (EReference) getSubject().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUser() {
		if (userEClass == null) {
			userEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(4);
		}
		return userEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUser_UserName() {
		return (EAttribute) getUser().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGroup() {
		if (groupEClass == null) {
			groupEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(5);
		}
		return groupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroup_GroupName() {
		return (EAttribute) getGroup().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGroup_Subjects() {
		return (EReference) getGroup().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAction() {
		if (actionEClass == null) {
			actionEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(6);
		}
		return actionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAction_ActionName() {
		return (EAttribute) getAction().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAction_Permissions() {
		return (EReference) getAction().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivityAction() {
		if (activityActionEClass == null) {
			activityActionEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(7);
		}
		return activityActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityAction_CompositeActivityActions() {
		return (EReference) getActivityAction().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityAction_Activity() {
		return (EReference) getActivityAction().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAtomicActivityAction() {
		if (atomicActivityActionEClass == null) {
			atomicActivityActionEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(8);
		}
		return atomicActivityActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeActivityAction() {
		if (compositeActivityActionEClass == null) {
			compositeActivityActionEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(9);
		}
		return compositeActivityActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeActivityAction_ActivityActions() {
		return (EReference) getCompositeActivityAction()
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPermission() {
		if (permissionEClass == null) {
			permissionEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(10);
		}
		return permissionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPermission_Roles() {
		return (EReference) getPermission().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPermission_Actions() {
		return (EReference) getPermission().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPermission_AuthorizationConstraints() {
		return (EReference) getPermission().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPermission_PName() {
		return (EAttribute) getPermission().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNeedToKnow() {
		if (needToKnowEClass == null) {
			needToKnowEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(11);
		}
		return needToKnowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSeparationOfDuty() {
		if (separationOfDutyEClass == null) {
			separationOfDutyEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(12);
		}
		return separationOfDutyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSeparationOfDuty_MinimumUsers() {
		return (EAttribute) getSeparationOfDuty().getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSeparationOfDuty_MaxUserActionsPermitted() {
		return (EAttribute) getSeparationOfDuty().getEStructuralFeatures().get(
				1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBindingOfDuty() {
		if (bindingOfDutyEClass == null) {
			bindingOfDutyEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(13);
		}
		return bindingOfDutyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBindingOfDuty_MaxUsers() {
		return (EAttribute) getBindingOfDuty().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBindingOfDuty_SameUserActionCount() {
		return (EAttribute) getBindingOfDuty().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAuthorizationConstraint() {
		if (authorizationConstraintEClass == null) {
			authorizationConstraintEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(14);
		}
		return authorizationConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAuthorizationConstraint_ConstraintName() {
		return (EAttribute) getAuthorizationConstraint()
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAuthorizationConstraint_Expression() {
		return (EAttribute) getAuthorizationConstraint()
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAuthorizationConstraint_Permissions() {
		return (EReference) getAuthorizationConstraint()
				.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAuthorizationConstraint_DynamicEnforcement() {
		return (EAttribute) getAuthorizationConstraint()
				.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivityAuthorizationConstraint() {
		if (activityAuthorizationConstraintEClass == null) {
			activityAuthorizationConstraintEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(15);
		}
		return activityAuthorizationConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityAuthorizationConstraint_Activities() {
		return (EReference) getActivityAuthorizationConstraint()
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessAction() {
		if (processActionEClass == null) {
			processActionEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(16);
		}
		return processActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessAction_CompositeProcessActions() {
		return (EReference) getProcessAction().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessAction_Process() {
		return (EReference) getProcessAction().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAtomicProcessAction() {
		if (atomicProcessActionEClass == null) {
			atomicProcessActionEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(17);
		}
		return atomicProcessActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeProcessAction() {
		if (compositeProcessActionEClass == null) {
			compositeProcessActionEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(18);
		}
		return compositeProcessActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeProcessAction_ProcessActions() {
		return (EReference) getCompositeProcessAction()
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getItemAwareElementAction() {
		if (itemAwareElementActionEClass == null) {
			itemAwareElementActionEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(19);
		}
		return itemAwareElementActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getItemAwareElementAction_CompositeItemAwareElementActions() {
		return (EReference) getItemAwareElementAction()
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getItemAwareElementAction_ItemAwareElement() {
		return (EReference) getItemAwareElementAction()
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAtomicItemAwareElementAction() {
		if (atomicItemAwareElementActionEClass == null) {
			atomicItemAwareElementActionEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(20);
		}
		return atomicItemAwareElementActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeItemAwareElementAction() {
		if (compositeItemAwareElementActionEClass == null) {
			compositeItemAwareElementActionEClass = (EClass) EPackage.Registry.INSTANCE
					.getEPackage(Securebpmn2Package.eNS_URI).getEClassifiers()
					.get(21);
		}
		return compositeItemAwareElementActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeItemAwareElementAction_ItemAwareElementActions() {
		return (EReference) getCompositeItemAwareElementAction()
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Securebpmn2Factory getSecurebpmn2Factory() {
		return (Securebpmn2Factory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isLoaded = false;

	/**
	 * Laods the package and any sub-packages from their serialized form.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void loadPackage() {
		if (isLoaded)
			return;
		isLoaded = true;

		URL url = getClass().getResource(packageFilename);
		if (url == null) {
			throw new RuntimeException("Missing serialized package: "
					+ packageFilename);
		}
		URI uri = URI.createURI(url.toString());
		Resource resource = new EcoreResourceFactoryImpl().createResource(uri);
		try {
			resource.load(null);
		} catch (IOException exception) {
			throw new WrappedException(exception);
		}
		initializeFromLoadedEPackage(this, (EPackage) resource.getContents()
				.get(0));
		createResource(eNS_URI);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isFixed = false;

	/**
	 * Fixes up the loaded package, to make it appear as if it had been programmatically built.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fixPackageContents() {
		if (isFixed)
			return;
		isFixed = true;
		fixEClassifiers();
	}

	/**
	 * Sets the instance class on the given classifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void fixInstanceClass(EClassifier eClassifier) {
		if (eClassifier.getInstanceClassName() == null) {
			eClassifier.setInstanceClassName("org.eclipse.securebpmn2."
					+ eClassifier.getName());
			setGeneratedClassName(eClassifier);
		}
	}

} //Securebpmn2PackageImpl
