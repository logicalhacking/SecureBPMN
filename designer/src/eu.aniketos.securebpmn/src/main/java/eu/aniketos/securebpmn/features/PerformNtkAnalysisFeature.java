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

import java.util.List;

import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.InputSet;
import org.eclipse.bpmn2.OutputSet;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.securebpmn2.ActivityAuthorizationConstraint;
import org.eclipse.securebpmn2.AuthorizationConstraint;
import org.eclipse.securebpmn2.CompositeItemAwareElementAction;
import org.eclipse.securebpmn2.ItemAwareElementAction;
import org.eclipse.securebpmn2.NeedToKnow;
import org.eclipse.securebpmn2.Permission;

import eu.aniketos.securebpmn.ntk.NeedToKnowUtil;
import eu.aniketos.securebpmn.util.DialogUtil;

/**
 * This feature performs an analysis of the need-to-know specification and
 * constraints in the process and notifies the user with the result, i.e., if
 * violations were found or not.
 *
 *
 */
public class PerformNtkAnalysisFeature extends AbstractCustomFeature {

    public PerformNtkAnalysisFeature(IFeatureProvider fp) {
        super(fp);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.graphiti.features.impl.AbstractFeature#getName()
     */
    @Override
    public String getName() {
        return "Perform NtK analysis"; //$NON-NLS-1$
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
        return "Perform the analysis of need-to-know specifications"; //$NON-NLS-1$
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
    @Override
    public void execute(ICustomContext context) {

        final List<EObject> diagramElements = getDiagram().eResource()
                                              .getContents();

        for (EObject obj : diagramElements) {

            if (!(obj instanceof UserTask || obj instanceof ServiceTask))
                continue;

            final Task t = (Task) obj;

            if (t.getIoSpecification() == null)
                continue;

            // check read access
            for (InputSet inSet : t.getIoSpecification().getInputSets()) {
                for (DataInput in : inSet.getDataInputRefs()) {
                    boolean isValid = false;
                    for (ItemAwareElementAction iaea : in
                            .getItemAwareElementActions()) {
                        if (isValid)
                            break;

                        if (!iaea.getActionName().equals("read"))
                            continue;

                        for (CompositeItemAwareElementAction comp : iaea
                                .getCompositeItemAwareElementActions()) {
                            if (comp.getActionName().equals("read/write")) {
                                // analyze parent composite action from this
                                // point on
                                iaea = comp;
                                break;
                            }
                        }

                        for (Permission p : iaea.getPermissions()) {
                            if (isValid)
                                break;

                            if (!(p instanceof NeedToKnow))
                                continue;

                            for (AuthorizationConstraint ac : p
                                    .getAuthorizationConstraints()) {

                                if (!(ac instanceof ActivityAuthorizationConstraint))
                                    continue;

                                ActivityAuthorizationConstraint aac = (ActivityAuthorizationConstraint) ac;

                                if (aac.getActivities().contains(t)) {
                                    isValid = true;
                                    break;
                                }
                            }
                        }
                    }

                    if (!isValid) {
                        String varName = in.getId().substring(
                                             NeedToKnowUtil.ID_PREFIX_INPUT.length());

                        if (t instanceof UserTask) {
                            // UserTask message
                            DialogUtil
                            .openMessageDialog(
                                "Validation error",
                                "Read access of process variable \""
                                + varName
                                + "\" is not permitted at Task \""
                                + t.getId()
                                + "\".\n\nRemove the corresponding form field or review your need-to-know specification.",
                                DialogUtil.ERROR);
                        } else if (t instanceof ServiceTask) {
                            // ServiceTask message
                            DialogUtil
                            .openMessageDialog(
                                "Validation error",
                                "Read access of process variable \""
                                + varName
                                + "\" is not permitted at Task \""
                                + t.getId()
                                + "\".\n\nReview your Java implementation or your need-to-know specification. To see where the access occurs in the implementation, use the \"Check Service Task\" feature available via the context menu.",
                                DialogUtil.ERROR);
                        }
                        return;
                    }
                }
            }

            // check write access
            for (OutputSet outSet : t.getIoSpecification().getOutputSets()) {
                for (DataOutput out : outSet.getDataOutputRefs()) {
                    boolean isValid = false;
                    for (ItemAwareElementAction iaea : out
                            .getItemAwareElementActions()) {
                        if (isValid)
                            break;

                        if (!iaea.getActionName().equals("write"))
                            continue;

                        for (CompositeItemAwareElementAction comp : iaea
                                .getCompositeItemAwareElementActions()) {
                            if (comp.getActionName().equals("read/write")) {
                                // analyze parent composite action from this
                                // point on
                                iaea = comp;
                                break;
                            }
                        }

                        for (Permission p : iaea.getPermissions()) {
                            if (isValid)
                                break;

                            if (!(p instanceof NeedToKnow))
                                continue;

                            for (AuthorizationConstraint ac : p
                                    .getAuthorizationConstraints()) {

                                if (!(ac instanceof ActivityAuthorizationConstraint))
                                    continue;

                                ActivityAuthorizationConstraint aac = (ActivityAuthorizationConstraint) ac;

                                if (aac.getActivities().contains(t)) {
                                    isValid = true;
                                    break;
                                }
                            }
                        }
                    }

                    if (!isValid) {
                        String varName = out.getId().substring(
                                             NeedToKnowUtil.ID_PREFIX_OUTPUT.length());

                        if (t instanceof UserTask) {
                            // UserTask message
                            DialogUtil
                            .openMessageDialog(
                                "Validation error",
                                "Write access of process variable \""
                                + varName
                                + "\" is not permitted at Task \""
                                + t.getId()
                                + "\".\n\nRemove the corresponding form field or review your need-to-know specification.",
                                DialogUtil.ERROR);
                        } else if (t instanceof ServiceTask) {
                            // ServiceTask message
                            DialogUtil
                            .openMessageDialog(
                                "Validation error",
                                "Write access of process variable \""
                                + varName
                                + "\" is not permitted at Task \""
                                + t.getId()
                                + "\".\n\nReview your Java implementation or your need-to-know specification. To see where the access occurs in the implementation, use the \"Check Service Task\" feature available via the context menu.",
                                DialogUtil.ERROR);
                        }
                        return;
                    }
                }
            }

        }

        // no errors
        DialogUtil
        .openMessageDialog(
            "No validation errors",
            "All process variables are accessed according to the specification.",
            DialogUtil.INFO);

    }

}
