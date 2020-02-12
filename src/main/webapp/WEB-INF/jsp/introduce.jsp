<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="baseJSP.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color: white;">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10,IE=edge,chrome=1"/>
    <title>Rapid Development Framework 快速开发框架</title>
    <link rel="stylesheet" href="${baseStatic}layuiadmin/style/admin.css" media="all">
    <script type="text/javascript" src="${baseStatic}js/jquery.min.js"></script>
    <style>
        .place {
            height: 40px;
            background: url(${baseStatic}images/righttop.gif) repeat-x;
        }
        ul {
            display: block;
            margin: 0;
            padding: 0;
            list-style: none;
        }
        .placeul li {
            float: left;
            line-height: 40px;
            padding-left: 7px;
            padding-right: 12px;
            <%--background: url(${baseStatic}images/rlist.gif) no-repeat right;--%>
        }
        li {
            display: block;
            margin: 0;
            padding: 0;
            list-style: none;
        }
        .place span {
            line-height: 40px;
            font-weight: bold;
            float: left;
            margin-left: 12px;
        }

        dl, dt, dd, span {
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<!-- 位置 start -->
<div class="place">
    <span style="font-family: '微软雅黑';color: #333;">欢迎使用RDF框架</span>
</div>

<div class="layui-fluid">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend>更新日志</legend>
    </fieldset>
    <ul class="layui-timeline">
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">重大更新需注意，更多更新详情请自行查看svn记录</h3>
                <p>
                    <a target="_blank" href="https://github.com/spring-projects/spring-boot/releases/tag/v2.1.6.RELEASE">Springboot框架底层升级至2.1.6,官方Spring Boot 1.5 将在19年8月结束生命周期</a>
                    <br><a target="_blank" href="https://www.showdoc.cc/gokrdf?page_id=2405611575663598">代码生成新增功能字段及使用解析</a>
                    <br><a target="_blank" href="https://www.showdoc.cc/gokrdf?page_id=2241593056480804">MybatisPlus3.1.1组件说明</a>
                    <br><a target="_blank" href="https://www.showdoc.cc/gokrdf?page_id=2397248754689003">Token验证及Session验证</a>
                    <br><a target="_blank" href="https://www.showdoc.cc/page/edit/366669741323355/2441979890721909">常用标签及写法,controller返回值示例</a>
                    <br><a target="_blank" href="https://www.showdoc.cc/gokrdf?page_id=2396994170025618">系统加载启动及缓存开关</a>
                    <br><a target="_blank" href="https://www.jianshu.com/p/97222440cd08">yaml配置文件,层级结构明显,除普通配置,还可配置复杂变量,配置List等</a>
                </p>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019-07-11</h3>
                <p>
                    新增统一web返回fail状态时的统一写法
                    <br>- 新增代码生成controller删除功能
                    <br>- 新增代码生成新增修改页面保存后步骤,可选择返回列表或留在当前页
                    <br>- 新增代码生成创建人,更新人,创建时间,更新时间默认字段
                    <br>- 新增代码生成创建人,更新人可直接关联sys_admin表中名称
                    <br>- 新增代码生成时,由创建时间,更新时间时页面添加时间日期选择器,按照日期可查询
                    <br>- 新增代码生成时,由创建时间,更新时间时condition按照数据库生成对应查询使用的时间字符串
                    <br>- 新增全局404问题捕获,自动完成跳转404页面
                    <br>- 新增全局500异常捕获,取消继承BaseController的Controller下的异常处理,交由框架完成统一异常处理
                    <br>- 新增日志打印优化,完成分级,分文件夹日志打印demo,添加Junit测试日志打印套件
                    <br>- 新增数据字典缓存更新开关,可通过开关配置数据字典是否自动刷新缓存
                    <br>- 修复代码生成后,页面上的数据为零不显示数据为空问题
                    <br>- 修复代码生成,编辑页面显示问题
                    <br>- 修复代码生成insert方法无法填入id返回值的问题
                    <br>- 修复代码生成页面传参controller接值按照String的问题
                    <br>- 修复代码生成controller调用service时id参数为string的问题
                    <br>- 修复新增修改页面中三个按钮的显示问题
                    <br>- 修复代码生成中查询无法正确携带创建时间和更新时间值进行回显的问题
                    <br>- 修复因时区问题springboot框架无法正常获取数据库时间字段的问题
                    <br>- 修复CKFinder上传图片后无法查看预览的问题
                    <br>- 修复CKFinder组件在appuser中使用时无法正常回显缩略图的问题
                    <br>- 修复代码生成controller捕获异常后返回值异常的问题
                    <br>- 变更代码生成默认id,修改为int型,交由数据库完成自增
                    <br>- 变更代码生成查询条件,由全等查询变更为int型全等,varchar型Like模糊查询
                    <br>- 变更打开ErrorPageFilter功能,完成全局捕获异常及报错
                    <br>- 变更Springboot框架底层由2.0.4升级到2.1.6
                    <br>- 变更日志打印配置,开放总配置,取消日志文件打印(按需手动开放)
                    <br>- 变更全局500异常捕获提示信息为中文
                    <br>- 变更代码生成中修改页面的返回提示操作判断,新增不成功时提示
                    <br>- 优化404,500错误页面的返回首页的链接问题
                    <br>- 优化系统配置中的日志捕获问题
                    <br>- 优化代码生成中controller的日志捕获问题
                    <br>- 优化yaml配置,因springboot底层升级部分配置读取不到
                    <br>- 优化maven打包策略,删除无效配置
                    <br>- 优化文件上传工具类中修复当前使用的路径问题,添加mac和linux下的路径修复 详情
                    <br>- 优化大部分新增修改页面,修复三个按钮样式问题,部分页面添加返回按钮
                    <br>- 优化系统配置新增修改时的操作流程问题
                    <br>- 优化页面数据字典ftl标签取值问题,由直接从持久化数据库取值修改为redis取值
                    <br>- 删除首页默认14权限跳转页面的代码,为测试遗留代码
                    <br>- 删除AppUserController中无效重复异常代码
                    <br>- 删除部分框架无效代码
                    <br>- 删除BaseController中的无效的Springboot容器错误页面处理代码
                    <br>- 删除因jdk版本过高引入sun公司私有类的图片相关及二维码生成相关代码
                    <br>- 删除系统日志新增功能
                    <br>- 删除系统日志修改,详情功能
                </p>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019-07-05</h3>
                <p>
                    新增session验证功能,详情查看 Token验证及Session验证
                    <br>- 新增拦截器静态资源开放
                    <br>- 新增普通页面背景添加动态特效 背景及点击特效
                    <br>- 新增点击事件全局添加新型特效 背景及点击特效
                    <br>- 新增app接口新增redis相关操作
                    <br>- 新增系统配置信息新增修改删除时,自动刷新redis缓存
                    <br>- 修复app用户使用默认密码添加后,无法正常登陆的问题修复
                    <br>- 优化Junit测试套件
                    <br>- 变更后台系统初始化左侧菜单取消收缩改为开放
                    <br>- 变更静态资源文件位置变更为static
                    <br>- 变更app接口用户缓存从持久化数据库变更为redis信息
                    <br>- 优化yaml结构,添加部分注释,提升可读性
                    <br>- 优化生成配置文件结构
                    <br>- 删除bootstrap相关css
                    <br>- 删除用于持久化用户登陆的token信息表
                    <br>- 删除Junit中EhCache缓存单元测试套件
                    <br>- 删除代码生成多余目录结构
                </p>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019-06-28</h3>
                <p>
                    新增后台用户导入导出功能
                    <br>- 新增页面统一信息返回模版封装,示例在AppUser相关页面
                    <br>- 新增Redis缓存组件,配置信息在总配置文件中
                    <br>- 新增Redis缓存工具类,提供日常可使用的redis的功能,底层使用sringboot中的redistemplate,如需新增功能可自行查看相关api
                    <br>- 新增appuser列表页面性别搜索功能,并入数据字典
                    <br>- 新增添加系统配置文件是否在系统启动时写入redis的开关,默认关闭状态,rdf.loadConfigWhenStart : false
                    <br>- 修复文件管理查看功能报错
                    <br>- 修复后台用户异常捕获处理
                    <br>- 修复系统配置列表页面的字段显示bug
                    <br>- 修复系统配置中的字段与数据库关键字冲突
                    <br>- 变更密码显示
                    <br>- 变更系统主页面进入后左侧菜单栏默认打开,如需默认关闭，只需给main.jsp 中id为LAY_app的div添加class="layadmin-side-shrink"即可,还需把主页的显示收缩状态的图标修改
                    <br>- 优化数据字典管理页面功能
                    <br>- 优化后台角色,组织结构,菜单管理,appuser功能页面样式优化,接口返回提示
                    <br>- 优化导入导出时有数据字典字段的功能
                    <br>- 优化数据导出样式
                </p>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019-06-21</h3>
                <p>
                    - 新增app登陆接口异常捕获
                    <br>- 新增ztree组件,用于部分业务需求弹窗显示树形结构
                    <br>- 新增代码生成模版
                    <br>- 新增CKEditor组件引入,富文本编辑器
                    <br>- 新增继承ECharts插件
                    <br>- 新增ECache缓存配置
                    <br>- 新增YAML配置文件
                    <br>- 修复代码生成模版中jsp页面模版修改EL表达式冲突问题
                    <br>- 修复文件管理器报错修复
                    <br>- 变更appuser列表及修改页面添加新版数据字典使用示例
                    <br>- 变更appuser修改页面添加CKEditor使用示例
                    <br>- 变更首页修改工作台默认显示内容
                    <br>- 优化model结构,condition,vo文件夹移入model文件夹,model下结构变更为domain,condition,vo三个文件夹同级
                    <br>- 优化树形结构选择
                    <br>- 删除AppUserController中无效代码
                    <br>- 删除代码生成中所有无效模版
                    <br>- 删除springboot启动类中ECache缓存
                </p>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019-06-15</h3>
                <p>
                    rdf框架初始化 <a target="_blank" href="https://www.showdoc.cc/gokrdf?page_id=2241618674164771">官方文档</a>
                    <br>- springboot2.0.4版本
                    <br>- mybatisplus3.1版本
                    <br>- mysql5.0版本以上
                    <br>- 组件初始化
                    <br>- 代码生成
                    <br>- LogBack-Spring日志
                    <br>- Lombok
                    <br>- YAML配置文件
                </p>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">特别鸣谢</h3>
                <p>
                    张总  钦总鼎力支持
                    <br>吴国梁 毋浩 李玮 初代版本合并的倾力付出
                    <br>王旭 唐朝俊 杨媚 刘薇 张拓 王昱心 使用及优化建议
                </p>
            </div>
        </li>
    </ul>
</div>
</body>
</html>