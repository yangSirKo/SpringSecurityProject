package com.imooc.security.core.validator.code.sms;

/**
 *  短信验证码默认的发送逻辑
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	@Override
	public void send(String mobile, String code) {
		System.out.println("向手机号 "+ mobile +" 发送短信验证码："+ code);
	}

}
