<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="models.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link href="https://fonts.googleapis.com/css?family=Catamaran" rel="stylesheet">
<link href="${pageContext.request.contextPath}/styles/searchbarStyle.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/styles/productDetailStyle.css" rel="stylesheet"
	type="text/css" />
<title>Product - ${product.name}</title>
</head>
<body>
	<%@ include file="/searchbar.jsp"%>

	<div class="wrapper">
		<p>Category > ${product.category}</p>
	</div>
	<div class="wrapper content">
		<div class="photo">
			<c:choose> 
				<c:when test="${product.image == null}">
				<img src="${pageContext.request.contextPath}/Images/images.png"
					alt="product-image">
				</c:when>
				<c:otherwise>
					<img src="data:image/jpg;base64,${product.image}" alt="product-image"/>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="other"></div>
		<div class="product">
			<div class="product-header">${product.name}</div>
			<div class="product-header">${product.price}</div>
			<p>Description</p>
			<div class="product-desc">${product.description}</div>
			<div class="product-btn">
				<c:choose>
					<c:when test="${seller.id == user.id}">
						<a
							href="${pageContext.request.contextPath}/products/product/edit?id=${product.productId}">Edit</a>
						<a
							href="${pageContext.request.contextPath}/products/product/delete?id=${product.productId}"
							id="btn-delete">Delete Post</a>
					</c:when>
					<c:otherwise>
						<a
							href="${pageContext.request.contextPath}/transaction/new?id=${product.productId}">Buy</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="seller">
			<div class="product-btn">
				<h2>Seller Info</h2>
				<div class="seller-info">Username: ${seller.username}</div>
				<div class="seller-info">Location: ${seller.city}</div>
			<c:if test="${seller.id != user.id}">
				<form action="${pageContext.request.contextPath}/SendMessage" method="post">
					<textarea rows="15" cols="70" name="message" maxlength="500" id="message" ></textarea> <br>
					<input type="submit" value="Send Inquiry" id="sendMessage" 
					style="width:520px; height:30px; background-color:#8bc34a; font-size:20px; color:white; margin-top:10px; margin-left:2px;">
				</form>
			</c:if>
			</div>
		</div>

	</div>
	
		<script>
		document.getElementById("sendMessage").onclick = function() {
			var message = document.getElementById("message");
			var inquiry = message.value;
			if (inquiry.length == 0 || !inquiry.replace(/\s/g, '').length) {
				message.innerHTML = "Hi! I am interested in this product. Is this still available?";
				alert("We are unable to send your message at this time. You may also contact this seller using SMS or phone call");	
				return false;
			} else{
				alert("Inquiry Sent");
				return true;
			}
		}
</script>
</body>
</html>