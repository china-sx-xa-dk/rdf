<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="baseJSP.jsp" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>404</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${baseStatic}css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${baseStatic}js/jquery.min.js"></script>
<style>
.tipBox{padding-bottom:50px;border:#ddd 1px solid;background-color:#f5f5f5; background-image:url("${baseStatic}images/seller/404.png");background-position:900px center;background-repeat:no-repeat;}
.tipBox .row.nobg [class*="col-"]{background:#f5f5f5;border:#f5f5f5 2px solid;}
.tipTle{float:left;font-size:20px;color:#666;}
.tipTle span{font-family:Helvetica;font-size:50px;margin-right:15px;color:#0E90D2;}
.tipIntro{font-size:14px;color:#666;float:left;}
</style>
</head>
<body>
  
  	
  	<div id="dcMain" style="margin-left:0px;position:inherit;padding-top:0px;">
  		<!-- 业务代码 start -->
  		<div id="index" class="mainBox" >
  		<!-- ------------------------------------ 正文代码开始 start ------------------------------------ -->
  		
  			<div class="e_main tipBox ">
				<div class="row nobg mt_15">
					<div class="col-x-1"></div>
					<div class="col-x-4">
						<div class="tipTle mt_15"><span>404</span>您要查看的页面暂时找不到</div>
					</div>
				</div>
				<div class="row nobg">
					<div class="col-x-1"></div>
					<div class="col-x-4">
						<span class="tipTle mt_15">我们其实也很想帮您找到页面，但是我们也是无能为力了!</span>
					</div>
				</div>
				<div class="row nobg">
					<div class="col-x-1"></div>
					<div class="col-x-4">
						<span class="tipIntro mt_10">你还重新尝试刷新一下本页面，或联系管理员</span>
					</div>
				</div>
				<div class="row nobg mt_25">
					<div class="col-x-1"></div>
					<div class="col-x-4">
						<a href="javascript:location.reload()" class="frm_btn btn_blue">刷新当前页面</a>
						<a href="${basePath}dispatch?page=introduce" class="frm_btn btn_def">返回首页</a>
					</div>
				</div>
			</div>		
			
			
		<!-- ------------------------------------ 正文代码开始 end ------------------------------------ -->			
  		</div>
  		<!-- 业务代码 end -->
  	</div>
</body>
</html>