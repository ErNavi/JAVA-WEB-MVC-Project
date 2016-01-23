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

    <title>Registration</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="navbar-fixed-top.css" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script> 

<script src="<%=request.getContextPath() %>/js/jquery.validate.js"></script> 
<script src="<%=request.getContextPath() %>/js/scriptCompany.js"></script> 
<script>
function doAjaxPost(txtname,txtupass,txtemail,txturl,st,txtans,membership,createdBy)
{  
	 
	 $.ajax({  
	   type: "POST",  
	   url: "CompanyRegistrationController",  
	   data: "txtname="+txtname+"&txtupass="+txtupass+"&txtemail="+txtemail+"&txturl="+txturl+"&st="+st+"&txtans="+txtans+"&membership="+membership+"&createdBy="+createdBy,  
	   success: function(data){  
		   if(data.indexOf('your Email ID is already Exists ....!!!!') != -1)
		   {
		  $("#show").html("<h1 align='center' STYLE='color: red'>"+data+"</h1>");	
		  }
		   else
			   {
			   window.location="<%=request.getContextPath() %>"+"/LoginController";
		   }
	     
	   } //success close 
	   
	  
	 });  
	} // doAjaxPost close
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
          <a class="navbar-brand" href="#">Company Registration</a>
        </div>
        <div class="navbar-collapse collapse">
          
          <ul class="nav navbar-nav navbar-right">
            <li><a href="LoginController">Login</a></li>
            <li class="active"><a href="#">Sign Up</a></li>
            <li><a href="../navbar/">About Us</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container">

      <!-- Main component for a primary marketing message or call to action -->
     <form method="post" class="form-horizontal" role="form" id="registrationForm">

<div class="form-group">
    <label class="col-sm-2 control-label" for="txtname">Company Name</label>
    <div class="col-sm-5">
      <input type="text" class="form-control" name="txtname" id="txtname" placeholder="Enter Company Name">
    </div>
  </div>  
<div class="form-group">
    <label for="txtupass" class="col-sm-2 control-label">Password</label>
    
    <div class="col-sm-5">
      <input type="password" class="form-control" id="txtupass" placeholder="Enter password" name="txtupass">
    </div>
  </div>

<div class="form-group">
    <label for="confirm_password" class="col-sm-2 control-label">Retype Password</label>
    
    <div class="col-sm-5">
      <input type="password" class="form-control" id="confirm_password" placeholder="ReEnter password" name="confirm_password">
    </div>
  </div>




<div class="form-group">
    <label class="col-sm-2 control-label" for="txtemail">Email Address</label>
    <div class="col-sm-5">
      <input type="email" class="form-control" name="txtemail" id="txtemail" placeholder="Enter email address">
    </div>
  </div>
  
  <div class="form-group">
    <label class="col-sm-2 control-label" for="txturl">Company URL</label>
    <div class="col-sm-5">
      <input type="text" class="form-control" name="txturl" id="txturl" placeholder="Enter company URL">
    </div>
  </div>
  
  <div class="form-group">
    <label class="col-sm-2 control-label" for="logo">Company Logo</label>
    <div class="col-sm-5">
      <input type="file" class="form-control" name="logo" id="logo" placeholder="Enter company URL">
    </div>
  </div>
  
  
  
  <div class="form-group">
    <label class="col-sm-2 control-label" for="txtemail">Security Question</label>
    <div class="col-sm-5" style=" overflow x:hidden;overflow y:scroll ">
      

<select name="st" id="st" class="form-control" size="5">
            <option value="">--Choose--</option> 
		<c:forEach  items="${questionList}"  var="question" >
			<option value="${question.id}">
			<c:out value="${question.detail}"/>
			</option>
		</c:forEach>
</select>
</div>
  </div>


<div class="form-group">
    <label class="col-sm-2 control-label" for="answer">Answer</label>
    <div class="col-sm-5">
      <input type="text" class="form-control" name="txtans" id="txtans" placeholder="Enter Answer">
    </div>
  </div>


<div class="form-group membership-radio">
    <label class="col-sm-2 control-label" for="membership">Membership</label>
    <div class="col-sm-5">
    <input type="radio"  name="membership" id="membershipF" value=1 /> :Free
	          <input type="radio"  name="membership" id="membershipP" value=2 /> :Premium
             <input type="radio"  name="membership" id="membershipG" value=3 />  :Gold</div>
    
<div id="membershipLast"> 
            </div>
  </div>

<div class="form-group">
            <label class="col-sm-2 control-label" for="message"> Please agree to our policy</label>
            <div class="col-sm-5">
             <input id="agree" class="checkbox" type="checkbox" name="agree">
            </div>
          </div>
 

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button id="submit" type="submit" class="btn btn-info">Register</button>
      <button type="reset" class="btn">Cancel</button>
    </div>
  </div>

</form>



    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
   <!--  <h1 align='center' STYLE='color: red; line-height: 50px; font-size: 40px'>  <c:out value="${param.message}" /></h1>-->
 
 <div id="show"></div>
  </body>
</html>