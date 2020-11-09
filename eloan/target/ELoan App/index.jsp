<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLoan system</title>
</head>
<body>
	<!-- write the html code to read user credentials and send it to validateservlet
	    to validate and user servlet's registernewuser method if create new user
	    account is selected
	-->
	<!-- Tab content -->
	<jsp:include page="header.jsp" />
	<hr />
	<form action="registeruser" method="post">
		<div class="row">
			<div class="col">
				<!-- left part -->
			</div>
			<div class="col">
				<table style="margin: 25% px; position: relative;">
					<%
						String errMessage = (String) request.getAttribute("error");
						if (errMessage != null) {
					%>
					<%
						out.println(errMessage);
					%>
					<%
						}
					%>

					<tr>
						<td>Username:</td>
						<td><input type="text" name="username" required></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" required>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" class="button" value="Login">
							Not registered <a href="newuserui.jsp">click</a></td>
					</tr>
				</table>
			</div>
		</div>
	</form>

</body>
<hr />
<jsp:include page="footer.jsp" />
</html>