package com.yifabao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientTest {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String path = "http://xunta.so";
		HttpGet httpget = new HttpGet(path);
		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					// do something useful
					BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
					String line = null;
					while((line = reader.readLine())!=null){
						System.out.println(line);
					}
				} finally {
					instream.close();
				}
			}
		} finally {
			response.close();
		}

	}
}
