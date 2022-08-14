<!-- - components/common_css_js.jsp là nơi khai báo jstl, cdn bootstrap, đường link css, js -->
<!-- dùng chung cho toàn project -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/templates/css/style.css'/> "/> --%>
<style><%@include file="/templates/style.css"%></style>