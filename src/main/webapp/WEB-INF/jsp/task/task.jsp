<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			alert('You click like action, row: ' + JSON.stringify(row));
		},
		'click .remove' : function(e, value, row, index) {
			$table.bootstrapTable('remove', {
				field : 'taskId',
				values : [ row.id ]
			});
		}
	};
	function operateFormatter(value, row, index) {
		return [ '<a class="like" href="javascript:void(0)" title="Modify">',
				'<i class="glyphicon glyphicon-wrench"></i>', '</a>  ',
				'<a class="remove" href="javascript:void(0)" title="Remove">',
				'<i class="glyphicon glyphicon-remove"></i>', '</a>' ].join('');
	}
	function getTasks() {
		$('#table-javascript').bootstrapTable(
				{
					method : 'get',
					url : 'getTasks.do',
					queryParams : function(p) {
						return {
							groupId : $("#taskGroupNameSelect").children(
									'option:selected').attr("id")
						};
					},
					cache : false,
					height : 400,
					striped : true,
					pagination : true,
					pageSize : 50,
					pageList : [ 10, 25, 50, 100, 200 ],
					search : true,
					showColumns : true,
					showRefresh : true,
					minimumCountColumns : 2,
					clickToSelect : true,
					cardView : true,
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
					}, {
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
	$(document)
			.ready(
					function() {
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
											})
								});
						$("input[name=inlineRadioOptions]").each(
										function(i) {$(this).click(function() {
																$("#parameterDiv").html("");
																$("#parameterDiv").html($("#"+$(this).attr("class")+ "").html())});
						});
					});
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
						</select> <label for="TaskGroupName" class="control-label"><small>New
								Task</small></label> <input class=" btn btn-default" type="button" value="New"
							data-toggle="modal" data-target="#addNewTask">
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
				<div class="modal-body" style="height: 650px">
					<form role="form" action="<%=basePath%>home/taskGroupManage.do"
						method="Post">
						<div class="form-group">
							<label for="TaskGroupName" class="col-sm-3 control-label"><small>TasK
									Name</small></label>
							<div class="col-sm-12">
								<input type="text" class="form-control" name="groupName"
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
									placeholder="Relative Task Id" required>
							</div>
						</div>
						<div class="form-group">
							<label for="TaskGroupDesc" class="col-sm-4 control-label"><small>
									Task Index</small></label>
							<div class="col-sm-12">
								<input type="text" class="form-control" name="relativeTaskId"
									placeholder="Task Index" required>
							</div>
						</div>
						<div class="form-group">
							<label for="TaskGroupDesc" class="col-sm-4 control-label"><small>Task
									Catalog</small></label>
							<div class="col-sm-12">
								<label class="radio-inline"> <input type="radio"
									name="inlineRadioOptions" id="accessWebRadio"
									class="accessWebParameter" value="option1" checked>
									Access Web Task
								</label> <label class="radio-inline"> <input type="radio"
									name="inlineRadioOptions" id="scriptTaskRadio"
									class="scriptTaskParameter" value="option2"> Script
									Task
								</label> <label class="radio-inline"> <input type="radio"
									name="inlineRadioOptions" id="validateRadio"
									class="validateTaskParameter" value="option3"> Validate
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
									placeholder="User Name" required> <label
									for="TaskGroupName" class="col-sm-3 control-label"><small>User
										Password</small></label> <input type="password" class="form-control"
									name="userPassword" placeholder="User Password" required>
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

	<!-- task parameter cut -->
	<div style="display: none" id="accessWebParameter">
		<label for="TaskGroupName" class="col-sm-3 control-label"><small>Server
				Url</small></label> <input type="text" class="form-control" name="serverUrl"
			placeholder="Server Url" required> <label for="TaskGroupName"
			class="col-sm-3 control-label"><small>User Name</small></label> <input
			type="text" class="form-control" name="userName"
			placeholder="User Name" required> <label for="TaskGroupName"
			class="col-sm-3 control-label"><small>User Password</small></label> <input
			type="password" class="form-control" name="userPassword"
			placeholder="User Password" required>
	</div>
	<div style="display: none" id="scriptTaskParameter">
		<label for="TaskGroupName" class="col-sm-3 control-label"><small>Script
		</small></label> <input type="text" class="form-control" name="serverUrl"
			placeholder="Script need to execute" required> <label
			for="TaskGroupName" class="col-sm-3 control-label"><small>Expect
				Result </small></label> <input type="text" class="form-control" name="userName"
			placeholder="Expect Result" required>
	</div>
	<div style="display: none" id="validateTaskParameter">
		<label for="TaskGroupName" class="col-sm-4 control-label"><small>Validate
				Expression </small></label> <input type="text" class="form-control" name="serverUrl"
			placeholder="Validate Expression" required> <label
			for="TaskGroupName" class="col-sm-3 control-label"><small>Expect
				Result </small></label> <input type="text" class="form-control" name="userName"
			placeholder="Expect Result" required>
	</div>
</body>