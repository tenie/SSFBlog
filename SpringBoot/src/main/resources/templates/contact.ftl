<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title></title> 
	<link rel="shortcut icon" href="lib/assets/img/codeMonkey.ico"> 
    <link href="lib/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
	<link href="lib/css/ssfblog_css/main.css" rel="stylesheet">
    <link href="lib/css/clean-blog.min.css" rel="stylesheet"> 
    <link href="lib/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="lib/noty-master/lib/noty.css" />  
	<link rel="stylesheet" type="text/css" href="lib/bootstrap_switch/css/bootstrap3/bootstrap-switch.css" /> 	 
</head>
<body class="contact_html">
    <nav class="navbar navbar-default navbar-custom navbar-fixed-top">
    </nav>
    <header class="intro-header" style="background-image: url('lib/img/bg-footer-1.jpg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="page-heading">
                        <h1>联系我</h1>
                        <hr class="small">
                        <span class="subheading">有什么问题? 联系我, 会给您答复(可能)~</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Main Content -->
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <p>如果您想联系我 ?</p>
                <p>填写下面信息, 提交后您的信息将发送到我的邮箱: 
                <a href="mailto:${myEmail}"  > <font><font>${myEmail}</font></font></a>
				</p>
                <form name="sentMessage" id="contactForm" novalidate>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label>Name</label>
                            <input type="text" class="form-control" placeholder="Name" id="name" name="name"required data-validation-required-message="Please enter your name.">
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label>Email Address</label>
                            <input type="email" class="form-control" placeholder="Email Address" id="email"  name="email" required data-validation-required-message="Please enter your email address.">
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label>Phone Number</label>
                            <input type="tel" class="form-control" placeholder="Phone Number" id="phone" name="phone" required data-validation-required-message="Please enter your phone number.">
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label>Message</label>
                            <textarea rows="5" class="form-control" placeholder="Message" id="message" name="message"  required data-validation-required-message="Please enter a message."></textarea>
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <br>
                    <div id="success"></div>
                    <div class="row">
                        <div class="form-group col-xs-12">
                            <button id="submitBTN"  class="btn btn-default">提交
                            <i id="submitBTN_ico" class="fa fa-spinner fa-spin animated hidden"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <hr>
    <footer>
    </footer>
<script src="/lib/js/ssfblog_js/app.js"></script> 
<script src="/lib/js/ssfblog_js/main.js"></script>  
</body>   
</html>
