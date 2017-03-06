<%@include file="includes/header.html"%>

<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.entity.BookLoan"%>
<%@page import="com.gcit.lms.entity.BookCopy"%>



<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>


<%@page import="com.gcit.lms.service.LibrarianService "%>
<%@page import="com.gcit.lms.service.AdminService"%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>


<%
	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	AdminService service = (AdminService) ac.getBean("adminService");
	BorrowerService brservice = (BorrowerService) ac.getBean("brservice");
	LibrarianService lbservice = (LibrarianService) ac.getBean("lbservice");

	List<Book> Allbooks = service.readBooks();
	List<BookCopy> allBookCopy = lbservice.readBookCopies();
	List<Author> AllAuthors = service.readAuthors(1);
	List<Genre> AllGenres = service.readGenres(1);
	List<Publisher> AllPublisher = service.readPublishers();
	List<LibraryBranch> lbranches = service.readLibraryBranch();
%>


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
				<li><a href="AddbookCopies.jsp">Add Book Copies</a></li>

			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>

<script>
	function searchBook() {
		$.ajax({
			url : "searchBooks",
			method : "post",
			data : {
				searchString : $('#searchString').val(),
				pageNo : $('#pageNo').val(),
			}
		}).done(function(data) {
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
			<form action="checkOutBook" method="post" class="form-inline">
				<select name="branchId" class="selectpicker  ">

					<%
						for (LibraryBranch lb : lbranches) {
					%>
					<option value=<%=lb.getBranchId()%>><%=lb.getBranchName()%></option>
					<%
						}
					%>
				</select> <select name="bookId" class="selectpicker  ">

					<%
						for (Book b : Allbooks) {
					%>
					<option value=<%=b.getBookId()%>><%=b.getTitle()%></option>
					<%
						}
					%>
				</select> <input name="noOfCopies" type="text" class="form-control"
					placeholder="Enter number of copies">

				<button type="submit" class="btn btn-primary">Add copies</button>
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
						<th>Book</th>
						<th>Library Branch</th>
						<th>Number of Copies</th>

					</tr>
				</thead>
				<tbody>
					<%
						for (BookCopy bc : allBookCopy) {
					%>
					<tr>

						<td scope="row"><%=allBookCopy.indexOf(bc) + 1%></td>
						<%-- 						<td><%=if(bl.getBook())bl.getBook().getTitle() %></td>
 --%>
						<td>
							<%
								out.println(service.readBookByPk(bc.getBookId()).getTitle());
							%>
						</td>

						<td>
							<%
								out.println(service.readLibraryBranchByPk(bc.getBranchId()).getBranchName());
							%>
						</td>

						<td>
							<%
								out.println(bc.getNoOfCopies());
							%>
						</td>
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
