/**
 * 
 * @creatTime 上午11:09:01
 * @author XuYi
 */
package com.eddy.entity;

import java.lang.reflect.Field;

/**
 * @author XuYi
 * 
 */
public class SeleniumNode {

	private Field field;
	private Object instance;
	private String path;

	public SeleniumNode(Field field, Object instance, String path) {
		super();
		this.field = field;
		this.instance = instance;
		this.path = path;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
