<%@page import="com.iiht.evaluation.eloan.model.LoanInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- write the code to read application number, and send it to admincontrollers
	     callemi method to calculate the emi and other details also provide links
	     to logout and admin home page
	-->
<jsp:include page="header.jsp"/>
<div align="left"><a href="admin?action=logout">Home</a></div>
<div align="right"><a href="admin?action=logout">Logout</a></div>

<%
			LoanInfo loan = null;
			if(request.getAttribute("loan") !=null){
				loan = (LoanInfo) request.getAttribute("loan");
			}
		%>
		<form action="admin?action=process" method="post">
		<div>
			<div><label for="loanid">Enter Loan Id</label> </div>
			<%if(loan == null) {%>
			<div><input type="text" id="loanid" name="loanid" > </div>
			<%} else  {%>
			<div><input type="text" id="loanid" name="loanid" value=<%out.println(loan.getApplno());%>> </div>
			<%} %>
		</div>
		<div>
			<div><input type="submit" value="Get Details"> </div>
		</div>
	</form>
			<div>
				<div>
					<label for="loginid">Loan Number: 
					<%= ( loan == null?"":loan.getApplno() ) %>
						</label>
				</div>
				<div>
					<label for="lpd">Loan Applied Date: 
					<%= ( loan == null?"":loan.getDoa() ) %>
					</label>
				</div>
			</div>
			<div>
				<div>
					<label for="loanname">Loan Name: <%= ( loan == null?"":loan.getLoanname() ) %> </label>
				</div>
			</div>
			<div>
				<div>
					<label for="loanamount">Loan Amount: <%= ( loan == null?"":loan.getAmtrequest() ) %></label>
				</div>
			</div>
			<div>
				<div>
					<label for="lbs">Business Structure: <%= ( loan == null?"":loan.getBstructure() ) %></label>
				</div>
			</div>
			<div>
				<div>
					<label for="taxindicator">Tax Indicator: <%= ( loan == null?"":loan.getBindicator() ) %></label>
				</div>
			<div>
				<div>
					<label for="contactdetails">Contact Details: <%= ( loan == null?"":loan.getAddress() ) %></label>
				</div>
			</div>

		<br/>
		<form action="admin?action=updatestatus" method="post">
<table style="margin: 10px;">
					<%
						String info = (String) request.getAttribute("info");
						if (info != null) {
							out.println(info);
						}
					%>
					<tr>
						<td>Loan Amount Sanctioned:</td>
						<td><input type="text" name="las" required></td>
					</tr>
					<tr>
						<td>Term of Loan (Duration):</td>
						<td><input type="text" name="duration" required></td>
					</tr>
					<tr>
						<td>Payment Start Date:</td>
						<td><input type="text" name="psd"></td>
					</tr>
					<tr>
						<td>Loan closure Date:</td>
						<td><input type="text" name="lcd" required></td>
					</tr>
					<tr>
						<td>EMI:</td>
						<td><input type="text" name="emi" required></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" class="button" value="Approve Loan">
							
					</tr>
				</table>

</form>

<a href="admin?action=updatestatus&status=reject">Reject </a>
<jsp:include page="footer.jsp"/>
</body>
</html>