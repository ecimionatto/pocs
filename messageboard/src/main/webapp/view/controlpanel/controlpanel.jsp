<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<header>
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
    		 messagesToDisplay =  messagesToDisplay + '<p> ' + message.user.username + ' says <b>' + message.message + ' </b> @ ' + message.timestamp + '</p><hr>'; 
   		 });
	
	 		
         $('#messages').html(messagesToDisplay);
 	
		});
	}, 1000);
	</script>
	</head>
<body>
	
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
		<p>
			<span>user </span>
			<input id ="username" type="text"/> 
			<input id="jsonButton" type="button" value="follow"/>
			<div id="followingMessage"></div>
			<script>
				$("#jsonButton").click(function() {
					$.getJSON("controlpanel/searchUsers/"+$('#username').val(), function(user) {
						$('#followingMessage').html("you are following " + user.username + " now!");
					});
				});
			</script>
		</p>
	</div>
	<hr>
	<div>
		<h2>your updated messages ${user.username}</h2>
	</div>
	<div id="messages">
	</div>
	<div>
	</div>
	

</body>
</html>


