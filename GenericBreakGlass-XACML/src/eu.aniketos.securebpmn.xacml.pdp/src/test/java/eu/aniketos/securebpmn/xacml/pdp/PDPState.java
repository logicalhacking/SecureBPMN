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

package eu.aniketos.securebpmn.xacml.pdp;

import eu.aniketos.securebpmn.xacml.pdpstate.DemoPDPStateMgt;
import eu.aniketos.securebpmn.xacml.pdpstate.PDPStateManagement;

public class PDPState {

    private static PDPStateManagement pdpStateMgt;
    private static DemoPDPStateMgt demoPdpStateMgt;

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public static final String ALICE = "Alice",
                               BOB = "Bob",
                               NURSE = "nurse",
                               CLINICAN = "clinician";



    public static void setupDemoRoles() {
        init();

        demoPdpStateMgt.addRole(ALICE, NURSE);
        demoPdpStateMgt.addRole(BOB, CLINICAN);
        demoPdpStateMgt.addRole(BOB, NURSE);
    }


    private static void init() {
        if (pdpStateMgt == null ) {
            pdpStateMgt = PDPStateManagement.getInstance();
            demoPdpStateMgt = new DemoPDPStateMgt();
        }

    }



}
