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

    <title>Survey Detail</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    

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
<script>
 $(document).ready(function(){
	 $(".create_date").prop("readonly", true);
	     $(".removeU_1").hide(function(){
	    	 $(".usedBtn_1").show();
	    	 $(".readonlyText_1").prop("readonly", true);
	 
	 });

	     $(".removeU_0").show(function(){
	    	 $(".usedBtn_0").hide(); 
	    	 $(".readonlyText_0").prop("readonly", false);
	   });
 });
 </script>
<script>
            $(document).ready(function() {
            //	alert("in fn ajax:");
            	$('.c_removeclass').click(function(event) { 
                    var id=$(this).val();
                    var check=0;
                    $(this).closest("tr").remove();
                  //  alert("in fn: id= "+id);
                $.get('SurveyDetailsRemoveController',{id:id},function(responseText) { 
              	 	//$('#welcometext').text(responseText);  
              	 	//alert("in fn2: resText= "+id);
              	 	$(this).closest("tr").remove();//remove text box     
                    });
                });
            });
</script>

<script>
            $(document).ready(function() {
            //	alert("in update ajax:");
            	$('.updateSurvey').click(function(event) { 
                    var id=$(this).val();
                    var check=1;
                    var name_val="#name_"+id;
                    var description_val="#description_"+id;
					var survey_name=$(name_val).val();
					var description=$(description_val).val();
                 	
              //      alert("in fn: name= "+name+" description= "+description+" id= "+id+" check= "+check);
                $.get('SurveyDetailsRemoveController',{check:check,id:id,survey_name:survey_name,description:description},function(responseText) { 
              	 	//$('#welcometext').text(responseText);  
              	 	alert("Survey Updated");
              	 	//$(this).closest("tr").remove();//remove text box     
                    });
                });
            });
</script>

<script>
            $(document).ready(function() {
            	//alert("in update ajax:");
            	$('.c_selectclass').click(function(event) { 
                    var id=$(this).val();

                    document.location.href="ChooseTempletController?sid="+id;
                });
            });
</script>

<script>
            $(document).ready(function() {

            	$('.c_selectclass').click(function(event) { 
                    var id=$(this).val();
                    $("#id_box").val(id);
                    var name="#name_"+id;
                    var description="#description_"+id;
					var name_val=$(name).val();
					var description_val=$(description).val();
					$(function () {
						  $('#survey_name').val(name_val);
						  $('#description').val(description_val);
					});
            	});
            });
</script>

<script>
function doAjaxPost(survey_name, id, description) {  
	// alert("in ajax functn");
	 $.ajax({  
	   type: "POST",  
	   url: "SurveyDetailsController",  
	   data: "id="+id+"&survey_name="+survey_name+"&description="+description,  
	   success: function(resp){  
	//     alert("The Row Updated: "+resp);
	     //document.getElementById("show").innerHTML=resp;
	    // $("#sid_next").val(resp);
	     document.location.href="ChooseTempletController?sid="+resp;
	     
	   }  
	 });  
	}  
</script>

  </head>

  <body>

<%@include file="navigation.jsp" %>

    <div class="container">

      <!-- Main component for a primary marketing message or call to action -->
   <form class="form-horizontal" role="form" id="surveyDetail">
 <h2>Create New Survey</h2>
 <div>
  <div class="form-group">
    <label class="col-sm-2 control-label" for="survey_name">Survey Name</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="survey_name" id="survey_name" placeholder="Enter Survey Name">
    <input type="text" id="id_box" value="0" style="visibility: hidden">
    </div>
  </div>
  <div class="form-group">
    <label for="description" class="col-sm-2 control-label">Description</label>
    
    <div class="col-sm-10">
      <input type="text" class="form-control" id="description" placeholder="Enter description" name="description">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button id="submit" type="submit" class="btn btn-info btn-large" value="0">Save</button>
      <button type="reset" class="btn reset">Cancel</button>
    </div>
  </div>
  </div>
  
  
</form action="">
<!--<form class="form-horizontal" role="form" id="surveyDetail">
<input type="text" class="form-control" id="sid_next" placeholder="Enter description" name="sid_next">
<button id="submit" type="submit" class="btn btn-info btn-large" value="0">Next</button>
</form>-->

<div id="total_survey" >
  
    <h2>Choose From Old Survey</h2>
    <div class="form-inline">

<table id="ChooseEmailParentTable" class="table table-striped">
<tr>
<td><label for="description" class="col-sm-12 control-label">Survey Name</label></td>
<td><label for="description" class="col-sm-12 control-label">Survey Detail</label></td>
<td><label for="description" class="col-sm-12 control-label">Created Date</label></td>
<td colspan="4"><label for="description" class="col-sm-12 control-label">Choose Action</label></td>

</tr>

<c:forEach  items="${surveyList}"  var="surveyList" >
<tr >
<td>
	<div class="col-sm-12">
      <input id="name_${surveyList.id}" class="form-control name readonlyText_${surveyList.used}" name="c_name"  placeholder="Enter Email Address" type="text" value="${surveyList.name}">
    </div>
</td>
<td>
	<div class="col-sm-10">
      <input id="description_${surveyList.id}" width="max" class="form-control description readonlyText_${surveyList.used}" name="c_description" placeholder="Enter Last Name" type="text" value="${surveyList.description}">
    </div></td>
<td>
	<div class="col-sm-10">
      <input id="create_date" width="max" class="form-control create_date" name="c_createDate" placeholder="Enter Last Name" type="text" value="${surveyList.createDate}">
    </div></td>
<td><button  type="button" class="btn usedBtn_${surveyList.used}" value="${surveyList.id}">Used</button><button id="remove" type="button" class="c_removeclass btn btn-info removeU_${surveyList.used}" value="${surveyList.id}">Remove</button></td>
<td><button  type="button" class="c_selectclass btn btn-info" value="${surveyList.id}">Select</button></td>
<td><button  type="button" class="btn usedBtn_${surveyList.used}" value="${surveyList.id}">Used</button><button id="remove" type="button" class="btn btn-info updateSurvey removeU_${surveyList.used}" value="${surveyList.id}">Update</button></td>
<td><button  type="button" class="btn usedBtn_${surveyList.used}" value="${surveyList.id}">Used</button><button id="remove" type="button" class="btn btn-info sendSurvey removeU_${surveyList.used}" value="${surveyList.id}">Send</button></td>
</tr>
</c:forEach>
</table>
</div>
    </div>
    </div>
</body>
</html>