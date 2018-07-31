package com.imooc.security.core.validator.code;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.imooc.security.core.properties.SecurityProperties;
/**
 * 验证码拦截器
 * @author 杨Sir
 * OncePerRequestFilter 可以使过滤器只调用一次
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

	private AuthenticationFailureHandler authenticationFailureHandler;
	//从Session中获取请求信息
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	private Set<String> urls = new HashSet<>();
	//获取配置路径参数
	private SecurityProperties securityProperties;
	//匹配路径的工具类
	private AntPathMatcher pathMatcher = new AntPathMatcher();
	
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();

		String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(),",");
		if(configUrls != null) {
			for (String string : configUrls) {
				urls.add(string);
			}
		}
		urls.add("/authentication/form");
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
		
		boolean action = false;
		for (String url : urls) {
			//传入的 request.getRequestURI() 能否和 自己配的 url匹配
			if(pathMatcher.match(url, request.getRequestURI())) {
				action = true;
			}
		}
		
		//判断是否是登录请求
		if(action) {
			try {
				validate(new ServletWebRequest(request));
			}catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return ;
			}
		} 
		filterChain.doFilter(request, response);
	}

	/**
	 * 效验
	 * @param servletWebRequest
	 * @throws ServletRequestBindingException 
	 * @throws ValidateCodeException 
	 */
	private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException, ValidateCodeException {
		//Session中存在的值
		ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, 
				ValidateCodeController.SESSION_KEY);
		
		//用户输入的验证码值
		String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");
		
		if(StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码值不能为空！");
		}
		if(codeInSession == null) {
			throw new ValidateCodeException("验证码不存在");
		}
		if(codeInSession.isExpried()) {
			sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
			throw new ValidateCodeException("验证码已过期");
		}
		if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException("验证码不匹配！");
		}
		
		sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}


	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}
	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	

	
}
