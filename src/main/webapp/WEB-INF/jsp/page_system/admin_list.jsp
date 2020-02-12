<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../baseJSP.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户信息</title>
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
</head>
<body>
<div class="layui-fluid">
<!-- ----------------------正文代码start---------------------- -->

    <div id="importBox" style="display: none;">
        <form id="importForm"  method="post" enctype="multipart/form-data"
              class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
            <input id="importFile" name="file" type="file" /><br/><br/>　
            <span>导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！</span>　<br/><br/>
            <button id="btnImportSubmit" onclick="importExcel();" style="height: 30px; line-height: 30px;" type="button" class="layui-btn">导入</button>
            <a href="${basePath}admin/template">下载模板</a>
        </form>
    </div>
	<%-- 功能导航栏 --%>
	<div class="tool_top">
		<div class="layui-inline">
			<a href="${basePath}admin/goAddOrEdit">
				<button type="button" class="layui-btn layui-btn-normal" lay-text="新增用户">
					<i class="layui-icon layui-icon-add-1"></i>新增
				</button>
			</a>
		</div>
	</div>

	<!-- 查询条件 start -->
	<form action="${basePath}admin/findAdminList" method="post" id="searchFrm" class="layui-form layui-card layui-form-pane">
		<input type="hidden" name="currentPage" id="SCH_CUR_PAGE" value="${page.currentPage}"/>
		<input type="hidden" name="showCount" id="SCH_PAGE_SHOW_COUNT" value="${page.showCount}"/>

		<div class="layui-card-body layui-row layui-col-space15">
			<div class="layui-col-md12 layui-col-space15">
				<div class="layui-inline">
					<label class="layui-form-label">账号</label>
					<div class="layui-input-block"><input type="text" id="loginName" name="loginName" value="${admin.loginName}" placeholder="请输入账号" autocomplete="off" class="layui-input"></div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">姓名</label>
					<div class="layui-input-block"><input type="text" name="adminName" value="${admin.adminName}" placeholder="请输入用户姓名" autocomplete="off" class="layui-input"></div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">状态</label>
					<div class="layui-input-block">
						<select name="adminState" lay-verify="">
							<option value="">全部</option>
							<%--<option value="1"<c:if test="${admin.adminState==1}">selected</c:if>>启用</option>
							<option value="2"<c:if test="${admin.adminState==2}">selected</c:if>>禁用</option>--%>
							<c:forEach items="${fns:getDictList('adminState')}" var="dict">
								<option value="${dict.value}" <c:if test="${admin.adminState==dict.value}">selected</c:if>>${dict.label}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">所属组织</label>
                    <div class="layui-input-block">
                        <div id="orgId" class="layui-form-select select-tree">
                        </div>
                    </div>
				</div>

				<div class="layui-inline">
					<button class="layui-btn" lay-submit lay-filter="adminUserSearch" >
						<i class="layui-icon layui-icon-search"></i>
					</button>
					<button onclick="exportInfo()" id="btnExport" type="button" class="layui-btn">导出</button>
                    <button id="btnImport" type="button" class="layui-btn">导入</button>
				</div>
			</div>
		</div>
	</form>
	<!-- 查询条件 end -->
	<sys:message content="${message}"/>
	<!-- 数据列表 start -->
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-body">
					<table class="layui-table">
						<thead>
							<tr>
								<th style="width: 13%;text-align: center">账号</th>
								<th style="width: 9%;text-align: center">角色</th>
								<th style="width: 7%;text-align: center">状态</th>
								<th style="width: 8%;text-align: center">姓名</th>
								<th style="width: 10%;text-align: center">手机</th>
								<th style="width: 7%;text-align: center">所属机构</th>
								<th style="width: 14%;text-align: center">最后登录时间</th>
								<th style="width: 14%;text-align: center">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${data_list != null && data_list.size() > 0 }">
									<c:forEach var="item" items="${data_list}" varStatus="i">
									<tr>
										<td>${item.loginName}</td>
										<td>${item.roleName}</td>
										<td align="center">
											<span class="layui-bg-green" style="padding: 3px;">${fns:getDictLabel(item.adminState, 'adminState', '')}</span>
											<%--<c:if test="${item.adminState == 1 }"><span class="layui-bg-green">已启用</span></c:if>
											<c:if test="${item.adminState == 2 }"><span class="layui-bg-red">>已禁用</span></c:if>--%>
										</td>
										<td>${item.adminName}</td>
										<td style="text-align: center;">${item.adminMobile}</td>
										<td style="text-align: center;">${item.orgName}</td>
										<td style="text-align: center;"><fmt:formatDate value="${item.adminLoginDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td align="center">
											<%--<a class="layui-btn layui-btn-primary layui-btn-xs">查看</a>--%>
											<a class="layui-btn layui-btn-normal layui-btn-xs" href="${basePath}admin/goAddOrEdit?adminId=${item.adminId}" lay-text="编辑用户" class="e_tb_btn edit">编辑</a>
											<c:if test="${sessionScope.currentUser.adminId == 1}">
												<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="delItem(${item.adminId})">删除</a>
											</c:if>
										</td>
									</tr>
									</c:forEach>
								</c:when>
								<c:when test="${data_list == null || data_list.size() == 0 }">
									<tr><td colspan="9" align="center">没有查询到数据</td></tr>
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
	<!-- 数据列表 end -->


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

    function exportInfo(){
        layer.confirm('确认要导出用户数据吗？',{offset: 'auto'}, function(index) {
            $("#searchFrm").attr("action","${basePath}admin/exportOut");
            $("#searchFrm").submit();
            $("#searchFrm").attr("action","${basePath}admin/findAdminList");
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
	function delItem(userId){
		layer.confirm('确定删除该条数据吗？',{offset: 'auto'}, function(index) {
			window.location.assign("${basePath}admin/deleteAdmin?adminId="+userId);
		});
	}

	//导入
	function importExcel(result) {
		var formData = new FormData();
		formData.append("file",$("#importFile")[0].files[0]);
		$.ajax({
			url: "${basePath}admin/importExecl",
			type: 'POST',
			data : formData,
			processData : false,// 告诉jQuery不要去处理发送的数据
			contentType : false,// 告诉jQuery不要去设置Content-Type请求头
			beforeSend:function(){
				console.log("正在进行，请稍候");
			},
			success: function (data) {
				if(data == "OK"){
					layer.alert("导入成功！",{icon: 6});
					window.setTimeout(function(){$("#searchFrm").submit();}, 2000);
				}
				else if(data == "ERROR")
                {
                    layer.alert("导入失败！",{icon: 6});
                }else{
					layer.alert(data,{icon: 6});
				}
			},
			error: function (responseStr) {
				layer.alert("导入失败！",{icon: 6});
			}
		});
	}

    //获取组织机构数据
    var zNodes =  JSON.parse('${orgTreeList}');
	var selectIds = '${admin.orgId}';
    //设置下拉树选择
	initSelectTree(zNodes,"所属组织机构","orgId",selectIds,false);
</script>
</div>
</body>
</html>