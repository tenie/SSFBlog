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
	<link rel="shortcut icon" href="lib/assets/img/codeMonkey.ico"> 
    <!-- Bootstrap Core CSS -->
    <link href="lib/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
	 <link href="lib/css/ssfblog_css/main.css" rel="stylesheet">
    <!-- Theme CSS -->
    <link href="lib/css/clean-blog.min.css" rel="stylesheet"> 
    <!-- Custom Fonts --> 
    <link href="lib/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="lib/css/font.css" rel="stylesheet" type="text/css"> 
	<link rel="stylesheet" type="text/css" href="lib/simditor/styles/simditor.css" /> 
	<!--  提示alert样式 -->
	<link rel="stylesheet" type="text/css" href="lib/js/bootstrap-toastr/toastr.min.css" />  
	<link rel="stylesheet" type="text/css" href="lib/js/jQuery-FlexSlider/flexslider.css" />  
	<link rel="stylesheet" type="text/css" href="lib/noty-master/lib/noty.css" />  
	<link rel="stylesheet" type="text/css" href="lib/bootstrap_switch/css/bootstrap3/bootstrap-switch.css" /> 
	    <link href="lib/vendor/font-awesome/css/font-awesome-animation .css" rel="stylesheet" type="text/css">
	
	<!--   图片轮播的css-->
	 <!-- <link href="css/style.css" rel="stylesheet"> -->
	 
<style>


/*  搜索输入框*/
.search {
border: 0px !important;
}
#searchInput:focus{
	border: 0px !important;
}
/*  搜索*/
div.search {padding: 30px 0}
form {
  position: relative;
  width: 300px;
  margin: 0 auto;
}
/* .d1 {background: #A3D0C3;} */
.d1 input {
  width: 100%;
  height: 42px;
  padding-left: 10px;
  border: 2px solid #7BA7AB;
  border-radius: 5px;
  outline: none;
  background: #F9F0DA;
  color: #9E9C9C;
}
.d1 button {
  position: absolute; 
  top: 0;
  right: 0px;
  width: 42px;
  height: 42px;
  border: none;
  background: #7BA7AB;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
}
.d1 button:before {
  content: "\f002";
  font-family: FontAwesome;
  font-size: 16px;
  color: #F9F0DA;
}
/*  按钮动画*/
.wrapper-inner-tab-backgrounds-first{
	float: left;	
	width: 33.33%;
	background-color: #feb41d;
}
.button19 {
	color: rgba(255,255,255,1);
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	-o-transition: all 0.3s;
	transition: all 0.3s;
	position: relative;
	border: 1px solid rgba(255,255,255,0.5);
	overflow: hidden;
}
.button19 a{
	color: rgba(51,51,51,1);
	text-decoration: none;
	display: block;
}
.button19::before{
	content: '';
	position: absolute; 
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
    z-index: 1;
	background-color: rgba(255,255,255,0.25);
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	-o-transition: all 0.3s;
	transition: all 0.3s;
	-webkit-transform: translate(13%,190%) rotate(-30deg);
	transform: translate(13%,190%) rotate(-30deg);
	-webkit-transition-timing-function: cubic-bezier(0.75, 0, 0.125, 1);
	transition-timing-function: cubic-bezier(0.75, 0, 0.125, 1);
}
.button19::after{
	content: '';
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
    z-index: 1;
	background-color: rgba(255,255,255,0.25);
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	-o-transition: all 0.3s;
	transition: all 0.3s;
	-webkit-transform: translate(-13%,-190%) rotate(-30deg);
	transform: translate(-13%,-190%) rotate(-30deg);
	-webkit-transition-timing-function: cubic-bezier(0.75, 0, 0.125, 1);
	transition-timing-function: cubic-bezier(0.75, 0, 0.125, 1);
}
.button19::before{
	-webkit-transition-delay: 0.2s; /* Safari */
    transition-delay: 0.2s;
}
.button19:hover::before, .button19:hover::after{
	-webkit-transform: translate(0,0);
	transform: translate(0,0);	
}


</style>
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
    <header class="intro-header" style="background-image: url('lib/img/home-bg.jpg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="site-heading">
                        <h1>Tenie Blog</h1>
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
        
      <!--   <div id="loading">
			<div id="loading-center">
				<div id="loading-center-absolute">
					<div id="object"></div>
				</div>
			</div> 
		</div> -->
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" > 
            	 <div id="loading"  >
						<div id="loading-center">
							<div id="loading-center-absolute">
								<div class="object" id="object_four"></div>
								<div class="object" id="object_three"></div>
								<div class="object" id="object_two"></div>
								<div class="object" id="object_one"></div> 
							</div>
						</div> 
					</div>
                  <div id="pageTitleContainer"  >
              		
                  </div>  
               <!--  <div id="loading" class="loading">Loading pages...</div>   -->
                <!-- Pager -->
                <ul class="pager">
                	 <li class="previous ">
                        <a  href="javascript:" id="previous">&larr; 上一页 </a> <!--  class=" cursor_not_allowed" -->
                    </li>
                    <li class="next">
                        <a href="javascript:" id="next">下一页 &rarr;</a>
                        
                        
        
<!-- <div class="wrapper-inner-tab-backgrounds">
	<div class="wrapper-inner-tab-backgrounds-first">
		<div class="sim-button button19" data-text="Login">
			<span>Login</span>
		</div>
	</div> 
</div> -->
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <hr> 
    <!-- Footer -->
    <footer id="footer"> 
    </footer>

<!-- jQuery -->
<script src="lib/vendor/jquery/jquery.min.js"></script>   
<script src="lib/js/jquery.cookie.js" type="text/javascript"></script> 
<!--  my js-->
<script src="lib/js/ssfblog_js/main.js"></script> 
<script src="lib/vendor/bootstrap/js/bootstrap.min.js"></script> 
<!-- Contact Form JavaScript -->
<script src="lib/js/jqBootstrapValidation.js"></script>
<script src="lib/js/contact_me.js"></script> 
<!-- Theme JavaScript -->
<script src="lib/js/clean-blog.min.js"></script> 
<script src="lib/js/bootstrap-toastr/toastr.min.js"></script>
     <!-- 富文本编辑框 -->
<script type="text/javascript" src="lib/simditor/scripts/module.js"></script>
<script type="text/javascript" src="lib/simditor/scripts/hotkeys.js"></script> 
<script type="text/javascript" src="lib/simditor/scripts/simditor.js"></script> 
<script type="text/javascript" src="lib/js/jQuery-FlexSlider/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="lib/noty-master/lib/noty.js"></script> 
<script type="text/javascript" src="lib/noty-master/lib/mo.min.js"></script>

<div class="modal fade" tabindex="-1" role="dialog" id="searchModal">
    <div class="modal-dialog modal-lg" role="document" style="max-width: 700px;" >
      <div class="modal-content" style=" box-shadow: -3px 10px 20px #f5f5f5; top: 200px;  ">
			<div class="input-group input-group-lg">
			 
			  <span class="input-group-addon " id="searchbtn" style="border: 0px;background-color:white;"><a href="javascript:"><i class="fa fa-search" aria-hidden="true"></i></a></span>
			  <input id="searchInput"  type="text" class="form-control search" placeholder="搜索..." >
			</div> 
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div>
</body> 
<script>
	function showSearch(){
		$('#searchModal').modal({backdrop:true,show:true}); 
	}
</script> 
</html>
