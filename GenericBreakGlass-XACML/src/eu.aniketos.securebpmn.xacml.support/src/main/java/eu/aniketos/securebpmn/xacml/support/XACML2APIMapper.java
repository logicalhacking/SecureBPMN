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

package eu.aniketos.securebpmn.xacml.support;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;

import eu.aniketos.securebpmn.xacml.api.ErrorType;
import eu.aniketos.securebpmn.xacml.api.ReasonType;
import eu.aniketos.securebpmn.xacml.api.SecurityError;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoAttribute;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoObligation;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoResult;
import eu.aniketos.securebpmn.xacml.api.autho.Decision;

import com.sun.xacml.Obligation;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.ctx.Result;

public class XACML2APIMapper {

    private static final Logger logger = Logger.getLogger(XACML2APIMapper.class);

    public static AuthoResult getAuthoResult(ResponseCtx resCtx) throws SecurityError {

        AuthoResult	authoResult = new AuthoResult();

        if (resCtx.getResults().size() != 1 ) {
            logger.error("Received unexpected number of results: " + resCtx.getResults().size());
            throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR, "Received unexpected number of results");
        }

        Result xacmlResult = resCtx.getResults().iterator().next();

        authoResult.setDecision(Decision.getFromInt(xacmlResult.getDecision()));
        authoResult.setObligations(transformObligations(xacmlResult.getObligations()));
        authoResult.setStatusCode(xacmlResult.getStatus().getCode());
        authoResult.setStatusMessage(xacmlResult.getStatus().getMessage());

        // TODO get missing attributes


        return authoResult;
    }

    public static List<AuthoObligation> transformObligations(Set<Obligation> xacmlOblgs) {
        if ( xacmlOblgs != null && xacmlOblgs.size() >  0 ) {
            List<AuthoObligation> oblgs = new Vector<AuthoObligation>();

            for ( Obligation xacmlOblg : xacmlOblgs ) {
                AuthoObligation oblg = new AuthoObligation(xacmlOblg.getId());
                if ( xacmlOblg.getAssignments() != null && xacmlOblg.getAssignments().size() > 0 ) {
                    Collection<AuthoAttribute> attrs = new Vector<AuthoAttribute>(xacmlOblg.getAssignments().size());
                    for ( Attribute xacmlAttr : xacmlOblg.getAssignments()) {
                        attrs.add(new AuthoAttribute(AuthoAttribute.OBLIGATION_CATEGORY,
                                                     xacmlAttr.getId(), xacmlAttr.getValue().getType(), xacmlAttr.getValue().encode()));
                    }
                    oblg.setParameters(attrs);
                }
                oblgs.add(oblg);
            }
            return oblgs;
        } else {
            return null;
        }
    }

}
