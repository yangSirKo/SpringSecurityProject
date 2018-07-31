package com.imooc.security.core.properties;

/**
 * 
 * @author 杨Sir 默认的ImageValidateCode配置
 */
public class ImageCodeProperties extends SmsCodeProperties{

	//覆盖父类的默认长度 6 
	public ImageCodeProperties() {
		setLength(4);
	}
	
	private int width = 67;
	private int height = 23;
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
