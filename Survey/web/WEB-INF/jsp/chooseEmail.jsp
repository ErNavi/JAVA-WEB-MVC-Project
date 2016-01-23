<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>Create Email List</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.validate.js"></script> 
<script src="<%=request.getContextPath() %>/js/script.js"></script> 

<script src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script> 

<script src="<%=request.getContextPath() %>/js/jquery.validate.js"></script> 

  <script src="http://code.jquery.com/jquery-latest.js">   
        </script>
       

<script> 
// STARTS :script for adding more text boxes on click
$(document).ready(function() {
//	alert("in fn add box:");
var InputsDetail  = $("#EmailParentTable"); //Input boxes wrapper ID
var x = InputsDetail.length; //initlal text box count
var FieldCount=1; //this will keep track of text box added

$("#AddMoreEmail").click(function (e) { 
	
		FieldCount++; //text box added increment
            $(InputsDetail).append('<tr><td><div class="col-sm-10"><input class="form-control" name="email" id="email_'+ FieldCount +'" placeholder="Enter Email Address" type="text"></div></td><td><div class="col-sm-10"><input class="form-control" name="fname" id="fname_'+ FieldCount +'" placeholder="Enter First Name" type="text"></div></td><td><div class="col-sm-10"><input class="form-control" name="lname" id="lname_'+ FieldCount +'" placeholder="Enter Last Name" type="text"></div></td><td><a href="#" class="removeclass">Remove</a></td></tr>');
            x++; //text box increment }

        	return false;
	
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

<script>
 $(document).ready(function(){
	     $("#addMoreEmail").hide(function(){
	 $("#addMoreEmailBtn").show();
	 });
	   });
 </script>
 <script>
	 $(document).ready(function(){	 
		 $(".readOnlyText").prop("readonly", true);
   $("#hideAddMoreEmailBtn").click(function(){
     $("#addMoreEmail").hide(function(){
 $("#addMoreEmailBtn").show();
 });
   });
   $("#addMoreEmailBtn").click(function(){
     $("#addMoreEmail").show(function(){
 $("#addMoreEmailBtn").hide();
 });
   });
 });
 </script>

 <script>
            $(document).ready(function() {
            //	alert("in fn ajax:");
            	$('.c_removeclass').click(function(event) { 
                    var id=$(this).val();
                    $(this).closest("tr").remove();
                  //  alert("in fn: id= "+id);
                $.get('ChooseEmailAjaxController',{id:id},function(responseText) { 
              	 	//$('#welcometext').text(responseText);  
              	 	//alert("in fn2: resText= "+id);
              	 	$(this).closest("tr").remove();//remove text box     
                    });
                });
            });
        </script>
</head>
<body>

<%@include file="navigation.jsp" %>
    
    <div class="container">
    <div id="total_email" >
    <form action="ChooseEmailController" method="post" id="chooseEmail-form">
    
    <div class="form-inline">

<table id="ChooseEmailParentTable" class="table table-striped">
<tr>
<td><label for="description" class="col-sm-12 control-label">Email</label></td>
<td><label for="description" class="col-sm-12 control-label">First name:</label></td>
<td><label for="description" class="col-sm-12 control-label">Last Name:</label></td>
<td></td>
</tr>

<c:forEach  items="${emailList}"  var="emailList" >
<tr >
<td>
	<div class="col-sm-12">
      <input class="form-control readOnlyText" name="c_email"  placeholder="Enter Email Address" type="text" value="${emailList.email}">
    </div>
</td>
<td>
	<div class="col-sm-10">
      <input width="100%" class="form-control readOnlyText" name="c_fname" placeholder="Enter First Name" type="text" value="${emailList.firstName}">
    </div></td>
<td>
	<div class="col-sm-10">
      <input width="max" class="form-control readOnlyText" name="c_lname" placeholder="Enter Last Name" type="text" value="${emailList.lastName}">
    </div></td>
<td><button id="remove" type="button" class="c_removeclass btn btn-info" value="${emailList.id}">Remove</button></td>
</tr>
</c:forEach>
</table>
</div> 

<div class="form-group">
    <div class="col-sm-offset-5 col-sm-12">
      <button id="reg_1" type="submit" class="btn btn-info"> Next </button>
      <button type="reset" class="btn">Cancel</button>
      <button id="addMoreEmailBtn" type="button" class="btn btn-info">Add More Email</button>
    </div>
  </div>

</form>
    </div>
    </div>
    
    
    <div class="container">
   <div id="addMoreEmail"> 
   <h2>Add More Emails Here</h2>
<form action="ChooseEmailAjaxController" method="post" id="registration-form">

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
      <button id="hideAddMoreEmailBtn" type="reset" class="btn">Cancel</button>
    </div>
  </div>
</form>
</div>
</div>
</body>
</html>