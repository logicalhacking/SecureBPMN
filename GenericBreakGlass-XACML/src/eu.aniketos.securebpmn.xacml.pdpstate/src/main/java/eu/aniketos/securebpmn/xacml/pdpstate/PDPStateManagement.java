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

package eu.aniketos.securebpmn.xacml.pdpstate;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import eu.aniketos.securebpmn.xacml.api.SecurityError;
import eu.aniketos.securebpmn.xacml.api.ErrorType;
import eu.aniketos.securebpmn.xacml.api.ReasonType;
import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;
import eu.aniketos.securebpmn.xacml.api.autho.IPDPStateManagement;
import eu.aniketos.securebpmn.xacml.api.log.AccessControlRequest;
import eu.aniketos.securebpmn.xacml.pdpstate.db.AttributeDBIdentifier;
import eu.aniketos.securebpmn.xacml.pdpstate.db.HibernateUtil;

public class PDPStateManagement implements IPDPStateManagement {
	
	private static PDPStateManagement instance;
	
	private PDPState pdpState;
	private HibernateUtil dbUtil;
	//private IPDP pdp;
	
	private Map<String, AttributeDBIdentifier> attributes = new HashMap<String, AttributeDBIdentifier>();
	
	private Map<String, Dependency> dependencies = new HashMap<String, Dependency>();
	//private Map<AttributeDBIdentifier, Assignment> dependencies_remove = new HashMap<AttributeDBIdentifier, Assignment>();

	
	private static Logger logger = Logger.getLogger(PDPStateManagement.class);
	
	private static final String ATTRIBUTE = "attribute:",
			DEPENDENCY = "dependency:";
	public static final String CONFFILE_NAME = "pdpStateDependencies.conf";
	
	public static PDPStateManagement getInstance() {
		if ( instance == null ) {
			instance = new PDPStateManagement();
		}
		return instance;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties log4jProps = new Properties();
		log4jProps.load(new BufferedInputStream(new FileInputStream(new File("src/test/log4j.properties"))));
		PropertyConfigurator.configure(log4jProps);
		PDPStateManagement.getInstance();
	}
	
	private PDPStateManagement() {
		pdpState = PDPState.getInstance();
		dbUtil = pdpState.getHibernateUtil();
		
		try {
			// check if there is a conf file on the class path
			File confFile = new File(CONFFILE_NAME);
			if ( ! confFile.exists() ) {
				// try with conf before...
				confFile = new File("conf/" +CONFFILE_NAME);
			}
			if ( ! confFile.exists() ) {
				// try from mvn default test location
				confFile = new File("src/test/resources/" +CONFFILE_NAME);
			}
			
			if ( confFile.exists()) {
				logger.info("Reading PDPStateManagement configuration from " + confFile.getAbsolutePath());
				readConfig(confFile);
			}
			else {
				logger.warn("Reading PDPStateManagement configuration from jar; You might define a more accurate " + CONFFILE_NAME);
				readConfig(this.getClass().getResourceAsStream("/" + CONFFILE_NAME));
			}
		} catch (IOException e) {
			logger.error("Could not configuration file from PDPStateManagement: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	public Map<String, Dependency> getDependencies() {
		return Collections.unmodifiableMap(dependencies);
	}

	public void updatePDPState(AccessControlRequest request) throws SecurityError {
		
		//get resource and action
		URI resource = request.getResource();
		Dependency assign = dependencies.get(resource.toString());
		
		if ( assign == null ) {
			logger.error("For the resource (" + resource + ") defined in log " + request.getEvaluationId() + " no assignment is defined");
			throw new SecurityError(ErrorType.CONFIGURATION_ERROR, ReasonType.PDE_ENGINE_ERROR, "Cannot update the PDPState: missing assignment definition");
		} else {
			assign.writeStateChange(request, pdpState);
		}
	}
	
	private void readConfig(File confFile) throws IOException {
		readConfig(new FileInputStream(confFile));
	}
	
	private void readConfig(InputStream stream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
	
		
		String key = null;
		AttributeDBIdentifier attrId = null;
		
		String line = null;
		while ( (line = reader.readLine()) != null ) {
			line = line.trim();
			if ( ! (line.startsWith("#") || line.length() == 0 ) ) {
				if ( line.startsWith(ATTRIBUTE) ) {
					key = line.substring(ATTRIBUTE.length(), line.length());
					try {
						attrId = readAttribute(reader);
						if ( attributes.containsKey(key)) {
							logger.warn("Overwriting attribute definition with key " + key);
						}
						attributes.put(key, attrId);
					} catch (URISyntaxException e) {
						logger.warn("Could not read attribute with key " + key + ": URISyntaxException: " + e.getMessage());
					} catch (SyntaxError e) {
						logger.warn("Could not read attribute with key " + key + ": Syntax Error: " + e.getMessage());
					}
				} else if ( line.startsWith(DEPENDENCY)) {
					key = "null";
					try {
						key = line.substring(DEPENDENCY.length(), line.length()).trim();
						Dependency assign = Dependency.readAssignment(key, reader, attributes, dbUtil);
						if (dependencies.containsKey(key)) {
							logger.warn("Overwriting dependency definition with key " + key);
						}
						this.dependencies.put(key, assign);
					} catch (SyntaxError e) {
						logger.warn("Could not read dependency with key " + key + ": SyntaxError: " + e.getMessage());
					}
				} else {
					logger.warn("Ignoring misplace line " + line);
				}
			}
		}
	}
	
	
	private AttributeDBIdentifier readAttribute(BufferedReader reader) throws IOException, URISyntaxException, SyntaxError {
		URI category, attributeId, dataType, issuer;
		category = readValueLine(reader);
		dataType = readValueLine(reader);
		attributeId = readValueLine(reader);
		issuer = readValueLine(reader);
		
		AttributeIdentifier attr = new AttributeIdentifier(category, dataType, attributeId, issuer);
		return dbUtil.getAttributeDBIdentifier(attr);
	}
	
	private URI readValueLine(BufferedReader reader) throws IOException, URISyntaxException, SyntaxError {
		String line = reader.readLine().trim();
		if ( line.startsWith("#") ) {
			return readValueLine(reader);
		}
		if ( "null".equals(line)) {
			return null;
		}
		if ( line.length() == 0 || line.startsWith(ATTRIBUTE) || line.startsWith(DEPENDENCY)) {
			throw new SyntaxError("Invalid line after attribute declaration: " + line);
		}
		return new URI(line);
	}
	
//	private void addDependency(String dependency) throws SyntaxError {
//	int m = dependency.indexOf("->");
//	if ( m == -1 ) {
//		throw new SyntaxError("Missing assignment -> ");
//	}
//	String key = dependency.substring(0, m).trim();
//	AttributeDBIdentifier dbKey = attributes.get(key);
//
//
//	if ( dbKey == null ) {
//		throw new SyntaxError("Attribute with key " + key + " is not defined so far");
//	}
//	List<AttributeDBIdentifier> dependencies = new Vector<AttributeDBIdentifier>();
//	StringTokenizer tokenizer = new StringTokenizer(dependency.substring(m + 2), ",");
//	while (tokenizer.hasMoreTokens()) {
//		String token = tokenizer.nextToken().trim();
//		if ( ! (token.length() == 0) ) {
//			// some attributes may not depend on any other attribute
//			if ( dependencies.size() == 0 && "null".equals(token) ) {
//				this.dependenciesString.put(key, dependencies);
//				this.dependencies.put(dbKey, dependencies);
//				return;
//			}
//			AttributeDBIdentifier dependent = attributes.get(token);
//			if ( dependent == null ) {
//				throw new SyntaxError("Attribute with key " + dependent + " is not defined so far");
//			} else {
//				dependencies.add(dependent);
//			}
//		}
//	}
//	if ( dependencies.size() == 0 ) {
//		throw new SyntaxError("No dependency is defined");
//	}
//	this.dependenciesString.put(key, dependencies);
//	this.dependencies.put(dbKey, dependencies);
//}
//
//
}
