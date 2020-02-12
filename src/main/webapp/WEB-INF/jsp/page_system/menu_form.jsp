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

	<script src="${baseStatic}layuiadmin/util_form_check.js"></script>
	<script type="text/javascript">

		layui.config({
			base: '${baseStatic}layuiadmin/' //静态资源所在路径
		}).extend({
			index: 'lib/index' //主入口模块
		}).use(['jquery', 'index', 'form'], function () {
			$ = layui.jquery;//初始化jquery
			var form = layui.form;//初始化form组件
			//自定义校验规则
			form.verify(util_from_check);

			//监听提交form点击事件
			form.on('submit(saveMenu)', function (data) {
				$("#inputForm").submit();
			});
		});


	</script>
</head>
<body>
<div class="layui-fluid">
	<form method="post" action="${basePath}permission/save" id="inputForm" class="layui-form layui-card layui-form-pane">
		<input type="hidden" name="permissionId" value="${menu.permissionId}"/>
		<div class="layui-card-header">菜单${not empty menu.permissionId?'修改':'新增'}</div>
		<div class="layui-card-body layui-row layui-col-space15">
			<div class="layui-col-md12 layui-col-space15">
				<div class="layui-col-md4">
					<label class="layui-form-label">上级菜单:</label>
					<div class="layui-input-block" style="height: 38px;">
						<sys:treeselect id="menu" name="parent.permissionId" value="${menu.parent.permissionId}" labelName="parent.permissionName" labelValue="${menu.parent.permissionName}"
										title="菜单" url="./permission/treeData" extId="${menu.permissionId}" cssClass="layui-input"/>
					</div>
				</div>
				<div class="layui-col-md4">
					<label class="layui-form-label"><font color="red">*</font>菜单名称:</label>
					<div class="layui-input-block">
						<input type="text" name="permissionName" id="permissionNameStr" maxlength="50"
							   value="${menu.permissionName}" placeholder="请输入菜单名称"
							   autocomplete="off" class="layui-input"
							   lay-verify="chk_null|required|chk_all_number"
							   lay-verType="tips">
					</div>
				</div>
				<div class="layui-col-md4">
					<label class="layui-form-label"><span style="color: red;">*</span>菜单路径:</label>
					<div class="layui-input-block">
						<input type="text" name="permissionValue" id="permissionValue" maxlength="500"
							   value="${menu.permissionValue}" placeholder="请输入菜单路径"
							   autocomplete="off" class="layui-input"
							   lay-verify="chk_null|required|chk_all_number"
							   lay-verType="tips">
					</div>
				</div>

				<div class="layui-col-md4">
					<label class="layui-form-label"><span style="color: red;">*</span>排序:</label>
					<div class="layui-input-block">
						<input type="number" name="permissionSort" id="permissionSort" maxlength="20"
							   value="${menu.permissionSort}" placeholder="请输入排序"
							   autocomplete="off" class="layui-input"
							   lay-verify="chk_null|required|chk_just_number"
							   lay-verType="tips">
					</div>
				</div>

				<div class="layui-form-item  layui-col-md12" style="text-align: center">
					<button lay-submit lay-filter="saveMenu" class="layui-btn layui-btn-normal" lay-text="保存">
						<i class="layui-icon layui-icon-release"></i>保存
					</button>
					<button type="reset" class="layui-btn layui-btn-normal" lay-text="重置">
						<i class="layui-icon layui-icon-refresh"></i>重置
					</button>
					<button type="button" class="layui-btn layui-btn-normal" lay-text="返回" onclick="javascript: location.href='${basePath}permission/list'">
						<i class="layui-icon layui-icon-close"></i>返回
					</button>
				</div>
			</div>
		</div>
	</form>
</div>
</body>
</html>