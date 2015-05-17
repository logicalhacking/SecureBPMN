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
package org.eclipse.bpmn2.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.CustomProperty;
import org.eclipse.bpmn2.FieldExtension;
import org.eclipse.bpmn2.Operation;
import org.eclipse.bpmn2.ServiceTask;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.ServiceTaskImpl#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ServiceTaskImpl#getOperationRef <em>Operation Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ServiceTaskImpl#getCustomProperties <em>Custom Properties</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ServiceTaskImpl#getImplementationType <em>Implementation Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ServiceTaskImpl#getFieldExtensions <em>Field Extensions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ServiceTaskImpl#getResultVariableName <em>Result Variable Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceTaskImpl extends TaskImpl implements ServiceTask {
	/**
	 * The default value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementation()
	 * @generated
	 * @ordered
	 */
	protected static final String IMPLEMENTATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementation()
	 * @generated
	 * @ordered
	 */
	protected String implementation = IMPLEMENTATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOperationRef() <em>Operation Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationRef()
	 * @generated
	 * @ordered
	 */
	protected Operation operationRef;

	/**
	 * The cached value of the '{@link #getCustomProperties() <em>Custom Properties</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<CustomProperty> customProperties;

	/**
	 * The default value of the '{@link #getImplementationType() <em>Implementation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationType()
	 * @generated
	 * @ordered
	 */
	protected static final String IMPLEMENTATION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImplementationType() <em>Implementation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationType()
	 * @generated
	 * @ordered
	 */
	protected String implementationType = IMPLEMENTATION_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFieldExtensions() <em>Field Extensions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList<FieldExtension> fieldExtensions;

	/**
	 * The default value of the '{@link #getResultVariableName() <em>Result Variable Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultVariableName()
	 * @generated
	 * @ordered
	 */
	protected static final String RESULT_VARIABLE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResultVariableName() <em>Result Variable Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultVariableName()
	 * @generated
	 * @ordered
	 */
	protected String resultVariableName = RESULT_VARIABLE_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Bpmn2Package.Literals.SERVICE_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImplementation() {
		return implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementation(String newImplementation) {
		String oldImplementation = implementation;
		implementation = newImplementation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.SERVICE_TASK__IMPLEMENTATION,
					oldImplementation, implementation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getOperationRef() {
		if (operationRef != null && operationRef.eIsProxy()) {
			InternalEObject oldOperationRef = (InternalEObject) operationRef;
			operationRef = (Operation) eResolveProxy(oldOperationRef);
			if (operationRef != oldOperationRef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							Bpmn2Package.SERVICE_TASK__OPERATION_REF,
							oldOperationRef, operationRef));
			}
		}
		return operationRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation basicGetOperationRef() {
		return operationRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperationRef(Operation newOperationRef) {
		Operation oldOperationRef = operationRef;
		operationRef = newOperationRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.SERVICE_TASK__OPERATION_REF, oldOperationRef,
					operationRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<CustomProperty> getCustomProperties() {
		if (customProperties == null) {
			customProperties = new EObjectResolvingEList<CustomProperty>(
					CustomProperty.class, this,
					Bpmn2Package.SERVICE_TASK__CUSTOM_PROPERTIES);
		}
		return customProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImplementationType() {
		return implementationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementationType(String newImplementationType) {
		String oldImplementationType = implementationType;
		implementationType = newImplementationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.SERVICE_TASK__IMPLEMENTATION_TYPE,
					oldImplementationType, implementationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<FieldExtension> getFieldExtensions() {
		if (fieldExtensions == null) {
			fieldExtensions = new EObjectResolvingEList<FieldExtension>(
					FieldExtension.class, this,
					Bpmn2Package.SERVICE_TASK__FIELD_EXTENSIONS);
		}
		return fieldExtensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResultVariableName() {
		return resultVariableName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultVariableName(String newResultVariableName) {
		String oldResultVariableName = resultVariableName;
		resultVariableName = newResultVariableName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.SERVICE_TASK__RESULT_VARIABLE_NAME,
					oldResultVariableName, resultVariableName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Bpmn2Package.SERVICE_TASK__IMPLEMENTATION:
			return getImplementation();
		case Bpmn2Package.SERVICE_TASK__OPERATION_REF:
			if (resolve)
				return getOperationRef();
			return basicGetOperationRef();
		case Bpmn2Package.SERVICE_TASK__CUSTOM_PROPERTIES:
			return getCustomProperties();
		case Bpmn2Package.SERVICE_TASK__IMPLEMENTATION_TYPE:
			return getImplementationType();
		case Bpmn2Package.SERVICE_TASK__FIELD_EXTENSIONS:
			return getFieldExtensions();
		case Bpmn2Package.SERVICE_TASK__RESULT_VARIABLE_NAME:
			return getResultVariableName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case Bpmn2Package.SERVICE_TASK__IMPLEMENTATION:
			setImplementation((String) newValue);
			return;
		case Bpmn2Package.SERVICE_TASK__OPERATION_REF:
			setOperationRef((Operation) newValue);
			return;
		case Bpmn2Package.SERVICE_TASK__CUSTOM_PROPERTIES:
			getCustomProperties().clear();
			getCustomProperties().addAll(
					(Collection<? extends CustomProperty>) newValue);
			return;
		case Bpmn2Package.SERVICE_TASK__IMPLEMENTATION_TYPE:
			setImplementationType((String) newValue);
			return;
		case Bpmn2Package.SERVICE_TASK__FIELD_EXTENSIONS:
			getFieldExtensions().clear();
			getFieldExtensions().addAll(
					(Collection<? extends FieldExtension>) newValue);
			return;
		case Bpmn2Package.SERVICE_TASK__RESULT_VARIABLE_NAME:
			setResultVariableName((String) newValue);
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
		case Bpmn2Package.SERVICE_TASK__IMPLEMENTATION:
			setImplementation(IMPLEMENTATION_EDEFAULT);
			return;
		case Bpmn2Package.SERVICE_TASK__OPERATION_REF:
			setOperationRef((Operation) null);
			return;
		case Bpmn2Package.SERVICE_TASK__CUSTOM_PROPERTIES:
			getCustomProperties().clear();
			return;
		case Bpmn2Package.SERVICE_TASK__IMPLEMENTATION_TYPE:
			setImplementationType(IMPLEMENTATION_TYPE_EDEFAULT);
			return;
		case Bpmn2Package.SERVICE_TASK__FIELD_EXTENSIONS:
			getFieldExtensions().clear();
			return;
		case Bpmn2Package.SERVICE_TASK__RESULT_VARIABLE_NAME:
			setResultVariableName(RESULT_VARIABLE_NAME_EDEFAULT);
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
		case Bpmn2Package.SERVICE_TASK__IMPLEMENTATION:
			return IMPLEMENTATION_EDEFAULT == null ? implementation != null
					: !IMPLEMENTATION_EDEFAULT.equals(implementation);
		case Bpmn2Package.SERVICE_TASK__OPERATION_REF:
			return operationRef != null;
		case Bpmn2Package.SERVICE_TASK__CUSTOM_PROPERTIES:
			return customProperties != null && !customProperties.isEmpty();
		case Bpmn2Package.SERVICE_TASK__IMPLEMENTATION_TYPE:
			return IMPLEMENTATION_TYPE_EDEFAULT == null ? implementationType != null
					: !IMPLEMENTATION_TYPE_EDEFAULT.equals(implementationType);
		case Bpmn2Package.SERVICE_TASK__FIELD_EXTENSIONS:
			return fieldExtensions != null && !fieldExtensions.isEmpty();
		case Bpmn2Package.SERVICE_TASK__RESULT_VARIABLE_NAME:
			return RESULT_VARIABLE_NAME_EDEFAULT == null ? resultVariableName != null
					: !RESULT_VARIABLE_NAME_EDEFAULT.equals(resultVariableName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (implementation: ");
		result.append(implementation);
		result.append(", implementationType: ");
		result.append(implementationType);
		result.append(", resultVariableName: ");
		result.append(resultVariableName);
		result.append(')');
		return result.toString();
	}

} //ServiceTaskImpl
