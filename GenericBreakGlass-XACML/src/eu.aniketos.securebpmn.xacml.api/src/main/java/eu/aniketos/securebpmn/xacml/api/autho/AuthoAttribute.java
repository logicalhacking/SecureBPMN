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

import javax.xml.bind.annotation.XmlType;

@XmlType(namespace="http://aniketos.eu/autho")
public class AuthoAttribute {
	
	public static final URI OBLIGATION_CATEGORY = URI.create("urn:category:obligation");
	
	enum STATUS {
		MISSING,
		RESOLVED,
		RESOLUTION_ERROR
	}
	private Long id;
	
	protected AttributeIdentifier attrId;
	protected String value;
	
	protected boolean underRevision;
	
	//TODO save, if -) within initial request -) requested through 
	public static final int INITIAL_REQUST = 0x1, ATTR_RESOLVER = 0x2;
	
	
	
	public AuthoAttribute() {
		// needed for web service stuff
	}

	public AuthoAttribute(AttributeIdentifier attrId, String value) {
		this.attrId = attrId;
		this.value = value;
	}
	

	
	public AuthoAttribute(URI categoryId, URI attributeId, URI dataType, String value) {
		this.attrId = new AttributeIdentifier(categoryId, dataType, attributeId, null);
		this.value = value;
	}
	
	public AttributeIdentifier getAttributeIdentifier() {
		return this.attrId;
	}
	
	public void setAttributeIdentifier(AttributeIdentifier attrId) {
		this.attrId = attrId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
