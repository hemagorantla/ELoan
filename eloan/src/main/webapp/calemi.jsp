<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div align="left"><a href="adminhome1.jsp">Home</a></div>
<div align="right"><a href="admin?action=logout">Logout</a></div>
 <!--
     Read the values from the admin servlet and cal emi and other details and send to
     to the same admin servlet to update the values in the database 
  -->  
  		<form action="admin?action=callemi" method="post">
<table style="margin: 10px;">
					<%
						String emi = null;
						String las = null;
						String duration = null;
						String roi = null;
						if (request.getAttribute("emi") != null) {
							emi = (String)request.getAttribute("emi");
						}
						if (request.getAttribute("las") != null) {
							las = (String)request.getAttribute("las");
						}
						if (request.getAttribute("duration") != null) {
							duration = (String)request.getAttribute("duration");
						}
						if (request.getAttribute("roi") != null) {
							roi = (String)request.getAttribute("roi");
						}
					%>
					<tr>
						<td>Loan Amount Sanctioned:</td>
						<%if(las == null) { %>
						<td><input type="text" name="las" required></td>
						<%} else{ %>
						<td><input type="text" name="las" value="<%=las %>" required></td>
						<%} %>
					</tr>
					<tr>
						<td>Term of Loan (Duration):</td>
						<%if(duration == null) { %>
						<td><input type="text" name="duration" required></td>
						<%} else{ %>
						<td><input type="text" name="duration" value="<%=duration %>" required></td>
						<%} %>
					</tr>
					<tr>
						<td>Rate of Interest: </td>
						<%if(roi == null) { %>
						<td><input type="text" name="roi" required></td>
						<%} else{ %>
						<td><input type="text" name="roi" value="<%=roi %>" required></td>
						<%} %>
					</tr>
					<tr>
						<td>EMI:</td>
						<%if(emi == null) { %>
						<td><input type="text" name="emi" disabled></td>
						<%} else{ %>
						<td><input type="text" name="emi" value="<%=emi %>" disabled></td>
						<%} %>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" class="button" value="Calculate EMI">
							
					</tr>
				</table>

</form>
<jsp:include page="footer.jsp"/>
</body>
</html>