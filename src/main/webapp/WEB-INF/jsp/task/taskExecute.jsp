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
})
function tasklogFormatter(value, row, index){
 return "<textarea style='width:1000px;height:250px;font-size: 12px;color: white;background: black;'>"+value+"</textarea>";
}
function getTasks() {
		$('#table-javascript').bootstrapTable(
				{
					method : 'get',
					url : 'getTaskLogs.do',
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
						field : 'logId',
						title : 'Log ID',
						align : 'center',
						valign : 'middle',
						sortable : true
					}, {
						field : 'groupId',
						title : 'Group Id',
						align : 'center',
						valign : 'middle',
						sortable : true
					}, {
						field : 'executeDate',
						title : 'Execute Date',
						align : 'center',
						valign : 'middle',
						sortable : true
					}, {
						field : 'taskLog',
						title : 'Task Log',
						align : 'center',
						valign : 'middle',
						sortable : true,
						formatter:tasklogFormatter
					}, {
						field : 'taskStatus',
						title : 'Task Status',
						align : 'center',
						valign : 'middle',
						sortable : true
					} ]
				});
	}
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
</body>
</html>