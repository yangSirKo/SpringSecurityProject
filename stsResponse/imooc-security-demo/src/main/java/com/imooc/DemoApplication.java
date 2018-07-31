/**
 * 
 */
package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author 杨Sir
 *
 */
@SpringBootApplication  //表示这是一个SpringBoot项目
@RestController         //表示是一个启动Rest服务的一个类
//@EnableSwagger2
public class DemoApplication {

	/**
	 * @param args
	 * SpringBoot 项目的入口
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello Spring Security";
	}
}
