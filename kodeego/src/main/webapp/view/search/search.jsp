<html>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tokeninput.js"></script>
<link rel="stylesheet" href="css/token-input.css" type="text/css" />
<link rel="stylesheet" href="css/token-input-facebook.css"
	type="text/css" />
<body>
	<div>
		<p>
			<span>search for code</span><input type="text" id="types" /><a
				href="code/searchCodeByType">search</a>
				        <script type="text/javascript">
        $(document).ready(function() {
            $("#types").tokenInput("search/getAllTypes");
        });
        </script>
				
		</p>
	</div>
	<div>
		<p>
			<span><a href="code">submit new code</a></span>
		</p>
	</div>
</body>
<html>