<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header text-center">
			<h3 class="modal-title w-100">Add brand</h3>
		</div>
		<div class="modal-body">
			<form id="add-from" action="${pageContext.request.contextPath}/brand/add" method="post">
							<div class="form-group">
								<label for="id">Id:</label> 
								
							</div>
							<div class="form-group">
								<label for="braName">Name:</label> 
								<input class="form-control" type="text" id="" name="braName">
							</div>

							<div class="container text-center">
								<button type="submit" class="btn btn-outline-success">Create</button>
								<button type="reset" class="btn btn-outline-warning">Reset</button>
							</div>
						</form>
		</div>
	</div>
</div>
