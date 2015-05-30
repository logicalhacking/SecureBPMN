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

package eu.aniketos.securebpmn.validation;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

import org.activiti.designer.eclipse.common.ActivitiBPMNDiagramConstants;
import org.activiti.designer.eclipse.extension.export.ExportMarshaller;
import org.activiti.designer.eclipse.preferences.PreferencesUtil;
import org.activiti.designer.eclipse.util.ExtensionPointUtil;
import org.activiti.designer.util.preferences.Preferences;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.satmc.SatmcLexer;
import org.antlr.satmc.SatmcParser;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;

import eu.aniketos.securebpmn.satmc.SatmcMessage;
import eu.aniketos.securebpmn.util.DialogUtil;
import eu.aniketos.securebpmn.util.SecurityUtil;

import eu.avantssar.satmc.SATMCPortType;
import eu.avantssar.satmc.SATMCService;

/**
 * A Runnable that generates the ASLan file and analyzes it via SATMC for a
 * given Diagram.
 *
 *
 */
public class ValidateAslanRunnable implements IRunnableWithProgress {

    private Diagram diagram;
    private boolean localValidation;

    private SatmcMessage result;

    /**
     * Default constructor.
     *
     * @param diagram
     *            The diagram that should be validated.
     * @param result
     *            The variable where the result is saved to.
     * @param localValidation
     *            true if the local SATMC binary should be used, false for the
     *            SATMC web service.
     */
    public ValidateAslanRunnable(Diagram diagram, SatmcMessage result,
                                 boolean localValidation) {
        this.diagram = diagram;
        this.localValidation = localValidation;
        this.result = result;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core
     * .runtime.IProgressMonitor)
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException,
        InterruptedException {

        try {

            monitor.beginTask("Validating security properties", 10);

            // get uri
            URI uri = diagram.eResource().getURI();
            URI platformUri = uri.trimFragment();
            platformUri = platformUri.trimFileExtension();
            platformUri = platformUri.appendFileExtension("aslan");

            monitor.worked(1);

            // generate file
            monitor.subTask("Generating ASLan");

            final ExportMarshaller marshaller = ExtensionPointUtil
                                                .getExportMarshaller(ActivitiBPMNDiagramConstants.ASLAN_MARSHALLER_NAME);
            if (marshaller == null) {
                throw new IllegalArgumentException(
                    "Unable to invoke ExportMarshaller with name "
                    + ActivitiBPMNDiagramConstants.ASLAN_MARSHALLER_NAME);
            }
            final IProgressMonitor subMonitor = new SubProgressMonitor(monitor,
                    2);
            try {
                marshaller.marshallDiagram(diagram, subMonitor);
            } finally {
                subMonitor.done();
            }

            // check for problem markers
            final IWorkspace workspace = ResourcesPlugin.getWorkspace();

            final IFile diagramFile = workspace.getRoot().getFile(
                                          new Path(diagram.eResource().getURI()
                                                   .toPlatformString(true)));

            try {
                if (diagramFile
                        .findMarkers(ExportMarshaller.MARKER_ID, true, 3).length > 0) {
                    Display.getDefault().syncExec(new Runnable() {
                        public void run() {
                            DialogUtil
                            .openMessageDialog(
                                "Export problems present",
                                "The diagram contains export problems! This might result in an outdated analysis result, you should fix the problems and run the analysis again.",
                                DialogUtil.WARNING);
                        }
                    });
                }
            } catch (CoreException e1) {
                e1.printStackTrace();
            }

            // file validation
            String res = "";
            final String fileUri = getResource(platformUri).getLocation()
                                   .toString();

            if (localValidation) {
                // call local binary and pass file path
                monitor.subTask("Executing local SATMC binary");
                try {
                    String pathToBinary = PreferencesUtil
                                          .getStringPreference(Preferences.PATH_TO_SATMC_BINARY);
                    File workingDir = null;
                    if (pathToBinary.length() == 0) {
                        pathToBinary = "satmc";
                    } else {
                        int lastIndex = pathToBinary.lastIndexOf(System
                                        .getProperty("file.separator"));

                        if (lastIndex > 0) {
                            workingDir = new File(pathToBinary.substring(0,
                                                  lastIndex));
                        }
                    }
                    String[] cmd = { pathToBinary, fileUri, "--max=80",
                                     "--mutex=0"
                                   };
                    res = cmdExec(cmd, workingDir);
                } catch (IOException e) {
                    Display.getDefault().syncExec(new Runnable() {
                        public void run() {
                            DialogUtil
                            .openMessageDialog(
                                "SATMC Execution Error",
                                "Could not run SATMC. Try providing the full path to the executable file in the Activiti preferences.",
                                DialogUtil.ERROR);
                        }
                    });
                    e.printStackTrace();
                    monitor.worked(7);
                    monitor.done();
                    return;
                }
            } else {
                // read file as string and call web service
                monitor.subTask("Calling SATMC web service");
                try {
                    final SATMCService service = new SATMCService();
                    final SATMCPortType port = service.getSATMCSOAPPort();

                    res = port.validate(SecurityUtil.readFileAsString(fileUri),
                                        80, false, true, false, "aslan", "");
                } catch (Exception e) {
                    Display.getDefault().syncExec(new Runnable() {
                        public void run() {
                            DialogUtil
                            .openMessageDialog(
                                "SATMC Execution Error",
                                "Could not reach SATMC web service. Check your internet connection and, if you are behind a firewall, try setting the proxy in Eclipse to \"manual\".",
                                DialogUtil.ERROR);
                        }
                    });
                    e.printStackTrace();
                    monitor.worked(7);
                    monitor.done();
                    return;
                }
            }

            monitor.worked(4);

            // save result to file
            monitor.subTask("Saving results");

            InputStream content = new ByteArrayInputStream(res.getBytes());

            URI platformResultUri = uri.trimFragment();
            platformResultUri = platformResultUri.trimFileExtension();
            platformResultUri = platformResultUri.appendFileExtension("result");

            final IFile newfile = workspace.getRoot().getFile(
                                      new Path(platformResultUri.toPlatformString(true)));

            try {
                if (newfile.exists()) {
                    newfile.setContents(content, true, true,
                                        new NullProgressMonitor());
                } else {
                    newfile.create(content, true, new NullProgressMonitor());
                }
                newfile.refreshLocal(IResource.DEPTH_INFINITE, null);
            } catch (CoreException e) {
                e.printStackTrace();
            }

            monitor.worked(1);

            // parse results
            monitor.subTask("Parsing results");

            try {
                SatmcLexer lex = new SatmcLexer(new ANTLRStringStream(res));
                CommonTokenStream tokens = new CommonTokenStream(lex);

                SatmcParser parser = new SatmcParser(tokens);
                SatmcMessage parserResult = parser.output();

                // copy result to result variable
                result.summary = parserResult.summary;
                result.goal = parserResult.goal;
                result.trace = parserResult.trace;
                result.cfs = parserResult.cfs;

            } catch (RecognitionException e) {
                e.printStackTrace();
            }

            monitor.worked(2);
        } finally {
            monitor.done();
        }

    }

    /**
     * Retrieves the Resource for a given URI.
     *
     * @param resourceURI
     *            The URI for the Resource.
     * @return The found Resource.
     */
    private IResource getResource(URI resourceURI) {

        final IResource fileResource = ResourcesPlugin.getWorkspace().getRoot()
                                       .findMember(resourceURI.toPlatformString(true));

        IResource result = null;
        if (fileResource != null && fileResource.exists()) {
            result = ResourcesPlugin.getWorkspace().getRoot()
                     .findMember(resourceURI.toPlatformString(true));
        }
        return result;
    }

    /**
     * Executes a command on the local machine.
     *
     * @param cmdLine
     *            The command that should be executed.
     * @param dir
     *            The working directory in which the command should be executed.
     * @return The standard output of the command.
     * @throws IOException
     *             Errors that occur during the execution.
     */
    private String cmdExec(String[] cmdLine, File dir) throws IOException {
        String line;
        String output = "";

        Process p = Runtime.getRuntime().exec(cmdLine, null, dir);
        BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
        while ((line = input.readLine()) != null) {
            output += (line + '\n');
        }
        input.close();

        return output;
    }

}
