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
<%@page import="com.gcit.lms.entity.Genre"%>

<%@page import="com.gcit.lms.service.AdminService"%>

<%

ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("adminService");
	List<Genre> genres = new ArrayList<Genre>();
	List<Book> Allbooks = service.readBooks();
	genres = service.readGenres(1);
%>

<script>
function searchGenre(){
	$.ajax({
		   url: "searchGenres",
		   method: "post",
		   data: {
		      searchString: $('#searchString').val(),
		      pageNo:$('#pageNo').val(),
		   }
		}).done(function( data ) {
		    $('#genreTable').html(data);
		 });
}

</script>


<div class="container">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-1">
			<div id="imaginary_container">
				<div class="input-group stylish-input-group">
					<input type="text" class="form-control"
						placeholder="Type Author Genre name to Search!" name="searchString"
						id="searchString" oninput="searchGenre()">	
						 <span
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
			<form action="addGenre" method="post" class="form-inline">
				<input name="genreName" type="text" class="form-control"
					placeholder="Enter genre's name"> <select name="bookIds"
					class="selectpicker  " multiple>

					<%
			for (Book b : Allbooks) {
		%>
					<option value=<%=b.getBookId()%>><%=b.getTitle()%></option>
					<%
			}
		%>
				</select>


				<button type="submit" class="btn btn-primary">Add Genre</button>
			</form>
		</div>
	</div>
</div>

<div class="container MarginCusotm">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-1">
			<table class="table table-inverse table-bordered" id="genreTable">

				<thead>
					<tr>
						<th>#</th>
						<th>Name</th>
						<th>Associated Books</th>
						<th>Update</th>
						<th>Delete</th>

					</tr>
				</thead>
				<tbody>
					<%
						for (Genre g : genres) {
					%>
					<tr>

						<td scope="row"><%=genres.indexOf(g) + 1%></td>
						<td><%=g.getGenreName()%></td>
						<td>
							<%
								if (g.getBooks() != null && !g.getBooks().isEmpty()) {
										List<com.gcit.lms.entity.Book> books = g.getBooks();
										for (Book b : books) {
											out.println(b.getTitle());
											out.println(", ");
										}
									}
							%>


						</td>

						</td>
						<td><button type="button" class="btn btn-sm btn-warning"
								onclick="javascript:location.href='editGenre?genreId=<%=g.getGenreId()%>'">Edit</button></td>
						<td><button type="button" class="btn btn-sm btn-danger"
								onclick="javascript:location.href='deleteGenre?genreId=<%=g.getGenreId()%>'">Delete</button></td>
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
