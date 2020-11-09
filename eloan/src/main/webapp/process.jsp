<%@page import="com.iiht.evaluation.eloan.model.LoanInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script>
function add_years() 
{
	var date = document.getElementById("psd").value;
	dt = new Date(date);
	const n = document.getElementById("duration").value;
    calDt =  new Date(dt.setFullYear(dt.getFullYear() + parseInt(n)));  
    console.log("Value: "+calDt);
    var parsedDt = calDt.getDate()+"-"+calDt.getMonth()+"-"+calDt.getFullYear();
    document.getElementById("lcd").value = parsedDt;
}

function getEmi(){
	console.log("Get emi method called.");
	loanSanctionAmount = document.getElementById("las").value;
	roi = document.getElementById("roi").value;
	duration = document.getElementById("duration").value;
	loanPayment =  parseFloat(loanSanctionAmount) * Math.pow(1+parseFloat(roi)/100, duration);
	emi = parseFloat(loanPayment)/duration;
	document.getElementById("emi").value = emi;
}

</script>
</head>
<body>
	<!-- write the code to read application number, and send it to admincontrollers
	     callemi method to calculate the emi and other details also provide links
	     to logout and admin home page
	-->
<jsp:include page="header.jsp"/>
<div align="left"><a href="adminhome1.jsp">Home</a></div>
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
		<%if(loan != null){ %>
		<form action="admin?action=updatestatus&status=accept" method="post">
		<input type="hidden" id="loandidtoupdate"  name="loandidtoupdate" value="<%=loan.getApplno()%>">
<table style="margin: 10px;">
					<tr>
						<td>Loan Amount Sanctioned:</td>
						<td><input type="text" id="las"  name="las" required></td>
					</tr>
					<tr>
						<td>Term of Loan (Duration):</td>
						<td><input type="text" id="duration"  name="duration" required></td>
					</tr>
					<tr>
						<td>Rate of Interest:</td>
						<td><input type="text" id="roi"  name="roi" required></td>
					</tr>
					<tr>
						<td>Payment Start Date:</td>
						<td><input type="text" id="psd"  name="psd" onblur="add_years()" required></td>
					</tr>
					<tr>
						<td>Loan closure Date:</td>
						<td><input type="text" id="lcd" name="lcd" readonly="readonly" required></td>
					</tr>
					<tr>
						<td>EMI:</td>
						<td><input type="text"  id="emi" name="emi" readonly="readonly" required></td>
						<td><input type="button" class="button" value="Get EMI" onclick="getEmi()">
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" class="button" value="Approve Loan">
							
					</tr>
				</table>

</form>
<a href="admin?action=updatestatus&status=reject&loandidtoupdate=<%=loan.getApplno()%>">Reject </a>
<%} %>

<jsp:include page="footer.jsp"/>
</body>
</html>