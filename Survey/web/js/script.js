$(document).ready(function(){$('#reg').click(function(){$('#registration-form').validate({
	
	    rules: {
	    	
		      email:{
		    	  
		    	  required: true,
			        email: true  
		    	  
		      },
		      
		      fname:{
		    	  
		    	  required:false
		      },
		      
              lname:{
		    	  
		    	  required: false
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

}); // end document.ready

