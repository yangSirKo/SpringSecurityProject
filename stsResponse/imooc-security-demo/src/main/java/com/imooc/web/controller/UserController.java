package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.User.UserDetailView;
import com.imooc.dto.User.UserSimpleView;
import com.imooc.dto.UserQueryCondition;
import com.imooc.exception.UserNotExistException;

//import io.swagger.annotations.ApiParam;

/**
 * 
 * @author 杨Sir
 * Pageable是 spring-data里的一个对象，如果用spring-data做数据访问层，pageable分页会很方便。
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	/**
	 * 获取当前用户
	 */
	@GetMapping("/me")
	public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
		
		return user;
		
		//带参数的写法 Authentication，结果和下面的方法相同
		//return authentication;
		//方法不带参数的写法
		//return SecurityContextHolder.getContext().getAuthentication();
	}
	
	/**
	 * 删除用户
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public void delete( @PathVariable String id) {
		
		System.out.println(id);
//		throw new UserNotExistException("删除异常");
	}
	
	/**
	 * 更新用户
	 * @param user
	 * @param errors
	 * @return
	 */
	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {
		
		if(errors.hasErrors()) {
			//打印 @Valid验证出的所有错误
			errors.getAllErrors().stream().forEach(error -> 
				{
//					FieldError fieldError = (FieldError)error;
//					String message = fieldError.getField() +" " + fieldError.getDefaultMessage();
					System.out.println(error.getDefaultMessage());
				}
			);
		}
		
		System.out.println(user.getId());
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
		System.out.println("后台打印：" + user.getBrithday());
		
		user.setId("1");
		return user;
	}
	
	/**
	 * 添加
	 * @param user
	 * @param errors
	 * @return
	 */
	@PostMapping
	public User create(@Valid @RequestBody User user, BindingResult errors) {
		
		if(errors.hasErrors()) {
			//打印 @Valid验证出的所有错误
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		
		System.out.println(user.getId());
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
		System.out.println("后台打印：" + user.getBrithday());
		
		user.setId("1");
		return user;
	}
	
	/**
	 * 查看多个用户
	 * @param condition
	 * @param pageable
	 * @return
	 */
	@GetMapping()
	@JsonView(UserSimpleView.class)
	public List<User> query(UserQueryCondition condition,
							@PageableDefault(page=1,size=15,sort="userName,asc") Pageable pageable){
		
		//反射的toString工具打印对象到控制台
		System.out.println(ReflectionToStringBuilder.toString(condition,ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());
		
		List<User> userList = new ArrayList<>();
		userList.add(new User());
		userList.add(new User());
		userList.add(new User());

		return userList;
	}
	
	/**
	 * 查看详情
	 * @param id
	 * @return
	 * @throws UserNotExistException
	 */
	@GetMapping("/{id:\\d+}")
	@JsonView(UserDetailView.class)
	public User getInfo(@PathVariable String id) throws UserNotExistException {
		
//		throw new RuntimeException(id);
		System.out.println("getInfo start...");
		User user = new User();
		user.setUserName("Tom");
		return user;
	}
}
