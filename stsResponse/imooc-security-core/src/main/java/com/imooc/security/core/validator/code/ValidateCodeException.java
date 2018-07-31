package com.imooc.security.core.validator.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 
 * @author 杨Sir
 * 身份认证过程中所有异常的基类
 */
public class ValidateCodeException extends AuthenticationException{

	private static final long serialVersionUID = 1L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
