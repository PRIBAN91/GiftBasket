<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mandatory Basket Result</title>
</head>
<body>

	<h3 align="center">
		<%
			String result = (String) request.getAttribute("Result");
			out.println(result);
		%>
	</h3>

	<br>
	<br>
	<br>
	<br>

	<div align="center">
		<a href="MandatoryBasket"> Click here to go back to the index
			page.</a>
	</div>

</body>
</html>