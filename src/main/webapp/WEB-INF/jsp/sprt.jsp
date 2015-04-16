<body>
	<div class="col-md-10 col-md-offset-3  col-md-offset-2 main">
		<div class="panel panel-default">
			<div class="panel-heading">
				Web
				<button type="button" class="btn btn-default navbar-btn">Begin
					Check</button>
			</div>
			<div class="panel-body">

				<div class="row placeholders">
					<div class="col-xs-6 col-md-4 placeholder">
						<a href="#"><span class="glyphicon glyphicon-globe"
							aria-hidden="true"></span></a>
						<h4>Production Web Status</h4>
						<p>
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
						</p>
						<p>
							<a class="btn btn-primary" href="#" role="button" id="click_connect">Connect to Server
								&raquo;
							</a>
							<a class="btn btn-default" href="#" role="button">View details &raquo;</a>
						</p>
						
						<script type="text/javascript">
						$(function(){
							$("#select_drop_down").hide();
							$("#click_connect").click( function(){
								$("#click_connect").attr({"disabled":"disabled"});
								$.ajax({
									type:"GET",
									url:"getLogs.do",
									data:"userid=123&email=lee@cn.ibm.com",
									dataType:"json",
									success: function(msg){
										$("#click_connect").removeAttr("disabled");
										$("#select_drop_down").empty();
										$.each(msg,function(){
											var str = '<option value='+this+'>'+this+'</option>';
											$("#select_drop_down").append(str);
											
										});
										$("#select_drop_down").show();
									}
								});
								
							});
							
						});
						</script>
						<select id="select_drop_down" class="dropdown">
						</select>
					</div>
					<div class="col-xs-6 col-md-4 placeholder">
						<a href="#"><span class="glyphicon glyphicon-globe"
							aria-hidden="true"></span></a>
						<h4>CDT Web Status</h4>
						<p>
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
						</p>
						<p>
							<a class="btn btn-default" href="#" role="button">Check
								&raquo;</a> <a class="btn btn-default" href="#" role="button">View
								details &raquo;</a>
						</p>
					</div>
					<div class="col-xs-6 col-md-4 placeholder">
						<a href="#"><span class="glyphicon glyphicon-globe"
							aria-hidden="true"></span></a>
						<h4>DST Web Status</h4>
						<p>
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
						</p>
						<p>
							<a class="btn btn-default" href="#" role="button">Check
								&raquo;</a> <a class="btn btn-default" href="#" role="button">View
								details &raquo;</a>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				Db
				<button type="button" class="btn btn-default navbar-btn">Begin
					Check</button>
			</div>
			<div class="panel-body">
				<div class="row placeholders">
					<div class="col-xs-6 col-md-4 placeholder">
						<a href="#"><span class="glyphicon glyphicon-th"
							aria-hidden="true"></span></a>
						<h4>Production db Status</h4>
						<p>
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
						</p>
						<p>
							<a class="btn btn-default" href="#" role="button">Check
								&raquo;</a> <a class="btn btn-default" href="#" role="button">View
								details &raquo;</a>
						</p>
					</div>
					<div class="col-xs-6 col-md-4 placeholder">
						<a href="#"><span class="glyphicon glyphicon-th"
							aria-hidden="true"></span></a>
						<h4>CDT db Status</h4>
						<p>
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
						</p>
						<p>
							<a class="btn btn-default" href="#" role="button">Check
								&raquo;</a> <a class="btn btn-default" href="#" role="button">View
								details &raquo;</a>
						</p>
					</div>
					<div class="col-xs-6 col-md-4 placeholder">
						<a href="#"><span class="glyphicon glyphicon-th"
							aria-hidden="true"></span></a>
						<h4>DST db Status</h4>
						<p>
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
						</p>
						<p>
							<a class="btn btn-default" href="#" role="button">Check
								&raquo;</a> <a class="btn btn-default" href="#" role="button">View
								details &raquo;</a>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				Dpropr
				<button type="button" class="btn btn-default navbar-btn">Begin
					Check</button>
			</div>
			<div class="panel-body">
				<div class="row placeholders">
					<div class="col-xs-6 col-md-4 placeholder">
						<a href="#"><span class="glyphicon glyphicon-refresh"
							aria-hidden="true"></span></a>
						<h4>Production dpropr Status</h4>
						<p>
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
						</p>
						<p>
							<a class="btn btn-default" href="#" role="button">Check
								&raquo;</a> <a class="btn btn-default" href="#" role="button">View
								details &raquo;</a>
						</p>
					</div>
					<div class="col-xs-6 col-md-4 placeholder">
						<a href="#"><span class="glyphicon glyphicon-refresh"
							aria-hidden="true"></span></a>
						<h4>CDT dpropr Status</h4>
						<p>
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
						</p>
						<p>
							<a class="btn btn-default" href="#" role="button">Check
								&raquo;</a> <a class="btn btn-default" href="#" role="button">View
								details &raquo;</a>
						</p>
					</div>
					<div class="col-xs-6 col-md-4 placeholder">
						<a href="#"><span class="glyphicon glyphicon-refresh"
							aria-hidden="true"></span></a>
						<h4>DST dpropr Status</h4>
						<p>
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
						</p>
						<p>
							<a class="btn btn-default" href="#" role="button">Check
								&raquo;</a> <a class="btn btn-default" href="#" role="button">View
								details &raquo;</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>