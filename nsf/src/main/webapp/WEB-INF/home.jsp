<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE web-app PUBLIC
  "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
   "http://java.sun.com/dtd/web-app_2_3.dtd" >

<%@ page session="false"%>



<html>

<head>
<title>RNA SSAC | Home</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css"
	type="text/css">
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/images/logo.png">
</head>

<body>
	<!-- Links for the Navigation Menu -->
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

	<!-- Code to keep the rest of the site separate from the Navigation Menu -->
	<div class="site-wrap">

		<!-- Logo -->
		<div id="header">
			<a href="<c:url value="/"/>"><img class="logo"
				src="${pageContext.request.contextPath}/resources/images/logo.png"
				alt=""></a>
		</div>

		<!-- Code for body of website -->

		<!-- Left side of body -->
		<div id="featured">
			<div>
				<div class="article" style="max-width: 600px">
					<h2>Welcome to RNA SSAC Project</h2>
					<img style="max-width: 600px"
						src="${pageContext.request.contextPath}/resources/images/journalsm.jpg"
						alt="">
					<p>We intend to provide various services for researchers and
						professional with new RNA sequence representation in which 2D
						structural information is embedded in the sequence with desirable
						features. Our new representation is a linear sequence which
						unambiguously and (structurally) uniquely identifies the
						corresponding 2D structure. Any structure in non-nested form (a
						pseudo-knot) can be distinguished from other structures easily.</p>
				</div>
				<ul>
					<li>
						<h3>SUBMIT</h3>
						<div>
							<p>Submit your RNA sample to add to our database.</p>
							<a href="<c:url value="/"/>" class="more">read more</a>
						</div> <img
						src="${pageContext.request.contextPath}/resources/images/f.gif"
						alt="">
					</li>
					<li>
						<h3>SEARCH</h3>
						<div>
							<p>Search for the RNA using substring or pattern.</p>
							<a href="<c:url value="/upload"/>" class="more">read more</a>
						</div> <img
						src="${pageContext.request.contextPath}/resources/images/suffixArray.png"
						alt="">
					</li>
					<li>
						<h3>COMPARE</h3>
						<div>
							<p>Compare your RNA sample with our existing RNA database.</p>
							<a href="<c:url value="/comparison"/>" class="more">read more</a>
						</div> <img
						src="${pageContext.request.contextPath}/resources/images/d.gif"
						alt="">
					</li>
					<li>
						<h3>ANNOTATE</h3>
						<div>
							<p>Annotate the RNA with possible matches.</p>
							<a href="<c:url value="/"/>" class="more">read more</a>
						</div> <img
						src="${pageContext.request.contextPath}/resources/images/ee.gif"
						alt="">
					</li>
				</ul>
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
					<li id="facebook"><a href="https://www.facebook.com/rnassac/">facebook</a>
					</li>
					<li id="twitter"><a href="http://www.twitter.com/">twitter</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>