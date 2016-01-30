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
	<!--///////////////////Cart Page//////////////////////-->
	<!--//////////////////////////////////////////////////-->
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ul class="breadcrumb">
						<li><a href="index.html">Home</a></li>
						<li><a href="cart.html">Cart</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="product well">
					<div class="col-md-3">
						<div class="image">
							<img src="${base}/static/assets/images/galaxy-note.jpg" />
						</div>
					</div>
					<div class="col-md-9">
						<div class="caption">
							<div class="name"><h3><a href="product.html">Aliquam erat volutpat</a></h3></div>
							<div class="info">	
								<ul>
									<li>Brand: text</li>
									<li>ID: 0122222</li>
								</ul>
							</div>
							<div class="price">$122<span>$98</span></div>
							<label>Qty: </label> <input class="form-inline quantity" type="text" value="1"><a href="#" class="btn btn-2 ">Update</a>
							<hr>
							<a href="#" class="btn btn-default pull-right">REMOVE</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>	
			</div>
			<div class="row">
				<div class="product well">
					<div class="col-md-3">
						<div class="image">
							<img src="${base}/static/assets/images/Z1.png" />
						</div>
					</div>
					<div class="col-md-9">
						<div class="caption">
							<div class="name"><h3><a href="product.html">Aliquam erat volutpat</a></h3></div>
							<div class="info">
								<ul>
									<li>Brand: text</li>
									<li>ID: 0122222</li>
								</ul>
							</div>
							<div class="price">$122<span>$98</span></div>
							<label>Qty: </label> <input class="form-inline quantity" type="text" value="1"><a href="#" class="btn btn-2 ">Update</a>
							<hr>
							<a href="#" class="btn btn-default pull-right">REMOVE</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>	
			</div>
			<div class="row">
				<div class="col-md-4 col-md-offset-8 ">
					<center><a href="#" class="btn btn-1">Continue To Shopping</a></center>
				</div>
			</div>
			<div class="row">
				<div class="pricedetails">
					<div class="col-md-4 col-md-offset-8">
						<table>
							<h6>Price Details</h6>
							<tr>
								<td>Total</td>
								<td>350.00</td>
							</tr>
							<tr>
								<td>Discount</td>
								<td>-----</td>
							</tr>
							<tr>
								<td>Delivery Charges</td>
								<td>100.00</td>
							</tr>
							<tr style="border-top: 1px solid #333">
								<td><h5>TOTAL</h5></td>
								<td>400.00</td>
							</tr>
						</table>
						<center><a href="#" class="btn btn-1">Checkout</a></center>
					</div>
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
