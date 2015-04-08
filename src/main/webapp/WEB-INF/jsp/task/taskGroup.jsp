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
	$(document).ready(function() {
		$("a[name=modifyTaskGroup]").click(function() {
			var tr = $(this).parent().parent();
			var groupId = tr.find(".groupId").html();
			var groupName = tr.find(".groupName").html();
			var description = tr.find(".description").html();
			var createDate = tr.find(".createDate").html();
			$("#groupId").val(groupId);
			$("#groupName").val(groupName);
			$("#description").val(description);
		});
		 
	})
	function deleteGroup(id){
			$(".btn-danger").attr('href','<%=basePath%>home/deleteTaskGroup.do?groupId='+id);
	}
</script>
</head>
<body>
	<c:choose>
		<c:when test="${not empty TaskGroupList}">
			<div class="col-md-10 col-md-offset-3  col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						Task Group
						<button class="btn btn-default" data-toggle="modal"
							data-target="#myModal">New Task Group</button>
					</div>
					
				</div>
					<table data-toggle="table">
							<thead>
								<tr>
									<th>Task Group ID</th>
									<th>Task Group Name</th>
									<th>Description</th>
									<th>Modify Time</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${TaskGroupList}" var="taskGroup">
									<tr>
										<td class="groupId">${taskGroup.groupId}</td>
										<td class="groupName">${taskGroup.groupName}</td>
										<td class="description">${taskGroup.description}</td>
										<td class="createDate">${taskGroup.createDate}</td>
										<td><a href="#" data-toggle="modal"
											data-target="#modifyModal" name="modifyTaskGroup"><span
												class="glyphicon glyphicon-wrench" aria-hidden="true"></span></a>
											<a href="#" data-toggle="modal" data-target="#myModal2"
											onClick="deleteGroup(${taskGroup.groupId})"><span
												class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-md-10 col-md-offset-3  col-md-offset-2 main">
				<div class="alert alert-warning" role="alert">
					<h3>
						<small>There is no Task Group Exist.Please create!</small>
					</h3>
				</div>
				<button type="button" class="btn btn-success" data-toggle="modal"
					data-target="#myModal">Create Task Group</button>
			</div>
		</c:otherwise>
	</c:choose>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Create Task Group</h4>
				</div>
				<div class="modal-body" style="height: 300px">
					<form role="form" action="<%=basePath%>home/taskGroupManage.do"
						method="Post">
						<div class="form-group">
							<label for="TaskGroupName" class="col-sm-3 control-label"><small>TasK
									Group Name</small></label>
							<div class="col-sm-12">
								<input type="text" class="form-control" name="groupName"
									placeholder="Group Name" required>
							</div>
						</div>
						<div class="form-group">
							<label for="TaskGroupDesc" class="col-sm-4 control-label"><small>Task
									Group Description</small></label>
							<div class="col-sm-12">
								<textarea class="form-control" rows="4"
									placeholder="Description" name="description" required></textarea>
							</div>
						</div>
						<div class="form-group">
							<div
								style="float: right; padding-top: 20px; padding-right: 15px;">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary" id="addGroup">Add</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Modify Task Group</h4>
				</div>
				<div class="modal-body" style="height: 300px">
					<form role="form" action="<%=basePath%>home/taskGroupModify.do"
						method="Post">
						<input type="hidden" id="groupId" name="groupId">
						<div class="form-group">
							<label for="TaskGroupName" class="col-sm-3 control-label"><small>TasK
									Group Name</small></label>
							<div class="col-sm-12">
								<input type="text" class="form-control" id="groupName"
									name="groupName" placeholder="Group Name" required>
							</div>
						</div>
						<div class="form-group">
							<label for="TaskGroupDesc" class="col-sm-4 control-label"><small>Task
									Group Description</small></label>
							<div class="col-sm-12">
								<textarea class="form-control" rows="4"
									placeholder="Description" name="description" id="description"
									required></textarea>
							</div>
						</div>
						<div class="form-group">
							<div
								style="float: right; padding-top: 20px; padding-right: 15px;">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary" id="addGroup">Update</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- /.modal -->

	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Delete task group
						confirm</h4>
				</div>
				<div class="modal-body">Do you want delete the task group and
					all the tasks belongs the group?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close
					</button>
					<a class="btn btn-danger">Delete</a>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>
</body>
</html>