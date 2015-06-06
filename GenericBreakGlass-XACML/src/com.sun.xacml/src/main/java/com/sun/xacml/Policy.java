
/*
 * @(#)Policy.java
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

import com.sun.xacml.combine.CombinerElement;
import com.sun.xacml.combine.CombinerParameter;
import com.sun.xacml.combine.RuleCombinerElement;
import com.sun.xacml.combine.RuleCombiningAlgorithm;

import com.sun.xacml.cond.VariableDefinition;
import com.sun.xacml.cond.VariableManager;
import com.sun.xacml.ctx.PolicyIssuer;
import com.sun.xacml.debug.RuntimeInfo;
import com.sun.xacml.debug.RuntimeInfo.ELEMENT_TYPE;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import java.net.URI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Represents one of the two top-level constructs in XACML, the PolicyType.
 * This contains rules, which in turn contain most of the logic of 
 * a policy.
 *
 * @since 1.0
 * @author Seth Proctor
 * @author Ludwig Seitz
 */
public class Policy extends AbstractPolicy {

    /**
     * The <code>Set</code> of <code>VariableDefinition</code>s in this policy
     */
    private Set<VariableDefinition> definitions;
      
    /**
     * Creates a new <code>Policy</code> with only the required elements.
     *
     * @param id the policy identifier
     * @param combiningAlg the <code>CombiningAlgorithm</code> used on the
     *                     rules in this set
     *                     
     * @param target the <code>Target</code> for this policy
     */
    public Policy(URI id, RuleCombiningAlgorithm combiningAlg, Target target) {
        this(id, null, null, combiningAlg, null, null, target, null, 
                null, null);
    }
        
    /**
     * Creates a new <code>Policy</code> with only the required elements.
     *
     * @param id the policy identifier
     * @param combiningAlg the <code>CombiningAlgorithm</code> used on the
     *                     rules in this set
     * @param issuer  the <code>PolicyIssuer</code> for this policy or null
     *                     if it is the trusted issuer                                
     * @param target the <code>Target</code> for this policy
     */
    public Policy(URI id, RuleCombiningAlgorithm combiningAlg, 
            PolicyIssuer issuer, Target target) {
        this(id, null, null, combiningAlg, null, issuer, target, null, 
                null, null);
    }
    
    /**
     * Creates a new <code>Policy</code> with only the required elements
     * plus rules.
     *
     * @param id the policy identifier
     * @param combiningAlg the <code>CombiningAlgorithm</code> used on the
     *                     rules in this set
     * @param target the <code>Target</code> for this policy
     * @param rules a list of <code>Rule</code> objects
     *
     * @throws IllegalArgumentException if the <code>List</code> of rules
     *                                  contains an object that is not a
     *                                  <code>Rule</code>
     */
    public Policy(URI id, RuleCombiningAlgorithm combiningAlg, Target target,
                  List<Rule> rules) {
        this(id, null, null, combiningAlg, null, null, target, null, 
                rules, null);
    }

    /**
     * Creates a new <code>Policy</code> with only the required elements
     * plus rules.
     *
     * @param id the policy identifier
     * @param combiningAlg the <code>CombiningAlgorithm</code> used on the
     *                     rules in this set
     * @param issuer  the <code>PolicyIssuer</code> for this policy or null
     *                  if it is the trusted issuer                   
     * @param target the <code>Target</code> for this policy
     * @param rules a list of <code>Rule</code> objects
     *
     * @throws IllegalArgumentException if the <code>List</code> of rules
     *                                  contains an object that is not a
     *                                  <code>Rule</code>
     */
    public Policy(URI id, RuleCombiningAlgorithm combiningAlg, 
                PolicyIssuer issuer, Target target, List<Rule> rules) {
        this(id, null, null, combiningAlg, null, issuer, target, null, 
                rules, null);
    }
    
    /**
     * Creates a new <code>Policy</code> with the required elements plus
     * rules and a String description.
     *
     * @param id the policy identifier
     * @param version the policy version or null for the default (this is
     *                always null for pre-2.0 policies)
     * @param xacmlVersion  the xacml version identifier.                
     * @param combiningAlg the <code>CombiningAlgorithm</code> used on the
     *                     rules in this set
     * @param description a <code>String</code> describing the policy
     * @param target the <code>Target</code> for this policy
     * @param rules a list of <code>Rule</code> objects
     *
     * @throws IllegalArgumentException if the <code>List</code> of rules
     *                                  contains an object that is not a
     *                                  <code>Rule</code>
     */
    public Policy(URI id, String version, String xacmlVersion, 
                  RuleCombiningAlgorithm combiningAlg,
                  String description, Target target, List<Rule> rules) {
        this(id, version, xacmlVersion, combiningAlg, description, null,
             target, null, rules, null);
    }

    /**
     * Creates a new <code>Policy</code> with the required elements plus
     * a version, rules, and a String description. Note that the version
     * is an XACML 2.0 feature.
     *
     * @param id the policy identifier
     * @param version the policy version or null for the default (this must
     *                always be null for XACML 1.x policies)
     * @param xacmlVersion  the xacml version identifier.                
     * @param combiningAlg the <code>CombiningAlgorithm</code> used on the
     *                     rules in this set
     * @param description a <code>String</code> describing the policy
     * @param issuer  the <code>PolicyIssuer</code> for this policy or null
     *                  if it is the trusted issuer
     * @param target the <code>Target</code> for this policy
     * @param rules a list of <code>Rule</code> objects
     *
     * @throws IllegalArgumentException if the <code>List</code> of rules
     *                                  contains an object that is not a
     *                                  <code>Rule</code>
     */
    public Policy(URI id, String version, String xacmlVersion,
                  RuleCombiningAlgorithm combiningAlg,
                  String description, PolicyIssuer issuer, Target target, 
                  List<Rule> rules) {
        this(id, version, xacmlVersion, combiningAlg, description, issuer,
                target, null, rules, null);
    }
    
    /**
     * Creates a new <code>Policy</code> with the required elements plus
     * rules, a String description and policy defaults.
     *
     * @param id the policy identifier
     * @param version the policy version or null for the default (this is
     *                always null for pre-2.0 policies)
     * @param xacmlVersion  the xacml version identifier.                
     * @param combiningAlg the <code>CombiningAlgorithm</code> used on the
     *                     rules in this set
     * @param description a <code>String</code> describing the policy
     * @param target the <code>Target</code> for this policy
     * @param defaultVersion the XPath version to use
     * @param rules a list of <code>Rule</code> objects
     *
     * @throws IllegalArgumentException if the <code>List</code> of rules
     *                                  contains an object that is not a
     *                                  <code>Rule</code>
     */
    public Policy(URI id, String version, String xacmlVersion,
                  RuleCombiningAlgorithm combiningAlg,
                  String description, Target target, String defaultVersion,
                  List<Rule> rules) {
        this(id, version, xacmlVersion, combiningAlg, description, null,
                target, defaultVersion, rules, null);
    }

    /**
     * Creates a new <code>Policy</code> with the required elements plus
     * a version, rules, a String description and policy defaults. Note that
     * the version is an XACML 2.0 feature.
     *
     * @param id the policy identifier
     * @param version the policy version or null for the default (this must
     *                always be null for XACML 1.x policies)
     * @param xacmlVersion  the xacml version identifier.               
     * @param combiningAlg the <code>CombiningAlgorithm</code> used on the
     *                     rules in this set
     * @param description a <code>String</code> describing the policy
     * @param issuer  the <code>PolicyIssuer</code> for this policy or null
     *                  if it is the trusted issuer
     * @param target the <code>Target</code> for this policy
     * @param defaultVersion the XPath version to use
     * @param rules a list of <code>Rule</code> objects
     *
     * @throws IllegalArgumentException if the <code>List</code> of rules
     *                                  contains an object that is not a
     *                                  <code>Rule</code>
     */
    public Policy(URI id, String version, String xacmlVersion,
                  RuleCombiningAlgorithm combiningAlg,
                  String description, PolicyIssuer issuer, Target target, 
                  String defaultVersion, List<Rule> rules) {
        this(id, version, xacmlVersion, combiningAlg, description, issuer,
                target, defaultVersion, rules, null);
    }
    
    /**
     * Creates a new <code>Policy</code> with the required elements plus
     * a version, rules, a String description, policy defaults, and
     * obligations. Note that the version is an XACML 2.0 feature.
     *
     * @param id the policy identifier
     * @param version the policy version or null for the default (this must
     *                always be null for XACML 1.x policies)
     * @param xacmlVersion  the xacml version identifier.                  
     * @param combiningAlg the <code>CombiningAlgorithm</code> used on the
     *                     rules in this set
     * @param description a <code>String</code> describing the policy
     * @param target the <code>Target</code> for this policy
     * @param defaultVersion the XPath version to use
     * @param rules a list of <code>Rule</code> objects
     * @param obligations a set of <code>Obligations</code> objects
     *
     * @throws IllegalArgumentException if the <code>List</code> of rules
     *                                  contains an object that is not a
     *                                  <code>Rule</code>
     */
    public Policy(URI id, String version, String xacmlVersion,
                  RuleCombiningAlgorithm combiningAlg,
                  String description, Target target, String defaultVersion,
                  List<Rule> rules, Set<Obligation> obligations) {
        this(id, version, xacmlVersion, combiningAlg, description, null,
                target, defaultVersion, rules, obligations, null);
    }
    
    /**
     * Creates a new <code>Policy</code> with the required elements plus
     * rules, a String description, policy defaults, and obligations.
     *
     * @param id the policy identifier
     * @param version the policy version or null for the default (this is
     *                always null for pre-2.0 policies)
     * @param xacmlVersion  the xacml version identifier.                   
     * @param combiningAlg the <code>CombiningAlgorithm</code> used on the
     *                     rules in this set
     * @param description a <code>String</code> describing the policy
     * @param issuer  the <code>PolicyIssuer</code> for this policy or null
     *                  if it is the trusted issuer
     * @param target the <code>Target</code> for this policy
     * @param defaultVersion the XPath version to use
     * @param rules a list of <code>Rule</code> objects
     * @param obligations a set of <code>Obligations</code> objects
     *
     * @throws IllegalArgumentException if the <code>List</code> of rules
     *                                  contains an object that is not a
     *                                  <code>Rule</code>
     */
    public Policy(URI id, String version, String xacmlVersion, 
                  RuleCombiningAlgorithm combiningAlg,
                  String description, PolicyIssuer issuer, Target target, 
                  String defaultVersion, List<Rule> rules, Set<Obligation> obligations) {
        this(id, version, xacmlVersion, combiningAlg, description, issuer, 
                target, defaultVersion, rules, obligations, null);
    }

    /**
     * Creates a new <code>Policy</code> with the required elements plus
     * a version, rules, a String description, policy defaults, obligations,
     * and variable definitions. Note that the version and definitions are
     * XACML 2.0 features.
     *
     * @param id the policy identifier
     * @param version the policy version or null for the default (this must
     *                always be null for XACML 1.x policies)
     * @param xacmlVersion  the xacml version identifier.                  
     * @param combiningAlg the <code>CombiningAlgorithm</code> used on the
     *                     rules in this set
     * @param description a <code>String</code> describing the policy
     * @param target the <code>Target</code> for this policy
     * @param defaultVersion the XPath version to use
     * @param rules a list of <code>Rule</code> objects
     * @param obligations a set of <code>Obligations</code> objects
     * @param definitions a set of <code>VariableDefinition</code> objects
     *                    that must provide all definitions referenced by
     *                    all <code>VariableReference</code>s in the policy
     *
     * @throws IllegalArgumentException if the <code>List</code> of rules
     *                                  contains an object that is not a
     *                                  <code>Rule</code>
     */
    public Policy(URI id, String version, String xacmlVersion, 
                  RuleCombiningAlgorithm combiningAlg,
                  String description, Target target, String defaultVersion,
                  List<Rule> rules, Set<Obligation> obligations, Set<VariableDefinition> definitions) {
       this(id, version, xacmlVersion, combiningAlg, description, null, target,
               defaultVersion, rules, obligations, definitions);
    }
    
    /**
     * Creates a new <code>Policy</code> with the required elements plus
     * rules, a String description, policy defaults, obligations, and
     * variable definitions.
     *
     * @param id the policy identifier
     * @param version the policy version or null for the default (this is
     *                always null for pre-2.0 policies)
     * @param xacmlVersion  the xacml version identifier.                  
     * @param combiningAlg the <code>CombiningAlgorithm</code> used on the
     *                     rules in this set
     * @param description a <code>String</code> describing the policy
     * @param issuer  the <code>PolicyIssuer</code> for this policy or null
     *                  if it is the trusted issuer
     * @param target the <code>Target</code> for this policy
     * @param defaultVersion the XPath version to use
     * @param rules a list of <code>Rule</code> objects
     * @param obligations a set of <code>Obligations</code> objects
     * @param definitions a set of <code>VariableDefinition</code> objects
     *                    that must provide all definitions referenced by
     *                    all <code>VariableReference</code>s in the policy
     *
     * @throws IllegalArgumentException if the <code>List</code> of rules
     *                                  contains an object that is not a
     *                                  <code>Rule</code>
     */
    public Policy(URI id, String version, String xacmlVersion, 
                  RuleCombiningAlgorithm combiningAlg,
                  String description, PolicyIssuer issuer, Target target, 
                  String defaultVersion, List<Rule> rules, Set<Obligation> obligations, 
                  Set<VariableDefinition> definitions) {
        super(id, version, xacmlVersion, combiningAlg, description, issuer, 
                target, defaultVersion, obligations, null, 
                Constants.MAX_DELEGATION_DEPTH_UNDEFINED);

        List<CombinerElement> list = null;

        // check that the list contains only rules
        if (rules != null) {
            list = new ArrayList<CombinerElement>();
            Iterator<Rule> it = rules.iterator();
            while (it.hasNext()) {
//                Object o = it.next();
//                if (! (o instanceof Rule)) {
//                    throw new IllegalArgumentException("non-Rule in rules");
//                }
                list.add(new RuleCombinerElement(it.next()));
          }
        }

        setChildren(list);

        // save the definitions
        if (definitions == null) {
            this.definitions = VariableDefinition.EMPTY_SET;
        } else {
            this.definitions = Collections.
                unmodifiableSet(new HashSet<VariableDefinition>(definitions));
        }
    }
    
     /**
     * Creates a new <code>Policy</code> with the required and optional
     * elements. If you need to provide combining algorithm parameters, you
     * need to use this constructor. Note that unlike the other constructors
     * in this class, the rules list is actually a list of
     * <code>CombinerElement</code>s used to match a rule with any
     * combiner parameters it may have.
     *
     * @param id the policy identifier
     * @param version the policy version or null for the default (this must
     *                always be null for XACML 1.x policies)
     * @param xacmlVersion  the xacml version identifier.                   
     * @param combiningAlg the <code>CombiningAlgorithm</code> used on the
     *                     rules in this set
     * @param description a <code>String</code> describing the policy or
     *                    null if there is no description
     * @param target the <code>Target</code> for this policy
     * @param defaultVersion the XPath version to use or null if there is
     *                       no default version
     * @param ruleElements a list of <code>RuleCombinerElement</code> objects
     *                     or null if there are no rules
     * @param obligations a set of <code>Obligations</code> objects or null
     *                    if there are no obligations
     * @param definitions a set of <code>VariableDefinition</code> objects
     *                    that must provide all definitions referenced by
     *                    all <code>VariableReference</code>s in the policy
     * @param parameters the <code>List</code> of
     *                   <code>CombinerParameter</code>s provided for general
     *                   use by the combining algorithm
     *
     * @throws IllegalArgumentException if the <code>List</code> of rules
     *                                  contains an object that is not a
     *                                  <code>RuleCombinerElement</code>
     */
    public Policy(URI id, String version, String xacmlVersion, 
                  RuleCombiningAlgorithm combiningAlg,
                  String description, Target target, String defaultVersion, 
                  List<CombinerElement> ruleElements, Set<Obligation> obligations, Set<VariableDefinition> definitions, 
                  List<CombinerParameter> parameters) {
        this(id, version, xacmlVersion, combiningAlg, description, null,
                    target, defaultVersion, ruleElements, obligations,
                    definitions, parameters, 
                    Constants.MAX_DELEGATION_DEPTH_UNDEFINED);
    }

    /**
     * Creates a new <code>Policy</code> with the required and optional
     * elements. If you need to provide combining algorithm parameters, you
     * need to use this constructor. Note that unlike the other constructors
     * in this class, the rules list is actually a list of
     * <code>CombinerElement</code>s used to match a rule with any
     * combiner parameters it may have.
     *
     * @param id the policy identifier
     * @param version the policy version or null for the default (this is
     *                always null for pre-2.0 policies)
     * @param xacmlVersion  the xacml version identifier.                 
     * @param combiningAlg the <code>CombiningAlgorithm</code> used on the
     *                     rules in this set
     * @param description a <code>String</code> describing the policy or
     *                    null if there is no description
     * @param issuer  the PolicyIssuer or null if this is the trusted issuer
     * @param target the <code>Target</code> for this policy
     * @param defaultVersion the XPath version to use or null if there is
     *                       no default version
     * @param ruleElements a list of <code>RuleCombinerElement</code> objects
     *                     or null if there are no rules
     * @param obligations a set of <code>Obligations</code> objects or null
     *                    if there are no obligations
     * @param definitions a set of <code>VariableDefinition</code> objects
     *                    that must provide all definitions referenced by
     *                    all <code>VariableReference</code>s in the policy
     * @param parameters the <code>List</code> of
     *                   <code>CombinerParameter</code>s provided for general
     *                   use by the combining algorithm
     * @param maxDelegationDepth  the maximum delegation depth authorised
     *                            by this policy set.    
     *                                
     * @throws IllegalArgumentException if the <code>List</code> of rules
     *                                  contains an object that is not a
     *                                  <code>RuleCombinerElement</code>
     */
    public Policy(URI id, String version, String xacmlVersion, 
                  RuleCombiningAlgorithm combiningAlg,
                  String description, PolicyIssuer issuer, Target target, 
                  String defaultVersion, List<CombinerElement> ruleElements, Set<Obligation> obligations, 
                  Set<VariableDefinition> definitions, List<CombinerParameter> parameters, int maxDelegationDepth) {
        super(id, version, xacmlVersion, combiningAlg, description, issuer, 
                target, defaultVersion, obligations, parameters, 
                maxDelegationDepth);

        // check that the list contains only RuleCombinerElements
        if (ruleElements != null) {
            Iterator<CombinerElement> it = ruleElements.iterator();
            while (it.hasNext()) {
            	CombinerElement o = it.next();
                if (! (o instanceof RuleCombinerElement)) {
                    throw new IllegalArgumentException("non-Rule in rules");
                }
            }
        }

        setChildren(ruleElements);

        // save the definitions
        if (definitions == null) {
            this.definitions = VariableDefinition.EMPTY_SET;
        } else {
            this.definitions = Collections.
                unmodifiableSet(new HashSet<VariableDefinition>(definitions));
        }
    }

    /**
     * Creates a new Policy based on the given root node. This is 
     * private since every class is supposed to use a getInstance() method
     * to construct from a Node, but since we want some common code in the
     * parent class, we need this functionality in a constructor.
     */
    private Policy(Node root) throws ParsingException {
        super(root, "Policy", "RuleCombiningAlgId");
        this.src = RuntimeInfo.getRuntimeInfo(this, root, ELEMENT_TYPE.POLICY);

        List<Rule> rules = new ArrayList<Rule>();
        Map<String, List<CombinerParameter>> parameters = new HashMap<String, List<CombinerParameter>>();
        
        Map<Object, Node> variableIds = new HashMap<Object, Node>();
        PolicyMetaData metaData = getMetaData();

        // first off, go through and look for any definitions to get their
        // identifiers up front, since before we parse any references we'll
        // need to know what definitions we support
        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE
                    && child.getLocalName().equals("VariableDefinition")) {
                String id = null;
                if (child.getAttributes().getNamedItem("VariableId") != null) {
                    id = child.getAttributes().getNamedItem("VariableId")
                              .getNodeValue();
                } else {
                    throw new ParsingException("Error while trying to parse "
                            + "VariableId in a Policy");
                }
                // it's an error to have more than one definition with the
                // same identifier
                if (variableIds.containsKey(id)) {
                    throw new ParsingException("multiple definitions for " +
                                               "variable " + id);
                }
                variableIds.put(id, child);
            }
        }

        // now create a manager with the defined variable identifiers
        VariableManager manager = new VariableManager(variableIds, metaData);
        this.definitions = new HashSet<VariableDefinition>();

        // next, collect the Policy-specific elements
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                String name = child.getLocalName();

                if (name.equals("Rule")) {
                    rules.add(Rule.getInstance(child, metaData, manager));
                } else if (name.equals("RuleCombinerParameters")) {
                    String ref = null;
                    if (child.getAttributes() != null) {
                        if (child.getAttributes().getNamedItem("RuleIdRef")
                                != null) {
                            ref = child.getAttributes()
                                       .getNamedItem("RuleIdRef")
                                       .getNodeValue();
                        } else {
                            throw new ParsingException("Required xml-attribute"
                                    + " RuleIdRef not found");
                        }
                    } else {
                        throw new ParsingException("Required xml-attribute"
                                + " RuleIdRef not found");
                    }

                    // if we found the parameter before than add it the end of
                    // the previous paramters, otherwise create a new entry
                    if (parameters.containsKey(ref)) {
                        List<CombinerParameter> list = parameters.get(ref);
                        parseParameters(list, child);
                    } else {
                        List<CombinerParameter> list = new ArrayList<CombinerParameter>();
                        parseParameters(list, child);
                        parameters.put(ref, list);
                    }
                } else if (name.equals("VariableDefinition")) {
                    String id = null;
                    if (child.getAttributes() != null) {
                        if (child.getAttributes().getNamedItem("VariableId")
                                != null) {
                            id = child.getAttributes()
                                      .getNamedItem("VariableId")
                                      .getNodeValue();
                        } else {
                            throw new ParsingException("Required xml-attribute"
                                   + " VariableId not found");
                        }
                    } else {
                        throw new ParsingException("Required xml-attribute"
                                + " VariableId not found");
                    }

                    // parsing definitions is a little strange, since they can
                    // contain references to definitions we haven't yet parsed
                    // or circular references, but we still want to verify the
                    // references and the types...so, for each definition, we
                    // ask the manager though getDefinition, which takes care
                    // of loading any forward references, handles loops, etc.
                    // It also handles caching definitions, so we don't end
                    // up parsing the same definitions multiple times
                    this.definitions.add(manager.getDefinition(id));
                }
            }
        }

        this.definitions = Collections.unmodifiableSet(this.definitions);

        // now make sure that we can match up any parameters we may have
        // found to a cooresponding Rule...
        List<CombinerElement> elements = new ArrayList<CombinerElement>();
        Iterator<Rule> it = rules.iterator();

        while (it.hasNext()) {
            Rule rule = it.next();
            String id = rule.getId().toString();
            List<CombinerParameter> list = parameters.remove(id);

            elements.add(new RuleCombinerElement(rule, list));
        }

        // ...and that there aren't extra parameters
        if (! parameters.isEmpty()) {
            throw new ParsingException("Unmatched parameters in Rule");
        }
        // finally, set the list of Rules
        setChildren(elements);
    }
    
    /**
     * The clone method
     * FIXME: this does no deep copy on the Lists and Sets.
     * 
     * @return  a copy of this object.
     */
    public Object clone() {
        Policy clone = (Policy)super.clone();
        clone.definitions = new HashSet<VariableDefinition>(this.definitions); 
        clone.setChildren(this.getChildElements());
        return clone;
    }
    
    /**
     * Helper method that parses out a collection of combiner parameters.
     */
    private void parseParameters(List<CombinerParameter> parameters, Node root)
        throws ParsingException
    {
        NodeList nodes = root.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE
                    && node.getLocalName().equals("CombinerParameter")) {
                parameters.add(CombinerParameter.getInstance(node));
            }
        }
    }

    /**
     * Creates an instance of a <code>Policy</code> object based on a
     * DOM node. The node must be the root of PolicyType XML object,
     * otherwise an exception is thrown.
     *
     * @param root the DOM root of a PolicyType XML type
     * 
     * @return  A policy object. 
     *
     * @throws ParsingException if the PolicyType is invalid
     */
    public static Policy getInstance(Node root) 
            throws ParsingException {
        // first off, check that it's the right kind of node
        if (root.getNodeType() != Node.ELEMENT_NODE
                || ! root.getLocalName().equals("Policy")) {
            throw new ParsingException("Cannot create Policy from root of " +
                                       "type " + root.getLocalName());
        }

        return new Policy(root);
    }

    /**
     * Returns the variable definitions in this Policy.
     *
     * @return a <code>Set</code> of <code>VariableDefinition</code>s
     */
    public Set<VariableDefinition> getVariableDefinitions() {
        return this.definitions;
    }

    /**
     * Encodes this <code>Policy</code> into its XML representation and writes
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
     * Encodes this <code>Policy</code> into its XML representation and writes
     * this encoding to the given <code>OutputStream</code> with
     * indentation.
     *
     * @param output a stream into which the XML-encoded data is written
     * @param charsetName  the character set to use in encoding of strings.
     *                     This may be null in which case the platform
     *                     default character set will be used.
     * @param indenter an object that creates indentation strings
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

        out.println(indent + "<Policy xmlns=\"" 
                + getMetaData().getXACMLIdentifier()
                + "\" PolicyId=\"" + getId().toString() 
                + "\" RuleCombiningAlgId=\"" 
                + getCombiningAlg().getIdentifier().toString() 
                + "\"");

        if (getMaxDelegationDepth() 
                != Constants.MAX_DELEGATION_DEPTH_UNDEFINED) {
            out.println("MaxDelegationDepth=\"" + getMaxDelegationDepth() 
                    + "\">");

        } else {
            out.println(">");
        }
        
        indenter.in();
        String nextIndent = indenter.makeString();

        String description = getDescription();
        if (description != null) {
            out.println(nextIndent + "<Description>" + description +
                        "</Description>");
        }
        encodePolicyIssuer(output, charsetName, indenter);
        
        
        String version = getDefaultVersion();
        if (version != null) {
            out.println("<PolicyDefaults><XPathVersion>" + version +
                        "</XPathVersion></PolicyDefaults>");
        }
        getTarget().encode(output, charsetName, indenter);

        Iterator<VariableDefinition> it = this.definitions.iterator();
        while (it.hasNext()) {
            ((VariableDefinition)(it.next())).encode(output, charsetName,
        	    indenter);
        }
        encodeCommonElements(output, charsetName, indenter);

        indenter.out();
        out.println(indent + "</Policy>");
    }

	public RuntimeInfo getRuntimeInfo() {
		return this.src;
	}
}
