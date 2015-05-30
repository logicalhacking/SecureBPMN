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

package eu.aniketos.securebpmn.validation;

/**
 * A class representing the access of a process variable.
 *
 *
 */
public class ProcVarAccess {

    private String name;
    private int location;
    private boolean write;

    public ProcVarAccess(String name, int location, boolean write) {
        this.name = name;
        this.location = location;
        this.write = write;
    }

    /**
     * Returns the name of the process variable.
     *
     * @return The name of the process variable.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the location, where the access of the variable occurs. This is
     * only applicable for accesses found in Java implementations.
     *
     * @return The location as the position in the file.
     */
    public int getLocation() {
        return location;
    }

    /**
     * Returns if the access is of type read or write
     *
     * @return true if the type is write, false if it is read.
     */
    public boolean isWrite() {
        return write;
    }

}
