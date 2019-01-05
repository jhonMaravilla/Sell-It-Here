<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/styles/productStyle.css"
	rel="stylesheet" type="text/css" />
<link href="styles/footerStyle.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="${pageContext.request.contextPath}/styles/searchbarStyle.css"
	rel="stylesheet" type="text/css" />
<title>Products</title>

</head>
<body>
	<%@ include file="/searchbar.jsp"%>


	<div id="content" class="wrapper">

		<c:forEach var="item" items="${products}">
			<a
				href="${pageContext.request.contextPath}/products/product?id=${item.productId}"
				class="card">
				<div>
					<c:choose>
						<c:when test="${item.image == null}">
							<img src="${pageContext.request.contextPath}/Images/images.png"
								alt="product-image">
						</c:when>
						
						<c:otherwise>
							<img src="data:image/jpg;base64,${item.image}"
								alt="product-image" />
						</c:otherwise>
					</c:choose>
				
				</div> <span class="product-trend-label">$${item.price}</span>
				<div>
					<div class="item-name">${item.name}</div>
					<div class="item-price">
						<div>Location: ${item.price}</div>
					</div>
				</div>
			</a>

		</c:forEach>

	</div>

	<%@ include file="/footer_div.jsp"%>
	<script>
    let isOn = false;
    let user_div = document.getElementById("user_div");
    document.getElementById("toggle_btn").addEventListener("click", e => {
      if (isOn) {
        user_div.style.display = 'none';
        isOn = false;
      } else {
        user_div.style.display = 'block';
        isOn = true;
        e.stopPropagation();
      }
    })
    document.addEventListener('click', e => {
      if (isOn) {
        user_div.style.display = 'none';
        isOn = false;
      }
    })
  </script>
</body>
</html>