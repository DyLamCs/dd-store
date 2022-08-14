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
			<h2 class="text-center">Category</h2>
			<div class="container mb-3 btn-add">
				<a class="btn btn-outline-success" data-toggle="modal"
					data-target="#addStudentModal">Add brand</a> <a
					href="student/pdfview" class="btn btn-outline-info">Export to
					PDF</a>
			</div>

			<div class="modal fade" id="addStudentModal" role="dialog">
				<%@include file="/WEB-INF/views/category/add-category.jsp"%>
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
						<c:forEach items="${ category }" var="category" varStatus="status">
							<tr>
											<td>${category.getCtgId()}</td>
                                            <td>${category.getCtgName()}</td>
                                            
								<td>
									<div>
										<a class="btn btn-outline-info btn-action" data-toggle="modal" data-target="#updateStudentModal${ category.getCtgId() }">Update</a>
										<a data-toggle="modal" data-target="#deleteStudentModal${ category.getCtgId() }" class="btn btn-outline-danger btn-action">Delete</a>
									</div>
									<div class="modal fade" id="updateStudentModal${ category.getCtgId() }" role="dialog">
										<%@include file="/WEB-INF/views/category/update-category.jsp"%>
									</div>
									<div class="modal fade" id="deleteStudentModal${ category.getCtgId() }" role="dialog">
										<%@include file="/WEB-INF/views/category/delete-category.jsp"%>
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