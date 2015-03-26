<body>
	<div class="col-md-10 col-md-offset-3  col-md-offset-2 main">
		<div id="custom-toolbar">
			<div class="form-inline" role="form">
				<button class="btn btn-primary btn-lg" data-toggle="modal"
					data-target="#myModal">New Task</button>
			</div>
		</div>
		<div class="panel panel-default" style="margin-top: 10px">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Task ID</th>
						<th>Task Name</th>
						<th>Create Time</th>
						<th>Execute Time</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Tanmay</td>
						<td>Bangalore</td>
						<td>560001</td>
						<td>560001</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Create Task</h4>
				</div>
				<div class="modal-body">aaa</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close
					</button>
					<button type="button" class="btn btn-primary">Add</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>
	<!-- /.modal -->
</body>