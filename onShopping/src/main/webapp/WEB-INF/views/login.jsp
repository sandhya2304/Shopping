<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />


<c:set var="contextRoot" value="${pageContext.request.contextPath}" />




<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>OnShopping- ${title}</title>

<script>
	window.menu = '${title}';

	window.contextRoot = '${contextRoot}'
</script>

<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap dtatatable -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${css}/shop-homepage.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->

		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<!-- brand and toggle get grouped for mobile better display -->
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot}/home">Online
						Shopping!!</a>
				</div>
			</div>
		</nav>


		<!-- Page Content an Loading the home content only when users click home and click home get from controller -->

	<div class="content">

		<div class="container">
		
		<!-- this will be display when credentials are worng -->
		<c:if test="${not empty msg }">
		
		<div class="row">

				<div class="col-md-offset-3 col-md-6">
		             
		             <div class="alert alert-danger">
		             
		                ${msg}
		             
		             </div>
		             
		        </div>
		 </div> 
		
		</c:if>
		
		<!-- this will be display when user has logout -->
		<c:if test="${not empty logout }">
		
		<div class="row">

				<div class="col-md-offset-3 col-md-6">
		             
		             <div class="alert alert-success">
		             
		                ${logout}
		             
		             </div>
		             
		        </div>
		 </div> 
		
		</c:if>

		    <div class="row">

				<div class="col-md-offset-3 col-md-6">

					<div class="panel panel-primary">

						<div class="panel-heading">
							<h4>Login</h4>
						</div>

						<div class="panel-body">
							<form action="${contextRoot}/login" method="POST"
								class="form-horizontal" id="loginForm">
								
								<div class="form-group">
									<label for="username" class="col-md-4 control-label">Email:
									</label>
									<div class="col-md-8">
										<input type="text" name="username" id="username"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="password" class="col-md-4 control-label">Password:
									</label>
									<div class="col-md-8">
										<input type="password" name="password" id="password"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-offset-4 col-md-8">
									<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
										<input type="submit" value="Login" class="btn btn-primary" />
									</div>
								</div>
							</form>

						</div>
						<div class="panel-footer">
							<div class="text-right">
								New User - <a href="${contextRoot}/register">Register Here</a>
							</div>
						</div>

					</div>

				</div>

			</div>


		</div>


	</div>
	<!-- /.container footer->
      <%@ include file="./shared/footer.jsp" %>
   
    <!-- /.container -->

	<!-- jQuery -->
	<script src="${js}/jquery.js"></script>

	<!-- jquery validator -->
	<script src="${js}/jquery.validate.js"></script>


	<!-- Bootstrap Core JavaScript -->
	<script src="${js}/bootstrap.min.js"></script>



	<!-- Self Coded JavaScript -->
	<script src="${js}/myapp.js"></script>

	</div>

</body>

</html>
