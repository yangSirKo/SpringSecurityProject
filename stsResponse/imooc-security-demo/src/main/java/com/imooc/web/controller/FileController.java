package com.imooc.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.dto.FileInfo;


/**
 * 
 * @author 杨Sir
 * 处理文件的控制器
 */
@RestController
@RequestMapping("/file")
public class FileController {

	String filePath = "F:\\stsResponse\\imooc-security-demo\\src\\main\\java\\com\\imooc\\web\\controller";
	
	@PostMapping
	public FileInfo upload(MultipartFile file) throws  IOException {
		
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		File localFile = new File(filePath,new Date().getTime()+".txt");
		file.transferTo(localFile);
		
		return new FileInfo(localFile.getAbsolutePath());
	}
	
	@GetMapping("{id:\\d+}")
	public void download(@PathVariable String id,HttpServletRequest request,HttpServletResponse response) {
		
		try(
			InputStream inputStream = new FileInputStream(new File(filePath,id+".txt"));
			OutputStream outputStream = response.getOutputStream();
				){
			//下载的contentType
			response.setContentType("application/x-download");
			//指定下载到文件的名称
			response.setHeader("Content-Disposition", "attachment;filename=test.txt");
			
			//将InputStream流 复制到OutputStream流中
			IOUtils.copy(inputStream, outputStream);
			
			outputStream.flush();
		}catch(Exception e) {	
			System.out.println("文件下载出错");
		}
		
	}

}
