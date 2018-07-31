/**
 * 
 */
package com.imooc.exception;

/**
 * @author 杨Sir
 * 自定义一个用户不存在异常
 */
public class UserNotExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	public UserNotExistException(String id) {
		super("user not exit");
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		
}
