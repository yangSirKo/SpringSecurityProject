package com.imooc.security.browser;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.security.browser.support.SimpleResponse;
import com.imooc.security.core.properties.SecurityProperties;

/**
 * 
 * @author 杨Sir
 *
 */
@RestController
public class BrowserSecurityController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//HttpSessionRequestCache类会将当前的请求缓存到session中。
	//跳转后通过这个类将之前缓存的请求拿出来
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	//用这个类作跳转
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * 当需要身份认证时，跳转到这里
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/authentication/require")
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED) //返回状态码
	public SimpleResponse requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		//将请求拿到
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if(savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			logger.info("引发跳转的URL是："+ targetUrl);
			if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
				redirectStrategy.sendRedirect(request, response,
						securityProperties.getBrowser().getLoginPage());
						//根据配置文件指定跳转的页面，否则为默认页面	
			}
			
		}
		return new SimpleResponse("访问的服务需要身份验证，请先登录！");
	}
}




