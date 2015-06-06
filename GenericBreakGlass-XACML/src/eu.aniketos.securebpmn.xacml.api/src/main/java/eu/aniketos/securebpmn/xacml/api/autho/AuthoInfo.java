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

package eu.aniketos.securebpmn.xacml.api.autho;

import java.util.List;

import eu.aniketos.securebpmn.xacml.api.idm.IdInfo;

public class AuthoInfo extends IdInfo {
	
	public AuthoInfo() {
		
	}
	
	public AuthoInfo(IdInfo idInfo) {
		super(idInfo); //TODO copy arguments
	}
	
	//List of attributes assigned to the user, e.g., roles, etc.
	//for usual, this information is obtained by the IDM
	protected List<AuthoAttribute> userAttributes; 
	
	protected String IDManager;

	
	 
//	String getProxyGrantingTicket();
//	String getAuthenticatedUser();
//	SecurityError getException();
//	boolean hasError();
//	ErrorType getError();
//	ReasonType getReason();
}
