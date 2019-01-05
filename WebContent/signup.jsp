<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<link href="${pageContext.request.contextPath}/styles/signupStyle.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="styles/headerStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%@ include file="/header.jsp"%>

	<div id="content">
		<div id="signUp">


			<form action="${pageContext.request.contextPath}/signup"
				method="post">
				<table id="editTable">
					<tr>
						<td colspan="2">
							<center>
								<h3>Create Account</h3>
							</center>
						</td>
					</tr>
					<tr>
						<td><label class="labelWidth"> First Name <input
								type="text" name="fname" class="textWidth"
								value="${param.fname}" required />
						</label></td>
					</tr>

					<tr>
						<td><label class="labelWidth"> Last Name <input
								type="text" name="lname" class="textWidth"
								value="${param.lname}" required>
						</label></td>
					</tr>

					<tr>
						<td><label class="labelWidth"> Username <input
								type="text" name="user" class="textWidth" required>
						</label></td>
					</tr>

					<tr>
						<td><label class="labelWidth"> Password: <input
								type="password" name="password" class="textWidth" required>
						</label></td>
					</tr>

					<tr>
						<td><label class="labelWidth"> Email: <input
								type="email" name="email" class="textWidth" required>
						</label></td>
					</tr>
					<tr>
						<td><label class="labelWidth"> Street Address: <input
								type="text" name="street" class="textWidth"></label></td>
					</tr>

					<tr>
						<td><label class="labelWidth"> City: <input
								type="text" name="city" class="textWidth" required>
						</label></td>
					</tr>

					<tr>
						<td><label class="labelWidth"> Province/Territory <select
								id="locationPicker" name="province">
									<option value="" disabled selected hidden>Select
										province/territory</option>
									<option value="Alberta">Alberta</option>
									<option value="British Columbia">British Columbia</option>
									<option value="Manitoba">Manitoba</option>
									<option value="New Brunswick">New Brunswick</option>
									<option value="Newfoundland">Newfoundland</option>
									<option value="Northwest Territories">Northwest
										Territories</option>
									<option value="Nova Scotia">Nova Scotia</option>
									<option value="Ontario" selected="selected">Ontario</option>
									<option value="Prince Edward Island">Prince Edward
										Island</option>
									<option value="Quebec">Quebec</option>
									<option value="Saskatchewan">Saskatchewan</option>
									<option value="Yukon">Yukon</option>
							</select>
						</label></td>
					</tr>

					<tr>
						<td><label class="labelWidth"> Postal Code <input
								type="text" name="postal" value="${param.postal}"
								class="textWidth">
						</label></td>
					</tr>

					<tr>
						<td><label class="labelWidth"> Phone <input
								type="text" name="phone" class="textWidth">
						</label></td>
					</tr>

					<tr>
						<td><c:if test="${signUpMessage != null}">
								<ul>
									<c:forEach var="msg" items="${signUpMessage}">
										<li><c:out value="${msg}" /></li>
									</c:forEach>
								</ul>
							</c:if></td>
					</tr>

					<tr>
						<td colspan="2">
							<center>
								<input type="submit" value="Create Account" id="createAcc">
							</center>
						</td>
					</tr>
				</table>
			</form>

		</div>

		<div id="signIn">
			<form action="${pageContext.request.contextPath}/signin"
				method="post">
				<table id="signInTable">
					<tr>
						<td colspan="2">
							<center>
								<h3>Sign In</h3>
							</center>
						</td>
						<input type="hidden" name="redirect_uri"
							value=<c:out value="${uri}" default="${pageContext.request.contextPath}" /> />
					</tr>

					<tr>
						<td><input type="text" name="email"
							placeholder="Username/Email" class="textWidth4" />
					</tr>

					<tr>
						<td><input type="password" name="password" placeholder="Password"
							class="textWidth4" /></td>
					</tr>

					<tr>
						<td><c:if test="${signInMessage != null}">
								<c:out value="${signInMessage}" />
							</c:if></td>
					<tr>
						<td colspan="2">
							<center>
								<input type="submit" value="Sign In" id="createAcc">
							</center> </
						</td>
					</tr>

				</table>
			</form>
		</div>

	</div>

	<div id="footer">
		</br> </br>
		<div class="floatRight">
			<table>
				<tr>
					<td><label class="labelWidth"> Follow Us</label></td>
					<td style="width: 700px; text-align: right;"><label
						class="labelWidth"> © Sell It Here 2018</label></td>
					</td>
			</table>
		</div>
		</br> </br>
		<div class="floatRight2">
			<table>
				<tr>
					<td><img
						src="${pageContext.request.contextPath}/Images/fb.svg"
						class="footLogo"></td>
					<td><img
						src="${pageContext.request.contextPath}/Images/gmail.svg"
						class="footLogo"></td>
					<td><img
						src="${pageContext.request.contextPath}/Images/instag.svg"
						class="footLogo"></td>
					<td><img
						src="${pageContext.request.contextPath}/Images/twitter.svg"
						class="footLogo"></td>
					<td><img
						src="${pageContext.request.contextPath}/Images/yt.svg"
						class="footLogo"></td>
					<td><img
						src="${pageContext.request.contextPath}/Images/pin.svg"
						class="footLogo"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>