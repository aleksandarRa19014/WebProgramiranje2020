$(document).ready(function() {
	
	
		$.ajax({
		  	url: "/WebProject/rest/userService/currentUser",
			type: "GET",
			contentType: "application/json",
			dataType: "JSON"
		  
		}).done(function( data, textStatus, jqXHR ) {
			
			$("#curentUser").html(data.name);
			$("#curentUser").addClass( "glyphicon glyphicon-user" );
	
		}).fail(function() {
		    
		});
	
	
	$("#logout").click(function(event) {
	
		  event.preventDefault();	
		  $.ajax({
			  	url: "/WebProject/rest/userService/logout",
				type: "POST",
				contentType: "application/json",
				dataType: "JSON"
			  
		  }).done(function( data, textStatus, jqXHR ) {
				alert(textStatus);
				goTo();
			}).fail(function() {
			    
			});
		
		
	});
	
	function goTo(){
		alert("SSSSSSSSSSS ")
		window.location = "http://localhost:8090/WebProject/";
	}
	
	
});


