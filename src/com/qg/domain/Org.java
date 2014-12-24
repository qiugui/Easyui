 package com.qg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
 @Entity
 @Table(name="org")
 public class Org {

	 @Id
	 @Column(name="id",length=11)
	 @GenericGenerator(name="idGenerator",strategy="increment")
	 @GeneratedValue(generator="idGenerator")
	 private int id;
	 
	 @Column(name="name",length=128)
	 private String name;
	 
	 @Column(name="iconCls",length=128)
	 private String iconCls;
	 
	 @Column(name="principal",length=128)
	 private String principal;
	 
	 @Column(name="count",length=11)
	 private int count;
	 
	 @Column(name="description",length=128)
	 private String description;
	 
	 @Column(name="state",length=128)
	 private String state;
	 
	 @Column(name="parent_id",length=11)
	 private int parent_id;

	 public Org(){
		 
	 }
	 
	 
	 
	public Org(int id, String name, String iconCls, String principal,
			int count, String description, String state, int parent_id) {
		this.id = id;
		this.name = name;
		this.iconCls = iconCls;
		this.principal = principal;
		this.count = count;
		this.description = description;
		this.state = state;
		this.parent_id = parent_id;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	 
}

 