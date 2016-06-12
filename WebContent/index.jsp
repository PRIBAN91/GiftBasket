<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index Page</title>
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
		<form method="Post" action="MakeBestChoice">
			<select name="products" multiple>
				<%
					for (String prodName : prodList) {
				%>
				<option value="<%=prodName%>"><%=prodName%></option>
				<%
					}
				%>
				<!-- <option value="saab">Saab</option>
				<option value="opel">Opel</option>
				<option value="audi">Audi</option> -->
			</select> <br></br> <input type="submit">
		</form>
	</div>

</body>
</html>