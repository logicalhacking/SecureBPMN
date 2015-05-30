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

import org.eclipse.securebpmn2.ItemAwareElementAction;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Item Aware Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#getDataState <em>Data State</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#getItemSubjectRef <em>Item Subject Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#getItemAwareElementActions <em>Item Aware Element Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement()
 * @model
 * @generated
 */
public interface ItemAwareElement extends BaseElement {
	/**
	 * Returns the value of the '<em><b>Data State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data State</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data State</em>' containment reference.
	 * @see #setDataState(DataState)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_DataState()
	 * @model containment="true" ordered="false"
	 *        extendedMetaData="kind='element' name='dataState' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
	 * @generated
	 */
	DataState getDataState();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.ItemAwareElement#getDataState <em>Data State</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data State</em>' containment reference.
	 * @see #getDataState()
	 * @generated
	 */
	void setDataState(DataState value);

	/**
	 * Returns the value of the '<em><b>Item Subject Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Item Subject Ref</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Item Subject Ref</em>' reference.
	 * @see #setItemSubjectRef(ItemDefinition)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_ItemSubjectRef()
	 * @model ordered="false"
	 *        extendedMetaData="kind='attribute' name='itemSubjectRef'"
	 * @generated
	 */
	ItemDefinition getItemSubjectRef();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.ItemAwareElement#getItemSubjectRef <em>Item Subject Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Item Subject Ref</em>' reference.
	 * @see #getItemSubjectRef()
	 * @generated
	 */
	void setItemSubjectRef(ItemDefinition value);

	/**
	 * Returns the value of the '<em><b>Item Aware Element Actions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.securebpmn2.ItemAwareElementAction}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.securebpmn2.ItemAwareElementAction#getItemAwareElement <em>Item Aware Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Item Aware Element Actions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Item Aware Element Actions</em>' containment reference list.
	 * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_ItemAwareElementActions()
	 * @see org.eclipse.securebpmn2.ItemAwareElementAction#getItemAwareElement
	 * @model opposite="itemAwareElement" containment="true"
	 * @generated
	 */
	List<ItemAwareElementAction> getItemAwareElementActions();

} // ItemAwareElement
