var net_tenie  = new Object()


net_tenie.pageInfo={
	limit:0,
	offset:0,
	index:0,
	count:0,
	first:true,
	previous:"",
	next:""
}
net_tenie.paging2=function(up){
	
	//如果是首页第一加载不走这里, 也就是不做翻页操作
	 if(! net_tenie.pageInfo.first){
		 if(up){ 
			 net_tenie.pageInfo.offset = net_tenie.pageInfo.offset - net_tenie.pageInfo.limit
			 net_tenie.pageInfo.index = --net_tenie.pageInfo.index
			 net_tenie.pageInfo.end=false;
		 }else{ 
			 net_tenie.pageInfo.offset = net_tenie.pageInfo.offset + net_tenie.pageInfo.limit
			 net_tenie.pageInfo.index = ++net_tenie.pageInfo.index
		 } 
	 }else{
		 net_tenie.pageInfo.first = false;  
	 }
	 
	//给下一页加样式   
	 if((net_tenie.pageInfo.offset + net_tenie.pageInfo.lm) >= net_tenie.pageInfo.count){  //偏移量加limit 大于总行数的话, 给下一页添加禁用样式 
		 net_tenie.addDisabled(net_tenie.pageInfo.next) ; 
		 alert(1)
		 return
	}else{ 
		 net_tenie.rmDisabled(net_tenie.pageInfo.next)
	}
	 
	 //给上一页加样式 
	 if(net_tenie.pageInfo.offset<=0){ //如果offset<=0 添加禁用样式 
		 net_tenie.addDisabled(net_tenie.pageInfo.previous)
	}else{ 
		 net_tenie.rmDisabled(net_tenie.pageInfo.previous)
	} 
	
}

//sessionStroage
net_tenie.isSignIn=function(){
	
	return false;
}

//获取sessionStroage没有返回cookie对象
//net_tenie.getStroage=function(){
//	if(window.localStorage){
//		return window.localStorage
//	}else{
//		return 
//	}
//}

//浏览器存储
//增
net_tenie.stroageAdd=function(key,val){
	if(window.sessionStorage){
		window.sessionStorage.setItem(key,val)
	}else{
		$.cookie(key, val, { expires: 1 }); 
	}
}
//查
net_tenie.stroageGet=function(key){
	if(window.sessionStorage){
		return window.sessionStorage.getItem(key)
	}else{
		return $.cookie(key); 
	}
}
//改
net_tenie.stroageUpdate=net_tenie.stroageAdd;
//删
net_tenie.stroageDelete=function(key){
	if(window.sessionStorage){
		return window.sessionStorage.removeItem(key)
	}else{
		return $.removeCookie(key); 
	}
}







//加载导航
net_tenie.navPage=function(){ 
	$.get("/nav.html",function(htmldata){  //获取展示的html样式 
		$("nav").append(htmldata)
		net_tenie.sigIn()
		net_tenie.publishPage()
	})
}

//加载footer
net_tenie.footerPage=function(){
	$.get("/footer.html",function(htmldata){  //获取展示的html样式 
		$("footer").append(htmldata) 
	})
}
//加载导航,和脚
net_tenie.initPage=function(){
	
	net_tenie.navPage()
	net_tenie.footerPage()
	 
}
//加载首页标题


//联系页面 ,原有页面有这个功能
//net_tenie.contactPagePost=function(){
//	//var data = $("form").serialize()
//	$.post("/pageTitle/postContactData",$("form").serialize(),function(data){
//		if(data.error !="error "){ 
//			net_tenie.toastr("success",data.msg)
//		}else{
//			net_tenie.toastr("warning",data.msg)
//		}
//	})
//}

//给对应位置的链接赋值id,存储在cookie
function cl(thiz){
	var rel = $(thiz).attr("rel")
	$.cookie('postContent', rel, { expires: 1 }); 
}

//bolg内容加载
net_tenie.initPost=function(){
	 
	var postContent = $.cookie("postContent")
	$.removeCookie("postContent");
	if(!postContent){return}
	$.get("/pageTitle/postContent/"+postContent,function(data){ 
		$(".post-heading").append('<span class="meta" id="time">Posted by <a href="#">Tenie Bolg</a> on '+$(data)[0].time+' </span>')
		$("#post-title").text($(data)[0].post_title)
		$("#post-subtitle").text($(data)[0].post_subtitle)
		$("#postContent").html($(data)[0].post_content)
	}) 
}

//首页
//标题信息渲染到页面上
net_tenie.initpage=function(data){ //date是查询到的博客标题信息集

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
net_tenie.paging=function(lm,previous,next){
	var page = {
			limit:lm,
			offset:0,
			index:0,
			count:(lm*2),
			end:false 
	}
	$.extend(net_tenie.pageInfo,page)
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
				 net_tenie.addDisabled(next) ; 	
			}
			$.extend(net_tenie.pageInfo,page)  //全局变量缓存
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
				 net_tenie.addDisabled(next) ; 
			 } 
		}else{
			 if(next)
			 net_tenie.rmDisabled(next)
		}
		 
		 //给上一页加样式 
		 if(page.offset<=0){ //如果offset<=0 添加禁用样式
			 if(previous)
			 net_tenie.addDisabled(previous)
		}else{
			 if(previous)
			 net_tenie.rmDisabled(previous)
		} 
		$.extend(net_tenie.pageInfo,page)  //全局变量缓存
		return page;
	}
}
//分页初始化

//禁用样式<添加style属性值的方式>
net_tenie.addDisabled2=function(obj){
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
net_tenie.addDisabled=function(obj){
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
net_tenie.rmDisabled=function(obj){
	var theObj = $(obj)
	theObj.removeAttr("disabled");
	if(theObj.hasClass("cursor_not_allowed")){
		theObj.removeClass("cursor_not_allowed")
	}
}
 



//登入按钮事件
net_tenie.sigIn=function(){
	$("#signInBtn").click(function(){
		 if($("#exampleModal").length>0){
			 $("#exampleModal").modal('show')
		 }else{
			 $.get("/signInPage.html",function(htmldata){  
					$("body").append(htmldata); 
					$("#exampleModal").modal('show');
					 net_tenie.key13("#submitSignIndata");
					$("#submitSignIndata").click(function(){
						 var tf = $("#signInForm").valid()
						 if(!tf)return
						$.post("/sigIn",$("#signInForm").serialize(),function(data){  
							console.log(data)
							if(data.error == "no"){ 
								$("#siginPageClose").click()
								net_tenie.stroageAdd("signIn","success");
								net_tenie.toastr("success",data.msg)
							}else{
								alert("fail")
								net_tenie.toastr("warning",'帐号或密码错误!')
								net_tenie.stroageAdd("signIn","fail");
								
							}
						})
					})
				}) 
		 }
		
	})
} 


//发布博文
net_tenie.publishPage=function(){
	$("#publishBtn").click(function(){
		//获取页面
		$.get("/publishPage.html",function(htmldata){  
			
			if($("#publishModal").length>0){
				 $("#publishModal").modal('show')
				 return
			}
			$("body").append(htmldata); 
			$("#publishModal").modal({backdrop:false,keyboard:false,show:true});
			var publishPageEditor = new Simditor({
				  textarea: $('#editorPublish')
				  //optional options
				});
			$("#submitPublishdata").click(function(){
				var val = publishPageEditor.getValue(); 
				net_tenie.saveBlogData(val)
			})
		}) 
	})	
}


//提交博文保存
net_tenie.saveBlogData=function(data){
	$.post("/submitPublishdata",{title:$("#publishTitle").val(),
								 tag:$("#tag").text(),
								 data:data  },function(data){ 
								
		if(data=="ok"){
		$("#publishdataPageClose").click()
		}else{
		alert("fail")	
		}
	})
}




/*
 * 公共方法
*/
//提示组件配置; 
net_tenie.toastr=function(method,msg){
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
net_tenie.key13=function(target){ 
	$(document).keyup(function(e){   
		if(e.keyCode == 13){ 
			$(target).click()
		} 
	})
	
} 







