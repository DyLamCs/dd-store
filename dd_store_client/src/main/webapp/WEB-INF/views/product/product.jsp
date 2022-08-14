<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/layoutadmin/header.jsp"%>  
	<!-- table -->
		<div class="page-wrapper">
			<div class="page-breadcrumb bg-white">
				<div class="row align-items-center">
					<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
						<h4 class="page-title">Product manager</h4>
					</div>
					<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
						<div class="d-md-flex">
							<ol class="breadcrumb ms-auto">
								<li><a href="#" class="fw-normal"
									style="margin-right: 10px">Dashboard</a></li>
							</ol>
<!--buttom Create Acccount -->
							<button type="button" class="btn btn-success" data-toggle="modal"
							data-target="#addStudentModal">Create Account</button>
							
<!--chuyển qua modal add-account.jsp -->
							<div class="modal fade" id="addStudentModal" role="dialog">
								<%@include file="/WEB-INF/views/product/add-product.jsp"%>
							</div>
							
							</div>
					</div>
				</div>
				<!-- /.col-lg-12 -->
			</div>


			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-12">
						<div class="white-box">

							<div class="table-responsive">
								<table class="table text-nowrap">
									<thead>
										<tr>
											<th class="border-top-0">Stt</th>
											<th class="border-top-0">Name</th>
											<th class="border-top-0">Image</th>
											<th class="border-top-0">Brand</th>
											<th class="border-top-0">Category</th>
											<th class="border-top-0">Price</th>
											<th class="border-top-0">Code</th>
											<th class="border-top-0">Quantity</th>
											<th class="border-top-0">Action</th>
										</tr>
									</thead>
									<tbody id="myTable">
										<c:forEach items="${product}" var="product" varStatus="status">
											<tr>
												<td>${status.index+1}</td>
												<td>${product.getProName()}</td>
												<td><img id="image-1" alt=""
													src="${product.getProImage()} "
													style="width: 100px;" /></td>
												<td>${product.getBrand().getBraName()}</td>
												<td>${product.getCategory().getCtgName()}</td>
												<td>${product.getProPrice()}</td>
												<td>${product.getProCode()}</td>
												<td>${product.getProQuantity()}</td>

												<td>
													<div>
														<div>
															<a class="btn btn-outline-info btn-action"
																data-toggle="modal"
																data-target="#updateProductModal${product.getProId()}">Update</a>
															
															<a data-toggle="modal"
																data-target="#deleteProductModal${product.getProId()}"
																class="btn btn-outline-danger btn-action">Delete</a>
														</div>
														<div class="modal fade"
															id="updateProductModal${product.getProId()}"
															role="dialog">
															<%@include file="/WEB-INF/views/product/update-product.jsp"%>
														</div>
														<div class="modal fade"
															id="deleteProductModal${product.getProId()}"
															role="dialog">
															<%@include file="/WEB-INF/views/product/delete-product.jsp"%>
														</div>
													</div>
												</td>
											</tr>

										</c:forEach>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>




			<footer class="footer text-center">
				2022 Â© DD.STORE <a href="https://www.fb.com/">ddstore.com</a>
			</footer>

		</div>
		<!-- and table -->

	</div>
</body>
</html>
