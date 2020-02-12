<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../baseJSP.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>代码生成</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
</head>
<body>
<div class="layui-fluid">
    <!-- 查询条件 start -->
    <form action="${ajaxPath}/database/findTableList" method="post" id="searchFrm" class="layui-form layui-card layui-form-pane">
        <input type="hidden" name="currentPage" id="SCH_CUR_PAGE" value="${page.currentPage}"/>
        <input type="hidden" name="showCount" id="SCH_PAGE_SHOW_COUNT" value="${page.showCount}"/>

        <div class="layui-card-body layui-row layui-col-space15">
            <div class="layui-col-md12 layui-col-space15">
                <div class="layui-inline">
                    <button id="goSetUp" type="button" class="layui-btn layui-btn-normal" lay-text="设置代码生成配置">
                        <i class="layui-icon layui-icon-set"></i>设置代码生成配置
                    </button>
                </div>
                <div class="layui-inline">
                    <button id="genCode" type="button" class="layui-btn layui-btn-normal" lay-text="生成代码到路径">
                        <i class="layui-icon layui-icon-ok"></i>生成代码到路径
                    </button>
                </div>
                <div class="layui-inline">
                    <button id="downCode" type="button" class="layui-btn layui-btn-normal" lay-text="生成代码文件下载">
                        <i class="layui-icon layui-icon-download-circle"></i>生成代码文件下载
                    </button>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">表名</label>
                    <div class="layui-input-block"><input type="text" name="tableName" value="${tableName}" placeholder="请输入表名" autocomplete="off" class="layui-input"></div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="tableSearch" >
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
                            <th style="width: 10px;text-align: center;"><input type="checkbox" name="all" class="table-checkbox" onclick="checkAll();"/></th>
                            <th style="width: 25%;">表名</th>
                            <th style="width: 25%;">表注释</th>
                            <th style="width: 25%;">表类型</th>
                            <th style="width: 25%;">创建时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${data_list != null && data_list.size() > 0 }">
                                <c:forEach var="item" items="${data_list}" varStatus="i">
                                    <tr>
                                        <td style="text-align: center;"><input type="checkbox" tableName="${item.tableName}" name="single" class="table-checkbox" onclick="checkThis();"/></td>
                                        <td>${item.tableName}</td>
                                        <td>${item.tableComment}</td>
                                        <td>${item.engine}</td>
                                        <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:when test="${data_list == null || data_list.size() == 0 }">
                                <tr><td colspan="9" align="center">暂无数据</td></tr>
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
        form.on('submit(tableSearch)', function(data){
            layer.msg('正在加载数据', {icon:16,shade:0.01,time:0,offset: 'auto'});
        });

        //分页处理
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

        //生成代码到本地
        $('#genCode').on('click', function(){
            var tableNames = "";
            $("input[name='single']").each(function(){
                if($(this).is(":checked")==true){
                    tableNames += $(this).attr("tableName") + "-";
                }
            });
            if(tableNames.length == 0){
                layer.msg('请选择要生成的数据表！');
                return false;
            }else{
                tableNames = tableNames.substring(0,tableNames.length-1);
            }
            var content = "是否确认将选中的数据表生成本地代码?";
            layer.confirm(content, {btn: ['确定', '取消'], title: "生成代码确认"}, function () {
                $.ajax({
                    type:"post",
                    url:"${ajaxPath}/database/" + tableNames,
                    data:{},
                    dataType:"json",
                    success:function(data) {
                        if(data.code == 200){
                            layer.msg('生成代码成功!',{offset: 'auto'});
                            if (data.data) {
                                var content1 = "是否打开代码输出路径(代码输出文件夹)?";
                                layer.confirm(content1, {btn: ['确定', '取消'], title: "打开文件位置"}, function () {
                                    layer.close(layer.index);
                                    $.ajax({
                                        type:"get",
                                        url:"${ajaxPath}/database/open",
                                        data:{},
                                        dataType:"json",
                                        success:function(data) {
                                            if(data.code != 200){
                                                layer.msg('打开文件位置异常!',{offset: 'auto'});
                                            }
                                        },
                                    });
                                });
                            }
                        }else{
                            if(data.msg != null && data.msg != ""){
                                layer.msg(data.msg,{offset: 'auto'});
                            }else{
                                layer.msg('生成代码异常!',{offset: 'auto'});
                            }
                        }
                    },
                });
            });
        });

        //生成代码到zip下载
        $('#downCode').on('click', function(){
            var tableNames = "";
            $("input[name='single']").each(function(){
                if($(this).is(":checked")==true){
                    tableNames += $(this).attr("tableName") + "-";
                }
            });
            if(tableNames.length == 0){
                layer.msg('请选择要生成的数据表！');
                return false;
            }else{
                tableNames = tableNames.substring(0,tableNames.length-1);
            }
            var content = "是否确认将选中的数据表生成代码文件下载?";
            layer.confirm(content, {btn: ['确定', '取消'], title: "生成下载确认"}, function () {
                location.href = '${ajaxPath}/database/zip/' + tableNames;
                layer.msg('若浏览器已开始进行zip下载，则代码生成成功。<br>若无响应，请检查配置或排除其他问题后重新生成！', {
                    time: 20000, //20s后自动关闭
                    btn: ['确定'],
                    btnAlign: 'c' //按钮居中
                });
            });
        });

        //打开代码生成配置页面
        $('#goSetUp').on('click', function(){
            location.href = '${ajaxPath}/database/getConfig';
        });

    });
</script>
</body>
</html>