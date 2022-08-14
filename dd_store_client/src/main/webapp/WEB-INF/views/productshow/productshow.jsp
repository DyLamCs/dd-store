<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Product</title>
<%@ include file="/components/common_css_js_home.jsp"%>
<%@ include file="/templates/layoutclient/head.jsp"%>
<!-- Font Awesome Icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
	
	<style type="text/css">
#het {
	width: 100%;
	position: fixed;
	z-index: 5000;
}

#navigation {
	width: 100%;
	position: fixed;
	z-index: 5000;
	top: 120px;
}
</style>
</head>
<body>
	<%@ include file="/templates/layoutclient/header.jsp"%>

	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- ASIDE -->
				<div id="aside" class="col-md-3">
					<!-- aside Widget -->
					<div class="aside" style="margin-top: 200px;">
						<h3 class="aside-title">Categories</h3>

						<c:forEach items="${category}" var="category">
							<div class="checkbox-filter">

								<div class="input-checkbox">
									<input type="checkbox" id="category-1"> <label
										for="category-1"> <span></span>
										${category.getCtgName()}
									</label>
								</div>

							</div>
						</c:forEach>
					</div>
					<!-- /aside Widget -->


					<!-- aside Widget -->
					<div class="aside">
						<h3 class="aside-title">Brand</h3>
						<c:forEach items="${brand}" var="brand">
							<div class="checkbox-filter">
								<div class="input-checkbox">
									<input type="checkbox" id="brand-1"> <label
										for="brand-1"> <span></span> ${brand.getBraName()}
									</label>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- /aside Widget -->


				</div>
				<!-- /ASIDE -->

				<!-- STORE -->
				<div id="store" class="col-md-9" style="margin-top: 180px;">
					<!-- store top filter -->
					<div class="store-filter clearfix">
						<div class="store-sort">
							<label> Sort By: <select class="input-select">
									<option value="0">Popular</option>
									<option value="1">Position</option>
							</select>
							</label> <label> Show: <select class="input-select">
									<option value="0">20</option>
									<option value="1">50</option>
							</select>
							</label>
						</div>
						<ul class="store-grid">
							<li class="active"><i class="fa fa-th"></i></li>
							<li><a href="#"><i class="fa fa-th-list"></i></a></li>
						</ul>
					</div>
					<!-- /store top filter -->

					<!-- store products  -->
					<div class="row">
						<c:forEach items="${product}" var="product">
							<!-- product 2 -->
							
							<div class="col-md-4 col-xs-6">
								
								<div class="product">
								<a href="productshow/productdetail/${product.getProId()}">
									<div class="product-img" >
										<img id="image-1" src="${product.getProImage()}" />
									</div>
									</a>
									<div class="product-body">
										<p class="product-category">${product.getCategory().getCtgName()}</p>
										<h3 class="product-name">
											<a href="#">${product.getProName()}</a>
										</h3>
										<h4 class="product-price">
										<fmt:setLocale value="en_US" />
										<fmt:formatNumber value="${product.getProPrice()}" type="currency" />
										</h4>
									</div>
									<div class="add-to-cart">
										<a href="/dd_store_client/cart/add/${product.getProId() }"><button class="add-to-cart-btn">
											<i class="fa fa-shopping-cart"></i> add to cart

										</button></a>
									</div>
								</div>
							</div>
						</c:forEach>
						<!-- /product -->
					</div>






					<!-- /store products -->



					<!-- store bottom filter -->
					<div class="store-filter clearfix">
						<span class="store-qty">Showing 20-100 products</span>
						<ul class="store-pagination">
							<li class="active">1</li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#"><i class="fa fa-angle-right"></i></a></li>
						</ul>
					</div>
					<!-- /store bottom filter -->
				</div>
				<!-- /STORE -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /SECTION -->



	<!-- NEWSLETTER -->
	<div id="newsletter" class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-12">
					<div class="newsletter">
						<p>
							Sign Up for the <strong>NEWSLETTER</strong>
						</p>
						<form>
							<input class="input" type="email" placeholder="Enter Your Email">
							<button class="newsletter-btn">
								<i class="fa fa-envelope"></i> Subscribe
							</button>
						</form>
						<ul class="newsletter-follow">
							<li><a href="#"><i class="fa fa-facebook"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#"><i class="fa fa-instagram"></i></a></li>
							<li><a href="#"><i class="fa fa-pinterest"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /NEWSLETTER -->


	<%@ include file="/templates/layoutclient/footer.jsp"%>
</body>
</html>