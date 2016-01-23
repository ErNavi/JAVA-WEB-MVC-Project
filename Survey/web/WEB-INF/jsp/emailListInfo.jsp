<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://cdn.sanwebe.com/wp-content/themes/sanwebe/js/jquery-1.10.2.min.js" type="text/javascript"></script>
 <!-- Bootstrap core CSS -->
 <link href="navbar-fixed-top.css" rel="stylesheet">
 <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

    <!-- Custom styles for this template -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manange Email List</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.validate.js"></script> 
<script src="<%=request.getContextPath() %>/js/script.js"></script> 
<Script> // STARTS :script for adding more text boxes on click
$(document).ready(function() {
	$("#viewPrev").click(function (e) {

		window.location="<%=request.getContextPath() %>"+"/ChooseEmailController";
		});

var MaxInputs       = 50; //maximum input boxes allowed
var InputsDetail  = $("#EmailParentTable"); //Input boxes wrapper ID

var x = InputsDetail.length; //initlal text box count

var FieldCount=1; //this will keep track of text box added

$("#AddMoreEmail").click(function (e) { 
	if(x <= MaxInputs){
		FieldCount++; //text box added increment
            $(InputsDetail).append('<tr><td><div class="col-sm-10"><input class="form-control" name="email" id="email_'+ FieldCount +'" placeholder="Enter Email Address" type="text"></div></td><td><div class="col-sm-10"><input class="form-control" name="fname" id="fname_'+ FieldCount +'" placeholder="Enter First Name" type="text"></div></td><td><div class="col-sm-10"><input class="form-control" name="lname" id="lname_'+ FieldCount +'" placeholder="Enter Last Name" type="text"></div></td><td><a href="#" class="removeclass">Remove</a></td></tr>');
            x++; //text box increment }

        	return false;
	}
   });
$("body").on("click",".removeclass", function(e){ //user click on remove text
	if( x > 1 ) {
                $(this).closest("tr").remove();//remove text box
                x--; //decrement textbox
                FieldCount--;
	}
return false;
});


}); // ENDS :script for adding more text boxes on click
</script>
</head>
<body>

<%@include file="navigation.jsp" %>
    
    <div class="container">
    
<form action="EmailListController" method="post" id="registration-form">
<center><h2>Create Email List</h2><br></center>
<table><tr><td><h3>Add List of Recepients</h3></td><td><button id="viewPrev" type="button" class="btn btn-info"> View Your Stored Emails </button></td></tr></table>
<br>
<div class="form-inline">

<table id="EmailParentTable" class="table table-striped">
<tr>
<td><label for="description" class="col-sm-12 control-label">Email</label></td>
<td><label for="description" class="col-sm-12 control-label">First name:</label></td>
<td><label for="description" class="col-sm-12 control-label">Last Name:</label></td>
<td></td>
</tr>

<tr>
<td>
	<div class="col-sm-12">
      <input class="form-control" name="email" id="email_1" placeholder="Enter Email Address" type="text">
    </div>
</td>
<td>
	<div class="col-sm-10">
      <input width="100%" class="form-control" name="fname" id="fname_1" placeholder="Enter First Name" type="text">
    </div></td>
<td>
	<div class="col-sm-10">
      <input width="max" class="form-control" name="lname" id="lname_1" placeholder="Enter Last Name" type="text">
    </div></td>
<td><a class="removeclass" href="#"></a></td>
</tr>
</table>
</div>
 <div class="col-sm-offset-8 col-sm-10">
<span class="small">
    <a id="AddMoreEmail" class="btn btn-info" href="#">Add More Email</a>
</span>
</div>
<br><br>

<div class="form-group">
    <div class="col-sm-offset-5 col-sm-12">
      <button id="reg" type="submit" class="btn btn-info"> Save </button>
      <button type="reset" class="btn">Cancel</button>
   
    </div>
  </div>
</form>
</div>
</body>
</html>