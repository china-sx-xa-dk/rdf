<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../baseJSP.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编辑数据字典</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
</head>
<body>
<div class="layui-fluid">
    <!-- ----------------------正文代码start---------------------- -->
    <form method="post" action="${basePath}dict/doAddOrEdit" id="editFrm" class="layui-form layui-card layui-form-pane">
        <input type="hidden" id="id" name="id" value='${data_dict.id}'/>
        <div class="layui-card-header">数据字典${not empty data_dict.id?'修改':'新增'}</div>
        <sys:message content="${message}"/>
        <div class="layui-card-body layui-row layui-col-space15">
            <div class="layui-col-md12 layui-col-space15">

                    <div class="layui-col-md4">
                        <label class="layui-form-label"><span style="color: red;">*</span>数据值</label>
                        <div class="layui-input-block">
                                <input type="text" id="value" name="value" value="${data_dict.value}" placeholder="请输入数据值" autocomplete="off" class="layui-input" lay-verify="chk_null" lay-verType="tips">
                        </div>
                    </div>
                    <div class="layui-col-md4">
                        <label class="layui-form-label"><span style="color: red;">*</span>标签名</label>
                        <div class="layui-input-block">
                            <input type="text" id="label" name="label"  value="${data_dict.label}" placeholder="请输入标签名" autocomplete="off" class="layui-input" lay-verify="chk_null" lay-verType="tips">
                        </div>
                    </div>
                    <div class="layui-col-md4">
                        <label class="layui-form-label"><span style="color: red;">*</span>类型</label>
                        <div class="layui-input-block">
                            <input type="text" id="type" name="type"  value="${data_dict.type}" placeholder="请输入类型" autocomplete="off" class="layui-input" lay-verify="chk_null" lay-verType="tips">
                        </div>
                    </div>

                    <div class="layui-col-md4">
                        <label class="layui-form-label"><span style="color: red;">*</span>描述</label>
                        <div class="layui-input-block">
                            <input type="text" id="description" name="description" value="${data_dict.description}" placeholder="请输入描述" autocomplete="off" class="layui-input" lay-verify="chk_null" lay-verType="tips">
                        </div>
                    </div>
                    <div class="layui-col-md4">
                        <label class="layui-form-label"><span style="color: red;">*</span>排序</label>
                        <div class="layui-input-block">
                            <input type="text" id="sort" name="sort"  value="${data_dict.sort}" placeholder="请输入排序" autocomplete="off" class="layui-input" lay-verify="chk_null|number" lay-verType="tips">
                        </div>
                    </div>
                    <div class="layui-col-md4">
                        <label class="layui-form-label">备注信息</label>
                        <div class="layui-input-block">
                            <input type="text" id="remarks" name="remarks"  value="${data_dict.remarks}" placeholder="请输入备注信息" autocomplete="off" class="layui-input" lay-verType="tips">
                        </div>
                    </div>


                <div class="layui-form-item  layui-col-md12" style="text-align: center">
                    <button lay-submit lay-filter="saveSysDict" class="layui-btn layui-btn-normal" lay-text="保存">
                        <i class="layui-icon layui-icon-release"></i>保存
                    </button>
                    <button type="reset" class="layui-btn layui-btn-normal" lay-text="重置">
                        <i class="layui-icon layui-icon-refresh"></i>重置
                    </button>
                    <button type="button" class="layui-btn layui-btn-normal" lay-text="返回" onclick="javascript: location.href='${basePath}dict/findDictList'">
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
    }).use(['jquery', 'index', 'form'], function () {
        $ = layui.jquery;//初始化jquery
        var form = layui.form;//初始化form组件
        //自定义文本框、下拉框校验规则
        form.verify(util_from_check);

        //监听提交form点击事件
        form.on('submit(saveSysDict)', function (data) {
            $("#editFrm").submit();
        });

    });
</script>
</body>
</html>