<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="basePath" value="${pageContext.request.contextPath}/"/>
<c:set var="baseStatic" value="${pageContext.request.contextPath}/static/"/><%-- 项目静态文件路径 --%>
<%@ attribute name="content" type="java.lang.String" required="true" description="消息内容"%>
<%@ attribute name="type" type="java.lang.String" description="消息类型：info、success、warning、error、loading"%>
<link rel="stylesheet" href="${baseStatic}js/jBox/Skins2/Metro/jbox.css" media="all">
<style>
	.alert {
		padding: 8px 35px 8px 14px;
		text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
		border: 1px solid #efb99e;
		-webkit-border-radius: 4px;
		-moz-border-radius: 4px;
		border-radius: 4px;
	}
	.alert-success {
		color: #669533;
		background-color: #d5ecbf;
		border-color: #d2e6ab;
	}
	.alert-error {
		color: #bd4247;
		background-color: #f2bdb1;
		border-color: #f0a5a4
	}
	.alert .close {
		position: relative;
		top: -2px;
		right: -21px;
		line-height: 20px;
	}
	button.close {
		padding: 0;
		cursor: pointer;
		background: transparent;
		border: 0;
		-webkit-appearance: none;
	}
	.close {
		float: right;
		font-size: 20px;
		font-weight: bold;
		color: #000;
		text-shadow: 0 1px 0 #fff;
		opacity: .2;
		filter: alpha(opacity=20);
	}
</style>
<script type="text/javascript" src="${baseStatic}js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="${baseStatic}js/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript">$.jBox.closeTip();</script>
<c:if test="${not empty content}">
	<c:if test="${not empty type}"><c:set var="ctype" value="${type}"/></c:if><c:if test="${empty type}"><c:set var="ctype" value="${fn:indexOf(content,'失败') eq -1?'success':'error'}"/></c:if>
	<div id="messageBox" class="alert alert-${ctype} hide"><button data-dismiss="alert" class="close">×</button>${content}</div> 
	<script type="text/javascript">if(!$.jBox.tip.mess){$.jBox.tip.mess=1;$.jBox.tip("${content}","${ctype}",{persistent:true,opacity:0});$("#messageBox").show();}</script>
</c:if>