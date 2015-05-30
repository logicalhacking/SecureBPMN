/*
 * @(#)ReductionGraph.java
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.xacml.AbstractPolicy;
import com.sun.xacml.Constants;
import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.Obligation;
import com.sun.xacml.PolicyTreeElement;
import com.sun.xacml.ctx.RequestElement;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.ctx.Status;

/**
 * This class saves a graph structure for reducing untrusted policies.
 * 
 * @author Ludwig Seitz
 *
 */
public class ReductionGraph implements Cloneable {   
   
    /**
     * <code>Set</code>s of <code>ReductionGraphEdge</code>s of the graph 
     * keyed by the nodeIds (i.e. policyId <code>URI</code>s) they go out from.
     */
    private Map<URI, Set<ReductionGraphEdge>> fromEdges;
    
    /**
     * <code>Set</code>s of <code>ReductionGraphEdge</code>s of the graph 
     * keyed by the nodeIds (i.e. policyId <code>URI</code>s) they go in to.
     */
    private Map<URI, Set<ReductionGraphEdge>> toEdges;
    
    /**
     * The <code>ReductionGraphNode</code>s keyed by the id.
     */
    private Map<URI, ReductionGraphNode> nodes;
    
    /**
     * Constructor, starts a new ReductionGraph.
     * 
     * @param pps  The parent policy set, from which we gather the nodes
     *             of the graph.
     */
    public ReductionGraph(AbstractPolicy pps) {
        this.fromEdges = new HashMap<URI, Set<ReductionGraphEdge>>();
        this.toEdges = new HashMap<URI, Set<ReductionGraphEdge>>();
        this.nodes = new HashMap<URI, ReductionGraphNode>();
        if (pps != null) {
            Iterator<PolicyTreeElement> iter = pps.getChildren().iterator();
            while(iter.hasNext()) {
                AbstractPolicy policy = (AbstractPolicy)iter.next();
                this.nodes.put(policy.getId(), new ReductionGraphNode(policy));
            }
        }
    }
    
    /**
     * The clone method. 
     * FIXME: this does no deep copy on the content of the Maps.
     * 
     * @return  a copy of this object.
     */
    public Object clone() {
        try {
            ReductionGraph clone = (ReductionGraph) super.clone();
            clone.fromEdges = new HashMap<URI, Set<ReductionGraphEdge>>(this.fromEdges);
            clone.toEdges = new HashMap<URI, Set<ReductionGraphEdge>>(this.toEdges);
            clone.nodes = new HashMap<URI, ReductionGraphNode>(this.nodes);
            return clone;
        } catch (CloneNotSupportedException e) {//this should never happen
            throw new RuntimeException("Couldn't clone ReductionGraph");
        }
    }
    
    /**
     * A private helper that does the reduction of an untrusted policy or
     * policyset. 
     *  
     * @param ctx  The evaluation context.
     * @param result  The evaluation result of this policy.
     * @param policyId  The id of the policy being reduced.
     * 
     * @return  The adapted evaluation result of this policy
     *          (see standard).
     */
    public Result reduce(EvaluationCtx ctx, Result result, URI policyId) {
      int allowedEdges = 0;
      int extendedAllowedEdges = 0;
      if (result.getDecision() == Result.DECISION_PERMIT) {
          allowedEdges = ReductionGraphEdge.PP;
          extendedAllowedEdges 
              = ReductionGraphEdge.PP | ReductionGraphEdge.PI;
      } else if (result.getDecision() == Result.DECISION_DENY) {
          allowedEdges = ReductionGraphEdge.DP;
          extendedAllowedEdges 
              = ReductionGraphEdge.DP | ReductionGraphEdge.DI;
      } else if (result.getDecision() == Result.DECISION_INDETERMINATE) {
          allowedEdges 
              = ReductionGraphEdge.PP | ReductionGraphEdge.PI;
          extendedAllowedEdges 
              = ReductionGraphEdge.DP | ReductionGraphEdge.DI;
      }
      
      Result reductionResult = graphSearch(allowedEdges, ctx, policyId);
      if(reductionResult == null) {
          reductionResult = graphSearch(extendedAllowedEdges, ctx, policyId);
          if (reductionResult == null) {
              return null;
          }
          List<String> codes = new ArrayList<String>(); 
          codes.add(Status.STATUS_PROCESSING_ERROR);
          return new Result(Result.DECISION_INDETERMINATE,
                            new Status(codes, "Error while reducing"));
      }
      return reductionResult; // Permit because reductionResult != null
    }
    
    private Result graphSearch(int allowedEdges, EvaluationCtx ctx,
                                URI policyId) {
        // Get the node.
        ReductionGraphNode node = (ReductionGraphNode)this.nodes.get(policyId);
        
        // Prepare the 'discarded' result (see standard).
        Result nullResult = null;
        
        // Check if there is a parent policy set
        if (node == null) {
            ctx.newEvent(Integer.valueOf(allowedEdges));
            ctx.closeCurrentEvent(nullResult);
            return null;
        } 
        
        // Recursion end clause
        if (node.isTrusted()) {
            return new Result(Result.DECISION_PERMIT, node.getObligations());
        }
        
        // Signal the reduction event
        ctx.newEvent(Integer.valueOf(allowedEdges));   
       
        Result result = node.previousReduction(allowedEdges);
        if (result == null || result.getDecision() != Result.DECISION_DENY) {
            //DENY means that the node has not yet been reduced.
            //Its a fix so we can just transmit the result.
            ctx.closeCurrentEvent(result);
            return result;
        }
                
        // create an administrative context
        int newDecision = ctx.getDecision();
        //Figure out decision if necessary
        if (newDecision == Result.INVALID_DECISION) {
            if ((allowedEdges & ReductionGraphEdge.PP)
                    == ReductionGraphEdge.PP) {
                newDecision = Result.DECISION_PERMIT;
            } else if ((allowedEdges & ReductionGraphEdge.DP)
                    == ReductionGraphEdge.DP) {
                newDecision = Result.DECISION_DENY;
            } else { // this should never happen because decision should be 
                     // permit or deny.
                //close the reduction event
                ctx.closeCurrentEvent(nullResult);  
                return null;            
            }
        }        
        Set<RequestElement> newDelegate = null;
        // btw: we can safely assume that the policyIssuer is not 
        // null here otherwise this function would not have been called.
        newDelegate = new HashSet<RequestElement>();
        RequestElement delegate = new RequestElement(Constants.DELEGATE,
                node.getIssuer().getAttributes());
        newDelegate.add(delegate);            
        EvaluationCtx admCtx = ctx.createAdminCtx(newDecision, 
                                                  newDelegate);

        // deactivate this policy, we don't want to search it again.
        admCtx.addInactivePolicyId(node.getNodeId());
        
        // Create edgeExplorer to go through the graph edges from this node
        EdgeExplorer potentialEdges = new EdgeExplorer(allowedEdges, admCtx,
                node.getNodeId());
        while (potentialEdges.hasNext()) {
            URI nextPolicyId = potentialEdges.next();
            if (nextPolicyId == null) {
                ctx.closeCurrentEvent(nullResult);
                node.setState(null, allowedEdges, 
                              admCtx.getDelegationDepth());
                return null;
            }
            result = graphSearch(allowedEdges, admCtx, nextPolicyId);
            if (result != null &&
                    result.getDecision() == Result.DECISION_PERMIT) {
                ctx.closeCurrentEvent(result);
                Set<Obligation> obligations = new HashSet<Obligation>(node.getObligations());
                obligations.addAll(result.getObligations());
                node.setState(result, allowedEdges,
                              admCtx.getDelegationDepth());
                //now return the result
                return new Result(Result.DECISION_PERMIT, obligations);
            }
        }
        ctx.closeCurrentEvent(nullResult);
        node.setState(nullResult, allowedEdges, admCtx.getDelegationDepth());
        return null;
    }
   
    /**
     * Return the type of edge there is for two policies identified
     * by their PolicyId <code>URI</code>s.
     * 
     * @param from  The policy from which the edge originates.
     * @param to  The policy to which the edge goes.
     * 
     * @return  the type of edge as in the static list above.
     */
    public int getEdge(URI from, URI to) {
        Set<ReductionGraphEdge> rteSet = this.fromEdges.get(from);
        if (rteSet == null) {
            return ReductionGraphEdge.NOT_EVALUATED;
        }
        Iterator<ReductionGraphEdge> iter = rteSet.iterator();
        while (iter.hasNext()) {
            ReductionGraphEdge rte = (ReductionGraphEdge)iter.next();
            if (rte.getTo().equals(to)) {
                return rte.getType();
            }
        }        
        //if we arrived here, no edges between the two policies where found
        return ReductionGraphEdge.NOT_EVALUATED;  
        
    }
    
    /**
     * Set an edge in the ReductionGraph.
     * 
     * @param edge  The edge to be set.
     */
    public void setEdge(ReductionGraphEdge edge) {
        Set<ReductionGraphEdge> fromSet = this.fromEdges.get(edge.getFrom());
        if (fromSet == null) {
            fromSet = new HashSet<ReductionGraphEdge>();
            this.fromEdges.put(edge.getFrom(), fromSet);
        }
        fromSet.add(edge);
        Set<ReductionGraphEdge> toSet = this.toEdges.get(edge.getTo());
        if (toSet == null) {
            toSet = new HashSet<ReductionGraphEdge>();
            this.toEdges.put(edge.getTo(), toSet);
        }
        toSet.add(edge);
    }
    
    /**
     * Returns the <code>Set</code> of <code>ReductionGraphEdge</code>s
     * that go out from a specific policy.
     * 
     * @param from  The policy identified its PolicyId <code>URI</code>.
     * 
     * @return  A <code>Set</code> of <code>ReductionGraphEdge</code>s.
     */
    public Set<ReductionGraphEdge> getFromEdges(URI from) {
        Set<ReductionGraphEdge> result = this.fromEdges.get(from);
        if (result == null) {
            result = new HashSet<ReductionGraphEdge>();
            this.fromEdges.put(from, result);
        }
        return result;
    } 
    
    /**
     * Returns the <code>Set</code> of <code>ReductionGraphEdge</code>s
     * that go to a specific policy.
     * 
     * @param to  The policy identified its PolicyId <code>URI</code>.
     * 
     * @return  A <code>Set</code> of <code>ReductionGraphEdge</code>s.
     */
    public Set<ReductionGraphEdge> getToEdges(URI to) { 
        Set<ReductionGraphEdge> result = this.fromEdges.get(to);
        if (result == null) {
            result = new HashSet<ReductionGraphEdge>();
            this.fromEdges.put(to, result);
        }
        return result;
    }
    
    /**
     * Get a node from the graph.
     * 
     * @param nodeId  The node's id.
     * 
     * @return  The node.
     */
    public ReductionGraphNode getNode(URI nodeId) {
        return (ReductionGraphNode)this.nodes.get(nodeId);
    }
    
    /**
     * Encode to string for debugging
     * 
     * @return  String representation of the graph.
     */
    public String toString() {
        String result = "";
        Iterator<Map.Entry<URI, ReductionGraphNode>> iter = this.nodes.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<URI, ReductionGraphNode> entry =  iter.next();
            ReductionGraphNode node = (ReductionGraphNode) entry.getValue();
            result += node.toString();
            if (this.fromEdges.get(entry.getKey()) != null) {
                Iterator<ReductionGraphEdge> iter2 = (this.fromEdges.get(entry.getKey())).iterator();
                while (iter2.hasNext()) {
                    ReductionGraphEdge edge = iter2.next();
                    result += "[" + edge.toString() + "] ";
                }
            }
            result += Constants.nl;
        }
        return result;
    }
}


 