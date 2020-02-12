<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../baseJSP.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10,IE=edge,chrome=1"/>
<style>
.tableBasic th{text-align:right;}
</style>

<script type="text/javascript" src="${baseStatic}js/jquery-ztree/3.5.12/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${baseStatic}js/jquery-ztree/3.5.12/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="${baseStatic}js/jquery-ztree/3.5.12/js/jquery.ztree.exedit-3.5.min.js"></script>
<script type="text/javascript" src="${baseStatic}js/jquery-ztree/3.5.12/js/jquery.ztree.exhide-3.5.min.js"></script>
<link type="text/css" rel="stylesheet" href="${baseStatic}js/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.css"/>

<link type="text/css" rel="stylesheet" href="${baseStatic}css/public.css"/>

<script type="text/javascript">
var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};


	layui.config({
		base: '${baseStatic}layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['jquery','index'],function() {
		$ = layui.jquery;//初始化jquery

		var loading = layer.msg('正在加载...', {icon: 16, shade: 0.2, time: 0, offset: 'auto'});
		$.ajax({
			type:"get",
			url:"${basePath}rolePms/getRolePmsList",
			data:{'roleId': $('#roleId').val()},
			dataType:"json",
			success:function(data) {
				$.fn.zTree.init($("#treeDemo"), setting, data.permissionList);
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.expandAll(true);

				layer.close(loading);
				$.each(data.relationList, function (index, item) {
					zTree.checkNode( zTree.getNodeByParam('id', item.permissionId ), true, false );
				});
			}
		});


		//绑定保存按钮事件
		$('#btnsave').bind('click', function() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
					checkedNodes = zTree.getCheckedNodes(true),
					permissionIds = "";
			$.each(checkedNodes, function (index, item) {
				permissionIds += permissionIds == "" ? item.id : "," + item.id;
			});
			if(permissionIds==""){
				// $.jBox.tip("您没有选中任何菜单项！","error");
				layer.msg('您没有选中任何菜单项！', {offset: 'auto'});
				return false;
			}
			// $.jBox.tip("正在保存......","loading");
			var data = {'permissionIds' : permissionIds, 'roleId' : $('#roleId').val()};
			$.post('${basePath}rolePms/doRoleGrant', data, function (result) {
				if(result == "success"){
					layer.msg('保存成功', {offset: 'auto'});
					window.setTimeout(function(){
						window.location.assign("${basePath}role/findRoleList");
					},1500);
				}else{
					layer.msg(result, {offset: 'auto'});
				}
			});
		});

	});


$(document).ready(function() {
	
	//绑定取消按钮事件
	$('#btncancel').bind('click', function () {
		window.location.assign("${basePath}role/findRoleList");
	});
});

</script>
</head>
<body>


  	<!-- ------------------------------------ 正文代码开始 start ------------------------------------ -->
  	<div id="dcMain" style="margin-left:0px;">
	<!-- 业务代码 start -->
	<div id="index" class="mainBox">

		 <div class="indexBox" >
				<input type="hidden" id="roleId" name="roleId" value="${roleId }" />
				<ul id="treeDemo" class="ztree" style="margin-bottom: 10px;"></ul>
				<button class="btn" id="btnsave" style="margin-left: 10px;">确定</button>
				 <a href="${basePath}role/findRoleList">
					 <button type="button" class="btn" style="margin-left: 10px;">
						 返回
					 </button>
				 </a>
			</div>
		</div>

	</div>
	<!-- 业务代码 end -->
  	<!-- ------------------------------------ 正文代码开始 end ------------------------------------ -->
  	
</body>
</html>