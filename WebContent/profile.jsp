<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.UserBean" %>
<%@ page import="java.util.*, java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title> Profile </title>
  <link href="${pageContext.request.contextPath}/styles/myProfile.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link href="${pageContext.request.contextPath}/styles/headerStyle.css" rel="stylesheet" type="text/css" />
</head>

<body>
<%
	UserBean user = (UserBean) session.getAttribute("user");
%>

<%@ include file="/header.jsp"%>


  <div id="navBar">
		<div id="center">
			<ol>
				<li class="horizontal"><a href="${pageContext.request.contextPath}"> Home </a></li>
				<li class="horizontal"><a href="${pageContext.request.contextPath}/user/profile" style="border:.5px solid black;
	border-radius:5px;">  My Account </a></li> 
				<li class="horizontal"><a href="${pageContext.request.contextPath}/transaction"> Transactions </a></li>
				<li class="horizontal"><a href="${pageContext.request.contextPath}/buyingMessage.jsp"> Messages </a></li>
			</ol>
		</div>
	</div>

  <div id="content">
  	<div class="divCenter"> 
  	
  	<h3> Profile </h3> 
  	
    <form action="${pageContext.request.contextPath}/user/profile" method="Post">
  		
    	<table id="editTable">
    		<tr>
    			<td> <label class="labelWidth" > First Name </label> </td>
				<td> <input type="text" name="fname" class="textWidth" value="${user.fname}" required></td>
    		</tr>
    		
    		<tr>
    			<td> <label class="labelWidth"> Last Name </label> </td>
				<td> <input type="text" name="lname" class="textWidth" value="${user.lname}" required> </td>
    		</tr>
    			
    		<tr>
    			<td> <label class="labelWidth">  Street Address  </label></td>
    			<td> <input type="text" name="street" class="textWidth" value="${user.street}" required> </td>
    		</tr>
    		
    		<tr>
    			<td> <label class="labelWidth"> City  </label> </td>
    			<td> <input type="text" name="city" class="textWidth" value="${user.city}" required> </td>
    		</tr>
    		
    		<tr>
    			<td><label class="labelWidth"> Province/Territory   </label></td>
    			<td>
						 <select id="locationPicker" name="province">
			              <option selected hidden value="${user.province}"> ${user.province} </option>
			              <option value="Alberta"> Alberta </option>
			              <option value="British Columbia"> British Columbia </option>
			              <option value="Manitoba"> Manitoba </option>
			              <option value="New Brunswick"> New Brunswick </option>
			              <option value="Newfoundland"> Newfoundland </option>
			              <option value="Northwest Territories"> Northwest Territories </option>
			              <option value="Nova Scotia"> Nova Scotia </option>
			              <option value="Ontario"> Ontario </option>
			              <option value="Prince Edward Island"> Prince Edward Island </option>
			              <option value="Quebec"> Quebec </option>
			              <option value="Saskatchewan"> Saskatchewan </option>
			              <option value="Yukon"> Yukon </option>
			            </select>
			     </td>
    		</tr>
    		<tr>
    			<td> <label class="labelWidth" required> Postal  </label> </td>
    			<td> <input type="text" name="postal" class="textWidth" value="${user.postal}"> </td>
    		</tr>
    		<tr>
    			<td> <label class="labelWidth" required> Phone  </label> </td>
    			<td> <input type="text" name="phone" class="textWidth" value="${user.phone}"> </td>
    		</tr>
    	</table>
    
    
    <h3> Account </h3>
 
    	<table id="editTable2">
    		<tr>
    			<td> <label class="labelWidth"> Email  </label> </td>
				<td> <input type="email" name="email" class="textWidth" value="${user.email}" required> </td>
    		</tr>
    		
    		<tr>
    			<td> <label class="labelWidth"> New Password </label> </td>
				<td> <input type="password" name="password1" id="password1" class="textWidth" pattern=".{8,16}" title="Password must be 8 to 16 characters long"> </td>
				
    		</tr>
    		<tr>
    			<td> <label class="labelWidth"> Re-enter </label> </td>
				<td> <input type="password" name="password2" id="password2" class="textWidth"> </td>
    		</tr>
    		<tr>
    			<td> <label class="labelWidth"> Credit Card </label> </td>
				<td> <input type="text" name="creditcard" class="textWidth" id="creditcard" pattern="[0-9]{16}" title="Must be 16 digit card number"> </td>
    		</tr>
    		<tr>
    			<td colspan="2"> <br><label class="labelWidth" style="color:red;" id="warning">  </label> </td>
    		</tr>
    	</table>
    </div>
		<br>
		<input type="submit" value="Save Changes" id="saveChanges">
    </form> 

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
	
<script>
		document.getElementById("saveChanges").onclick = function() {
			var password1 = document.getElementById("password1").value;
			var password2 = document.getElementById("password2").value;
			var warning = document.getElementById("warning");
		    
		    if(password1.length != 0 || password2.length !=0){
				if (password2 === password1){
					return true;
				} else{
					document.getElementById("password1").value = "";
					document.getElementById("password2").value = "";
					warning.style.fontWeight = "bold";
					warning.innerHTML = "Password missmatch!";
					return false;
				}
			}
			
		} 
</script> 
	
</body>
</html>