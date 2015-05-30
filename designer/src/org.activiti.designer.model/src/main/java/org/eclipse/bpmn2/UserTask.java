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
 * A representation of the model object '<em><b>User Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.UserTask#getRenderings <em>Renderings</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.UserTask#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.UserTask#getAssignee <em>Assignee</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.UserTask#getCandidateUsers <em>Candidate Users</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.UserTask#getCandidateGroups <em>Candidate Groups</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.UserTask#getFormKey <em>Form Key</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.UserTask#getFormProperties <em>Form Properties</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.UserTask#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.UserTask#getDueDate <em>Due Date</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getUserTask()
 * @model extendedMetaData="name='tUserTask' kind='elementOnly'"
 * @generated
 */
public interface UserTask extends Task {
	/**
	 * Returns the value of the '<em><b>Renderings</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.Rendering}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Renderings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Renderings</em>' containment reference list.
	 * @see org.eclipse.bpmn2.Bpmn2Package#getUserTask_Renderings()
	 * @model containment="true" ordered="false"
	 *        extendedMetaData="kind='element' name='rendering' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
	 * @generated
	 */
	List<Rendering> getRenderings();

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
	 * @see org.eclipse.bpmn2.Bpmn2Package#getUserTask_Implementation()
	 * @model required="true" ordered="false"
	 *        extendedMetaData="kind='attribute' name='implementation'"
	 * @generated
	 */
	String getImplementation();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.UserTask#getImplementation <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' attribute.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(String value);

	/**
	 * Returns the value of the '<em><b>Assignee</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assignee</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assignee</em>' attribute.
	 * @see #setAssignee(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getUserTask_Assignee()
	 * @model
	 * @generated
	 */
	String getAssignee();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.UserTask#getAssignee <em>Assignee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assignee</em>' attribute.
	 * @see #getAssignee()
	 * @generated
	 */
	void setAssignee(String value);

	/**
	 * Returns the value of the '<em><b>Candidate Users</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.CandidateUser}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Candidate Users</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Candidate Users</em>' reference list.
	 * @see org.eclipse.bpmn2.Bpmn2Package#getUserTask_CandidateUsers()
	 * @model
	 * @generated
	 */
	List<CandidateUser> getCandidateUsers();

	/**
	 * Returns the value of the '<em><b>Candidate Groups</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.CandidateGroup}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Candidate Groups</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Candidate Groups</em>' reference list.
	 * @see org.eclipse.bpmn2.Bpmn2Package#getUserTask_CandidateGroups()
	 * @model
	 * @generated
	 */
	List<CandidateGroup> getCandidateGroups();

	/**
	 * Returns the value of the '<em><b>Form Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Form Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Form Key</em>' attribute.
	 * @see #setFormKey(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getUserTask_FormKey()
	 * @model
	 * @generated
	 */
	String getFormKey();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.UserTask#getFormKey <em>Form Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Form Key</em>' attribute.
	 * @see #getFormKey()
	 * @generated
	 */
	void setFormKey(String value);

	/**
	 * Returns the value of the '<em><b>Form Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.FormProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Form Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Form Properties</em>' containment reference list.
	 * @see org.eclipse.bpmn2.Bpmn2Package#getUserTask_FormProperties()
	 * @model containment="true"
	 * @generated
	 */
	List<FormProperty> getFormProperties();

	/**
	 * Returns the value of the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority</em>' attribute.
	 * @see #setPriority(Integer)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getUserTask_Priority()
	 * @model
	 * @generated
	 */
	Integer getPriority();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.UserTask#getPriority <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' attribute.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(Integer value);

	/**
	 * Returns the value of the '<em><b>Due Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Due Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Due Date</em>' attribute.
	 * @see #setDueDate(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getUserTask_DueDate()
	 * @model
	 * @generated
	 */
	String getDueDate();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.UserTask#getDueDate <em>Due Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Due Date</em>' attribute.
	 * @see #getDueDate()
	 * @generated
	 */
	void setDueDate(String value);

} // UserTask
