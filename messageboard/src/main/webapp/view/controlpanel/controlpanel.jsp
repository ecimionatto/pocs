<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.7.min.js">
		
	</script>

	<script>
		$.ajaxSetup({
			scriptCharset : "utf-8",
			contentType : "application/json; charset=utf-8"
		});
		
		var refreshId = setInterval(function() {
			$.getJSON("controlpanel/messages", function(data) {

				var messagesToDisplay = '';
				$.each(data, function(id, message) {
					messagesToDisplay = messagesToDisplay + '<p> '
							+ message.user.username + ' says <b>'
							+ message.message + ' </b> @ ' + message.timestamp
							+ '</p><hr>';
				});

				$('#messages').html(messagesToDisplay);
				
			});
		}, 2500);
		
		$.getJSON("controlpanel/observedUsers/"
					, function(data) {
				
			var messagesToDisplay = '';
			$.each(data, function(id, user) {
				messagesToDisplay = messagesToDisplay + '<p> you are following <b>' 
				+ user.username + '</b> <a href="/controlpanel/stopFollowing/' + user.username+ '">stop following</a> </p>';
			});

			$('#followingMessage').html(messagesToDisplay);
		});
		
	</script>

</head>

<body>
	<div>
		<p>
			<a href="welcome">logout</a>
		</p>
	</div>
	<div>
		<a href="message">add message</a>
	</div>

	<form:form modelAttribute="user" action="user" method="post">
		<div>
			<p>
				<span>user </span>
				<form:input id="username" path="" />
				<input id="followButton" type="button" value="follow" />
			<div id="followingMessage"></div>
			<script>
				$("#followButton").click(
						function() {
							$.getJSON("controlpanel/follow/"
									+ $('#username').val(), function(data) {
								
								var messagesToDisplay = '';
								$.each(data, function(id, user) {
									messagesToDisplay = messagesToDisplay + '<p> you are following <b>' 
									+ user.username + '</b> <a href="/controlpanel/stopFollowing/' + user.username+ '">stop following</a> </p>';
								});

								$('#followingMessage').html(messagesToDisplay);
							});
						});
			</script>
		</div>
	</form:form>
	<hr>
	<div>
		<h2>your updated messages ${user.username}</h2>
	</div>
	<div id="messages"></div>
	<div></div>


</body>
</html>


