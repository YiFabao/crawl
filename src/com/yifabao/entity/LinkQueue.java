package com.yifabao.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 除了URL队列之外, 在爬虫过程中，还需要一个数据结构来记录已经访问过的URL.<br>
 * 每当要访问一个URL的时候, 首先在这个数据结构中进行查找, 如果当前的URL已经存<br> 
 * 在, 则丢弃它, 这个数据结构要有两个特点:<ul>
 * 	<li>结构中保存的URL不能重复</li>
 * 	<li>能够快速查找(实际系统中URL数量非常多, 因此要考虑查找性能)</li></ul>
 * 针对以上两点, 我们选择HashSet作为存储结构.
 */


public class LinkQueue {
	
	/**
	 * 已访问的URL队列
	 */
	private static Set<Object> visitedUrl = new HashSet<Object>();
	
	/**
	 * 待访问的URL队列
	 */
	private static Queue unVisitedUrl = new Queue();
	
	/**
	 * 获得URL队列
	 * @return
	 */
	public static Queue getUnVisitedUrl(){
		return unVisitedUrl;
	}
	
	/**
	 * 添加到访问过的URL队列中
	 * @param url
	 */
	public static void addVisitedUrl(String url){
		visitedUrl.add(url);
	}
	
	/**
	 * 移除访问过的URL
	 * @return
	 */
	public static void removeVisitedUrl(String url)
	{
		visitedUrl.remove(url);
	}
	
	/**
	 * 未访问的 URL 出列
	 * @return 
	 */
	public static Object unVisitedUrlDeQueue(){
		return unVisitedUrl.deQueue();
	}
	
	/**
	 * 添加 URL 到 未访问的 URL 队列中.<br>
	 * 如果该URL已经访问过了, 或已经在未访问队列中存在, <br>
	 * 那么就不会保存
	 * @param url
	 */
	public static void addUnVisitedUrl(String url){
		if(url !=null && !url.trim().equals("")
				&& !visitedUrl.contains(url)
				&& !unVisitedUrl.contains(url)){
			unVisitedUrl.enQueue(url);
		}
	}
	
	/**
	 * 获得已经访问的 URL 数目
	 * @return  URL 数目
	 */
	public static int getVisitedUrlNum(){
		return visitedUrl.size();
	}
	
	/**
	 * 判断未访问的URL队列中是否为空
	 * @return true 为空,false 非空
	 */
	public static boolean unVisitedUrlEmpty(){
		return unVisitedUrl.isQueueEmpty();
	}
	
}
