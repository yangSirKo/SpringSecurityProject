/**
 * 
 */
package com.imooc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.service.HelloService;

/**
 * @author 杨Sir
 *
 * 泛型第一个参数是指定绑定的校验注解名称，第二个参数指定效验类型
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	//可以注入spring 容器任何东西来执行效验逻辑
	@Autowired
	private HelloService helloService;
	
	/**
	 * 初始化方法
	 */
	@Override
	public void initialize(MyConstraint arg0) {
		System.out.println("init MyConstraintValidator");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		helloService.greeting("jack");
		System.out.println(value);
		return false;
	}

}
