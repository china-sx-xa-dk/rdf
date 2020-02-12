<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="baseJSP.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${baseStatic}layuiadmin/style/admin.css" media="all">

    <title>dome页</title>
    <style>
        .layui-upload-img {width: 80px;height: 80px;margin: 0 10px 10px 0;border: none;}
    </style>
</head>
<body>
<div class="layui-fluid">
    <!-- ----------------------正文代码start---------------------- -->
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header">营业额<span class="layui-badge layui-bg-red layuiadmin-badge">近7天</span></div>
                <div class="layui-card-body layuiadmin-card-list">
                    <p class="layuiadmin-big-font">9,999,666</p>
                    <p>累计营业额 <span class="layuiadmin-span-color">128,879万 <i class="layui-inline layui-icon layui-icon-dollar"></i></span></p>
                </div>
            </div>
        </div>


        <div class="layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header">form表单</div>
                <div class="layui-card-body">
                    <!--表单 start-->
                    <form method="post" action="" class="layui-form layui-form-pane">
                        <div class="layui-row layui-col-space15">
                            <div class="layui-col-md12">
                                <label class="layui-form-label">姓名</label>
                                <div class="layui-input-block"><input type="text" name="userName" placeholder="这是非vue数据" autocomplete="off" class="layui-input" lay-verify="required|username" lay-verType="tips"></div>
                            </div>
                        </div>
                        <div class="layui-row layui-col-space15">
                            <div class="layui-col-md6">
                                <label class="layui-form-label">密码</label>
                                <div class="layui-input-block"><input type="text" name="userPass" placeholder="这是非vue数据" autocomplete="off" class="layui-input" lay-verify="required" lay-verType="tips"></div>
                            </div>
                            <div class="layui-col-md6">
                                <label class="layui-form-label">年龄</label>
                                <div class="layui-input-block"><input type="text" name="userAge" placeholder="这是非vue数据" autocomplete="off" class="layui-input" lay-verify="required" lay-verType="tips"></div>
                            </div>
                        </div>
                        <div class="layui-row layui-col-space15">
                            <div class="layui-col-md12">
                                <label class="layui-form-label">性别</label>
                                <div class="layui-input-block">
                                    <select name="userSex" lay-verify="required" lay-verType="tips">
                                        <option value="">请选择性别</option>
                                        <option value="0">男</option>
                                        <option value="1">女</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="layui-row layui-col-space15">
                            <div class="layui-col-md12">
                                <img src="${bg_imgUpload}" class="layui-upload-img p_img_uploadBtn"/>
                                <input type="hidden" name="userHeadImg" id="userHeadImg"/>
                            </div>
                        </div>
                        <div class="layui-row layui-col-space15">
                            <div class="layui-col-md12">
                                <button class="layui-btn" lay-submit  lay-filter="saveFrm">提交保存</button>
                            </div>
                        </div>
                    </form>
                    <!--表单 end-->
                </div>
            </div>
        </div>



        <div class="layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-body"></div>
            </div>
        </div>
        <div class="layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header"></div>
                <div class="layui-card-body"></div>
            </div>
        </div>
    </div>
    <!-- ----------------------正文代码end---------------------- -->
</div>

<script src="${baseStatic}layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: 'layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['jquery','index','form','upload'], function () {
        var $ = layui.jquery;
        var form = layui.form;//初始化form组件
        var upload = layui.upload;



        //固定数量图片上传
        upload.render({
            elem: '.p_img_uploadBtn'
            ,url: '${basePath}util/ajaxUpload'
            ,before: function(){
                var item = this.item;//当前点击的dom元素
                item.attr("src","${bg_imgUploadIng}");//设置正在上传
            }
            ,done: function(res, index, upload){//上传成功
                var item = this.item;//当前点击的dom元素
                item.attr("src","${basePath}" + res.data);
                $("#userHeadImg").val(res.data);
            },error: function(index, upload){
                var item = this.item;//当前点击的dom元素
                item.attr("src","${bg_imgUploadErr}");//设置正在上传
            }
        });



        //自定义文本框、下拉框校验规则
        form.verify({
            username: function(value, item){ //value：表单的值、item：表单的DOM对象
                if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){return '用户名不能有特殊字符';}
                if(/(^\_)|(\__)|(\_+$)/.test(value)){return '用户名首尾不能出现下划线\'_\'';}
                if(/^\d+\d+\d$/.test(value)){return '用户名不能全为数字';}
                if(value.length < 6 || value.length > 16 ){return '用户名为6-16位数字字母组合';}
            },
            aaa:function(value, item){

            }
        });

        //监听提交form点击事件
        form.on('submit(saveFrm)', function(data){
            layer.msg(JSON.stringify(data.field));
            <%--var loading = layer.msg('正在提交数据', {icon:16,shade:0.2,time:0});--%>
            <%--var url = "${basePath}admin/doAddOrEdit";--%>
            <%--$.post(url, data.field, function (result) {--%>
                <%--if (result == 'success') {--%>
                    <%--layer.alert('数据已保存成功');--%>
                <%--} else if (result == 'failed') {--%>
                    <%--layer.alert('数据保存失败，请稍后再试');--%>
                <%--} else {--%>
                    <%--layer.alert(result);--%>
                <%--}--%>
            <%--});--%>
            return false;
        });
    });
</script>
</body>
</html>