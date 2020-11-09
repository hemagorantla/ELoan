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
<div align="right"><a href="admin?action=logout">Logout</a></div>
<h4>Admin Dash Board</h4>
<%
						String message = (String) request.getAttribute("message");
						if (message != null) {
							out.println(message);
						}
					%>
<div style="margin-left: 50mm;" class="">
<a href="admin?action=listall">List all Loans</a>
	<br>
	<a href="admin?action=process">Process Loan</a>
	<br>
	<a href="calemi.jsp">Calculate EMI</a>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>