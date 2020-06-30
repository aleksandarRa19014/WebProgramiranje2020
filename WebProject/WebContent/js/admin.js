$(document).ready(function() {
	
	
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


