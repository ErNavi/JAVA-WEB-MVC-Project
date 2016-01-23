<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 

"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<title>
Add Question for Survey
</title>
<!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
    <!-- Custom styles for this template -->
   <!--   <link href="navbar-fixed-top.css" rel="stylesheet">-->

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.validate.js"></script> 

<script src="<%=request.getContextPath() %>/js/tinymce/tinymce.min.js"></script>
	<script>
	$(document).ready(function() {
	    tinymce.init({
		theme: "modern",
		mode : "textareas",
		editor_selector: "text_1",
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
	
	    });
	});
	
	
		
	
	</script>


<script> 
var questionIndex=1;
$(document).ready(function() {

	$(".mainContainer").find(".addQuestion").hide();
	$(".mainContainer").find(".removeQuestion").hide();
	$(".mainContainer").find(".next1").click(function() {
		
		
	$(".mainContainer").find(".div2").css("display", "block");
	$(this).hide();
		
	});
	
	
$(".mainContainer").find(".next2").click(function(){
	

var option  = $(".mainContainer").find(".option");
selectValues = $(".mainContainer").find(".select").val();
//alert("Maincontainer select value"+selectValues);
var i;
//alert("above for loop");
for(i=2;i<=selectValues;i++)
	{
	$(".mainContainer").find(".option").append('<tr><td>'+i+')<input type="checkbox" class="check" value="'+i+'" id="check'+i+'"></td><td><input type="text" id="option'+i+'" class="textBox form-control"><span></span></td></tr>');
	
	}

$(".mainContainer").find(".div3").css("display", "block");
$(this).hide();
$(".mainContainer").find(".select").hide();
$(".mainContainer").find(".addQuestion").show();
//alert('Maincontainer after $(".addQuestion").css("display", "block");');


});
	
$(".mainContainer").find(".addQuestion").click(function(){
	
	
	add();
		
});

});
function afterQuestion()
{
	
	
	$(".mainContainer").find(".div2").css("display", "block");
	
    $(".mainContainer").find(".next1").hide();
		
	
}

function afterSelect()
{
	
var option  = $(".mainContainer").find(".option");
selectValues = $(".mainContainer").find(".select").val();
//alert("Maincontainer select value"+selectValues);
var i;
//alert("above for loop");
for(i=2;i<=selectValues;i++)
	{
	$(".mainContainer").find(".option").append('<tr><td>'+i+')<input type="checkbox" class="check" value="'+i+'" id="check'+i+'"></td><td><input type="text" id="option'+i+'" class="textBox form-control"><span></span></td></tr>');
	
	}

$(".mainContainer").find(".div3").css("display", "block");
$(".mainContainer").find(".next2").hide();

$(".mainContainer").find(".select").hide();
$(".mainContainer").find(".addQuestion").show();
//alert('Maincontainer after $(".addQuestion").css("display", "block");');

	
	}
	
	
function add()
{
	
	var resu=tinymce.get("text_"+questionIndex).getContent();
	
	if(resu!="")
		{
	
	    validateTextBoxes();
	    countCheckboxes();

	    $(".mainContainer").find("span").hide();
		//alert('in addQuestion click');
		//$(".mainContainer").html( $(".mainContainer" ).html()+'<button type="button" class="removeQuestion">RemoveQuestion</button>');
		var oldValueTINYMCE=tinymce.get("text_"+questionIndex).getContent();
		//if(oldValueTINYMCE!=null||oldValueTINYMCE!="")
			
		tinymce.get("text_"+questionIndex).remove();
		$(".mainContainer").clone().appendTo( ".oldContainer" );
		
		var oldValue=
		tinymce.init({
			theme: "modern",
			mode : "textareas",
			editor_selector: "text_"+questionIndex,
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
		
		    });
		tinymce.get("text_"+questionIndex).setContent(oldValueTINYMCE);
		
	tinymce.get("text_"+questionIndex).getBody().setAttribute('contenteditable', 'false');
		$(".oldContainer").find(".textBox").prop("readonly", true);
		$(".oldContainer").find(".check").prop("disabled", true);


	    //alert(5555555555555555555555);
<!------------------------------------------------------------------------------------------------------>		
		
		
		//alert("sooooooooooooooooooooooo"+tinymce.get("text_"+questionIndex).getContent({format : 'text'}));
		var content=tinymce.get("text_"+questionIndex).getContent({format : 'text'});
		
		//alert("the value in contene"+content);
		
		var correctAnswer = [];
        var checkObj =$(".mainContainer").find(".option").find("input:checkbox");
       var checkCount=checkObj.length/2; 
      //  alert("hello i am after pushessssssssssss data in oldContainer no of checkbox="+checkCount); 
              
          for (var i=0; i<checkCount; i++) {
         if (checkObj[i].type === "checkbox") {
        	 
        	 if(checkObj[i].checked === true)
        		 {
        		 correctAnswer[i]=0;
        		 
        		 }
        	 else
        		 {
        		 correctAnswer[i]=1;
        		 
        		 }
        	 
            	
            }
        }
          
      //  alert('selected checkbox value='+correctAnswer); 
        
       
       var option=[];
       var optionObj=$(".mainContainer").find(".option").find("input:text");
       var optionCount=optionObj.length/2;
     //  alert('options count='+optionCount);
       
     
       for (var j=0; j<optionCount; j++){
    //	   alert("in for loop option");
    	   if (optionObj[j].type === "text") 
    		   {
    		   
    		   option[j] = $(".mainContainer").find(".option").find("#option"+(j+1)).val();
    		 
    		   }
    	   
       }
      // alert('options value='+option); 
   
       var sid=$("#sid_value").val();
       
     //  doAjaxPost(textarea,option,correctAnswer,sid);
       //alert("after send data to doAjax...............");
      
		
		$(".mainContainer:first").addClass( "submittedQuestion" );
		$(".mainContainer:first").attr("id",questionIndex);
		var removeid=questionIndex;
		 $(".mainContainer:first").find(".removeQuestion").attr("id","remove_"+questionIndex++);
		
		$(".mainContainer:first").removeClass( "mainContainer" );
		doAjaxPost(content,option,correctAnswer,sid,removeid);
		//alert('wait maincontainer is going to empty');
		 
		$('.mainContainer').empty(); 
		  
		  
		  
		  
<!-------------------------------------------------------------------------------------------------->
$(".template").find(".textarea").attr("id","text_"+questionIndex);
$("#text_"+questionIndex).addClass("text_"+questionIndex);
		  $( ".template" ).children().clone(true).appendTo($(".mainContainer" ));
		  
		  tinymce.init({
				theme: "modern",
				mode : "textareas",
				editor_selector: "text_"+questionIndex,
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
			
			    });
		
        
    //    $(".mainContainer").find(".mce-tinymce").css('visibility', 'visible');
       
        
        
        
        
		   $(".submittedQuestion").find(".addQuestion").hide();
		  
		   $(".submittedQuestion").find(".removeQuestion").show();
		   
		  /*  $(".submittedQuestion").find(".removeQuestion").click(function(){
				
				 $(this).closest("div").remove();
			});  */
		  // tinymce.remove('textarea1');
		   $(".mainContainer").find(".addQuestion").hide();
			$(".mainContainer").find(".removeQuestion").hide(); 
	 
			/* tinymce.get("text_"+questionIndex).getBody().setAttribute('contenteditable', 'false');
			
			$(".oldContainer").find(".textBox").prop("readonly", true);
			$(".oldContainer").find(".check").prop("disabled", true); */
 
	
	
		}
	else{
		//alert("in else");
		$(".mainContainer").find(".span").html("please write question.");
	}
		
		
		}
	
	
	

	
	
	
	
 function validateTextBoxes()
{
	
	 var txtElems =$(".mainContainer").find(".option").find("input:text");
	
	var max=txtElems.length;
	//alert("total input element="+max);
	var isValid = 0;
    $(".mainContainer").find(".option").find('input:text').each(function(){
    
    	
        if($(this).is(":blank")){
            $(this).css('border','1px solid red');
            $(".mainContainer").find(".option").find("span").html("this is required");
           
             }
        else{
            $(this).css('border','1px solid green');
            isValid++;
            }
        
        });
   // alert("isValid="+isValid);
    
    if(isValid<max){
        alert('Please enter content to all text fields');
       event.preventDefault();
      
        }
    
    alert("above same value check");
     /* $(".mainContainer").find(".option").find('input:text').each(function() {
     var  val=$(this).val();
       // alert(val);
     var next_val="";  
        if (next_val==val) {
              alert("same value");
        }
        else
        	{
        	 alert("not same value");
        	}
      next_val=val;
     });   */
     
     var valarr = [];
     $(".mainContainer").find(".option").find('input:text').each(function () {

          var curr = $(this).val();
          if (jQuery.inArray(curr, valarr) > -1) {
              alert("same value in option does not required..!!!");
          } else {
              valarr.push(curr);
          }

    });
     

    } 
 
 
    
	
   function countCheckboxes(){
	  
   var checkElems =$(".mainContainer").find(".option").find("input:checkbox");
    var count = 0;
   var noOfCheckBox=checkElems.length;
   // alert("nooooooooooooooooooo of checkbox="+noOfCheckBox);
    for (var i=0; i<checkElems.length; i++) {
      if (checkElems[i].type === "checkbox" && checkElems[i].checked === true) {
        count++;
        
      }
    }
    if(count < 1){
    
   
    $(".mainContainer").find(".span").html("please check atleast one check box");
    alert("You should select atlest 1 checkbox");
    event.preventDefault();
 
   
    }
} 
   
   
   function doAjaxPost(textarea,option,correctAnswer,sid,removeid)
   {  
	//  alert("**************** in doAjax value of option"+option[0]);
   	 $.ajax({  
   		 
   		
   	   type: "POST",  
   	   url: "AddQuestionController", 

  data: "textarea="+textarea+"&option="+option+"&correctAnswer="+correctAnswer+"&sid="+sid,  
   //	 data: {option:option,textarea:textarea,correctAnswer:correctAnswer},
   	   success: function(data){  
   		 //  alert("*********************"+data);
   		   var qid=data.trim();
   		  $("#remove_"+removeid).val(qid);
   		  $("#show").html("<h1 align='center' STYLE='color: red'>"+data+"</h1>");	
   		  
   	     
   	   } //success close 
   	   
   	  
   	 });  
   	} // doAjaxPost close   
 
   	
  
    $(document).ready(function() {
    //	alert("in remoooooov top");
    
   	$("body").on("click",".removeQuestion", function(e){
        var id=$(this).val();
        $(this).closest("div").remove();
       // alert("below closest id=_"+id);
    $.get('QuestionRemoveController',{id:id},function(responseText) { 
  	 	
  	 //	alert("at the end resText= "+id);
  	 	
        });
    });
    });
   	
</script>
  <script>
            $(document).ready(function() {
            	//alert("in update ajax:");
            	$('.preview').click(function() { 
                    var id=$("#sid_value").val();
                    document.location.href= "AddQuestionController?sid="+id+"&check=1";
                    //document.location.href="previewSurvey.jsp?sid="+id+"&check=1";
                });
            });
</script>
</head>
<body>
<input type='text' id='sid_value' value='<c:out value="${param.sid}" />' style='visibility: hidden'>

<%@include file="navigation.jsp" %>
<!-- ----------------------------------------------------------------------------------------- -->
<div class="container">
<!-- Main component for a primary marketing message or call to action -->
<div class="oldContainer form-group">

</div>
<!-- ----------------------------------------------------------------------------------------- -->
<div class="mainContainer form-group" id="1">
<div class="div1">
<h1 align="center">ADD QUESTION</h1>
<label col-sm-2 control-label><h2>Question</h2></label>
<textarea rows="2" cols="5"  name="textarea" class="textarea form-control text_1" id="text_1">

</textarea>
</br>
<button type="button" class="next1 btn btn-info">Next</button>
</div>

<div class="div2" style="display: none;">
<label col-sm-2 control-label>No. of Option:</label>
<select class="select form-control">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
</select>
<br>
<button type="button" class="next2 btn btn-info">Next</button>
</div>

<div class="div3" style="display: none;">
<table class="option table table-striped">
<tr><td>
1)<input type="checkbox" class="check" value="1" id="check1">
</td>
<td>
<input type="text" id="option1" class="textBox form-control"><span></span>
</td></tr>
</table>

</div>

<button type="button" class="addQuestion btn btn-info" >AddQuestion</button>
 <button type="button" class="removeQuestion btn btn-info" style="visibility: none;" id="remove_1">RemoveQuestion</button>
 <span class="span"></span>
</div>
<!-- ----------------------------------------------------------------------------------------- -->


<!-- ----------------------------------------------------------------------------------------- -->
<div class="template form-group" style="display: none;">
<div class="div1">
<h1 align="center">ADD QUESTION</h1>
<label><h2>Question</h2></label>

 <textarea rows="2" cols="5"  name="textarea" class="textarea form-control">

</textarea> 
</br>
<button type="button" class="next1 btn btn-info" onclick="afterQuestion()">Next</button>
</div>

<div class="div2" style="display: none;">
<label>No. of Option:</label>
<select class="select form-control">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
</select>
<br>
<button type="button" class="next2 btn btn-info" onclick="afterSelect()">Next</button>
</div>

<div class="div3" style="display: none;">
<table class="option table table-striped">
<tr><td>
1)<input type="checkbox" class="check" value="1" id="check1">
</td>
<td>
<input type="text" id="option1" class="textBox form-control"><span></span>
</td></tr>
</table>

</div>

<button type="button" class="addQuestion btn btn-info" onclick="add()">AddQuestion</button>
 <button type="button" class="removeQuestion btn btn-info" style="visibility: none;">RemoveQuestion</button>
 <span class="span"></span>
</div>

<!-- ----------------------------------------------------------------------------------------- -->
</div>
<button type="button" class="preview btn btn-info" >Preview Survey</button>
</body>
</html>