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

package eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

/**
 *
 * This class allows to create a tree of objects (especially XACML elements)
 *
 */
public class XACMLTree<T> {
    private XACMLTree<T> father;

    private List<XACMLTree<T>> childs;

    private T element;

    private XACMLTree<T> cur_pointer = this;

    private static Logger logger = Logger.getLogger(XACMLTree.class);

    public XACMLTree() {

    }

    public XACMLTree(T element) {
        this.element = element;
    }

    public void addChild(T element) {
        if ( cur_pointer == this ) {
            if ( childs == null ) {
                childs = new Vector<XACMLTree<T>>();
            }
            XACMLTree<T> child = new XACMLTree<T>(element);
            child.father = this;
            //child.element = element;
            childs.add(child);
            cur_pointer = child;
        } else {
            cur_pointer.addChild(element);
        }
    }

    public boolean close(T element) {
        if ( cur_pointer == this ) {
            if ( this.element != element ) {
                logger.warn("False cur pointer! " + this.element.toString() + " vs. " + element.toString());
            }
            return true;
        } else {
            if (cur_pointer.close(element)) {
                cur_pointer = this;
            }
            return false;
        }
    }

    public List<XACMLTree<T>> getChilds() {
        return childs;
    }

    public void clear() {
        element = null;
        cur_pointer = this;
        if (childs != null) {
            childs.clear();
        }
    }

    public boolean isEmpty() {
        if ( element == null && (childs == null || childs.size() == 0 )) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasChilds() {
        if ( childs != null && childs.size() > 0 ) {
            return true;
        } else {
            return false;
        }
    }

    public XACMLTree<T> getCurrent() {
        if (cur_pointer == this ) {
            return this;
        } else {
            return cur_pointer.getCurrent();
        }
    }

    public T getElement() {
        return this.element;
    }

    public XACMLTree<T> getFather() {
        return father;
    }

    /**
     * returns true, if the provided treeElement is a superior node of "this"
     * @param treeElement
     * @return
     */
    public boolean isFather(T treeElement) {
        if ( this.getFather() == null || this.getFather().element == null ) {
            return false;
        } else if ( this.getFather().element == element ) {
            return true;
        } else {
            return this.getFather().isFather(treeElement);
        }
    }
}