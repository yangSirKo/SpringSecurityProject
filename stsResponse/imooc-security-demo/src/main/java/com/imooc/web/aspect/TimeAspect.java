package com.imooc.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 
 * @author 杨Sir
 *
 */
//@Aspect   注解掉是为了不影响看 异步处理的日志
//@Component
public class TimeAspect {

	/**
	 * 环绕的方式
	 * @param pjp
	 * @return 返回被拦截的方法的返回值
	 * @throws Throwable 
	 */
	@Around("execution(* com.imooc.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("time aspect start");

		//获取参数
		Object[] objs = pjp.getArgs();
		for(Object obj : objs) {
			System.out.println("arg is "+obj);
		}
		
		long start = new Date().getTime();
		Object result = pjp.proceed();
		System.out.println("time aspect 耗时 " + (new Date().getTime() - start));
		
		System.out.println("time aspect end");
		return result;
	}
	
}
