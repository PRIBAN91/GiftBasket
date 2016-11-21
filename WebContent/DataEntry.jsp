<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products Entry Screen</title>
<script type="text/javascript">
	function validateForm() {
		var prodName = document.forms["myForm"]["prodName"].value;
		var subProdName = document.forms["myForm"]["subProdName"].value;
		var price = document.forms["myForm"]["price"].value;
		var regex = /^\d+$/;
		if (!prodName.match(/\S/)) {
			alert("Please input product name to proceed.");
			return false;
		}
		if (!subProdName.match(/\S/)) {
			alert("Please enter sub category name to proceed.");
			return false;
		}
		if (!price.match(/\S/)) {
			alert("Please enter price of the product to proceed.");
			return false;
		}
		if (!regex.test(price)) {
			alert("Please enter numbers only for price.");
			return false;
		}
	}
</script>
</head>
<body>
	<h2 align="center">Enter your store's product details accordingly.</h2>

	<br>
	<%
		String msg = (String) request.getAttribute("Message");
	%>

	<div align="center">
		<form method="Post" action="ProductEntry" name="myForm">
			<br> <br> Product Name : <input type="text" name="prodName">
			<br> <br> Sub Category Name : <input type="text"
				name="subProdName"> <br> <br>Price : <input
				type="text" name="price"> <br> <br> <input
				type="submit" onclick="return validateForm()">
		</form>
	</div>

	<%
		if (msg != null) {
	%>
	<br>
	<br>
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