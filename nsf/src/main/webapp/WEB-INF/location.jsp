<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE web-app PUBLIC
  "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
   "http://java.sun.com/dtd/web-app_2_3.dtd" >

<%@ page session="false"%>


<html>
<head>
	<meta charset="UTF-8">
	<title>Our Team - NSF SSAC</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
	<link rel="icon" href="${pageContext.request.contextPath}/resources/images/logo.png">
</head>
<body>
	<ul class="navigation">
	    <li class="nav-item"><a href="<c:url value="/"/>">Home</a></li>
	    <li class="nav-item"><a href="<c:url value="/contact"/>">Contact Us</a></li>
	    <li class="nav-item"><a href="<c:url value="/about"/>">About Us</a></li>
	    <li class="nav-item"><a href="<c:url value="/team"/>">Team Members</a></li>
	    <li class="nav-item"><a href="<c:url value="/upload"/>">Search</a></li>
		<li class="nav-item"><a href="<c:url value="/comparison"/>">Comparison</a></li>
	</ul>

	<input type="checkbox" id="nav-trigger" class="nav-trigger" />
	<label for="nav-trigger"></label>

	<div class="site-wrap">
	
	<div id="header">
		<a href="<c:url value="/"/>" ><img class="logo" src="${pageContext.request.contextPath}/resources/images/logo.png" alt=""></a>
	</div>
	<div id="body">
		<div class="content">
			<img src="${pageContext.request.contextPath}/resources/images/campus.png" alt="">
			<h2>Our Team</h2>
			<div class="section">
				<div>
					<span>Project Owner</span>
					<ul>
						<li>
							<ul>
							<li><img src="${pageContext.request.contextPath}/resources/images/arslan.jpg" alt="" width="170" height="196"></li>
								<li>
									Professor Dr. Abdullah Arslan
									<br />TAMUC: Computer Science
									<br />Phone: +1 (903) 468-3097
									<br />E-mail: <a href="mailto:Abdullah.Arslan@tamuc.edu">Abdullah.Arslan@tamuc.edu</a>
									<br />Hours: Mondays to Fridays 7:00am - 6:00pm
								</li>
							</ul>
						</li>
						
					</ul>
				</div>
				<div>
					<span>Student Researcher - Undergraduate</span>
					<ul>
						<li>
							<ul>
							<li><img src="${pageContext.request.contextPath}/resources/images/rabin.jpg" alt="Rabin's pic" width="170" height="196"></li>
								
								<li>
									Rabindra Raj Pandey
									<br />TAMUC: Computer Science
									<br />Phone: +1 (817) 271-3281
									<br />E-mail: <a href="mailto:rrj.pandey@gmail.com">rrj.pandey@gmail.com</a>
									<br />Hours: Mondays to Fridays 7:00am - 6:00pm
								</li>
							</ul>
						</li>
						
						
					</ul>
				</div>
				<div>
					<span>Student Researcher - Undergraduate Student</span>
					<ul>
						<li>
							<ul>
							<li><img src="${pageContext.request.contextPath}/resources/images/eric.png" alt="Eric's Pic" width="170" height="196"></li>
								
								<li>
									Eric Fry
									<br />TAMUC: Computer Science &amp; Mathematics
									<br />Phone: +1 (903) 583-0238
									<br /><a href="mailto:efry1@leomail.tamuc.edu">efry1@leomail.tamuc.edu</a>
									<br />Hours: Monday-Fridays 8:00am - 6:00pm
								</li>
							</ul>
						</li>
					</ul>
				</div>
				<div>
					<span>Student Researcher - Graduate Student</span>
					<ul>
						<li>
							<ul>
							<li><img src="${pageContext.request.contextPath}/resources/images/jitu.jpg" alt="Jithendar's pic" width="170" height="196"></li>
								
								<li>
									Jithendar Anandan
									<br />TAMUC, Computer Science
									<br />Phone: +1 (903) 442-1741
									<br />E-mail: <a href="mailto:janandan@leomail.tamuc.edu">janandan@leomail.tamuc.edu</a>
									<br />Hours: Monday-Fridays 7:00am - 6:00pm
								</li>
							</ul>
						</li>
					</ul>
				</div>
				<div>
					<span>Student Researcher - Undergraduate Student</span>
					<ul>
						<li>
							<ul>
							<li><img src="${pageContext.request.contextPath}/resources/images/.jpg" alt="Keith's Pic" width="170" height="196"></li>
								
								<li>
									Keith Monschke
									<br />TAMUC: Computer Science &amp; Mathematics
									<br />Phone: +1 (903) 583-0238
									<br /><a href="mailto:kmonschke@leomail.tamuc.edu">kmonschke@leomail.tamuc.edu</a>
									<br />Hours: Monday-Fridays 7:00am - 6:00pm
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="sidebar">
			<h3>contact</h3>
			<ul>
				<li>
					<span class="address">address</span>
					<ul>
						<li>Computer Science Department
						<br />Texas A&amp;M University
						<br />PO Box 3011
						<br />Commerce, TX 75428
						<br />101 &amp; 102 JOUR Building
						</li>
					</ul>
				</li>
				<li>
					<span class="phone">telephone</span>
					<ul>
						<li>
							903-468-3097
						</li>
					</ul>
				</li>
				<li>
					<span class="email">email</span>
					<ul>
						<li>
							<a href="#">Abdullah.Arslan@tamuc.edu</a>
						</li>
					</ul>
				</li>
				<li>
					<span class="twitter">twitter</span>
					<ul>
						<li>
							<a href="#">@rnassac</a>
						</li>
					</ul>
				</li>
				<li>
					<span class="facebook">facebook</span>
					<ul>
						<li>
							<a href="#">www.facebook/RNASSAC</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	<div id="footer">
		<div>
			<p>
				<span>2015 &copy; NSF SSAC Project funded by NSF.</span><a href="#" >Terms of Service</a> | <a href="#" >Privacy Policy</a>
			</p>
			<ul>
				<li id="facebook">
					<a href="https://www.facebook.com/rnassac/">facebook</a>
				</li>
				<li id="twitter">
					<a href="http://www.twitter.com/">twitter</a>
				</li>
				<li id="googleplus">
					<a href="http://www.googleplus.com/">googleplus</a>
				</li>
				<li id="rss">
					<a href="#" >rss</a>
				</li>
			</ul>
			
		</div>
	</div>
	</div>
</body>
</html>