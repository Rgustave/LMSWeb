<%@include file="includes/header.html"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@include file="includes/nav.html"%>
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
	List<Publisher> publishers = new ArrayList<>();
	List<Book> Allbooks = service.readBooks();
	publishers = service.readPublishers();
%>


<script>
	function searchPublisher() {
		$.ajax({
			url : "searchPublisher",
			method : "post",
			data : {
				searchString : $('#searchString').val(),
				pageNo : $('#pageNo').val(),
			}
		}).done(function(data) {
			$('#publisherTable').html(data);
		});
	}
</script>

<div class="container">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-1">
			<div id="imaginary_container">
				<div class="input-group stylish-input-group">
					<input type="text" class="form-control"
						placeholder="Type Author Publisher's name to Search!"
						name="searchString" id="searchString" oninput="searchPublisher()">


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
			<form action="addPublisher" method="post" class="form-inline">
				<input name="publisherName" type="text" class="form-control"
					placeholder="publisher's name"> <input
					name="publisherAddress" type="text" class="form-control"
					placeholder=" publisher's address"> <input
					name="publisherPhone" type="text" class="form-control"
					placeholder="publisher's Phone"> <select name="bookIds"
					class="selectpicker  " multiple>

					<%
						for (Book b : Allbooks) {
					%>
					<option value=<%=b.getBookId()%>><%=b.getTitle()%></option>
					<%
						}
					%>
				</select>


				<button type="submit" class="btn btn-primary">Add Publisher</button>
			</form>
		</div>
	</div>
</div>

<div class="container MarginCusotm">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-1">
			<table class="table table-inverse table-bordered" id="publisherTable">

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
						for (Publisher p : publishers) {
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
								onclick="javascript:location.href='editPublisher?pubId=<%=p.getPublisherId()%>'">Edit</button></td>
						<td><button type="button" class="btn btn-sm btn-danger"
								onclick="javascript:location.href='deletePublisher?pubId=<%=p.getPublisherId()%>'">Delete</button></td>
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
