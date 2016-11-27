<%@page import="com.bestchoice.model.Products"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simple Basket</title>
<link rel="stylesheet" href="css/style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="js/index.js"></script>
<script type="text/javascript">
	function validateForm() {
		var prodList = document.forms["myForm"]["products"].value;
		var z = document.forms["myForm"]["budget"].value;
		var regex = /^\d+(?:\.\d{0,2})$/;
		if (!prodList.match(/\S/)) {
			alert("Please chose at least one product from the list to proceed.");
			return false;
		}
		if (!z.match(/\S/)) {
			alert("Please enter Budget Amount to proceed.");
			return false;
		}
		if (!regex.test(z)) {
			alert("Please enter Curreny characters only for Budget Amount. "
					+ "Allowed input are [0-9] and . only with upto 2 decimal places.");
			return false;
		}
	}
</script>
</head>
<body>

	<div id="wrapper">

		<div id="leftWrapper">
			<div id="listView" class="list">
				<li><a href="HomeScreen">Home</a></li>
				<li><a href="#">Mandatory Basket</a></li>
				<li class="list-item-active"><a href="#">Simple Basket</a></li>
				<li><a href="CustomerReview">Customer Review</a></li>
				<li><a href="/GiftBasket/DataEntry.jsp">Product Entry</a></li>
				<li><a href="#">Edit Product Details</a></li>
				<li><a href="#">Contact Us</a></li>
			</div>
		</div>

		<div id="rightWrapper">
			<div id="header">
				<a id="fullPage" href="#">|||</a>
			</div>
			<div id="contentWrapper">
				<div class="article-header" al>
					<div align="center">Enter one product from list for us to
						prepare Simple Basket</div>
				</div>

				<br> <br>

				<%
					@SuppressWarnings("unchecked")
					List<String> prodList = (List<String>) request.getAttribute("ProductList");
					//String msg = (String) request.getAttribute("Message");
				%>
				<div align="center">
					<form method="Post" action="PrepareSimpBasket" name="myForm">
						<select name="products">
							<%
								for (String prods : prodList) {
							%>
							<option value="<%=prods%>"><%=prods%></option>
							<%
								}
							%>
						</select> <br> <br> Enter budget : <input type="text"
							name="budget"> <br> <br> <input type="submit"
							onclick="return validateForm()">
					</form>
				</div>

			</div>
		</div>
	</div>

</body>
</html>