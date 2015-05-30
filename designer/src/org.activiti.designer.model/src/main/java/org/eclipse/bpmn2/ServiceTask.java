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
package org.eclipse.bpmn2;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.ServiceTask#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ServiceTask#getOperationRef <em>Operation Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ServiceTask#getCustomProperties <em>Custom Properties</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ServiceTask#getImplementationType <em>Implementation Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ServiceTask#getFieldExtensions <em>Field Extensions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ServiceTask#getResultVariableName <em>Result Variable Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getServiceTask()
 * @model extendedMetaData="name='tServiceTask' kind='elementOnly'"
 * @generated
 */
public interface ServiceTask extends Task {
	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation</em>' attribute.
	 * @see #setImplementation(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getServiceTask_Implementation()
	 * @model required="true" ordered="false"
	 *        extendedMetaData="kind='attribute' name='implementation'"
	 * @generated
	 */
	String getImplementation();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.ServiceTask#getImplementation <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' attribute.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(String value);

	/**
	 * Returns the value of the '<em><b>Operation Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Ref</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Ref</em>' reference.
	 * @see #setOperationRef(Operation)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getServiceTask_OperationRef()
	 * @model ordered="false"
	 *        extendedMetaData="kind='attribute' name='operationRef'"
	 * @generated
	 */
	Operation getOperationRef();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.ServiceTask#getOperationRef <em>Operation Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation Ref</em>' reference.
	 * @see #getOperationRef()
	 * @generated
	 */
	void setOperationRef(Operation value);

	/**
	 * Returns the value of the '<em><b>Custom Properties</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.CustomProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Custom Properties</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Custom Properties</em>' reference list.
	 * @see org.eclipse.bpmn2.Bpmn2Package#getServiceTask_CustomProperties()
	 * @model
	 * @generated
	 */
	List<CustomProperty> getCustomProperties();

	/**
	 * Returns the value of the '<em><b>Implementation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation Type</em>' attribute.
	 * @see #setImplementationType(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getServiceTask_ImplementationType()
	 * @model
	 * @generated
	 */
	String getImplementationType();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.ServiceTask#getImplementationType <em>Implementation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation Type</em>' attribute.
	 * @see #getImplementationType()
	 * @generated
	 */
	void setImplementationType(String value);

	/**
	 * Returns the value of the '<em><b>Field Extensions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.FieldExtension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Extensions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Extensions</em>' reference list.
	 * @see org.eclipse.bpmn2.Bpmn2Package#getServiceTask_FieldExtensions()
	 * @model
	 * @generated
	 */
	List<FieldExtension> getFieldExtensions();

	/**
	 * Returns the value of the '<em><b>Result Variable Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Variable Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Variable Name</em>' attribute.
	 * @see #setResultVariableName(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getServiceTask_ResultVariableName()
	 * @model
	 * @generated
	 */
	String getResultVariableName();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.ServiceTask#getResultVariableName <em>Result Variable Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Variable Name</em>' attribute.
	 * @see #getResultVariableName()
	 * @generated
	 */
	void setResultVariableName(String value);

} // ServiceTask
