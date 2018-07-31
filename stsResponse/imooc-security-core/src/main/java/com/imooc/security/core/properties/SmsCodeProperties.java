package com.imooc.security.core.properties;

/**
 * 
 * @author 杨Sir 默认的ImageValidateCode配置
 */
public class SmsCodeProperties { 
	
	//长度
	private int length = 6;
	//过期时间
	private int expireIn = 60;
	
	//设置过滤器拦截的接口(多个路径)
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}

}
