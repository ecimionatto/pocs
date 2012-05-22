<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>
	<form:form modelAttribute="login" action="login" method="post">
		<div>
			<h3>view messages</h3>
		</div>
		<div>
			<p>
				<a href="controlpanel">messages</a>
			</p>
		</div>

		<div>
			<h3>new user sign-up</h3>
		</div>
		<div>
			<p>
				<a href="user">register</a>
			</p>
		</div>
	</form:form>
</body>
</html>