<%@page import="com.iiht.evaluation.eloan.model.LoanInfo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Loans</title>
</head>
<body>
	<!-- write code to display all the loan details 
             which are received from the admin controllers' listall method
	--> 
<jsp:include page="header.jsp"/>
<div align="left"><a href="adminhome1.jsp">Home</a></div>
<div align="right"><a href="admin?action=logout">Logout</a></div>
<div style="margin-left: 50mm;" class="">
<table border="1">
<thead><tr>
<th>Loan Number</th><th> Loan Amount</th><th>Creation Date</th><th>Actions</th>
</tr></thead>
 <%
 List<LoanInfo> loans= (List<LoanInfo>)  request.getAttribute("loans");
 for(LoanInfo loan:loans){
	 %>
	<tr>
	<td><%=loan.getApplno()%> </td><td><%=loan.getAmtrequest() %> </td>
	<td><%=loan.getDoa()%> 
	<td><a href="admin?action=process&loanid=<%=loan.getApplno()%>">Process Loan</a> </td>
	</tr> 
<%	 
 }
 
 
 %>
 </table>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>