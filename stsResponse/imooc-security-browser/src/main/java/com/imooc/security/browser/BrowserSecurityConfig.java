package com.imooc.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validator.code.ValidateCodeFilter;

/**
 * 浏览器拦截的配置类
 * @author 杨Sir
 *
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired 
	private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler imoocAuthenticationFailureHandler;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);   第一次执行 rememberMe 需要token建表
		return tokenRepository;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		//可以返回自己写的加密方式，实现PasswordEncoder接口
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.afterPropertiesSet();
		
//		http.httpBasic()   默认的是Basic验证
		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin()    //进行表单验证，会出现一个表单
//			.loginPage("/imooc-sigIn.html")   //添加一个默认的登录页面，需要配授权，不拦截这个请求，否则会拦截死循环
				.loginPage("/authentication/require")
				.loginProcessingUrl("/authentication/form") //让UsernamePasswordAuthentitionFilter处理此请求
				.successHandler(imoocAuthenticationSuccessHandler)  //配置登录成功处理页
				.failureHandler(imoocAuthenticationFailureHandler)  //登录失败处理页
				.and()
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
			.and()    
			.authorizeRequests()  //请求验证
			.antMatchers("/authentication/require",
					"/code/image",	
					securityProperties.getBrowser().getLoginPage()).permitAll()  //不拦截这个请求
			.anyRequest()    //所有请求
			.authenticated()   //身份验证
			.and()
			.csrf().disable();  //关闭请求防护功能
		
	}
	
}
