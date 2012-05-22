<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>

	<form:form modelAttribute="codeTo" action="code" method="post">
		<div>
			<h2>your code implementation</h2>
		</div>
		<div>
			<p><span>code category</snap></p><p>
				<form:textarea path="type"/>
				<form:errors path="type" />
			</p>
			<p><span>code snippet</snap></p><p>
				<form:textarea path="code" rows="20" cols="100" />
				<form:errors path="code" />
			</p>
			<p><span>comments</snap></p><p>
				<form:textarea path="comments" rows="3" cols="100" />
				<form:errors path="comments" />
			</p>
			<p>
				<input type="submit" value="submit code"/>
			</p>
		</div>
	</form:form>
</body>
</html>