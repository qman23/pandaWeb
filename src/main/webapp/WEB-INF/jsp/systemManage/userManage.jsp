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
$(document).ready(function(){
	getUsers();
});
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
			alert("delte user now");
		}
};
function operateFormatter(value, row, index) {
		
		return [ '<a class="like" href="#" title="Modify" data-toggle="modal" data-target="#modifyUser">',
				'<i class="glyphicon glyphicon-wrench"></i>', '</a>  ',
				'<a class="remove" href="#" title="Remove" data-toggle="modal" data-target="#deleteUser">',
				'<i class="glyphicon glyphicon-trash"></i>', '</a>' ].join('');
}
function roleFormatter(value, row, index){
	var temp="";
	$.each(row.roles,function(i,n){
		temp+=("<li style='text-align: left;'>"+n.roleName+"</li>");
	});
	return "<ul>"+temp+"</ul>";
}
function getUsers() {
		$('#table-javascript').bootstrapTable(
				{
					method : 'get',
					url : '<%=basePath%>home/userList.do',
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
						field : 'userid',
						title : 'User ID',
						align : 'center',
						valign : 'middle',
						sortable : true
					}, {
						field : 'email',
						title : 'Email',
						align : 'center',
						valign : 'middle',
						sortable : true
					}, {
						field : 'password',
						title : 'Password',
						align : 'center',
						valign : 'middle',
						sortable : true
					}, {
						field : 'roles',
						title : 'Role',
						align : 'center',
						valign : 'middle',
						sortable : true,
						formatter:roleFormatter
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
	window.operateEvents = {
		'click .like' : function(e, value, row, index) {
			$.ajax({
				   type: &quot;get&quot;,
				   url: &quot;&lt;%=basePath%&gt;/getTask.do&quot;,
				   data: &quot;taskId=&quot;+row.taskId,
				   success: function(msg){
				     $('#modifyTask').find('.modal-body').html(msg);
				   }
				});
		},
		'click .remove' : function(e, value, row, index) {
				$('#deleteTaskId').val(row.taskId);
				$('#deleteModel').show();
		}
	};<body>
	<div class="col-md-10 main">
		<h2 class="sub-header">User Manage</h2>
		<table id="table-javascript"></table>
	</div>
</body>