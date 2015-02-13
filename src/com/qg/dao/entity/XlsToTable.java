 package com.qg.dao.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Repository;

import com.qg.dao.SqlUtil;
import com.qg.vo.ImportFileInfoVO;
import com.qg.vo.TableFieldInfoVO;
/** 
* @ClassName: XlsToTable 
* @Description: 将.xls格式的Excel表格转化成数据库表 
* @author qiugui 
* @date 2014年12月26日 上午10:34:05 
*  
*/ 
@Repository(value="xlsToTable") 
public class XlsToTable extends SqlUtil {
	
	/**   
	 * @Title: excelToTable   
	 * @Description: 将Excel转成数据库的表   
	 * @param filePath        
	 * @throws FileNotFoundException 
	 */
	 
	public void excelToTable(String filePath,ImportFileInfoVO importFileInfoVO) throws Exception{
		//很据文件路径获得输入流
		InputStream is = new FileInputStream(new File(filePath));
		//获取数据表格
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		//获取第一个sheet
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		
		//获取表的所有字段
		final TableFieldInfoVO[] fields=importFileInfoVO.getFieldsInfo();
		//得到文件行数
		final int rowSize=hssfSheet.getLastRowNum();
		//获得上传文件对应的数据库表名
		final String tableName = importFileInfoVO.getTableName();
		//字段名
		String fieldName = ""; 
		String sql = null;
		String insertData = "";
		
		for (int i=1;i<fields.length;i++) {
			fieldName +=fields[i].getFieldName()+",";
		}

		fieldName=fieldName.substring(0, fieldName.length()-1);
		// 循环行Row
		for (int rowNum = 1; rowNum <= rowSize; rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow == null) {
				continue;
			}

			// 循环列Cell
			for (int cellNum = 1; cellNum < hssfRow.getLastCellNum(); cellNum++) {
				HSSFCell hssfCell = hssfRow.getCell(cellNum);
				if (hssfCell == null) {
					continue;
				}

				String content = this.getValue(hssfCell);
				System.out.println("  行数" + rowNum
						+ "  列数" + cellNum + "  单元格内容" + content);
					
					insertData += "'" + content + "',";

			}

			insertData=insertData.substring(0, insertData.length()-1);
			sql = "INSERT INTO " + tableName
					+ " ("+fieldName+") VALUES " + " ("
					+ insertData + ");";
			System.out.println(sql);
			this.getJdbcTemplate().execute(sql);
			insertData = "";

		}
		//关闭流
		if (is != null) {
			is.close();
		}
	}
	
	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
	
}

 