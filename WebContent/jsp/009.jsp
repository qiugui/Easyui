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
	
	flag="";
	
	$(function(){
		$('#tt').treegrid({
			url:'${pageContext.request.contextPath}/org/getTreegrid.do',
			title:'机构组织列表',
			idField:'id',   //数据表格要有主键
			treeField:'name',   //treegrid树形主键text
			collapsible:true,
			width:1000,
			height:600,
			columns:[[{
				title:'机构名称',
				field:'name',
				width:200
			},{
				title:'机构人数',
				field:'count',
				align:'center',
				width:100
			},{
				title:'机构负责人',
				field:'principal',
				align:'center',
				width:100
			},{
				title:'机构描述',
				field:'description',
				align:'center',
				width:500
			}]],
			
			onContextMenu:function(e,row){
				e.preventDefault();
				$('#tt').treegrid('select',row.id);
				$('#myMenu').menu('show',{
					left:e.pageX,
					top:e.pageY
				});
			}
		});	
		
		$('#btnsave').click(function(){
			if ( flag=='append') {
				var node=$('#tt').treegrid('getSelected');
				$.ajax({
					type:'post',
					url:'${pageContext.request.contextPath}/org/save.do',
					cache:false,
					dataType:'json',
					data:{
						parent_id:node.id,						
						name:$('#myform').find('input[name=name]').val(),
						count:$('#myform').find('input[name=count]').val(),
						discription:$('#myform').find('textarea[name=description]').val(),
						principal:$('#myform').find('input[name=principal]').val()
					},
					success:function(result){
						var parent=$('#tt').treegrid('getParent',node.id);
						$('#tt').treegrid('reload',parent.id);
						$.messager.show({
							title:'提示信息',
							msg:result.msg
						});
					}
					
				});
				$('#myDialog').dialog('close');
			}else{
				
				
			}
			
		});
		
		
		$('#btncancel').click(function(){
			$('#myDialog').dialog('close');
		});
	});

	function append(){
		flag='append';
		$('#myDialog').dialog({
			title:'新增管理机构'
		});
		$('#myform').form('clear');
		$('#myDialog').dialog('open');
		
	}
	
	function edit(){
		
		
	}
	
	function shanchu(){
		
		
	}


</script>

<title>Insert title here</title>
</head>
<body>
	<table id="tt"></table>
	<div id="myDialog" class="easyui-dialog" style="width:280px;height:250px;" modal=true draggable=true closed=true>
		<div id="mytab" title="机构信息" class="easyui-tabs" plain=true fit=true >
			<div title="机构信息" fit=true>
				<form id="myform" method="post">
					<table>
						<tbody>
							<tr>
								<td>机构名称</td>
								<td><input type="text" name="name" class="easyui-validatebox" required=true missingMessage="机构名称必填"></td>
							</tr>
							<tr>
								<td>机构人数</td>
								<td><input type="text" name="count" class="easyui-numberbox" data-options="required:true,min:1,max:100,precision:0" missingMessage="人数在1到100之间"></td>
							</tr>
							<tr>
								<td>机构描述</td>
								<td>
								<textarea cols="21" rows="3" name="description" class="easyui-validatebox" required=true missingMessage="填写描述信息" ></textarea>
								</td>
							</tr>
							<tr>
								<td>机构负责人</td>
								<td><input type="text" name="principal" class="easyui-validatebox" required=true missingMessage="机构负责人必填"></td>
							</tr>
							<tr align="center">
								<td colspan="2">
									<a id="btnsave" class="easyui-linkbutton">确认</a>
									<a id="btncancel" class="easyui-linkbutton">取消</a>
								</td>	
							</tr>
						</tbody>
					</table>
				</form>
		
		</div>
	</div>
	<div id="myMenu" class="easyui-menu" style="width:50px">
		<div onClick="append()" >添加</div>
		<div onClick="edit()" >修改</div>
		<div onClick="shanchu()" >删除</div>
	</div>
</body>
</html>