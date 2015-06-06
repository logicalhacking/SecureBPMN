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

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;

/**
 * Helper class, encoding XACML Objects to its XML representation
 *
 *
 */
public class XACMLEncoder {


    public static final String ERROR_EVALUATING_REQUEST = "todo";

    private static Logger logger = Logger.getLogger(XACMLEncoder.class);


    /**
     * Helper function, encodes a com.sun.xacml.ctx.ResponseCtx to a String, containing a XML representation
     * @param response
     * @return XML representation of response
     */
    public static String encodeResponseCtx(ResponseCtx response) {
        ByteArrayOutputStream bAOut = new ByteArrayOutputStream();
        BufferedOutputStream bOut = new BufferedOutputStream(bAOut);

        try {
            response.encode(bAOut, null);
            bOut.flush();
        } catch (UnsupportedEncodingException e) {
            logger.error("Unexpected UnsupportedEncodingException during encoding ResponseCtx to String:" + e.getMessage(), e );
            return ERROR_EVALUATING_REQUEST;
        } catch (IOException e) {
            logger.error("Unexpected IOException during encoding ResponseCtx to String:" + e.getMessage(), e );
            return ERROR_EVALUATING_REQUEST;
        }

        return bAOut.toString();
    }

    /**
     * Helper function, encodes a com.sun.xacml.ctx.RequestCtx to a String, containing a XML representation
     * @param request
     * @return XML representation of request
     */
    public static String encodeRequestCtx(RequestCtx request) {
        ByteArrayOutputStream bAOut = new ByteArrayOutputStream();
        BufferedOutputStream bOut = new BufferedOutputStream(bAOut);

        try {
            request.encode(bAOut, null);
            bOut.flush();
        } catch (UnsupportedEncodingException e) {
            logger.error("Unexpected UnsupportedEncodingException during encoding ResponseCtx to String:" + e.getMessage(), e );
            return ERROR_EVALUATING_REQUEST;
        } catch (IOException e) {
            logger.error("Unexpected IOException during encoding ResponseCtx to String:" + e.getMessage(), e );
            return ERROR_EVALUATING_REQUEST;
        }

        return bAOut.toString();
    }
}
