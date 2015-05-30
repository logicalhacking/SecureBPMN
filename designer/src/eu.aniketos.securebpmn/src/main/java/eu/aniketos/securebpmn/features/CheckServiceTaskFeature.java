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

package eu.aniketos.securebpmn.features;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.activiti.designer.util.eclipse.ActivitiUiUtil;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.impl.ServiceTaskImpl;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import eu.aniketos.securebpmn.util.DialogUtil;
import eu.aniketos.securebpmn.validation.ProcVarCheckASTVisitor;

/**
 * This feature provides a feature that checks a provided ServiceTask Java implementation if
 * it accesses a process variable with a provided name and displays the result
 * to the user.
 *
 *
 */
public class CheckServiceTaskFeature extends AbstractCustomFeature {

    public CheckServiceTaskFeature(IFeatureProvider fp) {
        super(fp);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.graphiti.features.impl.AbstractFeature#getName()
     */
    @Override
    public String getName() {
        return "Check service task"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.graphiti.features.custom.AbstractCustomFeature#getDescription
     * ()
     */
    @Override
    public String getDescription() {
        return "Check if service task accesses a specific process variable"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.graphiti.features.custom.AbstractCustomFeature#canExecute
     * (org.eclipse.graphiti.features.context.ICustomContext)
     */
    @Override
    public boolean canExecute(ICustomContext context) {
        if (context.getPictogramElements() == null)
            return false;
        for (PictogramElement pictogramElement : context.getPictogramElements()) {
            if (pictogramElement.getLink() == null)
                continue;
            Object boObject = getBusinessObjectForPictogramElement(pictogramElement);
            if (boObject instanceof ServiceTask == false) {
                return false;
            }
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.graphiti.features.custom.ICustomFeature#execute(org.eclipse
     * .graphiti.features.context.ICustomContext)
     */
    public void execute(ICustomContext context) {

        ServiceTask servicetask = null;

        // get BusinessObject
        try {
            servicetask = (ServiceTask) ActivitiUiUtil
                          .getBusinessObjectFromContext(context,
                                  ServiceTaskImpl.class);
        } catch (Exception e) {
            DialogUtil.openMessageDialog("BO Missing",
                                         "No ServiceTask BusinessObject for Context found",
                                         DialogUtil.ERROR);
        }
        if (servicetask == null)
            return;

        // check if service task has java impl
        if (servicetask.getImplementationType() == null
                || !(servicetask.getImplementationType().equals("classType"))) {
            DialogUtil.openMessageDialog("Wrong Type",
                                         "Type must be \"Java class\" to perform this check.",
                                         DialogUtil.ERROR);
            return;
        }

        if (servicetask.getImplementation() == null
                || servicetask.getImplementation().length() == 0) {
            DialogUtil.openMessageDialog("Missing Class definition",
                                         "No Java class specified.", DialogUtil.ERROR);
            return;
        }

        // prompt user for process variable
        String procVar = DialogUtil.openInputDialog("Process Variable",
                         "Enter the process variable that should not be accessed.", "",
                         null);

        if (procVar == null) {
            // user canceled
            return;
        }

        // map FQ class name to java file

        // TODO hack, do this nicely
        String[] classParts = servicetask.getImplementation().split("\\.");
        String implFileName = classParts[classParts.length - 1] + ".java";

        StringBuilder sb = new StringBuilder();
        sb.append("/");
        sb.append("src");
        sb.append("/");
        sb.append("main");
        sb.append("/");
        sb.append("java");
        sb.append("/");
        for (int i = 0; i < classParts.length - 1; i++) {
            sb.append(classParts[i]);
            sb.append("/");
        }
        sb.append(implFileName);

        IResource resourceToRead = null;
        int filesFound = 0;

        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        IProject[] projects = root.getProjects();
        for (IProject project : projects) {
            IResource resourceInRuntimeWorkspace = root.findMember(project
                                                   .getName() + sb.toString());
            if (resourceInRuntimeWorkspace != null) {
                filesFound++;
                if (resourceToRead == null) {
                    resourceToRead = resourceInRuntimeWorkspace;
                }
            }
        }

        if (filesFound == 0) {
            DialogUtil.openMessageDialog("Missing source",
                                         "Implementation source file not found in workspace.",
                                         DialogUtil.ERROR);
            return;
        } else if (filesFound > 1) {
            DialogUtil
            .openMessageDialog(
                "Multiple files",
                "Found "
                + filesFound
                + " matching source files in workspace, using the following:\n\n"
                + resourceToRead.getFullPath(),
                DialogUtil.WARNING);
        }

        // read file
        StringBuffer fileData = new StringBuffer(1000);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(
                        resourceToRead.getLocationURI())));
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
                buf = new char[1024];
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            DialogUtil.openMessageDialog("IO Error",
                                         "Error while reading source file: " + e.getMessage(),
                                         DialogUtil.ERROR);
            return;
        }

        // build AST for Impl
        List<Integer> locationList = new ArrayList<Integer>();
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setSource(fileData.toString().toCharArray());
        CompilationUnit node = (CompilationUnit) parser.createAST(null);
        node.accept(new ProcVarCheckASTVisitor(procVar, locationList));

        // remove duplicates
        List<Integer> uniqueLocationList = new ArrayList<Integer>();
        for (Integer i : locationList) {
            Integer newLoc = node.getLineNumber(i);
            if (!uniqueLocationList.contains(newLoc))
                uniqueLocationList.add(newLoc);
        }

        if (uniqueLocationList.size() > 0) {
            // notify user
            StringBuilder msgSB = new StringBuilder();
            msgSB.append("Process variable access of \"" + procVar
                         + "\" found in implementation at ");
            if (uniqueLocationList.size() > 1) {
                msgSB.append("lines ");
                for (int i = 0; i < uniqueLocationList.size() - 1; i++) {
                    msgSB.append(uniqueLocationList.get(i) + ", ");
                }
                msgSB.append("and "
                             + uniqueLocationList.get(uniqueLocationList.size() - 1));
            } else {
                msgSB.append("line " + uniqueLocationList.get(0));
            }
            msgSB.append(".");

            int answer = DialogUtil.openMessageDialog("Violation",
                         msgSB.toString(), DialogUtil.ERROR, new String[] { "OK",
                                 "Open File"
                                                                          }, 0);

            if (answer == 1) {
                // open source file
                IWorkbench wb = PlatformUI.getWorkbench();
                IWorkbenchPage page = wb.getActiveWorkbenchWindow()
                                      .getActivePage();
                IFile file = (IFile) resourceToRead;
                IEditorDescriptor desc = wb.getEditorRegistry()
                                         .getDefaultEditor(file.getName());
                HashMap<String, Integer> map = new HashMap<String, Integer>();
                map.put(IMarker.LINE_NUMBER, uniqueLocationList.get(0));
                try {
                    IMarker marker = file.createMarker(IMarker.TEXT);
                    marker.setAttributes(map);
                    page.openEditor(new FileEditorInput(file), desc.getId());
                    IDE.openEditor(page, marker);
                    marker.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            DialogUtil.openMessageDialog("No violation",
                                         "No process variable access of \"" + procVar
                                         + "\" found in implementation.", DialogUtil.INFO);
        }

    }

}
