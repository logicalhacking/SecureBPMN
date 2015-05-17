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

//1. Populate the permissions of the associated task
//2. Checked ones are 
package org.activiti.designer.security.property;


import java.util.ArrayList;
import java.util.List;
import org.activiti.designer.util.eclipse.ActivitiUiUtil;
import org.activiti.designer.util.property.ActivitiPropertySection;
import org.eclipse.bpmn2.Activity;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.validation.internal.modeled.model.validation.Constraint;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.securebpmn2.ActivityAction;
import org.eclipse.securebpmn2.AuthorizationConstraint;
import org.eclipse.securebpmn2.BindingOfDuty;
import org.eclipse.securebpmn2.Permission;
import org.eclipse.securebpmn2.Role;
import org.eclipse.securebpmn2.SecurityFlow;
import org.eclipse.securebpmn2.SeparationOfDuty;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 *
 */
public class PropertySodBodSection extends ActivitiPropertySection implements ITabbedPropertyConstants {

	private Composite composite;
	private String[] titles = {"  ","Activity","PermissionId","Permission Name","Action", "Roles"};
	private SelectionListener selectionListener;
	private Button saveButton;
	private Button selectAllButton;
	private Button deSelectAllButton;
	private Table table;
	private Composite buttonBox;
	private Text minUsersText;
	private Text maxActionText;
	private CLabel minUsersLabel;
	private CLabel maxActionLabel;
	private Text bodMaxUsersText;
	private Text bodActionText;
	private CLabel bodMaxUsersLabel;
	private CLabel bodActionLabel;
	private Composite checkButtonBox;
	private Button enforcementTypeButton; 
	


	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
		composite = factory.createFlatFormComposite(parent);
		FormData data;
		

		CLabel tableLabel = factory.createCLabel(composite, "Select Permissions :"); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(70, 0);
		data.top = new FormAttachment(0, 0);
		tableLabel.setLayoutData(data);

		data = new FormData(180,170);
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(70, 0);
		data.top = new FormAttachment(tableLabel, -VSPACE);
		table = new Table (composite, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setLayoutData(data);	
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		buttonBox = getButtonBoxControl(composite);
		data = new FormData();
		data.left = new FormAttachment(table, -HSPACE);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(tableLabel, -VSPACE);
		buttonBox.setLayoutData(data);
		buttonBox.setVisible(true);
		buttonBox.addFocusListener(listener);			
		
		for (int i=0; i<titles.length; i++) {
			TableColumn column = new TableColumn (table, SWT.NONE);			
			column.setText (titles [i]);
			column.setWidth(170);
		}
		table.getColumn(0).setWidth(30);
		table.getColumn(2).setWidth(0);
		
		/*for (int i=0; i<titles.length; i++) {
			table.getColumn (i).pack ();
		}	*/
		table.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event event) {
				String string = event.detail == SWT.CHECK ? "Checked" : "Selected";
//				System.out.println (event.item + " " + string);
				selectionChanged();
			}
		});
		
		// static/dynamic enforcement control
		checkButtonBox = new Composite(composite, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		checkButtonBox.setLayout(layout);
		checkButtonBox.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		
		enforcementTypeButton = factory.createButton(checkButtonBox, "Dynamic enforcement?", SWT.CHECK);
		enforcementTypeButton.setToolTipText("Enable this checkbox to enforce the constraint dynamically.");
		enforcementTypeButton.setVisible(true);
		GridData gdata = new GridData(GridData.FILL_HORIZONTAL);
		gdata.widthHint=40;
		enforcementTypeButton.setLayoutData(gdata);
		enforcementTypeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Widget widget = event.widget;
				if (widget == enforcementTypeButton) {
					enforcementTypeChanged();
				}
			}
		});
		
		checkButtonBox.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent event) {
				enforcementTypeButton = null;
				checkButtonBox = null;
			}
		});
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(80, 0);
		data.top = new FormAttachment(table, VSPACE);
		checkButtonBox.setLayoutData(data);
		checkButtonBox.setVisible(true);

		composite.pack ();
	}
	
	private void enforcementTypeChanged() {
		if (enforcementTypeButton.getSelection()) {
			// Dynamic enforcement model change
			PictogramElement pe = getSelectedPictogramElement();
			if (pe != null) {
				Object bo = Graphiti.getLinkService()
						.getBusinessObjectForLinkedPictogramElement(pe);
				if (bo instanceof AuthorizationConstraint) {
					DiagramEditor diagramEditor = (DiagramEditor) getDiagramEditor();
					TransactionalEditingDomain editingDomain = diagramEditor
							.getEditingDomain();
					ActivitiUiUtil.runModelChange(new Runnable() {
						public void run() {

							Object bo = Graphiti
									.getLinkService()
									.getBusinessObjectForLinkedPictogramElement(
											getSelectedPictogramElement());
							if (bo == null) {
								return;
							} else {
								AuthorizationConstraint ac = (AuthorizationConstraint) bo;
								ac.setDynamicEnforcement(true);
							}
						}
					}, editingDomain, "Constraint enforcement type update");
				}
			}
		} else {
			// Static enforcement model change
			PictogramElement pe = getSelectedPictogramElement();
			if (pe != null) {
				Object bo = Graphiti.getLinkService()
						.getBusinessObjectForLinkedPictogramElement(pe);
				if (bo instanceof AuthorizationConstraint) {
					DiagramEditor diagramEditor = (DiagramEditor) getDiagramEditor();
					TransactionalEditingDomain editingDomain = diagramEditor
							.getEditingDomain();
					ActivitiUiUtil.runModelChange(new Runnable() {
						public void run() {

							Object bo = Graphiti
									.getLinkService()
									.getBusinessObjectForLinkedPictogramElement(
											getSelectedPictogramElement());
							if (bo == null) {
								return;
							} else {
								AuthorizationConstraint ac = (AuthorizationConstraint) bo;
								ac.setDynamicEnforcement(false);
							}
						}
					}, editingDomain, "Constraint enforcement type update");
				}
			}
		}
			
	}

	@Override
	public void refresh() {	
		buttonBox.removeFocusListener(listener);
		
		if(minUsersText != null) {
			minUsersText.removeFocusListener(listener);
			minUsersText.setVisible(false);
  		    minUsersLabel.setVisible(false);			
	    }
		
		if(maxActionText != null) {
			maxActionText.removeFocusListener(listener);
			maxActionText.setVisible(false);
   		    maxActionLabel.setVisible(false);
	    }
		if(bodMaxUsersText != null) {
			bodMaxUsersText.removeFocusListener(listener);
			bodMaxUsersText.setVisible(false);
   		    bodMaxUsersLabel.setVisible(false);
	    }
		
		if(bodActionText != null) {
			bodActionText.removeFocusListener(listener);
			bodActionText.setVisible(false);
   		    bodActionLabel.setVisible(false);
	    }
		
		 PictogramElement pe = getSelectedPictogramElement();
		    if (pe != null) {
		      Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
		      if (bo == null)
		        return;
		      
		      AuthorizationConstraint constraint = (AuthorizationConstraint) bo;
		      displayPermissions(constraint); 
		     
		    	  if(minUsersText ==null){
		    		  minUsersText = createText(composite, getWidgetFactory(), checkButtonBox);
		    		  minUsersLabel=createLabel("Minimum Users Required :", composite, getWidgetFactory(), minUsersText);
		    		  minUsersText.setVisible(false);
		    		  minUsersLabel.setVisible(false);
		    	  }
		    	  if(maxActionText==null){
			    	  maxActionText = createText(composite, getWidgetFactory(), minUsersText);
		    		  maxActionLabel=createLabel("Maximum Actions Allowed per User:", composite, getWidgetFactory(), maxActionText);
		    		  maxActionText.setVisible(false);
		    		  maxActionLabel.setVisible(false);
		    	  }	
		    	  if(bodMaxUsersText ==null){
		    		  bodMaxUsersText = createText(composite, getWidgetFactory(), checkButtonBox);
		    		  bodMaxUsersLabel=createLabel("Mamimum Users Allowed :", composite, getWidgetFactory(), bodMaxUsersText);
		    		  bodMaxUsersText.setVisible(false);
		    		  bodMaxUsersLabel.setVisible(false);
		    	  }
		    	  if(bodActionText==null){
		    		  bodActionText = createText(composite, getWidgetFactory(), bodMaxUsersText);
		    		  bodActionLabel=createLabel("Actions performed by same User:", composite, getWidgetFactory(), bodActionText);
		    		  bodActionText.setVisible(false);
		    		  bodActionLabel.setVisible(false);
		    	  }	
		    	  if(bo instanceof SeparationOfDuty){
		    	  SeparationOfDuty sod=(SeparationOfDuty) bo;
		    	  minUsersText.setText("");
		    	  if(sod.getMinimumUsers()!=null)
		    	  minUsersText.setText(Integer.toString(sod.getMinimumUsers()));
		    	  
		    	  maxActionText.setText("");
		    	  if(sod.getMaxUserActionsPermitted()!=null)
		    	  maxActionText.setText(Integer.toString(sod.getMaxUserActionsPermitted()));
		    	  
		    	  minUsersText.setVisible(true);
	    		  minUsersLabel.setVisible(true);
	    		  maxActionText.setVisible(true);
	    		  maxActionLabel.setVisible(true);
		    	  
		      }
		    	  else if(bo instanceof BindingOfDuty){
		    		  BindingOfDuty bod=(BindingOfDuty) bo;
		    		  bodMaxUsersText.setText("");
			    	  if(bod.getMaxUsers()!=null)
			    		  bodMaxUsersText.setText(Integer.toString(bod.getMaxUsers()));
			    	  
			    	  bodActionText.setText("");
			    	  if(bod.getSameUserActionCount()!=null)
			    		  bodActionText.setText(Integer.toString(bod.getSameUserActionCount()));
			    	  
			    	  bodMaxUsersText.setVisible(true);
		    		  bodMaxUsersLabel.setVisible(true);
		    		  bodActionText.setVisible(true);
		    		  bodActionLabel.setVisible(true);
		    	  }
		      else{
		    	  if(minUsersText!=null){
		    		  minUsersText.setVisible(false);
		    		  minUsersLabel.setVisible(false);
		    	  }
		    	  if(maxActionText!=null){			    	 
		    		  maxActionText.setVisible(false);
		    		  maxActionLabel.setVisible(false);
		    	  }	
		    	  if(bodMaxUsersText!=null){
		    		  bodMaxUsersText.setVisible(false);
		    		  bodMaxUsersLabel.setVisible(false);
		    	  }
		    	  if(bodActionText!=null){			    	 
		    		  bodActionText.setVisible(false);
		    		  bodActionLabel.setVisible(false);
		    	  }	
		      }
		      if(minUsersText != null) {
		    	  minUsersText.addFocusListener(listener);
		        }
		      if(maxActionText != null) {
		    	  maxActionText.addFocusListener(listener);
		        }
		      if(bodMaxUsersText != null) {
		    	  bodMaxUsersText.addFocusListener(listener);
		        }
		      if(bodActionText != null) {
		    	  bodActionText.addFocusListener(listener);
		        }
		      buttonBox.addFocusListener(listener);
		    
		      // TODO update selection of enforcementTypeButton
		      enforcementTypeButton.setSelection(constraint.isDynamicEnforcement());
				
		      
		    }
	}
		     
			

	private FocusListener listener = new FocusListener() {

		public void focusGained(final FocusEvent e) {
		}

		public void focusLost(final FocusEvent e) {
			PictogramElement pe = getSelectedPictogramElement();
			if (pe != null) {
				Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
				if (bo instanceof AuthorizationConstraint) {
					DiagramEditor diagramEditor = (DiagramEditor) getDiagramEditor();
					TransactionalEditingDomain editingDomain = diagramEditor.getEditingDomain();
					ActivitiUiUtil.runModelChange(new Runnable() {
						public void run() {
							Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(getSelectedPictogramElement());
							if (bo == null) {
								return;
							}
							AuthorizationConstraint constraint = (AuthorizationConstraint) bo;
						    displayPermissions(constraint);
						    if(bo instanceof SeparationOfDuty){
						    	SeparationOfDuty sod=(SeparationOfDuty) bo;
						    	if(minUsersText!=null){
							    	if(minUsersText.getText() != null && minUsersText.getText().length() > 0) {
						                Integer minUsersValue = null;
						                try {
						                	minUsersValue = Integer.valueOf(minUsersText.getText());
						                } catch(Exception e) {}
						                sod.setMinimumUsers(minUsersValue);
						              }
						    	}
						    	if(maxActionText!=null){
							    	if(maxActionText.getText() != null && maxActionText.getText().length() > 0) {
						                Integer maxActionValue = null;
						                try {
						                	maxActionValue = Integer.valueOf(maxActionText.getText());
						                } catch(Exception e) {}
						                sod.setMaxUserActionsPermitted(maxActionValue);
						              }
						    	}
						    	
						    }
						    else if(bo instanceof BindingOfDuty){
						    	BindingOfDuty bod=(BindingOfDuty) bo;
						    	if(bodMaxUsersText!=null){
							    	if(bodMaxUsersText.getText() != null && bodMaxUsersText.getText().length() > 0) {
						                Integer bodMaxUsersValue = null;
						                try {
						                	bodMaxUsersValue = Integer.valueOf(bodMaxUsersText.getText());
						                } catch(Exception e) {}
						                bod.setMaxUsers(bodMaxUsersValue);
						              }
						    	}
						    	if(bodActionText!=null){
							    	if(bodActionText.getText() != null && bodActionText.getText().length() > 0) {
						                Integer bodActionValue = null;
						                try {
						                	bodActionValue = Integer.valueOf(bodActionText.getText());
						                } catch(Exception e) {}
						                bod.setSameUserActionCount(bodActionValue);
						              }
						    	}					    	
						    }
						}
					}, editingDomain, "Model Update");
				}

			}
		}
	};
	
	/**
	 * Helper method to create a push button.
	 * 
	 * @param parent
	 *            the parent control
	 * @param key
	 *            the resource name used to supply the button's label text
	 * @return Button
	 */
	private Button createPushButton(Composite parent, String key) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText(key);
		button.setFont(parent.getFont());
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint=40;
		button.setLayoutData(data);
		button.addSelectionListener(getSelectionListener());
		return button;
	}
	
	/**
	 * Returns this field editor's selection listener. The listener is created
	 * if necessary.
	 * 
	 * @return the selection listener
	 */
	private SelectionListener getSelectionListener() {
		if (selectionListener == null) {
			createSelectionListener();
		}
		return selectionListener;
	}
	
	/**
	 * Creates a selection listener.
	 */
	public void createSelectionListener() {
		selectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Widget widget = event.widget;
				if (widget == saveButton) {
					savePressed();
				} else if (widget == deSelectAllButton) {
					deSelectAllPressed();
				} 
				 else if (widget == selectAllButton) {
						selectAllPressed();
					} 
				else if (widget == table) {
					selectionChanged();
				}
			}
		};
	}
	
	/**
	 * Creates the Add, Remove, Up, and Down button in the given button box.
	 * 
	 * @param box
	 *            the box for the buttons
	 */
	private void createButtons(Composite box) {
		box.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		saveButton = createPushButton(box, "Save");	
		selectAllButton = createPushButton(box, "Select All");
		deSelectAllButton = createPushButton(box, "Deselect All");
	}
	
	/**
	 * Notifies that the Add button has been pressed.
	 */
	private void savePressed() {
		  PictogramElement pe = getSelectedPictogramElement();
	      if (pe != null) {
	        Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
	        if (bo instanceof AuthorizationConstraint) {
	          DiagramEditor diagramEditor = (DiagramEditor) getDiagramEditor();
	          TransactionalEditingDomain editingDomain = diagramEditor.getEditingDomain();
	          ActivitiUiUtil.runModelChange(new Runnable() {

	            public void run() {
		Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(getSelectedPictogramElement());
		if (bo == null) {
            return;
          }
		else{
			
			AuthorizationConstraint authorizationConstraint= (AuthorizationConstraint)bo;
			//get all the permissions of the associated tasks
			List<Permission> allPermissions=new ArrayList<Permission>();
			List<Permission> toAddList=new ArrayList<Permission>();
			List<Permission> toRemoveList=new ArrayList<Permission>();
			List<Permission> currentPermissions=null;
			Permission permission=null;	
			Activity activity=null;
			List<SecurityFlow> flows=authorizationConstraint.getOutgoingSecurityFlow();
			currentPermissions=authorizationConstraint.getPermissions();
		     for(SecurityFlow sf: flows){
		    	activity=(Activity)sf.getTargetRefNode(); 	
		 		List<ActivityAction> availableActions =activity.getActivityActions();
		 		for (ActivityAction temp : availableActions) {
		 			if(temp!=null){
		 			allPermissions.add(temp.getPermissions().get(0));		 			
		 			}				 			
		 		}
		 		activity=null;
		     }
			//iterate through the table and prepare two list toaddList toremoveList of permissions
		     TableItem[] items=table.getItems();
		     TableItem tempItem=null;
		     for(int i=0;i<items.length;i++){
		    	 tempItem=items[i];
		    	 permission=getPermission(tempItem.getText(2),allPermissions);
		    	 if(tempItem.getChecked()){
		    		 toAddList.add(permission);
		    	 }
		    	 else{
		    		 toRemoveList.add(permission);
		    	 }
		    	 
		     }
		     //add the new permissions to the constraint
		     for(Permission toAdd: toAddList){
		    	 if(!currentPermissions.contains(toAdd)){
		    		 authorizationConstraint.getPermissions().add(toAdd);
		    	 }
		     }
		     // remove the permissions 
		     for(Permission toRemove: toRemoveList){
		    	 if(currentPermissions.contains(toRemove)){
		    		 authorizationConstraint.getPermissions().remove(toRemove);
		    	 }
		     }					
			displayPermissions(authorizationConstraint);			
		}	      
	            }
       }, editingDomain, "Model Update");
     }

   }
		
	}
	


	
	private void deSelectAllPressed() {
		for(TableItem ti:table.getItems()){
			ti.setChecked(false);
			}
		
	}
	
	private void selectAllPressed() {
		for(TableItem ti:table.getItems()){
			ti.setChecked(true);
		}
		
	}
	
	protected void selectionChanged() {
		//TableItem[] items = table.getSelection();
		//removeButton.setEnabled(items.length > 0);
	}
	
	
	
	public Composite getButtonBoxControl(Composite parent) {
		if (buttonBox == null) {
			buttonBox = new Composite(parent, SWT.NULL);
			GridLayout layout = new GridLayout();
			layout.marginWidth = 0;
			buttonBox.setLayout(layout);
			createButtons(buttonBox);
			buttonBox.addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent event) {
					saveButton = null;
					deSelectAllButton = null;
					selectAllButton=null;
					buttonBox = null;
				}
			});

		} else {
			//checkParent(buttonBox, parent);
		}
		selectionChanged();
		return buttonBox;
		
	}
	
	
	
	private void displayPermissions(AuthorizationConstraint ac){
		table.removeAll();	
		List<Permission> currentPermissions=null;
		Permission permission=null;	
		Activity activity=null;
		List<Role> roles=null;
		currentPermissions=ac.getPermissions();
		List<SecurityFlow> flows=ac.getOutgoingSecurityFlow();
	     for(SecurityFlow sf: flows){
	    	activity=(Activity)sf.getTargetRefNode(); 	    	
	 		//List<Role> roles=null;
	 		List<ActivityAction> availableActions =activity.getActivityActions();	
	 		StringBuilder st=new StringBuilder();
	 		for (ActivityAction temp : availableActions) {
	 			if(temp!=null){
	 			permission=temp.getPermissions().get(0);
	 			roles=permission.getRoles();
	 			for (int i = 0; i < roles.size(); i++) {
	 				st.append(roles.get(i).getName());
	 				if(i!=roles.size()-1)
	 				{
	 					st.append(",");
	 				}
	 			}
	 			}
	 			TableItem tableItem = new TableItem(table, SWT.NONE);
	 			if(currentPermissions.contains(permission))
	 			tableItem.setChecked(true);	 			
	 			tableItem.setText(new String[] {"",activity.getName(),permission.getId(),permission.getPName(),temp.getActionName(),st.toString()});
	 			selectionChanged();	
	 			st.delete(0, st.length());
	 			roles=null;
	 			permission=null;
	 			
	 		}
	 		activity=null;
	     }		
		
	}	
	
	private Permission getPermission(String id,List<Permission> permissions){
		Permission perm=null;
		for(Permission temp: permissions){
			if(id.equals(temp.getId())){
				perm=temp;
				break;
			}
		}
		return perm;		
	}
	
	 private Text createText(Composite parent, TabbedPropertySheetWidgetFactory factory, Control top) {
		    Text text = factory.createText(parent, ""); //$NON-NLS-1$
		    FormData data = new FormData();
		    data.left = new FormAttachment(0, 220);
		    data.right = new FormAttachment(70, 0);
		    if(top == null) {
		      data.top = new FormAttachment(0, VSPACE);
		    } else {
		      data.top = new FormAttachment(top, VSPACE);
		    }
		    text.setLayoutData(data);
		    text.addFocusListener(listener);
		    return text;
		  }
	 
	 private CLabel createLabel(String text, Composite parent, TabbedPropertySheetWidgetFactory factory, Control control) {
		    CLabel label = factory.createCLabel(parent, text);
		    FormData data = new FormData();
		    data.left = new FormAttachment(0, 0);
		    data.right = new FormAttachment(control, -HSPACE);
		    data.top = new FormAttachment(control, 0, SWT.CENTER);
		    label.setLayoutData(data);
		    return label;
		  }
	

}
