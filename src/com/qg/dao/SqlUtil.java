 package com.qg.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
 
/** 
* @ClassName: SqlUtil 
* @Description:  注入jdbcTemplate
* @author qiugui 
* @date 2014年11月21日 下午5:58:00 
*  
*/  
@Component("sqlUtil") 
public class SqlUtil {

	@Resource(name="jdbcTemplate") 
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}

 