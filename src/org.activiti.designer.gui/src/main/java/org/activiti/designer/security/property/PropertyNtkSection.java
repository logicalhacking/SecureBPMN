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

package org.activiti.designer.security.property;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.activiti.designer.util.eclipse.ActivitiUiUtil;
import org.activiti.designer.util.property.ActivitiPropertySection;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditor;

import org.eclipse.securebpmn2.Action;
import org.eclipse.securebpmn2.ActivityAuthorizationConstraint;
import org.eclipse.securebpmn2.AtomicActivityAction;
import org.eclipse.securebpmn2.AtomicItemAwareElementAction;
import org.eclipse.securebpmn2.CompositeItemAwareElementAction;
import org.eclipse.securebpmn2.ItemAwareElementAction;
import org.eclipse.securebpmn2.NeedToKnow;
import org.eclipse.securebpmn2.Permission;
import org.eclipse.securebpmn2.Role;
import org.eclipse.securebpmn2.Securebpmn2Factory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import eu.aniketos.securebpmn.ntk.NeedToKnowUtil;
import eu.aniketos.securebpmn.util.SecurityUtil;

/**
 * Creates and controls the need-to-know tab in the properties view.
 * 
 * 
 */
public class PropertyNtkSection extends ActivitiPropertySection implements
		ITabbedPropertyConstants {

	private CCombo actionCombo;
	private CCombo processVariableCombo;
	private CCombo roleCombo;
	private Table table;
	private Composite buttonBox;
	private Button addButton;
	private Button removeButton;
	private int checkCount = 0;
	private SelectionListener selectionListener;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls
	 * (org.eclipse.swt.widgets.Composite,
	 * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		// general vars
		TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
		Composite composite = factory.createFlatFormComposite(parent);
		FormData data;

		// setup action combo box
		actionCombo = factory.createCCombo(composite, SWT.NONE);
		for (String actionName : NeedToKnowUtil
				.getItemAwareElementActionNames()) {
			actionCombo.add(actionName);
		}
		data = new FormData();
		data.left = new FormAttachment(0, 120);
		data.right = new FormAttachment(80, 0);
		data.top = new FormAttachment(0, VSPACE);
		actionCombo.setLayoutData(data);
		// actionCombo.addFocusListener(listener);

		// setup action label
		CLabel actionLabel = factory.createCLabel(composite, "Action :"); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(actionCombo, -HSPACE);
		data.top = new FormAttachment(actionCombo, 0, SWT.CENTER);
		actionLabel.setLayoutData(data);

		// setup process variable combo box
		processVariableCombo = factory.createCCombo(composite, SWT.NONE);
		data = new FormData();
		data.left = new FormAttachment(0, 120);
		data.right = new FormAttachment(80, 0);
		data.top = new FormAttachment(actionCombo, VSPACE);
		processVariableCombo.setLayoutData(data);
		// processVariableCombo.addFocusListener(listener);

		// setup process variable label
		CLabel processVariableLabel = factory.createCLabel(composite,
				"Process Variable :"); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(processVariableCombo, -HSPACE);
		data.top = new FormAttachment(processVariableCombo, 0, SWT.CENTER);
		processVariableLabel.setLayoutData(data);

		// setup role combo box
		roleCombo = factory.createCCombo(composite, SWT.NONE);
		for (Role role : SecurityUtil.getRoles(getDiagram())) {
			roleCombo.add(role.getName());
		}
		data = new FormData();
		data.left = new FormAttachment(0, 120);
		data.right = new FormAttachment(80, 0);
		data.top = new FormAttachment(processVariableCombo, VSPACE);
		roleCombo.setLayoutData(data);
		// roleCombo.addFocusListener(listener);

		// setup role label
		CLabel roleLabel = factory.createCLabel(composite, "Role :"); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(roleCombo, -HSPACE);
		data.top = new FormAttachment(roleCombo, 0, SWT.CENTER);
		roleLabel.setLayoutData(data);

		// setup table label
		CLabel tableLabel = factory
				.createCLabel(composite, "NtK Permissions :"); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(0, 100);
		data.top = new FormAttachment(roleCombo, VSPACE);
		tableLabel.setLayoutData(data);

		// setup permission table part 1
		data = new FormData(200, 140);
		data.left = new FormAttachment(tableLabel, 10);
		data.right = new FormAttachment(80, 0);
		data.top = new FormAttachment(roleCombo, VSPACE);
		table = new Table(composite, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL
				| SWT.H_SCROLL);
		table.setLayoutData(data);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		// setup table buttons
		buttonBox = getButtonBoxControl(composite);
		data = new FormData();
		data.left = new FormAttachment(table, -HSPACE);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(roleLabel, 0);
		buttonBox.setLayoutData(data);
		buttonBox.setVisible(true);
		// buttonBox.addFocusListener(listener);

		// setup permission table part 2
		String[] titles = { "  ", "Name", "Process Variable", "Action", "Roles" };
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			column.setWidth(170);
		}
		table.getColumn(0).setWidth(30);
		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.CHECK) {
					TableItem item = (TableItem) event.item;
					if (item.getChecked() == true) {
						checkCount++;
					} else {
						checkCount--;
					}
				}
				selectionChanged();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	@Override
	public void refresh() {
		
		// try to get the roles if combo is still empty
		if (roleCombo.getItems().length == 0) {
			for (Role role : SecurityUtil.getRoles(getDiagram())) {
				roleCombo.add(role.getName());
			}
		}

		// update task IO specifications
		System.out.print("[SCVM-BPMN] updating task IO specifications...");
		final Diagram diagram = getDiagram();

		DiagramEditor diagramEditor = (DiagramEditor) getDiagramEditor();
		TransactionalEditingDomain editingDomain = diagramEditor
				.getEditingDomain();
		ActivitiUiUtil.runModelChange(new Runnable() {
			public void run() {
				final List<Task> tasks = new ArrayList<Task>();
				for (EObject obj : diagram.eResource().getContents()) {
					if (obj instanceof Task) {
						tasks.add((Task) obj);
					}
				}

				for (Task t : tasks) {
					NeedToKnowUtil.updateIOSpecification(t, diagram);
				}
			}
		}, editingDomain, "Model Update");
		System.out.println("done!");

		// refresh process variable combo box
		List<String> accessedProcessVariables = new ArrayList<String>();
		;

		PictogramElement pe = getSelectedPictogramElement();
		if (pe != null) {
			Object bo = Graphiti.getLinkService()
					.getBusinessObjectForLinkedPictogramElement(pe);
			if (bo instanceof UserTask) {
				accessedProcessVariables = NeedToKnowUtil
						.getAccessedProcessVariableNames((UserTask) bo);
				updateTable((Activity) bo);
			} else if (bo instanceof ServiceTask) {
				accessedProcessVariables = NeedToKnowUtil
						.getAccessedProcessVariableNames((ServiceTask) bo);
				updateTable((Activity) bo);
			}
		} else {
			return;
		}

		processVariableCombo.removeAll();
		for (String processVariableName : accessedProcessVariables) {
			processVariableCombo.add(processVariableName);
		}

	}

	/**
	 * Creates the Composite holding the Buttons for adding and removing
	 * need-to-know Permissions.
	 * 
	 * @param parent
	 *            The parent Composite that the new Composite will be placed on.
	 * @return A Composite holding the add/remove Buttons
	 */
	private Composite getButtonBoxControl(Composite parent) {
		if (buttonBox == null) {
			buttonBox = new Composite(parent, SWT.NULL);
			GridLayout layout = new GridLayout();
			layout.marginWidth = 0;
			buttonBox.setLayout(layout);
			buttonBox.setBackground(Display.getDefault().getSystemColor(
					SWT.COLOR_WHITE));
			addButton = createPushButton(buttonBox, "Add");
			removeButton = createPushButton(buttonBox, "Remove");
			buttonBox.addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent event) {
					addButton = null;
					removeButton = null;
					buttonBox = null;
				}
			});
		}
		selectionChanged();
		return buttonBox;

	}

	/**
	 * Enables the remove Button when at least one element in the table is
	 * selected and disables it when no elements are selected.
	 */
	private void selectionChanged() {
		removeButton.setEnabled(checkCount > 0);
	}

	/**
	 * Helper method to create and set up a Button.
	 * 
	 * @param parent
	 *            The parent Composite the Button will be placed on.
	 * @param key
	 *            The text that will be displayed on the Button.
	 * @return A newly created Button.
	 */
	private Button createPushButton(Composite parent, String key) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText(key);
		button.setFont(parent.getFont());
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 40;
		button.setLayoutData(data);
		button.addSelectionListener(getSelectionListener());
		return button;
	}

	/**
	 * Returns the SelectionListener and creates one if no Listener is present.
	 * 
	 * @return The SelectionListener for this class.
	 */
	private SelectionListener getSelectionListener() {
		if (selectionListener == null) {
			createSelectionListener();
		}
		return selectionListener;
	}

	/**
	 * Creates a SelectionListener that manages the Button functionality.
	 */
	private void createSelectionListener() {
		selectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Widget widget = event.widget;
				if (widget == addButton) {
					addPressed();
				} else if (widget == removeButton) {
					removePressed();
				} else if (widget == table) {
					selectionChanged();
				}
			}
		};
	}

	/**
	 * Contains the code that is executed when the "add" Button is pressed. In
	 * particular, it creates a new NeedToKnow Permission or updates the
	 * existing one.
	 */
	private void addPressed() {
		if (actionCombo.getText() == "" || processVariableCombo.getText() == ""
				|| roleCombo.getText() == "")
			return;

		PictogramElement pe = getSelectedPictogramElement();
		if (pe != null) {
			Object bo = Graphiti.getLinkService()
					.getBusinessObjectForLinkedPictogramElement(pe);
			if (bo instanceof Activity) {
				DiagramEditor diagramEditor = (DiagramEditor) getDiagramEditor();
				TransactionalEditingDomain editingDomain = diagramEditor
						.getEditingDomain();
				ActivitiUiUtil.runModelChange(new Runnable() {
					public void run() {
						Object bo = Graphiti.getLinkService()
								.getBusinessObjectForLinkedPictogramElement(
										getSelectedPictogramElement());
						if (bo == null) {
							return;
						} else {
							// check for existing NtK permissions
							Activity activity = (Activity) bo;
							NeedToKnow selectedNtk = null;

							for (ActivityAuthorizationConstraint ac : activity
									.getActivityAuthorizationConstraints()) {
								if (selectedNtk != null)
									break;

								for (Permission p : ac.getPermissions()) {
									if (selectedNtk != null)
										break;

									if (p instanceof NeedToKnow) {
										NeedToKnow candidateNtk = (NeedToKnow) p;

										for (Action a : p.getActions()) {
											if (selectedNtk != null)
												break;

											if (a instanceof ItemAwareElementAction) {
												ItemAwareElementAction iaea = (ItemAwareElementAction) a;
												if (iaea.getActionName()
														.equalsIgnoreCase(
																actionCombo
																		.getText())) {
													// action is the same
													if (iaea instanceof AtomicItemAwareElementAction) {
														// check directly
														if (iaea.getItemAwareElement() == null)
															continue;

														if (actionCombo
																.getText()
																.equalsIgnoreCase(
																		"read")
																&& iaea.getItemAwareElement() instanceof DataInput
																&& iaea.getItemAwareElement()
																		.getId()
																		.equals(NeedToKnowUtil.ID_PREFIX_INPUT
																				+ processVariableCombo
																						.getText())) {
															selectedNtk = candidateNtk;
															break;
														} else if (actionCombo
																.getText()
																.equalsIgnoreCase(
																		"write")
																&& iaea.getItemAwareElement() instanceof DataOutput
																&& iaea.getItemAwareElement()
																		.getId()
																		.equals(NeedToKnowUtil.ID_PREFIX_OUTPUT
																				+ processVariableCombo
																						.getText())) {
															selectedNtk = candidateNtk;
															break;
														}
													} else {
														// check subactions
														boolean containsRead = false;
														boolean containsWrite = false;

														for (ItemAwareElementAction inner_iaea : ((CompositeItemAwareElementAction) iaea)
																.getItemAwareElementActions()) {
															if (inner_iaea instanceof AtomicActivityAction) {
																if (containsRead
																		&& containsWrite)
																	break;
																if (inner_iaea
																		.getItemAwareElement() == null)
																	continue;

																if (inner_iaea
																		.getItemAwareElement() instanceof DataInput
																		&& inner_iaea
																				.getItemAwareElement()
																				.getId()
																				.equals(NeedToKnowUtil.ID_PREFIX_INPUT
																						+ processVariableCombo
																								.getText())) {
																	containsRead = true;
																}
																if (inner_iaea
																		.getItemAwareElement() instanceof DataOutput
																		&& inner_iaea
																				.getItemAwareElement()
																				.getId()
																				.equals(NeedToKnowUtil.ID_PREFIX_OUTPUT
																						+ processVariableCombo
																								.getText())) {
																	containsWrite = true;
																}
															}
														}

														if (containsRead
																&& containsWrite) {
															selectedNtk = candidateNtk;
															break;
														}
													}
												}
											}
										}
									}
								}
							}

							if (selectedNtk == null) {
								// combination not present, create
								selectedNtk = Securebpmn2Factory.eINSTANCE
										.createNeedToKnow();
								selectedNtk.setId(UUID.randomUUID().toString());
								selectedNtk.setPName("Perm-" + activity.getId()
										+ "-" + processVariableCombo.getText()
										+ "-" + actionCombo.getText());
								Role targetRole = null;
								for (Role role : SecurityUtil
										.getRoles(getDiagram())) {
									if (role.getName().equalsIgnoreCase(
											roleCombo.getText())) {
										targetRole = role;
										break;
									}
								}
								if (!getDiagram().eResource().getContents()
										.contains(targetRole)
										&& targetRole != null) {
									getDiagram().eResource().getContents()
											.add(targetRole);
								}
								selectedNtk.getRoles().add(targetRole);
								getDiagram().eResource().getContents()
										.add(selectedNtk);

								ActivityAuthorizationConstraint activityAC = Securebpmn2Factory.eINSTANCE
										.createActivityAuthorizationConstraint();
								activityAC.setId(UUID.randomUUID().toString());
								activityAC.getPermissions().add(selectedNtk);
								activityAC.getActivities().add(activity);
								getDiagram().eResource().getContents()
										.add(activityAC);

								if (actionCombo.getText().equals("read/write")) {
									// create composite action
									AtomicItemAwareElementAction readAction = Securebpmn2Factory.eINSTANCE
											.createAtomicItemAwareElementAction();
									readAction.setId(UUID.randomUUID()
											.toString());
									readAction.setActionName("read");
									ItemAwareElement readElement = findItemAwareElement(
											processVariableCombo.getText(),
											false);
									if (readElement != null) {
										readAction
												.setItemAwareElement(readElement);
									} else {
										System.err
												.println("[SCVM-BPMN] ItemAwareElement for variable "
														+ processVariableCombo
																.getText()
														+ "/read does not exist!");
									}
									getDiagram().eResource().getContents()
											.add(readAction);

									AtomicItemAwareElementAction writeAction = Securebpmn2Factory.eINSTANCE
											.createAtomicItemAwareElementAction();
									writeAction.setId(UUID.randomUUID()
											.toString());
									writeAction.setActionName("write");
									ItemAwareElement writeElement = findItemAwareElement(
											processVariableCombo.getText(),
											true);
									if (writeElement != null) {
										writeAction
												.setItemAwareElement(writeElement);
									} else {
										System.err
												.println("[SCVM-BPMN] ItemAwareElement for variable "
														+ processVariableCombo
																.getText()
														+ "/write does not exist!");
									}
									getDiagram().eResource().getContents()
											.add(writeAction);

									CompositeItemAwareElementAction rwAction = Securebpmn2Factory.eINSTANCE
											.createCompositeItemAwareElementAction();
									rwAction.setId(UUID.randomUUID().toString());
									rwAction.setActionName("read/write");
									rwAction.getItemAwareElementActions().add(
											readAction);
									rwAction.getItemAwareElementActions().add(
											writeAction);
									rwAction.getPermissions().add(selectedNtk);
									getDiagram().eResource().getContents()
											.add(rwAction);

								} else {
									// create atomic action
									AtomicItemAwareElementAction iaeAction = Securebpmn2Factory.eINSTANCE
											.createAtomicItemAwareElementAction();
									iaeAction.setId(UUID.randomUUID()
											.toString());
									iaeAction.setActionName(actionCombo
											.getText());
									iaeAction.getPermissions().add(selectedNtk);
									// add ItemAwareElement
									ItemAwareElement iaElement = findItemAwareElement(
											processVariableCombo.getText(),
											actionCombo.getText()
													.equals("read") ? false
													: true);
									if (iaElement != null) {
										iaeAction
												.setItemAwareElement(iaElement);
									} else {
										System.err
												.println("[SCVM-BPMN] ItemAwareElement for variable "
														+ processVariableCombo
																.getText()
														+ "/"
														+ actionCombo.getText()
														+ " does not exist!");
									}
									getDiagram().eResource().getContents()
											.add(iaeAction);
								}

							} else {
								// update existing ntk: add role
								Role targetRole = null;
								for (Role role : SecurityUtil
										.getRoles(getDiagram())) {
									if (role.getName().equalsIgnoreCase(
											roleCombo.getText())) {
										targetRole = role;
										break;
									}
								}
								if (!getDiagram().eResource().getContents()
										.contains(targetRole)
										&& targetRole != null) {
									getDiagram().eResource().getContents()
											.add(targetRole);
								}
								selectedNtk.getRoles().add(targetRole);
							}

							updateTable(activity);
						}
					}
				}, editingDomain, "Adding NtK Permission");
			}
		}
	}

	/**
	 * Helper method for retrieving the ItemAwareElement, in particular the
	 * DataInput or DataOutput element, for a given process variable name and
	 * the type of access (read or write).
	 * 
	 * @param processVariable
	 *            The name of the process variable.
	 * @param isWriteable
	 *            The type of access to the variable. true for write access,
	 *            false for read access.
	 * @return The ItemAwareElement corresponding to the process variable
	 *         access.
	 */
	private ItemAwareElement findItemAwareElement(String processVariable,
			boolean isWriteable) {

		for (EObject o : getDiagram().eResource().getContents()) {

			if (o instanceof DataInput && !isWriteable) {
				final DataInput in = (DataInput) o;
				if (in.getId().equals(
						NeedToKnowUtil.ID_PREFIX_INPUT + processVariable))
					return in;
			} else if (o instanceof DataOutput && isWriteable) {
				final DataOutput out = (DataOutput) o;
				if (out.getId().equals(
						NeedToKnowUtil.ID_PREFIX_OUTPUT + processVariable))
					return out;
			}
		}

		return null;
	}

	/**
	 * Contains the code that is executed when the "remove" Button is pressed.
	 * In particular, it removes the NeedToKnow Permission from the Diagram.
	 */
	private void removePressed() {

		PictogramElement pe = getSelectedPictogramElement();
		if (pe != null) {
			Object bo = Graphiti.getLinkService()
					.getBusinessObjectForLinkedPictogramElement(pe);
			if (bo instanceof Activity) {
				DiagramEditor diagramEditor = (DiagramEditor) getDiagramEditor();
				TransactionalEditingDomain editingDomain = diagramEditor
						.getEditingDomain();
				ActivitiUiUtil.runModelChange(new Runnable() {

					public void run() {
						Object bobj = Graphiti.getLinkService()
								.getBusinessObjectForLinkedPictogramElement(
										getSelectedPictogramElement());
						if (bobj == null) {
							return;
						} else {
							// remove the selected
							// permission/activitiyAC/IAEAction
							Activity activity = (Activity) bobj;
							for (TableItem tItem : table.getItems()) {
								List<ActivityAuthorizationConstraint> removeList = new ArrayList<ActivityAuthorizationConstraint>();
								if (tItem.getChecked()) {
									for (ActivityAuthorizationConstraint actAC : activity
											.getActivityAuthorizationConstraints()) {
										if (actAC.getPermissions().size() > 0) {
											Permission p = actAC
													.getPermissions().get(0);
											if (p.getPName().equals(
													tItem.getText(1))) {
												for (Action a : p.getActions()) {
													getDiagram().eResource()
															.getContents()
															.remove(a);
												}
												p.getActions().clear();
												p.getRoles().clear();
												p.getAuthorizationConstraints()
														.clear();
												getDiagram().eResource()
														.getContents()
														.remove(p);
												removeList.add(actAC);
												getDiagram().eResource()
														.getContents()
														.remove(actAC);
											}
										}

									}
								}
								for (ActivityAuthorizationConstraint actAC : removeList) {
									activity.getActivityAuthorizationConstraints()
											.remove(actAC);
								}
							}
							updateTable(activity);

						}
					}
				}, editingDomain, "Removing NtK Permission");
			}
		}
	}

	/**
	 * Creates the table entries for a given activity.
	 * 
	 * @param activity
	 *            The activity for which the entries should be generated.
	 */
	private void updateTable(Activity activity) {
		table.removeAll();

		for (ActivityAuthorizationConstraint aac : activity
				.getActivityAuthorizationConstraints()) {

			if (aac.getPermissions().size() > 0
					&& aac.getPermissions().get(0) instanceof NeedToKnow) {

				Permission p = aac.getPermissions().get(0);
				String[] nameParts = p.getPName().split("-");

				TableItem ti = new TableItem(table, SWT.NONE);
				String[] itemText = { "", p.getPName(), nameParts[2],
						nameParts[3], "" };
				for (Role r : p.getRoles()) {
					if (itemText[4].length() == 0) {
						itemText[4] += r.getName();
					} else {
						itemText[4] += ", " + r.getName();
					}
				}
				ti.setText(itemText);

			}

		}
		selectionChanged();
	}
}
