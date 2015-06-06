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

public interface IContextProvider {
	/**
	 * resolves one attribute
	 * 
	 * writes the result to attr and returns it as string
	 * 
	 * @param attr
	 * @return
	 */
	String resolveAttribute(AuthoAttribute attr);
	/**
	 * resolves a set of attributes, returns number of errors
	 * @param attr
	 * @return
	 */
	int resolveAttributes(List<AuthoAttribute> attr);
}
