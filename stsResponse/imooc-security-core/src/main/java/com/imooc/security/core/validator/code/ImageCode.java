package com.imooc.security.core.validator.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码接口，继承validate类
 * 
 * @author 杨Sir
 *
 */
public class ImageCode extends ValidateCode{

	private BufferedImage image;
	
	// 第三个代表多少秒后验证码过期
	public ImageCode(BufferedImage image, String code, int expireIn) {
		super(code,expireIn);
		this.image = image;
	}

	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
		super(code, expireTime);
		this.image = image;
	}
	
	//验证码是否过期
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
