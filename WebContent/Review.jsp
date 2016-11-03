<%@page import="com.bestchoice.model.Products"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Review Page</title>
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
		<select name="products">
			<%
				for (Products prods : prodList) {
			%>
			<option value="<%=prods.getSubProductName()%>"><%=prods.getSubProductName()%></option>
			<%
				}
			%>
		</select>
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