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

package eu.aniketos.securebpmn.roles;

import java.util.ArrayList;
import java.util.List;

/**
 * A definition of a role and the containing users in the user/role definition
 * file.
 *
 *
 */
public class RoleDef implements RoleDefLine {

    private String roleName;
    private List<String> members;

    /**
     * Creates a role definition.
     *
     * @param roleName
     *            The name of the role.
     */
    public RoleDef(String roleName) {
        this.roleName = roleName;
        members = new ArrayList<String>();
    }

    /**
     * Adds a member to the role.
     *
     * @param member
     *            The name of a new member as a String.
     */
    public void addMember(String member) {
        members.add(member);
    }

    /**
     * Returns the name of the role.
     *
     * @return The name of the role as a String.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Returns the name of the members of the role.
     *
     * @return A List containing the names of the members.
     */
    public List<String> getMembers() {
        return members;
    }

}
