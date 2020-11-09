<%@page import="com.iiht.evaluation.eloan.model.LoanInfo"%>
<%@page import="com.iiht.evaluation.eloan.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Details</title>
</head>
<body>
	<!-- write the code to display the loan status information 
	     received from user controllers' display status method
	-->
	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<%
			LoanInfo loans = (LoanInfo) request.getAttribute("loans");
			out.println(loans.getApplno());
		%>

		<h2>
			Loan Details applied by User :
			<%
			User a1 = (User) request.getAttribute("user");
			out.println(a1.getUsername());
		%>
		</h2>
		<form action="application" method="post">
			<div>
				<div>
					<label for="loginid">Loan Number : <input type="text"
						name="loannumber" value="<%out.println(loans.getApplno());%>">
						<%
							out.println(loans.getApplno());
						%></label>
				</div>
				<div>
					<label for="loginid">Loan applied date : <%
						out.println(loans.getDoa());
					%></label>
				</div>
			</div>
			<div>
				<div>
					<label for="loginid">Loan Name</label>
				</div>
				<div>
					<input type="text" id="loanamount" name="loanname"
						value="<%out.println(loans.getLoanname());%>">
				</div>
			</div>
			<div>
				<div>
					<label for="loginid">Loan Amount</label>
				</div>
				<div>
					<input type="text" id="loanamount" name="loanamount"
						value="<%out.println(loans.getAmtrequest());%>">
				</div>
			</div>
			<div>
				<div>
					<label for="loginid">Business Struture</label>
				</div>
				<div>
					<input type="text" id="loanamount" name="businessstruture"
						value="<%out.println(loans.getBstructure());%>">
				</div>
			</div>
			<div>
				<div>
					<label for="loginid">Tax Indicator</label>
				</div>
				<div>
					<input type="text" name="taxindiacator"
						value="<%out.println(loans.getBindicator());%>">
				</div>
			</div>
			<div>
				<div>
					<label for="loginid">Contact Details</label>
				</div>
				<div>
					<input type="text" name="address1"
						value="<%out.println(loans.getAddress());%>">
				</div>
			</div>
			<div>
				<div>
					<input type="submit" value="Edit Loan">
				</div>
			</div>

		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>