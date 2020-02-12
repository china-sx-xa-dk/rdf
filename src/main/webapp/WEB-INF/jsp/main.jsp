<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="baseJSP.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>欢迎登陆rdf管理系统</title>
</head>
<body class="layui-layout-body">
  
  <div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
      <div class="layui-header">
        <!-- 头部区域 -->
        <ul class="layui-nav layui-layout-left">
          <li class="layui-nav-item layadmin-flexible" lay-unselect>
            <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
              <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
            </a>
          </li>
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;" layadmin-event="refresh" title="刷新">
              <i class="layui-icon layui-icon-refresh-3"></i>
            </a>
          </li>
        </ul>
        
        <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="theme">
              <i class="layui-icon layui-icon-theme"></i>
            </a>
          </li>
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;">
              <%--${sessionScope.currentUser.loginName}--%>
              <cite>${sessionScope.currentUser.adminName}</cite>
            </a>
            <dl class="layui-nav-child">
              <dd><a lay-href="${basePath}admin/goChangePass">修改密码</a></dd>
              <hr>
              <dd style="text-align: center;"><a href="${basePath}logout">退出</a></dd>
            </dl>
          </li>
          
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;"><i class="layui-icon layui-icon-more-vertical"></i></a>
          </li>
          <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
            <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
          </li>
        </ul>
      </div>

      <!-- 侧边菜单 -->
      <div class="layui-side layui-side-menu">
        <div class="layui-side-scroll">
          <div class="layui-logo" lay-href="${basePath}index">
            <span> rdf 管理平台 </span>
          </div>
          
          <dl class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
            <c:if test="${sessionScope.currentUser.roleId != 14}">
              <li data-name="home" class="layui-nav-item">
                <a href="javascript:;" lay-tips="工作台" lay-direction="2">
                  <i class="layui-icon layui-icon-home"></i>
                  <cite>工作台</cite>
                </a>
                <dl class="layui-nav-child">
                  <dd data-name="index" class="">
                    <a lay-href="${basePath}dispatch?page=introduce">更新日志</a>
                  </dd>
                </dl>
              </li>
            </c:if>

            <c:if test="${!empty sessionScope.perList}">
              <c:forEach var="p" items="${sessionScope.perList}">
                <c:if test="${!empty p.perList && p.permissionFname != null && p.permissionnFame != ''}">
                  <li data-name="component" class="layui-nav-item">
                    <a href="javascript:;" lay-tips="${p.permissionFname}">
                      <i class="layui-icon layui-icon-component"></i>
                      <cite>${p.permissionFname}</cite>
                    </a>
                    <dl class="layui-nav-child">
                      <c:forEach var="c" items="${p.perList}">
                        <dd data-name="button">
                          <a lay-href="${basePath}${c.permissionValue}">${c.permissionName}</a>
                        </dd>
                      </c:forEach>
                    </dl>
                  </li>
                </c:if>
              </c:forEach>
            </c:if>

                
            </ul>
          </dl>
        </div>
      </div>

      <!-- 页面标签 -->
      <div class="layadmin-pagetabs" id="LAY_app_tabs">
        <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-down">
          <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
            <li class="layui-nav-item" lay-unselect>
              <a href="javascript:;"></a>
              <dl class="layui-nav-child layui-anim-fadein">
                <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
              </dl>
            </li>
          </ul>
        </div>
        <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
          <ul class="layui-tab-title" id="LAY_app_tabsheader">
            <li lay-id="${basePath}index" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
          </ul>
        </div>
      </div>
      <!-- 主体内容 -->

      <div class="layui-body" id="LAY_app_body">
        <div class="layadmin-tabsbody-item layui-show">
          <iframe src="${basePath}dispatch?page=introduce" frameborder="0" class="layadmin-iframe"></iframe>
        </div>
      </div>

      <!-- 辅助元素，一般用于移动设备下遮罩 -->
      <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
  </div>

  <script>
  <c:if test="${currentUser eq null}">window.parent.parent.location = "${basePath}";</c:if>
  layui.config({
    base: '${baseStatic}layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['jquery','index'],function(){

  });

  layui.data.theme = function(){
    layui.use('form', function(){
      var form = layui.form
              ,admin = layui.admin;

      //监听隐藏开关
      form.on('switch(system-theme-sideicon)', function(){
        admin.theme({
          hideSideIcon: this.checked
        })
      });
    });
  };

  </script>
</body>
</html>