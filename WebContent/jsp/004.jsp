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
	$('a[title]').click(function(){
		var src=$(this).attr('title');
		var title=$(this).html();
		if($('#tt').tabs('exists',title)){
			$('#tt').tabs('select',title);
		} else {
			$('#tt').tabs('add',{
				title:title,
				content:'<iframe  src='+src+' height=100% width=100% frameborder=0px ></iframe>',
				closable:true
			});
		}
	});
});


</script>

<title>Insert title here</title>
</head>
<body>
	<div id="cc" class="easyui-layout" fit=true>
		<div data-options="region:'north',split:false,align:'center'"
			style="height: 50px;" align="center">
			<font size=6 style="color: black;">QiuGui</font>
		</div>

		<div data-options="region:'south',split:false" style="height: 25px;"
			align="center">底部信息</div>

		<div data-options="region:'west',title:'导航菜单'" style="width: 200px;">
			<div id="aa" class="easyui-accordion"  style="position: absolute; top:27px; left:0px;right:0px; bottom:0px">
				<div title="系统维护" iconCls="icon-edit" selected=true
					style="overflow: auto; padding: 10px;">
					<a title="${pageContext.request.contextPath }/jsp/005.jsp">用户列表</a><br />
					<a title="${pageContext.request.contextPath }/jsp/002.jsp">权限管理</a><br />

				</div>
				<div title="用户管理" iconCls="icon-reload" style="padding: 10px;">content2</div>
				<div title="权限管理">content3</div>
			</div>
		</div>

		<div region="center" >
			<div id="tt" class="easyui-tabs" fit=true border=false>

			</div>

		</div>

	</div>

</body>
</html>