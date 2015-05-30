
/*
 * @(#)MapFunction.java	1.4 01/30/03
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

package com.sun.xacml.cond;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.Indenter;
import com.sun.xacml.ParsingException;

import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.BagAttribute;
import com.sun.xacml.debug.RuntimeInfo;

import java.net.URI;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Represents the higher order bag function map.
 *
 * @since 1.0
 * @author Seth Proctor
 */
class MapFunction implements Function
{

    /**
     * The name of this function
     */
    public static final String NAME_MAP = FunctionBase.FUNCTION_NS + "map";

    // the return type for this instance
    private URI returnType;

    // the stuff used to make sure that we have a valid identifier or a
    // known error, just like in the attribute classes
    private static URI identifier;
    private static RuntimeException earlyException;
    
    private static Logger logger = Logger.getLogger(MapFunction.class);

    // try to initialize the identifier
    static {
        try {
            identifier = URI.create(NAME_MAP);
        } catch (IllegalArgumentException e) {
            earlyException = e;
        }
    }

    /**
     * Creates a new instance of a <code>MapFunction</code>.
     *
     * @param returnType the type returned by this function
     */
    public MapFunction(URI returnType) {
        this.returnType = returnType;
    }

    /**
     * Returns a <code>Set</code> containing all the function identifiers
     * supported by this class.
     *
     * @return a <code>Set</code> of <code>String</code>s
     */
    public static Set<String> getSupportedIdentifiers() {
        Set<String> set = new HashSet<String>();

        set.add(NAME_MAP);

        return set;
    }

    /**
     * Creates a new instance of the map function using the data found in
     * the DOM node provided. This is called by a proxy when the factory
     * is asked to create one of these functions.
     *
     * @param root the DOM node of the apply tag containing this function
     *
     * @return a <code>MapFunction</code> instance
     *
     * @throws ParsingException if the DOM data was incorrect
     */
    public static MapFunction getInstance(Node root) throws ParsingException {
        URI returnType = null;
        // check if this really is a Function (may be an Apply element too
        if (root.getNodeType() != Node.ELEMENT_NODE) {
            throw new ParsingException("Can't create a Function from a "
                    + root.getLocalName() + " element");
        }
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE 
                    && node.getLocalName().equals("Function")) {
                
                String funcName = null;
                if (node.getAttributes().getNamedItem("FunctionId") != null) {
                    funcName = node.getAttributes().getNamedItem("FunctionId")
                                   .getNodeValue();
                } else {
                    throw new ParsingException("Required xml-attribute"
                            + " FunctionId not found in Function element");
                }
                FunctionFactory factory = FunctionFactory.getGeneralInstance();
                try {
                    Function function = factory.createFunction(funcName);
                    returnType = function.getReturnType();
                    break;
                } catch (FunctionTypeException fte) {
                    // try to get this as an abstract function
                    try {
                        Function function = factory.
                            createAbstractFunction(funcName, root);
                        returnType = function.getReturnType();
                        break;
                    } catch (Exception e) {
                        // any exception here is an error
                        throw new ParsingException("invalid abstract map", e);
                    }
                } catch (Exception e) {
                    // any exception that's not function type is an error
                    throw new ParsingException("couldn't parse map body", e);
                }
            }
        }

        // see if we found the return type
        if (returnType == null) {
            throw new ParsingException("couldn't find the return type");
        }

        return new MapFunction(returnType);
    }
    
    /**
     * Returns the full identifier of this function, as known by the factories.
     *
     * @return the function's identifier
     */
    public URI getIdentifier() {
        // strictly speaking, this should never happen
        if (earlyException != null) {
            throw earlyException;
        }

        return identifier;
    }

    /**
     * Returns the same value as <code>getReturnType</code>. This is here
     * to support the <code>Expression</code> interface.
     *
     * @return the return type
     */
    public URI getType() {
        return getReturnType();
    }

    /**
     * Returns the attribute type returned by this function.
     *
     * @return the return type
     */
    public URI getReturnType() {
        return this.returnType;
    }

    /**
     * Returns <code>true</code>, since the map function always returns a bag
     *
     * @return true
     */
    public boolean returnsBag() {
        return true;
    }

    /**
     * Evaluates the function given the input data. Map expects a
     * <code>Function</code> followed by a <code>BagAttribute</code>.
     *
     * @param inputs the input agrument list
     * @param context the representation of the request
     *
     * @return the result of evaluation
     */
    public EvaluationResult evaluate(List<Expression> inputs, EvaluationCtx context) {

        // get the inputs, which we expect to be correct
        Iterator<Expression> iterator = inputs.iterator();
        Function function = null;

        Expression xpr = (Expression)(iterator.next());
        if (xpr instanceof Function) {
            function = (Function)xpr;
        } else {
            function = (Function)(((VariableReference)xpr).
                                  getReferencedDefinition().getExpression());
        }

        Evaluatable eval = (Evaluatable)(iterator.next());
        EvaluationResult result = eval.evaluate(context);
                
        // in a higher-order case, if anything is INDETERMINATE, then
        // we stop right away
        if (result.indeterminate()) {
            return result;
        }
        BagAttribute bag = (BagAttribute)(result.getAttributeValue());
        
        // param: function, bag
        // return: bag
        // for each value in the bag evaluate the given function with
        // the value and put the function result in a new bag that
        // is ultimately returned

        Iterator<AttributeValue> it = bag.iterator();
        List<AttributeValue> outputs = new ArrayList<AttributeValue>();

        while (it.hasNext()) {
            List<Expression> params = new ArrayList<Expression>();
            params.add(it.next());
            result = function.evaluate(params, context);

            if (result.indeterminate()) {
                return result;
            }

            outputs.add(result.getAttributeValue());
        }

        return new EvaluationResult(new BagAttribute(this.returnType, 
                                                     outputs));
    }
    
    /**
     * Checks that the input list is valid for evaluation.
     *
     * @param inputs a <code>List</code> of inputs
     *
     * @throws IllegalArgumentException if the inputs cannot be evaluated
     */
    public void checkInputs(List<Expression> inputs) throws IllegalArgumentException {
    	checkInputs(inputs, null);
    }

    /**
     * Checks that the input list is valid for evaluation.
     *
     * @param inputs a <code>List</code> of inputs
     *
     * @throws IllegalArgumentException if the inputs cannot be evaluated
     */
    public void checkInputs(List<Expression> inputs, RuntimeInfo src) throws IllegalArgumentException {
    	Expression [] list = inputs.toArray(new Expression[inputs.size()]);

        // check that we've got the right number of arguments
        if (list.length != 2) {
            throw new IllegalArgumentException("map requires two inputs"
            		+ (src != null ? src.getLocationMsgForError() : ""));
        }

        // now check that we've got the right types for map
        Function function = null;

        if (list[0] instanceof Function) {
            function = (Function)(list[0]);
        } else if (list[0] instanceof VariableReference) {
            Expression xpr = ((VariableReference)(list[0])).
                getReferencedDefinition().getExpression();
            if (xpr instanceof Function) {
                function = (Function)xpr;
            }
        }

        if (function == null) {
            throw new IllegalArgumentException("first argument to map must " 
            		+ "be a Function" + (src != null ? src.getLocationMsgForError() : ""));
        }
        Evaluatable eval = (Evaluatable)(list[1]);
        if (! eval.returnsBag()) {
            throw new IllegalArgumentException("second argument to map must " 
            		+ "be a bag" + (src != null ? src.getLocationMsgForError() : ""));
        }

        // finally, check that the type in the bag is right for the function
        List<Evaluatable> input = new ArrayList<Evaluatable>();
        input.add( (Evaluatable) list[1]);
        function.checkInputsNoBag(input);
    }
    
    /**
     * Always throws <code>IllegalArgumentException</code> since map needs
     * to work on a bag
     *
     * @param inputs a <code>List</code> of inputs
     *
     * @throws IllegalArgumentException always
     */
    public void checkInputsNoBag(List<Evaluatable> inputs) throws IllegalArgumentException {
    	checkInputsNoBag(inputs);
    }

    /**
     * Always throws <code>IllegalArgumentException</code> since map needs
     * to work on a bag
     *
     * @param inputs a <code>List</code> of inputs
     *
     * @throws IllegalArgumentException always
     */
    public void checkInputsNoBag(List<Evaluatable> inputs, RuntimeInfo src) throws IllegalArgumentException {
        throw new IllegalArgumentException("map requires a bag");
    }

    /**
     * Encodes this <code>MapFunction</code> into its XML representation and
     * writes this encoding to the given <code>OutputStream</code> with no
     * indentation.
     *
     * @param output a stream into which the XML-encoded data is written
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
     * Encodes this <code>MapFunction</code> into its XML representation and
     * writes this encoding to the given <code>OutputStream</code> with
     * indentation.
     *
     * @param output a stream into which the XML-encoded data is written
     * @param charsetName  the character set to use in encoding of strings.
     *                     This may be null in which case the platform
     *                     default character set will be used.
     * @param indenter an object that creates indentation strings
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
        out.println(indenter.makeString() + "<Function FunctionId=\"" +
                NAME_MAP + "\"/>");
    }

	public RuntimeInfo getRuntimeInfo() {
		logger.warn("SourceLocator feature currently not supported for Map Function");
		return null;
		//throw new RuntimeException();
	}

	public void setRuntimeInfo(RuntimeInfo src) {
		logger.warn("SourceLocator feature currently not supported for Map Function");
	}
	
	public void unsetRuntimeInfo(RuntimeInfo src) {
		logger.warn("SourceLocator feature currently not supported for Map Function");
	}

}
