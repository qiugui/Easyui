
package com.huateng.uniform.excel.tableobject;

import java.util.Iterator;

import com.huateng.ebank.business.common.DAOUtils;
import com.huateng.ebank.business.common.GlobalInfo;
import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.uniform.excel.batchimport.BaseUpload;
import com.huateng.uniform.excel.batchimport.BatchImportInfoVO;
import com.huateng.uniform.excel.batchimport.TableFieldInfoVO;
import com.huateng.uniform.utills.DataMyUtil;
import com.nantian.ofpiwap.secure.Account;
import com.nantian.ofpiwap.secure.SecurityContext;

/**
 * author: kin wong
 * 
 * class desc:可信域名批量上传类
 */
public class RoleInfoItemUpload extends BaseUpload {
	public RoleInfoItemUpload() throws Exception {
		GlobalInfo globalInfo = GlobalInfo.getCurrentInstance();
		/* 设置总体信息 */
		batchVO = new BatchImportInfoVO();
		TableFieldInfoVO[] fields = new TableFieldInfoVO[8];
		batchVO.setFieldInfo(fields);
		batchVO.setTableName("SYS_ROLE_INFO");
		batchVO.setOperDesc("批量导入角色");
		batchVO.setOtherFieldLength(5);

		/**
		 * ROLE_ID 角色ID-- ROLE_NAME 角色名称 status 状态-- is_lock-- ST -- system_ID
		 * LP_BRCODE-- flowNODE_CODE
		 * */
		fields[0] = new TableFieldInfoVO();
		fields[0].setFieldsName("ROLE_NAME");
		fields[0].setFieldsDesc("角色名称");
		fields[0].setRegex(".{1,30}");
		fields[0].setLimitDesc("“角色名称”要求长度小于30个字符，不能为空");
		
		fields[1] = new TableFieldInfoVO();
		fields[1].setFieldsName("SYSTEM_ID");
		fields[1].setFieldsDesc("子系统编号");
		fields[1].setRegex("[0-9]{2}");
		fields[1].setLimitDesc("“子系统编号”填写正确编码");

		fields[2] = new TableFieldInfoVO();
		fields[2].setFieldsName("FLOWNODE_CODE");
		fields[2].setFieldsDesc("流程节点代码");
		//fields[2].setRegex(".{1,10}");
		//fields[2].setLimitDesc("“流程节点代码”填写正确编码");

		/* 设置非文件字段信息 */

		/* 设置非文件字段信息 */
		fields[3] = new TableFieldInfoVO();
		fields[3].setFieldsName("ROLE_ID");
		/*Iterator it = DAOUtils.getHQLDAO().queryByQL(
				"select max(id) from RoleInfo");
		int id = 100;
		if (it.hasNext()) {
			Number num = (Number) it.next();
			id = num.intValue() + 1;
		}*/
		fields[3].setFieldValue("");

		fields[4] = new TableFieldInfoVO();
		fields[4].setFieldsName("STATUS");
		fields[4].setFieldValue("1");

		fields[5] = new TableFieldInfoVO();
		fields[5].setFieldsName("IS_LOCK");
		fields[5].setFieldValue("0");

		fields[6] = new TableFieldInfoVO();
		fields[6].setFieldsName("ST");
		fields[6].setFieldValue("4");

		fields[7] = new TableFieldInfoVO();
		fields[7].setFieldsName("LP_BRCODE");
		fields[7].setFieldValue(globalInfo.getLpBrcode());

	}
}
