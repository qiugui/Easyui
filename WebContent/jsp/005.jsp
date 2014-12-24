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
		
		$('#tt').datagrid({
			url:'${pageContext.request.contextPath}/user/showall.do',
			idField:'id',
			fitColumns:true,
			columns:[[
				{
					field:'id',
					title:'Id号',
					width:50,
					align:'center'
				},{
					field:'username',
					title:'用户名',
					width:100,
					align:'center'
				},{
					field:'password',
					title:'密码',
					width:100,
					align:'center'
				},{
					field:'email',
					title:'电子邮箱',
					width:150,
					align:'center'
				},{
					field:'birthday',
					title:'出生日期',
					width:150,
					formatter: function(value,row,index){
						if(value){
							return getLocalTime(value.time);
						}else{
							return value;
						}
					},

					align:'center'
				},{
					field:'cityId',
					title:'所属城市编号',
					width:100,
					formatter: function(value,row,index){
						if(value==1){
							return '北京';
						}else if(value==2){
							return '深圳';
						}else if(value==3){
							return '上海';
						}else if(value==4){
							return '广州';
						}else {
							return '备注：用户未填写';
						} 
						/* var str='';
						$.ajax({
							type:'post',
							url:'${pageContext.request.contextPath}/user/showcity.do',
							cache:false,
							async:false, //将ajax的异步请求更改成同步请求
							data:{id:value},
							dataType:'json',
							success:function(result){
								str=result.name;
							}			
						});
						return str; */
					},
					align:'center'
				}          
			]]
			
			
			
		});
		
		
		
	});
	
	function getLocalTime(nS) {     
		   return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');     
		};
</script>


<title>Insert title here</title>
</head>
<body>
	<table id="tt" fit=true></table>
</body>
</html>