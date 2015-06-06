package com.sun.xacml.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.xacml.Constants;

/**
 * 
 * Class which is able to keep track of the line numbers 
 * 
 *
 */
public class DOMLinesParser extends DOMParser {

	private XMLLocator locator;
	private boolean curEleNodeError = false;
	private static Logger logger = Logger.getLogger(DOMLinesParser.class);
	
	private String currentFile = null;
	
	public DOMLinesParser() {
		
	}
	
	public void parse (File file) throws SAXException, IOException {
		parse(new InputSource(new FileInputStream(file)), file.getPath());
	}

	public void parse (InputStream input, String fileName) throws SAXException, IOException {
		parse(new InputSource(input), fileName);
	}
	
	public void parse (InputStream input) throws SAXException, IOException {
		parse(new InputSource(input), null);
	}
	
	public void parse (InputSource input) throws SAXException, IOException {
		parse(input, null);
	}
	
	public static boolean checkFeatureAvailable() {
		 try {
				DOMParser tmpParser = new DOMParser();
				tmpParser.setFeature( "http://apache.org/xml/features/dom/defer-node-expansion", false );
				return true;
			} catch (Exception e) {
				return false;
			} 
	}

	
	public void parse (InputSource input, String fileName) throws SAXException, IOException {
		logger.debug("Start parsing policy file " + fileName + " with line numbers");
		this.currentFile = fileName;
		 try {
			this.setFeature( "http://apache.org/xml/features/dom/defer-node-expansion", false );
			curEleNodeError = false;
		} catch (Exception e) {
			logger.error("Could not set feature required for keeping track of line numbers!");
			curEleNodeError = true;
		} 
		super.parse(input);
	}
	
	


	@Override
	public void startElement(QName elementQName, XMLAttributes attrList, Augmentations augs) 
	throws XNIException {
		super.startElement(elementQName, attrList, augs);
		
		Node node = null;
		try {
			node = (Node) this.getProperty( "http://apache.org/xml/properties/dom/current-element-node" );
		}
		catch( SAXException ex )
		{
			if ( ! curEleNodeError ) {
				curEleNodeError = true;
				logger.error("Could not retreive node for setting current start line: " + ex.getMessage(), ex);
			}
		}
		
		if( node != null ) {
			node.setUserData( Constants.LINE_NUMBER, new Integer( locator.getLineNumber() ), null ); // Save location String into node
			if ( this.currentFile != null) {
				node.setUserData( Constants.SOURCE_FILE, this.currentFile, null);
			}
		}
	} //startElement 

	/* We override startDocument callback from DocumentHandler */

	public void startDocument(XMLLocator locator, String encoding, 
			NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
		super.startDocument(locator, encoding, namespaceContext, augs);

		this.locator = locator;
		Node node = null ;
		try {
			node = (Node) this.getProperty( "http://apache.org/xml/properties/dom/current-element-node" );
		}
		catch( org.xml.sax.SAXException ex )
		{
			if ( ! curEleNodeError ) {
				curEleNodeError = true;
				logger.error("Could not retreive node for setting current start line: " + ex.getMessage(), ex);
			}
		}

		if( node != null ) {
			node.setUserData( Constants.LINE_NUMBER, new Integer(locator.getLineNumber() ), null ); // Save location String into node
			if ( this.currentFile != null) {
				node.setUserData( Constants.SOURCE_FILE, this.currentFile, null);
			}
		}
	} 
}
