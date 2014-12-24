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
	//alert('hello...nihao ');
	
	$(function(){
		
		/* $.messager.alert('我的消息','这是提示信息','warning'); */
		
		/* $.messager.confirm('确定框','确定吗？',function(r){
			if(r){
				$.messager.show({
					title:'确定结果',
					msg:'你点击确定了'
				});
			}else{
				$.messager.show({
					title:'确定结果',
					msg:'你点击取消了'
				});
			}
		}); */
		
		/* $.messager.prompt('输入框','请输入信息',function(val){
			if(val){
				$.messager.show({
					title:'确定结果',
					msg:'你输入的结果是'+val
				});
			}
		}); */
		
		/* $.messager.confirm('确定框','确定显示进度条吗？',function(r){
			if(r){
				$.messager.progress({
					title:'进度条',
					msg:'进度条实例',
					text:'加载中...',
					interval:500
					
				});
			}else{
				$.messager.show({
					title:'确定结果',
					msg:'你点击取消了',
					showType:'show',
					style:{
						right:'',
						top:document.body.scrollTop+document.documentElement.scrollTop,
						bottom:''
					}
	
				});
			}
		}); */
		
	});
	
	
</script>
<title>Insert title here</title>
</head>
<body>

</body>
</html>