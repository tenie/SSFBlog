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
	<link rel="shortcut icon" href="../lib/assets/img/codeMonkey.ico"> 
    <!-- Bootstrap Core CSS -->
    <link href="../lib/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
	 <link href="../lib/css/ssfblog_css/main.css" rel="stylesheet">
    <!-- Theme CSS -->
    <link href="../lib/css/clean-blog.min.css" rel="stylesheet"> 
    <!-- Custom Fonts --> 
    <link href="../lib/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../lib/css/font.css" rel="stylesheet" type="text/css"> 
	<link rel="stylesheet" type="text/css" href="../lib/simditor/styles/simditor.css" /> 
	<!--  提示alert样式 -->
	<link rel="stylesheet" type="text/css" href="../lib/js/bootstrap-toastr/toastr.min.css" />  
	<link rel="stylesheet" type="text/css" href="../lib/js/jQuery-FlexSlider/flexslider.css" />  
	<link rel="stylesheet" type="text/css" href="../lib/noty-master/lib/noty.css" />  
	<link rel="stylesheet" type="text/css" href="../lib/bootstrap_switch/css/bootstrap3/bootstrap-switch.css" /> 
	 
<style>
	p{
	margin: 0px 0;
	}
	body{
	    font-size: 14px;
	}
	
	.foo{
	    padding: 10px 15px;
	    width: 100%;
	    height: 80px;
	    font-size: 13px;
	    border: 1px solid #dcdcdc;
	    border-radius: 4px;
	    background-color: hsla(0,0%,71%,.1);
	    resize: none;
	    display: inline-block;
	    vertical-align: top;
	    outline-style: none;
	}
	
	.foosend{
		float: right;
	    width: 78px;
	    margin: 10px 0;
	    padding: 8px 18px;
	    font-size: 16px;
	    border: none;
	    border-radius: 4px;
	    color: #fff!important;
	    background-color: #42c02e;
	    cursor: pointer;
	    outline: none;
	    display: block;
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
    <header  style="margin-top: 65px">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="post-heading">
                        <h2   id="post-title" style="display: inline;">${data.post_title} </h2>
                        <#if data.show_content == 0>
						    <span class="badge"  id="post_badge" style="margin-bottom: 25px;">private</span>
						<#else>  
							 <span class="badge hidden" id="post_badge" style="margin-bottom: 25px;">private</span>
						</#if> 
						<p>
						<#list tags as tag>
							<span style ='color: #888b94;'	>${tag.tag}</span>  &nbsp;
						</#list>
						</p>	
                        <!-- <p  style = "color:#777"  id="post-subtitle">   
                       	 	<span class="meta" id="time">  </span>
                        </p> -->
                    </div>
                    <div class="author" style="    margin: 20px 0 40px;">
			           <!--  <a class="avatar" href="/u/fbd6cad57496" style="width: 48px;height: 48px; vertical-align: middle; display: inline-block;">
			           		 <img style="width: 100%;height: 100%; border: 1px solid #ddd;border-radius: 50%;" src="//upload.jianshu.io/users/upload_avatars/6615117/9954acce-5f24-408e-b3d1-636eecd73903?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144" alt="144">
						</a>          -->
						<div class="info">
				            <span class="tag" style="    padding: 1px 2px;   font-size: 12px;   color: #ea6f5a;   border: 1px solid #ea6f5a;   border-radius: 3px;">作者</span>
				            <span class="name" ><a href="/u/fbd6cad57496">Tenie</a></span>
				            <!-- 关注用户按钮 -->
				            <div data-author-follow-button=""></div>
				            <!-- 文章数据信息 -->
				            <div class="meta" style="margin-top: 5px;font-size: 12px;color: #969696;border-bottom: 1px solid #eee;padding-bottom: 5px;">
				              	<!-- 如果文章更新时间大于发布时间，那么使用 tooltip 显示更新时间 -->
				                <span class="publish-time" id="time">${data.time}  </span>
					            <span class="wordage">字数 ${data.text_length} </span>  
					            <span class="views-count">阅读 ${data.read_quantity}</span>
					            <span class="comments-count">评论 0</span>
					            <span class="likes-count">喜欢 0</span>
					            <span >
					            	   <!-- 如果是当前作者，加入编辑按钮 
								        <a href="javascript:" onclick="ssfblog.editPage(${data.id})" style="    margin-top: -20px;float: right;padding: 0 12px;font-size: 14px;border: 1px solid #dcdcdc;color: #9b9b9b;line-height: 30px;border-radius: 50px;" target="_blank" class="edit">
								      	  编辑文章.
								        </a>--> 
								        <!-- Split button -->
										<div class="btn-group" style="float: right; margin-top: -50px;border: 1px solid #dcdcdc;border-radius: 50px;" >
										  <button type="button" onclick="ssfblog.editPage(${data.id})" class="btn  " style="background-color: transparent;;border-radius: 50px 0px 0px 50px;"> &nbsp; &nbsp; 编 辑 &nbsp; &nbsp; </button>
										  <button type="button"  style="border-radius: 0px 50px 50px 0px;    background-color: transparent;" class="btn  dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										    <span class="caret"></span>
										    <span class="sr-only">Toggle Dropdown</span>
										  </button>
										  <ul class="dropdown-menu">
										     
										      <#if isLog && data.show_content == 1>
												 <li >
												    <a href="javascript:" onclick='ssfblog.hiddenBlog(${data.id})'>
												   		<i class="fa fa-lock"></i>&nbsp;&nbsp;
												   		设为私有
												    </a>
											    </li>
											<#else>  
												 <li class="disabled">
												    <a href="javascript:" >
												   		<i class="fa fa-lock"></i>&nbsp;&nbsp;
												   		设为私有
												    </a>
											    </li>
											</#if> 
										     <#if isLog  && data.show_content == 0>
												 <li >
												    <a href="javascript:" onclick='ssfblog.publicBlog(${data.id})' >
													    <i class="fa fa-unlock" aria-hidden="true"></i>&nbsp;
													    公开
												    </a>
											    </li>
											<#else>  
												 <li class="disabled">
												    <a href="javascript:"  >
													    <i class="fa fa-unlock" aria-hidden="true"></i>&nbsp;
													    公开
												    </a>
											    </li>
											</#if> 	
										    
										    <li role="separator" class="divider"></li>
										     <#if isLog>
												 <li >
												   <a href="javascript:" onclick="ssfblog.deleteBlog(${data.id})"  >
													    <i class="fa fa-trash-o" aria-hidden="true"></i>&nbsp;
													    删除
												    </a>
											    </li>
											<#else>  
												 <li class="disabled">
												    <a href="javascript:"    >
													    <i class="fa fa-trash-o" aria-hidden="true"></i>&nbsp;
													    删除
												    </a>
											    </li>
											</#if> 
										    
										  </ul>
										</div>
								        
					            </span>
				            </div>
				         </div> 
			        </div>
			        
                </div>
            </div>
        </div>
    </header>

    <!-- Post Content -->
    <article >
        <div class="container"> 
            <div class="row">
                <div id="postContent"class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1"  >
                  ${data.post_content} 
				</div>
            </div>
            
            <div class="row"><!-- 分割线-->
       		 	<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" >
                   <div style="border-bottom: 1px solid #eee;padding-bottom: 5px;"></div>
				</div>
       		</div>
       		 <div class="row"><!-- 喜欢按钮-->
       		 	<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" >
						<div class="meta-bottom">
						    <div class="like">
						    	<div class="btn like-group" style=" padding: 13px 0 15px;  font-size: 0;  border: 1px solid #ea6f5a;  border-radius: 40px;">
						    		<div class="btn-like" style="font-size: 19px;  display: inline-block;">
						    		<a style="    color: #ea6f5a; padding: 18px 15px 18px 30px;"><i style="    margin-right: 5px;font-size: 21px;" class="iconfont ic-like"></i>喜欢</a>
						    	</div> 
						   	<div class="modal-wrap" style="font-size: 18px;  border-left: 1px solid rgba(236,97,73,.4);  display: inline-block;">
						   		<a style="    color: #ea6f5a; padding: 18px 30px 18px 17px;">3</a></div>
						   	</div> <!----></div> 
						</div>                    
				</div>
       		</div>
       		
            <div class="row">
       		 	<div id="post_coment"class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" >
                   <div class="row">
                   		<div class=col-xs-12">
                   			评论
                   		</div>
                   		<div class=col-xs-12">
                   			 <!-- 评论模块, 包含主评论和子评论 -->
								<div  id="comment-12597916" class="comment" style="padding: 20px 0 30px;border-bottom: 1px solid #f0f0f0;">
									<div class="master_comment">
										<div class="author" style="    margin-bottom: 15px;">
											<a href="#" style= "margin-right: 5px; width: 38px; height: 38px;  vertical-align: middle;  display: inline-block;" target="_blank" class="avatar">
												<img style="width: 100%; height: 100%;  border: 1px solid #ddd;  border-radius: 50%;" src="//upload.jianshu.io/users/upload_avatars/5951273/c18ed5e9-7077-462d-a163-976f1232be28.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/114/h/114">
											</a>
											<div class="info" style="display: inline-block; vertical-align: middle;">
												<a href="/u/ef1ab35d8e27" target="_blank" class="name">不倒翁Linda</a><!---->
												<div class="meta" style="font-size: 12px;  color: #969696;">
													<span>2楼 · 2017.07.07 22:33</span>
												</div>
											</div>
										</div>
										<div class="comment-wrap">
											<div class="comment_content">
												<p style=" font-size: 16px;">
												皓月当空，泪眼婆娑晚安💤愿远嫁的姑娘都幸福<img src="//static.jianshu.io/assets/emojis/blossom.png" alt=":blossom:" title=":blossom:" class="emoji" width="20" height="20"><img src="//static.jianshu.io/assets/emojis/cherry_blossom.png" alt=":cherry_blossom:" title=":cherry_blossom:" class="emoji" width="20" height="20">🍵
												</p>
											</div> 
												<a  href="#"style="color:#969696"> <i class="fa fa-thumbs-o-up" aria-hidden="true"></i> <span>赞</span></a>
												<a onclick="foo()" href="javascript:"style="color:#969696"> <i class="fa fa-commenting-o" aria-hidden="true"></i> <span>回复</span></a> 
										</div>
									</div>
									<!-- 子评论: 初始时没有-->
									<div class="sub-comment-list  "  style="margin-top: 20px;  padding: 5px 0 5px 20px; border-left: 2px solid #d9d9d9;">
										<div id="comment-12598354" class="sub-comment" style="margin-bottom: 15px;  padding-bottom: 15px;  border-bottom: 1px dashed #f0f0f0;">
											<p style="margin: 0 0 5px; font-size: 14px; line-height: 1.5;">
												<a style='    color: #3194d0;' href="/u/0f6e22338545" target="_blank">简舒悦</a>：
												<span><a href="/users/ef1ab35d8e27" class="maleskine-author" target="_blank" data-user-slug="ef1ab35d8e27">@不倒翁Linda</a> 谢谢，晚安</span>
											</p>
											<div class="sub-tool-group" style="font-size: 12px; color: #969696;">
												<span>2017.07.07 22:43</span>
												<a href="#" style="margin-left: 10px;  color: #969696;"><i class="fa fa-commenting-o" aria-hidden="true"></i><span>回复</span></a>
												<!--<a class="report"><span>举报</span></a>-->
											</div>
										</div>
										<div class="sub-comment more-comment"  style="font-size: 14px; margin-bottom: 15px;  padding-bottom: 15px; color: #969696; border: none;">
											<a style="color: #969696" href="javascript:" class="add-comment-btn"><i class="fa fa-pencil" aria-hidden="true"></i><span>添加新评论</span></a><!----><!----><!---->
										</div>
										<!---->
                                        <!-- 回复评论-->
                                            <div id="reply_area" class="hidden" >
                                                <form class="new-comment"> 
                                                	<div class="row">
  <div class="col-xs-6">
    <div class="input-group">
      <span class="input-group-btn">
        <a  class="btn btn-default" style="    padding: 6px 12px;">name</a>
      </span>
      <input style="border-radius:0;" type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
   <div class="col-xs-6">
    <div class="input-group">
      <span class="input-group-btn">
        <a class="btn btn-default" style="    padding: 6px 12px;" type="button">email</a>
      </span>
      <input style="border-radius:0;" type="text" class="form-control" placeholder="Search for...">
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 --> 
</div><!-- /.row -->
                                                	
                                                	
                                                	
                                                	
                                                    <textarea class="foo" placeholder="写下你的评论...">
                                                    </textarea>
                                                    <div style="height: 50px;" class="write-function-block">
                                                        <div class="emoji-modal-wrap">
                                                            <a class="emoji">
                                                                <i class="iconfont ic-comment-emotions">
                                                                </i>
                                                            </a> 
                                                        </div>
                                                        <div class="hint" style="float: left;  margin: 20px 0 0 20px; font-size: 13px;  color: #969696;"> ⌘+Return 发表 </div>
                                                        <a class="btn btn-send  foosend"> 发送 </a>
                                                        <a class="cancel" style=" float: right; margin: 18px 30px 0 0; font-size: 16px;  color: #969696;"> 取消 </a>
                                                    </div>
                                                </form>
                                            </div>	
									</div>
									
																	
								</div>                  			 
                   		</div>
                   		
                   </div>
				</div>
       		</div>
        </div>
    </article>

    <hr>
    

    <!-- Footer -->
    <footer id="footer"> 
    </footer>
    
    
    
	 
<!-- jQuery -->
<script src="../lib/vendor/jquery/jquery.min.js"></script>   
<script src="../lib/js/jquery.cookie.js" type="text/javascript"></script> 
<!--  my js-->
<script src="../lib/js/ssfblog_js/main.js"></script> 
<script src="../lib/vendor/bootstrap/js/bootstrap.min.js"></script> 
<!-- Contact Form JavaScript -->
<script src="../lib/js/jqBootstrapValidation.js"></script>
<script src="../lib/js/contact_me.js"></script> 
<!-- Theme JavaScript -->
<script src="../lib/js/clean-blog.min.js"></script> 
<script src="../lib/js/bootstrap-toastr/toastr.min.js"></script>
     <!-- 富文本编辑框 -->
<script type="text/javascript" src="../lib/simditor/scripts/module.js"></script>
<script type="text/javascript" src="../lib/simditor/scripts/hotkeys.js"></script> 
<script type="text/javascript" src="../lib/simditor/scripts/simditor.js"></script> 
<script type="text/javascript" src="../lib/js/jQuery-FlexSlider/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="../lib/noty-master/lib/noty.js"></script> 
<script type="text/javascript" src="../lib/noty-master/lib/mo.min.js"></script>
 <script>
 	function foo(){ 
 		$("#reply_area").removeClass("hidden")
 	}
 </script>
</body>
</html>
