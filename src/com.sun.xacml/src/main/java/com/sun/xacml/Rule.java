
/*
 * @(#)Rule.java
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

import com.sun.xacml.attr.BooleanAttribute;

import com.sun.xacml.cond.Apply;
import com.sun.xacml.cond.Condition;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.cond.VariableManager;

import com.sun.xacml.ctx.Result;
import com.sun.xacml.ctx.Status;
import com.sun.xacml.debug.RuntimeInfo;
import com.sun.xacml.debug.RuntimeInfo.ELEMENT_TYPE;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import java.net.URI;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Represents the RuleType XACML type. This has a target for matching, and
 * encapsulates the condition and all sub-operations that make up the heart
 * of most policies.
 *
 * @since 1.0
 * @author Seth Proctor
 */
public class Rule implements PolicyTreeElement
{

    // the attributes associated with this Rule
    private URI idAttr;
    private int effectAttr;

    // the elements in the rule, each of which is optional
    private String description = null;
    private Target target = null;
    private Condition condition = null;
    
    private RuntimeInfo src = null;

    /**
     * Creates a new <code>Rule</code> object for XACML 1.x and 2.0.
     *
     * @param id the rule's identifier
     * @param effect the effect to return if the rule applies (either
     *               Pemit or Deny) as specified in <code>Result</code>
     * @param description a textual description, or null
     * @param target the rule's target, or null if the target is to be
     *               inherited from the encompassing policy
     * @param condition the rule's condition, or null if there is none
     */
    public Rule(URI id, int effect, String description,  Target target,
                Condition condition) {
        this.idAttr = id;
        this.effectAttr = effect;
        this.description = description;
        this.target = target;
        this.condition = condition;
    }
    
    /**
     * Creates a new <code>Rule</code> object for XACML 1.x and 2.0.
     *
     * @param id the rule's identifier
     * @param effect the effect to return if the rule applies (either
     *               Pemit or Deny) as specified in <code>Result</code>
     * @param description a textual description, or null
     * @param target the rule's target, or null if the target is to be
     *               inherited from the encompassing policy
     * @param condition the rule's condition, or null if there is none
     */
    public Rule(URI id, int effect, String description,  Target target,
                Condition condition, RuntimeInfo src) {
        this.idAttr = id;
        this.effectAttr = effect;
        this.description = description;
        this.target = target;
        this.condition = condition;
        this.src = src;
    }

    /**
     * Creates a new <code>Rule</code> object for XACML 1.x only.
     *
     * @deprecated As of 2.0 you should use the Constructor that accepts
     *             the new <code>Condition</code> class.
     *
     * @param id the rule's identifier
     * @param effect the effect to return if the rule applies (either
     *               Pemit or Deny) as specified in <code>Result</code>
     * @param description a textual description, or null
     * @param target the rule's target, or null if the target is to be
     *               inherited from the encompassing policy
     * @param condition the rule's condition, or null if there is none
     */
    public Rule(URI id, int effect, String description,  Target target,
            Apply condition) { 
        this.idAttr = id;
        this.effectAttr = effect;
        this.description = description;
        this.target = target;
        this.condition = new Condition(condition.getFunction(),
                condition.getChildren());
    }

    /**
     * Returns a new instance of the <code>Rule</code> class based on a
     * DOM node. The node must be the root of an XML RuleType.
     *
     * @deprecated As of 2.0 you should avoid using this method and should
     *             instead use the version that takes a
     *             <code>PolicyMetaData</code> instance. This method will
     *             only work for XACML 1.x policies.
     *
     * @param root the DOM root of a RuleType XML type
     * @param xpathVersion the XPath version to use in any selectors or XPath
     *                     functions, or null if this is unspecified (ie, not
     *                     supplied in the defaults section of the policy)
     *                     
     * @return The Rule object.
     *
     * @throws ParsingException if the RuleType is invalid
     */
    public static Rule getInstance(Node root, String xpathVersion)
            throws ParsingException {
        return getInstance(root,
                new PolicyMetaData(
                        Constants.XACML_1_0_IDENTIFIER,
                        xpathVersion),
                        null);
    }
  
    /**
     * Returns a new instance of the <code>Rule</code> class based on a
     * DOM node. The node must be the root of an XML RuleType.
     *
     * @param root the DOM root of a RuleType XML type
     * @param metaData the meta-data associated with this Rule's policy
     * @param manager the <code>VariableManager</code> used to connect
     *                <code>VariableReference</code>s to their cooresponding
     *                <code>VariableDefinition<code>s
     *                
     * @return The Rule object.              
     *
     * @throws ParsingException if the RuleType is invalid
     */
    public static Rule getInstance(Node root, PolicyMetaData metaData,
            VariableManager manager) throws ParsingException {
    	
    	RuntimeInfo src = RuntimeInfo.getRuntimeInfo(root, ELEMENT_TYPE.RULE);
        //check if we really got a rule
        if (root.getNodeType() != Node.ELEMENT_NODE) {
            throw new ParsingException("Can't create a Rule from" 
                    + " a node that is not an element-node"
                    + (src != null ? src.getLocationMsgForError() : ""));
        }
        if (!root.getLocalName().equals("Rule")) {
            throw new ParsingException("Can't create a Rule from a "
                    + root.getLocalName() + " element"
                    + (src != null ? src.getLocationMsgForError() : ""));
        }
        
        URI id = null;
        int effect = 0;
        String description = null;
        Target target = null;
        Condition condition = null;
        
        // first, get the attributes
        NamedNodeMap attrs = root.getAttributes();
        
        try {
            // get the two required attrs...
            id = new URI(attrs.getNamedItem("RuleId").getNodeValue());
        } catch (Exception e) {
            throw new ParsingException("Error parsing required attribute " +
                    "RuleId in a Rule"
            		 + (src != null ? src.getLocationMsgForError() : ""), e);
        }

        String str = null;
        if (attrs.getNamedItem("Effect") != null) {
            str = attrs.getNamedItem("Effect").getNodeValue();
        } else {
            throw new ParsingException("Required attribute Effect missing" 
                    + "while parsing a Rule"
                    + (src != null ? src.getLocationMsgForError() : ""));
        }
        
        if (str.equals(Result.PERMIT)) {
            effect = Result.DECISION_PERMIT;
        } else if (str.equals(Result.DENY)) {
            effect = Result.DECISION_DENY;
        } else {
            throw new ParsingException("Invalid Effect: " + effect
            		 + (src != null ? src.getLocationMsgForError() : ""));
        }

        // next, get the elements
        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                String cname = child.getLocalName();
                if (cname.equals("Description")) {
                    description = child.getFirstChild().getNodeValue();
                } else if (cname.equals("Target")) {
                    target = Target.getInstance(child, metaData);
                } else if (cname.equals("Condition")) {
                    condition 
                        = Condition.getInstance(child, metaData, manager);
                } else {
                	throw new ParsingException("Found invalid element: "
                            + cname + " in Rule " + id 
                            + (src != null ? src.getLocationMsgForError() : ""));
                }
            }
        }
        Rule rule = new Rule(id, effect, description, target, condition, src);
        if ( src != null ) {
        	src.setXACMLObject(rule);
        }
        return rule;
    }

    /**
     * Returns the effect that this <code>Rule</code> will return from
     * the evaluate method (Permit or Deny) if the request applies.
     *
     * @return a decision effect, as defined in <code>Result</code>
     */
    public int getEffect() {
        return this.effectAttr;
    }

    /**
     * Returns the id of this <code>Rule</code>
     *
     * @return the rule id
     */
    public URI getId() {
        return this.idAttr;
    }

    /**
     * Returns the given description of this <code>Rule</code> or null if 
     * there is no description
     *
     * @return the description or null
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the target for this <code>Rule</code> or null if there
     * is no target
     *
     * @return the rule's target
     */
    public Target getTarget() {
        return this.target;
    }

    /**
     * Since a rule is always a leaf in a policy tree because it can have
     * no children, this always returns an empty <code>List</code>.
     *
     * @return a <code>List</code> with no elements
     */
    public List<PolicyTreeElement> getChildren() {
        return PolicyTreeElement.EMPTY_LIST;
    }

    /**
     * Returns the condition for this <code>Rule</code> or null if there
     * is no condition
     *
     * @return the rule's condition
     */
    public Condition getCondition() {
        return this.condition;
    }

    /**
     * Given the input context sees whether or not the request matches this
     * <code>Rule</code>'s <code>Target</code>. Note that unlike the matching
     * done by the <code>evaluate</code> method, if the <code>Target</code>
     * is missing than this will return Indeterminate. This lets you write
     * your own custom matching routines for rules but lets evaluation
     * proceed normally.
     *
     * @param context the representation of the request
     *
     * @return the result of trying to match this rule and the request
     */
    public MatchResult match(EvaluationCtx context) {
        if (this.target == null) {
            ArrayList<String> code = new ArrayList<String>();
            code.add(Status.STATUS_PROCESSING_ERROR);
            Status status = new Status(code, "no target available for " +
                                       "matching a rule");
            return new MatchResult(MatchResult.INDETERMINATE, status);
        }
        return this.target.match(context);
    }

    /**
     * Evaluates the rule against the supplied context. This will check that
     * the target matches, and then try to evaluate the condition. If the
     * target and condition apply, then the rule's effect is returned in
     * the result.
     * <p>
     * Note that rules are not required to have targets. If no target is
     * specified, then the rule inherits its parent's target. In the event
     * that this <code>Rule</code> has no <code>Target</code> then the
     * match is assumed to be true, since evaluating a policy tree to this
     * level required the parent's target to match.
     *
     * @param context the representation of the request we're evaluating
     *
     * @return the result of the evaluation
     */
    public Result evaluate(EvaluationCtx context) {
        context.newEvent(this);
        // If the Target is null then it's supposed to inherit from the
        // parent policy, so we skip the matching step assuming we wouldn't
        // be here unless the parent matched
        if (this.target != null) {
            MatchResult match = match(context);
            int result = match.getResult();
            
            // if the target didn't match, then this Rule doesn't apply
            if (result == MatchResult.NO_MATCH) {
                Result evalResult = new Result(Result.DECISION_NOT_APPLICABLE,
                                               context);
                context.closeCurrentEvent(evalResult);
                return evalResult;
            }
            // if the target was indeterminate, we can't go on
            if (result == MatchResult.INDETERMINATE) {
                Result evalResult = new Result(Result.DECISION_INDETERMINATE,
                                               match.getStatus(),
                                               context);
                context.closeCurrentEvent(evalResult);
                return evalResult;
            }
        }

        // if there's no condition, then we just return the effect...
        if (this.condition == null) {
            Result evalResult = new Result(this.effectAttr, 
                                           context);
            context.closeCurrentEvent(evalResult);
            return evalResult; 
        }

        // ...otherwise we evaluate the condition
        EvaluationResult result = this.condition.evaluate(context);
        
        if (result.indeterminate()) {
            // if it was INDETERMINATE, then that's what we return
            Result evalResult = new Result(Result.DECISION_INDETERMINATE,
                                           result.getStatus(),
                                           context);
            context.closeCurrentEvent(evalResult);
            return evalResult;
        }
        // otherwise we return the effect on true, and NA on false
        BooleanAttribute bool =
            (BooleanAttribute)(result.getAttributeValue());

        if (bool.getValue()) {
            Result evalResult = new Result(this.effectAttr, 
                                           context);
            context.closeCurrentEvent(evalResult);
            return evalResult;
            
        }
        Result evalResult = new Result(Result.DECISION_NOT_APPLICABLE,
                                       context);
        context.closeCurrentEvent(evalResult);
        return evalResult; 
    }

    /**
     * Encodes this <code>Rule</code> into its XML representation and writes
     * this encoding to the given <code>OutputStream</code> with no
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
     * Encodes this <code>Rule</code> into its XML representation and writes
     * this encoding to the given <code>OutputStream</code> with
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

        out.print(indent + "<Rule RuleId=\"" + this.idAttr.toString() 
                + "\" Effect=\"" + Result.DECISIONS.get(this.effectAttr) 
                + "\"");

        if ((this.description != null) || (this.target != null)
                || (this.condition != null)) {
            // there is some content in the Rule
            out.println(">");

            indenter.in();
            String nextIndent = indenter.makeString();

            if (this.description != null) {
                out.println(nextIndent + "<Description>" + this.description 
                        + "</Description>");
            }

            if (this.target != null) {
                this.target.encode(output, charsetName, indenter);
            }
            
            if (this.condition != null) {
                this.condition.encode(output, charsetName, indenter);
            }

            indenter.out();
            out.println(indent + "</Rule>");
        } else {
            // the Rule is empty, so close the tag and we're done
            out.println("/>");
        }
    }

	public RuntimeInfo getRuntimeInfo() {
		return this.src;
	}

}
