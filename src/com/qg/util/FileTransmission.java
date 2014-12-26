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

/** 
* @ClassName: FileTransmission 
* @Description: 文件传输类，包括文件的上传与下载 
* @author qiugui 
* @date 2014年12月24日 下午4:38:00 
*  
*/ 
@Component("fileTransmission")
public class FileTransmission {

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	@Resource(name = "excelToTableService")
	ExcelToTableService excelToTableService;

	/**   
	 * @Title: uploadFile   
	 * @Description: 文件上传并导入数据库的方法   
	 * @param file
	 * @param path
	 * @return        
	 */
	 
	public String uploadFile(MultipartFile file, String path,String fileflag) {

			// 若文件目录不存在，则创建文件目录
			if (!new File(path).exists()) {
				new File(path).mkdir();
			}
			
			// 以系统当前时间作为上传文件的文件名
			Date date = new Date();
			String fileName = simpleDateFormat.format(date);
			// 获取上传文件的后缀名
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			// 拼接上传文件的全路径&文件名
			String filePath = path + fileName + suffix;
			
			try {
				// 将文件上传至服务器指定文件夹
				file.transferTo(new File(filePath));

				excelToTableService.eTt(filePath,suffix,fileflag);

			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
			
			return "success";

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
