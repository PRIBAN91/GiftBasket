<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products Entry Screen</title>
<link rel="stylesheet" href="css/style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="js/index.js"></script>
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

	<div id="wrapper">

		<div id="leftWrapper">
			<div id="listView" class="list">
				<li><a href="HomeScreen">Home</a></li>
				<li><a href="MandatoryBasket">Mandatory Basket</a></li>
				<li><a href="SimpleBasket">Simple Basket</a></li>
				<li><a href="CustomerReview">Customer Review</a></li>
				<li class="list-item-active"><a href="#">Product Entry</a></li>
				<li><a href="/GiftBasket/EditProductDetails.jsp">Edit
						Product Details</a></li>
				<li><a href="#">Contact Us</a></li>
			</div>
		</div>

		<div id="rightWrapper">
			<div id="header">
				<a id="fullPage" href="#">|||</a>
			</div>
			<div id="contentWrapper">
				<div class="article-header" al>
					<div align="center">
						Enter store's product details<br>(Only for Store Managers)
					</div>
				</div>

				<br> <br>

				<%
					String msg = (String) request.getAttribute("Message");
				%>

				<div align="center">
					<form method="Post" action="ProductEntry" name="myForm">
						Product Name : <input type="text" name="prodName"> <br>
						<br> Sub Category Name : <input type="text"
							name="subProdName"> <br> <br>Price : <input
							type="text" name="price"> <br> <br> <input
							type="submit" onclick="return validateForm()">
					</form>
				</div>

				<%
					if (msg != null) {
				%>
				<br> <br>
				<div align="center">
					<h3 style="color: red;"><%=msg%></h3>
				</div>
				<%
					}
				%>

			</div>
		</div>
	</div>

</body>
</html>