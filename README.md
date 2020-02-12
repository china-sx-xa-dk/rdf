<h1><a href="https://github.com/china-sx-xa-dk/Java-frame/tree/master/rdf">rdf快速开发后台管理系统脚手架</a></h1>

​		一直使用SpringMVC和JDK7完成日常工作，近来初识SpringBoot，并且升级JDK8，想多尝试函数式编程和新的特性，使用网络上找到的各种组件，搭建了一个快速开发后台管理系统的脚手架RDF。

​		SpringBoot初学，技术也并不是非常大牛，框架中的问题会持续的进行修改，如果有好的意见也非常愿意接受，谢谢。

​		本项目完全是出于学习和交流的目的，非常欢迎给予友善的建议和评论。

>  **rdf是想边学习springboot和提升Java编写的一个后台管理系统的脚手架** 

>  **取名rdf是公司同事的建议，自己的取名实在不敢恭维，我可能直接就叫demo1！** 

>  **基本功能都已实现，在公司同事的帮助下和领导的同意下，也使用了几个不大的项目进行了实验** 

>  **框架适用于较小业务型后台管理系统，因为没有考虑分布式，所以还是使用的jsp的方式展现页面** 

>  **如果对你有帮助的话请右上角点个star，非常感谢！** 

![JDK](https://img.shields.io/badge/jdk-1.8-green.svg?style=flat-square) [![LICENSE](https://img.shields.io/github/license/gujiniCY/mayday.svg?style=flat-square)](https://github.com/gujiniCY/mayday/blob/master/LICENSE)

#### [RDF-快速开发后台管理系统运行说明](https://www.showdoc.cc/669549704349225?page_id=3884607577102281)

​	帮助文档:使用showdoc	点击标题跳转

​	开源协议:MIT

​	致谢：

1. [SpringBoot](http://spring.io/projects/spring-boot) 版本2.0.4
2. [MyBatis](http://www.mybatis.org/mybatis-3/) ORM框架
3. [MySQL](https://www.mysql.com/) 数据库，版本8.0
4. [Maven](http://maven.apache.org/) 依赖管理
5. [Druid](https://github.com/alibaba/druid/) 阿里连接池
6. LayUIAdmin 后台模板
7. 陕西高格信息技术有限公司同事们的帮助和项目实际使用经验

## 使用代码说明

1. 下载代码,使用Idea,其他的没试过,别问这么多,我暂时只会用idea搭建
   `git clone https://github.com/china-sx-xa-dk/rdf.git`
2. 运行sql文件夹下的sql文件
3. 到src/main/resources下的application.yaml下修改你的数据库链接
4. 最好是新建一个tomcat选择提供的两个部署包中的任意一个完成运行，可看到效果
5. [点击查看部署方案](https://www.showdoc.cc/gokrdf?page_id=3036244322512958)

## 图片演示