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

package eu.aniketos.securebpmn.export.html.export;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.activiti.designer.eclipse.common.ActivitiBPMNDiagramConstants;
import org.activiti.designer.eclipse.extension.export.AbstractExportMarshaller;
import org.activiti.designer.eclipse.extension.export.ExportMarshaller;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.SubProcess;

/**
 * Exports an HTML representation of the diagram being saved to the workspace.
 *
 * @since 0.5.7
 * @version 1
 *
 */
public class HtmlExportMarshaller extends AbstractExportMarshaller {

    private static final String FILENAME_PATTERN = ExportMarshaller.PLACEHOLDER_ORIGINAL_FILENAME_WITHOUT_EXTENSION
            + ".html";

    private IProgressMonitor monitor;
    private Diagram diagram;

    public HtmlExportMarshaller() {
    }

    /*
     * (non-Javadoc)
     *
     * @see org.activiti.designer.eclipse.extension.export.ExportMarshaller#
     * getMarshallerName()
     */
    @Override
    public String getMarshallerName() {
        return ActivitiBPMNDiagramConstants.HTML_MARSHALLER_NAME;
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
        return "SecureBPMN: HTML";
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

        this.monitor.beginTask("Exporting to HTML", 100);

        // Clear problems for this marshaller first
        clearMarkers(getResource(diagram.eResource().getURI()));

        this.monitor.worked(10);

        marshallHtml();

        this.monitor.worked(90);

        this.monitor.done();

    }

    /**
     * Initializes and controls the HTML file generation.
     */
    private void marshallHtml() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(baos, "UTF-8");
            Writer out = new BufferedWriter(osw);

            final EList<EObject> contents = diagram.eResource().getContents();

            Process process = null;

            boolean found = false;
            List<FlowElement> flowElements = new ArrayList<FlowElement>();
            for (final EObject eObject : contents) {
                if (eObject instanceof Process && !found) {
                    process = (Process) eObject;
                    found = true;
                }
                if (eObject instanceof FlowElement)
                    flowElements.add((FlowElement) eObject);
            }

            if (process == null) {
                addProblemToDiagram(diagram, "Process cannot be null", null);
            }

            HtmlWriter hw = new HtmlWriter(out);

            hw.writeStartDocument();
            hw.writeHead(process.getName());
            hw.writeStartBody();

            hw.writeHeading(process.getName(), 1);

            createHtml(flowElements, hw, "");

            hw.writeEndBody();
            hw.writeEndDocument();
            out.flush();

            final byte[] bytes = baos.toByteArray();
            final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            saveResource(getRelativeURIForDiagram(diagram, FILENAME_PATTERN),
                         bais, new NullProgressMonitor());
        } catch (Exception e) {
            e.printStackTrace();
            addProblemToDiagram(
                diagram,
                "An exception occurred while creating the HTML: "
                + e.getMessage(), null);
        }

    }

    /**
     * Creates the actual HTML output for a given (Sub)Process.
     *
     * @param flowElements
     *            The list of flow elements that should be included in the HTML
     *            file.
     * @param hw
     *            The HtmlWriter to which the output is sent.
     * @param subProcessID
     *            The ID of the SubProcess, if the function is used for one.
     *            Provide an empty String otherwise.
     * @throws IOException
     *             Any IOException that might occur during usage of the
     *             HtmlWriter.
     */
    private void createHtml(List<FlowElement> flowElements, HtmlWriter hw,
                            String subProcessID) throws IOException {
        if (subProcessID != "") {
            hw.writeHorizontalRule();
            hw.writeHeading("FlowElements of " + subProcessID, 2);
        }

        hw.writeStartTable();
        hw.writeStartTableRow();
        hw.writeTableHeadingCell("type");
        hw.writeTableHeadingCell("id");
        hw.writeTableHeadingCell("name");
        hw.writeTableHeadingCell("properties");
        hw.writeEndTableRow();

        List<SubProcess> subProcesses = new ArrayList<SubProcess>();

        for (final FlowElement flowElement : flowElements) {

            String type = flowElement.getClass().getName();
            if (type.lastIndexOf('.') > 0) {
                type = type.substring(type.lastIndexOf('.') + 1); // trim to
                // unqualified
                // name
            }
            type = type.substring(0, type.length() - 4); // cut out "Impl"

            hw.writeStartTableRow();

            hw.writeTableCell(type);
            if (subProcessID.length() > 0) {
                hw.writeTableCell(subProcessID + "/" + flowElement.getId());
            } else {
                hw.writeTableCell(flowElement.getId());
            }

            if (flowElement.getName().length() > 0) {
                hw.writeTableCell(flowElement.getName());
            } else {
                hw.writeTableCell("&nbsp;");
            }

            hw.writeTableCell(PropertiesStringBuilder.create(flowElement));

            hw.writeEndTableRow();

            if (flowElement instanceof SubProcess)
                subProcesses.add((SubProcess) flowElement);
        }

        hw.writeEndTable();

        if (subProcesses.size() > 0) {
            for (final SubProcess sp : subProcesses) {
                createHtml(sp.getFlowElements(), hw, sp.getId());
            }
        }

    }

}
