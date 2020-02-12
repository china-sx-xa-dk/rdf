<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%--<%--%>
<%--String path = request.getContextPath();--%>
<%--String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";--%>
<%--%>--%>
<%@ include file="baseJSP.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10,IE=edge,chrome=1"/>
    <title>Rapid Development Framework 快速开发框架</title>
    <style type="text/css">
        body{padding:0px;margin:0px;background:#fbfbfb;font-family: 微软雅黑;}
        .top_bar{width:100%;height:80px;background:#fff;padding:10px 0px;float:left;webkit-box-shadow: 1px 0px 10px 2px #CECECE;box-shadow: 1px 0px 10px 2px #CECECE;}
        .top_bar .top_cBox{width:900px;height:80px;margin:0px auto;}
        .top_bar .top_cBox .top_logo{float:left;height:45px;margin-top:7px;line-height:45px;font-size:40px;font-weight:700;text-decoration:none;color:#439ACC;}
        .top_bar .top_cBox .top_logo b{color: #a63307;}
        .top_bar .top_cBox .top_logo img{height:45px;}
        .middle_box{width:100%;float:left;padding:50px 0px;background:#52B8F2; position:absolute;left:0px;top:50%;margin-top:-250px;}
        .middle_box .m_cBox{width:1100px;height:400px;margin:0px auto;}
        .middle_box .m_cBox .login_img{margin-top:30px;width:540px;float:left;height:340px;overflow:hidden;text-align:center;}
        .middle_box .m_cBox .login_img img{height:340px;}
        .middle_box .m_cBox .login_box{width:320px;height:380px;background:#fff;float:right;margin-right:120px;margin-top:15px; webkit-box-shadow: 1px 0px 10px 2px #2787BD;box-shadow: 1px 0px 10px 2px #2787BD;}
        .middle_box .m_cBox .login_box h1{padding:0px;margin:0px;padding:15px 0px;text-indent:25px;font-size:24px;color:#439ACC;margin-top:8px;}
        .m_ipt{width:268px;height:40px;float:left; border:#ddd 1px solid;margin-left:25px;overflow:hidden;margin-top:15px;}
        .m_ipt i{display:block;width:30px;height:30px;float:left;margin:5px;}
        .m_ipt i img{width:30px;height:30px;}
        .m_ipt input{width:224px;height:40px;float:right;font-size:14px;line-height:40px;border:none;margin-top:-1px;outline:none;color:#555;background-color:#fff;text-indent:10px;}
        .m_rInfo{width:268px;height:25px;float:left;margin-left:25px;margin-top:15px;line-height:25px;font-size:12px;color:#999;}
        .m_rInfo input{float:left;margin-top:6.3px;}
        .m_btn{width:268px;height:40px; line-height:40px; float:left; margin-left:25px;overflow:hidden;margin-top:15px;background:#439ACC;color:#fff;text-align:center;
            font-size:18px;text-decoration:none;}
        .m_btn:hover{background:#4091BF;}
        .f_info{width:100%;height:20px;line-height:20px;color:#888;font-size:12px;text-align:center;position:fixed;bottom:20px;left:0px;}

        .top_web{float:right;line-height:65px;color:#666;font-size:16px;}
    </style>
    <script type="text/javascript" src="${baseStatic}js/jquery.min.js"></script>
    <!-- jbox -->
    <script type="text/javascript" src="${baseStatic}js/jBox/jquery.jBox-2.3.min.js"></script>
    <script type="text/javascript" src="${baseStatic}js/jBox/jquery.jBox-zh-CN.js"></script>
    <link type="text/css" rel="stylesheet" href="${baseStatic}js/jBox/Skins2/Metro/jbox.css"/>

    <script type="text/javascript">
        $(function(){
            //登录
            $("#loginBtn").click(function(){
                var userName = $.trim($("#userName").val());
                var password = $.trim($("#password").val());
                if(userName=="" || password==""){
                    $("#err_tip").html("用户名，密码不能为空！");
                }else{
                    $.jBox.tip("正在努力登录中...", "loading");
                    $("#loginFrm").submit();
                }
            });
        });

        //回车提交表单
        $(document).keyup(function(event){
            if(event.keyCode ==13){
                $("#loginBtn").trigger("click");
            }
        });
    </script>
</head>
<body>
<!-- 头部logo等 start -->
<div class="top_bar">
    <div class="top_cBox">
        <a href="${basePath}" class="top_logo"><b>Rapid Development Framework 快速开发框架</b></a>
    </div>
</div>
<!-- 头部logo等 end -->
<!-- 中部内容区 Start -->
<div class="middle_box">
    <div class="m_cBox">

        <div class="login_img">
            <img src="${baseStatic}images/login/login.png" style="" />
        </div>

        <!-- 登录盒子 start -->
        <div class="login_box">
            <h1>用户登录</h1>
            <form id="loginFrm" action="${basePath}login" method="post">

                <!-- error显示区 start -->
                <div id="err_tip" style="width:268px;float:left;height:15px; line-height:15px; margin-left:25px; color:#F54E19;font-size:14px;">
                    <c:if test="${msg != null}">
                        ${msg}
                    </c:if>
                </div>
                <!-- error显示区 end -->

                <div class="m_ipt">
                    <i><img src="${baseStatic}images/icon_user.png"/></i>
                    <input type="text" id="userName" name="userName" placeholder="请输入您的账号"/>
                </div>
                <div class="m_ipt">
                    <i><img src="${baseStatic}images/icon_pwd.png"/></i>
                    <input type="password" id="password"  type="password" name="password" placeholder="请输入您的密码"/>
                </div>
                <div class="m_rInfo">
                    <input type="checkbox" id="remember" name="remember" value="1"/> 记住我一周
                </div>
                <a href="javascript:void(0)" id="loginBtn" class="m_btn">登录</a>
            </form>
            <div class="m_rInfo">
                如果您忘记密码？请联系管理员进行重置。
            </div>
        </div>
        <!-- 登录盒子 end -->
    </div>
</div>
<!-- 中部内容区 end -->


<!-- 页脚start -->
<div class="f_info">Copyright  ©2017-2019  Corporation, All Rights Reserved sxgokit</div>
<!-- 页脚end -->
</body>
</html>