/*
 * @(#)ReductionGraphNode.java
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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.sun.xacml.AbstractPolicy;
import com.sun.xacml.Obligation;
import com.sun.xacml.ctx.PolicyIssuer;
import com.sun.xacml.ctx.Result;

public class ReductionGraphNode {

    /**
     * Static value for the reducable* members.
     */
    public static final int UNKNOWN = -1;
    
    /**
     * Static value for the reducable* members.
     */
    public static final int NO = 0;
    
    /**
     * Static value for the reducable* members.
     */
    public static final int YES = 1;
    
    /**
     * Describes if this node can be reduced to a trusted
     * node by PP edges. 
     */    
    private int reducablePP;
    
    /**
     * Describes if this node can be reduced to a trusted
     * node by PP and PI edges. 
     */   
    private int reducablePI;
    
    /**
     * Describes if this node can be reduced to a trusted
     * node by DP edges. 
     */
    private int reducableDP;
    
    /**
     * Describes if this node can be reduced to a trusted
     * node by DP and DI edges. 
     */
    private int reducableDI;
    
    /**
     * Saves the length of the shortest path for a reducable node.
     */
    private int reductionPathLength;
    
    /**
     * Describes if this is a trusted node.
     */
    private boolean trusted;
    
    /**
     * Node id.
     */
    private URI nodeId;
    
    /**
     * The obligations related to this nodes reduction path.
     */
    private Set<Obligation> obligations;
    
    /**
     * The policy issuer of the policy represented by this node.
     */
    private PolicyIssuer issuer;
    
    /**
     * Creates a new untrusted node.
     * 
     * @param policy  The policy which is represented by the node.
     */
    public ReductionGraphNode(AbstractPolicy policy) {
        this.trusted = policy.hasTrustedIssuer();
        this.nodeId = policy.getId();
        if (this.trusted) {
            this.reducablePP = YES;
            this.reducablePI = YES;
            this.reducableDP = YES;
            this.reducableDI = YES;
            this.reductionPathLength = 0;
        } else {
            this.reducablePP = UNKNOWN;
            this.reducablePI = UNKNOWN;
            this.reducableDP = UNKNOWN;
            this.reducableDI = UNKNOWN;
            this.reductionPathLength = UNKNOWN;
        }
        this.obligations = new HashSet<Obligation>();
        this.obligations.addAll(policy.getObligations());
        this.issuer = policy.getPolicyIssuer();
    
    }

    /**
     * @return  Can this node be reduced to a trusted node by DP and DI edges.
     *          Meaning of the return values as in the static declarations of
     *          this class.
     */
    public int getReducableDI() {
        return this.reducableDI;
    }

    /**
     * Set new value for reducableDI.  Meaning of the values as in the static
     * declarations of this class. You also need to set the length of
     * the reduction path.
     * 
     * @param reducableDI
     * @param reductionPathLength
     */
    public void setReducableDI(int reducableDI, int reductionPathLength) {
        this.reducableDI = reducableDI;
        this.reductionPathLength = reductionPathLength;
    }

    /**
     * @return  Can this node be reduced to a trusted node by DP edges.
     *          Meaning of the return values as in the static declarations of
     *          this class.
     */
    public int getReducableDP() {
        return this.reducableDP;
    }

    /**
     * Set new value for reducableDP.  Meaning of the values as in the static
     * declarations of this class. You also need to set the length of
     * the reduction path.
     * 
     * @param reducableDP
     * @param reductionPathLength
     */
    public void setReducableDP(int reducableDP, int reductionPathLength) {
        this.reducableDP = reducableDP;
        this.reductionPathLength = reductionPathLength;
    }

    /**
     * @return  Can this node be reduced to a trusted node by PP and PI edges.
     *          Meaning of the return values as in the static declarations of
     *          this class.
     */
    public int getReducablePI() {
        return this.reducablePI;
    }

    /**
     * Set new value for reducablePI.  Meaning of the values as in the static
     * declarations of this class. You also need to set the length of
     * the reduction path.
     * 
     * @param reducablePI
     * @param reductionPathLength
     */
    public void setReducablePI(int reducablePI, int reductionPathLength) {
        this.reducablePI = reducablePI;
        this.reductionPathLength = reductionPathLength;
    }

    /**
     * @return  Can this node be reduced to a trusted node by PP.
     *          Meaning of the return values as in the static declarations of
     *          this class.
     */
    public int getReducablePP() {
        return this.reducablePP;
    }
    
    /**
     * Set new value for reducablePP.  Meaning of the values as in the static
     * declarations of this class. You also need to set the length of
     * the reduction path.
     * 
     * @param reducablePP
     * @param reductionPathLength
     */
    public void setReducablePP(int reducablePP, int reductionPathLength) {
        this.reducablePP = reducablePP;
        this.reductionPathLength = reductionPathLength;
    }

    /**
     * Set the state of the reducable variables based on a reduction result
     * and the type of reduction (i.e. PP, PP-PI, DP, DP-DI).
     * 
     * @param result  The reduction result (PERMIT or null)
     * @param reductionType  The int code determining the allowed edges 
     *                       for this type of reduction. A bitwise or
     *                       of the codes from 
     *                       <code>ReductionGraphEdge</code>.
     * @param delegationDepth  The length of the reduction path. 
     */
    public void setState(Result result, int reductionType, 
                         int delegationDepth) {
        if (reductionType 
                == (ReductionGraphEdge.PP | ReductionGraphEdge.PI)) {
            if (result == null) {
                this.reducablePP = ReductionGraphNode.NO;
                this.reducablePI = ReductionGraphNode.NO;
            } else {
                this.reducablePI = ReductionGraphNode.YES;
                this.reductionPathLength = delegationDepth;
            }
        } else if (reductionType == ReductionGraphEdge.PP) {
            if (result == null) {
                this.reducablePP = ReductionGraphNode.NO;
            } else {
                this.reducablePP = ReductionGraphNode.YES;
                this.reducablePI = ReductionGraphNode.YES;  
                this.reductionPathLength = delegationDepth;
            }
        } else if (reductionType
                    == (ReductionGraphEdge.DP | ReductionGraphEdge.DI)) {
            if (result == null) {
                this.reducableDP = ReductionGraphNode.NO;
                this.reducableDI = ReductionGraphNode.NO;
            } else {
                this.reducableDI = ReductionGraphNode.YES;
                this.reductionPathLength = delegationDepth;
            }
        } else if (reductionType == ReductionGraphEdge.DP){           
            if (result == null) {
                this.reducableDP = ReductionGraphNode.NO;
            } else {
                this.reducableDP = ReductionGraphNode.YES;
                this.reducableDI = ReductionGraphNode.YES;
                this.reductionPathLength = delegationDepth;
            }
        } else {
            throw new RuntimeException("invalid reductionType =="
                    + reductionType);
        }
        
    }
    
    /**
     * Determines if the node is already reduced for this type
     * of reduction (i.e. PP, PP-PI, DP, DP-DI).
     * 
     * @param reductionType  The int code determining the allowed edges 
     *                       for this type of reduction. A bitwise or
     *                       of the codes from 
     *                       <code>ReductionGraphEdge</code>.
     * @return  The result of the previous reduction.                       
     */
    public Result previousReduction(int reductionType) {
        if ((reductionType & ReductionGraphEdge.PP)
                == ReductionGraphEdge.PP) {
            if (this.reducablePP == ReductionGraphNode.YES) {
                return new Result(Result.DECISION_PERMIT, this.obligations);
            } else if ((reductionType & ReductionGraphEdge.PI)
                    == ReductionGraphEdge.PI) {
                if (this.reducablePI == ReductionGraphNode.YES) {
                    return new Result(Result.DECISION_INDETERMINATE);
                } else if (this.reducablePI == ReductionGraphNode.NO) {
                    return null;
                }                
            } else if (this.reducablePP == ReductionGraphNode.NO) {
                return null;
            }
        } else if ((reductionType & ReductionGraphEdge.DP)
                        == ReductionGraphEdge.DP) {
            if (this.reducableDP == ReductionGraphNode.YES) {
                return new Result(Result.DECISION_PERMIT, this.obligations);
            } else if ((reductionType & ReductionGraphEdge.DI)
                        == ReductionGraphEdge.DI) {
                if (this.reducableDI == ReductionGraphNode.YES) {
                    return new Result(Result.DECISION_INDETERMINATE);
                } else if (this.reducableDI == ReductionGraphNode.NO) {
                    return null;
                }  
            } else if (this.reducableDP == ReductionGraphNode.NO) {
                return null;
            }
        }
        //means this node has not yet been reduced
        return new Result(Result.DECISION_DENY);
    }
    
    /**
     * @return  True if this node is trusted.
     */
    public boolean isTrusted() {
        return this.trusted;
    }

    /**
     * @return  The node id.
     */
    public URI getNodeId() {
        return this.nodeId;
    }

    /**
     * @return  The policy issuer.
     */
    public PolicyIssuer getIssuer() {
        return (PolicyIssuer)this.issuer.clone();
    }
    
    /**
     * @return  A <code>Set</code> of <code>Obligation</code>s related
     *          to this nodes reduction path.
     */
    public Set<Obligation> getObligations() {
        return Collections.unmodifiableSet(this.obligations);
    }
    
    /**
     * @return the reductionPathLength.
     */
    public int getReductionPathLength() {
        return this.reductionPathLength;
    }

    /**
     * Add new obligations to this nodes set of obligations.
     * 
     * @param obligations  the new obligations, a <code>Set</code>
     *                     of <code>Obligation</code>s.
     */
    public void addObligations(Set<Obligation> obligations) {
        this.obligations.addAll(obligations);
    }
    
    /**
     * Encode this node to string for debugging.
     * 
     * @return  String representation of the graph.
     */
    public String toString() {
        String [] values = {"Unknown", "NO", "YES"};
        return this.nodeId.toString() + "{PP=" + values[this.reducablePP+1]
       + ", PI=" + values[this.reducablePI+1] + ", DP="
       + values[this.reducableDP+1] + ", DI=" 
       + values[this.reducableDI+1] + "}";
    }

}
