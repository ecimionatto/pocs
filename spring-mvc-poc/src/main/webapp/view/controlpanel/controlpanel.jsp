<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<header>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.7.min.js">
		
	</script>
</header>

<body>
	<form:form modelAttribute="login" action="${login.user}" method="post">
		<div>
			<h2>control panel</h2>
		</div>
		<div>
			<p>
				<span>user:</span>
				<form:input id="user" path="user" for="user" readonly="true" />
			</p>
			<p>
				<input id="jsonButton" type="button" />
				<script>
					$.ajaxSetup({
						scriptCharset : "utf-8",
						contentType : "application/json; charset=utf-8"
					});
					$("#jsonButton").click(function() {
						$.getJSON("json", {
							name : $('#login').val()
						}, function(login) {
							$("#user").val(login.user);
						});
					});
				</script>
			</p>
		</div>
	</form:form>
</body>
</html>