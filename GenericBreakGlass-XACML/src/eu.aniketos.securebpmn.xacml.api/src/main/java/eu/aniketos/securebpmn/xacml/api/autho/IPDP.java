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

package eu.aniketos.securebpmn.xacml.api.autho;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import eu.aniketos.securebpmn.xacml.api.SecurityError;
import eu.aniketos.securebpmn.xacml.api.idm.IdInfo;

@WebService(targetNamespace="aniketos.eu/autho")
public interface IPDP {

	String evaluateXACML(@WebParam(name="xacmlRequ")String xacmlRequest) throws SecurityError;

	AuthoResult evaluate(@WebParam(name="idInfo") IdInfo idInfo,
			@WebParam(name="resource") String resource, 
			@WebParam(name="action")String action, 
			@WebParam(name="attributes") List<AuthoAttribute> attributes) throws SecurityError;
		
	String getXACMLPEPConfig();
	
	boolean logBreakGlassAccess(@WebParam(name="evaluationId") long evaluationId, @WebParam(name="justification") String justification);
	
	void notifyStateChange(@WebParam(name="evaluationId") long evaluationId) throws SecurityError;
	
}
