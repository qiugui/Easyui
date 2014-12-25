/*  Copyright 2011-2013 Shanghai Huateng Software Systems Co., Ltd.
 *  All rights reserved.
 *  THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF SHANGHAI HUATENG
 *  SOFTWARE SYSTEMS CO., LTD.  THE CONTENTS OF THIS FILE MAY NOT
 *  BE DISCLOSED TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM,
 *  IN WHOLE OR IN PART, WITHOUT THE PRIOR WRITTEN PERMISSION OF
 *  SHANGHAI HUATENG SOFTWARE SYSTEMS CO., LTD.
 *
 *  title:中国建设银行新一代风险监控系统－反钓鱼子系统
 *  author：kin wong
 *  date:2013-01-29 下午03:13:26
 *  file desc：批量导入信息VO类文件
 */
package com.huateng.uniform.excel.batchimport;

/**
 * author: kin wong
 *
 * class desc:批量导入信息VO类
 */
public class BatchImportInfoVO {
	private String tableName;//表名
	private String operDesc;//操作描述
	private TableFieldInfoVO[] fieldInfo;//字段信息
	private int otherFieldLength; //其它字段个数
	
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

	public TableFieldInfoVO[] getFieldInfo() {
		return fieldInfo;
	}
	public void setFieldInfo(TableFieldInfoVO[] fieldInfo) {
		this.fieldInfo = fieldInfo;
	}
	public int getOtherFieldLength() {
		return otherFieldLength;
	}
	public void setOtherFieldLength(int otherFieldLength) {
		this.otherFieldLength = otherFieldLength;
	}
}
