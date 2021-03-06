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
		var regex = /^\d+$/;
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
			alert("Please enter Numbers only for Budget Amount. "
					+ "Allowed input are [0-9].");
			return false;
		}

		var flag = false;
		for (i = 0; i < mandatoryList.length; i++) {
			for (j = 0; j < optionalList.length; j++) {
				if (mandatoryList[i] == optionalList[j]) {
					flag = true;
					$("#optional option[value=" + optionalList[j] + "]").prop(
							'selected', false);
					break;
				}
			}
		}
		if (flag) {
			if (confirm("Few of your Mandatory bucket items are identical to the Optional ones."
					+ "Since we can not have this discrepency, we have deleted the similar items"
					+ " from your Optional bucket. Do you want to continue?") == true) {
				return true;
			} else {
				return false;
			}
		}
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
					<div align="center">Welcome to the concept of Mandatory
						Basket</div>
				</div>

				<br> <br>
				<%
					@SuppressWarnings("unchecked")
					List<String> prodList = (List<String>) request.getAttribute("ProductList");
				%>
				<div align="center">
					<form method="Post" action="MakeMandatoryBasket" name="myForm">
						<table>
							<tr>
								<td align="center">Select Mandatory Item/s :</td>
								<td align="center"><select name="mandatory" id="mandatory"
									multiple multiple style="width: 150px;">
										<%
											for (String prodName : prodList) {
										%>
										<option value="<%=prodName%>"><%=prodName%></option>
										<%
											}
										%>
								</select></td>
							</tr>
							<tr>
								<td align="center">Select Optional Item/s :</td>
								<td align="center"><select name="optional" id="optional"
									multiple style="width: 150px;">
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
						href="https://github.com/PRIBAN91/GiftBasket/wiki/Mandatory-Basket-Concept"
						target="_blank">Click Here</a>
				</div>

			</div>
		</div>
	</div>

</body>
</html>