<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Make your choice</title>
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
	<h2 align="center">Welcome to the concept of Gift Basket!</h2>
	<br>
	<%
		System.out.print("Before list fetch");
		@SuppressWarnings("unchecked")
		List<String> prodList = (List<String>) request.getAttribute("ProductList");
	%>
	<div align="center">
		<form method="Post" action="MakeBestChoice" name="myForm">
			<select name="products" multiple>
				<%
					for (String prodName : prodList) {
				%>
				<option value="<%=prodName%>"><%=prodName%></option>
				<%
					}
				%>
			</select> <br> <br> Enter Budget : <input type="text" name="budget"><br>
			<br> <input type="submit" onclick="return validateForm()">
		</form>
	</div>
	<br>
	<br>
	<br>
	<br>
	<div align="center">
		<a href="CustomerReview"> Click here to provide feedback for
			products.</a>
	</div>

</body>
</html>