/* Copyright 2012-2015 SAP SE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.aniketos.securebpmn.xacml.support.comb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.support.comb.PolicyLatticeIterator.MODE;

import com.sun.xacml.PolicyTreeElement;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.combine.CombinerElement;
import com.sun.xacml.combine.CombinerParameter;

public class LatticeElem {

    private int level = 0;
    private String identifier;
    /**
     * policy IDs this policy extends, defined as combiner parameter
     */
    private List<String> extendedPolicyIds;
    /**
     * policies which are extended by this policy (i.e., upwards)
     */
    private List<LatticeElem> extendedPolicies;
    /**
     * policies extending this policy (i.e, downwards)
     */
    private List<LatticeElem> extendingPolicies;
    private PolicyTreeElement permitPolicy, denyPolicy;

    private static final String
    POLICY_IDENDIFIER = "urn:policyLattice:identifier",
    POLICY_TYPE = "urn:policyLattice:type",
    EXTENDS_POLICY = "urn:policyLattice:extends";

    private static final Logger logger = Logger.getLogger(LatticeElem.class);

    public static LatticeElem addLatticeElem(CombinerElement combElem,
            Map<String, LatticeElem> elems) {

//		if ( ! (combElem.getElement() instanceof AbstractPolicy) ) {
//			logger.warn("LatticeElement has to be an instance of AbstractPolicy (got " + combElem.getElement().getClass().toString() + ")");
//			return null;
//		}
//
//		AbstractPolicy policy = (AbstractPolicy) combElem.getElement();

        // read parameters for policy
        List<CombinerParameter> params = combElem.getParameters();
        //List<CombinerParameter> params = policy.getCombiningParameters();
        String identifier = null, type = null;
        List<String> extendedPolicies = null;
        if ( params != null && params.size() > 0 ) {
            for ( CombinerParameter param : params ) {
                String name = param.getName();
                if ( POLICY_IDENDIFIER.equals(name) ) {
                    identifier = getString(name, param.getValue());
                } else if ( POLICY_TYPE.equals(name) ) {
                    type = getString(name, param.getValue());
                } else if ( EXTENDS_POLICY.equals(name))  {
                    if ( extendedPolicies == null ) {
                        extendedPolicies = new ArrayList<String>();
                    }
                    extendedPolicies.add(getString(name, param.getValue()));
                } else {
                    logger.warn("Unknown parameter " + name);
                }
            }
        } else {
            logger.warn("No CombinerParamters defined for " + combElem.getElement().getId());
        }

        if ( identifier ==  null ) {
            logger.warn("Cannot read policy " + combElem.getElement().getId() + " without defining " + POLICY_IDENDIFIER + " as combiner parameter");
            return null;
        } else {
            LatticeElem elem;
            if ( elems.containsKey(identifier)) {
                elem = elems.get(identifier);
            } else {
                elem = new LatticeElem(identifier);
                elems.put(identifier, elem);
            }

            if ( extendedPolicies != null ) {
                if ( elem.extendedPolicyIds != null ) {
                    logger.warn("For policy with ID " + identifier + " there are already extending policies defined");
                } else {
                    elem.extendedPolicyIds = extendedPolicies;
                }
            }

            if ( type != null ) {
                if ( "permit".equals(type.toLowerCase()) ) {
                    if ( elem.permitPolicy != null ) {
                        logger.warn("Overwriting permit policy for ID " + identifier);
                    }
                    elem.permitPolicy = combElem.getElement();
                } else if ( "deny".equals(type.toLowerCase()) ) {
                    if ( elem.denyPolicy != null ) {
                        logger.warn("Overwriting deny policy for ID " + identifier);
                    }
                }
            } else {
                // assume default value
                if ( elem.permitPolicy != null ) {
                    logger.warn("Overwriting permit policy for ID " + identifier);
                }
                elem.permitPolicy = combElem.getElement();
            }
            return elem;
        }
    }

    private LatticeElem(String identifer) {
        this.identifier = identifer;
    }


    private static String getString(String name, AttributeValue value ) {
        if ( value instanceof StringAttribute ) {
            return ((StringAttribute) value).getValue();
        } else {
            logger.warn("Attribute " + name + " was not an String Attribute");
            return null;
        }
    }



    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = Math.max(this.level, level);
    }

    public void resetLeve(int level) {
        this.level = level;
    }

    public PolicyTreeElement getPermitPolicy() {
        return permitPolicy;
    }

    public void setPermitPolicy(PolicyTreeElement permitPolicy) {
        this.permitPolicy = permitPolicy;
    }

    public PolicyTreeElement getDenyPolicy() {
        return denyPolicy;
    }

    public void setDenyPolicy(PolicyTreeElement denyPolicy) {
        this.denyPolicy = denyPolicy;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<String> getExtendedPolicyIds() {
        return extendedPolicyIds;
    }

    public void addExtendingPolicy(LatticeElem elem) {
        if ( this.extendingPolicies == null) {
            this.extendingPolicies = new ArrayList<LatticeElem>();
        }
        this.extendingPolicies.add(elem);
    }

    public List<LatticeElem> getExtendingPolicies() {
        return this.extendingPolicies;
    }

    public void resolveExtendedPolicies(Map<String, LatticeElem> policies) {
        if ( this.extendedPolicyIds != null &&
                this.extendedPolicies.size() > 0 ) {
            this.extendingPolicies = new ArrayList<LatticeElem>();

            for ( String elemId : extendedPolicyIds) {
                if ( policies.containsKey(elemId)) {
                    extendedPolicies.add(policies.get(elemId));
                } else {
                    logger.warn("Could not find a policy with ID " + elemId + " to resolve extended policies of policy " + identifier);
                }
            }
        }
    }

    public List<LatticeElem> getExtendedPolicies() {
        return this.extendedPolicies;
    }

    public Iterable<LatticeElem> downwards() {
        return new PolicyLatticeIterator(this, MODE.DOWNWARDS);
    }

    public Iterable<LatticeElem> upwards() {
        return new PolicyLatticeIterator(this, MODE.UPWARDS);
    }
}
