
/*
 * @(#)RevocationFinderModule.java
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

package com.sun.xacml.finder;

import java.net.URI;

import com.sun.xacml.AbstractPolicy;
import com.sun.xacml.EvaluationCtx;

/**
 * This is the abstract class that all <code>RevocationFinder</code> modules
 * extend. All methods have default values to represent that the given
 * feature isn't supported by this module, so module writers needs only
 * implement the methods for the features they're supporting.
 *
 * @since 3.0
 * @author Ludwig Seitz
 */
public abstract class RevocationFinderModule
{

    /**
     * Returns this module's identifier. A module does not need to provide
     * a unique identifier, but it is a good idea, especially in support of
     * management software. Common identifiers would be the full package
     * and class name (the default if this method isn't overridden), just the
     * class name, or some other well-known string that identifies this class.
     *
     * @return this module's identifier
     */
    public String getIdentifier() {
        return getClass().getName();
    }
    
    /**
     * Checks whether a policy supports the revocation of a candidate policy.
     *  
     * @param policy  The policy that may support a revocation
     * @param candidate  The id of the policy or policy set that is candidate
     *                   for revocation.
     * @param context  the context in which this policy may support revocations
     * 
     * @return true  if this policy supports a the revocation of the candidate,
     *               false otherwise. 
     */
    public boolean supportsRevocation(AbstractPolicy policy, URI candidate, 
                                      EvaluationCtx context) {
        return false;
    }
}

