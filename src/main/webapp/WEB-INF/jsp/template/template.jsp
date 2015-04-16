<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>Welcome to Panda</title>
<link rel="stylesheet" href="<%=basePath%>style/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>style/css/template.css">
<link rel="stylesheet" href="<%=basePath%>style/css/bootstrap-table.css">
<link rel="shortcut icon" type="image/x-icon" href="<%=basePath%>style/img/ruyo_net_w_32.png" media="screen" /> 
<script src="<%=basePath%>style/js/jquery-1.11.2.min.js"></script>
<script src="<%=basePath%>style/js/bootstrap.min.js"></script>
<script src="<%=basePath%>style/js/bootstrap-table.js"></script>
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
			   	this.getBarDiv().hide();
		   }
 };
</script>
<style>
html {
	-ms-text-size-adjust: 100%;
	-webkit-text-size-adjust: 100%;
}

body {
	margin: 0;
}

.main-nav {
	margin-left: 1px;
	padding-top:20px
}

.main-nav.nav-tabs.nav-stacked>li {
	
}

.main-nav.nav-tabs.nav-stacked>li>a {
	padding: 10px 8px;
	font-size: 12px;
	font-weight: 600;
	color: #4A515B;
	background: #E9E9E9;
	background: -moz-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #FAFAFA),
		color-stop(100%, #E9E9E9));
	background: -webkit-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
	background: -o-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
	background: -ms-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
	background: linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA',
		endColorstr='#E9E9E9');
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA', endColorstr='#E9E9E9')";
	border: 1px solid #D5D5D5;
	border-radius: 4px;
}

.main-nav.nav-tabs.nav-stacked>li>a>span {
	color: #4A515B;
}

.main-nav.nav-tabs.nav-stacked>li.active>a, #main-nav.nav-tabs.nav-stacked>li>a:hover
	{
	color: #FFF;
	background: #3C4049;
	background: -moz-linear-gradient(top, #4A515B 0%, #3C4049 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #4A515B),
		color-stop(100%, #3C4049));
	background: -webkit-linear-gradient(top, #4A515B 0%, #3C4049 100%);
	background: -o-linear-gradient(top, #4A515B 0%, #3C4049 100%);
	background: -ms-linear-gradient(top, #4A515B 0%, #3C4049 100%);
	background: linear-gradient(top, #4A515B 0%, #3C4049 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B',
		endColorstr='#3C4049');
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B', endColorstr='#3C4049')";
	border-color: #2B2E33;
}

#main-nav.nav-tabs.nav-stacked>li.active>a, #main-nav.nav-tabs.nav-stacked>li>a:hover>span
	{
	color: #FFF;
}

.main-nav.nav-tabs.nav-stacked>li {
	margin-bottom: 4px;
}

.nav-header.collapsed>span.glyphicon-chevron-toggle:before {
	content: "\e114";
}

.nav-header>span.glyphicon-chevron-toggle:before {
	content: "\e113";
}

footer.duomi-page-footer {
	background-color: white;
}

footer.duomi-page-footer .beta-message {
	color: #a4a4a4;
}

footer.duomi-page-footer .beta-message a {
	color: #53a2e4;
}

footer.duomi-page-footer .list-inline a, footer.authenticated-footer .list-inline li
	{
	color: #a4a4a4;
	padding-bottom: 30px;
}

footer.duomi-page-footer {
	background-color: white;
}

footer.duomi-page-footer .beta-message {
	color: #a4a4a4;
}

footer.duomi-page-footer .beta-message a {
	color: #53a2e4;
}

footer.duomi-page-footer .list-inline a, footer.authenticated-footer .list-inline li
	{
	color: #a4a4a4;
	padding-bottom: 30px;
}

.secondmenu a {
	font-size: 12px;
	color: #4A515B;
	text-align: center;
	border-radius: 4px;
}

.secondmenu>li>a:hover {
	background-color: #6f7782;
	border-color: #428bca;
	color: #fff;
}

.secondmenu li.active {
	background-color: #6f7782;
	border-color: #428bca;
	border-radius: 4px;
}

.secondmenu li.active>a {
	color: #ffffff;
}

.navbar-static-top {
	background-color: #212121;
	margin-bottom: 5px;
}

.navbar-brand {
	background: url('../style/img/ruyo_net_w_32.png')
		no-repeat 10px 8px;
	display: inline-block;
	vertical-align: middle;
	padding-left: 50px;
	color: #fff;
}

.navbar-brand:hover {
	color: #fff;
}

.collapse.glyphicon-chevron-toggle, .glyphicon-chevron-toggle:before {
	content: "\e113";
}

.collapsed.glyphicon-chevron-toggle:before {
	content: "\e114";
}
</style>
<decorator:head/>
</head>

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
				<a class="navbar-brand" href="index.do">Panda</a>
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
					<li class="active"><a href="index.do">Dashboard <span
							class="sr-only">(current)</span></a></li>
					<li><a href="#">help</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">${CurrentUser}
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<%=basePath%>changePwd.do">Change Password</a></li>
							<li><a href="<%=basePath%>logout.do">logout</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<div style="height: 5px; display: none" id="progress_div">
			<div class="progress-bar progress-bar-success progress-bar-striped"
				id="progress" role="progressbar" aria-valuenow="40"
				aria-valuemin="0" aria-valuemax="100"></div>
		</div>
		<script>
		       progressUtils.enable();
				var progress=$("#progress_1").val();
				progressUtils.set(progress);
		</script>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<ul id="main-nav" class="main-nav nav nav-tabs nav-stacked" style="">
					<li class="${homeClass }"><a href="index.do"> <i class="glyphicon glyphicon-th-large"></i>
							Home
					</a></li>
					<li><a href="#systemSetting" class="nav-header collapsed"
						data-toggle="collapse"> <i class="glyphicon glyphicon-cog"></i>
							System Management <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
					</a>
						<ul id="systemSetting" class="nav nav-list secondmenu collapse ${SMcurrentTab}">
							<li class="${userManageClass}"><a  href="userManage.do"><i class="glyphicon glyphicon-user"></i>&nbsp;User Manage</a></li>
							<li><a href="#"><i class="glyphicon glyphicon-asterisk"></i>&nbsp;Role Manage</a></li>
							<li><a href="#"><i class="glyphicon glyphicon-eye-open"></i>&nbsp;Permission Manage</a></li>
						</ul></li>
					<li><a href="#configSetting" class="nav-header collapsed"
						data-toggle="collapse"> <i
							class="glyphicon glyphicon-credit-card"></i> Project Check  <span
							class="pull-right glyphicon  glyphicon-chevron-toggle"></span>
					</a>
						<ul id="configSetting" class="nav nav-list secondmenu collapse ${PCcurrentTab}">
							<li class="${spvClass }"><a href="spv.do"><i
									class="glyphicon glyphicon-globe"></i>&nbsp;Spv Check</a></li>
							<li class="${sprtClass }"><a href="sprt.do"><i
									class="glyphicon glyphicon-star-empty"></i>&nbsp;Sprt Check</a></li>
							<li class="${reportClass }"><a href="reports.do"><i class="glyphicon glyphicon-star"></i>&nbsp;Check Reports</a></li>
						</ul></li>

					<li><a href="#disSetting" class="nav-header collapsed"
						data-toggle="collapse"> <i class="glyphicon glyphicon-globe"></i>
							Task <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
					</a> 
						<ul id="disSetting" class="nav nav-list secondmenu collapse ${TKcurrentTab}">
							<li class="${taskGroupClass}"><a href="taskGroupManage.do" ><i class="glyphicon glyphicon-th-list"></i>&nbsp;Task Group Manage</a></li>
							<li class="${taskClass}"><a href="taskManage.do"><i class="glyphicon glyphicon-th-list"></i>&nbsp;Task Manage</a></li>
						</ul>
					</li>
					<li><a href="#dicSetting" class="nav-header collapsed"
						data-toggle="collapse"> <i class="glyphicon glyphicon-bold"></i>
							Sever health <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
					</a>
						<ul id="dicSetting" class="nav nav-list secondmenu collapse">
							<li><a href="#"><i
									class="glyphicon glyphicon-text-width"></i>&nbsp;health check</a></li>
								<li><a href="#"><i class="glyphicon glyphicon-th-list"></i>&nbsp;View log</a></li>	
						</ul></li>
					<li><a href="#"> <i class="glyphicon glyphicon-fire"></i>
							About Us 
					</a></li>

				</ul>
			</div>
			<div class="col-md-15">
				<decorator:body />
			</div>
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
