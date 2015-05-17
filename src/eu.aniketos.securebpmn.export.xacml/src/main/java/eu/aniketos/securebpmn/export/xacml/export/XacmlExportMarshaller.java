/* Copyright 2012-2015 SAP SE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.aniketos.securebpmn.export.xacml.export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.net.URI;
import java.util.List;

import org.activiti.designer.eclipse.common.ActivitiBPMNDiagramConstants;
import org.activiti.designer.eclipse.extension.export.AbstractExportMarshaller;
import org.activiti.designer.eclipse.extension.export.ExportMarshaller;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.Task;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.securebpmn2.BindingOfDuty;
import org.eclipse.securebpmn2.SeparationOfDuty;

import com.sun.xacml.combine.FirstApplicablePolicyAlg;
import com.sun.xacml.combine.PolicyCombiningAlgorithm;

/**
 * Exports an XACML representation of the diagram being saved to the workspace
 * as XML-File.
 *
 */
public class XacmlExportMarshaller extends AbstractExportMarshaller {

    private static final String FILENAME_PATTERN = ExportMarshaller.PLACEHOLDER_ORIGINAL_FILENAME_WITHOUT_EXTENSION
            + ".xacml";
    private IProgressMonitor monitor;
    private Diagram diagram;

    public XacmlExportMarshaller() {
    }

    /**
     * @see org.activiti.designer.eclipse.extension.export.ExportMarshaller#
     *      getMarshallerName()
     */
    @Override
    public String getMarshallerName() {
        return ActivitiBPMNDiagramConstants.XACML_MARSHALLER_NAME;
    }

    /**
     * @see org.activiti.designer.eclipse.extension.export.ExportMarshaller#getFormatName()
     */
    @Override
    public String getFormatName() {
        return "SecureBPMN: XACML";
    }

    /**
     *
     *
     * @see org.activiti.designer.eclipse.extension.export.ExportMarshaller#
     *      marshallDiagram(org.eclipse.graphiti.mm.pictograms.Diagram,
     *      org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void marshallDiagram(Diagram diagram, IProgressMonitor monitor) {

        this.monitor = monitor;
        this.diagram = diagram;
        this.monitor.beginTask("Exporting to XACML", 100);
        clearMarkers(getResource(diagram.eResource().getURI()));
        this.monitor.worked(10);
        String validatorId = ActivitiBPMNDiagramConstants.XACML_VALIDATOR_ID;
        boolean validBpmn = invokeValidator(validatorId, diagram,
                                            new SubProgressMonitor(this.monitor, 10));

        if (validBpmn) {
            marshallXacml();
        } else {
            addProblemToDiagram(
                diagram,
                "XACML Export skipped because SecureBPMN validation failed.",
                null);
        }
        this.monitor.worked(80);
        this.monitor.done();
    }

    /**
     * Controls and executes the XACML file generation.
     */
    public void marshallXacml() {

        try {
            final List<EObject> contents = diagram.eResource().getContents();
            Process process = null;

            for (final EObject eObject : contents) {
                if (eObject instanceof Process) {
                    process = (Process) eObject;
                }
            }
            if (process == null) {
                addProblemToDiagram(diagram, "Process cannot be null", null);
            }
            final XacmlFileBuilder xfb = new XacmlFileBuilder();
            // element-specific content generation
            for (EObject object : contents) {
                if (object instanceof ParallelGateway) {
                    /*
                     * ParallelGatewayExport.createParallelGatewayElements(
                     * (ParallelGateway) object, xfb);
                     */
                } else if (object instanceof ExclusiveGateway) {
                    /*
                     * ExclusiveGatewayExport.createExclusiveGatewayElements(
                     * (ExclusiveGateway) object, xfb);
                     */
                } else if (object instanceof StartEvent) {
                    /*
                     * final String startEventFact = "start_event_" +
                     * ((StartEvent) object).getId(); xfb.addType("fact",
                     * startEventFact); xfb.addInit(startEventFact);
                     */
                } else if (object instanceof Task) {

                    TaskExport.gatherTaskInfo((Task) object, xfb);

                } else if (object instanceof SeparationOfDuty) {
                    /*
                     * SeparationOfDutyExport.createSeparationOfDutyElements(
                     * (SeparationOfDuty) object, xfb);
                     */
                } else if (object instanceof BindingOfDuty) {
                    /*
                     * BindingOfDutyExport.createBindingOfDutyElements(
                     * (BindingOfDuty) object, xfb);
                     */
                }
            }
            // the policy
            //URI policyID = URI.create("testPolicy");
            // TODO insert options for the used CombiningAlg
            //RuleCombiningAlgorithm ruleCombiningAlg = new DenyOverridesRuleAlg();

            //xfb.createPolicy(policyID, ruleCombiningAlg, xfb.createTarget(),
            //		xfb.getRules());
            // the policy Set
            URI policySetID = URI.create("testPolicySet");
            // TODO insert options for the used CombiningAlg
            PolicyCombiningAlgorithm policyCombiningAlg = new FirstApplicablePolicyAlg();

            // TODO maybe create deny rule for the PolicySet-Target if no inner
            // rules apply
            xfb.createPolicySet(policySetID, policyCombiningAlg,
                                xfb.createTarget(), xfb.getPolicies());

            // mapping the encoded PolicySet data
            final ByteArrayOutputStream baos = (ByteArrayOutputStream) xfb
                                               .encodePolicySet();

            // write policy to file
            final byte[] bytes = baos.toByteArray();
            final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            saveResource(getRelativeURIForDiagram(diagram, FILENAME_PATTERN),
                         bais, new NullProgressMonitor());

            //xfb.testPDP();

        } catch (Exception e) {
            e.printStackTrace();
            addProblemToDiagram(
                diagram,
                "An exception occurred while creating the XACML file: "
                + e.getMessage(), null);
        }
    }
}
