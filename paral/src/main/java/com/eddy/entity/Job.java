/**
 * 
 * @creatTime 下午1:34:43
 * @author XuYi
 */
package com.eddy.entity;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eddy.annotations.ElementAction;
import com.eddy.util.PropertiesConfig;

/**
 * @author XuYi
 * 
 */
public class Job {

	protected WebDriver driver;
	protected Actions action;
	private static long SLEEPTIME = 600;
	private static final Logger logger = LoggerFactory.getLogger(Job.class);


	/**
	 * 登录
	 * 
	 * @param driver
	 * @throws Exception
	 * @creatTime 上午9:20:59
	 * @author XuYi
	 */
	protected void login(WebDriver driver, String url, long openWait, long loginWait) throws Exception {
		driver.get(url);
		Thread.sleep(openWait);
		driver.findElement(By.xpath("//div[3]/div[2]")).click();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(PropertiesConfig.getValue("username"));
		driver.findElement(By.cssSelector("input[type=\"password\"]")).clear();
		driver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys(PropertiesConfig.getValue("password"));

		Robot r = new Robot();

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		logger.debug("test unit <" + this.getClass().getName() + ".> login success");
		Thread.sleep(loginWait);
	}

	/**
	 * 单击动作
	 * 
	 * @creatTime 下午1:38:38
	 * @author XuYi
	 * @throws Exception
	 */
	protected void clickAction(SeleniumNode node) throws Exception {
		ElementAction annotation = node.getField().getAnnotation(ElementAction.class);
		WebElement element = moveToElement(annotation.type(), node.getPath());
		action.click(element).perform();
		logger.debug("test unit <" + this.getClass().getName() + ".> click action success, path " + node.getPath());
		Thread.sleep(annotation.sleepTime());
	}

	/**
	 * 双击动作
	 * 
	 * @creatTime 下午1:41:37
	 * @author XuYi
	 * @throws Exception
	 */
	protected void doubleClickAction(SeleniumNode node) throws Exception {
		ElementAction annotation = node.getField().getAnnotation(ElementAction.class);
		WebElement element = moveToElement(annotation.type(), node.getPath());
		action.click(element).click(element).perform();
//		action.doubleClick(element).perform();
		logger.debug("test unit <" + this.getClass().getName() + ".> double click action success, path " + node.getPath());
		Thread.sleep(annotation.sleepTime());
	}

	/**
	 * 右击动作
	 * 
	 * @creatTime 下午1:42:09
	 * @author XuYi
	 * @throws Exception
	 */
	protected void contextClickAction(SeleniumNode node) throws Exception {
		ElementAction annotation = node.getField().getAnnotation(ElementAction.class);
		WebElement element = moveToElement(annotation.type(), node.getPath());
		action.contextClick(element).perform();
		logger.debug("test unit <" + this.getClass().getName() + ".> context click action success, path " + node.getPath());
		Thread.sleep(annotation.sleepTime());
	}

	/**
	 * 文本输入动作
	 * 
	 * @creatTime 下午1:43:08
	 * @author XuYi
	 * @throws Exception
	 */
	public void setValueAction(SeleniumNode node) throws Exception {
		ElementAction annotation = node.getField().getAnnotation(ElementAction.class);
		WebElement element = annotation.type().findElement(driver, node.getPath());
		element.clear();
		element.sendKeys(annotation.value());
		logger.debug("test unit <" + this.getClass().getName() + ".> set value action success, path " + node.getPath());
		Thread.sleep(annotation.sleepTime());
	}

	/**
	 * 最终判断动作
	 * 
	 * @param node
	 * @creatTime 下午3:54:48
	 * @author XuYi
	 */
	public void assertAction(SeleniumNode node) {
		ElementAction annotation = node.getField().getAnnotation(ElementAction.class);
		WebElement element = annotation.type().findElement(driver, node.getPath());
		switch (annotation.assertType()) {
		case EQUALS:
			Assert.assertEquals(annotation.value(), element.getText());
			break;
		case TRUE:
			Assert.assertTrue(Boolean.parseBoolean(element.getText()));
			break;
		case NUMBER:
			Assert.assertEquals(Integer.parseInt(annotation.value()), element.findElements(By.xpath("child::*")).size());
			break;
		case VALUE:
			Assert.assertEquals(annotation.value(), element.getAttribute("value"));
			break;
		default:
			throw new IllegalArgumentException("assertType 枚举类用法错误");
		}
		logger.debug("test unit <" + this.getClass().getName() + ".> assert action success, path " + node.getPath());
	}

	/**
	 * 回车动作
	 * 
	 * @creatTime 下午1:43:31
	 * @author XuYi
	 * @throws Exception
	 */
	public void enterAction(SeleniumNode node) throws Exception {
		ElementAction annotation = node.getField().getAnnotation(ElementAction.class);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		logger.debug("test unit <" + this.getClass().getName() + ".> click enter success, path " + node.getPath());
		Thread.sleep(annotation.sleepTime());
	}

	private WebElement moveToElement(ByType type, String path) throws Exception {
		WebElement element = type.findElement(driver, path);
		action.moveToElement(element).perform();
		Thread.sleep(SLEEPTIME);
		return element;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public Actions getAction() {
		return action;
	}

	public void setAction(Actions action) {
		this.action = action;
	}

}
