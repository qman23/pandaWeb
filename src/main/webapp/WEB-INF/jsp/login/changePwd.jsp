<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Welcome to Panda</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
	$('#confirmNewPassword').blur(function(){
		$('#messageDiv').html('');
		if($('input[id=confirmNewPassword]').val()!=$('input[id=newPassword]').val()){
			$('#messageDiv').html("<div class='alert alert-danger' role='alert'>"+
			"<span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> Please Confirm Your New Password!</div>");
		}
	});		
});
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
		
		<h3>Please fill in</h3>
		
	</div> <!-- /login-header -->
	
	<div id="login-content" class="clearfix">
	
	<form action="<%=basePath%>changePwd.do" method="post">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="username">Email address</label>
						<div class="controls">
							<input type="email" class="" id="email" name="email" placeholder="Email" required autofocus/>
						</div>
					</div>
		
					<div class="control-group">
						<label for="password" class="control-label">Current Password</label> 
						<input type="password" id="password" name="currentPassword"  placeholder="Password" required/>
					</div>
					
					<div class="control-group">
						<label for="inputPassword" class="control-label">New Password</label>
					    <input type="password" id="newPassword" name="newPassword" placeholder="New Password" required>
				    </div>
				    
					<div class="control-group">
						<label class="control-label" for="password">Confirm Password</label>
						 <div class="controls">
						  <input type="password"  placeholder="Confirm new Password" id="confirmNewPassword" required/>
						  </div>
					</div>
					
				<div class="pull-right" style="padding-top:10px">
					<button type="submit" class="btn btn-warning btn-large">
						Register
					</button>
				</div>
				</fieldset>
		</form>
		</div> <!-- /login-content -->
		<div id="login-extra">
			<p>Do you want to login in? <a href="<%=basePath%>login.do">Login in.</a></p>
			<p>Create a new user?<a href="<%=basePath%>register.do">Register.</a></p>
		</div> <!-- /login-extra -->
			<div  id="messageDiv">
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
