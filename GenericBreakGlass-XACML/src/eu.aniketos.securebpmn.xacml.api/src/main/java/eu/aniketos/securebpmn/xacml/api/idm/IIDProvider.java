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

package eu.aniketos.securebpmn.xacml.api.idm;

/**
 *
 * Resovles the AuthInfo with aim of a ID Provider
 * 
 *
 */
public interface IIDProvider {
	/**
	 * Resolved the AuthInfo t
	 * 
	 * @param authInfo
	 * @return
	 */
	IdInfo authenticate(AuthInfo authInfo);
	
	boolean isValidInfo(String info);
	
	AuthInfo createAuthInfo(String info);
	
	String getProviderID();
}
