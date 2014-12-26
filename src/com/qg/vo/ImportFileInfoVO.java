 package com.qg.vo;

import org.springframework.stereotype.Component;
 /** 
* @ClassName: ImportFileInfoVO 
* @Description: 导入表的信息类 
* @author qiugui 
* @date 2014年12月26日 上午11:33:23 
*  
*/ 
@Component(value="importFileInfoVO")
 public class ImportFileInfoVO {

	//表名称
	private String tableName;
	//操作描述
	private String operDesc;
	//字段信息
	private TableFieldInfoVO[] fieldsInfo;
	//其他字段个数
	private int otherFieldLength;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getOperDesc() {
		return operDesc;
	}
	public void setOperDesc(String operDesc) {
		this.operDesc = operDesc;
	}
	public TableFieldInfoVO[] getFieldsInfo() {
		return fieldsInfo;
	}
	public void setFieldsInfo(TableFieldInfoVO[] fieldsInfo) {
		this.fieldsInfo = fieldsInfo;
	}
	public int getOtherFieldLength() {
		return otherFieldLength;
	}
	public void setOtherFieldLength(int otherFieldLength) {
		this.otherFieldLength = otherFieldLength;
	}
}

 