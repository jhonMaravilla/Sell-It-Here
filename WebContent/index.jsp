<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sell It Here</title>
<link href="styles/headerStyle.css" rel="stylesheet" type="text/css" />
<link href="styles/indexStyle.css" rel="stylesheet" type="text/css" />
<link href="styles/footerStyle.css" rel="stylesheet" type="text/css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>
	<%@ include file="/header.jsp"%>

	<div class="wrapper">
		<section class="index-banner"> <img
			src="${pageContext.request.contextPath}/Images/index_img_1.png"></section>
	</div>

	<!-- <div id="main" class="center"> -->
	<div class="wrapper">
		<div id="categories">
			<ul>
				<li><a
					href="${pageContext.request.contextPath}/products?category=car">
						<div class="icons"> <img alt="" src="${pageContext.request.contextPath}/Images/cars.png" height="80" width="80"> </div> Cars
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/products?category=electonic">
						<div class="icons"> <img alt="" src="${pageContext.request.contextPath}/Images/electronic.svg" height="80" width="80"></div> Electronics
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/products?category=fashion">
						<div class="icons"> <img alt="" src="${pageContext.request.contextPath}/Images/fashion.svg" height="80" width="80"> </div> Fashion
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/products?category=home">
						<div class="icons"> <img alt="" src="${pageContext.request.contextPath}/Images/home.svg" height="80" width="80"> </div> Home
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/products?category=garden">
						<div class="icons"> <img alt="" src="${pageContext.request.contextPath}/Images/garden.png" height="80" width="80"> </div> Garden
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/products?category=accessory">
						<div class="icons"> <img alt="" src="${pageContext.request.contextPath}/Images/accessories.png" height="80" width="80"> </div> Accessories
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/products?category=motor">
						<div class="icons"> <img alt="" src="${pageContext.request.contextPath}/Images/motor.svg" height="80" width="80"> </div> Motors
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/products?category=sport">
						<div class="icons"> <img alt="" src="${pageContext.request.contextPath}/Images/sports.svg" height="80" width="80"> </div> Sports
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/products?category=toy">
						<div class="icons"> <img alt="" src="${pageContext.request.contextPath}/Images/toy.png" height="80" width="80"> </div> Toys
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/products?category=other">
						<div class="icons"> <img alt="" src="${pageContext.request.contextPath}/Images/other.png" height="80" width="80"> </div> Others
				</a></li>

			</ul>
		</div>

		<div id="recent_posts">
			<h3 style="font-family: 'Catamaran';">Recent Posting</h3>
			<ul>
				<c:forEach var="product" items="${products}">
					<li class="item"><a
						href="${pageContext.request.contextPath}/products/product?id=${product.productId}">
							<div class="img">
								<c:choose>
									<c:when test="${product.image == null}">
										<img
											src="${pageContext.request.contextPath}/Images/images.png"
											alt="product-image">
									</c:when>
									<c:otherwise>
										<img src="data:image/jpg;base64,${product.image}"
											alt="product-image" />
									</c:otherwise>
								</c:choose>
							</div>
							<div class="item_name">${product.name}</div>
							<div class="item_price">
								<div>Price: $${product.price}</div>
								<%-- 	<div>Seller: ${product.price}</div> --%>
							</div>
					</a></li>

				</c:forEach>
			</ul>
		</div>

	</div>

</body>
</html>