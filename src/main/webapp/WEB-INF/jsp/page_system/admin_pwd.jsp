<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../baseJSP.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10,IE=edge,chrome=1"/>

<script type="text/javascript">
	layui.config({
		base: '${baseStatic}layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['jquery', 'index', 'form'], function () {
		$ = layui.jquery;//初始化jquery
		var form = layui.form;//初始化form组件

		//监听提交form点击事件
		form.on('submit(saveActivity)', function (data) {
			var oldPass = $("#oldPass").val();
			if(oldPass == null || oldPass == ''){
				layer.msg('旧密码不能为空!', {offset: 'auto'});
				return false;
			}

			var newPass = $("#newPass").val();
			if(newPass == null || newPass == ''){
				layer.msg('新密码不能为空!', {offset: 'auto'});
				return false;
			}else if(!/^[0-9a-zA-Z]{6,16}$/.test(newPass)){
				layer.msg('密码只能是6~16位数字、字母及下划线组成!', {offset: 'auto'});
				return false;
			}

			var verifyPass = $("#verifyPass").val();
			if(verifyPass == null || verifyPass == ''){
				layer.msg('确认密码不能为空!', {offset: 'auto'});
				return false;
			}else if(newPass != verifyPass){
				layer.msg('两次密码输入不一致!', {offset: 'auto'});
				return false;
			}
			var data = {"oldPass":oldPass,"newPass":newPass};
			var url = "${basePath}admin/doChangePass";
			var loading = layer.msg('正在修改...', {icon:16,shade:0.2,time:0,offset: 'auto'});
			$.post(url,data,function(result){
				layer.close(loading);//关闭loading
				if(result == 'success'){
					layer.msg('修改成功，稍后请重新登录......', {offset: 'auto'});
				}else if(result == 'opwdWrong'){
					layer.msg('旧密码输入不正确!', {offset: 'auto'});
				}else{
					layer.msg('修改失败，请稍后再试!', {offset: 'auto'});
				}
			});
			//如果需要ajax提交则 return false，data.field即为表单数据的json数据
			return false;
		});
	});

</script>
</head>
<body>



  	<!-- ------------------------------------ 正文代码开始 start ------------------------------------ -->
	<div class="layui-fluid">
		<form method="post" action="" id="editFrm" class="layui-form layui-card layui-form-pane">
			<div class="layui-card-header">密码修改</div>
			<div class="layui-card-body layui-row layui-col-space15">
				<div class="layui-col-md12 layui-col-space15">
					<div class="layui-col-md4">
						<label class="layui-form-label">旧密码</label>
						<div class="layui-input-block">
							<input type="password" id="oldPass" name="oldPass" placeholder="请输入旧密码"
															  autocomplete="off" class="layui-input"
															  lay-verify="chk_null|required" lay-verType="tips">
						</div>
					</div>
				</div>
				<div class="layui-col-md12 layui-col-space15">
					<div class="layui-col-md4">
						<label class="layui-form-label">新密码</label>
						<div class="layui-input-block">
							<input type="password" id="newPass" name="newPass" placeholder="请输入新密码"
								   autocomplete="off" class="layui-input"
								   lay-verify="chk_null|required" lay-verType="tips">
						</div>
					</div>
				</div>
				<div class="layui-col-md12 layui-col-space15">
					<div class="layui-col-md4">
						<label class="layui-form-label">密码确认</label>
						<div class="layui-input-block">
							<input type="password" id="verifyPass" name="verifyPass" placeholder="请再次输入新密码"
								   autocomplete="off" class="layui-input"
								   lay-verify="chk_null|required" lay-verType="tips">
						</div>
					</div>
				</div>
				<div class="layui-col-md4 layui-col-space15">
					<div class="layui-form-item" style="text-align: center">
						<button lay-submit lay-filter="saveActivity" class="layui-btn layui-btn-normal" lay-text="确认修改">
							<i class="layui-icon layui-icon-release"></i>确认修改
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
  	<!-- ------------------------------------ 正文代码开始 end ------------------------------------ -->
  	
  	
</body>
</html>