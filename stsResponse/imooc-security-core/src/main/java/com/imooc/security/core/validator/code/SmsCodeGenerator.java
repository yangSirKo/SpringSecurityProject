package com.imooc.security.core.validator.code;


import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.SecurityProperties;

/**
 *
 * @author 杨Sir
 * 
 *         默认的验证码生成逻辑
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}


	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	
	

}
