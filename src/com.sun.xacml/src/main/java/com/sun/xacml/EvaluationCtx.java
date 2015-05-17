
/*
 * @(#)EvaluationCtx.java
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

package com.sun.xacml;

import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.DateAttribute;
import com.sun.xacml.attr.DateTimeAttribute;
import com.sun.xacml.attr.TimeAttribute;

import com.sun.xacml.cond.EvaluationResult;

import com.sun.xacml.ctx.RequestElement;
import com.sun.xacml.ctx.Result;

import com.sun.xacml.reduction.ReductionGraph;

import java.net.URI;

import java.util.Map;
import java.util.Set;

import org.w3c.dom.Node;


/**
 * Manages the context of a single policy evaluation. Typically, an instance
 * is instantiated whenever the PDP gets a request and needs to perform an
 * evaluation as a result. The <code>BasicEvaluationCtx</code> class
 * provides a basic implementation that is used by default.
 *
 * @since 1.0
 * @author Seth Proctor
 * @author Ludwig Seitz
 */
public interface EvaluationCtx
{

    /**
     * Create a context for an administrative request from an
     * existing context.
     * 
     * @param decision  The decision code corresponding to those in the
     *                   <code>Result</code> class.
     * @param delegate  The delegate in this request (a set containing a 
     *                    single <code>RequestElement</code>). 
     *                    
     * @return  An administrative context for this context.                     
     */
    public EvaluationCtx createAdminCtx(int decision, Set<RequestElement> delegate);

    /**
     * Creates a copy of this context with disabled attribute finder.
     * 
     * @return  A copy of this context with disabled attribute finder.
     */
    public EvaluationCtx copyWithoutAttributeFinder();

    /**
     * Returns the DOM root of the original RequestType XML document, if
     * this context is backed by an XACML Request. If this context is not
     * backed by an XML representation, then an exception is thrown.
     *
     * @return the DOM root node
     *
     * @throws UnsupportedOperationException if the context is not backed
     *                                       by an XML representation
     */
    public Node getRequestRoot();

    /**
     * Returns the resource scope, which will be one of the five fields
     * denoting Immediate, Children, Descendants, XPath-expression or
     * EntireHierarchy.
     *
     * @return the scope of the resource
     */
    public int getScope();

    /**
     * Returns the identifier for the resource being requested.
     *
     * @return the resource
     */
    public AttributeValue getResourceId();

    /**
     * Changes the value of the resource-id attribute in this context. This
     * is useful when you have multiple resources (ie, a scope other than
     * IMMEDIATE), and you need to keep changing only the resource-id to
     * evaluate the different effective requests.
     *
     * @param resourceId the new resource-id value
     */
    public void setResourceId(AttributeValue resourceId);


    /**
     * Returns the value for the current time as known by the PDP (if this
     * value was also supplied in the Request, this will generally be a
     * different value). Details of caching or location-based resolution
     * are left to the underlying implementation.
     *
     * @return the current time
     */
    public TimeAttribute getCurrentTime();

    /**
     * Returns the value for the current date as known by the PDP (if this
     * value was also supplied in the Request, this will generally be a
     * different value). Details of caching or location-based resolution
     * are left to the underlying implementation.
     *
     * @return the current date
     */
    public DateAttribute getCurrentDate();

    /**
     * Returns the value for the current dateTime as known by the PDP (if this
     * value was also supplied in the Request, this will generally be a
     * different value). Details of caching or location-based resolution
     * are left to the underlying implementation.
     *
     * @return the current date
     */
    public DateTimeAttribute getCurrentDateTime();

    /**
     * Return available attribute values of the selected category.
     * 
     * @param category the category the attribute value(s) must be in
     * @param type the type of the attribute value(s) to find
     * @param id the id of the attribute value(s) to find
     * @param issuer the issuer of the attribute value(s) to find or null
     *
     * @return a result containing a bag either empty because no values were
     * found or containing at least one value, or status associated with an
     * Indeterminate result
     */
    public EvaluationResult getAttribute(URI category, URI type, URI id, 
            URI issuer);

    /**
     * Returns the attribute value(s) retrieved using the given XPath
     * expression.
     *
     * @param contextPath the XPath expression to search
     * @param namespaceNode the DOM node defining namespace mappings to use,
     *                      or null if mappings come from the context root
     * @param type the type of the attribute value(s) to find
     * @param xpathVersion the version of XPath to use
     *
     * @return a result containing a bag either empty because no values were
     * found or containing at least one value, or status associated with an
     * Indeterminate result
     */
    public EvaluationResult getAttribute(String contextPath,
            Node namespaceNode, URI type,
            String xpathVersion);

    /**
     * Get the decision.
     * 
     * @return The <code>int</code> value of the decision according to
     *         the <code>Result</code> class. 
     */
    public int getDecision();

    /**
     * Get the delegation depth.
     * 
     *  @return  The <code>int</code> value specifying the number of nodes
     *           in the reduction graph until now (not including this one).
     */
    public int getDelegationDepth();

    /**
     * Get a whole category.
     * 
     * @param category  The name of the category.
     * 
     * @return The <code>Set</code> of <RequestElement</code>s with
     *         the matching category.
     */
    public Set<RequestElement> getCategory(URI category);    

    /**
     * @return The <code>Set</code> of <code>RequestElement</code>s
     *         describing the attributes to be included in the result.
     */
    public Set<RequestElement> getIncludedAttributes();

    /**
     * @return  the <code>Map</code> of <code>RequestElements</code>
     *          defining this request.
     */
    public Map<URI, Set<RequestElement>> getRequestElements();

    /**
     * Save the parent <code>PolicySet</code> in this evaluation context 
     * for doing reduction of delegated policies if that becomes necessary.
     * 
     * @param pps  the parent policy set
     */
    public void saveParentPolicySet(AbstractPolicy pps);

    /**
     * Create a reduction graph for the current parent PolicySet.
     *
     */
    public void createReductionGraph();

    /**
     * @return  The current reduction graph.
     */
    public ReductionGraph getReductionGraph();

    /**
     * Remove the current <code>ReductionGraph</code> from the stack.
     */
    public void popReductionGraph();

    /**
     * Get the parent <code>PolicySet</code> for this evaluation context.
     * 
     * @return  the parent policy set
     */
    public AbstractPolicy getParentPolicySet();

    /**
     * Remove the current parent <code>PolicySet</code> from the stack
     * of parent policy sets. 
     */
    public void popParentPolicySet();

    /**
     * Add new inactive PolicyId to the Map
     * @param policyId  the id of the new inactive policy 
     */
    public void addInactivePolicyId(URI policyId);

    /**
     * Return an unmodifiable <code>Set</code> of <code>URI</code>s of
     * inactive policies
     * @return  the inactive policies
     */
    public Set<URI> getInactivePolicyIds();

    /**
     * Checks whether a <code>Policy</code> or <code>PolicySet</code> 
     * supports a revocation of a specific Policy of PolicySet 
     * in this context.
     * 
     * @param supporting  The policy or policy set that could support 
     *                    a revocation.
     * @param candidate  The id of the policy or policy set that is candidate
     *                   for revocation.
     * 
     * @return  true if the policy/policy set supports a revocation, 
     *          false otherwise.
     */
    public boolean supportsRevocation(AbstractPolicy supporting, 
            URI candidate);

    /**
     * Signal a new event to this EvaluationCtx.
     * 
     * @param element  The new event.
     */
    public void newEvent(Object element);

    /**
     * Signal that an event has finished and pass the result
     * which is a <code>Result</code>
     * 
     * @param result  The result of the finished event.
     */
    public void closeCurrentEvent(Result result);

    /**
     * Signal that an event has finished and pass the result
     * which is a <code>MatchResult</code>
     * 
     * @param result  The result of the finished event.
     */
    public void closeCurrentEvent(MatchResult result);

    /**
     * Signal that an event has finished and pass the result
     * which is a <code>EvaluationResult</code>
     * 
     * @param result  The result of the finished event.
     */
    public void closeCurrentEvent(EvaluationResult result);

    /**
     * Signal that an event has finished with a <code>String</code> message.
     * 
     * @param message  The message.
     */
    public void closeCurrentEvent(String message);

    /**
     * Signal that an event has finished with no result.
     */
    public void closeCurrentEvent();

}

