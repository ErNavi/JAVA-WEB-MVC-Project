$(document).ready(function(){
	$("#registrationForm").validate(
		{
			
			submitHandler: function (form) {
				
	    	    var txtname = $('#txtname').val();
	    	    var txtupass = $('#txtupass').val();
	    	    var txtemail = $('#txtemail').val();
	    	    var txturl = $('#txturl').val();
	    	    var logo = $('#logo').val();
	    	    var st = $('#st').val();
	    	    var txtans = $('#txtans').val();   
	    	    var membership = $('input:radio[name=membership]:checked').val();
	    	    var createdBy = $('#txtname').val();
	    	    doAjaxPost(txtname,txtupass,txtemail,txturl,st,txtans,membership,createdBy);
	    	   
	    },
	
	rules: {
    
      
		txtname: {
	        required: true,
	       required: true
	      },
		  
		
		  
	      txtupass: {
				required: true,
				minlength: 6
			},
			confirm_password: {
				required: true,
				minlength: 6,
				equalTo: "#txtupass"
			},
		  
			txtemail: {
	        required: true,
	        email: true
	      },
	      
	      txturl: {
		        required: true,
		      },

	      st : {
	        required: true,
	       
	      },
		  
	      membership : {
	        required: true,
	       
	      },

	
	     
	      txtans: {
	      	minlength:2,
	        required: true
	      },

	     
         
	      
		   agree: "required"
		  
	    

	  
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
	


}); // end document.ready
