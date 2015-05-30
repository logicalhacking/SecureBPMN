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

package eu.aniketos.securebpmn.validation.view;

import org.eclipse.swt.widgets.Display;

import eu.aniketos.securebpmn.visualization.rbac.RbacVisualization;

/**
 * Controls the automated playback of the attack trace visualization.
 *
 *
 */
public class PlayerControlRunnable implements Runnable {

    private ValidationView view;

    /**
     * Default constructor.
     *
     * @param view
     *            The view that is used.
     */
    public PlayerControlRunnable(ValidationView view) {
        this.view = view;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            while (RbacVisualization.getInstance().getPlayer().hasNextStep()) {
                RbacVisualization.getInstance().getPlayer().nextStep();
                // set status text
                Display.getDefault().syncExec(new Runnable() {
                    public void run() {
                        view.setStatusText(RbacVisualization.getInstance()
                                           .getPlayer().getStepInfo());
                    }
                });
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
        }
    }

}
