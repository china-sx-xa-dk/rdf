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
	<%-- 功能导航栏 --%>

    <div class="tool_top">
        <div class="layui-inline">
            <button type="button" onclick="goForm(0)" class="layui-btn layui-btn-normal" lay-text="新增数据">
                <i class="layui-icon layui-icon-add-1"></i>新增
            </button>
        </div>
    </div>

	<!-- 查询条件 start -->
	<form action="${basePath}system/sensitiveInfo/get_list" method="post" id="searchFrm" class="layui-form layui-card layui-form-pane">
		<input type="hidden" name="currentPage" id="SCH_CUR_PAGE" value="${page.currentPage}"/>
		<input type="hidden" name="showCount" id="SCH_PAGE_SHOW_COUNT" value="${page.showCount}"/>

		<div class="layui-card-body layui-row layui-col-space15">
			<div class="layui-col-md12 layui-col-space15">
				<div class="layui-inline">
					<label class="layui-form-label">敏感词内容</label>
					<div class="layui-input-block"><input type="text" id="sensitiveContent" name="sensitiveContent" value="${condition.sensitiveContent}" placeholder="请输入敏感词内容..." autocomplete="off" class="layui-input"></div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">替换内容</label>
					<div class="layui-input-block"><input type="text" id="replaceContent" name="replaceContent" value="${condition.replaceContent}" placeholder="请输入替换内容..." autocomplete="off" class="layui-input"></div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">启用状态</label>
					<div class="layui-input-block">
						<select name="state">
							<option value="" <c:if test="${condition.state eq null}">selected</c:if>>请选择状态</option>
							<option value="1" <c:if test="${condition.state==1}">selected</c:if>>启用</option>
							<option value="2" <c:if test="${condition.state==2}">selected</c:if>>禁用</option>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn" lay-submit lay-filter="SearchBtn" >
						<i class="layui-icon layui-icon-search"></i>
					</button>
				</div>
				<div class="layui-inline">
					<button class="layui-btn" type="reset">重置</button>
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
							<th>敏感词内容</th>
							<th>替换内容</th>
							<th>启用状态</th>
							<th>排序号</th>
							<th>操作</th>
						</tr>
						</thead>
						<tbody>
						<c:choose>
							<c:when test="${data_list != null && data_list.size() > 0 }">
								<c:forEach var="item" items="${data_list}" varStatus="i">
									<tr>
										<td>${item.sensitiveContent}</td>
										<td>${item.replaceContent}</td>
										<td align="center">
											<c:if test="${item.state==1}">
												<span class="layui-bg-green" style="padding: 3px;">启用</span>
											</c:if>
											<c:if test="${item.state==2}">
												<span class="layui-bg-red" style="padding: 3px;">禁用</span>
											</c:if>
										</td>
										<td align="center">${item.sortnum}</td>
										<td align="center">
											<a class="layui-btn layui-btn-xs" onclick="goForm(${item.id})" lay-text="编辑" class="e_tb_btn edit">编辑</a>
											&nbsp;&nbsp;
											<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="delItem('${item.id}')">删除</a>
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:when test="${data_list == null || data_list.size() == 0 }">
								<tr><td colspan="5" align="center">没有查询到数据</td></tr>
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
	}).use(['jquery','index','form','laypage','laydate'],function(){
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
	});

	//打开表单弹窗
	function goForm(id){
	    var title = "新增";
	    if(id != 0){
            title = "修改";
        }
		layer.open({
			type: 2
			,title: '敏感词'+title
			,content: '${basePath}system/sensitiveInfo/jump_edit?id='+id
			,area: ['600px', '350px']
			,offset: 'auto'
			,resize: false
			,fixed : false
			,yes: function(index, layero){}
			,success: function(layero, index){}
		});
	}

	//删除用户
	function delItem(id){
		layer.confirm('确定删除该条数据吗？',{offset: 'auto'}, function(index) {
			var loading = layer.msg('正在删除数据', {icon:16,shade:0.2,time:0,offset: 'auto'});
			var url = "${basePath}system/sensitiveInfo/delete";
			var data = {"id":id};
			$.post(url,data,function(result){
				layer.close(loading);//关闭loading
				if(result.code == '200'){
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