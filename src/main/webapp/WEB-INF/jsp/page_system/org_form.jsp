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
</head>
<body>

<div class="layui-fluid">
	<!-- ----------------------表单信息start---------------------- -->
	<form method="post" action="${basePath}org/save" id="inputForm" class="layui-form layui-card layui-form-pane">
		<input type="hidden" name="orgId" value='${org.orgId}'/>
		<div class="layui-card-header">机构${not empty org.orgId?'修改':'新增'}</div>
		<sys:message content="${message}"/>
		<div class="layui-card-body layui-row layui-col-space15">
			<div class="layui-col-md12 layui-col-space15">
				<div class="layui-col-md4">
					<label class="layui-form-label">上级机构:</label>
					<div class="layui-input-block">
						<input type="hidden" name="parent.orgId" value='${org.parent.orgId}'/>
						<input type="text" name="parent.orgName" value="${org.parent.orgName}" autocomplete="off" class="layui-input" readonly="readonly">
					</div>
				</div>
				<div class="layui-col-md4">
					<label class="layui-form-label"><span style="color: red;">*</span>机构名称:</label>
					<div class="layui-input-block">
						<input type="text" id="orgNameStr" name="orgName" value="${org.orgName}" autocomplete="off" class="layui-input" lay-verify="chk_null" lay-verType="tips" placeholder="请输入机构名称">
					</div>
				</div>

				<div class="layui-form-item layui-col-md8" style="text-align: center">
					<button lay-submit lay-filter="saveOrg" class="layui-btn layui-btn-normal" lay-text="保存">
						<i class="layui-icon layui-icon-release"></i>保存
					</button>
					<button type="reset" class="layui-btn layui-btn-normal" lay-text="重置">
						<i class="layui-icon layui-icon-refresh"></i>重置
					</button>
					<button type="button" class="layui-btn layui-btn-normal" lay-text="返回" onclick="javascript: location.href='${basePath}org/list'">
						<i class="layui-icon layui-icon-close"></i>返回
					</button>
				</div>
			</div>
		</div>
	</form>
	<!-- ----------------------表单信息end---------------------- -->
</div>

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
        form.on('submit(saveOrg)', function (data) {
			$("#inputForm").submit();
        });
    });


</script>
</body>
</html>