# SSFBlog

> 简介:
    展示博文,发布博文, 修改博文的博客应用.
    
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
  

> 更新说明: 将项目迁移到了Spring-boot, 构建运行更简单了
---
> 构建:
   
     
    配置文件: application.yml 根据你的情况配置
    $ cd  Springt-boot
    $ mvn  package
    $ cd target
    $  java -jar myblog-0.0.1-SNAPSHOT.jar  
 在浏览器中键入 http://localhost:8088 就ok了~           //这里的端口根据你的应用服务器的情况而定
 
 

---
> 技术栈:
1. 前端:
    1. 主要页面是静态的, 数据是动态加载的, 其中每个页面都要用到的导航栏, 和footer 是制作成单独html,在页面初始化的时候进行单独加载的到主页面中.
       在首页分页查询篇博文是前端控制的, 写在了js代码中, 现在是每次加载10篇文章,如不满意可以修改源码main.js; 该分页组件是我自己写的,结合了,后端的分页查询缓存,所以,在修改前端分页相关的代码时, 可能不起效果是因为后端缓存造成的.
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
        
        项目使用了很多开源项目, 能用上这些项目非常幸运.
        还有很多网站的页面设计很优秀让我大开眼睛, 豁然开朗,包塞顿开,学到很多,如:[简书](http://www.jianshu.com/)的文章阅读页面,当前阅读页面标题部分是模仿简书的(未能超越);
        页面中的置顶按钮是从[极客标签](http://www.gbtags.com/)学来的,其他不一一列举了(如有版权,联系我删除~)
        1. 页面模板 : [cleanblog](https://startbootstrap.com/template-overviews/clean-blog/)
        1. alert组件 :  [noty.js](http://ned.im/noty) 
        1. ~~富文本编辑组件 :  [simditor.js](http://simditor.tower.im/)~~ 
        1. 富文本编辑组件 :  [ckeditor.js](http://ckeditor.com/)
        1. 图标组件 :  [Font Awesome](http://fontawesome.io/)
    1. 图片查看组件: [fengyuanchen/viewer](https://fengyuanchen.github.io/viewer)
        1. 其他知名框架: Bootstrap;
        Bootstrap-switch.js;
        jquery.js;
        jQuery.validate.js;
        jquery.cookie.js;
        google.fonts;还有来源网络的图片资源..
         
        
1. 后端:
    
    后端提供的都是JSON数据结构API,数据结构见"前端model"部分. 除了一个文章阅读页面, 是使用freemarker框架输出数据渲染好的页面外; 其他页面数据渲染工作都在前端利用后端输出的JSON数据完成.

    题外话: 第一版的文章阅读页面也是同过ajax请求json数据来渲染, 但是不利于分享, 转发网址是无法定位到具体文章的.
    后来改为restful风格url来统一定位每篇文章了; 让后端把页面数据渲染后输出对应文章.当然了,其他的所有的API都是restful api;
    
    使用到的框架:
    1. 使用Spring框架全家桶中的小半桶;IoC,MVC,AOP,DAO
    2. 使用freemarker,在渲染文章阅读页面
    3. ORM,使用Activejdbc, 非常轻量级的orm.单线程的,没有连接池,使用的时结合环绕AOP和IoC中的连接池对象,去除了打开链接关闭链接,事务的样板代码.
    
    对于后端的博客首页, 进行了数据缓存的, 如果没有修改过数据, 每次都是从后端缓存中直接返回给前端的.减少了数据库的访问; 在AOP中有统一的缓存清空(符合指定url的请求后, 会清空缓存, 如有新文章发布,更新等...)
    
1. 数据库:
    
    ~~mySQL;需要建4个表; 见src目录下的sql脚本.~~
    
    ~~1. 一个表用来存放发表的博文信息:内容,状态,阅读量等..~~

    ~~2. 一个表用来存博文的标签, 是博文表的子表.与上表一对多的关系~~
    
    ~~3. 还有一个表存放联系页面, 提交心信息;~~
    
    ~~4. 还有一个评论表, 添加评论数据~~
    
    数据库改用h2, 不依赖mySql了, 真正做到开箱即用, 首次使用会自动创建表.

       
---
