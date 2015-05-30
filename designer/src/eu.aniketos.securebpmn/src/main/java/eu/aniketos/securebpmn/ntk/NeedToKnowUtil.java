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

package eu.aniketos.securebpmn.ntk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.FormProperty;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.InputSet;
import org.eclipse.bpmn2.OutputSet;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.securebpmn2.AuthorizationConstraint;
import org.eclipse.securebpmn2.ItemAwareElementAction;
import org.eclipse.securebpmn2.NeedToKnow;
import org.eclipse.securebpmn2.Permission;

import eu.aniketos.securebpmn.validation.ProcVarAccess;
import eu.aniketos.securebpmn.validation.ProcVarListASTVisitor;

/**
 * This class contains utility methods that are being used in the need-to-know
 * analysis.
 *
 *
 */
public class NeedToKnowUtil {

    public static final String ID_PREFIX_IOSPEC = "iospec_";
    public static final String ID_PREFIX_INSET = "inset_ntk_";
    public static final String ID_PREFIX_OUTSET = "outset_ntk_";
    public static final String ID_PREFIX_INPUT = "input_ntk_";
    public static final String ID_PREFIX_OUTPUT = "output_ntk_";

    /**
     * Provides the names of the SecureBPMN ItemAwareElementActions that are
     * used.
     *
     * @return An Array containing the names of the ItemAwareElementActions as
     *         Strings.
     */
    public static String[] getItemAwareElementActionNames() {

        return new String[] { "read", "write", "read/write" };
    }

    /**
     * Finds the names of the process variables that are accessed in the
     * provided task. Only the HTML forms of a UserTask and the Java
     * implementation of a ServiceTask can be analyzed.
     *
     * @param task
     *            The task (UserTask or ServiceTask) that should be analyzed.
     * @return A List with the names of the accessed process variables as
     *         Strings.
     */
    public static List<String> getAccessedProcessVariableNames(Task task) {

        List<String> result = new ArrayList<String>();

        List<ProcVarAccess> varAccesses = new ArrayList<ProcVarAccess>();

        if (task instanceof UserTask) {
            varAccesses = getAccessedProcessVariables((UserTask) task);
        } else if (task instanceof ServiceTask) {
            varAccesses = getAccessedProcessVariables((ServiceTask) task);
        }

        for (ProcVarAccess var : varAccesses) {
            final String varName = var.getName();
            if (!result.contains(varName))
                result.add(varName);
        }

        return result;

    }

    /**
     * Analyzes a UserTask and extracts the process variables that are accesses
     * and the type of access (read, write or both). Note that only the Activiti
     * built-in HTML forms are analyzed.
     *
     * @param userTask
     *            The UserTask that should be analyzed.
     * @return A List containing the process variable accesses represented as
     *         ProcVarAccess.
     */
    public static List<ProcVarAccess> getAccessedProcessVariables(
        UserTask userTask) {

        List<ProcVarAccess> result = new ArrayList<ProcVarAccess>();

        for (FormProperty fp : userTask.getFormProperties()) {

            boolean var = false;
            String expr = fp.getValue();

            if (expr.startsWith("${") && expr.endsWith("}")) {
                expr = expr.substring(2, expr.length() - 1);
                var = true;
            }

            if (var) {
                // variable access
                if (fp.getReadable() != null && fp.getReadable()
                        && fp.getWriteable() != null && !fp.getWriteable()) {
                    // read only
                    result.add(new ProcVarAccess(expr, -1, false));
                } else if (fp.getReadable() != null && !fp.getReadable()
                           && fp.getWriteable() != null && fp.getWriteable()) {
                    // write only
                    result.add(new ProcVarAccess(expr, -1, true));
                } else {
                    // read/write default assumption
                    result.add(new ProcVarAccess(expr, -1, false));
                    result.add(new ProcVarAccess(expr, -1, true));
                }
            }
        }

        return result;

    }

    /**
     * Analyzes a ServiceTask and extracts the process variables that are
     * accesses and the type of access (read, write or both). Note that only
     * ServiceTasks with a Java implementation are analyzed.
     *
     * @param serviceTask
     *            The ServiceTask that should be analyzed.
     * @return A List containing the process variable accesses represented as
     *         ProcVarAccess.
     */
    public static List<ProcVarAccess> getAccessedProcessVariables(
        ServiceTask serviceTask) {

        List<ProcVarAccess> result = new ArrayList<ProcVarAccess>();

        // check if service task has valid java impl
        if (serviceTask.getImplementationType() == null
                || !(serviceTask.getImplementationType().equals("classType"))) {
            return result;
        }

        if (serviceTask.getImplementation() == null
                || serviceTask.getImplementation().length() == 0) {
            return result;
        }

        final String canonicalClassName = serviceTask.getImplementation();
        String[] classParts = canonicalClassName.split("\\.");
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
            return result;
        } else if (filesFound > 1) {
            System.err
            .println("[ListProcVar WARNING] Found multiple source files for class: "
                     + canonicalClassName);
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
            System.err
            .println("[ListProcVar ERROR] Error reading source file for class \""
                     + canonicalClassName + "\": " + e.getMessage());
            return result;
        }

        // build AST for Impl
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setSource(fileData.toString().toCharArray());
        CompilationUnit node = (CompilationUnit) parser.createAST(null);
        node.accept(new ProcVarListASTVisitor(result));

        return result;

    }

    /**
     * Updates the BPMN 2.0 elements that represent the process variable access
     * of a given task, in particular, DataInput and DataOutput elements.
     *
     * @param task
     *            The task whose elements should be updated.
     * @param diagram
     *            The diagram containing the task.
     */
    public static void updateIOSpecification(Task task, Diagram diagram) {

        if (!(task instanceof UserTask || task instanceof ServiceTask))
            return;

        InputOutputSpecification iospec = task.getIoSpecification();
        // create InputOutputSpecification if missing
        if (iospec == null) {
            iospec = Bpmn2Factory.eINSTANCE.createInputOutputSpecification();
            iospec.setId(ID_PREFIX_IOSPEC + task.getId());
            task.setIoSpecification(iospec);
            diagram.eResource().getContents().add(iospec);
        }

        // keep InputOutputSpecification ID consistent with Task ID
        if (!iospec.getId().equals(ID_PREFIX_IOSPEC + task.getId())) {
            task.getIoSpecification().setId(ID_PREFIX_IOSPEC + task.getId());
        }

        // search for NtK InputSet
        InputSet inSet = null;
        for (InputSet tmpInSet : iospec.getInputSets()) {
            if (tmpInSet.getId().equals(ID_PREFIX_INSET + task.getId())) {
                inSet = tmpInSet;
                break;
            }
        }
        if (inSet == null) {
            inSet = Bpmn2Factory.eINSTANCE.createInputSet();
            inSet.setId(ID_PREFIX_INSET + task.getId());
            inSet.setName("Need-to-know DataObjects read by task "
                          + task.getId());
            iospec.getInputSets().add(inSet);
            diagram.eResource().getContents().add(inSet);
        }

        // search for NtK OutputSet
        OutputSet outSet = null;
        for (OutputSet tmpOutSet : iospec.getOutputSets()) {
            if (tmpOutSet.getId().equals(ID_PREFIX_OUTSET + task.getId())) {
                outSet = tmpOutSet;
                break;
            }
        }
        if (outSet == null) {
            outSet = Bpmn2Factory.eINSTANCE.createOutputSet();
            outSet.setId(ID_PREFIX_OUTSET + task.getId());
            outSet.setName("Need-to-know DataObjects written by task "
                           + task.getId());
            iospec.getOutputSets().add(outSet);
            diagram.eResource().getContents().add(outSet);
        }

        // create DataInput/DataOutput elements
        List<ProcVarAccess> varAccesses = new ArrayList<ProcVarAccess>();
        if (task instanceof UserTask) {
            varAccesses = getAccessedProcessVariables((UserTask) task);
        } else {
            varAccesses = getAccessedProcessVariables((ServiceTask) task);
        }

        // add new elements
        List<EObject> diagramElements = diagram.eResource().getContents();
        for (ProcVarAccess varAccess : varAccesses) {
            if (varAccess.isWrite()) {
                // check if DataOutput element exists in diagram
                DataOutput output = null;
                for (EObject obj : diagramElements) {
                    if (obj instanceof DataOutput) {
                        if (((DataOutput) obj).getId().equals(
                                    ID_PREFIX_OUTPUT + varAccess.getName())) {
                            output = (DataOutput) obj;
                            break;
                        }
                    }
                }
                if (output == null) {
                    // DataOutput does not exist, create
                    output = Bpmn2Factory.eINSTANCE.createDataOutput();
                    output.setId(ID_PREFIX_OUTPUT + varAccess.getName());
                    outSet.getDataOutputRefs().add(output);
                    output.getOutputSetRefs().add(outSet);
                    diagram.eResource().getContents().add(output);
                } else {
                    // check if DataOutput element exists in OutputSet
                    if (!outSet.getDataOutputRefs().contains(output)) {
                        outSet.getDataOutputRefs().add(output);
                        output.getOutputSetRefs().add(outSet);
                    }
                }
            } else {
                // check if DataInput exists in diagram
                DataInput input = null;
                for (EObject obj : diagramElements) {
                    if (obj instanceof DataInput) {
                        if (((DataInput) obj).getId().equals(
                                    ID_PREFIX_INPUT + varAccess.getName())) {
                            input = (DataInput) obj;
                            break;
                        }
                    }
                }
                if (input == null) {
                    // DataInput does not exist, create
                    input = Bpmn2Factory.eINSTANCE.createDataInput();
                    input.setId(ID_PREFIX_INPUT + varAccess.getName());
                    inSet.getDataInputRefs().add(input);
                    input.getInputSetRefs().add(inSet);
                    diagram.eResource().getContents().add(input);
                } else {
                    // check if DataInput element exists in InputSet
                    if (!inSet.getDataInputRefs().contains(input)) {
                        inSet.getDataInputRefs().add(input);
                        input.getInputSetRefs().add(inSet);
                    }
                }
            }
        }

        // remove elements for missing process variables
        for (DataInput in : inSet.getDataInputRefs()) {
            boolean varDeleted = true;
            for (ProcVarAccess pvar : varAccesses) {
                if (in.getId().equals(ID_PREFIX_INPUT + pvar.getName())
                        && !pvar.isWrite()) {
                    varDeleted = false;
                    break;
                }
            }
            if (varDeleted) {
                inSet.getDataInputRefs().remove(in);
                in.getInputSetRefs().remove(inSet);
                if (in.getInputSetRefs().size() == 0) {
                    diagram.eResource().getContents().remove(in);
                }
                for (ItemAwareElementAction iaea : in
                        .getItemAwareElementActions()) {
                    for (Permission p : iaea.getPermissions()) {
                        p.getActions().remove(iaea);
                    }
                }
            }
        }

        for (DataOutput out : outSet.getDataOutputRefs()) {
            boolean varDeleted = true;
            for (ProcVarAccess pvar : varAccesses) {
                if (out.getId().equals(ID_PREFIX_OUTPUT + pvar.getName())
                        && pvar.isWrite()) {
                    varDeleted = false;
                    break;
                }
            }
            if (varDeleted) {
                outSet.getDataOutputRefs().remove(out);
                out.getOutputSetRefs().remove(outSet);
                if (out.getOutputSetRefs().size() == 0) {
                    diagram.eResource().getContents().remove(out);
                }
                for (ItemAwareElementAction iaea : out
                        .getItemAwareElementActions()) {
                    for (Permission p : iaea.getPermissions()) {
                        p.getActions().remove(iaea);
                    }
                }
            }
        }

        // remove broken NtK permissions
        for (EObject o : diagramElements) {
            if (o instanceof NeedToKnow) {
                NeedToKnow ntk = (NeedToKnow) o;

                if (ntk.getActions().size() == 0) {
                    // delete ActivityAC
                    for (AuthorizationConstraint ac : ntk
                            .getAuthorizationConstraints()) {
                        diagram.eResource().getContents().remove(ac);
                    }
                    // delete NtK
                    diagram.eResource().getContents().remove(ntk);
                }
            }
        }

    }

}
