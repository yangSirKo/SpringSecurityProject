package com.imooc.security.browser.support;

/**
 * 封装验证的错误信息
 * 
 * @author 杨Sir
 *
 */
public class SimpleResponse {

	private Object content;

	public SimpleResponse(Object content) {
		this.content = content;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}
