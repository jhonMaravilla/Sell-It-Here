<div class="wrapper">
	<header>
		<a href="${pageContext.request.contextPath}" class="header-brand">Sell It Here</a>
		<nav>
			<ul>
				<c:choose>
					<c:when test="${ user != null }">
						<li><a href="${pageContext.request.contextPath}/user/profile">Hi,
								${user.username}</a></li>
						<li><a
							href="${pageContext.request.contextPath}/products/product/new">Sell
								Item</a></li>
						<li><a
							href="${pageContext.request.contextPath}/signin?action=delete">Sign
								out</a></li>

					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/signin">Sign
								in/Sign up</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</header>
</div>

