# SSFBlog

> 简介:
    搭建自己的博客

> 2023-02-08 更新:  迁移到java 17 , SpringBoot3


---
 
> 快速启动:
   
     
    $ git clone git@github.com:tenie/SSFBlog.git
    $ cd  SSFBlog/SpringBoot
    $ mvn  package -Dmaven.test.skip=true
    $ cd target 
    $ java -jar myblog-0.0.1-SNAPSHOT.jar
    浏览器打开 : http://localhost:8088
    配置文件: application.yml 配置信息
    
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
