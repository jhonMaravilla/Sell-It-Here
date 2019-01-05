<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="models.*,dao.*" %>
<%@ page import="java.util.*, java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/styles/messages.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/styles/headerStyle.css" rel="stylesheet" type="text/css" />
<title> Messages </title>
</head>
<body>

<%
	UserBean user = (UserBean) session.getAttribute("user");
	ArrayList<MessageBean> messages = MessageDAO.getAllMessage(user.getId());
	pageContext.setAttribute("message", messages);
		
		
%>

<%@ include file="/header.jsp"%>

  <div id="navBar">
		<div id="center">
			<ol>
				<li class="horizontal"><a href="${pageContext.request.contextPath}"> Home </a></li>
				<li class="horizontal"><a href="${pageContext.request.contextPath}/user/profile">  My Account </a></li> 
				<li class="horizontal"><a href="${pageContext.request.contextPath}/products/product/new"> Sell Item </a></li>
				<li class="horizontal"><a href="${pageContext.request.contextPath}/transaction"> Transactions </a></li>
				<li class="horizontal"><a href="${pageContext.request.contextPath}/messages.jsp" style="border:.5px solid black;
	border-radius:5px;"> Messages </a></li>
				<li class="horizontal"><a href="${pageContext.request.contextPath}/signin?action=delete"> Sign out </a></li>
			</ol>
		</div>
	</div>

  <div id="content">
		<div id="center2">
			<ol>
				<li class="horizontal2"><a href="${pageContext.request.contextPath}/messages.jsp" style="border-bottom:6px solid rgb(255, 63, 85);color:rgb(255, 63, 85);"> All </a></li>
				<li class="horizontal2"><a href="${pageContext.request.contextPath}/buyingMessage.jsp?">  Buying </a></li> 
				<li class="horizontal2"><a href="${pageContext.request.contextPath}/sellingMessage.jsp"> Selling </a></li>
			</ol>
		</div>
		<br><br>
		<div id="center3">
		<table id="editTable">
				<tr>
					<th style="padding-right: 100px;"> Username </th>
					<th colspan="3"> Message </th>
					<th style="padding-left: 100px;"> Date Sent</th>
				</tr>
				<tr>
					<%System.out.println(messages.size()); %>
					<%
						if(messages.size() == 0){
					%>
						<td> </td>
						<td colspan="2"> <br><br><h2>No messages yet </h2> </td>
						<td></td>
					<%} %>

		<c:forEach items="${message}" var="inquiry">	
			<form action="${pageContext.request.contextPath}/MessageResponse" method="post">
				<c:choose>	
					<c:when test="${inquiry.isRead() == 'false'}">
						
							<td id="user"> <b> ${inquiry.senderUser} </b></td>
							<td colspan="3" id="mess"> <b> <button type="submit" formaction="${pageContext.request.contextPath}/MessageResponse?"
									  id="messButton">
									${inquiry.message}</button> </b></td>
							<td id="date"> <b> <fmt:formatDate type = "both" dateStyle = "long" timeStyle = "short" value="${inquiry.dateCreated}" /> </b></td>
						
					</c:when>
					<c:otherwise>
							<td id="user"> ${inquiry.senderUser} </td>
							<td colspan="3" id="mess"> <b> <button type="submit" formaction="${pageContext.request.contextPath}/MessageResponse?"
									  id="messButton">
									${inquiry.message}</button> </b></td>
							<td id="date"><fmt:formatDate type = "both" dateStyle = "long" timeStyle = "short" value="${inquiry.dateCreated}" /> </td>
					</c:otherwise>
				</c:choose>
			</tr>
			</form>
		</c:forEach>
		</table>
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