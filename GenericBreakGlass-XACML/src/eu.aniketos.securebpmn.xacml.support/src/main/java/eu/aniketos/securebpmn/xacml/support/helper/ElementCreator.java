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

package eu.aniketos.securebpmn.xacml.support.helper;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.support.Category;

import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.attr.AttributeFactory;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.RequestElement;

public abstract class ElementCreator implements IElementCreator {

    private static Logger logger = Logger.getLogger(ElementCreator.class);

    protected static AttributeFactory attrFactory = AttributeFactory.getInstance();


    protected abstract URI getCategory();
    protected abstract URI getDefaultId();
    protected abstract URI getDefaultType();
    public abstract void setCategory(Category category);

    public RequestElement getRequestElement(String value) throws ParsingException {
        Attribute attrs;
        try {
            attrs = new Attribute(getDefaultId(), null, attrFactory.createValue(getDefaultType(), value));
        } catch (UnknownIdentifierException e) {
            logger.error("Could not create attribute for category " + getCategory() + ", default type " + getDefaultType() + " and value " + value);
            return null; //should not happen...
        }
        Set<Attribute> resourceSet = new HashSet<Attribute>();
        resourceSet.add(attrs);
        return new RequestElement(getCategory(), resourceSet);
    }



    public RequestElement getRequestElement(Set<Attribute> attrs) {
        return new RequestElement(getCategory(), attrs);
    }


    public Attribute getAttribute(URI attributeId, URI dataType, String value) throws ParsingException, UnknownIdentifierException {
        return new Attribute(attributeId != null ? attributeId : getDefaultId(),
                             null, attrFactory.createValue(dataType != null ? dataType : getDefaultType(), value));
    }

    public Attribute getAttributeDefaultType(String value) throws ParsingException {
        try {
            return new Attribute(getDefaultId(), null, attrFactory.createValue(getDefaultType(), value));
        } catch (UnknownIdentifierException e) {
            logger.error("Could not create attribute for category " + getCategory() + ", default type " + getDefaultType() + " and value " + value);
            return null; //should not happen...
        }
    }
}
