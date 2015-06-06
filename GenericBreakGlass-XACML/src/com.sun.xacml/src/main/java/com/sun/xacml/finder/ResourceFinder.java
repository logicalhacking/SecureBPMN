
/*
 * @(#)ResourceFinder.java
 *
 * Copyright 2003-2006 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistribution of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 * 
 *   2. Redistribution in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed or intended for use in
 * the design, construction, operation or maintenance of any nuclear facility.
 */

package com.sun.xacml.finder;

import com.sun.xacml.EvaluationCtx;

import com.sun.xacml.attr.AttributeValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * This class is used by the PDP to handle resource scopes other than
 * Immediate. In the case of a scope of Children, Descendants, 
 * XPath-expression or EntireHierarchy the PDP needs a list of Resource Ids
 * to evaluate, each of which will get its own Result. Like the PolicyFinder,
 * this is not tied in any way to the rest of the PDP code, and could be
 * provided as a stand-alone resource.
 * <p>
 * This class basically is a coordinator that asks each module in turn
 * if it can handle the given identifier. Evaluation proceeds in order through
 * the given modules, and once a module returns a non-empty response (whether
 * or not it contains any errors or only errors), the evaluation is
 * finished and the result is returned. One of the issues here is ordering,
 * since a given resource may look to several modules like something that
 * they can handle. So, you must be careful when assigning to ordering of
 * the modules in this finder.
 * <p>
 *
 * @since 1.0
 * @author Seth Proctor
 */
public class ResourceFinder
{

    /**
     * the list of all modules
     */
    private List<ResourceFinderModule> allModules;

    /**
     * the list of child modules
     */
    private List<ResourceFinderModule> childModules;

    /**
     * the list of descendant modules
     */
    private List<ResourceFinderModule> descendantModules;
    
    /**
     * the list of xpath modules
     */
    private List<ResourceFinderModule> xpathModules;
    
    /**
     * the list of hierarchy modules
     */
    private List<ResourceFinderModule> hierarchyModules;
    
    // the logger we'll use for all messages
    private static final Logger logger =
        Logger.getLogger(ResourceFinder.class.getName());

    /**
     * Default constructor.
     */
    public ResourceFinder() {
        this.allModules = new ArrayList<ResourceFinderModule>();
        this.childModules = new ArrayList<ResourceFinderModule>();
        this.descendantModules = new ArrayList<ResourceFinderModule>();
        this.xpathModules = new ArrayList<ResourceFinderModule>();
        this.hierarchyModules = new ArrayList<ResourceFinderModule>();
    }

    /**
     * Returns the ordered <code>List</code> of
     * <code>ResourceFinderModule</code>s used by this class to find resources.
     *
     * @return a <code>List</code> of <code>ResourceFinderModule</code>s
     */
    public List<ResourceFinderModule> getModules() {
        return new ArrayList<ResourceFinderModule>(this.allModules);
    }

    /**
     * Sets the ordered <code>List</code> of <code>ResourceFinderModule</code>s
     * used by this class to find resources. The ordering will be maintained.
     *
     * @param modules a code>List</code> of <code>ResourceFinderModule</code>s
     */
    public void setModules(List<ResourceFinderModule> modules) {
        Iterator<ResourceFinderModule> it = modules.iterator();

        this.allModules = new ArrayList<ResourceFinderModule>(modules);
        this.childModules = new ArrayList<ResourceFinderModule>();
        this.descendantModules = new ArrayList<ResourceFinderModule>();
        
        while (it.hasNext()) {
            ResourceFinderModule module = it.next();
            
            if (module.isChildSupported()) {
                this.childModules.add(module);
            }
            if (module.isDescendantSupported()) {
                this.descendantModules.add(module);
            }
            if (module.isXPathSupported()) {
                this.xpathModules.add(module);
            }
            if (module.isHierarchySupported()) {
                this.hierarchyModules.add(module);
            }
        }
    }

    /**
     * Finds Resource Ids using the Children scope, and returns all resolved
     * identifiers as well as any errors that occurred. If no modules can
     * handle the given Resource Id, then an empty result is returned.
     *
     * @param parentResourceId the root of the resources
     * @param context the representation of the request data
     *
     * @return the result of looking for child resources
     */
    public ResourceFinderResult findChildResources(AttributeValue
                                                   parentResourceId,
                                                   EvaluationCtx context) {
        Iterator<ResourceFinderModule> it = this.childModules.iterator();

        while (it.hasNext()) {
            ResourceFinderModule module = it.next();

            // ask the module to find the resources
            ResourceFinderResult result =
                module.findChildResources(parentResourceId, context);

            // if we found something, then always return that result
            if (! result.isEmpty()) {
                return result;
            }
        }

        // no modules applied, so we return an empty result
        if (logger.isInfoEnabled()) {
            logger.info("No ResourceFinderModule existed to handle the " +
                        "children of " + parentResourceId.encode());
        }

        return new ResourceFinderResult();
    }

    /**
     * Finds Resource Ids using the Descendants scope, and returns all resolved
     * identifiers as well as any errors that occurred. If no modules can
     * handle the given Resource Id, then an empty result is returned.
     *
     * @param parentResourceId the root of the resources
     * @param context the representation of the request data
     *
     * @return the result of looking for descendant resources
     */
    public ResourceFinderResult findDescendantResources(AttributeValue
                                                        parentResourceId,
                                                        EvaluationCtx
                                                        context) {
        Iterator<ResourceFinderModule> it = this.descendantModules.iterator();

        while (it.hasNext()) {
            ResourceFinderModule module = it.next();

            // ask the module to find the resources
            ResourceFinderResult result =
                module.findDescendantResources(parentResourceId, context);

            // if we found something, then always return that result
            if (! result.isEmpty()) {
                return result;
            }
        }

        // no modules applied, so we return an empty result
        if (logger.isInfoEnabled()) {
            logger.info("No ResourceFinderModule existed to handle the " +
                        "descendants of " + parentResourceId.encode());
        }        
        return new ResourceFinderResult();
    }
    
    /**
     * Finds Resource Ids using an XPath expression on the Content with the
     * "urn:oasis:names:tc:xacml:3.0:attribute-category:resource" category
     * and returns all matching nodes as identifiers as well as any errors 
     * that occurred. If no modules can handle the given Resource Id, then
     * an empty result is returned.
     *
     * @param xpathResourceId the root of the resources
     * @param context the representation of the request data
     *
     * @return the result of looking for descendant resources
     */
    public ResourceFinderResult findXPathResources(
            AttributeValue xpathResourceId, EvaluationCtx context) {
        Iterator<ResourceFinderModule> it = this.xpathModules.iterator();

        while (it.hasNext()) {
            ResourceFinderModule module = it.next();

            // ask the module to find the resources
            ResourceFinderResult result =
                module.findXPathResources(xpathResourceId, context);

            // if we found something, then always return that result
            if (! result.isEmpty()) {
                return result;
            }
        }

        // no modules returned results, so we return an empty result
        if (logger.isInfoEnabled()) {
            logger.info("No ResourceFinderModule returned results for the " 
                    + "xpath " + xpathResourceId.encode());
        }
        return new ResourceFinderResult();
    }

    /**
     * Finds Resource Ids using the EntireHierarchy scope and returns all
     * matching nodes as identifiers as well as any errors that occurred. 
     * If no modules can handle the given Resource Id, then an empty result 
     * is returned.
     *
     * @param parentResourceId the root of the resources
     * @param context the representation of the request data
     *
     * @return the result of looking for descendant resources
     */
    public ResourceFinderResult findHierarchyResources(
            AttributeValue parentResourceId, EvaluationCtx context) {
        Iterator<ResourceFinderModule> it = this.hierarchyModules.iterator();

        while (it.hasNext()) {
            ResourceFinderModule module = it.next();

            // ask the module to find the resources
            ResourceFinderResult result =
                module.findHierarchicyResources(parentResourceId, context);

            // if we found something, then always return that result
            if (! result.isEmpty()) {
                return result;
            }
        }

        // no modules applied, so we return an empty result
        if (logger.isInfoEnabled()) {
            logger.info("No ResourceFinderModule existed to handle the " +
                        "hierarchy of " + parentResourceId.encode());
        }        
        return new ResourceFinderResult();
    }
}
