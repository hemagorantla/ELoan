<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register User</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
	<form action="registerNewUser" method="post">
		<div class="row">
			<div class="col">
				<!-- left part -->
			</div>
			<div class="col">
				<table style="margin: 10px;">
					<%
						String info = (String) request.getAttribute("info");
						if (info != null) {
							out.println(info);
						}
					%>
					<tr>
						<td>Username:</td>
						<td><input type="text" name="username" required></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" required></td>
					</tr>
					<tr>
						<td>email:</td>
						<td><input type="text" name="email"></td>
					</tr>
					<tr>
						<td>Phone:</td>
						<td><input type="text" name="phone" required></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" class="button" value="Register">
							
					</tr>
				</table>
			</div>
		</div>
	</form>

</body>
<hr/>
<jsp:include page="footer.jsp"/>
</html>