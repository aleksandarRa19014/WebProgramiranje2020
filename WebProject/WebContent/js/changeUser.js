$(document).ready(function() {
	
	var userName;
	var role;
	
	$.ajax({
	  	url: "/WebProject/rest/userService/currentUser",
		type: "GET",
		contentType: "application/json",
		dataType: "JSON"
	  
	}).done(function( data, textStatus, jqXHR ) {
		
	
			userName = data.userName;	    
		    role = data.role;
		    $("#name").attr("value",data.name);
		    $("#lastName").attr("value",data.lastName);
		    $("#password").attr("value",data.password); 
		    $("#gander").val(data.gander).change();
		    

	}).fail(function() {
	    
	});
	
	$('#forma').validate({
		
		  rules: {
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
				  	userName: userName,
				    password: $("#password").val(),
				    name: $("#name").val(),
				    lastName: $("#lastName").val(),
				    role : role,
				    gander: $( "#gander option:selected" ).text().toLowerCase()	
			  });
			  
			  console.log(s);
			  $.ajax({
					url: "/WebProject/rest/userService/update",
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
			window.location = "http://localhost:8090/WebProject/adminPage.html";	
	}
	
});
	