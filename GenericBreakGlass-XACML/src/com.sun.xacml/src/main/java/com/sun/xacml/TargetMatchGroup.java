
/*
 * @(#)TargetMatchGroup.java
 *
 * Copyright 2005 Sun Microsystems, Inc. All Rights Reserved.
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

package com.sun.xacml;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import java.net.URI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.xacml.debug.RuntimeInfo;
import com.sun.xacml.debug.RuntimeInfo.ELEMENT_TYPE;


/**
 * This class contains a group of <code>TargetMatch</code> instances and
 * represents the different elements in an XACML Target (e.g. Subject)
 *
 * @since 2.0
 * @author Seth Proctor
 * @author Ludwig Seitz
 */
public class TargetMatchGroup implements MatchElement
{
	
    /**
     * The list of <code>TargetMatch</code>es.
     */
    private List<TargetMatch> matches;
    
    /**
     * The XACML version number. Only used for encoding.
     */
    private int xacmlVersion;
    
    /**
     * The category. Only used for encoding XACML versions < 3.0
     */
    private URI category;
    
    public static final List<TargetMatchGroup> EMPTY_LIST = Collections.<TargetMatchGroup>emptyList();
    
    private RuntimeInfo src;
    
    /**
     * Constructor that creates a new <code>TargetMatchGroup</code> based
     * on the given elements with the default XACML version.
     *
     * @param matchElements  A <code>List</code> of <code>TargetMatch</code>.
     *                       They should all have the same category.
     */
    public TargetMatchGroup(List<TargetMatch> matchElements) {
        this(matchElements, Constants.XACML_DEFAULT_VERSION, null);
    }
    /**
     * Constructor that creates a new <code>TargetMatchGroup</code> based
     * on the given elements.
     *
     * @param matchElements  A <code>List</code> of <code>TargetMatch</code>.
     *                       They should all have the same category.
     * @param xacmlVersion  The XACML version number.
     * @param category  The category if this match group.   
     *                  This is null for XACML version > 2.0.         
     */
    public TargetMatchGroup(List<TargetMatch> matchElements, int xacmlVersion, 
                            URI category) {
        if (matchElements == null) {
            this.matches = Collections.unmodifiableList(new ArrayList<TargetMatch>());
        } else {  
            this.matches =
                Collections.unmodifiableList(new ArrayList<TargetMatch>(matchElements));
        }
        
        this.xacmlVersion = xacmlVersion;
        this.category = category;
        if (this.category == null 
                && this.xacmlVersion < Constants.XACML_VERSION_3_0) {
            throw new IllegalArgumentException("Can't create TargetSections" 
                    + " in XACML version < 3.0 without category");  
        }
    }
    
    /**
     * Creates a <code>Target</code> based on its DOM node.
     *
     * @param root  The node to parse for the target group.
     * @param metaData  Meta-data associated with the policy.
     * @param masterCategory  The category of the TargetSection, for
     *                        checking consistency.
     *
     * @return a new <code>TargetMatchGroup</code> constructed by parsing
     *
     * @throws ParsingException if the DOM node is invalid
     */
    public static TargetMatchGroup getInstance(Node root,
                                               PolicyMetaData metaData,
                                               URI masterCategory)
            throws ParsingException {
    	RuntimeInfo src = RuntimeInfo.getRuntimeInfo(root, ELEMENT_TYPE.TARGET_MATCH_GROUP );
        if (root.getNodeType() != Node.ELEMENT_NODE) {
            throw new ParsingException("Can't create a TargetMatchGroup from" 
                    + " a node that is not an element-node"
                    +  (src != null ? src.getLocationMsgForError() : ""));
        }
        List<TargetMatch> matches = new ArrayList<TargetMatch>();
        NodeList children = root.getChildNodes();
        String categoryStr = root.getLocalName();
        
        if (!categoryStr.equals("AllOf")) { //XACML v2.0 or lower
            if (metaData.getXACMLVersion() 
                    > Constants.XACML_VERSION_2_0) {
                throw new ParsingException("Can't create a > XACML 2.0"
                        + " TargetMatchGroup form a " + categoryStr
                        + " element" 
                        + (src != null ? src.getLocationMsgForError() : ""));
            } 
            
            URI category = null;          
            if (categoryStr.equals("Subject")) {
                category = Constants.SUBJECT_CAT;
            } else if (categoryStr.equals("Resource")) {
                category = Constants.RESOURCE_CAT;
            } else if (categoryStr.equals("Action")) {
                category = Constants.ACTION_CAT;
            } else if (categoryStr.equals("Environment")){
                category= Constants.ENVIRONMENT_CAT;
            } else {
                throw new ParsingException("Can't create a < XACML 3.0"
                        + " TargetMatchGroup from a " + categoryStr
                        + " element"
                        +  (src != null ? src.getLocationMsgForError() : ""));
            }
            
            if (!category.equals(masterCategory)) {
                throw new ParsingException(categoryStr + " element must"
                        + " can't be in enclosing " 
                        + masterCategory.toString() + " element"
                        +  (src != null ? src.getLocationMsgForError() : ""));
            }
        } else if (categoryStr.equals("AllOf")
                && (metaData.getXACMLVersion()
                        < Constants.XACML_VERSION_3_0)) {
            throw new ParsingException("Can't create a  < XACML 3.0"
                    + " TargetMatchGroup from a " + categoryStr
                    + " element"
                    + (src != null ? src.getLocationMsgForError() : ""));
        } 
        
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) { 
                matches.add(TargetMatch.getInstance(child, metaData,
                        masterCategory));
            }
        }
        
        TargetMatchGroup targetMatchGroup = new TargetMatchGroup(matches, 
        		metaData.getXACMLVersion(), masterCategory);
        if ( src != null ) {
            targetMatchGroup.src = src;
            src.setXACMLObject(targetMatchGroup);
        }
        return targetMatchGroup;
    }

    /**
     * Determines whether this <code>TargetMatchGroup</code> matches
     * the input request (whether it is applicable). 
     * 
     * @param context the representation of the request
     *
     * @return the result of trying to match the group with the context
     */
    public MatchResult match(EvaluationCtx context) {
    	//TODO <start> changed function
    	if ( this.matches.size() == 0 ) {
    		return new MatchResult(MatchResult.MATCH);
    	}
    	//TODO <end> changed function
        Iterator<TargetMatch> it = this.matches.iterator();
        MatchResult result = null;

        while (it.hasNext()) {
            TargetMatch tm = (TargetMatch)(it.next());
            context.newEvent(tm);
            result = tm.match(context);
            context.closeCurrentEvent(result);
            if (result.getResult() != MatchResult.MATCH) {
                break;
            }
        }

        return result;
    }

    /**
     * Returns the category of this group.
     * 
     * @return  the category of this group.
     */
    public URI getCategory() {
        return this.category;
    }
    
    /**
     * Encodes this <code>TargetMatchGroup</code> into its XML representation
     * and writes this encoding to the given <code>OutputStream</code> with no
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
     * Encodes this <code>TargetMatchGroup</code> into its XML representation
     * and writes this encoding to the given <code>OutputStream</code> with
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
        Iterator<TargetMatch> it = this.matches.iterator();
        String closingTag = null;

        if (this.xacmlVersion > Constants.XACML_VERSION_2_0) {
            out.println(indent + "<AllOf>");
            closingTag = "</AllOf>";
        } else { // XACML 2.0 and below compatibility code
            if (this.category.equals(Constants.SUBJECT_CAT)) {
                out.println(indent + "<Subject>");
                closingTag = "</Subject>";
            } else if (this.category.equals(Constants.RESOURCE_CAT)) {
                out.println(indent + "<Resource>"); 
                closingTag = "</Resource>";
            } else if (this.category.equals(Constants.ACTION_CAT)) {
                out.println(indent + "<Action>"); 
                closingTag = "</Action>";
            } else if (this.category.equals(Constants.ENVIRONMENT_CAT)) {
                out.println(indent + "<Environment>");
                closingTag = "</Environment>";
            }
        }
        
        indenter.in();
        
        while (it.hasNext()) {
            TargetMatch tm = (TargetMatch)(it.next());
            tm.encode(output, charsetName, indenter);
        }
        out.println(indent + closingTag);
        indenter.out();
    }
    
    /**
     * Returns the matches of this match group.
     * @return  The list of <code>TargetMatch</code>es.
     */
    public List<TargetMatch> getMatches() {
        return Collections.unmodifiableList(this.matches);
    }
	public RuntimeInfo getRuntimeInfo() {
		return this.src;
	}

}
