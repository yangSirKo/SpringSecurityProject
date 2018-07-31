package com.imooc.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * 
 * @author 杨Sir
 * //@Constraint表示是一个校验注解,括号里的值是指定执行该效验逻辑的类
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {

	String message();

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
