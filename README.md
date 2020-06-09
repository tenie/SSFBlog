# SSFBlog

> 简介:
    搭建属于自己的博客
    
> 2020-06-01 更新说明:  项目迁移至Spring-boot, 使构建运行更简便
---
 
> 快速启动:
   
     
    git clone git@github.com:tenie/SSFBlog.git
    //SpringBoot方式
    配置文件: application.yml 根据你的情况配置
    $ cd  SSFBlog/SpringBoot
    $ mvn  package
    $ cd target 
    $  java -jar myblog-0.0.1-SNAPSHOT.jar
    打开你的浏览器 : http://localhost:8088
    
    //SpringFramework方式
    配置文件: application.yml 根据你的情况配置
    $ cd   SSFBlog/SpringFramework/parent
    $ mvn  package
    $ cp web/target/Root.war tomcat/webapps/
    $ tomcat/bin/startup.sh
    在浏览器中键入 http://localhost:8088 就ok了~           //这里的端口根据你的应用服务器的情况而定
   
> 博客有2模式, 访客和博主模式.

- 访客 : 
    - 查询,搜索公开的博文  
    - 发评论, 私信博主 
- 博主 : 通过帐号登入后有以下权限:
    - 发布, 修改, 删除, 编辑博文
    - 隐藏/公开博文 

---
> 技术栈:
-  前端:
 
    - 页面模板 : [cleanblog](https://startbootstrap.com/template-overviews/clean-blog/)
    - alert组件 :  [noty.js](http://ned.im/noty) 
    - 富文本编辑组件 :  [ckeditor.js](http://ckeditor.com/)
    - 图标组件 :  [Font Awesome](http://fontawesome.io/)
    - 图片查看组件: [fengyuanchen/viewer](https://fengyuanchen.github.io/viewer)
    - 其他库: Bootstrap;
    Bootstrap-switch.js;
    jquery.js;
    jQuery.validate.js;
    jquery.cookie.js;
    还有来源网络的图片资源..
         
-  后端: 
    
    - 使用Spring框架全家桶
    - 使用freemarker
    - ORM,使用Activejdbc, myBatis
    
-  数据库: 
    
    - 使用h2数据库, 去除了mySql, 做到开箱即用, 首次使用会自动创建表.

       
---
