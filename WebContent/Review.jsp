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
		var regex = /^\d+(?:\.\d{0,1})$/;
		if (!prodList.match(/\S/)) {
			alert("Please chose at least one product from the list to proceed with review.");
			return false;
		}
		if (!z.match(/\S/)) {
			alert("Please enter Rating to proceed.");
			return false;
		}
		if (z > 10) {
			alert("Please enter rating below or equal to 10 to proceed.");
			return false;
		}
		if (!regex.test(z)) {
			alert("Please enter rating in proper format.");
			return false;
		}
	}
</script>
</head>
<body>
	<h2 align="center">Put your reviews and ratings here</h2>
	<br>
	<%
		@SuppressWarnings("unchecked")
		List<Products> prodList = (List<Products>) request.getAttribute("ProductList");
		String msg = (String) request.getAttribute("Message");
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
				name="rating"> (Up to 1 decimal places allowed) <br> <br>
			<input type="submit" onclick="return validateForm()">
		</form>
	</div>

	<br>
	<br>
	<%
		if (msg != null) {
	%>
	<div align="center">
		<h3 style="color: red;"><%=msg%></h3>
	</div>
	<%
		}
	%>
	<br>
	<br>

	<div align="center">
		<a href="BasicServlet"> Click here to go back to the index page.</a>
	</div>

</body>
</html>