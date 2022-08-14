<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    
    <%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>


<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header text-center">
			<h3 class="modal-title w-100">Update</h3>
		</div>
		<div class="modal-body">
			<form
				action="${pageContext.request.contextPath}/product/update/${product.getProId()}"
				method="post">
				
							<div class="form-group">
								<label for="name">Name:</label> 
								<input class="form-control" type="text" id="" name="proName" value="${product.getProName()}">
							</div>
							
							<div class="form-group">
								<label for="dob">Img</label> 
								<input class="form-control" type="text" id="" name="proImage" value="${product.getProImage()}">
							</div>
							

						<div class="form-group">
							<label for="dob">Brand:</label> <select class="form-control" name="brandId">
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
								<label for="dob">Product Details:</label> 
								<input class="form-control" type="text" id="" name="proDetail" value="${product.getProDetail()}">
							</div>
							
							<div class="form-group">
								<label for="dob">Price:</label> 
								<input class="form-control" type="number" id="" name="proPrice" value="${product.getProPrice()}">
							</div>
							
							<div class="form-group">
								<label for="dob">Code</label> 
								<input class="form-control" type="text" id="" name="proCode" value="${product.getProCode()}">
							</div>
							
							<div class="form-group">
								<label for="dob">Quantity</label> 
								<input class="form-control" type="text" id="" name="proQuantity" value="${product.getProQuantity()}">
							</div>
							
				<div class="modal-footer">
					<div class="container text-center">
						<button type="submit" class="btn btn-outline-success">Update</button>
						<button type="reset" class="btn btn-outline-warning">Reset</button>
						<a data-dismiss="modal" class="btn btn-outline-info">Close</a>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>