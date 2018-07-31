package com.imooc.security.core.validator.code;

import java.time.LocalDateTime;

/**
 * 短信验证码接口
 */
public class ValidateCode {
	
	protected String code;
	protected LocalDateTime expireTime;

	// 第三个代表多少秒后验证码过期
	public ValidateCode(String code, int expireIn) {
		super();
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}

	public ValidateCode(String code, LocalDateTime expireTime) {
		super();
		this.code = code;
		this.expireTime = expireTime;
	}
	
	//验证码是否过期
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

}
