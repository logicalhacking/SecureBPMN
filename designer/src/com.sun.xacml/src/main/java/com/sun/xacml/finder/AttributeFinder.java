
/*
 * @(#)AttributeFinder.java
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

import com.sun.xacml.attr.BagAttribute;

import com.sun.xacml.cond.EvaluationResult;

import java.net.URI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import org.w3c.dom.Node;


/**
 * This class is used by the PDP to find attribute values that weren't
 * originally supplied in the request. It can be called with the data supplied
 * in <code>AttributeDesignator<code>s or <code>AttributeSelector</code>s.
 * Because the modules in this finder may themselves need attribute data
 * to search for attribute data, it's possible that the modules will look
 * for values in the <code>EvaluationCtx</code>, which may in turn result
 * in the invocation of this finder again, so module writers need to be
 * careful about how they build their modules.
 * <p>
 * Note that unlike the PolicyFinder, this class doesn't always need to
 * use every module it has to find a value. The ordering is maintained,
 * however, so it will always start with the first module, and proceed
 * in order until it finds a value or runs out of modules.
 *
 * @since 1.0
 * @author Seth Proctor
 * @author Ludwig Seitz
 */
public class AttributeFinder implements Cloneable
{

    // the list of all modules
    private List<AttributeFinderModule> allModules;

    //
    private List<AttributeFinderModule> designatorModules;

    //
    private List<AttributeFinderModule> selectorModules;

    // the logger we'll use for all messages
    private static final Logger logger =
        Logger.getLogger(AttributeFinder.class.getName());

    /**
     * Default constructor.
     */
    public AttributeFinder() {
        this.allModules = new ArrayList<AttributeFinderModule>();
        this.designatorModules = new ArrayList<AttributeFinderModule>();
        this.selectorModules = new ArrayList<AttributeFinderModule>();
    }

    
    /**
     * The clone method. 
     * FIXME: this does no deep copy on the Lists.
     * 
     * @return  a copy of this object.
     */
    public Object clone() {
        try {
            AttributeFinder clone = (AttributeFinder)super.clone();
            clone.allModules = new ArrayList<AttributeFinderModule>(this.allModules);
            clone.designatorModules = new ArrayList<AttributeFinderModule>(this.designatorModules);
            clone.selectorModules = new ArrayList<AttributeFinderModule>(this.selectorModules);
            return clone; 
        } catch (CloneNotSupportedException e) {//this should never happen
            throw new RuntimeException("Couldn't clone AttributeFinder");
        } 
    }
    
    /** 
     * copy constructor
     * @param attrFinder
     */
    protected AttributeFinder(AttributeFinder attrFinder) {
    	this.allModules = attrFinder.allModules;
    	this.designatorModules = attrFinder.designatorModules;
    	this.selectorModules = attrFinder.selectorModules;
    }
    
    /**
     * Returns the ordered <code>List</code> of
     * <code>AttributeFinderModule</code>s used by this class to find
     * attribute values.
     *
     * @return a <code>List</code> of <code>AttributeFinderModule</code>s
     */
    public List<AttributeFinderModule> getModules() {
        return new ArrayList<AttributeFinderModule>(this.allModules);
    }

    /**
     * Sets the ordered <code>List</code> of
     * <code>AttributeFinderModule</code>s used by this class to find
     * attribute values. The ordering will be maintained.
     *
     * @param modules a <code>List</code> of
     *                <code>AttributeFinderModule</code>s
     */
    public void setModules(List<AttributeFinderModule> modules) {
        Iterator<AttributeFinderModule> it = modules.iterator();

        this.allModules = new ArrayList<AttributeFinderModule>(modules);
        this.designatorModules = new ArrayList<AttributeFinderModule>();
        this.selectorModules = new ArrayList<AttributeFinderModule>();

        while (it.hasNext()) {
            AttributeFinderModule module = it.next();
            
            if (module.isDesignatorSupported()) {
                this.designatorModules.add(module);
            }
            if (module.isSelectorSupported()) {
                this.selectorModules.add(module);
            }
        }
    }
    
    /**
     * Sets only the designatorModules
     * @param modules
     */
    public void setDesignatorModules(List<AttributeFinderModule> modules) {
    	
    	for ( AttributeFinderModule mod : modules) {
    		if ( mod.isDesignatorSupported() ) {
    			logger.warn(mod + " does not support designator selection!");
    		}
    	}
    	
    	this.designatorModules = modules;
    }

    /**
     * Tries to find attribute values based on the given designator data.
     * The result, if successful, will always contain a
     * <code>BagAttribute</code>, even if only one value was found. If no
     * values were found, but no other error occurred, an empty bag is
     * returned.
     * 
     * @param category  the category of the attribute
     * @param attributeType the datatype of the attributes to find or null
     * @param attributeId the identifier of the attributes to find or null
     * @param issuer the issuer of the attributes, or null if unspecified
     * @param context the representation of the request data
     *
     * @return the result of attribute retrieval, which will be a bag of
     *         attributes or an error
     */
    public EvaluationResult findAttribute(URI category, URI attributeType,
    									   URI attributeId, URI issuer,
                                          EvaluationCtx context) {
        Iterator<AttributeFinderModule> it = this.designatorModules.iterator();

        // go through each module in order
        while (it.hasNext()) {
            AttributeFinderModule module = it.next();
            
            // see if the module can find an attribute value
            EvaluationResult result =
                module.findAttribute(category, attributeType, attributeId, 
                					 issuer, context);
            
            // if there was an error, we stop right away
            if (result.indeterminate()) {
                if (logger.isInfoEnabled()) {
                    logger.info("Error while trying to resolve values: " +
                            result.getStatus().getMessage());
                }

                return result;
            }
            
            // if the result wasn't empty, then return the result
            BagAttribute bag = (BagAttribute)(result.getAttributeValue());
            if (! bag.isEmpty()) {
                return result;
            }
        }
        
        // if we got here then there were no errors but there were also no
        // matches, so we have to return an empty bag
        if (logger.isDebugEnabled()) {
            logger.debug("Failed to resolve any values for " +
                        attributeId.toString());

        }        

        return new EvaluationResult(BagAttribute.
                createEmptyBag(attributeType));
    }

    /**
     * Tries to find attribute values based on the given selector data.
     * The result, if successful, must always contain a
     * <code>BagAttribute</code>, even if only one value was found. If no
     * values were found, but no other error occurred, an empty bag is
     * returned.
     *
     * @param contextPath the XPath expression to search against
     * @param namespaceNode the DOM node defining namespace mappings to use,
     *                      or null if mappings come from the context root
     * @param attributeType the datatype of the attributes to find
     * @param context the representation of the request data
     * @param xpathVersion the XPath version to use
     *
     * @return the result of attribute retrieval, which will be a bag of
     *         attributes or an error
     */
    public EvaluationResult findAttribute(String contextPath,
                                          Node namespaceNode,
                                          URI attributeType,
                                          EvaluationCtx context,
                                          String xpathVersion) {
        Iterator<AttributeFinderModule> it = this.selectorModules.iterator();

        // go through each module in order
        while (it.hasNext()) {
            AttributeFinderModule module = it.next();
            
            // see if the module can find an attribute value
            EvaluationResult result =
                module.findAttribute(contextPath, namespaceNode, attributeType,
                                     context, xpathVersion);

            // if there was an error, we stop right away
            if (result.indeterminate()) {
                if (logger.isInfoEnabled()) {
                    logger.info("Error while trying to resolve values: " +
                                result.getStatus().getMessage());
                }
                return result;
            }

            // if the result wasn't empty, then return the result
            BagAttribute bag = (BagAttribute)(result.getAttributeValue());
            if (! bag.isEmpty()) {
                return result;
            }
        }

        // if we got here then there were no errors but there were also no
        // matches, so we have to return an empty bag
        if (logger.isInfoEnabled()) {
            logger.info("Failed to resolve any values for " + contextPath);
        }
        
        return new EvaluationResult(BagAttribute.
                                    createEmptyBag(attributeType));
    }

}
