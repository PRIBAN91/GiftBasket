<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Make your choice</title>
<link rel="stylesheet" href="css/style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="js/index.js"></script>
<script type="text/javascript">
	function validateForm() {
		var mandatoryList = new Array(), optionalList = new Array();
		$.each($("#mandatory").val(), function(i, val) {
			mandatoryList[i] = val;
		});
		$.each($("#optional").val(), function(i, val) {
			optionalList[i] = val;
		});
		var z = document.forms["myForm"]["budget"].value;
		var regex = /^\d+(?:\.\d{0,2})$/;
		if (mandatoryList == null || mandatoryList.length == 0) {
			alert("If you don't have anything mandatory to select,"
					+ " please select our Home page to determine Gift Basket.");
			return false;
		}
		if (optionalList == null || optionalList.length == 0) {
			alert("If you don't have anything optional to select, "
					+ "please select our Home page to determine Gift Basket.");
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

		for (i = 0; i < mandatoryList.length; i++) {

			for (j = 0; j < optionalList.length; j++) {
				alert("Here");
				if (mandatoryList[i] == optionalList[j]) {
					alert("Hi");
					$("#optional option[value=" + optionalList[j] + "]").prop(
							'selected', false);
					break;
				}
			}
		}
		return false;
	}
</script>
</head>
<body>

	<div id="wrapper">

		<div id="leftWrapper">
			<div id="listView" class="list">
				<li><a href="HomeScreen">Home</a></li>
				<li class="list-item-active"><a href="#">Mandatory Basket</a></li>
				<li><a href="SimpleBasket">Simple Basket</a></li>
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
					<div align="center">Welcome to the concept of Mandatory
						Basket</div>
				</div>

				<br> <br>
				<%
					@SuppressWarnings("unchecked")
					List<String> prodList = (List<String>) request.getAttribute("ProductList");
				%>
				<div align="center">
					<form method="Post" action="#" name="myForm">
						Mandatory : <select name="mandatory" id="mandatory" multiple>
							<%
								for (String prodName : prodList) {
							%>
							<option value="<%=prodName%>"><%=prodName%></option>
							<%
								}
							%>
						</select> <br> <br> Optional : <select name="optional"
							id="optional" multiple>
							<%
								for (String prodName : prodList) {
							%>
							<option value="<%=prodName%>"><%=prodName%></option>
							<%
								}
							%>
						</select> <br> <br> Enter Budget : <input type="text"
							name="budget"><br> <br> <input type="submit"
							onclick="return validateForm()">
					</form>
				</div>

			</div>
		</div>
	</div>

</body>
</html>