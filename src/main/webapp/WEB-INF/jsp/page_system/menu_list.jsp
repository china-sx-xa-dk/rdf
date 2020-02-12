<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../baseJSP.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>菜单管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet"  href="${baseStatic}treeTable/themes/vsStyle/treeTable.css" />
	<script type="text/javascript" src="${baseStatic}treeTable/jquery.treeTable.min.js" ></script>

	<script type="text/javascript">

		//初始化加载layui
		layui.config({
			base: '${baseStatic}layuiadmin/' //静态资源所在路径
		}).extend({
			index: 'lib/index' //主入口模块
		}).use(['jquery','index','form','laypage'],function(){
			$ = layui.jquery;//初始化jquery
			var form = layui.form;//初始化form组件
			var laypage = layui.laypage;//初始化分页组件

		});


		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 2}).show();
		});


		//删除用户
		function deleteBtnn(permissionId){
			layer.confirm('确定要删除该菜单及所有子菜单项吗？',{offset: 'auto'}, function(index) {
				window.location.assign("${basePath}permission/delete?permissionId="+permissionId);
			});
		}
	</script>
</head>

<body>

<div class="layui-fluid">
	<!-- ----------------------正文代码start---------------------- -->

	<%-- 功能导航栏 --%>
	<div class="tool_top">
		<div class="layui-inline">
			<a href="${basePath}permission/form">
				<button type="button" class="layui-btn layui-btn-normal" lay-text="新增菜单">
					<i class="layui-icon layui-icon-add-1"></i>新增
				</button>
			</a>
		</div>
	</div>
	<sys:message content="${message}"/>
	<!-- 数据列表 start -->
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-body">
					<table id="treeTable" class="layui-table">
						<thead>
						<tr>
							<th style="width: 15%;text-align: center">名称</th>
							<th style="width: 35%;text-align: center">链接</th>
							<th style="width: 10%;text-align: center">排序</th>
							<th style="width: 10%;text-align: center">可见</th>
							<th style="width: 30%;text-align: center">操作</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="menu">
							<tr id="${menu.permissionId}" pId="${menu.permissionParentId ne '1'?menu.permissionParentId:'0'}">
								<td>${menu.permissionName}</td>
								<td>${menu.permissionValue}</td>
								<td style="text-align: center">${menu.permissionSort}</td>
								<td style="text-align: center">${menu.isShow eq '1'?'显示':'隐藏'}</td>
								<td align="center">
										<%--<a class="layui-btn layui-btn-primary layui-btn-xs">查看</a>--%>
									<a class="layui-btn layui-btn-normal layui-btn-xs" href="${basePath}permission/form?permissionId=${menu.permissionId}" lay-text="修改菜单" class="e_tb_btn edit">修改</a>
									<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="deleteBtnn(${menu.permissionId});" href="#" >删除</a>
									<a class="layui-btn layui-btn-normal layui-btn-xs" href="${basePath}permission/form?parent.permissionId=${menu.permissionId}">添加下级菜单</a>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- 数据列表 end -->
</div>
<!-- ----------------------正文代码end---------------------- -->
</body>
</html>