# SSFBlog

> 简介:
    展示博文,发布博文, 修改博文的博客应用.
    
博客有2模式, 游客和博主模式.

- 游客 : 简单讲就是Read Only,其他功能有:
    - 分页查询所以公开的博文 
    - 根据标签查询有相同标签的博文列表
    - 查看博文详细内容
    - 发信息给博主 
- 博主 : 需要帐号密码登入后, 有以下权限:
    - 发布博文
    - 修改博文
    - 删除博文
    - 编辑博文
    - 隐藏博文
    - 公开博文 
  

---
> 构建:
    
    构建前需要对配置文件,进行配置, 键入你的数据(如:数据库密码,邮箱信息,配置登入帐号密码)
    配置文件: SSDBlog/src/parent/web/src/main/resources/webconfig.properties
    $ cd SSDBlog/src/parent
    $ mvn clean package
    $ cp ./web/target/ROOT.war your_tomcat_home/webapps/
    $ your_tomcat_home/bin/startup.sh  ||  $ your_tomcat_home/bin/startup.bat

 在浏览器中键入 http://localhost:8080 就ok了~
 
 

---
> 技术栈:
1. 前端:
    1. 展示层(view): 基于bootstrap 的一个开源模板:[cleanblog](https://github.com/deviodigital/cleanblog/)
    2. 控制层(control): jQuery框架, 页面使用ajax,与服务器端API交互json数据来控制view层;
    3. 数据模型(model) : 调用服务器端API 都会返回一个相同结构的json数据对象, 字段信息如下:
    
        名称 | 类型 | 说明
        ---|---|---
        error | boolean | 判断调用是否成功
        msg| string | 服务器返回的信息
        data | list | 数据集合(键值对的数据结构)
        mapRs | map | 键值对数据
        success| string | 冗余字段,目前没用
    4. 其他: 
    
        项目使用了很多开源项目, 能用上他们的项目非常幸运.
        还有很多网站的页面设计很优秀网站让我大开眼睛,学到很多,如:[简书](http://www.jianshu.com/)的文章阅读页面,当前阅读页面标题部分是模仿简书的(未能超越);
        页面中的置顶按钮是从[极客标签](http://www.gbtags.com/)学来的,其他不一一列举了(如有版权问题,联系我删除~)
        1. 页面模板 : [cleanblog](https://github.com/deviodigital/cleanblog/)
        1. alert组件 :  [noty.js](http://ned.im/noty) 
        1. 富文本编辑组件 :  [simditor.js](http://simditor.tower.im/)
        1. 富文本编辑组件 :  [simditor.js]()
        1. 图标组件 :  [Font Awesome](http://fontawesome.io/)
        1. 其他知名框架: Bootstrap;
        Bootstrap-switch.js;
        jquery.js;
        jQuery.validate.js;
        jquery.cookie.js;
        google.fonts;还有来源网络的图片资源..
         
        
1. 后端:
    1. 使用Spring框架全家桶中的小半桶;IoC,MVC,AOP,DAO
    2. 使用freemarker,在渲染文章阅读页面
    3. ORM,使用Activejdbc, 非常轻量级的orm.单线程的,没有连接池,使用的时结合环绕AOP,去除了样板代码.
    
1. 数据库: mySQL; 没什么说的


       
---
