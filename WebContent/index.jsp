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
		var prodList = document.forms["myForm"]["products"].value;
		var z = document.forms["myForm"]["budget"].value;
		var regex = /^\d+$/;
		if (!prodList.match(/\S/)) {
			alert("Please chose at least one product from the list to proceed.");
			return false;
		}
		if (!z.match(/\S/)) {
			alert("Please enter Budget Amount to proceed.");
			return false;
		}
		if (!regex.test(z)) {
			alert("Please enter Numbers only for Budget Amount. "
					+ "Allowed input are [0-9].");
			return false;
		}
	}
</script>
</head>
<body>

	<div id="wrapper">

		<div id="leftWrapper">
			<div id="listView" class="list">
				<li class="list-item-active"><a href="#">Home</a></li>
				<li><a href="MandatoryBasket">Mandatory Basket</a></li>
				<li><a href="SimpleBasket">Simple Basket</a></li>
				<li><a href="CustomerReview">Customer Review</a></li>
				<li><a href="/GiftBasket/DataEntry.jsp">Product Entry</a></li>
				<li><a href="ViewProducts">View Product Details</a></li>
				<li><a href="#">Contact Us</a></li>
			</div>
		</div>

		<div id="rightWrapper">
			<div id="header">
				<a id="fullPage" href="#">|||</a>
			</div>
			<div id="contentWrapper">
				<div class="article-header" al>
					<div align="center">Welcome to the concept of Gift Basket</div>
				</div>

				<br> <br>
				<%
					@SuppressWarnings("unchecked")
					List<String> prodList = (List<String>) request.getAttribute("ProductList");
				%>
				<div align="center">
					<form method="Post" action="MakeBestChoice" name="myForm">
						<table>
							<tr>
								<td align="center">Select Item/s :</td>
								<td align="center"><select name="products" multiple
									style="width: 150px;">
										<%
											for (String prodName : prodList) {
										%>
										<option value="<%=prodName%>"><%=prodName%></option>
										<%
											}
										%>
								</select></td>
							</tr>
						</table>
						<br> Enter Budget : <input type="text" name="budget"><br>
						<br> <input type="submit" onclick="return validateForm()">
					</form>
				</div>
				<br> <br>
				<div align="center">
					For more details on this module, please click here : <a
						href="https://github.com/PRIBAN91/GiftBasket/wiki/Gift-Basket-Concept"
						target="_blank">Click Here</a>
				</div>

			</div>
		</div>
	</div>

</body>
</html>