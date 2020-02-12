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
                enable : true,
                editNameSelectAll : true,
                showRenameBtn : showRenameBtn,
                renameTitle:"编辑",
                showRemoveBtn : showRemoveBtn,
                removeTitle:"删除"
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
                url:"${basePath}org/loadTree",
                data:{},
                dataType:"json",
                success:function(data) {
                    if(data.state != "failed"){
                        $.fn.zTree.init($("#orgTree"), setting, data);
                        var zTree = $.fn.zTree.getZTreeObj("orgTree");
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
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='添加' onfocus='this.blur();'></span>";
            sObj.after(addStr );
            $("#addBtn_" + treeNode.tId).bind('click',function(){
                var strHtml = '<table width="100%" border="0" cellspacing="0" cellpadding="7" class="tableBasic">';
                strHtml += '<tr>';
                strHtml	+= '<td style="display: none;"><input type="text" id="orgId" name="orgId"></td>';
                strHtml	+= '<td style="display: none;"><input type="text" id="parentId" name="parentId" value='+treeNode.id+'></td>';
                /*strHtml	+= '<th style="width:100;">组织机构ID：</th>';
                strHtml += '<td><input type="text" id="orgId" name="orgId" class="inpMain"></td>';*/
                strHtml += '</tr>';
                strHtml += '<tr>';
                strHtml	+= '<th>组织机构名称：</th>';
                strHtml += '<td><input type="text" id="orgName" name="orgName" class="inpMain"></td>';
                strHtml += '</tr>';
                strHtml += '<tr>';
                strHtml	+= '<th>经纬度范围：</th>';
                strHtml += '<td><textarea  id="areaRange" name="areaRange" rows="5" cols="22"  class="inpMain"></textarea></td>';
                strHtml += '</tr>';
                strHtml	+= '</table>';
                saveOrg("add",strHtml);
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
                url:"${basePath}org/getDataById",
                data:{"orgId" : treeNode.id},
                dataType:"json",
                success:function(obj){
                    if(obj.state != "failed"){
                        var strHtml = '<table width="100%" border="0" cellspacing="0" cellpadding="7" class="tableBasic">';
                        strHtml += '<tr>';
                        strHtml += '<td style="display: none;"><input type="text" id="orgId" name="orgId" value='+treeNode.id+'></td>';
                        strHtml	+= '<td style="display: none;"><input type="text" id="parentId" name="parentId" value='+obj.parentId+'></td>';
                       /* strHtml	+= '<th style="width:100;">组织机构ID：</th>';
                        strHtml += '<td><input type="text" id="orgId" name="orgId" class="inpMain" readonly="readonly" value='+obj.orgId+'></td>';*/
                        strHtml += '</tr>';
                        strHtml += '<tr>';
                        strHtml	+= '<th>组织机构名称：</th>';
                        strHtml += '<td><input type="text" id="orgName" name="orgName" class="inpMain" value='+obj.orgName+'></td>';
                        strHtml += '</tr>';
                        strHtml += '<tr>';
                        strHtml	+= '<th>经纬度范围：</th>';
                        strHtml += '<td><textarea  id="areaRange" name="areaRange" rows="5" cols="22"  class="inpMain">'+obj.areaRange+'</textarea></td>';
                        strHtml += '</tr>';
                        strHtml	+= '</table>';
                        saveOrg("edit",strHtml);
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
                    var data = {"orgId" : treeNode.id};
                    $.post('${basePath}org/delDataById',data,function(result){
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
        function saveOrg(event,html) {
            var submit = function (v, h, f) {
                /*var orgId = $.trim(f.orgId);
                if (orgId == null || orgId == '') {
                    $.jBox.tip("组织机构ID不能为空！", 'error', { focusId: "orgId" });
                    return false;
                }
                if(orgId.length > 20){
                    $.jBox.tip("组织机构ID不能超过20字！", 'error', { focusId: "orgId" });
                    return false;
                }*/

                var orgName = $.trim(f.orgName);
                if(orgName == null || orgName == ""){
                    $.jBox.tip("组织机构名称不能为空！", 'error', { focusId: "orgName" });
                    return false;
                }
                if(orgName.length > 50){
                    $.jBox.tip("菜单路径不能超过50字！", 'error', { focusId: "orgName" });
                    return false;
                }
                $.jBox.tip("正在保存......","loading");
                var url = "${basePath}org/doAddOrEdit";
                var data = {"orgId":f.orgId,"parentId":f.parentId,"orgName":f.orgName,"areaRange":f.areaRange};
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
            var title = "添加组织机构";
            if(event != 'add'){
                title = "编辑组织机构";
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
            <ul id="orgTree" class="ztree"></ul>
        </div>

    </div>
</div>
<!-- 业务代码 end -->
<!-- ------------------------------------ 正文代码开始 end ------------------------------------ -->

</body>
</html>