<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<header>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.7.min.js">
		
	</script>
</header>

<body>
	<div>
		<p>
			<a href="logout">logout</a>
		</p>
	</div>
	<div>
		<a href="message">add message</a>
	</div>
	<div>
		<h2>messages for ${user.username}</h2>
	</div>
	<div>
		<c:forEach items="${user.messages}" var="singleMessage">
			<p>
				<span><c:out value="${singleMessage.message}" /></span>
			</p>
			<p>
				<span><c:out value="${singleMessage.timestamp}" /></span>
			</p>
			<hr>
		</c:forEach>
	</div>

</body>
</html>