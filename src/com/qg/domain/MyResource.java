 package com.qg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="resource")
 public class MyResource {

	 @Id
	 @Column(name="id",length=11)
	 @GenericGenerator(name="generator",strategy="increment")
	 @GeneratedValue(generator="generator")
	 private int id;
	 
	 @Column(name="name",length=32)
	 private String name;
	 
	 @Column(name="url",length=128)
	 private String url;
	 
	 @Column(name="checked",length=11)
	 private int checked;
	 
	 @Column(name="icon",length=32)
	 private String icon;
	 
	 @Column(name="parent_id",length=11)
	 private int parent_id;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
}

 