package com.imooc.security.core.validator.code.sms;

public interface SmsCodeSender {

	/**
	 * 短信验证码发送接口
	 * @param mobile  ： 手机号
	 * @param code  ： 短信验证码内容,一串数字
	 */
	void send(String mobile,String code);
	
}
