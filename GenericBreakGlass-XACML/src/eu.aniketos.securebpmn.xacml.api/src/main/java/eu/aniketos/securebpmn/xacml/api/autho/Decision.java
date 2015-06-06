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

import javax.xml.bind.annotation.XmlType;

@XmlType(namespace="http://aniketos.eu/autho")
public enum Decision {
	DECISION_PERMIT, 
	DECISION_DENY,
	DECISION_INDETERMINATE,
	DECISION_NOT_APPLICABLE,
	INVALID_DECISION;
	
	private String[] messages = { "Permit", "Deny", 
			"Indeterminate",
			"NotApplicable", "Invalid" };
	
	public String getMessage() {
		return messages[this.ordinal()];
	}
	
	public static Decision getFromInt(int decision) {
		switch (decision) {
		case 0:
			return DECISION_PERMIT;
		case 1:
			return DECISION_DENY;
		case 2:
			return DECISION_INDETERMINATE;
		case 3:
			return DECISION_NOT_APPLICABLE;
		case 5:
			return INVALID_DECISION;
		}
		return null;
	}

}
