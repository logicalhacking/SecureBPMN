/*
 * @(#)Result.java
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

package com.sun.xacml.ctx;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.Indenter;
import com.sun.xacml.Obligation;
import com.sun.xacml.ParsingException;
import com.sun.xacml.PolicyMetaData;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Represents the ResultType XML object from the Context schema. Any number
 * of these may included in a <code>ResponseCtx</code>. This class encodes the
 * decision effect, as well as an optional resource identifier and optional
 * status data. Any number of obligations may also be included.
 *
 * @since 1.0
 * @author Seth Proctor
 * @author Marco Barreno
 */
public class Result implements Cloneable
{
	
//	public enum DECISION {
//		DECISION_PERMIT, 
//		DECISION_DENY,
//		DECISION_INDETERMINATE,
//		DECISION_NOT_APPLICABLE,
//		INVALID_DECISION;
//		
//		private String[] messages = { PERMIT, DENY, 
//				INDETERMINATE,
//                NOT_APPLICABLE, "Invalid" };
//		
//		public String getMessage() {
//			return messages[this.ordinal()];
//		}
//		
//		public static DECISION getFromInt(int decision) {
//			switch (decision) {
//			case Result.DECISION_PERMIT:
//				return DECISION_PERMIT;
//			case Result.DECISION_DENY:
//				return DECISION_DENY;
//			case Result.DECISION_INDETERMINATE:
//				return DECISION_INDETERMINATE;
//			case Result.DECISION_NOT_APPLICABLE:
//				return DECISION_NOT_APPLICABLE;
//			case Result.INVALID_DECISION:
//				return INVALID_DECISION;
//			}
//			return null;
//		}
//	}

    /**
     * The decision to permit the request
     */
    public static final int DECISION_PERMIT = 0;

    /**
     * The decision to deny the request
     */
    public static final int DECISION_DENY = 1;

    /**
     * The decision that a decision about the request cannot be made
     */
    public static final int DECISION_INDETERMINATE = 2;

    /**
     * The decision that nothing applied to us
     */
    public static final int DECISION_NOT_APPLICABLE = 3;
    
    /**
     * An uninitialized decision value
     */
    public static final int INVALID_DECISION = 5;
    
    
    public static final String PERMIT = "Permit";
    
    public static final String DENY = "Deny";
    
    public static final String INDETERMINATE = "Indeterminate";
    
    public static final String NOT_APPLICABLE = "NotApplicable";
    


    /**
     * string versions of the 4 Decision types used for encoding
     */
    private static final String [] PRIVATE_DECISIONS = { PERMIT, DENY, 
    											INDETERMINATE,
                                                NOT_APPLICABLE};
    
    /**
     * unmodifiable list of the previous
     */
    public static final List<String> DECISIONS =
              Collections.unmodifiableList(Arrays.asList(PRIVATE_DECISIONS));
    
    
    
    /**
     * Null string for disambiguation.
     */
    public static final String nullString = null;
    
    /**
     * the decision effect
     */
    private int decision = -1;

    /**
     * the status data
     */
    private Status status = null;

    /**
     * the resource identifier or null if there is none
     */
    private String resource = null;
  
    /**
     * the <code>Set</code> of <code>RequestElement</code>s describing
     * the attributes included in the result. May be empty.
     */
    private Set<RequestElement> includedAttributes;

    /**
     * the set of obligations which may be empty
     */
    private Set<Obligation> obligations;
    
    /**
     * Constructs a <code>Result</code> object with default status data (OK).
     *
     * @param decision the decision effect to include in this result. This
     *                 must be one of the four fields in this class.
     *
     * @throws IllegalArgumentException if decision is not valid
     */
    public Result(int decision) throws IllegalArgumentException {
        this(decision, null, nullString, null);
    }
    
    /**
     * Constructs a <code>Result</code> object with default status data (OK),
     * and obligations, but no resource identifier.
     *
     * @param decision the decision effect to include in this result. This
     *                 must be one of the four fields in this class.
     * @param obligations the obligations the PEP must handle
     *
     * @throws IllegalArgumentException if decision is not valid
     */
    public Result(int decision, Set<Obligation> obligations)
        throws IllegalArgumentException
    {
        this(decision, null, nullString, obligations);
    }

    /**
     * Constructs a <code>Result</code> object with status data but without a
     * resource identifier. Typically the decision is DECISION_INDETERMINATE
     * in this case, though that's not always true.
     *
     * @param decision the decision effect to include in this result. This
     *                 must be one of the four fields in this class.
     * @param status the <code>Status</code> to include in this result
     *
     * @throws IllegalArgumentException if decision is not valid
     */
    public Result(int decision, Status status) throws IllegalArgumentException {
        this(decision, status, nullString, null);
    }

    /**
     * Constructs a <code>Result</code> object with status data and obligations
     * but without a resource identifier. Typically the decision is
     * DECISION_INDETERMINATE in this case, though that's not always true.
     *
     * @param decision the decision effect to include in this result. This
     *                 must be one of the four fields in this class.
     * @param status the <code>Status</code> to include in this result
     * @param obligations the obligations the PEP must handle
     *
     * @throws IllegalArgumentException if decision is not valid
     */
    public Result(int decision, Status status, Set<Obligation> obligations)
        throws IllegalArgumentException
    {
        this(decision, status, nullString, obligations);
    }

    /**
     * Constructs a <code>Result</code> object with a resource identifier,
     * but default status data (OK). The resource being named must match
     * the resource (or a descendent of the resource in the case of a
     * hierarchical resource) from the associated request.
     *
     * @param decision the decision effect to include in this result. This
     *                 must be one of the four fields in this class.
     * @param context  the evaluation context to determine the ResourceId
     *                 or the included attributes.
     * 
     * @throws IllegalArgumentException if decision is not valid
     */
    public Result(int decision, EvaluationCtx context)
        throws IllegalArgumentException
    {
        this(decision, null, context, null);
    }

    /**
     * Constructs a <code>Result</code> object with a resource identifier,
     * and obligations, but default status data (OK). The resource being named
     * must match the resource (or a descendent of the resource in the case of
     * a hierarchical resource) from the associated request.
     *
     * @param decision the decision effect to include in this result. This
     *                 must be one of the four fields in this class.
     * @param context  the evaluation context to determine the ResourceId
     *                 or the included attributes.
     * @param obligations the obligations the PEP must handle
     *
     * @throws IllegalArgumentException if decision is not valid
     */
    public Result(int decision, EvaluationCtx context, Set<Obligation> obligations)
        throws IllegalArgumentException
    {
        this(decision, null, context, obligations);
    }

    /**
     * Constructs a <code>Result</code> object with status data and a
     * resource identifier.
     *
     * @param decision the decision effect to include in this result. This
     *                 must be one of the four fields in this class.
     * @param status the <code>Status</code> to include in this result
     * @param context  the evaluation context to determine the ResourceId
     *                 or the included attributes.
     *
     * @throws IllegalArgumentException if decision is not valid
     */
    public Result(int decision, Status status, EvaluationCtx context)
        throws IllegalArgumentException
    {
        this(decision, status, context, null);
    }
    
    /**
     * Constructs a <code>Result</code> object with status data, a
     * resource identifier, and obligations.
     *
     * @param decision the decision effect to include in this result. This
     *                 must be one of the four fields in this class.
     * @param status the <code>Status</code> to include in this result
     * @param context  the evaluation context to determine the ResourceId
     *                 or the included attributes.
     * @param obligations the obligations the PEP must handle
     *
     * @throws IllegalArgumentException if decision is not valid
     */
    public Result(int decision, Status status, EvaluationCtx context,
                  Set<Obligation> obligations)
            throws IllegalArgumentException {
        //  check that decision is valid
        if ((decision != DECISION_PERMIT) && (decision != DECISION_DENY) &&
                (decision != DECISION_INDETERMINATE) &&
                (decision != DECISION_NOT_APPLICABLE)  ) {
            throw new IllegalArgumentException("invalid decision value");
        }
        this.decision = decision;

        if (status == null) {
            this.status = Status.getOkInstance();
        } else {
            this.status = status;
        }
        if (obligations == null) {
            this.obligations = new HashSet<Obligation>();
        } else {
            this.obligations = obligations;
        }
 
        this.includedAttributes = new HashSet<RequestElement>();
        if (!context.getIncludedAttributes().isEmpty()) {
            this.includedAttributes = new HashSet<RequestElement>(context.getIncludedAttributes());
            Iterator<RequestElement> iter = this.includedAttributes.iterator();
            while (iter.hasNext()) {
                if (!(iter.next() instanceof RequestElement)) {
                    throw new IllegalArgumentException("included attributes"
                            + " must be RequestElement objects");
                }
            }
        } else if (context.getResourceId() != null) {
            this.resource = context.getResourceId().encode();
        }       
    }
    
    /**
     * Constructs a <code>Result</code> object with status data, a
     * resource identifier, and obligations.
     *
     * @param decision the decision effect to include in this result. This
     *                 must be one of the four fields in this class.
     * @param status the <code>Status</code> to include in this result
     * @param resource  the ResourceId this result refers to.
     * @param obligations the obligations the PEP must handle
     *
     * @throws IllegalArgumentException if decision is not valid
     */
    public Result(int decision, Status status, String resource, 
                  Set<Obligation> obligations) throws IllegalArgumentException {
        // check that decision is valid
        if ((decision != DECISION_PERMIT) && (decision != DECISION_DENY) &&
                (decision != DECISION_INDETERMINATE) &&
                (decision != DECISION_NOT_APPLICABLE)) {
            throw new IllegalArgumentException("invalid decision value");
        }
        this.decision = decision;

        if (status == null) {
            this.status = Status.getOkInstance();
        } else {
            this.status = status;
        }
        if (obligations == null) {
            this.obligations = new HashSet<Obligation>();
        } else {
            this.obligations = obligations;
        }

        // not used by this constructor
        this.includedAttributes = new HashSet<RequestElement>();

        this.resource = resource;
    }

    /**
     * Constructs a <code>Result</code> object with status data, a
     * resource identifier, obligations and included attributes.
     * 
     * @param decision the decision effect to include in this result. This
     *                 must be one of the four fields in this class.
     * @param status the <code>Status</code> to include in this result
     * @param includedAttributes  the <code>Set</code> of 
     *                            <code>RequestElement</code>s describing
     *                            the attributes included in the result. 
     *                            Can be null.
     * @param obligations the obligations the PEP must handle@param decision
     *
     * @throws IllegalArgumentException
     */
    public Result(int decision, Status status, Set<RequestElement> includedAttributes, 
                  Set<Obligation> obligations) throws IllegalArgumentException {
        // check that decision is valid
        if ((decision != DECISION_PERMIT) && (decision != DECISION_DENY) &&
                (decision != DECISION_INDETERMINATE) &&
                (decision != DECISION_NOT_APPLICABLE)) {
            throw new IllegalArgumentException("invalid decision value");
        }
        this.decision = decision;

        if (status == null) {
            this.status = Status.getOkInstance();
        } else {
            this.status = status;
        }
        if (obligations == null) {
            this.obligations = new HashSet<Obligation>();
        } else {
            this.obligations = obligations;
        }
       
        this.includedAttributes = new HashSet<RequestElement>();
        if (includedAttributes != null && !includedAttributes.isEmpty()) {
            this.includedAttributes = includedAttributes;
            Iterator<RequestElement> iter = this.includedAttributes.iterator();
            while (iter.hasNext()) {
                if (!(iter.next() instanceof RequestElement)) {
                    throw new IllegalArgumentException("included attributes"
                            + " must be RequestElement objects");
                }
            }
        }
    }

    /**
     * The clone method.
     * 
     * @return  a copy of this object.
     */
    public Object clone() {
        try {
            Result clone = (Result)super.clone();
            clone.decision = this.decision;
            clone.status = this.status;
            clone.resource = this.resource;
            clone.includedAttributes = this.includedAttributes;
            clone.obligations = this.obligations;
            return clone;
        } catch (CloneNotSupportedException e) {//this should never happen
            throw new RuntimeException("Couldn't clone Result");
        }
    }


    /**
     * Creates a new instance of a <code>Result</code> based on the given
     * DOM root node. A <code>ParsingException</code> is thrown if the DOM
     * root doesn't represent a valid ResultType.
     *
     * @param root the DOM root of a ResultType
     *
     * @return a new <code>Result</code>
     *
     * @throws ParsingException if the node is invalid
     */
    public static Result getInstance(Node root) throws ParsingException {
        //check if this really is a result
        if (root.getNodeType() != Node.ELEMENT_NODE
                || !root.getLocalName().equals("Result")) {
            throw new ParsingException("Can't create a Result from a "
                    + root.getLocalName() + " element");
        }
        int decision = -1;
        Status status = null;
        String resource = null;
        Set<Obligation> obligations = null;
        Set<RequestElement> includedAttributes = new HashSet<RequestElement>();
        
        NamedNodeMap attrs = root.getAttributes();
        Node resourceAttr = attrs.getNamedItem("ResourceId");
        if (resourceAttr != null) {
            resource = resourceAttr.getNodeValue();
        }
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String name = node.getLocalName();

                if (name.equals("Decision")) {
                    String type = node.getFirstChild().getNodeValue();
                    for (int j = 0; j < DECISIONS.size(); j++) {
                        if (DECISIONS.get(j).equals(type)) {
                            decision = j;
                            break;
                        }
                    }

                    if (decision == -1) {
                        throw new ParsingException("Unknown Decision: " 
                                + type);
                    }
                } else if (name.equals("Status")) {
                    status = Status.getInstance(node);
                } else if (name.equals("Obligations")) {
                    obligations = parseObligations(node);
                } else if (name.equals("Attributes")) {
                    if (resource != null) {
                        throw new ParsingException("Can not mix ResourceId" 
                                + " and included attributes");
                    }
                    //parse RequestElement with default 3.0 metaData
                    includedAttributes.add(RequestElement.getInstance(
                            (Element)node, new PolicyMetaData()));
                }
            }
        }

        if (resource != null) {
            return new Result(decision, status, resource, obligations);
        }
        return new Result(decision, status, includedAttributes,
                          obligations);
    }

    /**
     * Helper method that handles the obligations
     */
    private static Set<Obligation> parseObligations(Node root) throws ParsingException {
        Set<Obligation> set = new HashSet<Obligation>();

        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE
                    && node.getLocalName().equals("Obligation")) {
                set.add(Obligation.getInstance(node));
            }
        }

        if (set.size() == 0) {
            throw new ParsingException("ObligationsType must not be empty");
        }
        return set;
    }

    /**
     * Returns the decision associated with this <code>Result</code>. This
     * will be one of the four <code>DECISION_*</code> fields in this class.
     *
     * @return the decision effect
     */
    public int getDecision() {
        return this.decision; 
    }

    /**
     * Returns the status data included in this <code>Result</code>.
     * Typically this will be <code>STATUS_OK</code> except when the decision
     * is <code>INDETERMINATE</code>.
     *
     * @return status associated with this Result
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Returns the resource to which this Result applies, or null if none
     * is specified.
     *
     * @return a resource identifier or null
     */
    public String getResource() {
        return this.resource;
    }
    
    /**
     * @return  Returns the included attributes as a <code>Set</code> of
     *          <code>Attribute</code>s. This may be empty.
     */
    public Set<RequestElement> getIncludedAttributes() {
        return this.includedAttributes;
    }

    /**
     * Sets the resource identifier if it has not already been set before.
     * The core code does not set the resource identifier, so this is useful
     * if you want to write wrapper code that needs this information.
     *
     * @param resource the resource identifier
     *
     * @return true if the resource identifier was set, false if it already
     *         had a value
     */
    public boolean setResource(String resource) {
        if (this.resource != null) {
            return false;
        }
        this.resource = resource;

        return true;
    }

    /**
     * Returns the set of obligations that the PEP must fulfill, which may
     * be empty.
     *
     * @return the set of obligations
     */
    public Set<Obligation> getObligations() {
        return this.obligations;
    }

    /**
     * Adds an obligation to the set of obligations that the PEP must fulfill
     *
     * @param obligation the <code>Obligation</code> to add
     */
    public void addObligation(Obligation obligation) {
        if (obligation != null) {
            this.obligations.add(obligation);
        }
    }

    /**
     * Encodes this <code>Result</code> into its XML form and writes this
     * out to the provided <code>OutputStream<code> with no indentation.
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
     * Encodes this <code>Result</code> into its XML form and writes this
     * out to the provided <code>OutputStream<code> with indentation.
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
        PrintStream out;
        if(charsetName == null) {
            out = new PrintStream(output);
        } else {
            out = new PrintStream(output, false, charsetName);
        }
        String indent = indenter.makeString();

        indenter.in();
        String indentNext = indenter.makeString();

        // encode the starting tag
        if (this.resource == null ||
                !this.includedAttributes.isEmpty()) {
            out.println(indent + "<Result>");
        } else {
            out.println(indent + "<Result ResourceId=\"" + this.resource 
                               + "\">");
        }

        // encode the decision
        out.println(indentNext + "<Decision>" + DECISIONS.get(this.decision) 
                                                          + "</Decision>");
        // encode the included attributes
        if (!this.includedAttributes.isEmpty()) {
            Iterator<RequestElement> it = this.includedAttributes.iterator();
            while (it.hasNext()) {
                RequestElement element = (RequestElement)(it.next());
                element.encode(output, charsetName, indenter);
            }
        }
     
        // encode the status
        if (this.status != null) {
            this.status.encode(output, charsetName, indenter);
        }

        // encode the obligations
        if (this.obligations.size() != 0) {
            out.println(indentNext + "<Obligations>");
            
            Iterator<Obligation> it = this.obligations.iterator();
            indenter.in();

            while (it.hasNext()) {
                Obligation obligation = (Obligation)(it.next());
                obligation.encode(output, charsetName, indenter);
            }

            indenter.out();
            out.println(indentNext + "</Obligations>");
        }

        indenter.out();

        // finish it off
        out.println(indent + "</Result>");
    }
}
