/**
 * 
 * @creatTime 下午5:32:04
 * @author XuYi
 */
package com.eddy.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.eddy.annotations.ElementAction;
import com.eddy.entity.BasicJob;
import com.eddy.entity.SeleniumNode;

/**
 * @author XuYi
 * 
 */
public class SeleniumUtil {

	private static final String directory = "/seleniumScreenshot/";
	private static final String format = ".jpg";
	public static final String BASEURL = "http://itossdemo.siteview.com";
	private static WebDriver driver;
	
	
	public static void captureScreen(WebDriver dr, String captureName) {
		File screenShotFile = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
		try {
			 FileUtils.copyFile (screenShotFile, new File(new StringBuffer(directory).append(captureName).append(format).toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		return driver;
	}
	
	/**
	 * 解析任务
	 * @param job
	 * @return
	 * @creatTime 上午11:54:11
	 * @author XuYi
	 * @throws Exception 
	 */
	public static LinkedBlockingQueue<SeleniumNode> parseJob(BasicJob job) throws Exception {
		LinkedBlockingQueue<SeleniumNode> queue = new LinkedBlockingQueue<SeleniumNode>();
		Field[] fields = job.getClass().getDeclaredFields();
		for (Field f : fields) {
			if (!f.isAnnotationPresent(ElementAction.class)) {
				throw new IllegalArgumentException("未定义ElementAction注解");
			}
			
			if (!f.isAccessible()) {
				f.setAccessible(true);
			}
			SeleniumNode node = new SeleniumNode(f, job, null == f.get(job) ? "" : f.get(job).toString());
			if (!queue.offer(node)) {
				throw new IllegalArgumentException("插入队列不成功");
			}
		}
		return queue;
	}
	
//	public WebDriver getDriver(int timeout) {
//		if (StringUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))) {
//			System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
//		}
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
//		return driver;
//	}
	
}
