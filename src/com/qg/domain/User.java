package com.qg.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="user")
public class User implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id",length=11,nullable=false)
	@GenericGenerator(name="idGenerator",strategy="identity")
	@GeneratedValue(generator="idGenerator")
	private Integer id;
	
	@Column(name="username",length=15,nullable=false)
	private String username;
	
	@Column(name="password",length=32,nullable=false)
	private String password;
	
	@Column(name="email",length=20,nullable=true)
	private String email;
	
	@Column(name="birthday")
	private Date birthday;
	
	@Column(name="cityId",length=2)
	private Integer cityId;
	
	public Integer getCityId() {
		return cityId;
	}


	public void setCityId(Integer cityId) {
		if(cityId==null){
			cityId=0;
		}
		this.cityId = cityId;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public User(){

	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
