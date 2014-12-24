 package com.qg.dao.entity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qg.dao.BaseDAO;
import com.qg.domain.User;
@Repository("userDAO") 
public class UserDAO extends BaseDAO<User> {

	public User user;
	
	@SuppressWarnings("unchecked")
	public User checkUser(int id,String password){
		List<User> list= (List<User>) getHibernateTemplate().find(
				"from User where id=? and password=?", id,password);
		
		user=list.get(0);
		return user;
	}
}

 