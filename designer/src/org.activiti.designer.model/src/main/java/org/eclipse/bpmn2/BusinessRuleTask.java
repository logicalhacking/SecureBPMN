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
 * A representation of the model object '<em><b>Business Rule Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.BusinessRuleTask#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.BusinessRuleTask#getRuleNames <em>Rule Names</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.BusinessRuleTask#getInputVariables <em>Input Variables</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.BusinessRuleTask#getResultVariableName <em>Result Variable Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.BusinessRuleTask#isExclude <em>Exclude</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getBusinessRuleTask()
 * @model extendedMetaData="name='tBusinessRuleTask' kind='elementOnly'"
 * @generated
 */
public interface BusinessRuleTask extends Task {
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
	 * @see org.eclipse.bpmn2.Bpmn2Package#getBusinessRuleTask_Implementation()
	 * @model required="true" ordered="false"
	 *        extendedMetaData="kind='attribute' name='implementation'"
	 * @generated
	 */
	String getImplementation();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.BusinessRuleTask#getImplementation <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' attribute.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(String value);

	/**
	 * Returns the value of the '<em><b>Rule Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rule Names</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rule Names</em>' attribute list.
	 * @see org.eclipse.bpmn2.Bpmn2Package#getBusinessRuleTask_RuleNames()
	 * @model
	 * @generated
	 */
	List<String> getRuleNames();

	/**
	 * Returns the value of the '<em><b>Input Variables</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Variables</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Variables</em>' attribute list.
	 * @see org.eclipse.bpmn2.Bpmn2Package#getBusinessRuleTask_InputVariables()
	 * @model
	 * @generated
	 */
	List<String> getInputVariables();

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
	 * @see org.eclipse.bpmn2.Bpmn2Package#getBusinessRuleTask_ResultVariableName()
	 * @model
	 * @generated
	 */
	String getResultVariableName();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.BusinessRuleTask#getResultVariableName <em>Result Variable Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Variable Name</em>' attribute.
	 * @see #getResultVariableName()
	 * @generated
	 */
	void setResultVariableName(String value);

	/**
	 * Returns the value of the '<em><b>Exclude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exclude</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exclude</em>' attribute.
	 * @see #setExclude(boolean)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getBusinessRuleTask_Exclude()
	 * @model
	 * @generated
	 */
	boolean isExclude();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.BusinessRuleTask#isExclude <em>Exclude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exclude</em>' attribute.
	 * @see #isExclude()
	 * @generated
	 */
	void setExclude(boolean value);

} // BusinessRuleTask
