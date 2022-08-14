<%@ include file="/templates/layoutadmin/header.jsp"%>
<!-- table -->
<div class="page-wrapper">
	<div class="page-breadcrumb bg-white">
		<div class="row align-items-center">
			<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				<h4 class="page-title">Account Manager</h4>
			</div>
			<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				<div class="d-md-flex">
					<ol class="breadcrumb ms-auto">
						<li><a href="#" class="fw-normal" style="margin-right: 10px">Dashboard</a></li>
					</ol>
					<!--buttom Create Acccount -->
					<button type="button" class="btn btn-success" data-toggle="modal"
						data-target="#addStudentModal">Create Account</button>

					<!--chuyển qua modal add-account.jsp -->
					<div class="modal fade" id="addStudentModal" role="dialog">
						<%@include file="/WEB-INF/views/account/add-account.jsp"%>
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
									<th class="border-top-0">Phone</th>
									<th class="border-top-0">Email</th>
									<th class="border-top-0">Address</th>
									<th class="border-top-0">Role</th>
									<th class="border-top-0">ACtion</th>
								</tr>
							</thead>
							<tbody id="myTable">
								<c:forEach items="${ account }" var="account" varStatus="status">
									<tr>
										<td>${status.index+1}</td>
										<td>${account.getAccName()}</td>
										<td>${account.getAccPhone()}</td>
										<td>${account.getAccEmail()}</td>
										<td>${account.getAccCity()},${account.getAccStreet()}</td>
										<td>${account.getAccRole()}</td>
										<td>
											<div>
												<div>
													<a class="btn btn-outline-info btn-action" data-toggle="modal" data-target="#updateStudentModal${ account.getAccId() }">Update</a>
													<a data-toggle="modal" data-target="#deleteStudentModal${ account.getAccId() }" class="btn btn-outline-danger btn-action">Delete</a>
												</div>
												<div class="modal fade" id="updateStudentModal${ account.getAccId() }" role="dialog">
													<%@include file="/WEB-INF/views/account/update-account.jsp"%>
												</div>

												<div class="modal fade"
													id="deleteStudentModal${ account.getAccId() }"
													role="dialog">
													<%@include file="/WEB-INF/views/account/delete-account.jsp"%>
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
