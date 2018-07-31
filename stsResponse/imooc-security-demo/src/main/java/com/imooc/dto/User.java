package com.imooc.dto;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.validator.MyConstraint;

public class User {
	//简单视图
	public interface UserSimpleView{}
	//复杂视图
	public interface UserDetailView extends UserSimpleView{}
	
	private String id;
	
	@MyConstraint(message="这是一个自定义测试")
	private String userName;
	
	@NotBlank(message="密码不能为空")
	private String password;
	
	@Past(message="生日必须是过去的时间")
	private Date brithday;
	
	@JsonView(UserSimpleView.class)
	public Date getBrithday() {
		return brithday;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@JsonView(UserSimpleView.class)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
