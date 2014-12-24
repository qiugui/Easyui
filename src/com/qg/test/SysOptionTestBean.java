package com.qg.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qg.dao.entity.UserDAO;
import com.qg.domain.MyResource;
import com.qg.service.ResourceService;
import com.qg.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:resources/config/spring/applicationContext.xml" })
public class SysOptionTestBean extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource(name="userService")
	private UserService userService;
	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Resource(name="resourceService")
	private ResourceService resourceService;
	
	@Test
	@Rollback(false)
	public void test() throws Exception {
		
		int id=2;
	/*	String password="123456";
		
		User user=userDAO.checkUser(id, password);
		
		System.out.println(user.getId()+user.getUsername()+user.getEmail());*/

		MyResource myResource=new MyResource();
		
		myResource=resourceService.findById(id);
		
		System.out.println(myResource.getId()+" "+myResource.getName()+" "+myResource.getParent_id());
	}
}
