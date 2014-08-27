/**
 * 
 * @creatTime 上午9:30:03
 * @author XuYi
 */
package com.eddy.entity;

import java.util.concurrent.ThreadFactory;

/**
 * @author XuYi
 * 
 */
public class MineThreadFactory implements ThreadFactory {

	private String threadName;

	public MineThreadFactory(String threadName) {
		super();
		this.threadName = threadName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
	 */
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, threadName);
		t.setDaemon(true);
		return t;
	}

}
