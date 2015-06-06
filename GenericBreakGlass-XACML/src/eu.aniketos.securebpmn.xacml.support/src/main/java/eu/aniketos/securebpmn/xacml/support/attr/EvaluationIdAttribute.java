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

package eu.aniketos.securebpmn.xacml.support.attr;

import java.net.URI;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import eu.aniketos.securebpmn.xacml.support.finder.IEvaluationIdContext;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.ParsingException;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.cond.EvaluationResult;

/**
 *
 * This attribute represents an urn:type:evaluationId which has to be evaluated at runtime by the xacml engine.
 * Within the evaluation, the evaluation ID is read from the evaluation context and stored within the attribute.
 *
 */
public class EvaluationIdAttribute extends AttributeValue {


    public static final String identifier = "urn:type:evaluationId";
    public static final URI identifierURI = URI.create(identifier);

    public static final Long INVALID = new Long(-1);

    private static Logger logger = Logger.getLogger(EvaluationIdAttribute.class);

    public static final String RUNTIME = "RUNTIME";

    private Long value;



    public EvaluationIdAttribute(Long value) {
        super(identifierURI);
        this.value = value;
    }

    protected EvaluationIdAttribute(URI type) {
        super(type);
    }

    @Override
    public EvaluationResult evaluate(EvaluationCtx context) {

        if ( value == null || value.longValue() == -1 ) {
            if ( context instanceof IEvaluationIdContext ) {
                //retreive evaluationID from current context
                value = ((IEvaluationIdContext) context).getCurrentEvaluationId();
            } else {
                logger.warn("Received non-IEvaluationIdContext: Could not determine evaluation ID");
                value = new Long(-1);
            }
        }
        return new EvaluationResult(this);
    }

    public static EvaluationIdAttribute getInstance(String value) {
        if ( value == null  || RUNTIME.equals(value) ) {
            return new EvaluationIdAttribute(new Long(-1));
        } else {
            try {
                long newValue = Long.parseLong(value);
                return new EvaluationIdAttribute(newValue);
            } catch (NumberFormatException e) {
                logger.warn("Could not transfer evaluationID \"" + value + "\" to long value: " + e.getMessage());
                return new EvaluationIdAttribute(new Long(-1));
            }
        }
    }

    public static EvaluationIdAttribute getInstance(Node root) throws ParsingException {
        if (root.getFirstChild() != null) {
            return getInstance(root.getFirstChild().getNodeValue());
        } else {
            return new EvaluationIdAttribute(new Long(-1));
        }
    }


    @Override
    public String encode() {
        return value.toString();
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    public Long getEvaluationId() {
        return value;
    }

}
