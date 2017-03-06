<%@include file="includes/header.html"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@include file="includes/nav.html"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.entity.Publisher"%>

<%@page import="com.gcit.lms.service.AdminService"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>

<%
	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	AdminService service = (AdminService) ac.getBean("adminService");
	List<Book> Allbooks = service.readBooks();
	List<Author> AllAuthors = service.readAuthors(1);
	List<Genre> AllGenres = service.readGenres(40);
	List<Publisher> AllPublisher = service.readPublishers();

%>


<script>
function searchBook(){
	$.ajax({
		   url: "searchBooks",
		   method: "post",
		   data: {
		      searchString: $('#searchString').val(),
		      pageNo:$('#pageNo').val(),
		   }
		}).done(function( data ) {
		    $('#booksTable').html(data);
		 });
}

</script>
<div class="container">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-1">
			<div id="imaginary_container">
				<div class="input-group stylish-input-group">
				
					<input type="text" class="form-control"
						placeholder="Type Book title to Search!" name="searchString"
						id="searchString" oninput="searchBook()"> <span
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
			<form action="addBook" method="post" class="form-inline">
				<input name="bookTitle" type="text" class="form-control"
					placeholder="Enter book title"> <select name="pubId"
					class="selectpicker  ">

					<%
			for (Publisher p : AllPublisher) {
		%>
					<option value=<%=p.getPublisherId() %>><%=p.getPublisherName() %></option>
					<%
			}
		%>
				</select> <select name="genreIds" class="selectpicker  " multiple>

					<%
			for (Genre g : AllGenres) {
		%>
					<option value=<%=g.getGenreId()%>><%=g.getGenreName()%></option>
					<%
			}
		%>
				</select> <select name="authorIds" class="selectpicker  " multiple>

					<%
			for (Author a : AllAuthors) {
		%>
					<option value=<%=a.getAuthorId()%>><%=a.getAuthorName()%></option>
					<%
			}
		%>
				</select>


				<button type="submit" class="btn btn-primary">Add Book</button>
			</form>
		</div>
	</div>
</div>

<div class="container MarginCusotm">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-1">
			<table class="table table-inverse table-bordered" id="booksTable">

				<thead>
					<tr>
						<th>#</th>
						<th>Title</th>
						<th>Publisher</th>
						<th>Genre</th>
						<th>Authors</th>
						<th>Update</th>
						<th>Delete</th>

					</tr>
				</thead>
				<tbody>
					<%
						for (Book b : Allbooks) {
					%>
					<tr>

						<td scope="row"><%=Allbooks.indexOf(b) + 1%></td>
						<td><%=b.getTitle() %></td>
						<td>
							<% if(b.getPublisher()!=null){
							out.println(b.getPublisher().getPublisherName());
						}
							
						%>
						</td>

						<td>
							<%
								if (b.getGenres() != null && !b.getGenres().isEmpty()) {
										List<Genre> genres = b.getGenres() ;
										for (Genre gr : genres) {
											out.println(gr.getGenreName());
											out.println(", ");
										}
									}
							%>


						</td>
						<td>
							<%
								if ( b.getAuthors()!= null && !b.getAuthors().isEmpty()) {
										List<Author> authors = b.getAuthors();
										for (Author a : authors) {
											out.println(a.getAuthorName());
											out.println(", ");
										}
									}
							%>


						</td>

					
						<td><button type="button" class="btn btn-sm btn-warning"
								onclick="javascript:location.href='editBook?bookId=<%=b.getBookId()%>'">Edit</button></td>
						<td><button type="button" class="btn btn-sm btn-danger"
								onclick="javascript:location.href='deleteBook?bookId=<%=b.getBookId()%>'">Delete</button></td>
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
