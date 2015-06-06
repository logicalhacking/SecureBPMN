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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.attributes.AbstractAttribute;
import eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation.attributes.AttributeHelper;

import com.sun.xacml.AbstractPolicy;
import com.sun.xacml.attr.AttributeDesignator;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.BagAttribute;
import com.sun.xacml.attr.TimeAttribute;
import com.sun.xacml.combine.CombiningAlgorithm;
import com.sun.xacml.cond.Apply;
import com.sun.xacml.cond.BagFunction;
import com.sun.xacml.cond.EqualFunction;
import com.sun.xacml.cond.Expression;
import com.sun.xacml.cond.Function;
import com.sun.xacml.cond.HigherOrderFunction;
import com.sun.xacml.cond.LogicalFunction;
import com.sun.xacml.cond.TimeInRangeFunction;
import com.sun.xacml.ctx.Result;

public class HOLEncoder implements ExtToolTypeEncoder {

    private static HOLEncoder instance = new HOLEncoder();
    private static Map<URI, FunctionInfo> functions;

    private static final String VALUE = "#";
    private static final String VAR = "$";

    private static final String EQUAL = "==";
    //private static final String NOT_EQUAL = "<>";
    private static final String LESS_OR_EQUAL = "<=";
    private static final String MORE_OR_EQUAL = ">=";
    //private static final String LESS = "<";
    private static final String MORE = ">";

    private static final String AND = "AND";
    private static final String OR = "OR";

    //Higher order functions
    private static final String ANYOFANY = "anyOfAny";

    //bag functions
    private static final String BAGSIZE = "bagsize";

    private static final String time_max = 24*60*60*1000 + "";

    static {
        functions = new HashMap<URI, FunctionInfo>();
        //FunctionInfo(FunctionID functionID, String operator, Notation notation, boolean defaultConstr)
        functions.put(
            URI.create(EqualFunction.NAME_STRING_EQUAL),
            new FunctionInfo(FunctionID.STRING_EQUAL, EQUAL, Notation.INFIX));
        functions.put(
            URI.create(EqualFunction.NAME_ANYURI_EQUAL),
            new FunctionInfo(FunctionID.ANYURI_EQUAL, EQUAL, Notation.INFIX));
        functions.put(
            URI.create(EqualFunction.NAME_BOOLEAN_EQUAL),
            new FunctionInfo(FunctionID.BOOLEAN_EQUAL, EQUAL, Notation.INFIX));
        functions.put(
            URI.create(EqualFunction.NAME_INTEGER_EQUAL),
            new FunctionInfo(FunctionID.INTEGER_EQUAL, EQUAL, Notation.INFIX));

        functions.put(
            URI.create(LogicalFunction.NAME_AND),
            new FunctionInfo(FunctionID.AND, AND, Notation.INFIX));
        functions.put(
            URI.create(LogicalFunction.NAME_OR),
            new FunctionInfo(FunctionID.OR, OR, Notation.INFIX));

        functions.put(
            URI.create("urn:oasis:names:tc:xacml:1.0:function:string-one-and-only"),
            new FunctionInfo(FunctionID.ONE_AND_ONLY, "", Notation.PREFIX));
        functions.put(
            URI.create("urn:oasis:names:tc:xacml:1.0:function:time-one-and-only"),
            new FunctionInfo(FunctionID.ONE_AND_ONLY, "", Notation.PREFIX));
        functions.put(
            URI.create("urn:oasis:names:tc:xacml:1.0:function:boolean-one-and-only"),
            new FunctionInfo(FunctionID.ONE_AND_ONLY, "", Notation.PREFIX));

        functions.put(
            URI.create(TimeInRangeFunction.NAME),
            new FunctionInfo(FunctionID.TIME_IN_RANGE, null, Notation.CUSTOM));

        // TODO
        functions.put(
            URI.create(HigherOrderFunction.NAME_ANY_OF_ANY),
            new FunctionInfo(FunctionID.ANY_OF_ANY, ANYOFANY, Notation.FUNCTION));

        functions.put(
            URI.create(BagFunction.FUNCTION_NS + "string" + BagFunction.NAME_BASE_BAG_SIZE),
            new FunctionInfo(FunctionID.STRING_BAGSIZE, BAGSIZE, Notation.FUNCTION));

    }

    private enum FunctionID {
        STRING_EQUAL,
        ANYURI_EQUAL,
        BOOLEAN_EQUAL,
        INTEGER_EQUAL,
        AND,
        OR,
        TIME_IN_RANGE,
        ONE_AND_ONLY,
        ANY_OF_ANY,
        STRING_BAGSIZE
    }

    private enum Notation {
        PREFIX,
        INFIX,
        POSTFIX,
        FUNCTION,
        CUSTOM
    }

    private KnownAttrStore attrReslv;

    public static HOLEncoder getInstance() {
        return instance;
    }

    public void setKnownAttrStore(KnownAttrStore attrReslv) {
        this.attrReslv = attrReslv;
    }

    public String getFunctionEnc(Function func) {
        if ( functions.containsKey(func.getIdentifier()) ) {
            return functions.get(func.getIdentifier()).operator;
        } else {
            throw new RuntimeException("Function " + func.getIdentifier() + " currently not supported!");
        }
    }

//	private void printInfix(Apply apply, Function func, FunctionInfo info, PrintStream out) {
//		out.print("(");
//		List<Expression> childs = apply.getChildren();
//		if ( childs.size() != 2) {
//			throw new RuntimeException("Infix notation currently only possible for 2 parameters!");
//		}
//		printExpression(childs.get(0), out);
//		out.print(" " + info.operator + " ");
//		printExpression(childs.get(1), out);
//		out.print(") ");
//	}

    private void printInfix(Apply apply, Function func, FunctionInfo info, PrintStream out) {
        out.print("(");
        List<Expression> childs = apply.getChildren();
        if ( childs.size() < 2) {
            throw new RuntimeException("Infix notation currently only possible for 2 or more parameters!");
        }
        printExpression(childs.get(0), out);
        for ( int i = 1; i < childs.size(); ++i ) {
            out.print(" " + info.operator + " ");
            printExpression(childs.get(i), out);
        }
        out.print(") ");
    }

    private void printPrefix(Apply apply, Function func, FunctionInfo info, PrintStream out) {
        out.print(" " + info.operator + " (");
        for ( Expression child : apply.getChildren() ) {
            printExpression(child, out);
        }
        out.print(")");
    }

    /**
     * prints functions which are part of an apply element
     * @param apply
     * @param func
     * @param info
     * @param out
     */
    private void printFunction(Apply apply, Function func, FunctionInfo info, PrintStream out) {

        out.print(" (" + info.operator + " ( ");
        boolean first = true;
        for ( Expression child : apply.getChildren() ) {
            if ( first ) {
                first = false;
            } else {
                out.print(", ");
            }
            printExpression(child, out);
        }
        out.print(")");
    }

    /**
     * prints functions which are "standaline", i.e., there is a <Function> element in the policy
     * <br/>
     * mainly/only (? - TODO) used for higher order functions?
     * @param func
     * @param out
     */
    private void printFunction(Function func, PrintStream out) {
        FunctionInfo info = getFunctionInfo(func);

        out.print(info.operator);
    }

    private void printExpression(Expression expression, PrintStream out) {
        if ( expression instanceof BagAttribute ) {
            out.print(getBagEnc((BagAttribute)expression));
        } else if ( expression instanceof AttributeValue ) {
            out.print(getAttrValueEnc( ( AttributeValue) expression));
        } else  if ( expression instanceof AttributeDesignator ) {
            AttributeDesignator attr = (AttributeDesignator) expression;
            AttributeIdentifier attrId = AttributeHelper.getAttributeIdentifier(attr);
            AttributeValue attrValRes = attrReslv.getAttributeValue(attrId);
            out.print(getAttrDesignatorEnc(attr, attrValRes));
        } else if ( expression instanceof Apply ) {
            printApply ( (Apply) expression, out);
        } else if ( expression instanceof Function ) {
            printFunction ( (Function)expression, out);
        }
        else {
            throw new RuntimeException("Unexpected element: " + expression);
        }
    }



    public void printApply(Apply apply, PrintStream out) {
        Function func = apply.getFunction();
        FunctionInfo info = getFunctionInfo(func);

        switch ( info.notation ) {
        case INFIX:
            printInfix(apply, func, info, out);
            break;
        case PREFIX:
            printPrefix(apply, func, info, out);
            break;
        case POSTFIX:
            throw new RuntimeException("TODO: currently not implemented");
        case FUNCTION:
            printFunction(apply, func, info, out);
            break;
        case CUSTOM:
            switch ( info.functionID ) {
            case TIME_IN_RANGE:
                printTimeInRange(apply, func, info, out);
                break;
            default:
                throw new RuntimeException("For function " + func.getIdentifier() + " no Custom notation is available");
            }
            break;
        default:
            throw new RuntimeException("Notation " + info.notation + " currently not supported");
        }
    }


    public String getBagEnc(BagAttribute bag) {
        if ( bag.size() != 1) {
            throw new RuntimeException("BagAttributes with size != 1 currently not supported!");
        }
        return getAttrValueEnc( bag.iterator().next());
    }

    public String getAttrValueEnc(AttributeValue val) {
        if ( val instanceof BagAttribute) {
            return getBagEnc( (BagAttribute) val);
        } else if ( val instanceof TimeAttribute ) {
            return VALUE + ((TimeAttribute)val).getMilliseconds();
        } else {
            return VALUE + val.encode();
        }
    }

    public String getAttrDesignatorEnc(AttributeDesignator attr, AttributeValue attrVal) {
        if (attrVal != null && ! AttributeHelper.isAbstractAttribute(attrVal)  ) {
            return getAttrValueEnc(attrVal);
        } else {
            return VAR + attr.getId();
        }
    }


    public String getAND() {
        return AND;
    }

    public String getOR() {
        // TODO Auto-generated method stub
        return OR;
    }

    public String getDecision(int decision) {
        switch ( decision) {
        case Result.DECISION_DENY:
            return "DENY";
        case Result.DECISION_PERMIT:
            return "PERMIT";
        case Result.DECISION_INDETERMINATE:
            return "INDETERMINATE";
        case Result.DECISION_NOT_APPLICABLE:
            return "NOT_APPLICABLE";
        }
        throw new RuntimeException("Currently not supported decision: " + decision);
    }

    public String getCombiningAlg(CombiningAlgorithm alg) {
        String name = alg.getIdentifier().toString();
        return name.substring(name.lastIndexOf(":")+1);
    }


    private FunctionInfo getFunctionInfo(Function func) {
        FunctionInfo info =  functions.get(func.getIdentifier());
        if ( info == null ) {
            throw new RuntimeException("Function " + func.getIdentifier() + " currently not supported!");
        }
        return info;
    }


    private static class FunctionInfo {
        FunctionID functionID;
        String operator;
        Notation notation;

        FunctionInfo(FunctionID functionID, String operator, Notation notation) {
            this.functionID = functionID;
            this.operator = operator;
            this.notation = notation;
        }
    }

    /*
     * CUSTOM FUNCTION
     */

    /**
     * urn:oasis:names:tc:xacml:2.0:function:time-in-range Function:
     *
     * three values: a, b, c: true, if a is within b and c <br/>
     * two cases:
     * <li>
     * <ul> b <= c: a >= b AND a <= c </ul>
     * <ul> b > c: ( a >= b AND a <= 24h) OR ( a >= 0h AND a <= c ) </ul>
     * </li>
     * if either b or c are variables, then <br/>
     * (b<=c AND a >= b AND a <= c ) OR  ( b>=c AND (( a >= b AND a <= 24h) OR ( a >= 0h AND a <= c )) )
     */
    private void printTimeInRange(Apply apply, Function func, FunctionInfo info, PrintStream out) {


        List<Expression> childs = apply.getChildren();
        if ( childs.size() != 3) {
            throw new RuntimeException("TimeInRange function only possible for 3 parameters!");
        }

        String[] vars = new String[3];
        for ( int i = 0; i < 3; ++i ) {
            if ( childs.get(i) instanceof AttributeValue ) {
                vars[i] = getAttrValueEnc( (AttributeValue) childs.get(i));
            } else if ( childs.get(i) instanceof AttributeDesignator ) {
                AttributeDesignator attrDsgn = (AttributeDesignator) childs.get(i);
                AttributeValue attrVal = attrReslv.getAttributeValue(
                                             AttributeHelper.getAttributeIdentifier(attrDsgn));
                if (attrVal != null && ! (attrVal instanceof AbstractAttribute )) {
                    vars[i] = getAttrValueEnc(attrVal);
                } else {
                    vars[i] = attrDsgn.getId().toString();
                }
            } else if ( childs.get(i) instanceof Apply ) {
                ByteArrayOutputStream bOS = new ByteArrayOutputStream();
                PrintStream out2 = new PrintStream(bOS);
                printApply( (Apply)childs.get(i), out2);
                out2.flush();
                vars[i] = bOS.toString();
            } else {
                throw new RuntimeException("Unrecognised type for param " + i  + " for TimeInRange function: " + childs.get(i).getClass());
            }
        }

        if ( vars[1].startsWith(VAR) || vars[2].startsWith(VAR) ) {
            //b or c are variables - case distinction b < c or b > c
            out.print("( ( " + vars[1] + LESS_OR_EQUAL + vars[2] +  " " + AND + " " + getBsC(vars) + " ) " +
                      OR + " ( " + vars[1] + MORE + vars[2] +  " " + AND + " " + getBsC(vars) + " ) )");
        } else {
            int b = Integer.parseInt(vars[1].substring(1));
            int c = Integer.parseInt(vars[2].substring(1));
            if ( b <= c ) {
                out.print(getBsC(vars));
            } else {
                out.print(getBgC(vars));
            }
        }
    }
    /**
     * helper function for printTimeInRange - when second value smaller equal third value
     * @param vars
     * @return
     */
    private String getBsC(String[] vars) {
        //  b < c: a >= b AND a <= c
        return "( " + vars[0]  + MORE_OR_EQUAL + vars[1] + " " + AND + " " + vars[0] + LESS_OR_EQUAL + vars[2] + ")";
    }

    /**
     * helper function for printTimeInRange - when second value larger third value
     * @param vars
     * @return
     */
    private String getBgC(String[] vars) {
        // b > c: ( a >= b AND a <= 24h)
        //        OR ( a >= 0h AND a <= c )
        return "( ( " + vars[0] + MORE_OR_EQUAL + vars[1] + " " + AND + " " + vars[0] + LESS_OR_EQUAL + time_max + ") " +
               OR + " ( " + vars[0] + MORE_OR_EQUAL + "0 " + AND + " " + vars[0] + LESS_OR_EQUAL + vars[2] + ") )";
    }

    public String getPolicy(AbstractPolicy policy) {
        return getCombiningAlg(policy.getCombiningAlg());
    }


}
