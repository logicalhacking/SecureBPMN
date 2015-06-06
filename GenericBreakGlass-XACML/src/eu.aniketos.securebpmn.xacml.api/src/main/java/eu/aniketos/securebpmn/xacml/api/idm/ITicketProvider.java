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

import java.net.URL;

import eu.aniketos.securebpmn.xacml.api.SecurityError;

public interface ITicketProvider {
	/**
	 * This method logs in at the SSO Engine (i.e., the CAS Server) 
	 * <br/>
	 * In case of <b>any error</b> (e.g., ConnectException, IOException) which result out of an 
	 * incorrect infrastructure, this functions returns null, which signals "not logged in"
	 * 
	 * @param username used to login at the SSO Engine (i.e., CAS Server)
	 * @param password used to login at the SSO Engine (i.e., CAS Server)
	 * @return the value of the Ticket Granting Cookie. <b>CAUTION</b> If the return value is null, 
	 * the login has not been successful, it has to be called login again! 
	 */
	public String login(String username, String password) throws SecurityError;
	
	/**
	 * This function is used for every (Web Service) Call that is done in the SoKNOS system, as for each of these
	 * call a Service Ticket (CAS Ticket) is required.
	 * 
	 * @param service The URL of the service for which the service ticket is required 
	 * @return
	 * @throws InvalidCASTicketException This exception is thrown, if there is no valid CAS Ticket available (i.e., 
	 * the user is not logged in or the CAS session expired, etc.)
	 */
	public AuthInfo getServiceTicket(URL service) throws SecurityError; 
	
	/**
	 * Invalidates any existing active session. If no active session is available, no error is thrown. If
	 * this function is called, for further getServiceTicket() calls a (re)login is required 
	 * 
	 * @return <b>true</b>, if logout has been successful or no active session was found and <b>false</b>, 
	 * if any error occured (e.g., network error)
	 */
	public boolean logout();
}
