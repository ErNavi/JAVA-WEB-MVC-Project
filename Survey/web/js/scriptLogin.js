$(document).ready(function(){
	$("#loginPage").validate(

{

    submitHandler: function (form) {
			
    	    var companyloginid = $('#companyloginid').val();
			
			var companyloginpassword = $('#companyloginpassword').val();
			doAjaxPost(companyloginid,companyloginpassword);
    },

    rules: {
		
	      companyloginid:  {
    	    required: true,
    	    email:true
    	  },
    	  companyloginpassword:  {
    	  required: true
    	  }
	},//rules
	highlight: function(element) {
		$(element).closest('.control-group').removeClass('success').addClass('error');
	},//highlights
	success: function(element) {
		element
		.text('OK!').addClass('valid')
		.closest('.control-group').removeClass('error').addClass('success');
								
	}
});
});