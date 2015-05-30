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

/**
 * A definition of a relation between two roles in the role hierarchy, used in
 * the user/role definition file.
 *
 *
 */
public class RoleRel implements RoleDefLine {

    private String superRole;
    private String subRole;

    /**
     * Creates a relation between two roles.
     *
     * @param superRole
     *            The name of the higher role.
     * @param subRole
     *            The name of the lower role.
     */
    public RoleRel(String superRole, String subRole) {
        this.superRole = superRole;
        this.subRole = subRole;
    }

    /**
     * Returns the name of the higher role.
     *
     * @return The name of the higher role as a String.
     */
    public String getSuperRole() {
        return superRole;
    }

    /**
     * Returns the name of the lower role.
     *
     * @return The Name of the lower role as a String.
     */
    public String getSubRole() {
        return subRole;
    }

}
