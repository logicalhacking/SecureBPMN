/*
 * @(#)ReductionGraphEdge.java
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

import com.sun.xacml.ctx.Status;

/**
 * This class stores an edge of the reduction graph.
 * 
 * @author Ludwig Seitz
 *
 */
public class ReductionGraphEdge {
    
    /**
     * An edge that has not yet been evaluated.
     * Binary encoding for bitwise manipulation.
     * Only PP | PI and DP | DI are possible making
     * the values 1, 2, 3, 4 and 12 reserved. Leaving
     * 0 for NOT_EVALUATED and 13 for NOT_APPLICABLE
     */
    public static final int NOT_EVALUATED = 0;
    
    /**
     * An edge that reduces PERMIT to PERMIT.
     */
    public static final int PP = 1;
    
    /**
     * An edge that reductes PERMIT to INDETERMINATE
     */
    public static final int PI = 2;
    
    
    /**
     * An edge that reductes DENY to PERMIT
     */
    public static final int DP = 4;
    
    /**
     * An edge that reductes DENY to INDETERMINATE
     */
    public static final int DI = 8;
    
    /**
     * An edge that reduces to NOT_APPLICABLE
     */
    public static final int NOT_APPLICABLE = 16;
    
    /**
     * The policyId <code>URI</code> this edge originates in.
     */
    private URI from;
    
    /**
     * The policyId <code>URI</code> this edge points to.
     */
    private URI to;
    
    /**
     * The type of edge this is from the types specified in 
     * ReductionGraph class.
     */
    private int type;
    
    /**
     * The status for a PI or DI edge. Null for all other types.
     */
    private Status status;
    
    /**
     * Constructor.
     * 
     * @param from  The policyId <code>URI</code> this edge originates in.
     * @param to  The policyId <code>URI</code> this edge points to.
     * @param type The type of edge this is, from the types specified 
     *             in ReductionGraph class.
     */
    public ReductionGraphEdge(URI from, URI to, int type) {
        this(from, to, type, null);
    }
    
    /**
     * Constructor.
     * 
     * @param from  The policyId <code>URI</code> this edge originates in.
     * @param to  The policyId <code>URI</code> this edge points to.
     * @param type The type of edge this is, from the types specified 
     *             in ReductionGraph class.
     */
    public ReductionGraphEdge(URI from, URI to, Integer type) {
        this(from, to, type, null);
    }
    
    /**
     * Constructor.
     * 
     * @param from  The policyId <code>URI</code> this edge originates in.
     * @param to  The policyId <code>URI</code> this edge points to.
     * @param type The type of edge this is, from the types specified 
     *             in ReductionGraph class.
     * @param status  The status for a PI or DI edge.
     */
    public ReductionGraphEdge(URI from, URI to, int type, Status status) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.status = status;
    }
    
    /**
     * Constructor.
     * 
     * @param from  The policyId <code>URI</code> this edge originates in.
     * @param to  The policyId <code>URI</code> this edge points to.
     * @param type The type of edge this is, from the types specified 
     *             in ReductionGraph class.
     * @param status  The status for a PI or DI edge.
     */
    public ReductionGraphEdge(URI from, URI to, Integer type, Status status) {
        this.from = from;
        this.to = to;
        this.type = type.intValue();
        this.status = status;
    }
    
    
    /**
     * @return  The policyId <code>URI</code> this edge originates in.
     */
    public URI getFrom() {
        return this.from;
    }
    
    /**
     * @return  The policyId <code>URI</code> this edge points to.
     */
    public URI getTo() {
        return this.to;
    }
    
    /**
     * @return  The type of edge this is from the types specified in 
     * ReductionGraph class.
     */
    public int getType() {
        return this.type;
    }
    
    /**
     * @return  The <code>Status</code> or null if there isn't one.
     */
    public Status getStatus() {
        return this.status;
    }
    
    /**
     * Encode edge to string for debugging.
     * 
     * @return  String representation of the graph edge.
     */
    public String toString() {
        //Values for the reduction graph edges. 
        //Currently only the values NE, PP, (PP,PI), DP, (DP,DI) and NA
        //are legal
        String [] values = {"NE", "PP", "PI", "PP,PI", "DP", "PP,DP", 
                "PI,DP", "PP,PI,DP", "DI", "PP,DI", "PI,DI", "PP,PI,DI",
                "DP,DI", "PP,DP,DI", "PP,PI,DI", "PP,PI,DP,DI", "NA"};
        return values[this.type] + "->" + this.to.toString();
    }
}
