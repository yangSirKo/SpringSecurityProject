package com.imooc.code;

import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validator.code.ImageCode;
import com.imooc.security.core.validator.code.ValidateCodeGenerator;

//注意这个bean的名字，必须为imageCodeGenerator，否则默认的生成验证码逻辑有效

//@Component("imageCodeGenerator")   覆盖core中默认的图片验证码生成逻辑
public class DemoImageCodeGenerator implements ValidateCodeGenerator{

	@Override
	public ImageCode generate(ServletWebRequest request) {
		
		System.out.println("更高级的图片验证码");
		return null;   //这里汇报空指针异常，因为这没有给具体的验证码实现
	}

}
