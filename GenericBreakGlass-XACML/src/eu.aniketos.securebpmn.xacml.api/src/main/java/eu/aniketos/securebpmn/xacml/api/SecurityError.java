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

package eu.aniketos.securebpmn.xacml.api;

import javax.xml.ws.WebFault;


@WebFault(targetNamespace="aniketos.eu/") //, name="SecurityError" name="eu.aniketos.SecurityError", faultBean="eu.aniketos.SecurityError
public class SecurityError extends Exception {

	private static final long serialVersionUID = 1031874369988364332L;
	
	protected ErrorType error;
	protected ReasonType reason;

	protected String message;
	
	protected long evaluationId;

	private static final String[] error_messages = {
		"Authentication failed",
		"Authorization failed",
		"Configuration error"
	};

	
	private static final String[] reason_messages = {
		"SSO Engine error",
		"Missing Security Token",
		"Missing authenticated user",
		"Invalid Security Token",
		"Invalid Username or Password",
		"Security Token not valid for requested service",
		
		"Invalid parameters",
		"PDE Engine error",
		"Access requires break-glass",
		"No policy defined for requested resource",
		"Invalid XACML Defintion",
		"User is not permitted to access the requested resource"		
	};
	
	/**
	 * Creating a new SecurityError, message is generated from error and reason
	 * 
	 * @param error
	 * @param reason
	 */
	public SecurityError(ErrorType error, ReasonType reason) {
		super(generateMessage(error, reason));
		this.message = super.getMessage();
		this.error = error;
		this.reason = reason;
	}
	
	/**
	 * Creating a new SecurityError, message is generated from error and reason, enhanced with additionalMessage
	 * 
	 * @param error
	 * @param reason
	 * @param additionalMessage
	 */
	public SecurityError(ErrorType error, ReasonType reason, String additionalMessage) {
		super(generateMessage(error, reason, additionalMessage));
		this.message = super.getMessage();
		this.error = error;
		this.reason = reason;
	}

	/**
	 * Creating a new SecurityError, message is generated from error and reason
	 * 
	 * @param error
	 * @param reason
	 * @param exception
	 */
	public SecurityError(ErrorType error, ReasonType reason, Throwable exception) {
		super(generateMessage(error, reason), exception);
		this.message = super.getMessage();
		this.error = error;
		this.reason = reason;
	}
	/**
	 * Creating a new SecurityError, message is generated from error and reason, enhanced with additionalMessage
	 * 
	 * @param error
	 * @param reason
	 * @param additionalMessage
	 * @param exception
	 */
	public SecurityError(ErrorType error, ReasonType reason, String additionalMessage, Throwable exception) {
		super(generateMessage(error, reason, additionalMessage), exception);
		this.message = super.getMessage();
		this.error = error;
		this.reason = reason;
	}
	
	/**
	 * Creating a new Security Error with a free defined message. Should only be used to create a new SecurityError in Proxies, wrapping a received error
	 * @param message
	 * @param error
	 * @param reason
	 */
	public SecurityError(String message, ErrorType error, ReasonType reason) {
		super(message);
		this.message = super.getMessage();
		this.error = error;
		this.reason = reason;
	}

	/**
	 * returns the main error type for this SecurityError
	 * 
	 * @return
	 */
	public ErrorType getError() {
		return error;
	}
	/**
	 * should not be used to create a new message; is required for web service interfaces
	 * @param error
	 */
	public void setError(ErrorType error) {
		this.error = error;
	}

	/**
	 * returns the more detailed reason for this SecurityError 
	 *  
	 * @return
	 */
	public ReasonType getReason() {
		return reason;
	}
	
	/**
	 * should not be used to create a new message; is required for web service interfaces
	 * @param reason
	 */
	public void setReason(ReasonType reason) {
		this.reason = reason;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
	/**
	 * DO NOT USE! Required for SCA
	 * @param message
	 */
	public void setMessage(String message) {
		//required for SCA? i.e., finding "message" as attribute with getters and setters
		this.message = message;
	}
	
	public long getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(long evaluationId) {
		this.evaluationId = evaluationId;
	}
	
	
//	/**
//	 * DO NOT USE! Required for SCA
//	 *
//	 */
//	public SecurityError() {
//		
//	}
	
	
	
	private static String generateMessage(ErrorType error, ReasonType reason) {
		return error_messages[error.ordinal()] + ": " + reason_messages[reason.ordinal()];
		
	}
	
	private static String generateMessage(ErrorType error, ReasonType reason, String additionalMessage) {
		return generateMessage(error, reason) + " (" + additionalMessage + ")";
	}
	
}
