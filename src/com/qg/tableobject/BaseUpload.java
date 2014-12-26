 package com.qg.tableobject;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qg.vo.ImportFileInfoVO;
 /** 
* @ClassName: BaseUpload 
* @Description: 上传文件的基类 
* @author qiugui 
* @date 2014年12月26日 上午11:17:54 
*  
*/ 
@Component("baseUpload")
public class BaseUpload {

	@Resource(name="importFileInfoVO")
	ImportFileInfoVO importFileInfoVO;

	public ImportFileInfoVO getImportFileInfoVO() {
		return importFileInfoVO;
	}
	
}

 