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
package org.eclipse.securebpmn2.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.securebpmn2.Permission;
import org.eclipse.securebpmn2.Role;
import org.eclipse.securebpmn2.Securebpmn2Package;
import org.eclipse.securebpmn2.Subject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.securebpmn2.impl.RoleImpl#getRoleName <em>Role Name</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.RoleImpl#getParentRoles <em>Parent Roles</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.RoleImpl#getSubjects <em>Subjects</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.RoleImpl#getChildRoles <em>Child Roles</em>}</li>
 *   <li>{@link org.eclipse.securebpmn2.impl.RoleImpl#getPermissions <em>Permissions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleImpl extends SecurityFlowNodeImpl implements Role {
	/**
	 * The default value of the '{@link #getRoleName() <em>Role Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleName()
	 * @generated
	 * @ordered
	 */
	protected static final String ROLE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRoleName() <em>Role Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleName()
	 * @generated
	 * @ordered
	 */
	protected String roleName = ROLE_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParentRoles() <em>Parent Roles</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> parentRoles;

	/**
	 * The cached value of the '{@link #getSubjects() <em>Subjects</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubjects()
	 * @generated
	 * @ordered
	 */
	protected EList<Subject> subjects;

	/**
	 * The cached value of the '{@link #getChildRoles() <em>Child Roles</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> childRoles;

	/**
	 * The cached value of the '{@link #getPermissions() <em>Permissions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPermissions()
	 * @generated
	 * @ordered
	 */
	protected EList<Permission> permissions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Securebpmn2Package.Literals.ROLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoleName(String newRoleName) {
		String oldRoleName = roleName;
		roleName = newRoleName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Securebpmn2Package.ROLE__ROLE_NAME, oldRoleName, roleName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Role> getParentRoles() {
		if (parentRoles == null) {
			parentRoles = new EObjectWithInverseResolvingEList.ManyInverse<Role>(
					Role.class, this, Securebpmn2Package.ROLE__PARENT_ROLES,
					Securebpmn2Package.ROLE__CHILD_ROLES);
		}
		return parentRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Subject> getSubjects() {
		if (subjects == null) {
			subjects = new EObjectWithInverseResolvingEList.ManyInverse<Subject>(
					Subject.class, this, Securebpmn2Package.ROLE__SUBJECTS,
					Securebpmn2Package.SUBJECT__ROLES);
		}
		return subjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Role> getChildRoles() {
		if (childRoles == null) {
			childRoles = new EObjectWithInverseResolvingEList.ManyInverse<Role>(
					Role.class, this, Securebpmn2Package.ROLE__CHILD_ROLES,
					Securebpmn2Package.ROLE__PARENT_ROLES);
		}
		return childRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Permission> getPermissions() {
		if (permissions == null) {
			permissions = new EObjectWithInverseResolvingEList.ManyInverse<Permission>(
					Permission.class, this,
					Securebpmn2Package.ROLE__PERMISSIONS,
					Securebpmn2Package.PERMISSION__ROLES);
		}
		return permissions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Securebpmn2Package.ROLE__PARENT_ROLES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getParentRoles())
					.basicAdd(otherEnd, msgs);
		case Securebpmn2Package.ROLE__SUBJECTS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getSubjects())
					.basicAdd(otherEnd, msgs);
		case Securebpmn2Package.ROLE__CHILD_ROLES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getChildRoles())
					.basicAdd(otherEnd, msgs);
		case Securebpmn2Package.ROLE__PERMISSIONS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getPermissions())
					.basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
		case Securebpmn2Package.ROLE__PARENT_ROLES:
			return ((InternalEList<?>) getParentRoles()).basicRemove(otherEnd,
					msgs);
		case Securebpmn2Package.ROLE__SUBJECTS:
			return ((InternalEList<?>) getSubjects()).basicRemove(otherEnd,
					msgs);
		case Securebpmn2Package.ROLE__CHILD_ROLES:
			return ((InternalEList<?>) getChildRoles()).basicRemove(otherEnd,
					msgs);
		case Securebpmn2Package.ROLE__PERMISSIONS:
			return ((InternalEList<?>) getPermissions()).basicRemove(otherEnd,
					msgs);
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
		case Securebpmn2Package.ROLE__ROLE_NAME:
			return getRoleName();
		case Securebpmn2Package.ROLE__PARENT_ROLES:
			return getParentRoles();
		case Securebpmn2Package.ROLE__SUBJECTS:
			return getSubjects();
		case Securebpmn2Package.ROLE__CHILD_ROLES:
			return getChildRoles();
		case Securebpmn2Package.ROLE__PERMISSIONS:
			return getPermissions();
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
		case Securebpmn2Package.ROLE__ROLE_NAME:
			setRoleName((String) newValue);
			return;
		case Securebpmn2Package.ROLE__PARENT_ROLES:
			getParentRoles().clear();
			getParentRoles().addAll((Collection<? extends Role>) newValue);
			return;
		case Securebpmn2Package.ROLE__SUBJECTS:
			getSubjects().clear();
			getSubjects().addAll((Collection<? extends Subject>) newValue);
			return;
		case Securebpmn2Package.ROLE__CHILD_ROLES:
			getChildRoles().clear();
			getChildRoles().addAll((Collection<? extends Role>) newValue);
			return;
		case Securebpmn2Package.ROLE__PERMISSIONS:
			getPermissions().clear();
			getPermissions()
					.addAll((Collection<? extends Permission>) newValue);
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
		case Securebpmn2Package.ROLE__ROLE_NAME:
			setRoleName(ROLE_NAME_EDEFAULT);
			return;
		case Securebpmn2Package.ROLE__PARENT_ROLES:
			getParentRoles().clear();
			return;
		case Securebpmn2Package.ROLE__SUBJECTS:
			getSubjects().clear();
			return;
		case Securebpmn2Package.ROLE__CHILD_ROLES:
			getChildRoles().clear();
			return;
		case Securebpmn2Package.ROLE__PERMISSIONS:
			getPermissions().clear();
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
		case Securebpmn2Package.ROLE__ROLE_NAME:
			return ROLE_NAME_EDEFAULT == null ? roleName != null
					: !ROLE_NAME_EDEFAULT.equals(roleName);
		case Securebpmn2Package.ROLE__PARENT_ROLES:
			return parentRoles != null && !parentRoles.isEmpty();
		case Securebpmn2Package.ROLE__SUBJECTS:
			return subjects != null && !subjects.isEmpty();
		case Securebpmn2Package.ROLE__CHILD_ROLES:
			return childRoles != null && !childRoles.isEmpty();
		case Securebpmn2Package.ROLE__PERMISSIONS:
			return permissions != null && !permissions.isEmpty();
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
		result.append(" (roleName: ");
		result.append(roleName);
		result.append(')');
		return result.toString();
	}

} //RoleImpl
