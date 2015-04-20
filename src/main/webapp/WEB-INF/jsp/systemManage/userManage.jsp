<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<div class="col-md-10 main">

		<h2 class="sub-header">User Manage</h2>
		<nav>
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>User Id</th>
						<th>User Email</th>
						<th>User PassWord</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="user" items="${userList}" >
					<tr>
						<td>${user.userid }</td>
						<td>${user.email }</td>
						<td>${user.password }</td>
					</tr>
				</c:forEach>
					
				</tbody>
			</table>
		</div>
		<nav>
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
	</div>
</body>