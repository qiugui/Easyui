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
		
		
		$("#dialog").dialog({
			title:'对话框',
			toolbar:[{
				text:'增加',
				iconCls:'icon-add',
				handler:function(){
					console.info('点击增加按钮');
				}
			},{
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					$.messager.show({
						title:'点击了toolbar',
						msg:'点击了修改'
					});
				}
			}],
			
			
			buttons:[{
				text:'确认',
				iconCls:'icon-ok',
				handler:function(){
					$.messager.show({
						title:'点击了按钮',
						msg:'确认'
					});
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$.messager.show({
						title:'点击了按钮',
						msg:'取消'
					});
				}
			}]
			
		
		});
		
	});

</script>


<title>Insert title here</title>
</head>
<body>
<div id="dialog" style="height:300px;width:300px" ></div>
</body>
</html>