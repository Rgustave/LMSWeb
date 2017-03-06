
<%@include file="includes/header.html"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>

<%
	AdminService service = new AdminService();
	List<LibraryBranch> libraryBranches = new ArrayList<>();
	List<Book> Allbooks = service.readBooks();
	libraryBranches = service.readLibraryBranch();
%>
<%LibraryBranch libraryBranch = (LibraryBranch)request.getAttribute("lbranch");%>

<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand em-text" href="index.jsp">LABRARY
				MANAGMENT SYSTEM</a>
		</div>
		<div id="navbar " class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="Addbook.copies.jsp">Add Book Copies</a></li>

			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>
<div class="container">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-1">
			<div id="imaginary_container">
				<div class="input-group stylish-input-group">
					<input type="text" class="form-control"
						placeholder="Search Library branch"> <span
						class="input-group-addon">
						<button type="submit">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</span>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="container MarginCusotm">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-1">
			<form action=LibrianeditlBranch method="post" class="form-inline">
				<input name="lbranchName" type="text" class="form-control"
					value="<%=libraryBranch.getBranchName() %>"> <input
					type="hidden" name="branchId"
					value="<%=libraryBranch.getBranchId()%>"> <input
					name="lbranchAddress" type="text" class="form-control"
					value="<%=libraryBranch.getBranchAddress() %>">

				<button type="submit" class="btn btn-primary">Edit Library
					Branch</button>
			</form>
		</div>
	</div>
</div>

<div class="container MarginCusotm">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-1">
			<table class="table table-inverse table-bordered">

				<thead>
					<tr>
						<th>#</th>
						<th>Name</th>
						<th>Address</th>
						<th>Update</th>

					</tr>
				</thead>
				<tbody>
					<%
						for (LibraryBranch lb : libraryBranches) {
					%>
					<tr>
						<td scope="row"><%=libraryBranches.indexOf(lb) + 1%></td>

						<td><%=lb.getBranchName()%></td>
						<td><%=lb.getBranchAddress()%></td>

						<td><button type="button" class="btn btn-sm btn-warning"
								onclick="javascript:location.href='LibrianeditlBranch?branchId=<%=lb.getBranchId()%>'">Edit</button></td>
					<tr>
						<%
							}
						%>
					
				</tbody>
			</table>

		</div>
	</div>
</div>

<%@include file="includes/header.html"%>
