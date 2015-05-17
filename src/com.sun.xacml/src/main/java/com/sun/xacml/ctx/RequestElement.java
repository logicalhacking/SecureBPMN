/*
 * @(#)RequestElement.java
 *
 * Copyright 2005-2006 Swedish Institute of Computer Science All Rights Reserved.
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
 * Neither the name of Swedish Institute of Computer Science or the names of 
 * contributors may be used to endorse or promote products derived from this 
 * software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. THE SWEDISH INSTITUE OF COMPUTER 
 * SCIENCE ("SICS") AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES 
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS 
 * SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SICS OR ITS LICENSORS BE 
 * LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, 
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED
 * AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF OR
 * INABILITY TO USE THIS SOFTWARE, EVEN IF SICS HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed or intended for use in
 * the design, construction, operation or maintenance of any nuclear facility.
 */

package com.sun.xacml.ctx;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.xacml.Constants;
import com.sun.xacml.Indenter;
import com.sun.xacml.ParsingException;
import com.sun.xacml.PolicyMetaData;

/**
 * Represents an element of a request made to the PDP. This is the class contains 
 * for example the subject, resource, action, delegate and one of the indirect 
 * delegate objects. Its purpose is to serve as an extension point for generic 
 * request elements.
 *
 * @since 3.0
 * @author Ludwig Seitz
 */
public class RequestElement implements Cloneable {
    
    /**
     * The map of attributes characterizing this request element
     * keyed by attribute-id. Maps to <code>Set</code>s of 
     * <code>Attribute</code>s with the same id. 
     */
    private Map<URI, Set<Attribute>> attributes = null;
    
    /**
     * The set of attributes with the includeInResult flag.
     */
    private Set<Attribute> includeAttributes = null;
   
    /**
     * This holds the optional Content/ResourceContent element, 
     * or null if there is no content.
     */
    private Node content = null;
   
    /**
     * The category of the request element
     */
    private URI category = null;
    
    /**
    * The XACML version.
    */
    private int xacmlVersion = Constants.XACML_DEFAULT_VERSION;
    
    /**
     * Remember if this was a pre 3.0 request with the SubjectCategory
     * attribute (for encoding).
     */
    private boolean withSubjectCategory = false;
    
    
    public static final Set<RequestElement> EMPTY_SET = Collections.<RequestElement>emptySet();
    
    /**
     * Creates a request element out of it's category and attributes
     * 
     * @param category  the cateogry, must not be null
     * @param attributes  must be a <code>Set</code> containing 
     *                    <code>Attribute</code> objects.
     *                    Must not be null or empty.
     */
    public RequestElement(URI category, Set<Attribute> attributes) {
        this(category, attributes, null,
             Constants.XACML_DEFAULT_VERSION, false);
    }
    
    /**
     * Creates a request element without content.
     * 
     * @param category  the cateogry, must not be null
     * @param attributes  must be a <code>Set</code> containing 
     *                    <code>Attribute</code> objects.
     *                    Must not be null or empty.
     * @param xacmlVersion  The version number of the XACML used (see
     *                      <code>PolicyMetaData</code> for details). 
     * @param withSubjectCategory  Remember if this was a pre 3.0
     *                             request with attribute category.                     
     */
    public RequestElement(URI category, Set<Attribute> attributes, int xacmlVersion,
                          boolean withSubjectCategory) {
        this(category, attributes, null, xacmlVersion, withSubjectCategory);
    }
    
    /**
     * Creates a request element out of it's category and attributes
     * 
     * @param category  the cateogry, must not be null
     * @param attributes  must be a <code>Set</code> containing 
     *                    <code>Attribute</code> objects.
     *                    Must not be null or empty.
     * @param content  the content element, or null if there is no content.                          
     * @param xacmlVersion  The version number of the XACML used (see
     *                      <code>PolicyMetaData</code> for details). 
     * @param withSubjectCategory  Remember if this was a pre 3.0
     *                             request with attribute category.                       
     */
    public RequestElement(URI category, Set<Attribute> attributes, 
            Node content, int xacmlVersion,
            boolean withSubjectCategory) {
        
        this.attributes = new HashMap<URI, Set<Attribute>>();
        this.includeAttributes = new HashSet<Attribute>();
        // convert the set to a map for request optimization and
        // make sure the contents are of correct type.
        Iterator<Attribute> attrs = attributes.iterator();
        while (attrs.hasNext()){
//            Object thing = attrs.next();
//            if (!(thing instanceof Attribute)) {
//                throw new IllegalArgumentException(category.toString() 
//                        + " input is not well formed (should be an attribute "
//                        + "but is a " + thing.getClass().getName() + ")");
//                                
//            }
            Attribute attr = attrs.next();
            URI id = attr.getId();
            if (this.attributes.containsKey(id)) {
            	Set<Attribute> set = this.attributes.get(id);
                set.add(attr);
            } else {
                Set<Attribute> set = new HashSet<Attribute>();
                set.add(attr);
                this.attributes.put(id, set);
            } 
            if (attr.includeInResult()) {
                this.includeAttributes.add(attr);
            }
        }
                
        if (content != null) {
            if (xacmlVersion < Constants.XACML_VERSION_3_0
                    && !category.equals(Constants.RESOURCE_CAT)) {
                throw new IllegalArgumentException("Can't have Content" 
                        + " in this category for this XACML version");             
            }
            this.content = content.cloneNode(true);
        }
        
        if (category == null) {
            throw new IllegalArgumentException("Request elements " +
                                                "require a category");
        }
        this.category = category;
        this.xacmlVersion = xacmlVersion;
        this.withSubjectCategory = withSubjectCategory;
    }
    
    /**
     * Constructor. Creates a request element out of it's name and attributes
     * (already in Map), without content.
     * 
     * @param category  the cateogry, must not be null
     * @param attributes  must be a <code>Map</code> of <code>Set</code>s 
     *                    containing <code>Attribute</code> objects, keyed
     *                    by the attribute ids. Must not be null or empty.
     */
    public RequestElement(URI category, Map<URI, Set<Attribute>> attributes) {
        this(category, attributes, null);
        
    }
    
    /**
     * Constructor. Creates a request element out of it's name and attributes
     * (already in Map), with content.
     * 
     * @param category  the cateogry, must not be null
     * @param attributes  must be a <code>Map</code> of <code>Set</code>s 
     *                    containing <code>Attribute</code> objects, keyed
     *                    by the attribute ids. Must not be null or empty.
     * @param content  the content element, or null if there is no content.
     */
    public RequestElement(URI category, Map<URI, Set<Attribute>> attributes, Node content) {
        this.includeAttributes = new HashSet<Attribute>();
        if (attributes == null || attributes.isEmpty()) {
            throw new IllegalArgumentException("Can't create RequestElement"
                    + " with empty or null attributes map.");
        }
        Iterator<Map.Entry<URI, Set<Attribute>>> entries = attributes.entrySet().iterator();
        while(entries.hasNext()) {
            Map.Entry<URI, Set<Attribute>> foo = entries.next();
//            if (!(foo.getKey() instanceof URI)) {
//                throw new IllegalArgumentException("Can't create a Request" 
//                        + "Element with non URI keys in the attributes" 
//                        + " Map");
//            }
//            Object bar = foo.getValue();
//            if (!(bar instanceof Set)) {
//                throw new IllegalArgumentException("Can't create a Request" 
//                        + "Element with non-Set objects in the attributes" 
//                        + " Map");
//            }
//            Set attrSet = (Set)bar;
            Set<Attribute> attrSet = foo.getValue();
            Iterator<Attribute> iter = attrSet.iterator();
            while(iter.hasNext()) {
                Object foobar = iter.next();
                if (!(foobar instanceof Attribute)) {
                    throw new IllegalArgumentException("Can't create a"
                            + " RequestElement with non-Attribute objects in"
                            + " the sets in the attributes Map");
                }
                Attribute attr = (Attribute)foobar;
                if (!attr.getId().equals(foo.getKey())) {
                    throw new IllegalArgumentException("Key mapped" 
                            + " to the wrong attribute in attributes map");
                }
                if (attr.includeInResult()) {
                    this.includeAttributes.add(attr);
                }
            }
            
        }
        this.attributes = new HashMap<URI, Set<Attribute>>(attributes);
        if (content != null) {
            this.content = content.cloneNode(true);
        }
        this.category = category;
    }
    
    /**
     * The clone method.
     * 
     * @return  a copy of this object.
     */
    public Object clone() {
        try {
            RequestElement clone = (RequestElement)super.clone();
            // deep copy of the attributes
            clone.attributes = new HashMap<URI, Set<Attribute>>();
            clone.includeAttributes = new HashSet<Attribute>();
            Iterator<URI> iter = this.attributes.keySet().iterator();
            while(iter.hasNext()) {
                URI key = URI.create(iter.next().toString());
                Set<Attribute> attrs = new HashSet<Attribute>();
                Iterator<Attribute> iter2 = this.attributes.get(key).iterator();
                while(iter2.hasNext()) {
                    attrs.add( (Attribute) iter2.next().clone());
                }
                clone.attributes.put(key, attrs);
            }
            Iterator<Attribute> iter3 = this.includeAttributes.iterator();
            while(iter3.hasNext()) {
                clone.includeAttributes.add( (Attribute) iter3.next().clone());
            }
            if (this.content != null) {
                clone.content = this.content.cloneNode(true);
            }
            clone.category = this.category;
            clone.xacmlVersion = this.xacmlVersion;
            clone.withSubjectCategory = this.withSubjectCategory;
            return clone;
        } catch (CloneNotSupportedException e) {//this should never happen
            throw new RuntimeException("Couldn't clone RequestElement");
        }
    }
            
    /**
     * Create a new <code>RequestElement</code> by parsing a node in a
     * request.  This node should be created by schema-verified parsing of an
     * <code>XML</code> document.
     *
     * @param root the node to parse for the <code>RequestElement</code>
     * @param metaData  the meta-data associated with the containing request.
     *
     * @return a new <code>RequestElement</code> constructed by parsing
     *
     * @throws ParsingException if the DOM node is invalid
     */
    public static RequestElement getInstance(Element root, 
                PolicyMetaData metaData) throws ParsingException {
        URI category = null;
        boolean withSubjectCategory = false;
        if (metaData.getXACMLVersion() == Constants.XACML_VERSION_3_0) {
            NamedNodeMap xmlAttrs = root.getAttributes();
            if (xmlAttrs == null) {
                throw new ParsingException("No XML attributes found,"
                        + " where Category attribute was expected");
            }
            Node catNode = xmlAttrs.getNamedItem("Category");
            if (catNode == null) {
                throw new ParsingException("'Category' XML attribute " +
                "not found"); 
            }

            String categoryStr = catNode.getNodeValue();
            try {
                category = new URI(categoryStr);
            } catch (URISyntaxException e) {
                throw new ParsingException("Error while parsing "
                        + "category: " + categoryStr, e);
            }
        } else if (metaData.getXACMLVersion() 
                < Constants.XACML_VERSION_3_0) {
            if (root.getLocalName().equals("Subject")) {
                NamedNodeMap xmlAttrs = root.getAttributes();
                if (xmlAttrs == null) {
                    category = Constants.SUBJECT_CAT;
                } else {
                    Node catNode = xmlAttrs.getNamedItem("SubjectCategory");
                    if (catNode == null) {
                        category = Constants.SUBJECT_CAT;
                    } else {
                        withSubjectCategory = true;
                        String categoryStr = catNode.getNodeValue();
                        try {
                            category = new URI(categoryStr);
                        } catch (URISyntaxException e) {
                            throw new ParsingException("Error while parsing "
                                    + "category: " + categoryStr, e);
                        }
                    }
                }
            } else if (root.getLocalName().equals("Resource")) {
                category = Constants.RESOURCE_CAT;
            } else if (root.getLocalName().equals("Action")) {
                category = Constants.ACTION_CAT;
            } else if (metaData.getXACMLVersion() 
                    == Constants.XACML_VERSION_2_0
                    && root.getLocalName().equals("Environment")) {
                category = Constants.ENVIRONMENT_CAT;
            } else {
                throw new ParsingException("Invalid node: " 
                        + root.getLocalName() + " in XACML " 
                        + metaData.getXACMLVersion() + " request");
            }
        } else {
            throw new ParsingException("Invalid/Unsupported XACML version");            
        }

        // Find the content element if there is one
        Node content = null;
        NodeList children = root.getChildNodes();
        for(int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node != null && node.getNodeType() == Node.ELEMENT_NODE) { 
                if (node.getLocalName().equals("Content")) {
                    if (metaData.getXACMLVersion()
                            < Constants.XACML_VERSION_3_0) {
                        throw new ParsingException("Content element not"+
                                " supported for XACML version " 
                                + metaData.getXACMLVersion());
                    }
                    content = node.cloneNode(true);
                    break;
                } else if (node.getLocalName().equals("ResourceContent")) {
                    if (metaData.getXACMLVersion() 
                            > Constants.XACML_VERSION_2_0) {
                        throw new ParsingException("ResourceContent element not"
                                + " supported for XACML version " 
                                + metaData.getXACMLVersion());
                    }
                    content = node.cloneNode(true);
                    break;
                }
            }
        }

        Set<Attribute> attributes = parseAttributes(root);

        return new RequestElement(category, attributes, content,
                metaData.getXACMLVersion(), withSubjectCategory);
    }
    
    /**
     * Helper method that parses a set of Attribute types. 
     * @param root  the root <code>Node</code> containing the set of
     *              Attributes and Contents
     * @return  A <code>Set</code> <code>Attribute</code>s.               
     */
    private static Set<Attribute> parseAttributes(Node root) throws ParsingException {
        Set<Attribute> set = new HashSet<Attribute>();
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE
                    && node.getLocalName().equals("Attribute")) {
                List<Attribute> attrs = Attribute.getInstances(node);
                set.addAll(attrs);
            }
        }
        return set;
    }
    
    /**
     * Get the category of this request element.
     * 
     * @return the category of the request element
     */
    public URI getCategory() {
        return this.category;
    }    
    
    /**
     * Get the attributes of the request element as a <code>Map</code>
     * The keys of the Map are the attribute ids.
     * 
     * @return the <code>Map</code> of <code>Set</code>s of
     *         <code>Attribute</code>s of the request element.
     */
    public Map<URI, Set<Attribute>> getAttributes() {    
        return Collections.unmodifiableMap(this.attributes);
    }
    
    /**
     * Get the content node of the request element if there is one.
     *  
     * @return  a <code>Node</code> or null.
     * 
     */
    public Node getContent() {
        if (this.content == null) {
            return null;
        }
        return this.content.cloneNode(true);
    }
    
    /**
     * @return  The <code>Set</code> of attributes with the includeInResult
     *          flag.
     */
    public Set<Attribute> getIncludeInResultSet() {
        return Collections.unmodifiableSet(this.includeAttributes);
    }
    
    
    /**
     * Encodes this request element into its XML representation and writes
     * this encoding to the given <code>OutputStream</code> without
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
     * Encodes this request element into its XML representation and writes
     * this encoding to the given <code>OutputStream</code> with
     * indentation.
     *
     * @param output a stream into which the XML-encoded data is written
     * @param charsetName  the character set to use in encoding of strings.
     *                     This may be null in which case the platform
     *                     default character set will be used.
     * @param indenter an object that creates indentation strings
     * 
     * @throws UnsupportedEncodingException 
     */
    public void encode(OutputStream output, String charsetName,
                       Indenter indenter) 
            throws UnsupportedEncodingException {
        // setup the formatting & outstream stuff
        String indent = indenter.makeString();
        PrintStream out;
        if(charsetName == null) {
            out = new PrintStream(output);
        } else {
            out = new PrintStream(output, false, charsetName);
        }

        // write out the encoded form
        String endTag = null;
        if (this.xacmlVersion > Constants.XACML_VERSION_2_0) {
            out.println(indent + "<Attributes Category=\""
                    + this.category.toString() + "\">");
            endTag = indent + "</Attributes>";
        } else {
            if (this.category.equals(Constants.RESOURCE_CAT)) {
                out.println(indent + "<Resource>");
                endTag = indent + "</Resource>";
            } else if (this.category.equals(Constants.ACTION_CAT)) {
                out.println(indent + "<Action>");
                endTag = indent + "</Action>";
            } else if (this.xacmlVersion == Constants.XACML_VERSION_2_0
                    && this.category.equals(Constants.ENVIRONMENT_CAT)) {
                out.println(indent + "<Environment>");
                endTag = indent + "</Environment>";
            } else {
                if (this.withSubjectCategory) {
                    out.println(indent + "<Subject SubjectCategory=\""
                            + this.category.toString() + "\">");
                    endTag = indent + "</Subject>";
                } else {
                    out.println(indent + "<Subject>");
                    endTag = indent + "</Subject>";
                }
            }
        } 
        
        // go in one more for next-level elements...
        indenter.in();
        
        if (this.content != null) {
            out.println(indenter.makeString() + "<Content>");
            encodeContent(this.content, output, charsetName, indenter);
            out.println(indenter.makeString() + "</Content>");
        }
        encodeAttributes(this.attributes, output, charsetName, indenter);
                
        indenter.out();
        
        //Notice: the indent was already included previously
        out.println(endTag);
    }
    
    /**
     * Private helper function to encode the content node.
     * @throws UnsupportedEncodingException 
     */
    private void encodeContent(Node content, OutputStream output,
                                String charsetName, Indenter indenter) 
            throws UnsupportedEncodingException {
        indenter.in();
        String indent = indenter.makeString();
        PrintStream out;
        if(charsetName == null) {
            out = new PrintStream(output);
        } else {
            out = new PrintStream(output, false, charsetName);
        }
        StringWriter sw = new StringWriter();
        try {
            Transformer serializer 
                = TransformerFactory.newInstance().newTransformer();
            serializer.setOutputProperty(
                    OutputKeys.OMIT_XML_DECLARATION, "yes");
            serializer.transform(new DOMSource(content), new StreamResult(sw));
        } catch (TransformerException e) {
            throw new RuntimeException(
            "'This never happens' error happened"); 
        }      
        out.println(indent + sw.toString());
        indenter.out();
    }
    
    /**
     * Private helper function to encode the attribute sets
     * @throws UnsupportedEncodingException 
     */
    private void encodeAttributes(Map<URI, Set<Attribute>> attributes, OutputStream output,
                                  String charsetName, Indenter indenter)
                throws UnsupportedEncodingException {
        Iterator<Map.Entry<URI, Set<Attribute>>> it = attributes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<URI, Set<Attribute>> entry = it.next();
            Set<Attribute> set = entry.getValue();
            Iterator<Attribute> it2 = set.iterator();
            while (it2.hasNext()) {
                Attribute attr = (Attribute) it2.next();
                attr.encode(output, charsetName, indenter);
            }
        }
    }
}
