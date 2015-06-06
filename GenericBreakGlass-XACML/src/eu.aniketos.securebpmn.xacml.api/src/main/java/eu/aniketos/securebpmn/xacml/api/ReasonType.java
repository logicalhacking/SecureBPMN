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

import javax.xml.bind.annotation.XmlType;

/**
 * Defines a more detailed reason for the error
 * 
 */
@XmlType(namespace="http://aniketos.eu/")
public enum ReasonType {
	SSO_ENGINE_ERROR,
	MISSING_CAS_TICKET,
	MISSING_USER,
	INVALID_CAS_TICKET,
	INVALID_USERNAME_PASSWORD,
	CAS_TICKET_WRONG_SERVICE,
	
	INVALID_PARAMETERS,
	PDE_ENGINE_ERROR,
	BREAK_GLASS,
	UNDEFINED_POLICY,
	INVALID_XACML,
	DENY
}
