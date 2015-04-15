package com.yifabao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class URLTest {
	public static void main(String[] args) throws IOException {
		String path  = "http://www.baidu.com";
		URL pageURL = new URL(path);
		InputStream stream = pageURL.openStream();
		BufferedReader sb = new BufferedReader(new InputStreamReader(stream));
		String line =null;
		while((line=sb.readLine())!=null)
		{
			System.out.println(line);
		}
	 
	}
}
