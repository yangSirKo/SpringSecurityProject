/**
 * 
 */
package com.imooc.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.imooc.web.filter.TimeFilter;
import com.imooc.web.interceptor.TimeInterceptor;

/**
 * @author 杨Sir
 * 
 */
@Configuration  
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private TimeInterceptor interceptor;
	
	/**
	 * 添加一个拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(interceptor);   注解掉是为了不影响看 异步处理的日志
	}
	
	/**
	 * 创建过滤器 bean
	 * @return
	 */
//	@Bean   注解掉是为了不影响看 异步处理的日志
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean  filterRegistrationBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		filterRegistrationBean.setFilter(timeFilter);
		  
		List<String> urls = new ArrayList<>();
		urls.add("/*");   //拦截所有请求
		filterRegistrationBean.setUrlPatterns(urls);
		
		return filterRegistrationBean;
	}
	
}
