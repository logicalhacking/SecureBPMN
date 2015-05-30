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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;

import eu.aniketos.securebpmn.ntk.NeedToKnowUtil;
import eu.aniketos.securebpmn.util.DialogUtil;
import eu.aniketos.securebpmn.validation.ProcVarAccess;

/**
 * The feature collects a list of all process variables that are accessed in the
 * process and the way they are accessed (read, write or both) and displays it
 * to the user.
 *
 *
 */
public class ListProcessVariablesFeature extends AbstractCustomFeature {

    public ListProcessVariablesFeature(IFeatureProvider fp) {
        super(fp);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.graphiti.features.impl.AbstractFeature#getName()
     */
    @Override
    public String getName() {
        return "List process variables"; //$NON-NLS-1$
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
        return "Retrieve all processs variables accessed and display them to the user."; //$NON-NLS-1$
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
        // fill list
        Map<String, String> accesses = new HashMap<String, String>();

        List<EObject> diagramContents = getDiagram().eResource().getContents();

        for (EObject object : diagramContents) {
            if (object instanceof ServiceTask) {

                ServiceTask sTask = (ServiceTask) object;

                for (ProcVarAccess varAccess : NeedToKnowUtil
                        .getAccessedProcessVariables(sTask)) {
                    if (accesses.containsKey(varAccess.getName())) {
                        // update
                        String currentAccess = accesses
                                               .get(varAccess.getName());
                        if (currentAccess.length() > 1) {
                            // nothing to do, already rw
                            continue;
                        }

                        if ((currentAccess.equals("r") && varAccess.isWrite())
                                || (currentAccess.equals("w") && !varAccess
                                    .isWrite())) {
                            accesses.put(varAccess.getName(), "rw");
                        } else
                            continue;

                    } else {
                        accesses.put(varAccess.getName(),
                                     varAccess.isWrite() ? "w" : "r");
                    }
                }
            } else if (object instanceof UserTask) {

                UserTask uTask = (UserTask) object;

                for (ProcVarAccess varAccess : NeedToKnowUtil
                        .getAccessedProcessVariables(uTask)) {
                    if (accesses.containsKey(varAccess.getName())) {
                        // update
                        String currentAccess = accesses
                                               .get(varAccess.getName());
                        if (currentAccess.length() > 1) {
                            // nothing to do, already rw
                            continue;
                        }

                        if ((currentAccess.equals("r") && varAccess.isWrite())
                                || (currentAccess.equals("w") && !varAccess
                                    .isWrite())) {
                            accesses.put(varAccess.getName(), "rw");
                        } else
                            continue;

                    } else {
                        accesses.put(varAccess.getName(),
                                     varAccess.isWrite() ? "w" : "r");
                    }
                }

            }
        }

        // notify user
        StringBuilder sb = new StringBuilder();

        Set<String> accessedVars = accesses.keySet();

        if (accessedVars.size() == 0) {
            sb.append("No process variables are accessed in this process.");
        } else {

            sb.append("The following process variables are being accessed:\n");

            for (String s : accessedVars) {
                sb.append(s + " (" + accesses.get(s) + ")\n");
            }

        }

        DialogUtil.openMessageDialog("Process variable access", sb.toString(),
                                     DialogUtil.INFO);

    }

}
