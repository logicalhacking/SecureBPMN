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

package eu.aniketos.securebpmn.xacml.pdpstate.db;

import java.net.URI;

import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;

/**
 * This class contains meta-data for the PDPState.
 * <br/>
 * allows to identify and reference an attribute as it is used within XACML; 
 * for the PDPState, this is more or less meta data information; i.e., all different
 * types of attributes are listed here.
 * 
 * this is the same implementation as for the LogServer; may be merged if both 
 * implementation are moved to one jar
 * 
 */
public class AttributeDBIdentifier extends AttributeIdentifier {
	
	protected int id = -1;

	public AttributeDBIdentifier(URI category, URI attributeType, URI attributeId, URI issuer) {
		super(category, attributeType, attributeId, issuer);
	}
	
	public AttributeDBIdentifier(AttributeIdentifier attrId) {
		super(attrId.getCategory(), attrId.getAttributeType(), attrId.getAttributeId(), attrId.getIssuer());
	}
	
	protected AttributeDBIdentifier() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public void setCategory(URI categrory) {
		super.category = categrory;
	}

	public void setAttributeType(URI attributeType) {
		super.attributeType = attributeType;
	}

	public void setAttributeId(URI attributeId) {
		super.attributeId = attributeId;
	}

	public void setIssuer(URI issuer) {
		super.issuer = issuer;;
	}
}
