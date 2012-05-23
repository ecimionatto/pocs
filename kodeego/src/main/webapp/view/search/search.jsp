<html>
	<head>
		<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>

		
	</script>

	<script>
		$.ajaxSetup({
			scriptCharset : "utf-8",
			contentType : "application/json; charset=utf-8"
		});
		
		
		$(document).ready(function() {
		$.get("search/getAllTypes", function(data) {
			
			var alltypes = "source: ["
			var items = [];
			$.each(data, function(key, val) {
				if (val.type!=null) {
    				alltypes = alltypes + val.type + ", ";
    			}
  			});
			alltypes = alltypes + "]"
			
			$("input#types").autocomplete(alltypes);
		});
		});
		
	</script>

	</head>
	<body>
	<div>
		<p>
			<span>search for code</span><input type="text" id="types"/><a href="code/searchCodeByType">search</span>
		</p>
	</div>
	<div>
		<p>
			<span><a href="code">submit new code</span>
		</p>
	</div>
	</body>
<html>