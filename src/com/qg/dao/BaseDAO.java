 package com.qg.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
 
 /** 
* @ClassName: BaseDAO 
* @Description: BaseDAO接口的实现 
* @author qiugui 
* @date 2014年11月29日 上午9:40:06 
* 
* @param <T> 
*/ 
@SuppressWarnings({"unchecked"})
public class BaseDAO<T> extends HqlUtil implements IBaseDAO<T> {
	
	private Class<T> entityClass;
	
	
	@SuppressWarnings("rawtypes")
	public BaseDAO(){
		Type genType=getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		entityClass=(Class) params[0];
 	}
	
	
	public T findById(int id) throws Exception {
		
		T entity=null;
		try {
			entity=this.getHibernateTemplate().get(entityClass, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return entity;		 
	}


	
	public List<T> findAll() throws Exception {
		
		List<T> list=null;
		try {
			list=(List<T>) this.getHibernateTemplate().find(
					"from "+entityClass.getName());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		 
		return list;
	}


	@Override
	public boolean save(T entity) throws Exception {
		boolean flag=false;
		try {
			this.getHibernateTemplate().save(entity);
			flag=true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		 return flag;
	}


	@Override
	public boolean update(T entity) throws Exception {
		boolean flag=false;
		try {
			this.getHibernateTemplate().update(entity);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		 return flag;
	}


	@Override
	public boolean update(String hql, Object... params) throws Exception {
		boolean flag = false;

		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}

		try {
			query.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return flag;
	}


	@Override
	public boolean deleteById(Serializable id) throws Exception {
		boolean flag=false;
		String hql ="delete from "+entityClass.getName()+" where id = ?";
		Query query=getSessionFactory().getCurrentSession().createQuery(hql);
		
		query.setParameter(0, id);
		
		int i=query.executeUpdate();
		
		if(i==1){
			flag=true;
		}
		
		 return flag;
		 
	}


	@Override
	public boolean updateById(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		 return false;
		 
	}

	
}

 