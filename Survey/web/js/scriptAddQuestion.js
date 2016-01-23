$(document).ready(function(){
	
	/*$(".mainContainer").find(".next1").click(function() {
		
	$('#addQuestion').validate({
	
	    rules: {
	    	
	    	textarea:{
		    	  
		    	  required: true,
			       
		    	  
		      }
		      
		     	
	    },

		highlight: function(element) {
			$(element).closest('.control-group').removeClass('success').addClass('error');
		},
		success: function(element) {
			element
			.text('OK!').addClass('valid')
			.closest('.control-group').removeClass('error').addClass('success');
		},
		errorPlacement: function(error, element) {
			  if (element.attr("name") == "membership") {
			     error.insertAfter("#membershipLast");
			  } else {
			     error.insertAfter(element);
			  }
			}
			
			
	  });
		
	});*/
	
	function validateTextBoxes()
	{
	

	$(".mainContainer").find(".addQuestion").click(function() {
		
		$('#addQuestionForm').validate({
		
		    rules: {
		    	
		    	option1:{
			    	  
			    	  required: true,
				       
			    	  
			      }
			      
			     	
		    },

			highlight: function(element) {
				$(element).closest('.control-group').removeClass('success').addClass('error');
			},
			success: function(element) {
				element
				.text('OK!').addClass('valid')
				.closest('.control-group').removeClass('error').addClass('success');
			},
			errorPlacement: function(error, element) {
				  if (element.attr("name") == "membership") {
				     error.insertAfter("#membershipLast");
				  } else {
				     error.insertAfter(element);
				  }
				}
				
				
		  });
			
		});
		
	
	
	}

}); // end document.ready
