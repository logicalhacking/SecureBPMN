package com.sun.xacml.attr;

import java.net.URI;

public class TypeIdentifierConstants {
	
	public static final String ANYURI = 
		"http://www.w3.org/2001/XMLSchema#anyURI";
	public static final String BASE64BINARY = 
		"http://www.w3.org/2001/XMLSchema#base64Binary";
	public static final String BOOLEAN = 
		"http://www.w3.org/2001/XMLSchema#boolean";
	public static final String DATE = 
		"http://www.w3.org/2001/XMLSchema#date";
	public static final String DATETIME = 
		"http://www.w3.org/2001/XMLSchema#dateTime";
	public static final String DAYTIMEDURATION = 
		"http://www.w3.org/TR/2002/WD-xquery-operators-20020816#dayTimeDuration";
	public static final String DECISION = 
		"urn:oasis:names:tc:xacml:3.0:delegation:decision";
	public static final String DNSNAME = 
		"urn:oasis:names:tc:xacml:2.0:data-type:dnsName";
	public static final String DOUBLE = 
		"http://www.w3.org/2001/XMLSchema#double";
	public static final String HEXBINARY = 
		"http://www.w3.org/2001/XMLSchema#hexBinary";
	public static final String INTEGER =  
		"http://www.w3.org/2001/XMLSchema#integer";
	public static final String IPADDRESS = 
		"urn:oasis:names:tc:xacml:2.0:data-type:ipAddress";
	public static final String RFC822NAME = 
		"urn:oasis:names:tc:xacml:1.0:data-type:rfc822Name";
	public static final String STRING = 
		"http://www.w3.org/2001/XMLSchema#string";
	public static final String TIME = 
		"http://www.w3.org/2001/XMLSchema#time";
	public static final String X500NAME = 
		"urn:oasis:names:tc:xacml:1.0:data-type:x500Name";
	public static final String YEARMONTHDURATION = 
		"http://www.w3.org/TR/2002/WD-xquery-operators-20020816#yearMonthDuration";
	
	public static final URI ANYURI_URI = 
		URI.create(ANYURI);
	public static final URI BASE64BINARY_URI = 
		URI.create(BASE64BINARY);
	public static final URI BOOLEAN_URI =
		URI.create(BOOLEAN);
	public static final URI DATE_URI = 
		URI.create(DATE);
	public static final URI DATETIME_URI = 
		URI.create(DATETIME);
	public static final URI DAYTIMEDURATION_URI = 
		URI.create(DAYTIMEDURATION);
	public static final URI DECISION_URI = 
		URI.create(DECISION);
	public static final URI DNSNAME_URI = 
		URI.create(DNSNAME);
	public static final URI DOUBLE_URI = 
		URI.create(DOUBLE);
	public static final URI HEXBINARY_URI = 
		URI.create(HEXBINARY);
	public static final URI INTEGER_URI = 
		URI.create(INTEGER);
	public static final URI IPADDRESS_URI = 
		URI.create(IPADDRESS);
	public static final URI RFC822NAME_URI = 
		URI.create(RFC822NAME);
	public static final URI STRING_URI = 
		URI.create(STRING);
	public static final URI TIME_URI = 
		URI.create(TIME);
	public static final URI X500NAME_URI = 
		URI.create(X500NAME);
	public static final URI YEARMONTHDURATION_URI = 
		URI.create(YEARMONTHDURATION);
	

}
