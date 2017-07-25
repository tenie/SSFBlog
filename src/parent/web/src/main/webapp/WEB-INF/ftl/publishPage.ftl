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
	<link rel="shortcut icon" href="/lib/assets/img/codeMonkey.ico"> 
    <!-- Bootstrap Core CSS -->
    <link href="/lib/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
	 <link href="/lib/css/ssfblog_css/main.css" rel="stylesheet">
    <!-- Theme CSS -->
    <link href="/lib/css/clean-blog.min.css" rel="stylesheet"> 
    <!-- Custom Fonts --> 
    <link href="/lib/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/lib/css/font.css" rel="stylesheet" type="text/css"> 
	<link rel="stylesheet" type="text/css" href="/lib/simditor/styles/simditor.css" /> 
	<!--  提示alert样式 -->
	<link rel="stylesheet" type="text/css" href="/lib/js/bootstrap-toastr/toastr.min.css" />  
	<link rel="stylesheet" type="text/css" href="/lib/js/jQuery-FlexSlider/flexslider.css" />  
	<link rel="stylesheet" type="text/css" href="/lib/noty-master/lib/noty.css" />  
	<link rel="stylesheet" type="text/css" href="/lib/bootstrap_switch/css/bootstrap3/bootstrap-switch.css" /> 	 
</head>

<body class="editPublishPage">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-custom navbar-fixed-top  ">
         
    </nav > 
    <!-- Main Content -->
    <div class="container"  style="margin-top: 65px">
        <div class="row">
            <div class="col-xs-12 ">
                <div id="publishModal" >
	 
			          <div   style=" margin-top: 20px;   ">
			            <form id="editPublishdataForm"> 
			               <input type="hidden" id="edittextLength" name="textLength">
			               <input type="hidden" id="editcontent" name="content">
			                 <input type="hidden" id="id" name="id" value="${blogContent.id}">
			               <#if blogContent.show_content == 1>
			                    <input type="hidden" id="showContent"  name="showContent" value="true"><!-- 公开私有 -->
			               <#else>
			               	    <input type="hidden" id="showContent"  name="showContent" value="false"><!-- 公开私有 -->
			               </#if>
			          		
			          	   <#if blogContent.top == 0>
			          	   		<input type="hidden" id="istop"  name="isTop" value="true"><!-- 是否置顶 -->
			          	   <#else>
			          	         <input type="hidden" id="istop"  name="isTop" value="false"><!-- 是否置顶 -->
			          	   </#if>
			              
						   <input id="publishTitle" class="contentTitle" value="${blogContent.post_title}"   autofocus name="title" type="text"   placeholder="标题:" required data-validation-required-message="必填"  aria-describedby="sizing-addon1"> 
			               <!-- <textarea id="editorPublish" placeholder="Balabala" ></textarea> -->
			               <div   id="Text"  >
			               	${blogContent.post_content}
			               </div>   
			  	         
			  	          <!-- 标签组件-->
			  			  <div>
				  			   <div  id="edittag" class="editPageTag  " style=" display: inline; background-color: #f8f8f8;">
				                <span class='editTag'  >标签: </span>
				               	<#list tags as tag>
				               			<span class=""> 
				               				${tag.tag}
				               				<button type="button" class="ssfbdel" onclick="ssfblog.rmtag(this)">
				               		            <span aria-hidden="true" style=" float: none;">×</span>  
				               		       </button>
				               		       <input type="hidden" name="tag" value="${tag.tag}" >
				               		   </span>
				               </#list>
				               </div> 
				               <input id="edittagInput" type="text" style=" border: 0px;width: 40%; display: inline;" placeholder="添加相关标签，用逗号或回车分隔 " aria-describedby="sizing-addon1">  
				           		
			  			  
			  			  </div>
			              
			           		<div class="switch" style="display: inline;" >
							    是否公开: 
							    
							     <#if blogContent.show_content == 1>
							 		    <input type="checkbox"  checked name="publishPagecheckbox"/>
							     <#else>
							        	<input type="checkbox"    name="publishPagecheckbox"/>
							     </#if>
							    
							    
							</div>
							<div class="switch"  style="display: inline;">
								
							    是否置顶: 
							     <#if blogContent.top == 0>
									 <input type="checkbox"  checked  name="top"/>
								<#else>
									 <input type="checkbox"   name="top"/>
								</#if>
							    
							   
							</div>
			            </form>
			         </div>
			         <div style="padding: 10px;  float: right;">
			          	 
			            <button type="button" class="btn btn-default" data-dismiss="modal" id="publishdataPageClose">Close</button>
			            <button type="button" class="btn btn-primary" id="submitPublishdataBtn">Publish</button>  
			           
			        </div> 
			</div>
          
       

            </div>
        </div>
    </div>

    <hr>

    <!-- Footer -->
    <footer>
         
    </footer>

<!-- jQuery -->
<script src="/lib/vendor/jquery/jquery.min.js"></script>   
<script src="/lib/js/jquery.cookie.js" type="text/javascript"></script> 
<!--  my js-->
<script src="/lib/js/ssfblog_js/main.js"></script> 
<script src="/lib/vendor/bootstrap/js/bootstrap.min.js"></script> 
<!-- Contact Form JavaScript -->
<script src="/lib/js/jqBootstrapValidation.js"></script>
<script src="/lib/js/contact_me.js"></script> 
<!-- Theme JavaScript -->
<script src="/lib/js/clean-blog.min.js"></script> 
<script src="/lib/js/bootstrap-toastr/toastr.min.js"></script>
     <!-- 富文本编辑框 -->
<script type="text/javascript" src="/lib/simditor/scripts/module.js"></script>
<script type="text/javascript" src="/lib/simditor/scripts/hotkeys.js"></script> 
<script type="text/javascript" src="/lib/simditor/scripts/simditor.js"></script> 
<script type="text/javascript" src="/lib/js/jQuery-FlexSlider/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="/lib/noty-master/lib/noty.js"></script> 
<script type="text/javascript" src="/lib/noty-master/lib/mo.min.js"></script> 
 
     
<script src="/lib/js/jquery-validation/js/jquery.validate.min.js"></script>  
<script src="/lib/js/jquery-validation/js/additional-methods.js"></script> 
<script src="/lib/bootstrap_switch/js/bootstrap-switch.js "></script>  
<script src="/lib/ckeditor/ckeditor.js"></script>  
</body>    
</html>
