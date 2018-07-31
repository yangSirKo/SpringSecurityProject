package com.imooc.security.core.validator.code;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validator.code.sms.SmsCodeSender;
/**
 * 
 * @author 杨Sir
 *
 */
@RestController
public class ValidateCodeController {

	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	// 验证码放入到这个session
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	//创建图片验证码
	@Autowired
	private ValidateCodeGenerator imageCodeGenerator;
	
	//创建短信验证码
	@Autowired
	private ValidateCodeGenerator smsCodeGenerator;
	
	//注入验证码发送器
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@GetMapping("/code/image")
	public void createImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 创建验证码
		ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
		// 存入session
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
		// 写到接口响应中
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}
	
	
	@GetMapping("/code/sms")
	public void createSms(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
		// 创建验证码
		ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
		// 存入session
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
		// 短信验证码发送，调用短信服务商
		//ServletRequestUtils.getRequiredStringParameter:表示参数必须传
		String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
		smsCodeSender.send(mobile, smsCode.getCode());
		
		
	}
	

}
