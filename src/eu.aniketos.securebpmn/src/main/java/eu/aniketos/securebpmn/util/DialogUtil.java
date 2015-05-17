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

package eu.aniketos.securebpmn.util;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Creates user notification/input dialogs.
 *
 */

public class DialogUtil {

    public static final int ERROR = 2;
    public static final int WARNING = 1;
    public static final int INFO = 0;

    /**
     * Get the currently active Shell or the first available one if none is
     * active.
     *
     * @return The current Shell.
     */
    private static Shell getShell() {
        Display display = PlatformUI.getWorkbench().getDisplay();

        Shell shell = display.getActiveShell();

        if (shell == null) {
            // eclipse window not active, get first shell
            Shell[] shells = display.getShells();
            shell = shells.length > 0 ? shells[0] : null;
        }

        return shell;
    }

    /**
     * Opens a dialog window that contains a message and an input field. The
     * input is returned as a String.
     *
     * @param title
     *            The title of the message window.
     * @param message
     *            The message to be displayed in the window.
     * @param initialValue
     *            The initial value of the input field.
     * @param validator
     *            An InputValidator Class that checks the contents of the input
     *            field.
     * @return The contents of the input field as a String.
     */
    public static String openInputDialog(String title, String message,
                                         String initialValue, IInputValidator validator) {
        InputDialog id = new InputDialog(getShell(), title, message,
                                         initialValue, validator);
        id.open();

        return id.getValue();
    }

    /**
     * Opens a dialog window that contains an image and a message.
     *
     * @param title
     *            The title of the message window.
     * @param message
     *            The message to be displayed in the window.
     * @param image
     *            The image that should be displayed in the window.
     * @param buttons
     *            The labels of the Buttons the window should contain.
     * @param defaultButton
     *            The index of the Button that should be selected by default.
     * @return The index of the Button that was pressed.
     */
    public static int openMessageDialog(String title, String message,
                                        int image, String[] buttons, int defaultButton) {
        MessageDialog dialog;
        switch (image) {
        case INFO:
            dialog = new MessageDialog(getShell(), title, null, message,
                                       MessageDialog.INFORMATION, buttons, defaultButton);
            break;
        case WARNING:
            dialog = new MessageDialog(getShell(), title, null, message,
                                       MessageDialog.WARNING, buttons, defaultButton);
            break;
        case ERROR:
            dialog = new MessageDialog(getShell(), title, null, message,
                                       MessageDialog.ERROR, buttons, defaultButton);
            break;
        default:
            dialog = new MessageDialog(getShell(), title, null, message,
                                       MessageDialog.NONE, buttons, defaultButton);
            break;
        }
        return dialog.open();
    }

    /**
     * Opens a dialog window that contains an image and a message.
     *
     * @param title
     *            The title of the message window.
     * @param message
     *            The message to be displayed in the window.
     * @param image
     *            The image that should be displayed in the window.
     * @return The index of the Button that was pressed.
     */
    public static int openMessageDialog(String title, String message, int image) {
        return openMessageDialog(title, message, image, new String[] { "OK" },
                                 0);
    }
}
