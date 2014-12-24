 package com.qg.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qg.domain.City;
import com.qg.domain.User;
import com.qg.service.UserService;
 
 @Controller
 @RequestMapping("/user")
 public class UserController {

	 @Resource(name="userService")
	 public UserService userService;
	 
	 @RequestMapping(value="/showall",method=RequestMethod.POST)
	 public void showAll(HttpServletRequest request,HttpServletResponse response){
		 List<User> list=null;
		 try {
			list=userService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			 
		}
		 
		 response.setContentType("text/html;charset=UTF-8");
		 String json=JSONArray.fromObject(list).toString();
		 try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			 
		}
	 }
	 
	 @RequestMapping(value="/checkuser",method=RequestMethod.POST)
	 public String checkUser(@RequestParam("id")int id,
			 @RequestParam("password") String password){
		 User user=null;
		 user=userService.checkUser(id, password);
		 if(user!=null){
			 return "ok";
		 }else{
			 return "error";
		 }
	 }
 	 
	 @RequestMapping(value="/showcity",method=RequestMethod.POST)
	 public void showCity(HttpServletRequest request,HttpServletResponse response){
		 List<City> list=new ArrayList<City>();		
		 City city1=new City(1, "北京");
		 City city2=new City(2, "深圳");
		 City city3=new City(3, "上海");
		 City city4=new City(4, "广州");
		 
		 list.add(city1);
		 list.add(city2);
		 list.add(city3);
		 list.add(city4);
		 
		 String string=JSONArray.fromObject(list).toString();
		 
		 response.setContentType("text/html;charset=UTF-8");
		 try {
			response.getWriter().write(string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			 
		}
	 }
	 
	 @RequestMapping(value="/save",method=RequestMethod.POST)
	 public void save (HttpServletRequest request,HttpServletResponse response) {
		 User user=new User();
		 String info=null;		 
		 
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		 
		 int id=Integer.parseInt(request.getParameter("id"));
		 String username=request.getParameter("username");
		 String password=request.getParameter("password");
		 Date birthday=null;
		try {
			birthday = sdf.parse(request.getParameter("birthday"));
		} catch (ParseException e2) {
			 e2.printStackTrace();
			 
		}
		 int cityId=Integer.parseInt(request.getParameter("city"));
		 String email=request.getParameter("email");
		 
		 user.setId(id);
		 user.setUsername(username);
		 user.setPassword(password);
		 user.setBirthday(birthday);
		 user.setEmail(email);
		 user.setCityId(cityId);
		 
		 try {
			 userService.save(user);
			 info="{\"title\":\"成功！\",\"message\":\"保存成功！\"}";
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().write(info);
		} catch (Exception e) {
			e.printStackTrace();
			info="{\"title\":\"失败！\",\"message\":\"保存失败！\"}";
			response.setContentType("text/html;charset=UTF-8");
			try {
				response.getWriter().write(info);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				 e1.printStackTrace();
				 
			}
		}
	 }
}

 