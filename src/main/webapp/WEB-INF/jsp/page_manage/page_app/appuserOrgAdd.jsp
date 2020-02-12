<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../baseJSP.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>机构用户添加</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="${baseStatic}layuiadmin/style/admin.css" media="all">
	<script type="text/javascript">
		//保存
		var flg = true;
		function saveBtnn() {
            if(flg){
                flg = false;
                var ids = "";
                $("td[name='one']").each(function(i) {
                    if($(this).children("div").hasClass('layui-form-checked'))
                    {
                        ids += $(this).attr("userId")+ ",";
                    }
                });
                if (ids == "") {
                    layer.msg('请至少选择一位用户!',{offset: 'auto'});
                }
                else{
                    var orgId = $("#orgId").val();
                    window.location.assign("${ajaxPath}web/appuser/addUserSave?ids="+ids+"&orgId="+orgId);
                }

            }
        }

		function checkInfo(obj){
			var name = $(obj).attr("name");
			if(name == "all")
            {
                if($(obj).children("div").hasClass('layui-form-checked'))
                {
                    $("td[name='one']").each(function(i) {
                        $(this).children("div").addClass('layui-form-checked')
                    });
                }
                else{
                    $("td[name='one']").each(function(i) {
                        $(this).children("div").removeClass('layui-form-checked')
                    });
                }
            }
		}
	</script>
</head>
<body>
<div class="layui-fluid">
	<form method="post" action="${baseStatic}web/appuser/addUser" id="searchFrm" class="layui-form layui-card layui-form-pane">
		<input type="hidden" name="orgIdNot" id="orgId" value='${org.orgId}'/>
        <input type="hidden" name="currentPage" id="SCH_CUR_PAGE" value="${page.currentPage}"/>
        <input type="hidden" name="showCount" id="SCH_PAGE_SHOW_COUNT" value="${page.showCount}"/>
		<div class="layui-card-header">机构用户添加</div>
        <sys:message content="${message}"/>
		<div class="layui-card-body layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-row layui-col-space15">
					<div class="layui-col-md4">
						<label class="layui-form-label">机构名称:</label>
						<div class="layui-input-block">
							<input type="text" disabled="disabled" value="${org.orgName}" class="layui-input">
						</div>
					</div>
					<div class="layui-col-md4"></div>
					<div class="layui-col-md4">
						<button class="layui-btn" onclick="saveBtnn();" type="button">提交保存</button>
						<button type="button" onclick="history.go(-1)" class="layui-btn layui-btn-primary">返回</button>
					</div>
				</div>
			</div>
		</div>
        <!-- 查询结果列表 start -->
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <table class="layui-table">
                            <thead>
                            <tr>
                                <th name="all" onclick="checkInfo(this);"><input type="checkbox" name="layTableCheckbox" lay-skin="primary"><div class="layui-unselect layui-form-checkbox" lay-skin="primary"><i class="layui-icon layui-icon-ok"></i></div></th>
                                <th>登录账户</th>
                                <th>用户名</th>
                                <th>性别</th>
                                <th>联系电话</th>
                                <th>邮箱</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:choose>
                                <c:when test="${data_list != null && data_list.size() > 0 }">
                                    <c:forEach var="item" items="${data_list}" varStatus="i">
                                        <tr>
                                            <td name="one" userId="${item.id}" onclick="checkInfo(this);"><input type="checkbox" name="layTableCheckbox" lay-skin="primary"><div class="layui-unselect layui-form-checkbox" lay-skin="primary"><i class="layui-icon layui-icon-ok"></i></div></td>
                                            <td>${item.loginName}</td>
                                            <td>${item.userName}</td>
                                            <td>${fns:getDictLabel(item.userSex, 'sex', '')}</td>
                                            <td>${item.userMobile}</td>
                                            <td>${item.userEmail}</td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${data_list == null || data_list.size() == 0 }">
                                    <tr><td colspan="8" align="center">没有查询到数据</td></tr>
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
	</form>
    <script>
        layui.config({
            base: '${baseStatic}layuiadmin/' //静态资源所在路径
        }).extend({
            index: 'lib/index' //主入口模块
        }).use(['jquery','index','form','laypage'],function(){
            $ = layui.jquery;//初始化jquery
            var form = layui.form;//初始化form组件
            var laypage = layui.laypage;//初始化分页组件

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

    </script>
</div>
</body>
</html>