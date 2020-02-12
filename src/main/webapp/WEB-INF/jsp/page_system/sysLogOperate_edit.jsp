<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../baseJSP.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编辑系统日志</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">

    <style>
         .layui-input-block{
            margin-left: 120px !important;
        }
         .layui-form-label {
            width:120px !important;
        }

    </style>
</head>
<body>
<div class="layui-fluid">
    <!-- ----------------------正文代码start---------------------- -->
    <form method="post" action="" id="editFrm" class="layui-form layui-card layui-form-pane">
        <input type="hidden" id="logId" name="logId" value='${data_log.logId}' />
        <div class="layui-card-header">系统日志${not empty data_log.logId?'修改':'新增'}</div>
        <div class="layui-card-body layui-row layui-col-space15">
            <div class="layui-col-md5">

                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md12">
                        <label class="layui-form-label">操作用户ID</label>
                        <div class="layui-input-block"><input type="text" name="userId" value="${data_log.userId}"  autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips" readOnly="true"></div>
                    </div>
                </div>

                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md12">
                        <label class="layui-form-label">操作用户账号</label>
                        <div class="layui-input-block"><input type="text" value="${data_log.loginName}"  autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips" readOnly="true"></div>
                    </div>
                </div>

                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md12">
                        <label class="layui-form-label">功能Id</label>
                        <div class="layui-input-block"><input type="text" name="funcId" value="${data_log.funcId}"  autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips" readOnly="true"></div>
                    </div>
                </div>

                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md12">
                        <label class="layui-form-label">模块名称</label>
                        <div class="layui-input-block"><input type="text" name="funcSysName" value="${data_log.funcSysName}"  autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips" readOnly="true"></div>
                    </div>
                </div>

                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md12">
                        <label class="layui-form-label">功能名称</label>
                        <div class="layui-input-block"><input type="text" name="funcName" value="${data_log.funcName}"  autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips" readOnly="true"></div>
                    </div>
                </div>

                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md12">
                        <label class="layui-form-label">操作路径</label>
                        <div class="layui-input-block"><input type="text" name="pageUrl" value="${data_log.pageUrl}"  autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips" readOnly="true"></div>
                    </div>
                </div>

                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md12">
                        <label class="layui-form-label">操作IP</label>
                        <div class="layui-input-block"><input type="text" name="operateIp" value="${data_log.operateIp}" autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips" readOnly="true"></div>
                    </div>
                </div>

                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md12">
                        <label class="layui-form-label">操作时间</label>
                        <div class="layui-input-block">
                            <input type="text" name="" value="<fmt:formatDate value="${data_log.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />"  autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips" readOnly="true">
                        </div>
                    </div>
                </div>

                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md12">
                        <label class="layui-form-label">操作描述</label>
                        <div class="layui-input-block"><input type="text" name="operateDesc" value="${data_log.operateDesc}"  autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips" readOnly="true"></div>
                    </div>
                </div>
                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md12">
                        <label class="layui-form-label">操作类型</label>
                        <div class="layui-input-block"><input type="text" name="visitType" value="${data_log.visitType}"  autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips" readOnly="true"></div>
                    </div>
                </div>

                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md12">
                        <label class="layui-form-label">电话号码</label>
                        <div class="layui-input-block"><input type="text" name="phone" value="${data_log.phone}"  autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips" readOnly="true"></div>
                    </div>
                </div>

                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md12">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-block"><input type="text" name="remark" value="${data_log.remark}"  autocomplete="off" class="layui-input" lay-verify="chk_null|required" lay-verType="tips" readOnly="true"></div>
                    </div>
                </div>

                <%--<div class="layui-row layui-col-space15">--%>
                    <%--<div class="layui-col-md3"></div>--%>
                    <%--<div class="layui-col-md7">--%>
                        <%--<button lay-submit lay-filter="saveSysLog" class="layui-btn layui-btn-normal" lay-text="提交保存">--%>
                            <%--<i class="layui-icon layui-icon-release"></i>提交保存--%>
                        <%--</button>--%>
                        <%--<button type="reset" class="layui-btn layui-btn-normal" lay-text="重置">--%>
                            <%--<i class="layui-icon layui-icon-refresh"></i>重置--%>
                        <%--</button>--%>
                    <%--</div>--%>
                <%--</div>--%>

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
        form.on('submit(saveSysLog)', function (data) {
            var loading = layer.msg('正在提交数据', {icon: 16, shade: 0.2, time: 0, offset: 'auto'});
            var url = "${basePath}sysLogOperate/doAddOrEdit";
            $.post(url, data.field, function (result) {
                if (result == 'success') {
                    layer.msg('数据保存成功', {offset: 'auto'});
                } else if (result == 'failed') {
                    layer.msg('数据保存失败，请稍后再试', {offset: 'auto'});
                } else {
                    layer.msg(result, {offset: 'auto'});
                }
            });
            //如果需要ajax提交则 return false，data.field即为表单数据的json数据
            return false;
        });

    });
</script>
</body>
</html>