<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE web-app PUBLIC
  "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
   "http://java.sun.com/dtd/web-app_2_3.dtd" >

<%@ page session="false"%>



<html>
<head>
<meta charset="UTF-8">
<title>RNA SSAC | Search</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css"
	type="text/css">
	<link rel="icon" href="${pageContext.request.contextPath}/resources/images/logo.png">
</head>
<body>
	<ul class="navigation">
		<li class="nav-item"><a href="<c:url value="/"/>">Home</a></li>
		<li class="nav-item"><a href="<c:url value="/contact"/>">Contact
				Us</a></li>
		<li class="nav-item"><a href="<c:url value="/about"/>">About
				Us</a></li>
		<li class="nav-item"><a href="<c:url value="/team"/>">Team
				Members</a></li>
		<li class="nav-item"><a href="<c:url value="/upload"/>">Search</a></li>
		<li class="nav-item"><a href="<c:url value="/comparison"/>">Comparison</a></li>
	</ul>

	<input type="checkbox" id="nav-trigger" class="nav-trigger" />
	<label for="nav-trigger"></label>

	<div class="site-wrap">
		<!-- insert the rest of your page markup here -->

		<div id="header">
			<a href="<c:url value="/"/>"><img class="logo"
				src="${pageContext.request.contextPath}/resources/images/logo.png"
				alt=""></a>
		</div>
		<div id="featured" align="center">
			<div>
				<div class="article">
				<h2>Suffix Search</h2>
					<p>This page is used to search the existing database of 4666 RNA structures provided by 
						<a href="http://www.rnasoft.ca/strand/">RNASOFT</a>. To use this page you need to 
						have a file in BPSeq format that contains the pattern and/or structure you are 
						searching for. Once you have the file located on your computer use the file 
						upload form below. The file will be converted into our <strong>R</strong>elative 
						<strong>A</strong>ddressing <strong>B</strong>ased representation (file extension .rab).</p>
				<h2>
					<font size="3" color="red">${msg}</font>
				</h2>

				<c:set var="now" value="<%=new java.util.Date()%>" />

				<p>
					<fmt:formatDate type="date" pattern="yyyy-MM-dd_HH-mm-ss"
						value="${now}" var="newDate" />
				</p>

				<form method="POST" enctype="multipart/form-data">
					<table>
						<tr>
							<td style="text-align:right">File to Upload: </td>
							<td style="text-align:left"><input type="file" name="file"></td>
						</tr>
						<tr>
							<td style="text-align:right">Search Type: </td>
							<td style="text-align:left"><input size="3" type="text" name="searchType" value=""></input></td>
						</tr>
						<tr>
							<td style="text-align:right;">Search Type Info: Enter </td>
							<td style="text-align:left">1 For Nucleotide Only<br />0 For Structure Only</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:center"><input type="submit" value="Perform Suffix Array Search"
								onclick="form.action='/nsf/verna';"></input></td>
							<!-- <td style="text-align:left"><input type="submit" value="Regular Expression Search"
								onclick="form.action='/nsf/uploaded';"></input></td> -->
						</tr>
						<tr>
							<td style="text-align:center">
								<input type="hidden" name="name" value="RNA_${newDate}.rab" readonly="readonly"></input>
							</td>
						</tr>
						
					</table>
				</form>
				</div>
			</div>

		</div>
		 
 		<div id="footer">
			<div>
				<p>
					<span>All rights reserved &copy; <script>
						document.write(new Date().getFullYear())
					</script>
						RNA SSAC Project funded by the National Science Foundation (<a
						href="http://www.nsf.gov/">NSF</a>).
					</span><!-- <a href="#">Terms of Service</a> | <a href="#">Privacy Policy</a> -->
				</p>
				<ul>
					<li id="facebook"><a
						href="https://www.facebook.com/rnassac/">facebook</a>
					</li>
					<li id="twitter"><a href="http://www.twitter.com/">twitter</a>
					</li>
				</ul>

			</div>
		</div>
	</div>

</body>
</html>