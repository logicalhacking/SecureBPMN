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
package org.eclipse.bpmn2.impl;

import org.eclipse.bpmn2.AlfrescoMailTask;
import org.eclipse.bpmn2.Bpmn2Package;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Alfresco Mail Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.AlfrescoMailTaskImpl#getToMany <em>To Many</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.AlfrescoMailTaskImpl#getTemplate <em>Template</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.AlfrescoMailTaskImpl#getTemplateModel <em>Template Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AlfrescoMailTaskImpl extends MailTaskImpl implements
		AlfrescoMailTask {
	/**
	 * The default value of the '{@link #getToMany() <em>To Many</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToMany()
	 * @generated
	 * @ordered
	 */
	protected static final String TO_MANY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getToMany() <em>To Many</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToMany()
	 * @generated
	 * @ordered
	 */
	protected String toMany = TO_MANY_EDEFAULT;

	/**
	 * The default value of the '{@link #getTemplate() <em>Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplate()
	 * @generated
	 * @ordered
	 */
	protected static final String TEMPLATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTemplate() <em>Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplate()
	 * @generated
	 * @ordered
	 */
	protected String template = TEMPLATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTemplateModel() <em>Template Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplateModel()
	 * @generated
	 * @ordered
	 */
	protected static final String TEMPLATE_MODEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTemplateModel() <em>Template Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplateModel()
	 * @generated
	 * @ordered
	 */
	protected String templateModel = TEMPLATE_MODEL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AlfrescoMailTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Bpmn2Package.Literals.ALFRESCO_MAIL_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getToMany() {
		return toMany;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setToMany(String newToMany) {
		String oldToMany = toMany;
		toMany = newToMany;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.ALFRESCO_MAIL_TASK__TO_MANY, oldToMany, toMany));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTemplate(String newTemplate) {
		String oldTemplate = template;
		template = newTemplate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.ALFRESCO_MAIL_TASK__TEMPLATE, oldTemplate,
					template));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTemplateModel() {
		return templateModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTemplateModel(String newTemplateModel) {
		String oldTemplateModel = templateModel;
		templateModel = newTemplateModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.ALFRESCO_MAIL_TASK__TEMPLATE_MODEL,
					oldTemplateModel, templateModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Bpmn2Package.ALFRESCO_MAIL_TASK__TO_MANY:
			return getToMany();
		case Bpmn2Package.ALFRESCO_MAIL_TASK__TEMPLATE:
			return getTemplate();
		case Bpmn2Package.ALFRESCO_MAIL_TASK__TEMPLATE_MODEL:
			return getTemplateModel();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case Bpmn2Package.ALFRESCO_MAIL_TASK__TO_MANY:
			setToMany((String) newValue);
			return;
		case Bpmn2Package.ALFRESCO_MAIL_TASK__TEMPLATE:
			setTemplate((String) newValue);
			return;
		case Bpmn2Package.ALFRESCO_MAIL_TASK__TEMPLATE_MODEL:
			setTemplateModel((String) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case Bpmn2Package.ALFRESCO_MAIL_TASK__TO_MANY:
			setToMany(TO_MANY_EDEFAULT);
			return;
		case Bpmn2Package.ALFRESCO_MAIL_TASK__TEMPLATE:
			setTemplate(TEMPLATE_EDEFAULT);
			return;
		case Bpmn2Package.ALFRESCO_MAIL_TASK__TEMPLATE_MODEL:
			setTemplateModel(TEMPLATE_MODEL_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case Bpmn2Package.ALFRESCO_MAIL_TASK__TO_MANY:
			return TO_MANY_EDEFAULT == null ? toMany != null
					: !TO_MANY_EDEFAULT.equals(toMany);
		case Bpmn2Package.ALFRESCO_MAIL_TASK__TEMPLATE:
			return TEMPLATE_EDEFAULT == null ? template != null
					: !TEMPLATE_EDEFAULT.equals(template);
		case Bpmn2Package.ALFRESCO_MAIL_TASK__TEMPLATE_MODEL:
			return TEMPLATE_MODEL_EDEFAULT == null ? templateModel != null
					: !TEMPLATE_MODEL_EDEFAULT.equals(templateModel);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (toMany: ");
		result.append(toMany);
		result.append(", template: ");
		result.append(template);
		result.append(", templateModel: ");
		result.append(templateModel);
		result.append(')');
		return result.toString();
	}

} //AlfrescoMailTaskImpl
