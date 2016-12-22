<%@page import="com.bestchoice.model.Products"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Product Details</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="js/index.js"></script>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/r/ju-1.11.4/jqc-1.11.3,dt-1.10.8/datatables.min.css" />
<script type="text/javascript"
	src="https://cdn.datatables.net/r/ju-1.11.4/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#example').dataTable();
	});
</script>

</head>
<body>
	<div id="wrapper">

		<div id="leftWrapper">
			<div id="listView" class="list">
				<li><a href="HomeScreen">Home</a></li>
				<li><a href="MandatoryBasket">Mandatory Basket</a></li>
				<li><a href="SimpleBasket">Simple Basket</a></li>
				<li><a href="CustomerReview">Customer Review</a></li>
				<li><a href="/GiftBasket/DataEntry.jsp">Product Entry</a></li>
				<li><a href="#">View Product Details</a></li>
				<li><a href="#">Contact Us</a></li>
			</div>
		</div>

		<div id="rightWrapper">
			<div id="header">
				<a id="fullPage" href="#">|||</a>
			</div>
			<div id="contentWrapper">
				<div class="article-header" al>
					<div align="center">
						View Product Details<br>(Only for Store Managers)
					</div>
				</div>

				<%
					@SuppressWarnings("unchecked")
					List<Products> prodList = (List<Products>) request.getAttribute("ProductList");
				%>
				<br> <br>
				<div class="container">

					<table id="example" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>Product Name</th>
								<th>Sub Category</th>
								<th>Price</th>
								<th>Rating Average</th>
								<th>Ratings Total</th>
								<th>Ratings Count</th>
							</tr>
						</thead>
						<tbody>

							<%
								for (Products prod : prodList) {
							%>
							<tr>
								<td><%=prod.getProductName()%></td>
								<td><%=prod.getSubProductName()%></td>
								<td><%=prod.getPrice()%></td>
								<td><%=prod.getReviewValue()%></td>
								<td><%=prod.getReviewTotal()%></td>
								<td><%=prod.getReviewCount()%></td>
							</tr>
							<%
								}
							%>

						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>

</body>
</html>