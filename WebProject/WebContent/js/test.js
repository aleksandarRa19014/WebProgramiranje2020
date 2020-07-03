$(document).ready(function() {
	
	$(".link").click(function(e) {
	    e.preventDefault();
	    $('.content-container div').hide();
	    $('#' + $(this).data('rel')).show();
	});
	
	
	 $("#link5").click(function(){
		 alert("sss");
		    $("#content5").load("test.html");
		  });
	
	
	/*$("#logout").click(function() {
		 

		  $.ajax({
			  	url: "/WebProject/rest/userService/logout",
				type: "POST",
				contentType: "application/json",
				dataType: "JSON"
			  
		  }).done(function( data, textStatus, jqXHR) {
				console.log(textStatus);
				//goTo();
				 alert( "Handler for .click() called." );
			}).fail(function() {
			    
			});
		  
		  
	});
	
	
	
	$("#curentUser").click(function() {
		  $.ajax({
			  	url: "/WebProject/rest/userService/getAllUsers",
				type: "GET",
				contentType: "application/json",
				dataType: "JSON"
			  
		  }).done(function( data, textStatus, jqXHR) {
			  	for(i=0; i < data.length; i++){
			  		console.log(data[i].userName);
			  	}
			}).fail(function() {
			    
			});
		  
		  
	});
	
	
	
	
	
	function goTo(){
		alert("SSSSSSSSSSS ")
		window.location = "http://localhost:8090/WebProject/login.html";
	}
	
	
	
	
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
						  required: "Please enter username;"
					  },
					  password: { 
						  required:"Please enter password;"
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
						   
							alert(textStatus);
							console.log(data);
						}).fail(function( data, textStatus, jqXHR) {
							alert(data.responseText);
							
						});
				  }
		
		});	
	
		
		
	
	
	/*$('#forma').submit(function(event) {
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
			   
				alert(textStatus);
			}).fail(function() {
			    
			});
		
	});	*/
	
});