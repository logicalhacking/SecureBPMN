
/*
 * @(#)TargetMatch.java
 *
 * Copyright 2003-2005 Sun Microsystems, Inc. All Rights Reserved.
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

package com.sun.xacml;

import com.sun.xacml.EvaluationCtx;

import com.sun.xacml.attr.AttributeDesignator;
import com.sun.xacml.attr.AttributeFactory;
import com.sun.xacml.attr.AttributeSelector;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.BagAttribute;
import com.sun.xacml.attr.BooleanAttribute;

import com.sun.xacml.cond.Evaluatable;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.cond.Expression;
import com.sun.xacml.cond.Function;
import com.sun.xacml.cond.FunctionFactory;
import com.sun.xacml.cond.FunctionTypeException;

import com.sun.xacml.ctx.Status;
import com.sun.xacml.debug.RuntimeInfo;
import com.sun.xacml.debug.RuntimeInfo.ELEMENT_TYPE;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Represents the Match XML type in XACML. This is the part of the Target that
 * actually evaluates whether the specified attribute values in the Target match 
 * the corresponding attribute values in the request context.
 *
 * @since 1.0
 * @author Seth Proctor
 * @author Ludwig Seitz
 */
public class TargetMatch implements MatchElement
{
	
    /**
     * The function used for matching.
     */
    private Function function;
    
    /**
     * The <code>AttributeDesignator</code> or 
     * <code>AttributeSelector</code> to be used to select 
     * attributes from the request context
     */             
    private Evaluatable eval;
    
    /**
     * The value to compare against.
     */
    private AttributeValue attrValue;
    
    /**
     * The xacml version for encoding.
     */
    private int xacmlVersion;
    
    /**
     * The match category. Used only for backwards compatibility.
     */
    private URI category;
    
    private RuntimeInfo src;
    
    /**
     * Constructor that creates a default version<code>TargetMatch</code> 
     * from components.
     *
     * @param function the <code>Function</code> that represents the MatchId
     * @param eval the <code>AttributeDesignator</code> or 
     *             <code>AttributeSelector</code> to be used to select 
     *             attributes from the request context
     * @param attrValue the <code>AttributeValue</code> to compare against
     *
     * @throws IllegalArgumentException if the input type isn't a valid value
     */
    public TargetMatch(Function function, Evaluatable eval,
                       AttributeValue attrValue) 
        throws IllegalArgumentException {
        this(function, eval, attrValue, Constants.XACML_DEFAULT_VERSION,
                null);
    }
    
    
    
    /**
     * Constructor that creates a <code>TargetMatch</code> from components.
     *
     * @param function the <code>Function</code> that represents the MatchId
     * @param eval the <code>AttributeDesignator</code> or 
     *             <code>AttributeSelector</code> to be used to select 
     *             attributes from the request context
     * @param attrValue the <code>AttributeValue</code> to compare against
     * @param xacmlVersion  The XACML version number.
     * @param category  The category of this match (e.g. "Subject") for
     *                  XACML versions 2.0 and earlier. Is null for
     *                  later versions.
     *
     * @throws IllegalArgumentException if the input type isn't a valid value
     */
    public TargetMatch(Function function, Evaluatable eval,
                       AttributeValue attrValue, int xacmlVersion,
                       URI category) 
        throws IllegalArgumentException {

        this.function = function;
        this.eval = eval;
        this.attrValue = attrValue; 
        this.xacmlVersion = xacmlVersion;
        this.category = category;
    }
    
    /**
     * Creates a <code>TargetMatch</code> by parsing a node, using the
     * input prefix to determine which category of match this is.
     *
     * @param root The node to parse for the <code>TargetMatch</code>
     * @param metaData The policy's meta-data
     * @param masterCategory  The category of the TargetMatchGroup, for
     *                        checking consistency.
     * 
     * @return a new <code>TargetMatch</code> constructed by parsing
     *
     * @throws ParsingException if there was an error during parsing
     */
    public static TargetMatch getInstance(Node root, 
            PolicyMetaData metaData, URI masterCategory)
            throws ParsingException {
    	RuntimeInfo src = RuntimeInfo.getRuntimeInfo(root, ELEMENT_TYPE.TARGET_MATCH);
        // first make sure the node passed is indeed a TargetMatch
        if (root.getNodeType() != Node.ELEMENT_NODE) {
            throw new ParsingException("Can't create a TargetMatch from" 
                    + " a node that is not an element-node"
                    + (src != null ? src.getLocationMsgForError() : ""));
        }
        if (!root.getLocalName().endsWith("Match")) {
            throw new ParsingException("Can't create a TargetMatch from a "
                    + root.getLocalName() + " element"
                    + (src != null ? src.getLocationMsgForError() : ""));
        }
        Function function;
        Evaluatable eval = null;
        AttributeValue attrValue = null;
      
        AttributeFactory attrFactory = AttributeFactory.getInstance();
        
        // get the function type, making sure that it's really a correct
        // Target function
        String funcName = null;
        if (root.getAttributes().getNamedItem("MatchId") != null) {
            funcName = root.getAttributes().getNamedItem("MatchId")
            .getNodeValue();
        } else {
            throw new ParsingException("Required XML attribute " 
                    + "MatchId not found while parsing a TargetMatch"
                    + (src != null ? src.getLocationMsgForError() : ""));
        }
        
        FunctionFactory factory = FunctionFactory.getTargetInstance();
        try {
            URI funcId = new URI(funcName);
            function = factory.createFunction(funcId);
        } catch (URISyntaxException use) {
            throw new ParsingException("Error parsing TargetMatch"
            		+ (src != null ? src.getLocationMsgForError() : ""), use);
        } catch (UnknownIdentifierException uie) {
            throw new ParsingException("Unknown MatchId"
            		+ (src != null ? src.getLocationMsgForError() : ""), uie);
        } catch (FunctionTypeException fte) {
            // try to create an abstract function
            try {
                URI funcId = new URI(funcName);
                function = factory.createAbstractFunction(funcId, root);
            } catch (Exception e) {
                // any exception here is an error
                throw new ParsingException("invalid abstract function"
                		+ (src != null ? src.getLocationMsgForError() : ""), e);
            }
        }

        // next, get the designator or selector being used, and the attribute
        // value paired with it
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String name = node.getLocalName();
                // endsWith() is compatibility code for XACML 2.0
                if (name.endsWith("AttributeDesignator")) {
                    if (name.equals("AttributeDesignator")) {
                        if (metaData.getXACMLVersion() 
                                < Constants.XACML_VERSION_3_0) {
                            throw new ParsingException(
                                    "Can't create a < XACML 3.0 " 
                                    + "AttributeDesignator out of a "
                                    + name + " element"
                                    + (src != null ? src.getLocationMsgForError() : ""));
                        }
                    } else { // XACML 2.0 compatibility
                        if (metaData.getXACMLVersion() 
                                > Constants.XACML_VERSION_2_0) {
                            throw new ParsingException(
                                    "Can't create a > XACML 2.0 " 
                                    + "AttributeDesignator out of a "
                                    + name + " element"
                                    + (src != null ? src.getLocationMsgForError() : ""));
                        }
                        String categoryStr 
                            = name.replaceAll("AttributeDesignator", "");
                        URI category = null;
                        if (categoryStr.equals("Subject")) {
                            category = Constants.SUBJECT_CAT;
                        } else if (categoryStr.equals("Resource")) {
                            category = Constants.RESOURCE_CAT;
                        } else if (categoryStr.equals("Action")) {
                            category = Constants.ACTION_CAT;
                        } else if (categoryStr.equals("Environment")){
                            category = Constants.ENVIRONMENT_CAT;
                        } else {
                            throw new ParsingException("Can't create a "
                                    + "< XACML 3.0 AttributeDesignator out of"
                                    + " a " + categoryStr + " element"
                                    + (src != null ? src.getLocationMsgForError() : ""));
                        }
                        
                        if (!category.equals(masterCategory)) {
                            throw new ParsingException(categoryStr + "Match" 
                                    + " can't be located in enclosing " 
                                    + masterCategory.toString() + " element"
                                    + (src != null ? src.getLocationMsgForError() : ""));
                        }
                    }
                    eval = AttributeDesignator.getInstance(node, metaData);
                } else if (name.equals("AttributeSelector")) {
                    eval = AttributeSelector.getInstance(node, metaData);
                } else if (name.equals("AttributeValue")) {
                    try {
                        attrValue = attrFactory.createValue(node);
                    } catch (UnknownIdentifierException uie) {
                        throw new ParsingException(
                                "Unknown Attribute Type"
                        		+ (src != null ? src.getLocationMsgForError() : ""), uie);
                    }
                } else {
                    throw new ParsingException("Encountered: '"
                            + name + "' node while parsing a TargetMatch"
                            + (src != null ? src.getLocationMsgForError() : ""));
                }
            }
        }
        
        // finally, check that the inputs are valid for this function
        List<Evaluatable> inputs = new ArrayList<Evaluatable>();
        inputs.add(attrValue);
        inputs.add(eval);
        function.checkInputsNoBag(inputs, src);
        
        TargetMatch targetMatch = new TargetMatch(function, eval, attrValue, 
                metaData.getXACMLVersion(), masterCategory);
        if ( src != null ) {
            targetMatch.src = src;
            src.setXACMLObject(targetMatch);
        }
        return targetMatch;
    }

    /**
     * Returns the <code>Function</code> used to do the matching.
     *
     * @return the match function
     */
    public Function getMatchFunction() {
        return this.function;
    }

    /**
     * Returns the <code>AttributeValue</code> used by the matching function.
     *
     * @return the <code>AttributeValue</code> for the match
     */
    public AttributeValue getMatchValue() {
        return this.attrValue;
    }

    /**
     * Returns the <code>AttributeDesignator</code> or
     * <code>AttributeSelector</code> used by the matching function.
     *
     * @return the designator or selector for the match
     */
    public Evaluatable getMatchEvaluatable() {
        return this.eval;
    }
    
    /**
     * Returns the category of this match.
     * 
     * @return  the category of this match.
     */
    public URI getCategory() {
        return this.category;
    }
    

	public RuntimeInfo getRuntimeInfo() {
		return this.src;
	}
    
    /**
     * Determines whether this <code>TargetMatch</code> matches
     * the input request (whether it is applicable)
     *
     * @param context the representation of the request
     *
     * @return the result of trying to match the TargetMatch and the request
     */
    public MatchResult match(EvaluationCtx context) {
        // start by evaluating the AD/AS
        EvaluationResult result = this.eval.evaluate(context);
                
        if (result.indeterminate()) {
            // in this case, we don't ask the function for anything, and we
            // simply return INDETERMINATE
           return new MatchResult(MatchResult.INDETERMINATE,
                                  result.getStatus());
        }

        // an AD/AS will always return a bag
        BagAttribute bag = (BagAttribute)(result.getAttributeValue());

        if (! bag.isEmpty()) {
            // we got back a set of attributes, so we need to iterate through
            // them, seeing if at least one matches
            Iterator<AttributeValue> it = bag.iterator();
            boolean atLeastOneError = false;
            Status firstIndeterminateStatus = null;

            while (it.hasNext()) {
                ArrayList<Expression> inputs = new ArrayList<Expression>();

                inputs.add(this.attrValue);
                inputs.add(it.next());

                // do the evaluation
                MatchResult match = evaluateMatch(inputs, context);
                
                // we only need one match for this whole thing to match
                if (match.getResult() == MatchResult.MATCH) {
                    return match;
                }

                // if it was INDETERMINATE, we want to remember for later
                if (match.getResult() == MatchResult.INDETERMINATE) {
                    atLeastOneError = true;

                    // there are no rules about exactly what status data
                    // should be returned here, so like in the combining
                    // algs, we'll just track the first error
                    if (firstIndeterminateStatus == null) {
                        firstIndeterminateStatus = match.getStatus();
                    }
                }
            }

            // if we got here, then nothing matched, so we'll either return
            // INDETERMINATE or NO_MATCH
            if (atLeastOneError) {
                return new MatchResult(MatchResult.INDETERMINATE,
                                       firstIndeterminateStatus);
            }
           return new MatchResult(MatchResult.NO_MATCH);
            
        }
        // this is just an optimization, since the loop above will
        // actually handle this case, but this is just a little
        // quicker way to handle an empty bag
        return new MatchResult(MatchResult.NO_MATCH);
    }
    
    /**
     * Private helper that evaluates an individual match.
     */
    private MatchResult evaluateMatch(List<Expression> inputs, EvaluationCtx context) {

    	RuntimeInfo funcSrc = null;
    	//set (context dependent) source locator for function
    	if ( src != null ) {
    		//funcSrc = RuntimeInfo.getIndirectSourceLocator(src, ELEMENT_TYPE.FUNCTION);
    		funcSrc = src.getIndirectRuntimeInfo(function, ELEMENT_TYPE.FUNCTION);
    		this.function.setRuntimeInfo(funcSrc);
    	}        
    	
    	// first off, evaluate the function
        EvaluationResult result = this.function.evaluate(inputs, context);
        
    	//unset source locator 
    	if ( funcSrc != null ) {
    		this.function.unsetRuntimeInfo(funcSrc) ;
    	}

        // if it was indeterminate, then that's what we return immediately
        if (result.indeterminate()) {
            return new MatchResult(MatchResult.INDETERMINATE,
                                   result.getStatus());
        }

        // otherwise, we figure out if it was a match
        BooleanAttribute bool = (BooleanAttribute)(result.getAttributeValue());

        if (bool.getValue()) {
            return new MatchResult(MatchResult.MATCH);
        }
        return new MatchResult(MatchResult.NO_MATCH);
    }

    /**
     * Encodes this <code>TargetMatch</code> into its XML representation and
     * writes this encoding to the given <code>OutputStream</code> with no
     * indentation.
     *
     * @param output  a stream into which the XML-encoded data is written
     * @param charsetName  the character set to use in encoding of strings.
     *                     This may be null in which case the platform
     *                     default character set will be used.
     *                     
     * @throws UnsupportedEncodingException 
     */
    public void encode(OutputStream output, String charsetName)
            throws UnsupportedEncodingException {
        encode(output, charsetName, new Indenter(0));
    }

    /**
     * Encodes this <code>TargetMatch</code> into its XML representation and
     * writes this encoding to the given <code>OutputStream</code> with
     * indentation.
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
        String closingTag = null;

        if (this.xacmlVersion < Constants.XACML_VERSION_3_0) {
            if (this.category.equals(Constants.SUBJECT_CAT)) {
                out.print(indent + "<SubjectMatch ");
                closingTag = "</SubjectMatch>";
            } else if (this.category.equals(Constants.RESOURCE_CAT)) {
                out.print(indent + "<ResourceMatch "); 
                closingTag = "</ResourceMatch>";
            } else if (this.category.equals(Constants.ACTION_CAT)) {
                out.print(indent + "<ActionMatch "); 
                closingTag = "</ActionMatch>";
            } else if (this.category.equals(Constants.ENVIRONMENT_CAT)) {
                out.print(indent + "<EnvironmentMatch ");
                closingTag = "</EnvironmentMatch>";
            }
        } else {
            out.print(indent + "<Match "); 
            closingTag = "</Match>";
        }
        
        out.println("MatchId=\"" 
                + this.function.getIdentifier().toString()+ "\">");
        indenter.in();

        this.attrValue.encode(output, charsetName, indenter);
        this.eval.encode(output, charsetName, indenter);
        
        indenter.out();
        out.println(indent + closingTag);
    }
}