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


/**
 * 
 * Allows to identify attributes by its defining URIs, e.g., as key for hash tables
 * <br/>
 * As attributes within xacml cannot be uniquely identified using their java objects, this 
 * class can be used as such a unique identification
 *
 */
public class AttributeIdentifier {
	
	protected URI category, attributeType, attributeId, issuer;
	
	public AttributeIdentifier(URI category, URI attributeType, URI attributeId, URI issuer) {
		if ( category == null || attributeId == null || attributeType == null ) {
			throw new RuntimeException("category, type and ID must not be null!");
		}
		this.category = category;
		this.attributeType = attributeType;
		this.attributeId = attributeId;
		this.issuer = issuer;
	}
	
	
	public AttributeIdentifier() {
		//needed for web service stuff.. should not be used otherwise
	}


	@Override
	public int hashCode() {
		if ( issuer == null ) {
			return category.hashCode() + attributeId.hashCode() + attributeType.hashCode();
		} else {
			return category.hashCode() + attributeId.hashCode() + attributeType.hashCode() + issuer.hashCode();
		}
		
	}
	
	@Override 
	public boolean equals(Object o) {
		if ( o instanceof AttributeIdentifier) {
			AttributeIdentifier a = (AttributeIdentifier) o;
			if ( this.category.equals(a.category) &&
					this.attributeType.equals(a.attributeType) &&
					this.attributeId.equals(a.attributeId) &&
					((this.issuer == null && a.issuer == null) || this.issuer.equals(a.issuer))) {
				return true;
			} else {
				return false;
			}
			
		} else {
			return false;
		}		
	}

	
	@Override 
	public String toString() {
		return "[category]" + category + "[category];[type]" + attributeType + "[type];[id]" + attributeId + "[id];[issuer]" + issuer + "[issuer]";
	}
	
	
	public URI getCategory() {
		return category;
	}

	public URI getAttributeType() {
		return attributeType;
	}

	public URI getAttributeId() {
		return attributeId;
	}

	public URI getIssuer() {
		return issuer;
	}
}
