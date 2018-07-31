/**
 * 
 */
package com.imooc.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.browser.support.SimpleResponse;
import com.imooc.security.core.properties.BrowserProperties;
import com.imooc.security.core.properties.LoginType;
import com.imooc.security.core.properties.SecurityProperties;

/**
 * @author 杨Sir
 * 
 * 登录失败处理
 * 实现 AuthenticationFailureHandler接口，可以返回json信息
 * 要同时支持返回json和跳转，继承ExceptionMappingAuthenticationFailureHandler类，spring默认的失败处理器
 */
@Component("imoocAuthenticationFailureHandler")
public class ImoocAuthenticationFailureHandler extends ExceptionMappingAuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());	
	
	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private ObjectMapper objectMapper;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		logger.info("登录失败");
		
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			//返回json
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); //返回500
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
		}else {
			//默认的请求转发，转发到HTML页
			super.onAuthenticationFailure(request, response, exception);
		}
		
	}

}
