/* 处理数据表格复选框功能  wgl 2019/05/31 */

//控制全选/全不选
function checkAll(){
    var checked = $("input[name='all']").is(":checked");
    if(checked){
        $("input[name='single']").prop("checked", true);
    }else{
        $("input[name='single']").prop("checked", false);
    }
}

// 点击单选时判断是否控制全选/全不选
function checkThis(){
    var chknum = $("input[name='single']").size();// 选项总个数
    var chk = 0;
    $("input[name='single']").each(function(){
        if($(this).is(":checked")==true){
            chk++;
        }
    });
    if(chknum == chk){// 全选
        $("input[name='all']").prop("checked",true);
    }else{// 全不选
        $("input[name='all']").prop("checked",false);
    }
}