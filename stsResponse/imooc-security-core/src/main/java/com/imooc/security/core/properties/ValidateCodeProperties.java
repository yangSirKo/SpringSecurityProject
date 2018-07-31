/**
 * 
 */
package com.imooc.security.core.properties;

/**
 * @author 杨Sir
 * 加载图片验证码的应用级配置
 */
public class ValidateCodeProperties {

	private  ImageCodeProperties image = new ImageCodeProperties();
	
	private  SmsCodeProperties sms = new SmsCodeProperties();

	public ImageCodeProperties getImage() {
		return image;
	}

	public SmsCodeProperties getSms() {
		return sms;
	}

	public void setSms(SmsCodeProperties sms) {
		this.sms = sms;
	}

	public void setImage(ImageCodeProperties image) {
		this.image = image;
	}
	
	
}
