<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title></title>
	<link rel="shortcut icon" href="../lib/assets/img/codeMonkey.ico"> 
    <link href="../lib/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
	<link href="/lib/css/ssfblog_css/myblog-post.css" rel="stylesheet">

	<link href="../lib/css/ssfblog_css/main.css" rel="stylesheet">
    <link href="../lib/css/clean-blog.min.css" rel="stylesheet"> 
    <link href="../lib/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="../lib/noty-master/lib/noty.css" />  
	<link rel="stylesheet" type="text/css" href="../lib/bootstrap_switch/css/bootstrap3/bootstrap-switch.css" /> 
	<link rel="stylesheet" type="text/css" href="../lib/viewer/viewer.min.css " /> 
	<link href="../lib/vendor/font-awesome/css/font-awesome-animation .css" rel="stylesheet" type="text/css">

</head>

<body class="fadeIn postpage ">
   <div id="loader-wrapper" class="in" >
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
    <nav class="navbar navbar-default navbar-custom navbar-fixed-top" > 
    </nav>

    <header  style="margin-top: 65px">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="post-heading">
                    	<p>
                    		<#if data.top == 0>
                   			  <span class='blogtop_flag top_tag'><i class='fa fa-flag' aria-hidden='true' style="float: left;margin-top: -5px;margin-right: 5px;"></i></span>
                     		<#else>
                     		  <span class='unblogtop_flag top_tag'><i class='fa fa-flag-o' aria-hidden='true' style="float: left;margin-top: -5px;margin-right: 5px;"></i></span>
                    		</#if>
                    		<h1  id="post-title" style="display: inline;">${data.postTitle} </h1>
                    		<input type="hidden" id='postId' value='${data.id}'>
                    	
                    		<#if data.showContent == 0>
						    <span class="badge"  id="postBadge" style="margin-bottom: 25px;">private</span>
							<#else>  
							 <span class="badge hidden" id="postBadge" style="margin-bottom: 25px;">private</span>
							</#if> 
                    	</p>
                        
                       
						<p style="    margin-top: 10px;">
						<i class="fa fa-tags" aria-hidden="true"></i>
						<#list tags as tag>
							<a href="#"> 
							 <span style ='color: #888b94;    padding: 1px 5px;    background: #f5f5f5;    border-bottom: none;    border-top-width: 10px;    margin-top: 10px;' onclick='ssfblog.tagSearch2("${tag.tag}")'	>${tag.tag}</span>  &nbsp;
						   </a>
						</#list>
						</p>	
                        
                    </div>
                    <div  style=" margin: 20px 0 40px;">
			                   
						<div class="info">
							<a class=" head-image-parent" href="javascript:">
				           		 <img class="image head-image"  src="${photo}" alt="">
							</a>
				            <span class="author" >作者</span>
				            <span class="name" ><a href="/">${myName}</a></span>
				            <div class="master-mate" >
				              	<i class="fa fa-calendar" aria-hidden="true"></i>
				                <span class="publish-time" id="time" style="  padding-right: 5px;">${data.time?string('yyyy-MM-dd HH:mm:ss')}  </span>
					            <span class="wordage"  style="  padding-right: 5px;">字数: ${data.textLength} </span>  
					            <span class="views-count"  style="  padding-right: 5px;">阅读: ${data.readQuantity}</span>
					            <span class="comments-count"  style="  padding-right: 5px;">评论: ${commentLength}</span>
					            <span class="likes-count" >喜欢: <span id="postLike">${data.postLike}</span></span>
					            <span >
										<div class="btn-group ssfblog-editbtn hidden-xs" > 
										  <#if isLog>
										  <button type="button" onclick="ssfblog.editPage2(${data.id})" class="btn ssfblog-editbtn-subleft "  >
										     <i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;编 辑 &nbsp; &nbsp; 
										  </button>
										  <#else> 
										  <button type="button"   class="btn  disabled ssfblog-editbtn-subleft "  >
										    <i class="fa fa-pencil-square-o" aria-hidden="true"></i> &nbsp;编 辑 &nbsp; &nbsp;
										  </button>
										  </#if>  
										  <button type="button"   class="btn  dropdown-toggle ssfblog-editbtn-subright" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										    <span class="caret"></span>
										    <span class="sr-only">Toggle Dropdown</span>
										  </button>
										  <ul class="dropdown-menu">
										     
										      <#if isLog && data.showContent == 1>
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
										     <#if isLog  && data.showContent == 0>
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
												 	<input type="hidden" id='islogin' value='true'>
												   <a href="javascript:" onclick="ssfblog.deleteBlog(${data.id})"  >
													    <i class="fa fa-trash-o" aria-hidden="true"></i>&nbsp;
													    删除
												    </a>
											    </li>
											<#else>  
												 <li class="disabled">
												 	<input type="hidden" id='islogin' value='false'>
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
    <article >
        <div class="container"> 
            <div class="row">
                <div id="postContent"class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1"  >
                  ${data.postContent} 
				</div>
            </div>
            
            <div class="row">
       		 	<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" >
                   <div style="border-bottom: 1px solid #eee;padding-bottom: 5px;"></div>
				</div>
       		</div>
       		 <div class="row">
       		 	<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" style="margin-top: 20px;margin-bottom: 30px;" >
						<div class="meta-bottom">
						    <div class="like">
						    	<div class="btn like-group" style=" padding: 13px 0 15px;  font-size: 0;  border: 1px solid #ea6f5a;  border-radius: 40px;">
						    		<div class="btn-like" style="font-size: 19px;  display: inline-block;">
						    		<a id="likebtn"style="color: #ea6f5a; padding: 18px 15px 18px 30px;"><i style=" margin-right: 5px;font-size: 21px;"  class="fa fa-heart-o"  id="faHeart"></i>喜欢</a>
						    	</div> 
						   	<div class="modal-wrap" style="font-size: 18px;  border-left: 1px solid rgba(236,97,73,.4);  display: inline-block;">
						   		<a style="color: #ea6f5a; padding: 18px 30px 18px 17px;" id="likecount">${data.postLike}</a></div>
						   	</div> <!----></div> 
						</div>                    
				</div>
       		</div> 
       		
            <div class="row">
       		 	<div id="postComent"class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" >
                   <div class="row">
                   		<div class="col-xs-12">
                   			<h5>
                   			 <span id="comentCount">${commentLength}</span> 评论
                   			</h5>
                   		</div>
                   		<div class="col-xs-12">
						<#list comments as commentObj> 
								<div    class="comment" style="padding: 20px 0 30px;border-bottom: 1px solid #f0f0f0;">
									<div class="masterComment">
										<div  style=" margin-bottom: 15px;">
										 <#if  commentObj.myselft=1 >
											<a href="javascript:" style= "margin-right: 5px; width: 38px; height: 38px;  vertical-align: middle;  display: inline-block;" target="_blank" class="avatar">
												<img  class="image" style="width: 100%; height: 100%;  border: 1px solid #ddd;  border-radius: 50%;" src="/lib/assets/img/codeMonkey.ico">
											</a>
											<div class="info" style="display: inline-block; vertical-align: middle;">
												<a href="javascript:" target="_blank" class="name">${commentObj.name}
												<span class="tag" style="    padding: 1px 2px;   font-size: 12px;   color: #ea6f5a;   border: 1px solid #ea6f5a;   border-radius: 3px;">博主</span>
												 </a> 
										<#else>
											<a href="javascript:" style= "margin-right: 5px; width: 38px; height: 38px;  vertical-align: middle;  display: inline-block;" target="_blank" class="avatar">
												<img  class="image" style="width: 100%; height: 100%;  border: 1px solid #ddd;  border-radius: 50%;" src="/lib/img/head/head_96.754632229085px_1196697_easyicon.net.png">
											</a> 
											<div class="info" style="display: inline-block; vertical-align: middle;">
												<a href="javascript:" target="_blank" class="name">${commentObj.name}</a> 
										 </#if>	
											
												<div class="meta" style="font-size: 12px;  color: #969696;">
													<span>${commentObj_index + 1 }楼 · ${commentObj.createdAt?string('yyyy-MM-dd HH:mm:ss')};</span>
												</div>
											</div>
										</div>
										<div class="comment-wrap">
											<div class="commentContent">
												<p style=" font-size: 16px;">
												${commentObj.comment}
												</p>
											</div> 
												<a  href="javascript:" onclick="ssfblog.commentLike(${commentObj.id},this)" style="color:#969696"><i class="fa fa-thumbs-o-up faa-vertical animated-hover"></i></a> <span style="color:#969696"><span class="commentLikelength">${commentObj.commentLike}</span>赞</span>
												<a onclick="ssfblog.reply(this,${commentObj.id},'${commentObj.name}')" href="javascript:"style="color:#969696"> <i class="fa fa-commenting-o faa-vertical animated-hover" aria-hidden="true"></i> <span>回复</span></a> 
										</div>
									</div>
									  
								  <#if  commentObj.subcomment?has_content  >
								  <#assign i =  commentObj.subcomment?size>
								  
								   <#list commentObj.subcomment as item>  
									<div class="sub-comment-list  "  style="margin-top: 20px;  padding: 5px 0 5px 20px; border-left: 2px solid #d9d9d9;">
										<div   class="sub-comment" style="margin-bottom: 15px;  padding-bottom: 15px;  border-bottom: 1px dashed #f0f0f0;">
											<p style="margin: 0 0 5px; font-size: 14px; line-height: 1.5;">
												 <#if  item.myselft == 1 >
													 <a style='    color: #3194d0;' href="javascript:" target="_blank">${item.name}
														<span class="tag" style="    padding: 1px 2px;   font-size: 12px;   color: #ea6f5a;   border: 1px solid #ea6f5a;   border-radius: 3px;">博主</span>
													 </a>：
												 <#else>
												 	<a style='    color: #3194d0;' href="javascript:" target="_blank">${item.name}</a>： 
												 </#if>
												
												<span><a href="javascript:" class="maleskine-author" target="_blank" data-user-slug="ef1ab35d8e27">${item.comment}</span>
											</p>
											<div class="sub-tool-group" style="font-size: 12px; color: #969696;">
												<span>${item.createdAt?string('yyyy-MM-dd HH:mm:ss')}</span>
												<a href="javascript:" onclick="ssfblog.reply(this,${commentObj.id},'${item.name}')" style="margin-left: 10px;  color: #969696;"><i class="fa fa-commenting-o" aria-hidden="true"></i><span>回复</span></a>
											</div>
										</div>
										<#if item_index == (i-1)>
                                         <div class="reply_area"   >
                                         </div>	
										</#if>
                                       
									</div>
								 </#list> 
								 <#else>
									<div class="sub-comment-list  hidden "  style="margin-top: 20px;  padding: 5px 0 5px 20px; border-left: 2px solid #d9d9d9;"> 
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
             <div class="row" >
       		 	<div id="comment_form_div"class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" >
                        <div class="comment_block">
							<form id="coment_form">
	                            <p class="comment-form-comment">
	                                <label for="comment">
	                                                                                                               发表评论
	                                     <a href="javascript:" id="cancel_reply" class='hidden' style="font-weight: lighter;padding-left:10px;text-decoration: underline !important;">取消</a>
	                                </label>
	                                <textarea id="comment" name="comment"   cols="45" rows="8" maxlength="65525" aria-required="true" required="required" style="width: 100%;"></textarea>
	                            </p>
	                            <#if ! isLog >
	                            <div class="commenter_info">
	                            	<p class="comment-form-author">
		                                <label for="author" style="width: 55px;">
		                                   	 名称
		                                    <span class="required">
		                                        *
		                                    </span>
		                                </label>
		                                <input id="author" name="name" type="text"  size="30" maxlength="30"  aria-required="true" required="required">
		                            </p>
		                            <p class="comment-form-email">
		                                <label for="email" style="width: 55px;">
		                             	           邮件
		                                    <span class="required">
		                                        *
		                                    </span>
		                                </label>
		                                <input id="email" name="email" type="email"  size="30"  maxlength="80" aria-describedby="email-notes" aria-required="true" required="required">
		                            </p>
		                            <p class="comment-form-url">
		                                <label for="url" style="width: 55px;">
		                               	      网址
		                                </label>
		                                <input id="url" name="url" type="url" value="" size="30" maxlength="200">
		                            </p>
	                            </div>
	                            </#if>
								<input type="hidden" name="postId" value="${data.id}"> 
							</form>
							 <p>
								<button class="btn btn-info btn-4" id="post_comment">
									提交
								</button>
                            </p>
                        </div>                       
       		 	</div>
             </div>
        </div>
    </article>

    <hr>
    <footer id="footer"> 
    </footer>
  
   
<script src="/lib/js/ssfblog_js/app.js"></script> 
<script src="/lib/js/ssfblog_js/main.js"></script> 
</body> 
</html>
