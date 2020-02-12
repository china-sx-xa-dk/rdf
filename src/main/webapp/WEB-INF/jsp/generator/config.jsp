<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../baseJSP.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>代码生成配置</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link type="text/css" rel="stylesheet" href="${baseStatic}layuiadmin/style/admin.css" media="all"/>
    <style type="text/css">
        .layui-form-item .layui-form-label{
            width: 250px;
            text-align: left;
        }
        .layui-form-item .layui-input-inline {
            width: 400px;
        }
    </style>
</head>
<body>
<div class="layui-fluid">
    <%-- 表单信息 start --%>
    <form action="" class="layui-form layui-card layui-form-pane">
        <div class="layui-card-body layui-row layui-col-space15">
            <div class="layui-col-md12 layui-col-space15">

                <div class="layui-form-item">
                    <h1>代码生成配置</h1>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span style="color: red;">*</span>项目路径(projectPath)</label>
                    <div class="layui-input-inline">
                        <input type="text" id="projectPath" name="projectPath" value="${config.projectPath}" placeholder="请输入projectPath" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">项目在硬盘上的基础路径，示例: E:/workspace_idea/rdf</div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span style="color: red;">*</span>输出路径(outRoot)</label>
                    <div class="layui-input-inline">
                        <input type="text" id="outRoot" name="outRoot" value="${config.outRoot}" placeholder="请输入outRoot" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">代码生成的文件输出路径(路径为部署应用系统下)，示例: 绝对路径(E:/work/outRoot) 相对路径(/src/main)</div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">模版位置(template)</label>
                    <div class="layui-input-inline">
                        <input type="text" id="template" name="template" disabled value="${config.template}" placeholder="请输入template" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">模板代码位置，示例: 相对路径(/src/main/resources/static/generator/template/LayUI)</div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">表名前缀(tableRemovePrefixes)</label>
                    <div class="layui-input-inline">
                        <input type="text" id="tableRemovePrefixes" name="tableRemovePrefixes" value="${config.tableRemovePrefixes}" placeholder="请输入tableRemovePrefixes" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">生成代码需要移除的表名前缀，例如t_user变User类则配置t_，使用逗号分隔同时配置多个前缀，示例: table_,t_(不能有空格)</div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span style="color: red;">*</span>模块名称(modulepackage)</label>
                    <div class="layui-input-inline">
                        <input type="text" id="modulepackage" name="modulepackage" value="${config.modulepackage}" placeholder="请输入modulepackage" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">模板代码的modulepackage属性，模块名称，示例: system</div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span style="color: red;">*</span>作者名称(author)</label>
                    <div class="layui-input-inline">
                        <input type="text" id="author" name="author" value="${config.author}" placeholder="请输入author" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">模板代码的author属性(注释的作者名称)，示例: xiaomo</div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span style="color: red;">*</span>上级包名(basepackage)</label>
                    <div class="layui-input-inline">
                        <input type="text" id="basepackage" name="basepackage" value="${config.basepackage}" placeholder="请输入basepackage" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">模板代码的basepackage属性，示例: com.sxgokit.rdf</div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span style="color: red;">*</span>公共包名(commonspackage)</label>
                    <div class="layui-input-inline">
                        <input type="text" id="commonspackage" name="commonspackage" value="${config.commonspackage}" placeholder="请输入commonspackage" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">模板代码的commonspackage属性，示例: com.sxgokit.rdf</div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span style="color: red;">*</span>页面包名(namespace)</label>
                    <div class="layui-input-inline">
                        <input type="text" id="namespace" name="namespace" value="${config.namespace}" placeholder="请输入namespace" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">模板代码的namespace属性，前端页面所属包名，示例: jsp</div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">数据权限类型</label>
                    <div class="layui-input-inline">
                        <select id="dataAuthorityType" name="dataAuthorityType" lay-verify="">
                            <option value="1"<c:if test="${config.dataAuthorityType==1}">selected</c:if>>全部匹配</option>
                            <option value="2"<c:if test="${config.dataAuthorityType==2}">selected</c:if>>只限当前用户</option>
                            <option value="3"<c:if test="${config.dataAuthorityType==3}">selected</c:if>>当前用户所属组织本级及所有下级</option>
                        </select>
                    </div>
                    <div class="layui-form-mid layui-word-aux">当前生成代码模块的数据权限类型</div>
                </div>

                <div class="layui-form-item">
                    <button id="refresh" type="button" class="layui-btn layui-btn-normal" lay-text="刷新">
                        <i class="layui-icon layui-icon-refresh"></i>刷新
                    </button>
                    <button id="setUp" type="button" class="layui-btn layui-btn-normal" lay-text="设置">
                        <i class="layui-icon layui-icon-set"></i>设置
                    </button>
                    <button id="backTable" type="button" class="layui-btn layui-btn-normal" lay-text="返回">
                        <i class="layui-icon layui-icon-return"></i>返回
                    </button>
                </div>
            </div>
        </div>
    </form>
    <%-- 表单信息 end --%>
</div>
<script>
    layui.config({
        base: '${baseStatic}layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['jquery','index','form'],function(){
        $ = layui.jquery;//初始化jquery
        var form = layui.form;//初始化form组件

        //刷新代码生成配置
        $('#refresh').on('click', function(){
            if(valadite()){
                var content = "是否确认刷新代码生成配置?";
                layer.confirm(content, {btn: ['确定', '取消'], title: "刷新确认"}, function () {
                    optConfig(true);
                });
            }
        });

        //更新代码生成配置
        $('#setUp').on('click', function(){
            if(valadite()){
                var content = "是否确认重新设置代码生成配置?";
                layer.confirm(content, {btn: ['确定', '取消'], title: "更改确认"}, function () {
                    optConfig(false);
                });
            }
        });

        //返回代码生成页面
        $('#backTable').on('click', function(){
            location.href = '${ajaxPath}/database/findTableList';
        });
    });

    //进行配置必填校验
    function valadite(){
        if($("#projectPath").val() == ""){
            layer.msg('请输入项目路径!',{offset: 'auto'});
            return false;
        }else if($("#outRoot").val() == ""){
            layer.msg('请输入输出路径!',{offset: 'auto'});
            return false;
        }else if($("#modulepackage").val() == ""){
            layer.msg('请输入模块名称!',{offset: 'auto'});
            return false;
        }else if($("#author").val() == ""){
            layer.msg('请输入作者名称!',{offset: 'auto'});
            return false;
        }else if($("#basepackage").val() == ""){
            layer.msg('请输入上级包名!',{offset: 'auto'});
            return false;
        }else if($("#commonspackage").val() == ""){
            layer.msg('请输入公共包名!',{offset: 'auto'});
            return false;
        }else if($("#namespace").val() == ""){
            layer.msg('请输入页面包名!',{offset: 'auto'});
            return false;
        }
        return true;
    }

    //刷新/更改配置信息
    //isRead：true读取；false写入；
    function optConfig(isRead){
        var config = {
            isRead:isRead,
            projectPath:$("#projectPath").val(),
            outRoot:$("#outRoot").val(),
            template:$("#template").val(),
            tableRemovePrefixes:$("#tableRemovePrefixes").val(),
            modulepackage:$("#modulepackage").val(),
            author:$("#author").val(),
            basepackage:$("#basepackage").val(),
            commonspackage:$("#commonspackage").val(),
            namespace:$("#namespace").val(),
            dataAuthorityType:$("#dataAuthorityType").val()
        };
        console.log(config);
        $.ajax({
            type:"post",
            url:"${ajaxPath}/database/optConfig",
            data: config,
            dataType:"json",
            success:function(data) {
                if(data.code == 200){
                    layer.msg('刷新代码生成配置成功!',{offset: 'auto'});
                    window.setTimeout(function(){
                        location.href = '${ajaxPath}/database/getConfig';
                    },2000);
                }else{
                    layer.msg('刷新代码生成配置失败!',{offset: 'auto'});
                }
            },
        });
    }
</script>
</body>
</html>