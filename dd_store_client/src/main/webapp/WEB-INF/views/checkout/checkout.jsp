<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Checkout</title>
<%@ include file="/templates/layoutclient/head.jsp"%>
</head>
<body>
	<%@ include file="/templates/layoutclient/header.jsp"%>

	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container" style="margin-top: 250px;">
			<!-- row -->
			<div class="row">

				<div class="col-md-7">
					<!-- Billing Details -->
					<div class="billing-details">
						<div class="section-title">
							<h3 class="title">THÔNG TIN ĐẶT HÀNG</h3>
						</div>
						<div class="form-group">
							<input class="input" type="text" name="first-name"
								placeholder="First Name">
						</div>
						<div class="form-group">
							<input class="input" type="text" name="last-name"
								placeholder="Last Name">
						</div>
						<div class="form-group">
							<input class="input" type="email" name="email"
								placeholder="Email">
						</div>
						<div class="form-group">
							<input class="input" type="text" name="address"
								placeholder="Address">
						</div>
						<div class="form-group">
							<input class="input" type="text" name="city" placeholder="City">
						</div>
						<div class="form-group">
							<input class="input" type="text" name="country"
								placeholder="Country">
						</div>
						<div class="form-group">
							<input class="input" type="text" name="zip-code"
								placeholder="ZIP Code">
						</div>
						<div class="form-group">
							<input class="input" type="tel" name="tel"
								placeholder="Telephone">
						</div>
						
					</div>
					<!-- /Billing Details -->


					<!-- Order notes -->
					<div class="order-notes">
						<textarea class="input" placeholder="Order Notes"></textarea>
					</div>
					<!-- /Order notes -->
				</div>

				<!-- Order Details -->

				<div class="col-md-5 order-details">
					<div class="section-title text-center">
						<h3 class="title">ĐƠN ĐẶT HÀNG</h3>
					</div>
					<div class="order-summary">
						<div class="order-col">
							<div>
								<strong>SẢN PHẨM</strong>
							</div>
							<div>
								<strong>GIÁ</strong>
							</div>
						</div>
						<c:forEach items="${sessionScope.myCartModel}" var="cart">
							<div class="order-products">
								<div class="order-col">
									<div>x ${cart.value.quantiti}: ${ cart.value.productmodel.proName}</div>
									<div>
										<fmt:setLocale value="en_US" />
										<fmt:formatNumber value="${ cart.value.productmodel.proPrice}"
											type="currency" />
									</div>
								</div>

							</div>
						</c:forEach>

						<div class="order-col">
							<div>
								<strong>TỔNG TIỀN</strong>
							</div>
							<div>
								<b style="color: red"><fmt:setLocale value="en_US" />
									<fmt:formatNumber value="${ sessionScope.total }"
										type="currency" /></b>
							</div>
						</div>

					</div>
					<div class="payment-method">
						<div class="input-radio"></div>
						<div class="input-radio"></div>
						<div class="input-radio"></div>
					</div>
					<div class="input-checkbox">
						<input type="checkbox" id="terms"> <label for="terms">
							<span></span> Bạn có chắc muốn đặt hàng.
						</label>
					</div>
					<a href="#" class="primary-btn order-submit">ĐẶT HÀNG</a>
				</div>

				<!-- /Order Details -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /SECTION -->
	<%@ include file="/templates/layoutclient/footer.jsp"%>
</body>
</html>


