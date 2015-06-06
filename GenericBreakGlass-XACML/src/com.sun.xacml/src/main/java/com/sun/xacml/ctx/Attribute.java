
/*
 * @(#)Attribute.java
 *
 * Copyright 2003-2004 Sun Microsystems, Inc. All Rights Reserved.
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

package com.sun.xacml.ctx;

import com.sun.xacml.Indenter;

import com.sun.xacml.Constants;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.PolicyMetaData;

import com.sun.xacml.attr.AttributeFactory;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.StandardAttributeFactory;

import java.io.PrintStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Represents the AttributeType XML type found in the context schema.
 *
 * @since 1.0
 * @author Seth Proctor
 */
public class Attribute implements Cloneable
{

    /**
     * The required attribute id,
     */
    protected URI id;

    // optional issuer attribute
    private String issuer = null;
    
    // the single value associated with this attribute
    private AttributeValue value;
    
    // the XACML version of this attribute
    private int xacmlVersion = Constants.XACML_DEFAULT_VERSION;
    
    // a switch indicatig whether to include this attribute in the
    // result status.
    private boolean includeInResult = false;

    /**
     * Creates a new <code>Attribute</code> of the type specified in the
     * given <code>AttributeValue</code>.
     *
     * @param id the id of the attribute
     * @param issuer the attribute's issuer or null if there is none
     * @param value the actual value associated with the attribute meta-data,
     *  `           must not be null.
     */
    public Attribute(URI id, String issuer, AttributeValue value) {
       this(id, issuer, value, Constants.XACML_DEFAULT_VERSION, false);
    }
    
    /**
     * Creates a new <code>Attribute</code> of the type specified in the
     * given <code>AttributeValue</code>.
     *
     * @param id the id of the attribute
     * @param issuer the attribute's issuer or null if there is none
     * @param value the actual value associated with the attribute meta-data,
     *             must not be null.
     * @param xacmlVersion  The XACML version number for compatibility code.
     */
    public Attribute(URI id, String issuer,
                     AttributeValue value, int xacmlVersion) {
        this(id, issuer, value, xacmlVersion, false);
    }
    
    /**
     * Creates a new <code>Attribute</code> of the type specified in the
     * given <code>AttributeValue</code>.
     *
     * @param id the id of the attribute
     * @param issuer the attribute's issuer or null if there is none
     * @param value the actual value associated with the attribute meta-data,
     *             must not be null.
     * @param xacmlVersion  The XACML version number for compatibility code.
     * @param includeInResult  A switch indicating that this attribute
     *                         should be included in the result.
     */
    public Attribute(URI id, String issuer,
                     AttributeValue value, int xacmlVersion, 
                     boolean includeInResult) {
        this.id = id;
        this.issuer = issuer;
        this.value = value;
        this.xacmlVersion = xacmlVersion;
        this.includeInResult = includeInResult;
    }
    
    /**
     * The clone method.
     * 
     * @return  a copy if this object.
     */
    public Object clone() {
        try {
            Attribute clone = (Attribute)super.clone();
            clone.id = this.id;
            clone.issuer = this.issuer;
            
            StandardAttributeFactory fac 
                = StandardAttributeFactory.getFactory();
            try {
                clone.value = fac.createValue(this.value.getType(), 
                                              this.value.encode());
            } catch (UnknownIdentifierException e) {
                throw new RuntimeException("Impossible exception");
            } catch (ParsingException e) {
                throw new RuntimeException("Impossible exception");
            }

            clone.xacmlVersion = this.xacmlVersion;
            clone.includeInResult = this.includeInResult;
            return clone;
        } catch (CloneNotSupportedException e1) {//this should never happen
            throw new RuntimeException("Couldn't clone Attribute");
        }
    }

    /**
     * Creates an instances of <code>Attribute</code>s based on the root DOM
     * node of the XML data. This method returns a list of attributes since
     * the XACML syntax allows multi valued attributes in a single
     * AttributeType XML type.
     *
     * @param root the DOM root of the AttributeType XML type
     *
     * @return a list of attributes
     *
     *@throws ParsingException if the data is invalid
     */
    public static List<Attribute> getInstances(Node root)
    throws ParsingException {
        // check if this really is an attribute
        if (root.getNodeType() != Node.ELEMENT_NODE
                || !root.getLocalName().equals("Attribute")) {
            throw new ParsingException("Can't create an Attribute from a "
                    + root.getLocalName() + " element");
        }
        URI id = null;
        URI type = null;
        String issuer = null;       
        boolean includeInResult = false;
        List<Attribute> result = new LinkedList<Attribute>();

        AttributeFactory attrFactory = AttributeFactory.getInstance();

        // Now get the xacml version
        PolicyMetaData metaData = new PolicyMetaData(root.getNamespaceURI(), 
                Constants.XPATH_1_0_IDENTIFIER);
        
        NamedNodeMap attrs = root.getAttributes();
        
        try {
            id = new URI(attrs.getNamedItem("AttributeId").getNodeValue());
        } catch (Exception e) {
            throw new ParsingException("Error parsing required attribute " +
                                       "AttributeId in AttributeType", e);
        }
        
        if (metaData.getXACMLVersion() < Constants.XACML_VERSION_3_0) {
            try {
                type = new URI(attrs.getNamedItem("DataType").getNodeValue());
            } catch (Exception e) {
                throw new ParsingException("Error parsing required attribute " +
                                           "DataType in AttributeType", e);
            }            
        }
        
        try {
            Node issuerNode = attrs.getNamedItem("Issuer");
            if (issuerNode != null) {
                issuer = issuerNode.getNodeValue();
            }
        } catch (Exception e) {
            // shouldn't happen, but just in case...
            throw new ParsingException("Error parsing optional AttributeType"
                                       + " attribute", e);
        }

        Node includeNode = attrs.getNamedItem("includeInResult");
        if (includeNode != null) {
            if (includeNode.getNodeValue().equals("true")) {
                includeInResult = true; 
            } else if (includeNode.getNodeValue().equals("false")){
                includeInResult = false;
            } else {
                throw new ParsingException("Error parsing boolean value" 
                        + " includeInResult: " 
                        + includeNode.getNodeValue());
            }
        }

        // now we get the attribute value
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE
                    && node.getLocalName().equals("AttributeValue")) {
                // get the type
                if (metaData.getXACMLVersion() > 
                        Constants.XACML_VERSION_2_0) {
                    Node typeNode = node.getAttributes()
                                        .getNamedItem("DataType");
                    if (typeNode == null) {
                        throw new ParsingException("No DataType xml-attribute "
                                + "found in AttributeValue");
                    }
                    String typeStr = typeNode.getNodeValue();
                    try {
                        type = new URI(typeStr);
                    } catch (Exception e) {
                        throw new ParsingException("Error parsing required " 
                                + "attribute DataType in AttributeValue", e);
                    }
                }
                
                // now get the value
                try {
                    AttributeValue value = attrFactory.createValue(node, type);
                    Attribute attr = new Attribute(id, issuer, value, 
                            metaData.getXACMLVersion(), includeInResult);
                    result.add(attr);
                } catch (UnknownIdentifierException uie) {
                    throw new ParsingException("Unknown DataType", uie);
                }
            }
        }
        if(result.isEmpty())
            throw new ParsingException("Attribute must contain a value");

        return result;
    }

    /**
     * Returns the id of this attribute
     *
     * @return the attribute id
     */
    public URI getId() {
        return this.id;
    }
    
    /**
     * Returns the issuer of this attribute, or null if no issuer was named
     * 
     * @return the issuer or null
     */
    public String getIssuer() {
        return this.issuer;
    }
   
    /**
     * The value of this attribute, or null if no value was included
     *
     * @return the attribute's value or null
     */
    public AttributeValue getValue() {
        return this.value;
    }
    
    /**
     * Returns the xacml version number for this attribute.
     * 
     * @return  The xacml version number as defined in
     *          <code>PolicyMetaData</code>.
     */
    public int getVersion() {
        return this.xacmlVersion;
    }
    
    /**
     * @return  True if this attribute should be included in the result.
     */
    public boolean includeInResult() {
        return this.includeInResult;
    }
    
    /**
     * Encodes this attribute into its XML representation and writes
     * this encoding to the given <code>OutputStream</code> with no
     * indentation.
     *
     * @param output a stream into which the XML-encoded data is written
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
     * Encodes this attribute into its XML representation and writes
     * this encoding to the given <code>OutputStream</code> with
     * indentation.
     *
     * @param output a stream into which the XML-encoded data is written
     * @param charsetName  the character set to use in encoding of strings.
     *                     This may be null in which case the platform
     *                     default character set will be used.
     * @param indenter an object that creates indentation strings
     * @throws UnsupportedEncodingException 
     */
    public void encode(OutputStream output, String charsetName,
                       Indenter indenter) 
            throws UnsupportedEncodingException {
        // setup the formatting & outstream stuff
        PrintStream out;
        if(charsetName == null) {
            out = new PrintStream(output);
        } else {
            out = new PrintStream(output, false, charsetName);
        }

        // write out the encoded form
        out.println(encode(indenter));
    }
    
    /**
     * Encoding method that returns the text-encoded version of
     * this attribute with formatting.
     *
     * @return the text-encoded XML
     */
    public String encode(Indenter indenter) {
        String indent = indenter.makeString();
        indenter.in();
        String innerIndent = indenter.makeString();
        indenter.out();
        String encoded = indent 
            + "<Attribute AttributeId=\"" + this.id.toString() + "\""; 
            
        if (this.xacmlVersion < Constants.XACML_VERSION_3_0) {
            encoded += " DataType=\"" + this.value.getType().toString() + "\"";
        }

        if (this.issuer != null) {
            encoded += " Issuer=\"" + this.issuer + "\"";
        }
        
        if (this.xacmlVersion >= Constants.XACML_VERSION_3_0
                && this.includeInResult == true) {
            encoded += " includeInResult=\"true\"";
        }
        encoded += ">" + Constants.nl; 
        
        if (this.xacmlVersion < Constants.XACML_VERSION_3_0) {
            encoded += innerIndent +
                this.value.encodeWithTags(false) + Constants.nl
                + indent + "</Attribute>";
            
        } else {
            encoded += innerIndent + 
                this.value.encodeWithTags(true) + Constants.nl
                + indent + "</Attribute>";
        }

        return encoded;
    }
    
    
    /**
     * Simple encoding method that returns the text-encoded version of
     * this attribute with no formatting.
     *
     * @return the text-encoded XML
     */
    public String encode() {
        String encoded = "<Attribute AttributeId=\"" + this.id.toString() 
            + "\""; 
            
        if (this.xacmlVersion < Constants.XACML_VERSION_3_0) {
            encoded += " DataType=\"" + this.value.getType().toString() + "\"";
        }

        if (this.issuer != null) {
            encoded += " Issuer=\"" + this.issuer + "\"";
        }
        
        if (this.xacmlVersion >= Constants.XACML_VERSION_3_0
                && this.includeInResult == true) {
            encoded += " includeInResult=\"true\"";
        }
        encoded += ">";
        
        if (this.xacmlVersion < Constants.XACML_VERSION_3_0) {
            encoded += this.value.encodeWithTags(false) + "</Attribute>";
        } else {
            encoded += this.value.encodeWithTags(true) + "</Attribute>";
        }

        return encoded;
    }

}
