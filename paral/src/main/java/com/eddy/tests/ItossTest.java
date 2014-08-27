/**
 * 
 * @creatTime 上午9:13:52
 * @author XuYi
 */
package com.eddy.tests;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.eddy.util.SeleniumUtil;

/**
 * @author XuYi
 * 
 */
public class ItossTest {

	private static String groupXpath = "//html/body/div[4]/div[4]/div/div/div/div[2]/div/div/div/div[2]";
	private static String newDeviceXpath = "//html/body/div[5]/div/div[8]/div[2]";
	private static String newDeviceChildXpath = "//html/body/div[6]/div/div[2]";
	private static String ipXpath = "//html/body/div[4]/div[4]/div[1]/div/div/div[6]/div[5]/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div/div[1]/input";
	private static String pwdXpath = "//html/body/div[4]/div[4]/div[1]/div/div/div[6]/div[5]/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div/div[4]/input";
	private static String saveXpath = "//html/body/div[4]/div[4]/div[1]/div/div/div[6]/div[5]/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div/div[33]";
	private static String groupNodeXpath = "//html/body/div[4]/div[4]/div[1]/div/div/div[2]/div/div/div[1]/div[2]";

	/**
	 * 登录
	 * @param driver
	 * @throws Exception
	 * @creatTime 上午9:20:59
	 * @author XuYi
	 */
	protected void login(WebDriver driver) throws Exception {
		driver.get(SeleniumUtil.BASEURL + "/itoss/webloader");
		Thread.sleep(5 * 1000);
		driver.findElement(By.xpath("//div[3]/div[2]")).click();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("admin");
		driver.findElement(By.cssSelector("input[type=\"password\"]")).clear();
		driver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys("manage");

		Robot r = new Robot();

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(10 * 1000);
	}

	/**
	 * 组节点右击
	 * @param driver
	 * @param action
	 * @throws Exception
	 * @creatTime 上午9:21:05
	 * @author XuYi
	 */
	protected void groupClick(WebDriver driver, Actions action) throws Exception {
		WebElement group = driver.findElement(By.xpath(groupXpath));

		action.moveToElement(group).doubleClick(group).perform();
		Thread.sleep(300);
		action.moveToElement(group).contextClick(group).perform();
		Thread.sleep(700);
		// /html/body/div[6]/div/div/div[6]/div[2] --yuzhenbiao 的节点
		WebElement newDevice = driver.findElement(By.xpath(newDeviceXpath));
		action.moveToElement(newDevice).perform();
		Thread.sleep(500);
		action.click(newDevice).perform();
		Thread.sleep(500);
		WebElement newDeviceChild = driver.findElement(By.xpath(newDeviceChildXpath));
		action.moveToElement(newDeviceChild).perform();
		Thread.sleep(500);
		action.click(newDeviceChild).perform();

		Thread.sleep(3000);
	}

	/**
	 * 保存设备
	 * @param driver
	 * @throws Exception
	 * @creatTime 上午9:21:37
	 * @author XuYi
	 */
	protected void saveDevice(WebDriver driver) throws Exception {
		WebElement ip = driver.findElement(By.xpath(ipXpath));
		WebElement pwd = driver.findElement(By.xpath(pwdXpath));
		ip.clear();
		pwd.clear();
		ip.sendKeys("192.168.9.181");

		pwd.sendKeys("123");

		Thread.sleep(1000);

		WebElement save = driver.findElement(By.xpath(saveXpath));
		save.click();
		Thread.sleep(20 * 1000);
	}

	/**
	 * 展示结果
	 * @param driver
	 * @param action
	 * @throws Exception
	 * @creatTime 上午9:21:46
	 * @author XuYi
	 */
	protected void showResult(WebDriver driver, Actions action) throws Exception {
		WebElement node = driver.findElement(By.xpath(groupNodeXpath));
		action.moveToElement(node).perform();
		Thread.sleep(800);
		action.doubleClick(node).perform();
	}
}
