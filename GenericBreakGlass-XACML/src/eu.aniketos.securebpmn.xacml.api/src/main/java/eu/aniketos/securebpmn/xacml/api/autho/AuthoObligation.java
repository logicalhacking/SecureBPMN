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

import java.net.URI;
import java.util.Collection;

import javax.xml.bind.annotation.XmlType;

@XmlType(namespace="http://aniketos.eu/autho")
public class AuthoObligation {
	
	private Long id;
	private String type;
	private Collection<AuthoAttribute> parameters;
	
	public AuthoObligation() {
		
	}
	
	public AuthoObligation(URI type) {
		this.type = type.toString();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Collection<AuthoAttribute> getParameters() {
		return parameters;
	}
	public void setParameters(Collection<AuthoAttribute> parameters) {
		this.parameters = parameters;
	}
}
