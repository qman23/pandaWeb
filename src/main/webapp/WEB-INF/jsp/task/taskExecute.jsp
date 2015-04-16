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
<script>
$(document).ready(function(){
$("#executeTask").click(function(){
		 window.document.location.href="<%=basePath%>"+'home/executeTaskBygroupId.do?groupId='+$('#taskGroupNameSelect').children('option:selected').attr("id")
});
})
</script>
</head>
<body>
<c:choose>
		<c:when test="${not empty TaskGroupList}">
			<div class="col-md-10 col-md-offset-3  col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<label for="TaskGroupName" class="control-label"><small>TasK
								Group Name</small></label> <select class="form-control" id="taskGroupNameSelect"
							style="width: auto">
							<c:forEach items="${TaskGroupList}" var="taskGroup">
								<option class="groupNameOption" id="${taskGroup.groupId}">${taskGroup.groupName}</option>
							</c:forEach>
						</select> <label for="TaskGroupName" class="control-label"><small>Execute
								Task</small></label> <input class=" btn btn-default" type="button" value="Execute" id="executeTask"
							>
					</div>
				</div>
				<table id="table-javascript"></table>
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-md-10 col-md-offset-3  col-md-offset-2 main">
				<div class="alert alert-warning" role="alert">
					<h3>
						<small>There is no Task Group Exist.Please create!</small>
					</h3>
				</div>
				<a class="btn btn-success" href="taskGroupManage.do">Create Task
					Group</a>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>