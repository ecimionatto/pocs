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
			<a href="welcome">logout</a>
		</p>
	</div>
	<div>
		<a href="message">add message</a>
	</div>
	<div>
		<h2>messages for ${user.username}</h2>
	</div>
	<div id="messages">
		<c:forEach items="${user.observedMessages}" var="singleMessage">
			<p>
				<span><c:out value="${singleMessage.message}" /></span>
			</p>
			<p>
				<span><c:out value="${singleMessage.timestamp}" /></span>
			</p>
			<hr>
		</c:forEach>
	</div>
	<div>
	<form:form modelAttribute="user" action="${controlpanel/search}" method="get">
		<div>
			<h2>search to follow users</h2>
		</div>
		<div>
			<p>
				<span>user:</span>
				<form:input id="username" path="username" for="user"/>
				<input id="jsonButton" type="button" value="follow"/>
				<div id="followingMessage"></div>
				<script>
					$.ajaxSetup({
						scriptCharset : "utf-8",
						contentType : "application/json; charset=utf-8"
					});
					$("#jsonButton").click(function() {
						$.getJSON("controlpanel/searchUsers/"+$('#username').val(), function(user) {
							$('#followingMessage').html("you are following " + user.username + " now!");
						});
					});
				</script>
			</p>
		</div>
	</form:form>
	</div>
	

</body>
</html>