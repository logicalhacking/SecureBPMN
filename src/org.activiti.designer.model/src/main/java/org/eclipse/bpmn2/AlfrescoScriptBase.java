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
 * A representation of the model object '<em><b>Alfresco Script Base</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.AlfrescoScriptBase#getRunAs <em>Run As</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.AlfrescoScriptBase#getScriptProcessor <em>Script Processor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getAlfrescoScriptBase()
 * @model
 * @generated
 */
public interface AlfrescoScriptBase extends BaseElement {
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
	 * @see org.eclipse.bpmn2.Bpmn2Package#getAlfrescoScriptBase_RunAs()
	 * @model
	 * @generated
	 */
	String getRunAs();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.AlfrescoScriptBase#getRunAs <em>Run As</em>}' attribute.
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
	 * @see org.eclipse.bpmn2.Bpmn2Package#getAlfrescoScriptBase_ScriptProcessor()
	 * @model
	 * @generated
	 */
	String getScriptProcessor();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.AlfrescoScriptBase#getScriptProcessor <em>Script Processor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script Processor</em>' attribute.
	 * @see #getScriptProcessor()
	 * @generated
	 */
	void setScriptProcessor(String value);

} // AlfrescoScriptBase
