<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="width=device-width" />
<title>今日就诊</title>
<script src="${path}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
	<script>
	$(function(){
		window.location = "/enjoyhisfy/client/register/register_index2.htm";
	})
	</script>
</body>
</html>