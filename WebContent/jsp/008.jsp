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


var flag='';


$(function(){
	

	$('#tree').tree({
	
		//发送异步url请求，还会携带一个id请求
		url:'${pageContext.request.contextPath}/resource/getTree.do',
		dnd:true,
		onDrop:function(target,source,point){
			var target=$('#tree').tree('getNode',target);
			/* console.info(target);
			console.info(source);
			console.info(point); */
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/resource/changelevel.do',
				data:{
					targetId:target.id,
					sourceId:source.id,
					point:point
				},
				dataType:'json',
				success:function(result){
					$.messager.show({
						title:'提示信息',
						msg:'成功'	
					});
				}
				
				
			});
			
		},
		onContextMenu:function(e,node){
			e.preventDefault();
			$('#tree').tree('select',node.target);
			$('#mymenu').menu('show',{
				left:e.pageX,
				top:e.pageY
			})
		}
	
	});
	
	$('#savebtn').click(function(){
		
		if (flag=='append'){
			var node=$('#tree').tree('getSelected');
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/resource/save.do',
				cache:false,
				data:{
					parentId:node.id,
					name:$('#myform').find('input[name=name]').val(),
					url: $('#myform').find('input[name=url]').val()
				},
				dataType:'json',
				success:function(result){
					//当选中的是叶子节点时，更新其父节点，当选择是非叶子节点，只更新当前节点
					if($('#tree').tree('isLeaf',node.target)){
						var parent=$('#tree').tree('getParent',node.target);
						$('#tree').tree('reload',parent.target);
					} else {
						$('#tree').tree('reload',node.target);
					}
					
					
					$.messager.show({
						title:'提示信息',
						msg:result.msg
					});
				}	
			});
			$('#mydialog').dialog('close');
		}else{
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/resource/update.do',
				cache:false,
				data:{
					id:$('#myform').find('input[name=id]').val(),
					name:$('#myform').find('input[name=name]').val(),
					url: $('#myform').find('input[name=url]').val()
				},
				dataType:'json',
				success:function(result){
					var node=$('#tree').tree('getSelected');
					var parent=$('#tree').tree('getParent',node.target);
					//刷新节点一定是父级节点
					$('#tree').tree('reload',parent.target);
					$.messager.show({
						title:'提示信息',
						msg:result.msg
					});
				}
			});
			$('#mydialog').dialog('close');
		}
		
		
		
	});
	
	$('#cancelbtn').click(function(){
		$('#mydialog').dialog('close');
	});
	
});

function append(){
	flag='append';
	$('#myform').form('clear');
	$('#mydialog').dialog('open');
	
}

function edit(){
	flag='edit';
	var node=$('#tree').tree('getSelected');
	$('#myform').form('clear');
	$('#myform').form('load',{
		id:node.id,
		name:node.text,
		url:node.attributes.url
	});
	$('#mydialog').dialog('open');
}

function shanchu(){
	$.messager.confirm('提示信息','确认删除吗？',function(r){
		if(r){
			var node=$('#tree').tree('getSelected');
			
			$.post('${pageContext.request.contextPath}/resource/delete.do',{id:node.id},function(result){
				$('#tree').tree('remove',node.target);
				$.messager.show({
					title:'提示信息',
					msg:result.msg
				});
			},'json');
		} else {
			return;
		}
		
	});
}

</script>

<title>Insert title here</title>
</head>
<body>
<ul id="tree"></ul>
<div title="权限菜单" id="mydialog" class="easyui-dialog" style="width:300px;" closed=true>
	<form id="myform" method="post">
		<input type="hidden" name="id">
		<table>
			<tr>
				<td>权限名称</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>url</td>
				<td><input type="text" name="url"></td>
			</tr>
			<tr align="center">
				<td colspan=2 >
					<a id="savebtn" class="easyui-linkbutton">保存</a>
					<a id="cancelbtn" class="easyui-linkbutton">取消</a>
				</td>
			</tr>
		</table>
		
	</form>
</div>

<div id="mymenu" class="easyui-menu" style="width:120px">
	<div onclick="append()" iconCls="icon-add">Append</div>
	<div onclick="edit()" iconCls="icon-edit">Edit</div>
	<div onclick="shanchu()" iconCls="icon-cancel">Delete</div>

</div>
</body>
</html>







