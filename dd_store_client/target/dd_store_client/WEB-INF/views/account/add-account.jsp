<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header text-center">
			<h3 class="modal-title w-100">Add Account</h3>
		</div>
		<div class="modal-body">
			<form id="add-from" action="${pageContext.request.contextPath}/account/add" method="post">
							<div class="form-group">
								<label for="name">Name:</label> 
								<input class="form-control" type="text" id="" name="accName">
							</div>
							
							<div class="form-group">
								<label for="dob">Password</label> 
								<input class="form-control" type="password" id="" name="accPassword">
							</div>
							
							<div class="form-group">
								<label for="dob">Role</label> 
								<input class="form-control" type="text" id="" name="accRole">
							</div>
							
							<div class="form-group">
								<label for="age">Phone:</label> 
								<input class="form-control" type="number" id="" name="accPhone">
							</div>
							
							<div class="form-group">
								<label for="dob">Email:</label> 
								<input class="form-control" type="email" id="" name="accEmail">
							</div>
							
							<div class="form-group">
								<label for="dob">Street</label> 
								<input class="form-control" type="text" id="" name="accStreet">
							</div>
							
							<div class="form-group">
								<label for="dob">City</label> 
								<input class="form-control" type="text" id="" name="accCity" >
							</div>

							<div class="container text-center">
								<button type="submit" class="btn btn-outline-success">Create</button>
								<button type="reset" class="btn btn-outline-warning">Reset</button>
							</div>
						</form>
		</div>
	</div>
</div>
