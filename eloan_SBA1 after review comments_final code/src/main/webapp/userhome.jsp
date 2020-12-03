<%@page import="com.iiht.evaluation.eloan.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>user home</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h2>
		Welcome
		<%
		User a1 = (User) session.getAttribute("user");
		out.println(a1.getUsername());
	%>
	</h2>
	<%
						String message = (String) request.getAttribute("message");
						if (message != null) {
							out.println(message);
						}
					%>
	<h4>User Dash Board</h4>

	<div align="right">
		<a href="logout">Logout</a>
	</div>
	<a href="application1">Apply for Loan</a>
	<br>
	<a href="trackloan?userid=<%=a1.getId()%>">Track Loan Application</a>
	<br>
	<a href="editLoanProcess">Edit Loan Application</a>
	<jsp:include page="footer.jsp" />
</body>
</html>