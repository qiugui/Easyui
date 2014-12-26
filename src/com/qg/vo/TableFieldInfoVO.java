 package com.qg.vo;

import org.springframework.stereotype.Component;
 /** 
* @ClassName: TableFieldInfoVO 
* @Description: 待导入表的字段信息VO类 
* @author qiugui 
* @date 2014年12月26日 上午11:34:36 
*  
*/ 
@Component(value="tableFieldInfoVO")
 public class TableFieldInfoVO {

	//字段名
	private String fieldName;
	//字段中文描述（必填）
	private String fieldDesc;
	//是否主键
	private boolean isKey;
	//是否唯一约束
	private boolean isUnique;
	//正则表达式
	private String regex;
	//限制描述
	private String limitDesc;
	//如果可以为空，为空时的默认值
	private String defaultValue;
	//字段值
	private String fieldValue;
	//该数据是否与其他表关联
	private boolean isTableValue;
	//检验数据关联性表语句
	private String isTableSql;
	
	/**   
	 * @Title:  TableFieldInfoVO   
	 * @Description:  设置默认值      
	 */  
	 
	public TableFieldInfoVO(){
		//非主键
		this.isKey = false;
		//正则表达式可以为空
		this.regex = "";
		//可以不唯一
		this.isUnique = false;
		//无限制
		this.limitDesc = "";
		//默认值为空
		this.defaultValue = "";
		//默认为false
		this.isTableValue = false;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public boolean isKey() {
		return isKey;
	}

	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}

	public boolean isUnique() {
		return isUnique;
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getLimitDesc() {
		return limitDesc;
	}

	public void setLimitDesc(String limitDesc) {
		this.limitDesc = limitDesc;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public boolean isTableValue() {
		return isTableValue;
	}

	public void setTableValue(boolean isTableValue) {
		this.isTableValue = isTableValue;
	}

	public String getIsTableSql() {
		return isTableSql;
	}

	public void setIsTableSql(String isTableSql) {
		this.isTableSql = isTableSql;
	}
	
	
}

 