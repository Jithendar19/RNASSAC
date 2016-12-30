
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE web-app PUBLIC
  "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
   "http://java.sun.com/dtd/web-app_2_3.dtd" >
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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

		<div id="section" align="center">

			<div align="left">



				<sf:form method="POST" modelAttribute="RegularExpPojo"
					action="/nsf/search">


					<table cellspacing="0">

						<tr>
							<td><sf:hidden name="path" path="path" value="${msg}"
									readonly="true" /></td>
						</tr>
						<tr>
							<td><sf:hidden path="" id="listWords" readonly="true" /></td>
							<td><sf:errors path="" /></td>
						</tr>
						<tr>
							<th><label for="pattern">Search RegE</label></th>
							<td><sf:input path="pattern" id="pattern" /></td>
							<td><sf:errors path="pattern" /></td>
						</tr>

						<tr>
							<th><label for="foundOnFile">Save Search?</label></th>
							<td><select name="foundOnFile">
									<option value="0">NO</option>
									<option value="1">YES</option>
							</select></td>
						</tr>

						<tr>
							<th><label for="noOfMatches">NoOfMatches </label></th>
							<td><sf:label path="noOfMatches" id="noOfMatches">
									<font size="3" color="red">${noOfMatches}</font>
								</sf:label></td>
							<td><sf:errors path="noOfMatches" /></td>
						</tr>



						<tr>
							<th><input type="Submit" value="Search" /></th>
							<td></td>
						</tr>
					</table>

					<font size="3" color="blue">${msgs}</font>
					<br />
					<font size="2" color="red">${status}</font>

				</sf:form>
			</div>
		</div>
		<div id="body">
			<div class="content">

				<fieldset>


					<table cellspacing="0">
						<tr>
							<td><c:set var="string1" value="${text}" />
								<p>
									<c:out value="${string1}" escapeXml="false" />
								</p></td>
						</tr>
					</table>
				</fieldset>
			</div>
		</div>
		<div id="footer">
			<div>
				<p>
					<span>2015 &copy; NSF SSAC Project funded by NSF.</span><a href="#">Terms
						of Service</a> | <a href="#">Privacy Policy</a>
				</p>
				<ul>
					<li id="facebook"><a
						href="https://www.facebook.com/NSF-SSAC-533481070149236/">facebook</a>
					</li>
					<li id="twitter"><a href="http://www.twitter.com/">twitter</a>
					</li>
					<li id="googleplus"><a href="http://www.googleplus.com/">googleplus</a>
					</li>
					<li id="rss"><a href="#">rss</a></li>
				</ul>

			</div>
		</div>
	</div>
</html>