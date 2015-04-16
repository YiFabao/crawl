package com.yifabao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GetTest {
	public static void main(String[] args) throws UnknownHostException, IOException {
		String host = "www.lietu.com";
		String file = "/index.jsp";//网页路径
		int port = 80;//端口号
		
		Socket s = new Socket(host,port);
		
		OutputStream out = s.getOutputStream();
		
		PrintWriter outw = new PrintWriter(out,false);
		outw.print("GET "+file+" HTTP/1.1\r\n");
		outw.print("Accept:text/plain,text/html,text/*\r\n");
		outw.print("\r\n");
		outw.flush();//发送get命令
		
		InputStream in = s.getInputStream();
		InputStreamReader inr = new InputStreamReader(in);
		
		BufferedReader br = new BufferedReader(inr);
		
		String line;
		
		while((line=br.readLine())!=null)
		{
			System.out.println(line);
		}
		
		
	}
}
