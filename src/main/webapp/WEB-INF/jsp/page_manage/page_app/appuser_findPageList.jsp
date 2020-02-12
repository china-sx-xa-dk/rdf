<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../baseJSP.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>APP用户列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${baseStatic}layuiadmin/style/admin.css" media="all">
</head>
<body>
<div class="layui-fluid">
    <!-- ----------------------正文代码start---------------------- -->

    <!-- 查询条件 start -->
    <form action="${basePath}manage/appuser/findPageList" method="post" id="searchFrm" class="layui-form layui-card layui-form-pane">
        <input type="hidden" name="currentPage" id="SCH_CUR_PAGE" value="${page.currentPage}"/>
        <input type="hidden" name="showCount" id="SCH_PAGE_SHOW_COUNT" value="${page.showCount}"/>

        <div class="layui-card-body layui-row layui-col-space15">
            <div class="layui-col-md12 layui-col-space15">
                <div class="layui-inline">
                    <label class="layui-form-label">工号</label>
                    <div class="layui-input-block"><input type="text" id="jobNumber" name="jobNumber" value="${condition.jobNumber}" placeholder="请输入工号" autocomplete="off" class="layui-input"></div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-block"><input type="text" id="userName" name="userName" value="${condition.userName}" placeholder="请输入姓名" autocomplete="off" class="layui-input"></div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <select id="userSex" name="userSex" lay-verify="">
                            <option value="">全部</option>
                            <c:if test="${appUserSexList != null && appUserSexList.size() > 0 }">
                                <c:forEach var="item" items="${appUserSexList}" varStatus="i">
                                    <option value="${item.key}"<c:if test="${condition.userSex==item.key}">selected</c:if>>${item.value}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">登录名</label>
                    <div class="layui-input-block"><input type="text" id="loginName" name="loginName" value="${condition.loginName}" placeholder="请输入登录名" autocomplete="off" class="layui-input"></div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">联系电话</label>
                    <div class="layui-input-block"><input type="text" id="userMobile" name="userMobile" value="${condition.userMobile}" placeholder="请输入联系电话" autocomplete="off" class="layui-input"></div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">区域</label>
                    <div class="layui-input-block">
                        <select id="orgId" name="orgId" lay-verify="">
                            <option value="">全部</option>
                            <c:if test="${sysOrgList != null && sysOrgList.size() > 0 }">
                                <c:forEach var="item" items="${sysOrgList}" varStatus="i">
                                    <option value="${item.orgId}"<c:if test="${condition.orgId==item.orgId}">selected</c:if>>${item.orgName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">部门</label>
                    <div class="layui-input-block">
                        <select id="userDept" name="userDept" lay-verify="">
                            <option value="">全部</option>
                            <c:if test="${appUserDeptList != null && appUserDeptList.size() > 0 }">
                                <c:forEach var="item" items="${appUserDeptList}" varStatus="i">
                                    <option value="${item.key}"<c:if test="${condition.userDept==item.key}">selected</c:if>>${item.value}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">类型</label>
                    <div class="layui-input-block">
                        <select id="userType" name="userType" lay-verify="">
                            <option value="">全部</option>
                            <c:if test="${appUserTypeList != null && appUserTypeList.size() > 0 }">
                                <c:forEach var="item" items="${appUserTypeList}" varStatus="i">
                                    <option value="${item.key}"<c:if test="${condition.userType==item.key}">selected</c:if>>${item.value}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-block">
                        <select id="userState" name="userState" lay-verify="">
                            <option value="">全部</option>
                            <c:if test="${appUserStateList != null && appUserStateList.size() > 0 }">
                                <c:forEach var="item" items="${appUserStateList}" varStatus="i">
                                    <option value="${item.key}"<c:if test="${condition.userState==item.key}">selected</c:if>>${item.value}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn" lay-submit lay-filter="adminUserSearch" >
                        <i class="layui-icon layui-icon-search"></i>
                    </button>
                </div>
                <div class="layui-inline">
                    <button id="resetBtn" type="button" class="layui-btn">重置</button>
                </div>
                <div class="layui-inline">
                    <button id="exportBtn" type="button" class="layui-btn">导出</button>
                </div>
            </div>
        </div>
    </form>
    <!-- 查询条件 end -->


    <!-- 查询结果列表 start -->
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">

                    <table class="layui-table">
                        <thead>
                        <tr>
                            <th style="width: 12%">工号</th>
                            <th style="width: 10%">姓名</th>
                            <th style="width: 8%">性别</th>
                            <th style="width: 10%">登录名</th>
                            <th style="width: 10%">联系电话</th>
                            <th style="width: 10%">邮箱</th>
                            <th style="width: 8%">区域</th>
                            <th style="width: 8%">部门</th>
                            <th style="width: 12%">类型</th>
                            <th style="width: 8%">状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${data_list != null && data_list.size() > 0 }">
                                <c:forEach var="item" items="${data_list}" varStatus="i">
                                    <tr>
                                        <td>${item.jobNumber}</td>
                                        <td>${item.userName}</td>
                                        <td>${item.userSexName}</td>
                                        <td>${item.loginName}</td>
                                        <td>${item.userMobile}</td>
                                        <td>${item.userEmail}</td>
                                        <td>${item.orgName}</td>
                                        <td>${item.userDeptName}</td>
                                        <td>${item.userTypeName}</td>
                                        <td>${item.userStateName}</td>
                                        <td align="center">
                                            <a class="layui-btn layui-btn-xs" lay-href="${basePath}manage/appuser/jump_detail?id=${item.id}" lay-text="详情" class="e_tb_btn edit">详情</a><br/>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:when test="${data_list == null || data_list.size() == 0 }">
                                <tr><td colspan="11" align="center">没有查询到数据</td></tr>
                            </c:when>
                        </c:choose>
                        </tbody>
                    </table>

                    <%-- 分页 start --%>
                    <div class="layui-card-body layui-row layui-col-space15">
                        <div class="layui-col-md12">
                            <div class="layui-col-md11"><div id="listTablePage"></div></div><!--分页-->
                        </div>
                    </div>
                    <%-- 分页 end --%>

                </div>
            </div>
        </div>
    </div>
    <!-- 查询结果列表 end -->

    <!-- ----------------------正文代码end---------------------- -->
</div>
<script>
    layui.config({
        base: '${baseStatic}layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['jquery','index','form','laypage'],function(){
        $ = layui.jquery;//初始化jquery
        var form = layui.form;//初始化form组件
        var laypage = layui.laypage;//初始化分页组件

        //监听提交form点击事件
        form.on('submit(adminUserSearch)', function(data){
            layer.msg('正在加载数据', {icon:16,shade:0.01,time:0,offset: 't'});
            //layer.msg(JSON.stringify(data.field));
            //如果需要ajax提交则 return false，data.field即为表单数据的json数据
        });

        //分页
        laypage.render({
            elem: document.getElementById('listTablePage'),count:${page.totalResult},curr:${page.currentPage},limit:${page.showCount},
            layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],jump: function(obj,first){
                if(!first){//第一次不执行
                    layer.msg('正在加载数据', {icon:16,shade:0.01,time:0,offset: 't'});
                    $("#SCH_CUR_PAGE").val(obj.curr);
                    $("#SCH_PAGE_SHOW_COUNT").val(obj.limit);
                    $("#searchFrm").submit();
                }
            }
        });

        $("#resetBtn").click(function(){
            window.location.href="${basePath}manage/appuser/findPageList";
        });

        $("#exportBtn").click(function(){
            window.location.href="${basePath}manage/appuser/export_AppUser?jobNumber=" + $("#jobNumber").val()
                + "&userName=" + $("#userName").val()
                + "&userSex=" + $("#userSex").val()
                + "&loginName=" + $("#loginName").val()
                + "&userMobile=" + $("#userMobile").val()
                + "&orgId=" + $("#orgId").val()
                + "&userDept=" + $("#userDept").val()
                + "&userType=" + $("#userType").val()
                + "&userState=" + $("#userState").val();
        });

    });
</script>
</div>
</body>
</html>