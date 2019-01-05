
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


<div class="search-outer">
	<div class="search-div wrapper">
		<form action="${pageContext.request.contextPath}/products">
			<input type="hidden" name="city"
				value='<c:out value="${user.city}" default="%"/>' />
			<div id="box1">
				<input type="text" name="search" placeholder="search..."
					value="${param.search}" /> <input type="submit" value="Search" />
			</div>
			<div id="box2">
				<div id="category">
					<div>Category:</div>
					<select name="category">
						<option value="" selected="selected">all</option>
						<option value="car">Cars</option>
						<option value="electonic">Electonic</option>
						<option value="motor">Motors</option>
						<option value="accessory">Accessories</option>
						<option value="home">Home</option>
						<option value="sport">Sports</option>
						<option value="fashion">Fashion</option>
						<option value="other">Other</option>
					</select>
				</div>
				<div id="price">
					<div>Price:</div>
					<div class="dropdown_div">
						<input type="number" name="minPrice" min="0" placeholder="Price"
							value=0 />
						<div>to</div>
						<input type="number" name="maxPrice" min="0" placeholder="Price"
							value=2000 />
					</div>
				</div>

				<div id="sort">
					<div>Sort by:</div>
					<select name="sort">
						<option value="NEW_OLD" selected="selected">Date(most
							recent)</option>
						<option value="LOW_HIGH">Price: low to high</option>
						<option value="HIGH_LOW">Price: high to low</option>
					</select>
				</div>
			</div>
		</form>
	</div>
</div>