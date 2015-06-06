/*
 * @(#)PolicyIssuer.java
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

package com.sun.xacml.ctx;

import java.net.URI;
import java.util.Date;
import java.util.Set;


/**
 * Represents a policy issuer in the list of indirect delegates of an administrative
 *  request made to the PDP. This is the class contains a set of attributes for the 
 *  policy issuer and an issue date, that specifies when the policy was issued. 
 *  The date is used for request evaluation in a historic attribute model.
 *
 * @since 3.0
 * @author Ludwig Seitz
 */
public class PolicyIssuer extends RequestElement {

    /**
     * the issue instant member variable, can be null
     */
    private Date issueDate = null;
    
    /**
     * Constructor. Creates a policy issuer out of the components.
     *
     * @param attributes  must be a <code>Set</code>s of 
     *                     <code>Attribute</code>s
     */
    public PolicyIssuer(Set<Attribute> attributes) {
        super(URI.create("PolicyIssuer"), attributes);
        this.issueDate = null;
    }
    
    /**
     * Constructor. Creates a policy issuer out of it's attributes and the 
     * policy issue date
     *
     * @param attributes  must be a set of <code>Attribute</code> objects
     * @param issueDate  the issue date of the policy. May be null.
     */
    public PolicyIssuer(Set<Attribute> attributes, Date issueDate) {
        super(URI.create("PolicyIssuer"), attributes);
        if (issueDate != null) {
            this.issueDate = (Date)issueDate.clone();
        }        
    }
   
    /**
     * The clone method.
     * 
     * @return  a copy of this object.
     */
    public Object clone() {
        PolicyIssuer clone = (PolicyIssuer)super.clone();
        if (this.issueDate != null) {
            this.issueDate = (Date)this.issueDate.clone();
        }
        return clone;
    }
    
    /**
     * Get the issue date as a <code>Date</code> object
     * of this indirect delegate.
     * 
     * @return the issue date, may be null
     */
    public Date getIssueDate() {
        if (this.issueDate != null) {
            return (Date)this.issueDate.clone();
        }
        return null;
    }
    
    /**
     * Set the issueDate from a <code>Date</code> object.
     * @param issueDate  the issueDate to be set
     */
    public void setDate(Date issueDate) {
        this.issueDate = (Date)issueDate.clone();
    }
}
