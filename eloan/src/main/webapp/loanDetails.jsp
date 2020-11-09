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
	<div align="left"><a href="userhome.jsp">Home</a></div>
	<div align="right"><a href="logout">Logout</a></div>
	<hr />
	<div align=center>
			<%
			User a1 = (User) session.getAttribute("user");
		%>
	<h2>Edit Loan Details:
		<%
		LoanInfo loan = null;
		if(request.getAttribute("loan") != null){
			loan = (LoanInfo) request.getAttribute("loan");
			out.println(loan.getApplno());
		}
		%>
		</h2>
		<form action="editLoanProcess" method="post">
		<div>
			<div><label for="loanid">Enter Loan Id</label> </div>
			<%if(loan == null) {%>
			<div><input type="text" id="loanid" name="loanid" required> </div>
			<%} else  {%>
			<div><input type="text" id="loanid" name="loanid" value=<%out.println(loan.getApplno());%> required> </div>
			<%} %>
		</div>
		<div>
			<div><input type="submit" value="Get Loan to Edit"> </div>
		</div>
	</form>
<%if(loan!=null){ %>
		<form action="editloan" method="post">
		<input type="hidden" id="loandidtoeditloand"  name="loandidtoeditloand" value="<%=loan.getApplno()%>">
			<div>
				<div>
					<label for="loginid">Loan applied date : <%
						out.println(loan.getDoa());
					%></label>
				</div>
			</div>
			<div>
				<div>
					<label for="loginid">Loan Name</label>
				</div>
				<div>
					<input type="text" id="loanamount" name="loanname"
						value="<%out.println(loan.getLoanname());%>">
				</div>
			</div>
			<div>
				<div>
					<label for="loginid">Loan Amount</label>
				</div>
				<div>
					<input type="text" id="loanamount" name="loanamount"
						value="<%out.println(loan.getAmtrequest());%>">
				</div>
			</div>
			<div>
				<div>
					<label for="loginid">Business Struture</label>
				</div>
				<div>
					<input type="text" id="loanamount" name="businessstruture"
						value="<%out.println(loan.getBstructure());%>">
				</div>
			</div>
			<div>
				<div>
					<label for="loginid">Tax Indicator</label>
				</div>
				<div>
					<input type="text" name="taxindiacator"
						value="<%out.println(loan.getBindicator());%>">
				</div>
			</div>
			<div>
				<div>
					<label for="loginid">Contact Details</label>
				</div>
				<div>
					<input type="text" name="address1"
						value="<%out.println(loan.getAddress());%>">
				</div>
			</div>
			<div>
				<div>
					<input type="submit" value="Edit Loan">
				</div>
			</div>

		</form>
		<%} %>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>