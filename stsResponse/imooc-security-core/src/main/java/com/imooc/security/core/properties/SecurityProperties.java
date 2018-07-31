package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author 杨Sir
 * 注：@ConfigurationProperties(prefix="imooc.security ")
 * 	     表示SecurityProperties类可以获取到配置文件中的imooc.security开头的配置
 */
@ConfigurationProperties(prefix="imooc.security")
public class SecurityProperties {
	
	//browser读取imooc.security.browser开头的配置
	private BrowserProperties browser = new BrowserProperties();
	
	private ValidateCodeProperties code = new ValidateCodeProperties();

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}
}
