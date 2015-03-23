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
<script src="<%=basePath%>style/js/jquery-1.11.2.min.js"></script>
<script src="<%=basePath%>style/js/bootstrap.min.js"></script>
<style>
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

#register {
	margin-top: 10px;
}
</style>
<script>
$(document).ready(function(){
	$("#cancel").click(function(){
		window.location.href="<%=basePath%>login.do";
		});
	$('#confirmPassword').blur(function(){
			$('#messageDiv').html('');
			if($('input[id=password]').val()!=$('input[id=confirmPassword]').val()){
				$('#messageDiv').html("<div class='alert alert-danger' role='alert'>"+
				"<span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> Please Confirm Your Password!</div>");
			}
		});
	})
</script>
</head>

<body>

	<div class="container">
		<form class="form-signin" action="<%=basePath%>register.do"
			method="post">
			<h2 class="form-signin-heading">Please register</h2>
			<label for="inputEmail" class="sr-only">Email address</label> <input
				type="email" id="email" name="email" class="form-control"
				placeholder="Email address" required autofocus> <label
				for="inputPassword" class="sr-only">Password</label> <input
				type="password" id="password" name="password" class="form-control"
				placeholder="Password" required> <label for="inputPassword"
				class="sr-only">Confirm Password</label> <input type="password"
				id="confirmPassword" class="form-control" placeholder="Password"
				required>

			<button class="btn btn-lg btn-primary btn-block" id="register"
				type="submit">Register</button>
			<button class="btn btn-lg btn-primary btn-block" id="cancel"
				type="button">Cancel</button>
			
		</form>
		<div class="row">
		<div class="col-md-4"></div>
			<div class="col-md-4" id="messageDiv">
				<c:if test="${not empty message  }">
					<div class="alert alert-danger" role="alert">
						<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
						${message}
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
