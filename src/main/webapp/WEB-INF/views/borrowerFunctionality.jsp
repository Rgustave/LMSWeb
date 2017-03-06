<%@include file="includes/header.html"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.entity.BookLoan"%>


<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>


<%@page import="com.gcit.lms.service.AdminService"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>


<%
    BorrowerService brservice = new BorrowerService();
	AdminService service = new AdminService();
	List<Book> Allbooks = service.readBooks();
	List<Author> AllAuthors = service.readAuthors(1);
	List<Genre> AllGenres = service.readGenres(1);
	List<Publisher> AllPublisher = service.readPublishers();
	List<LibraryBranch> lbranches = service.readLibraryBranch();

%>
<%Borrower borrower = (Borrower)request.getAttribute("borrower");
List<BookLoan> bookloans = brservice.readBookLoans(borrower.getCardNo());
%>

    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand em-text" href="index.jsp">LABRARY MANAGMENT SYSTEM</a>
        </div>
        <div id="navbar " class="collapse navbar-collapse">
          <ul class="nav navbar-nav navbar-right">
				<p class="navbar-text">Logged in as <%=borrower.getName() %></p> 
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>





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
			<form action="checkOutBook" method="post" class="form-inline">
		<input type="hidden" name="cardNo" value="<%=borrower.getCardNo() %>">
			
				
				 <select name="branchId" class="selectpicker  " >

					<%
			for (LibraryBranch lb : lbranches) {
		%>
					<option value=<%=lb.getBranchId()%>><%=lb.getBranchName()%></option>
					<%
			}
		%>
				</select> <select name="bookId" class="selectpicker  " >

					<%
			for (Book b : Allbooks) {
		%>
					<option value=<%=b.getBookId()%>><%=b.getTitle()%></option>
					<%
			}
		%>
				</select>


				<button type="submit" class="btn btn-primary">Check out</button>
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
						<th>Date Out</th>
						<th>Due Date</th>
						<th>Date In</th>
						<th>CheckIn</th>

					</tr>
				</thead>
				<tbody>
					<%
						for (BookLoan bl : bookloans) {
					%>
					<tr>

						<td scope="row"><%=bookloans.indexOf(bl) + 1%></td>
<%-- 						<td><%=if(bl.getBook())bl.getBook().getTitle() %></td>
 --%>						<td>
							<% if(bl.getBook()!=null){
							out.println(bl.getBook().getTitle());
						}
							
						%>
						</td>
						
							<td>
							<% if(bl.getLibraryBranch()!=null){
							out.println(bl.getLibraryBranch().getBranchName());
						}
							
						%>
						</td>
					

					<td><%=bl.getCheckoutTime() %></td>
					<td><%=bl.getDueDate() %></td>
					
					  
					  	<td>
							<% if(bl.getCheckin()==null){
							out.println("NOT YET");
						}
							
						%>
						</td>
					
					

					
						<td><button type="button" class="btn btn-sm btn-warning"
								onclick="javascript:location.href='checkIn?checkoutTime=<%=bl.getCheckoutTime() %>'">CHEK-IN</button></td>
						
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
