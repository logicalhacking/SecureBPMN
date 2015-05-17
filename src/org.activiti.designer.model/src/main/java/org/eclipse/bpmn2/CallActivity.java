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
 * A representation of the model object '<em><b>Call Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.CallActivity#getCalledElementRef <em>Called Element Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.CallActivity#getCalledElement <em>Called Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.CallActivity#getInParameters <em>In Parameters</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.CallActivity#getOutParameters <em>Out Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getCallActivity()
 * @model extendedMetaData="name='tCallActivity' kind='elementOnly'"
 * @generated
 */
public interface CallActivity extends Activity {
	/**
	 * Returns the value of the '<em><b>Called Element Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Element Ref</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Called Element Ref</em>' reference.
	 * @see #setCalledElementRef(CallableElement)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getCallActivity_CalledElementRef()
	 * @model ordered="false"
	 *        extendedMetaData="kind='attribute' name='calledElement'"
	 * @generated
	 */
	CallableElement getCalledElementRef();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.CallActivity#getCalledElementRef <em>Called Element Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Called Element Ref</em>' reference.
	 * @see #getCalledElementRef()
	 * @generated
	 */
	void setCalledElementRef(CallableElement value);

	/**
	 * Returns the value of the '<em><b>Called Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Called Element</em>' attribute.
	 * @see #setCalledElement(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getCallActivity_CalledElement()
	 * @model
	 * @generated
	 */
	String getCalledElement();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.CallActivity#getCalledElement <em>Called Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Called Element</em>' attribute.
	 * @see #getCalledElement()
	 * @generated
	 */
	void setCalledElement(String value);

	/**
	 * Returns the value of the '<em><b>In Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.IOParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Parameters</em>' containment reference list.
	 * @see org.eclipse.bpmn2.Bpmn2Package#getCallActivity_InParameters()
	 * @model containment="true"
	 * @generated
	 */
	List<IOParameter> getInParameters();

	/**
	 * Returns the value of the '<em><b>Out Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.IOParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Parameters</em>' containment reference list.
	 * @see org.eclipse.bpmn2.Bpmn2Package#getCallActivity_OutParameters()
	 * @model containment="true"
	 * @generated
	 */
	List<IOParameter> getOutParameters();

} // CallActivity
