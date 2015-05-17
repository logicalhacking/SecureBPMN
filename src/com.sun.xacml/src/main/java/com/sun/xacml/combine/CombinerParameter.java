
/*
 * @(#)CombinerParameter.java
 *
 * Copyright 2005 Sun Microsystems, Inc. All Rights Reserved.
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

package com.sun.xacml.combine;

import com.sun.xacml.Indenter;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;

import com.sun.xacml.attr.AttributeFactory;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.debug.Locatable;
import com.sun.xacml.debug.RuntimeInfo;
import com.sun.xacml.debug.RuntimeInfo.ELEMENT_TYPE;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Represents a single named parameter to a combining algorithm. Parameters
 * are only used by XACML 2.0 and later policies.
 *
 * @since 2.0
 * @author Seth Proctor
 */
public class CombinerParameter implements Locatable
{

    // the name of this parameter
    private String name;

    // the value of this parameter
    private AttributeValue value;
    
    public static final List<CombinerParameter> EMPTY_LIST = Collections.<CombinerParameter>emptyList();
    
    private RuntimeInfo src;

    /**
     * Creates a new CombinerParameter.
     *
     * @param name the parameter's name
     * @param value the parameter's value
     */
    public CombinerParameter(String name, AttributeValue value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Returns a new instance of the <code>CombinerParameter</code> class
     * based on a DOM node. The node must be the root of an XML
     * CombinerParameterType.
     *
     * @param root the DOM root of a CombinerParameterType XML type
     * 
     * @return  The combiner parameter. 
     *
     * @throws ParsingException if the CombinerParameterType is invalid
     */
    public static CombinerParameter getInstance(Node root)
            throws ParsingException {
    	RuntimeInfo src = RuntimeInfo.getRuntimeInfo(root, ELEMENT_TYPE.COMBINER_PARAMETER);
        // check if this really is a CombinerParameter
        if (root.getNodeType() != Node.ELEMENT_NODE
                || !root.getLocalName().equals("CombinerParameter")) {
            throw new ParsingException("Can't create a CombinerParameter "
                    + "from a " + root.getLocalName() + " element"
                    + ( src == null ? "" : src.getLocationMsgForError()));
        }
        // get the name, which is a required attribute
        String name = null;
        if (root.getAttributes().getNamedItem("ParameterName") != null) {
            name = root.getAttributes().getNamedItem("ParameterName")
                       .getNodeValue();
        } else {
            throw new ParsingException("Required XML attribute ParameterName"
                   + " not found while parsing a CombinerParameter"
                   + ( src == null ? "" : src.getLocationMsgForError()));
        }

        // get the attribute value, the only child of this element
        AttributeFactory attrFactory = AttributeFactory.getInstance();
        AttributeValue value = null;
        
        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if ( child.getNodeType() == Node.ELEMENT_NODE ) {
                try {
                    value = attrFactory.createValue(child);
                    break;
                } catch (UnknownIdentifierException uie) {
                    throw new ParsingException("Unknown AttributeId"
                    		 + ( src == null ? "" : src.getLocationMsgForError()), uie);
                }
            }
        }
        if ( value == null ) {
        	throw new ParsingException("Missing AttributeValue for CombinerParameter" +
        			( src == null ? "" : src.getLocationMsgForError()));
        }
        
        CombinerParameter param = new CombinerParameter(name, value);
        param.src = src;
        return param;
    }

    /**
     * Returns the name of this parameter.
     *
     * @return the name of this parameter
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the value provided by this parameter.
     *
     * @return the value provided by this parameter
     */
    public AttributeValue getValue() {
        return this.value;
    }

    /**
     * Encodes this parameter into its  XML representation and writes this
     * encoding to the given <code>OutputStream</code> with indentation.
     *
     * @param output  a stream into which the XML-encoded data is written
     * @param charsetName  the character set to use in encoding of strings.
     *                     This may be null in which case the platform
     *                     default character set will be used.
     * @param indenter  an object that creates indentation strings
     * 
     * @throws UnsupportedEncodingException 
     */
    public void encode(OutputStream output, String charsetName,
                       Indenter indenter)
            throws UnsupportedEncodingException {
        PrintStream out;
        if(charsetName == null) {
            out = new PrintStream(output);
        } else {
            out = new PrintStream(output, false, charsetName);
        }
        String indent = indenter.makeString();

        out.println(indent + "<CombinerParameter ParameterName=\"" +
                getName() + "\">");
        indenter.in();

        getValue().encode(output, charsetName, indenter);

        out.println(indent + "</CombinerParameter>");
        indenter.out();
    }

	public RuntimeInfo getRuntimeInfo() {
		return src;
	}

}
