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

package eu.aniketos.securebpmn.xacml.cond;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.attr.AnyURIAttribute;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.BooleanAttribute;
import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.cond.Expression;
import com.sun.xacml.cond.FunctionBase;

public class CustomCompareFunction extends FunctionBase {

    public static final String NAMESPACE = "urn:custom:";

    public static final String NAME_STRING_STARTSWITH = NAMESPACE + "string-starts-with";
    public static final int ID_STRING_STARTSWITH = 0;

    public static final String NAME_URI_STARTSWITH = NAMESPACE + "uri-starts-with";
    public static final int ID_URI_STARTSWITH = 1;

    public static final String NAME_URI_STARTSWITH_STRING = NAMESPACE + "uri-starts-with-string";
    public static final int ID_URI_STARTSWITH_STRING = 2;


    private static final String[][] functionParams = new String[][] {
        new String[] { StringAttribute.identifier, StringAttribute.identifier },
        new String[] { AnyURIAttribute.identifier, AnyURIAttribute.identifier },
        new String[] { AnyURIAttribute.identifier, StringAttribute.identifier }
    };


    /**
     * Creates a new <code>StringFunction</code> object.
     *
     * @param functionName the standard XACML name of the function to be
     *                     handled by this object, including the full namespace
     *
     * @throws IllegalArgumentException if the function is unknown
     */
    public CustomCompareFunction(String functionName) {
        super (functionName, getId(functionName),
               functionParams[getId(functionName)],
               new boolean[] {false, false},
               BooleanAttribute.identifier, false);

//    	int id = getId(functionName);
//
//    	switch ( id ) {
//    	case ID_STRING_STARTSWITH:
//            super(functionName, getId(functionName), StringAttribute.identifier,
//                    false, 2, BooleanAttribute.identifier, false);
//
//    	}

    }

    public static Set<String> getSupportedIdentifiers() {
        Set<String> set = new HashSet<String>();

        set.add(NAME_STRING_STARTSWITH);
        set.add(NAME_URI_STARTSWITH);
        set.add(NAME_URI_STARTSWITH_STRING);

        return set;
    }

    public EvaluationResult evaluate(List<Expression> inputs, EvaluationCtx context) {

        // Evaluate the arguments
        AttributeValue [] argValues = new AttributeValue [inputs.size()];
        EvaluationResult result = evalArgs(inputs, context, argValues);
        if (result != null) {
            return result;
        }
        // Now that we have real values, perform the comparison operation

        String s1, s2;

        boolean resultVal;

        switch (getFunctionId()) {
        case ID_STRING_STARTSWITH:
            s1 = ((StringAttribute) argValues[0]).getValue();
            s2 = ((StringAttribute) argValues[1]).getValue();
            break;
        case ID_URI_STARTSWITH:
            s1 = (((AnyURIAttribute) argValues[0]).getValue()).toString();
            s2 = (((AnyURIAttribute) argValues[1]).getValue()).toString();
            break;
        case ID_URI_STARTSWITH_STRING:
            s1 = (((AnyURIAttribute) argValues[0]).getValue()).toString();
            s2 = ((StringAttribute) argValues[1]).getValue();
            break;
        default :
            throw new IllegalArgumentException("unknown functionId " + getFunctionId());
        }
        resultVal = s1.startsWith(s2) | s2.startsWith(s1);

        return EvaluationResult.getInstance(resultVal);
    }

    private static int getId(String functionName) {
        if ( NAME_STRING_STARTSWITH.equals(functionName) ) {
            return ID_STRING_STARTSWITH;
        } else if ( NAME_URI_STARTSWITH.equals(functionName) ) {
            return ID_URI_STARTSWITH;
        } else if ( NAME_URI_STARTSWITH_STRING.equals(functionName) ) {
            return ID_URI_STARTSWITH_STRING;
        }


        throw new IllegalArgumentException("unknown match function: " +
                                           functionName);
    }
}
