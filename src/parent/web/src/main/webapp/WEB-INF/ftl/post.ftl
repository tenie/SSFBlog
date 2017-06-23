<#setting classic_compatible=true>
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
                        <h2 id="post-title">${data[0].post_title} </h2>
                        <h4 class=" " id="post-subtitle"> ${data[0].post_subtitle} </h4>
                        <span class="meta" id="time">Posted by <a href="#">Tenie Bolg </a> on ${data[0].time} </span>
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
                  ${data[0].post_content} 
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



</body>
<script type="text/javascript">

 
	$(function(){ 
	 
	 
		ssfblog.initPage() 
		//ssfblog.initPost()
		 
		ssfblog.backToTop()
		
		
	})
	
	 
	 
</script>

</html>
