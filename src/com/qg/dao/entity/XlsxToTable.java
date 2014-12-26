 package com.qg.dao.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import com.qg.dao.SqlUtil;
import com.qg.vo.ImportFileInfoVO;
import com.qg.vo.TableFieldInfoVO;
/** 
* @ClassName: XlsxToTable 
* @Description: 将.xlsx格式的Excel表格转化成数据库表 
* @author qiugui 
* @date 2014年12月26日 上午10:35:47 
*  
*/ 
@Repository(value="xlsxToTable") 
public class XlsxToTable extends SqlUtil {

	/**   
	 * @Title: excelToTable   
	 * @Description: 将Excel转成数据库的表   
	 * @param filepath        
	 * @throws Exception 
	 */
	 
	public void excelToTable(String filePath,ImportFileInfoVO importFileInfoVO) throws Exception{
		//很据文件路径获得输入流
		InputStream is = new FileInputStream(new File(filePath));
		//获取数据表格
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		//获取第一个sheet
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		
		//获取表的所有字段
		final TableFieldInfoVO[] fields=importFileInfoVO.getFieldsInfo();
		//得到文件行数
		final int rowSize=xssfSheet.getLastRowNum();
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
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if (xssfRow == null) {
				continue;
			}

			// 循环列Cell
			for (int cellNum = 0; cellNum < xssfRow.getLastCellNum(); cellNum++) {
				XSSFCell xssfCell = xssfRow.getCell(cellNum);
				if (xssfCell == null) {
					continue;
				}

				String content = this.getValue(xssfCell);
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
	private String getValue(XSSFCell xssfCell) {

		if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfCell.getNumericCellValue());
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}
}

 