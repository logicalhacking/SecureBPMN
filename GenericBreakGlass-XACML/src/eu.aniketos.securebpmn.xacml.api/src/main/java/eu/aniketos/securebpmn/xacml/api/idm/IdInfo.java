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

import java.util.List;

import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Contains authentication information (ID Provider) and authorization 
 * relevant information (identity management) about the user 
 *
 */
@XmlType(namespace="http://idm.aniketos.eu/")
public class IdInfo {



	//the resolved userID
	protected String userId; // Required
	
	//ID Provider which was used to resolve the userID
	protected String IdProvider; // Required
	
	//List of services which are in the call chain to the current instance
	protected List<String> callChain; // Optional, technology dependant, e.g., CAS
	
	public IdInfo(String userId) {
		this.userId = userId;
	}
	
	
	public IdInfo(String userId, String IdProvider, List<String> callChaind) {
		this.userId = userId;
		this.IdProvider = IdProvider;
		this.callChain = callChaind;
	}
	
	protected IdInfo(IdInfo copy) {
		this.userId = copy.userId;
		this.IdProvider = copy.IdProvider;
		this.callChain = copy.callChain;
	}
	
	protected IdInfo() {
		
	}
	
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getIdProvider() {
		return IdProvider;
	}


	public void setIdProvider(String idProvider) {
		IdProvider = idProvider;
	}


	public List<String> getCallChain() {
		return callChain;
	}


	public void setCallChain(List<String> callChain) {
		this.callChain = callChain;
	}
	

}
