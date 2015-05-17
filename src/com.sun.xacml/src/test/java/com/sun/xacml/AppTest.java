package com.sun.xacml;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
	public static void main(String[] args) throws IOException, ParsingException, UnknownIdentifierException {
		Properties log4jProps = new Properties();
		log4jProps.load(new BufferedInputStream(new FileInputStream(new File("../pdp/src/test/log4j.properties"))));
		PropertyConfigurator.configure(log4jProps);
		//		//AppTest test = new AppTest("pdpServer test");
		//test.testApp();
		foo();
	}

	private static void foo() throws FileNotFoundException, ParsingException, UnknownIdentifierException {

		ConfigurationStore config = new ConfigurationStore(new FileInputStream(new File("src/main/resources/policy-config.xml")), "src/main/resources/");
		
		//PDP pdp = 
		new PDP(config.getDefaultPDPConfig());

	}		

}
