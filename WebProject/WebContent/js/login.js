$(document).ready(function() {
	
	$('#forma').validate({
		
		  rules: {
			  userName: {
			        required: true
			      },
			      password: {
			        required: true
		      },
		  },
			  messages: {
				  userName: {
					  required: "Please enter username."
				  },
				  password: { 
					  required:"Please enter password."
				  }
			  }
		  ,
		  submitHandler: function(form,event) {
			  event.preventDefault();
				
				var s = JSON.stringify({
						userName : $("#userName").val(),
						password : $("#password").val()	
				});

				$.ajax({
						url: "/WebProject/rest/userService/login",
						type: "POST",
						contentType: "application/json",	
						data: s,
						dataType: "JSON"	
				  }).done(function( data, textStatus, jqXHR) {
					   
					  	alert(data.role);
						goTo(data.role);
					}).fail(function( data, textStatus, jqXHR) {
						alert(data.responseText);
						location.reload();
					});
			  }
	
	});	
	
	
	function goTo(role){
		
		if(role == "admin"){
			window.location = "adminPage.html";
		}else if(role == "host"){
			window.location = "hostPage.html";
		}else if(role == "guest"){
			window.location = "guestPage.html";
		}	
	}
	
});
	