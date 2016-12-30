<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE web-app PUBLIC
  "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
   "http://java.sun.com/dtd/web-app_2_3.dtd" >

<%@ page session="false"%>

<html>
<head>
<!-- <meta charset="UTF-8"> -->
<title>RNA SSAC | About Us</title>
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
		<div id="body">
			<img
				src="${pageContext.request.contextPath}/resources/images/laboratory.jpg"
				alt="">
			<div class="sidebar">
				<h3>contact</h3>
				<ul>
					<li><span class="address">address</span>
						<ul>
							<li>Computer Science Department</li>
							<li>Texas A&amp;M University</li>
							<li>PO Box 3011</li>
							<li>Commerce, TX 75428</li>
							<li>101 &amp; 102 JOUR Building</li>
						</ul></li>

					<li><span class="phone">telephone</span>
						<ul>
							<li>903-468-3097</li>
						</ul></li>

					<li><span class="email">email</span>
						<ul>
							<li><a href="#">Abdullah.Arslan@tamuc.edu</a></li>
						</ul></li>

					<li><span class="twitter">twitter</span>
						<ul>
							<li><a href="#">@rnassac</a></li>
						</ul></li>

					<li><span class="facebook">facebook</span>
						<ul>
							<li><a href="#">www.facebook/RNASSAC</a></li>
						</ul></li>
				</ul>
			</div>
			<div class="content">
				<h2>About</h2>
				<h3>We propose a new representation for RNA substructures.</h3>
				<p>The new representation has important features which reduces
					substructure search to a classical substring search. For the
					resulting search problem, we create suffix arrays and perform
					binary search. We also use a method that creates indices to speed
					up the search. We perform experiments. We create a large RNA
					sequence database by replicating real RNA structure sequences in
					bpseq format, and randomly selecting substructure sequences from
					our database. We test the naive substring matching algorithm, our
					suffix-array based algorithm, and a regular expression matching
					method that indexes the database to speed up the search.</p>

				<h3>Notation, Basic Definitions and Preliminaries</h3>
				<p>Notation, Basic Definitions and Preliminaries RNA molecules
					have 2D structures that are described by context free grammars. In
					similar substructures (motifs) in RNA molecules point to similar
					functions. Alignments that align common substructures (motif
					regions) (however, this may not increase the resulting similarity
					score) are shown to be superior to ordinary alignments [?, 29, 30,
					14, 39, 11]. A string S is a sequence of characters from a finite
					alphabet. We use sequence to mean a string because in the most
					immediate applications for this proposal sequences are used
					describing DNA, protein, and RNA. A substring s of string S is a
					sequence (a continuous segment) of S. A subsequence of a string S
					is a string obtained by deleting characters from S. Although
					substring and subsequence definitions are different, we prefer
					subsequence also to mean a continuous segment (substring) in this
					proposal. We use subsequences only in this meaning.</p>

				<h3>RNA Substructure Search</h3>
				<p>Our proposed representation for RNA secondary structures
					enables fast algorithms for searching a given substructure either
					in a given single RNA, or in an underlying RNA database. In
					unambiguous bracket representation, the search problem for a single
					RNA is the classical string pattern matching in text problem. There
					are very fast algorithms for it [28]. The problem can be solved in
					linear time. In the case of database search, unambiguous bracket
					representation makes it possible to create data structures from the
					RNA sequences in this format. One option is to create a
					(generalized) suffix tree (or a suffix array). The search can be
					answered in time linear in the size of the input substructure (i.e.
					the length of the string for the substructure in unambiguous
					bracket representation). One disadvantage of this approach is that
					such data structures can require significant amount of memory for
					large databases. If this is the case, then another solution is to
					use a seed table in the way BLAST uses. A seed is a string of some
					fixed-length</p>

				<h3>Interface with Varna</h3>
				<p>We integrated the suffix array search with web application
					and Varna (a java based application that is capable of drawing out
					the RNA in an applet). Once the user uploads a pattern file(Bpseq
					format preferably) the file is processed to searched across the
					database of RNA using suffixarray search and the result is returned
					in a string that contains the RNA index, matching pattern starting
					index, pattern length and no of matches. The return index is then
					processed in a loop for given no of matches each match corresponds
					to the RNA info stored in a array from which the structure info,
					rna name, nucleotide are extracted and passed to the corresponding
					web page in an array as a model attribute. Then array of matching
					RNA is then processed in a loop in jsp tag and passed to varna
					applet via javascript when the select event occurs in the page.
					Varna draws an rna for each nucleotide and structure inside the
					applet, the hightbutton next to the applet highlights the matches
					pattern in the rna tht is being displayed.</p>


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