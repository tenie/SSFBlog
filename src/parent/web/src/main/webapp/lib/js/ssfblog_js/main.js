var ssfblog  = new Object()
ssfblog.showcount=10;

//sessionStroage
ssfblog.isSignIn=function()
{	
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

// /*

 /*
  *   // */ 
 

//加载导航,和脚
ssfblog.initPage=function(){ 
	ssfblog.navPage()
	ssfblog.footerPage() 
}
 

//给对应位置的链接赋值id,存储在cookie
function cl(thiz){
	var rel = $(thiz).attr("rel")
	$.cookie('postContent', rel, { expires: 1 }); 
}

//搜索相同标题的内容在首页显示
ssfblog.tagSearch=function(tag){
	
	tag = tag.substring(1,tag.length); 
	var url = "/search/tagSearch/"+tag;
	$.get(url,function(data){  
		//console.log(data)
		$("#pageTitleContainer").html("");
		$("#loading").removeClass("hidden");
		$("#previous").hide();
		$("#next").hide();
		var callback = function(){
			$("a[rel='#"+tag+"']").addClass("tagSearch")
		}
		ssfblog.initIndex(data,callback);
		   
		})
}

//首页
//主页标题信息渲染
ssfblog.initIndex=function(datas,callback){ //date是查询到的博客标题信息集 
	//console.log(datas)
	var data =  datas.mapRs.dataList
	var signIn = datas.mapRs.signIn
	var datalen = $(data).length 
	if(!signIn){ 
		window.sessionStorage.setItem("signIn","out");
	}
	$.get("/index_contentTitle.html",function(htmldata){  //获取展示的html样式 
		$("#pageTitleContainer").hide("slow");
		//console.log(date)  
		var f = ""
		var pageTitleContainer = $("#pageTitleContainer")
		for(i=0;i<datalen;i++){ //几偏文件就加载几个标题
			 f+=htmldata+"\n"
		}
		//console.log(f)
		pageTitleContainer.append(f) 
		var post_title = pageTitleContainer.find(".post-title")	//主题
		var badge = pageTitleContainer.find("span[class*=badge]")	//徽章 
		var post_subtitle = pageTitleContainer.find(".post-subtitle") //标签
		var time = pageTitleContainer.find(".time")					//时间
		//var src_page = pageTitleContainer.find(".src_page")			//文章链接<a>	
		var a =pageTitleContainer.find("a[class*='src_page']")		 //文章链接<a>	
		var delBtn = pageTitleContainer.find("a[class*='deletebtn']") //删除按钮
		var publicbtn =  pageTitleContainer.find("a[class*='publicbtn']")  //公布按钮
		var hiddenbtn =  pageTitleContainer.find("a[class*='hiddenbtn']")  //私有按钮
		 
		//解析数据,给页面赋值
		for(i=0;i<datalen;i++){  
			a.eq(i).attr("href","article/"+$(data)[i].id)
			var titlestr ="<b>" +$(data)[i].post_title+"</b>"
			
			var top = $(data)[i].top
			if(0==top){
				titlestr= "<span class='blogtop'>[置顶]</span>"+titlestr;
			}
			post_title.eq(i).append(titlestr)
//			post_title.eq(i).text($(data)[i].post_title)
			//post_subtitle.eq(i).text($(data)[i].post_subtitle)
			
			//副标题
			var tags = $(data)[i].tags;
			var tagSize =  tags.length;
			for(var j = 0 ; j < tagSize ; j++){
				post_subtitle.eq(i).append("<a  style ='color: #888b94;'rel='"+tags[j]+"' href='javascript:' onclick='ssfblog.tagSearch(\""+tags[j]+"\")'>"+tags[j]+"</a>  &nbsp;");
			}
			
			
			time.eq(i).text($(data)[i].time)
//		 	src_page.eq(i).attr("rel",$(data)[i].id)  //使用url来获取文章了, 不需要这个了
			delBtn.eq(i).attr("rel",$(data)[i].id)
			
			if($(data)[i].show_content == 0){  //显示徽章
				badge.eq(i).removeClass("hidden")  
				publicbtn.eq(i).parent().removeClass("disabled") //徽章显示(私有文章), 公布按钮点亮
			}else{
				if(signIn){
					hiddenbtn.eq(i).parent().removeClass("disabled") //如果徽章没有显示(公开文章), 私有按钮点了(在登入的情况下)
				} 
			}  
		} 
		//编辑按钮事件
		$(".editbtn").click(function(){  
			if(signIn){
				var id =$(this).parent().siblings().find(".deletebtn").attr("rel") 
				ssfblog.editPage(id)
			}else{
				ssfblog.alert("warning","需要登入才可编辑~")
			}
			
		})
		
		//登入状态下才可以删除....操作
		if(signIn){
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
//			hiddenbtn.parent().removeClass("disabled") 
			deletebtn.parent().removeClass("disabled")
//			publicbtn.parent().removeClass("disabled")
			
		}
		//loading动画操作
		setTimeout(function(){
			$("#loading").addClass("hidden")
			$("#pageTitleContainer").show("slow");
			if(callback){
				callback();
			}
			
		},500) 
		
	})
}
//对文章信息修改页面内容的刷新
ssfblog.refreshHtml=function(){
	setTimeout(function(){location.reload();},500) 
//	//如果在index.html 页面刷新地址栏
//	if($("body").hasClass("index_page")){
//		setTimeout(function(){location.href="/"},500) 
//	}//在post.ftl时的页面修改
//	else if($("body").hasClass("postpage")){
//			var postBadge = $("#post_badge");
//			if(postBadge.hasClass("hidden")){
//				postBadge.removeClass("hidden")
//			}else{
//					postBadge.addClass("hidden")
//			} 
//	}
}

//隐藏博文
ssfblog.hiddenBlog=function(id){
	var del = function(){  
		$.get("/article/hiddenContent/"+id,function(data){
    		if(!data.error){
    			ssfblog.alert("information","ok~")
//    			setTimeout(function(){location.href="/"},1000) 
    			ssfblog.refreshHtml();
    		}else{
    			ssfblog.alert("error","登入后才有权限操作~") 
    		} 
    	}) 
	}
	ssfblog.confirm("确定设未私有?",del) 
}
 

//删除博文
ssfblog.deleteBlog=function(id){
	var del = function(){
		$.post("/article/delete/"+id,function(data){
    		if(!data.error){
    			ssfblog.alert("information","删除成功")
    			//如果在index页面刷新地址栏
    			setTimeout(function(){location.href="/"},1000) 
//    			ssfblog.refreshHtml()
    		}else{
    			ssfblog.alert("error","没有权限") 
    		} 
    	}) 
	}
	ssfblog.confirm("确定删除?",del) 
}

//public博文
ssfblog.publicBlog=function(id){
	var del = function(){
		$.get("/article/publicContent/"+id,function(data){
    		if(!data.error){
    			ssfblog.alert("information","ok~")
    			//setTimeout(function(){location.href="/"},1000) 
    			ssfblog.refreshHtml()
    		}else{
    			ssfblog.alert("error","没有权限") 
    		} 
    	}) 
	}
	ssfblog.confirm("确定公开?",del) 
}




//登入登出按钮事件
ssfblog.login=function(){
	//登出按钮事件
	$("#signOutBtn").click(function(){
		ssfblog.navSignChange() 
		//后台删除session,前端也要删除sessionStorage
		$.post("/logout",function(data){
			if(data.msg){
				ssfblog.toastr("info",data.msg)
				if(window.sessionStorage){
					window.sessionStorage.setItem("signIn","out");
				}
				//刷新首页
				ssfblog.refreshHtml()
			}
			
		})
	})
	
//登入按钮事件
$("#signInBtn").click(function(){
		 if($("#exampleModal").length>0){
			 $("#exampleModal").modal('show')
		 }else{
			 $.get("/loginPage.html",function(htmldata){  
					$("body").append(htmldata);
					//定义在焦点失去的时候触发的函数;
				    $("#signInForm").validate({
				        onfocusout: function(element) { $(element).valid(); }
				    });
					$("#exampleModal").modal('show');
					$("#exampleModal").on('hidden.bs.modal', function (e) {
						$("#imageCodeSpan").hide();  
					})
					 ssfblog.key13("#submitSignIndata");
					$("#submitSignIndata").click(function(){
						 var tf = $("#signInForm").valid()
						 
						 if($("#imageCode").val().length==0){
							 ssfblog.toastr("warning","验证码不能为空") 
							 return
						 }
						 if(!tf)return
						$.post("/login",$("#signInForm").serialize(),function(data){  
							//console.log(data)
							if(!data.error){ //登入成功 : 关闭界面, 提示成功,在浏览器session中保存登入过, 隐藏登入按钮,现实登出按钮
								 ssfblog.offkey13("#submitSignIndata");
								$("#siginPageClose").click()
								//ssfblog.stroageAdd("signIn","success");
								if(window.sessionStorage){
									window.sessionStorage.setItem("signIn","success");
								}
								ssfblog.toastr("success",data.msg)
								ssfblog.navSignChange() 
								//刷新首页
								ssfblog.refreshHtml()
							}else{ 
								ssfblog.toastr("warning",data.msg)
								ssfblog.stroageAdd("signIn","fail");
								
							}
						})
					})
				}) 
		 }
		
	})
} 

//begin 博文发布
//发布博文页面   
ssfblog.publishPage=function(){
	var publishPageEditor;
	var val;
	$("#publishBtn").click(function(){
		//如果页面已经加载模态框就直接现实
		if($("#publishModal").length>0){
			 $("#publishModal").modal('show') 
			val = publishPageEditor.getValue(); 
			 publishPageEditor = new Simditor({
				  textarea: $('#editorPublish')
				  //optional options
				});
		 
			setTimeout(function(){
				 $("#PublishdataForm div[class='simditor-placeholder']").empty()
				 $("#PublishdataForm div[class='simditor-body']").empty().append(val) 
			},100)
			
			  
		}else{
			//获取页面
			$.get("/publishPage.html",function(htmldata){  
				//else
				$("body").append(htmldata);
				ssfblog.publishPage_html();
				//模态框配置
				$("#publishModal").modal({backdrop:false,keyboard:false,show:true}); 
				publishPageEditor = new Simditor({
					  textarea: $('#editorPublish')
					  //optional options
					});
				//保存按钮设置
				$("#submitPublishdata").click(function(){
					val = publishPageEditor.getValue();  
						ssfblog.saveBlogData(val) 
				}) 
			}) 
			
		}

	})	
}  
//编辑博客页面
ssfblog.editPage=function(id){ 
	var publishPageEditor ;
	//如果已经加载模态框就删除,再加载一个新的
	if($("#editpublishModal").length>0){
		$("#editpublishModal").remove();
//		$("#editpublishModal").modal('show')
//		  publishPageEditor = new Simditor({
//			  textarea: $('#editorPublishText')
//			  //optional options
//			}); 
	}else{
		//获取页面
		$.get("/editPage.html",function(htmldata){ 
				 
				$("body").append(htmldata);  
				ssfblog.editPage_html();
				//模态框配置
				 $("#editpublishModal").modal({backdrop:false,keyboard:false,show:true}); 
				 publishPageEditor = new Simditor({
					  textarea: $('#editorPublishText')
					  //optional options
					});
				//保存按钮设置
				$("#submitPublishdataBtn").click(function(){
					var val = publishPageEditor.getValue();  
						ssfblog.updateBlogData(val,$("#editpublishModal")) 
				}) 
		})
	}
		
			 setTimeout(function(){
				 //页面赋值
					$.get("/article/get/"+id,function(data){    
						$("#editPublishdataForm div[class='simditor-placeholder']").empty()
							$("#editPublishdataForm div[class='simditor-body']").empty().append(data.mapRs.post_content)  
							$('#editpublishTitle').val(data.mapRs.post_title)
							$("#editPageID").val(id) 
							 
							var stateTf = data.mapRs.show_content==1?true:false;
							$("#editshowContent").val(stateTf) 
							var istop = data.mapRs.top==0?true:false;
							$("#editisTop").val(istop) 
//							$("#editpublishPagecheckbox").attr("checked",false)
							//标签赋值
							for(var i=0;i<data.data.length;i++){
								var val = data.data[i].tag
								$("#edittag").append( "<span class=''>"+" "+val+" <button type='button' class='ssfbdel' onclick='ssfblog.rmtag(this)'><span aria-hidden='true' style=' float: none;'>×</span></button>"
										  +"<input type='hidden' name='tag' value='"+val+"' ></span>"
											)
							}
							
							//开关组件设置 
							$('input[name="editpublishPagecheckbox"]').bootstrapSwitch({
								state:stateTf,
								onText:"Yes",
								offText:"No",
							//	labelText:"State",
								offColor:"warning",
								/* onInit:function(event, state) {
									this.state=false
								}, */
							    onSwitchChange:function(event, state) {
									 $("#editshowContent").val(state)
							   }		
							});
							
							$('input[name="editTop"]').bootstrapSwitch({
								state:istop,
								onText:"Yes",
								offText:"No",
							//	labelText:"State",
								offColor:"warning",
								/* onInit:function(event, state) {
									this.state=false
								}, */
							    onSwitchChange:function(event, state) {
									 $("#editisTop").val(state)
							   }		
							});
							
							
						//	$('#edittag')
						 
					});  
			 },100)
			
	 
}  
 
//更新博文
ssfblog.updateBlogData=function(data,$modal){ 
	 
	//获取字符数
	var text = $modal.find(".simditor-body").text();
		text=text.replace(/\s/gi,""); // "\s" :匹配空白符
		textLength = text.length
		//console.log(textLength)
	$modal.find("#edittextLength").val(textLength)
    $modal.find("#editcontent").val(data)  
	var formval  = $modal.find("#editPublishdataForm").serialize()
  $.post("/updatePublishdata",formval ,function(returndata){
		if(!returndata.error){
		//$("#publishdataPageClose").click()
		ssfblog.alert("success",returndata.msg);
		ssfblog.openSignInWindow("#enditpublishdataPageCloseBtn",null)//关闭窗口 
		//在首页发布博文,就刷新页面
		setTimeout(function(){
			location.reload();
		},2000) 
		}else {
			ssfblog.toastr("warning",returndata.msg)	
		 	ssfblog.openSignInWindow("#enditpublishdataPageCloseBtn","#signInBtn");	 
		}
	})
//	$.post("/updatePublishdata",formval ,function(returndata){  
//		if(returndata =="ok"){
//		//$("#publishdataPageClose").click()
//		var	closeBtn =  $modal.find("#publishdataPageClose");
//		ssfblog.openSignInWindow(closeBtn,null)//关闭窗口,
//		}else if(returndata.error ='nologin') {
//			ssfblog.toastr("warning",'未登入不可发布!,请先登入~')	
//		 	ssfblog.openSignInWindow(closeBtn,"#signInBtn");	 
//		}
//	})
}

//提交博文保存
ssfblog.saveBlogData=function(data){ 
	
	//获取字符数
	var text = $(".simditor-body").text();
	text=text.replace(/\s/gi,""); // "\s" :匹配空白符
	textLength = text.length 
	$("#textLength").val(textLength)
    $("#content").val(data)  
	var formval  = $("#PublishdataForm").serialize()
 
	//校验
	if(!$("#publishTitle").valid() || textLength<1){
		if(textLength<1){
			ssfblog.alert("error","内容不能为空")
		}
		return
	} 
	$.post("/submitPublishdata",formval ,function(returndata){
		if(!returndata.error){
		//$("#publishdataPageClose").click()
			ssfblog.alert("success",returndata.msg);
		ssfblog.openSignInWindow("#publishdataPageClose",null)//关闭窗口
		
		//在首页发布博文,就刷新页面
		setTimeout(function(){
			var href = location.pathname
			if(href == "/index.html" || href== "/"){
				location.href="/"
			}  
		},2000) 
		}else {
			ssfblog.toastr("warning",returndata.msg)	
		 	ssfblog.openSignInWindow("#publishdataPageClose","#signInBtn");	 
		}
	})
}
//删除标签
ssfblog.rmtag=function(the){  
	$(the).parent().remove();
}

//关闭弹出框,打开登入框(参数为jquery能选择到的对象, 不想做操作传入字符串"null")
ssfblog.openSignInWindow=function(close,open){
	if(close){
		$(close).click();
	}
	if(open){
		$(open).click()
	}
}


//加载导航
ssfblog.navPage=function(){ 
	$.get("/nav.html",function(htmldata){  //获取展示的html样式 
		$("nav").append(htmldata);
		ssfblog.nav_Html();
		ssfblog.login();
		ssfblog.publishPage();
		//如果登入过就隐藏登入按钮
		if(window.sessionStorage){ 
			 var si = window.sessionStorage.getItem("signIn") 
			 if("success" == si){
				 ssfblog.navSignChange() 
			 }
		}
	})
}
//加载footer
ssfblog.footerPage=function(){
	$.get("/footer.html",function(htmldata){  //获取展示的html样式 
		$("footer").append(htmldata) 
	})
}


/*
 * 公共方法
*/
//提示组件配置; 
ssfblog.alert=function(method,msg){
	ssfblog.toastr(method,msg)
}
ssfblog.toastr=function(method,msg){
	 new Noty({
	      text: msg,
	      type:method  ,  // var types = ['alert', 'warning', 'success', 'information', 'error']
	      theme: 'metroui',
	      layout: 'topRight',
	      timeout: 2000,
	      animation: {
	        open: mojsShow,
	        close: mojsClose
	      }
	    }).show()
	
//	new Noty({
//		  type: method,
//		  layout: 'topRight',
//		  theme: 'metroui',
//		  text: msg,
//		  timeout: 1000,
//		  progressBar: true,
//		  closeWith: ['click', 'button'],
//		  animation: {
////		    open: 'noty_effects_open',
////		    close: 'noty_effects_close'
//			  open: mojsShow,
//		      close: mojsClose
//		  },
//		  id: false,
//		  force: false,
//		  killer: false,
//		  queue: 'global',
//		  container: false,
//		  buttons: [],
//		  sounds: {
//		    sources: [],
//		    volume: 1,
//		    conditions: []
//		  },
//		  titleCount: {
//		    conditions: []
//		  },
//		  modal: false
//		}).show()
	
/*之前用的, 没有动画效果
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
   
   */	 
}

//确认框
ssfblog.confirm=function(msg,func){
	var n = new Noty({
		  text: msg,
		  theme:'metroui',
		  type:"warning",
		  buttons: [
		    Noty.button('YES', 'btn btn-success',function(){
		    	func();
		        n.close();
		    }), 
		    Noty.button('NO', 'btn btn-error', function () { 
		        n.close();
		    })
		  ]
		}).show();  
}

//回车触发点击事件
ssfblog.key13=function(target,el){ 
	var ele ;
	if(el){
		ele = el
	}else{
		ele=document 
	}
	$(ele).on("keyup",function(e){   
		if(e.keyCode == 13){ 
			$(target).click()
		} 
	}) 
} 
//别名
ssfblog.key_enter=function(target,el){
	ssfblog.key13(target,el);
}

//解绑回车触发点击事件
ssfblog.offkey13=function(el){ 
	var ele ;
	if(el){
		ele = el
	}else{
		ele=document 
	}
	$(ele).off("keyup") 
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



//提示组件的动画效果 
var mojsShow = function (promise) {
  var n = this
  var Timeline = new mojs.Timeline()
  var body = new mojs.Html({
    el: n.barDom,
    x: {500: 0, delay: 0, duration: 500, easing: 'elastic.out'},
    isForce3d: true,
    onComplete: function () {
      promise(function (resolve) {
        resolve()
      })
    }
  })

  var parent = new mojs.Shape({
    parent: n.barDom,
    width: 200,
    height: n.barDom.getBoundingClientRect().height,
    radius: 0,
    x: {[150]: -150},
    duration: 1.2 * 500,
    isShowStart: true
  })
  /**
   * var parent=new mojs.Shape({
   * parent:n.barDom,
   * width:200,
   * height:n.barDom.getBoundingClientRect().height,
   * radius:0,
   * x:_defineProperty({},150,-150),
   * duration:1.2*500,
   * isShowStart:true});
   */
  

  n.barDom.style['overflow'] = 'visible'
  parent.el.style['overflow'] = 'hidden'

  var burst = new mojs.Burst({
    parent: parent.el,
    count: 10,
    top: n.barDom.getBoundingClientRect().height + 75,
    degree: 90,
    radius: 75,
    angle: {[-90]: 40},
    children: {
      fill: '#EBD761',
      delay: 'stagger(500, -50)',
      radius: 'rand(8, 25)',
      direction: -1,
      isSwirl: true
    }
  })

  const fadeBurst = new mojs.Burst({
    parent: parent.el,
    count: 2,
    degree: 0,
    angle: 75,
    radius: {0: 100},
    top: '90%',
    children: {
      fill: '#EBD761',
      pathScale: [.65, 1],
      radius: 'rand(12, 15)',
      direction: [-1, 1],
      delay: .8 * 500,
      isSwirl: true
    }
  })

  Timeline.add(body, burst, fadeBurst, parent)
  Timeline.play()
}

var mojsClose = function (promise) {
  var n = this
  new mojs.Html({
    el: n.barDom,
    x: {0: 500, delay: 10, duration: 500, easing: 'cubic.out'},
    isForce3d: true,
    onComplete: function () {
      promise(function (resolve) {
        resolve()
      })
    }
  }).play()
}

//begin 分页组件 
//分页信息缓存
ssfblog.pageInfo={
	limit:0,
	offset:0,
	index:0,
	count:0,
	first:true,
	previous:"",
	next:""
}
 
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
				 $(next).hide();  //隐藏
				// ssfblog.addDisabled(next) ; //禁用样式 
			 } 
		}else{
			 if(next)
			// ssfblog.rmDisabled(next)
				 $(next).show();  //显示
		} 
		 //给上一页加样式 
		 if(page.offset<=0){ //如果offset<=0 添加禁用样式
			 if(previous)
			// ssfblog.addDisabled(previous) //禁用样式改为隐藏
			$(previous).hide();			    //隐藏
		}else{
			 if(previous)
				 $(previous).show();			//现实
			// ssfblog.rmDisabled(previous)//移除禁用样式改为显示
		} 
		$.extend(ssfblog.pageInfo,page)  //全局变量缓存
		return page;
	}
}
ssfblog.hasDisabledStyle = false;
//添加禁用 
ssfblog.addDisabled=function(obj){
	var theObj = $(obj)
	theObj.attr("disabled","Disabled"); 
	//遍历style元素没有禁用样式就添加一个
	if(!ssfblog.hasDisabledStyle){ 
//		var styleObj = $("style");
//		$.each(styleObj,function(i,n){
//			var text = $(n).text(); 
//			if(text.indexOf(".cursor_not_allowed")>=0){
//				ssfblog.hasDisabledStyle = true;
//				 return false;
//			}
//		}) 
		ssfblog.hasDisabledStyle = true;
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
//end 分页组件




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

//control+enter键触发点击时间
ssfblog.ctlEnterToClick=function(focus,btn){
	$(focus).on("keyup",function(e){  
		if(e.keyCode==13 ){
			if (event.ctrlKey==1){ 
				$(btn).click() 
			}
		}
	})
//	$("#publishModal").on("keyup",function(e){  
//		if(e.keyCode==13 ){
//			if (event.ctrlKey==1){ 
//				$("#submitPublishdata").click() 
//			}
//		}
//	})
}


//各页面初始化方法

//nav.html
ssfblog.nav_Html=function(){
	// 不同页面, 对导航栏做不同效果定制
	var href = location.pathname
	if(href == "/index.html" || href== "/"){
 		$("#logo").addClass("visible-xs")
	} 
	if($("body").hasClass("postpage")){
		$("#bs-example-navbar-collapse-1>ul>li> a").attr("style","color: #969696")
		$("#a_dropdown").attr("style","color: black");
		$("#logo").attr("style","color: #969696");
		$("nav").append('<hr style="margin-top: 0px;margin-bottom: 0px;">')
	} 
	//附加菜单使用鼠标悬浮出发
	$("#a_dropdown").hover( function(){ $(this).click() },function(){} );
	
	//给页面添加搜索框

	var searchHtml ='<div class="modal fade" tabindex="-1" role="dialog" id="searchModal">   <div class="modal-dialog modal-lg" role="document" style="max-width: 700px;" > <div class="modal-content" style=" box-shadow: -3px 10px 20px #f5f5f5; top: 200px;  "> <button  type="button" class="hidden" data-dismiss="modal" id="searchModalClose">Close</button> <div class="input-group input-group-lg">  <span class="input-group-addon " id="searchbtn" style="border: 0px;background-color:white;"><a  id="search_a" href="javascript:"><i class="fa fa-search" aria-hidden="true"></i></a></span>  <input id="searchInput" autofocus  type="text" class="form-control search" placeholder="标题搜索..." > </div>  </div> </div> </div>'
	$('body').append(searchHtml);
}

//publishPage.html 页面初始化函数
ssfblog.publishPage_html=function(){ 
	//把输入框中的标签放入标签栏,并情况输入框
	var tagIn = function(){
		if($("#tagInput").val()){
			var val = $("#tagInput").val()
			$("#tag").append( "<span class=''>"+" #"+val+" <button type='button' class='ssfbdel' onclick='ssfblog.rmtag(this)'><span aria-hidden='true' style=' float: none;'>×</span></button>"
							  +"<input type='hidden' name='tag' value='#"+val+"' ></span>"
								)
			$("#tagInput").val("") 
		} 
	}
	//输入框change事件
	$("#tagInput").on("change",function(e){
		tagIn() 
	})
	//输入框中捕获回车,逗号,分号按键, 就将输入框中的值放入标签栏中
	$("#tagInput").on("keyup",function(e){  
		if(e.keyCode==13 || e.keyCode==186 || e.keyCode==188){ 
			var str = $("#tagInput").val() 
			str = str.substring(0,str.length-1)
			  $("#tagInput").val(str)
			tagIn()  
    	}   
	})  
	//2个按钮初始化
	$('input[name="publishPagecheckbox"]').bootstrapSwitch({
				state:false,  //input的checked可以控制这个状态
				onText:"Yes",
				offText:"No",
				//labelText:"State",
				offColor:"warning",
				/* onInit:function(event, state) {
					this.state=false
				}, */
			    onSwitchChange:function(event, state) {
					 $("#showContent").val(state)
			   }		
		});
		
		$('input[name="top"]').bootstrapSwitch({
			state:false,  //input的checked可以控制这个状态
			onText:"Yes",
			offText:"No",
			//labelText:"State",
			offColor:"warning",
			/* onInit:function(event, state) {
				this.state=false
			}, */
		    onSwitchChange:function(event, state) {
				 $("#istop").val(state)
		   }		
	});
	
	
	//发布快捷键
	ssfblog.ctlEnterToClick("#publishModal","#submitPublishdata")
}

//editPage.html
ssfblog.editPage_html=function(){
		//把输入框中的标签放入标签栏,并情况输入框
	var tagIn = function(){
		if($("#edittagInput").val()){
			var val = $("#edittagInput").val()
			$("#edittag").append( "<span class=''>"+" #"+val+" <button type='button' class='ssfbdel' onclick='ssfblog.rmtag(this)'><span aria-hidden='true' style=' float: none;'>×</span></button>"
							  +"<input type='hidden' name='tag' value='#"+val+"' ></span>"
								)
			$("#edittagInput").val("")
			
		} 
	}
	//输入框change事件
	$("#edittagInput").on("change",function(e){
		tagIn() 
	})
	//输入框中捕获回车,逗号,分号按键, 就将输入框中的值放入标签栏中
	$("#edittagInput").on("keyup",function(e){  
		if(e.keyCode==13 || e.keyCode==186 || e.keyCode==188){ 
			var str = $("#edittagInput").val() 
			str = str.substring(0,str.length-1)
			  $("#edittagInput").val(str)
			tagIn()  
    	}   
	}) 
	//发布快捷键
	ssfblog.ctlEnterToClick("#editpublishModal","#submitPublishdataBtn")
//	$("#").on("keyup",function(e){  
//		if(e.keyCode==13 ){
//			if (event.ctrlKey==1){ 
//				$("#").click() 
//			}
//		}
//	}) 
	

	
}
ssfblog.comment_html;
//评论点赞
ssfblog.commentLike=function(id,thiz){
	$.get("commentLikePlus/"+id,function(data){
		if(!data.error){
			 $(thiz).parent().find(".commentLikelength").text(data.msg)
		}
	})	
	 
}  
//表单提交:评论
ssfblog.bindEvenToPostComment=function(id){ 
	if($("#coment_form").valid()){  
		$.post("comment/"+id,$("#coment_form").serialize(),function(){
			location.reload();
		})
	} 
}

ssfblog.reply=function(thiz,id,name){
		function cleanCommentDiv(){
		 $(".reply_area").empty();//评论区先清空 
	        $.each( $(".sub-comment-list"),function(i,n){
	        	if($(n).find('div').length<2){
	        		$(n).addClass("hidden")
	        	}
	        })
	     $("#cancel_reply").addClass("hidden")
		}
	  
     var $div = $("#comment_form_div"); 
     $div.empty()
     //找到评论的区
    var $comment =  $(thiz).closest(".comment")
    var $reply_area =  $comment.find(".reply_area");
    
    cleanCommentDiv();
    
     $reply_area.append(ssfblog.comment_html)
     if(name){
     	$("#comment").append("@"+name+" ")
     }
     $("#comment").focus()
     $reply_area.parent().removeClass("hidden")   
      $("#cancel_reply").removeClass("hidden")
      //取消
     $("#cancel_reply").one("click",function(){  
    	//主评论区, 添加评论form, 取消按钮隐藏, 主评论区的提交重新帮定事件的清空子评论区的form
    	//$reply_area.empty(); 
     	$div.append(ssfblog.comment_html)	
     	//$("#cancel_reply").addClass("hidden")
     	cleanCommentDiv();
 		$("#post_comment").click(function(){
			 ssfblog.bindEvenToPostComment("-1");
		})
     })
     	//提交按钮绑定新的事件
	 $("#post_comment").on("click",function(){ssfblog.bindEvenToPostComment(id)})
}

//搜索
ssfblog.showSearch=function(show){
	 if(show){
		 $('#searchModal').modal({backdrop:true,show:true});  
		 $('#searchModal').one('shown.bs.modal', function (e) {
			 document.getElementById('searchInput').focus();
		 })

	 }
	
	 
	 $("#search_a").one('click',function(){  
		 var val = $("#searchInput").val(); 
		 $.cookie('searchVal', val, { expires: 1 }); 
		 /**
		    $("#searchInput").val($.cookie('searchVal')); 
		    ssfblog.showSearch()
		  */
		 ssfblog.search(val);
		 ssfblog.offkey13("#searchInput")
	 })
	 ssfblog.key_enter("#search_a","#searchInput") 
	 
}

$(function(){
	
	//$("head").append("<script></script>")
//	ssfblog.initPage()  
//	ssfblog.backToTop() 
	setTimeout(function(){
		$(document).ajaxError(function(event,request, settings){
			ssfblog.alert("error","服务器出错了~") 
	});
	},50)
	//index.html
	var searchstr = location.search;
 
	//
	if($("body").hasClass("postpage")){//文章阅读代码
		ssfblog.postpageInitfunc()
		
	}else if($("body").hasClass("contact_html")){ 
		$("#submitBTN").on("mouseup",function(){
			 setTimeout(function(){ 
				if($(".error").length == 0){
					$("#submitBTN").attr("disabled","disabled")
					$("#submitBTN_ico").removeClass("hidden")
				}
				 },100) 
		})
	}
	else if($("body").hasClass("index_page")){//首页代码
		//搜索时
		
	 	if(searchstr=='?search'){ 
	 		setTimeout(function(){
	 			val = $.cookie('searchVal') 
		 		$("#searchInput").val( $.cookie('searchVal') );  
			    ssfblog.showSearch(false);
			    $("#search_a").click()
			   
	 		},50)
	 		
	 		return;
	 	}
		
		
		//分页初始化; 设置每页显示的行数, 上一页和下一页的按钮
		pageSplit = ssfblog.paging(ssfblog.showcount,"#previous","#next");  
		//获取第一页
		obj = pageSplit() 
		//页面内容初始化
	   $.get("/pageTitle/1/"+obj.limit+"/"+obj.offset,function(data){  
		 /*   ssfblog.initIndex(data.mapRs.dataList) */
		   ssfblog.initIndex(data)
		   pageSplit({setCount:{tf:true,count:data.mapRs.Count}}) //把后端获取的总行数,赋值给分页对象的总行数
		   
		})
		//下一页
	 $("#next").click(function(){ 
		 //到了最后一页,直接返回(点击无效)
		 if((ssfblog.pageInfo.offset+ssfblog.pageInfo.limit) >=ssfblog.pageInfo.count){ 
				 return
			 }
		 $("#loading").removeClass("hidden");
			//$("#pageTitleContainer").show("slow");
		 var p =  pageSplit();    
		 
		  $.get("/pageTitle/0/"+p.limit+"/"+p.offset,function(data){    
			  $("#pageTitleContainer").html("");	
			   ssfblog.initIndex(data)
			  // $("#loading").hide("slow");
			})    
	 })
	 //上一页
	 $("#previous").click(function(){ 
		 //到了第一页,直接返回(点击无效)
		 if(ssfblog.pageInfo.index <=0){ 
			 return
		 }  
		 $("#loading").removeClass("hidden");
		  var p =  pageSplit({previous:true}); 
		
		  $.get("/pageTitle/0/"+p.limit+"/"+p.offset,function(data){ 
			  $("#pageTitleContainer").html("");
			   ssfblog.initIndex(data	) 
			})   
	 })
	 
	}
	setTimeout(function(){
		ssfblog.initPage()  
		ssfblog.backToTop() 
	},800)
	
})
//阅读页面初始化方法
ssfblog.postpageInitfunc=function(){
	var islogin = $("#islogin").val();
	if('false'==islogin){
		window.sessionStorage.setItem("signIn","out");
	}
	$(".image").viewer({url:"data-original",title:false,toolbar:false,tooltip:false});  //查看图片插件
	var $postContent = $("#postContent")
	$postContent.find("img").viewer({url:"data-original",title:false,toolbar:false,tooltip:false}); 
	var $postContent_a = $postContent.find("a");
	$postContent_a.append('<i class="fa fa-external-link VisibilityHidden"  aria-hidden="true"></i>');
	$postContent_a.hover(
			  function () {
				    $(this).find("i").removeClass("VisibilityHidden")
				  },
				  function () {
					  $(this).find("i").addClass("VisibilityHidden")
				  }
				);
	ssfblog.comment_html =  $("#comment_form_div").html();
	$("#post_comment").click(function(){
	    ssfblog.bindEvenToPostComment("-1") 
	})
	
	//喜欢按钮事件
	$("#likebtn").click(function(){
		$.get("likeplus/"+$("#postId").val(),function(data){
			if(!data.error){
				$("#likecount").text(data.msg)
				$("#postLike").text(data.msg)
			}
		})	
	});
}

ssfblog.search=function(val){
	if(!$("body").hasClass("index_page")){
		location.href="/index.html?search"
		return
	}
	$("#searchInput").val("");
	var valstr = "val_"+val;
	var url = "/search/"+valstr;
	$.get(url,function(data){  
		//console.log(data)
		$("#pageTitleContainer").html("");
		
		$("#loading").removeClass("hidden");
		$("#previous").hide();
		$("#next").hide();
		//回调函数
		var callback = function(){
		 var htmltext = "<span class='tagSearch'>"+val+"</span>"
		 var length = 	$(".post-title b").length
			$.each($(".post-title b"),function(i,n){
				var el = $(n);
				var text = el.text();   
				text = text.replace(new RegExp(val, 'g'), htmltext); 
				 el.empty();
				 el.append(text)  
			}) 
			$("#searchModalClose").click();
		 	
//		 	 $("#search_a").off("click")
		  
		    if(data.mapRs.dataList.length== 0){
		    	$("#pageTitleContainer").empty()
		    	$("#pageTitleContainer").append("<p>没找到与'"+htmltext+"'相关的标题</p>")
		    }
		}
		ssfblog.initIndex(data,callback);
		   
		})
}

//bolg内容加载
//@Deprecated  //页面有后端渲染了
//ssfblog.initPost=function(){ 
//	var postContent = $.cookie("postContent")
//	
//	//$.removeCookie("postContent");
//	if(!postContent){return}
//	$.get("/pageTitle/postContent/"+postContent,function(data){ 
//		$(".post-heading").append('<span class="meta" id="time">Posted by <a href="#">Tenie Bolg</a> on '+$(data)[0].time+' </span>')
//		$("#post-title").text($(data)[0].post_title)
//		$("#post-subtitle").text($(data)[0].post_subtitle)
//		$("#postContent").html($(data)[0].post_content)
//	}) 
//}

/**
 *  图片轮播激活方法
		$('.bg-slider').flexslider({
	        animation: "fade",
	        directionNav: false, //remove the default direction-nav - https://github.com/woothemes/FlexSlider/wiki/FlexSlider-Properties
	        controlNav: false, //remove the default control-nav
	        slideshowSpeed: 6000
	    }); 
 * //拖动元素框
	$("#publishModal").Tdrag({
    	 handle:".title"
    });
    //定义在焦点失去的时候触发验证函数;
    $("#PublishdataForm").validate({
      //  onfocusout: function(element) { $(element).valid(); }
    }); 
   //form提交时触发校验的回调 
    $.validator.setDefaults({
    submitHandler: function() {
   //   alert("提交事件!"); 提交时会触发只有submit按钮和form.submit()才能触发
    }
});
 * 
 */
