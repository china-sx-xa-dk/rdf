<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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

	<script src="${baseStatic}js/vue.min.js"></script>
	<title>首页</title>
	<style>
		.layui-upload-img {width:300px;height:300px;border: none;}
	</style>
</head>
<body>
	<!--上传单张图片-->
	<img src="${bg_imgUpload}" class="layui-upload-img p_img_uploadBtn"/>


<script src="${baseStatic}layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: 'layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['jquery', 'index', 'upload','layer'], function () {
        var $ = layui.jquery;
        var upload = layui.upload;
    	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layui.$('#TEMP_UPLOAD_PATH').val("");
        //固定数量图片上传
        upload.render({
            elem: '.p_img_uploadBtn'
            ,url: '${basePath}manage/util/ajaxUpload'
            ,before: function(){
                var item = this.item;//当前点击的dom元素
                item.attr("src","${bg_imgUploadIng}");//设置正在上传
                parent.layui.$('#TEMP_UPLOAD_PATH').val("");
            }
            ,done: function(res, index, upload){//上传成功
                var item = this.item;//当前点击的dom元素
                item.attr("src",res.data);
                parent.layui.$('#TEMP_UPLOAD_PATH').val(res.data);
            },error: function(index, upload){
                var item = this.item;//当前点击的dom元素
                item.attr("src","${bg_imgUploadErr}");//设置上传失败
                parent.layui.$('#TEMP_UPLOAD_PATH').val("");
            }
        });
    });
</script>
</body>
</html>