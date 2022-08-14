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
			<h2 class="text-center">brand</h2>
			<div class="container mb-3 btn-add">
				<a class="btn btn-outline-success" data-toggle="modal"
					data-target="#addStudentModal">Add brand</a> <a
					href="student/pdfview" class="btn btn-outline-info">Export to
					PDF</a>
			</div>

			<div class="modal fade" id="addStudentModal" role="dialog">
				<%@include file="/WEB-INF/views/brand/add-brand.jsp"%>
			</div>

			<div class="table-responsive">
				<table class="table table-hover text-center" border="1" >
					<thead>
						<tr>
											<th scope="col">Stt</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Action</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ brand }" var="brand" varStatus="status">
							<tr>
											<td>${brand.getBraId()}</td>
                                            <td>${brand.getBraName()}</td>
                                            
								<td>
									<div>
										<a class="btn btn-outline-info btn-action" data-toggle="modal" data-target="#updateStudentModal${ brand.getBraId() }">Update</a>
										<a data-toggle="modal" data-target="#deleteStudentModal${ brand.getBraId() }" class="btn btn-outline-danger btn-action">Delete</a>
									</div>
									<div class="modal fade" id="updateStudentModal${ brand.getBraId() }" role="dialog">
										<%@include file="/WEB-INF/views/brand/update-brand.jsp"%>
									</div>
									<div class="modal fade" id="deleteStudentModal${ brand.getBraId() }" role="dialog">
										<%@include file="/WEB-INF/views/brand/delete-brand.jsp"%>
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