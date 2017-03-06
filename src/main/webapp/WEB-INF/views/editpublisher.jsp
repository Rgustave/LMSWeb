<%@include file="includes/header.html"%>
<%@include file="includes/nav.html"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>

<%
	AdminService service = new AdminService();
	List<Publisher> publishers = new ArrayList<>();
	List<Book> Allbooks = service.readBooks();
	publishers = service.readPublishers();
%>
<%Publisher publisher = (Publisher)request.getAttribute("publisher");%>

<div class="container">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-1">
			<div id="imaginary_container">
				<div class="input-group stylish-input-group">
					<input type="text" class="form-control" placeholder="Search Publisher">
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
		<div class="col-sm-10 col-sm-offset-1">
			<form action="editPublisher" method="post" class="form-inline">
				<input name="publisherName" type="text" class="form-control"
					value="<%=publisher.getPublisherName() %>">
					
			       <input type="hidden" name="pubId" value="<%=publisher.getPublisherId() %>">
					<input name="publisherAddress" type="text" class="form-control"
					value="<%=publisher.getPublisherAddress() %>">
					<input name="publisherPhone" type="text" class="form-control"
					value="<%=publisher.getPublisherPhone() %>">
					 <select name="bookIds" class="selectpicker  "  multiple>		
					<%
			for (Book b : Allbooks) {
		%>
		<option value=<%=b.getBookId()%>><%=b.getTitle()%></option>
		<%
			}
		%>
				</select>


				<button type="submit" class="btn btn-primary">Edit Publisher</button>
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
						<th>Phone</th>
						<th>Written Books</th>
						<th>Update</th>
						<th>Delete</th>

					</tr>
				</thead>
				<tbody>
					<%
						for (Publisher p :publishers) {
					%>
					<tr>

						<td scope="row"><%=publishers.indexOf(p) + 1%></td>
						<td><%=p.getPublisherName()%></td>
						<td><%=p.getPublisherAddress()%></td>
						<td><%=p.getPublisherPhone()%></td>
						
						<td>
							<%
								if (p.getBooks() != null && !p.getBooks().isEmpty()) {
										List<Book> books = p.getBooks();
										for (Book b : books) {
											out.println(b.getTitle());
											out.println(", ");
										}
									}
							%>


						</td>

						</td>
						<td><button type="button" class="btn btn-sm btn-warning"
								onclick="javascript:location.href='editPublisher?publisherId=<%=p.getPublisherId()%>'">Edit</button></td>
						<td><button type="button" class="btn btn-sm btn-danger"
								onclick="javascript:location.href='deletePublisher?publisherId=<%=p.getPublisherId()%>'">Delete</button></td>
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
