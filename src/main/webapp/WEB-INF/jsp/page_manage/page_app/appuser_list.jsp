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
</head>
<body>
<div class="layui-fluid">
    <!-- ----------------------正文代码start---------------------- -->

    <div id="importBox" style="display: none;">
        <form id="importForm" action="${basePath}web/appuser/importExecl" method="post" enctype="multipart/form-data"
              class="form-search" style="padding-left:20px;text-align:center;"><br/>
            <input id="importFile" name="file" type="file" /><br/><br/>　
            <span>导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！</span>　<br/><br/>
            <button id="btnImportSubmit"style="height: 30px; line-height: 30px;" type="submit" class="layui-btn">导入</button>
            <a href="${basePath}web/appuser/template">下载模板</a>
        </form>
    </div>
    <%-- 功能导航栏 --%>
    <div class="tool_top">
        <div class="layui-inline">
            <button type="button" onclick="goForm('0')" class="layui-btn layui-btn-normal" lay-text="新增数据">
                <i class="layui-icon layui-icon-add-1"></i>新增
            </button>
        </div>
    </div>


    <!-- 查询条件 start -->
    <form action="${basePath}web/appuser/get_list" method="post" id="searchFrm" class="layui-form layui-card layui-form-pane">
        <input type="hidden" name="currentPage" id="SCH_CUR_PAGE" value="${page.currentPage}"/>
        <input type="hidden" name="showCount" id="SCH_PAGE_SHOW_COUNT" value="${page.showCount}"/>

        <div class="layui-card-body layui-row layui-col-space15">
            <div class="layui-col-md12 layui-col-space15">

                <div class="layui-inline">
                    <label class="layui-form-label">登录账户</label>
                    <div class="layui-input-block"><input type="text" id="loginName" name="loginName" value="${condition.loginName}" placeholder="请输入登录名" autocomplete="off" class="layui-input"></div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-block"><input type="text" id="userName" name="userName" value="${condition.userName}" placeholder="请输入用户名" autocomplete="off" class="layui-input"></div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <select id="userSex" name="userSex">
                            <option value="">全部</option>
                            <c:forEach items="${fns:getDictList('sex')}" var="sex">
                                <option value="${sex.value}" <c:if test="${condition.userSex==sex.value}">selected</c:if>>${sex.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">联系电话</label>
                    <div class="layui-input-block"><input type="text" id="userMobile" name="userMobile" value="${condition.userMobile}" placeholder="请输入联系电话" autocomplete="off" class="layui-input"></div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn" lay-submit lay-filter="SearchBtn" >
                        <i class="layui-icon layui-icon-search"></i>
                    </button>
                </div>
                <div class="layui-inline">
                    <button id="resetBtn" class="layui-btn" type="button" >重置</button>
                </div>
                <div class="layui-inline">
                    <button onclick="exportInfo()" id="btnExport" type="button" class="layui-btn">导出</button>
                    <button id="btnImport" type="button" class="layui-btn">导入</button>
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
                            <th>头像</th>
                            <th>登录账户</th>
                            <th>用户名</th>
                            <th>性别</th>
                            <th>联系电话</th>
                            <th>邮箱</th>
                            <th>最后一次登录时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${data_list != null && data_list.size() > 0 }">
                                <c:forEach var="item" items="${data_list}" varStatus="i">
                                    <tr>
                                        <td><c:if test="${!empty item.headPhoto}"><img src="${item.headPhoto}" style="height: 50px; width: 50px;"/></c:if></td>
                                        <td>${item.loginName}</td>
                                        <td>${item.userName}</td>
                                        <td>${fns:getDictLabel(item.userSex, 'sex', '')}</td>
                                        <td>${item.userMobile}</td>
                                        <td>${item.userEmail}</td>
                                        <td><fmt:formatDate value="${item.userLastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td align="center">
                                            <a class="layui-btn layui-btn-xs" onclick="goForm('${item.id}')" lay-text="编辑" class="e_tb_btn edit">编辑</a>
                                            &nbsp;&nbsp;
                                            <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="delItem('${item.id}')">删除</a>
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
        form.on('submit(SearchBtn)', function(data){
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

        $("#resetBtn").click(function(){
            window.location.href="${basePath}web/appuser/get_list";
        });
    });

    //打开表单弹窗
    function goForm(id){
        var title = "新增";
        if(id != "0"){
            title = "修改";
        }
        layer.open({
            type: 2
            ,title: 'APP用户'+title
            ,content: '${basePath}web/appuser/jump_edit?id='+id
            ,area: ['780px', '700px']
            ,offset: 'auto'
            ,resize: false
            ,fixed : false
            ,yes: function(index, layero){}
            ,success: function(layero, index){}
            ,cancel: function(index, layero){
                //弹出页面关闭回调方法
                var body = layer.getChildFrame('body', index);
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.returnList();
            }
        });
    }

    function exportInfo(){
        layer.confirm('确认要导出APP用户数据吗？',{offset: 'auto'}, function(index) {
            $("#searchFrm").attr("action","${basePath}web/appuser/exportOut");
            $("#searchFrm").submit();
            $("#searchFrm").attr("action","${basePath}web/appuser/get_list");
            layer.msg('导出成功');
        });
    }

    $("#btnImport").click(function(){
        layer.open({
            type: 1,
            skin: 'layui-layer-demo', //样式类名
            closeBtn: 0, //不显示关闭按钮
            shift: 2,
            area: ['420px', '240px'], //宽高
            shadeClose: true, //开启遮罩关闭
            content:$('#importBox')
        });
    });

    //删除用户
    function delItem(id){
        layer.confirm('确定要删除该用户吗？',{offset: 'auto'}, function(index) {
            window.location.assign("${ajaxPath}web/appuser/delete?id="+id);
        });
    }
</script>
</div>
</body>
</html>