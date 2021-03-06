<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../baseJSP.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>系统日志管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
</head>
<body>
<div class="layui-fluid">
    <!-- ----------------------正文代码start---------------------- -->

    <%-- 功能导航栏 --%>
    
    <!-- 查询条件 start -->
    <form action="${basePath}sysLogOperate/get_list" method="post" id="searchFrm" class="layui-form layui-card layui-form-pane">
        <input type="hidden" name="currentPage" id="SCH_CUR_PAGE" value="${page.currentPage}"/>
        <input type="hidden" name="showCount" id="SCH_PAGE_SHOW_COUNT" value="${page.showCount}"/>

        <div class="layui-card-body layui-row layui-col-space15">
            <div class="layui-col-md12 layui-col-space15">

                <div class="layui-inline">
                    <label class="layui-form-label">功能名称</label>
                    <div class="layui-input-block"><input type="text" name="funcName" value="${condition.funcName }" placeholder="请输入功能名称" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">操作类型</label>
                    <div class="layui-input-block"><input type="text" name="visitType" value="${condition.visitType }" placeholder="请输入操作类型" autocomplete="off" class="layui-input">
                    </div>
                </div>


                <div class="layui-inline">
                    <button class="layui-btn" lay-submit lay-filter="sysLogOperateListSearch">
                        <i class="layui-icon layui-icon-search"></i>
                    </button>
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
                            <th style="width: 10%;">操作用户Id</th>
                            <th style="width: 10%">操作用户账号</th>
                            <th style="width: 10%">功能名称</th>
                            <th style="width: 10%">操作类型</th>
                            <th style="width: 20%">操作描述</th>
                            <th style="width: 10%">操作时间</th>
                            <%--<th style="width: 10%;text-align: center;">操作</th>--%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${data_list != null && data_list.size() > 0 }">
                                <c:forEach var="item" items="${data_list}" varStatus="i">
                                    <tr>
                                        <td>${item.userId}</td>
                                        <td>${item.loginName}</td>
                                        <td>${item.funcName}</td>
                                        <td>${item.visitType}</td>
                                        <td>${item.operateDesc}</td>
                                        <td><fmt:formatDate value="${item.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <%--<td style="text-align: center;">--%>
                                            <%--<a class="layui-btn layui-btn-normal layui-btn-xs" lay-href="${basePath}sysLogOperate/goAddOrEdit?logId=${item.logId}" lay-text="查看系统日志">详情</a>--%>
                                            <%--&lt;%&ndash;<a class="layui-btn layui-btn-danger layui-btn-xs" href="javascript:void(0);" onclick="delItem('${item.logId}');">删除</a>&ndash;%&gt;--%>
                                        <%--</td>--%>
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
        form.on('submit(sysLogOperateListSearch)', function(data){
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
    function delItem(configId) {
        layer.confirm('确定删除该条数据吗？', {offset: 'auto'}, function (index) {
            var loading = layer.msg('正在删除数据', {icon: 16, shade: 0.2, time: 0, offset: 'auto'});
            var url = "${basePath}sysLogOperate/delete";
            var data = {"logId": logId};
            $.post(url, data, function (result) {
                layer.close(loading);//关闭loading
                if (result == 'success') {
                    layer.msg('数据已删除，正在刷新', {offset: 'auto'});
                    window.setTimeout(function () {
                        $("#searchFrm").submit();
                    }, 1500);
                } else {
                    layer.msg('删除失败，请稍后再试!', {offset: 'auto'});
                }
            });
        });
    }

</script>
</div>
</body>
</html>