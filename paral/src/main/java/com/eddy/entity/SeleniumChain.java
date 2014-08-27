/**
 * 
 * @creatTime 上午9:13:10
 * @author XuYi
 */
package com.eddy.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author XuYi
 * 
 */
public class SeleniumChain {

	private static SeleniumChain instance;
	private static List<BasicJob> jobs;
	private BlockingQueue<Object> signal;
	private ExecutorService jobHandler;
	private static int poolSize = 10;
	private static final Logger logger = LoggerFactory.getLogger(SeleniumChain.class);
	private CountDownLatch shutDownSignal;

	public SeleniumChain() {
		jobs = new ArrayList<BasicJob>();
		signal = new LinkedBlockingQueue<Object>();
		jobHandler = Executors.newFixedThreadPool(poolSize, new MineThreadFactory("selenuim job"));
	}

	/**
	 * i think you know
	 * 
	 * @param job
	 * @return
	 * @creatTime 上午9:25:01
	 * @author XuYi
	 */
	public static SeleniumChain addJob(BasicJob job) {
		if (null == instance) {
			instance = new SeleniumChain();
		}
		if (null == job) {
			throw new IllegalArgumentException("参数为空");
		}
		jobs.add(job);
		return instance;
	}

	/**
	 * 执行
	 * 
	 * @creatTime 上午10:30:34
	 * @author XuYi
	 */
	public void execute() {
		shutDownSignal = new CountDownLatch(jobs.size());
		signal.offer(new Object());
		for (int i = 0; i < jobs.size(); i++) {
			BasicJob job = null;
			try {
				job = jobs.get(i);
				signal.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			job.setChain(this);
			job.setShutDownSignal(shutDownSignal);
			jobHandler.execute(job);
		}
		try {
			shutDownSignal.await();
			logger.info("finally count : " + shutDownSignal.getCount());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("非正常唤醒", e);
		}

	}

	public BlockingQueue<Object> getSignal() {
		return signal;
	}

}
