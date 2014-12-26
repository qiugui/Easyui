 package com.qg.tableobject;

import org.springframework.stereotype.Component;

import com.qg.vo.ImportFileInfoVO;
import com.qg.vo.TableFieldInfoVO;
 /** 
* @ClassName: UserinfoUpload 
* @Description: 用户信息上传类 
* @author qiugui 
* @date 2014年12月26日 上午11:19:11 
*  
*/ 
@Component("userinfoUpload")
public class UserinfoUpload extends BaseUpload {

	
	public UserinfoUpload(){
		//设置导入的数据表格信息
		importFileInfoVO=new ImportFileInfoVO();
		TableFieldInfoVO[] fieldsInfo=new TableFieldInfoVO[5];
		importFileInfoVO.setFieldsInfo(fieldsInfo);
		importFileInfoVO.setTableName("user_info");
		importFileInfoVO.setOperDesc("用户信息的导入");
		importFileInfoVO.setOtherFieldLength(3);
		
		//设置字段名
		fieldsInfo[0]=new TableFieldInfoVO();
		fieldsInfo[0].setFieldName("id");
		fieldsInfo[0].setFieldDesc("用户id");
		fieldsInfo[0].setLimitDesc("\"用户id\"为主键");
		fieldsInfo[0].setKey(true);
		fieldsInfo[0].setUnique(true);
		
		fieldsInfo[1]=new TableFieldInfoVO();
		fieldsInfo[1].setFieldName("name");
		fieldsInfo[1].setFieldDesc("用户名");
		fieldsInfo[1].setLimitDesc("\"用户名\"字段");
		
		fieldsInfo[2]=new TableFieldInfoVO();
		fieldsInfo[2].setFieldName("sex");
		fieldsInfo[2].setFieldDesc("性别");
		fieldsInfo[2].setLimitDesc("\"用户性别\"为男、女");
		
		fieldsInfo[3]=new TableFieldInfoVO();
		fieldsInfo[3].setFieldName("age");
		fieldsInfo[3].setFieldDesc("年龄");
		fieldsInfo[3].setLimitDesc("\"用户年龄\"字段");
		
		fieldsInfo[4]=new TableFieldInfoVO();
		fieldsInfo[4].setFieldName("address");
		fieldsInfo[4].setFieldDesc("地址");
		fieldsInfo[4].setLimitDesc("\"用户地址\"字段");
		
	}
}

 