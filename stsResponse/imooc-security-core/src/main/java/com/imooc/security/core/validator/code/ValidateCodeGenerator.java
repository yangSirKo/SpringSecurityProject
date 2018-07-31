/**
 * 
 */
package com.imooc.security.core.validator.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author 杨Sir
 * 验证码生成逻辑可配，其他应用可以实现该接口进行自定义
 * 
 * 典型的增量开发模式：不改变原有代码的基础上进行扩展
 */
public interface ValidateCodeGenerator {
	/**
	 * 生成验证码的方法
	 * @param request
	 * @return
	 */
	ValidateCode generate(ServletWebRequest request);

}
