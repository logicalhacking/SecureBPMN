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

import eu.aniketos.securebpmn.xacml.support.Category;

import com.sun.xacml.Constants;
import com.sun.xacml.attr.AnyURIAttribute;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.RequestElement;

/**
 *
 * Creates a RequestElement, containing a resource for evaluation by the XACML PDP
 *
 *
 */
public class ResourceCreator extends ElementCreator {

    private static final URI resourceID_URI = Constants.RESOURCE_CAT; //Category.RESOURCE.getURI();
    private static final URI defaultId =  Constants.RESOURCE_ID; //Category.RESOURCE.getDefaultId();
    private static final URI default_Type = URI.create(AnyURIAttribute.identifier); //Category.RESOURCE.getDefaultType();


    @Override
    protected URI getCategory() {
        return resourceID_URI;
    }

    @Override
    protected URI getDefaultType() {
        return default_Type;
    }

    @Override
    protected URI getDefaultId() {
        return defaultId;
    }

    @Override
    public void setCategory(Category category) {
        if ( ! category.getURI().equals(resourceID_URI) ) {
            throw new RuntimeException("It is not possbile to set the category of " + ResourceCreator.class + " afterwards");
        }
    }


    public RequestElement getRequestElement(URI resource) {
        Attribute resourceAttribute = new Attribute(defaultId, null, new AnyURIAttribute(resource));
        Set<Attribute> resourceSet = new HashSet<Attribute>();
        resourceSet.add(resourceAttribute);
        return new RequestElement(resourceID_URI, resourceSet);
    }

    public Attribute getAttributeDefaultType(URI value) {
        return new Attribute(getDefaultId(), null, new AnyURIAttribute(value));
    }
}
