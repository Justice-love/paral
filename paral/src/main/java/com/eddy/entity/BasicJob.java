/**
 * 
 * @creatTime 上午11:50:12
 * @author XuYi
 */
package com.eddy.entity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eddy.annotations.ElementAction;
import com.eddy.annotations.TestUnit;
import com.eddy.util.SeleniumUtil;

/**
 * @author XuYi
 * 
 */
public class BasicJob extends Job implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(BasicJob.class);
	private SeleniumChain chain;
	private Thread excuteThread;
	private TestUnit unit;
	private boolean quit = false;
	private CountDownLatch shutDownSignal;

//	public BasicJob(SeleniumChain chain) {
//		this();
//		this.chain = chain;
//	}

	public BasicJob() {
		if (!this.getClass().isAnnotationPresent(TestUnit.class)) {
			throw new IllegalArgumentException("未定义TestUnit注解");
		}
		TestUnit unit = this.getClass().getAnnotation(TestUnit.class);
		this.unit = unit;
	}

	private void init() {
		if (StringUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))) {
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		}
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(unit.timeOut(), TimeUnit.SECONDS);
		super.driver = driver;
		super.action = new Actions(driver);
	}
	
	/**
	 * 执行函数
	 * 
	 * @creatTime 下午2:26:51
	 * @author XuYi
	 * @throws Exception
	 */
	public void excute() throws Exception {
		try {
			init();
			logger.debug("begin excute");
			if (LoginNeeded.YES.equals(unit.isLogin())) {
				super.login(super.driver, unit.baseURL(), unit.openWait(), unit.loginWait());
			} else {
				super.driver.get(unit.baseURL());
			}

			BlockingQueue<SeleniumNode> queue = SeleniumUtil.parseJob(this);
			for (SeleniumNode node : queue) {
				excuteAction(node);
			}
			// 截图
			SeleniumUtil.captureScreen(driver, this.getClass().getName() + "_" + System.currentTimeMillis());
		} finally {
			if (null != super.driver) {
				super.driver.quit();
				logger.info("driver quit");
			}
			// 信号
			this.getChain().getSignal().put(new Object());
			logger.info("close driver and send signal");
			
			logger.info("nodify main thread , count : " + shutDownSignal.getCount());
			shutDownSignal.countDown();
		}
	}

	/**
	 * 
	 * i know you know
	 * 
	 * @param node
	 * @creatTime 下午2:38:49
	 * @author XuYi
	 * @throws Exception
	 */
	private void excuteAction(SeleniumNode node) throws Exception {
		ElementAction e = node.getField().getAnnotation(ElementAction.class);
		if (Exist.NOTSURE.equals(e.isExist())) {
			try {
				e.type().findElement(super.driver, node.getPath());
			} catch (NoSuchElementException e1) {
				logger.debug("this element is not exist, path : " + node.getPath());
				return;
			}
		}
		
		switch (e.action()) {
		case CLICK:
			super.clickAction(node);
			return;
		case DOUBLECLICK:
			super.doubleClickAction(node);
			return;
		case CONTEXTCLICK:
			super.contextClickAction(node);
			return;
		case SETVALUE:
			super.setValueAction(node);
			return;
		case ENTER:
			super.enterAction(node);
			return;
		case ASSERT:
			super.assertAction(node);
			return;
		default:
			throw new IllegalArgumentException("Action枚举类使用异常,请查看");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			excute();
		} catch (NoSuchElementException e0) {
			e0.printStackTrace();
			logger.error("test unit <" + this.getClass().getName() + ".> not found this element", e0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("have exception", e);
		} catch (AssertionError e1) {
			e1.printStackTrace();
			logger.error("test unit <" + this.getClass().getName() + ".> assert error, message " + e1.getMessage(), e1);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.eddy.entity.Job#setDriver(org.openqa.selenium.WebDriver)
	 */
	public void setDriver(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(unit.timeOut(), TimeUnit.SECONDS);
		super.driver = driver;
	}

	public TestUnit getUnit() {
		return unit;
	}

	public void setUnit(TestUnit unit) {
		this.unit = unit;
	}

	public SeleniumChain getChain() {
		return chain;
	}

	public void setChain(SeleniumChain chain) {
		this.chain = chain;
	}

	public Thread getExcuteThread() {
		return excuteThread;
	}

	public void setExcuteThread(Thread excuteThread) {
		this.excuteThread = excuteThread;
	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}

	public void setShutDownSignal(CountDownLatch shutDownSignal) {
		this.shutDownSignal = shutDownSignal;
	}
	

}
