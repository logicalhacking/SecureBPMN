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
 * A representation of the model object '<em><b>Activiti Listener</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.ActivitiListener#getImplementationType <em>Implementation Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ActivitiListener#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ActivitiListener#getEvent <em>Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ActivitiListener#getFieldExtensions <em>Field Extensions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ActivitiListener#getRunAs <em>Run As</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ActivitiListener#getScriptProcessor <em>Script Processor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getActivitiListener()
 * @model
 * @generated
 */
public interface ActivitiListener extends BaseElement {
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
	 * @see org.eclipse.bpmn2.Bpmn2Package#getActivitiListener_ImplementationType()
	 * @model
	 * @generated
	 */
	String getImplementationType();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.ActivitiListener#getImplementationType <em>Implementation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation Type</em>' attribute.
	 * @see #getImplementationType()
	 * @generated
	 */
	void setImplementationType(String value);

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
	 * @see org.eclipse.bpmn2.Bpmn2Package#getActivitiListener_Implementation()
	 * @model
	 * @generated
	 */
	String getImplementation();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.ActivitiListener#getImplementation <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' attribute.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(String value);

	/**
	 * Returns the value of the '<em><b>Event</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' attribute.
	 * @see #setEvent(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getActivitiListener_Event()
	 * @model
	 * @generated
	 */
	String getEvent();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.ActivitiListener#getEvent <em>Event</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' attribute.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(String value);

	/**
	 * Returns the value of the '<em><b>Field Extensions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.FieldExtension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Extensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Extensions</em>' containment reference list.
	 * @see org.eclipse.bpmn2.Bpmn2Package#getActivitiListener_FieldExtensions()
	 * @model containment="true"
	 * @generated
	 */
	List<FieldExtension> getFieldExtensions();

	/**
	 * Returns the value of the '<em><b>Run As</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Run As</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Run As</em>' attribute.
	 * @see #setRunAs(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getActivitiListener_RunAs()
	 * @model
	 * @generated
	 */
	String getRunAs();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.ActivitiListener#getRunAs <em>Run As</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Run As</em>' attribute.
	 * @see #getRunAs()
	 * @generated
	 */
	void setRunAs(String value);

	/**
	 * Returns the value of the '<em><b>Script Processor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script Processor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script Processor</em>' attribute.
	 * @see #setScriptProcessor(String)
	 * @see org.eclipse.bpmn2.Bpmn2Package#getActivitiListener_ScriptProcessor()
	 * @model
	 * @generated
	 */
	String getScriptProcessor();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.ActivitiListener#getScriptProcessor <em>Script Processor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script Processor</em>' attribute.
	 * @see #getScriptProcessor()
	 * @generated
	 */
	void setScriptProcessor(String value);

} // ActivitiListener
