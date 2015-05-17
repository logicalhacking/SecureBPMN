
/*
 * @(#)AttributeDesignator.java
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

package com.sun.xacml.attr;

import com.sun.xacml.Constants;
import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.Indenter;
import com.sun.xacml.ParsingException;
import com.sun.xacml.PolicyMetaData;

import com.sun.xacml.cond.Evaluatable;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.cond.Expression;

import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.Status;
import com.sun.xacml.ctx.StatusDetail;
import com.sun.xacml.debug.RuntimeInfo;
import com.sun.xacml.debug.RuntimeInfo.ELEMENT_TYPE;
import com.sun.xacml.finder.RequiredAttributesModule;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;


/**
 * Represents Attribute Designators in XACML.
 *
 * @since 1.0
 * @author Seth Proctor
 * @author Ludwig Seitz
 */
public class AttributeDesignator implements Evaluatable
{
	
    /**
     * The category of the Designator.
     */
    private URI category;
    
    /**
     * The datatype resolved by this designator
     */
    private URI type;
    
    /**
     * The attribute id resolved by this designator
     */
    private URI id;

    /**
     * The optional issuer attribute
     */
    private URI issuer;

    /**
     * Throw an error if the resolution doesn't find something
     */
    private boolean mustBePresent;

    /**
     * The XACML version
     */
    private int xacmlVersion;
    
    /**
     * Backwards compatibility switch that indicates if the
     * SubjectCategory was explicit or default.
     */
    private boolean withSubjectCategory = false;
    
    /**
     * Information where the Attribute Designator has been defined in src (files)
     */
    private RuntimeInfo src = null;
    
    /**
     * the logger we'll use for all messages
     */
    private static final Logger logger =
        Logger.getLogger(AttributeDesignator.class.getName());
    
    private static ArrayList<RequiredAttributesModule> requiredAttrModules;
    
    public static void setRequiredAttrModules(ArrayList<RequiredAttributesModule> requiredAttrModules) {
    	AttributeDesignator.requiredAttrModules = requiredAttrModules;
    }

    /**
     * Creates a new <code>AttributeDesignator</code>.
     *
     * @param category  the category of this designator           
     * @param type the data type resolved by this designator
     * @param id the attribute id looked for by this designator
     * @param mustBePresent whether resolution must find a value 
     * @param issuer the issuer of the values to search for or null if no
     *               issuer is specified
     *
     */
    public AttributeDesignator(URI category, URI type, URI id,
            boolean mustBePresent, URI issuer)  {
        this(category, type, id, mustBePresent, issuer, 
                Constants.XACML_DEFAULT_VERSION, false);
    }
        
    /**
     * Creates a new <code>AttributeDesignator</code>.
     *
     * @param category  the category of this designator           
     * @param type the data type resolved by this designator
     * @param id the attribute id looked for by this designator
     * @param mustBePresent whether resolution must find a value 
     * @param issuer the issuer of the values to search for or null if no
     *               issuer is specified
     * @param xacmlVersion  the XACML version that is used.
     * @param withSubjectCategory  Backwards compatibility switch that
     *                             indicates if the SubjectCategory is 
     *                             explicit or default.
     *
     */
    public AttributeDesignator(URI category, URI type, URI id,
            boolean mustBePresent, URI issuer, int xacmlVersion,
            boolean withSubjectCategory)  {
        
        this.category = category;
        this.type = type;
        this.id = id;
        this.mustBePresent = mustBePresent;
        this.issuer = issuer;
        this.xacmlVersion = xacmlVersion;
        this.withSubjectCategory = withSubjectCategory;
    }
    

    
    /**
     * Creates a new <code>AttributeDesignator</code> based on the DOM
     * root of the XML data.
     * 
     * @param root  the DOM root of the AttributeDesignatorType XML type
     * @param metaData  the meta-data associated with the containing policy
     *
     * @return the designator
     *
     * @throws ParsingException if the AttributeDesignatorType was invalid
     */
    public static AttributeDesignator getInstance(Node root,
    		PolicyMetaData metaData) throws ParsingException {
    	URI category = null;
        URI type = null;
        URI id = null;
        URI issuer = null;
        boolean mustBePresent = false;
        int xacmlVersion = metaData.getXACMLVersion();
        boolean withSubjectCategory = false;
        
        RuntimeInfo src = RuntimeInfo.getRuntimeInfo(root, ELEMENT_TYPE.ATTRIBUTE_DESIGNATOR);

        NamedNodeMap attrs = root.getAttributes();
        if (root.getNodeType() != Node.ELEMENT_NODE) {
            throw new ParsingException("Cannot build a AttributeDesignator" 
                    + " with this node type: " + root.getClass().getName()
                    + (src != null ? src.getLocationMsgForError() : ""));
        }
        
        if (root.getLocalName().equals("SubjectAttributeDesignator")) {
            if (xacmlVersion > Constants.XACML_VERSION_2_0) {
                throw new ParsingException("Can't create a > XACML 2.0" 
                        + "AttributeDesignator from " + root.getLocalName() 
                        + (src != null ? src.getLocationMsgForError() : ""));
            }
            //compatibility code for XACML 2.0
            category = Constants.SUBJECT_CAT;
            if (attrs != null) {
                Node catNode = attrs.getNamedItem("SubjectCategory");
                if (catNode != null) {
                    try {
                        category = new URI(catNode.getNodeValue());
                    } catch (URISyntaxException e) {
                        throw new ParsingException("Error while parsing" 
                                + "category: " + catNode.getNodeValue()
                                + (src != null ? src.getLocationMsgForError() : ""));
                    }
                    withSubjectCategory = true;
                }
            }
        } else if (root.getLocalName().equals("ResourceAttributeDesignator")) {
            if (xacmlVersion > Constants.XACML_VERSION_2_0) {
                throw new ParsingException("Can't create an > XACML 2.0 " 
                        + "AttributeDesignator from " + root.getLocalName()
                        + src.getLocationMsgForError());
            }
            category = Constants.RESOURCE_CAT;
        } else if (root.getLocalName().equals("ActionAttributeDesignator")) {
            if (xacmlVersion > Constants.XACML_VERSION_2_0) {
                throw new ParsingException("Can't create an > XACML 2.0 " 
                        + "AttributeDesignator from " + root.getLocalName()
                        + (src != null ? src.getLocationMsgForError() : ""));
            }
            category = Constants.ACTION_CAT;
        } else if (root.getLocalName().equals(
                "EnvironmentAttributeDesignator")) {
            if (xacmlVersion !=  Constants.XACML_VERSION_2_0) {
                throw new ParsingException("Can't create an XACML 2.0 " 
                        + "AttributeDesignator from " + root.getLocalName()
                        + src.getLocationMsgForError());
            }
            category = Constants.ENVIRONMENT_CAT;
        } else {
            if (xacmlVersion < Constants.XACML_VERSION_3_0) {
                throw new ParsingException("Can't create an XACML " 
                        + xacmlVersion + " AttributeDesignator from " 
                        + root.getLocalName()
                        + (src != null ? src.getLocationMsgForError() : ""));
            }
            // there's always a category
            try {
                category = new URI(
                        attrs.getNamedItem("Category").getNodeValue());
            } catch (Exception e) {
                throw new ParsingException("Required Category missing or" 
                        + " invalid in AttributeDesignator"
                        + (src != null ? src.getLocationMsgForError() : ""), e);
            }
        }
        try {
            // there's always an Id
            id = new URI(attrs.getNamedItem("AttributeId").getNodeValue());
        } catch (Exception e) {
            throw new ParsingException("Required AttributeId missing or"
                    + " invalid in AttributeDesignator"
                    + src.getLocationMsgForError(), e);
        }
        
        try {
            // there's always a data type
            type = new URI(attrs.getNamedItem("DataType").getNodeValue());
        } catch (Exception e) {
            throw new ParsingException("Required DataType missing or"
                    + " invalid in AttributeDesignator"
                    + (src != null ? src.getLocationMsgForError() : ""), e);
        }
        
        try {
            // there might be an issuer
            Node node = attrs.getNamedItem("Issuer");
            if (node != null) {
                issuer = new URI(node.getNodeValue());
            }
            // there might be a mustBePresent flag
            node = attrs.getNamedItem("MustBePresent");
            if (node != null) {
                if (node.getNodeValue().equals("true")) {
                    mustBePresent = true;
                }
            }
        } catch (Exception e) {
            // this shouldn't ever happen, but in theory something could go
            // wrong in the code in this try block
            throw new ParsingException("Error parsing AttributeDesignator " +
                                       "optional attributes"
            		 				   + (src != null ? src.getLocationMsgForError() : ""), e);
        }
        
        AttributeDesignator attrDesgn = new AttributeDesignator(category, type, 
        		id, mustBePresent, issuer, xacmlVersion, withSubjectCategory);
        if ( src != null ) {
            attrDesgn.src = src;
            src.setXACMLObject(attrDesgn);
        }
        return attrDesgn;
    }

    /**
     * Returns the type of attribute that is resolved by this designator.
     * While an AD will always return a bag, this method will always return
     * the type that is stored in the bag.
     *
     * @return the attribute type
     */
    public URI getType() {
        return this.type;
    }

    /**
     * Returns the AttributeId of the values resolved by this designator.
     *
     * @return identifier for the values to resolve
     */
    public URI getId() {
        return this.id;
    }

    /**
     * Returns the category for this designator. 
     *
     * @return the category
     */
    public URI getCategory() {
        return this.category;
    }

    /**
     * Returns the issuer of the values resolved by this designator if
     * specified.
     *
     * @return the attribute issuer or null if unspecified
     */
    public URI getIssuer() {
        return this.issuer;
    }

    /**
     * Returns whether or not a value is required to be resolved by this
     * designator.
     *
     * @return true if a value is required, false otherwise
     */
    public boolean mustBePresent() {
        return this.mustBePresent;
    }

    /**
     * Always returns true, since a designator always returns a bag of
     * attribute values.
     *
     * @return true
     */
    public boolean returnsBag() {
        return true;
    }

    /**
     * Always returns an empty list since designators never have children.
     *
     * @return an empty <code>List</code>
     */
    public List<Expression> getChildren() {
        return Expression.EMPTY_LIST; //Collections.<Expression>emptyList();
    }
    
    public RuntimeInfo getRuntimeInfo() {
    	return this.src;
    }
	
    /**
     * Evaluates the pre-assigned meta-data against the given context,
     * trying to find some matching values.
     *
     * @param context the representation of the request
     *
     * @return a result containing a bag either empty because no values were
     * found or containing at least one value, or status associated with an
     * Indeterminate result
     */
    public EvaluationResult evaluate(EvaluationCtx context) {
        context.newEvent(this);
        EvaluationResult result = null;

        // look in the right section for some attribute values
        result = context.getAttribute(this.category, this.type, this.id, 
                                this.issuer);
        
        // if the lookup was indeterminate, then we return immediately
        if (result.indeterminate()) {
            context.closeCurrentEvent(result);
            return result;
        }

        BagAttribute bag = (BagAttribute)(result.getAttributeValue());

        if (bag.isEmpty()) {
            // if it's empty, this may be an error
            if (this.mustBePresent) {
                if (logger.isInfoEnabled()) {
                    logger.info("AttributeDesignator failed to resolve a " 
                            + "value for a required attribute: " 
                            + this.id.toString() + " (MustBePresent: " + 
                            mustBePresent + ")");
                }

                ArrayList<String> code = new ArrayList<String>();
                code.add(Status.STATUS_MISSING_ATTRIBUTE);
                
                String message = "Couldn't find " 
                	+ "AttributeDesignator attribute";

                // Note that there is a bug in the XACML spec. You can't
                // specify an identifier without specifying acceptable
                // values. Until this is fixed, this code will only
                // return the status code, and not any hints about what
                // was missing
                Set<Attribute> attr = null;
                for ( RequiredAttributesModule requAttrMod : requiredAttrModules ) {
                	attr = requAttrMod.resolveRequiredAttributes(context, this);
                	
                	if ( attr != null && attr.size() > 0 ) {
                		break;
                	}
                }
                
                Status status;
                if ( attr == null ) {
                	status = new Status(code, message);
                } else {
                	status = new Status(code, message, new StatusDetail(attr));
                }
                
                EvaluationResult evalResult = new EvaluationResult(status);
                context.closeCurrentEvent(evalResult);
                return evalResult;
            }
        }

        // if we got here the bag wasn't empty, or mustBePresent was false,
        // so we just return the result
        context.closeCurrentEvent(result);
        return result;
    }

    /**
     * Encodes this designator into its XML representation and
     * writes this encoding to the given <code>OutputStream</code> with no
     * indentation.
     *
     * @param output  a stream into which the XML-encoded data is written
     * @param charsetName  the character set to use in encoding of strings.
     *                     This may be null in which case the platform
     *                     default character set will be used.
     *  
     * @throws UnsupportedEncodingException 
     */
    public void encode(OutputStream output, String charsetName)
            throws UnsupportedEncodingException {
        encode(output, charsetName, new Indenter(0));
    }

    /**
     * Encodes this designator into its XML representation and
     * writes this encoding to the given <code>OutputStream</code> with
     * indentation.
     *
     * @param output  a stream into which the XML-encoded data is written
     * @param charsetName  the character set to use in encoding of strings.
     *                     This may be null in which case the platform
     *                     default character set will be used.
     * @param indenter  an object that creates indentation strings
     * 
     * @throws UnsupportedEncodingException 
     */
    public void encode(OutputStream output, String charsetName,
                       Indenter indenter) 
            throws UnsupportedEncodingException {
        PrintStream out;
        if(charsetName == null) {
            out = new PrintStream(output);
        } else {
            out = new PrintStream(output, false, charsetName);
        }
        String indent = indenter.makeString();

        String tag = "";

        if (this.xacmlVersion > Constants.XACML_VERSION_2_0) {
            tag = "<AttributeDesignator Category=\"" + this.category.toString()
            + "\" ";
        } else {
            if (this.category.equals(Constants.RESOURCE_CAT)) {
                tag = "<ResourceAttributeDesignator ";
            } else if (this.category.equals(Constants.ACTION_CAT)) {
               tag = "<ActionAttributeDesignator ";
            } else if (this.xacmlVersion == Constants.XACML_VERSION_2_0
                    && this.category.equals(Constants.ENVIRONMENT_CAT)) {
                tag = "<EnvironmentAttributeDesignator ";
            } else {
                //For backwards compatibility all unknown categories are
                //cast to subject categories.
                if (this.withSubjectCategory) {
                    tag = "<SubjectAttributeDesignator SubjectCategory=\""
                        + this.category.toString() + "\" ";
                    
                } else {
                    tag = "<SubjectAttributeDesignator ";
                }
            }
        }
            
        tag += "AttributeId=\"" + this.id.toString() + "\" DataType=\"" 
        	+ this.type.toString() + "\"";

        if (this.issuer != null) {
            tag += " Issuer=\"" + this.issuer.toString() + "\"";
        }

        if (this.mustBePresent) {
            tag += " MustBePresent=\"true\"";
        }

        tag += "/>";

        out.println(indent + tag);
    }


}
