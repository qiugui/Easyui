 package com.qg.dao.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qg.dao.BaseDAO;
import com.qg.domain.Org;
@Repository("orgDAO") 
public class OrgDAO extends BaseDAO<Org> {

	@SuppressWarnings("unchecked")
	public List<Org> getChildrenById(String id){
		String hql="";
		
		if ("".equals(id) || id==null){
			hql="FROM Org WHERE parent_id = 999999";
		} else {
			hql="FROM Org WHERE parent_id = "+id;
		}
		
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		
		List<Org> preList=query.list();
		
		List<Org> list=new ArrayList<Org>();
		
		for (Org org:preList){
			if (this.getChildren(org.getId()+"").size()>0){
				org.setState("closed");
			}
			
			list.add(org);
		}
		
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Org> getChildren(String id){
		
		String hql="FROM Org WHERE parent_id = "+id;
		
		Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
		
		List<Org> list=query.list();
		
		return list;
		
		
	}
}

 