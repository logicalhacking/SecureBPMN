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

package eu.aniketos.securebpmn.xacml.api.idm;


/**
 * contains (technology neutral) authentication information information, e.g.,
 * <ul> 
 *   <li>SAML Token</li>
 *   <li>CAS Token</li>
 *   <li>OpenID Token</li> 
 * </ul>
 * 
 */
public class AuthInfo {
	protected String authProviderId;
	protected String token;
	protected IdInfo idInfo;
	
	public AuthInfo() {
		
	}
	
	public AuthInfo(String authProviderId, String token) {
		this.authProviderId = authProviderId;
		this.token = token;
	}
	
	protected AuthInfo(IdInfo idInfo) {
		this.idInfo = idInfo;
	}

	public String getAuthProviderId() {
		return authProviderId;
	}

	public void setAuthProviderId(String authProviderId) {
		this.authProviderId = authProviderId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
