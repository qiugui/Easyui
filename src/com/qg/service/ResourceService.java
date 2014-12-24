 package com.qg.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.qg.dao.entity.ResourceDAO;
import com.qg.domain.MyResource;
import com.qg.dto.TreeDTO;
 @Service("resourceService")
 @Transactional
 public class ResourceService {

	 @Resource(name="resourceDAO")
	 private ResourceDAO resourceDAO;
	 
	 public MyResource findById(int id) throws Exception{

			return resourceDAO.findById(id);
				 
	 }

	 public List<MyResource> getChildren(int id) throws Exception{
		 return resourceDAO.getChildren(id);
	 }
	 
	public List<TreeDTO> getChildrenByParentId(String id) throws Exception {
		
		 return resourceDAO.getChildrenByParentId(id);
		 
	}
	
	public boolean update(MyResource entity) throws Exception{
		return resourceDAO.update(entity);
	}
	
	public boolean update(String hql, Object... params) throws Exception {
		return resourceDAO.update(hql, params);
	}
	
	public boolean save(MyResource entity) throws Exception{
		return resourceDAO.save(entity);
	}
	
	public void delete(int id) throws Exception{
		List<MyResource> children=resourceDAO.getChildren(id);
		for(MyResource child:children){
			int cid=child.getId();
			resourceDAO.deleteById(cid);
			this.delete(cid);	
		}
		resourceDAO.deleteById(id);
	}
}

 