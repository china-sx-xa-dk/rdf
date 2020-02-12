<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../baseJSP.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>菜单管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10,IE=edge,chrome=1"/>
<style>
.tableBasic th{text-align:right;}
</style>

<script type="text/javascript" src="${baseStatic}js/jquery.min.js"></script>
<!-- jBox -->
<script type="text/javascript" src="${baseStatic}js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="${baseStatic}js/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet" href="${baseStatic}js/jBox/Skins2/Metro/jbox.css"/>
<!-- zTree -->
<script type="text/javascript" src="${baseStatic}js/jquery-ztree/3.5.12/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${baseStatic}js/jquery-ztree/3.5.12/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="${baseStatic}js/jquery-ztree/3.5.12/js/jquery.ztree.exedit-3.5.min.js"></script>
<script type="text/javascript" src="${baseStatic}js/jquery-ztree/3.5.12/js/jquery.ztree.exhide-3.5.min.js"></script>
<link type="text/css" rel="stylesheet" href="${baseStatic}js/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.css"/>

<link type="text/css" rel="stylesheet" href="${baseStatic}css/public.css"/>

<script type="text/javascript">

//设置树属性
var setting = {
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		edit : {
			<c:if test="${sessionScope.currentUser.adminId == 1}">
            enable : true,
            editNameSelectAll : true,
            showRenameBtn : showRenameBtn,
            renameTitle:"编辑",
            showRemoveBtn : showRemoveBtn,
            removeTitle:"删除"
			</c:if>
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			beforeDrag : beforeDrag,
			beforeEditName : beforeEditName,
			beforeRemove : beforeRemove,
			onRemove : onRemove
		}
	};

	//加载菜单树
	$(document).ready(function() {
		getTree();
	});

	//获取菜单树
	function getTree(){
		$.jBox.tip("正在加载数据，请稍等......", "loading");
		$.ajax({
			type:"get",
			url:"${basePath}permission/loadTree",
			data:{},
			dataType:"json",
			success:function(data) {
				if(data.state != "failed"){
					$.fn.zTree.init($("#menuTree"), setting, data);
					var zTree = $.fn.zTree.getZTreeObj("menuTree");
					zTree.expandAll(true);
					$.jBox.closeTip();
				}else{
					$.jBox.tip("数据加载失败，请刷新一下试试......", "info");
				}
			},
		});
	}

	//根节点以外显示删除按钮
	function showRemoveBtn(treeId, treeNode) {
		return treeNode.typeCode != 0;
	}

	//根节点以外显示编辑按钮
	function showRenameBtn(treeId, treeNode) {
		return treeNode.typeCode != 0;
	}

	//给节点增加 添加按钮
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0){
			return;
		}
		<c:if test="${sessionScope.currentUser.adminId == 1}">
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='添加' onfocus='this.blur();'></span>";
        sObj.after(addStr );
		</c:if>
		$("#addBtn_" + treeNode.tId).bind('click',function(){
			var strHtml = '<table width="100%" border="0" cellspacing="0" cellpadding="7" class="tableBasic">';
				strHtml += '<tr>';
				strHtml	+= '<td style="display: none;"><input type="text" id="permissionId" name="permissionId"></td>';
				strHtml	+= '<td style="display: none;"><input type="text" id="permissionParentId" name="permissionParentId" value='+treeNode.id+'></td>';
				strHtml	+= '<th style="width:100;">菜单名称：</th>';
				strHtml += '<td><input type="text" id="permissionName" name="permissionName" class="inpMain"></td>';
				strHtml += '</tr>';
				strHtml += '<tr>';
				strHtml	+= '<th>菜单地址：</th>';
				strHtml += '<td><input type="text" id="permissionValue" name="permissionValue" class="inpMain"></td>';
				strHtml += '</tr>';
				strHtml += '<tr>';
				strHtml	+= '<th>菜单排序：</th>';
		    	strHtml += '<td><input type="text" id="permissionSort" name="permissionSort" class="inpMain"></td>';
		    	strHtml += '</tr>';
		    	strHtml	+= '</table>';
		    savePermission("add",strHtml);
		});
	};

	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};

	function beforeDrag(treeId, treeNodes) {
		return false;
	}

	//获取菜单信息
	function beforeEditName(treeId, treeNode) {
		$.ajax({
			type:"get",
			url:"${basePath}permission/getDataById",
			data:{"permissionId" : treeNode.id},
			dataType:"json",
			success:function(obj){
		        if(obj.state != "failed"){
		        	var strHtml = '<table width="100%" border="0" cellspacing="0" cellpadding="7" class="tableBasic">';
						strHtml += '<tr>';
		        		strHtml += '<td style="display: none;"><input type="text" id="permissionId" name="permissionId" value='+treeNode.id+'></td>';
						strHtml	+= '<td style="display: none;"><input type="text" id="permissionParentId" name="permissionParentId" value='+obj.permissionParentId+'></td>';
						strHtml	+= '<th style="width:100;">菜单名称：</th>';
						strHtml += '<td><input type="text" id="permissionName" name="permissionName" class="inpMain" value='+obj.permissionName+'></td>';
						strHtml += '</tr>';
						strHtml += '<tr>';
						strHtml	+= '<th>菜单地址：</th>';
						strHtml += '<td><input type="text" id="permissionValue" name="permissionValue" class="inpMain" value='+obj.permissionValue+'></td>';
						strHtml += '</tr>';
						strHtml += '<tr>';
						strHtml	+= '<th>菜单排序：</th>';
			    		strHtml += '<td><input type="text" id="permissionSort" name="permissionSort" class="inpMain" value='+obj.permissionSort+'></td>';
			    		strHtml += '</tr>';
			    		strHtml	+= '</table>';
			    	savePermission("edit",strHtml);
	    		}else{
					$.jBox.tip("获取菜单数据失败。","error");
	    		}
		 	 }
		});
		return false;
	}

	//删除
	function beforeRemove(treeId, treeNode) {
 		var submit = function (v, h, f) {
 		    if (v == 'ok'){
		        $.jBox.tip("正在删除......", "loading");
		        var data = {"permissionId" : treeNode.id};
				$.post('${basePath}permission/delDataById',data,function(result){
			        if(result == "success"){
			        	$.jBox.tip("删除成功,正在刷新页面......", "success");
			        	window.setTimeout(function(){
		    				getTree();
		    			},1500);
		    		}else{
			        	$.jBox.tip("删除失败，请刷新一下再试！", "error");
		    		}
				});
		    }else if (v == 'cancel'){
	 	    }
 	 	    return true;
		};
 		$.jBox.confirm("确认删除菜单[" + treeNode.name + "]吗？", "提示", submit);
 		return false;
	}

	//删除回调
	function onRemove(e, treeId, treeNode) {
	}

	//保存
	function savePermission(event,html) {
		var submit = function (v, h, f) {
			var permissionName = $.trim(f.permissionName);
		    if (permissionName == null || permissionName == '') {
		        $.jBox.tip("菜单名称不能为空！", 'error', { focusId: "permissionName" });
		        return false;
		    }
		    if(permissionName.length > 100){
		    	$.jBox.tip("菜单名称不能超过100字！", 'error', { focusId: "permissionName" });
		        return false;
		    }

		    var permissionValue = $.trim(f.permissionValue);
		    if(permissionValue == null || permissionValue == ""){
		    	 $.jBox.tip("菜单路径不能为空！", 'error', { focusId: "permissionValue" });
			     return false;
		    }
		    if(permissionValue.length > 200){
		    	 $.jBox.tip("菜单路径不能超过200字！", 'error', { focusId: "permissionValue" });
			     return false;
		    }

		    if(!/^[0-9]{0,22}$/.test(f.permissionSort)){
		    	$.jBox.tip("排序编号只能为数字！", 'error', { focusId: "permissionSort" });
		        return false;
		    }

		    $.jBox.tip("正在保存......","loading");
		    var url = "${basePath}permission/doAddOrEdit";
			var data = {"permissionId":f.permissionId,"permissionParentId":f.permissionParentId,"permissionName":f.permissionName,"permissionValue":f.permissionValue,"permissionSort":f.permissionSort};
			$.post(url, data, function(result){
				if(result == "success"){
					$.jBox.tip("保存成功,正在刷新页面......", "success");
		        	window.setTimeout(function(){
	    				getTree();
	    			},1500);
				}else{
					$.jBox.tip("保存失败，请稍后再试！","error");
				}
			});
		};
		var title = "添加菜单";
		if(event != 'add'){
			title = "编辑菜单";
		}
		$.jBox(html, { title: title, submit: submit });
	};
</script>

</head>
<body>



  	<!-- ------------------------------------ 正文代码开始 start ------------------------------------ -->
  	<div id="dcMain" style="margin-left:0px;">
  		<!-- 业务代码 start -->
  		<div id="index" class="mainBox">
  			 <div class="indexBox" >
				<ul id="menuTree" class="ztree"></ul>
			</div>

  		</div>
  	</div>
  		<!-- 业务代码 end -->
  	<!-- ------------------------------------ 正文代码开始 end ------------------------------------ -->

</body>
</html>