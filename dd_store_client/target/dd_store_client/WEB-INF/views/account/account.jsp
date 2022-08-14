<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Insert title here</title>
<%@include file="/components/common_css_js.jsp"%>
</head>
<body>
	<div class="container">
		<div class="card mt-3">
			<h2 class="text-center">Student List</h2>
			<div class="container mb-3 btn-add">
				<a class="btn btn-outline-success" data-toggle="modal"
					data-target="#addStudentModal">Add Student</a> <a
					href="student/pdfview" class="btn btn-outline-info">Export to
					PDF</a>
			</div>

			<div class="modal fade" id="addStudentModal" role="dialog">
				<%@include file="/WEB-INF/views/account/add-account.jsp"%>
			</div>

			<div class="table-responsive">
				<table class="table table-hover text-center" border="1" >
					<thead>
						<tr>
											<th scope="col">Stt</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Phone</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">Address</th>
                                            <th scope="col">Role</th>
                                            <th scope="col">Action</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ account }" var="account" varStatus="status">
							<tr>
											<td>${account.getAccId()}</td>
                                            <td>${account.getAccName()}</td>
                                            <td>${account.getAccPhone()}</td>
                                            <td>${account.getAccEmail()}</td>
                                            <td>${account.getAccCity()},  ${account.getAccStreet()}</td>
                                            <td>${account.getAccRole()}</td>
								<td>
									<div>
										<a class="btn btn-outline-info btn-action" data-toggle="modal" data-target="#updateStudentModal${ account.getAccId() }">Update</a>
										<a data-toggle="modal" data-target="#deleteStudentModal${ account.getAccId() }" class="btn btn-outline-danger btn-action">Delete</a>
									</div>
									<div class="modal fade" id="updateStudentModal${ account.getAccId() }" role="dialog">
										<%@include file="/WEB-INF/views/account/update-account.jsp"%>
									</div>
									<div class="modal fade" id="deleteStudentModal${ account.getAccId() }" role="dialog">
										<%@include file="/WEB-INF/views/account/delete-account.jsp"%>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>


</body>
</html>