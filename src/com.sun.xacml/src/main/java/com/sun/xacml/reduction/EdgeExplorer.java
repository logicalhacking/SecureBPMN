/*
 * @(#)EdgeExplorer.java
 *
 * Copyright 2007 Swedish Institute of Computer Science All Rights Reserved.
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

package com.sun.xacml.reduction;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.sun.xacml.AbstractPolicy;
import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.MatchResult;
import com.sun.xacml.PolicyReference;
import com.sun.xacml.PolicySet;
import com.sun.xacml.PolicyTreeElement;
import com.sun.xacml.ctx.Result;

/**
 * This class provides an iterator that goes through the support
 * edges of a policy.
 * 
 * @author Ludwig Seitz
 *
 */
public class EdgeExplorer {
    
    /**
     * The node we are exploring from.
     */
    private ReductionGraphNode node;
    
    /**
     * The type of edges we want to explore encoded as 
     * bitwise or of the codes in 
     * <code>ReductionGraphEdge</code>.
     */
    private int allowedEdges;
    
    /**
     * The context we want to explore in.
     */
    private EvaluationCtx ctx;
    
    /**
     * The List of neighboring nodes.
     */
    private List<AbstractPolicy> neighbors;
    
    /**
     * Iterator going through the neighbors.
     */
    private Iterator<AbstractPolicy> neighborIter;
    
    /**
     * Constructor.
     * 
     * @param allowedEdges  The type of edges we want to explore
     *                      as bitwise or of the corresponding 
     *                      codes in <code>ReductionGraphEdge</code>.
     * @param ctx  The context we want to explore in.
     * @param startPolicy  The PolicyId we are exploring from.
     */
    public EdgeExplorer(int allowedEdges, EvaluationCtx ctx,
            URI startPolicy) {
        this.node = ctx.getReductionGraph().getNode(startPolicy);
        this.ctx = ctx;
        this.allowedEdges = allowedEdges;
        // first get the parent policy set from the stack in context
        AbstractPolicy parentPolicySet = ctx.getParentPolicySet();
        if (parentPolicySet != null) {
           this.neighbors = getActiveChilds(parentPolicySet.getChildren(), 
                                            ctx);
        } else {
            this.neighbors = new ArrayList<AbstractPolicy>();
        }
        
        this.neighborIter = this.neighbors.iterator();
        
    }
    
    /**
     * @return  true if there are potential edges left to explore,
     *          false otherwise.
     */
    public boolean hasNext() {
        return this.neighborIter.hasNext();
    }
    
    /**
     * @return  The next node that is reachable by one of the allowed 
     *           edge types, or null if there aren't any more.
     */
    public URI next() {
        if (!this.neighborIter.hasNext()) {       
            return null;
        }
        AbstractPolicy neighbor = (AbstractPolicy)this.neighborIter.next();
        //  First check if there are already matching edges
        Set<ReductionGraphEdge> fromEdges = this.ctx.getReductionGraph().getFromEdges(
                this.node.getNodeId());
        Iterator<ReductionGraphEdge> edgeIt = fromEdges.iterator();
        while (edgeIt.hasNext()) {
            ReductionGraphEdge edge = edgeIt.next();
            if (edge.getTo().equals(neighbor.getId())) {
                // we use bitwise AND to see if the edge type is allowed
                if ((this.allowedEdges & edge.getType()) 
                        == edge.getType()) { 
                    //report this event
                    this.ctx.newEvent(edge);
                    this.ctx.closeCurrentEvent();
                    return neighbor.getId();
                } else if (edge.getType() 
                        == ReductionGraphEdge.NOT_APPLICABLE) {
                    // we don't need to process this any further
                    // so we can go to the next element
                    this.ctx.newEvent(edge);
                    this.ctx.closeCurrentEvent();
                    return next();
                } else if (((this.allowedEdges & ReductionGraphEdge.PP) 
                                == ReductionGraphEdge.PP)
                            && edge.getType() == ReductionGraphEdge.PI) {
                    // we don't need to process this any further
                    // so we can go to the next element
                    this.ctx.newEvent(edge);
                    this.ctx.closeCurrentEvent();
                    return next();
                } else if (((this.allowedEdges & ReductionGraphEdge.DP)
                                == ReductionGraphEdge.DP) 
                            && edge.getType() == ReductionGraphEdge.DI) {
                    // we don't need to process this any further
                    // so we can go to the next element
                    this.ctx.newEvent(edge);
                    this.ctx.closeCurrentEvent();
                    return next();
                }
            }
        }
        //This edge hasn't been evaluated before, so we do it now
        this.ctx.newEvent(neighbor);
        MatchResult matchResult = neighbor.match(this.ctx);
        switch (matchResult.getResult()) {
        case MatchResult.NO_MATCH:
            this.ctx.closeCurrentEvent(
                    new Result(Result.DECISION_NOT_APPLICABLE));
            this.ctx.getReductionGraph().setEdge(new ReductionGraphEdge(
                    this.node.getNodeId(), neighbor.getId(), 
                    ReductionGraphEdge.NOT_APPLICABLE));
            return next();
        case MatchResult.INDETERMINATE:
            this.ctx.closeCurrentEvent(
                    new Result(Result.DECISION_INDETERMINATE, this.ctx));
            if (this.ctx.getDecision() == Result.DECISION_PERMIT) {
                this.ctx.getReductionGraph().setEdge(new ReductionGraphEdge(
                        this.node.getNodeId(), neighbor.getId(),
                        ReductionGraphEdge.PI, 
                        matchResult.getStatus()));
                if ((this.allowedEdges & ReductionGraphEdge.PI)
                        == ReductionGraphEdge.PI) {
                    return neighbor.getId();
                }
            } else { // decision is DENY
                this.ctx.getReductionGraph().setEdge(new ReductionGraphEdge(
                        this.node.getNodeId(), neighbor.getId(), 
                        ReductionGraphEdge.DI,
                        matchResult.getStatus()));
                if ((this.allowedEdges & ReductionGraphEdge.DI)
                        == ReductionGraphEdge.DI) {
                    return neighbor.getId();
                }
            }
            return next();
        case MatchResult.MATCH:
            //evaluate policy
            if (neighbor instanceof PolicySet) {
                this.ctx.saveParentPolicySet(neighbor);
            } else if (neighbor instanceof PolicyReference) {
                PolicyReference ref = (PolicyReference)neighbor;
                if (ref.getReferenceType() 
                        == PolicyReference.POLICYSET_REFERENCE) {
                    this.ctx.saveParentPolicySet(neighbor);
                }
            }
                    
            Result eval = neighbor.getCombiningAlg().combine(this.ctx, 
                    neighbor.getCombiningParameters(),
                    neighbor.getChildElements());
            // check for maxDelegationDepth
            if (this.ctx.getDelegationDepth() 
                    > neighbor.getMaxDelegationDepth()) { 
                this.ctx.closeCurrentEvent("MaxDelegationDepth violated");
                this.ctx.getReductionGraph().setEdge(new ReductionGraphEdge(
                        this.node.getNodeId(), neighbor.getId(), 
                        ReductionGraphEdge.NOT_APPLICABLE));
                return null;
            }
            // Check if it supports the revocation of
            // a policy down the delegation chain
            if (this.ctx.supportsRevocation(neighbor, this.node.getNodeId())) {
                Result nullResult = null;
                this.ctx.closeCurrentEvent(nullResult);
                this.ctx.getReductionGraph().setEdge(new ReductionGraphEdge(
                        this.node.getNodeId(), neighbor.getId(), 
                        ReductionGraphEdge.NOT_APPLICABLE));
                return null; 
            }
            this.ctx.closeCurrentEvent(eval);
            if (neighbor instanceof PolicySet) {
                this.ctx.popParentPolicySet();
                this.ctx.popReductionGraph();
            } else if (neighbor instanceof PolicyReference) {
                PolicyReference ref = (PolicyReference)neighbor;
                if (ref.getReferenceType() 
                        == PolicyReference.POLICYSET_REFERENCE) {
                    this.ctx.popParentPolicySet();
                    this.ctx.popReductionGraph();
                }
            }
            switch (eval.getDecision()) {
            case Result.DECISION_PERMIT :
                if (this.ctx.getDecision() == Result.DECISION_PERMIT) {
                    this.ctx.getReductionGraph().setEdge(
                            new ReductionGraphEdge(
                                    this.node.getNodeId(), neighbor.getId(), 
                                    ReductionGraphEdge.PP));
                    if ((this.allowedEdges & ReductionGraphEdge.PP)
                            == ReductionGraphEdge.PP) {
                        return neighbor.getId();
                    }
                } else if (this.ctx.getDecision() == Result.DECISION_DENY) {
                    this.ctx.getReductionGraph().setEdge(
                            new ReductionGraphEdge(
                                    this.node.getNodeId(), neighbor.getId(), 
                                    ReductionGraphEdge.DP));
                    if ((this.allowedEdges & ReductionGraphEdge.DP)
                            == ReductionGraphEdge.DP) {
                        return neighbor.getId();
                    }
                }
                return next();
            case Result.DECISION_INDETERMINATE :
                if (this.ctx.getDecision() == Result.DECISION_PERMIT) {
                    this.ctx.getReductionGraph().setEdge(
                            new ReductionGraphEdge(
                                    this.node.getNodeId(), neighbor.getId(), 
                                    ReductionGraphEdge.PI, 
                                    eval.getStatus()));

                    if ((this.allowedEdges & ReductionGraphEdge.PI)
                            == ReductionGraphEdge.PI) {
                        return neighbor.getId();
                    }
                } else if (this.ctx.getDecision() == Result.DECISION_DENY) {
                    this.ctx.getReductionGraph().setEdge(
                            new ReductionGraphEdge(
                                    this.node.getNodeId(), neighbor.getId(), 
                                    ReductionGraphEdge.DI, 
                                    eval.getStatus()));
                    if ((this.allowedEdges & ReductionGraphEdge.DI)
                            == ReductionGraphEdge.DI) {
                        return neighbor.getId();
                    }
                }
                return next();
            default:
                this.ctx.getReductionGraph().setEdge(new ReductionGraphEdge(
                        this.node.getNodeId(), neighbor.getId(), 
                        ReductionGraphEdge.NOT_APPLICABLE));
                return next();            
            }
        }
        return next();
    }
    
    /**
     * Private helper that builds the list of active child policies/policy sets
     * from the list of all childs and the list of deactivated childs.
     * Used in reduction steps of a delegation chain.
     * 
     * @param allChilds  a <code>List</code> of <code>CombinerElement</code>s.
     * @param context    the context containing the deactivated childs.
     * 
     * @return  A list of <code>CombinerElemet</code>s.
     */
    private List<AbstractPolicy> getActiveChilds(List<PolicyTreeElement> allChilds, EvaluationCtx context) {
        List<AbstractPolicy> activeChilds = new ArrayList<AbstractPolicy>();
        Iterator<PolicyTreeElement> iter = allChilds.iterator();
        while (iter.hasNext()) {
            AbstractPolicy policy = (AbstractPolicy)iter.next();
            URI elementId = null;
            if (policy instanceof PolicyReference) {
                //PolicyReferences get special treatment here. 
                //this is due to the fact that in certain cases we do not
                // want to run the resolvePolicy() method of a policy
                // reference here, which would be the case if we used getId().
                // An example for such cases can be found in the XACML 2.0
                // test cases, test group E, test 003.
                PolicyReference pr = (PolicyReference)policy;
                elementId = pr.getReference();
            } else {
                elementId = policy.getId();
            }
           if (!context.getInactivePolicyIds().contains(elementId)) {
                   activeChilds.add(policy);
           }
        }
        return activeChilds;
    }
    
    
    
}
