<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
$(document).ready(function(){
	$("#modifyCatalog").find("input[name=taskCatalogId]").each(function(i) {
		if($(this).attr("checked")=="checked"){
			$("#parameterDivModify").html("");
			$("#parameterDivModify").html($("#"+$(this).attr("class")+ "").html());
		}
		$(this).click(function() {
			$("#parameterDivModify").html("");
			$("#parameterDivModify").html($("#"+$(this).attr("class")+ "").html());
		})
	});
})
</script>
</head>
<body>
	<form role="form" action="updateTask.do" method="Post">
		<input type="hidden" id="taskGroupid" name="taskGroupId" value="${task.groupId}"></input>
		<input type="hidden" id="taskGroupid" name="taskId" value="${task.taskId}"></input>
		<div class="form-group">
			<label for="TaskGroupName" class="col-sm-3 control-label"><small>TasK
					Name</small></label>
			<div class="col-sm-12">
				<input type="text" class="form-control" name="taskName"
					placeholder="Task Name" value="${task.taskName}" required>
			</div>
		</div>
		<div class="form-group">
			<label for="TaskGroupDesc" class="col-sm-4 control-label"><small>Task
					Comments</small></label>
			<div class="col-sm-12">
				<textarea class="form-control" rows="4" placeholder="Comments"
					name="comments" required>${task.comments}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="TaskGroupDesc" class="col-sm-4 control-label"><small>Relative
					Task Id</small></label>
			<div class="col-sm-12">
				<input type="text" class="form-control" name="relativeTaskId"
					placeholder="Relative Task Id" value="${task.relativeId}">
			</div>
		</div>
		<div class="form-group">
			<label for="TaskGroupDesc" class="col-sm-4 control-label"><small>
					Task Index</small></label>
			<div class="col-sm-12">
				<input type="text" class="form-control" name="taskIndex"
					placeholder="Task Index" required value="${task.index}">
			</div>
		</div>
		<div class="form-group">
			<label for="TaskGroupDesc" class="col-sm-4 control-label"><small>Task
					Catalog</small></label>
			<div class="col-sm-12" id="modifyCatalog">
				<label class="radio-inline"> <input type="radio"
					name="taskCatalogId" id="accessWebRadio"
					class="accessWebParameterModify" value="2"
					<c:if test="${task.catalogId eq 2}">checked</c:if>> Access Web Task
				</label>
				 <label class="radio-inline"> <input type="radio"
					name="taskCatalogId" id="validateRadio"
					class="accessServerParameterModify" value="4"
					<c:if test="${task.catalogId eq 4}">checked</c:if>> Access Server Task
				</label>
				 <label class="radio-inline"> <input type="radio"
					name="taskCatalogId" id="scriptTaskRadio"
					class="scriptTaskParameterModify" value="1"
					<c:if test="${task.catalogId eq 1}">checked</c:if>> Script Task
				</label> <label class="radio-inline"> <input type="radio"
					name="taskCatalogId" id="validateRadio"
					class="validateTaskParameterModify" value="3"
					<c:if test="${task.catalogId eq 3}">checked</c:if>> Validate Task
				</label>
				
			</div>
		</div>
		<div class="form-group">
			<label for="TaskGroupDesc" class="col-sm-4 control-label"><small>Task
					Parameter</small></label>
			<div class="col-sm-12" id="parameterDivModify"></div>
		</div>
		<div class="form-group">
			<div style="float: right; padding-top: 20px; padding-right: 15px;">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="submit" class="btn btn-primary" id="addGroup">Update</button>
			</div>
		</div>
	
	</form>
	<!-- Access Web Task parameter cut -->
		<div style="display: none" id="accessWebParameterModify">
			<label for="TaskGroupName" class="col-sm-3 control-label"><small>Server
					Url</small></label> <input type="text" class="form-control" name="serverUrl"
				placeholder="Server Url" required value="${task.data.Url }"> <label
				for="TaskGroupName" class="col-sm-3 control-label"><small>User
					Name</small></label> <input type="text" class="form-control" name="userName"
				placeholder="User Name" required value="${task.data.UserName }"> <label
				for="TaskGroupName" class="col-sm-3 control-label"><small>User
					Password</small></label> <input type="password" class="form-control"
				name="userPassword" placeholder="User Password" required value="${task.data.Password }">
		</div>
		<!-- Script Task  parameter cut -->
		<div style="display: none" id="scriptTaskParameterModify">
			<label for="TaskGroupName" class="col-sm-3 control-label"><small>Script
			</small></label> <input type="text" class="form-control" name="script"
				placeholder="Script need to execute" required value="${task.data.script }"> <label
				for="TaskGroupName" class="col-sm-3 control-label"><small>Expect
					Result </small></label> <input type="text" class="form-control" name="expectResult"
				placeholder="Expect Result" required value="${task.data.expectResult }">
		</div>
		<!-- Validate Task parameter cut -->
		<div style="display: none" id="validateTaskParameterModify">
			<label for="TaskGroupName" class="col-sm-4 control-label"><small>Validate
					Expression </small></label> <input type="text" class="form-control"
				name="validateExpression" placeholder="Validate Expression" required value="${task.data.validateExpression }">
			<label for="TaskGroupName" class="col-sm-3 control-label"><small>Expect
					Result </small></label> <input type="text" class="form-control" name="expectResult"
				placeholder="Expect Result" required value="${task.data.expectResult }">
		</div>
		<!-- Access Server Task parameter cut -->
		<div style="display: none" id="accessServerParameterModify">
			<label for="TaskGroupName" class="col-sm-3 control-label"><small>Server
					Url</small></label> <input type="text" class="form-control" name="serverUrl"
				placeholder="Server Url" required value="${task.data.hostName }"> <label
				for="TaskGroupName" class="col-sm-3 control-label"><small>User
					Name</small></label> <input type="text" class="form-control" name="userName"
				placeholder="User Name" required value="${task.data.userName }"> <label
				for="TaskGroupName" class="col-sm-3 control-label"><small>User
					Password</small></label> <input type="password" class="form-control"
				name="userPassword" placeholder="User Password" required value="${task.data.passWord }">
		</div>
</body>
</html>