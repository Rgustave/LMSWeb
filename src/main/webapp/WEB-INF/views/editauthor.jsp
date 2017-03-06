<%@include file="includes/header.html"%>


<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@include file="includes/nav.html"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>

<%
	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	AdminService service = (AdminService) ac.getBean("adminService");
	List<Author> authors = new ArrayList<Author>();
	List<Book> Allbooks = service.readBooks();
	authors = service.readAuthors(1);
%>
<%
	Author author = (Author) request.getAttribute("author");
%>

<div class="container">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-1">
			<div id="imaginary_container">
				<div class="input-group stylish-input-group">
					<input type="text" class="form-control" placeholder="Search Author">
					<span class="input-group-addon">
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
			<form action="editAuthor" method="post" class="form-inline">
				<input type="text" class="form-control" name="authorName"
					value="<%=author.getAuthorName()%>"> <input type="hidden"
					name="authorId" value="<%=author.getAuthorId()%>"> <select
					name="bookIds" class="selectpicker  " multiple>

					<%
						for (Book b : Allbooks) {
					%>
					<option value=<%=b.getBookId()%>><%=b.getTitle()%></option>
					<%
						}
					%>
				</select>


				<button type="submit" class="btn btn-primary">Edit Author</button>
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
						<th>Book Written</th>
						<th>Update</th>
						<th>Delete</th>

					</tr>
				</thead>
				<tbody>
					<%
						for (Author a : authors) {
					%>
					<tr>

						<td scope="row"><%=authors.indexOf(a) + 1%></td>
						<td><%=a.getAuthorName()%></td>
						<td>
							<%
								if (a.getBooks() != null && !a.getBooks().isEmpty()) {
										List<Book> books = a.getBooks();
										for (Book b : books) {
											out.println(b.getTitle());
											out.println(", ");
										}
									}
							%>


						</td>

						</td>
						<td><button type="button" class="btn btn-sm btn-warning"
								onclick="javascript:location.href='editAuthor?authorId=<%=a.getAuthorId()%>'">Edit</button></td>
						<td><button type="button" class="btn btn-sm btn-danger"
								onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>'">Delete</button></td>
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
