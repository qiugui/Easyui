<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%= basePath%>">
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function(){
		$('#files').combobox({
			url : '${pageContext.request.contextPath}/fileupload/listAllFiles.do',
			valueField:'id',    
		    textField:'fileName',
		    width : 200
		});
		
		
		
		$('#downloadbtn').click(function(){
			var fileName=$('#files').combobox('getText');
			fileName=encodeURIComponent(fileName);
			if (fileName==''){
				$.messager.show ({
					title:'提示信息',
					msg : '请选择一个文件！'
				});
			} else {
				window.location.href='${pageContext.request.contextPath}/fileupload/download.do?fileName='+fileName;
				
			}
			
		});
		
	});
	
	
</script>
<title>Insert title here</title>
</head>
<body>

选择模版<select id="files" class="easyui-combobox" ></select><br/>
<a id="downloadbtn" class="easyui-linkbutton">下载</a>
</body>
</html>