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

package eu.aniketos.securebpmn.xacml.api.log;



public interface ILogStore {
	/**
	 * store the provided AccessControlRequest to the log store;
	 * this function should not block and return immediately, i.e.,
	 * the request should be put on a queue and handled by another 
	 * thread
	 * @param requ
	 */
	void storeAccessControlRequest(AccessControlRequest requ);
	
	boolean logBreakGlassAccess();

	boolean shutdown();
	
	/**
	 * returns a new unique ID; this ID should be used to create a new
	 * AccessControlRequest
	 * @return
	 */
	Long getNewEvaluationId();
	
	AccessControlRequest getAccessControlRequest(Long evaluationId);
}
