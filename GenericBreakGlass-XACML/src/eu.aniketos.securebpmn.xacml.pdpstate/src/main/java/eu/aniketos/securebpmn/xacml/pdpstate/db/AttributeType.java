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

import java.util.List;



/**
 * This class contains meta-data for the PDPState.
 * <br/>
 * The "main" metaData class for the PDPState; here, all known 
 * assignments are stored. This is needed to keep the database in 
 * a consistent and non-redundant state and to allow the PDPStateModule 
 * to retrieve the context information from the PDP in an easy way 
 * 
 */
public class AttributeType  {
	
	/**
	 * DB generated ID
	 */
	protected int id = -1;
	
	/**
	 * The type of attribute which can be resolved by this attributeType, 
	 * e.g, role
	 */
	protected AttributeDBIdentifier attrType;
	
	/**
	 * List of required attributes which are required to determine the 
	 * assigned value (may be empty, e.g., in case of active policies),
	 * example: subject-id for attrTyp role
	 */
	protected List<ContextAttribute> ctxTypes;
	
	public AttributeType(AttributeDBIdentifier attrType, List<ContextAttribute> ctxTypes) {
		this.attrType = attrType;
		this.ctxTypes = ctxTypes;
	}


	//empty constructor for hibernate
	public AttributeType() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AttributeDBIdentifier getAttrType() {
		return attrType;
	}

	public void setAttrType(AttributeDBIdentifier attrType) {
		this.attrType = attrType;
	}
	
	public List<ContextAttribute> getCtxTypes() {
		return ctxTypes;
	}

	public void setCtxTypes(List<ContextAttribute> ctxTypes) {
		this.ctxTypes = ctxTypes;
	}
}
