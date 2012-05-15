<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>

	<form:form modelAttribute="message" action="message" method="post">
		<div>
			<h2>create you new post</h2>
		</div>
		<div>
			<p>
				<span>message:</span>
				<form:input path="message" />
				<form:errors path="message" />
			</p>
			<p>
				<input type="submit" />
			</p>
		</div>
	</form:form>
</body>
</html>