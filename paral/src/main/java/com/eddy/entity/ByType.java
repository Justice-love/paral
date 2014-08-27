/**
 * 
 * @creatTime 下午1:05:39
 * @author XuYi
 */
package com.eddy.entity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author XuYi
 *
 */
public enum ByType {

	/**
	 * id选择器
	 * @author XuYi
	 *
	 */
	ID {
		public WebElement findElement(WebDriver driver, String path) {
			return driver.findElement(By.id(path));
		}
	},
	
	/**
	 * css选择器
	 * @author XuYi
	 *
	 */
	CSS {
		public WebElement findElement(WebDriver driver, String path) {
			return driver.findElement(By.cssSelector(path));
		}
	},
	
	/**
	 * Xpath选择器
	 * @author XuYi
	 *
	 */
	XPATH {
		public WebElement findElement(WebDriver driver, String path) {
			return driver.findElement(By.xpath(path));
		}
	};
	
	public WebElement findElement(WebDriver driver, String path) {
		return null;
	}
	
	@SuppressWarnings("unused")
	private static String prefix = "/";
}
