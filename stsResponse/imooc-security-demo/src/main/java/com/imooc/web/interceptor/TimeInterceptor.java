package com.imooc.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * @author 杨Sir
 * 想要拦截器起作用，需要在配置文件中进行注册
 */
@Component 
public class TimeInterceptor implements HandlerInterceptor {

	/**
	 * 在拦截服务器方法之前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {

		System.out.println("preHandle");
		
		//打印controller类名和方法名
		System.out.println(((HandlerMethod)handle).getBean().getClass().getName());
		System.out.println(((HandlerMethod)handle).getMethod().getName());
		request.setAttribute("start", new Date().getTime());
		
		//返回 false，则拦截的请求立马结束
		return true;
	}

	/**
	 * 在拦截服务器方法运行之后执行，如果服务器方法中抛出异常，则不执行此方法。		
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, 
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
		long start = (long) request.getAttribute("start");
		System.out.println("time interceptor 耗时："+ (new Date().getTime()-start));
	
	}

	
	/**
	 * 不管是否抛出异常，都会执行该方法
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handle, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
		long start = (long) request.getAttribute("start");
		System.out.println("time interceptor 耗时："+ (new Date().getTime()-start));
		System.out.println("ex is " + ex);
	
	}

}
