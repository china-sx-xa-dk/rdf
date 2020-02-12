<%@page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags" %>
<%
    String path = request.getContextPath();
    String ajaxPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}/"/>
<%-- 引用/配置请添加注释说明用途，避免重复引用/代码冗余 (^_^) --%>

<%-- 路径相关配置 START --%>
<c:set var="basePath" value="${pageContext.request.contextPath}/"/><%-- 项目路径 --%>
<c:set var="baseStatic" value="${pageContext.request.contextPath}/static/"/><%-- 项目静态文件路径 --%>
<c:set var="ajaxPath" value="<%=ajaxPath%>"/><%-- AJAX请求访问路径 --%>
<c:set var="bg_imgUpload" value="${baseStatic}/images/bg_upLoadImg.png"/><!-- 上传默认背景图片 -->
<c:set var="bg_imgUploadIng" value="${baseStatic}images/bg_upLoadImgIng.gif"/><!-- 上传中背景图片 -->
<c:set var="bg_imgUploadErr" value="${baseStatic}images/bg_upLoadImgErr.png"/><!-- 上传出错背景图片 -->
<%-- 路径相关配置 END --%>

<%-- 公共CSS引用 START --%>
<link type="text/css" rel="stylesheet" href="${baseStatic}layuiadmin/layui/css/layui.css" media="all"/><%-- layui公共CSS --%>
<link type="text/css" rel="stylesheet" href="${baseStatic}layuiadmin/style/admin.css" media="all"/><%-- layui公共CSS --%>
<link type="text/css" rel="stylesheet" href="${baseStatic}css/common/common.css" media="all"/><%-- 自定义系统公共CSS --%>
<%-- 公共CSS引用 END --%>

<%-- 公共JS引用 START --%>
<script type="text/javascript" src="${baseStatic}js/jquery.min.js"></script><%-- Jquery公共JS --%>
<script type="text/javascript" src="${baseStatic}layuiadmin/layui/layui.js"></script><%-- layui公共JS --%>
<script type="text/javascript" src="${baseStatic}js/common/tableCheck.js"></script><%-- 表格复选框处理公共JS --%>
<%-- 公共JS引用 END --%>

<%-- 拓展效果 start --%>
<script type="text/javascript" src="${baseStatic}js/beautifulheart.js"></script>
<%--<canvas id="c_n9" width="1920" height="990" style="position: fixed; top: 0px; left: 0px; z-index: -1; opacity: 0.5;"></canvas>--%>
<%--<script src="https://files.cnblogs.com/files/jingmoxukong/canvas-nest.min.js"></script>--%>
<%--<script type="text/javascript" src="${baseStatic}js/canvas-nest.min.js"></script>--%>
<%-- 拓展效果 end --%>