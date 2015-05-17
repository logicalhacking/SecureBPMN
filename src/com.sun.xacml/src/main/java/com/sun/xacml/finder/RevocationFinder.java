
/*
 * @(#)RevocationFinder.java
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

import com.sun.xacml.AbstractPolicy;
import com.sun.xacml.EvaluationCtx;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is used by the PDP to find revocations of policies
 *
 * @since 3.0
 * @author Ludwig Seitz
 */
public class RevocationFinder implements Cloneable {

    // the list of all modules
    private List<RevocationFinderModule> allModules = null;

    /**
     * Default constructor.
     */
    public RevocationFinder() {
        this.allModules = new ArrayList<RevocationFinderModule>();
    }

    /**
     * The clone method.
     * 
     * @return  a copy of this object
     */
    public Object clone() {
        try {
            RevocationFinder clone = (RevocationFinder)super.clone();
            clone.allModules = new ArrayList<RevocationFinderModule>(this.allModules);
            return clone;
        } catch (CloneNotSupportedException e) {//this should never happen
            throw new RuntimeException("Couldn't clone RevocationFinder");
        }
    }

    /**
     * Returns the ordered <code>List</code> of modules used by this class
     * to find policy revocations
     * 
     * @return the list of modules used by this class
     */
    public List<RevocationFinderModule> getModules() {
        return new ArrayList<RevocationFinderModule>(this.allModules);
    }

    /**
     * Sets the ordered <code>List</code> of modules used by this class
     * to find attribute values. The ordering will be maintained.
     *
     * @param modules the modules this class will use
     */
    public void setModules(List<RevocationFinderModule> modules) {
        this.allModules = new ArrayList<RevocationFinderModule>(modules);   
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
        if (this.allModules != null) {
            Iterator<RevocationFinderModule> it = this.allModules.iterator();
            
            // go through each module in order
            while (it.hasNext()) {
                RevocationFinderModule module = it.next();
       
                // see if the module can find a revocation
                boolean result = module.supportsRevocation(policy,
                                                           candidate,
                                                           context);
                
                if (result == true) {
                    // we found a revocation
                    return true;
                }
            }
        }
        // we found no revocation or there is no revocation finder module
        return false;
   
    }
}
