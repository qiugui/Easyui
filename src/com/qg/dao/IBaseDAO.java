 package com.qg.dao;

import java.io.Serializable;
import java.util.List;
 
 
 
/** 
* @ClassName: IBaseDAO 
* @Description: 基本的DAO接口 
* @author qiugui 
* @date 2014年11月29日 上午9:38:22 
* 
* @param <T> 
*/ 
public interface IBaseDAO <T>{

	public boolean save(T entity)throws Exception;
	
	public boolean update(T entity)throws Exception;
	
	public boolean updateById(Serializable id)throws Exception;
	
	public boolean deleteById(Serializable id)throws Exception;
	
	public T findById(int id)throws Exception;
	
	public List<T> findAll()throws Exception;

	boolean update(String hql, Object... params) throws Exception;
	
	
}

 