package org.activiti.designer.preferences;

import org.activiti.designer.eclipse.common.ActivitiPlugin;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
// <SecureBPMN>
import org.activiti.designer.util.preferences.Preferences;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FileFieldEditor;
// </SecureBPMN>

public class ActivitiPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public ActivitiPreferencePage() {
		super(GRID);
	}

	public void createFieldEditors() {
		// <SecureBPMN>
		// Adding custom settings to the preference pane.
		addField(new FileFieldEditor(Preferences.PATH_TO_SATMC_BINARY.getPreferenceId(), 
				"&Location of the SATMC binary", getFieldEditorParent()));
		addField(new BooleanFieldEditor(Preferences.ALL_TASKS_AS_HUMANTASKS.getPreferenceId(), "&Analyze all Tasks as HumanTasks", getFieldEditorParent()));
        // </SecureBPMN>
	}

	@Override
	public void init(IWorkbench workbench) {
		IPreferenceStore prefStore = ActivitiPlugin.getDefault().getPreferenceStore();
		setPreferenceStore(prefStore);
        // <SecureBPMN>
		setDescription("Set general preferences of the Activiti Designer");
		setTitle("Activiti Designer Preferences");
		// </SecureBPMN>
	}
}
