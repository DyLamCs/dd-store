<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header text-center">
			<h3 class="modal-title w-100">Update brand</h3>
		</div>
		<div class="modal-body">
			<form
				action="${pageContext.request.contextPath}/brand/update/${ brand.getBraId()}"
				method="post">
				
				<div class="form-group">
								<label for="name">Name:</label> 
								<input class="form-control" type="text" id="" name="braName" value="${brand.getBraName()}">
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
