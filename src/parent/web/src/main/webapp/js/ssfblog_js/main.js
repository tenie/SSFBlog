var ssfblog  = new Object()


ssfblog.pageInfo={
	limit:0,
	offset:0,
	index:0,
	count:0,
	first:true,
	previous:"",
	next:""
}
ssfblog.paging2=function(up){  
	//如果是首页第一加载不走这里, 也就是不做翻页操作
	 if(! ssfblog.pageInfo.first){
		 if(up){ 
			 ssfblog.pageInfo.offset = ssfblog.pageInfo.offset - ssfblog.pageInfo.limit
			 ssfblog.pageInfo.index = --ssfblog.pageInfo.index
			 ssfblog.pageInfo.end=false;
		 }else{ 
			 ssfblog.pageInfo.offset = ssfblog.pageInfo.offset + ssfblog.pageInfo.limit
			 ssfblog.pageInfo.index = ++ssfblog.pageInfo.index
		 } 
	 }else{
		 ssfblog.pageInfo.first = false;  
	 }
	 
	//给下一页加样式   
	 if((ssfblog.pageInfo.offset + ssfblog.pageInfo.lm) >= ssfblog.pageInfo.count){  //偏移量加limit 大于总行数的话, 给下一页添加禁用样式 
		 ssfblog.addDisabled(ssfblog.pageInfo.next) ; 
		 alert(1)
		 return
	}else{ 
		 ssfblog.rmDisabled(ssfblog.pageInfo.next)
	}
	 
	 //给上一页加样式 
	 if(ssfblog.pageInfo.offset<=0){ //如果offset<=0 添加禁用样式 
		 ssfblog.addDisabled(ssfblog.pageInfo.previous)
	}else{ 
		 ssfblog.rmDisabled(ssfblog.pageInfo.previous)
	} 
	
}

//sessionStroage
ssfblog.isSignIn=function(){
	
	return false;
}

//获取sessionStroage没有返回cookie对象
//ssfblog.getStroage=function(){
//	if(window.localStorage){
//		return window.localStorage
//	}else{
//		return 
//	}
//}

//浏览器存储
//增
ssfblog.stroageAdd=function(key,val){
	if(window.sessionStorage){
		window.sessionStorage.setItem(key,val)
	}else{
		$.cookie(key, val, { expires: 1 }); 
	}
}
//查
ssfblog.stroageGet=function(key){
	if(window.sessionStorage){
		return window.sessionStorage.getItem(key)
	}else{
		return $.cookie(key); 
	}
}
//改
ssfblog.stroageUpdate=ssfblog.stroageAdd;
//删
ssfblog.stroageDelete=function(key){
	if(window.sessionStorage){
		return window.sessionStorage.removeItem(key)
	}else{
		return $.removeCookie(key); 
	}
}







//加载导航
ssfblog.navPage=function(){ 
	$.get("/nav.html",function(htmldata){  //获取展示的html样式 
		$("nav").append(htmldata)
		ssfblog.sigIn()
		ssfblog.publishPage()
		//如果登入过就隐藏登入按钮
		if(window.sessionStorage){ 
			 var si = window.sessionStorage.getItem("signIn") 
			 if("success" == si){
				 ssfblog.navSignChange() 
			 }
		}
	})
}

//登入按钮隐藏,登出按钮现实
ssfblog.navSignChange=function(){
	var sin  = $("#signInBtn")
	var sout = $("#signOutBtn")
	if(sin.hasClass("hidden")){
		sin.removeClass("hidden")
		sout.addClass("hidden")
	}else{
		sout.removeClass("hidden")
		sin.addClass("hidden")
	}
	 
}

//加载footer
ssfblog.footerPage=function(){
	$.get("/footer.html",function(htmldata){  //获取展示的html样式 
		$("footer").append(htmldata) 
	})
}
//加载导航,和脚
ssfblog.initPage=function(){
	
	ssfblog.navPage()
	ssfblog.footerPage()
	 
}
//加载首页标题


//联系页面 ,原有页面有这个功能
//ssfblog.contactPagePost=function(){
//	//var data = $("form").serialize()
//	$.post("/pageTitle/postContactData",$("form").serialize(),function(data){
//		if(data.error !="error "){ 
//			ssfblog.toastr("success",data.msg)
//		}else{
//			ssfblog.toastr("warning",data.msg)
//		}
//	})
//}

//给对应位置的链接赋值id,存储在cookie
function cl(thiz){
	var rel = $(thiz).attr("rel")
	$.cookie('postContent', rel, { expires: 1 }); 
}

//bolg内容加载
ssfblog.initPost=function(){ 
	var postContent = $.cookie("postContent")
	//$.removeCookie("postContent");
	if(!postContent){return}
	$.get("/pageTitle/postContent/"+postContent,function(data){ 
		$(".post-heading").append('<span class="meta" id="time">Posted by <a href="#">Tenie Bolg</a> on '+$(data)[0].time+' </span>')
		$("#post-title").text($(data)[0].post_title)
		$("#post-subtitle").text($(data)[0].post_subtitle)
		$("#postContent").html($(data)[0].post_content)
	}) 
}

//首页
//主页标题信息渲染
ssfblog.initpage=function(data){ //date是查询到的博客标题信息集

	var datalen = $(data).length
	$.get("/index_contentTitle.html",function(htmldata){  //获取展示的html样式
		//console.log(date)  
		var f = ""
		var pageTitleContainer = $("#pageTitleContainer")
		for(i=0;i<datalen;i++){ //几偏文件就加载几个标题
			 f+=htmldata+"\n"
		}
		//console.log(f)
		pageTitleContainer.append(f) 
		var post_title = pageTitleContainer.find(".post-title")
		var post_subtitle = pageTitleContainer.find(".post-subtitle")
		var time = pageTitleContainer.find(".time")
		var src_page = pageTitleContainer.find(".src_page")
		//解析数据,给页面赋值
		for(i=0;i<datalen;i++){ 
			post_title.eq(i).text($(data)[i].post_title)
			post_subtitle.eq(i).text($(data)[i].post_subtitle)
			time.eq(i).text($(data)[i].time)
		 	src_page.eq(i).attr("rel",$(data)[i].id)
		}
		
	})
}
//首页
//分页记录, 参数:limit, offset, 上一页下一页按钮, 总行数(总行数在初始化的时候不设置, 但获取到总行数后,再设置,这样下一页才会知道到哪一页停止)
ssfblog.paging=function(lm,previous,next){
	var page = {
			limit:lm,
			offset:0,
			index:0,
			count:(lm*2),
			end:false 
	}
	$.extend(ssfblog.pageInfo,page)
	var first = true;
	return function(opt){  
		var option ={
				previous:false, 
				setCount:{tf:false,count:0}
		}
		//只是设置总行数, 就返回方法了
		$.extend(option,opt) 
		if(option.setCount.tf){ 
			page.count=option.setCount.count 
			if(page.count <= lm){
				 ssfblog.addDisabled(next) ; 	
			}
			$.extend(ssfblog.pageInfo,page)  //全局变量缓存
			return page
		}
		  
		//如果是首页第一加载不走这里, 也就是不做翻页操作
		 if(! first){
			 if(option.previous){ 
				 page.offset = page.offset - page.limit
				 page.index = --page.index
				 page.end=false;
			 }else{ 
				 page.offset = page.offset + page.limit
				 page.index = ++page.index
			 } 
		 }else{
			 first = false;	 
		 }
		 
		//给下一页加样式   
		 if((page.offset+lm) >= page.count){  //偏移量加limit 大于总行数的话, 给下一页添加禁用样式
			 if(next&& !option.previous){
				 ssfblog.addDisabled(next) ; 
			 } 
		}else{
			 if(next)
			 ssfblog.rmDisabled(next)
		}
		 
		 //给上一页加样式 
		 if(page.offset<=0){ //如果offset<=0 添加禁用样式
			 if(previous)
			 ssfblog.addDisabled(previous)
		}else{
			 if(previous)
			 ssfblog.rmDisabled(previous)
		} 
		$.extend(ssfblog.pageInfo,page)  //全局变量缓存
		return page;
	}
}
//分页初始化

//禁用样式<添加style属性值的方式>
ssfblog.addDisabled2=function(obj){
	var theObj = $(obj)
	theObj.attr("disabled","Disabled");
	var style = theObj.attr("style");
	if(style){ 
		if(! style.indexOf("not-allowed")>=0){
			theObj.attr("style",style+";cursor: not-allowed;")
		}  
	}else{
		theObj.attr("style","cursor: not-allowed;")
	} 
}
//添加禁用(测试通过)
ssfblog.addDisabled=function(obj){
	var theObj = $(obj)
	theObj.attr("disabled","Disabled");
	var styleObj = $("style");
	var tf = false;
	$.each(styleObj,function(i,n){
		var text = $(n).text();
		 
		if(text.indexOf(".cursor_not_allowed")>=0){
			tf = true;
		}
	})
	if(!tf){
		$("head").append("<style>.cursor_not_allowed{cursor: not-allowed; color:#ccc;}</style>")
		
	}
	 
	if(! theObj.hasClass("cursor_not_allowed")){  
			theObj.addClass("cursor_not_allowed");  
	} 
}
 
//移除禁用样式
ssfblog.rmDisabled=function(obj){
	var theObj = $(obj)
	theObj.removeAttr("disabled");
	if(theObj.hasClass("cursor_not_allowed")){
		theObj.removeClass("cursor_not_allowed")
	}
}
 



//登入登出按钮事件
ssfblog.sigIn=function(){
	//登出按钮事件
	$("#signOutBtn").click(function(){
		ssfblog.navSignChange() 
		//后台删除session,前端也要删除sessionStorage
		$.post("/sigOut",function(data){
			if(data.msg){
				ssfblog.toastr("info",data.msg)
				if(window.sessionStorage){
					window.sessionStorage.setItem("signIn","out");
				}
			}
			
		})
	})
	
	//登入按钮事件
	$("#signInBtn").click(function(){
		 if($("#exampleModal").length>0){
			 $("#exampleModal").modal('show')
		 }else{
			 $.get("/signInPage.html",function(htmldata){  
					$("body").append(htmldata); 
					$("#exampleModal").modal('show');
					 ssfblog.key13("#submitSignIndata");
					$("#submitSignIndata").click(function(){
						 var tf = $("#signInForm").valid()
						 if(!tf)return
						$.post("/sigIn",$("#signInForm").serialize(),function(data){  
							console.log(data)
							if(data.error == "no"){ //登入成功 : 关闭界面, 提示成功,在浏览器session中保存登入过, 隐藏登入按钮,现实登出按钮
								$("#siginPageClose").click()
								//ssfblog.stroageAdd("signIn","success");
								if(window.sessionStorage){
									window.sessionStorage.setItem("signIn","success");
								}
								ssfblog.toastr("success",data.msg)
								ssfblog.navSignChange() 
							}else{
								//alert("fail")
								ssfblog.toastr("warning",'帐号或密码错误!')
								ssfblog.stroageAdd("signIn","fail");
								
							}
						})
					})
				}) 
		 }
		
	})
} 


//发布博文
ssfblog.publishPage=function(){
	$("#publishBtn").click(function(){
		//获取页面
		$.get("/publishPage.html",function(htmldata){  
			
			//如果页面已经加载模态框就直接现实
			if($("#publishModal").length>0){
				 $("#publishModal").modal('show')
				 return
			}
			//else
			$("body").append(htmldata); 
			//模态框配置
			$("#publishModal").modal({backdrop:false,keyboard:false,show:true}); 
			var publishPageEditor = new Simditor({
				  textarea: $('#editorPublish')
				  //optional options
				});
			$("#submitPublishdata").click(function(){
				var val = publishPageEditor.getValue(); 
				ssfblog.saveBlogData(val)
			})
		}) 
	})	
}


//提交博文保存
ssfblog.saveBlogData=function(data){
	var tagVal=""
	var tag = $("#tag > span")
	$.each( tag, function(i, n){
		tagVal+= $(n).text()+"&&&"
	});
	
	//console.log($("#PublishdataForm").serialize());
	var formval  = $("#PublishdataForm").serialize()
	formval=formval+"&data="+data
	console.log(formval);
	$.post("/submitPublishdata",formval
//								{title:$("#publishTitle").val(),
//								 tag:$("#tag").text(),
//								 tag:tagVal,
//								 data:data  }
							,function(returndata){ 
						
		if(returndata=="ok"){
		$("#publishdataPageClose").click()
		}else if(returndata=='nologin') {
			ssfblog.toastr("warning",'未登入不可发布!,请先登入~')	
		 	ssfblog.openSignInWindow("#publishdataPageClose","#signInBtn");	
		 	
		}
	})
}
//关闭弹出框,打开登入框
ssfblog.openSignInWindow=function(close,open){
	if(close){
		$(close).click();
	}
	if(open){
		$(open).click()
	}
}




/*
 * 公共方法
*/
//提示组件配置; 
ssfblog.toastr=function(method,msg){
	if(typeof(toastr) != 'undefined'){
		toastr.options = {
				  "closeButton": true,
				  "debug": false,
				  "positionClass": "toast-top-center",
				  "onclick": null,
				  "showDuration": "1000",
				  "hideDuration": "1000",
				  "timeOut": "2000",
				  "extendedTimeOut": "1000",
				  "showEasing": "swing",
				  "hideEasing": "linear",
				  "showMethod": "fadeIn",
				  "hideMethod": "fadeOut"
		};
		toastr[method](msg) //使用[]传字符串的方式来调用方法
	} 
	
}

//回车触发点击事件
ssfblog.key13=function(target){ 
	$(document).keyup(function(e){   
		if(e.keyCode == 13){ 
			$(target).click()
		} 
	})
	
} 



//ajax 调用的时候添加loading 效果
ssfblog.ajax=function(method,url, data,successfunc){
	if(method == "post"){
		$.post(url,data,function(data){
			successfunc(data)
		})
	}
}


//回到顶部按钮
ssfblog.backToTop = function() {
	if ($("#footer").length > 0) {
		$("body").append('<div id="backtotop" class="showme"><div class="bttbg"></div></div>');
		a()
		}
	function a() {
		var b = jQuery("#footer").position().top - jQuery(window).height() - 200;
		jQuery(function() {
			jQuery(window).scroll(function() {
				if (jQuery(this).scrollTop() > 100) {
					jQuery("#backtotop").addClass("showme")
				} else {
					jQuery("#backtotop").removeClass("showme")
				}
			});
			jQuery("#backtotop").click(function() {
				jQuery("body,html").animate({
					scrollTop: 0
				}, 400);
				return false
			})
		});
		if (jQuery(window).scrollTop() == 0) {
			jQuery("#backtotop").removeClass("showme")
		} else {
			jQuery("#backtotop").addClass("showme")
		}
	}
}


