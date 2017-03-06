<%@include file="includes/header.html"%>
<%@include file="includes/nav.html"%>

<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Genre"%>

<%@page import="com.gcit.lms.service.AdminService"%>

<%
	AdminService service = new AdminService();
	List<Genre> genres = new ArrayList<>();
	List<Book> Allbooks = service.readBooks();
	genres = service.readGenres();
%>
<%Genre genre = (Genre)request.getAttribute("genre");%>


<div class="container">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-1">
			<div id="imaginary_container">
				<div class="input-group stylish-input-group">
					<input type="text" class="form-control" placeholder="Search Genre">
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
			<form action="editGenre" method="post" class="form-inline">
				<input name="genreName" type="text" class="form-control"
					value="<%=genre.getGenreName() %>">
			<input type="hidden" name="genreId" value="<%=genre.getGenreId() %>">
					
					 <select name="bookIds" class="selectpicker  "  multiple>
					
					<%
			for (Book b : Allbooks) {
		%>
		<option value=<%=b.getBookId()%>><%=b.getTitle()%></option>
		<%
			}
		%>
				</select>

				<button type="submit" class="btn btn-primary">Edit Genre</button>
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
