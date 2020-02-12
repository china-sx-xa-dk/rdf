<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="baseJSP.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>上传音频</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <script type="text/javascript" src="${baseStatic}js/jquery.min.js"></script>
    <script src="${baseStatic}js/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="${baseStatic}js/ajaxUpload/ajaxfileupload.js"></script>
    <style>
        body {
            margin: 0px;
            padding: 0px;
        }

        .imgUploadBox {
            min-width: 300px;
            min-height: 300px;
            float: left;
            border: #ddd 1px solid;
            position: relative;
            background: #f5f5f5;
            overflow: hidden;
        }

        .imgUploadBox .uploadImgBtn {
            display: block;
            width: 300px;
            height: 300px;
            position: absolute;
            left: 0px;
            top: 0px;
            z-index: 5;
            line-height: 300px;
            text-align: center;
            font-size: 16px;
            text-decoration: none;
            color: #666;
        }

        .imgUploadBox .uploadImgBtn:hover {
            background: #ECECEC;
        }

        .imgUploadBox .uploadImgBtn span {
            height: 100%;
            display: inline-block;
            vertical-align: middle;
        }

        .imgUploadBox .uploadImgBtn img {
            max-width: 300px;
            max-height: 300px;
            vertical-align: middle;
        }

        .imgUploadBox .imgUploadLoading {
            width: 300px;
            height: 300px;
            position: absolute;
            left: 0px;
            top: 0px;
            z-index: 9;
            background: rgba(0, 0, 0, 0.5);
            line-height: 300px;
            text-align: center;
            font-size: 16px;
            color: #fff;
        }
    </style>

</head>
<body>
<!-- ********************************* 上传音频 start ********************************* -->
<div class="imgUploadBox" id="t_imgUploadBox">
    <!-- 上传按钮 -->
    <a class="uploadImgBtn" id="t_imgUploadBtn" href="javascript:void(0)">上传音频</a>
    <!-- 上传中遮罩 -->
    <div class="imgUploadLoading" id="t_imgUploadLoading" style="display:none">正在上传...</div>
    <!-- 隐藏FileInput -->
    <input id="t_fileToUpload" style="display:none" type="file" name="soundFile"/>
    <!-- 取值回调信息 -->
    <input id="t_filePath" name="t_filePath" type="hidden" value=""/>
</div>
<!-- ********************************* 上传图片 end ********************************* -->

<script>
    $(function () {
        $("#t_imgUploadBtn").on('click', function () {
            $('#t_fileToUpload').click();
        });

        //选择文件之后执行上传
        $('#t_fileToUpload').on('change', function () {
            $("#t_imgUploadBtn").html("<span></span><img src='${baseStatic}images/upload_default.jpg'/>");
            $("#t_imgUploadLoading").show();

            $.ajaxFileUpload({
                url: '${basePath}util/ajaxUploadSound',
                secureuri: false,
                fileElementId: 't_fileToUpload',//file标签的id
                dataType: 'text',//返回数据的类型
                success: function (data, status) {
                    //图片格式不正确
                    if (data == "imgTypeErr") {
                        alert("文件格式不正确，必须为.mp3/.WMA/文件");
                        $("#t_imgUploadLoading").hide();
                        $("#t_imgUploadBtn").html("音频格式错误，请重传");
                    } else if (data != "failed") {
                        //设置隐藏域路径值
                        $("#t_filePath").val(data);
                        //把图片替换
                        $("#t_imgUploadBtn").html(data);
                        $("#t_imgUploadLoading").hide();
                    } else {
                        $("#t_imgUploadLoading").hide();
                        $("#t_imgUploadBtn").html("上传出错，点击重传");
                    }
                },
                error: function (data, status, e) {
                    $("#t_imgUploadLoading").hide();
                    $("#t_imgUploadBtn").html("上传出错，点击重传");
                }
            });
        });
    });
</script>
</body>
</html>