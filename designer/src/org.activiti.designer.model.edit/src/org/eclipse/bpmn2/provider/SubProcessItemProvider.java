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
import org.eclipse.bpmn2.SubProcess;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.securebpmn2.Securebpmn2Factory;

/**
 * This is the item provider adapter for a {@link org.eclipse.bpmn2.SubProcess} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SubProcessItemProvider extends ActivityItemProvider implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubProcessItemProvider(AdapterFactory adapterFactory) {
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

			addTriggeredByEventPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Triggered By Event feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTriggeredByEventPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_SubProcess_triggeredByEvent_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_SubProcess_triggeredByEvent_feature",
						"_UI_SubProcess_type"),
				Bpmn2Package.Literals.SUB_PROCESS__TRIGGERED_BY_EVENT, true,
				false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null,
				null));
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
			childrenFeatures
					.add(Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__LANE_SETS);
			childrenFeatures
					.add(Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS);
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
	 * This returns SubProcess.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object,
				getResourceLocator().getImage("full/obj16/SubProcess"));
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
		String label = ((SubProcess) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_SubProcess_type")
				: getString("_UI_SubProcess_type") + " " + label;
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

		switch (notification.getFeatureID(SubProcess.class)) {
		case Bpmn2Package.SUB_PROCESS__TRIGGERED_BY_EVENT:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), false, true));
			return;
		case Bpmn2Package.SUB_PROCESS__LANE_SETS:
		case Bpmn2Package.SUB_PROCESS__FLOW_ELEMENTS:
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
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__LANE_SETS,
				Bpmn2Factory.eINSTANCE.createLaneSet()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createAdHocSubProcess()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createBoundaryEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createBusinessRuleTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCallActivity()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createCallChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createChoreographyTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createComplexGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataObject()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataObjectReference()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createDataStoreReference()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createEndEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createEventBasedGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createExclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createImplicitThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createInclusiveGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createIntermediateCatchEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createIntermediateThrowEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createManualTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createParallelGateway()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createReceiveTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createSendTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createSequenceFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createServiceTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createSubChoreography()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createTransaction()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createAlfrescoUserTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createAlfrescoStartEvent()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createAlfrescoScriptTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Bpmn2Factory.eINSTANCE.createAlfrescoMailTask()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createSecurityFlowNode()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createSecurityFlow()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createRole()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createAuthorizationConstraint()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createSeparationOfDuty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Securebpmn2Factory.eINSTANCE.createBindingOfDuty()));

		newChildDescriptors.add(createChildParameter(
				Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS,
				Securebpmn2Factory.eINSTANCE
						.createActivityAuthorizationConstraint()));
	}

}