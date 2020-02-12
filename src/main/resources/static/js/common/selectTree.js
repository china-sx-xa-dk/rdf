//zNodes     : 必传  treeData 树状结构数据
//treeName   : 必传  树名称  例：组织机构；菜单
//id         : 必传  treeDiv的ID,也是对应表单对象属性名 例：orgId
//selectIds  : 必传  已选树节点数据ID字符串，进行回填选中
//isMultiple : 必传  是否多选 true多选；false单选
//chkboxType : 非必传  多选框类型{"Y": "ps", "N": "s"} (更多请参阅zTree官网),只在多选时生效
function initSelectTree(zNodes,treeName,id,selectIds, isMultiple, chkboxType) {
    var setting = {
        view: {
            dblClickExpand: false,
            showLine: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        check: {
            enable: false,
            chkboxType: {"Y": "ps", "N": "s"}
        },
        callback: {
            onClick: onClick,
            onCheck: onCheck
        }

    };
    if (isMultiple) {
        setting.check.enable = isMultiple;
    }
    if (chkboxType !== undefined && chkboxType != null) {
        setting.check.chkboxType = chkboxType;
    }
    var html = '<div class = "layui-select-title" >' +
        '<input id="' + id + 'Show"' + 'type = "text" placeholder = "请选择'+treeName+'" value = "" class = "layui-input" readonly>' +
        '<i class= "layui-edge" ></i>' +
        '</div>';
    $("#" + id).append(html);
    $("#" + id).parent().append('<div class="tree-content scrollbar">' +
        '<input hidden id="' + id + 'Hide" ' +
        'name="' + id + '">' +
        '<ul id="' + id + 'Tree" class="ztree scrollbar" style="margin-top:0;"></ul>' +
        '</div>');
    $("#" + id).bind("click", function () {
        if ($(this).parent().find(".tree-content").css("display") !== "none") {
            hideMenu()
        } else {
            $(this).addClass("layui-form-selected");
            var Offset = $(this).offset();
            var width = $(this).width() - 2;
            $(this).parent().find(".tree-content").css({
                left: Offset.left + "px",
                top: Offset.top + $(this).height() + "px"
            }).slideDown("fast");
            $(this).parent().find(".tree-content").css({
                width: width
            });
            $("body").bind("mousedown", onBodyDown);
        }
    });
    var treeObj =  $.fn.zTree.init($("#" + id + "Tree"), setting, zNodes);
    //默认展开所有节点
    treeObj.expandAll(true);
    //设置初始化节点选中   selectIds
    if(selectIds != "" && selectIds != null && selectIds != undefined){
        var selectIdArray = selectIds.split(",");
        for(var i=0; i<selectIdArray.length; i++) {
            var node = treeObj.getNodeByParam("id",selectIdArray[i]);
            treeObj.selectNode(node);
            onClick(null, id + "Tree", node);
        }
    }
}

function beforeClick(treeId, treeNode) {
    var check = (treeNode && !treeNode.isParent);
    if (!check) alert("只能选择最下级组织...");
    return check;
}

function onClick(event, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    if (zTree.setting.check.enable == true) {
        zTree.checkNode(treeNode, !treeNode.checked, false)
        assignment(treeId, zTree.getCheckedNodes());
    } else {
        assignment(treeId, zTree.getSelectedNodes());
        hideMenu();
    }
    //当判断页面存在treeChange方法时，可用与tree回调处理
    if(funExists("treeChange")){
        treeChange(event, treeId, treeNode);
    }
}

function onCheck(event, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    assignment(treeId, zTree.getCheckedNodes());
}

function hideMenu() {
    $(".select-tree").removeClass("layui-form-selected");
    $(".tree-content").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}

function assignment(treeId, nodes) {
    var names = "";
    var ids = "";
    for (var i = 0, l = nodes.length; i < l; i++) {
        names += nodes[i].name + ",";
        ids += nodes[i].id + ",";
    }
    if (names.length > 0) {
        names = names.substring(0, names.length - 1);
        ids = ids.substring(0, ids.length - 1);
    }
    treeId = treeId.substring(0, treeId.length - 4);
    $("#" + treeId + "Show").attr("value", names);
    $("#" + treeId + "Show").attr("title", names);
    $("#" + treeId + "Hide").attr("value", ids);
}

function onBodyDown(event) {
    if ($(event.target).parents(".tree-content").html() == null) {
        hideMenu();
    }
}

//判断是否存在某个方法
function funExists(funName){
    try{
        if(typeof eval(funName)=="undefined"){return false;}
        if(typeof eval(funName)=="function"){return true;}
    }catch(e){
        return false;
    }
}
