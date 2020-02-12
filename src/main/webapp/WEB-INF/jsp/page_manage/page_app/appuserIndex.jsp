<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../baseJSP.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">

	<link rel="stylesheet" href="${baseStatic}common/jeesite.css" media="all">
	<link type="text/css" rel="stylesheet" href="${baseStatic}css/public.css"/>
	<!-- zTree -->
	<script type="text/javascript" src="${baseStatic}js/jquery-ztree/3.5.12/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="${baseStatic}js/jquery-ztree/3.5.12/js/jquery.ztree.excheck-3.5.min.js"></script>
	<script type="text/javascript" src="${baseStatic}js/jquery-ztree/3.5.12/js/jquery.ztree.exedit-3.5.min.js"></script>
	<script type="text/javascript" src="${baseStatic}js/jquery-ztree/3.5.12/js/jquery.ztree.exhide-3.5.min.js"></script>
	<link type="text/css" rel="stylesheet" href="${baseStatic}js/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.css"/>
	<style type="text/css">
		.ztree {overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;}
	</style>
</head>
<body style="background-color: #f2f2f2;">
	<div id="content" class="row-fluid">
		<div id="left" class="accordion-group" style="background-color: #fff;margin-top: 10px;">
			<div class="layui-card-header">组织机构<i class="icon-refresh pull-right" onclick="refreshTree();"></i></div>
			<div id="ztree" class="ztree"></div>
		</div>
		<div id="openClose" class="close">&nbsp;</div>
		<div id="right">
			<iframe id="officeContent" src="${ajaxPath}web/appuser/list?orgId=1" width="100%" height="91%" frameborder="0"></iframe>
		</div>
	</div>
	<script type="text/javascript">
		var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
			callback:{onClick:function(event, treeId, treeNode){
					var id = treeNode.id == '0' ? '' :treeNode.id;
					$('#officeContent').attr("src","${ajaxPath}web/appuser/list?orgId="+id);
				}
			}
		};
		
		function refreshTree(){
			$.getJSON("${ajaxPath}/org/treeData",function(data){
				$.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
			});
		}
		refreshTree();
		 
		var leftWidth = 180; // 左侧窗口大小
		var htmlObj = $("html"), mainObj = $("#main");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize(){
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":"hidden", "overflow-y":"hidden"});
			mainObj.css("width","auto");
			frameObj.height(strs[0] - 5);
			var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
			$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			$(".ztree").width(leftWidth - 10).height(frameObj.height() - 46);
		}
	</script>
	<script src="${baseStatic}common/wsize.js" type="text/javascript"></script>
</body>
</html>