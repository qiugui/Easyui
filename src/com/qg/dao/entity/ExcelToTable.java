package com.qg.dao.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.qg.dao.SqlUtil;

/** 
* @ClassName: ExcelToTable 
* @Description: 将Excel表格转化成数据库中的表 
* @author qiugui 
* @date 2014年12月24日 下午3:19:20 
*  
*/ 
@Repository("excelToTable")
public class ExcelToTable extends SqlUtil {

	@Resource(name = "jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	public void eTT(String filePath, String suffix) throws Exception {

		InputStream is = new FileInputStream(new File(filePath));
		String sql = null;
		String insertData = "";

		if (".xls".equals(suffix)) {
			System.out.println("进入" + suffix);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			/**
			 * 将.xls格式的Excel文件转换成数据库中的表
			 */
			// 循环工作表sheet
			// for (int numSheet = 0; numSheet <
			// hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

			// if (hssfSheet == null) {
			// continue;
			// }

			// 利用sheet的名字作为表名
			String tableName = hssfSheet.getSheetName();

			sql = "DROP TABLE IF EXISTS " + tableName + ";";
			jdbcTemplate.execute(sql);
			sql = "CREATE TABLE "
					+ tableName
					+ " (id int(11) primary key,name varchar(20),sex varchar(7),"
					+ "age int(11),address varchar(128));";
			jdbcTemplate.execute(sql);

			// 循环行Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}

				// 循环列Cell
				for (int cellNum = 0; cellNum < hssfRow.getLastCellNum(); cellNum++) {
					HSSFCell hssfCell = hssfRow.getCell(cellNum);
					if (hssfCell == null) {
						continue;
					}

					String content = getValue(hssfCell);
					System.out.println("  sheet数" + rowNum + "  行数" + rowNum
							+ "  列数" + cellNum + "  单元格内容" + content);
					if (cellNum != hssfRow.getLastCellNum() - 1) {
						if (cellNum == 0 || cellNum == 3) {
							insertData += content + ",";
						} else {
							insertData += "'" + content + "',";
						}

					} else {
						insertData += "'" + content + "'";
					}
				}

				sql = "INSERT INTO " + tableName
						+ " (id,name,sex,age,address) VALUES " + " ("
						+ insertData + ");";
				System.out.println(sql);
				jdbcTemplate.execute(sql);
				insertData = "";

			}
			// }

		} else {

			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			System.out.println("进入" + suffix);
			/**
			 * 将.xlsx格式的Excel文件转换成数据库中的表
			 */
			// 循环工作表sheet
			// for (int numSheet = 0; numSheet <
			// xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

			// if (xssfSheet == null) {
			// continue;
			// }

			// 利用sheet的名字作为表名
			String tableName = xssfSheet.getSheetName();

			sql = "DROP TABLE IF EXISTS " + tableName + ";";
			jdbcTemplate.execute(sql);
			sql = "CREATE TABLE "
					+ tableName
					+ " (id int(11) primary key,name varchar(20),sex varchar(7),"
					+ "age int(11),address varchar(128));";
			jdbcTemplate.execute(sql);

			// 循环行Row
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
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

					String content = getValue(xssfCell);
					System.out.println("  sheet数" + rowNum + "  行数" + rowNum
							+ "  列数" + cellNum + "  单元格内容" + content);
					if (cellNum != xssfRow.getLastCellNum() - 1) {
						if (cellNum == 0 || cellNum == 3) {
							insertData += content + ",";
						} else {
							insertData += "'" + content + "',";
						}

					} else {
						insertData += "'" + content + "'";
					}
				}

				sql = "INSERT INTO " + tableName
						+ " (id,name,sex,age,address) VALUES " + " ("
						+ insertData + ");";
				System.out.println(sql);
				jdbcTemplate.execute(sql);
				insertData = "";

			}
			// }
		}

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
	
	/*  excel中的字符转换
	 * 
	 * private String getValue(HSSFCell hssfCell) {

		String str = "";
		switch (hssfCell.getCellType()) {
		case HSSFCell.CELL_TYPE_BLANK:
			str = "";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			str = Boolean.toString(hssfCell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(hssfCell)) {
				str = String.valueOf(hssfCell.getDateCellValue());
			} else {
				hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
				String temp = hssfCell.getStringCellValue();
				if (temp.indexOf(".") > -1) {
					str = String.valueOf(new Double(temp)).trim();
				} else {
					str = temp.trim();
				}
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:
			str = hssfCell.getStringCellValue().trim();
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			str = "";
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			str = hssfCell.getStringCellValue();
			if (str != null) {
				str = singleRow[n].replaceAll("#N/A", "").trim();
			}
			break;
		default:
			str = "";
			break;
		}
	}*/

	
	/*		使用jxl读取excel
	 * 
	 * // 定义单元格的内容
			Cell cell = null;
			// 拼接sql语句
			String sql = null;
			// 拼接表格一行的数据值
			String insertData = "";
			// 加载Excel文件
			InputStream fis = new FileInputStream(new File(filePath));
			// 得到Workbook
			Workbook workbook = Workbook.getWorkbook(fis);
			// 取得sheet，如果有多个sheet，利用wb.getSheets()方法得到sheet[]数组
			Sheet sheet = workbook.getSheet(0);
			// 利用sheet的名字作为表名
			sql = "DROP TABLE IF EXISTS " + sheet.getName() + ";/r/n";

			jdbcTemplate.execute(sql);

			sql = "CREATE TABLE " + sheet.getName()
					+ " (id int(11) primary key,name varchar(20),sex varchar(7),"
					+ "age int(11),address varchar(128));";

			jdbcTemplate.execute(sql);

			// 创建表

			// 循环取得表中内容
			for (int i = 1; i < sheet.getRows(); i++) {
				for (int j = 0; j < sheet.getColumns(); j++) {
					cell = sheet.getCell(j, i);
					String content=cell.getContents();
					if (j != sheet.getColumns() - 1) {
						if (j == 0 || j == 3) {
							insertData += content + ",";
						} else {
							insertData += "'" + content + "',";
						}

					} else {
						insertData += "'" + content + "'";
					}

				}
				sql = "INSERT INTO " + sheet.getName()
						+ " (id,name,sex,age,address) VALUES " + " (" + insertData
						+ ");";
				jdbcTemplate.execute(sql);
				insertData = "";
			}

			if (workbook != null) {
				workbook.close();
			}*/
}
