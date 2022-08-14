
<!-- HEADER -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header id="het">
	<!-- TOP HEADER -->
	<div id="top-header">
		<div class="container">
			<ul class="header-links pull-left">
				<li><a href="#"><i class="fa fa-phone"></i> 0825421124 </a></li>
				<li><a href="#"><i class="fa fa-envelope-o"></i>
						ddstore@gmail.com</a></li>
				<li><a href="#"><i class="fa fa-map-marker"></i> CanTho</a></li>
			</ul>
			<ul class="header-links pull-right">
				
				<c:choose>
					<c:when test="${sessionScope.account!=null}">
						<li style="color: white;">Hello: ${sessionScope.account.accName}</li>
					</c:when>
					<c:otherwise>
					<li><a href="login"><i class="fa fa-user"></i> Login</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="logout"><i class="fa fa-user"></i>Logout</a></li>
			</ul>
		</div>
	</div>
	<!-- /TOP HEADER -->

	<!-- MAIN HEADER -->
	<div id="header">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- LOGO -->
				<div class="col-md-3">
					<div class="header-logo">
						<a href="#" class="logo"> <img alt=""
							src="./templates/home/img/ddstore1.png">
						</a>
					</div>
				</div>
				<!-- /LOGO -->

				<!-- SEARCH BAR -->
				<div class="col-md-6">
					<div class="header-search">
						<form>
							<select class="input-select">
								<option value="0">All Categories</option>
								<option value="1">Category 01</option>
								<option value="1">Category 02</option>
							</select> <input class="input" placeholder="Search here">
							<button class="search-btn">Search</button>
						</form>
					</div>
				</div>
				<!-- /SEARCH BAR -->

				<!-- ACCOUNT -->
				<div class="col-md-3 clearfix">
					<div class="header-ctn">
						<!-- Wishlist -->
						<div>
							<a href="#"> <i class="fa fa-heart-o"></i> <span>Your
									Wishlist</span> <span class="qty">2</span>
							</a>
						</div>
						<!-- /Wishlist -->

						<!-- Cart -->
						<div class="dropdown">
							<a href="cart" class="dropdown-toggle"> <i href="cart"
								class="fa fa-shopping-cart"></i> <span>Your Cart</span>
							</a>

						</div>
						<!-- /Cart -->

						<!-- Menu Toogle -->
						<div class="menu-toggle">
							<a href="#"> <i class="fa fa-bars"></i> <span>Menu</span>
							</a>
						</div>
						<!-- /Menu Toogle -->
					</div>
				</div>
				<!-- /ACCOUNT -->
			</div>
			<!-- row -->
		</div>
		<!-- container -->
	</div>
	<!-- /MAIN HEADER -->
</header>

<!-- /HEADER -->
<!-- NAVIGATION -->
<nav style="margin-top: 20px;" id="navigation">
	<!-- container -->
	<div class="container">
		<!-- responsive-nav -->
		<div id="responsive-nav">
			<!-- NAV -->
			<ul class="main-nav nav navbar-nav">
				
				<li><a
					href="${pageContext.servletContext.contextPath}/productshow">Product</a></li>
				<li><a href="#">Contact</a></li>
			</ul>
			<!-- /NAV -->
		</div>
		<!-- /responsive-nav -->
	</div>
	<!-- /container -->
</nav>
<!-- /NAVIGATION -->
