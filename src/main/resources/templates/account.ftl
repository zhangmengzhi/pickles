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
	<!--///////////////////Account Page///////////////////-->
	<!--//////////////////////////////////////////////////-->
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ul class="breadcrumb">
						<li><a href="index.html">Home</a></li>
						<li><a href="account.html">Account</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="heading"><h2>Login</h2></div>
					<form name="form1" id="ff1" method="post" action="login.php">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Username :" name="username" id="username" required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="Password :" name="email" id="email" required>
						</div>
						<button type="submit" class="btn btn-1" name="login" id="login">Login</button>
						<a href="#">Forgot Your Password ?</a>
					</form>
				</div>
				<div class="col-md-6">
					<div class="heading"><h2>New User ? Create An Account.</h2></div>
					<form name="form2" id="ff2" method="post" action="register.php">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="First Name :" name="firstname" id="firstname" required>
						</div>
						<div class="form-group">
							<input type="email" class="form-control" placeholder="Last Name :" name="lastname" id="lastname" required>
						</div>
						<div class="form-group">
							<input type="tel" class="form-control" placeholder="Email Address :" name="email" id="email" required>
						</div>
						<div class="form-group">
							<input type="tel" class="form-control" placeholder="Mobile :" name="phone" id="phone" required>
						</div>
						<div class="form-group">
							<input name="gender" id="gender" type="radio"> Male <input name="gender" id="gender" type="radio"> Female 
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="Password :" name="password" id="password" required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="Retype Password :" name="repassword" id="repassword" required>
						</div>
						<div class="form-group">
							<input name="agree" id="agree" type="checkbox" > I agree to your website.
						</div>
						<button type="submit" class="btn btn-1">Create</button>
					</form>
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