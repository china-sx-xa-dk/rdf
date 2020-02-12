﻿<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../baseJSP.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编辑用户信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <%-- zTree引用 start --%>
    <link type="text/css" rel="stylesheet" href="${baseStatic}js/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.css" media="all"/>
    <link type="text/css" rel="stylesheet" href="${baseStatic}js/jquery-ztree/3.5.12/css/metroStyle/metroStyle.css" media="all"/>
    <link type="text/css" rel="stylesheet" href="${baseStatic}js/jquery-ztree/3.5.12/css/metroStyle/index.css" media="all"/>
    <script src="${baseStatic}js/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js"></script>
    <script src="${baseStatic}js/common/selectTree.js"></script>
    <%-- zTree引用 end --%>
    <%-- 表单校验引用 start --%>
    <script src="${baseStatic}layuiadmin/util_form_check.js"></script>
    <%-- 表单校验引用 end --%>
</head>
<body>
<div class="layui-fluid">
    <!-- ----------------------表单信息start---------------------- -->
    <form method="post" action="${basePath}admin/doAddOrEdit" id="editFrm" class="layui-form layui-card layui-form-pane">
        <input type="hidden" id="adminId" name="adminId" value='${data_admin.adminId}'/>
        <div class="layui-card-header">用户信息${not empty data_admin.adminId?'修改':'新增'}</div>
        <sys:message content="${message}"/>
        <div class="layui-card-body layui-row layui-col-space15">
            <div class="layui-col-md12 layui-col-space15">

                <div class="layui-col-md4">
                    <label class="layui-form-label"><span style="color: red;">*</span>登录账号:</label>
                    <div class="layui-input-block">
                        <input name="loginName" value="${data_admin.loginName}" placeholder="请输入登录账号" lay-verify="chk_null" lay-verType="tips" type="text" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label"><span style="color: red;">*</span>真实姓名:</label>
                    <div class="layui-input-block">
                        <input name="adminName" value="${data_admin.adminName}" placeholder="请输入真实姓名" lay-verify="chk_null" lay-verType="tips" type="text" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label"><span style="color: red;">*</span>手机号码:</label>
                    <div class="layui-input-block">
                        <input name="adminMobile" value="${data_admin.adminMobile}" placeholder="请输入手机号码" lay-verify="chk_null|chk_tel_phone_number" lay-verType="tips" type="text" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label"><span style="color: red;">*</span>所属组织:</label>
                    <div class="layui-input-block">
                        <div id="orgId" class="layui-form-select select-tree">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label"><span style="color: red;">*</span>用户角色:</label>
                    <div class="layui-input-block">
                        <select name="roleId" lay-verify="chk_null" lay-verType="tips" placeholder="请选择用户角色">
                            <option value="">请选择角色</option>
                            <c:forEach items="${roleList}" var="role">
                                <c:if test="${role.roleId != 1}">
                                    <option value="${role.roleId}"
                                            <c:if test="${data_admin.roleId == role.roleId }">selected</c:if>>${role.roleName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label"><span style="color: red;">*</span>身份证号:</label>
                    <div class="layui-input-block">
                        <input name="adminCardno" value="${data_admin.adminCardno}" placeholder="请输入身份证号" lay-verify="chk_null|identity" lay-verType="tips" type="text" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label"><span style="color: red;">*</span>用户性别:</label>
                    <div class="layui-input-block">
                        <select name="adminSex">
                            <option value="0" <c:if test="${data_admin.adminSex==0}">selected</c:if>>未知</option>
                            <c:forEach items="${fns:getDictList('sex')}" var="dict">
                                <option value="${dict.value}" <c:if test="${data_admin.adminSex==dict.value}">selected</c:if>>${dict.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label"><span style="color: red;">*</span>是否启用:</label>
                    <div class="layui-input-block">
                        <select name="adminState" class="frm_select">
                            <c:forEach items="${fns:getDictList('adminState')}" var="dict">
                                <option value="${dict.value}" <c:if test="${data_admin.adminState==dict.value}">selected</c:if>>${dict.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label"><span style="color: red;">*</span>邮箱账号:</label>
                    <div class="layui-input-block">
                        <input name="adminEmail" value="${data_admin.adminEmail}" placeholder="请输入邮箱地址" lay-verify="chk_null|email" lay-verType="tips" type="text" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" style="text-align: center">
                    <button lay-submit lay-filter="saveAdminUser" class="layui-btn layui-btn-normal" lay-text="保存">
                        <i class="layui-icon layui-icon-release"></i>保存
                    </button>
                    <button id="reset" type="reset" class="layui-btn layui-btn-normal" lay-text="重置">
                        <i class="layui-icon layui-icon-refresh"></i>重置
                    </button>
                    <button type="button" class="layui-btn layui-btn-normal" lay-text="返回" onclick="javascript: location.href='${basePath}admin/findAdminList'">
                        <i class="layui-icon layui-icon-close"></i>返回
                    </button>
                </div>
            </div>
        </div>
    </form>
    <!-- ----------------------表单信息end---------------------- -->
</div>
<script>
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
        form.on('submit(saveAdminUser)', function (data) {
            if(validateData()){
                var loading = layer.msg('正在提交数据', {icon: 16, shade: 0.2, time: 0, offset: 'auto'});
                $("#editFrm").submit();
            }else{
                return false;
            }
        });
    });

    //表单校验
    function validateData() {
        var orgId = $("input[name='orgId']").val();
        if(orgId == undefined || orgId == "" || orgId == null){
            layer.msg('请选择所属组织', {offset: 'auto'});
            return false;
        }
        return true;
    }

    //获取组织机构数据
    var zNodes =  JSON.parse('${orgTreeList}');
    var selectIds = '${data_admin.orgId}';
    //设置下拉树选择
    initSelectTree(zNodes,"所属组织机构","orgId",selectIds,false);

</script>
</body>
</html>