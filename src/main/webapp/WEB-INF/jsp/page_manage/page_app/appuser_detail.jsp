<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../../baseJSP.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>app用户信息详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${baseStatic}layuiadmin/style/admin.css" media="all">
    <style>
        .layui-upload-img {
            width: 250px;
            height: 150px;
            margin: 0 10px 10px 0;
            border: none;
        }
    </style>
</head>
<body>
<div class="layui-fluid">
    <!-- ----------------------正文代码start---------------------- -->
    <form method="post" action="" id="editFrm" class="layui-form layui-card layui-form-pane">

        <div class="layui-card-header">执法用户详情</div>

        <div class="layui-card-body layui-row layui-col-space15">
            <div class="layui-col-md11">
                <input type="hidden" name="id" value="${data.id}"/>
                <div class="layui-row layui-col-space15">

                    <div class="layui-form-item">
                        <label class="layui-form-label">登录名</label>
                        <div class="layui-field-box" style="margin-left: 110px;">${data.loginName}</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">登录密码</label>
                        <div class="layui-field-box" style="margin-left: 110px;">${data.loginPassword}</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">工号</label>
                        <div class="layui-field-box" style="margin-left: 110px;">${data.jobNumber}</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">姓名</label>
                        <div class="layui-field-box" style="margin-left: 110px;">${data.userName}</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">用户性别</label>
                        <div class="layui-field-box" style="margin-left: 110px;">${data.userSexName}</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">身份证</label>
                        <div class="layui-field-box" style="margin-left: 110px;">${data.userCardNo}</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">联系电话</label>
                        <div class="layui-field-box" style="margin-left: 110px;">${data.userMobile}</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">所属区域</label>
                        <div class="layui-field-box" style="margin-left: 110px;">${data.orgName}</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">所属部门</label>
                        <div class="layui-field-box" style="margin-left: 110px;">${data.userDeptName}</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">用户状态</label>
                        <div class="layui-field-box" style="margin-left: 110px;">${data.userStateName}</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">用户类型</label>
                        <div class="layui-field-box" style="margin-left: 110px;">${data.userTypeName}</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">邮箱</label>
                        <div class="layui-field-box" style="margin-left: 110px;">${data.userEmail}</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-field-box" style="margin-left: 110px;">${data.userDesc}</div>
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
            var loading = layer.msg('正在提交数据', {icon: 16, shade: 0.2, time: 0});
            $.ajax({
                type: "post",
                url: "${basePath}manage/appuser/appuser_edit",
                traditional: true,
                data: datas,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (result) {
                    if (result.code == '200') {
                        layer.alert('数据保存成功', {offset: 't'});
                    } else {
                        layer.alert(result.message, {offset: 't'});
                    }
                }
            });
            //如果需要ajax提交则 return false，data.field即为表单数据的json数据
            return false;
        });

    });
</script>
</body>
</html>