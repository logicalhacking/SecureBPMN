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

package eu.aniketos.securebpmn.xacml.support.helper;

import org.apache.log4j.Logger;

import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;

import eu.aniketos.securebpmn.xacml.api.log.AccessControlRequest;
import eu.aniketos.securebpmn.xacml.support.XACMLEncoder;

public class LogPreparer {

    private static Logger logger = Logger.getLogger(LogPreparer.class);

    public static void prepare(AccessControlRequest requ) {
        RequestCtx requCtx = (RequestCtx) requ.getRequest();
        ResponseCtx respCtx = (ResponseCtx) requ.getResponse();

        if ( requ.getXacmlRequest() == null || requ.getXacmlResponse() == null) {
            requ.setXacmlRequest(XACMLEncoder.encodeRequestCtx(requCtx));
            requ.setXacmlResponse(XACMLEncoder.encodeResponseCtx(respCtx));

            if ( ! (requ.getXacmlRequest() == null && requ.getXacmlResponse() == null)
                    || requ.getResource() == null || requ.getResult() == null ) {
                logInconsistant(requ);
            }
        }

        if ( requ.getResource() == null || requ.getResult() == null ) {
            //TODO read from requCtx
        }
    }

    private static void logInconsistant(AccessControlRequest requ) {
        logger.warn("Inconsistant AccessControlRequest: XacmlRequest" + requ.getXacmlRequest()  +
                    ", XacmlResponse: " +  requ.getXacmlResponse() +
                    ", Resource: " + requ.getResource() +
                    ", Result: " + requ.getResult());
    }

}
