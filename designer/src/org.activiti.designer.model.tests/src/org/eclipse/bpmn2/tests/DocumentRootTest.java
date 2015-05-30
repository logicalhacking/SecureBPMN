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

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.DocumentRoot;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getActivity() <em>Activity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getAdHocSubProcess() <em>Ad Hoc Sub Process</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getFlowElement() <em>Flow Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getArtifact() <em>Artifact</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getAssignment() <em>Assignment</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getAssociation() <em>Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getAuditing() <em>Auditing</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getBaseElement() <em>Base Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getBaseElementWithMixedContent() <em>Base Element With Mixed Content</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getBoundaryEvent() <em>Boundary Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getBusinessRuleTask() <em>Business Rule Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCallableElement() <em>Callable Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCallActivity() <em>Call Activity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCallChoreography() <em>Call Choreography</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCallConversation() <em>Call Conversation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getConversationNode() <em>Conversation Node</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCancelEventDefinition() <em>Cancel Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getEventDefinition() <em>Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getRootElement() <em>Root Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCatchEvent() <em>Catch Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCategory() <em>Category</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCategoryValue() <em>Category Value</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getChoreography() <em>Choreography</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCollaboration() <em>Collaboration</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getChoreographyActivity() <em>Choreography Activity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getChoreographyTask() <em>Choreography Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCompensateEventDefinition() <em>Compensate Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getComplexBehaviorDefinition() <em>Complex Behavior Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getComplexGateway() <em>Complex Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getConditionalEventDefinition() <em>Conditional Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getConversation() <em>Conversation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getConversationAssociation() <em>Conversation Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getConversationLink() <em>Conversation Link</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationKey() <em>Correlation Key</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationProperty() <em>Correlation Property</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationPropertyBinding() <em>Correlation Property Binding</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationPropertyRetrievalExpression() <em>Correlation Property Retrieval Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationSubscription() <em>Correlation Subscription</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataAssociation() <em>Data Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataInput() <em>Data Input</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataInputAssociation() <em>Data Input Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataObject() <em>Data Object</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataObjectReference() <em>Data Object Reference</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataOutput() <em>Data Output</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataOutputAssociation() <em>Data Output Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataState() <em>Data State</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataStore() <em>Data Store</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataStoreReference() <em>Data Store Reference</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDefinitions() <em>Definitions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDocumentation() <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getEndEvent() <em>End Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getEndPoint() <em>End Point</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getError() <em>Error</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getErrorEventDefinition() <em>Error Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getEscalation() <em>Escalation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getEscalationEventDefinition() <em>Escalation Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getEvent() <em>Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getEventBasedGateway() <em>Event Based Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getExclusiveGateway() <em>Exclusive Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getExpression() <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getExtension() <em>Extension</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getExtensionElements() <em>Extension Elements</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getFlowNode() <em>Flow Node</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getFormalExpression() <em>Formal Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGateway() <em>Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGlobalBusinessRuleTask() <em>Global Business Rule Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGlobalChoreographyTask() <em>Global Choreography Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGlobalConversation() <em>Global Conversation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGlobalManualTask() <em>Global Manual Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGlobalScriptTask() <em>Global Script Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGlobalTask() <em>Global Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGlobalUserTask() <em>Global User Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGroup() <em>Group</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getHumanPerformer() <em>Human Performer</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getPerformer() <em>Performer</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getResourceRole() <em>Resource Role</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getImplicitThrowEvent() <em>Implicit Throw Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getImport() <em>Import</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getInclusiveGateway() <em>Inclusive Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getInputSet() <em>Input Set</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getInterface() <em>Interface</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getIntermediateCatchEvent() <em>Intermediate Catch Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getIntermediateThrowEvent() <em>Intermediate Throw Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getIoBinding() <em>Io Binding</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getIoSpecification() <em>Io Specification</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getItemDefinition() <em>Item Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getLane() <em>Lane</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getLaneSet() <em>Lane Set</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getLinkEventDefinition() <em>Link Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getLoopCharacteristics() <em>Loop Characteristics</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getManualTask() <em>Manual Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getMessage() <em>Message</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getMessageEventDefinition() <em>Message Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getMessageFlow() <em>Message Flow</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getMessageFlowAssociation() <em>Message Flow Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getMonitoring() <em>Monitoring</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getMultiInstanceLoopCharacteristics() <em>Multi Instance Loop Characteristics</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getOperation() <em>Operation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getOutputSet() <em>Output Set</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getParallelGateway() <em>Parallel Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getParticipant() <em>Participant</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getParticipantAssociation() <em>Participant Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getParticipantMultiplicity() <em>Participant Multiplicity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getPartnerEntity() <em>Partner Entity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getPartnerRole() <em>Partner Role</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getPotentialOwner() <em>Potential Owner</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getProcess() <em>Process</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getProperty() <em>Property</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getReceiveTask() <em>Receive Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getRelationship() <em>Relationship</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getRendering() <em>Rendering</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getResource() <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getResourceAssignmentExpression() <em>Resource Assignment Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getResourceParameter() <em>Resource Parameter</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getResourceParameterBinding() <em>Resource Parameter Binding</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getScript() <em>Script</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getScriptTask() <em>Script Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getSendTask() <em>Send Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getSequenceFlow() <em>Sequence Flow</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getServiceTask() <em>Service Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getSignal() <em>Signal</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getSignalEventDefinition() <em>Signal Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getStandardLoopCharacteristics() <em>Standard Loop Characteristics</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getStartEvent() <em>Start Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getSubChoreography() <em>Sub Choreography</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getSubConversation() <em>Sub Conversation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getSubProcess() <em>Sub Process</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getTask() <em>Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getTerminateEventDefinition() <em>Terminate Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getText() <em>Text</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getTextAnnotation() <em>Text Annotation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getThrowEvent() <em>Throw Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getTimerEventDefinition() <em>Timer Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getTransaction() <em>Transaction</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getUserTask() <em>User Task</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class DocumentRootTest extends TestCase {

	/**
	 * The fixture for this Document Root test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRoot fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(DocumentRootTest.class);
	}

	/**
	 * Constructs a new Document Root test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRootTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Document Root test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(DocumentRoot fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Document Root test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRoot getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(Bpmn2Factory.eINSTANCE.createDocumentRoot());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getActivity() <em>Activity</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getActivity()
	 * @generated
	 */
	public void testGetActivity() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setActivity(org.eclipse.bpmn2.Activity) <em>Activity</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setActivity(org.eclipse.bpmn2.Activity)
	 * @generated
	 */
	public void testSetActivity() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getAdHocSubProcess() <em>Ad Hoc Sub Process</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getAdHocSubProcess()
	 * @generated
	 */
	public void testGetAdHocSubProcess() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setAdHocSubProcess(org.eclipse.bpmn2.AdHocSubProcess) <em>Ad Hoc Sub Process</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setAdHocSubProcess(org.eclipse.bpmn2.AdHocSubProcess)
	 * @generated
	 */
	public void testSetAdHocSubProcess() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getFlowElement() <em>Flow Element</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getFlowElement()
	 * @generated
	 */
	public void testGetFlowElement() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setFlowElement(org.eclipse.bpmn2.FlowElement) <em>Flow Element</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setFlowElement(org.eclipse.bpmn2.FlowElement)
	 * @generated
	 */
	public void testSetFlowElement() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getArtifact() <em>Artifact</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getArtifact()
	 * @generated
	 */
	public void testGetArtifact() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setArtifact(org.eclipse.bpmn2.Artifact) <em>Artifact</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setArtifact(org.eclipse.bpmn2.Artifact)
	 * @generated
	 */
	public void testSetArtifact() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getAssignment() <em>Assignment</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getAssignment()
	 * @generated
	 */
	public void testGetAssignment() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setAssignment(org.eclipse.bpmn2.Assignment) <em>Assignment</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setAssignment(org.eclipse.bpmn2.Assignment)
	 * @generated
	 */
	public void testSetAssignment() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getAssociation() <em>Association</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getAssociation()
	 * @generated
	 */
	public void testGetAssociation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setAssociation(org.eclipse.bpmn2.Association) <em>Association</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setAssociation(org.eclipse.bpmn2.Association)
	 * @generated
	 */
	public void testSetAssociation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getAuditing() <em>Auditing</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getAuditing()
	 * @generated
	 */
	public void testGetAuditing() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setAuditing(org.eclipse.bpmn2.Auditing) <em>Auditing</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setAuditing(org.eclipse.bpmn2.Auditing)
	 * @generated
	 */
	public void testSetAuditing() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getBaseElement() <em>Base Element</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getBaseElement()
	 * @generated
	 */
	public void testGetBaseElement() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setBaseElement(org.eclipse.bpmn2.BaseElement) <em>Base Element</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setBaseElement(org.eclipse.bpmn2.BaseElement)
	 * @generated
	 */
	public void testSetBaseElement() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getBaseElementWithMixedContent() <em>Base Element With Mixed Content</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getBaseElementWithMixedContent()
	 * @generated
	 */
	public void testGetBaseElementWithMixedContent() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setBaseElementWithMixedContent(java.lang.Object) <em>Base Element With Mixed Content</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setBaseElementWithMixedContent(java.lang.Object)
	 * @generated
	 */
	public void testSetBaseElementWithMixedContent() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getBoundaryEvent() <em>Boundary Event</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getBoundaryEvent()
	 * @generated
	 */
	public void testGetBoundaryEvent() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setBoundaryEvent(org.eclipse.bpmn2.BoundaryEvent) <em>Boundary Event</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setBoundaryEvent(org.eclipse.bpmn2.BoundaryEvent)
	 * @generated
	 */
	public void testSetBoundaryEvent() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getBusinessRuleTask() <em>Business Rule Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getBusinessRuleTask()
	 * @generated
	 */
	public void testGetBusinessRuleTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setBusinessRuleTask(org.eclipse.bpmn2.BusinessRuleTask) <em>Business Rule Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setBusinessRuleTask(org.eclipse.bpmn2.BusinessRuleTask)
	 * @generated
	 */
	public void testSetBusinessRuleTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCallableElement() <em>Callable Element</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCallableElement()
	 * @generated
	 */
	public void testGetCallableElement() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCallableElement(org.eclipse.bpmn2.CallableElement) <em>Callable Element</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCallableElement(org.eclipse.bpmn2.CallableElement)
	 * @generated
	 */
	public void testSetCallableElement() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCallActivity() <em>Call Activity</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCallActivity()
	 * @generated
	 */
	public void testGetCallActivity() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCallActivity(org.eclipse.bpmn2.CallActivity) <em>Call Activity</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCallActivity(org.eclipse.bpmn2.CallActivity)
	 * @generated
	 */
	public void testSetCallActivity() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCallChoreography() <em>Call Choreography</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCallChoreography()
	 * @generated
	 */
	public void testGetCallChoreography() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCallChoreography(org.eclipse.bpmn2.CallChoreography) <em>Call Choreography</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCallChoreography(org.eclipse.bpmn2.CallChoreography)
	 * @generated
	 */
	public void testSetCallChoreography() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCallConversation() <em>Call Conversation</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCallConversation()
	 * @generated
	 */
	public void testGetCallConversation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCallConversation(org.eclipse.bpmn2.CallConversation) <em>Call Conversation</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCallConversation(org.eclipse.bpmn2.CallConversation)
	 * @generated
	 */
	public void testSetCallConversation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getConversationNode() <em>Conversation Node</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getConversationNode()
	 * @generated
	 */
	public void testGetConversationNode() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setConversationNode(org.eclipse.bpmn2.ConversationNode) <em>Conversation Node</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setConversationNode(org.eclipse.bpmn2.ConversationNode)
	 * @generated
	 */
	public void testSetConversationNode() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCancelEventDefinition() <em>Cancel Event Definition</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCancelEventDefinition()
	 * @generated
	 */
	public void testGetCancelEventDefinition() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCancelEventDefinition(org.eclipse.bpmn2.CancelEventDefinition) <em>Cancel Event Definition</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCancelEventDefinition(org.eclipse.bpmn2.CancelEventDefinition)
	 * @generated
	 */
	public void testSetCancelEventDefinition() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getEventDefinition() <em>Event Definition</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getEventDefinition()
	 * @generated
	 */
	public void testGetEventDefinition() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setEventDefinition(org.eclipse.bpmn2.EventDefinition) <em>Event Definition</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setEventDefinition(org.eclipse.bpmn2.EventDefinition)
	 * @generated
	 */
	public void testSetEventDefinition() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getRootElement() <em>Root Element</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getRootElement()
	 * @generated
	 */
	public void testGetRootElement() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setRootElement(org.eclipse.bpmn2.RootElement) <em>Root Element</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setRootElement(org.eclipse.bpmn2.RootElement)
	 * @generated
	 */
	public void testSetRootElement() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCatchEvent() <em>Catch Event</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCatchEvent()
	 * @generated
	 */
	public void testGetCatchEvent() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCatchEvent(org.eclipse.bpmn2.CatchEvent) <em>Catch Event</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCatchEvent(org.eclipse.bpmn2.CatchEvent)
	 * @generated
	 */
	public void testSetCatchEvent() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCategory() <em>Category</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCategory()
	 * @generated
	 */
	public void testGetCategory() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCategory(org.eclipse.bpmn2.Category) <em>Category</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCategory(org.eclipse.bpmn2.Category)
	 * @generated
	 */
	public void testSetCategory() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCategoryValue() <em>Category Value</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCategoryValue()
	 * @generated
	 */
	public void testGetCategoryValue() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCategoryValue(org.eclipse.bpmn2.CategoryValue) <em>Category Value</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCategoryValue(org.eclipse.bpmn2.CategoryValue)
	 * @generated
	 */
	public void testSetCategoryValue() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getChoreography() <em>Choreography</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getChoreography()
	 * @generated
	 */
	public void testGetChoreography() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setChoreography(org.eclipse.bpmn2.Choreography) <em>Choreography</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setChoreography(org.eclipse.bpmn2.Choreography)
	 * @generated
	 */
	public void testSetChoreography() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCollaboration() <em>Collaboration</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCollaboration()
	 * @generated
	 */
	public void testGetCollaboration() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCollaboration(org.eclipse.bpmn2.Collaboration) <em>Collaboration</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCollaboration(org.eclipse.bpmn2.Collaboration)
	 * @generated
	 */
	public void testSetCollaboration() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getChoreographyActivity() <em>Choreography Activity</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getChoreographyActivity()
	 * @generated
	 */
	public void testGetChoreographyActivity() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setChoreographyActivity(org.eclipse.bpmn2.ChoreographyActivity) <em>Choreography Activity</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setChoreographyActivity(org.eclipse.bpmn2.ChoreographyActivity)
	 * @generated
	 */
	public void testSetChoreographyActivity() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getChoreographyTask() <em>Choreography Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getChoreographyTask()
	 * @generated
	 */
	public void testGetChoreographyTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setChoreographyTask(org.eclipse.bpmn2.ChoreographyTask) <em>Choreography Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setChoreographyTask(org.eclipse.bpmn2.ChoreographyTask)
	 * @generated
	 */
	public void testSetChoreographyTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCompensateEventDefinition() <em>Compensate Event Definition</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCompensateEventDefinition()
	 * @generated
	 */
	public void testGetCompensateEventDefinition() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCompensateEventDefinition(org.eclipse.bpmn2.CompensateEventDefinition) <em>Compensate Event Definition</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCompensateEventDefinition(org.eclipse.bpmn2.CompensateEventDefinition)
	 * @generated
	 */
	public void testSetCompensateEventDefinition() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getComplexBehaviorDefinition() <em>Complex Behavior Definition</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getComplexBehaviorDefinition()
	 * @generated
	 */
	public void testGetComplexBehaviorDefinition() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setComplexBehaviorDefinition(org.eclipse.bpmn2.ComplexBehaviorDefinition) <em>Complex Behavior Definition</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setComplexBehaviorDefinition(org.eclipse.bpmn2.ComplexBehaviorDefinition)
	 * @generated
	 */
	public void testSetComplexBehaviorDefinition() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getComplexGateway() <em>Complex Gateway</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getComplexGateway()
	 * @generated
	 */
	public void testGetComplexGateway() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setComplexGateway(org.eclipse.bpmn2.ComplexGateway) <em>Complex Gateway</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setComplexGateway(org.eclipse.bpmn2.ComplexGateway)
	 * @generated
	 */
	public void testSetComplexGateway() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getConditionalEventDefinition() <em>Conditional Event Definition</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getConditionalEventDefinition()
	 * @generated
	 */
	public void testGetConditionalEventDefinition() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setConditionalEventDefinition(org.eclipse.bpmn2.ConditionalEventDefinition) <em>Conditional Event Definition</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setConditionalEventDefinition(org.eclipse.bpmn2.ConditionalEventDefinition)
	 * @generated
	 */
	public void testSetConditionalEventDefinition() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getConversation() <em>Conversation</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getConversation()
	 * @generated
	 */
	public void testGetConversation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setConversation(org.eclipse.bpmn2.Conversation) <em>Conversation</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setConversation(org.eclipse.bpmn2.Conversation)
	 * @generated
	 */
	public void testSetConversation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getConversationAssociation() <em>Conversation Association</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getConversationAssociation()
	 * @generated
	 */
	public void testGetConversationAssociation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setConversationAssociation(org.eclipse.bpmn2.ConversationAssociation) <em>Conversation Association</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setConversationAssociation(org.eclipse.bpmn2.ConversationAssociation)
	 * @generated
	 */
	public void testSetConversationAssociation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getConversationLink() <em>Conversation Link</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getConversationLink()
	 * @generated
	 */
	public void testGetConversationLink() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setConversationLink(org.eclipse.bpmn2.ConversationLink) <em>Conversation Link</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setConversationLink(org.eclipse.bpmn2.ConversationLink)
	 * @generated
	 */
	public void testSetConversationLink() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationKey() <em>Correlation Key</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCorrelationKey()
	 * @generated
	 */
	public void testGetCorrelationKey() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCorrelationKey(org.eclipse.bpmn2.CorrelationKey) <em>Correlation Key</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCorrelationKey(org.eclipse.bpmn2.CorrelationKey)
	 * @generated
	 */
	public void testSetCorrelationKey() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationProperty() <em>Correlation Property</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCorrelationProperty()
	 * @generated
	 */
	public void testGetCorrelationProperty() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCorrelationProperty(org.eclipse.bpmn2.CorrelationProperty) <em>Correlation Property</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCorrelationProperty(org.eclipse.bpmn2.CorrelationProperty)
	 * @generated
	 */
	public void testSetCorrelationProperty() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationPropertyBinding() <em>Correlation Property Binding</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCorrelationPropertyBinding()
	 * @generated
	 */
	public void testGetCorrelationPropertyBinding() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCorrelationPropertyBinding(org.eclipse.bpmn2.CorrelationPropertyBinding) <em>Correlation Property Binding</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCorrelationPropertyBinding(org.eclipse.bpmn2.CorrelationPropertyBinding)
	 * @generated
	 */
	public void testSetCorrelationPropertyBinding() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationPropertyRetrievalExpression() <em>Correlation Property Retrieval Expression</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCorrelationPropertyRetrievalExpression()
	 * @generated
	 */
	public void testGetCorrelationPropertyRetrievalExpression() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCorrelationPropertyRetrievalExpression(org.eclipse.bpmn2.CorrelationPropertyRetrievalExpression) <em>Correlation Property Retrieval Expression</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCorrelationPropertyRetrievalExpression(org.eclipse.bpmn2.CorrelationPropertyRetrievalExpression)
	 * @generated
	 */
	public void testSetCorrelationPropertyRetrievalExpression() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationSubscription() <em>Correlation Subscription</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getCorrelationSubscription()
	 * @generated
	 */
	public void testGetCorrelationSubscription() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setCorrelationSubscription(org.eclipse.bpmn2.CorrelationSubscription) <em>Correlation Subscription</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setCorrelationSubscription(org.eclipse.bpmn2.CorrelationSubscription)
	 * @generated
	 */
	public void testSetCorrelationSubscription() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getDataAssociation() <em>Data Association</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getDataAssociation()
	 * @generated
	 */
	public void testGetDataAssociation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setDataAssociation(org.eclipse.bpmn2.DataAssociation) <em>Data Association</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setDataAssociation(org.eclipse.bpmn2.DataAssociation)
	 * @generated
	 */
	public void testSetDataAssociation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getDataInput() <em>Data Input</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getDataInput()
	 * @generated
	 */
	public void testGetDataInput() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setDataInput(org.eclipse.bpmn2.DataInput) <em>Data Input</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setDataInput(org.eclipse.bpmn2.DataInput)
	 * @generated
	 */
	public void testSetDataInput() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getDataInputAssociation() <em>Data Input Association</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getDataInputAssociation()
	 * @generated
	 */
	public void testGetDataInputAssociation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setDataInputAssociation(org.eclipse.bpmn2.DataInputAssociation) <em>Data Input Association</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setDataInputAssociation(org.eclipse.bpmn2.DataInputAssociation)
	 * @generated
	 */
	public void testSetDataInputAssociation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getDataObject() <em>Data Object</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getDataObject()
	 * @generated
	 */
	public void testGetDataObject() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setDataObject(org.eclipse.bpmn2.DataObject) <em>Data Object</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setDataObject(org.eclipse.bpmn2.DataObject)
	 * @generated
	 */
	public void testSetDataObject() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getDataObjectReference() <em>Data Object Reference</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getDataObjectReference()
	 * @generated
	 */
	public void testGetDataObjectReference() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setDataObjectReference(org.eclipse.bpmn2.DataObjectReference) <em>Data Object Reference</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setDataObjectReference(org.eclipse.bpmn2.DataObjectReference)
	 * @generated
	 */
	public void testSetDataObjectReference() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getDataOutput() <em>Data Output</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getDataOutput()
	 * @generated
	 */
	public void testGetDataOutput() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setDataOutput(org.eclipse.bpmn2.DataOutput) <em>Data Output</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setDataOutput(org.eclipse.bpmn2.DataOutput)
	 * @generated
	 */
	public void testSetDataOutput() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getDataOutputAssociation() <em>Data Output Association</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getDataOutputAssociation()
	 * @generated
	 */
	public void testGetDataOutputAssociation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setDataOutputAssociation(org.eclipse.bpmn2.DataOutputAssociation) <em>Data Output Association</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setDataOutputAssociation(org.eclipse.bpmn2.DataOutputAssociation)
	 * @generated
	 */
	public void testSetDataOutputAssociation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getDataState() <em>Data State</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getDataState()
	 * @generated
	 */
	public void testGetDataState() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setDataState(org.eclipse.bpmn2.DataState) <em>Data State</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setDataState(org.eclipse.bpmn2.DataState)
	 * @generated
	 */
	public void testSetDataState() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getDataStore() <em>Data Store</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getDataStore()
	 * @generated
	 */
	public void testGetDataStore() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setDataStore(org.eclipse.bpmn2.DataStore) <em>Data Store</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setDataStore(org.eclipse.bpmn2.DataStore)
	 * @generated
	 */
	public void testSetDataStore() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getDataStoreReference() <em>Data Store Reference</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getDataStoreReference()
	 * @generated
	 */
	public void testGetDataStoreReference() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setDataStoreReference(org.eclipse.bpmn2.DataStoreReference) <em>Data Store Reference</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setDataStoreReference(org.eclipse.bpmn2.DataStoreReference)
	 * @generated
	 */
	public void testSetDataStoreReference() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getDefinitions() <em>Definitions</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getDefinitions()
	 * @generated
	 */
	public void testGetDefinitions() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setDefinitions(org.eclipse.bpmn2.Definitions) <em>Definitions</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setDefinitions(org.eclipse.bpmn2.Definitions)
	 * @generated
	 */
	public void testSetDefinitions() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getDocumentation() <em>Documentation</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getDocumentation()
	 * @generated
	 */
	public void testGetDocumentation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setDocumentation(org.eclipse.bpmn2.Documentation) <em>Documentation</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setDocumentation(org.eclipse.bpmn2.Documentation)
	 * @generated
	 */
	public void testSetDocumentation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getEndEvent() <em>End Event</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getEndEvent()
	 * @generated
	 */
	public void testGetEndEvent() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setEndEvent(org.eclipse.bpmn2.EndEvent) <em>End Event</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setEndEvent(org.eclipse.bpmn2.EndEvent)
	 * @generated
	 */
	public void testSetEndEvent() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getEndPoint() <em>End Point</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getEndPoint()
	 * @generated
	 */
	public void testGetEndPoint() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setEndPoint(org.eclipse.bpmn2.EndPoint) <em>End Point</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setEndPoint(org.eclipse.bpmn2.EndPoint)
	 * @generated
	 */
	public void testSetEndPoint() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getError() <em>Error</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getError()
	 * @generated
	 */
	public void testGetError() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setError(org.eclipse.bpmn2.Error) <em>Error</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setError(org.eclipse.bpmn2.Error)
	 * @generated
	 */
	public void testSetError() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getErrorEventDefinition() <em>Error Event Definition</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getErrorEventDefinition()
	 * @generated
	 */
	public void testGetErrorEventDefinition() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setErrorEventDefinition(org.eclipse.bpmn2.ErrorEventDefinition) <em>Error Event Definition</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setErrorEventDefinition(org.eclipse.bpmn2.ErrorEventDefinition)
	 * @generated
	 */
	public void testSetErrorEventDefinition() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getEscalation() <em>Escalation</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getEscalation()
	 * @generated
	 */
	public void testGetEscalation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setEscalation(org.eclipse.bpmn2.Escalation) <em>Escalation</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setEscalation(org.eclipse.bpmn2.Escalation)
	 * @generated
	 */
	public void testSetEscalation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getEscalationEventDefinition() <em>Escalation Event Definition</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getEscalationEventDefinition()
	 * @generated
	 */
	public void testGetEscalationEventDefinition() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setEscalationEventDefinition(org.eclipse.bpmn2.EscalationEventDefinition) <em>Escalation Event Definition</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setEscalationEventDefinition(org.eclipse.bpmn2.EscalationEventDefinition)
	 * @generated
	 */
	public void testSetEscalationEventDefinition() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getEvent() <em>Event</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getEvent()
	 * @generated
	 */
	public void testGetEvent() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setEvent(org.eclipse.bpmn2.Event) <em>Event</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setEvent(org.eclipse.bpmn2.Event)
	 * @generated
	 */
	public void testSetEvent() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getEventBasedGateway() <em>Event Based Gateway</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getEventBasedGateway()
	 * @generated
	 */
	public void testGetEventBasedGateway() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setEventBasedGateway(org.eclipse.bpmn2.EventBasedGateway) <em>Event Based Gateway</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setEventBasedGateway(org.eclipse.bpmn2.EventBasedGateway)
	 * @generated
	 */
	public void testSetEventBasedGateway() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getExclusiveGateway() <em>Exclusive Gateway</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getExclusiveGateway()
	 * @generated
	 */
	public void testGetExclusiveGateway() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setExclusiveGateway(org.eclipse.bpmn2.ExclusiveGateway) <em>Exclusive Gateway</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setExclusiveGateway(org.eclipse.bpmn2.ExclusiveGateway)
	 * @generated
	 */
	public void testSetExclusiveGateway() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getExpression() <em>Expression</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getExpression()
	 * @generated
	 */
	public void testGetExpression() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setExpression(org.eclipse.bpmn2.Expression) <em>Expression</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setExpression(org.eclipse.bpmn2.Expression)
	 * @generated
	 */
	public void testSetExpression() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getExtension() <em>Extension</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getExtension()
	 * @generated
	 */
	public void testGetExtension() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setExtension(org.eclipse.bpmn2.Extension) <em>Extension</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setExtension(org.eclipse.bpmn2.Extension)
	 * @generated
	 */
	public void testSetExtension() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getExtensionElements() <em>Extension Elements</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getExtensionElements()
	 * @generated
	 */
	public void testGetExtensionElements() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setExtensionElements(java.lang.Object) <em>Extension Elements</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setExtensionElements(java.lang.Object)
	 * @generated
	 */
	public void testSetExtensionElements() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getFlowNode() <em>Flow Node</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getFlowNode()
	 * @generated
	 */
	public void testGetFlowNode() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setFlowNode(org.eclipse.bpmn2.FlowNode) <em>Flow Node</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setFlowNode(org.eclipse.bpmn2.FlowNode)
	 * @generated
	 */
	public void testSetFlowNode() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getFormalExpression() <em>Formal Expression</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getFormalExpression()
	 * @generated
	 */
	public void testGetFormalExpression() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setFormalExpression(org.eclipse.bpmn2.FormalExpression) <em>Formal Expression</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setFormalExpression(org.eclipse.bpmn2.FormalExpression)
	 * @generated
	 */
	public void testSetFormalExpression() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getGateway() <em>Gateway</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getGateway()
	 * @generated
	 */
	public void testGetGateway() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getGlobalBusinessRuleTask() <em>Global Business Rule Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getGlobalBusinessRuleTask()
	 * @generated
	 */
	public void testGetGlobalBusinessRuleTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setGlobalBusinessRuleTask(org.eclipse.bpmn2.GlobalBusinessRuleTask) <em>Global Business Rule Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setGlobalBusinessRuleTask(org.eclipse.bpmn2.GlobalBusinessRuleTask)
	 * @generated
	 */
	public void testSetGlobalBusinessRuleTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getGlobalChoreographyTask() <em>Global Choreography Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getGlobalChoreographyTask()
	 * @generated
	 */
	public void testGetGlobalChoreographyTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setGlobalChoreographyTask(org.eclipse.bpmn2.GlobalChoreographyTask) <em>Global Choreography Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setGlobalChoreographyTask(org.eclipse.bpmn2.GlobalChoreographyTask)
	 * @generated
	 */
	public void testSetGlobalChoreographyTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getGlobalConversation() <em>Global Conversation</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getGlobalConversation()
	 * @generated
	 */
	public void testGetGlobalConversation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setGlobalConversation(org.eclipse.bpmn2.GlobalConversation) <em>Global Conversation</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setGlobalConversation(org.eclipse.bpmn2.GlobalConversation)
	 * @generated
	 */
	public void testSetGlobalConversation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getGlobalManualTask() <em>Global Manual Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getGlobalManualTask()
	 * @generated
	 */
	public void testGetGlobalManualTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setGlobalManualTask(org.eclipse.bpmn2.GlobalManualTask) <em>Global Manual Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setGlobalManualTask(org.eclipse.bpmn2.GlobalManualTask)
	 * @generated
	 */
	public void testSetGlobalManualTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getGlobalScriptTask() <em>Global Script Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getGlobalScriptTask()
	 * @generated
	 */
	public void testGetGlobalScriptTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setGlobalScriptTask(org.eclipse.bpmn2.GlobalScriptTask) <em>Global Script Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setGlobalScriptTask(org.eclipse.bpmn2.GlobalScriptTask)
	 * @generated
	 */
	public void testSetGlobalScriptTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getGlobalTask() <em>Global Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getGlobalTask()
	 * @generated
	 */
	public void testGetGlobalTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setGlobalTask(org.eclipse.bpmn2.GlobalTask) <em>Global Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setGlobalTask(org.eclipse.bpmn2.GlobalTask)
	 * @generated
	 */
	public void testSetGlobalTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getGlobalUserTask() <em>Global User Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getGlobalUserTask()
	 * @generated
	 */
	public void testGetGlobalUserTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setGlobalUserTask(org.eclipse.bpmn2.GlobalUserTask) <em>Global User Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setGlobalUserTask(org.eclipse.bpmn2.GlobalUserTask)
	 * @generated
	 */
	public void testSetGlobalUserTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getGroup() <em>Group</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getGroup()
	 * @generated
	 */
	public void testGetGroup() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setGroup(org.eclipse.bpmn2.Group) <em>Group</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setGroup(org.eclipse.bpmn2.Group)
	 * @generated
	 */
	public void testSetGroup() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getHumanPerformer() <em>Human Performer</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getHumanPerformer()
	 * @generated
	 */
	public void testGetHumanPerformer() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setHumanPerformer(org.eclipse.bpmn2.HumanPerformer) <em>Human Performer</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setHumanPerformer(org.eclipse.bpmn2.HumanPerformer)
	 * @generated
	 */
	public void testSetHumanPerformer() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getPerformer() <em>Performer</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getPerformer()
	 * @generated
	 */
	public void testGetPerformer() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setPerformer(org.eclipse.bpmn2.Performer) <em>Performer</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setPerformer(org.eclipse.bpmn2.Performer)
	 * @generated
	 */
	public void testSetPerformer() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getResourceRole() <em>Resource Role</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getResourceRole()
	 * @generated
	 */
	public void testGetResourceRole() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setResourceRole(org.eclipse.bpmn2.ResourceRole) <em>Resource Role</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setResourceRole(org.eclipse.bpmn2.ResourceRole)
	 * @generated
	 */
	public void testSetResourceRole() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getImplicitThrowEvent() <em>Implicit Throw Event</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getImplicitThrowEvent()
	 * @generated
	 */
	public void testGetImplicitThrowEvent() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setImplicitThrowEvent(org.eclipse.bpmn2.ImplicitThrowEvent) <em>Implicit Throw Event</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setImplicitThrowEvent(org.eclipse.bpmn2.ImplicitThrowEvent)
	 * @generated
	 */
	public void testSetImplicitThrowEvent() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getImport() <em>Import</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getImport()
	 * @generated
	 */
	public void testGetImport() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setImport(org.eclipse.bpmn2.Import) <em>Import</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setImport(org.eclipse.bpmn2.Import)
	 * @generated
	 */
	public void testSetImport() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getInclusiveGateway() <em>Inclusive Gateway</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getInclusiveGateway()
	 * @generated
	 */
	public void testGetInclusiveGateway() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setInclusiveGateway(org.eclipse.bpmn2.InclusiveGateway) <em>Inclusive Gateway</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setInclusiveGateway(org.eclipse.bpmn2.InclusiveGateway)
	 * @generated
	 */
	public void testSetInclusiveGateway() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getInputSet() <em>Input Set</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getInputSet()
	 * @generated
	 */
	public void testGetInputSet() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setInputSet(org.eclipse.bpmn2.InputSet) <em>Input Set</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setInputSet(org.eclipse.bpmn2.InputSet)
	 * @generated
	 */
	public void testSetInputSet() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getInterface() <em>Interface</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getInterface()
	 * @generated
	 */
	public void testGetInterface() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setInterface(org.eclipse.bpmn2.Interface) <em>Interface</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setInterface(org.eclipse.bpmn2.Interface)
	 * @generated
	 */
	public void testSetInterface() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getIntermediateCatchEvent() <em>Intermediate Catch Event</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getIntermediateCatchEvent()
	 * @generated
	 */
	public void testGetIntermediateCatchEvent() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setIntermediateCatchEvent(org.eclipse.bpmn2.IntermediateCatchEvent) <em>Intermediate Catch Event</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setIntermediateCatchEvent(org.eclipse.bpmn2.IntermediateCatchEvent)
	 * @generated
	 */
	public void testSetIntermediateCatchEvent() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getIntermediateThrowEvent() <em>Intermediate Throw Event</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getIntermediateThrowEvent()
	 * @generated
	 */
	public void testGetIntermediateThrowEvent() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setIntermediateThrowEvent(org.eclipse.bpmn2.IntermediateThrowEvent) <em>Intermediate Throw Event</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setIntermediateThrowEvent(org.eclipse.bpmn2.IntermediateThrowEvent)
	 * @generated
	 */
	public void testSetIntermediateThrowEvent() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getIoBinding() <em>Io Binding</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getIoBinding()
	 * @generated
	 */
	public void testGetIoBinding() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setIoBinding(org.eclipse.bpmn2.InputOutputBinding) <em>Io Binding</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setIoBinding(org.eclipse.bpmn2.InputOutputBinding)
	 * @generated
	 */
	public void testSetIoBinding() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getIoSpecification() <em>Io Specification</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getIoSpecification()
	 * @generated
	 */
	public void testGetIoSpecification() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setIoSpecification(org.eclipse.bpmn2.InputOutputSpecification) <em>Io Specification</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setIoSpecification(org.eclipse.bpmn2.InputOutputSpecification)
	 * @generated
	 */
	public void testSetIoSpecification() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getItemDefinition() <em>Item Definition</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getItemDefinition()
	 * @generated
	 */
	public void testGetItemDefinition() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setItemDefinition(org.eclipse.bpmn2.ItemDefinition) <em>Item Definition</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setItemDefinition(org.eclipse.bpmn2.ItemDefinition)
	 * @generated
	 */
	public void testSetItemDefinition() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getLane() <em>Lane</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getLane()
	 * @generated
	 */
	public void testGetLane() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setLane(org.eclipse.bpmn2.Lane) <em>Lane</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setLane(org.eclipse.bpmn2.Lane)
	 * @generated
	 */
	public void testSetLane() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getLaneSet() <em>Lane Set</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getLaneSet()
	 * @generated
	 */
	public void testGetLaneSet() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setLaneSet(org.eclipse.bpmn2.LaneSet) <em>Lane Set</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setLaneSet(org.eclipse.bpmn2.LaneSet)
	 * @generated
	 */
	public void testSetLaneSet() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getLinkEventDefinition() <em>Link Event Definition</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getLinkEventDefinition()
	 * @generated
	 */
	public void testGetLinkEventDefinition() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setLinkEventDefinition(org.eclipse.bpmn2.LinkEventDefinition) <em>Link Event Definition</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setLinkEventDefinition(org.eclipse.bpmn2.LinkEventDefinition)
	 * @generated
	 */
	public void testSetLinkEventDefinition() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getLoopCharacteristics() <em>Loop Characteristics</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getLoopCharacteristics()
	 * @generated
	 */
	public void testGetLoopCharacteristics() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setLoopCharacteristics(org.eclipse.bpmn2.LoopCharacteristics) <em>Loop Characteristics</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setLoopCharacteristics(org.eclipse.bpmn2.LoopCharacteristics)
	 * @generated
	 */
	public void testSetLoopCharacteristics() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getManualTask() <em>Manual Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getManualTask()
	 * @generated
	 */
	public void testGetManualTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setManualTask(org.eclipse.bpmn2.ManualTask) <em>Manual Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setManualTask(org.eclipse.bpmn2.ManualTask)
	 * @generated
	 */
	public void testSetManualTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getMessage() <em>Message</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getMessage()
	 * @generated
	 */
	public void testGetMessage() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setMessage(org.eclipse.bpmn2.Message) <em>Message</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setMessage(org.eclipse.bpmn2.Message)
	 * @generated
	 */
	public void testSetMessage() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getMessageEventDefinition() <em>Message Event Definition</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getMessageEventDefinition()
	 * @generated
	 */
	public void testGetMessageEventDefinition() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setMessageEventDefinition(org.eclipse.bpmn2.MessageEventDefinition) <em>Message Event Definition</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setMessageEventDefinition(org.eclipse.bpmn2.MessageEventDefinition)
	 * @generated
	 */
	public void testSetMessageEventDefinition() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getMessageFlow() <em>Message Flow</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getMessageFlow()
	 * @generated
	 */
	public void testGetMessageFlow() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setMessageFlow(org.eclipse.bpmn2.MessageFlow) <em>Message Flow</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setMessageFlow(org.eclipse.bpmn2.MessageFlow)
	 * @generated
	 */
	public void testSetMessageFlow() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getMessageFlowAssociation() <em>Message Flow Association</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getMessageFlowAssociation()
	 * @generated
	 */
	public void testGetMessageFlowAssociation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setMessageFlowAssociation(org.eclipse.bpmn2.MessageFlowAssociation) <em>Message Flow Association</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setMessageFlowAssociation(org.eclipse.bpmn2.MessageFlowAssociation)
	 * @generated
	 */
	public void testSetMessageFlowAssociation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getMonitoring() <em>Monitoring</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getMonitoring()
	 * @generated
	 */
	public void testGetMonitoring() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setMonitoring(org.eclipse.bpmn2.Monitoring) <em>Monitoring</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setMonitoring(org.eclipse.bpmn2.Monitoring)
	 * @generated
	 */
	public void testSetMonitoring() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getMultiInstanceLoopCharacteristics() <em>Multi Instance Loop Characteristics</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getMultiInstanceLoopCharacteristics()
	 * @generated
	 */
	public void testGetMultiInstanceLoopCharacteristics() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setMultiInstanceLoopCharacteristics(org.eclipse.bpmn2.MultiInstanceLoopCharacteristics) <em>Multi Instance Loop Characteristics</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setMultiInstanceLoopCharacteristics(org.eclipse.bpmn2.MultiInstanceLoopCharacteristics)
	 * @generated
	 */
	public void testSetMultiInstanceLoopCharacteristics() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getOperation() <em>Operation</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getOperation()
	 * @generated
	 */
	public void testGetOperation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setOperation(org.eclipse.bpmn2.Operation) <em>Operation</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setOperation(org.eclipse.bpmn2.Operation)
	 * @generated
	 */
	public void testSetOperation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getOutputSet() <em>Output Set</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getOutputSet()
	 * @generated
	 */
	public void testGetOutputSet() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setOutputSet(org.eclipse.bpmn2.OutputSet) <em>Output Set</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setOutputSet(org.eclipse.bpmn2.OutputSet)
	 * @generated
	 */
	public void testSetOutputSet() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getParallelGateway() <em>Parallel Gateway</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getParallelGateway()
	 * @generated
	 */
	public void testGetParallelGateway() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setParallelGateway(org.eclipse.bpmn2.ParallelGateway) <em>Parallel Gateway</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setParallelGateway(org.eclipse.bpmn2.ParallelGateway)
	 * @generated
	 */
	public void testSetParallelGateway() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getParticipant() <em>Participant</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getParticipant()
	 * @generated
	 */
	public void testGetParticipant() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setParticipant(org.eclipse.bpmn2.Participant) <em>Participant</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setParticipant(org.eclipse.bpmn2.Participant)
	 * @generated
	 */
	public void testSetParticipant() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getParticipantAssociation() <em>Participant Association</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getParticipantAssociation()
	 * @generated
	 */
	public void testGetParticipantAssociation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setParticipantAssociation(org.eclipse.bpmn2.ParticipantAssociation) <em>Participant Association</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setParticipantAssociation(org.eclipse.bpmn2.ParticipantAssociation)
	 * @generated
	 */
	public void testSetParticipantAssociation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getParticipantMultiplicity() <em>Participant Multiplicity</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getParticipantMultiplicity()
	 * @generated
	 */
	public void testGetParticipantMultiplicity() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setParticipantMultiplicity(org.eclipse.bpmn2.ParticipantMultiplicity) <em>Participant Multiplicity</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setParticipantMultiplicity(org.eclipse.bpmn2.ParticipantMultiplicity)
	 * @generated
	 */
	public void testSetParticipantMultiplicity() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getPartnerEntity() <em>Partner Entity</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getPartnerEntity()
	 * @generated
	 */
	public void testGetPartnerEntity() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setPartnerEntity(org.eclipse.bpmn2.PartnerEntity) <em>Partner Entity</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setPartnerEntity(org.eclipse.bpmn2.PartnerEntity)
	 * @generated
	 */
	public void testSetPartnerEntity() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getPartnerRole() <em>Partner Role</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getPartnerRole()
	 * @generated
	 */
	public void testGetPartnerRole() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setPartnerRole(org.eclipse.bpmn2.PartnerRole) <em>Partner Role</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setPartnerRole(org.eclipse.bpmn2.PartnerRole)
	 * @generated
	 */
	public void testSetPartnerRole() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getPotentialOwner() <em>Potential Owner</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getPotentialOwner()
	 * @generated
	 */
	public void testGetPotentialOwner() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setPotentialOwner(org.eclipse.bpmn2.PotentialOwner) <em>Potential Owner</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setPotentialOwner(org.eclipse.bpmn2.PotentialOwner)
	 * @generated
	 */
	public void testSetPotentialOwner() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getProcess() <em>Process</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getProcess()
	 * @generated
	 */
	public void testGetProcess() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setProcess(org.eclipse.bpmn2.Process) <em>Process</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setProcess(org.eclipse.bpmn2.Process)
	 * @generated
	 */
	public void testSetProcess() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getProperty() <em>Property</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getProperty()
	 * @generated
	 */
	public void testGetProperty() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setProperty(org.eclipse.bpmn2.Property) <em>Property</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setProperty(org.eclipse.bpmn2.Property)
	 * @generated
	 */
	public void testSetProperty() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getReceiveTask() <em>Receive Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getReceiveTask()
	 * @generated
	 */
	public void testGetReceiveTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setReceiveTask(org.eclipse.bpmn2.ReceiveTask) <em>Receive Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setReceiveTask(org.eclipse.bpmn2.ReceiveTask)
	 * @generated
	 */
	public void testSetReceiveTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getRelationship() <em>Relationship</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getRelationship()
	 * @generated
	 */
	public void testGetRelationship() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setRelationship(org.eclipse.bpmn2.Relationship) <em>Relationship</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setRelationship(org.eclipse.bpmn2.Relationship)
	 * @generated
	 */
	public void testSetRelationship() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getRendering() <em>Rendering</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getRendering()
	 * @generated
	 */
	public void testGetRendering() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setRendering(org.eclipse.bpmn2.Rendering) <em>Rendering</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setRendering(org.eclipse.bpmn2.Rendering)
	 * @generated
	 */
	public void testSetRendering() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getResource() <em>Resource</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getResource()
	 * @generated
	 */
	public void testGetResource() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setResource(org.eclipse.bpmn2.Resource) <em>Resource</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setResource(org.eclipse.bpmn2.Resource)
	 * @generated
	 */
	public void testSetResource() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getResourceAssignmentExpression() <em>Resource Assignment Expression</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getResourceAssignmentExpression()
	 * @generated
	 */
	public void testGetResourceAssignmentExpression() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setResourceAssignmentExpression(org.eclipse.bpmn2.ResourceAssignmentExpression) <em>Resource Assignment Expression</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setResourceAssignmentExpression(org.eclipse.bpmn2.ResourceAssignmentExpression)
	 * @generated
	 */
	public void testSetResourceAssignmentExpression() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getResourceParameter() <em>Resource Parameter</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getResourceParameter()
	 * @generated
	 */
	public void testGetResourceParameter() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setResourceParameter(org.eclipse.bpmn2.ResourceParameter) <em>Resource Parameter</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setResourceParameter(org.eclipse.bpmn2.ResourceParameter)
	 * @generated
	 */
	public void testSetResourceParameter() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getResourceParameterBinding() <em>Resource Parameter Binding</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getResourceParameterBinding()
	 * @generated
	 */
	public void testGetResourceParameterBinding() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setResourceParameterBinding(org.eclipse.bpmn2.ResourceParameterBinding) <em>Resource Parameter Binding</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setResourceParameterBinding(org.eclipse.bpmn2.ResourceParameterBinding)
	 * @generated
	 */
	public void testSetResourceParameterBinding() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getScript() <em>Script</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getScript()
	 * @generated
	 */
	public void testGetScript() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setScript(java.lang.Object) <em>Script</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setScript(java.lang.Object)
	 * @generated
	 */
	public void testSetScript() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getScriptTask() <em>Script Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getScriptTask()
	 * @generated
	 */
	public void testGetScriptTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setScriptTask(org.eclipse.bpmn2.ScriptTask) <em>Script Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setScriptTask(org.eclipse.bpmn2.ScriptTask)
	 * @generated
	 */
	public void testSetScriptTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getSendTask() <em>Send Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getSendTask()
	 * @generated
	 */
	public void testGetSendTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setSendTask(org.eclipse.bpmn2.SendTask) <em>Send Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setSendTask(org.eclipse.bpmn2.SendTask)
	 * @generated
	 */
	public void testSetSendTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getSequenceFlow() <em>Sequence Flow</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getSequenceFlow()
	 * @generated
	 */
	public void testGetSequenceFlow() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setSequenceFlow(org.eclipse.bpmn2.SequenceFlow) <em>Sequence Flow</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setSequenceFlow(org.eclipse.bpmn2.SequenceFlow)
	 * @generated
	 */
	public void testSetSequenceFlow() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getServiceTask() <em>Service Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getServiceTask()
	 * @generated
	 */
	public void testGetServiceTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setServiceTask(org.eclipse.bpmn2.ServiceTask) <em>Service Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setServiceTask(org.eclipse.bpmn2.ServiceTask)
	 * @generated
	 */
	public void testSetServiceTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getSignal() <em>Signal</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getSignal()
	 * @generated
	 */
	public void testGetSignal() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setSignal(org.eclipse.bpmn2.Signal) <em>Signal</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setSignal(org.eclipse.bpmn2.Signal)
	 * @generated
	 */
	public void testSetSignal() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getSignalEventDefinition() <em>Signal Event Definition</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getSignalEventDefinition()
	 * @generated
	 */
	public void testGetSignalEventDefinition() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setSignalEventDefinition(org.eclipse.bpmn2.SignalEventDefinition) <em>Signal Event Definition</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setSignalEventDefinition(org.eclipse.bpmn2.SignalEventDefinition)
	 * @generated
	 */
	public void testSetSignalEventDefinition() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getStandardLoopCharacteristics() <em>Standard Loop Characteristics</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getStandardLoopCharacteristics()
	 * @generated
	 */
	public void testGetStandardLoopCharacteristics() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setStandardLoopCharacteristics(org.eclipse.bpmn2.StandardLoopCharacteristics) <em>Standard Loop Characteristics</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setStandardLoopCharacteristics(org.eclipse.bpmn2.StandardLoopCharacteristics)
	 * @generated
	 */
	public void testSetStandardLoopCharacteristics() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getStartEvent() <em>Start Event</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getStartEvent()
	 * @generated
	 */
	public void testGetStartEvent() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setStartEvent(org.eclipse.bpmn2.StartEvent) <em>Start Event</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setStartEvent(org.eclipse.bpmn2.StartEvent)
	 * @generated
	 */
	public void testSetStartEvent() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getSubChoreography() <em>Sub Choreography</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getSubChoreography()
	 * @generated
	 */
	public void testGetSubChoreography() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setSubChoreography(org.eclipse.bpmn2.SubChoreography) <em>Sub Choreography</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setSubChoreography(org.eclipse.bpmn2.SubChoreography)
	 * @generated
	 */
	public void testSetSubChoreography() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getSubConversation() <em>Sub Conversation</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getSubConversation()
	 * @generated
	 */
	public void testGetSubConversation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setSubConversation(org.eclipse.bpmn2.SubConversation) <em>Sub Conversation</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setSubConversation(org.eclipse.bpmn2.SubConversation)
	 * @generated
	 */
	public void testSetSubConversation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getSubProcess() <em>Sub Process</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getSubProcess()
	 * @generated
	 */
	public void testGetSubProcess() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setSubProcess(org.eclipse.bpmn2.SubProcess) <em>Sub Process</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setSubProcess(org.eclipse.bpmn2.SubProcess)
	 * @generated
	 */
	public void testSetSubProcess() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getTask() <em>Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getTask()
	 * @generated
	 */
	public void testGetTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setTask(org.eclipse.bpmn2.Task) <em>Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setTask(org.eclipse.bpmn2.Task)
	 * @generated
	 */
	public void testSetTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getTerminateEventDefinition() <em>Terminate Event Definition</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getTerminateEventDefinition()
	 * @generated
	 */
	public void testGetTerminateEventDefinition() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setTerminateEventDefinition(org.eclipse.bpmn2.TerminateEventDefinition) <em>Terminate Event Definition</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setTerminateEventDefinition(org.eclipse.bpmn2.TerminateEventDefinition)
	 * @generated
	 */
	public void testSetTerminateEventDefinition() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getText() <em>Text</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getText()
	 * @generated
	 */
	public void testGetText() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setText(java.lang.Object) <em>Text</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setText(java.lang.Object)
	 * @generated
	 */
	public void testSetText() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getTextAnnotation() <em>Text Annotation</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getTextAnnotation()
	 * @generated
	 */
	public void testGetTextAnnotation() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setTextAnnotation(org.eclipse.bpmn2.TextAnnotation) <em>Text Annotation</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setTextAnnotation(org.eclipse.bpmn2.TextAnnotation)
	 * @generated
	 */
	public void testSetTextAnnotation() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getThrowEvent() <em>Throw Event</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getThrowEvent()
	 * @generated
	 */
	public void testGetThrowEvent() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setThrowEvent(org.eclipse.bpmn2.ThrowEvent) <em>Throw Event</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setThrowEvent(org.eclipse.bpmn2.ThrowEvent)
	 * @generated
	 */
	public void testSetThrowEvent() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getTimerEventDefinition() <em>Timer Event Definition</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getTimerEventDefinition()
	 * @generated
	 */
	public void testGetTimerEventDefinition() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setTimerEventDefinition(org.eclipse.bpmn2.TimerEventDefinition) <em>Timer Event Definition</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setTimerEventDefinition(org.eclipse.bpmn2.TimerEventDefinition)
	 * @generated
	 */
	public void testSetTimerEventDefinition() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getTransaction() <em>Transaction</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getTransaction()
	 * @generated
	 */
	public void testGetTransaction() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setTransaction(org.eclipse.bpmn2.Transaction) <em>Transaction</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setTransaction(org.eclipse.bpmn2.Transaction)
	 * @generated
	 */
	public void testSetTransaction() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#getUserTask() <em>User Task</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#getUserTask()
	 * @generated
	 */
	public void testGetUserTask() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link org.eclipse.bpmn2.DocumentRoot#setUserTask(org.eclipse.bpmn2.UserTask) <em>User Task</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.DocumentRoot#setUserTask(org.eclipse.bpmn2.UserTask)
	 * @generated
	 */
	public void testSetUserTask() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

} //DocumentRootTest
