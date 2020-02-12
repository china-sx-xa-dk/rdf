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
    <form method="post" action="${basePath}sysConfig/doAddOrEdit" id="editFrm" class="layui-form layui-card layui-form-pane">
        <input type="hidden" id="configId" name="configId" value='${data_config.configId}' />
        <div class="layui-card-header">系统配置${not empty data_config.configId?'修改':'新增'}</div>
        <div class="layui-card-body layui-row layui-col-space15">
            <div class="layui-col-md12 layui-col-space15">
                <c:if test="${empty data_config.configId}">
                    <div class="layui-col-md4">
                        <label class="layui-form-label"><span style="color: red;">*</span>配置项</label>
                        <div class="layui-input-block"><input type="text" name="configKey" value="${data_config.configKey}" placeholder="请输入配置项" autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips"></div>
                    </div>
                </c:if>
                <c:if test="${not empty data_config.configId}">
                    <input type="hidden" id="configKey" name="configKey" value='${data_config.configKey}' />
                </c:if>
                <div class="layui-col-md4">
                    <label class="layui-form-label"><span style="color: red;">*</span>配置值</label>
                    <div class="layui-input-block"><input type="text" name="configValue" value="${data_config.configValue}" placeholder="请输入配置值" autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips"></div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label"><span style="color: red;">*</span>配置描述</label>
                    <div class="layui-input-block"><input type="text" name="remarks" value="${data_config.remarks}" placeholder="请输入配置描述" autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips"></div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label"><span style="color: red;">*</span>排序</label>
                    <div class="layui-input-block"><input type="text" name="sortNum" value="${data_config.sortNum}" placeholder="请输入排序" autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips"></div>
                </div>


                <div class="layui-form-item" style="text-align: center">
                    <button lay-submit lay-filter="saveSysConfig" class="layui-btn layui-btn-normal" lay-text="保存">
                        <i class="layui-icon layui-icon-release"></i>保存
                    </button>
                    <button type="reset" class="layui-btn layui-btn-normal" lay-text="重置">
                        <i class="layui-icon layui-icon-refresh"></i>重置
                    </button>
                    <button type="button" class="layui-btn layui-btn-normal" lay-text="返回" onclick="javascript: location.href='${basePath}sysConfig/get_list'">
                        <i class="layui-icon layui-icon-close"></i>返回
                    </button>
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
        form.on('submit(saveAdminUser)', function (data) {
            var loading = layer.msg('正在提交数据', {icon: 16, shade: 0.2, time: 0, offset: 'auto'});
            $("#editFrm").submit();
        });

    });
</script>
</body>
</html>