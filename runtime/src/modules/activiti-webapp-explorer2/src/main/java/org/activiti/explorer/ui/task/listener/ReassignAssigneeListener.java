/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.explorer.ui.task.listener;

import java.util.Arrays;
import java.util.List;
import java.util.ServiceLoader;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.Event;
import org.activiti.engine.task.Task;
import org.activiti.explorer.ExplorerApp;
import org.activiti.explorer.I18nManager;
import org.activiti.explorer.Messages;
import org.activiti.explorer.NotificationManager;
import org.activiti.explorer.ui.custom.SelectUsersPopupWindow;
import org.activiti.explorer.ui.event.SubmitEvent;
import org.activiti.explorer.ui.event.SubmitEventListener;
import org.activiti.explorer.ui.task.TaskDetailPanel;
import org.activiti.explorer.ui.task.data.QueuedListQuery.SecurityCallback;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;


/**
 * @author Joram Barrez
 */
public class ReassignAssigneeListener implements ClickListener {

  private static final long serialVersionUID = 1L;
  
  protected Task task;
  protected TaskDetailPanel taskDetailPanel;
  protected I18nManager i18nManager;
//<SecureBPMN>
  protected NotificationManager notificationManager;
//</SecureBPMN>
 
  public ReassignAssigneeListener(Task task, TaskDetailPanel taskDetailPanel) {
    this.task = task;
    this.taskDetailPanel = taskDetailPanel;
    this.i18nManager = ExplorerApp.get().getI18nManager();
  //<SecureBPMN>
    this.notificationManager = ExplorerApp.get().getNotificationManager();
  //</SecureBPMN>
  }
  
  public void buttonClick(ClickEvent event) {
    List<String> ignoredIds = null;
    if (task.getAssignee() != null) {
      ignoredIds = Arrays.asList(task.getAssignee());
    }
    //<SecureBPMN> 
    //no reassigning selection pop-up without assignee
    if (event.getButton().getCaption().equals("No Delegation") || event.getButton().getCaption().equals("No Assignee")) {
    	notificationManager.showCustomNotification("Error!", "You are not the current Assignee for this Task!");
    	return;
    }
    // no actions without being the current assignee
    if (!task.getAssignee().equals(ExplorerApp.get().getLoggedInUser().getId())) {
    	notificationManager.showCustomNotification("Error!", "You are not the current Assignee for this Task!");
    	return;
    } else {
    	// return a negotiable delegation without pop-up
    	if (event.getButton().getCaption().equals("Return Delegation?")) {
    		final ServiceLoader<SecurityCallback> serviceLoader = ServiceLoader.load(SecurityCallback.class);
    		for (SecurityCallback callback : serviceLoader) {
    			try {
    				if(callback.negotiableCheck(task.getId(), ExplorerApp.get().getLoggedInUser().getId())) {
    					String newAssignee = ProcessEngines.getDefaultProcessEngine().getTaskService().getTaskEvents(task.getId()).get(0).getUserId();
    					List<Event> taskEvents = ProcessEngines.getDefaultProcessEngine().getTaskService().getTaskEvents(task.getId());
    					System.err.println("number of events on this task: " + taskEvents.size());
    					// first return without depth check
    					if (taskEvents.size() <= 1) {
    						task.setAssignee(newAssignee);
    						ProcessEngines.getDefaultProcessEngine().getTaskService().setAssignee(task.getId(), newAssignee);
    						taskDetailPanel.notifyAssigneeChanged();
    						return;
    					} else {
    						if (callback.delegationCheck(task.getId(), newAssignee)) {
    							task.setAssignee(newAssignee);
	    						ProcessEngines.getDefaultProcessEngine().getTaskService().setAssignee(task.getId(), newAssignee);
	    						taskDetailPanel.notifyAssigneeChanged();
	    						return;
    						} else {
    						notificationManager.showCustomNotification("Delegation failed!", "Delegation depth exceeded or violation of Seperation of Duty!");
    						return;
    						}
    					}
    				} else {
    					notificationManager.showCustomNotification("Delegation Failed!", "This task is not negotiable!");
    					return;
    				}
    			} catch (final RuntimeException e) {
            		e.printStackTrace();
            	} catch (final Throwable t) {
            		t.printStackTrace();
            		throw new RuntimeException(t);
            	}
    		}
    	}
    }
    //</SecureBPMN>
    
    final SelectUsersPopupWindow involvePeoplePopupWindow = 
        new SelectUsersPopupWindow(i18nManager.getMessage(Messages.TASK_ASSIGNEE_REASSIGN), false, ignoredIds);
    
    involvePeoplePopupWindow.addListener(new SubmitEventListener() {
      protected void submitted(SubmitEvent event) {
        // Update assignee
        String selectedUser = involvePeoplePopupWindow.getSelectedUserId();
        
        //<SecureBPMN>
        final ServiceLoader<SecurityCallback> serviceLoader = ServiceLoader.load(SecurityCallback.class);
        System.out.println("Trying to load delegation");
        for (SecurityCallback callback : serviceLoader) {
        	try {
        		//Delegation Check
        		if(task.getAssignee() != null && selectedUser != null) {       			
	        		if (callback.delegationCheck(task.getId(), selectedUser)) {
	        			task.setAssignee(selectedUser);
	                	ProcessEngines.getDefaultProcessEngine().getTaskService().setAssignee(task.getId(), selectedUser);
	                	taskDetailPanel.notifyAssigneeChanged();
	        		} else {
	        			notificationManager.showCustomNotification("Delegation failed!", "Delegation depth exceeded or violation of Seperation of Duty!");
	        		}
        		} 
        	} catch (final RuntimeException e) {
        		e.printStackTrace();
        	} catch (final Throwable t) {
        		t.printStackTrace();
        		throw new RuntimeException(t);
        		}
        	}
        //<SecureBPMN>
        /*
        	task.setAssignee(selectedUser);
        	ProcessEngines.getDefaultProcessEngine().getTaskService().setAssignee(task.getId(), selectedUser);
        
        // Update UI
        taskDetailPanel.notifyAssigneeChanged();
        */
        //</SecureBPMN>
      }
      protected void cancelled(SubmitEvent event) {
      }
    });
    
    ExplorerApp.get().getViewManager().showPopupWindow(involvePeoplePopupWindow);
  }
  
}
