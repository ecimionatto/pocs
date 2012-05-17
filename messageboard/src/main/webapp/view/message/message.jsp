<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>

	<form:form modelAttribute="message" action="message" method="post">
		<div>
			<h2>create you new post</h2>
		</div>
		<div>
			<span>what's up? </span>
			<p>
				<form:textarea path="message" rows="2" cols="30" />
				<form:errors path="message" />
			</p>
			<p>
				<input type="submit" value="post message"/>
			</p>
		</div>
	</form:form>
</body>
</html>