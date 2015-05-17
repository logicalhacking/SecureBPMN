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

import java.util.List;

import org.eclipse.bpmn2.BaseElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Subject</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.Subject#getRoles <em>Roles</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.Subject#getGroups <em>Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.securebpmn2.Securebpmn2Package#getSubject()
 * @model abstract="true"
 * @generated
 */
public interface Subject extends BaseElement {
	/**
	 * Returns the value of the '<em><b>Roles</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.Role}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.Role#getSubjects <em>Subjects</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roles</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getSubject_Roles()
	 * @see org.eclipse.securebpmn2.Role#getSubjects
	 * @model opposite="subjects" transient="true"
	 * @generated
	 */
	List<Role> getRoles();

	/**
	 * Returns the value of the '<em><b>Groups</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.Group}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.Group#getSubjects <em>Subjects</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groups</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groups</em>' reference list.
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getSubject_Groups()
	 * @see org.eclipse.securebpmn2.Group#getSubjects
	 * @model opposite="subjects"
	 * @generated
	 */
	List<Group> getGroups();

} // Subject
