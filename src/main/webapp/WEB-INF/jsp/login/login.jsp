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
  <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        margin-top:10px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
    </style>
<script>
$(document).ready(function(){
	$("#register").click(function(){
		window.location.href="<%=basePath%>register.do";
	});
})
</script>
</head>

<body>

	<div class="container">
		<form class="form-signin" action="<%=basePath%>login.do" method="post">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="inputEmail" class="sr-only">Email address</label> <input
				type="email" id="email" name="email" class="form-control"
				placeholder="Email address" required autofocus> <label
				for="inputPassword" class="sr-only">Password</label><input
				type="password" id="password" name="password" class="form-control"
				placeholder="Password" required>
			<div class="checkbox">
				<label> <input type="checkbox" name="rememberMe" value="true">
					Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" id="sign"
				type="submit">Sign in</button>
			<button class="btn btn-lg btn-primary btn-block" id="register"
				type="button">Register</button>
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
