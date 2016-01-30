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
	<!--///////////////////Category Page//////////////////-->
	<!--//////////////////////////////////////////////////-->
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ul class="breadcrumb">
						<li><a href="index.html">Home</a></li>
						<li><a href="category.html">Category : Mobile</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div id="main-content" class="col-md-8">
					<div class="row">
						<div class="col-md-12">
							<div class="products">
								<div class="col-lg-4 col-md-4 col-xs-12">
									<div class="product">
										<div class="image"><a href="product.html"><img src="${base}/static/assets/images/iphone.png" /></a></div>
										<div class="buttons">
											<a class="btn cart" href="#"><span class="glyphicon glyphicon-shopping-cart"></span></a>
											<a class="btn wishlist" href="#"><span class="glyphicon glyphicon-heart"></span></a>
											<a class="btn compare" href="#"><span class="glyphicon glyphicon-transfer"></span></a>
										</div>
										<div class="caption">
											<div class="name"><h3><a href="product.html">Aliquam erat volutpat</a></h3></div>
											<div class="price">$122<span>$98</span></div>
											<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span></div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-xs-12">
									<div class="product">
										<div class="image"><a href="product.html"><img src="${base}/static/assets/images/galaxy-s4.jpg" /></a></div>
										<div class="buttons">
											<a class="btn cart" href="#"><span class="glyphicon glyphicon-shopping-cart"></span></a>
											<a class="btn wishlist" href="#"><span class="glyphicon glyphicon-heart"></span></a>
											<a class="btn compare" href="#"><span class="glyphicon glyphicon-transfer"></span></a>
										</div>
										<div class="caption">
											<div class="name"><h3><a href="product.html">Aliquam erat volutpat</a></h3></div>
											<div class="price">$122<span>$98</span></div>
											<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span><span class="glyphicon glyphicon-star-empty"></span></div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-xs-12">
									<div class="product">
										<div class="image"><a href="product.html"><img src="${base}/static/assets/images/galaxy-note.jpg" /></a></div>
										<div class="buttons">
											<a class="btn cart" href="#"><span class="glyphicon glyphicon-shopping-cart"></span></a>
											<a class="btn wishlist" href="#"><span class="glyphicon glyphicon-heart"></span></a>
											<a class="btn compare" href="#"><span class="glyphicon glyphicon-transfer"></span></a>
										</div>
										<div class="caption">
											<div class="name"><h3><a href="product.html">Aliquam erat volutpat</a></h3></div>
											<div class="price">$122<span>$98</span></div>
											<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span><span class="glyphicon glyphicon-star-empty"></span><span class="glyphicon glyphicon-star-empty"></span></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="products">
								<div class="col-lg-4 col-md-4 col-xs-12">
									<div class="product">
										<div class="image"><a href="product.html"><img src="${base}/static/assets/images/iphone.png" /></a></div>
										<div class="buttons">
											<a class="btn cart" href="#"><span class="glyphicon glyphicon-shopping-cart"></span></a>
											<a class="btn wishlist" href="#"><span class="glyphicon glyphicon-heart"></span></a>
											<a class="btn compare" href="#"><span class="glyphicon glyphicon-transfer"></span></a>
										</div>
										<div class="caption">
											<div class="name"><h3><a href="product.html">Aliquam erat volutpat</a></h3></div>
											<div class="price">$122<span>$98</span></div>
											<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span></div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-xs-12">
									<div class="product">
										<div class="image"><a href="product.html"><img src="${base}/static/assets/images/galaxy-s4.jpg" /></a></div>
										<div class="buttons">
											<a class="btn cart" href="#"><span class="glyphicon glyphicon-shopping-cart"></span></a>
											<a class="btn wishlist" href="#"><span class="glyphicon glyphicon-heart"></span></a>
											<a class="btn compare" href="#"><span class="glyphicon glyphicon-transfer"></span></a>
										</div>
										<div class="caption">
											<div class="name"><h3><a href="product.html">Aliquam erat volutpat</a></h3></div>
											<div class="price">$122<span>$98</span></div>
											<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span><span class="glyphicon glyphicon-star-empty"></span></div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-xs-12">
									<div class="product">
										<div class="image"><a href="product.html"><img src="${base}/static/assets/images/galaxy-note.jpg" /></a></div>
										<div class="buttons">
											<a class="btn cart" href="#"><span class="glyphicon glyphicon-shopping-cart"></span></a>
											<a class="btn wishlist" href="#"><span class="glyphicon glyphicon-heart"></span></a>
											<a class="btn compare" href="#"><span class="glyphicon glyphicon-transfer"></span></a>
										</div>
										<div class="caption">
											<div class="name"><h3><a href="product.html">Aliquam erat volutpat</a></h3></div>
											<div class="price">$122<span>$98</span></div>
											<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span><span class="glyphicon glyphicon-star-empty"></span><span class="glyphicon glyphicon-star-empty"></span></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="products">
								<div class="col-lg-4 col-md-4 col-xs-12">
									<div class="product">
										<div class="image"><a href="product.html"><img src="${base}/static/assets/images/iphone.png" /></a></div>
										<div class="buttons">
											<a class="btn cart" href="#"><span class="glyphicon glyphicon-shopping-cart"></span></a>
											<a class="btn wishlist" href="#"><span class="glyphicon glyphicon-heart"></span></a>
											<a class="btn compare" href="#"><span class="glyphicon glyphicon-transfer"></span></a>
										</div>
										<div class="caption">
											<div class="name"><h3><a href="product.html">Aliquam erat volutpat</a></h3></div>
											<div class="price">$122<span>$98</span></div>
											<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span></div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-xs-12">
									<div class="product">
										<div class="image"><a href="product.html"><img src="${base}/static/assets/images/galaxy-s4.jpg" /></a></div>
										<div class="buttons">
											<a class="btn cart" href="#"><span class="glyphicon glyphicon-shopping-cart"></span></a>
											<a class="btn wishlist" href="#"><span class="glyphicon glyphicon-heart"></span></a>
											<a class="btn compare" href="#"><span class="glyphicon glyphicon-transfer"></span></a>
										</div>
										<div class="caption">
											<div class="name"><h3><a href="product.html">Aliquam erat volutpat</a></h3></div>
											<div class="price">$122<span>$98</span></div>
											<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span><span class="glyphicon glyphicon-star-empty"></span></div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-xs-12">
									<div class="product">
										<div class="image"><a href="product.html"><img src="${base}/static/assets/images/galaxy-note.jpg" /></a></div>
										<div class="buttons">
											<a class="btn cart" href="#"><span class="glyphicon glyphicon-shopping-cart"></span></a>
											<a class="btn wishlist" href="#"><span class="glyphicon glyphicon-heart"></span></a>
											<a class="btn compare" href="#"><span class="glyphicon glyphicon-transfer"></span></a>
										</div>
										<div class="caption">
											<div class="name"><h3><a href="product.html">Aliquam erat volutpat</a></h3></div>
											<div class="price">$122<span>$98</span></div>
											<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span><span class="glyphicon glyphicon-star-empty"></span><span class="glyphicon glyphicon-star-empty"></span></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row text-center">
						<ul class="pagination">
						  <li class="active"><a href="#">1</a></li>
						  <li><a href="#">2</a></li>
						  <li><a href="#">3</a></li>
						  <li><a href="#">4</a></li>
						  <li><a href="#">5</a></li>
						</ul>
					</div>
				</div>
				<div id="sidebar" class="col-md-4">
					<div class="widget wid-categories">
						<div class="heading"><h4>CATEGORIES</h4></div>
						<div class="content">
							<ul>
								<li><a href="#">PC Computers</a></li>
								<li><a href="#">Laptops & Notebooks</a></li>
								<li><a href="#">Mobiles & Tablet</a></li>
								<li><a href="#">Software</a></li>
							</ul>
						</div>
					</div>
					<div class="widget wid-type">
						<div class="heading"><h4>TYPE</h4></div>
						<div class="content">
							<select>
								<option value="EL" selected>Electronics</option>
								<option value="MT">Mice and Trackballs</option>
								<option value="WC">Web Cameras</option>
								<option value="TA">Tablates</option>
								<option value="AP">Audio Parts</option>
							</select>
						</div>
					</div>
					<div class="widget wid-discouts">
						<div class="heading"><h4>DISCOUNTS</h4></div>
						<div class="content">
							<label class="checkbox"><input type="checkbox" name="discount" checked="">Upto - 10% (20)</label>
							<label class="checkbox"><input type="checkbox" name="discount">40% - 50% (5)</label>
							<label class="checkbox"><input type="checkbox" name="discount">30% - 20% (7)</label>
							<label class="checkbox"><input type="checkbox" name="discount">10% - 5% (2)</label>
							<label class="checkbox"><input type="checkbox" name="discount">Other(50)</label>
						</div>
					</div>
					<div class="widget wid-brand">
						<div class="heading"><h4>BRAND</h4></div>
						<div class="content">
							<label class="checkbox"><input type="checkbox" name="brand">Nokia</label>
							<label class="checkbox"><input type="checkbox" name="brand">Samsung</label>
							<label class="checkbox"><input type="checkbox" name="brand">Iphone</label>
							<label class="checkbox"><input type="checkbox" name="brand">HTC</label>
							<label class="checkbox"><input type="checkbox" name="brand">Oppo</label>
							<label class="checkbox"><input type="checkbox" name="brand">Kings</label>
							<label class="checkbox"><input type="checkbox" name="brand">Zumba</label>	
						</div>
					</div>
					<div class="widget wid-product">
						<div class="heading"><h4>LATEST</h4></div>
						<div class="content">
							<div class="product">
								<a href="#"><img src="${base}/static/assets/images/galaxy-note.jpg" /></a>
								<div class="wrapper">
									<h5><a href="#">Samsung Galaxy Tab</a></h5>
									<div class="price">$122</div>
									<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span></div>
								</div>
							</div>
							<div class="product">
								<a href="#"><img src="${base}/static/assets/images/galaxy-s4.jpg" /></a>
								<div class="wrapper">
									<h5><a href="#">Samsung Galaxy Tab</a></h5>
									<div class="price">$122</div>
									<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span></div>
								</div>
							</div>
							<div class="product">
								<a href="#"><img src="${base}/static/assets/images/Z1.png" /></a>
								<div class="wrapper">
									<h5><a href="#">Samsung Galaxy Tab</a></h5>
									<div class="price">$122</div>
									<div class="rating"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star-empty"></span></div>
								</div>
							</div>
						</div>
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
