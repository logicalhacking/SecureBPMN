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
 * A representation of the model object '<em><b>Form Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.FormValue#getValueId <em>Value Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.FormValue#getValueName <em>Value Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getFormValue()
 * @model
 * @generated
 */
public interface FormValue extends BaseElement {
	/**
	 * Returns the value of the '<em><b>Value Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Id</em>' attribute.
	 * @see #setValueId(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getFormValue_ValueId()
	 * @model
	 * @generated
	 */
	String getValueId();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.FormValue#getValueId <em>Value Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Id</em>' attribute.
	 * @see #getValueId()
	 * @generated
	 */
	void setValueId(String value);

	/**
	 * Returns the value of the '<em><b>Value Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Name</em>' attribute.
	 * @see #setValueName(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getFormValue_ValueName()
	 * @model
	 * @generated
	 */
	String getValueName();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.FormValue#getValueName <em>Value Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Name</em>' attribute.
	 * @see #getValueName()
	 * @generated
	 */
	void setValueName(String value);

} // FormValue
