package com.yifabao;

import junit.framework.TestCase;

public class HelloWorldTest extends TestCase {
	
	public HelloWorldTest(String name) {
		super(name);
	}

	public void testSayHello(){
		HelloWorld world = new HelloWorld();
		assertEquals(world.sayHello(), "Hello World!");
	}
}
