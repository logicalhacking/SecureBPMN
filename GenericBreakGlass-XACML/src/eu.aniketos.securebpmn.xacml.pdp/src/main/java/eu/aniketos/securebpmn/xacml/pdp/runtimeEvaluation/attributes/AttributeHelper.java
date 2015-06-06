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

package eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.attributes;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;

import com.sun.xacml.PolicyTreeElement;
import com.sun.xacml.attr.AttributeDesignator;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.BagAttribute;
import com.sun.xacml.attr.BooleanAttribute;
import com.sun.xacml.attr.IntegerAttribute;
import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.attr.TimeAttribute;
import com.sun.xacml.attr.TypeIdentifierConstants;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.debug.RuntimeInfo;

/**
 * This class contains some helper functions which help to handle
 * all kinds of tasks regarding to XACML attributes
 *
 *
 */
public class AttributeHelper {

    private static Map<URI, AttributeValue > abstractAttributes;

    private static Logger logger = Logger.getLogger(AttributeHelper.class);

    static {
        abstractAttributes = new HashMap<URI, AttributeValue>();

        abstractAttributes.put(TypeIdentifierConstants.STRING_URI,
                               new TestStringAttribute("__test_value__"));
        abstractAttributes.put(TypeIdentifierConstants.INTEGER_URI,
                               new TestIntegerAttribute(Integer.MIN_VALUE + 42));
        abstractAttributes.put(TypeIdentifierConstants.TIME_URI,
                               new TestTimeAttribute(new Date(42)));
        abstractAttributes.put(TypeIdentifierConstants.BOOLEAN_URI,
                               new TestBooleanAttribute(false));

    }

    /**
     * returns attributes which can be used for abstract evaluation, i.e.,
     * those values are returned by the attribute resolver and can be identified
     * by the special combining algs and for analysis purposes as "abstract" values
     * using the isAbstractAttribute function
     * @param attributeType
     * @return
     */
    public static AttributeValue getAbstractAttribute(URI attributeType) {
        if ( abstractAttributes.containsKey(attributeType) ) {
            return abstractAttributes.get(attributeType);
        } else {
            throw new RuntimeException("missing default value for dataType " + attributeType);
        }
    }

    /**
     * returns if the attribute is an abstract attribute, i.e., was injected
     * into the engine as retrieved from the getAbstractAttribute function
     * @param value
     * @return
     */
    public static boolean isAbstractAttribute(AttributeValue value) {
        if ( value instanceof AbstractAttribute ) {
            return true;
        } else if ( value instanceof BagAttribute ) {
            for ( AttributeValue val : ((BagAttribute) value).iterable() ) {
                if ( val instanceof AbstractAttribute) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * returns if the result contains an abstract attribute, i.e., was injected
     * into the engine as retrieved from the getAbstractAttribute function
     * @param result
     * @return
     */
    public static boolean containsAbstractValue(EvaluationResult result) {
        if ( result != null && result.getAttributeValue() != null ) {
            return isAbstractAttribute(result.getAttributeValue());
        } else {
            return false;
        }
    }

    public static AttributeIdentifier getAttributeIdentifier(AttributeDesignator attrDsgn) {
        return new AttributeIdentifier(attrDsgn.getCategory(), attrDsgn.getType(),
                                       attrDsgn.getId(), attrDsgn.getIssuer());
    }


    /**
     * helper functions which creates a bag around a singe value
     * @param value
     * @return
     */
    public static BagAttribute createSingleBag(AttributeValue value) {
        ArrayList<AttributeValue> list = new ArrayList<AttributeValue>();
        list.add(value);
        return new BagAttribute(value.getType(), list);
    }

    /**
     * returns the next PolicyTreeElement for an XACML element (requiring its RuntimeInfo
     * object); for example, when having an attributeDesignator, one can decide if
     * this is used within a rule or within a policy
     * @param runtimeInfo
     * @return
     */
    public static PolicyTreeElement getPolicyTreeElement(RuntimeInfo runtimeInfo) {
        if ( runtimeInfo.getXACMLObject() instanceof PolicyTreeElement ) {
            return (PolicyTreeElement) runtimeInfo.getXACMLObject();
        } else {
            if ( runtimeInfo.getCalledFrom() == null ) {
                logger.warn("Could not find encapsulating PolicyTreeElement: reached most upper element without finding a PolicyTreeElement");
                return null;
            }
            return getPolicyTreeElement(runtimeInfo.getCalledFrom());
        }
    }

    static class TestStringAttribute extends StringAttribute implements AbstractAttribute {
        TestStringAttribute(String value) {
            super(value);
        }
    }

    static class TestIntegerAttribute extends IntegerAttribute implements AbstractAttribute {
        TestIntegerAttribute(int value) {
            super(value);
        }
    }

    static class TestTimeAttribute extends TimeAttribute implements AbstractAttribute {
        TestTimeAttribute(Date date) {
            super(date);
        }
    }

    static class TestBooleanAttribute extends BooleanAttribute implements AbstractAttribute {
        TestBooleanAttribute(boolean value) {
            super(value);
        }
    }
}
