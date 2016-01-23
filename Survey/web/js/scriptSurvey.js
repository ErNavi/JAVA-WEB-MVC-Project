$(document).ready(function(){
	$("#surveyDetail").validate(
			{
	
				   submitHandler: function (form) {
				
	    	    var survey_name = $('#survey_name').val();
				var id = $('#submit').val();
				var description = $('#description').val();
				doAjaxPost(survey_name, id, description);
	    },
	
	    rules: {
	    	
	      survey_name:{
	    	  
	    	  required: true
	      },
	      
	      description:
	    	  {
	    	  required: true
	    	  },
	     		  
	    },//rules

		highlight: function(element) {
			$(element).closest('.control-group').removeClass('success').addClass('error');
		},
		success: function(element) {
			element
			.text('OK!').addClass('valid')
			.closest('.control-group').removeClass('error').addClass('success');
		}
		
			
			
			
	  });
		
	}); // end document.ready

