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

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Security Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.SecurityFlow#getSourceRefNode <em>Source Ref Node</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.SecurityFlow#getTargetRefNode <em>Target Ref Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.securebpmn2.Securebpmn2Package#getSecurityFlow()
 * @model
 * @generated
 */
public interface SecurityFlow extends FlowElement {
	/**
	 * Returns the value of the '<em><b>Source Ref Node</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.FlowNode#getOutgoingSecurityFlow <em>Outgoing Security Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Ref Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Ref Node</em>' reference.
	 * @see #setSourceRefNode(FlowNode)
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getSecurityFlow_SourceRefNode()
	 * @see org.eclipse.bpmn2.FlowNode#getOutgoingSecurityFlow
	 * @model opposite="outgoingSecurityFlow" resolveProxies="false" required="true" ordered="false"
	 *        extendedMetaData="kind='attribute' name='sourceRef'"
	 * @generated
	 */
	FlowNode getSourceRefNode();

	/**
	 * Sets the value of the '{@link org.eclipse.securebpmn2.SecurityFlow#getSourceRefNode <em>Source Ref Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Ref Node</em>' reference.
	 * @see #getSourceRefNode()
	 * @generated
	 */
	void setSourceRefNode(FlowNode value);

	/**
	 * Returns the value of the '<em><b>Target Ref Node</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.FlowNode#getIncomingSecurityFlow <em>Incoming Security Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Ref Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Ref Node</em>' reference.
	 * @see #setTargetRefNode(FlowNode)
	 * @see org.eclipse.securebpmn2.Securebpmn2Package#getSecurityFlow_TargetRefNode()
	 * @see org.eclipse.bpmn2.FlowNode#getIncomingSecurityFlow
	 * @model opposite="incomingSecurityFlow" resolveProxies="false" required="true" ordered="false"
	 *        extendedMetaData="kind='attribute' name='targetRef'"
	 * @generated
	 */
	FlowNode getTargetRefNode();

	/**
	 * Sets the value of the '{@link org.eclipse.securebpmn2.SecurityFlow#getTargetRefNode <em>Target Ref Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Ref Node</em>' reference.
	 * @see #getTargetRefNode()
	 * @generated
	 */
	void setTargetRefNode(FlowNode value);

} // SecurityFlow
