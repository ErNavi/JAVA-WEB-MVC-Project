$(document).ready(function(){
	$("#forgotPage").validate(

{

    submitHandler: function (form) {
			
    	    var fuserid = $('#fuserid').val();
			var st = $('#st').val();
			var fSecurityAnswer = $('#fSecurityAnswer').val();
			doAjaxPost(fuserid, st, fSecurityAnswer);
    },

    rules: {
		st : {
	        required: true
	      },
	      fuserid:  {
    	    required: true,
    	    email:true
    	  },
          fSecurityAnswer:  {
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