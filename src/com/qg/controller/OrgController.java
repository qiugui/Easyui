 package com.qg.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qg.domain.Org;
import com.qg.service.OrgService;
 @Controller
 @RequestMapping("/org")
 public class OrgController {

	 @Resource(name="orgService")
	 private OrgService orgService;
	 
	 @RequestMapping("/getTreegrid")
	 @ResponseBody
	 public void getTreegrid(HttpServletRequest request,HttpServletResponse response){
		 String id=request.getParameter("id");
		 List<Org> list=orgService.getChildrenByParentId(id);
		 response.setContentType("text/html;charset=utf-8");
		 try {
			response.getWriter().write(JSONArray.fromObject(list).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			 
		}
	 }
	 
	 @RequestMapping("/save")
	 @ResponseBody
	 public void save(HttpServletRequest request,HttpServletResponse response){
		 try {
			request.setCharacterEncoding("utf-8");
			String parent_id=request.getParameter("parent_id");
			String name=request.getParameter("name");
			String description=request.getParameter("discription");
			String principal=request.getParameter("principal");
			String count=request.getParameter("count");
			System.out.println(parent_id+name+description+principal+count);
			Org org=new Org();
			org.setCount(Integer.parseInt(count));
			org.setName(name);
			org.setDescription(description);
			org.setParent_id(Integer.parseInt(parent_id));
			org.setPrincipal(principal);
			org.setParent_id(Integer.parseInt(parent_id));
			try {
				orgService.save(org);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("{\"msg\":\"success\"}");
			} catch (Exception e) {
				 e.printStackTrace();
				response.setContentType("text/html;charset=utf-8");
				try {
					response.getWriter().write("{\"msg\":\"success\"}");
				} catch (IOException e1) {
					 e1.printStackTrace();
					 
				}
				 
			}
			
		} catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
			 
		}
		 
	 }
}

 