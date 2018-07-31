package com.imooc.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 
 * @author 杨Sir
 * 处理用户信息获取逻辑
 */
@Component
public class MyUserDetailsService implements UserDetailsService{

//	@Autowired
//	可以注入一些Dao或mapper对象，供下面的loadUserByUsername使用
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		logger.info("登录的用户名："+userName);
		
		//根据用户名查找用户信息
		
		//User实现了UserDetails接口
		//密码应该是从数据库查出来的值
		//第三个参数权限也应该用数据库中查出来
//		return new User(userName, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		
		String pwd = encoder.encode("1234567");
		logger.info("数据库密码是："+ pwd );
		//根据查询信息判断用户是否被冻结
		return new User(userName, pwd, 
				true, true, true, true, 
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}
}


 







