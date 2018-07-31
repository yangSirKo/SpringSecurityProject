package com.imooc.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author 杨Sir
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		//模拟 MVC环境
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void whenUploadSuccess() throws  Exception {
		
		String result = mockMvc.perform(fileUpload("/file")
				//第一个参数必须和Controller中的处理逻辑的方法参数相同
				.file(new MockMultipartFile("file", "abc.txt", "multpart/form-data", "hello upload".getBytes("UTF-8"))))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	/**
	 * 模拟查询请求
	 */
	@Test
	public void whenQuerySuccess() throws Exception {
		
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
				.param("userName","jack").param("age", "18").param("ageTo","60").param("xxx", "yyy")
//				.param("size", "15")     //一页15条
//				.param("page", "3")      //第3页
//				.param("sort", "age,desc")   //按年龄降序排序
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	/**
	 * 获取用户详情
	 * @throws Exception 
	 */
	@Test
	public void whenGetInfoSuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())  //Rest请求是否成功用状态码表示
				.andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("Tom"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	/**
	 * 获取用户详情失败的情况
	 * MockMvcRequestBuilders 和 MockMvcResultMatchers 已经放入偏好设置，其方法可以当做静态方法直接使用。
	 * @throws Exception 
	 */
	@Test
	public void whenGetInfoFail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError()); //期望返回 4xx的状态
	}
	
	@Test
	public void whenCreateSuccess() throws Exception {
		
		Date date = new Date();
		System.out.println("发送到后台：" + date.getTime());
		
		String content = "{\"userName\":\"Tom\",\"password\":null,\"brithday\":"+date.getTime()+"}";
		String result = mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))     //post请求要传一个json字符串
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println("传回前台：" + result);
	}
	
	/**
	 * 修改
	 * @throws Exception
	 */
	@Test
	public void whenUpdateSuccess() throws Exception {
		
		//当前时间加一年
		Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		System.out.println("发送到后台：" + date.getTime());
		
		String content = "{\"id\":\"1\",\"userName\":\"Tom\",\"password\":null,\"brithday\":"+date.getTime()+"}";
		
		String result = mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))     //post请求要传一个json字符串
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println("传回前台：" + result);
	}
	
	/**
	 * 删除
	 * @throws Exception 
	 */
	@Test
	public void whenDeleteSuccess() throws Exception {
		
		mockMvc.perform(delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
	}
	
	
	
	
	
	
	
	
	
	
}
