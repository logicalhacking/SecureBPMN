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

package eu.aniketos.securebpmn.xacml.pdpstate;

public class InvalidAssignmentException extends RuntimeException {
	
	private static final long serialVersionUID = -387842157062192483L;

	public enum Reason {
		OVERLAPPING_ASSIGNMENT, //there already exists an assignment
		INVALID_DATE_NO_TIMEFRAME, //the dates do not spawn a time frame, i.e, from is after to
		INVALID_DATE_MODIFICATION_OF_PAST, //it is not allowed to do assignments in the past
		NO_ASSIGNMENT_AVAILABLE, // for the defined contraints (type, time) no such assignment is available
		INVALID_STATE // worst case - invalid state...
	}
	
	private Reason reason;
	private String message;
	
	public InvalidAssignmentException(Reason reason) {
		this.reason = reason;
	}
	
	public InvalidAssignmentException(Reason reason, String message) {
		this.reason = reason;
		this.message = message;
	}
	
	public Reason getReason() {
		return reason;
	}
	
	public String getMessage() {
		//TODO generate default message according to reason
		return message;
	}

}
