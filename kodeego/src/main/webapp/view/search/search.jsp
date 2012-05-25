<html>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tokeninput.js"></script>
<script type="text/javascript" src="js/jquery.pajinate.js"></script>
<link rel="stylesheet" href="css/token-input.css" type="text/css" />
<link rel="stylesheet" href="css/token-input-facebook.css"
	type="text/css" />
<body>
	<div>
		<p>
			<span>search for code</span><input type="text" id="types" />
			<button id="search" value="search">search</button>
			<script type="text/javascript">
				$(document).ready(function() {
					$("#types").tokenInput("search/getAllTypes");
				
					$("#search").click(function() {
						var typesCommaSeparated = "";
						var pars = $("li.token-input-token").find("p");
						for(var i=0; i<pars.length; i++ ){
							typesCommaSeparated = typesCommaSeparated + pars[i].innerHTML + ",";	
						}
						typesCommaSeparated = typesCommaSeparated.substring(0, typesCommaSeparated.length-1);
						$.getJSON("search/searchCode/" + typesCommaSeparated, function (data) {
							var listcontent = "";
							$.each(data, function(key, val){
								listcontent = listcontent + '<li id="' + key + '"><pre>' + val.code + '</pre></li>';
							});	
							$("#searchResult").html(listcontent);						
							$('#paging_container').pajinate({items_per_page : 5});
						});
					});
				});
			</script>
		</p>
	</div>
	<div id="wrapper">
		<div id="paging_container" class="container">
			<h2>search result</h2>
			<div class="page_navigation"></div>
				<ul id="searchResult" class="content">
				</ul>
		</div>
	</div>			
	<div>
		<p>
			<span><a href="code">submit new code</a></span>
		</p>
	</div>
</body>
<html>