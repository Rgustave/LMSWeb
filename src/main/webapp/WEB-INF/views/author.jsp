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
	if (request.getAttribute("authors") != null) {
		authors = (List<Author>) request.getAttribute("authors");

	} else {
		authors = service.readAuthors(1);
	} 
	%>

<script>
function searchAuthors(){
	$.ajax({
		   url: "searchAuthors",
		   method: "post",
		   data: {
		      searchString: $('#searchString').val(),
		      pageNo:$('#pageNo').val(),
		   }
		}).done(function( data ) {
		    $('#authorsTable').html(data);
		 });
}

</script>

<div class="container">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-1">
			<div id="imaginary_container">
				<div class="input-group stylish-input-group">

					<input type="text" class="form-control"
						placeholder="Type Author Name to Search!" name="searchString"
						id="searchString" oninput="searchAuthors()"> <span
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
			<form action="addAuthor" method="post" class="form-inline">
				<input name="authorName" type="text" class="form-control"
					placeholder="Enter author's name"> <select name="bookIds"
					class="selectpicker  " multiple>

					<%
						for (Book b : Allbooks) {
					%>
					<option value=<%=b.getBookId()%>><%=b.getTitle()%></option>
					<%
						}
					%>
				</select>


				<button type="submit" class="btn btn-primary">Add Author</button>
			</form>
		</div>
	</div>
</div>



<%-- <%
	Integer authorsCount = service.getAuthorsCount();
	int pageNo = 0;

	if ((authorsCount % 10) > 0) {
		pageNo = authorsCount / 10 + 1;
	} else {
		pageNo = authorsCount / 10;
	}
%> --%>
<%-- 
<div class="col-sm-9 col-sm-offset-1">
	<nav aria-label="Page navigation ">
		<ul class="pagination">
			<%
				for (int i = 1; i <= pageNo; i++) {
			%>
			<li><a id="pageNo" href="pageAuthors?pageNo=<%=i%>"><%=i%></a></li>
			<%
				}
			%>
		</ul>
	</nav>
</div> --%>


<div class="container MarginCusotm">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-1">
			<table class="table table-inverse table-bordered" id="authorsTable">

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
