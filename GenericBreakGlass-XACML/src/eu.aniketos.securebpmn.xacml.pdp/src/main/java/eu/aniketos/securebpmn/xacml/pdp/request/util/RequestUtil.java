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

package eu.aniketos.securebpmn.xacml.pdp.request.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Event;
import org.activiti.engine.task.Task;
import org.activiti.explorer.ui.task.data.QueuedListQuery.SecurityCallback;

import eu.aniketos.securebpmn.xacml.api.SecurityError;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoAttribute;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoResult;
import eu.aniketos.securebpmn.xacml.api.idm.IdInfo;
import eu.aniketos.securebpmn.xacml.pdp.PDPServer;

import com.sun.xacml.Constants;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.attr.TypeIdentifierConstants;

/**
 * Util-class for evaluating XACML-requests with inline PEP.
 *
 */
public class RequestUtil implements SecurityCallback {

    protected PDPServer pdpServer;
    protected TaskService taskService;
    protected IdentityService identityService;
    protected HistoryService historyService;
    protected FormService formService;
    protected RepositoryService repositoryService;
    protected List<String> currentlyClaimedTasks;
    protected List<org.activiti.engine.task.Event> taskEvents;
    protected URL configURL;

    public RequestUtil() {
        // System.out.println("\nCalling RequestUtil\n");
        try {
            configURL = this.getClass().getClassLoader()
                        .getResource("policy-config.xml");
            pdpServer = new PDPServer(new File(configURL.getFile()));

            taskService = ProcessEngines.getDefaultProcessEngine()
                          .getTaskService();
            identityService = ProcessEngines.getDefaultProcessEngine()
                              .getIdentityService();
            historyService = ProcessEngines.getDefaultProcessEngine()
                             .getHistoryService();
            formService = ProcessEngines.getDefaultProcessEngine()
                          .getFormService();
            repositoryService = ProcessEngines.getDefaultProcessEngine()
                                .getRepositoryService();
            currentlyClaimedTasks = new ArrayList<String>();
            taskEvents = new ArrayList<org.activiti.engine.task.Event>();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (UnknownIdentifierException e) {
            e.printStackTrace();
        }

    }

    /**
     * Inline PEP. <br>
     * Handles the RBAC- and SoD-enforcement<br>
     * Creates a request with additional {@link AuthoAttribute}s which is then
     * evaluated by the {@link PDPServer}.
     *
     *
     * @param taskId
     *            the task to be claimed
     * @param userId
     *            the current user
     */
    public boolean securityCheck(String taskId, String userId) {
        updatePolicyConfig();
        // catch empty checks
        if (taskId == null || userId == null) {
            return false;
        }

        List<AuthoAttribute> attributes = new Vector<AuthoAttribute>();

        // System.out.println("UserID: " + userId);

        // get the resource
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String resource = task.getTaskDefinitionKey();

        // System.out.println("Resourcename: " + resource);

        // add the role-attributes
        List<Group> groups = identityService.createGroupQuery()
                             .groupMember(userId).list();

        for (Iterator<Group> iterator = groups.iterator(); iterator.hasNext();) {
            Group group = (Group) iterator.next();

            // System.out.println("Rolename: " + group.getName().toLowerCase());

            attributes.add(new AuthoAttribute(Constants.SUBJECT_CAT, URI
                                              .create("urn:custom:subject:role"),
                                              TypeIdentifierConstants.STRING_URI, group.getName()
                                              .toLowerCase()));
        }

        // List of all tasks currently assigned to the logged in user in the
        // current process instance and get their Ids for a match
        List<Task> t = taskService.createTaskQuery()
                       .processDefinitionId(task.getProcessDefinitionId())
                       .taskAssignee(userId).list();
        List<HistoricTaskInstance> hti = historyService
                                         .createHistoricTaskInstanceQuery()
                                         .processDefinitionId(task.getProcessDefinitionId())
                                         .taskAssignee(userId).list();
        for (Iterator<HistoricTaskInstance> iterator = hti.iterator(); iterator
                .hasNext();) {
            HistoricTaskInstance historicTaskInstance = (HistoricTaskInstance) iterator
                    .next();
            if (!currentlyClaimedTasks.contains(historicTaskInstance
                                                .getTaskDefinitionKey())) {
                currentlyClaimedTasks.add(historicTaskInstance
                                          .getTaskDefinitionKey());
            }
        }
        for (Iterator<Task> iterator = t.iterator(); iterator.hasNext();) {
            Task task2 = (Task) iterator.next();
            if (!currentlyClaimedTasks.contains(task2.getTaskDefinitionKey())) {
                currentlyClaimedTasks.add(task2.getTaskDefinitionKey());
            }
        }
        // add the tasks for the evaluation
        for (Iterator<String> iterator = currentlyClaimedTasks.iterator(); iterator
                .hasNext();) {
            String claimedTaskName = (String) iterator.next();

            // System.out.println("ClaimedTaskName: " + claimedTaskName);

            attributes.add(new AuthoAttribute(Constants.RESOURCE_CAT, URI
                                              .create("urn:custom:resource:cc-tasks"),
                                              TypeIdentifierConstants.STRING_URI, claimedTaskName));

        }

        // TODO hardcoded action!
        try {
            final String action = "Full Access";

            // the evaluation call
            AuthoResult result = pdpServer.evaluate(new IdInfo(userId),
                                                    resource, action, attributes);

            System.out.println("RESPONSE: " + result.toString());
            System.out.println(result.getDecision().getMessage());

            if (result.getDecision().getMessage().equals("Permit")) {
                /*
                 * for (Iterator<AuthoAttribute> iterator =
                 * attributes.iterator(); iterator .hasNext();) { AuthoAttribute
                 * authoAttribute = (AuthoAttribute) iterator .next();
                 *
                 * System.out.println("Request was: " + userId + " : " +
                 * resource + " : " + action + " : " +
                 * authoAttribute.getValue()); }
                 */
                return true;
            }
            if (result.getDecision().getMessage().equals("NotApplicable")) {
                /*
                 * for (Iterator<AuthoAttribute> iterator =
                 * attributes.iterator(); iterator .hasNext();) { AuthoAttribute
                 * authoAttribute = (AuthoAttribute) iterator .next();
                 *
                 * System.out.println("Request was: " + userId + " : " +
                 * resource + " : " + action + " : " +
                 * authoAttribute.getValue()); }
                 */
            }

        } catch (SecurityError e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Inline PEP. <br>
     * Handles the Delegation-enforcement<br>
     * Creates a request with additional {@link AuthoAttribute}s which is then
     * evaluated by the {@link PDPServer}.
     *
     * @param taskId
     *            the task to be delegated
     * @param userId
     *            the user to be checked
     */
    public boolean delegationCheck(String taskId, String userId) {
        // check the delegationdepth against maxdelegationdepth

        List<AuthoAttribute> attributes = new Vector<AuthoAttribute>();

        // System.out.println("UserID: " + userId);

        // get the resource
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String resource = task.getTaskDefinitionKey();

        // System.out.println("Resourcename: " + resource);

        // List of all tasks currently assigned to the logged in user in the
        // current process instance and get their Ids for a match
        List<Task> t = taskService.createTaskQuery()
                       .processDefinitionId(task.getProcessDefinitionId())
                       .taskAssignee(userId).list();
        List<HistoricTaskInstance> hti = historyService
                                         .createHistoricTaskInstanceQuery()
                                         .processDefinitionId(task.getProcessDefinitionId())
                                         .taskAssignee(userId).list();
        for (Iterator<HistoricTaskInstance> iterator = hti.iterator(); iterator
                .hasNext();) {
            HistoricTaskInstance historicTaskInstance = (HistoricTaskInstance) iterator
                    .next();
            if (!currentlyClaimedTasks.contains(historicTaskInstance
                                                .getTaskDefinitionKey())) {
                currentlyClaimedTasks.add(historicTaskInstance
                                          .getTaskDefinitionKey());
            }
        }
        for (Iterator<Task> iterator = t.iterator(); iterator.hasNext();) {
            Task task2 = (Task) iterator.next();
            if (!currentlyClaimedTasks.contains(task2.getTaskDefinitionKey())) {
                currentlyClaimedTasks.add(task2.getTaskDefinitionKey());
            }
        }
        // add the tasks for the evaluation
        for (Iterator<String> iterator = currentlyClaimedTasks.iterator(); iterator
                .hasNext();) {
            String claimedTaskName = (String) iterator.next();

            // System.out.println("ClaimedTaskName: " + claimedTaskName);

            attributes.add(new AuthoAttribute(Constants.RESOURCE_CAT, URI
                                              .create("urn:custom:resource:cc-tasks"),
                                              TypeIdentifierConstants.STRING_URI, claimedTaskName));
        }

        // add the role-attributes
        List<Group> groups = identityService.createGroupQuery()
                             .groupMember(userId).list();

        for (Iterator<Group> iterator = groups.iterator(); iterator.hasNext();) {
            Group group = (Group) iterator.next();

            // System.out.println("Rolename: " + group.getName().toLowerCase());

            attributes.add(new AuthoAttribute(Constants.SUBJECT_CAT, URI
                                              .create("urn:custom:subject:role"),
                                              TypeIdentifierConstants.STRING_URI, group.getName()
                                              .toLowerCase()));
        }
        // add the delegatee-role
        attributes.add(new AuthoAttribute(Constants.SUBJECT_CAT, URI
                                          .create("urn:custom:subject:role"),
                                          TypeIdentifierConstants.STRING_URI, "delegatee"));

        // add attribute for each delegation of task, representing the
        // delegation depth
        String delegatedTo = "";
        taskEvents = taskService.getTaskEvents(taskId);
        for (final org.activiti.engine.task.Event event : taskEvents) {
            if (!event.getUserId().equals(event.getMessageParts().get(0))
                    && event.getAction().equals(Event.ACTION_ADD_USER_LINK)) {
                delegatedTo = event.getMessageParts().get(0);
                if (delegatedTo != null && delegatedTo != "") {
                    System.err.println("delegatedTo: " + delegatedTo);
                    attributes
                    .add(new AuthoAttribute(
                             Constants.RESOURCE_CAT,
                             URI.create("urn:custom:resource:delegationCounter"),
                             TypeIdentifierConstants.STRING_URI,
                             delegatedTo));
                }
            }
        }

        // TODO hardcoded action !
        final String action = "Full Access";

        AuthoResult result;
        try {
            // the evaluation call
            result = pdpServer.evaluate(new IdInfo(userId), resource, action,
                                        attributes);

            System.out.println("RESPONSE: " + result.toString());
            System.out.println(result.getDecision().getMessage());

            if (result.getDecision().getMessage().equals("Permit")) {
                /*
                 * for (Iterator<AuthoAttribute> iterator =
                 * attributes.iterator(); iterator .hasNext();) { AuthoAttribute
                 * authoAttribute = (AuthoAttribute) iterator .next();
                 *
                 * System.out.println("Request was: " + userId + " : " +
                 * resource + " : " + action + " : " +
                 * authoAttribute.getValue()); }
                 */
                return true;
            }
            if (result.getDecision().getMessage().equals("Deny")) {
                /*
                 * for (Iterator<AuthoAttribute> iterator =
                 * attributes.iterator(); iterator .hasNext();) { AuthoAttribute
                 * authoAttribute = (AuthoAttribute) iterator .next();
                 *
                 * System.out.println("Request was: " + userId + " : " +
                 * resource + " : " + action + " : " +
                 * authoAttribute.getValue()); }
                 */
            }
        } catch (SecurityError e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Inline PEP. <br>
     * Handles the Delegation-Type-Checks.<br>
     * Creates a request with additional {@link AuthoAttribute}s which is then
     * evaluated by the {@link PDPServer}.
     *
     * @param taskId
     *            the task to be checked
     * @param userId
     *            the user to be checked
     * @return a String representing the Type of allowed <code>Delegation</code>
     */
    public String delegationTypeCheck(String taskId, String userId) {

        List<AuthoAttribute> attributesForTransferTypeCheck = new Vector<AuthoAttribute>();

        // get the resource
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String resource = task.getTaskDefinitionKey();

        // add the delegatee-role
        attributesForTransferTypeCheck.add(new AuthoAttribute(
                                               Constants.SUBJECT_CAT, URI.create("urn:custom:subject:role"),
                                               TypeIdentifierConstants.STRING_URI, "delegatee"));

        // add attribute for each delegation of task to represent the delegation
        // depth
        String delegatedTo = "";
        taskEvents = taskService.getTaskEvents(taskId);
        for (final org.activiti.engine.task.Event event : taskEvents) {
            if (!event.getUserId().equals(event.getMessageParts().get(0))
                    && event.getAction().equals(Event.ACTION_ADD_USER_LINK)) {
                delegatedTo = event.getMessageParts().get(0);
                if (delegatedTo != null && delegatedTo != "") {
                    System.err.println("delegatedTo: " + delegatedTo);
                    attributesForTransferTypeCheck
                    .add(new AuthoAttribute(
                             Constants.RESOURCE_CAT,
                             URI.create("urn:custom:resource:delegationCounter"),
                             TypeIdentifierConstants.STRING_URI,
                             delegatedTo));
                }
            }
        }

        // TODO hardcoded action !
        final String action = "Full Access";

        AuthoResult resultTransferCheck;
        AuthoResult resultSimpleCheck;
        try {
            // check for transfer-type
            resultTransferCheck = pdpServer.evaluate(new IdInfo(userId),
                                  resource, action, attributesForTransferTypeCheck);

            System.out.println("RESPONSE: " + resultTransferCheck.toString());
            System.out.println(resultTransferCheck.getDecision().getMessage());

            if (resultTransferCheck.getDecision().getMessage().equals("Permit")) {
                /*
                 * for (Iterator<AuthoAttribute> iterator =
                 * attributesForTransferTypeCheck.iterator(); iterator
                 * .hasNext();) { AuthoAttribute authoAttribute =
                 * (AuthoAttribute) iterator .next();
                 *
                 * System.out.println("Request was: " + userId + " : " +
                 * resource + " : " + action + " : " +
                 * authoAttribute.getValue()); }
                 */
                return "Transfer Delegation";
            }
            // check for simple-type
            final String checkAction = "isSimpleDelegatable";
            resultSimpleCheck = pdpServer.evaluate(new IdInfo(userId),
                                                   resource, checkAction, null);

            if (resultSimpleCheck.getDecision().getMessage().equals("Permit")) {
                return "Simple Delegation";
            }
            // check for negotiability
            if (negotiableCheck(taskId, userId)) {
                return "Return Delegation?";
            }

        } catch (SecurityError e) {
            e.printStackTrace();
        }
        // if all checks fail
        return "No Delegation";
    }

    /**
     * Inline PEP. <br>
     * Handles the Negotiable-Checks.<br>
     * Creates a request which is then evaluated by the {@link PDPServer}.
     *
     * @param taskId
     *            the task to be checked
     * @param userId
     *            the user to be checked
     */
    public boolean negotiableCheck(String taskId, String userId) {

        // on a returned delegation user is null but needs to be evaluated
        if (userId == null) {
            userId = "";
        }

        // get the resource
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String resource = task.getTaskDefinitionKey();

        final String action = "isNegotiable";
        AuthoResult resultNegotiableCheck;
        try {
            // the evaluation call
            resultNegotiableCheck = pdpServer.evaluate(new IdInfo(userId),
                                    resource, action, null);
            if (resultNegotiableCheck.getDecision().getMessage()
                    .equals("Permit")) {
                return true;
            }
            if (resultNegotiableCheck.getDecision().getMessage().equals("Deny")) {
                return false;
            }

        } catch (SecurityError e) {
            e.printStackTrace();
        }

        return false;
    }

    public void updatePolicyConfig() {

        ArrayList<Deployment> deployments = new ArrayList<Deployment>();
        ArrayList<String> deployedProcessNames = new ArrayList<String>();
        ArrayList<String> stringsToWrite = new ArrayList<String>();
        ArrayList<String> listToWrite = new ArrayList<String>();

        deployments.addAll(repositoryService.createDeploymentQuery()
                           .orderByDeploymenTime().desc().list());

        if (!deployments.isEmpty()) {
            for (Iterator<Deployment> iterator = deployments.iterator(); iterator
                    .hasNext();) {

                Deployment deployment = (Deployment) iterator.next();
                String temp = deployment.getName();
                String[] split = temp.split("\\.");
                String policyName = "<string>file:" + split[0]
                                    + ".xacml</string>";

                if (!deployedProcessNames.contains(policyName)) {
                    deployedProcessNames.add(policyName);
                }
            }
            BufferedReader reader = null;
            BufferedWriter writer = null;

            try {
                reader = new BufferedReader(new FileReader(configURL.getFile()));
                String tmp;

                while ((tmp = reader.readLine()) != null) {
                    listToWrite.add(tmp);
                }
                for (Iterator<String> iterator = deployedProcessNames
                                                 .iterator(); iterator.hasNext();) {
                    String string = (String) iterator.next();
                    if (!listToWrite.contains(string)) {
                        stringsToWrite.add(string);
                    }
                }
                reader.close();

                reader = new BufferedReader(new FileReader(configURL.getFile()));

                listToWrite.clear();

                while ((tmp = reader.readLine()) != null) {
                    listToWrite.add(tmp);
                    if (tmp.contains("<list>")) {
                        listToWrite.addAll(stringsToWrite);
                    }
                }
                reader.close();

                writer = new BufferedWriter(new FileWriter(configURL.getFile()));
                for (int i = 0; i < listToWrite.size(); i++)
                    writer.write(listToWrite.get(i) + "\r\n");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
