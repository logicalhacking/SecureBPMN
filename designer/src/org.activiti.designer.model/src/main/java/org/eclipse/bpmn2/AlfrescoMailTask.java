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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Alfresco Mail Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.AlfrescoMailTask#getToMany <em>To Many</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.AlfrescoMailTask#getTemplate <em>Template</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.AlfrescoMailTask#getTemplateModel <em>Template Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getAlfrescoMailTask()
 * @model
 * @generated
 */
public interface AlfrescoMailTask extends MailTask {
	/**
	 * Returns the value of the '<em><b>To Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Many</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Many</em>' attribute.
	 * @see #setToMany(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getAlfrescoMailTask_ToMany()
	 * @model
	 * @generated
	 */
	String getToMany();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.AlfrescoMailTask#getToMany <em>To Many</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Many</em>' attribute.
	 * @see #getToMany()
	 * @generated
	 */
	void setToMany(String value);

	/**
	 * Returns the value of the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Template</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template</em>' attribute.
	 * @see #setTemplate(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getAlfrescoMailTask_Template()
	 * @model
	 * @generated
	 */
	String getTemplate();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.AlfrescoMailTask#getTemplate <em>Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Template</em>' attribute.
	 * @see #getTemplate()
	 * @generated
	 */
	void setTemplate(String value);

	/**
	 * Returns the value of the '<em><b>Template Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Template Model</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template Model</em>' attribute.
	 * @see #setTemplateModel(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getAlfrescoMailTask_TemplateModel()
	 * @model
	 * @generated
	 */
	String getTemplateModel();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.AlfrescoMailTask#getTemplateModel <em>Template Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Template Model</em>' attribute.
	 * @see #getTemplateModel()
	 * @generated
	 */
	void setTemplateModel(String value);

} // AlfrescoMailTask
