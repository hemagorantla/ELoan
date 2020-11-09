<%@page import="com.iiht.evaluation.eloan.model.LoanInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- write html code to read the application number and send to usercontrollers'
             displaystatus method for displaying the information
	-->
	<jsp:include page="header.jsp" />
<div align="left"><a href="userhome.jsp">Home</a></div>
	<div align="right"><a href="logout">Logout</a></div>
<% 	List<LoanInfo> loans= (List<LoanInfo>)  request.getAttribute("loans");
 if(loans.isEmpty()){
	 %>	
 <div>No loans to track.</div>
 <% }else{ %>
	<div style="margin-left: 50mm;" class="">
<table border="1">
<thead><tr>
<th>Loan Number</th><th> Status</th>
</tr></thead>
 <%
 for(LoanInfo loan:loans){
	 %>
	<tr>
	<td><%=loan.getApplno()%> </td>
	<td><%=loan.getStatus()%> 
	</tr> 
<%	 
 }
 }
 
 %>
 </table>
</div>
	<jsp:include page="footer.jsp" />
</body>
</html>