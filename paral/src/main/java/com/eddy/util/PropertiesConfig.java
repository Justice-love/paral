package com.eddy.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesConfig {
	public PropertiesConfig() {
	}

	private static Properties props = new Properties();
	static {
		try {
			if (StringUtils.isEmpty(System.getProperty("paral.conf"))) {
				props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties"));
			} else {
				props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(System.getProperty("paral.conf")));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		return props.getProperty(key);
	}

	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}
	
	public static void main(String[] args) {
		System.out.println(getValue("username"));
	}
}
