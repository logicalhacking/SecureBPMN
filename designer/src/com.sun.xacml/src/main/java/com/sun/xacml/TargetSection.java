
/*
 * @(#)TargetSection.java
 *
 * Copyright 2005-2006 Sun Microsystems, Inc. All Rights Reserved.
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

import com.sun.xacml.ctx.Status;
import com.sun.xacml.debug.RuntimeInfo;
import com.sun.xacml.debug.RuntimeInfo.ELEMENT_TYPE;

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


/**
 * This is a container class for instances of <code>TargetMatchGroup</code>
 * and represents the matching categories sections of an XACML Target
 * (e.g. Subjects, Resources, Actions, Environments, Delegates). 
 * This section may apply to any request.
 *
 * @since 2.0
 * @author Seth Proctor
 * @author Ludwig Seitz
 */
public class TargetSection implements MatchElement
{

    /**
     * The list of match groups
     */
    private List<TargetMatchGroup> matchGroups;
    
    /**
     * The XACML version of this target section
     * for encoding older XACML versions
     */
    private int xacmlVersion;
    
    /**
     * The category of this target section
     */
    private URI category;
    
    public static final List<TargetSection> EMPTY_LIST = Collections.<TargetSection>emptyList();
    
    private RuntimeInfo src;

    /**
     * Constructor that takes a group and a version. If the group is
     * null or empty, then this represents a section that matches any request.
     *
     * @param matchGroups  A possibly null <code>List</code> of
     *                     <code>TargetMatchGroup</code>s.
     */
    public TargetSection(List<TargetMatchGroup> matchGroups) {
      this(matchGroups, null, Constants.XACML_DEFAULT_VERSION);
    }
    
    /**
    * Constructor that takes a group and a version. If the group is
    * null or empty, then this represents a section that matches any request.
    *
    * @param matchGroups  A possibly null <code>List</code> of
    *                     <code>TargetMatchGroup</code>s.
    * @param category  The category of this target section.
    * @param xacmlVersion  The XACML version of this TargetSection 
    *                      (for encoding).
    */
   public TargetSection(List<TargetMatchGroup> matchGroups, URI category, int xacmlVersion) {
       if (matchGroups == null) {
           this.matchGroups = TargetMatchGroup.EMPTY_LIST;
       } else {
           this.matchGroups = Collections.
               unmodifiableList(new ArrayList<TargetMatchGroup>(matchGroups));
       }
       this.category = category;
       this.xacmlVersion = xacmlVersion;
       if (this.xacmlVersion < Constants.XACML_VERSION_3_0) {
           if (this.category == null) {
               throw new IllegalArgumentException("Can't create TargetSections" 
                       + " in XACML version < 3.0 without category");  
           }
           if (!this.category.toString().startsWith(
                       "urn:oasis:names:tc:xacml:1.0:subject-category:")
                   && !this.category.equals(Constants.RESOURCE_CAT)
                   && !this.category.equals(Constants.ACTION_CAT)) {
               if (this.xacmlVersion == Constants.XACML_VERSION_2_0) {
                   if (!this.category.equals(Constants.ENVIRONMENT_CAT)) {
                       throw new IllegalArgumentException("Illegal" 
                               + " Category: " 
                               + this.category.toString()
                               + " for pre XACML 3.0 policy");
                   }
               } else {
                   throw new IllegalArgumentException("Illegal" 
                           + " Category: " 
                           + this.category.toString()
                           + " for pre XACML 3.0 policy");
               }
           }
       }
   }
   
    /**
     * Creates a <code>Target</code> by parsing a node.
     *
     * @param root  The node to parse for the <code>Target</code>.
     * @param metaData  The meta-data from the enclosing policy.
     *
     * @return a new <code>Target</code> constructed by parsing
     *
     * @throws ParsingException if the DOM node is invalid
     */
    public static TargetSection getInstance(Node root, PolicyMetaData metaData)
        throws ParsingException {
    	RuntimeInfo src = RuntimeInfo.getRuntimeInfo(root, ELEMENT_TYPE.TARGET_SECTION);    	
        if (root.getNodeType() != Node.ELEMENT_NODE) {
            throw new ParsingException("Can't create a TargetSection from" 
                    + " a node that is not an element-node"
                    +  (src != null ? src.getLocationMsgForError() : ""));
        }
        List<TargetMatchGroup> groups = new ArrayList<TargetMatchGroup>();
        NodeList children = root.getChildNodes();
        String categoryStr = null;
        URI category = null;
        if (metaData.getXACMLVersion() > Constants.XACML_VERSION_2_0) {
            if (!root.getLocalName().equals("AnyOf")) {
                throw new ParsingException("Found: " + root.getLocalName()
                        + " tag where expecting AnyOf tag"
                        +  (src != null ? src.getLocationMsgForError() : ""));  
            } 
        } else {
            //Get node name minus the tailing 's'
            categoryStr = root.getLocalName();
            categoryStr = categoryStr.substring(0, categoryStr.length()-1);
            
            if (categoryStr.equals("Subject")) {
                category = Constants.SUBJECT_CAT;
            } else if (categoryStr.equals("Resource")) {
                category = Constants.RESOURCE_CAT;
            } else if (categoryStr.equals("Action")) {
                category = Constants.ACTION_CAT;
            } else if (metaData.getXACMLVersion() 
                        == Constants.XACML_VERSION_2_0
                        && categoryStr.equals("Environment")) {
                category = Constants.ENVIRONMENT_CAT;
            } else {
                throw new ParsingException("Invalid element: " 
                        + root.getLocalName()
                        + "for XACML version:" + metaData.getXACMLVersion()
                        +  (src != null ? src.getLocationMsgForError() : ""));
            }
        }
        
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                //check for Any* elements as children
                if (metaData.getXACMLVersion() < Constants.XACML_VERSION_2_0) {
                    if (child.getLocalName().startsWith("Any")) {
                    	TargetSection targetSection =  new TargetSection(groups, 
                    			category, metaData.getXACMLVersion());
                    	targetSection.src = src;
                        return targetSection;
                    }
                }
                groups.add(TargetMatchGroup.getInstance(child, metaData, 
                                                        category));
            }
        }
        
        // at this point the list is non-empty (it has specific groups to
        // match) or is empty 
    	TargetSection targetSection =  new TargetSection(groups, 
    			category, metaData.getXACMLVersion());
    	if ( src != null ) {
        	targetSection.src = src;
        	src.setXACMLObject(targetSection);
    	}
        return targetSection;
    }

    /**
     * Returns the <code>TargetMatchGroup</code>s contained in this group.
     *
     * @return a <code>List</code> of <code>TargetMatchGroup</code>s
     */
    public List<TargetMatchGroup> getMatchGroups() {
        return this.matchGroups;
    }

    /**
     * Returns whether this section matches any request.
     *
     * @return true if this section matches any request, false otherwise
     */
    public boolean matchesAny() {
        return this.matchGroups.isEmpty();
    }

    /**
     * Returns the category of this section.
     * 
     * @return  the category of this section.
     */
    public URI getCategory() {
        return this.category;
    }
    
    /**
     * Determines whether this <code>TargetSection</code> matches
     * the input request (whether it is applicable).
     * 
     * @param context the representation of the request
     *
     * @return the result of trying to match the target and the request
     */
    public MatchResult match(EvaluationCtx context) {
        // if we apply to anything, then we always match
        if (this.matchGroups.isEmpty()) {
            return new MatchResult(MatchResult.MATCH);
        }

        // there are specific matching elements, so prepare to iterate
        // through the list
        Iterator<TargetMatchGroup> it = this.matchGroups.iterator();
        Status firstIndeterminateStatus = null;

        // in order for this section to match, one of the groups must match 
        while (it.hasNext()) {
            // get the next group and try matching it
            TargetMatchGroup group = (TargetMatchGroup)(it.next());
            context.newEvent(group);
            MatchResult result = group.match(context);
            context.closeCurrentEvent(result);

            // we only need one match, so if this matched, then we're done
            if (result.getResult() == MatchResult.MATCH) {
                return result; 
            }
                
            // if we didn't match then it was either a NO_MATCH or
            // INDETERMINATE...in the second case, we need to remember
            // it happened, 'cause if we don't get a MATCH, then we'll
            // be returning INDETERMINATE
            if (result.getResult() == MatchResult.INDETERMINATE) {
                if (firstIndeterminateStatus == null) {
                    firstIndeterminateStatus = result.getStatus();
                }
            }
        }

        // if we got here, then none of the sub-matches passed, so
        // we have to see if we got any INDETERMINATE cases
        if (firstIndeterminateStatus == null) {
            return new MatchResult(MatchResult.NO_MATCH);
        }
        return new MatchResult(MatchResult.INDETERMINATE,
                               firstIndeterminateStatus);
    }

    /**
     * Encodes this <code>TargetSection</code> into its XML representation
     * and writes  this encoding to the given <code>OutputStream</code> with
     * no indentation.
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
     * Encodes this <code>TargetSection</code> into its XML representation and
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
        String closingTag = null;
        // figure out if this section applies to any request
        if (!this.matchGroups.isEmpty()) {
            if (this.xacmlVersion > Constants.XACML_VERSION_2_0) {
                // this has specific rules, so we can now encode them
                out.println(indent + "<AnyOf>");
                closingTag = "</AnyOf>";

            } else { // XACML 2.0 and below compatibility code
                if (this.category.equals(Constants.SUBJECT_CAT)) {
                    out.println(indent + "<Subjects>");
                    closingTag = "</Subjects>";
                } else if (this.category.equals(Constants.RESOURCE_CAT)) {
                    out.println(indent + "<Resources>"); 
                    closingTag = "</Resources>";
                } else if (this.category.equals(Constants.ACTION_CAT)) {
                    out.println(indent + "<Actions>"); 
                    closingTag = "</Actions>";
                } else if (this.category.equals(Constants.ENVIRONMENT_CAT)) {
                    out.println(indent + "<Environments>");
                    closingTag = "</Environments>";
                }
            }
            Iterator<TargetMatchGroup> it = this.matchGroups.iterator();
            indenter.in();
            while (it.hasNext()) {
                TargetMatchGroup group = (TargetMatchGroup)(it.next());
                group.encode(output, charsetName, indenter);
            }
            indenter.out();
            out.println(indent + closingTag);          
        }
    }

	public RuntimeInfo getRuntimeInfo() {
		return this.src;
	}
    
}
