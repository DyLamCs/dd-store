<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header text-center">
			<h3 class="modal-title w-100">Delete Student</h3>
		</div>
		<div class="modal-body">
			<h4>Bạn có chắc muốn xóa sinh viên <br> ${account.getAccId()} -
				${account.getAccName() }</h4>
		</div>
		<div class="modal-footer">
			<div class="container text-center">
				<a href="account/delete/${ account.accId}" class="btn btn-outline-success">Yes</a>
				<a data-dismiss="modal" class="btn btn-outline-info">No</a>
			</div>
		</div>
	</div>
</div>