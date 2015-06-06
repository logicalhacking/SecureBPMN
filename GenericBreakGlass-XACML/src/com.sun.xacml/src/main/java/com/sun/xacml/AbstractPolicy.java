
/*
 * @(#)AbstractPolicy.java
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
import com.sun.xacml.combine.CombiningAlgorithm;
import com.sun.xacml.combine.CombiningAlgFactory;
import com.sun.xacml.combine.PolicyCombiningAlgorithm;
import com.sun.xacml.combine.RuleCombiningAlgorithm;

import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.PolicyIssuer;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.debug.RuntimeInfo;
import com.sun.xacml.debug.RuntimeInfo.ELEMENT_TYPE;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Represents an instance of an XACML policy. 
 *
 * @since 1.0
 * @author Seth Proctor
 * @author Marco Barreno 
 * @author Ludwig Seitz
 *
 */


public abstract class AbstractPolicy implements PolicyTreeElement, Cloneable {

    // atributes associated with this policy
    private URI idAttr;
    private CombiningAlgorithm combiningAlg;
    
    /**
     * The version number for this policy. This is _not_ the XACML or
     * XPath version. This is for versioning policies/policySets.
     */
    private String version;

    // the elements in the policy
    private String description;
    private PolicyIssuer policyIssuer;
    private Target target;

    // the default version of XPath
    private String defaultVersion;

    // the meta-data associated with this policy
    private PolicyMetaData metaData;

    // the child elements under this policy represented simply as the
    // PolicyTreeElements...
    private List<PolicyTreeElement> children;
    // ...or the CombinerElements that are passed to combining algorithms
    private List<CombinerElement> childElements;

    // any obligations held by this policy
    private Set<Obligation> obligations;

    // the list of combiner parameters
    private List<CombinerParameter> parameters;
    
    // the maximum delegation depth authorised by this AbstractPolicy.
    private int maxDelegationDepth = Constants.MAX_DELEGATION_DEPTH_UNDEFINED;
    
    protected RuntimeInfo src;
    
    /**
     * Constructor used by <code>PolicyReference</code>, which supplies
     * its own values for the methods in this class.
     */
    protected AbstractPolicy() {
        // Used by PolicyRefence
    }
        
    /**
     * Constructor used to create a policy from concrete components.
     *
     * @param id the policy id
     * @param version the policy version or null for the default (this is
     *                always null for pre-2.0 policies)
     * @param xacmlVersion  the XACML version identifier.
     * @param combiningAlg the combining algorithm to use
     * @param description describes the policy or null if there is none
     * @param issuer the policy's issuer, used for delegation, can be null.
     * @param target the policy's target
     * @param defaultVersion the XPath version to use for selectors, 
     *                       can be null.
     * @param obligations the policy's obligations, can be null.
     * @param parameters  the policy's combiner parameters, can be null.
     * @param maxDelegationDepth  the maximum depth of delegation authorised
     *                            by this policy. A value of -1 indicates
     *                            that this was not set.
     */
    protected AbstractPolicy(URI id, String version, String xacmlVersion,
                             CombiningAlgorithm combiningAlg,
                             String description, PolicyIssuer issuer,
                             Target target, String defaultVersion, 
                             Set<Obligation> obligations, List<CombinerParameter> parameters, 
                             int maxDelegationDepth) {
        this.idAttr = id;
        this.combiningAlg = combiningAlg;
        this.description = description;
        this.target = target;
        this.defaultVersion = defaultVersion;

        this.policyIssuer = issuer;
        
        if (version == null) {
            this.version = "1.0";
        } else {
            this.version = version;
        }
        
        this.metaData = new PolicyMetaData(xacmlVersion, defaultVersion);
        
        if (obligations == null) {
            this.obligations = Obligation.EMPTY_SET;
        } else {
            this.obligations = Collections.
                unmodifiableSet(new HashSet<Obligation>(obligations));
        }

        if (parameters == null) {
            this.parameters = CombinerParameter.EMPTY_LIST;
        } else {
            this.parameters = Collections.
                unmodifiableList(new ArrayList<CombinerParameter>(parameters));
        }
        
        this.maxDelegationDepth = maxDelegationDepth;
    }

    /**
     * Constructor used by child classes to initialize the shared data from
     * a DOM root node.
     *
     * @param root the DOM root of the policy
     * @param policyPrefix either "Policy" or "PolicySet"
     * @param combiningName name of the field naming the combining alg
     *
     * @throws ParsingException if the policy is invalid
     */
    protected AbstractPolicy(Node root, String policyPrefix,
                             String combiningName) throws ParsingException {
        // get the attributes, all of which are common to Policies
        NamedNodeMap attrs = root.getAttributes();

        try {
            // get the attribute Id
            this.idAttr = new URI(attrs.getNamedItem(policyPrefix + "Id")
                                    .getNodeValue());
        } catch (Exception e) {
            throw new ParsingException("Error while parsing required "
                    + "attribute " + policyPrefix + "Id of a " 
                    + policyPrefix, e);
        }

        // see if there's a version
        Node versionNode = attrs.getNamedItem("Version");
        if (versionNode != null) {
            this.version = versionNode.getNodeValue();
        } else {
            // assign the default version
            this.version = "1.0";
        }
        
        // see if there's a MaxDelegationDepth
        Node depthNode = attrs.getNamedItem("MaxDelegationDepth");
        if (depthNode != null) {
            this.maxDelegationDepth 
                = Integer.parseInt(depthNode.getNodeValue());
        } else {
            this.maxDelegationDepth = Constants.MAX_DELEGATION_DEPTH_UNDEFINED;
        }

        // now get the combining algorithm...
        try {
            URI algId = new URI(attrs.getNamedItem(combiningName).
                                getNodeValue());
            CombiningAlgFactory factory = CombiningAlgFactory.getInstance();
            this.combiningAlg = factory.createAlgorithm(algId);
        } catch (URISyntaxException e) {
            throw new ParsingException("Error parsing combining algorithm" +
                                       " in " + policyPrefix, e);
        } catch (UnknownIdentifierException e) {
            throw new ParsingException("Error parsing combining algorithm" +
                                       " in " + policyPrefix, e);
        }
        
        // ...and make sure it's the right kind
        if (policyPrefix.equals("Policy")) {
            if (! (this.combiningAlg instanceof RuleCombiningAlgorithm)) {
                throw new ParsingException("Policy must use a Rule " +
                                           "Combining Algorithm");
            }
        } else {
            if (! (this.combiningAlg instanceof PolicyCombiningAlgorithm)) {
                throw new ParsingException("PolicySet must use a Policy " +
                                           "Combining Algorithm");
            }
        }
        
        // do an initial pass through the elements to pull out the
        // defaults, if any, so we can setup the meta-data
        NodeList children = root.getChildNodes();
        
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE 
                    && child.getLocalName().equals(
                            policyPrefix + "Defaults")) {
                handleDefaults(child);
            }
        }
        
        // with the defaults read, create the meta-data
        this.metaData = new PolicyMetaData(root.getNamespaceURI(), 
                this.defaultVersion);
        
        // now read the remaining policy elements        
        this.obligations = new HashSet<Obligation>();
        this.parameters = new ArrayList<CombinerParameter>();
        this.policyIssuer = null;
        children = root.getChildNodes();

        // now read the policy elements
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                String cname = child.getLocalName();
                if (cname.equals("Description")) {
                    if (child.getFirstChild() != null) {
                        this.description = child.getFirstChild().getNodeValue();
                    }
                } else if (cname.equals("PolicyIssuer")) {
                    parsePolicyIssuerAttributes(child);
                } else if (cname.equals("Target")) {
                    this.target = Target.getInstance(child, this.metaData);
                } else if (cname.equals("Obligations")) {
                    parseObligations(child);
                } else if (cname.equals(policyPrefix + "Defaults")) {
                    handleDefaults(child);
                } else if (cname.equals("CombinerParameters")) {
                    handleParameters(child);
                } 
            }
        }

        // finally, make sure the obligations and parameters are immutable
        this.obligations = Collections.unmodifiableSet(this.obligations);
        this.parameters = Collections.unmodifiableList(this.parameters);
    }
    
    /**
     * The clone method. 
     * FIXME: this does no deep copy on the Lists and Sets.
     * 
     * @return  a copy of this object.
     */
    public Object clone() {
        try {
            AbstractPolicy clone = (AbstractPolicy)super.clone();
                clone.idAttr = this.idAttr;

                try {
                    clone.combiningAlg 
                        = CombiningAlgFactory.getInstance().createAlgorithm(
                            this.combiningAlg.getIdentifier());
                } catch (UnknownIdentifierException e) {
                    throw new RuntimeException("Impossible exception: " 
                            + e.getMessage());
                }
                clone.version = this.version;
                clone.description = this.description;
                if (this.policyIssuer != null) {
                    clone.policyIssuer 
                        = (PolicyIssuer)this.policyIssuer.clone();
                } else { clone.policyIssuer = null; }
                clone.target = (Target)this.target.clone();
                clone.defaultVersion = this.defaultVersion;
                clone.metaData = this.metaData;
                clone.obligations = new HashSet<Obligation>(this.obligations);
                clone.parameters = new ArrayList<CombinerParameter>(this.parameters);
                clone.maxDelegationDepth =  this.maxDelegationDepth;
                return clone;
        } catch (CloneNotSupportedException e1) {// this should never happen
            throw new RuntimeException("Couldn't clone AbstractPolicy");
        }
    }

    /**
     * Helper routine that parses the PolicyIssuer attributes in 
     * the policy or policy set
     * @param root  the root node of PolicyIssuer
     * @throws ParsingException
     */
    private void parsePolicyIssuerAttributes(Node root) 
            throws ParsingException {
        NodeList nodes = root.getChildNodes();
        Set<Attribute> issuerAttr = new HashSet<Attribute>();
        
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE
                    && node.getLocalName().equals("Attribute")) {
                List<Attribute> attrs = Attribute.getInstances(node);
                issuerAttr.addAll(attrs);
            }
        }
       
        this.policyIssuer = new PolicyIssuer(issuerAttr);
    } 
    
    /**
     * Helper routine to parse the obligation data
     * 
     * @param root  The node containing the obligation data. 
     * 
     * @throws ParsingException 
     */
    private void parseObligations(Node root) throws ParsingException {
        NodeList nodes = root.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE
                    && node.getLocalName().equals("Obligation")) {
                this.obligations.add(Obligation.getInstance(node));
            }
        }
    }

    /**
     * There used to be multiple things in the defaults type, but now
     * there's just the one string that must be a certain value, so it
     * doesn't seem all that useful to have a class for this...we could
     * always bring it back, however, if it started to do more
     * 
     * @param root  The node that contains the XPathVersion. 
     */
    private void handleDefaults(Node root) throws ParsingException {
        this.defaultVersion = null;
        NodeList nodes = root.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE
                    && node.getLocalName().equals("XPathVersion")) {
                if (node.getFirstChild() != null) {
                    this.defaultVersion = node.getFirstChild().getNodeValue();
                } else { 
                    throw new ParsingException("XPathVersion xml-attribute" 
                            + "didn't have a value");
                            
                }
            }
        }
    }

    /**
     * Handles all the CombinerParameters in the policy or policy set
     * 
     * @param root  The node containing the CombinerParameters.
     *  
     * @throws ParsingException 
     */
    private void handleParameters(Node root) throws ParsingException {
        NodeList nodes = root.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE
                    && node.getLocalName().equals("CombinerParameter")) {
                this.parameters.add(CombinerParameter.getInstance(node));
            }
        }
    }
 
    /**
     * Returns the id of this policy
     *
     * @return the policy id
     */
    public URI getId() {
        return this.idAttr;
    }

    /**
     * Returns the version of this policy. If this is an XACML 1.x policy
     * then this will always return <code>"1.0"</code>.
     *
     * @return the policy version
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Returns the combining algorithm used by this policy
     *
     * @return the combining algorithm
     */
    public CombiningAlgorithm getCombiningAlg() {
        return this.combiningAlg;
    }

    /**
     * Returns the list of input parameters for the combining algorithm. If
     * this is an XACML 1.x policy then the list will always be empty.
     *
     * @return a <code>List</code> of <code>CombinerParameter</code>s
     */
    public List<CombinerParameter> getCombiningParameters() {
        return this.parameters;
    }

    /**
     * Returns the given description of this policy or null if there is no
     * description
     *
     * @return the description or null
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a <code>PolicyIssuer</code> that represents the 
     * PolicyIssuer for this policy.
     *
     * @return a <code>PolicyIssuer</code> or null
     */
    public PolicyIssuer getPolicyIssuer() {
        return this.policyIssuer;
    }
    
    /**
     * Returns the target for this policy
     *
     * @return the policy's target
     */
    public Target getTarget() {
        return this.target;
    }

    /**
     * Returns the XPath version to use or null if none was specified
     *
     * @return XPath version or null
     */
    public String getDefaultVersion() {
        return this.defaultVersion;
    }

    /**
     * Returns the <code>List</code> of children under this node in the
     * policy tree. Depending on what kind of policy this node represents
     * the children will either be <code>AbstractPolicy</code> objects
     * or <code>Rule</code>s.
     *
     * @return a <code>List</code> of child nodes
     */
    public List<PolicyTreeElement> getChildren() {
        return this.children;
    }

    /**
     * Returns the <code>List</code> of <code>CombinerElement</code>s that
     * is provided to the combining algorithm. This returns the same set
     * of children that <code>getChildren</code> provides along with any
     * associated combiner parameters.
     *
     * @return a <code>List</code> of <code>CombinerElement</code>s
     */
    public List<CombinerElement> getChildElements() {
        return this.childElements;
    }

    /**
     * Returns the Set of obligations for this policy, which may be empty
     *
     * @return the policy's obligations
     */
    public Set<Obligation> getObligations() {
        return this.obligations;
    }

    /**
     * Returns the meta-data associated with this policy
     * 
     * @return the policy's meta data.
     */
    public PolicyMetaData getMetaData() {
        return this.metaData;
    }
    
    /**
     *@return  Returns the maximum delegation depth or
     *         Integer.MAX_VALUE if there is none.
     */
    public int getMaxDelegationDepth() {
        return this.maxDelegationDepth;
    }
    
    /**
     * Given the input context sees whether or not the request matches this
     * policy. This must be called by combining algorithms before they
     * evaluate a policy. This is also used in the initial policy finding
     * operation to determine which top-level policies might apply to the
     * request.
     *
     * @param context the representation of the request
     *
     * @return the result of trying to match the policy and the request
     */
    public MatchResult match(EvaluationCtx context) {
        return this.target.match(context);
    }

    /**
     * Sets the child policy tree elements for this node, which are passed
     * to the combining algorithm on evaluation. The <code>List</code> must
     * contain <code>CombinerElement</code>s, which in turn will contain
     * <code>Rule</code>s or <code>AbstractPolicy</code>s, but may not
     * contain both types of elements.
     *
     * @param children1 a <code>List</code> of <code>CombinerElement</code>s
     *                 representing the child elements used by the combining
     *                 algorithm
     */
    protected void setChildren(List<CombinerElement> children1) {
        // we always want a concrete list, since we're going to pass it to
        // a combiner that expects a non-null input
        if (children1 == null) {
            this.children = PolicyTreeElement.EMPTY_LIST;
        } else {
            // NOTE: since this is only getting called by known child
            // classes we don't check that the types are all the same
            List<PolicyTreeElement> list = new ArrayList<PolicyTreeElement>();
            
            for ( CombinerElement element : children1 ) {
            	list.add(element.getElement());
            }

            this.children = Collections.unmodifiableList(list);
            this.childElements = Collections.unmodifiableList(children1);
        }
    }

    /**
     * Tries to evaluate the policy by calling the combining algorithm on
     * the given policies or rules. The <code>match</code> method must always
     * be called first, and must always return MATCH, before this method
     * is called.
     *
     * @param context the representation of the request
     *
     * @return the result of evaluation
     */
    public Result evaluate(EvaluationCtx context) {
        //Need to this as parent policy set if this is a policy set
        if (this instanceof PolicySet) {
            context.saveParentPolicySet(this);
        }
        
        RuntimeInfo combSrc = null;
        // check if runtime info is there and actually used 
        if ( src != null ) {  
        	//combSrc = RuntimeInfo.getIndirectSourceLocator(src, ELEMENT_TYPE.COMBINING_ALG);
        	//combSrc = src.getIndirectSourceLocator(ELEMENT_TYPE.COMBINING_ALG);
        	combSrc = src.getIndirectRuntimeInfo(combiningAlg, ELEMENT_TYPE.COMBINING_ALG);
        	this.combiningAlg.setRuntimeInfo(combSrc);
        }
        
        //prepare result variable
        Result result = this.combiningAlg.combine(context, this.parameters, 
                                                  this.childElements);   
        
        if ( combSrc != null ) {
        	this.combiningAlg.unsetRuntimeInfo(combSrc);
        }
        
        if (this instanceof PolicySet) {
            context.popParentPolicySet();
        }
               
        //create a set for collecting obligations
        Set<Obligation> collectedObligations = new HashSet<Obligation>();
        
        // Do we need to reduce this request to the trustedIssuer?
        if (!hasTrustedIssuer()) {
            if (result.getDecision() == Result.DECISION_NOT_APPLICABLE) {
                // we do not need to process reduction for this result
                return null;        
            }
             context.createReductionGraph();           
            Result reductionResult = context.getReductionGraph().reduce(
                    context, result, this.idAttr);
            if (reductionResult == null 
                    || reductionResult.getDecision() 
                            == Result.DECISION_INDETERMINATE) {
                //The reduction failed so we return this result now.
                return reductionResult;        
            }
            //check for obligations
            collectedObligations.addAll(reductionResult.getObligations()); 
        }
        
        // add the obligations of the current policy
        collectedObligations.addAll(this.obligations);
        
        // if we have no obligations, we're done
        if (collectedObligations.size() == 0) {
            return result;
        }
        
        // now, see if we should add any obligations to the set
        int effect = result.getDecision();
        
        if ((effect == Result.DECISION_INDETERMINATE) 
                || (effect == Result.DECISION_NOT_APPLICABLE)) {
            // we didn't permit/deny, so we never return obligations
            return result;
        }
            
        Iterator<Obligation> it = collectedObligations.iterator();
        while (it.hasNext()) {
            Obligation obligation = it.next();
            if (obligation.getFulfillOn() == effect) {
            	result.addObligation(obligation.evaluate(context));
            }
        }   
        // finally, return the result
        return result;
    } 
    
    /**
     * Routine used by <code>Policy</code> and <code>PolicySet</code> to
     * encode the PolicyIssuer element.
     *
     * @param output  a stream into which the XML-encoded data is written
     * @param charsetName  the character set to use in encoding of strings.
     *                     This may be null in which case the platform 
     *                     default character set will be used.
     * @param indenter  an object that creates indentation strings
     * 
     * @throws UnsupportedEncodingException 
     */
    protected void encodePolicyIssuer(OutputStream output,
	    String charsetName, Indenter indenter)
    throws UnsupportedEncodingException {

        if (this.policyIssuer != null) {
            PrintStream out = new PrintStream(output);
            String indent = indenter.makeString();

            out.println(indent + "<PolicyIssuer>");
            indenter.in();

            Iterator<Map.Entry<URI, Set<Attribute>>> it = this.policyIssuer.getAttributes().entrySet()
                                           .iterator();
            while (it.hasNext()) {
            	Map.Entry<URI, Set<Attribute>> entry = it.next();
                Iterator<Attribute> it2 = entry.getValue().iterator();
                while (it2.hasNext()) {
                    Attribute attr = it2.next();
                    attr.encode(output, charsetName, indenter);
                }
            }

            out.println(indent + "</PolicyIssuer>");
            indenter.out();
        }
    }
    
    
    /**
     * Routine used by <code>Policy</code> and <code>PolicySet</code> to
     * encode some common elements.
     *
     * @param output  a stream into which the XML-encoded data is written
     * @param charsetName  the character set to use in encoding of strings.
     *                     This may be null in which case the platform
     *                     default character set will be used.
     * @param indenter  an object that creates indentation strings
     * 
     * @throws UnsupportedEncodingException 
     */
    protected void encodeCommonElements(OutputStream output,
	    String charsetName, Indenter indenter)
            throws UnsupportedEncodingException {
    	
    	for ( CombinerElement element : this.childElements ) {
    		element.encode(output, charsetName, indenter);
    	}
    
        if (this.obligations.size() != 0) {
            PrintStream out = new PrintStream(output);
            String indent = indenter.makeString();

            out.println(indent + "<Obligations>");
            indenter.in();

            Iterator<Obligation> oblIt = this.obligations.iterator();
            while (oblIt.hasNext()) {
            	oblIt.next().encode(output, charsetName, indenter);
            }

            out.println(indent + "</Obligations>");
            indenter.out();
        }
    }
 
    /**
     * Method for checking if a policy was issued by the trusted issuer
     * 
     * @return True if the PolicyIssuer is the trusted issuer
     */
     public boolean hasTrustedIssuer() {
        if (this.policyIssuer == null) {
            return true;
        }
        return false;
     }
     
     /**
      * Method for getting the issue date of this policy, if it was set in the
      * policyIssuer member. Returns null if it has not been set.
      * 
      * @return the date at which this policy was issued or null.
      */
     public Date getIssueDate() {
         if (this.policyIssuer != null) {
             return this.policyIssuer.getIssueDate();
         }
        return null;
     }
     
     public RuntimeInfo getRuntimeInfo() {
    	 return this.src;
     }
     
//     public void setSourceLocator(RuntimeInfo src) {
//    	 this.src = src;
//     }
     
     /**
      * Returns a copy of this abstract policy with a new isser.
      * This is useful when policies are rewritten, for instance after
      * a digital signature is verified, in which case the issuer
      * is generated based on the signature of the policy.
      * Also useful for implementing some models of revocation.
      * 
      * @param issuer  The new policy issuer.
      * @return A identical copy of the abstract policy with     
      *         a new issuer.
      */
     public AbstractPolicy copyWithNewPolicyIssuer(PolicyIssuer issuer) {
    	 AbstractPolicy newPolicy = (AbstractPolicy)clone();	 
    	 newPolicy.policyIssuer = issuer;
    	 return newPolicy;
     }
}
