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

import java.lang.Thread.State;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import eu.aniketos.securebpmn.visualization.rbac.RbacVisualization;

/**
 * The Eclipse view controlling the attack trace visualization.
 *
 *
 */
public class ValidationView extends ViewPart {

    private Text statusText;
    private Thread playerThread;

    /**
     * Default constructor.
     */
    public ValidationView() {
        // beware: gets called before full ui is loaded if view was left open on
        // exit!
        statusText = null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
     * .Composite)
     */
    @Override
    public void createPartControl(Composite parent) {

        // check for validation result
        if (!RbacVisualization.getInstance().isResultSet()) {
            Label error = new Label(parent, SWT.NONE);
            error.setText("No validation result present. Please close this view and run the validation via your preferred method.");
            return;
        }

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        parent.setLayout(gridLayout);

        RowLayout buttonLayout = new RowLayout();
        buttonLayout.marginLeft = 5;
        buttonLayout.marginTop = 5;
        buttonLayout.marginRight = 5;
        buttonLayout.marginBottom = 5;
        Group buttonGroup = new Group(parent, SWT.SHADOW_IN);
        buttonGroup.setLayout(buttonLayout);
        buttonGroup.setText("visualization controls");
        buttonGroup.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
                                               false, 1, 1));

        Button first = new Button(buttonGroup, SWT.PUSH);
        first.setText("first step");
        first.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent event) {
                RbacVisualization.getInstance().getPlayer().firstStep();
                statusText.setText(RbacVisualization.getInstance().getPlayer()
                                   .getStepInfo());
                if (playerThread != null) {
                    playerThread.interrupt();
                    playerThread = null;
                }
            }

            public void widgetDefaultSelected(SelectionEvent event) {
            }
        });

        Button previous = new Button(buttonGroup, SWT.PUSH);
        previous.setText("previous step");
        previous.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent event) {
                RbacVisualization.getInstance().getPlayer().previousStep();
                statusText.setText(RbacVisualization.getInstance().getPlayer()
                                   .getStepInfo());
                if (playerThread != null) {
                    playerThread.interrupt();
                    playerThread = null;
                }
            }

            public void widgetDefaultSelected(SelectionEvent event) {
            }
        });

        Button playPause = new Button(buttonGroup, SWT.PUSH);
        playPause.setText("play/pause trace");
        playPause.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent event) {
                if (playerThread == null
                        || playerThread.getState() == State.TERMINATED) {
                    playerThread = new Thread(new PlayerControlRunnable(
                                                  ValidationView.this));
                    playerThread.start();
                } else {
                    playerThread.interrupt();
                    playerThread = null;
                }
            }

            public void widgetDefaultSelected(SelectionEvent event) {
            }
        });

        Button next = new Button(buttonGroup, SWT.PUSH);
        next.setText("next step");
        next.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent event) {
                RbacVisualization.getInstance().getPlayer().nextStep();
                statusText.setText(RbacVisualization.getInstance().getPlayer()
                                   .getStepInfo());
                if (playerThread != null) {
                    playerThread.interrupt();
                    playerThread = null;
                }
            }

            public void widgetDefaultSelected(SelectionEvent event) {
            }
        });

        Button last = new Button(buttonGroup, SWT.PUSH);
        last.setText("last step");
        last.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent event) {
                RbacVisualization.getInstance().getPlayer().lastStep();
                statusText.setText(RbacVisualization.getInstance().getPlayer()
                                   .getStepInfo());
                if (playerThread != null) {
                    playerThread.interrupt();
                    playerThread = null;
                }
            }

            public void widgetDefaultSelected(SelectionEvent event) {
            }
        });

        Group attackGroup = new Group(parent, SWT.SHADOW_IN);
        attackGroup.setLayout(new FillLayout());
        attackGroup.setText("attack trace");
        attackGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
                                               1, 2));
        Text attackText = new Text(attackGroup, SWT.MULTI | SWT.V_SCROLL
                                   | SWT.H_SCROLL);
        // attackText.setText(SatmcVisualization.getInstance().getFilteredAttackTrace());
        attackText.setText(RbacVisualization.getInstance().getAttackTrace());
        attackText.setEditable(false);

        Group statusGroup = new Group(parent, SWT.SHADOW_IN);
        statusGroup.setLayout(new FillLayout());
        statusGroup.setText("step information");
        statusGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true,
                                               1, 1));
        statusText = new Text(statusGroup, SWT.MULTI | SWT.V_SCROLL
                              | SWT.H_SCROLL);
        statusText.setText(RbacVisualization.getInstance().getPlayer()
                           .getStepInfo());
        statusText.setEditable(false);

    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    @Override
    public void setFocus() {
    	if(statusText != null){
    	    statusText.setFocus();
    	}
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.ui.part.WorkbenchPart#dispose()
     */
    @Override
    public void dispose() {
        RbacVisualization.getInstance().reset();
        super.dispose();
    }

    /**
     * Sets the text of the status text field. Note, that you must use
     * Display.getDefault().syncExec if you are calling this method from a
     * different thread.
     *
     * @param text
     */
    public void setStatusText(String text) {
        if (statusText != null)
            statusText.setText(text);
    }

}
