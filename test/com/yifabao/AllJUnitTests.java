package com.yifabao;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllJUnitTests extends TestCase {

	public AllJUnitTests(String name) {
		super(name);
	}
	
	public static Test suite(){
		TestSuite suite = new TestSuite();
		suite.addTestSuite(HelloWorldTest.class);
		
		return suite;
	}
	
}