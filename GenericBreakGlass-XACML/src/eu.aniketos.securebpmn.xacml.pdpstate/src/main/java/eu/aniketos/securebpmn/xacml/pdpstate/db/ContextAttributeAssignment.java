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
 * This class represents an assignment of a context attribute
 * to an attribute assignment; one could also see ContextAttributeAssignments 
 * as constraints when a attributeAssignment is valid.
 */
public class ContextAttributeAssignment {
	
	/**
	 * DB generated ID
	 */
	protected long id = -1;
	
	/**
	 * the assigned value, e.g, nurse@hospital.de
	 */
	protected String value;
	
	/**
	 * the context attribute which is instaniated  
	 */
	protected ContextAttribute ctxAttribute;

	/**
	 * the attribute assignment which is "constrained" with this
	 * context attribute assignment
	 */
	protected AttributeAssignment attrAssignment;
	
	public ContextAttributeAssignment() {}

	public ContextAttributeAssignment(String value, ContextAttribute ctxAttribute, AttributeAssignment attrAssignment) {
		this.value = value;
		this.ctxAttribute = ctxAttribute;
		this.attrAssignment = attrAssignment;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public ContextAttribute getCtxAttribute() {
		return ctxAttribute;
	}

	public void setCtxAttribute(ContextAttribute ctxAttribute) {
		this.ctxAttribute = ctxAttribute;
	}
	public AttributeAssignment getAttrAssignment() {
		return attrAssignment;
	}

	public void setAttrAssignment(AttributeAssignment attrAssignment) {
		this.attrAssignment = attrAssignment;
	}
	
	
	
	
//	protected AttributeDBIdentifier attrType;
//	
//	public ContextAttributeAssignment(AttributeDBIdentifier attrType, String value) {
//		this.attrType = attrType;
//		this.value = value;
//	}
//	
//	//empty constructor for hibernate
//	public ContextAttributeAssignment() {}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public AttributeDBIdentifier getAttrType() {
//		return attrType;
//	}
//
//	public void setAttrType(AttributeDBIdentifier attrType) {
//		this.attrType = attrType;
//	}
//
//	public String getValue() {
//		return value;
//	}
//
//	public void setValue(String value) {
//		this.value = value;
//	}

}
