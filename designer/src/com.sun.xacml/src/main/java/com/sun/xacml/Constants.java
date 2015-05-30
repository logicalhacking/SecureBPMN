/*
 * @(#)Constants.java
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

package com.sun.xacml;

import java.net.URI;

/**
 * A collection of XACML constants used across various classes.
 *
 * @since 3.0
 * @author Ludwig Seitz
 */
public class Constants {
    
    /**
     * The standard URI for listing a resource's id
     */
    public static final URI RESOURCE_ID =
        URI.create("urn:oasis:names:tc:xacml:1.0:resource:resource-id");

    /**
     * The standard URI for listing a subject's id
     */
    public static final URI SUBJECT_ID = 
    	URI.create("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
    
    /**
     * The standard URI for listing a subject's id
     */
    public static final URI ACTION_ID = 
    	URI.create("urn:oasis:names:tc:xacml:1.0:action:action-id");
    
    /**
     * The standard URI for listing a resource's scope
     */
    public static final URI RESOURCE_SCOPE =
        URI.create("urn:oasis:names:tc:xacml:1.0:resource:scope");
    
    /**
     * The standard Subject category (compatibility code 
     * for XACML 2.0)
     */
    public static final URI SUBJECT_CAT = URI.create(
        "urn:oasis:names:tc:xacml:1.0:subject-category:access-subject");
    
    /**
     * The standard Resource category.
     */
    public static final URI RESOURCE_CAT = URI.create(
        "urn:oasis:names:tc:xacml:3.0:attribute-category:resource");
    
    /**
     * The standard Resource category.
     */
    public static final URI ACTION_CAT = URI.create(
        "urn:oasis:names:tc:xacml:3.0:attribute-category:action");
    
    /**
     * The standard Resource category.
     */
    public static final URI ENVIRONMENT_CAT = URI.create(
        "urn:oasis:names:tc:xacml:3.0:attribute-category:environment"); 
    
    /**
     * Resource scope of Immediate (only the given resource)
     */
    public static final int SCOPE_IMMEDIATE = 0;

    /**
     * Resource scope of Children (the given resource and its direct
     * children)
     */
    public static final int SCOPE_CHILDREN = 1;

    /**
     * Resource scope of Descendants (the given resource and all descendants
     * at any depth or distance)
     */
    public static final int SCOPE_DESCENDANTS = 2;
    
    /**
     * Resource scope of XPath-expression (a set of nodes selected by an
     * XPath expression)
     */
    public static final int SCOPE_XPATH_EXPRESSION = 3;
    
    /**
     * Resource scope of EntireHierarchy (a node and all it's descendants)
     */
    public static final int SCOPE_ENTIRE_HIERARCHY = 4;
    
    /**
     * Scope of multiple elements. This applies to requests with multiple
     * Resources for XACML versions < 3.0 and to requests with multiple 
     * Attributes elements of the same category for XACML versions >= 3.0
     */
    public static final int SCOPE_MULTIPE_ELEMENTS = 5;
    
    /**
     * A reserved category for delegate matching
     */
    public static final URI DELEGATE = URI.create(
        "urn:oasis:names:tc:xacml:3.0:attribute-category:delegate");

    /**
     * A reserved category for delegate information matching
     */
    public static final URI DELEGATION_INFO 
        =  URI.create("urn:oasis:names:tc:xacml:3.0:attribute-category:"
                + "delegation-info");

    /**
     *  The reserved URI prefix for delegated attribute matching
     */
    public static final String DELEGATED = 
        "urn:oasis:names:tc:xacml:3.0:attribute-category:delegated:";
    
    /**
     * The reserved URI for the delegation decision
     */
    public static final URI DECISION = URI.create(
            "urn:oasis:names:tc:xacml:3.0:delegation:decision");
    
    /**
     * The reserved URI for the delegation depth
     */
    public static final URI DELEGATION_DEPTH = URI.create(
            "urn:oasis:names:tc:xacml:3.0:delegation:depth");
    

    /**
     * XACML 1.0 identifier
     */
    public static final String XACML_1_0_IDENTIFIER =
        "urn:oasis:names:tc:xacml:1.0:policy";

    /**
     * XACML 1.0 context identifier
     */
    public static final String XACML_1_0_CTX_ID =
        "urn:oasis:names:tc:xacml:1.0:context";
    
    /**
     * XACML 2.0 identifier
     */
    public static final String XACML_2_0_IDENTIFIER =
        "urn:oasis:names:tc:xacml:2.0:policy:schema:os";
    
    /**
     * XACML 1.0 context identifier
     */
    public static final String XACML_2_0_CTX_ID =
        "urn:oasis:names:tc:xacml:2.0:context:schema:os";
    

    /**
     * XACML 3.0 identifier
     */
    public static final String XACML_3_0_IDENTIFIER =
        "urn:oasis:names:tc:xacml:3.0:schema:os";
    
    /**
     * Version identifier for XACML 1.0
     */
    public static final int XACML_VERSION_1_0 = 0;
    
    /**
     * Version identifier for XACML 1.1 (which isn't a formal release
     * so has no namespace string, but still exists as a separate
     * specification)
     */
    public static final int XACML_VERSION_1_1 = 1;

    /**
     * Version identifier for XACML 1.2
     */
    public static final int XACML_VERSION_2_0 = 2;
    
    /**
     * Version identifier for XACML 3.0
     */
    public static final int XACML_VERSION_3_0 = 3;
    

    /**
     * The default version of XACML, 3.0, used if no namespace string
     * is specified
     */
    public static final int XACML_DEFAULT_VERSION = XACML_VERSION_3_0;

    /**
     * XPath 1.0 identifier
     */
    public static final String XPATH_1_0_IDENTIFIER =
        "http://www.w3.org/TR/1999/Rec-xpath-19991116";

    /**
     * Version identifier for an unspecified version of XPath
     */
    public static final int XPATH_VERSION_UNSPECIFIED = 0;

    /**
     * Version identifier for XPath 1.0
     */
    public static final int XPATH_VERSION_1_0 = 1;
    
    /**
     * Max delegation delegation depth undefined
     */
    public static final int MAX_DELEGATION_DEPTH_UNDEFINED = Integer.MAX_VALUE;
    
    /**
     * System specific line separator
     */
    public static final String nl = System.getProperty("line.separator");
    
    /**
     * Key for User Data available from node, describing the start line of the node in the 
     * xml source file. Feature must be activated.
     */
    public static final String LINE_NUMBER = "startLine";
    
    /**
     * Key for User Data available from node, describing the source file of the node
     */
    public static final String SOURCE_FILE = "srcFile";
    
}
