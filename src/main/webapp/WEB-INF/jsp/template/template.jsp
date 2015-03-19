<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" href="<%=basePath%>style/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>style/css/template.css">
<script src="<%=basePath%>style/js/jquery-1.11.2.min.js"></script>
<script src="<%=basePath%>style/js/bootstrap.min.js"></script>
<script>
 var progressUtils={
		   'getBarDiv':function(){
			   return $("#progress_div");
		   },
		   'getBar':function(){
			   return $("#progress");
		   },
		   'set':function(present){
			   this.getBar().css("width",present);
		   },
		   'enable':function(){
			  
			   this.getBarDiv().show();
		   },
		   'disable':function(){
			   if(this.getBar().css("width")=='100%'){
			   	this.getBarDiv().hide();
			   }
		   }
 };
</script>
<html>
<body>
	<input type="hidden" id="progress_1" value="25%">

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Panda</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Go</button>
				</form>
				<ul class="nav navbar-nav  navbar-right">
					<li class="active"><a href="#">Dashboard <span
							class="sr-only">(current)</span></a></li>
					<li><a href="#">help</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Profile
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li class="divider"></li>
							<li><a href="#">Separated link</a></li>
							<li class="divider"></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<div style="height: 8px;display:none" id="progress_div" >
			<div class="progress-bar progress-bar-success progress-bar-striped" id="progress"
				role="progressbar" aria-valuenow="40" aria-valuemin="0"
				aria-valuemax="100">
			</div>
		</div>
		<script>
		       progressUtils.enable();
				var progress=$("#progress_1").val();
				progressUtils.set(progress);
		</script>
	</nav>

	<div class="container-fluid">

		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="${homeClass}" id="homeLink"><a href="index.do">Overview <span
							class="sr-only">(current)</span></a></li>
					<li class="${spvClass}"><a href="spv.do" id="spvLink">SPV</a></li>
					<li class="${sprtClass}"><a href="sprt.do" id="sprtLink">SPRT</a></li>
					<li class="${reportClass}"><a href="reports.do" id="reportLink">Reports</a></li>
				</ul>
			</div>

			<decorator:body />
			<input type="hidden" id="progress_2" value="100%">
				<script>
							var progress2=$("#progress_2").val();
							progressUtils.set(progress2);
				</script>
		</div>
	</div>
	
</body>
	<script>
				//progressUtils.disable();
	</script>
</html>
