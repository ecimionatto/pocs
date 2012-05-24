<html>
<head>
<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>

<script>
	$.ajaxSetup({
		scriptCharset : "utf-8",
		contentType : "application/json; charset=utf-8"
	});

	var alltypes = "[";
	$(function getAllTypes() {
		$.get("search/getAllTypes", function(data) {
			var items = [];
			$.each(data, function(key, val) {
				items.push(val.type);
			});
			$("#types").autocomplete({
				source : items
			});
		});
	});
</script>

</head>
<body>
	<div>
		<p>
			<span>search for code</span><input id="types" /><a
				href="code/searchCodeByType">search</a>
		</p>
	</div>
	<div>
		<p>
			<span><a href="code">submit new code</a></span>
		</p>
	</div>
</body>
<html>