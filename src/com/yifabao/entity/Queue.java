package com.yifabao.entity;

import java.util.LinkedList;

/**
 * 队列
 * @author yifabao
 */
public class Queue {
	
	
	/**
	 * 使用链表实现队列
	 */
	private LinkedList<Object> queue = new LinkedList<Object>();
	
	/**
	 * 入队列
	 * @param t 进入队列的对象
	 */
	public void enQueue(Object t){
		queue.add(t);
	}
	
	/**
	 * 出队列
	 * @return 出队列中的第一个
	 */
	public Object deQueue(){
		return queue.removeFirst();
	}
	
	/**
	 * 判断队列是否为空
	 * @return true为空,false非空
	 */
	public boolean isQueueEmpty(){
		return queue.isEmpty();
	}
	
	/**
	 * 判断队列是否包含 对象t
	 * @param t 队列中的对象
	 * @return true包含，false不包含
	 */
	public boolean contains(Object t){
		return queue.contains(t);
	}

}
