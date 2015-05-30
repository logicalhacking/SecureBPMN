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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.activiti.designer.util.eclipse.ActivitiUiUtil;
import org.antlr.roledef.RoleDefLexer;
import org.antlr.roledef.RoleDefParser;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.securebpmn2.Action;
import org.eclipse.securebpmn2.Role;
import org.eclipse.securebpmn2.Securebpmn2Factory;
import org.eclipse.securebpmn2.Subject;
import org.eclipse.securebpmn2.User;

import eu.aniketos.securebpmn.roles.RoleDef;
import eu.aniketos.securebpmn.roles.RoleDefLine;
import eu.aniketos.securebpmn.roles.RoleRel;

/**
 * This Class contains utility methods for retrieving Users, Roles and
 * ActivityActions.
 *
 *
 */
public class SecurityUtil {

    private static List<Role> roles;
    private static List<User> users;
    private static List<Action> activityActions;
    private static String diagramName;

    /**
     * Retrieve the roles for a given Diagram.
     *
     * @param diagram
     *            The diagram for which the roles should be retrieved.
     * @return A List of Roles that are specified for the Diagram.
     */
    public static List<Role> getRoles(Diagram diagram) {
        if (diagram == null || diagram.getName() == null)
            return new ArrayList<Role>();

        if (diagramName == null) {
            diagramName = diagram.getName();
        }

        if (roles == null || !diagram.getName().equals(diagramName)) {
            loadRolesAndUsers(diagram);
        }
        return roles;
    }

    /**
     * Retrieve the users for a given Diagram.
     *
     * @param diagram
     *            The diagram for which the users should be retrieved.
     * @return A List of Users that are specified for the Diagram.
     */
    public static List<User> getUsers(Diagram diagram) {
        if (diagramName == null) {
            diagramName = diagram.getName();
        }

        if (users == null || !diagram.getName().equals(diagramName)) {
            loadRolesAndUsers(diagram);
        }
        return users;
    }

    /**
     * Retrieve the ActivityActions that are currently available.
     *
     * @return A List of ActivityActions that are available.
     */
    public static List<Action> getActivityActions() {
        if (activityActions == null) {
            activityActions = new ArrayList<Action>();

            Action claimActivityAction = Securebpmn2Factory.eINSTANCE
                                         .createAtomicActivityAction();
            claimActivityAction.setId(UUID.randomUUID().toString());
            claimActivityAction.setActionName("Claim");
            Action assignActivityAction = Securebpmn2Factory.eINSTANCE
                                          .createAtomicActivityAction();
            assignActivityAction.setId(UUID.randomUUID().toString());
            assignActivityAction.setActionName("Assign");
            Action completeActivityAction = Securebpmn2Factory.eINSTANCE
                                            .createAtomicActivityAction();
            completeActivityAction.setActionName("Complete");
            completeActivityAction.setId(UUID.randomUUID().toString());
            Action fullAccess = Securebpmn2Factory.eINSTANCE
                                .createCompositeActivityAction();
            fullAccess.setActionName("Full Access");

            activityActions.add(fullAccess);
            activityActions.add(claimActivityAction);
            activityActions.add(assignActivityAction);
            activityActions.add(completeActivityAction);

        }
        return activityActions;
    }

    /**
     * Loads the roles and users from the configuration file. The file must be
     * located in the same folder as the Diagram and the filename must be the
     * same name as the Diagram file with the extension .roles.
     *
     * @param diagram
     *            The Diagram for which the roles and users should be loaded.
     */
    private static void loadRolesAndUsers(final Diagram diagram) {

        // clear roles and users that may exist
        if (roles == null) {
            roles = new ArrayList<Role>();
        } else {
            roles.clear();
        }

        if (users == null) {
            users = new ArrayList<User>();
        } else {
            users.clear();
        }

        // parse role file and fill lists
        if (diagram != null) {

            // role hierarchy
            List<RoleRel> hierarchy = new ArrayList<RoleRel>();

            String in = "";

            // get file location
            URI uri = diagram.eResource().getURI();
            URI platformUri = uri.trimFragment();
            platformUri = platformUri.trimFileExtension();
            platformUri = platformUri.appendFileExtension("roles");

            final IResource fileResource = ResourcesPlugin.getWorkspace()
                                           .getRoot().findMember(platformUri.toPlatformString(true));
            if (fileResource != null && fileResource.exists()) {
                final String fileUri = fileResource.getLocation().toString();

                // read file
                try {
                    in = readFileAsString(fileUri);
                } catch (IOException e) {
                    System.err.println("[SCVM-RBAC] Error while reading file: "
                                       + fileUri.toString());
                    e.printStackTrace();
                }
            }

            if (in.length() > 0) {
                List<RoleDefLine> lines = null;

                try {
                    RoleDefLexer lex = new RoleDefLexer(new ANTLRStringStream(
                                                            in));
                    CommonTokenStream tokens = new CommonTokenStream(lex);

                    RoleDefParser parser = new RoleDefParser(tokens);
                    lines = parser.file();

                } catch (RecognitionException e) {
                    System.err
                    .println("[SCVM-RBAC] Error while parsing role/user file.");
                    e.printStackTrace();
                }

                // generate roles for result
                if (lines != null) {
                    for (RoleDefLine line : lines) {
                        if (line instanceof RoleDef) {
                            final RoleDef def = (RoleDef) line;

                            // create (if necessary) and add role
                            Role role = Securebpmn2Factory.eINSTANCE
                                        .createRole();
                            role.setName(def.getRoleName());
                            role.setId(UUID.randomUUID().toString());
                            roles.add(role);

                            // create and add users to role
                            for (String userName : def.getMembers()) {
                                User user = Securebpmn2Factory.eINSTANCE
                                            .createUser();
                                user.setUserName(userName);
                                user.setId(userName.toLowerCase());
                                users.add(user);

                                user.getRoles().add(role);
                                role.getSubjects().add(user);

                            }

                        } else if (line instanceof RoleRel) {
                            hierarchy.add((RoleRel) line);
                        }
                    }
                }
            }

            // TODO order hierarchy list

            // assign new roles to users
            for (RoleRel rel : hierarchy) {

                // find roles
                Role supRole = null;
                Role subRole = null;
                for (Role r : roles) {
                    if (r.getName().equals(rel.getSuperRole())
                            && supRole == null)
                        supRole = r;
                    if (r.getName().equals(rel.getSubRole()) && subRole == null)
                        subRole = r;
                }

                // assign users to subroles
                for (Subject s : supRole.getSubjects()) {
                    if (s instanceof User) {
                        User u = (User) s;
                        if (!u.getRoles().contains(subRole)) {
                            u.getRoles().add(subRole);
                            subRole.getSubjects().add(u);
                        }
                    }
                }
            }

        }

        if (roles.size() == 0) {
            System.out
            .println("[SCVM-RBAC] No roles or users provided, generating dummy roles and users.");
            Role manager = Securebpmn2Factory.eINSTANCE.createRole();
            manager.setName("Manager");
            manager.setId(UUID.randomUUID().toString());
            Role supervisor = Securebpmn2Factory.eINSTANCE.createRole();
            supervisor.setName("Supervisor");
            supervisor.setId(UUID.randomUUID().toString());
            Role clerk = Securebpmn2Factory.eINSTANCE.createRole();
            clerk.setId(UUID.randomUUID().toString());
            clerk.setName("Clerk");
            roles.add(manager);
            roles.add(supervisor);
            roles.add(clerk);
        }

        if (users.size() == 0) {
            for (Role role : roles) {
                User user1 = Securebpmn2Factory.eINSTANCE.createUser();
                user1.setUserName("user1_" + role.getName());
                user1.setId("user1_" + role.getName().toLowerCase());
                user1.getRoles().add(role);
                User user2 = Securebpmn2Factory.eINSTANCE.createUser();
                user2.setUserName("user2_" + role.getName());
                user2.setId("user2_" + role.getName().toLowerCase());
                user2.getRoles().add(role);
            }
        }


        // save users to diagram so that the editor won't crash loading a
        // diagram with roles that reference to the users.
        // unfortunately, the attributes of roles and users do not get loaded
        // correctly when opening a .activiti file.
        final List<User> usersToAdd = new ArrayList<User>();
        for (User u : users) {
            boolean isInDiagram = false;
            for (EObject o : diagram.eResource().getContents()) {
                if (o instanceof User) {
                    User my_user = (User) o;
                    if (my_user.getId() != null
                            && my_user.getId().equals(u.getId())) {
                        isInDiagram = true;
                        break;
                    }

                }
            }
            if (!isInDiagram) {
                usersToAdd.add(u);
            }

        }
        TransactionalEditingDomain editingDomain = TransactionUtil
                .getEditingDomain(diagram);

        if (!usersToAdd.isEmpty()) { // should fix aslan generation bug with automated role generation
            ActivitiUiUtil.runModelChange(new Runnable() {
                public void run() {
                    for (User u : usersToAdd) {
                        diagram.eResource().getContents().add(u);
                    }
                }
            }, editingDomain, "User management");
        }

    }
    /**
     * Reads a file from disk and writes its contents to a String.
     *
     * @param filePath
     *            The path to the file.
     * @return The contents of the file.
     * @throws IOException
     *             Errors that occur accessing the file.
     */
    public static String readFileAsString(String filePath) throws IOException {
        byte[] buffer = new byte[(int) new File(filePath).length()];
        BufferedInputStream f = null;
        try {
            f = new BufferedInputStream(new FileInputStream(filePath));
            f.read(buffer);
        } finally {
            if (f != null)
                try {
                    f.close();
                } catch (IOException ignored) {
                }
        }
        return new String(buffer);
    }

}
