<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
$(document).ready(function(){
getTasks();
	$("#submitQuery").click(function(){
			$(this).attr("disabled","true");
			$('#table-javascript').bootstrapTable(
											'refresh',
											{
												query : {
													queryType :$("#queryCondition").val(),
													queryValue:$("#parameter").val()
												}
											});
		});
	});
function getTasks() {
		$('#table-javascript').bootstrapTable(
				{
					method : 'get',
					url : 'queryBP.do',
					queryParams : function(p) {
						return {
							queryType :$("#queryCondition").val(),
							queryValue:$("#parameter").val()
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
					cardView : true,
					columns : [ {
						field : 'cNUM',
						title : 'cNUM',
						align : 'center',
						valign : 'bottom',
						sortable : true
					}, {
						field : 'countryCode',
						title : 'countryCode',
						align : 'center',
						valign : 'middle',
						sortable : true
					}, {
						 field : 'department',
						 title : 'department',
						 align : 'center',
						 valign : 'top',
						 sortable : true
						}, {
						 field : 'empType',
						 title : 'empType',
						 align : 'center',
						 valign : 'top',
						 sortable : true
						}, {
						 field : 'firstName',
						 title : 'firstName',
						 align : 'center',
						 valign : 'top',
						 sortable : true
						}, {
						 field : 'internetAddress',
						 title : 'internetAddress',
						 align : 'center',
						 valign : 'top',
						 sortable : true
						},
						{
						 field : 'jobResponsibility',
						 title : 'jobResponsibility',
						 align : 'center',
						 valign : 'top',
						 sortable : true
						},
						{
						 field : 'lastName',
						 title : 'lastName',
						 align : 'center',
						 valign : 'top',
						 sortable : true
						},
						 {
						 field : 'managerCNUM',
						 title : 'managerCNUM',
						 align : 'center',
						 valign : 'top',
						 sortable : true
						},
						{
						 field : 'managerCountry',
						 title : 'managerCountry',
						 align : 'center',
						 valign : 'top',
						 sortable : true
						},
						{
						 field : 'managerPersNum',
						 title : 'managerPersNum',
						 align : 'center',
						 valign : 'top',
						 sortable : true
						},
						{
						 field : 'name',
						 title : 'name',
						 align : 'center',
						 valign : 'top',
						 sortable : true
						},
						{
						 field : 'notesAddress',
						 title : 'notesAddress',
						 align : 'center',
						 valign : 'top',
						 sortable : true
						},
						{
						 field : 'tie',
						 title : 'tie',
						 align : 'center',
						 valign : 'top',
						 sortable : true
						},
						{
						 field : 'userid',
						 title : 'userid',
						 align : 'center',
						 valign : 'top',
						 sortable : true
						},
						{
						 field : 'xPhone',
						 title : 'xPhone',
						 align : 'center',
						 valign : 'top',
						 sortable : true
						} ]
				}).on('load-success.bs.table', function (e, data) {
           			 $("#submitQuery").removeAttr("disabled");
            });
	}
</script>
</head>
<body>
	<div class="col-md-10 main">
		<div class="panel panel-default">
			<div class="panel-heading">
				<label for="Blue Page Check" class="control-label"><small>Blue
						Page Check </small></label> <select class="form-control" id="queryCondition"
					style="width: auto">
					<option value="Notes mail">ByNotesMail</option>
					<option value="CNUM">CNUM</option>
					<option value="Last name">ByLastName</option>
					<option value="Serial number">BySerialNumber</option>
					<option value="Internet address">ByInternetAddress</option>
				</select> <input id="parameter" style="width:200px" class="form-control"
					type="text"></input> <input class=" btn btn-default" type="button"
					value="Submit" id="submitQuery">
			</div>
		</div>
		<table id="table-javascript"></table>
	</div>
</body>
</html>