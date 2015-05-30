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

package eu.aniketos.securebpmn.export.aslan.export;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
import org.eclipse.securebpmn2.Role;
import org.eclipse.securebpmn2.SeparationOfDuty;
import org.eclipse.securebpmn2.Subject;
import org.eclipse.securebpmn2.User;

import eu.aniketos.securebpmn.util.SecurityUtil;

/**
 * Exports an ASLan representation of the diagram being saved to the workspace.
 *
 * @since 0.5.7
 * @version 1
 *
 */
public class AslanExportMarshaller extends AbstractExportMarshaller {
    private static final String FILENAME_PATTERN = ExportMarshaller.PLACEHOLDER_ORIGINAL_FILENAME_WITHOUT_EXTENSION
            + ".aslan";
    private IProgressMonitor monitor;
    private Diagram diagram;

    public AslanExportMarshaller() {
    }

    /*
     * (non-Javadoc)
     *
     * @see org.activiti.designer.eclipse.extension.export.ExportMarshaller#
     * getMarshallerName()
     */
    @Override
    public String getMarshallerName() {
        return ActivitiBPMNDiagramConstants.ASLAN_MARSHALLER_NAME;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.activiti.designer.eclipse.extension.export.ExportMarshaller#getFormatName
     * ()
     */
    @Override
    public String getFormatName() {
        return "SecureBPMN: ASLan";
    }

    /*
     * (non-Javadoc)
     *
     * @see org.activiti.designer.eclipse.extension.export.ExportMarshaller#
     * marshallDiagram(org.eclipse.graphiti.mm.pictograms.Diagram,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void marshallDiagram(Diagram diagram, IProgressMonitor monitor) {

        this.monitor = monitor;
        this.diagram = diagram;

        this.monitor.beginTask("Exporting to ASLan", 100);

        // Clear problems for this marshaller first
        clearMarkers(getResource(diagram.eResource().getURI()));

        this.monitor.worked(10);

        // diagram validation

        // Retrieve validatorId to allow for overriding the default validator
        String validatorId = ActivitiBPMNDiagramConstants.ASLAN_VALIDATOR_ID;

        boolean validBpmn = invokeValidator(validatorId, diagram,
                                            new SubProgressMonitor(this.monitor, 10));

        if (validBpmn) {
            marshallAslan();
        } else {
            addProblemToDiagram(
                diagram,
                "ASLan Export skipped because SecureBPMN validation failed.",
                null);
        }

        this.monitor.worked(80);
        this.monitor.done();

    }

    /**
     * Controls and executes the ASLan file generation.
     */
    public void marshallAslan() {
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final OutputStreamWriter osw = new OutputStreamWriter(baos, "UTF-8");
            final Writer out = new BufferedWriter(osw);

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

            final AslanFileBuilder afb = new AslanFileBuilder(out);

            createSignatureSection(afb);

            createUserRoleMappings(afb);

            createStaticRuleSection(afb);

            // element-specific content generation
            for (EObject object : contents) {
                if (object instanceof ParallelGateway) {

                    ParallelGatewayExport.createParallelGatewayElements(
                        (ParallelGateway) object, afb);

                } else if (object instanceof ExclusiveGateway) {

                    ExclusiveGatewayExport.createExclusiveGatewayElements(
                        (ExclusiveGateway) object, afb);

                } else if (object instanceof StartEvent) {

                    final String startEventFact = "start_event_"
                                                  + ((StartEvent) object).getId();
                    afb.addType("fact", startEventFact);
                    afb.addInit(startEventFact);

                } else if (object instanceof Task) {

                    TaskExport.createTaskElements((Task) object, afb);

                } else if (object instanceof SeparationOfDuty) {

                    SeparationOfDutyExport.createSeparationOfDutyElements(
                        (SeparationOfDuty) object, afb);

                } else if (object instanceof BindingOfDuty) {

                    BindingOfDutyExport.createBindingOfDutyElements(
                        (BindingOfDuty) object, afb);
                }
            }

            int writeStatus = afb.writeOutput();
            out.flush();

            final byte[] bytes = baos.toByteArray();
            final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            saveResource(getRelativeURIForDiagram(diagram, FILENAME_PATTERN),
                         bais, new NullProgressMonitor());

            if (writeStatus == AslanFileBuilder.NO_GOALS) {
                addWarningToDiagram(
                    diagram,
                    "The ASLan file contains no goals. Analyzing it with SATMC will result in an error.",
                    null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            addProblemToDiagram(
                diagram,
                "An exception occurred while creating the ASLan file: "
                + e.getMessage(), null);
        }
    }

    /**
     * Writes the signature section of the ASLan file to the provided
     * AslanFileBuilder.
     *
     * @param afb
     *            The AslanFileBuilder the output is sent to.
     */
    private void createSignatureSection(AslanFileBuilder afb) {
        afb.addSignature("user_to_role : user * role -> fact");
        afb.addSignature("poto : userORrole * taskName -> fact");
        afb.addSignature("task_to_data : taskName * set * set -> fact");
        afb.addSignature("aknows : entity * data -> fact");
        afb.addSignature("mc_pair : data * data -> data");
        afb.addSignature("contains : set * data -> fact");
        afb.addSignature("task : taskName * nat -> taskInstance");
        afb.addSignature("canExecute : user * role * humanTaskName -> fact");
        afb.addSignature("granted : user * role * taskInstance -> fact");
        afb.addSignature("executed : user * taskInstance -> fact");
        afb.addSignature("ready : taskInstance -> fact");
        afb.addSignature("done : taskInstance -> fact");

        afb.addSignature("entity > organization");
        afb.addSignature("entity > user");
        afb.addSignature("data > object");
        afb.addSignature("data > set");
        afb.addSignature("userORrole > user");
        afb.addSignature("userORrole > role");
        afb.addSignature("taskName > automatedTaskName");
        afb.addSignature("taskName > humanTaskName");
    }

    /**
     * Writes the static rules of the ASLan file to the provided
     * AslanFileBuilder.
     *
     * @param afb
     *            The AslanFileBuilder the output is sent to.
     */
    private void createStaticRuleSection(AslanFileBuilder afb) {
        // variable type definitions
        afb.addType("user", "A");
        afb.addType("role", "R");
        afb.addType("humanTaskName", "HT");
        afb.addType("nat", "N");
        afb.addType("set", "IN");
        afb.addType("set", "OUT");
        afb.addType("automatedTaskName", "AT");

        // task claiming authorization
        afb.addHornClause("hc rbac_ac (A,R,HT) := canExecute(A,R,HT) :- user_to_role(A,R), poto(R,HT)");
        afb.addHornClause("hc direct_ac (A,R,HT) := canExecute(A,R,HT) :- user_to_role(A,R), poto(A,HT)");

        // task claiming
        afb.addRule("step authorizeTaskExecution(A,R,HT,N) := canExecute(A,R,HT). ready(task(HT,N)) => granted(A,R,task(HT,N))");

        // human task execution
        afb.addRule("step h_taskExecution(A,R,HT,N,IN,OUT) := granted(A,R,task(HT,N)). task_to_data(HT,IN,OUT) => executed(A,task(HT,N)). done(task(HT,N)). task_to_data(HT,IN,OUT). aknows(A,IN). aknows(A,OUT)");

        // automated task execution
        afb.addRule("step atask_execution(AT,N,IN,OUT) := ready(task(AT,N)). task_to_data(AT,IN,OUT) => done(task(AT,N)). task_to_data(AT,IN,OUT)");
    }

    /**
     * Writes the user to role mappings of the ASLan file to the provided
     * AslanFileBuilder.
     *
     * @param afb
     *            The AslanFileBuilder the output is sent to.
     */
    private void createUserRoleMappings(AslanFileBuilder afb) {

        final List<Role> roles = SecurityUtil.getRoles(diagram);

        for (Role r : roles) {

            final String roleName = r.getName().toLowerCase();
            afb.addType("role", roleName);

            for (Subject s : r.getSubjects()) {
                if (s instanceof User) {
                    final User u = (User) s;
                    final String userName = u.getUserName().toLowerCase();
                    afb.addType("user", userName);
                    afb.addInit("user_to_role(" + userName + "," + roleName
                                + ")");
                }
            }
        }
    }
}
