 package com.qg.util;

import java.util.HashMap;
import java.util.Map;
 /** 
* @ClassName: ImportFileCfg 
* @Description: 导入文件的配置类 
* @author qiugui 
* @date 2014年12月26日 上午11:10:56 
*  
*/ 
public class ImportFileCfg {

	public final static Map<String, String> keymap=new HashMap<String, String>();
	
	static {
		keymap.put("userinfo", "com.qg.tableobject.UserinfoUpload");
	}
}

 