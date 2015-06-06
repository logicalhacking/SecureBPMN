
/*
 * @(#)DecisionAttribute.java
 *
 * Copyright 2005-2006 Swedish Institute of Computer Science All Rights Reserved.
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

package com.sun.xacml.attr;

import java.net.URI;

import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.Result;

/**
 * Convenience class that represents the Decision attribute.
 * 
 * @author Ludwig Seitz
 *
 */
public class DecisionAttribute extends Attribute {
	
	
	/**
     * The static id URI of the Decision attribute
     */
    private static URI idURI;

    /**
     * RuntimeException that wraps an Exception thrown during the
     * creation of idURI, null if none.
     */
    private static RuntimeException earlyException;
    
    /**
     * Static initializer that initializes the identifierURI
     * class field so that we can catch any exceptions thrown
     * by URI(String) and transform them into a RuntimeException.
     * Such exceptions should never happen but should be reported
     * properly if they ever do.
     */
    static {
        try {
            idURI = URI.create(TypeIdentifierConstants.DECISION);
        } catch (IllegalArgumentException e) {
            earlyException = e;
        }
    }
    
    /**
     * Constructor that creates a Decision Attribute.
     *
     * @param decision  The decision code from the <code>Result</code> class.
     */
    public DecisionAttribute(int decision) {
        super(DecisionAttribute.idURI, null, 
                new StringAttribute((String)Result.DECISIONS.get(decision)));
        if (earlyException != null) {
            throw earlyException;
        }
        if (decision != Result.DECISION_DENY 
        		&& decision != Result.DECISION_PERMIT) {
        	throw new IllegalArgumentException("Decision attribute value must"
        			+ " be 'PERMIT' or 'DENY'");
        	
        }
    }
    
}
