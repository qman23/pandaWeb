<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<script>
	window.operateEvents = {
		'click .like' : function(e, value, row, index) {
			$.ajax({
				   type: "get",
				   url: "<%=basePath%>/getTask.do",
				   data: "taskId="+row.taskId,
				   success: function(msg){
				     $('#modifyTask').find('.modal-body').html(msg);
				   }
				});
		},
		'click .remove' : function(e, value, row, index) {
				$('#deleteTaskId').val(row.taskId);
				$('#deleteModel').show();
		}
	};
	function operateFormatter(value, row, index) {
		
		return [ '<a class="like" href="#" title="Modify" data-toggle="modal" data-target="#modifyTask">',
				'<i class="glyphicon glyphicon-wrench"></i>', '</a>  ',
				'<a class="remove" href="#" title="Remove" data-toggle="modal" data-target="#deleteModel">',
				'<i class="glyphicon glyphicon-trash"></i>', '</a>' ].join('');
	}
	function getTasks() {
		$('#table-javascript').bootstrapTable(
				{
					method : 'get',
					url : '<%=basePath%>home/getTasks.do',
					queryParams : function(p) {
						return {
							groupId : $("#taskGroupNameSelect").children(
									'option:selected').attr("id")
						};
					},
					cache : false,
					height : 580,
					striped : true,
					pagination : true,
					pageSize : 50,
					pageList : [ 10, 25, 50, 100, 200 ],
					search : true,
					showColumns : true,
					showRefresh : true,
					minimumCountColumns : 2,
					clickToSelect : true,
					columns : [ {
						field : 'state',
						checkbox : true
					}, {
						field : 'taskId',
						title : 'Task ID',
						align : 'center',
						valign : 'bottom',
						sortable : true
					}, {
						field : 'taskName',
						title : 'Task Name',
						align : 'center',
						valign : 'middle',
						sortable : true
					}, {
						field : 'catalogName',
						title : 'Catalog Name',
						align : 'center',
						valign : 'top',
						sortable : true
					}, {
						field : 'createDate',
						title : 'Create Date',
						align : 'center',
						valign : 'top',
						sortable : true
					}, {
						field : 'lastModify',
						title : 'Last Modify',
						align : 'center',
						valign : 'top',
						sortable : true
					}, {
						field : 'comments',
						title : 'Comments',
						align : 'center',
						valign : 'top',
						sortable : true
					},
					{
						field : 'relativeId',
						title : 'relativeId',
						align : 'center',
						valign : 'top',
						sortable : true
					},
					{
						field : 'index',
						title : 'index',
						align : 'center',
						valign : 'top',
						sortable : true
					},
					 {
						field : 'operate',
						title : 'operate',
						align : 'center',
						valign : 'top',
						sortable : true,
						formatter : operateFormatter,
						events : operateEvents
					} ]
				});
	}
	$(document).ready(function() {
						getTasks();
						$("#taskGroupNameSelect").change(
								function() {
									$('#table-javascript').bootstrapTable(
											'refresh',
											{
												query : {
													groupId : $(this).children(
															'option:selected')
															.attr("id")
												}
											});
								});
						$("input[name=taskCatalogId]").each(function(i) {$(this).click(function() {
																$("#parameterDiv").html("");
																$("#parameterDiv").html($("#"+$(this).attr("class")+ "").html())});
						});
						
						$('#newTaskBtn').click(function(){
							$('#taskGroupid').val($('#taskGroupNameSelect').children('option:selected').attr("id"));
						})
						
						$('#deleteTaskBtn').click(function(){
							$.ajax({
								   type: "post",
								   url: "<%=basePath%>home/deleteTask.do",
								   data: "taskId="+$('#deleteTaskId').val(),
								   success: function(msg){
								     $('#table-javascript').bootstrapTable(
															'refresh',
															{
																query : {
																	groupId : $('#taskGroupNameSelect').children(
																			'option:selected')
																			.attr("id")
																}
															});
									 $('#deleteModel').hide();
								   }
								});
						})
					});
</script>
</head>
<body>
	<c:choose>
		<c:when test="${not empty TaskGroupList}">
			<div class="col-md-10 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<label for="TaskGroupName" class="control-label"><small>TasK
								Group Name</small></label> <select class="form-control" id="taskGroupNameSelect"
							style="width: auto">
							<c:forEach items="${TaskGroupList}" var="taskGroup">
								<c:choose>
									<c:when test="${taskGroup.groupId eq groupId }">
										<option class="groupNameOption" selected id="${taskGroup.groupId}">${taskGroup.groupName}</option>
									</c:when>
									<c:otherwise>
									 	<option class="groupNameOption"  id="${taskGroup.groupId}">${taskGroup.groupName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select> 
						<label for="TaskGroupName" class="control-label"><small>New
								Task</small></label> <input class=" btn btn-default" type="button" value="New" id="newTaskBtn"
							data-toggle="modal" data-target="#addNewTask">
					</div>
				</div>
				<table id="table-javascript"></table>
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-md-10 main">
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
	<!-- create task details page -->
	<div class="modal fade" id="addNewTask" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Task initialize Details</h4>
				</div>
				<div class="modal-body" style="height: 700px">
					<form role="form" action="saveTask.do"
						method="Post">
						<input type="hidden" id="taskGroupid" name="taskGroupId"></input>
						<div class="form-group">
							<label for="TaskGroupName" class="col-sm-3 control-label"><small>TasK
									Name</small></label>
							<div class="col-sm-12">
								<input type="text" class="form-control" name="taskName"
									placeholder="Task Name" required>
							</div>
						</div>
						<div class="form-group">
							<label for="TaskGroupDesc" class="col-sm-4 control-label"><small>Task
									Comments</small></label>
							<div class="col-sm-12">
								<textarea class="form-control" rows="4" placeholder="Comments"
									name="comments" required></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="TaskGroupDesc" class="col-sm-4 control-label"><small>Relative
									Task Id</small></label>
							<div class="col-sm-12" >
								<input type="text" class="form-control" name="relativeTaskId"
									placeholder="Relative Task Id" >
							</div>
						</div>
						<div class="form-group">
							<label for="TaskGroupDesc" class="col-sm-4 control-label"><small>
									Task Index</small></label>
							<div class="col-sm-12">
								<input type="text" class="form-control" name="taskIndex"
									placeholder="Task Index" required>
							</div>
						</div>
						<div class="form-group">
							<label for="TaskGroupDesc" class="col-sm-4 control-label"><small>Task
									Catalog</small></label>
							<div class="col-sm-12">
								<label class="radio-inline"> <input type="radio"
									name="taskCatalogId" id="accessWebRadio"
									class="accessWebParameter" value="2" checked>
									Access Web Task
								</label> <label class="radio-inline"> <input type="radio"
									name="taskCatalogId" id="scriptTaskRadio"
									class="scriptTaskParameter" value="1"> Script
									Task
								</label> <label class="radio-inline"> <input type="radio"
									name="taskCatalogId" id="validateRadio"
									class="validateTaskParameter" value="3"> Validate
									Task
								</label>
								<label class="radio-inline"> <input type="radio"
									name="taskCatalogId" id="validateRadio"
									class="accessServerParameter" value="4"> Access Server
									Task
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="TaskGroupDesc" class="col-sm-4 control-label"><small>Task
									Parameter</small></label>
							<div class="col-sm-12" id="parameterDiv">
								<label for="TaskGroupName" class="col-sm-3 control-label"><small>Server
										Url</small></label> <input type="text" class="form-control" name="serverUrl"
									placeholder="Server Url" required> <label
									for="TaskGroupName" class="col-sm-3 control-label"><small>User
										Name</small></label> <input type="text" class="form-control" name="userName"
									placeholder="User Name" > <label
									for="TaskGroupName" class="col-sm-3 control-label"><small>User
										Password</small></label> <input type="password" class="form-control"
									name="userPassword" placeholder="User Password" >
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

	<!-- Access Web Task parameter cut -->
	<div style="display: none" id="accessWebParameter">
		<label for="TaskGroupName" class="col-sm-3 control-label"><small>Server
				Url</small></label> <input type="text" class="form-control" name="serverUrl"
			placeholder="Server Url" required> <label for="TaskGroupName"
			class="col-sm-3 control-label"><small>User Name</small></label> <input
			type="text" class="form-control" name="userName"
			placeholder="User Name" > <label for="TaskGroupName"
			class="col-sm-3 control-label"><small>User Password</small></label> <input
			type="password" class="form-control" name="userPassword"
			placeholder="User Password" >
	</div>
	<!-- accessServerParameter -->
	<div style="display: none" id="accessServerParameter">
		<label for="TaskGroupName" class="col-sm-3 control-label"><small>Server
				Url</small></label> <input type="text" class="form-control" name="serverUrl"
			placeholder="Server Url" required> <label for="TaskGroupName"
			class="col-sm-3 control-label"><small>User Name</small></label> <input
			type="text" class="form-control" name="userName"
			placeholder="User Name" required> <label for="TaskGroupName"
			class="col-sm-3 control-label"><small>User Password</small></label> <input
			type="password" class="form-control" name="userPassword"
			placeholder="User Password" required><label for="TaskGroupName"
			class="col-sm-3 control-label"><small>Port</small></label> <input
			type="text" class="form-control" name="port"
			placeholder="Port" required>
	</div>
	<!-- Script Task  parameter cut -->
	<div style="display: none" id="scriptTaskParameter">
		<label for="TaskGroupName" class="col-sm-3 control-label"><small>Script
		</small></label> <textarea rows="2" class="form-control" name="script"
			placeholder="Script need to execute" required></textarea> <label
			for="TaskGroupName" class="col-sm-3 control-label"><small>Expect
				Result </small></label> <input type="text" class="form-control" name="expectResult"
			placeholder="Expect Result" required></input>
	</div>
	<!-- Validate Task parameter cut -->
	<div style="display: none" id="validateTaskParameter">
		<label for="TaskGroupName" class="col-sm-4 control-label"><small>Validate
				Expression </small></label> <input type="text" class="form-control" name="validateExpression"
			placeholder="Validate Expression" required> <label
			for="TaskGroupName" class="col-sm-3 control-label"><small>Expect
				Result </small></label> <input type="text" class="form-control" name="expectResult"
			placeholder="Expect Result" required>
	</div>


	<!-- create task details page -->
	<div class="modal fade" id="modifyTask" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Change Task Details</h4>
				</div>
				<div class="modal-body" style="height: 700px">
					<img alt="Loading" src="../style/img/loading.gif">
				</div>
			</div>
		</div>
	</div>
	
	<!-- delete task confirm -->
	<div class="modal fade" id="deleteModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<input type="hidden" id="deleteTaskId"></input>
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Delete task 
						confirm</h4>
				</div>
				<div class="modal-body">Do you want delete the task?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close
					</button>
					<a class="btn btn-danger" id="deleteTaskBtn" >Delete</a>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>
</body>