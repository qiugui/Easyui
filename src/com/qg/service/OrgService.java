 package com.qg.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.qg.dao.entity.OrgDAO;
import com.qg.domain.Org;
 @Service("orgService")
 @Transactional
 public class OrgService {

	 @Resource(name="orgDAO")
	 private OrgDAO orgDAO;
	 
	 public List<Org> getChildrenByParentId(String id){
		 return orgDAO.getChildrenById(id);
	 }
	 
	 public boolean save(Org org) throws Exception{
		 return orgDAO.save(org);
	 }
}

 