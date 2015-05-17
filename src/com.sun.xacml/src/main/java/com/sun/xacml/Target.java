
/*
 * @(#)Target.java
 *
 * Copyright 2003-2005 Sun Microsystems, Inc. All Rights Reserved.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

//import org.apache.log4j.Logger;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.xacml.PolicyMetaData;
import com.sun.xacml.debug.RuntimeInfo;
import com.sun.xacml.debug.RuntimeInfo.ELEMENT_TYPE;

/**
 * Represents the TargetType XML type in XACML. This also stores several
 * other XML types: The matching categories (e.g. Subjects, Resources, 
 * Actions, Environments, Delegates). The target is used to quickly 
 * identify whether the parent element (a policy set, policy, or rule) is
 * applicable to a given request. 
 *
 * @since 1.0
 * @author Seth Proctor
 * @author Ludwig Seitz
 */
public class Target implements Cloneable, MatchElement
{

    /**
     * A <code>List</code> containing <code>TargetSection</code>s. 
     * These represent the disjunctive sections of this target.
     */
    private List<TargetSection> targetElements;
   
    /**
     * The version of XACML of the policy containing this target
     */
    private int xacmlVersion = Constants.XACML_DEFAULT_VERSION;
        
    
    private RuntimeInfo src;
    
//    /**
//     * The logger we'll use for all messages
//     */
//    private static final Logger logger =
//        Logger.getLogger(Target.class.getName());
   
    /**
     * Constructor that creates a <code>Target</code> from components.
     *
     * @param targetSections  A <code>List</code> of
     *                        <code>TargetSection</code>s can be null. 
     */
    public Target(List<TargetSection> targetSections) {
        this(targetSections, Constants.XACML_DEFAULT_VERSION);
    }
    
    /**
     * Constructor that creates a <code>Target</code> from components.
     *
     * @param targetSections  A <code>List</code> of
     *                        <code>TargetSection</code>s can be null.
     * @param xacmlVersion  The XACML version (for encoding). 
     */
    public Target(List<TargetSection> targetSections, int xacmlVersion) {
        if (targetSections != null) {
            this.targetElements = new ArrayList<TargetSection>(targetSections);
        } else {
            this.targetElements = TargetSection.EMPTY_LIST;
        }
        
        this.xacmlVersion = xacmlVersion;
    }
    
    /**
     * The clone method constructor
     * 
     * FIXME: this does no deep copy on the Lists and Sets.
     * 
     * @return  a copy of this object.
     */
    public Object clone() {
        try {
            Target clone = (Target)super.clone();
            clone.targetElements = new ArrayList<TargetSection>(this.targetElements);
            clone.xacmlVersion = this.xacmlVersion;
            return clone;
        } catch (CloneNotSupportedException e) {//this should never happen
            throw new RuntimeException("Couldn't clone Target");
        }
    }
    
    
    /**
     * Creates a <code>Target</code> by parsing a node.
     *
     * @param root  The node to parse for the <code>Target</code>
     * @param metaData  The policy meta data.
     * @return a new <code>Target</code> constructed by parsing
     *
     * @throws ParsingException if the DOM node is invalid
     */
    public static Target getInstance(Node root, PolicyMetaData metaData)
            throws ParsingException {
    	RuntimeInfo src = RuntimeInfo.getRuntimeInfo(root, ELEMENT_TYPE.TARGET);
        // first check we really got a Target
        if (root.getNodeType() != Node.ELEMENT_NODE) {
            throw new ParsingException("Can't create a Target from" 
                    + " a node that is not an element-node" + 
                    (src != null ? src.getLocationMsgForError() : ""));
        }
        if (!root.getLocalName().equals("Target")) {
            throw new ParsingException("Can't create a Target from a "
                    + root.getLocalName() + " element" + 
                    (src != null ? src.getLocationMsgForError() : ""));
        }
        List<TargetSection> targetElements = new ArrayList<TargetSection>();

        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) { 
                targetElements.add(TargetSection.getInstance(child, metaData));  
            }
        }
        Target target = new Target(targetElements, metaData.getXACMLVersion());
        if (src != null ) {
            target.src = src;
            src.setXACMLObject(target);
        }
        return target;
    }

    /**
     * Returns the <code>Map</code> of <code>TargetSection</code>s for 
     * this target, keyed by category.
     *
     * @return  the <code>Map</code> of <code>TargetSection</code>s.
     */
    public List<TargetSection> getTargetSections() {
       return Collections.unmodifiableList(this.targetElements);
    }
    
    /**
     * Returns whether or not this <code>Target</code> matches any request.
     *
     * @return true if this Target matches any request, false otherwise
     */
    //TODO <start> changed function
    /*public boolean matchesAny() {
        return this.targetElements.isEmpty();
    } */
    
    public boolean matchesAny() {
    	if ( this.targetElements.isEmpty() ) {
    		return true;
    	} else {
    		for ( TargetSection element : targetElements ) {
    			if ( ! element.matchesAny() ) {
    				return false;
    			}
    		}
    		return true;
    	}
    }
    //TODO <end> changed function
  
    /**
     * Determines whether this <code>Target</code> matches
     * the input request (whether it is applicable). 
     * 
     * @param context the representation of the request
     *
     * @return the result of trying to match the target and the request
     */
    public MatchResult match(EvaluationCtx context) {
    	context.newEvent(this);
        MatchResult result = null;
        
        // before matching, see if this target matches any request
        if (this.matchesAny()) {
            result = new MatchResult(MatchResult.MATCH);
            context.closeCurrentEvent(result);
            return result;
        }
        Iterator<TargetSection> it = this.targetElements.iterator();
        while (it.hasNext()) {
            TargetSection section 
                = (TargetSection)it.next();
            context.newEvent(section);
            result = section.match(context);
            context.closeCurrentEvent(result);
            if (result.getResult() != MatchResult.MATCH) {
//                logger.debug("failed to match a disjunctive section " 
//                        + "of the target");
                context.closeCurrentEvent(result);
                return result;
            }

        }
                
        // if we got here, then everything matched
        result = new MatchResult(MatchResult.MATCH);
        context.closeCurrentEvent(result);
        return result;
    }
    
    /**
     * Encodes this <code>Target</code> into its XML representation and writes
     * this encoding to the given <code>OutputStream</code> with no
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
     * Encodes this <code>Target</code> into its XML representation and writes
     * this encoding to the given <code>OutputStream</code> with
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
        indenter.out();
        String indent1 = indenter.makeString();
        indenter.out();
        String indent2 = indenter.makeString();
        indenter.in();
        indenter.in();


        if (this.matchesAny()) { 
            if (this.xacmlVersion > Constants.XACML_VERSION_1_1) {
                //since 2.0, if all the sections match any request, then the Target
                //element is empty and should be encoded simply as en empty tag
                out.println(indent + "<Target/>");
            } else {
                out.println(indent + "<Target>");
                out.println(indent1 + "<Subjects>");
                out.println(indent2 + "<AnySubject/>");
                out.println(indent1 + "</Subjects>");
                out.println(indent1 + "<Resources>");
                out.println(indent2 + "<AnyResource/>");
                out.println(indent1 + "</Resources>");
                out.println(indent1 + "<Actions>");
                out.println(indent2 + "<AnyAction/>");
                out.println(indent1 + "</Actions>");
                out.println(indent + "</Target>");
            }
        } else {
            out.println(indent + "<Target>");
            indenter.in();
            
            if (this.xacmlVersion < Constants.XACML_VERSION_3_0) {
                TargetSection subject = null;
                TargetSection resource = null;
                TargetSection action = null;
                TargetSection environment = null;
                //fetch subject, resource and action
                for (int i=0; i<this.targetElements.size(); i++) {
                    TargetSection section 
                        = (TargetSection)this.targetElements.get(i);
                    if (section.getCategory() != null) {
                        if (section.getCategory().equals(
                                    Constants.SUBJECT_CAT)) {
                            subject = section; 
                        }
                        if (section.getCategory().equals(
                                Constants.RESOURCE_CAT)) {
                            resource = section;
                        }
                        if (section.getCategory().equals(
                                Constants.ACTION_CAT)) {
                            action = section;
                        }
                        if (section.getCategory().equals(
                                Constants.ENVIRONMENT_CAT)) {
                            environment = section;
                        }
                    }
                }
                if (subject != null) {
                    subject.encode(output, charsetName, indenter);
                } else if (this.xacmlVersion < Constants.XACML_VERSION_2_0) {
                    out.println(indent1 + "<Subjects>");
                    out.println(indent2 + "<AnySubject/>");
                    out.println(indent1 + "</Subjects>");
                }
                if (resource != null) {
                    resource.encode(output, charsetName, indenter);
                } else if (this.xacmlVersion < Constants.XACML_VERSION_2_0) {
                    out.println(indent1 + "<Resources>");
                    out.println(indent2 + "<AnyResource/>");
                    out.println(indent1 + "</Resources>");
                }
                if (action != null) {
                    action.encode(output, charsetName, indenter);
                } else if (this.xacmlVersion < Constants.XACML_VERSION_2_0) {
                    out.println(indent1 + "<Actions>");
                    out.println(indent2 + "<AnyAction/>");
                    out.println(indent1 + "</Actions>");
                }
                if (environment != null 
                        && this.xacmlVersion == Constants.XACML_VERSION_2_0) {
                    environment.encode(output, charsetName, indenter);
                }
            } else {
                Iterator<TargetSection> it = this.targetElements.iterator();
                while (it.hasNext()) {
                    TargetSection section 
                    = (TargetSection)it.next();
                    section.encode(output, charsetName, indenter);
                }
            }
            indenter.out();
            out.println(indent + "</Target>");
        }
    }

	public RuntimeInfo getRuntimeInfo() {
		return this.src;
	}
}
