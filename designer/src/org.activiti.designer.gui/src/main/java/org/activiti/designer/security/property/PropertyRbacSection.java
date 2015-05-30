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




import java.util.List;
import java.util.UUID;

import org.activiti.designer.util.eclipse.ActivitiUiUtil;
import org.activiti.designer.util.property.ActivitiPropertySection;
import org.eclipse.bpmn2.Activity;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.securebpmn2.Action;
import org.eclipse.securebpmn2.ActivityAction;
import org.eclipse.securebpmn2.Permission;
import org.eclipse.securebpmn2.Role;
import org.eclipse.securebpmn2.Securebpmn2Factory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
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

import eu.aniketos.securebpmn.util.SecurityUtil;


/**
 *
 */
public class PropertyRbacSection extends ActivitiPropertySection implements ITabbedPropertyConstants {

	private CCombo actionCombo;
	private CCombo roleCombo;
	private String[] titles = {"  ","PermissionId","Permission Name", "Action", "Roles"};
	private SelectionListener selectionListener;
	private Button addButton;
	private Button removeButton;
	private Table table;
	private Composite buttonBox;
	private int checkCount=0;
	
	


	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
		Composite composite = factory.createFlatFormComposite(parent);
		FormData data;
		actionCombo = factory.createCCombo(composite, SWT.NONE);		
		List<Action> actionList=SecurityUtil.getActivityActions();
		for (int i = 0; i < actionList.size(); i++) {
			actionCombo.add(actionList.get(i).getActionName());
		}
	 

		data = new FormData();
		data.left = new FormAttachment(0, 120);
		data.right = new FormAttachment(80, 0);
		data.top = new FormAttachment(0, VSPACE);
		actionCombo.setLayoutData(data);
		actionCombo.addFocusListener(listener);

		CLabel actionLabel = factory.createCLabel(composite, "Action :"); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(actionCombo, -HSPACE);
		data.top = new FormAttachment(actionCombo, 0, SWT.CENTER);
		actionLabel.setLayoutData(data);
		
		
		roleCombo = factory.createCCombo(composite, SWT.NONE);
		data = new FormData();
		data.left = new FormAttachment(0, 120);
		data.right = new FormAttachment(80, 0);
		data.top = new FormAttachment(actionCombo, VSPACE);
		roleCombo.setLayoutData(data);
		roleCombo.addFocusListener(listener);

		CLabel roleLabel = factory.createCLabel(composite, "Role :"); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(roleCombo, -HSPACE);
		data.top = new FormAttachment(roleCombo, 0, SWT.CENTER);
		roleLabel.setLayoutData(data);
		
		CLabel tableLabel = factory.createCLabel(composite, "Permissions :"); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(0, 100);
		data.top = new FormAttachment(roleCombo, VSPACE);
		tableLabel.setLayoutData(data);

		data = new FormData(200,170);
		data.left = new FormAttachment(tableLabel, 10);
		data.right = new FormAttachment(80, 0);
		data.top = new FormAttachment(roleCombo, VSPACE);
		table = new Table (composite, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setLayoutData(data);		
		//Layout layout=table.getLayout();
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		/*GridData griddata = new GridData(SWT.FILL, SWT.FILL, true, true);
		layout. = 100;
		table.setLayoutData(griddata);
		//table.setLayout(layout);
		 * 
*/		
		buttonBox = getButtonBoxControl(composite);
		data = new FormData();
		data.left = new FormAttachment(table, -HSPACE);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(roleLabel, 0);
		buttonBox.setLayoutData(data);
		buttonBox.setVisible(true);
		buttonBox.addFocusListener(listener);		
		
		
		
		for (int i=0; i<titles.length; i++) {
			TableColumn column = new TableColumn (table, SWT.NONE);			
			column.setText (titles [i]);
			column.setWidth(170);
		}
		table.getColumn(0).setWidth(30);
		table.getColumn(1).setWidth(0);
		/*int count = 128;
		for (int i=0; i<count; i++) {
			TableItem item = new TableItem (table, SWT.NONE);
			item.setText (0, "");
			item.setText (1, "this stuff behaves the way I expect");
			item.setText (2, "almost everywhere");
			item.setText (3, "some.folder");
		}*/
		/*for (int i=0; i<titles.length; i++) {
			table.getColumn (i).pack ();
		}	*/
		table.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event event) {
				String string = event.detail == SWT.CHECK ? "Checked" : "Selected";
//				System.out.println (event.item + " " + string);
				if(event.detail == SWT.CHECK){
					TableItem item=	(TableItem)event.item;
					if(item.getChecked()==true){
						checkCount++;						
					}
					else{
						checkCount--;
					}					
				}
				selectionChanged();
			}
		});

		composite.pack ();
		//composite.open ();
	}

	@Override
	public void refresh() {
		actionCombo.removeFocusListener(listener);
		roleCombo.removeFocusListener(listener);
		buttonBox.removeFocusListener(listener);
		checkCount=0;
		
		 PictogramElement pe = getSelectedPictogramElement();
		    if (pe != null) {
		      Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
		      if (bo == null)
		        return;
		      
		      Activity activity = (Activity) bo;
		      displayPermissions(activity);
		      actionCombo.addFocusListener(listener);
		      roleCombo.addFocusListener(listener);
		      buttonBox.addFocusListener(listener);
		      
		      roleCombo.removeAll();
		      List<Role> roleList=SecurityUtil.getRoles(getDiagram());
				for (int i = 0; i < roleList.size(); i++) {
					roleCombo.add(roleList.get(i).getName());
				}
		    }
	}
		     
			

	private FocusListener listener = new FocusListener() {

		public void focusGained(final FocusEvent e) {
		}

		public void focusLost(final FocusEvent e) {
			PictogramElement pe = getSelectedPictogramElement();
			if (pe != null) {
				Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
				if (bo instanceof Activity) {
					DiagramEditor diagramEditor = (DiagramEditor) getDiagramEditor();
					TransactionalEditingDomain editingDomain = diagramEditor.getEditingDomain();
					ActivitiUiUtil.runModelChange(new Runnable() {
						public void run() {
							Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(getSelectedPictogramElement());
							if (bo == null) {
								return;
							}
							Activity activity=(Activity) bo;	
							displayPermissions(activity);
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
	/*	int widthHint = convertHorizontalDLUsToPixels(button,
				IDialogConstants.BUTTON_WIDTH);
		data.widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT,
				SWT.DEFAULT, true).x);*/
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
				if (widget == addButton) {
					addPressed();
				} else if (widget == removeButton) {
					removePressed();
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
		addButton = createPushButton(box, "Add");		
		removeButton = createPushButton(box, "Remove");
	}
	
	/**
	 * Notifies that the Add button has been pressed.
	 */
	private void addPressed() {
		if(actionCombo.getText()=="" || roleCombo.getText()==""){
			return;
		}
		PictogramElement pe = getSelectedPictogramElement();
	      if (pe != null) {
	        Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
	        if (bo instanceof Activity) {
	          DiagramEditor diagramEditor = (DiagramEditor) getDiagramEditor();
	          TransactionalEditingDomain editingDomain = diagramEditor.getEditingDomain();
	          ActivitiUiUtil.runModelChange(new Runnable() {

	            public void run() {
		Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(getSelectedPictogramElement());
		if (bo == null) {
            return;
          }
		else{
			//get the selected action
			Activity activity= (Activity)bo;
			List<ActivityAction> availableActions =activity.getActivityActions();
			ActivityAction selectedAction=null;
			if(availableActions.size()!=0){
			for (ActivityAction temp : availableActions) {
				if(temp.getActionName()!=null){
				if(temp.getActionName().equals(actionCombo.getText())){
					selectedAction=temp;
					break;
				}
				}
			}
			}
			//selected action not available
			if(selectedAction==null){
				selectedAction = Securebpmn2Factory.eINSTANCE.createAtomicActivityAction();
				selectedAction.setId(UUID.randomUUID().toString());
				selectedAction.setActionName(actionCombo.getText());
				getDiagram().eResource().getContents().add(selectedAction);
				activity.getActivityActions().add(selectedAction);				
				Permission newPermission=Securebpmn2Factory.eINSTANCE.createPermission();
				newPermission.setId(UUID.randomUUID().toString());
				newPermission.setPName("Perm-"+activity.getId()+"-"+actionCombo.getText());
				getDiagram().eResource().getContents().add(newPermission);
				selectedAction.getPermissions().add(newPermission);
				Role selectedRole=getSelectedRole(roleCombo.getText());	
				if(getDiagram().eResource().getContents().contains(selectedRole)==false)
				getDiagram().eResource().getContents().add(selectedRole);
				newPermission.getRoles().add(selectedRole);
				
			}
			//selected action is available
			else{
				//get the associated permission
				Permission availablePermission = selectedAction.getPermissions().get(0);
				//get the roles to check if the role is already associated
				List<Role> availableRoles=availablePermission.getRoles();
				Role targetRole=null;
				for (Role temp : availableRoles) {
					if(temp.getName().equals(roleCombo.getText())){
						targetRole=temp;
						break;
					}
				}
				if(targetRole==null){
					// add role selected to the permission
					Role selectedRole=getSelectedRole(roleCombo.getText());
					if(getDiagram().eResource().getContents().contains(selectedRole)==false)
						getDiagram().eResource().getContents().add(selectedRole);
					availablePermission.getRoles().add(selectedRole);
				}
				
				
			}
			
			displayPermissions(activity);
			
		}		
		 
        
	            }
       }, editingDomain, "Model Update");
     }

   }
		
	}
	


	/**
	 * Notifies that the Remove button has been pressed.
	 */
	private void removePressed() {
		PictogramElement pe = getSelectedPictogramElement();
	      if (pe != null) {
	        Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
	        if (bo instanceof Activity) {
	          DiagramEditor diagramEditor = (DiagramEditor) getDiagramEditor();
	          TransactionalEditingDomain editingDomain = diagramEditor.getEditingDomain();
	          ActivitiUiUtil.runModelChange(new Runnable() {

	            public void run() {
		Object bobj = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(getSelectedPictogramElement());
		if (bobj == null) {
          return;
        }
		else{
			//get the selected action
			Activity activity= (Activity)bobj;
			List<ActivityAction> availableActions =activity.getActivityActions();
			for(TableItem temp:table.getItems()){
				if(temp.getChecked()){
					for(ActivityAction aa:availableActions){
						if(temp.getText(3).equals(aa.getActionName())){
							aa.getPermissions().remove(0);
							activity.getActivityActions().remove(aa);
							break;
						}
					}
				}
			}			
			displayPermissions(activity);			
				      
	           }
	            }
     }, editingDomain, "Model Update");
   }

 }
		
	}
	
	protected void selectionChanged() {
		TableItem[] items = table.getSelection();
		//int size = table.getItemCount();

		//editButton.setEnabled(index >= 0);
		removeButton.setEnabled(checkCount > 0);
	//	upButton.setEnabled(size > 1 && index > 0);
    //downButton.setEnabled(size > 1 && index >= 0 && index < size - 1);
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
					addButton = null;
					//editButton = null;
					removeButton = null;
				//	upButton = null;
          //downButton = null;
					buttonBox = null;
				}
			});

		} else {
			//checkParent(buttonBox, parent);
		}
		selectionChanged();
		return buttonBox;
		
	}
	
	private Role getSelectedRole(String roleName){
		List<Role> roleList=SecurityUtil.getRoles(getDiagram());
		Role role=null;
		for (int i = 0; i < roleList.size(); i++) {
			role=roleList.get(i);
			if(role.getName().equals(roleName))
			{
				break;
			}
		}
		return role;
		
	}
	
	private void displayPermissions(Activity activity){
		table.removeAll();
		Permission permission=null;
		List<Role> roles=null;
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
			tableItem.setText(new String[] {"",permission.getId(),permission.getPName(),temp.getActionName(),st.toString()});
			selectionChanged();	
			st.delete(0, st.length());
		}
		
	}	
	

}
