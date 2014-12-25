
package com.huateng.uniform.excel.tableobject;


import org.apache.poi.hssf.util.HSSFColor.GOLD;

import com.huateng.ebank.business.common.GlobalInfo;
import com.huateng.ebank.business.management.service.TlrCodeService;
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
public class TlrInfoItemUpload extends BaseUpload {
	public TlrInfoItemUpload() throws Exception{
		GlobalInfo globalInfo = GlobalInfo.getCurrentInstance();
		/*设置总体信息*/
		batchVO = new BatchImportInfoVO();
		TableFieldInfoVO[] fields = new TableFieldInfoVO[15];
		batchVO.setFieldInfo(fields);
		batchVO.setTableName("SYS_TLR_INFO");
		batchVO.setOperDesc("批量导入人员");
		batchVO.setOtherFieldLength(12);

		
		fields[0] = new TableFieldInfoVO();
		fields[0].setFieldsName("TLRNO");
		fields[0].setFieldsDesc("操作员编号");
		fields[0].setRegex(".{8,20}");
		fields[0].setLimitDesc("“操作员编号”要求长度大于8个字符小于20个字符");
		fields[0].setUnique(true);
		
		fields[1] = new TableFieldInfoVO();
		fields[1].setFieldsName("TLR_NAME");
		fields[1].setFieldsDesc("操作员名称");
		fields[1].setRegex(".{1,20}");
		fields[1].setLimitDesc("“操作员名称”要求长度大于1个字符小于20个字符");
		
		fields[2] = new TableFieldInfoVO();
		fields[2].setFieldsName("BRCODE");
		fields[2].setFieldsDesc("组织机构号");
		fields[2].setRegex("[0-9]{6}");
		fields[2].setLimitDesc("“组织机构号”要求长度6个字符");
		fields[2].setTableValue(true);
		fields[2].setIsTableSql("select count(*) from  SYS_BCTL  where LP_BRCODE='"+globalInfo.getLpBrcode()+"'  and BRCODE=?");
		
		/**
		 * 主键
		 * */
		fields[3] = new TableFieldInfoVO();
		fields[3].setFieldsName("TLRCODE");
		fields[3].setFieldValue("");
		/**
		 * 密码自动生成为 123456
		 * */
		fields[4] = new TableFieldInfoVO();
		fields[4].setFieldsName("PASSWORD");
		fields[4].setFieldValue("5EEA521FA1B12A12316E9EA48971336E");
		/**
		 * 状态
		 * */
		fields[5] = new TableFieldInfoVO();
		fields[5].setFieldsName("STATUS");
		fields[5].setFieldValue("0");
		
		fields[6] = new TableFieldInfoVO();
		fields[6].setFieldsName("FLAG");
		fields[6].setFieldValue("1");
		
		fields[7] = new TableFieldInfoVO();
		fields[7].setFieldsName("CREATE_DATE");
		fields[7].setFieldValue(DataMyUtil.getDate());
		
		fields[8] = new TableFieldInfoVO();
		fields[8].setFieldsName("PSWDERRCNT");
		fields[8].setFieldValue("0");
		
		fields[9] = new TableFieldInfoVO();
		fields[9].setFieldsName("TOTPSWDERRCNT");
		fields[9].setFieldValue("0");
		
		fields[10] = new TableFieldInfoVO();
		fields[10].setFieldsName("PASSWDENC");
		fields[10].setFieldValue("AES128");
		
		fields[11] = new TableFieldInfoVO();
		fields[11].setFieldsName("IS_LOCK");
		fields[11].setFieldValue("0");
		
		fields[12] = new TableFieldInfoVO();
		fields[12].setFieldsName("IS_LOCK_MODIFY");
		fields[12].setFieldValue("1");
		
		fields[13] = new TableFieldInfoVO();
		fields[13].setFieldsName("ST");
		fields[13].setFieldValue("4");
		
		fields[14] = new TableFieldInfoVO();
		fields[14].setFieldsName("LP_BRCODE");
		fields[14].setFieldValue(globalInfo.getLpBrcode());
		
	}
}
