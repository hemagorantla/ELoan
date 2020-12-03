<%@page import="com.iiht.evaluation.eloan.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Application Form</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body onload="myFunction()">

	<jsp:include page="header.jsp" />
	<hr />
	<h2>
		Loan Application Form For User :
		<%
		User a1 = (User) request.getAttribute("user");
		out.println(a1.getUsername());
	%>
	</h2>
	<form action="application" method="post">
		<table style="margin: 10px;">
			<tr>
				<td><label>Loan Name</label></td>
				<td><input type="text" id="loanamount" name="loanname" required>
				</td>
			</tr>
			<tr>
				<td><label for="loginid">Loan Amount</label></td>
				<td><input type="text" id="loanamount" name="loanamount"
					required></td>
			</tr>
			<tr>
				<td><label for="loginid">Business Struture</label></td>
				<td><input type="text" id="loanamount" name="businessstruture"
					required></td>
			</tr>
			<tr>
				<td><label for="loginid">Tax Indicator</label></td>
				<td><input type="text" name="taxindiacator"> yes/no</td>
			</tr>
			<tr>
				<td><label for="loginid">Contact Details</label></td>
			</tr>
			<tr>
				<td><label for="loginid">Address 1</label></td>
				<td><input type="text" id="password" name="address1"></td>
			</tr>
			<tr>
				<td><label for="loginid">Address 2</label></td>
				<td><input type="text" id="password" name="address2"></td>
			</tr>
			<tr>
				<td><label for="loginid">Address 3</label></td>
				<td><input type="text" id="password" name="address3"></td>
			</tr>



			<tr>
				<td><input type="submit" value="Apply Loan"></td>
			</tr>
		</table>
	</form>
	<hr />
	<jsp:include page="footer.jsp" />


</body>
</html>