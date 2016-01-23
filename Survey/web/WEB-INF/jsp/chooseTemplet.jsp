<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose Template</title>

 	<link href="navbar-fixed-top.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="<%=request.getContextPath() %>/js/tinymce/tinymce.min.js"></script>
	<script>tinymce.init({
		selector: "textarea",
		theme: "modern",
		plugins: [
		"advlist autolink lists link image charmap print preview hr anchor pagebreak",
		"searchreplace wordcount visualblocks visualchars code fullscreen",
		"insertdatetime media nonbreaking save table contextmenu directionality",
		"emoticons template paste textcolor colorpicker textpattern"
		],
		toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
		toolbar2: "print preview media | forecolor backcolor emoticons",
		image_advtab: true,
		templates: [
		{title: 'Test template 1', content: 'Test 1'},
		{title: 'Test template 2', content: 'Test 2'}
		]
		});</script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script> 
    <script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/jquery.validate.js"></script> 
    <script src="<%=request.getContextPath() %>/js/scriptTemplet.js"></script> 


       <script src="http://code.jquery.com/jquery-latest.js">   
        </script>
        <script>
            $(document).ready(function() {                  
            	
            	$('#choose_temp').change(function(event) { 
            		tinymce.get("temp").setContent('Please Choose any Email Template.'); 
                    var username=$('#choose_temp').val();
                $.get('ChooseTempletAjaxController',{uname:username},function(responseText) { 
                	//$('#span').text(responseText);  
                	 tinymce.get("temp").setContent(responseText);       
                    });
                });
            });
        </script>

</head>
<body>
<%@include file="navigation.jsp" %>
    
    <div class="container">
       
       <form method="post" action="ChooseTempletController" class="form-horizontal" role="form" id="choose_Temp_Form"> 
        
        <div class="form-group">
    <label class="col-sm-2 col-sm-offset-2 control-label" for="subject">Subject</label>
    <div class="col-sm-5">
        <input type="text" class="form-control" name="subject" id="subject" placeholder="Enter subject">
  </div>
  </div>
       
       <div class="form-group">
    <label class="col-sm-2 col-sm-offset-2 control-label">Choose Template</label>
    <div class="col-sm-4" style=" overflow x:hidden;overflow y:scroll ">
       <!--<select name="choose_temp" id="choose_temp" class="form-control" size="1" onchange="ajax_fn(this.value)" >-->
       <select name="choose_temp" id="choose_temp" class="form-control" size="1">
       <option value="-1">---choose Template---</option>
        <option value="1">Informal Template 1</option>
        <option value="2">Informal Template 2</option>
        <option value="3">Formal Template 1</option>
        <option value="0" style="visibility: <c:choose>
										    <c:when test="${param.newuser>1}">
										       visible
										    </c:when>
										    <c:otherwise>
										        hidden
										    </c:otherwise>
											</c:choose>" >Last choosen Template</option>
	
        </select>
        </div>
  </div>
        <div>
        <input id="sid" name="sid" type="text" style="visibility: hidden"  value="<c:out value="${param.sid}" />"/>
        <!--<input id="c_cid" name="sid" type="text"  value="<c:out value="${param.newuser}" />"/>-->
        </div>
        
        <div>
            <span id="span" class="span"></span>
        </div>
        
<div class="form-group">
    <label class="col-sm-2 col-sm-offset-2 control-label" for="answer">You Choosed</label>
    <div class="col-sm-5">
  <textarea class="temp" id="temp" name="temp" rows="10" cols="50"></textarea>
  </div>
  </div>
<div><br/></div>
<div class="form-group">
    <div class="col-sm-offset-5 col-sm-10">
      <button id="reg" type="submit" class="btn btn-info">Next</button>
      <button type="reset" class="btn">Cancel</button>
      </div>
   </div>
    </form>
    </div>
    </body>
</html>