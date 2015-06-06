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


/**
 * This class contains meta-data for the PDPState.
 * <br/>
 * It is (more or less) the technical implementation of the n:m mapping
 * from AttributeType to AttributeDBIdentifier; however, it is referenced
 * from ContextAttributeAssignment (mainly out of consistency and data
 * space reasons) and therefore implemented as disting class 
 * 
 */
public class ContextAttribute {
	
	/**
	 * DB generated ID
	 */
	protected int id = -1;

	/**
	 * defines the required attributeId 
	 */
	protected AttributeDBIdentifier attrId;
		
	/**
	 * the AttributeType it is assigned to
	 */
	protected AttributeType attrType;
	
	public ContextAttribute() {}
	
	public ContextAttribute(AttributeDBIdentifier attrId, AttributeType attrType) {
		this.attrId = attrId;
		this.attrType = attrType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AttributeDBIdentifier getAttrId() {
		return attrId;
	}

	public void setAttrId(AttributeDBIdentifier attrId) {
		this.attrId = attrId;
	}

	public AttributeType getAttrType() {
		return attrType;
	}

	public void setAttrType(AttributeType attrType) {
		this.attrType = attrType;
	}
}
