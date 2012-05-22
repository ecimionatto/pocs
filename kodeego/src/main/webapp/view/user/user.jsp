<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>

	<form:form modelAttribute="user" action="user" method="post">
		<div>
			<h2>create you new user</h2>
		</div>
		<div>
			<p>
				<span>username:</span>
				<form:input path="username" />
				<form:errors path="username" />
			</p>
			<p>
				<span>name:</span>
				<form:input path="name" />
				<form:errors path="name" />
			</p>
			<p>
				<span>password:</span>
				<form:password path="password" />
				<form:errors path="password" />
			</p>
			<p>
				<input type="submit" />
			</p>
		</div>
	</form:form>
</body>
</html>