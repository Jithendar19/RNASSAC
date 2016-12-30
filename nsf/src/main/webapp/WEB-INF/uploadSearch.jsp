<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE web-app PUBLIC
  "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
   "http://java.sun.com/dtd/web-app_2_3.dtd" >

<%@ page session="false"%>



<html>
<head>
<meta charset="UTF-8">
<title>NSF RNA SSAC</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css"
	type="text/css">
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/images/logo.png">
</head>
<body>
	<div id="header">
		<a href="<c:url value="/"/>" class="logo"><img
			src="${pageContext.request.contextPath}/resources/images/logo.png"
			alt=""></a>
		<ul>
			<li><a href="<c:url value="/"/>">home</a></li>
			<li class="selected"><a href="#">about</a></li>
			<li><a href="<c:url value="/upload"/>">RegularExp Search</a></li>
			<li><a href="#">Our Teams</a></li>
			<li><a href="<c:url value="/uploadfile"/>">SuffixArraySearch</a>
			</li>
			<li class="nav-item"><a href="<c:url value="/comparison"/>">Comparison</a></li>

		</ul>
	</div>

	<div align="left">
		<h1>${msg}</h1>

		<table>
			<form method="POST" enctype="multipart/form-data"
				action="/nsf/RNASearch">

				File to upload: <input type="file" name="file"><br /> Name:
				<input type="text" name="name"><br /> <br /> <input
					type="submit" value="Search"> Press here to upload the
					file! 
			</form>
		</table>
	</div>
</body>
</html>
