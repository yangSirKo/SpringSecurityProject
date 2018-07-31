/**
 * 
 */
package com.imooc.service.impl;

import org.springframework.stereotype.Service;

import com.imooc.service.HelloService;

/**
 * @author 杨Sir
 *
 */
@Service
public class HelloServiceImpl implements HelloService{

	@Override
	public String greeting(String name) {
		System.out.println("greeting ...");
		return "hello "+name;
	}

	
}
