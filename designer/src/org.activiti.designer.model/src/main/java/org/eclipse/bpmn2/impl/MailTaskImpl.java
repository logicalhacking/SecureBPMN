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

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.MailTask;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mail Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.MailTaskImpl#getTo <em>To</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.MailTaskImpl#getFrom <em>From</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.MailTaskImpl#getSubject <em>Subject</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.MailTaskImpl#getCc <em>Cc</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.MailTaskImpl#getBcc <em>Bcc</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.MailTaskImpl#getHtml <em>Html</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.MailTaskImpl#getText <em>Text</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MailTaskImpl extends TaskImpl implements MailTask {
	/**
	 * The default value of the '{@link #getTo() <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected static final String TO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected String to = TO_EDEFAULT;

	/**
	 * The default value of the '{@link #getFrom() <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected static final String FROM_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getFrom() <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected String from = FROM_EDEFAULT;

	/**
	 * The default value of the '{@link #getSubject() <em>Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubject()
	 * @generated
	 * @ordered
	 */
	protected static final String SUBJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubject() <em>Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubject()
	 * @generated
	 * @ordered
	 */
	protected String subject = SUBJECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getCc() <em>Cc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCc()
	 * @generated
	 * @ordered
	 */
	protected static final String CC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCc() <em>Cc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCc()
	 * @generated
	 * @ordered
	 */
	protected String cc = CC_EDEFAULT;

	/**
	 * The default value of the '{@link #getBcc() <em>Bcc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBcc()
	 * @generated
	 * @ordered
	 */
	protected static final String BCC_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getBcc() <em>Bcc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBcc()
	 * @generated
	 * @ordered
	 */
	protected String bcc = BCC_EDEFAULT;

	/**
	 * The default value of the '{@link #getHtml() <em>Html</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHtml()
	 * @generated
	 * @ordered
	 */
	protected static final String HTML_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHtml() <em>Html</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHtml()
	 * @generated
	 * @ordered
	 */
	protected String html = HTML_EDEFAULT;

	/**
	 * The default value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected String text = TEXT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MailTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Bpmn2Package.Literals.MAIL_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTo() {
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTo(String newTo) {
		String oldTo = to;
		to = newTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.MAIL_TASK__TO, oldTo, to));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrom(String newFrom) {
		String oldFrom = from;
		from = newFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.MAIL_TASK__FROM, oldFrom, from));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubject(String newSubject) {
		String oldSubject = subject;
		subject = newSubject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.MAIL_TASK__SUBJECT, oldSubject, subject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCc() {
		return cc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCc(String newCc) {
		String oldCc = cc;
		cc = newCc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.MAIL_TASK__CC, oldCc, cc));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBcc() {
		return bcc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBcc(String newBcc) {
		String oldBcc = bcc;
		bcc = newBcc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.MAIL_TASK__BCC, oldBcc, bcc));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHtml() {
		return html;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHtml(String newHtml) {
		String oldHtml = html;
		html = newHtml;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.MAIL_TASK__HTML, oldHtml, html));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText() {
		return text;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setText(String newText) {
		String oldText = text;
		text = newText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Bpmn2Package.MAIL_TASK__TEXT, oldText, text));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Bpmn2Package.MAIL_TASK__TO:
			return getTo();
		case Bpmn2Package.MAIL_TASK__FROM:
			return getFrom();
		case Bpmn2Package.MAIL_TASK__SUBJECT:
			return getSubject();
		case Bpmn2Package.MAIL_TASK__CC:
			return getCc();
		case Bpmn2Package.MAIL_TASK__BCC:
			return getBcc();
		case Bpmn2Package.MAIL_TASK__HTML:
			return getHtml();
		case Bpmn2Package.MAIL_TASK__TEXT:
			return getText();
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
		case Bpmn2Package.MAIL_TASK__TO:
			setTo((String) newValue);
			return;
		case Bpmn2Package.MAIL_TASK__FROM:
			setFrom((String) newValue);
			return;
		case Bpmn2Package.MAIL_TASK__SUBJECT:
			setSubject((String) newValue);
			return;
		case Bpmn2Package.MAIL_TASK__CC:
			setCc((String) newValue);
			return;
		case Bpmn2Package.MAIL_TASK__BCC:
			setBcc((String) newValue);
			return;
		case Bpmn2Package.MAIL_TASK__HTML:
			setHtml((String) newValue);
			return;
		case Bpmn2Package.MAIL_TASK__TEXT:
			setText((String) newValue);
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
		case Bpmn2Package.MAIL_TASK__TO:
			setTo(TO_EDEFAULT);
			return;
		case Bpmn2Package.MAIL_TASK__FROM:
			setFrom(FROM_EDEFAULT);
			return;
		case Bpmn2Package.MAIL_TASK__SUBJECT:
			setSubject(SUBJECT_EDEFAULT);
			return;
		case Bpmn2Package.MAIL_TASK__CC:
			setCc(CC_EDEFAULT);
			return;
		case Bpmn2Package.MAIL_TASK__BCC:
			setBcc(BCC_EDEFAULT);
			return;
		case Bpmn2Package.MAIL_TASK__HTML:
			setHtml(HTML_EDEFAULT);
			return;
		case Bpmn2Package.MAIL_TASK__TEXT:
			setText(TEXT_EDEFAULT);
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
		case Bpmn2Package.MAIL_TASK__TO:
			return TO_EDEFAULT == null ? to != null : !TO_EDEFAULT.equals(to);
		case Bpmn2Package.MAIL_TASK__FROM:
			return FROM_EDEFAULT == null ? from != null : !FROM_EDEFAULT
					.equals(from);
		case Bpmn2Package.MAIL_TASK__SUBJECT:
			return SUBJECT_EDEFAULT == null ? subject != null
					: !SUBJECT_EDEFAULT.equals(subject);
		case Bpmn2Package.MAIL_TASK__CC:
			return CC_EDEFAULT == null ? cc != null : !CC_EDEFAULT.equals(cc);
		case Bpmn2Package.MAIL_TASK__BCC:
			return BCC_EDEFAULT == null ? bcc != null : !BCC_EDEFAULT
					.equals(bcc);
		case Bpmn2Package.MAIL_TASK__HTML:
			return HTML_EDEFAULT == null ? html != null : !HTML_EDEFAULT
					.equals(html);
		case Bpmn2Package.MAIL_TASK__TEXT:
			return TEXT_EDEFAULT == null ? text != null : !TEXT_EDEFAULT
					.equals(text);
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
		result.append(" (to: ");
		result.append(to);
		result.append(", from: ");
		result.append(from);
		result.append(", subject: ");
		result.append(subject);
		result.append(", cc: ");
		result.append(cc);
		result.append(", bcc: ");
		result.append(bcc);
		result.append(", html: ");
		result.append(html);
		result.append(", text: ");
		result.append(text);
		result.append(')');
		return result.toString();
	}

} //MailTaskImpl
