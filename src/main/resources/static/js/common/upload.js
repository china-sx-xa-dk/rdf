/*
 * 文件上传公共方法
 * 单图片上传、多图片上传、单文件上传、多文件上传
 * wgl 20190805
*/
var uploadUrl = getRootPath() + "/uploadFile";

/*
 * 单图片上传方法
 * @param id  对象属性名称
*/
function uploadImgSingle(id) {
    layui.use('upload', function () {
        var $ = layui.jquery, upload = layui.upload;
        //普通图片上传
        var uploadInst = upload.render({
            elem: '#' + id + '_upload'
            , url: uploadUrl
            , accept: 'images'
            , exts: 'png|jpg|jpeg' //只允许上传图片文件
            , before: function (obj) {
                layer.load(); //上传loading
            }
            , done: function (res) {
                if (res.code != 0) {
                    //如果上传失败
                    layer.msg('上传失败！');
                } else {
                    layer.closeAll('loading'); //关闭loading
                    layer.msg('上传成功！');
                    //上传成功，隐藏上传按钮
                    $("#" + id + "_upload").hide();
                    $("#" + id).val(res.data.src);
                    $("#" + id + "_upload").prev().append('<div divId="' + id + '_imgDiv" class="form_img_div"><img imgId="' + id + '_img" onclick="imgviewer(this);" src="' + res.data.src + '" class="form_img"><span spanId="' + id + '_delSpan" class="delImg_span"><img src="' + getRootPath() + '/static/images/delete.png" class="delete_img_icon"/></span></div>');
                    //用于图片预览
                    $("#imgViewDiv").append('<img src="' + res.data.src + '" data-original="' + res.data.src + '" class="imgviewer">');
                    //设置图片展示底部删除按钮
                    $("[spanId=" + id + "_delSpan]").hide();
                    $("[divId=" + id + "_imgDiv]").mouseover(function () {
                        $(this).find("span").show();
                    });
                    $("[divId=" + id + "_imgDiv]").mouseout(function () {
                        $(this).find("span").hide();
                    });
                    //点击删除图片
                    $("[spanId=" + id + "_delSpan]").on('click', function () {
                        //展示上传按钮
                        $("#" + id + "_upload").show();
                        $("#" + id).val("");
                        $("#" + id + "_upload").prev().empty();
                        deleteImg(res.data.src);
                    });
                }
            }
            , error: function () {
                layer.closeAll('loading'); //关闭loading
                //显示失败状态，并实现重传
                $("#" + id).val("");
                $("#" + id + "_upload").prev().empty();
                layer.msg('上传失败！请重新上传！');
            }
        });
    });
}

/**
 * 单图片原图展示(用于编辑时回显，可删除)
 * @param id  对象属性名称
 */
function loadImgSingle(id) {
    var src = $("#" + id).val();
    if(src != null && src != undefined && src !=""){
        $("#" + id + "_upload").hide();
        $("#" + id + "_upload").prev().append('<div divId="' + id + '_imgDiv" class="form_img_div"><img imgId="' + id + '_img" onclick="imgviewer(this);" src="' + src + '" class="form_img"><span spanId="' + id + '_delSpan" class="delImg_span"><img src="' + getRootPath() + '/static/images/delete.png" class="delete_img_icon"/></span></div>');
        //用于图片预览
        $("#imgViewDiv").append('<img src="' + src + '" data-original="' + src + '" class="imgviewer">');
        //设置图片展示底部删除按钮
        $("[spanId=" + id + "_delSpan]").hide();
        $("[divId=" + id + "_imgDiv]").mouseover(function () {
            $(this).find("span").show();
        });
        $("[divId=" + id + "_imgDiv]").mouseout(function () {
            $(this).find("span").hide();
        });
        //点击删除图片
        $("[spanId=" + id + "_delSpan]").on('click', function () {
            //展示上传按钮
            $("#" + id + "_upload").show();
            $("#" + id).val("");
            $("#" + id + "_upload").prev().empty();
            deleteImg(src);
        });
    }
}

/**
 * 单图片原图展示(用于详情时展示，不可删除)
 * @param id  对象属性名称
 */
function showImgSingle(id) {
    var src = $("#" + id).val();
    if(src != null && src != undefined && src !=""){
        $("#" + id).next().append('<div divId="' + id + '_imgDiv" class="form_img_div"><img imgId="' + id + '_img" onclick="imgviewer(this);" src="' + src + '" class="form_img"></div>');
        //用于图片预览
        $("#imgViewDiv").append('<img src="' + src + '" data-original="' + src + '" class="imgviewer">');
    }
}

/**
 * 多图片上传方法
 * @param id  对象属性名称
 * @param num 最多上传数量
 */
function uploadImgMultiple(id, num) {
    layui.use('upload', function () {
        var $ = layui.jquery, upload = layui.upload;
        //多图片上传
        var uploadInst = upload.render({
            elem: '#' + id + '_upload'
            , url: uploadUrl
            , multiple: true
            , number: num //上传的数量
            , accept: 'images'
            , exts: 'png|jpg|jpeg' //只允许上传图片文件
            , before: function (obj) {
                layer.load(); //上传loading
            }
            , done: function (res) {
                if (res.code != 0) {
                    //如果上传失败
                    return layer.msg('上传失败！');
                } else {
                    //获取已上传的文件数量
                    var imgCount = 0;
                    $("#" + id + "_upload").prev().find("div").each(function () {
                        //加上已有的文件数量
                        imgCount++;
                    });
                    //判断当前上传文件数小于限制数量时，进行图片添加，否则删除该图
                    layer.closeAll('loading'); //关闭loading
                    if (imgCount < num) {
                        layer.msg('上传成功！');
                        //上传成功
                        var imgurls = $("#" + id).val();
                        imgurls = imgurls + res.data.src + ",";
                        $("#" + id).val(imgurls);
                        $("#" + id + "_upload").prev().append('<div divId="' + id + '_imgDiv" class="form_img_div"><img imgId="' + id + '_img" onclick="imgviewer(this);" src="' + res.data.src + '" class="form_img"><span spanId="' + id + '_delSpan" class="delImg_span"><img src="' + getRootPath() + '/static/images/delete.png" class="delete_img_icon"/></span></div>')
                        //用于图片预览
                        $("#imgViewDiv").append('<img src="' + res.data.src + '" data-original="' + res.data.src + '" class="imgviewer">');
                        //设置图片展示底部删除按钮
                        $("[spanId=" + id + "_delSpan]").hide();
                        $("[divId=" + id + "_imgDiv]").mouseover(function () {
                            $(this).find("span").show();
                        });
                        $("[divId=" + id + "_imgDiv]").mouseout(function () {
                            $(this).find("span").hide();
                        });
                        //点击删除图片
                        $("[spanId=" + id + "_delSpan]").on('click', function () {
                            var path = $(this).prev().attr("src");
                            if (path == res.data.src) {
                                //重新赋值图片路径
                                imgurls = imgurls.replace(path + ",", "");
                                $("#" + id).val(imgurls);
                                $(this).parent().remove();
                                deleteImg(path);
                            }
                        });
                    }else{
                        layer.msg('最多只能上传'+num+'张图片！');
                        deleteImg(res.data.src);
                    }
                }
            }
            , error: function (index) {
                layer.closeAll('loading'); //关闭loading
                layer.msg('上传失败！请重新上传！');
            }
        });
    });
}

/**
 * 多图片原图展示(用于编辑时回显，可删除)
 * @param id  对象属性名称
 */
function loadImgMultiple(id) {
    var srcArrayStr = $("#" + id).val();
    var srcArray = srcArrayStr.split(",");
    if (srcArray != null && srcArray != undefined && srcArray.length > 0) {
        for (var i = 0; i < srcArray.length; i++) {
            var src = srcArray[i];
            if (src != "") {
                $("#" + id + "_upload").prev().append('<div divId="' + id + '_imgDiv" class="form_img_div"><img imgId="' + id + '_img" onclick="imgviewer(this);" src="' + src + '" class="form_img"><span spanId="' + id + '_delSpan" class="delImg_span"><img src="' + getRootPath() + '/static/images/delete.png" class="delete_img_icon"/></span></div>');
                //用于图片预览
                $("#imgViewDiv").append('<img src="' + src + '" data-original="' + src + '" class="imgviewer">');
            }
        }
    }
    //设置图片展示底部删除按钮
    $("[spanId=" + id + "_delSpan]").hide();
    $("[divId=" + id + "_imgDiv]").mouseover(function () {
        $(this).find("span").show();
    });
    $("[divId=" + id + "_imgDiv]").mouseout(function () {
        $(this).find("span").hide();
    });
    //点击删除图片
    $("[spanId=" + id + "_delSpan]").on('click', function () {
        //展示上传按钮
        var path = $(this).prev().attr("src");
        //重新赋值图片路径
        var imgUrls = $("#" + id).val();
        imgUrls = imgUrls.replace(path + ",", "");
        $("#" + id).val(imgUrls);
        $(this).parent().remove();
        deleteImg(path);
    });
}

/**
 * 多图片原图展示(用于详情时展示，不可删除)
 * @param id  对象属性名称
 */
function showImgMultiple(id) {
    var srcArrayStr = $("#" + id).val();
    var srcArray = srcArrayStr.split(",");
    if (srcArray != null && srcArray != undefined && srcArray.length > 0) {
        for (var i = 0; i < srcArray.length; i++) {
            var src = srcArray[i];
            if (src != "") {
                $("#" + id).next().append('<div divId="' + id + '_imgDiv" class="form_img_div"><img imgId="' + id + '_img" onclick="imgviewer(this);" src="' + src + '" class="form_img"></div>');
                //用于图片预览
                $("#imgViewDiv").append('<img src="' + src + '" data-original="' + src + '" class="imgviewer">');
            }
        }
    }
}

/**
 * 图片预览
 * @param obj
 */
function imgviewer(obj) {
    //重新更新预览图片组
    $("#imgViewDiv").viewer('update');
    var path = $(obj).attr("src");
    $("#imgViewDiv img").each(function () {
        var src = $(this).attr("src");
        if (src == path) {
            $(this).click();
        }
    });
}

/**
 * 点击图片删除事件删除图片
 * @param path  文件对应保存路径
 */
function deleteImg(path) {
    //清除预览图片组中对应的图片
    $("#imgViewDiv img").each(function () {
        var src = $(this).attr("src");
        if (src == path) {
            $(this).remove();
        }
    });
    //ajax删除已上传文件，无须进行返回处理(鉴于当前，若打开修改，删除图片不保存，会出现路径存在但图片已被删的清空，暂不清除服务器图片)
    /*$.ajax({
        type: "post",
        url: getRootPath() + "/deleteFile",
        data: {"path": path},
        success: function (result) {
        }
    });*/
}

/**
 * 页面点击返回或右上角关闭时，清除已上传图片(鉴于当前，若打开修改，未进行编辑后点击返回清除图片，会出现路径存在但图片已被删的清空，暂不清除服务器图片)
 */
function deleteImgAll() {
    //清除图片组中所有的图片
    var pathArrayStr = "";
    $("#imgViewDiv img").each(function () {
        var src = $(this).attr("src");
        pathArrayStr += src + ",";
    });
    if (pathArrayStr != "") {
        pathArrayStr = pathArrayStr.substr(0, pathArrayStr.length - 1);
    }
    //ajax删除已上传文件，无须进行返回处理
    /*$.ajax({
        type: "post",
        url: getRootPath() + "/deleteAllFile",
        data: {"pathArrayStr": pathArrayStr},
        success: function (result) {
        }
    });*/
}

/**
 * 获取当前项目访问路径
 * @returns {string}
 */
function getRootPath() {
    //获取当前网址
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址
    var localhostPaht = curWwwPath.substring(0, pos);
    return localhostPaht;
}
