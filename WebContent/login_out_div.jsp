	<div id="login_out">

			<c:choose>
				<c:when test="${ user != null }">
					<div id="login_state">
						<button id="toggle_btn">${user.username}<i
								class="fa fa-chevron-down"> </i>
						</button>
						<ul id="user_div">
							<li><a
								href="${pageContext.request.contextPath}/user/profile"> Your
									profile</a></li>
							<li><a href="${pageContext.request.contextPath}/products/product/new">Sell items</a></li>
							<li id="signout"><a href="${pageContext.request.contextPath}/signin?action=delete">Sign out</a></li>
						</ul>
					</div>
				</c:when>
				<c:otherwise>
		<%-- 			<a href="${pageContext.request.contextPath}/signin">Sign in</a>
					<a href="${pageContext.request.contextPath}/signup">Sign up</a> --%>
					 <a href="${pageContext.request.contextPath}/signin" id="login_link">Sign in/Sign up</a>
				</c:otherwise>
			</c:choose>

		</div>