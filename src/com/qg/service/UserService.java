 package com.qg.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.qg.dao.entity.UserDAO;
import com.qg.domain.User;
 
@Service("userService")
@Transactional
 public class UserService {

	@Resource(name="userDAO")
	UserDAO userDAO;
	 
	public User checkUser(int id,String password){
		return userDAO.checkUser(id, password);
	}
	
	public void save(User user) throws Exception {
		userDAO.save(user);
	}
	
	public List<User> findAll() throws Exception {
		return userDAO.findAll();
	}
}

 