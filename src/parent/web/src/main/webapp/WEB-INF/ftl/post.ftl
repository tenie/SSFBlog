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
       		 	<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" style="margin-top: 20px;margin-bottom: 30px;" >
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
                   		<div class="col-xs-12">
                   			<h5>
                   			 <span id="coment_count">${commentLength}</span> 评论
                   			</h5>
                   		</div>
                   		<div class="col-xs-12">
						<#list comments as commentObj> 
							   
                   			 <!-- 评论模块, 包含主评论和子评论 -->
								<div  id="comment-12597916" class="comment" style="padding: 20px 0 30px;border-bottom: 1px solid #f0f0f0;">
									<div class="master_comment">
										<div class="author" style="    margin-bottom: 15px;">
											<a href="#" style= "margin-right: 5px; width: 38px; height: 38px;  vertical-align: middle;  display: inline-block;" target="_blank" class="avatar">
												<img style="width: 100%; height: 100%;  border: 1px solid #ddd;  border-radius: 50%;" src="/lib/img/head/head_96.754632229085px_1196697_easyicon.net.png">
											</a>
											<div class="info" style="display: inline-block; vertical-align: middle;">
												<a href="javascript:" target="_blank" class="name">${commentObj.name}</a><!---->
												<div class="meta" style="font-size: 12px;  color: #969696;">
													<span>${commentObj_index + 1 }楼 · ${commentObj.created_at}</span>
												</div>
											</div>
										</div>
										<div class="comment-wrap">
											<div class="comment_content">
												<p style=" font-size: 16px;">
												${commentObj.comment}
												</p>
											</div> 
												<a  href="#"style="color:#969696"> <i class="fa fa-thumbs-o-up" aria-hidden="true"></i> <span>赞</span></a>
												<a onclick="foo(this,${commentObj.id})" href="javascript:"style="color:#969696"> <i class="fa fa-commenting-o" aria-hidden="true"></i> <span>回复</span></a> 
										</div>
									</div>
									  
								  <#if  commentObj.subcomment?has_content  >
								  <#assign i =  commentObj.subcomment?size>
								  
								   <#list commentObj.subcomment as item>  
									<!-- 子评论: 初始时没有 subcommentMap-->
									<div class="sub-comment-list  "  style="margin-top: 20px;  padding: 5px 0 5px 20px; border-left: 2px solid #d9d9d9;">
										<div id="comment-12598354" class="sub-comment" style="margin-bottom: 15px;  padding-bottom: 15px;  border-bottom: 1px dashed #f0f0f0;">
											<p style="margin: 0 0 5px; font-size: 14px; line-height: 1.5;">
												<a style='    color: #3194d0;' href="javascript:" target="_blank">${item.name}</a>：
												<span><a href="/users/ef1ab35d8e27" class="maleskine-author" target="_blank" data-user-slug="ef1ab35d8e27">${item.comment}</span>
											</p>
											<div class="sub-tool-group" style="font-size: 12px; color: #969696;">
												<span>${item.created_at}</span>
												<a href="javascript:" onclick="foo(this,${commentObj.id},'${item.name}')" style="margin-left: 10px;  color: #969696;"><i class="fa fa-commenting-o" aria-hidden="true"></i><span>回复</span></a>
											</div>
										</div>
										<!--
										<div class="sub-comment more-comment"  style="font-size: 14px; margin-bottom: 15px;  padding-bottom: 15px; color: #969696; border: none;">
											<a style="color: #969696" href="javascript:" class="add-comment-btn"><i class="fa fa-pencil" aria-hidden="true"></i><span>添加新评论</span></a>
										</div> -->
										<#if item_index == (i-1)>
										 <!-- 回复评论-->
                                         <div class="reply_area"   >
                                         </div>	
										</#if>
                                       
									</div>
								 </#list> 
								 <#else>
								 <!-- 子评论: 初始时没有 subcommentMap-->
									<div class="sub-comment-list  hidden "  style="margin-top: 20px;  padding: 5px 0 5px 20px; border-left: 2px solid #d9d9d9;"> 
                                        <!-- 回复评论-->
                                         <div class="reply_area"   >
                                        </div>	
									</div>
								 </#if>
									 				
								</div>    
							</#list>	              			 
                   		</div>
                   		
                   </div>
				</div>
       		</div>
            <!-- 提交评论区-->
             <div class="row" >
       		 	<div id="comment_form_div"class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" >
                        <div class="comment_block">
							<form id="coment_form">
                            <p class="comment-form-comment">
                                <label for="comment">
                                    Comment <a href="javascript:" id="cancel_reply" class='hidden' style="font-weight: lighter;padding-left:10px;text-decoration: underline !important;">Cancel</a>
                                </label>
                                <textarea id="comment" name="comment"   cols="45" rows="8" maxlength="65525" aria-required="true" required="required" style="width: 100%;"></textarea>
                            </p>
                            <p class="comment-form-author">
                                <label for="author">
                                    Name
                                    <span class="required">
                                        *
                                    </span>
                                </label>
                                <input id="author" name="name" type="text"  size="30" maxlength="30" aria-required="true" required="required">
                            </p>
                            <p class="comment-form-email">
                                <label for="email">
                                    Email
                                    <span class="required">
                                        *
                                    </span>
                                </label>
                                <input id="email" name="email" type="email"  size="30"  maxlength="80" aria-describedby="email-notes" aria-required="true" required="required">
                            </p>
                            <p class="comment-form-url">
                                <label for="url">
                                    Website
                                </label>
                                <input id="url" name="url" type="url" value="" size="30" maxlength="200">
                            </p>
								<input type="hidden" name="postId" value="${data.id}"> 
							</form>
							 <p  >
								<button id="post_comment">
									Post Comment
								</button>
                            </p>
                        </div>                       
       		 	</div>
             </div><!-- end 提交评论区-->


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
 //@ sourceURL=jsname.js
 	var comment_html;
 	function foo(thiz,id,name){ 
		 
         var $div = $("#comment_form_div");
		 console.log($div.html())   
         $div.empty()
         //找到评论的区
        var $comment =  $(thiz).closest(".comment")
        var $reply_area =  $comment.find(".reply_area")
         $reply_area.append(comment_html)
         if(name){
         	$("#comment").append("@"+name+" ")
         }
         $("#comment").focus()
         $reply_area.parent().removeClass("hidden")   
          $("#cancel_reply").removeClass("hidden")
          //取消
         $("#cancel_reply").one("click",function(){  
         		 $reply_area.empty();
         		 $div.append(comment_html)
         		 $("#cancel_reply").addClass("hidden")
         		 $("#post_comment").click(function(){
					 bindEvenToPostComment("-1")
						
					})
         	})
         	//提交
		 $("#post_comment").on("click",function(){bindEvenToPostComment(id)})
 	}

	$(function(){
		comment_html =  $("#comment_form_div").html();
		$("#post_comment").click(function(){
		 bindEvenToPostComment("-1")
			
		})
	})

	function bindEvenToPostComment(id){ 
	$.post("comment/"+id,$("#coment_form").serialize(),function(){
		location.reload();
	})
	}
 </script>
</body>
</html>
