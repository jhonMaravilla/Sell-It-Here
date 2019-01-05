<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.*,dao.*" %>
<%@ page import="java.util.*, java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/styles/reply.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>

<div id="header">

	</div>
	
<div class="wrapper content">
	<div>
		<form method="post" action= "${pageContext.request.contextPath}/SendMessage">
		<table id="editTable">
			<tr>
				<td class="param">  To: </td>
				<td class="value"> ${message.recipientUser}</td>
			</tr>
			
			<tr>
				<td class="param">  From: </td>
				<td class="value"> ${message.senderUser}</td>
			</tr>
			<tr>
				<td class="param">  Date: </td>
				<td class="value"> <fmt:formatDate type = "both" dateStyle = "long" timeStyle = "short" value="${message.dateCreated}" /> </td>
			</tr>
			<tr>
				<td class="param"> Message: </td>
				<td class="value"> ${message.message}</td>
			</tr>
		</table>
		
		<br><br>
		<div id="centerMe">
		<table id="sendMe">
			<tr>
				<td> <textarea rows="8" cols="100" name="message" ></textarea></td>
			</tr>
			<tr>
				<td > 
					<button type="submit" formaction="${pageContext.request.contextPath}/buyingMessage.jsp" id="left">
						 Back to messages
					</button>
					<button type="submit" formaction="${pageContext.request.contextPath}/SendMessage" id="right" value="${message.recipientID}" name="sendTo">
						 Submit 
					</button>
				
				</td>
			</tr>
		</table>
		</div>
		</form>
	</div>
</div>
	
	  	<div id="footer">
	</br></br>
		<div class="floatRight">
			<table>
			<tr>
				<td> <label class="labelWidth"> Follow Us</label> </td>
				<td style="width:700px;text-align:right;"> <label class="labelWidth"> © Sell It Here 2018</label> </td> </td>
			</table>
		</div>
		</br></br>
		<div class="floatRight2">
			<table>
				<tr>
					<td><img src="${pageContext.request.contextPath}/Images/fb.svg" class="footLogo"></td>
					<td><img src="${pageContext.request.contextPath}/Images/gmail.svg" class="footLogo"></td>
					<td><img src="${pageContext.request.contextPath}/Images/instag.svg" class="footLogo"></td>
					<td><img src="${pageContext.request.contextPath}/Images/twitter.svg" class="footLogo"></td>
					<td><img src="${pageContext.request.contextPath}/Images/yt.svg" class="footLogo"></td>
					<td><img src="${pageContext.request.contextPath}/Images/pin.svg" class="footLogo"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>