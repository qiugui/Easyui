 package com.qg.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qg.domain.MyResource;
import com.qg.dto.TreeDTO;
import com.qg.service.ResourceService;
 
@Controller 
@RequestMapping("/resource")
 public class ResourceController {

	@Resource(name="resourceService")
	private ResourceService resourceService;
	
	 @RequestMapping("/getTree")
	 @ResponseBody
	 public void getTree(HttpServletRequest request,HttpServletResponse response){
		 
		 
		 try {
			 //获取当前展开的节点id
			 String id=request.getParameter("id");
			 List<TreeDTO> tlist = this.resourceService.getChildrenByParentId(id);
			 
			 response.setContentType("text/html;charset=utf-8");
			 response.getWriter().write(JSONArray.fromObject(tlist).toString());
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 @RequestMapping("/changelevel")
	 @ResponseBody
	 public void changeLevel(HttpServletRequest request,HttpServletResponse response){
	 
		 try {
			String targetId=request.getParameter("targetId");
			String sourceId=request.getParameter("sourceId");
			String point=request.getParameter("point");
			//得到目标对象
			MyResource target=resourceService.findById(Integer.parseInt(targetId));
			//得到被操作的对象
			MyResource source=resourceService.findById(Integer.parseInt(sourceId));
			 
			if("append".equals(point)){
				source.setParent_id(target.getId());
			} else {
				source.setParent_id(target.getParent_id());
			}
			 
			resourceService.update(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
	 
	 }
	 
	 @RequestMapping("/save")
	 @ResponseBody
	 public String save(HttpServletRequest request,HttpServletResponse response){
		try {
			 String parentId=request.getParameter("parentId");
			 String name=request.getParameter("name");
			 String url=request.getParameter("url");
			 System.out.println(parentId+" "+name+" "+url);
			 MyResource myResource=new MyResource();
			 myResource.setName(name);
			 myResource.setUrl(url);
			 myResource.setParent_id(Integer.parseInt(parentId));
			 
			 resourceService.save(myResource);
			 return "{\"msg\":\"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"msg\":\"error\"}";
		}
	 }
	 
	 @RequestMapping("/update")
	 @ResponseBody
	 public String update(HttpServletRequest request,HttpServletResponse response){
		 try {
			 String id=request.getParameter("id");
			 String name=request.getParameter("name");
			 String url=request.getParameter("url");
			 Object params[]=new Object[3];
			 params[0]=name;
			 params[1]=url;
			 params[2]=Integer.parseInt(id);		 
			 String hql="update MyResource set name=?,url=? where id=?";		 
			 resourceService.update(hql, params);
             return "{\"msg\":\"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"msg\":\"error\"}";
		}
	 }
	
	 @RequestMapping("/delete")
	 @ResponseBody
	 public String deleteNodes(HttpServletRequest request,HttpServletResponse response){
		 try {
			 String id=request.getParameter("id");
			 resourceService.delete(Integer.parseInt(id));
			 return "{\"msg\":\"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"msg\":\"error\"}";
		}
	 }
	 
}

 