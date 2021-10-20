<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>${blogTitle}- About</title>
<link rel="shortcut icon" href="lib/assets/img/codeMonkey.ico">
<link href="lib/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="lib/css/ssfblog_css/main.css" rel="stylesheet">
<link href="lib/css/clean-blog.min.css" rel="stylesheet">
<link href="lib/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css"
	href="lib/noty-master/lib/noty.css" />
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap_switch/css/bootstrap3/bootstrap-switch.css" />
<style>
p {
	margin: 0px 0;
}

body {
	font-size: 14px;
}

.post-title {
	font-size: 26px;
	text-align: center;
	word-break: break-word;
	font-weight: 400
}
</style>
</head>

<body>
	<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
	</nav>
	<header class="intro-header"
		style="background-image: url('lib/img/bg-footer-2.jpg')">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
					<div class="page-heading">
						<h1>About Me</h1>
						<hr class="small">
						<span class="subheading">This is what I do.</span>
					</div>
				</div>
			</div>
		</div>
	</header>
	<div class="container">
		<div class="row">
			<div id="about_content"
				class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">
				<div class="content-wrap">
					<div id="content" class="content">

						<div id="posts" class="posts-expand">
							<header class="post-header">
								<h1 class="post-title" itemprop="name headline">${aboutMeTitle}</h1>
							</header>
							<h2 id="">
								<a href="" class="headerlink" title="${aboutMeHead1}"></a>${aboutMeHead1}
							</h2>
							<p>${aboutMeText1}</p>
							<h2 id="关于我">${aboutMeHead2}</h2>
							<p>${aboutMeText2}</p>
						</div>
					</div>
					<div class="comments" id="comments"></div>
				</div>
			</div>
		</div>
	</div>
	<hr>
	<footer> </footer>
<script src="/lib/js/ssfblog_js/app.js"></script>
<script src="/lib/js/ssfblog_js/main.js"></script>
</body>
</html>
