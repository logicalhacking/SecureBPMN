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

import java.sql.Timestamp;
import java.util.List;


/**
 * This class represents a instantiation of the AttributeType meta-data class.
 * 
 */
public class AttributeAssignment {
	
	/** 
	 * DB generated ID
	 */
	protected long id = -1;
	
	/**
	 * value for which it is valid, e.g., nurse 
	 */
	protected String value;
	
	/**
	 * beginning with which date the assignment is valid, per default -infinite
	 */
	protected Timestamp validFrom;
	
	/**
	 * up to when the assignment is valid, per default +infinite
	 */
	protected Timestamp validTo;
	
	/**
	 * which attributeType is instantiated; there, the meta data are stored, e.g.,
	 * that this is an assignment of an attribute "role". 
	 */
	protected AttributeType attrType;
	
	/**
	 * a list of attached attributes which are required from the context  
	 * e.g., the subject-id with value "carol@myhospital.com"
	 */
	protected List<ContextAttributeAssignment> ctxAttrAssignments;
	
	public AttributeAssignment() {
		this.setValidFrom(null);
		this.setValidTo(null);
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

	public Timestamp getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
		if ( this.validFrom == null ) {
			this.validFrom = new Timestamp(new java.util.Date().getTime());
		}
	}

	public Timestamp getValidTo() {
		return validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
		if ( this.validTo == null ) {
			this.validTo = new Timestamp(Long.MAX_VALUE);
		}
	}

	public AttributeType getAttrType() {
		return attrType;
	}

	public void setAttrType(AttributeType attributeType) {
		this.attrType = attributeType;
	}

	public List<ContextAttributeAssignment> getCtxAttrAssignments() {
		return ctxAttrAssignments;
	}

	public void setCtxAttrAssignments(
			List<ContextAttributeAssignment> ctxAttrAssignments) {
		this.ctxAttrAssignments = ctxAttrAssignments;
	}


}
