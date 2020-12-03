<%@page import="com.iiht.evaluation.eloan.model.LoanInfo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>admin home</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div align="right"><a href="index.jsp">Logout</a></div>
<h4>Admin Dash Board</h4>
<div style="margin-left: 50mm;" class="">
<table border="1">
<thead><tr>
<th>Loan Number</th><th> Loan Amount</th><th>Creation Date</th>
</tr></thead>
 <%
 List<LoanInfo> books= (List<LoanInfo>)  request.getAttribute("loans");
 for(LoanInfo loan:books){
	 %>
	<tr>
	<td><%=loan.getApplno()%> </td><td><%=loan.getAmtrequest() %> </td>
	<td><%=loan.getDoa()%> 
	<td><a href="admin?action=listall=<%=loan.getApplno()%>">Details</a> </td>
	<td><a href="admin?action=process=<%=loan.getApplno()%>">Process Loan</a> </td>
	</tr> 
<%	 
 }
 
 
 %>
 </table>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>