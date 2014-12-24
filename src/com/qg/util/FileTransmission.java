package com.qg.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.qg.service.ExcelToTableService;

@Component("fileTransmission")
public class FileTransmission {

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	@Resource(name = "excelToTableService")
	ExcelToTableService excelToTableService;

	public String uploadFile(MultipartFile file, String path) {
		if (!file.isEmpty()) {

			// 若文件目录不存在，则创建文件目录
			if (!new File(path).exists()) {
				new File(path).mkdir();
			}
			// 以系统当前时间作为上传文件的文件名
			Date date = new Date();
			String fileName = simpleDateFormat.format(date);
			// 获取上传文件的后缀名
			String suffix = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf("."));
			// 只允许上传Excel格式的文件
			if (".xlsx".equals(suffix) || ".xls".equals(suffix)) {
				// 拼接上传文件的全路径&文件名
				String filePath = path + fileName + suffix;
				// 将文件上传至服务器指定文件夹
				try {
					file.transferTo(new File(filePath));

					excelToTableService.eTt(filePath,suffix);

				} catch (Exception e) {
					e.printStackTrace();
					return "fail";
				}
				return "success";
			} else {
				return "fail1";
			}
		} else {
			return "fail2";
		}
	}

	public void downloadFile(HttpServletResponse response, String path) {
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			fis = new FileInputStream(new File(path));
			byte buff[] = new byte[1024];
			try {
				os = response.getOutputStream();
				int length = 0;
				while ((length = fis.read(buff)) > 0) {
					os.write(buff, 0, length);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}
	}
}