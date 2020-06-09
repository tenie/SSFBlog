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
   
博客有2模式, 游客和博主模式.

- 游客 : 简单讲就是Read Only,其功能有:
    - 分页查询所以公开的博文 
    - 根据标签查询有相同标签的博文列表
    - 查看博文详细内容
    - 发信息给博主 
- 博主 : 需要帐号密码登入, 登入后有以下权限:
    - 发布博文
    - 修改博文
    - 删除博文
    - 编辑博文
    - 隐藏博文
    - 公开博文 
  



 
 

---
> 技术栈:
1. 前端:
    
    1. 展示层(view): 基于bootstrap 的一个开源模板:[cleanblog](https://startbootstrap.com/template-overviews/clean-blog/)
    1. 控制层(control): jQuery框架, 页面使用ajax,与服务器端API交互json数据来控制view层;
    1. 数据模型(model) : 调用服务器端API 都会返回一个相同结构的json数据对象, 字段信息如下:
    
        名称 | 类型 | 说明
        ---|---|---
        error | boolean | 判断调用是否成功
        msg| string | 服务器返回的信息
        data | list | 数据集合(键值对的数据结构)
        mapRs | map | 键值对数据
        success| string | 冗余字段,目前没用
    1. 其他:  
        1. 页面模板 : [cleanblog](https://startbootstrap.com/template-overviews/clean-blog/)
        1. alert组件 :  [noty.js](http://ned.im/noty) 
 
        1. 富文本编辑组件 :  [ckeditor.js](http://ckeditor.com/)
        1. 图标组件 :  [Font Awesome](http://fontawesome.io/)
        1. 图片查看组件: [fengyuanchen/viewer](https://fengyuanchen.github.io/viewer)
        1. 其他库: Bootstrap;
        Bootstrap-switch.js;
        jquery.js;
        jQuery.validate.js;
        jquery.cookie.js;
        还有来源网络的图片资源..
         
1. 后端: 
    
    使用框架:
    1. 使用Spring框架全家桶
    2. 使用freemarker
    3. ORM,使用Activejdbc, myBatis
    
1. 数据库: 
    
    使用h2数据库, 去除了mySql, 做到开箱即用, 首次使用会自动创建表.

       
---
