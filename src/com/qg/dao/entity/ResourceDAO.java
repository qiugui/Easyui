 package com.qg.dao.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qg.dao.BaseDAO;
import com.qg.domain.MyResource;
import com.qg.dto.TreeDTO;
@Repository("resourceDAO") 
public class ResourceDAO extends BaseDAO<MyResource> {

	public MyResource findById(int id) throws Exception{
		return super.findById(id);
	}

	public boolean update(MyResource entity) throws Exception {
		return super.update(entity);
	};
	
	public boolean update(String hql, Object... params) throws Exception {
		return super.update(hql, params);
	}
	
	/**
	 * 
	 * @Title: getChildrenByParentId   
	 * @Description: 根据父id获取孩子节点们   
	 * @param id
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<TreeDTO> getChildrenByParentId(String id) throws Exception {
		
		String hql="";
		
		if("".equals(id) || id==null){
			hql="from MyResource where parent_id = 999999";
		} else {
			hql="from MyResource where parent_id = "+id;
		}
		
		List<MyResource> myResourcesList=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).list();
		
		List<TreeDTO> tList=new ArrayList<TreeDTO>();
		
		for(MyResource myResource:myResourcesList){
			TreeDTO treeDTO=new TreeDTO();
			treeDTO.setId(myResource.getId());
			treeDTO.setText(myResource.getName());
			treeDTO.setChecked(myResource.getChecked());
			treeDTO.setIconCls(myResource.getIcon());
			treeDTO.setParent_id(myResource.getParent_id());
			if(getChildren(myResource.getId()).size()>0){
				treeDTO.setState("closed");
			}else{
				treeDTO.setState("open");
			}					
			
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("url", myResource.getUrl());
			treeDTO.setAttributes(map);
			tList.add(treeDTO);
		}
		
		 return tList;
	}
	
	/**   
	 * @Title: getChildren   
	 * @Description: 根据父id获取孩子   
	 * @param pid
	 * @return
	 * @throws Exception        
	 */
	 
	@SuppressWarnings("unchecked")
	public List<MyResource> getChildren(int pid) throws Exception{
			
		String hql = "from MyResource where parent_id="+pid;
			
		List<MyResource> children=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).list();
		return children;
		
	}
}

 