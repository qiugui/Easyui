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
 *  file desc：批量导入公共服务类文件
 */
package com.huateng.uniform.excel.batchimport;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.huateng.ebank.business.common.DAOUtils;
import com.huateng.ebank.business.management.IDService;
import com.huateng.ebank.business.management.service.RoleIdService;
import com.huateng.ebank.business.management.service.TlrCodeService;
import com.huateng.ebank.entity.dao.mng.BctlDAO;
import com.huateng.ebank.entity.dao.mng.ROOTDAO;
import com.huateng.ebank.entity.dao.mng.ROOTDAOUtils;
import com.huateng.ebank.entity.data.mng.Bctl;
import com.huateng.ebank.framework.exceptions.CommonException;
import com.nantian.ofpiwap.srv.FormatedJDBCTableDao;

/**
 * author: kin wong
 * 
 * class desc:批量导入公共服务类
 */
public class BatchImportService extends FormatedJDBCTableDao {
	private WritableWorkbook writeBook;// 可读写的excel实例
	private WritableSheet writeSheet;// 可读写的excel工作簿实例
	private Workbook book;// excel实例
	private Sheet sheet;// excel工作簿的实例

	private BatchImportInfoVO batchVO;// 批量数据的插入信息

	public void setBatchVO(BatchImportInfoVO batchVO) {
		this.batchVO = batchVO;
	}

	/**
	 * 导入数据文件
	 * 
	 * @param filePath
	 *            上传文件的路径
	 * @return 错误信息列表
	 */
	@SuppressWarnings("unchecked")
	public List importFile(String filePath) {
		List errors = new ArrayList();
		try {
			this.fileFormate(filePath);
			this.readFile(filePath);
			List l = this.validate(batchVO);
			if (l.size() > 0) {
				errors.addAll(l);
			} else {
				this.insertDB(batchVO);
			}
		} catch (Exception e) {
			errors.add("系统内部出错，错误信息：" + e.toString());
			e.printStackTrace();
		} finally {
			this.closeFile();
		}

		return errors;
	}

	/**
	 * <b>method desc:Excel文件的预处理</b> <br/>
	 * method detail:删除空行、删除空列
	 * 
	 * @param filePath
	 *            上传文件的路径
	 * @throws IOException
	 * @throws BiffException
	 * @throws WriteException
	 * 
	 */
	public void fileFormate(String filePath) throws BiffException, IOException,
			WriteException {
		File file = new File(filePath);
		book = Workbook.getWorkbook(file);
		writeBook = Workbook.createWorkbook(file, book);
		writeSheet = writeBook.getSheet(0);

		/* 删除空行 */
		int row = writeSheet.getRows();
		int column = writeSheet.getColumns();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (!"".equals(writeSheet.getCell(j, i).getContents().trim()))
					break;
				else if (j == column - 1) {
					writeSheet.removeRow(i);
					row--;
					i--;
				}
			}
		}

		/* 删除空列 */
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				if (!"".equals(writeSheet.getCell(i, j).getContents().trim()))
					break;
				else if (j == row - 1) {
					writeSheet.removeColumn(i);
					column--;
					i--;
				}
			}
		}

		writeBook.write();
		writeBook.close();
		book.close();
	}

	/**
	 * <b>method desc:读取XLS文件</b> <br/>
	 * method detail:
	 * 
	 * @param filePath
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public void readFile(String filePath) throws BiffException, IOException {
		File file = new File(filePath);
		this.book = Workbook.getWorkbook(file);
		this.sheet = book.getSheet(0);
	}

	/**
	 * <b>method desc:关闭文件</b> <br/>
	 * method detail:
	 * 
	 */
	public void closeFile() {
		if (book != null)
			book.close();
	}

	/**
	 * <b>method desc:验证文件行数、列数、列标题、数据内容等</b> <br/>
	 * method detail:分成一组，每当一组遇到错误时，返回，不再向下执行
	 * 
	 * @return 错误列表
	 */
	@SuppressWarnings("unchecked")
	public List validate(BatchImportInfoVO batchVO) {
		List errors = new ArrayList();

		/* 验证文件行数，不能为空或超过1000行 */
		int rowSize = sheet.getRows();// 得到文件行数
		if (rowSize <= 1) {
			errors.add("文件数据不能为空");
			return errors;
		} else if (rowSize > 1001) {
			errors.add("文件数据不能超过" + 1000 + "行");
			return errors;
		}

		/* 验证文件列数，文件标题及顺序 */
		int columnSize = sheet.getColumns();// 文件的列数
		Cell titleCell[] = sheet.getRow(0);
		TableFieldInfoVO fields[] = batchVO.getFieldInfo();
		if (columnSize != fields.length - batchVO.getOtherFieldLength()) {
			errors.add("文件列数不对");
		} else {
			for (int i = 0; i < columnSize; i++) {
				if (!titleCell[i].getContents().trim()
						.equals(fields[i].getFieldsDesc().trim())) {
					int column = i + 1;
					errors.add("第" + column + "列内容不对，应该是“"
							+ fields[i].getFieldsDesc() + "”");
				}
			}
		}
		if (errors.size() > 0) {
			return errors;
		}

		/* 验证数据内容是否符合格式 */
		for (int i = 1; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				/* 验证是否符合格式 */
				if (fields[j].getRegex() == null
						|| "".equals(fields[j].getRegex())) {
					continue;
				} else {
					StringBuffer err = new StringBuffer();
					String str = sheet.getCell(j, i).getContents().trim();
					if (!str.matches(fields[j].getRegex())) {
						err.append(fields[j].getLimitDesc());
						err.append(",");
					}
					if (!"".equals(err.toString())) {
						int row = i + 1;
						errors.add("第" + row + "行" + err.toString().trim());
					}
				}
			}
		}

		/* 验证文件中唯一约束字段是否存在相同的记录 */
		for (int i = 0; i < columnSize; i++) {
			if (fields[i].isUnique()) {
				Cell[] columnCell = sheet.getColumn(i);

				for (int j = 1; j < columnCell.length; j++) {
					for (int n = 1; n < j; n++) {
						if (columnCell[j].getContents().trim()
								.equals(columnCell[n].getContents().trim())) {
							StringBuffer err = new StringBuffer();
							int row1 = j + 1;
							int row2 = n + 1;
							err.append("第" + row1 + "行与第" + row2 + "行的“"
									+ sheet.getCell(i, 0).getContents().trim()
									+ "”字段数据不能重复;");
							errors.add(err);
							break;
						}
					}
				}
			}
		}

		/* 验证数据库中唯一约束字段是否存在相同的记录 */
		for (int i = 0; i < columnSize; i++) {
			if (fields[i].isUnique()) {
				Cell[] columnCell = sheet.getColumn(i);
				String sql = "select count(*) from " + batchVO.getTableName()
						+ " where " + fields[i].getFieldsName() + " = ?";

				for (int j = 1; j < columnCell.length; j++) {
					int count = getJdbcTemplate()
							.queryForInt(
									sql,
									new Object[] { columnCell[j].getContents()
											.trim() });
					if (count > 0) {
						StringBuffer err = new StringBuffer();
						int row = j + 1;
						err.append("第" + row + "行的“"
								+ sheet.getCell(i, 0).getContents().trim()
								+ "”字段已在数据库中存在，不能重复;");
						errors.add(err);
					}
				}
			}
		}
		/**
		 * 校验数据的正确性(表关联)
		 * */
		for (int i = 0; i < columnSize; i++) {
			if (fields[i].isTableValue()) {
				Cell[] columnCell = sheet.getColumn(i);
				String sql = fields[i].getIsTableSql(); //
				for (int j = 1; j < columnCell.length; j++) {
					int count = getJdbcTemplate()
							.queryForInt(
									sql,
									new Object[] { columnCell[j].getContents()
											.trim() });
					String s = columnCell[j].getContents().trim();
					// 数据字段要求在表中存在
					boolean a = s.length() != 0;
					if (count <= 0
							&& (columnCell[j].getContents().trim().length() != 0)) {
						StringBuffer err = new StringBuffer();
						int row = j + 1;
						err.append("第" + row + "行的“"
								+ sheet.getCell(i, 0).getContents().trim()
								+ "”字段的数据在数据库中不存在;");
						errors.add(err);
					}
				}
			}
		}
		return errors;
	}

	/**
	 * <b>method desc:将结果插入数据库</b> <br/>
	 * 
	 * @param batchVO
	 */
	public synchronized void insertDB(BatchImportInfoVO batchVO) {
		final TableFieldInfoVO[] fieldInfo = batchVO.getFieldInfo();
		final int rowSize = sheet.getRows();// 得到文件行数
		final int columnSize = sheet.getColumns();// 文件的列数
		final String tableName = batchVO.getTableName();
		String fieldName = "";
		String fieldRex = "";
		String sql = "";
		String blnUpBrcode = "";
		int blnNO = 0;
		int brcodeNO = 0;
		int adminBrcodeNO = 0;
		for (int i = 0; i < fieldInfo.length; i++) {
			fieldName += fieldInfo[i].getFieldsName() + ","; // 组装字段名
			fieldRex += "?,"; // 组装通配符
			if (fieldInfo[i].getFieldsName().equals("BLN_UP_BRCODE")
					&& tableName.equals("SYS_BCTL")) {
				blnNO = i;
			}
			if (fieldInfo[i].getFieldsName().equals("BRCODE")
					&& tableName.equals("SYS_BCTL")) {
				brcodeNO = i;
			}
			if (fieldInfo[i].getFieldsName().equals("BRCODE")
					&& tableName.equals("SYS_TLR_INFO")) {
				adminBrcodeNO = i;
			}
		}
		fieldName = fieldName.substring(0, fieldName.length() - 1);
		fieldRex = fieldRex.substring(0, fieldRex.length() - 1);
		sql += "insert into ";
		sql += tableName;
		if (tableName.equals("SYS_BCTL")) {
			sql += " (" + fieldName + ",BR_PATH )";
		} else if (tableName.equals("SYS_TLR_INFO")) {
			sql += " (" + fieldName + ",ADMIN_BRCODE)";
		} else {
			sql += " (" + fieldName + ")";
		}
		sql += " values ";
		if (tableName.equals("SYS_BCTL") || tableName.equals("SYS_TLR_INFO")) {

			sql += " (" + fieldRex + ",?)";
		} else {
			sql += " (" + fieldRex + ")";
		}

		final int blnNUM = blnNO;
		final int brcodeNUM = brcodeNO;
		final int adminBrcodeNUM = adminBrcodeNO;

		/* 批量插入数据库 */
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int row)
					throws SQLException {
				int k = 1;
				/**
				 * 导入本身具有的值
				 * */
				for (int j = 0; j < columnSize; j++) {
					if(sheet.getRow(row + 1).length-1>=j&&sheet.getRow(row + 1)[j].getType()!=CellType.EMPTY)
						ps.setString(k++, sheet.getRow(row + 1)[j].getContents());
					else
						ps.setString(k++,"");
				}
				/**
				 * 导入需要计算的值
				 * 
				 * */
				for (int i = columnSize; i < fieldInfo.length; i++) {
					// 是否主键
					ps.setString(k++, fieldInfo[i].getFieldValue());

				}
				/**
				 * BR_PATH 机构路径
				 * */
				if (blnNUM != 0) {
					ROOTDAO rootdao = ROOTDAOUtils.getROOTDAO();
					try {
						List<Bctl> bctlList = rootdao
								.queryByQL2List("from Bctl where brcode='"
										+ sheet.getRow(row + 1)[blnNUM]
												.getContents() + "'");
						String brPath = bctlList.get(0).getBrpath() + "."
								+ fieldInfo[brcodeNUM].getFieldValue();
						ps.setString(k++, brPath);
					} catch (CommonException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (adminBrcodeNUM != 0) {
					String brcode = sheet.getRow(row + 1)[adminBrcodeNUM]
							.getContents().trim();
					BctlDAO bctlDAO = DAOUtils.getBctlDAO();
					Bctl adminbctl = bctlDAO.findByAdminBrcode(brcode);
					ps.setString(k++, adminbctl.getBrcode());
				}
				if(tableName.equals("SYS_BCTL")){
					//机构主键值
					try {
						ps.setString(12, IDService.getInstance().getBrcodeID());
					} catch (CommonException e) {
						e.printStackTrace();
					}
				}else if(tableName.equals("SYS_TLR_INFO")){
					//操作员主键值
					try {
						ps.setString(4, TlrCodeService.getInstance().getTlrCode());
					} catch (CommonException e) {
						e.printStackTrace();
					}
				}else {
					//角色岗位主键值
					//Iterator it ;
					try {
						/*it = DAOUtils.getHQLDAO().queryByQL("select max(id) from RoleInfo");
						int id = 100;
						if (it.hasNext()) {
							Number num = (Number) it.next();
							id = num.intValue() + 1;
						}*/
						ps.setString(4, RoleIdService.getInstance().getRoleId());
						
					} catch (CommonException e) {
						e.printStackTrace();
					}
				}
			}

			public int getBatchSize() {
				return rowSize - 1;
			}
		});
	}
}
