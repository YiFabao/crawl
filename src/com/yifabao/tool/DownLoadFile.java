package com.yifabao.tool;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.ws.Response;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;

/**
 * 下载文件
 * @author yifabao
 *
 */
public class DownLoadFile {

	/**
	 * 根据 URL 和 网页类型生成需要保存的网页的文件名, 去除 URL中的非文件名字符
	 * @param url 网页url
	 * @param contentType 网页类型
	 * @return 生成的文件名字符串
	 */
	public String getFileNameByUrl(String url,String contentType){
		//移除http
		url = url.substring(url.indexOf("://")+3);
		//text/html类型
		if(contentType.indexOf("html")!= -1){
			url = url.replaceAll("[\\?/:*|<>\"]", "_")+".html";
			return url;
		}else{//其他类型,如application/pdf类型
			return url.replaceAll("[\\?/:*|<>\"]","_")+"."+contentType.substring(contentType.lastIndexOf("/")+1);
		}
	}
	
	/**
	 * 保存网页字节数组到本地文件,filePath 为要保存的文件相对地址
	 * @param data 字节数组
	 * @param filePath 保存文件的地址
	 */
	private void saveToLocal(byte[] data,String filePath){
		
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));
			int i;
			for(i=0;i<data.length;i++){
				out.write(data[i]);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 保存网页字节流到本地文件,filePath 为要保存的文件相对地址
	 * @param inputStream　输入流
	 * @param filePath 本地文件路径
	 */
	private void saveToLocal(InputStream inputStream,String filePath){
		DataOutputStream out=null;
		try {
			out = new DataOutputStream(new FileOutputStream(new File(filePath)));
			byte buffer[] = new byte[1024];
			while((inputStream.read(buffer))!=-1){
				out.write(buffer);
			}
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 下载url指向的网页
	 * @param url 指定的url字符串
	 * @param rootPath　根路径名 windows下　d://xxx/xxx 注意最后面不要/  linux 下 /xxx/
	 * @return 文件路径
	 */
	public String downloadFile(String url,String rootPath)
	{
		String filePath = null;

		//1.连接池
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(20);
		
	
		// Increase max connections for localhost:80 to 50
		HttpHost localhost = new HttpHost("locahost", 80);
		cm.setMaxPerRoute(new HttpRoute(localhost), 50);
		
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true));
		CloseableHttpClient httpClient = httpClientBuilder.setConnectionManager(cm).build();

		//设置请求超时2秒,传输超时2秒
		HttpGet httpGet = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response=null;
		try {
			response = httpClient.execute(httpGet);//执行请求
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode != HttpStatus.SC_OK){
				System.err.println("Method failed:"+response.getStatusLine());
				filePath = null;
			}else{
				HttpEntity entity = response.getEntity();
				if(entity != null){
					InputStream inputStream = entity.getContent();
					//根据网页url 生成保存时的文件名
					filePath = rootPath+File.separator+getFileNameByUrl(url, entity.getContentType().getValue());
					saveToLocal(inputStream, filePath);
				}else{
					filePath = null;
				}
			
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				//释放连接
				response.close();
				httpClient.close();
				httpGet.releaseConnection();
				cm.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return filePath;
	}
	
}
