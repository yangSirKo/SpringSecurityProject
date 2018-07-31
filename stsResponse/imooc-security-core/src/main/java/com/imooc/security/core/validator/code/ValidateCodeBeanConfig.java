package com.imooc.security.core.validator.code;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imooc.security.core.validator.code.sms.DefaultSmsCodeSender;
import com.imooc.security.core.validator.code.sms.SmsCodeSender;

/**
 * 
 * @author 杨Sir 配置一些bean
 */
@Configuration
public class ValidateCodeBeanConfig {

//	@Autowired
//	private SecurityProperties securityProperties;、

	//默认的图片验证码生成逻辑
	@Bean
	@ConditionalOnMissingBean(name = "imageCodeGenerator") // 当其他位置不存在imageCodeGenerator这个bean时，才用下面的配置
	public ValidateCodeGenerator imageCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
//		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}

	//默认的短信验证码发送器
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class) // 当其他位置不存在SmsCodeSender的实现类(bean)时，才用下面的配置
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}
	
}
