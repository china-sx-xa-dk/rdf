<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../baseJSP.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>组织机构管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet"  href="${baseStatic}treeTable/themes/vsStyle/treeTable.css" />
	<script type="text/javascript" src="${baseStatic}treeTable/jquery.treeTable.min.js" ></script>

</head>
<body>
<div class="layui-fluid">
	<!-- ----------------------正文代码start---------------------- -->
	<sys:message content="${message}"/>
	<!-- 数据列表 start -->
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-body">
					<table id="treeTable" class="layui-table">
						<thead>
							<tr>
								<th style="width: 30%;">机构名称</th>
								<th style="width: 30%;">上级机构</th>
								<th style="width: 40%;text-align: center;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${list != null && list.size() > 0 }">
									<c:forEach items="${list}" var="org" varStatus="idx">
										<tr id="${org.orgId}" pId="${org.parentId ne '0'?org.parentId:'0'}">
											<td>${org.orgName}</td>
											<td>${org.parent.orgName}</td>
											<td align="center">
												<a class="layui-btn layui-btn-normal layui-btn-xs" href="${basePath}org/form?parent.orgId=${org.orgId}">添加下级组织</a>
												<c:if test="${idx.index != 0}">
													<a class="layui-btn layui-btn-normal layui-btn-xs" href="${basePath}org/form?orgId=${org.orgId}" lay-text="修改" class="e_tb_btn edit">修改</a>
													<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="deleteOrg(${org.orgId});" href="#" >删除</a>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:when test="${list == null || list.size() == 0 }">
									<tr><td colspan="3" align="center">暂无组织机构信息，请联系系统管理员！</td></tr>
								</c:when>
							</c:choose>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- 数据列表 end -->
	<!-- ----------------------正文代码end---------------------- -->
</div>
<%-- JS开始 --%>
<script type="text/javascript">
	//初始化加载treeTable
	$(document).ready(function() {
		$("#treeTable").treeTable({expandLevel : 2}).show();
	});

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

	//删除组织机构
	function deleteOrg(orgId) {
		layer.confirm('确定要删除该机构及所有子机构吗？',{offset: 'auto'}, function(index) {
			window.location.assign("${ajaxPath}org/delete?orgId="+orgId);
		});
	}
</script>
</body>
</html>