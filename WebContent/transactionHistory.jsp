<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css?family=Catamaran"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="${pageContext.request.contextPath}/styles/headerStyle.css"
	rel="stylesheet" type="text/css" />

<link
	href="${pageContext.request.contextPath}/styles/transactionHistoryStyle.css"
	rel="stylesheet" type="text/css" />

<link
	href="${pageContext.request.contextPath}/styles/userProfile_navStyle.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/styles/footerStyle.css"
	rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<%@ include file="/userProfile_nav.jsp"%>

	<div class="content wrapper">
		<div class="side-nav">
			<div id="title">Transaction</div>
			<ul>
				<li class="active" id="sale-tab">Sales</li>
				<li id="buy-tab">Purchases</li>
			</ul>
		</div>
		<div class="transaction-box">
			<div id="sale-list">
				<div class="table-name">Sale History</div>

				<div class="table-wrapper">
					<div class="header">
						<div class="header1">Date of Sale</div>
						<div class="header1">Item</div>
						<div class="header1">Price</div>
						<div class="header1">Buyer</div>
					</div>

					<div class="tbody">
						<table>
							<c:forEach var="record" items="${sales}">
								<tr>
									<td>${record.created_at}</td>
									<td>${record.product.name}</td>
									<td>$${record.product.price}</td>
									<td>${record.buyer.username}</td>
								</tr>
							</c:forEach>
						</table>
					</div>

				</div>
			</div>

			<div id="buy-list">
				<div class="table-name">Purchase History</div>

				<div class="table-wrapper">
					<div class="header">
						<div class="header1">Date of Purchase</div>
						<div class="header1">Item</div>
						<div class="header1">Price</div>
						<div class="header1">Seller</div>
					</div>

					<div class="tbody">
						<table>
							<c:forEach var="record" items="${purchases}">
								<tr>
									<td>${record.created_at}</td>
									<td>${record.product.name}</td>
									<td>$${record.product.price}</td>
									<td>${record.seller.username}</td>
								</tr>
							</c:forEach>
						</table>
					</div>


				</div>
			</div>
		</div>
</div>


		<%@ include file="/footer_div.jsp"%>
		<script>
			document.addEventListener("DOMContentLoaded", function(event) {

				let salebtn = document.getElementById("sale-tab");
				let buybtn = document.getElementById("buy-tab");
				let saletb = document.getElementById("sale-list");
				let buytb = document.getElementById("buy-list");

				salebtn.addEventListener("click", function(e) {
					if (!salebtn.classList.contains("active")) {
						buybtn.classList.remove("active");
						salebtn.classList.add("active");
						saletb.style.display = "block";
						buytb.style.display = "none";
					}
				})

				buybtn.addEventListener("click", function(e) {
					if (!buybtn.classList.contains("active")) {
						salebtn.classList.remove("active");
						buybtn.classList.add("active");
						buytb.style.display = "block";
						saletb.style.display = "none";
					}
				})

			});
		</script>
</body>
</html>