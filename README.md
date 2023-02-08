# SSFBlog

> 简介:
    搭建自己的博客

> 2023-02-08 更新说明:  迁移到java 17 , SpringBoot3
> 2020-06-01 更新说明:  项目迁移至Spring-boot, 使构建运行更简便

---
 
> 快速启动:
   
     
    $ git clone git@github.com:tenie/SSFBlog.git
    //SpringBoot, 推荐使用, 不需要依赖应用服务(如tomcat) 
    $ cd  SSFBlog/SpringBoot
    $ mvn  package
    $ cd target 
    $  java -jar myblog-0.0.1-SNAPSHOT.jar
    打开你的浏览器 : http://localhost:8088
    配置文件: application.yml 根据你的情况配置 
    
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
    
    - SpringBoot3
    - freemarker
    - myBatis
    
-  数据库: 
    
    - h2数据库

       
---
