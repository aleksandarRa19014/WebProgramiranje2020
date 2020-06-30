$(document).ready(function() {
	
	$('#forma').validate({
		
		  rules: {
			  userName: {
			        required: true
			      },
			  name: {
			        required: true
		      },
		      lastName: {
			        required: true
			  },
			  password: {
			        required: true
		      },
		      confirmPassword: {
			        required: true,
			        equalTo : "#password"
		      },
		      gander: {
			        required: true
		      },
		  },
		  messages: {
			  userName: {
				  required: "Please enter Username."
			  },
			  name: { 
				  required:"Please enter Name."
			  }, 
			  lastName: {
				  required: "Please enter Last Name."
			  },
			  password: { 
				  required:"Please enter password."
			  },
			  confirmPassword: {
				  required: "Please confirm password.",
				  equalTo: "Confirmed password is not equal with password."      
			  },
			  gander: { 
				  required:"Please select your gander."
			  }
			  
		  }
		  ,
		  submitHandler: function(form,event) {
			  
			  
			  console.log($("#confirmPassword").val());
			  console.log($("#password").val());
			  console.log($( "#gander option:selected" ).text());
			  
			  
			  event.preventDefault();
			  
			  var s = JSON.stringify({
				  	userName: $("#userName").val(),
				    password: $("#password").val(),
				    name: $("#name").val(),
				    lastName: $("#lastName").val(),
				    gander: $( "#gander option:selected" ).text().toLowerCase()	
			  });
			  
			  console.log(s);
			  $.ajax({
					url: "/WebProject/rest/userService/singUp",
					type: "POST",
					contentType: "application/json",	
					data: s,
					dataType: "JSON"	
			  }).done(function( data, textStatus, jqXHR) {
				   
				  	alert(data.role);
					goTo();
				}).fail(function( data, textStatus, jqXHR) {
					alert(data.responseText);
				});
			  }
	
	});	
	
	
	function goTo(){
			window.location = "http://localhost:8090/WebProject/guestPage.html";	
	}
	
});
	