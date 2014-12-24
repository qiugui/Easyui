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
	$(function() {
		$.extend($.fn.validatebox.defaults.rules,
				{
					fixlength : {
						validator : function(value, param) {
							return value.length == param[0];
						},
						message : '用户ID为一个字符'

					},

					fitlength : {
						validator : function(value, param) {
							return value.length >= param[0]
									&& value.length <= param[1];
						},
						message : '密码长度在3至6个之间'
					}
				});

		$('#birthday').datetimebox({
			required : true,
			missingMessage : '生日必填',
			editable : false

		});
		
		$('#btn').click(function(){
			$.messager.confirm('确认','确认增加一条记录吗？',function(r){
				if(r){
					$.ajax({
						type:'post',
						url:'${pageContext.request.contextPath }/user/save.do',
						data:$('#myform').serialize(),
						dataType:'text',
						success:function(result){
							var result=$.parseJSON(result);
							$.messager.show({
								
								title:result.title,
								msg:result.message
							});
						},
						error:function(result){
							var result=$.parseJSON(result);
							$.messager.show({
								title:result.title,
								msg:result.message
							});
						},
					});
				}
				
			});
		});

	});
</script>


<title>Insert title here</title>
</head>
<body>

	<div id="mydiv" class="easyui-panel" title="我的表单"
		style="height: 400px; width: 400px">
		<form id="myform" action="">
			<table>
				<tr>
					<td>用户ID</td>
					<td><input id="myIdinput" type="text" name="id"
						class="easyui-validatebox" required="true"
						missingMessage="用户ID必填！" validType="fixlength[1]" /></td>
				</tr>
				<tr>
					<td>用户名</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>用户密码</td>
					<td><input id="mypassword" type="password" name="password"
						class="easyui-validatebox" required="true"
						missingMessage="密码不能为空！" validType="fitlength[3,6]" /></td>
				</tr>
				<tr>
					<td>出生日期</td>
					<td><input id="birthday" type="text" name="birthday" /></td>
				</tr>
				<tr>
					<td>所属城市</td>
					<td><input id="city" name="city" class="easyui-combobox"
						url="${pageContext.request.contextPath }/user/showcity.do"
						valueField="cityId" textField="cityName" editable="false" /></td>
				</tr>
				<tr>
					<td>电子邮箱</td>
					<td><input type="text" name="email" class="easyui-validatebox"
						required="true" missingMessage="邮箱必填！" validType="email" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><a id="btn"
						class="easyui-linkbutton">提交</a></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>