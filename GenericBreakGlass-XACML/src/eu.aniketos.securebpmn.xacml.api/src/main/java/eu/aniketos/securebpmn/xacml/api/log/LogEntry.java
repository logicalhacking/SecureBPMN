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

import java.util.Date;

public abstract class LogEntry {
	
	public enum Log_Type {
		ACCESS_REQUEST,
		BREAK_GLASS
	}
	
	protected LogEntry(Date arrival, Log_Type type) {
		this.arrival = arrival;
		this.type = type;
	}
	
	private Date arrival;
//	private byte[] chkSum; 
//	
//	private Long previous;
	
	private Log_Type type;
	
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	
	public Log_Type getLogtype() {
		return type;
	}
}
