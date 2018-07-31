/**
 * 
 */
package com.imooc.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * @author 杨Sir
 *
 */
//@Component   使用WebConfig进行注册
public class TimeFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("time filter destroy");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("time filter start");
		long startTime = new Date().getTime();
		chain.doFilter(request, response);
		System.out.println("time filter 耗时 " + (new Date().getTime() - startTime ));
		System.out.println("time filter end");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("time filter init");

	}

}
