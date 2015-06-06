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

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;


import eu.aniketos.securebpmn.xacml.api.SecurityError;
import eu.aniketos.securebpmn.xacml.api.ErrorType;
import eu.aniketos.securebpmn.xacml.api.ReasonType;
import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoAttribute;
import eu.aniketos.securebpmn.xacml.api.idm.IdInfo;
import eu.aniketos.securebpmn.xacml.support.helper.ResourceCreator;

import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.RequestElement;
import com.sun.xacml.ctx.ResponseCtx;




/**
 * Helper class, decoding XML strings to XACML Objects
 *
 */
public class XACMLDecoder {

    private static Logger logger = Logger.getLogger(XACMLDecoder.class);

    public static RequestCtx decodeRequestCtx(String request) throws SecurityError {
        RequestCtx requ;
        try {
            requ = RequestCtx.getInstance(new ByteArrayInputStream(request.getBytes()));
        } catch (ParsingException e) {
            logger.error("ParsingException during parsing xacmlRequest", e);
            throw new SecurityError(ErrorType.AUTHORIZATION_FAILED, ReasonType.INVALID_XACML, "ParsingError: " + e.getMessage());
        }
        return requ;
    }

    public static ResponseCtx decodeResponseCtx(String reponse) throws SecurityError {
        ResponseCtx resp;
        try {
            resp = ResponseCtx.getInstance(new ByteArrayInputStream(reponse.getBytes()));
        } catch (ParsingException e) {
            logger.error("ParsingException during parsing xacmlRequest", e);
            throw new SecurityError(ErrorType.AUTHORIZATION_FAILED, ReasonType.INVALID_XACML, "ParsingError: " + e.getMessage());
        }
        return resp;
    }

    public static RequestCtx decodeRequestCtx(IdInfo authinfo, URI resource,
            String action) throws SecurityError {

        Set<RequestElement> requElems = new HashSet<RequestElement>();

        try {
            requElems.add(Category.SUBJECT.getRequestElement(authinfo.getUserId()));
        } catch (ParsingException e) {
            logger.error("Could not create subject element from subject " + authinfo.getUserId() + ": ParsingException: " + e.getMessage(), e);
        }

        requElems.add(( (ResourceCreator) Category.RESOURCE.getElementCreator()).getRequestElement(resource));

        if ( action != null ) {
            try {
                requElems.add(Category.ACTION.getRequestElement(action));
            } catch (ParsingException e) {
                logger.error("Could not create action element from action " + action + ": ParsingException: " + e.getMessage(), e);
            }
        }

        return new RequestCtx(requElems, null, null);
    }


    public static RequestCtx decodeRequestCtx(IdInfo idInfo, URI resource,
            String action, List<AuthoAttribute> attributes) throws SecurityError {

        //"cheap" creation if no attributes are present
        if ( attributes == null || attributes.size() == 0 ) {
            return decodeRequestCtx(idInfo, resource, action);
        } else {

            Map<Category, Set<Attribute>> attrsPerCat = new HashMap<Category, Set<Attribute>>();

            Set<Attribute> subjects = new HashSet<Attribute>();
            try {
                Attribute subjectId = Category.SUBJECT.getAttributeDefaultType(idInfo.getUserId());
                subjects.add(subjectId);
            } catch (ParsingException e) {
                logger.error("Could not create subject element from subject " + idInfo.getUserId() + ": ParsingException: " + e.getMessage(), e);
            }
            attrsPerCat.put(Category.SUBJECT, subjects);

            //RESOURCE as URI
            Set<Attribute> resources = new HashSet<Attribute>();
            resources.add( ( (ResourceCreator) Category.RESOURCE.getElementCreator()).getAttributeDefaultType(resource));
            attrsPerCat.put(Category.RESOURCE, resources);

            //ACTION if != NULL
            if (action != null ) {
                Set<Attribute> actions = new HashSet<Attribute>();
                try {
                    actions.add(Category.ACTION.getAttributeDefaultType(action));
                } catch (ParsingException e) {
                    // TODO log
                    e.printStackTrace();
                }
                attrsPerCat.put(Category.ACTION, actions);
            }

            for (AuthoAttribute attr : attributes) {
                AttributeIdentifier attrId = attr.getAttributeIdentifier();
                Category cat = Category.getCategory(attrId.getCategory());
                Set<Attribute> attrs;
                if ( attrsPerCat.containsKey(cat) ) {
                    attrs = attrsPerCat.get(cat);
                } else {
                    attrs = new HashSet<Attribute>();
                    attrsPerCat.put(cat, attrs);
                }
                try {
                    attrs.add(cat.getAttribute(attrId.getAttributeId(), attrId.getAttributeType(), attr.getValue()));
                } catch (ParsingException e) {
                    // TODO log
                    e.printStackTrace();
                } catch (UnknownIdentifierException e) {
                    // TODO log
                    e.printStackTrace();
                }
            }

            Set<RequestElement> requElems = new HashSet<RequestElement>();

            for (Category cat : attrsPerCat.keySet()) {
                requElems.add(cat.getRequestElement(attrsPerCat.get(cat)));
            }

            return new RequestCtx(requElems, null, null);
        }
    }
}
