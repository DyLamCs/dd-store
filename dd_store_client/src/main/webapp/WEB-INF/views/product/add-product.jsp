<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header text-center">
			<h3 class="modal-title w-100">Add Account</h3>
		</div>
		<div class="modal-body">
			<form id="add-from" action="${pageContext.request.contextPath}/product/add"
						method="post">
						<div class="form-group">
							<label for="name">Name:</label> <input class="form-control"
								type="text" id="" name="proName">
						</div>
						
					
						

						<div class="form-group">
							<label for="dob">Brand:</label> 
							<select name="brandId" class="form-control">
								<c:forEach items="${brand}" var="brand" varStatus="status">
									<option value="${brand.getBraId()}">
										${brand.getBraName()}</option>
								</c:forEach>
							</select>

						</div>
						

						<div class="form-group">
							<label for="dob">Categoty:</label> <select class="form-control" name="categoryId">
								<c:forEach items="${category}" var="category" varStatus="status">
									<option value="${category.getCtgId() }">
										${category.getCtgName() }</option>
								</c:forEach>
							</select>
						</div>
						
						<div class="form-group">
							<label for="name">Image:</label> <input class="form-control"
								type="text" id="" name="proImage">
						</div>

						<div class="form-group">
							<label for="name">Product Details:</label> <input
								class="form-control" type="text" id="" name="proDetail">
						</div>

						<div class="form-group">
							<label for="dob">Price:</label> <input class="form-control"
								type="text" id="" name="proPrice">
						</div>

						<div class="form-group">
							<label for="dob">Code:</label> <input class="form-control"
								type="text" id="" name="proCode">
						</div>

						<div class="form-group">
							<label for="dob">Quantity:</label> <input class="form-control"
								type="number" id="" name="proQuantity">
						</div>

						<div class="container text-center">
							<button type="submit" class="btn btn-outline-success">Create</button>
							<button type="reset" class="btn btn-outline-warning">Reset</button>
						</div>
					</form>
		</div>
	</div>
</div>


