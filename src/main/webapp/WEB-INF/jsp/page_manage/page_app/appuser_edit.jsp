<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../../baseJSP.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编辑记录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <script type="text/javascript" src="${baseStatic}common/jeesite.min.js" ></script>
    <%-- 表单校验引用 start --%>
    <script src="${baseStatic}layuiadmin/util_form_check.js"></script>
    <%-- 表单校验引用 end --%>
    <%-- 图片上传引用 start --%>
    <script src="${baseStatic}js/common/upload.js"></script>
    <%-- 图片预览 --%>
    <link type="text/css" rel="stylesheet" href="${baseStatic }imageviewer/dist/viewer.min.css"/>
    <script src="${baseStatic }imageviewer/dist/viewer.js"></script>
    <%-- 图片上传引用 end --%>
</head>
<body>
<div class="layui-fluid">
    <!-- ----------------------正文代码start---------------------- -->
    <form method="post" action="" id="editFrm" class="layui-form layui-card layui-form-pane">
        <%-- 图片预览隐藏域 --%>
        <div id="imgViewDiv" style="display: none;"></div>
        <input type="hidden" id="userId" name="id" value="${data.id}"/>
        <div class="form_div layui-card-body layui-row layui-col-space15">
            <div class="layui-col-md12 layui-col-space15">
                <div class="layui-form-item">
                    <label class="layui-form-label">头像</label>
                    <div class="layui-upload">
                        <input type="hidden" id="headPhoto" name="headPhoto" value="${data.headPhoto}" />
                        <div class="layui-upload-list" style="float: left;"></div>
                        <img src="${baseStatic}images/selectImg.png" id="headPhoto_upload" class="select_img"/>
                    </div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label">登录名</label>
                    <div class="layui-input-block"><input type="text" name="loginName"
                                                          value="${data.loginName}" placeholder="请输入登录名，必填"
                                                          autocomplete="off" class="layui-input"
                                                          lay-verify="chk_null|required" lay-verType="tips"></div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label">登录密码</label>
                    <div class="layui-input-block"><input type="text" name="loginPassword"
                                                          placeholder="默认密码123456，不修改不用填写"
                                                          autocomplete="off" class="layui-input"
                                                          lay-verType="tips"></div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label">用户状态 </label>
                    <div class="layui-input-block" style="width: 182px;">
                        <select name="userState" class="">
                            <c:forEach items="${fns:getDictList('adminState')}" var="dict">
                                <option value="${dict.value}" <c:if test="${data.userState==dict.value}">selected</c:if>>${dict.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-block"><input type="text" name="userName"
                                                          value="${data.userName}" placeholder="请输入姓名"
                                                          autocomplete="off" class="layui-input"
                                                          lay-verType="tips"></div>
                </div>

                <div class="layui-col-md4">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block" style="width: 182px;">
                        <select name="userSex">
                            <option value="0" <c:if test="${data.userSex==0}">selected</c:if>>未知</option>
                            <c:forEach items="${fns:getDictList('sex')}" var="dict">
                                <option value="${dict.value}" <c:if test="${data.userSex==dict.value}">selected</c:if>>${dict.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label">身份证</label>
                    <div class="layui-input-block"><input type="text" name="userCardNo"
                                                          value="${data.userCardNo}" placeholder="请输入身份证"
                                                          autocomplete="off" class="layui-input"
                                                          lay-verType="tips"></div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label">联系电话</label>
                    <div class="layui-input-block"><input type="text" name="userMobile"
                                                          value="${data.userMobile}" placeholder="请输入联系电话"
                                                          autocomplete="off" class="layui-input"
                                                          lay-verType="tips"></div>
                </div>

                <div class="layui-col-md4">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-block"><input type="text" name="userEmail"
                                                          value="${data.userEmail}" placeholder="请输入邮箱"
                                                          autocomplete="off" class="layui-input"
                                                          lay-verType="tips"></div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block"><input type="text" name="userDesc"
                                                          value="${data.userDesc}" placeholder="请输入备注描述"
                                                          autocomplete="off" class="layui-input"
                                                          lay-verType="tips"></div>
                </div>
            </div>
        </div>
        <%-- 提交与重置 start --%>
        <div class="btn_div layui-col-md4">
            <button lay-submit lay-filter="saveActivity" class="layui-btn layui-btn-normal" lay-text="保存">
                <i class="layui-icon layui-icon-release"></i>保存
            </button>
            <button type="reset" class="layui-btn layui-btn-normal" lay-text="重置">
                <i class="layui-icon layui-icon-refresh"></i>重置
            </button>
            <button type="button" onclick="returnList()" class="layui-btn layui-btn-normal" lay-text="返回">
                <i class="layui-icon layui-icon-close"></i>返回
            </button>
        </div>
        <%-- 提交与重置 end --%>
    </form>
    <!-- ----------------------正文代码end---------------------- -->
</div>
<script src="${baseStatic}layuiadmin/util_form_check.js"></script>
<script>
    // var obj = CKEDITOR.instances["userDesc"];
    //  obj.setData($("#txtcontent").html());
</script>
<script>
    layui.config({
        base: '${baseStatic}layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['jquery', 'index', 'form', 'layedit', 'upload', 'table', 'laydate'], function () {
        var $ = layui.jquery;//初始化jquery
        var form = layui.form;//初始化form组件
        var upload = layui.upload;
        var layEdit = layui.layedit; // 初始化富文本编辑框组件

        //自定义文本框、下拉框校验规则
        form.verify(util_from_check);

        //单图片编辑时回显
        loadImgSingle("headPhoto");

        //单图片上传
        uploadImgSingle("headPhoto");

        //监听提交form点击事件
        form.on('submit(saveActivity)', function (data) {
            var datas = JSON.stringify(data.field);
            var loading = layer.msg('正在提交数据', {icon: 16, shade: 0.2, time: 0, offset: 'auto'});
            $.ajax({
                type: "post",
                url: "${basePath}web/appuser/appuser_edit",
                traditional: true,
                data: datas,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (result) {
                    if (result.code == 200) {
                        layer.confirm('数据保存成功', {
                            btn: ['返回列表','继续操作'] //按钮
                        }, function(){
                            closeLayer();
                            //调用父页面列表刷新方法
                            window.parent.$("#searchFrm").submit();
                        }, function(){});
                    } else {
                        layer.msg('数据保存失败，请稍后再试', {offset: 'auto'});
                    }
                }
            });
            //如果需要ajax提交则 return false，data.field即为表单数据的json数据
            return false;
        });
    });

    //返回列表页面
    function returnList(){
        closeLayer();
    }

    //关闭当前layer弹出层
    function closeLayer(){
        //获取窗口索引
        var index = parent.layer.getFrameIndex(window.name);
        window.parent.layer.close(index);
    }
</script>
</body>
</html>