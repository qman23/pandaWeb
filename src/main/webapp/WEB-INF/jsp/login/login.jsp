<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>Welcome to Panda</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="<%=basePath%>style/css/bootstrap.min.css">
<link href="<%=basePath%>style/css/bootstrap-responsive.min.css" rel="stylesheet" />
 
<script src="<%=basePath%>style/js/jquery-1.11.2.min.js"></script>
<script src="<%=basePath%>style/js/bootstrap.min.js"></script>

<link href="<%=basePath%>style/css/font-awesome.css" rel="stylesheet" />    
<link href="<%=basePath%>style/css/adminia.css" rel="stylesheet" /> 
<link href="<%=basePath%>style/css/adminia-responsive.css" rel="stylesheet" /> 
<link href="<%=basePath%>style/css/pages/login.css" rel="stylesheet" /> 

<link rel="shortcut icon" type="image/x-icon" href="<%=basePath%>style/img/ruyo_net_w_32.png" media="screen" /> 
<script>
$(document).ready(function(){
	$("#register").click(function(){
		window.location.href="<%=basePath%>register.do";
	});
	$('#change').click(function(){
		window.location.href="<%=basePath%>changePwd.do";
	});
})
</script>
</head>

<body>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 				
			</a>	
			<div class="nav-collapse">
				<ul class="nav pull-right">	
					<li class="">			
					</li>
				</ul>
			</div> <!-- /nav-collapse -->
		</div> <!-- /container -->
	</div> <!-- /navbar-inner -->
</div> <!-- /navbar -->
<div id="login-container">
	<div id="login-header">
		
		<h3>Sign in</h3>
		
	</div> <!-- /login-header -->
	
	<div id="login-content" class="clearfix">
	
	<form action="<%=basePath%>login.do" method="post">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="username">Username</label>
						<div class="controls">
							<input type="email" class="" id="email" name="email" placeholder="Email"  required autofocus/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">Password</label>
						<div class="controls">
							<input type="password" class="" id="password" name="password" placeholder="Password" required/>
						</div>
					</div>
				<div id="remember-me" class="pull-left" style="padding-top:10px">
					<input type="checkbox" name="rememberMe" id="remember" value="true"/>
					<label id="remember-label" for="remember">Remember Me</label>
				</div>
				
				<div class="pull-right" style="padding-top:10px">
					<button type="submit" class="btn btn-warning btn-large">
						Login
					</button>
				</div>
				</fieldset>
		</form>
		</div> <!-- /login-content -->
		<div id="login-extra">
			<p>Don't have an account? <a href="<%=basePath%>register.do">Sign Up.</a></p>
			<p>Need Change Password?<a href="<%=basePath%>changePwd.do">Change.</a></p>
		</div> <!-- /login-extra -->
		
			<div id="messageDiv">
				<c:if test="${not empty message  }">
					<div class="alert alert-${messageType}" role="alert">
						<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
						${message}
					</div>
				</c:if>
			</div>
</div> <!-- /login-wrapper -->
</body>
</html>
