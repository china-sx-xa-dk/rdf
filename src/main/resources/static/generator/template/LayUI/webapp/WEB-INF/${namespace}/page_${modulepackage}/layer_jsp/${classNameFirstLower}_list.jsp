<#assign className = table.className>
<#assign classNameLower =className?uncap_first>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ include file="../baseJSP.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
</head>
<body>
<div class="layui-fluid">
	<!-- ----------------------正文代码start------------------------>
	<div id="importBox" style="display: none;">
		<form id="importForm"  method="post" enctype="multipart/form-data"
			  class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="importFile" name="file" type="file" /><br/><br/>　
			<span>导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！</span>　<br/><br/>
			<button id="btnImportSubmit" onclick="importExcel();" style="height: 30px; line-height: 30px;" type="button" class="layui-btn">导入</button>
			<a href="${r"${basePath}"}${modulepackage}/${classNameLower}/template">下载模板</a>
		</form>
	</div>
	<%-- 功能导航栏 --%>

    <div class="tool_top">
        <div class="layui-inline">
			<button type="button" onclick="goForm(0)" class="layui-btn layui-btn-normal" lay-text="新增数据">
				<i class="layui-icon layui-icon-add-1"></i>新增
			</button>
        </div>
    </div>

	<!-- 查询条件 start -->
	<form action="${r"${basePath}"}${modulepackage}/${classNameLower}/get_list" method="post" id="searchFrm" class="layui-form layui-card layui-form-pane">
		<input type="hidden" name="currentPage" id="SCH_CUR_PAGE" value="${r"${page.currentPage}"}"/>
		<input type="hidden" name="showCount" id="SCH_PAGE_SHOW_COUNT" value="${r"${page.showCount}"}"/>

		<div class="layui-card-body layui-row layui-col-space15">
			<div class="layui-col-md12 layui-col-space15">
				<#list table.columns as column>
				<#if column.pk>
				<#elseif column.isDateTimeColumn && column.columnNameLower == 'createTime'>
				<div class="layui-inline">
					<label class="layui-form-label">创建时间</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" id="createTime" name="createTimeStr" placeholder="yyyy-MM-dd" autocomplete="off" >
					</div>
				</div>
				<#elseif column.isDateTimeColumn && column.columnNameLower == 'updateTime'>
				<div class="layui-inline">
					<label class="layui-form-label">更新时间</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" id="updateTime" name="updateTimeStr" placeholder="yyyy-MM-dd" autocomplete="off" >
					</div>
				</div>
				<#elseif column.columnNameLower == 'delFlag'>
				<#elseif column.columnNameLower == 'createUser'>
				<#elseif column.columnNameLower == 'updateUser'>
				<#else>
				<div class="layui-inline">
					<label class="layui-form-label">${column.columnAlias}</label>
					<div class="layui-input-block"><input type="text" id="${column.columnNameLower}" name="${column.columnNameLower}" value="${r"${condition."}${column.columnNameLower}${r"}"}" placeholder="请输入${column.columnAlias}..." autocomplete="off" class="layui-input"></div>
				</div>
				</#if>
				</#list>
				<div class="layui-inline">
					<button class="layui-btn" lay-submit lay-filter="SearchBtn" >
						<i class="layui-icon layui-icon-search"></i>
					</button>
				</div>
				<div class="layui-inline">
					<button class="layui-btn" type="reset">重置</button>
				</div>
				<div class="layui-inline">
					<button onclick="exportInfo()" id="btnExport" type="button" class="layui-btn">导出</button>
					<button id="btnImport" type="button" class="layui-btn">导入</button>
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
							<#list table.columns as column>
							<#if column.columnNameLower == 'delFlag'>
							<#else>
							<th>${column.columnAlias}</th>
							</#if>
							</#list>
							<th>操作</th>
						</tr>
						</thead>
						<tbody>
						<c:choose>
							<c:when test="${r"${data_list != null && data_list.size() > 0 }"}">
								<c:forEach var="item" items="${r"${data_list}"}" varStatus="i">
									<tr>
									<#list table.columns as column>
										<#if column.isDateTimeColumn>
										<td><fmt:formatDate value="${r"${item."}${column.columnNameLower}${r"}"}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<#elseif column.columnNameLower == 'delFlag'>
										<#elseif column.columnNameLower == 'createUser'>
										<td>${r"${item.createUserName}"}</td>
										<#elseif column.columnNameLower == 'updateUser'>
										<td>${r"${item.updateUserName}"}</td>
										<#else>
										<td>${r"${item."}${column.columnNameLower}${r"}"}</td>
										</#if>
									</#list>
									<#list table.columns as column>
										<#if column.pk>
										<td align="center">
											<a class="layui-btn layui-btn-xs" onclick="goForm(${r"${item."}${column.columnNameLower}${r"}"})" lay-text="编辑" class="e_tb_btn edit">编辑</a>
											&nbsp;&nbsp;
											<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="delItem('${r"${item."}${column.columnNameLower}${r"}"}')">删除</a>
										</td>
										</#if>
									</#list>
									</tr>
								</c:forEach>
							</c:when>
							<c:when test="${r"${data_list == null || data_list.size() == 0 }"}">
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
		base: '${r"${baseStatic}"}layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['jquery','index','form','laypage','laydate'],function(){
		$ = layui.jquery;//初始化jquery
		var form = layui.form;//初始化form组件
		var laypage = layui.laypage;//初始化分页组件
		var laydate = layui.laydate;//日期选择插件
		<#list table.columns as column>
		<#if column.pk>
		<#elseif column.isDateTimeColumn && column.columnNameLower == 'createTime'>
		laydate.render({elem: '#createTime'});
		if("${r"${condition.createTimeStr}"}".length > 0){
			$("#createTime").val("${r"${condition.createTimeStr}"}");
		}
		<#elseif column.isDateTimeColumn && column.columnNameLower == 'updateTime'>
		laydate.render({elem: '#updateTime'});
		if("${r"${condition.updateTimeStr}"}".length > 0){
			$("#updateTime").val("${r"${condition.updateTimeStr}"}");
		}
		<#else>
		</#if>
		</#list>

		//监听提交form点击事件
		form.on('submit(SearchBtn)', function(data){
			layer.msg('正在加载数据', {icon:16,shade:0.01,time:0,offset: 'auto'});
		});

		//分页
		laypage.render({
			elem: document.getElementById('listTablePage'),count:${r"${page.totalResult}"},curr:${r"${page.currentPage}"},limit:${r"${page.showCount}"},
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

	//打开表单弹窗
	function goForm(id){
		var title = "新增";
		if(id != 0){
			title = "修改";
		}
	<#list table.columns as column>
	<#if column.pk>
		layer.open({
			type: 2
			,title: '数据'+title
			,content: '${r"${basePath}"}${modulepackage}/${classNameLower}/jump_edit?${column.columnNameLower}='+id
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
	</#if>
	</#list>
	}



	function exportInfo(){
		layer.confirm('确认要导出列表数据吗？',{offset: 'auto'}, function(index) {
			$("#searchFrm").attr("action","${r"${basePath}"}${modulepackage}/${classNameLower}/exportOut");
			$("#searchFrm").submit();
			$("#searchFrm").attr("action","${r"${basePath}"}${modulepackage}/${classNameLower}/get_list");
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

	//导入
	function importExcel(result) {
		var formData = new FormData();
		formData.append("file",$("#importFile")[0].files[0]);
		$.ajax({
			url: "${r"${basePath}"}${modulepackage}/${classNameLower}/importExecl",
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

	//删除用户
	function delItem(id){
		layer.confirm('确定删除该条数据吗？',{offset: 'auto'}, function(index) {
			var loading = layer.msg('正在删除数据', {icon:16,shade:0.2,time:0,offset: 'auto'});
			var url = "${r"${basePath}"}${modulepackage}/${classNameLower}/delete";
			<#list table.columns as column>
			<#if column.pk>
			var data = {"${column.columnNameLower}":id};
			</#if>
			</#list>
			$.post(url,data,function(result){
				layer.close(loading);//关闭loading
				if(result.code == 200){
					layer.msg('数据已删除，正在刷新',{offset: 'auto'});
					window.setTimeout(function(){$("#searchFrm").submit();}, 2000);
				}else{
					layer.msg('删除失败，请稍后再试!',{offset: 'auto'});
				}
			});
		});
	}
</script>
</div>
</body>
</html>