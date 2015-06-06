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

package eu.aniketos.securebpmn.xacml.support;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import eu.aniketos.securebpmn.xacml.support.helper.*;

import com.sun.xacml.Constants;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.attr.AnyURIAttribute;
import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.RequestElement;

/**
 *
 * Describes the categories known by xacml as enum
 *
 */
public enum Category implements IElementCreator {
    SUBJECT (Constants.SUBJECT_CAT, Constants.SUBJECT_ID, StringAttribute.identifier, new DefaultElementCreator()),
    RESOURCE (Constants.RESOURCE_CAT, Constants.RESOURCE_ID, AnyURIAttribute.identifier, new ResourceCreator()),
    ACTION (Constants.ACTION_CAT, Constants.ACTION_ID, StringAttribute.identifier, new DefaultElementCreator()),
    ENVIRONMENT (Constants.ENVIRONMENT_CAT, null, StringAttribute.identifier, new DefaultElementCreator());

    private URI uri, defaultId, defaultType;
    private ElementCreator creator;

    private static Map<URI, Category> known_categories;

    static {
        known_categories = new HashMap<URI, Category>();

        for ( Category cat : Category.values()) {
            known_categories.put(cat.getURI(), cat);
        }
    }

    Category(URI uri, URI defaultId, String defaultType, ElementCreator creator) {
        this.uri = uri;
        this.defaultId = defaultId;
        this.defaultType = URI.create(defaultType);
        this.creator = creator;
        creator.setCategory(this);
    }

    public URI getURI() {
        return uri;
    }

    public URI getDefaultId() {
        return defaultId;
    }

    public URI getDefaultType() {
        return defaultType;
    }

    public static Category getCategory(URI category) {
        return known_categories.get(category);
    }

    public ElementCreator getElementCreator() {
        return creator;
    }


    public Attribute getAttributeDefaultType(String value)
    throws ParsingException {
        return creator.getAttributeDefaultType(value);
    }

    public RequestElement getRequestElement(String value)
    throws ParsingException {
        return creator.getRequestElement(value);
    }

    public Attribute getAttribute(URI attributeId, URI dataType, String value)
    throws ParsingException, UnknownIdentifierException {
        return creator.getAttribute(attributeId, dataType, value);
    }

    public RequestElement getRequestElement(Set<Attribute> attrs) {
        return creator.getRequestElement(attrs);
    }
}
