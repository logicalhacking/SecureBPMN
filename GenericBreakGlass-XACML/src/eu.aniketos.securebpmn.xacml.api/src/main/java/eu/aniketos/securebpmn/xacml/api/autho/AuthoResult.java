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

import javax.xml.bind.annotation.XmlType;

import eu.aniketos.securebpmn.xacml.api.idm.IdInfo;

@XmlType(namespace="http://eu.aniketos/autho")
public class AuthoResult {
	
//	public enum DECISION {
//		DECISION_PERMIT, 
//		DECISION_DENY,
//		DECISION_INDETERMINATE,
//		DECISION_NOT_APPLICABLE,
//		INVALID_DECISION;
//		
//		private String[] messages = { "Permit", "Deny", 
//				"Indeterminate",
//				"NotApplicable", "Invalid" };
//		
//		public String getMessage() {
//			return messages[this.ordinal()];
//		}
//		
//		public static DECISION getFromInt(int decision) {
//			switch (decision) {
//			case 0:
//				return DECISION_PERMIT;
//			case 1:
//				return DECISION_DENY;
//			case 2:
//				return DECISION_INDETERMINATE;
//			case 3:
//				return DECISION_NOT_APPLICABLE;
//			case 5:
//				return INVALID_DECISION;
//			}
//			return null;
//		}
//	}
	
	private Long evaluationId;
	
	private Long id;
	
	/**
	 * The user for which the evaluation was done. Especially needed, if the 
	 * pep provides the raw AuthInfo to the PDP
	 */
	private IdInfo idInfo;
	
	private Decision decision;
	


	private List<String> statusCode;
	
	private String statusMessage;
	
	private List<AuthoAttribute> missingAttributes;
	
	private List<AuthoObligation> obligations;
	
	public String toString() {
		StringBuffer buff =new StringBuffer(decision.toString());
		buff.append(", statusCodes: ");
		for (String code : statusCode) {
			buff.append(code); buff.append(", ");
		}
		buff.append(", statusMessage: "); buff.append(statusMessage);
		buff.append(" obligations: "); buff.append(obligations == null ? "null" : obligations.size());
		return buff.toString();
	}
	
	public IdInfo getIdInfo() {
		return idInfo;
	}

	public void setIdInfo(IdInfo idInfo) {
		this.idInfo = idInfo;
	}

	public Decision getDecision() {
		return decision;
	}

	public void setDecision(Decision decision) {
		this.decision = decision;
	}

	public List<AuthoAttribute> getMissingAttributes() {
		return missingAttributes;
	}

	public void setMissingAttributes(List<AuthoAttribute> missingAttributes) {
		this.missingAttributes = missingAttributes;
	}

	public List<AuthoObligation> getObligations() {
		return obligations;
	}

	public void setObligations(List<AuthoObligation> obligations) {
		this.obligations = obligations;
	}

	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
	}

	public Long getEvaluationId() {
		return evaluationId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public List<String> getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(List<String> statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
}
