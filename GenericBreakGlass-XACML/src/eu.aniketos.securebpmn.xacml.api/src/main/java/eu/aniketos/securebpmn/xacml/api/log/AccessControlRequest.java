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

package eu.aniketos.securebpmn.xacml.api.log;

import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import eu.aniketos.securebpmn.xacml.api.autho.AuthoAttribute;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoObligation;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoResult;
import eu.aniketos.securebpmn.xacml.api.autho.DesignatorAttribute;
import eu.aniketos.securebpmn.xacml.api.idm.IdInfo;

public class AccessControlRequest extends LogEntry {
	
	private Long evaluationId; 
	private long policyVersion; 
	private Date execTime; 

	//request part
	private IdInfo idInfo;
	private URI resource;		 
	private String action;

	private Collection<AuthoAttribute> attributes;

	//RequestCtx
	private Object request;	
	private String xacmlRequest;


	private AuthoResult result;
	private List<AuthoObligation> obligations;
	
	private List<DesignatorAttribute> designAttrs; 
	
	//ResponseCtx
	private Object response;
	private String xacmlResponse;
	
	
	private long duration;		
	private String errorMessage;
	
	public AccessControlRequest() {
		super(null, Log_Type.ACCESS_REQUEST);
	}

	public AccessControlRequest(Long evaluationId, String xacmlRequest) {
		super(new Date(), Log_Type.ACCESS_REQUEST);
		this.evaluationId = evaluationId;
		this.xacmlRequest = xacmlRequest;
	}
	
	public AccessControlRequest(Long evaluationId, IdInfo idInfo, 
			URI resource, String action, List<AuthoAttribute> attributes) {
		super(new Date(), Log_Type.ACCESS_REQUEST); 
		this.evaluationId = evaluationId;
		this.idInfo = idInfo;
		this.resource = resource;
		this.action = action;
		this.attributes = attributes;
	}

	public void finished(Object request, Object response, String xacmlResponse, 
			Date execTime, long policyVersion, 
			List<DesignatorAttribute> designAttrs) {
		duration = new Date().getTime() - getArrival().getTime();
		this.setRequest(request);
		this.setResponse(response);
		this.execTime = execTime;
		this.policyVersion = policyVersion;
		this.xacmlResponse = xacmlResponse;
		this.designAttrs = designAttrs;
	}
	
	public void finished(Object request, Object response, AuthoResult result, 
			Date execTime, long policyVersion, 
			List<DesignatorAttribute> designAttrs) {
		duration = new Date().getTime() - getArrival().getTime();
		this.setRequest(request);
		this.setResponse(response);
		this.execTime = execTime;
		this.policyVersion = policyVersion;
		this.result = result;
		this.designAttrs = designAttrs;
	}
	
	
	

	public Long getEvaluationId() {
		return evaluationId;
	}
	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
	}
	public String getXacmlRequest() {
		return xacmlRequest;
	}
	public void setXacmlRequest(String xacmlRequest) {
		this.xacmlRequest = xacmlRequest;
	}
	public URI getResource() {
		return resource;
	}
	public void setResource(URI resource) {
		this.resource = resource;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Collection<AuthoAttribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(Collection<AuthoAttribute> attributes) {
		this.attributes = attributes;
	}
	public AuthoResult getResult() {
		return result;
	}
	public void setResult(AuthoResult result) {
		this.result = result;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setXacmlResponse(String xacmlResponse) {
		this.xacmlResponse = xacmlResponse;
	}
	public String getXacmlResponse() {
		return xacmlResponse;
	}
	public void setObligations(List<AuthoObligation> obligations) {
		this.obligations = obligations;
	}
	public List<AuthoObligation> getObligations() {
		return obligations;
	}

	public void setPolicyVersion(long policyVersion) {
		this.policyVersion = policyVersion;
	}

	public long getPolicyVersion() {
		return policyVersion;
	}

	public void setRequest(Object request) {
		this.request = request;
	}

	public Object getRequest() {
		return request;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Object getResponse() {
		return response;
	}
	
	public List<DesignatorAttribute> getDesignatorAttributes() {
		return this.designAttrs;
	}
	
	public void setDesignatorAttributes(List<DesignatorAttribute> designAttrs) {
		this.designAttrs = designAttrs;
	}

	public IdInfo getIdInfo() {
		return idInfo;
	}

	public void setIdInfo(IdInfo idInfo) {
		this.idInfo = idInfo;
	}
	
	public Date getExecTime() {
		return execTime;
	}

	public void setExecTime(Date execTime) {
		this.execTime = execTime;
	}
	
}
