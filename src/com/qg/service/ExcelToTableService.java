package com.qg.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.qg.dao.entity.ExcelToTable;

@Service("excelToTableService")
@Transactional
public class ExcelToTableService {

	@Resource(name = "excelToTable")
	ExcelToTable excelToTable;

	public void eTt(String filePath,String suffix) throws Exception {
		excelToTable.eTT(filePath,suffix);
	}
}
