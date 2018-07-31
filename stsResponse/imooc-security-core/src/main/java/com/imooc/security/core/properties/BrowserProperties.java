package com.imooc.security.core.properties;

/**
 * 
 * @author 杨Sir
 *
 */
public class BrowserProperties {

	private int rememberMeSeconds = 3600;  // 单位是秒
	//默认的请求登录页
	private String loginPage = "/imooc-sigIn.html";
	//默认的处理登录请求后返回数据方式
	private LoginType loginType = LoginType.JSON;
	
	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}
	
	
}
