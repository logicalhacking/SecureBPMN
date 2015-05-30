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
package org.eclipse.bpmn2.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>bpmn2</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class Bpmn2Tests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new Bpmn2Tests("bpmn2 Tests");
		suite.addTestSuite(DocumentRootTest.class);
		suite.addTestSuite(AdHocSubProcessTest.class);
		suite.addTestSuite(AssignmentTest.class);
		suite.addTestSuite(AssociationTest.class);
		suite.addTestSuite(AuditingTest.class);
		suite.addTestSuite(BoundaryEventTest.class);
		suite.addTestSuite(BusinessRuleTaskTest.class);
		suite.addTestSuite(CallActivityTest.class);
		suite.addTestSuite(CallChoreographyTest.class);
		suite.addTestSuite(CallConversationTest.class);
		suite.addTestSuite(CancelEventDefinitionTest.class);
		suite.addTestSuite(CategoryTest.class);
		suite.addTestSuite(CategoryValueTest.class);
		suite.addTestSuite(ChoreographyTest.class);
		suite.addTestSuite(ChoreographyTaskTest.class);
		suite.addTestSuite(CollaborationTest.class);
		suite.addTestSuite(CompensateEventDefinitionTest.class);
		suite.addTestSuite(ComplexBehaviorDefinitionTest.class);
		suite.addTestSuite(ComplexGatewayTest.class);
		suite.addTestSuite(ConditionalEventDefinitionTest.class);
		suite.addTestSuite(ConversationTest.class);
		suite.addTestSuite(ConversationAssociationTest.class);
		suite.addTestSuite(ConversationLinkTest.class);
		suite.addTestSuite(CorrelationKeyTest.class);
		suite.addTestSuite(CorrelationPropertyTest.class);
		suite.addTestSuite(CorrelationPropertyBindingTest.class);
		suite.addTestSuite(CorrelationPropertyRetrievalExpressionTest.class);
		suite.addTestSuite(CorrelationSubscriptionTest.class);
		suite.addTestSuite(DataAssociationTest.class);
		suite.addTestSuite(DataInputTest.class);
		suite.addTestSuite(DataInputAssociationTest.class);
		suite.addTestSuite(DataObjectTest.class);
		suite.addTestSuite(DataObjectReferenceTest.class);
		suite.addTestSuite(DataOutputTest.class);
		suite.addTestSuite(DataOutputAssociationTest.class);
		suite.addTestSuite(DataStateTest.class);
		suite.addTestSuite(DataStoreTest.class);
		suite.addTestSuite(DataStoreReferenceTest.class);
		suite.addTestSuite(DefinitionsTest.class);
		suite.addTestSuite(DocumentationTest.class);
		suite.addTestSuite(EndEventTest.class);
		suite.addTestSuite(EndPointTest.class);
		suite.addTestSuite(ErrorTest.class);
		suite.addTestSuite(ErrorEventDefinitionTest.class);
		suite.addTestSuite(EscalationEventDefinitionTest.class);
		suite.addTestSuite(EventBasedGatewayTest.class);
		suite.addTestSuite(EventDefinitionTest.class);
		suite.addTestSuite(ExclusiveGatewayTest.class);
		suite.addTestSuite(ExpressionTest.class);
		suite.addTestSuite(ExtensionAttributeDefinitionTest.class);
		suite.addTestSuite(ExtensionAttributeValueTest.class);
		suite.addTestSuite(ExtensionDefinitionTest.class);
		suite.addTestSuite(FormalExpressionTest.class);
		suite.addTestSuite(GlobalBusinessRuleTaskTest.class);
		suite.addTestSuite(GlobalChoreographyTaskTest.class);
		suite.addTestSuite(GlobalConversationTest.class);
		suite.addTestSuite(GlobalManualTaskTest.class);
		suite.addTestSuite(GlobalScriptTaskTest.class);
		suite.addTestSuite(GlobalTaskTest.class);
		suite.addTestSuite(GlobalUserTaskTest.class);
		suite.addTestSuite(GroupTest.class);
		suite.addTestSuite(HumanPerformerTest.class);
		suite.addTestSuite(ImplicitThrowEventTest.class);
		suite.addTestSuite(InclusiveGatewayTest.class);
		suite.addTestSuite(InputOutputSpecificationTest.class);
		suite.addTestSuite(InputSetTest.class);
		suite.addTestSuite(InterfaceTest.class);
		suite.addTestSuite(IntermediateCatchEventTest.class);
		suite.addTestSuite(IntermediateThrowEventTest.class);
		suite.addTestSuite(ItemAwareElementTest.class);
		suite.addTestSuite(ItemDefinitionTest.class);
		suite.addTestSuite(LaneTest.class);
		suite.addTestSuite(LaneSetTest.class);
		suite.addTestSuite(LinkEventDefinitionTest.class);
		suite.addTestSuite(ManualTaskTest.class);
		suite.addTestSuite(MessageTest.class);
		suite.addTestSuite(MessageEventDefinitionTest.class);
		suite.addTestSuite(MessageFlowTest.class);
		suite.addTestSuite(MessageFlowAssociationTest.class);
		suite.addTestSuite(MonitoringTest.class);
		suite.addTestSuite(MultiInstanceLoopCharacteristicsTest.class);
		suite.addTestSuite(OperationTest.class);
		suite.addTestSuite(OutputSetTest.class);
		suite.addTestSuite(ParallelGatewayTest.class);
		suite.addTestSuite(ParticipantTest.class);
		suite.addTestSuite(ParticipantAssociationTest.class);
		suite.addTestSuite(PartnerEntityTest.class);
		suite.addTestSuite(PartnerRoleTest.class);
		suite.addTestSuite(PerformerTest.class);
		suite.addTestSuite(PotentialOwnerTest.class);
		suite.addTestSuite(ProcessTest.class);
		suite.addTestSuite(PropertyTest.class);
		suite.addTestSuite(ReceiveTaskTest.class);
		suite.addTestSuite(RelationshipTest.class);
		suite.addTestSuite(RenderingTest.class);
		suite.addTestSuite(ResourceTest.class);
		suite.addTestSuite(ResourceParameterTest.class);
		suite.addTestSuite(ResourceRoleTest.class);
		suite.addTestSuite(ScriptTaskTest.class);
		suite.addTestSuite(SendTaskTest.class);
		suite.addTestSuite(SequenceFlowTest.class);
		suite.addTestSuite(ServiceTaskTest.class);
		suite.addTestSuite(SignalTest.class);
		suite.addTestSuite(SignalEventDefinitionTest.class);
		suite.addTestSuite(StandardLoopCharacteristicsTest.class);
		suite.addTestSuite(StartEventTest.class);
		suite.addTestSuite(SubChoreographyTest.class);
		suite.addTestSuite(SubConversationTest.class);
		suite.addTestSuite(SubProcessTest.class);
		suite.addTestSuite(TaskTest.class);
		suite.addTestSuite(TerminateEventDefinitionTest.class);
		suite.addTestSuite(TextAnnotationTest.class);
		suite.addTestSuite(TimerEventDefinitionTest.class);
		suite.addTestSuite(TransactionTest.class);
		suite.addTestSuite(UserTaskTest.class);
		suite.addTestSuite(CandidateUserTest.class);
		suite.addTestSuite(CandidateGroupTest.class);
		suite.addTestSuite(CustomPropertyTest.class);
		suite.addTestSuite(MailTaskTest.class);
		suite.addTestSuite(FieldExtensionTest.class);
		suite.addTestSuite(DataGridTest.class);
		suite.addTestSuite(DataGridRowTest.class);
		suite.addTestSuite(DataGridFieldTest.class);
		suite.addTestSuite(ActivitiListenerTest.class);
		suite.addTestSuite(FormPropertyTest.class);
		suite.addTestSuite(IOParameterTest.class);
		suite.addTestSuite(AlfrescoUserTaskTest.class);
		suite.addTestSuite(AlfrescoStartEventTest.class);
		suite.addTestSuite(AlfrescoScriptTaskTest.class);
		suite.addTestSuite(AlfrescoScriptBaseTest.class);
		suite.addTestSuite(AlfrescoMailTaskTest.class);
		suite.addTestSuite(FormValueTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Bpmn2Tests(String name) {
		super(name);
	}

} //Bpmn2Tests
