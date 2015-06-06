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
 * 
 * Defines the main type of the SecurityError
 */
@XmlType(namespace="http://aniketos.eu/")
public enum ErrorType {
	AUTHENTICATION_FAILED,
	AUTHORIZATION_FAILED,
	CONFIGURATION_ERROR
}
