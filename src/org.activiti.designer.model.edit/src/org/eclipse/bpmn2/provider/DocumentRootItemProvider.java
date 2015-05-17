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
package org.eclipse.bpmn2.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.DocumentRoot;

import org.eclipse.bpmn2.di.BpmnDiFactory;

import org.eclipse.dd.dc.DcFactory;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.securebpmn2.Securebpmn2Factory;

import org.eclipse.securebpmn2.provider.bpmn2EditPlugin;

/**
 * This is the item provider adapter for a {@link org.eclipse.bpmn2.DocumentRoot} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DocumentRootItemProvider extends ItemProviderAdapter implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRootItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(
			Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__ARTIFACT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__ASSIGNMENT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__ASSOCIATION);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__AUDITING);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__BOUNDARY_EVENT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__BUSINESS_RULE_TASK);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_ACTIVITY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_CHOREOGRAPHY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_CONVERSATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_NODE);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CATCH_EVENT);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CATEGORY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CATEGORY_VALUE);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__COLLABORATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_TASK);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__COMPLEX_GATEWAY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_LINK);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_KEY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_ASSOCIATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_INPUT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OBJECT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OUTPUT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STATE);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STORE);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STORE_REFERENCE);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__DEFINITIONS);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__DOCUMENTATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__END_EVENT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__END_POINT);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__ERROR);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__ESCALATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_BASED_GATEWAY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__EXPRESSION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__FORMAL_EXPRESSION);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__GATEWAY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_CONVERSATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_TASK);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_USER_TASK);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__GROUP);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__HUMAN_PERFORMER);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__PERFORMER);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ROLE);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__IMPORT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__INCLUSIVE_GATEWAY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__INPUT_SET);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__INTERFACE);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__IO_BINDING);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__IO_SPECIFICATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__ITEM_DEFINITION);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__LANE);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__LANE_SET);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__LINK_EVENT_DEFINITION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__LOOP_CHARACTERISTICS);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__MANUAL_TASK);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_FLOW);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__MONITORING);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__OPERATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__OUTPUT_SET);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__PARALLEL_GATEWAY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__PARTNER_ENTITY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__PARTNER_ROLE);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__POTENTIAL_OWNER);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__PROCESS);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__PROPERTY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__RECEIVE_TASK);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__RELATIONSHIP);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__RENDERING);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT_TASK);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__SEND_TASK);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__SEQUENCE_FLOW);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__SERVICE_TASK);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__SIGNAL);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__START_EVENT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_CHOREOGRAPHY);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_CONVERSATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_PROCESS);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__TASK);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION);
			childrenFeatures.add(Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT_ANNOTATION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__THROW_EVENT);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__TRANSACTION);
			childrenFeatures
					.add(Bpmn2Package.Literals.DOCUMENT_ROOT__USER_TASK);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns DocumentRoot.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object,
				getResourceLocator().getImage("full/obj16/DocumentRoot"));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_DocumentRoot_type");
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(DocumentRoot.class)) {
		case Bpmn2Package.DOCUMENT_ROOT__ACTIVITY:
		case Bpmn2Package.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS:
		case Bpmn2Package.DOCUMENT_ROOT__FLOW_ELEMENT:
		case Bpmn2Package.DOCUMENT_ROOT__ARTIFACT:
		case Bpmn2Package.DOCUMENT_ROOT__ASSIGNMENT:
		case Bpmn2Package.DOCUMENT_ROOT__ASSOCIATION:
		case Bpmn2Package.DOCUMENT_ROOT__AUDITING:
		case Bpmn2Package.DOCUMENT_ROOT__BASE_ELEMENT:
		case Bpmn2Package.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT:
		case Bpmn2Package.DOCUMENT_ROOT__BOUNDARY_EVENT:
		case Bpmn2Package.DOCUMENT_ROOT__BUSINESS_RULE_TASK:
		case Bpmn2Package.DOCUMENT_ROOT__CALLABLE_ELEMENT:
		case Bpmn2Package.DOCUMENT_ROOT__CALL_ACTIVITY:
		case Bpmn2Package.DOCUMENT_ROOT__CALL_CHOREOGRAPHY:
		case Bpmn2Package.DOCUMENT_ROOT__CALL_CONVERSATION:
		case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_NODE:
		case Bpmn2Package.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION:
		case Bpmn2Package.DOCUMENT_ROOT__EVENT_DEFINITION:
		case Bpmn2Package.DOCUMENT_ROOT__ROOT_ELEMENT:
		case Bpmn2Package.DOCUMENT_ROOT__CATCH_EVENT:
		case Bpmn2Package.DOCUMENT_ROOT__CATEGORY:
		case Bpmn2Package.DOCUMENT_ROOT__CATEGORY_VALUE:
		case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY:
		case Bpmn2Package.DOCUMENT_ROOT__COLLABORATION:
		case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY:
		case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY_TASK:
		case Bpmn2Package.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION:
		case Bpmn2Package.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION:
		case Bpmn2Package.DOCUMENT_ROOT__COMPLEX_GATEWAY:
		case Bpmn2Package.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION:
		case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION:
		case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION:
		case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_LINK:
		case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_KEY:
		case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY:
		case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING:
		case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION:
		case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION:
		case Bpmn2Package.DOCUMENT_ROOT__DATA_ASSOCIATION:
		case Bpmn2Package.DOCUMENT_ROOT__DATA_INPUT:
		case Bpmn2Package.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION:
		case Bpmn2Package.DOCUMENT_ROOT__DATA_OBJECT:
		case Bpmn2Package.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE:
		case Bpmn2Package.DOCUMENT_ROOT__DATA_OUTPUT:
		case Bpmn2Package.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION:
		case Bpmn2Package.DOCUMENT_ROOT__DATA_STATE:
		case Bpmn2Package.DOCUMENT_ROOT__DATA_STORE:
		case Bpmn2Package.DOCUMENT_ROOT__DATA_STORE_REFERENCE:
		case Bpmn2Package.DOCUMENT_ROOT__DEFINITIONS:
		case Bpmn2Package.DOCUMENT_ROOT__DOCUMENTATION:
		case Bpmn2Package.DOCUMENT_ROOT__END_EVENT:
		case Bpmn2Package.DOCUMENT_ROOT__END_POINT:
		case Bpmn2Package.DOCUMENT_ROOT__ERROR:
		case Bpmn2Package.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION:
		case Bpmn2Package.DOCUMENT_ROOT__ESCALATION:
		case Bpmn2Package.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION:
		case Bpmn2Package.DOCUMENT_ROOT__EVENT:
		case Bpmn2Package.DOCUMENT_ROOT__EVENT_BASED_GATEWAY:
		case Bpmn2Package.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY:
		case Bpmn2Package.DOCUMENT_ROOT__EXPRESSION:
		case Bpmn2Package.DOCUMENT_ROOT__EXTENSION:
		case Bpmn2Package.DOCUMENT_ROOT__EXTENSION_ELEMENTS:
		case Bpmn2Package.DOCUMENT_ROOT__FLOW_NODE:
		case Bpmn2Package.DOCUMENT_ROOT__FORMAL_EXPRESSION:
		case Bpmn2Package.DOCUMENT_ROOT__GATEWAY:
		case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK:
		case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK:
		case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_CONVERSATION:
		case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK:
		case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK:
		case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_TASK:
		case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_USER_TASK:
		case Bpmn2Package.DOCUMENT_ROOT__GROUP:
		case Bpmn2Package.DOCUMENT_ROOT__HUMAN_PERFORMER:
		case Bpmn2Package.DOCUMENT_ROOT__PERFORMER:
		case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_ROLE:
		case Bpmn2Package.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT:
		case Bpmn2Package.DOCUMENT_ROOT__IMPORT:
		case Bpmn2Package.DOCUMENT_ROOT__INCLUSIVE_GATEWAY:
		case Bpmn2Package.DOCUMENT_ROOT__INPUT_SET:
		case Bpmn2Package.DOCUMENT_ROOT__INTERFACE:
		case Bpmn2Package.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT:
		case Bpmn2Package.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT:
		case Bpmn2Package.DOCUMENT_ROOT__IO_BINDING:
		case Bpmn2Package.DOCUMENT_ROOT__IO_SPECIFICATION:
		case Bpmn2Package.DOCUMENT_ROOT__ITEM_DEFINITION:
		case Bpmn2Package.DOCUMENT_ROOT__LANE:
		case Bpmn2Package.DOCUMENT_ROOT__LANE_SET:
		case Bpmn2Package.DOCUMENT_ROOT__LINK_EVENT_DEFINITION:
		case Bpmn2Package.DOCUMENT_ROOT__LOOP_CHARACTERISTICS:
		case Bpmn2Package.DOCUMENT_ROOT__MANUAL_TASK:
		case Bpmn2Package.DOCUMENT_ROOT__MESSAGE:
		case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION:
		case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_FLOW:
		case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION:
		case Bpmn2Package.DOCUMENT_ROOT__MONITORING:
		case Bpmn2Package.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS:
		case Bpmn2Package.DOCUMENT_ROOT__OPERATION:
		case Bpmn2Package.DOCUMENT_ROOT__OUTPUT_SET:
		case Bpmn2Package.DOCUMENT_ROOT__PARALLEL_GATEWAY:
		case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT:
		case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION:
		case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY:
		case Bpmn2Package.DOCUMENT_ROOT__PARTNER_ENTITY:
		case Bpmn2Package.DOCUMENT_ROOT__PARTNER_ROLE:
		case Bpmn2Package.DOCUMENT_ROOT__POTENTIAL_OWNER:
		case Bpmn2Package.DOCUMENT_ROOT__PROCESS:
		case Bpmn2Package.DOCUMENT_ROOT__PROPERTY:
		case Bpmn2Package.DOCUMENT_ROOT__RECEIVE_TASK:
		case Bpmn2Package.DOCUMENT_ROOT__RELATIONSHIP:
		case Bpmn2Package.DOCUMENT_ROOT__RENDERING:
		case Bpmn2Package.DOCUMENT_ROOT__RESOURCE:
		case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION:
		case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_PARAMETER:
		case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING:
		case Bpmn2Package.DOCUMENT_ROOT__SCRIPT:
		case Bpmn2Package.DOCUMENT_ROOT__SCRIPT_TASK:
		case Bpmn2Package.DOCUMENT_ROOT__SEND_TASK:
		case Bpmn2Package.DOCUMENT_ROOT__SEQUENCE_FLOW:
		case Bpmn2Package.DOCUMENT_ROOT__SERVICE_TASK:
		case Bpmn2Package.DOCUMENT_ROOT__SIGNAL:
		case Bpmn2Package.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION:
		case Bpmn2Package.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS:
		case Bpmn2Package.DOCUMENT_ROOT__START_EVENT:
		case Bpmn2Package.DOCUMENT_ROOT__SUB_CHOREOGRAPHY:
		case Bpmn2Package.DOCUMENT_ROOT__SUB_CONVERSATION:
		case Bpmn2Package.DOCUMENT_ROOT__SUB_PROCESS:
		case Bpmn2Package.DOCUMENT_ROOT__TASK:
		case Bpmn2Package.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION:
		case Bpmn2Package.DOCUMENT_ROOT__TEXT:
		case Bpmn2Package.DOCUMENT_ROOT__TEXT_ANNOTATION:
		case Bpmn2Package.DOCUMENT_ROOT__THROW_EVENT:
		case Bpmn2Package.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION:
		case Bpmn2Package.DOCUMENT_ROOT__TRANSACTION:
		case Bpmn2Package.DOCUMENT_ROOT__USER_TASK:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createAdHocSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createCallActivity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createReceiveTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createSendTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createServiceTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createTransaction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createAlfrescoUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createAlfrescoScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
				Bpmn2Factory.eINSTANCE.createAlfrescoMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS,
				Bpmn2Factory.eINSTANCE.createAdHocSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createAdHocSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createBoundaryEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCallActivity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCallChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createComplexGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataObject()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataObjectReference()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataStoreReference()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createEndEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createEventBasedGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createExclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createImplicitThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createInclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createIntermediateCatchEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createIntermediateThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createParallelGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createReceiveTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createSendTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createSequenceFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createServiceTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createSubChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createTransaction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createAlfrescoUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createAlfrescoStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createAlfrescoScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Bpmn2Factory.eINSTANCE.createAlfrescoMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createSecurityFlowNode()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createSecurityFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createAuthorizationConstraint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createSeparationOfDuty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createBindingOfDuty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
				Securebpmn2Factory.eINSTANCE
						.createActivityAuthorizationConstraint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ARTIFACT,
				Bpmn2Factory.eINSTANCE.createAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ARTIFACT,
				Bpmn2Factory.eINSTANCE.createGroup()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ARTIFACT,
				Bpmn2Factory.eINSTANCE.createTextAnnotation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ASSIGNMENT,
				Bpmn2Factory.eINSTANCE.createAssignment()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ASSOCIATION,
				Bpmn2Factory.eINSTANCE.createAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__AUDITING,
				Bpmn2Factory.eINSTANCE.createAuditing()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createAdHocSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createAssignment()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createAuditing()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createBoundaryEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCallActivity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCallChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCallConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCancelEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCategory()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCategoryValue()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCollaboration()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCompensateEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createComplexBehaviorDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createComplexGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createConditionalEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createConversationAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createConversationLink()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCorrelationKey()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCorrelationProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCorrelationPropertyBinding()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE
						.createCorrelationPropertyRetrievalExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCorrelationSubscription()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createItemAwareElement()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataInput()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataInputAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataObject()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataObjectReference()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataOutput()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataOutputAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataState()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataStore()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataStoreReference()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDefinitions()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDocumentation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createEndEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createEndPoint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createError()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createErrorEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createEscalationEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createEventBasedGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createExclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createFormalExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGroup()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createResourceRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createPerformer()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createHumanPerformer()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createImplicitThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createInclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createInputOutputSpecification()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createInputSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createInterface()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createIntermediateCatchEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createIntermediateThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createItemDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createLane()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createLaneSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createLinkEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createMessage()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createMessageEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createMessageFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createMessageFlowAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createMonitoring()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
						Bpmn2Factory.eINSTANCE
								.createMultiInstanceLoopCharacteristics()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createOperation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createOutputSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createParallelGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createParticipant()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createParticipantAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createPartnerEntity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createPartnerRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createPotentialOwner()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createReceiveTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createRelationship()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createRendering()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createResource()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createResourceParameter()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createSendTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createSequenceFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createServiceTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createSignal()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createSignalEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createStandardLoopCharacteristics()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createSubChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createSubConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createTerminateEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createTextAnnotation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createTimerEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createTransaction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCandidateUser()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCandidateGroup()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCustomProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createFieldExtension()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataGrid()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataGridRow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataGridField()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createActivitiListener()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createFormProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createIOParameter()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createAlfrescoUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createAlfrescoStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createAlfrescoScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createAlfrescoScriptBase()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createAlfrescoMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createFormValue()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createSecurityFlowNode()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createSecurityFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createUser()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createGroup()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createActivityAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createAtomicActivityAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createCompositeActivityAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createPermission()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createNeedToKnow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createAuthorizationConstraint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createSeparationOfDuty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createBindingOfDuty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE
						.createActivityAuthorizationConstraint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createProcessAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createAtomicProcessAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createCompositeProcessAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE.createItemAwareElementAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE
						.createAtomicItemAwareElementAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
				Securebpmn2Factory.eINSTANCE
						.createCompositeItemAwareElementAction()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createSubProcess()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createAdHocSubProcess()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createAssignment()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createAssociation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createAuditing()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createBoundaryEvent()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createBusinessRuleTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createCallActivity()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createCallChoreography()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createCallConversation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createEventDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createCancelEventDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createCategory()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createCategoryValue()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createCollaboration()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createChoreography()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createChoreographyTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE
								.createCompensateEventDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE
								.createComplexBehaviorDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createComplexGateway()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE
								.createConditionalEventDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createConversation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createConversationAssociation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createConversationLink()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createCorrelationKey()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createCorrelationProperty()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE
								.createCorrelationPropertyBinding()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE
								.createCorrelationPropertyRetrievalExpression()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createCorrelationSubscription()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDataAssociation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createItemAwareElement()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDataInput()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDataInputAssociation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDataObject()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDataObjectReference()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDataOutput()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDataOutputAssociation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDataState()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDataStore()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDataStoreReference()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDefinitions()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDocumentation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createEndEvent()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createEndPoint()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createError()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createErrorEventDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createEscalation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE
								.createEscalationEventDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createEventBasedGateway()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createExclusiveGateway()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createExpression()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createExtension()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE
								.createExtensionAttributeDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createExtensionAttributeValue()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createExtensionDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createFormalExpression()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createGlobalTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createGlobalBusinessRuleTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createGlobalChoreographyTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createGlobalConversation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createGlobalManualTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createGlobalScriptTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createGlobalUserTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createGroup()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createResourceRole()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createPerformer()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createHumanPerformer()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createImplicitThrowEvent()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createImport()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createInclusiveGateway()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createInputOutputBinding()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createInputOutputSpecification()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createInputSet()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createInterface()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createIntermediateCatchEvent()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createIntermediateThrowEvent()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createItemDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createLane()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createLaneSet()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createLinkEventDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createManualTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createMessage()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createMessageEventDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createMessageFlow()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createMessageFlowAssociation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createMonitoring()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE
								.createMultiInstanceLoopCharacteristics()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createOperation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createOutputSet()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createParallelGateway()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createParticipant()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createParticipantAssociation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createParticipantMultiplicity()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createPartnerEntity()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createPartnerRole()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createPotentialOwner()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createProcess()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createProperty()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createReceiveTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createRelationship()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createRendering()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createResource()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE
								.createResourceAssignmentExpression()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createResourceParameter()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createResourceParameterBinding()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createScriptTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createSendTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createSequenceFlow()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createServiceTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createSignal()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createSignalEventDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE
								.createStandardLoopCharacteristics()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createStartEvent()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createSubChoreography()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createSubConversation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createTerminateEventDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createTextAnnotation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createTimerEventDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createTransaction()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createUserTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createCandidateUser()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createCandidateGroup()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createCustomProperty()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createMailTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createFieldExtension()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDataGrid()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDataGridRow()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createDataGridField()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createActivitiListener()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createFormProperty()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createIOParameter()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createAlfrescoUserTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createAlfrescoStartEvent()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createAlfrescoScriptTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createAlfrescoScriptBase()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createAlfrescoMailTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Bpmn2Factory.eINSTANCE.createFormValue()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE.createSecurityFlowNode()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE.createSecurityFlow()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE.createRole()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE.createUser()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE.createGroup()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE.createActivityAction()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE
								.createAtomicActivityAction()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE
								.createCompositeActivityAction()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE.createPermission()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE.createNeedToKnow()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE
								.createAuthorizationConstraint()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE.createSeparationOfDuty()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE.createBindingOfDuty()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE
								.createActivityAuthorizationConstraint()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE.createProcessAction()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE
								.createAtomicProcessAction()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE
								.createCompositeProcessAction()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE
								.createItemAwareElementAction()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE
								.createAtomicItemAwareElementAction()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						Securebpmn2Factory.eINSTANCE
								.createCompositeItemAwareElementAction()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						BpmnDiFactory.eINSTANCE.createBPMNDiagram()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						BpmnDiFactory.eINSTANCE.createBPMNEdge()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						BpmnDiFactory.eINSTANCE.createBPMNLabel()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						BpmnDiFactory.eINSTANCE.createBPMNLabelStyle()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						BpmnDiFactory.eINSTANCE.createBPMNPlane()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						BpmnDiFactory.eINSTANCE.createBPMNShape()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						DcFactory.eINSTANCE.createBounds()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						DcFactory.eINSTANCE.createFont()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						DcFactory.eINSTANCE.createPoint()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
						XMLTypeFactory.eINSTANCE.createAnyType()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BOUNDARY_EVENT,
				Bpmn2Factory.eINSTANCE.createBoundaryEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__BUSINESS_RULE_TASK,
				Bpmn2Factory.eINSTANCE.createBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT,
				Bpmn2Factory.eINSTANCE.createProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_ACTIVITY,
				Bpmn2Factory.eINSTANCE.createCallActivity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_CHOREOGRAPHY,
				Bpmn2Factory.eINSTANCE.createCallChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_CONVERSATION,
				Bpmn2Factory.eINSTANCE.createCallConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_NODE,
				Bpmn2Factory.eINSTANCE.createCallConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_NODE,
				Bpmn2Factory.eINSTANCE.createConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_NODE,
				Bpmn2Factory.eINSTANCE.createSubConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createCancelEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createCancelEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createCompensateEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createConditionalEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createErrorEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createEscalationEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createLinkEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createMessageEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createSignalEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createTerminateEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createTimerEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCancelEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCategory()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCollaboration()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCompensateEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createConditionalEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createCorrelationProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createDataStore()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createEndPoint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createError()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createErrorEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createEscalationEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createGlobalUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createInterface()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createItemDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createLinkEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createMessage()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createMessageEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createPartnerEntity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createPartnerRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createResource()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createSignal()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createSignalEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createTerminateEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
				Bpmn2Factory.eINSTANCE.createTimerEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CATCH_EVENT,
				Bpmn2Factory.eINSTANCE.createBoundaryEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CATCH_EVENT,
				Bpmn2Factory.eINSTANCE.createIntermediateCatchEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CATCH_EVENT,
				Bpmn2Factory.eINSTANCE.createStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CATCH_EVENT,
				Bpmn2Factory.eINSTANCE.createAlfrescoStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CATEGORY,
				Bpmn2Factory.eINSTANCE.createCategory()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CATEGORY_VALUE,
				Bpmn2Factory.eINSTANCE.createCategoryValue()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY,
				Bpmn2Factory.eINSTANCE.createChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY,
				Bpmn2Factory.eINSTANCE.createGlobalChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__COLLABORATION,
				Bpmn2Factory.eINSTANCE.createCollaboration()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__COLLABORATION,
				Bpmn2Factory.eINSTANCE.createChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__COLLABORATION,
				Bpmn2Factory.eINSTANCE.createGlobalChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__COLLABORATION,
				Bpmn2Factory.eINSTANCE.createGlobalConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY,
				Bpmn2Factory.eINSTANCE.createCallChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY,
				Bpmn2Factory.eINSTANCE.createChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY,
				Bpmn2Factory.eINSTANCE.createSubChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_TASK,
				Bpmn2Factory.eINSTANCE.createChoreographyTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION,
						Bpmn2Factory.eINSTANCE
								.createCompensateEventDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION,
						Bpmn2Factory.eINSTANCE
								.createComplexBehaviorDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__COMPLEX_GATEWAY,
				Bpmn2Factory.eINSTANCE.createComplexGateway()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION,
						Bpmn2Factory.eINSTANCE
								.createConditionalEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION,
				Bpmn2Factory.eINSTANCE.createConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION,
				Bpmn2Factory.eINSTANCE.createConversationAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_LINK,
				Bpmn2Factory.eINSTANCE.createConversationLink()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_KEY,
				Bpmn2Factory.eINSTANCE.createCorrelationKey()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY,
				Bpmn2Factory.eINSTANCE.createCorrelationProperty()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING,
						Bpmn2Factory.eINSTANCE
								.createCorrelationPropertyBinding()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION,
						Bpmn2Factory.eINSTANCE
								.createCorrelationPropertyRetrievalExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION,
				Bpmn2Factory.eINSTANCE.createCorrelationSubscription()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_ASSOCIATION,
				Bpmn2Factory.eINSTANCE.createDataAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_ASSOCIATION,
				Bpmn2Factory.eINSTANCE.createDataInputAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_ASSOCIATION,
				Bpmn2Factory.eINSTANCE.createDataOutputAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_INPUT,
				Bpmn2Factory.eINSTANCE.createDataInput()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION,
				Bpmn2Factory.eINSTANCE.createDataInputAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OBJECT,
				Bpmn2Factory.eINSTANCE.createDataObject()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE,
				Bpmn2Factory.eINSTANCE.createDataObjectReference()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OUTPUT,
				Bpmn2Factory.eINSTANCE.createDataOutput()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION,
				Bpmn2Factory.eINSTANCE.createDataOutputAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STATE,
				Bpmn2Factory.eINSTANCE.createDataState()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STORE,
				Bpmn2Factory.eINSTANCE.createDataStore()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STORE_REFERENCE,
				Bpmn2Factory.eINSTANCE.createDataStoreReference()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__DEFINITIONS,
				Bpmn2Factory.eINSTANCE.createDefinitions()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__DOCUMENTATION,
				Bpmn2Factory.eINSTANCE.createDocumentation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__END_EVENT,
				Bpmn2Factory.eINSTANCE.createEndEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__END_POINT,
				Bpmn2Factory.eINSTANCE.createEndPoint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ERROR,
				Bpmn2Factory.eINSTANCE.createError()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createErrorEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ESCALATION,
				Bpmn2Factory.eINSTANCE.createEscalation()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION,
						Bpmn2Factory.eINSTANCE
								.createEscalationEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT,
				Bpmn2Factory.eINSTANCE.createBoundaryEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT,
				Bpmn2Factory.eINSTANCE.createEndEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT,
				Bpmn2Factory.eINSTANCE.createImplicitThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT,
				Bpmn2Factory.eINSTANCE.createIntermediateCatchEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT,
				Bpmn2Factory.eINSTANCE.createIntermediateThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT,
				Bpmn2Factory.eINSTANCE.createStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT,
				Bpmn2Factory.eINSTANCE.createAlfrescoStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_BASED_GATEWAY,
				Bpmn2Factory.eINSTANCE.createEventBasedGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY,
				Bpmn2Factory.eINSTANCE.createExclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXPRESSION,
				Bpmn2Factory.eINSTANCE.createExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXPRESSION,
				Bpmn2Factory.eINSTANCE.createFormalExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION,
				Bpmn2Factory.eINSTANCE.createExtension()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createAdHocSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createAssignment()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createAuditing()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createBoundaryEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCallActivity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCallChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCallConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCancelEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCategory()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCategoryValue()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCollaboration()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCompensateEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createComplexBehaviorDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createComplexGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createConditionalEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createConversationAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createConversationLink()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCorrelationKey()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCorrelationProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCorrelationPropertyBinding()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE
						.createCorrelationPropertyRetrievalExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCorrelationSubscription()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createItemAwareElement()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataInput()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataInputAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataObject()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataObjectReference()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataOutput()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataOutputAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataState()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataStore()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataStoreReference()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDefinitions()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDocumentation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createEndEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createEndPoint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createError()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createErrorEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createEscalation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createEscalationEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createEventBasedGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createExclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createExtension()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createExtensionAttributeDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createExtensionAttributeValue()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createExtensionDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createFormalExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createGlobalTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createGlobalBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createGlobalChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createGlobalConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createGlobalManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createGlobalScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createGlobalUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createGroup()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createResourceRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createPerformer()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createHumanPerformer()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createImplicitThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createImport()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createInclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createInputOutputBinding()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createInputOutputSpecification()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createInputSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createInterface()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createIntermediateCatchEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createIntermediateThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createItemDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createLane()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createLaneSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createLinkEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createMessage()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createMessageEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createMessageFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createMessageFlowAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createMonitoring()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
						Bpmn2Factory.eINSTANCE
								.createMultiInstanceLoopCharacteristics()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createOperation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createOutputSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createParallelGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createParticipant()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createParticipantAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createParticipantMultiplicity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createPartnerEntity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createPartnerRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createPotentialOwner()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createReceiveTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createRelationship()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createRendering()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createResource()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createResourceAssignmentExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createResourceParameter()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createResourceParameterBinding()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createSendTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createSequenceFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createServiceTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createSignal()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createSignalEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createStandardLoopCharacteristics()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createSubChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createSubConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createTerminateEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createTextAnnotation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createTimerEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createTransaction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCandidateUser()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCandidateGroup()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCustomProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createFieldExtension()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataGrid()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataGridRow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataGridField()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createActivitiListener()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createFormProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createIOParameter()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createAlfrescoUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createAlfrescoStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createAlfrescoScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createAlfrescoScriptBase()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createAlfrescoMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createFormValue()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createSecurityFlowNode()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createSecurityFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createUser()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createGroup()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createActivityAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createAtomicActivityAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createCompositeActivityAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createPermission()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createNeedToKnow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createAuthorizationConstraint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createSeparationOfDuty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createBindingOfDuty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE
						.createActivityAuthorizationConstraint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createProcessAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createAtomicProcessAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createCompositeProcessAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createItemAwareElementAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE
						.createAtomicItemAwareElementAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				Securebpmn2Factory.eINSTANCE
						.createCompositeItemAwareElementAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				BpmnDiFactory.eINSTANCE.createBPMNDiagram()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				BpmnDiFactory.eINSTANCE.createBPMNEdge()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				BpmnDiFactory.eINSTANCE.createBPMNLabel()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				BpmnDiFactory.eINSTANCE.createBPMNLabelStyle()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				BpmnDiFactory.eINSTANCE.createBPMNPlane()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				BpmnDiFactory.eINSTANCE.createBPMNShape()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				DcFactory.eINSTANCE.createBounds()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				DcFactory.eINSTANCE.createFont()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				DcFactory.eINSTANCE.createPoint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
				XMLTypeFactory.eINSTANCE.createAnyType()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createAdHocSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createBoundaryEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createCallActivity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createCallChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createComplexGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createEndEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createEventBasedGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createExclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createImplicitThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createInclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createIntermediateCatchEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createIntermediateThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createParallelGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createReceiveTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createSendTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createServiceTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createSubChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createTransaction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createAlfrescoUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createAlfrescoStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createAlfrescoScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Bpmn2Factory.eINSTANCE.createAlfrescoMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Securebpmn2Factory.eINSTANCE.createSecurityFlowNode()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Securebpmn2Factory.eINSTANCE.createRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Securebpmn2Factory.eINSTANCE.createAuthorizationConstraint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Securebpmn2Factory.eINSTANCE.createSeparationOfDuty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Securebpmn2Factory.eINSTANCE.createBindingOfDuty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
				Securebpmn2Factory.eINSTANCE
						.createActivityAuthorizationConstraint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__FORMAL_EXPRESSION,
				Bpmn2Factory.eINSTANCE.createFormalExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK,
				Bpmn2Factory.eINSTANCE.createGlobalBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK,
				Bpmn2Factory.eINSTANCE.createGlobalChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_CONVERSATION,
				Bpmn2Factory.eINSTANCE.createGlobalConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK,
				Bpmn2Factory.eINSTANCE.createGlobalManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK,
				Bpmn2Factory.eINSTANCE.createGlobalScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_TASK,
				Bpmn2Factory.eINSTANCE.createGlobalTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_TASK,
				Bpmn2Factory.eINSTANCE.createGlobalBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_TASK,
				Bpmn2Factory.eINSTANCE.createGlobalManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_TASK,
				Bpmn2Factory.eINSTANCE.createGlobalScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_TASK,
				Bpmn2Factory.eINSTANCE.createGlobalUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_USER_TASK,
				Bpmn2Factory.eINSTANCE.createGlobalUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__GROUP,
				Bpmn2Factory.eINSTANCE.createGroup()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__HUMAN_PERFORMER,
				Bpmn2Factory.eINSTANCE.createHumanPerformer()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__HUMAN_PERFORMER,
				Bpmn2Factory.eINSTANCE.createPotentialOwner()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__PERFORMER,
				Bpmn2Factory.eINSTANCE.createPerformer()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__PERFORMER,
				Bpmn2Factory.eINSTANCE.createHumanPerformer()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__PERFORMER,
				Bpmn2Factory.eINSTANCE.createPotentialOwner()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ROLE,
				Bpmn2Factory.eINSTANCE.createResourceRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ROLE,
				Bpmn2Factory.eINSTANCE.createPerformer()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ROLE,
				Bpmn2Factory.eINSTANCE.createHumanPerformer()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ROLE,
				Bpmn2Factory.eINSTANCE.createPotentialOwner()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT,
				Bpmn2Factory.eINSTANCE.createImplicitThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__IMPORT,
				Bpmn2Factory.eINSTANCE.createImport()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__INCLUSIVE_GATEWAY,
				Bpmn2Factory.eINSTANCE.createInclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__INPUT_SET,
				Bpmn2Factory.eINSTANCE.createInputSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__INTERFACE,
				Bpmn2Factory.eINSTANCE.createInterface()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT,
				Bpmn2Factory.eINSTANCE.createIntermediateCatchEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT,
				Bpmn2Factory.eINSTANCE.createIntermediateThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__IO_BINDING,
				Bpmn2Factory.eINSTANCE.createInputOutputBinding()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__IO_SPECIFICATION,
				Bpmn2Factory.eINSTANCE.createInputOutputSpecification()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__ITEM_DEFINITION,
				Bpmn2Factory.eINSTANCE.createItemDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__LANE,
				Bpmn2Factory.eINSTANCE.createLane()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__LANE_SET,
				Bpmn2Factory.eINSTANCE.createLaneSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__LINK_EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createLinkEventDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__LOOP_CHARACTERISTICS,
						Bpmn2Factory.eINSTANCE
								.createMultiInstanceLoopCharacteristics()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__LOOP_CHARACTERISTICS,
				Bpmn2Factory.eINSTANCE.createStandardLoopCharacteristics()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__MANUAL_TASK,
				Bpmn2Factory.eINSTANCE.createManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE,
				Bpmn2Factory.eINSTANCE.createMessage()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createMessageEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_FLOW,
				Bpmn2Factory.eINSTANCE.createMessageFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION,
				Bpmn2Factory.eINSTANCE.createMessageFlowAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__MONITORING,
				Bpmn2Factory.eINSTANCE.createMonitoring()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS,
						Bpmn2Factory.eINSTANCE
								.createMultiInstanceLoopCharacteristics()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__OPERATION,
				Bpmn2Factory.eINSTANCE.createOperation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__OUTPUT_SET,
				Bpmn2Factory.eINSTANCE.createOutputSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__PARALLEL_GATEWAY,
				Bpmn2Factory.eINSTANCE.createParallelGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT,
				Bpmn2Factory.eINSTANCE.createParticipant()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION,
				Bpmn2Factory.eINSTANCE.createParticipantAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY,
				Bpmn2Factory.eINSTANCE.createParticipantMultiplicity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__PARTNER_ENTITY,
				Bpmn2Factory.eINSTANCE.createPartnerEntity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__PARTNER_ROLE,
				Bpmn2Factory.eINSTANCE.createPartnerRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__POTENTIAL_OWNER,
				Bpmn2Factory.eINSTANCE.createPotentialOwner()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__PROCESS,
				Bpmn2Factory.eINSTANCE.createProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__PROPERTY,
				Bpmn2Factory.eINSTANCE.createProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__RECEIVE_TASK,
				Bpmn2Factory.eINSTANCE.createReceiveTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__RELATIONSHIP,
				Bpmn2Factory.eINSTANCE.createRelationship()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__RENDERING,
				Bpmn2Factory.eINSTANCE.createRendering()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE,
				Bpmn2Factory.eINSTANCE.createResource()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION,
						Bpmn2Factory.eINSTANCE
								.createResourceAssignmentExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER,
				Bpmn2Factory.eINSTANCE.createResourceParameter()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING,
						Bpmn2Factory.eINSTANCE.createResourceParameterBinding()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createAdHocSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createAssignment()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createAuditing()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createBoundaryEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCallActivity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCallChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCallConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCancelEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCategory()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCategoryValue()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCollaboration()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCompensateEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createComplexBehaviorDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createComplexGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createConditionalEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createConversationAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createConversationLink()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCorrelationKey()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCorrelationProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCorrelationPropertyBinding()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE
						.createCorrelationPropertyRetrievalExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCorrelationSubscription()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDataAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createItemAwareElement()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDataInput()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDataInputAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDataObject()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDataObjectReference()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDataOutput()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDataOutputAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDataState()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDataStore()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDataStoreReference()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDefinitions()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDocumentation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createEndEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createEndPoint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createError()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createErrorEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createEscalation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createEscalationEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createEventBasedGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createExclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createExtension()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createExtensionAttributeDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createExtensionAttributeValue()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createExtensionDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createFormalExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createGlobalTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createGlobalBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createGlobalChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createGlobalConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createGlobalManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createGlobalScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createGlobalUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createGroup()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createResourceRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createPerformer()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createHumanPerformer()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createImplicitThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createImport()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createInclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createInputOutputBinding()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createInputOutputSpecification()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createInputSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createInterface()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createIntermediateCatchEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createIntermediateThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createItemDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createLane()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createLaneSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createLinkEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createMessage()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createMessageEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createMessageFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createMessageFlowAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createMonitoring()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
						Bpmn2Factory.eINSTANCE
								.createMultiInstanceLoopCharacteristics()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createOperation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createOutputSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createParallelGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createParticipant()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createParticipantAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createParticipantMultiplicity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createPartnerEntity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createPartnerRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createPotentialOwner()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createReceiveTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createRelationship()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createRendering()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createResource()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createResourceAssignmentExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createResourceParameter()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createResourceParameterBinding()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createSendTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createSequenceFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createServiceTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createSignal()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createSignalEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createStandardLoopCharacteristics()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createSubChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createSubConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createTerminateEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createTextAnnotation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createTimerEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createTransaction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCandidateUser()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCandidateGroup()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createCustomProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createFieldExtension()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDataGrid()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDataGridRow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createDataGridField()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createActivitiListener()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createFormProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createIOParameter()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createAlfrescoUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createAlfrescoStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createAlfrescoScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createAlfrescoScriptBase()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createAlfrescoMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Bpmn2Factory.eINSTANCE.createFormValue()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createSecurityFlowNode()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createSecurityFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createUser()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createGroup()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createActivityAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createAtomicActivityAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createCompositeActivityAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createPermission()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createNeedToKnow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createAuthorizationConstraint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createSeparationOfDuty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createBindingOfDuty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE
						.createActivityAuthorizationConstraint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createProcessAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createAtomicProcessAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createCompositeProcessAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE.createItemAwareElementAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE
						.createAtomicItemAwareElementAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				Securebpmn2Factory.eINSTANCE
						.createCompositeItemAwareElementAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				BpmnDiFactory.eINSTANCE.createBPMNDiagram()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				BpmnDiFactory.eINSTANCE.createBPMNEdge()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				BpmnDiFactory.eINSTANCE.createBPMNLabel()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				BpmnDiFactory.eINSTANCE.createBPMNLabelStyle()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				BpmnDiFactory.eINSTANCE.createBPMNPlane()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				BpmnDiFactory.eINSTANCE.createBPMNShape()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				DcFactory.eINSTANCE.createBounds()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				DcFactory.eINSTANCE.createFont()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				DcFactory.eINSTANCE.createPoint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
				XMLTypeFactory.eINSTANCE.createAnyType()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT_TASK,
				Bpmn2Factory.eINSTANCE.createScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SEND_TASK,
				Bpmn2Factory.eINSTANCE.createSendTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SEQUENCE_FLOW,
				Bpmn2Factory.eINSTANCE.createSequenceFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SERVICE_TASK,
				Bpmn2Factory.eINSTANCE.createServiceTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SIGNAL,
				Bpmn2Factory.eINSTANCE.createSignal()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createSignalEventDefinition()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS,
						Bpmn2Factory.eINSTANCE
								.createStandardLoopCharacteristics()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__START_EVENT,
				Bpmn2Factory.eINSTANCE.createStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__START_EVENT,
				Bpmn2Factory.eINSTANCE.createAlfrescoStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_CHOREOGRAPHY,
				Bpmn2Factory.eINSTANCE.createSubChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_CONVERSATION,
				Bpmn2Factory.eINSTANCE.createSubConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_PROCESS,
				Bpmn2Factory.eINSTANCE.createSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_PROCESS,
				Bpmn2Factory.eINSTANCE.createAdHocSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_PROCESS,
				Bpmn2Factory.eINSTANCE.createTransaction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TASK,
				Bpmn2Factory.eINSTANCE.createTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TASK,
				Bpmn2Factory.eINSTANCE.createBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TASK,
				Bpmn2Factory.eINSTANCE.createManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TASK,
				Bpmn2Factory.eINSTANCE.createReceiveTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TASK,
				Bpmn2Factory.eINSTANCE.createScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TASK,
				Bpmn2Factory.eINSTANCE.createSendTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TASK,
				Bpmn2Factory.eINSTANCE.createServiceTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TASK,
				Bpmn2Factory.eINSTANCE.createUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TASK,
				Bpmn2Factory.eINSTANCE.createMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TASK,
				Bpmn2Factory.eINSTANCE.createAlfrescoUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TASK,
				Bpmn2Factory.eINSTANCE.createAlfrescoScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TASK,
				Bpmn2Factory.eINSTANCE.createAlfrescoMailTask()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION,
						Bpmn2Factory.eINSTANCE.createTerminateEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createAdHocSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createAssignment()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createAuditing()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createBoundaryEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCallActivity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCallChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCallConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCancelEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCategory()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCategoryValue()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCollaboration()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCompensateEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createComplexBehaviorDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createComplexGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createConditionalEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createConversationAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createConversationLink()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCorrelationKey()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCorrelationProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCorrelationPropertyBinding()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE
						.createCorrelationPropertyRetrievalExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCorrelationSubscription()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDataAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createItemAwareElement()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDataInput()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDataInputAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDataObject()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDataObjectReference()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDataOutput()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDataOutputAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDataState()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDataStore()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDataStoreReference()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDefinitions()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDocumentation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createEndEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createEndPoint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createError()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createErrorEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createEscalation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createEscalationEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createEventBasedGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createExclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createExtension()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createExtensionAttributeDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createExtensionAttributeValue()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createExtensionDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createFormalExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createGlobalTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createGlobalBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createGlobalChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createGlobalConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createGlobalManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createGlobalScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createGlobalUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createGroup()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createResourceRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createPerformer()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createHumanPerformer()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createImplicitThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createImport()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createInclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createInputOutputBinding()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createInputOutputSpecification()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createInputSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createInterface()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createIntermediateCatchEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createIntermediateThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createItemDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createLane()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createLaneSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createLinkEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createMessage()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createMessageEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createMessageFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createMessageFlowAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createMonitoring()));

		newChildDescriptors
				.add(createChildParameter(
						Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
						Bpmn2Factory.eINSTANCE
								.createMultiInstanceLoopCharacteristics()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createOperation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createOutputSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createParallelGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createParticipant()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createParticipantAssociation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createParticipantMultiplicity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createPartnerEntity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createPartnerRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createPotentialOwner()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createReceiveTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createRelationship()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createRendering()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createResource()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createResourceAssignmentExpression()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createResourceParameter()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createResourceParameterBinding()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createSendTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createSequenceFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createServiceTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createSignal()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createSignalEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createStandardLoopCharacteristics()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createSubChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createSubConversation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createTerminateEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createTextAnnotation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createTimerEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createTransaction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCandidateUser()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCandidateGroup()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createCustomProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createFieldExtension()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDataGrid()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDataGridRow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createDataGridField()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createActivitiListener()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createFormProperty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createIOParameter()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createAlfrescoUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createAlfrescoStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createAlfrescoScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createAlfrescoScriptBase()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createAlfrescoMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Bpmn2Factory.eINSTANCE.createFormValue()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createSecurityFlowNode()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createSecurityFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createUser()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createGroup()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createActivityAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createAtomicActivityAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createCompositeActivityAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createPermission()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createNeedToKnow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createAuthorizationConstraint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createSeparationOfDuty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createBindingOfDuty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE
						.createActivityAuthorizationConstraint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createProcessAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createAtomicProcessAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createCompositeProcessAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE.createItemAwareElementAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE
						.createAtomicItemAwareElementAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				Securebpmn2Factory.eINSTANCE
						.createCompositeItemAwareElementAction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				BpmnDiFactory.eINSTANCE.createBPMNDiagram()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				BpmnDiFactory.eINSTANCE.createBPMNEdge()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				BpmnDiFactory.eINSTANCE.createBPMNLabel()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				BpmnDiFactory.eINSTANCE.createBPMNLabelStyle()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				BpmnDiFactory.eINSTANCE.createBPMNPlane()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				BpmnDiFactory.eINSTANCE.createBPMNShape()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				DcFactory.eINSTANCE.createBounds()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				DcFactory.eINSTANCE.createFont()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				DcFactory.eINSTANCE.createPoint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT,
				XMLTypeFactory.eINSTANCE.createAnyType()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT_ANNOTATION,
				Bpmn2Factory.eINSTANCE.createTextAnnotation()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__THROW_EVENT,
				Bpmn2Factory.eINSTANCE.createEndEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__THROW_EVENT,
				Bpmn2Factory.eINSTANCE.createImplicitThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__THROW_EVENT,
				Bpmn2Factory.eINSTANCE.createIntermediateThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION,
				Bpmn2Factory.eINSTANCE.createTimerEventDefinition()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__TRANSACTION,
				Bpmn2Factory.eINSTANCE.createTransaction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__USER_TASK,
				Bpmn2Factory.eINSTANCE.createUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.DOCUMENT_ROOT__USER_TASK,
				Bpmn2Factory.eINSTANCE.createAlfrescoUserTask()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature,
			Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify = childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_PROCESS
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__BUSINESS_RULE_TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_ACTIVITY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__MANUAL_TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__RECEIVE_TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT_TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__SEND_TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__SERVICE_TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__TRANSACTION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__USER_TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__BOUNDARY_EVENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CATCH_EVENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_CHOREOGRAPHY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__COMPLEX_GATEWAY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OBJECT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STORE_REFERENCE
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__END_EVENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__THROW_EVENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_BASED_GATEWAY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__INCLUSIVE_GATEWAY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__PARALLEL_GATEWAY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__SEQUENCE_FLOW
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__START_EVENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_CHOREOGRAPHY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__ARTIFACT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__ASSOCIATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__GROUP
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT_ANNOTATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__ASSIGNMENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__AUDITING
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_CONVERSATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_NODE
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CATEGORY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CATEGORY_VALUE
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__COLLABORATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_LINK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_KEY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_ASSOCIATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_INPUT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OUTPUT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STATE
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STORE
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__DEFINITIONS
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__DOCUMENTATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__END_POINT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__ERROR
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__EXPRESSION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__FORMAL_EXPRESSION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_CONVERSATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_USER_TASK
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ROLE
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__PERFORMER
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__HUMAN_PERFORMER
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__IO_SPECIFICATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__INPUT_SET
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__INTERFACE
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__ITEM_DEFINITION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__LANE
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__LANE_SET
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__LINK_EVENT_DEFINITION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_FLOW
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__MONITORING
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__LOOP_CHARACTERISTICS
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__OPERATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__OUTPUT_SET
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__PARTNER_ENTITY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__PARTNER_ROLE
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__POTENTIAL_OWNER
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__PROCESS
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__PROPERTY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__RELATIONSHIP
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__RENDERING
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__SIGNAL
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_CONVERSATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__ESCALATION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__IMPORT
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__IO_BINDING
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION
				|| childFeature == Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING;

		if (qualify) {
			return getString("_UI_CreateChild_text2", new Object[] {
					getTypeText(childObject), getFeatureText(childFeature),
					getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return bpmn2EditPlugin.INSTANCE;
	}

}
