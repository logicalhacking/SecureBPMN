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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;

/**
 * This feature performs an analysis of the access control specification and
 * constraints contained in the process and notifies the user, if violations
 * were found or not. For analysis of the ASLan file, the local SATMC binary is
 * used.
 *
 *
 */
public class ValidateAslanLocalFeature extends ValidateAslanFeature {

    public ValidateAslanLocalFeature(IFeatureProvider fp) {
        super(fp);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * eu.aniketos.securebpmn.features.ValidateAslanFeature
     * #getName()
     */
    @Override
    public String getName() {
        return "Validate security local"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * eu.aniketos.securebpmn.features.ValidateAslanFeature
     * #getDescription()
     */
    @Override
    public String getDescription() {
        return "Validate security featues using local SATMC binary"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * eu.aniketos.securebpmn.features.ValidateAslanFeature
     * #canExecute(org.eclipse.graphiti.features.context.ICustomContext)
     */
    @Override
    public boolean canExecute(ICustomContext context) {
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * eu.aniketos.securebpmn.features.ValidateAslanFeature
     * #execute(org.eclipse.graphiti.features.context.ICustomContext)
     */
    @Override
    public void execute(ICustomContext context) {
        super.execute(true);
    }
}
