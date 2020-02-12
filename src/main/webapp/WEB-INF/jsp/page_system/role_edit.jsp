<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../baseJSP.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>编辑角色信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
</head>
<body>
<div class="layui-fluid">
	<!-- ----------------------正文代码start---------------------- -->
	<form method="post" action="${basePath}role/doAddOrEdit" id="editFrm" class="layui-form layui-card layui-form-pane">
		<input type="hidden" id="roleId" name="roleId" value='${data_role.roleId}' />
		<div class="layui-card-header">角色信息${not empty data_role.roleId?'修改':'新增'}</div>
		<div class="layui-card-body layui-row layui-col-space15">

			<div class="layui-col-md5">
				<div class="layui-row layui-col-space15">
					<div class="layui-col-md12 ">
						<label class="layui-form-label">角色名称</label>
						<div class="layui-input-block"><input type="text" name="roleName" value="${data_role.roleName}" placeholder="请输入角色名称" autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips"></div>
					</div>
				</div>
				<div class="layui-row layui-col-space15">
					<div class="layui-col-md12 ">
						<label class="layui-form-label">是否启用</label>
						<div class="layui-input-block">
							<select id="roleState" name="roleState">
								<option value="0" <c:if test="${data_role.roleState==0 }"> selected </c:if>>启用</option>
								<option value="1" <c:if test="${data_role.roleState==1 }"> selected </c:if>>禁用</option>
							</select>
						</div>
					</div>
				</div>
				<div class="layui-row layui-col-space15">
					<div class="layui-col-md12 layui-form-text">
						<label class="layui-form-label">角色描述</label>
						<div class="layui-input-block">
							<textarea name="roleDesc" placeholder="请输入角色描述(非必填)" class="layui-textarea">${data_role.roleDesc}</textarea>
						</div>
					</div>
				</div>

				<div class="layui-row layui-col-space15">
					<div class="layui-col-md2"></div>
					<div class="layui-col-md10">
						<button lay-submit lay-filter="saveRole" class="layui-btn layui-btn-normal" lay-text="保存">
							<i class="layui-icon layui-icon-release"></i>保存
						</button>
						<button type="reset" class="layui-btn layui-btn-normal" lay-text="重置">
							<i class="layui-icon layui-icon-refresh"></i>重置
						</button>
						<button type="button" class="layui-btn layui-btn-normal" lay-text="返回" onclick="javascript: location.href='${basePath}role/findRoleList'">
							<i class="layui-icon layui-icon-close"></i>返回
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- ----------------------正文代码end---------------------- -->
</div>
<script src="${baseStatic}layuiadmin/util_form_check.js"></script>
<script>
    layui.config({
        base: '${baseStatic}layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['jquery','index','form'],function(){
        $ = layui.jquery;//初始化jquery
        var form = layui.form;//初始化form组件
		//自定义校验规则
		form.verify(util_from_check);


		//监听提交form点击事件
		form.on('submit(saveRole)', function (data) {
			$("#editFrm").submit();
		});

    });
</script>
</body>
</html>