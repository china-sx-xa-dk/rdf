<#assign className = table.className>
<#assign classNameLower =className?uncap_first>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../baseJSP.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
</head>
<body>
<div class="layui-fluid">
	<!-- ----------------------正文代码start---------------------- -->
	<form method="post" action="" id="editFrm" class="layui-form layui-card layui-form-pane">
		<#list table.columns as column>
		<#if column.pk>
		<input type="hidden" name="${column.columnNameLower}" value="${r"${data."}${column.columnNameLower}${r"}"}"/>
		<#else>
		</#if>
		</#list>
		<div class="form_div layui-card-body layui-row layui-col-space15">
			<div class="layui-col-md12 layui-col-space15">
				<fieldset>
					<legend>基本信息</legend>
					<#list table.columns as column>
					<#if column.pk>
					<#elseif column.isDateTimeColumn>
					<#elseif column.columnNameLower == 'delFlag'>
					<#elseif column.columnNameLower == 'createUser'>
					<#elseif column.columnNameLower == 'updateUser'>
					<#else>
					<div class="layui-col-md4">
						<label class="layui-form-label">${column.columnAlias}</label>
						<div class="layui-input-block"><input type="text" name="${column.columnNameLower}"
															  value="${r"${data."}${column.columnNameLower}${r"}"}" placeholder="请输入${column.columnAlias}"
															  autocomplete="off" class="layui-input"
															  lay-verify="chk_null|required" lay-verType="tips"></div>
					</div>
					</#if>
					</#list>
				</fieldset>
			</div>
		</div>
		<%-- 提交与重置 start --%>
		<div class="btn_div layui-col-md4">
			<button lay-submit lay-filter="saveActivity" class="layui-btn layui-btn-normal" lay-text="保存">
				<i class="layui-icon layui-icon-release"></i>保存
			</button>
			<button type="reset" class="layui-btn layui-btn-normal" lay-text="重置">
				<i class="layui-icon layui-icon-refresh"></i>重置
			</button>
			<button type="button" class="layui-btn layui-btn-normal" lay-text="返回" onclick="returnList()">
				<i class="layui-icon layui-icon-close"></i>返回
			</button>
		</div>
		<%-- 提交与重置 end --%>
	</form>
	<!-- ----------------------正文代码end---------------------- -->
</div>
<script src="${r"${baseStatic}"}layuiadmin/util_form_check.js"></script>
<script>
	layui.config({
		base: '${r"${baseStatic}"}layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['jquery', 'index', 'form', 'layedit', 'upload', 'table', 'laydate'], function () {
		var $ = layui.jquery;//初始化jquery
		var form = layui.form;//初始化form组件
		var upload = layui.upload;
		var layEdit = layui.layedit; // 初始化富文本编辑框组件

		//自定义文本框、下拉框校验规则
		form.verify(util_from_check);

		//监听提交form点击事件
		form.on('submit(saveActivity)', function (data) {
			var datas = JSON.stringify(data.field);
			var loading = layer.msg('正在提交数据', {icon: 16, shade: 0.2, time: 0, offset: 'auto'});
			$.ajax({
				type: "post",
				url: "${r"${basePath}"}${modulepackage}/${classNameLower}/save_edit",
				traditional: true,
				data: datas,
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success: function (result) {
					if (result.code == 200) {
						layer.confirm('数据保存成功', {
							btn: ['返回列表','继续操作'] //按钮
						}, function(){
							closeLayer();
							//调用父页面列表刷新方法
							window.parent.$("#searchFrm").submit();
						}, function(){});
					} else {
						layer.msg('数据保存失败，请稍后再试', {offset: 'auto'});
					}
				}
			});
			//如果需要ajax提交则 return false，data.field即为表单数据的json数据
			return false;
		});

	});

	//返回列表页面
	function returnList(){
		closeLayer();
	}

	//关闭当前layer弹出层
	function closeLayer(){
		//获取窗口索引
		var index = parent.layer.getFrameIndex(window.name);
		window.parent.layer.close(index);
	}
</script>
</body>
</html>