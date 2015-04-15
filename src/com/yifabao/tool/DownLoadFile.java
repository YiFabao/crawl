package com.yifabao.tool;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
