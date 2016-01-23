<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="navbar-fixed-top.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script> 

<script src="<%=request.getContextPath() %>/js/jquery.validate.js"></script> 
<script src="<%=request.getContextPath() %>/js/scriptSurvey.js"></script>
<script src="<%=request.getContextPath() %>/js/scriptLogin.js"></script> 
<script>
function doAjaxPost(companyloginid,companyloginpassword) {  
	 
	 $.ajax({  
	   type: "POST",  
	   url: "LoginController",  
	   data: "companyloginid="+companyloginid+"&companyloginpassword="+companyloginpassword,  
	   success: function(data){ 
		  // alert(":"+data+":");
		  if(data.indexOf('User ID or Password is wrong') != -1)
		   {
		  $("#show").html("<h1 align='center' STYLE='color: red'>"+data+"</h1>");	
		  }
		   else
			   {
			   window.location="<%=request.getContextPath() %>"+"/EmailListController";
		   }
			  	
			    			 
	  } //success close 
	   
	  
	  
	 });  
	}  
</script>



  </head>

  <body>

    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Login Detail</a>
        </div>
        <div class="navbar-collapse collapse">
          
          <ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="#">Login</a></li>
            <li><a href="CompanyRegistrationController">Sign Up</a></li>
            <li><a href="../navbar/">About Us</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container">

      <!-- Main component for a primary marketing message or call to action -->
     <form  method="post" class="form-horizontal" role="form" id="loginPage">
  <div class="form-group">
    <label class="col-sm-2 control-label" for="userid">User ID</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" name="companyloginid" id="companyloginid" placeholder="Enter email address">
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-2 control-label">Password</label>
    
    <div class="col-sm-10">
      <input type="password" class="form-control" id="companyloginpassword" placeholder="Enter password" name="companyloginpassword">
    </div>
  </div>
  
 


  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button id="submit" type="submit" class="btn btn-info">Sign in</button>
      <button type="reset" class="btn">Cancel</button>
    </div>
  </div>
</form>


<a href="ForgotPasswordController">Forgot Password..???</a>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
 <!-- <h1 align='center' STYLE='color: red; line-

height: 50px; font-size: 40px'>  <c:out value="${param.message}" /></h1>-->

<div id="show"></div>
   
   
  </body>
</html>