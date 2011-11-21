<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>
	<form:form modelAttribute="login" action="login" method="post">
		<div>
			<h2>login</h2>
		</div>
		<div>
			<p>
				<span>user:</span>
				<form:input path="user" />
				<form:errors path="user" />
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