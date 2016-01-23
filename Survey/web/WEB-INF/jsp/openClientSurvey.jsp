<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<title>
Survey
</title>

<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
   
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.validate.js"></script> 
<!-- <script>
$(document).ready(function() {
            	
            	alert("in send script:");
            	
            	$('.submit').click(function() { 
            		var sid=$("#sid_value").val();
                   
                   alert("sid send to controller="+sid);
                    document.location.href= "StartUpController?sid="+sid;
                    

                });
            });
</script> -->
<!-- <script>
$(document).ready(function(){
	
	/* var sid = $('#txtname').val();
    var eid = $('#txtupass').val();
    var cid = $('#txtemail').val();
    var qli = $('#st').val();
    var qoi = $('#txtans').val();   
    var createdBy = $('#txtname').val();
    doAjaxPost(sid,eid,cid,qli,qoi);

	 */
	 
	var qoi = [];
	var checkObj =$(".openClientSurvey").find(".option").find("input:checkbox");
	
	var count= $("h2").
	 
	 
	 
	
}

</script>  -->
<!-- <script>
function doAjaxPost(sid,eid,cid,qli,qoi)
{  
	 
	 $.ajax({  
	   type: "POST",  
	   url: "OpenClientSurveyController",  
	   data: "sid="+sid+"&eid="+eid+"&cid="+cid+"&qli="+qli+"&qoi="+qoi,  
	   success: function(data){  
		   if(data.indexOf('Thanx For your response..!!!') != -1)
		   {
		  $("#show").html("<h1 align='center' STYLE='color:green'>"+data+"</h1>");	
		  }
		   else
			   {
			   $("#show").html("retry...!!!")
		   }
	     
	   } //success close 
	   
	  
	 });  
	} // doAjaxPost close
</script> -->
</head>

<body>
<form action="OpenClientSurveyController" method="post" class="form-horizontal" role="form" id="openClientSurveyForm">
<input type="hidden" id='cid_value' name="cid" value='<c:out value="${param.cid}" />'>
<input type="hidden" id='sid_value' name="sid" value='<c:out value="${param.sid}" />'>
 <input type="hidden" id='eid_value' name="eid" value='<c:out value="${param.eid}" />'>
    
 <div class="openClientSurvey">
 <c:forEach  items="${questionList}"  var="questionList" varStatus="www">
<!-- ********************************STARTS*********************************************** -->

<div class="div1">
<!-- <h1 align="center">ADD QUESTION</h1> -->
<label ><h2>Question ${www.index+1}</h2></label>
<input type="hidden" name="question" value="${questionList.questionId}">
<div>
<p id="${questionList.questionId}">
${questionList.question}
</p>
</div>

 
<div class="div2">
<table class="option table table-striped">
<c:forEach  items="${questionList.list}"  var="list" varStatus="ww">
<tr>
<td>
${ww.index+1})<input type="checkbox" class="check"  id="${list.optionId}" name="option_${questionList.questionId}" value="${list.optionId}">
</td>
<td>
<div>${list.option}</div>
</td></tr>
</c:forEach>
</table>
</div>

<br>
</div>
</c:forEach>
 </div>
<br>
<button type="submit" class="submit btn btn-info" >Submit</button>
</form>

</body>
</html>