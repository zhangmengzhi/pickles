<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
    <head>
    <base id="base" href="${base}">
	<title>咸菜罐子 - pickles</title>
	<meta charset="utf-8" />
	<meta name="keywords" content="pickles空白页" />
	<meta name="description" content="pickles空白页" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="${base}/static/assets/css/bootstrap.min.css"  type="text/css">
	
	<!-- Custom CSS -->
    <link rel="stylesheet" href="${base}/static/assets/css/style.css">
	
	
	<!-- Custom Fonts -->
    <link rel="stylesheet" href="${base}/static/assets/font-awesome/css/font-awesome.min.css"  type="text/css">
    <link rel="stylesheet" href="${base}/static/assets/fonts/font-slider.css" type="text/css">
	
	<!-- jQuery and Modernizr-->
	<script src="${base}/static/assets/js/jquery-2.1.1.js"></script>
	
	<!-- Core JavaScript Files -->  	 
    <script src="${base}/static/assets/js/bootstrap.min.js"></script>
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="${base}/static/assets/js/html5shiv.js"></script>
        <script src="${base}/static/assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<!--Top-->
	<nav id="top">
		<div class="container">
			<div class="row">
				<div class="col-xs-6">
				</div>
				<div class="col-xs-6">
					<ul class="top-link">
						<li><a href="${base}/cart"><span class="glyphicon glyphicon-shopping-cart"></span>购物车</a></li>
						<li><a href="${base}/user/account"><span class="glyphicon glyphicon-user"></span>我的账户</a></li>
						<li><a href="${base}/contact"><span class="glyphicon glyphicon-envelope"></span> 联系我们</a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<!--Header-->
	<header class="container">
		<div class="row">
			<div class="col-md-4">
				<div id="logo"><img src="${base}/static/assets/images/logo.png" /></div>
			</div>
			<div class="col-md-4">
				<form class="form-search">  
					<input type="text" class="input-medium search-query">  
					<button type="submit" class="btn"><span class="glyphicon glyphicon-search"></span></button>  
				</form>
			</div>
		</div>
	</header>
	<!--Navigation-->
    <nav id="menu" class="navbar">
		<div class="container">
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				${navBar}
			</div>
		</div>
	</nav>
	<!--//////////////////////////////////////////////////-->
	<!--///////////////////Contact Page///////////////////-->
	<!--//////////////////////////////////////////////////-->
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ul class="breadcrumb">
						<li><a href="index.html">Home</a></li>
						<li><a href="contact.html">Contact</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="heading"><h1>CONTACT US</h1></div>
				</div>
				<div class="col-md-6" style="margin-bottom: 30px;">
					<form name="form1" id="ff" method="post" action="contact.php">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Your Name *" name="name" id="name" required data-validation-required-message="Please enter your name.">
						</div>
						<div class="form-group">
							<input type="email" class="form-control" placeholder="Your Email *" name="email" id="email" required data-validation-required-message="Please enter your email address.">
						</div>
						<div class="form-group">
							<input type="tel" class="form-control" placeholder="Your Phone *" name="phone" id="phone" required data-validation-required-message="Please enter your phone number.">
						</div>
						<div class="form-group">
							<textarea class="form-control" placeholder="Your Message *" name="message" id="message" required data-validation-required-message="Please enter a message."></textarea>
						</div>
						<button type="submit" class="btn btn-1">Send Message</button>
					</form>
				</div>
				<div class="col-md-6">
					<p><span class="glyphicon glyphicon-home"></span> California, United States 3000009</p>
					<p><span class="glyphicon glyphicon-earphone"></span> +6221 888 888 90 , +6221 888 88891</p>
					<p><span class="glyphicon glyphicon-envelope"></span> info@yourdomain.com</p>
					<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3164.289259162295!2d-120.7989351!3d37.5246781!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8091042b3386acd7%3A0x3b4a4cedc60363dd!2sMain+St%2C+Denair%2C+CA+95316%2C+Hoa+K%E1%BB%B3!5e0!3m2!1svi!2s!4v1434016649434" width="95%" height="230" frameborder="0" style="border:0"></iframe>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<div class="copyright">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						Copyright &copy; 2015-2016.张孟志 All rights reserved.
					</div>
					<div class="col-md-6">
						<div class="pull-right">
							<ul>
								<li><img src="" /></li>								
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>
