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
import java.util.List;
import java.util.Vector;

/**
 * 
 * Attribtues resolved at runtime by the com.sun.xacml.attr.AttributeDesignator
  *
 */
public class DesignatorAttribute {
	
	protected AttributeIdentifier attrId;


	protected List<String> values;
	
	public DesignatorAttribute(AttributeIdentifier attrId) {
		this.attrId = attrId;
	}
	
	public DesignatorAttribute(URI attributeId, URI dataType, URI categoryId) {
		this.attrId = new AttributeIdentifier(categoryId, dataType, attributeId, null);
	}
	
	public AttributeIdentifier getAttrId() {
		return attrId;
	}

	public void setAttrId(AttributeIdentifier attrId) {
		this.attrId = attrId;
	}
	
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}
	
	public void addBagValue(String bagValue) {
		if ( values == null ) {
			values = new Vector<String>();
		}
		values.add(bagValue);
	}
	
	public List<String> getBagValues() {
		return this.values;
	}
	
	public boolean isSingleValue() {
		return values!= null &&  values.size() == 1 ? true : false;
	}
	
	public boolean isEmptyValue() {
		return values == null ? true : false;
	}
}
