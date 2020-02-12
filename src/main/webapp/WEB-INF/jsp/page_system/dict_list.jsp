<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../baseJSP.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数据字典</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${baseStatic}layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${baseStatic}layuiadmin/style/admin.css" media="all">
</head>
<body>
<div class="layui-fluid">
<!-- ----------------------正文代码start---------------------- -->

    <%-- 功能导航栏 --%>
    <div class="tool_top">
        <div class="layui-inline">
            <a href="${basePath}dict/goAddOrEdit">
                <button type="button" class="layui-btn layui-btn-normal" lay-text="新增数据字典">
                    <i class="layui-icon layui-icon-add-1"></i>新增
                </button>
            </a>
        </div>
    </div>

    <!-- 查询条件 start -->
    <form action="${basePath}dict/findDictList" method="post" id="searchFrm" class="layui-form layui-card layui-form-pane">
        <input type="hidden" name="currentPage" id="SCH_CUR_PAGE" value="${page.currentPage}"/>
        <input type="hidden" name="showCount" id="SCH_PAGE_SHOW_COUNT" value="${page.showCount}"/>


        <div class="layui-card-body layui-row layui-col-space15">
            <div class="layui-col-md12 layui-col-space15">

                <div class="layui-inline">
                    <label class="layui-form-label">数据值</label>
                    <div class="layui-input-block"><input type="text" name="value" value="${sysDict.value}" placeholder="请输入数据值" autocomplete="off" class="layui-input"></div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">标签名</label>
                    <div class="layui-input-block"><input type="text" name="label" value="${sysDict.label}" placeholder="请输入标签名" autocomplete="off" class="layui-input"></div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">类型</label>
                    <div class="layui-input-block"><input type="text" name="type" value="${sysDict.type}" placeholder="请输入类型" autocomplete="off" class="layui-input"></div>
                </div>

                <div class="layui-inline">
                    <button class="layui-btn" lay-submit lay-filter="SysDictSearch" >
                        <i class="layui-icon layui-icon-search"></i>
                    </button>
                </div>
                <div class="layui-inline">
                    <button id="resetBtn" class="layui-btn" type="button" >重置</button>
                </div>
                <div class="layui-inline" title="刷新数据字典缓存">
                    <button id="refresh" class="layui-btn" type="button" >
                        <i class="layui-icon layui-icon-refresh"></i>
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
                                <th style="width: 15%;text-align: center;">数据值</th>
                                <th style="width: 15%;text-align: center;">标签名</th>
                                <th style="width: 15%;text-align: center;">类型</th>
                                <th style="width: 20%;text-align: center;">描述</th>
                                <th style="width: 10%;text-align: center;">排序</th>
                                <th style="width: 25%;text-align: center;">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${sysDictList != null && sysDictList.size() > 0 }">
                                <c:forEach var="item" items="${sysDictList}" varStatus="i">
                                    <tr>
                                        <td>${item.value}</td>
                                        <td>${item.label}</td>
                                        <td>${item.type}</td>
                                        <td style="text-align: center;">${item.description}</td>
                                        <td style="text-align: center;">${item.sort}</td>
                                        <td align="center">
                                                <%--<a class="layui-btn layui-btn-primary layui-btn-xs">查看</a>--%>
                                                <a class="layui-btn layui-btn-normal layui-btn-xs" href="${basePath}dict/goAddOrEdit?id=${item.id}" lay-text="编辑数据字典" class="e_tb_btn edit">编辑</a>
                                                <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="delItem(${item.id})">删除</a>
                                                    <a class="layui-btn layui-btn-normal layui-btn-xs" href="${basePath}dict/goAddOrEdit?id=${item.id}&type=${item.type}" lay-text="添加键值" class="e_tb_btn edit">添加键值</a>

                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:when test="${sysDictList == null || sysDictList.size() == 0 }">
                                <tr><td colspan="6" align="center">没有查询到数据</td></tr>
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

        $("#resetBtn").click(function(){
            window.location.href="${basePath}dict/findDictList";
        });

        $("#refresh").click(function(){
            $.ajax({
                type: "post",
                url: "${basePath}dict/refreshRedisSysDictHand",
                traditional: true,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (result) {
                    if (result.code == 200) {
                        layer.msg('数据字典缓存刷新成功', {offset: 'auto'});
                    } else {
                        layer.msg('数据字典缓存刷新成功，请稍后再试', {offset: 'auto'});
                    }
                }
            });
        });

        //监听提交form点击事件
        form.on('submit(SysDictSearch)', function(data){
            layer.msg('正在加载数据', {icon:16,shade:0.01,time:0,offset: 't'});
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

    });

    //删除用户
    function delItem(id){
        layer.confirm('确定删除该条数据吗？',{offset: 'auto'}, function(index) {
            window.location.assign("${ajaxPath}dict/deleteSysDict?id="+id);
        });
    }
</script>
</div>
</body>
</html>