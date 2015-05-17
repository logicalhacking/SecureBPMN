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

import org.eclipse.bpmn2.AlfrescoScriptBase;
import org.eclipse.bpmn2.AlfrescoScriptTask;
import org.eclipse.bpmn2.Bpmn2Package;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Alfresco Script Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.AlfrescoScriptTaskImpl#getRunAs <em>Run As</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.AlfrescoScriptTaskImpl#getScriptProcessor <em>Script Processor</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.AlfrescoScriptTaskImpl#getScript <em>Script</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AlfrescoScriptTaskImpl extends TaskImpl implements
		AlfrescoScriptTask {
	/**
	 * The default value of the '{@link #getRunAs() <em>Run As</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRunAs()
	 * @generated
	 * @ordered
	 */
	protected static final String RUN_AS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRunAs() <em>Run As</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRunAs()
	 * @generated
	 * @ordered
	 */
	protected String runAs = RUN_AS_EDEFAULT;

	/**
	 * The default value of the '{@link #getScriptProcessor() <em>Script Processor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScriptProcessor()
	 * @generated
	 * @ordered
	 */
	protected static final String SCRIPT_PROCESSOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScriptProcessor() <em>Script Processor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScriptProcessor()
	 * @generated
	 * @ordered
	 */
	protected String scriptProcessor = SCRIPT_PROCESSOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getScript() <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected static final String SCRIPT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScript() <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected String script = SCRIPT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AlfrescoScriptTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Bpmn2Package.Literals.ALFRESCO_SCRIPT_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRunAs() {
		return runAs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRunAs(String newRunAs) {
		String oldRunAs = runAs;
		runAs = newRunAs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.ALFRESCO_SCRIPT_TASK__RUN_AS, oldRunAs, runAs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getScriptProcessor() {
		return scriptProcessor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScriptProcessor(String newScriptProcessor) {
		String oldScriptProcessor = scriptProcessor;
		scriptProcessor = newScriptProcessor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.ALFRESCO_SCRIPT_TASK__SCRIPT_PROCESSOR,
					oldScriptProcessor, scriptProcessor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getScript() {
		return script;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScript(String newScript) {
		String oldScript = script;
		script = newScript;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.ALFRESCO_SCRIPT_TASK__SCRIPT, oldScript,
					script));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Bpmn2Package.ALFRESCO_SCRIPT_TASK__RUN_AS:
			return getRunAs();
		case Bpmn2Package.ALFRESCO_SCRIPT_TASK__SCRIPT_PROCESSOR:
			return getScriptProcessor();
		case Bpmn2Package.ALFRESCO_SCRIPT_TASK__SCRIPT:
			return getScript();
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
		case Bpmn2Package.ALFRESCO_SCRIPT_TASK__RUN_AS:
			setRunAs((String) newValue);
			return;
		case Bpmn2Package.ALFRESCO_SCRIPT_TASK__SCRIPT_PROCESSOR:
			setScriptProcessor((String) newValue);
			return;
		case Bpmn2Package.ALFRESCO_SCRIPT_TASK__SCRIPT:
			setScript((String) newValue);
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
		case Bpmn2Package.ALFRESCO_SCRIPT_TASK__RUN_AS:
			setRunAs(RUN_AS_EDEFAULT);
			return;
		case Bpmn2Package.ALFRESCO_SCRIPT_TASK__SCRIPT_PROCESSOR:
			setScriptProcessor(SCRIPT_PROCESSOR_EDEFAULT);
			return;
		case Bpmn2Package.ALFRESCO_SCRIPT_TASK__SCRIPT:
			setScript(SCRIPT_EDEFAULT);
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
		case Bpmn2Package.ALFRESCO_SCRIPT_TASK__RUN_AS:
			return RUN_AS_EDEFAULT == null ? runAs != null : !RUN_AS_EDEFAULT
					.equals(runAs);
		case Bpmn2Package.ALFRESCO_SCRIPT_TASK__SCRIPT_PROCESSOR:
			return SCRIPT_PROCESSOR_EDEFAULT == null ? scriptProcessor != null
					: !SCRIPT_PROCESSOR_EDEFAULT.equals(scriptProcessor);
		case Bpmn2Package.ALFRESCO_SCRIPT_TASK__SCRIPT:
			return SCRIPT_EDEFAULT == null ? script != null : !SCRIPT_EDEFAULT
					.equals(script);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == AlfrescoScriptBase.class) {
			switch (derivedFeatureID) {
			case Bpmn2Package.ALFRESCO_SCRIPT_TASK__RUN_AS:
				return Bpmn2Package.ALFRESCO_SCRIPT_BASE__RUN_AS;
			case Bpmn2Package.ALFRESCO_SCRIPT_TASK__SCRIPT_PROCESSOR:
				return Bpmn2Package.ALFRESCO_SCRIPT_BASE__SCRIPT_PROCESSOR;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == AlfrescoScriptBase.class) {
			switch (baseFeatureID) {
			case Bpmn2Package.ALFRESCO_SCRIPT_BASE__RUN_AS:
				return Bpmn2Package.ALFRESCO_SCRIPT_TASK__RUN_AS;
			case Bpmn2Package.ALFRESCO_SCRIPT_BASE__SCRIPT_PROCESSOR:
				return Bpmn2Package.ALFRESCO_SCRIPT_TASK__SCRIPT_PROCESSOR;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (runAs: ");
		result.append(runAs);
		result.append(", scriptProcessor: ");
		result.append(scriptProcessor);
		result.append(", script: ");
		result.append(script);
		result.append(')');
		return result.toString();
	}

} //AlfrescoScriptTaskImpl
