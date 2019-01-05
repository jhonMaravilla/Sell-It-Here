<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="${pageContext.request.contextPath}/styles/searchbarStyle.css"
	rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Catamaran"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/styles/productEditStyle.css"
	rel="stylesheet" type="text/css" />

<title><c:out value="${action}" default="Create new post" /></title>
</head>
<body>
	<%@ include file="/searchbar.jsp"%>
	<c:choose>
		<c:when test="${action != null}">
			<h2 style="text-align: center;">Edit Post</h2>
		</c:when>
		<c:otherwise>
			<h2 style="text-align: center;">Create new Post</h2>
		</c:otherwise>
	</c:choose>
	<form class="wrapper content"
		action="${pageContext.request.contextPath}/products/product"
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="action"
			value='<c:out value="${action}" default="new" />' />
		 <input
			type="hidden" name="id"
			value="<c:out value="${product.productId}" />" required />
		<div class="photo">
			<input type="file" id="img" name="photo"
				accept="image/png, image/jpeg" style="margin-top:170px;margin-left:40px;"/> 
		</div>
		<div class="other"></div>
		<div class="product">

			<div class="product-title">
				<div>Title</div>
				<input type="text" name="name" placeholder="Title"
					value="${product.name}" required />
			</div>
			<div class="product-price">
				<div>Price</div>
				<input type="number" name="price" placeholder="Price" min=0 step=0.01
					required value="${product.price}" />
			</div>
			<div class="product-category">
				<div>Category:</div>
				<select name="category">
					<option value="car">Cars</option>
					<option value="electonic">Electonic</option>
					<option value="motor">Motors</option>
					<option value="accessory">Accessories</option>
					<option value="home">Home</option>
					<option value="sport">Sports</option>
					<option value="fashion">Fashion</option>
					<option value="other" selected="selected">Other</option>
				</select>
			</div>
			<div class="product-desc">
				<h3>Description:</h3>
				<textarea name="description">${product.description}</textarea>
			</div>
		</div>
		<div class="seller">
			<div class="product-btn">
				<input type="submit"
					value='<c:out value="${submit}" default="Create Post" />' />
					 
					 <c:choose>
					 	<c:when test="${action != null}">
					 	<a
							href="${pageContext.request.contextPath}/products/product/delete?id=${product.productId}"
							id="btn-delete">Delete Post</a>
					 	</c:when>
					 	<c:otherwise>
					 	 <a href="${pageContext.request.contextPath}">Cancel</a>
					 	 </c:otherwise>
					 </c:choose>
			</div>
		</div>

	</form>
</body>
</html>