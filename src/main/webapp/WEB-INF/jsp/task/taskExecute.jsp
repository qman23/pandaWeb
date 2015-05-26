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
window.operateEvents = {
		'click .zoom' : function(e, value, row, index) {
				 $('#logdetails').find('#loading').css("display","block");
				  $('.modal-content-textarea').css("display","none");
			
				$.ajax({
				   type: "get",
				   url: "<%=basePath%>/getTaskLog.do",
				   data: "logId="+row.logId,
				   success: function(msg){
				  	 $('#logdetails').find('#loading').css("display","none");	
				  	 $('.modal-content-textarea').css("display","block");
				     $('#logdetails').find('.modal-content-textarea').html(msg);
				   }
				});
		},
		'click .remove' : function(e, value, row, index) {
			$('#deleteLogBtn').attr("href","deleteTaskLog.do?logId="+row.logId);
		}
};
function tasklogFormatter(value, row, index){
	if(row.taskStatus==1){
	 return "<textarea style='width:1000px;height:250px;font-size: 12px;color: white;background: seagreen;'>"+value+"</textarea>";
 	}else{
 	  return "<textarea style='width:1000px;height:250px;font-size: 12px;color: white;background: grey;'>"+value+"</textarea>";
 	}
}
function operateFormatter(value, row, index) {
		return [ '<a class="zoom" href="#" title="Modify" data-toggle="modal" data-target="#logdetails">',
				'<i class="glyphicon glyphicon-zoom-in"></i>', '</a>  ',
				'<a class="remove" href="#" title="Remove" data-toggle="modal" data-target="#deleteLog">',
				'<i class="glyphicon glyphicon-trash"></i>', '</a>' ].join('');
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
					height : '100%',
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
					},{
						field : 'operate',
						title : 'operate',
						align : 'center',
						valign : 'middle',
						sortable : true,
						formatter : operateFormatter,
						events : operateEvents
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
								<c:choose>
									<c:when test="${taskGroup.groupId eq groupId }">
										<option class="groupNameOption" selected id="${taskGroup.groupId}">${taskGroup.groupName}</option>
									</c:when>
									<c:otherwise>
									 	<option class="groupNameOption"  id="${taskGroup.groupId}">${taskGroup.groupName}</option>
									</c:otherwise>
								</c:choose>
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
<!-- create task details page -->
	<div class="modal fade" id="logdetails" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Log Details</h4>
				</div>
				<div class="modal-body" style="height: 700px">
					<img id="loading" alt="Loading" src="../style/img/loading.gif">
					<textarea class="modal-content-textarea" style="display:none;width:100%;height:100%" disabled="disabled">
					
					</textarea>
				</div>
			</div>
		</div>
	</div>
	
	
	
<!-- delete task confirm -->
	<div class="modal fade" id="deleteLog" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Delete log confirm</h4>
				</div>
				<div class="modal-body">Do you want delete the task log?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close
					</button>
					<a class="btn btn-danger" id="deleteLogBtn" >Delete</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>