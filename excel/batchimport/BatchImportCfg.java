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
 *  file desc：批量导入配置类文件
 */
package com.huateng.uniform.excel.batchimport;

import java.util.HashMap;
import java.util.Map;

/**
 * author: kin wong
 *
 * class desc:批量导入配置类
 */
public class BatchImportCfg {
	public static int DATA_MAX_SIZE = 1000;//文件限制的最大量
	
	public final static Map<String,String> keymap = new  HashMap<String, String>();//静态批量导入对象映射
	static{
		keymap.put("bctl", "com.huateng.uniform.excel.tableobject.BctlItemUpload");//机构导入
		keymap.put("tlrinfo", "com.huateng.uniform.excel.tableobject.TlrInfoItemUpload");//人员导入
		keymap.put("roleinfo", "com.huateng.uniform.excel.tableobject.RoleInfoItemUpload");//角色导入
	}
}
