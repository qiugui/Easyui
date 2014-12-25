
package com.huateng.uniform.excel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.huateng.ebank.framework.util.TopupsProperties;
import com.huateng.uniform.excel.batchimport.BaseUpload;
import com.huateng.uniform.excel.batchimport.BatchImportCfg;
import com.huateng.uniform.excel.batchimport.BatchImportService;
import com.huateng.uniform.utills.DataMyUtil;



/**
 * author: kin wong
 *
 * class desc:批量导入统一控制器（黑白名单、第三方网址等名单导入、模板下载）
 */
public class BatchImportController extends MultiActionController {
	protected Logger logger = Logger.getLogger(getClass());
	
	protected String result;//结果返回页面
	protected CommonsMultipartResolver multipartResolver;//文件上传
	protected BatchImportService batchImportService;//批量导入服务
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public void setMultipartResolver(CommonsMultipartResolver multipartResolver) {
		this.multipartResolver = multipartResolver;
	}
	
	public void setbatchImportService(BatchImportService batchImportService) {
		this.batchImportService = batchImportService;
	}

	/**
	 * <b>method desc:接收页面请求，上传文件并调用后台进行导入处理</b>
	 * <br>
	 * method detail:操作流程如下：
	 * <br> 1、封装页面返回对象
	 * <br> 2、接收文件信息，验证文件和上传的文件路径
	 * <br> 3、上传文件至服务器
	 * <br> 4、调用后台对应的DAO
	 * <br> 5、删除文件，返回结果
	 * 
	 * @param request 页面请求对象
	 * @param reponse 响应页面对象
	 * @return 页面数据模型对象
	 * @throws Exception 异常
	 */
	public ModelAndView uploadImport(HttpServletRequest request,
			HttpServletResponse reponse) throws Exception {
		File uploadFile = null;
		String f =request.getQueryString();
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest mrequest = resolver.resolveMultipart(request);
		String fileflag = mrequest.getParameter("fileflag");
		// 封装页面返回对象
		List errorList = new ArrayList();
		ModelMap mmap = new ModelMap(); 
		mmap.addObject("errors", errorList);
		ModelAndView modelAndView = new ModelAndView(result,mmap);
		
		try {
			//验证上传的文件大小限制
			mrequest.setCharacterEncoding("UTF-8");
			MultipartFile file = mrequest.getFile("uploadFile");
			if(file.getSize() > multipartResolver.getFileUpload().getSizeMax()){
				errorList.add("文件大小超过导入限制");
				return modelAndView;
			}
			
			//验证上传文件路径（是否存在，是否符合格式）
			String uploadPath =TopupsProperties.getValue("excel_upload_path");//文件路径
			String path=uploadPath;//String path = new String(uploadPath.getBytes("iso8859-1"), "utf-8");//文件路径
			if (!new File(path).exists()) {
				new File(path).mkdir();
			}
//			else if (path.endsWith("/")) {
//				path = path.substring(0, path.length() - 1);
//			} 
			
			//上传文件到指定的文件夹（用时间作为文件名保存防止文件重名）
			String currentTime = DataMyUtil.getFullDateTime();
			String fullFileName = path + currentTime + ".xls";//String fullFileName = path + "/" + currentTime + ".xls";
			uploadFile = new File(fullFileName);
			file.transferTo(uploadFile);
			
			//通过反射实例化详细信息
			String className = BatchImportCfg.keymap.get(fileflag);
			if(className != null && !"".equals(className)){
				Class c = Class.forName (className);
				BaseUpload upload = (BaseUpload)c.newInstance();
				batchImportService.setBatchVO(upload.getBatchVO());
				
				errorList.addAll(batchImportService.importFile(fullFileName));
			}
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
			errorList.add("没有找到对应的处理类：" + ce.toString());
		} catch (Exception e) {
			e.printStackTrace();
			errorList.add("系统内部出错，错误信息：" + e.toString());
		} finally {
			if (uploadFile != null) {
				uploadFile.delete();
			}
		}
		
		return modelAndView;
	}
	
	/**
	 * <b>method desc:下载模板文件</b>
	 * <br/>
	 * method detail:
	 * 
	 * @param request 页面请求对象
	 * @param response 响应页面对象
	 * @return 页面数据模型对象
	 * @throws Exception 异常
	 */
	public ModelAndView downloadTemp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 封装页面返回对象
		List errorList = new ArrayList();
		ModelMap mmap = new ModelMap(); 
		mmap.addObject("errors", errorList);
		ModelAndView modelAndView = new ModelAndView(result,mmap);
		
		
		String fileflag = request.getParameter("fileflag");
		//String downloadPath ="";//文件路径
		//String downloadPath = getServletContext().getRealPath("/")+"fpages\\excel\\modelexcel\\";
		String downloadPath = TopupsProperties.getValue("excel_download_path");
		String fileName = fileflag+".xls";
		
//		if (downloadPath.endsWith("/")) {
//			downloadPath = downloadPath.substring(0, downloadPath.length() - 1);
//		}
		
		StringBuilder sb = new StringBuilder(downloadPath);
		sb.append(fileName);
		
		File file = new File(sb.toString());
		//System.out.println(file.getCanonicalPath());
		logger.info(sb.toString());
		if (!file.exists()){
			errorList.add("模板文件不存在;");
		}else{
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;

			try {
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-disposition","attachment;filename="
								+ URLEncoder.encode(fileName, "UTF-8"));// 进行编码

				bis = new BufferedInputStream(new FileInputStream(sb.toString()));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (Exception e) {
				errorList.add("系统内部出错：" + e.toString());
			} finally {
				try {
					if (bis != null) {
						bis.close();
					}
					if (bos != null) {
						bos.close();
					}
				} catch (IOException e2) {
					errorList.add("系统内部出错：" + e2.toString());
				}
			}
		}
		
		if(errorList.size() > 0)
			return modelAndView;
		return null;
	}
}