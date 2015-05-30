/*
 * @(#)RequestCtx.java
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

import com.sun.xacml.Constants;
import com.sun.xacml.Indenter;
import com.sun.xacml.ParsingException;
import com.sun.xacml.PolicyMetaData;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Represents a request made to the PDP. This is the class that contains all
 * the data used to start a policy evaluation.
 *
 * @since 1.0
 * @author Seth Proctor
 * @author Marco Barreno
 * @author Ludwig Seitz
 */
public class RequestCtx
{
    
    /**
     * This contains the <code>requestElement</code>s.
     */
    private Set<RequestElement> requestElements = null;
	
    /**
     * Hold onto the root of the document for XPath searches
     */
    private Node documentRoot = null;

    /**
     * The XACML version.
     */
    private int xacmlVersion = Constants.XACML_DEFAULT_VERSION;
    
    /**
     * Constructor that creates a <code>RequestCtx</code> from 
     * <code>RequestElements</code>.
     *
     * @param requestElements  the elements of the request. E.g. subject,
     *                          resource, action. A <code>Set</code> of
     *                          <code>RequestElement</code>s. 
     *                          Must not be null, or empty.                       
     * @param documentRoot the root node of the DOM tree for this request.
     *                        Can be null. 
     *
     * @throws IllegalArgumentException if the inputs are not well formed  
     */
    public RequestCtx(Set<RequestElement> requestElements, Node documentRoot, 
            Node content) throws IllegalArgumentException {
        this(requestElements, documentRoot, Constants.XACML_DEFAULT_VERSION);
    }
    
    /**
     * Constructor that creates a <code>RequestCtx</code> from 
     * <code>RequestElements</code>.
     *
     * @param requestElements  the elements of the request. E.g. subject,
     *                          resource, action. A <code>Set</code> of
     *                          <code>RequestElement</code>s. 
     *                          Must not be null, or empty.                       
     * @param documentRoot the root node of the DOM tree for this request.
     *                        Can be null.    
     * @param xacmlVersion  The version number of the XACML used (see
     *                      <code>PolicyMetaData</code> for details).                 
     *
     * @throws IllegalArgumentException if the inputs are not well formed
     */
    public RequestCtx(Set<RequestElement> requestElements, Node documentRoot, 
            int xacmlVersion) throws IllegalArgumentException {
        
        //Control type of the requestElements
        Iterator<RequestElement> elements = requestElements.iterator();
        while (elements.hasNext()){
            Object element = elements.next();
            if (!(element instanceof RequestElement)) {
                throw new IllegalArgumentException("First parameter in request"
                        + " must be a Set of RequestElement objects"); 
            }
        }
        this.requestElements = new HashSet<RequestElement>(requestElements);
        
        // save document root node for AttributeSelectors.
        this.documentRoot = documentRoot;
        
        this.xacmlVersion = xacmlVersion;
    }
        
    /**
     * Create a new <code>RequestCtx</code> by parsing a node.  This
     * node should be created by schema-verified parsing of an
     * <code>XML</code> document.
     *
     * @param root the node to parse for the <code>RequestCtx</code>
     *
     * @return a new <code>RequestCtx</code> constructed by parsing
     *
     * @throws ParsingException if the DOM node is invalid
     */
    public static RequestCtx getInstance(Node root) throws ParsingException {
        Set<RequestElement> newRequestElements = new HashSet<RequestElement>();
        // get XACML version
        PolicyMetaData metaData = new PolicyMetaData(
                root.getNamespaceURI(), Constants.XPATH_1_0_IDENTIFIER);
        int xacmlVersion = metaData.getXACMLVersion();
        
        // First check to be sure the node passed is indeed a Request node.
        String tagName = root.getLocalName();
        if (root.getNodeType() != Node.ELEMENT_NODE
                || ! tagName.equals("Request")) {
            throw new ParsingException("Request cannot be constructed using " +
                                       "type: " + root.getLocalName());
        }
        
        // Now go through its child nodes, finding the Attributes in the
        // different categories. This may reset the XACML version if the
        // namespace had it wrong.
        NodeList children = root.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String name = node.getLocalName();
                if (name.equals("Attributes")) {  
                    xacmlVersion = Constants.XACML_VERSION_3_0;
                    RequestElement re  
                        = RequestElement.getInstance((Element)node, metaData);
                    if (re != null) {
                        newRequestElements.add(re);
                    }
                } else if (name.equals("Subject") ||
                           name.equals("Resource") ||
                           name.equals("Action") ||
                           name.equals("Environment")) {    
                    //compatibility code for XACML 2.0
                    xacmlVersion = Constants.XACML_VERSION_2_0;
                    RequestElement re  
                        = RequestElement.getInstance((Element)node, metaData);
                    if (re != null) {
                        newRequestElements.add(re);
                    }
                } else {
                throw new ParsingException("Illegal element: " 
                        + name + " where only Attributes and Content elements" 
                        + " expected");
                }
            }
        }
                
        // Now create and return the RequestCtx from the information
        // gathered
        return new RequestCtx(newRequestElements, root, xacmlVersion);
    }

    /**
     * Creates a new <code>RequestCtx</code> by parsing XML from an
     * input stream. Note that this a convenience method, and it will
     * not do schema validation by default. You should be parsing the data
     * yourself, and then providing the root node to the other
     * <code>getInstance</code> method. If you use this convenience
     * method, you probably want to turn on validation by setting the
     * context schema file (see the programmer guide for more information
     * on this).
     *
     * @param input a stream providing the XML data
     *
     * @return a new <code>RequestCtx</code>
     *
     * @throws ParsingException if there is an error parsing the input
     */
    public static RequestCtx getInstance(InputStream input)
        throws ParsingException 
    {
        return getInstance(InputParser.parseInput(input, "Request"));
    }

    /**
     * Returns a <code>Set</code> containing the <code>RequestElements</code>.
     *
     * @return the request's RequestElements
     */
    public Set<RequestElement> getRequestElements() {
        return Collections.unmodifiableSet(this.requestElements);
    }
    
    /**
     * @return  The xacml version of this request
     */
    public int getXACMLVersion() {
        return this.xacmlVersion;
    }
    
    /**
     * Returns the root DOM node of the document used to create this
     * object, or null if this object was created by hand (ie, not through
     * the <code>getInstance</code> method) or if the root node was not
     * provided to the constructor.
     *
     * @return the root DOM node or null
     */
    public Node getDocumentRoot() {
        return this.documentRoot;
    }
    
    /**
     * Encodes this context into its XML representation and writes this
     * encoding to the given <code>OutputStream</code>.  No
     * indentation is used.
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
     * Encodes this context into its XML representation and writes
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

        // Make a PrintStream for a nicer printing interface
        PrintStream out;
        if(charsetName == null) {
            out = new PrintStream(output);
        } else {
            out = new PrintStream(output, false, charsetName);
        }

        // Prepare the indentation string
        String topIndent = indenter.makeString();
        out.print(topIndent + "<Request xmlns=\"");
        
        // go in one more for next-level elements...
        indenter.in();
        
        switch (this.xacmlVersion) {
        case Constants.XACML_VERSION_1_0:
            /* falls through */
        case Constants.XACML_VERSION_1_1:    
           // there is no context id for 1.1 the schema uses the 1.0 id.
            out.println(Constants.XACML_1_0_CTX_ID + "\">");
            
            HashSet<RequestElement> subjects = new HashSet<RequestElement>();
            HashSet<RequestElement> resources = new HashSet<RequestElement>();
            RequestElement action = null;
            Iterator<RequestElement> iter = this.requestElements.iterator();
            while (iter.hasNext()) {
                RequestElement rE = iter.next();
                if (rE.getCategory().equals(Constants.RESOURCE_CAT)) {
                    resources.add(rE);
                } else if (rE.getCategory().equals(Constants.ACTION_CAT)) {
                    action = rE;
                } else {
                    //For backwards compatibility we cast all other categories
                    //to subject categories.
                    subjects.add(rE);
                }
            }
           
            //encode subjects
            Iterator<RequestElement> subjectIter = subjects.iterator();
            while(subjectIter.hasNext()) {
                RequestElement subject = subjectIter.next();
                subject.encode(output, charsetName, indenter);   
            }
            
            //encode resources
            Iterator<RequestElement> resourceIter = resources.iterator();
            while(resourceIter.hasNext()) {
                RequestElement resource = resourceIter.next();
                resource.encode(output, charsetName, indenter);
            }
            
            //encode action
            if (action != null) {
                action.encode(output, charsetName, indenter);
            }
            break;
        case Constants.XACML_VERSION_2_0:
            out.println(Constants.XACML_2_0_CTX_ID + "\">");
            
            subjects = new HashSet<RequestElement>();
            resources = new HashSet<RequestElement>();
            action = null;
            RequestElement environment = null;
            iter = this.requestElements.iterator();
            while (iter.hasNext()) {
                RequestElement rE = (RequestElement)iter.next();
                if (rE.getCategory().equals(Constants.RESOURCE_CAT)) {
                    resources.add(rE);
                } else if (rE.getCategory().equals(Constants.ACTION_CAT)) {
                    action = rE;
                } else if (rE.getCategory().equals(
                        Constants.ENVIRONMENT_CAT)) {
                    environment = rE; 
                } else {
                    //For backwards compatibility we cast all other categories
                    //to subject categories.
                    subjects.add(rE);
                }
            }
                      
            //encode subjects
            subjectIter = subjects.iterator();
            while(subjectIter.hasNext()) {
                RequestElement subject = (RequestElement)subjectIter.next();
                subject.encode(output, charsetName, indenter);   
            }
            
            //encode resources
            resourceIter = resources.iterator();
            while(resourceIter.hasNext()) {
                RequestElement resource = (RequestElement)resourceIter.next();
                resource.encode(output, charsetName, indenter);
            }
            
            //encode action
            if (action != null) {
                action.encode(output, charsetName, indenter);
            }
            
            //encode environment
            if (environment != null) {
                environment.encode(output, charsetName, indenter);
            }
            
            break;
        case Constants.XACML_VERSION_3_0:
            /* falls through */
        default:
            out.println(Constants.XACML_3_0_IDENTIFIER + "\">");    
            
            Iterator<RequestElement> elements = this.requestElements.iterator();
            while (elements.hasNext()) {
                RequestElement rEl = (RequestElement)elements.next();
                rEl.encode(output, charsetName, indenter);                   
            }
            break;          
        }
       
        indenter.out();
        
        out.println(topIndent + "</Request>");
    }
}
