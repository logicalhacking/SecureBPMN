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

import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.CandidateGroup;
import org.eclipse.bpmn2.CandidateUser;
import org.eclipse.bpmn2.FormProperty;
import org.eclipse.bpmn2.Rendering;
import org.eclipse.bpmn2.UserTask;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.UserTaskImpl#getRenderings <em>Renderings</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.UserTaskImpl#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.UserTaskImpl#getAssignee <em>Assignee</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.UserTaskImpl#getCandidateUsers <em>Candidate Users</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.UserTaskImpl#getCandidateGroups <em>Candidate Groups</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.UserTaskImpl#getFormKey <em>Form Key</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.UserTaskImpl#getFormProperties <em>Form Properties</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.UserTaskImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.UserTaskImpl#getDueDate <em>Due Date</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserTaskImpl extends TaskImpl implements UserTask {
	/**
	 * The cached value of the '{@link #getRenderings() <em>Renderings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRenderings()
	 * @generated
	 * @ordered
	 */
	protected EList<Rendering> renderings;

	/**
	 * The default value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementation()
	 * @generated
	 * @ordered
	 */
	protected static final String IMPLEMENTATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementation()
	 * @generated
	 * @ordered
	 */
	protected String implementation = IMPLEMENTATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getAssignee() <em>Assignee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignee()
	 * @generated
	 * @ordered
	 */
	protected static final String ASSIGNEE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAssignee() <em>Assignee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignee()
	 * @generated
	 * @ordered
	 */
	protected String assignee = ASSIGNEE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCandidateUsers() <em>Candidate Users</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCandidateUsers()
	 * @generated
	 * @ordered
	 */
	protected EList<CandidateUser> candidateUsers;

	/**
	 * The cached value of the '{@link #getCandidateGroups() <em>Candidate Groups</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCandidateGroups()
	 * @generated
	 * @ordered
	 */
	protected EList<CandidateGroup> candidateGroups;

	/**
	 * The default value of the '{@link #getFormKey() <em>Form Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormKey()
	 * @generated
	 * @ordered
	 */
	protected static final String FORM_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFormKey() <em>Form Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormKey()
	 * @generated
	 * @ordered
	 */
	protected String formKey = FORM_KEY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFormProperties() <em>Form Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<FormProperty> formProperties;

	/**
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected static final Integer PRIORITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected Integer priority = PRIORITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getDueDate() <em>Due Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDueDate()
	 * @generated
	 * @ordered
	 */
	protected static final String DUE_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDueDate() <em>Due Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDueDate()
	 * @generated
	 * @ordered
	 */
	protected String dueDate = DUE_DATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Bpmn2Package.Literals.USER_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Rendering> getRenderings() {
		if (renderings == null) {
			renderings = new EObjectContainmentEList<Rendering>(
					Rendering.class, this, Bpmn2Package.USER_TASK__RENDERINGS);
		}
		return renderings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImplementation() {
		return implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementation(String newImplementation) {
		String oldImplementation = implementation;
		implementation = newImplementation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.USER_TASK__IMPLEMENTATION, oldImplementation,
					implementation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAssignee() {
		return assignee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssignee(String newAssignee) {
		String oldAssignee = assignee;
		assignee = newAssignee;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.USER_TASK__ASSIGNEE, oldAssignee, assignee));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<CandidateUser> getCandidateUsers() {
		if (candidateUsers == null) {
			candidateUsers = new EObjectResolvingEList<CandidateUser>(
					CandidateUser.class, this,
					Bpmn2Package.USER_TASK__CANDIDATE_USERS);
		}
		return candidateUsers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<CandidateGroup> getCandidateGroups() {
		if (candidateGroups == null) {
			candidateGroups = new EObjectResolvingEList<CandidateGroup>(
					CandidateGroup.class, this,
					Bpmn2Package.USER_TASK__CANDIDATE_GROUPS);
		}
		return candidateGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFormKey() {
		return formKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormKey(String newFormKey) {
		String oldFormKey = formKey;
		formKey = newFormKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.USER_TASK__FORM_KEY, oldFormKey, formKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<FormProperty> getFormProperties() {
		if (formProperties == null) {
			formProperties = new EObjectContainmentEList<FormProperty>(
					FormProperty.class, this,
					Bpmn2Package.USER_TASK__FORM_PROPERTIES);
		}
		return formProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriority(Integer newPriority) {
		Integer oldPriority = priority;
		priority = newPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.USER_TASK__PRIORITY, oldPriority, priority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDueDate(String newDueDate) {
		String oldDueDate = dueDate;
		dueDate = newDueDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.USER_TASK__DUE_DATE, oldDueDate, dueDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Bpmn2Package.USER_TASK__RENDERINGS:
			return ((InternalEList<?>) getRenderings()).basicRemove(otherEnd,
					msgs);
		case Bpmn2Package.USER_TASK__FORM_PROPERTIES:
			return ((InternalEList<?>) getFormProperties()).basicRemove(
					otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Bpmn2Package.USER_TASK__RENDERINGS:
			return getRenderings();
		case Bpmn2Package.USER_TASK__IMPLEMENTATION:
			return getImplementation();
		case Bpmn2Package.USER_TASK__ASSIGNEE:
			return getAssignee();
		case Bpmn2Package.USER_TASK__CANDIDATE_USERS:
			return getCandidateUsers();
		case Bpmn2Package.USER_TASK__CANDIDATE_GROUPS:
			return getCandidateGroups();
		case Bpmn2Package.USER_TASK__FORM_KEY:
			return getFormKey();
		case Bpmn2Package.USER_TASK__FORM_PROPERTIES:
			return getFormProperties();
		case Bpmn2Package.USER_TASK__PRIORITY:
			return getPriority();
		case Bpmn2Package.USER_TASK__DUE_DATE:
			return getDueDate();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case Bpmn2Package.USER_TASK__RENDERINGS:
			getRenderings().clear();
			getRenderings().addAll((Collection<? extends Rendering>) newValue);
			return;
		case Bpmn2Package.USER_TASK__IMPLEMENTATION:
			setImplementation((String) newValue);
			return;
		case Bpmn2Package.USER_TASK__ASSIGNEE:
			setAssignee((String) newValue);
			return;
		case Bpmn2Package.USER_TASK__CANDIDATE_USERS:
			getCandidateUsers().clear();
			getCandidateUsers().addAll(
					(Collection<? extends CandidateUser>) newValue);
			return;
		case Bpmn2Package.USER_TASK__CANDIDATE_GROUPS:
			getCandidateGroups().clear();
			getCandidateGroups().addAll(
					(Collection<? extends CandidateGroup>) newValue);
			return;
		case Bpmn2Package.USER_TASK__FORM_KEY:
			setFormKey((String) newValue);
			return;
		case Bpmn2Package.USER_TASK__FORM_PROPERTIES:
			getFormProperties().clear();
			getFormProperties().addAll(
					(Collection<? extends FormProperty>) newValue);
			return;
		case Bpmn2Package.USER_TASK__PRIORITY:
			setPriority((Integer) newValue);
			return;
		case Bpmn2Package.USER_TASK__DUE_DATE:
			setDueDate((String) newValue);
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
		case Bpmn2Package.USER_TASK__RENDERINGS:
			getRenderings().clear();
			return;
		case Bpmn2Package.USER_TASK__IMPLEMENTATION:
			setImplementation(IMPLEMENTATION_EDEFAULT);
			return;
		case Bpmn2Package.USER_TASK__ASSIGNEE:
			setAssignee(ASSIGNEE_EDEFAULT);
			return;
		case Bpmn2Package.USER_TASK__CANDIDATE_USERS:
			getCandidateUsers().clear();
			return;
		case Bpmn2Package.USER_TASK__CANDIDATE_GROUPS:
			getCandidateGroups().clear();
			return;
		case Bpmn2Package.USER_TASK__FORM_KEY:
			setFormKey(FORM_KEY_EDEFAULT);
			return;
		case Bpmn2Package.USER_TASK__FORM_PROPERTIES:
			getFormProperties().clear();
			return;
		case Bpmn2Package.USER_TASK__PRIORITY:
			setPriority(PRIORITY_EDEFAULT);
			return;
		case Bpmn2Package.USER_TASK__DUE_DATE:
			setDueDate(DUE_DATE_EDEFAULT);
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
		case Bpmn2Package.USER_TASK__RENDERINGS:
			return renderings != null && !renderings.isEmpty();
		case Bpmn2Package.USER_TASK__IMPLEMENTATION:
			return IMPLEMENTATION_EDEFAULT == null ? implementation != null
					: !IMPLEMENTATION_EDEFAULT.equals(implementation);
		case Bpmn2Package.USER_TASK__ASSIGNEE:
			return ASSIGNEE_EDEFAULT == null ? assignee != null
					: !ASSIGNEE_EDEFAULT.equals(assignee);
		case Bpmn2Package.USER_TASK__CANDIDATE_USERS:
			return candidateUsers != null && !candidateUsers.isEmpty();
		case Bpmn2Package.USER_TASK__CANDIDATE_GROUPS:
			return candidateGroups != null && !candidateGroups.isEmpty();
		case Bpmn2Package.USER_TASK__FORM_KEY:
			return FORM_KEY_EDEFAULT == null ? formKey != null
					: !FORM_KEY_EDEFAULT.equals(formKey);
		case Bpmn2Package.USER_TASK__FORM_PROPERTIES:
			return formProperties != null && !formProperties.isEmpty();
		case Bpmn2Package.USER_TASK__PRIORITY:
			return PRIORITY_EDEFAULT == null ? priority != null
					: !PRIORITY_EDEFAULT.equals(priority);
		case Bpmn2Package.USER_TASK__DUE_DATE:
			return DUE_DATE_EDEFAULT == null ? dueDate != null
					: !DUE_DATE_EDEFAULT.equals(dueDate);
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
		result.append(" (implementation: ");
		result.append(implementation);
		result.append(", assignee: ");
		result.append(assignee);
		result.append(", formKey: ");
		result.append(formKey);
		result.append(", priority: ");
		result.append(priority);
		result.append(", dueDate: ");
		result.append(dueDate);
		result.append(')');
		return result.toString();
	}

} //UserTaskImpl
