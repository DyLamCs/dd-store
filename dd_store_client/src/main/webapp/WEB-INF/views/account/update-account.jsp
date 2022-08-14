<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header text-center">
			<h3 class="modal-title w-100">Update Student</h3>
		</div>
		<div class="modal-body">
			<form
				action="${pageContext.request.contextPath}/account/update/${ account.getAccId()}"
				method="post">
				
				<div class="form-group">
								<label for="name">Name:</label> 
								<input class="form-control" type="text" id="" name="accName" value="${account.getAccName()}">
							</div>
							
<!-- 							<div class="form-group"> -->
<!-- 								<label for="dob">Password</label>  -->
<%-- 								<input class="form-control" type="password" id="" name="accPassword" value="${account.getAccPassword()}"> --%>
<!-- 							</div> -->
							
							<div class="form-group">
								<label for="dob">Role</label> 
								<input class="form-control" type="text" id="" name="accRole" value="${account.getAccRole()}">
							</div>
							
							<div class="form-group">
								<label for="age">Phone:</label> 
								<input class="form-control" type="number" id="" name="accPhone" value="${account.getAccPhone()}">
							</div>
							
							<div class="form-group">
								<label for="dob">Email:</label> 
								<input class="form-control" type="email" id="" name="accEmail" value="${account.getAccEmail()}">
							</div>
							
							<div class="form-group">
								<label for="dob">Street</label> 
								<input class="form-control" type="text" id="" name="accStreet" value="${account.getAccStreet()}">
							</div>
							
							<div class="form-group">
								<label for="dob">City</label> 
								<input class="form-control" type="text" id="" name="accCity" value="${account.getAccCity()}">
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
