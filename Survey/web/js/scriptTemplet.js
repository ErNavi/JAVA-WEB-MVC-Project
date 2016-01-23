$(document).ready(function(){
		$("#choose_Temp_Form").validate(
				{
					
		
					rules: {
					choose_temp : {
				        required: true
				      }
				},//rules

			highlight: function(element) {
				$(element).closest('.control-group').removeClass('success').addClass('error');
			},
			success: function(element) {
				$(element).text('OK!').addClass('valid').closest('.control-group').removeClass('error').addClass('success');
			}
			
				
				
				
		  });
			
		}); // end document.ready

