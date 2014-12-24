 package com.qg.domain;

import org.springframework.stereotype.Repository;
 @Repository("myFile")
 public class MyFile {
	 private String id;
	 private String fileName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}

 