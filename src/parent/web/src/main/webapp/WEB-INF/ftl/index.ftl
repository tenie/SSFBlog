<!DOCTYPE html>
<html lang="">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Tenie Blog</title>


<!--  小图标 -->
<link rel="shortcut icon" href="/lib/assets/img/codeMonkey.ico">
<!-- Bootstrap Core CSS -->
<link href="/lib/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/lib/css/ssfblog_css/main.css" rel="stylesheet">
<!-- Theme CSS -->
<link href="/lib/css/clean-blog.min.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="/lib/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- <link
	href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'> -->
<!--  提示alert样式 -->
<link rel="stylesheet" type="text/css"
	href="/lib/noty-master/lib/noty.css" />
<link rel="stylesheet" type="text/css"
	href="/lib/bootstrap_switch/css/bootstrap3/bootstrap-switch.css" />

<!--   图片轮播的css-->
<!-- <link href="css/style.css" rel="stylesheet"> -->


</head>

<body class="index_page">
	<!--搜索  -->
	<!-- <form id="search">
<div class="modal fade " id="searchModal" tabindex="-1" role="dialog" aria-labelledby="searchModalLabel" style="display: block;">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      
    </div>
  </div>
</div>
</form> -->




	<!--  图片轮播 , 图片是通过css加载的-->
	<!-- <div>
		<div class="bg-slider-wrapper">
	        <div class="flexslider bg-slider">
	            <ul class="slides">
	                <li class="slide slide-1" style="width: 100%; float: left; margin-right: -100%; position: relative; opacity: 0; display: block; z-index: 1;"></li>
	                <li class="slide slide-2" style="width: 100%; float: left; margin-right: -100%; position: relative; opacity: 0; display: block; z-index: 1;"></li>
	                <li class="slide slide-3 flex-active-slide" style="width: 100%; float: left; margin-right: -100%; position: relative; opacity: 1; display: block; z-index: 2;"></li>
	            </ul>
	        </div>
	    </div> 
	</div>  -->

	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
	</nav>

	<!-- Page Header -->
	<!-- Set your background image for this header on the line below. -->
	<header class="intro-header"
		style="background-image: url('/lib/img/home-bg.jpg')">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
					<div class="site-heading">
						<h1>
							<a href="/" style="color: white">Tenie Blog</a>
						</h1>
						<hr class="small">
						<span class="subheading">Everything is temporary</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Main Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
				<!-- loading 动画 -->
				<div id="loader-wrapper" class="in">
					<div class="sk-circle">
						<div class="sk-circle1 sk-child"></div>
						<div class="sk-circle2 sk-child"></div>
						<div class="sk-circle3 sk-child"></div>
						<div class="sk-circle4 sk-child"></div>
						<div class="sk-circle5 sk-child"></div>
						<div class="sk-circle6 sk-child"></div>
						<div class="sk-circle7 sk-child"></div>
						<div class="sk-circle8 sk-child"></div>
						<div class="sk-circle9 sk-child"></div>
						<div class="sk-circle10 sk-child"></div>
						<div class="sk-circle11 sk-child"></div>
						<div class="sk-circle12 sk-child"></div>
					</div>
				</div>
				<!--  内容块-->
				<div id="pageTitleContainer">
					<#if foo.mapRs.dataList?exists> 
					<#list foo.mapRs.dataList as bd>
					<div class="post-preview">
						<a href="article/${bd.ID}" class="src_page">
							<p class="post-title" style="display: inline;">
								<#if bd.TOP==0>
								   <span class='blogtop_flag top_tag'><i class='fa fa-flag' aria-hidden='true'></i></span>
								<#else>
									<span class='unblogtop_flag top_tag'><i class='fa fa-flag-o' aria-hidden='true'></i></span>
								</#if>
								${bd.POST_TITLE}  
							</p>
						</a>
						<#if bd.SHOW_CONTENT== 0 >
							<span class="badge  " style="margin-bottom: 40px;">private</span>
							<#else>
							<span class="badge hidden" style="margin-bottom: 40px;">private</span>
						</#if>

						<p class="post-subtitle"
							style="margin-top: 0px; margin-bottom: 10px; font-size: 14px;">
							<i class="fa fa-tags" aria-hidden="true"></i>
							<#list bd.tags as val >
							<a  style ='color: #888b94;'rel='${val}' href='javascript:' onclick='ssfblog.tagSearch("${val}")'>${val}</a>  &nbsp;
							</#list>
						</p>

						<div class="row  ">
							<div class="col-xs-12"
								style="padding-left: 15px; font-size: 14px;">
								<!--  时间-->
								<i class="fa fa-calendar" aria-hidden="true"></i> <span
									class="post_time" style=''>${bd.TIME}</span>
								<div class="btn-group">
									<a href="javascript:" style="margin-left: 15px;"
										class="dropdown-toggle" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"> <i
										class="fa fa-cog" aria-hidden="true"></i>
									</a>
									<ul class="dropdown-menu">
										<#if islogin =="n">
											    <li><a  href="javascript:" onclick="ssfblog.alert('warning','需要登入才可编辑~')" class="editbtn"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> 编辑</a></li>
											    <li role="separator" class="divider"></li>
											    <li class="disabled"><a href="javascript:" class="deletebtn"><i class="fa fa-trash-o" aria-hidden="true"></i> 删除</a></li> 
											    <li class="disabled"><a href="javascript:" class="hiddenbtn"><i class="fa fa-lock"></i> 设为私有</a></li>
											    <li class="disabled"><a href="javascript:" class="publicbtn"><i class="fa fa-unlock" aria-hidden="true"></i> 公开</a></li> 
										 <#else>
											    <li><a href="/article/getEditPage/${bd.ID}"  class="editbtn"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> 编辑</a></li>
											    <li role="separator" class="divider"></li>
											    <li class=" "><a href="javascript:"  class="deletebtn"  rel="${bd.ID}" ><i class="fa fa-trash-o" aria-hidden="true"></i> 删除</a></li> 
											    <li class=" "><a href="javascript:" class="hiddenbtn"><i class="fa fa-lock"></i> 设为私有</a></li>
											    <#if  bd.SHOW_CONTENT == 0 >
											      <li class=" "><a href="javascript:" class="publicbtn"><i class="fa fa-unlock" aria-hidden="true"></i> 公开</a></li> 
												<#else>
												  <li class="disabled"><a href="javascript:" class="publicbtn"><i class="fa fa-unlock" aria-hidden="true"></i> 公开</a></li> 
												</#if>  
										 </#if>	
										
										
										 	
										
									</ul>
								</div>
							</div>
						</div>
					</div>
					<hr>

					</#list> </#if>
				</div>
				<!-- Pager -->
				<ul class="pager"> 
					<#if previouspage gte 0  > 
					<li class="previous "><a href="/page/${previouspage}" id="previous">&larr; 上一页 </a> <!--  class=" cursor_not_allowed" --></li>
					</#if>
					<#if nextpage gte 0  > 
					<li class="next"><a href="/page/${nextpage}" id="next"> 下一页 &rarr; </a></li>
					</#if>
					
				</ul>
			</div>
		</div>
	</div>

	<hr>
	<!-- Footer -->
	<footer id="footer"> </footer>

	<!-- jQuery -->
	<!-- <script src="lib/vendor/jquery/jquery.min.js"></script>   
<script src="lib/js/jquery.cookie.js" type="text/javascript"></script>  -->
	<!--  my js-->
	<!-- <script src="lib/js/ssfblog_js/main.js"></script> 
<script src="lib/vendor/bootstrap/js/bootstrap.min.js"></script>  -->
	<!-- Theme JavaScript -->
	<!-- 
<script src="lib/js/clean-blog.min.js"></script> -->
	<!-- 富文本编辑框 -->
	<!-- <script type="text/javascript" src="lib/noty-master/lib/noty.js"></script> 
<script type="text/javascript" src="lib/noty-master/lib/mo.min.js"></script> -->

	<script src="/lib/js/ssfblog_js/app.js"></script>
	<script src="/lib/js/ssfblog_js/main.js"></script>

</body>
<#if islogin =="y">
<script>
//登入状态下才可以删除....操作

	var hiddenbtn =	$(".hiddenbtn");
	var deletebtn =	$(".deletebtn");
	var publicbtn = $(".publicbtn");
	//隐藏按钮事件(设为私有)
	hiddenbtn.click(function(){  
		   if(!$(this).parent().hasClass("disabled")){
			   var id =$(this).parent().siblings().find(".deletebtn").attr("rel") 
			   ssfblog.hiddenBlog(id)
		   } 
	})
		//删除按钮事件
	deletebtn.click(function(){
		var id =$(this).attr("rel")
		ssfblog.deleteBlog(id)
	})
	
	//public文章按钮事件
	publicbtn.click(function(){
		   if(!$(this).parent().hasClass("disabled")){
			   var id =$(this).parent().siblings().find(".deletebtn").attr("rel") 
				ssfblog.publicBlog(id)
		   } 
	})
	//移除禁用样式 
//	hiddenbtn.parent().removeClass("disabled") 
	deletebtn.parent().removeClass("disabled")
//	publicbtn.parent().removeClass("disabled")
	
 
</script>

</#if>
</html>
