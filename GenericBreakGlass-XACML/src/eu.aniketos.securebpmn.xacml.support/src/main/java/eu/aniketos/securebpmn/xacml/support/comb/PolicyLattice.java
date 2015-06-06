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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.support.comb.PolicyLatticeIterator.MODE;

import com.sun.xacml.combine.CombinerElement;

public class PolicyLattice implements Iterable<LatticeElem> {

    private List<LatticeElem> level1;

    private Map<String, LatticeElem> policies;

    private static Logger logger = Logger.getLogger(PolicyLattice.class);

    public PolicyLattice(String latticeIdentifier, List<CombinerElement> combElem) {
        if ( logger.isDebugEnabled() ) {
            logger.debug("Creating PolicyLattice "
                         + (latticeIdentifier == null ? "without identifier" : latticeIdentifier) + " with "
                         + combElem.size() + " elements");
        }
        policies = new HashMap<String, LatticeElem>();
        level1 = new ArrayList<LatticeElem>();


        // read all policies
        for (int i = 0; i < combElem.size(); ++i ) {
            LatticeElem elem = LatticeElem.addLatticeElem(combElem.get(i), policies);
            if (logger.isDebugEnabled() && elem != null ) {
                logger.debug("Read policy element with ID \"" + elem.getIdentifier()
                             + "\" with " + (elem.getExtendedPolicyIds() == null ? 0 : elem.getExtendedPolicyIds().size()) + " extended policy definitions");
            }
        }

        //
        for ( LatticeElem elem : policies.values() ) {
            elem.resolveExtendedPolicies(policies);
            if ( elem.getExtendedPolicyIds() == null ||
                    elem.getExtendedPolicyIds().size() == 0 ) {
                level1.add(elem);
                logger.debug("Added " + elem.getIdentifier() + " as " + level1.size() + "th element as level 1 element");
            } else {
                for ( String extPol : elem.getExtendedPolicyIds()) {
                    if ( policies.containsKey(extPol) ) {
                        policies.get(extPol).addExtendingPolicy(elem);
                    } else {
                        logger.warn("Policy " + elem.getIdentifier() + " defined extension to " + extPol + ", but did not find policy");
                    }
                }
            }
//				if ( policies.containsKey(elem.getExtendedPolicy() )) {
//				policies.get(elem.getExtendedPolicy()).addChild(elem);
//				logger.debug("Added policy " + elem.getIdentifier()
//						+ " as child to policy" + elem.getExtendedPolicy() );
//			} else {
//				logger.warn("Policy with id " + elem.getIdentifier() +
//						" defines extending an unkown policy with id " + elem.getExtendedPolicy());
//			}
        }
        for ( LatticeElem elem : level1 ) {
            setPolicyLevel(elem, 1);
        }
    }


    private void setPolicyLevel(LatticeElem elem, int level) {
        elem.setLevel(level);
        if ( elem.getExtendingPolicies() != null ) {
            for ( LatticeElem refElem : elem.getExtendingPolicies() ) {
                setPolicyLevel(refElem, level + 1);
            }
        }
    }


    public Iterator<LatticeElem> iterator() {
        return new PolicyLatticeIterator(level1, MODE.DOWNWARDS);
    }




}
