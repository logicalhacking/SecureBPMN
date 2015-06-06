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

import eu.aniketos.securebpmn.xacml.support.Category;

public class DefaultElementCreator extends ElementCreator {

    private Category category;
    private URI categoryURI, defaultId, defaultType;

    public DefaultElementCreator(Category category) {
        setCategory(category);
    }

    public DefaultElementCreator() {
        ;
    }

    public void setCategory(Category category) {
        this.category = category;
        this.categoryURI = category.getURI();
        this.defaultId = category.getDefaultId();
        this.defaultType = category.getDefaultType();
    }

    @Override
    protected URI getCategory() {
        return categoryURI;
    }

    @Override
    protected URI getDefaultType() {
        return defaultType;
    }

    @Override
    protected URI getDefaultId() {
        return defaultId;
    }

    protected Category getCategoryObject() {
        return this.category;
    }


}
