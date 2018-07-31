/**
 * 
 */
package com.imooc.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.imooc.exception.UserNotExistException;

/**
 * @author 杨Sir
 * 用来处理控制器抛出的异常 
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(UserNotExistException.class) //确定处理哪个异常
	@ResponseBody   //返回为json
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  //返回的http状态码
	public Map<String,Object> handleUserNotExistException(UserNotExistException ex){
		
		Map<String,Object> result = new HashMap<>();
		result.put("id", ex.getId());
		result.put("message", ex.getMessage());
		return result;
	}
}
