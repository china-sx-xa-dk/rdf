<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="baseJSP.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${baseStatic}js/page/jquery.pager.js" type="text/javascript"></script>
<style>
.page_box{float:right;margin-right:5px;}
.page_info{float:left;margin-right:20px;}
.page_tool{float:right;height:30px;overflow:hidden;margin-top:3px;}
#page_tool ul.pages {display:block;border:none;text-transform:uppercase;font-size:10px;padding:0;float:left;height:24px;margin-top:3px;}
#page_tool ul.pages li {list-style:none;float:left;border:1px solid #bbb;text-decoration:none;margin:0px 1px;padding:0px 8px;height:24px;line-height:24px;}
#page_tool ul.pages li:hover {border:1px solid #0072C6;color:#0072C6;}
#page_tool ul.pages li.pgEmpty {border:1px solid #ddd;color:#ddd;}
#page_tool ul.pages li.pgCurrent {border:1px solid #0072C6;color:#0072C6;font-weight:700;background-color:#eee;}
</style>
<!-- ********************************* 分页 start ********************************* -->
<div class="page_box">
	<div class="page_info">
		总计 ${page.totalResult} 个记录，共 ${page.totalPage} 页，当前第 ${page.currentPage} 页
	</div>
	<div class="page_tool" id="page_tool"></div>
</div>
<!-- ********************************* 分页 end ********************************* -->
<!-- 备注：包含分页的页面，搜索Frm的Id必须为 searchForm 且 Frm内包含id为SCH_START_PAGE name为START_PAGE的隐藏input -->
<script type="text/javascript" language="javascript">
	$(document).ready(function() {
	    $("#page_tool").pager({pagenumber:${page.currentPage}, pagecount:${page.totalPage}, buttonClickCallback: PageClick });
	});
	var PageClick = function(pageclickednumber) {
		//设置检索Frm当前页参数
		if($("#SCH_START_PAGE").length > 0 ) { 
			$("#SCH_START_PAGE").val(pageclickednumber); 
		}else{
			$("input[name='currentPage']").val(pageclickednumber);	
		}
	 	$("#searchForm").submit();//提交搜索Frm
	 	$.jBox.tip("正在努力加载数据...","loading");
	}
</script>

