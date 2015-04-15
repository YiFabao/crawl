package com.yifabao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import junit.framework.TestCase;

public class InputStreamTest extends TestCase{
	
	public void testPageUrl() throws IOException{
		String path = "http://www.baidu.com";
		URL pageURL = new URL(path);
		InputStream stream = pageURL.openStream();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		StringBuffer sb = new StringBuffer();
		
		String line = null;
		while((line=reader.readLine())!=null){
			sb.append(line+"\n");
		}
		stream.close();
		System.out.println(sb.toString());
		assertEquals("aa", "aac");
	}
}
