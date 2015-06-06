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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PolicyLatticeIterator implements Iterator<LatticeElem>, Iterable<LatticeElem> {

    enum MODE {
        UPWARDS, // iterates over all refining policies, i.e., policies with lower levels
        DOWNWARDS // iterates over all extending policies, i.e., policies with higher levels
    }

    Queue<LatticeElem> todos;

    MODE mode;

    /**
     *
     * @param elements should be all on the same level
     */
    PolicyLatticeIterator(List<LatticeElem> elements, MODE mode) {
        this.mode = mode;
        todos = new LinkedList<LatticeElem>();
        for ( LatticeElem elem : elements) {
            todos.add(elem);
        }
    }

    PolicyLatticeIterator(LatticeElem element, MODE mode) {
        this.mode = mode;
        todos = new LinkedList<LatticeElem>();
        todos.add(element);
    }

    public boolean hasNext() {
        if ( todos.size() > 0 ) {
            return true;
        } else {
            return false;
        }
    }

    public LatticeElem next() {
        LatticeElem next = todos.remove();
        if ( mode == MODE.DOWNWARDS ) {
            if ( next.getExtendingPolicies() != null ) {
                for ( LatticeElem elem : next.getExtendingPolicies() ) {
                    // check if element to put on queue
                    if ( elem.getLevel() + 1 == next.getLevel() &&    // put on queue only if on next level
                            ( elem.getExtendedPolicyIds().size() == 1 || // if referenced elem has only one refining (extends) relation
                              ! todos.contains(elem) )	) {			      // or, if it has several, check that it is not already on the queue
                        todos.add(elem);
                    }
                }
            }
        } else if ( mode == MODE.UPWARDS ) {
            if ( next.getExtendedPolicyIds() != null ) {
                for ( LatticeElem elem : next.getExtendedPolicies() ) {
                    if ( elem.getLevel() -1 == next.getLevel() &&    // put on queue only if on next (upper) level
                            ( elem.getExtendingPolicies().size() == 1 || // if referenced elem has only one extending (refines) relation
                              ! todos.contains(elem))) {			   	 // or, if it has severl, that that it is not alrey on the queue
                        todos.add(elem);
                    }
                }
            }
        }
        return next;
    }

    public void remove() {
        throw new RuntimeException("remove is not permitted by PolicyLatticeIterator");
    }

    public Iterator<LatticeElem> iterator() {
        return this;
    }

}
