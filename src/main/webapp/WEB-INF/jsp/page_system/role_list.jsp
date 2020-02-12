<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../baseJSP.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>角色管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
</head>
<body>
<div class="layui-fluid">
    <!-- ----------------------正文代码start---------------------- -->

    <%-- 功能导航栏 --%>
    <c:if test="${sessionScope.currentUser.roleId == 1 }">
        <div class="tool_top">
            <div class="layui-inline">
                <a href="${basePath}role/goAddOrEdit">
                    <button type="button" class="layui-btn layui-btn-normal" lay-text="新增角色">
                        <i class="layui-icon layui-icon-add-1"></i>新增
                    </button>
                </a>
            </div>
        </div>
    </c:if>

    <!-- 查询条件 start -->
    <form action="${basePath}role/findRoleList" method="post" id="searchFrm" class="layui-form layui-card layui-form-pane">
        <input type="hidden" name="currentPage" id="SCH_CUR_PAGE" value="${page.currentPage}"/>
        <input type="hidden" name="showCount" id="SCH_PAGE_SHOW_COUNT" value="${page.showCount}"/>

        <div class="layui-card-body layui-row layui-col-space15">
            <div class="layui-col-md12 layui-col-space15">

                <div class="layui-inline">
                    <label class="layui-form-label">角色名称</label>
                    <div class="layui-input-block"><input type="text" name="roleName" value="${role.roleName }" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">角色状态</label>
                    <div class="layui-input-block">
                        <select name="roleState">
                            <option value="">全部</option>
                            <option value="0" <c:if test="${role.roleState==0}">selected</c:if>>启用</option>
                            <option value="1" <c:if test="${role.roleState==1}">selected</c:if>>禁用</option>
                        </select>
                    </div>
                </div>

                <div class="layui-inline">
                    <button class="layui-btn" lay-submit lay-filter="roleListSearch">
                        <i class="layui-icon layui-icon-search"></i>
                    </button>
                </div>
            </div>
        </div>
    </form>
    <!-- 查询条件 end -->
    <sys:message content="${message}"/>
    <!-- 查询结果列表 start -->
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">

                    <table class="layui-table">
                        <thead>
                            <tr>
                                <th style="width: 5%;text-align: center;">编号</th>
                                <th style="width: 12%">角色名称</th>
                                <th style="width: 34%">角色描述</th>
                                <th style="width: 10%;text-align: center;">角色状态</th>
                                <th style="width: 7%;text-align: center;">修改人</th>
                                <th style="width: 18%;text-align: center;">修改时间</th>
                                <th style="width: 15%;text-align: center;">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${data_list != null && data_list.size() > 0 }">
                                <c:forEach var="item" items="${data_list}" varStatus="i">
                                    <tr>
                                        <td align="center">
                                                ${i.index + (page.currentPage - 1) * page.showCount + 1}
                                        </td>
                                        <td>${item.roleName}</td>
                                        <td>${item.roleDesc}</td>
                                        <td align="center">
                                            <c:if test="${item.roleState==0}">
                                                <span class="layui-bg-green" style="padding: 3px;">启用</span>
                                            </c:if>
                                            <c:if test="${item.roleState==1}">
                                                <span class="layui-bg-red" style="padding: 3px;">禁用</span>
                                            </c:if>
                                        </td>
                                        <td>${item.updateUser}</td>
                                        <td align="center">
                                            <fmt:formatDate value="${item.roleUpdateDate}" pattern="yyyy-MM-dd  HH:mm:ss"/>
                                        </td>
                                        <td align="center">
                                            <a class="layui-btn layui-btn-normal layui-btn-xs"  href="${basePath}role/goRoleGrant?roleId=${item.roleId}" lay-text="角色授权">授权</a>
                                            <c:if test="${sessionScope.currentUser.roleId == 1 }">
                                                <a class="layui-btn layui-btn-normal layui-btn-xs" href="${basePath}role/goAddOrEdit?roleId=${item.roleId}" lay-text="编辑角色">编辑</a>
                                                <a class="layui-btn layui-btn-danger layui-btn-xs" href="javascript:void(0);" onclick="role_del('${item.roleId}');">删除</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:when test="${data_list == null || data_list.size() == 0 }">
                                <tr>
                                    <td colspan="7" align="center">没有查询到数据</td>
                                </tr>
                            </c:when>
                        </c:choose>
                        </tbody>
                    </table>

                    <!--分页-->
                    <div id="listTablePage"></div>
                </div>
            </div>
        </div>
    </div>
    <!-- 查询结果列表 end -->

    <!-- ----------------------正文代码end---------------------- -->
</div>
<script src="${baseStatic}layuiadmin/layui/layui.js"></script>
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
        form.on('submit(roleListSearch)', function(data){
            layer.msg('正在加载数据', {icon:16,shade:0.01,time:0,offset: 'auto'});
        });
        //分页
        laypage.render({
            elem: document.getElementById('listTablePage'),count:${page.totalResult},curr:${page.currentPage},limit:${page.showCount},
            layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],jump: function(obj,first){
                if(!first){//第一次不执行
                    layer.msg('正在加载数据', {icon:16,shade:0.01,time:0,offset: 'auto'});
                    $("#SCH_CUR_PAGE").val(obj.curr);
                    $("#SCH_PAGE_SHOW_COUNT").val(obj.limit);
                    $("#searchFrm").submit();
                }
            }
        });

    });


    //删除用户
    function role_del(roleId) {
        layer.confirm('确定删除该条数据吗？', {offset: 'auto'}, function (index) {
            window.location.assign("${basePath}role/deleteRole?roleId="+roleId);
        });
    }

</script>
</div>
</body>
</html>