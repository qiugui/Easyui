 package com.qg.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qg.domain.User;
import com.qg.service.UserService;
 
 /** 
* @ClassName: Test 
* @Description: AJAX请求测试
* @author qiugui 
* @date 2015年2月13日 下午2:45:38 
*  
*/ 
@Controller
 @RequestMapping("test")
 public class Test {
	 
	 @Resource(name="userService")
	 UserService userService;
	 
	 @RequestMapping("test1")
	 @ResponseBody
	 public void test(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 response.setHeader("Access-Control-Allow-Origin", "*");
		 response.setHeader("Content-type", "text/html; charset=utf-8");
		 PrintWriter out = response.getWriter();
		 
		 
		 
		 List<User> list = userService.findAll();
		 
		 out.write("<h3 style=\"font-family:Cursive,楷体\">hello world!用户信息表</h3>");
		 out.write("<table border=1px");
		 out.write("<tr><th>姓名</th><th>邮件</th></tr>");
		 
		 for(User user : list){
			 out.write("<tr><td>"+user.getUsername()+"</td><td>"+user.getEmail()+"</td></tr>"); 
		 }
		 
		 out.write("</table>");
	 }
}

 