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
				$('#userEmail').val(row.email);
				$('#modifyUser').show();
		},
		'click .remove' : function(e, value, row, index) {
			$('#deleteUserBtn').attr("href","deleteUser.do?userId="+row.userid);
			$('#deleteUser').show();
		},
		'click .role' : function(e, value, row, index) {
			$('#userId').val(row.email);
			$('#roleModify').show();
		},
};
function operateFormatter(value, row, index) {
		
		return [ '<a class="like" href="#" title="Modify" data-toggle="modal" data-target="#modifyUser">',
				'<i class="glyphicon glyphicon-wrench"></i>', '</a>  ',
				 '<a class="role" href="#" title="Modify" data-toggle="modal" data-target="#roleModify">',
				'<i class="glyphicon glyphicon-user"></i>', '</a>  ',
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
<body>
	<div class="col-md-10 main">
		<h2 class="sub-header">User Manage</h2>
		<table id="table-javascript"></table>
	</div>
	<div class="modal fade" id="modifyUser" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="false">
	<div class="modal-dialog">
			<div class="modal-content">
			<form action="updateUser.do" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Modify User</h4>
					</div>
					<div class="modal-body" style="height: 150px">
						<div class="form-group">
							<label for="TaskGroupName" class="col-sm-3 control-label"><small>User
									Email</small></label>
							<div class="col-sm-12">
								<input type="text" class="form-control" name="email" id="userEmail"
									placeholder="User Email" required>
							</div>
						</div>
						<div class="form-group">
							<label for="TaskGroupName" class="col-sm-3 control-label"><small>User
									PassWord</small></label>
							<div class="col-sm-12">
								<input type="passWord" class="form-control" name="password" id="userPassword"
									placeholder="PassWord" required>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close
						</button>
						<input type="submit" class="btn btn-primary" id="updateUser" value="submit"></input>
					</div>
					</form>
				</div>
			</div>
	
	</div>
	<!-- update role -->
	
	<div class="modal fade" id="roleModify" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="updateUserRole.do" method="post">
					<input type="hidden" id="userId" name="email"/>
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Role Change</h4>
					</div>
					<div class="modal-body" style="height: 50px">
						
							<div class="form-group">
										<div class="col-sm-12">
											<input type="radio" value="0" name="roleId" required>admin</input>
											<input type="radio" value="1" name="roleId" required>developer</input>
											<input type="radio" value="2" name="roleId" required>user</input>
										</div>
							</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close
						</button>
						<input type="submit" class="btn btn-primary" id="updateUserRole" value="submit"></input>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- delete task confirm -->
	<div class="modal fade" id="deleteUser" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Delete User Confirm</h4>
				</div>
				<div class="modal-body">Do you want delete the User and all relative task?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close
					</button>
					<a class="btn btn-danger" id="deleteUserBtn" >Delete</a>
				</div>
			</div>
		</div>
	</div>
</body>