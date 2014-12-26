package com.qg.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.qg.dao.entity.XlsToTable;
import com.qg.dao.entity.XlsxToTable;
import com.qg.tableobject.BaseUpload;
import com.qg.util.ImportFileCfg;
import com.qg.vo.ImportFileInfoVO;

/** 
* @ClassName: ExcelToTableService 
* @Description: 将Excel转成数据库的表 的服务类 
* @author qiugui 
* @date 2014年12月26日 上午10:38:10 
*  
*/ 
@Service("excelToTableService")
@Transactional
public class ExcelToTableService {

	@Resource(name = "xlsToTable")
	private XlsToTable xlsToTable;
	@Resource(name="xlsxToTable")
	private XlsxToTable xlsxToTable;

	/**   
	 * @Title: eTt   
	 * @Description: 将Excel转成数据库的表 方法   
	 * @param filePath
	 * @param suffix
	 * @throws Exception        
	 */
	 
	@SuppressWarnings("rawtypes")
	public void eTt(String filePath,String suffix,String fileflag) throws Exception {
		
		//根据上传文件的标记，获得上传文件所对应的tableobject的类路径
		String className=ImportFileCfg.keymap.get(fileflag);
		//利用反射实例化对应的上传文件类
		if (className != null && !"".equals(className)){
			Class clazz=Class.forName(className);
			BaseUpload upload=(BaseUpload) clazz.newInstance();
			ImportFileInfoVO importFileInfoVO=upload.getImportFileInfoVO();
					
			if (".xls".equals(suffix)){
				xlsToTable.excelToTable(filePath,importFileInfoVO);
			}else{
				xlsxToTable.excelToTable(filePath,importFileInfoVO);
			}
		
		}

		
	}
}
