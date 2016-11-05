<%@page import="com.bestchoice.model.Products"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Review Page</title>
<script type="text/javascript">
	function validateForm() {
		var prodList = document.forms["myForm"]["products"].value;
		var z = document.forms["myForm"]["rating"].value;
		var regex = /^\d+(?:\.\d{0,2})$/;
		if (!prodList.match(/\S/)) {
			alert("Please chose at least one product from the list to proceed with review.");
			return false;
		}
		if (!z.match(/\S/)) {
			alert("Please enter Rating to proceed.");
			return false;
		}
		if (z > 10) {
			alert("Please enter rating below 10 to proceed.");
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
	<h2 align="center">Put your reviews and ratings here</h2>
	<br>
	<%
		System.out.print("Before list fetch");
		@SuppressWarnings("unchecked")
		List<Products> prodList = (List<Products>) request.getAttribute("ProductList");
	%>
	<div align="center">
		<form method="Post" action="PostCustomerReview" name="myForm">
			<select name="products">
				<%
					for (Products prods : prodList) {
				%>
				<option value="<%=prods.getSubProductName()%>"><%=prods.getSubProductName()%></option>
				<%
					}
				%>
			</select> <br> <br> Enter Rating (Out of 10) : <input type="text"
				name="rating"> (Up to 2 decimal places allowed) <br>
		</form>
		<br> <input type="submit" onclick="return validateForm()">
	</div>

	<br>
	<br>
	<br>
	<br>

	<div align="center">
		<a href="BasicServlet"> Click here to go back to the index page.</a>
	</div>

</body>
</html>