<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<script type="text/javascript"
	src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.4.1/themes/icon.css" />
<script type="text/javascript"
	src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	
	$('#cc').combo({
		required:true,
		editable:false,
		width:100
	});
	$('#sp').appendTo($('#cc').combo('panel'));
	$('#sp input').click(function(){
		var v=$(this).val();
		var s=$(this).next('span').text();
		$('#cc').combo('setValue',v).combo('setText',s).combo('hidePanel');
	});
});
</script>
<title>Insert title here</title>
</head>
<body>
	<select id="cc"></select>
	<div id="sp">
		<div>选择一项</div>
		<input type="radio" name="lang" value="01"><span>JAVA</span><br /> <input type="radio" name="lang" value="02"><span>C++</span><br />
		<input type="radio" name="lang" value="03"><span>Linux</span><br /> <input type="radio" name="lang" value="04"><span>Basic</span><br />
		<input type="radio" name="lang" value="05"><span>Oracle</span><br />
	</div>
</body>
</html>