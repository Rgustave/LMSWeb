
<%@include file="includes/header.html"%>

<div class="jumbotron">
	<h1>GCIT Library Management System</h1>
	<p>Welcome to GCIT Library System. Have fun shopping.</p>
</div>

<div class="row">
	<div class="col-lg-6 col-lg-offset-3">
		<!--   print errors
 -->
	</div>
</div>
<div class="row">
	<div class="col-md-6 col-md-offset-3">
		<div class="panel panel-login">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-12">
						<a href="index.jsp" class="active" id="login-form-link">Login</a>
					</div>

				</div>
				<hr>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<form action="getBorrower" method="post" role="form"
							style="display: block;">
							<div class="form-group">
							<div class="dropdown">
									<button class="btn btn-default dropdown-toggle" type="button"
										data-toggle="dropdown">
										User <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="libririan">Librarian</a></li>
										<li><a href="Admin">Administrator</a></li>
										<li><a href="borrower ">Borrower</a></li>
									</ul>
								</div> 
							</div>

							<div class="form-group">
								<input type="type" name="cardNo" id="cardNo" tabindex="2"
									class="form-control" placeholder="CardNo" required>
							</div>

							<div class="form-group">
								<div class="row">
									<div class="col-sm-6 col-sm-offset-3">
										<input type="submit" name="login-submit" id="login-submit"
											tabindex="4" class="form-control btn btn-login"
											value="Log In">
									</div>
								</div>
							</div>

						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

</div>

<%@include file="includes/footer.html"%>
