<div class="container-fluid" style="color: #969696;">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header page-scroll">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">Toggle navigation</span> Menu
			<i class="fafa-bars"> </i>
		</button>
		<a class="navbar-brand " href="/" id="logo">${blogTitle}</a>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav navbar-right ">

			<li><a href="/">主页</a></li>
			<li><a href="/contact">联系</a></li>
			<li><a href="javascript:" onclick="ssfblog.showSearch(true)">搜索</a></li>
			<!-- 
			<li><a href="javascript:" >Image</a></li>
			-->
			<li><a href="/about">关于</a></li>
			<li>
				<div class="btn-group  ">
					<a id="a_dropdown" href="#" class="btn   dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#" id="signInBtn"><i class="fa fa-user"
								aria-hidden="true"></i> 登入</a></li>
						<li><a href="#" id="signOutBtn" class="hidden"><i
								class="fa fa-user-secret" aria-hidden="true"></i> 登出</a></li>
						<li><a href="/publishSpacePage.html" id="publishBtn2"><i
								class="fa fa-pencil-square-o" aria-hidden="true"></i> 发布</a></li>

					</ul>
				</div>
			</li>
		</ul>
	</div>
</div>
<script type="text/javascript">
var a_idx = 0;
var emije = new Array("🎁", "🎈", "🎏", "🎀", "🎊", "🎉", "🎎", "🏮", "❤️", "💛", "💚", "💙", "💜", "🖤", "❣️", "💕", "💞", "💓", "💗", "💖", "💘", "💝", "💟", "💯", "❗️", "🔅", "⁉️", "🔆", "🎵", "🎶", "💋", "😍", "🥰", "😆", "😁", "😄", "😃", "�", "😅", "🙂", "🙃", "😉", "😗", "😙", "😋", "😝", "😜", "🤪", "🤓", "🥳", "🤩", "🤗", "🤔", "🤭", "🤫", "🤥", "😵", "👿", "👽", "🌻", "🌼", "🌹", "🥀", "🔥", "💥", "💫", "⭐️", "🌟", "✨", "⚡️", "☄️", "️", "🌈", "🌪", "🌤", "⛅️", "🌥", "☁️", "🌦", "🌧", "⛈", "🌩", "🌨", "❄️", "☃️", "⛄️", "🌬", "💨", "🌊");

jQuery(document).ready(function($) {
$("body").click(function(e) {

var a = new Array("", "", "", "", "", "", "" ,"", "", "", "", "");
var idx = Math.ceil(Math.random()*88);
var idx2 = Math.ceil(Math.random()*88); 

var $i = $("<span/>").text(a[a_idx]+" "+emije[idx2]);

//var $i = $("<span/>").text(a[a_idx]);
a_idx = (a_idx + 1) % a.length;
var x = e.pageX,
y = e.pageY;
$i.css({
"z-index": 999999999999999999999999999999999999999999999999999999999999999999999,
"top": y - 20,
"left": x,
"position": "absolute",
"font-weight": "bold",
"color": "#ff6651"
});
$("body").append($i);
$i.animate({
"top": y - 180,
"opacity": 0
},
1500,
function() {
$i.remove();
});
});
});
</script>
