<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tenie Blog</title>
	<!--  小图标 -->
	<link rel="shortcut icon" href="/assets/img/codeMonkey.ico">
    <!-- Bootstrap Core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="/css/clean-blog.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/simditor/styles/simditor.css" /> 
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
  
	 
	<link rel="stylesheet" type="text/css" href="/lib/noty-master/lib/noty.css" /> 
	
	<!--  pageTransition-->
	<link href="/css/pageTransition/animations.css" rel="stylesheet">
	
	<link href="/css/ssfblog_css/main.css" rel="stylesheet">
	  
<style>
	p{
	margin: 0px 0;
	}
	body{
	    font-size: 14px;
	}
	

</style>
</head>

<body class="fadeIn postpage">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-custom navbar-fixed-top" > 
    </nav>

    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
  <!--   <header class="intro-header" style="background-image: url('img/post-bg.jpg')"> -->
    <header  >
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="post-heading">
                        <h2   id="post-title">${data.post_title} </h2>
                        <!-- <p  style = "color:#777"  id="post-subtitle"> 
							${data.post_subtitle} 
                       	 	<span class="meta" id="time">  </span>
                        </p> -->
                    </div>
                    <div class="author" style="    margin: 30px 0 40px;">
			           <!--  <a class="avatar" href="/u/fbd6cad57496" style="width: 48px;height: 48px; vertical-align: middle; display: inline-block;">
			           		 <img style="width: 100%;height: 100%; border: 1px solid #ddd;border-radius: 50%;" src="//upload.jianshu.io/users/upload_avatars/6615117/9954acce-5f24-408e-b3d1-636eecd73903?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144" alt="144">
						</a>          -->
						<div class="info">
				            <span class="tag" style="    padding: 1px 2px;   font-size: 12px;   color: #ea6f5a;   border: 1px solid #ea6f5a;   border-radius: 3px;">作者</span>
				            <span class="name" ><a href="/u/fbd6cad57496">Tenie</a></span>
				            <!-- 关注用户按钮 -->
				            <div data-author-follow-button=""></div>
				            <!-- 文章数据信息 -->
				            <div class="meta" style="margin-top: 5px;font-size: 12px;color: #969696;">
				              	<!-- 如果文章更新时间大于发布时间，那么使用 tooltip 显示更新时间 -->
				                <span class="publish-time" id="time">${data.time}  </span>
					            <span class="wordage">字数 ${data.text_length} </span>  
					            <span class="views-count">阅读 ${data.read_quantity}</span>
					            <span class="comments-count">评论 0</span>
					            <span class="likes-count">喜欢 0</span>
					            <span >
					            	   <!-- 如果是当前作者，加入编辑按钮 -->
								        <a href="#" style="    margin-top: -20px;float: right;padding: 0 12px;font-size: 14px;border: 1px solid #dcdcdc;color: #9b9b9b;line-height: 30px;border-radius: 50px;" target="_blank" class="edit">
								      	  编辑文章
								        </a>
					            </span>
				            </div>
				         </div> 
			        </div>
			        
                </div>
            </div>
        </div>
    </header>

    <!-- Post Content -->
    <article>
        <div class="container">
       		<div class="row">
       		 	<div id="postTitle"class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                   
				</div>
       		</div>
            <div class="row">
                <div id="postContent"class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                  ${data.post_content} 
				</div>
            </div>
        </div>
    </article>

    <hr>

    <!-- Footer -->
    <footer id="footer"> 
    </footer>
    
    
    

    <!-- jQuery -->
<script src="/vendor/jquery/jquery.min.js"></script> 
<script src="/js/jquery.cookie.js" type="text/javascript"></script> 
<script src="/js/ssfblog_js/main.js"></script>

<!--  <script src="/https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>-->
<!-- Bootstrap Core JavaScript https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css-->
<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--> 

<!-- Contact Form JavaScript -->
<script src="/js/jqBootstrapValidation.js"></script>
<script src="/js/contact_me.js"></script>

<!-- Theme JavaScript -->
<script src="/js/clean-blog.min.js"></script> 
     <!-- 富文本编辑框 -->
<script type="text/javascript" src="/simditor/scripts/module.js"></script>
<script type="text/javascript" src="/simditor/scripts/hotkeys.js"></script> 
<script type="text/javascript" src="/simditor/scripts/simditor.js"></script>


<script type="text/javascript" src="/lib/noty-master/lib/noty.js"></script>

<script type="text/javascript" src="/lib/noty-master/lib/mo.min.js"></script>

</body>
<script type="text/javascript"> 
	$(function(){   
		ssfblog.initPage() 
		//ssfblog.initPost() 
		ssfblog.backToTop() 
	}) 
</script>

</html>
