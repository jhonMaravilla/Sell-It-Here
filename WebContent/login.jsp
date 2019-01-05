<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="signin" method="post">
		<input type="hidden" name="redirect_uri" value=<c:out value="${uri}" default="${pageContext.request.contextPath}" /> />
		<input type="text" name="email" placeholder="Email..."/><br>
		<input type="text" name="password" placeholder="Password..." /><br>
		<input type="submit" value="Login" />
	</form>	
</body>
</html>