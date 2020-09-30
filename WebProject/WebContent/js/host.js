$(document).ready(function() {
	
	
		$.ajax({
		  	url: "/WebProject/rest/userService/currentUser",
			type: "GET",
			contentType: "application/json",
			dataType: "JSON"
		  
		}).done(function( data, textStatus, jqXHR ) {
			
			$("#curentUser").addClass( "glyphicon glyphicon-user" );
			$("#curentUser").html(data.name);
			
			    $("#t01 tbody").append(
			        "<tr>"
			            +"<td>"+data.name+"</td>"
			            +"<td>"+data.lastName+"</td>"
			            +"<td>"+data.gander+"</td>"
			            +"<td>"+data.role+"</td>"  
			        +"</tr>" );
			    
			
	
		}).fail(function() {
		    
		});
		
		
		$(".link").click(function(e) {
		    e.preventDefault();
		    $('#cont').children().hide();
		    
		    console.log($(this).data('rel'));
		    
		    $('#' + $(this).data('rel')).parentsUntil("div#cont").show();		//pokazi sve od njega na gore   
		    $('#' + $(this).data('rel')).siblings().show();						//svu njegovu bracu					
		    $('#' + $(this).data('rel')).show();								//i na kaju pokazi njega
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

		
		$('#gsearch').keyup(function(){
		    // Search Text
		   var search = $(this).val();

		    // Hide all table tbody rows
		   $('table tbody tr').hide();

		    // Count total search result
		   var len = $('table tbody  td:nth-child(2):contains("'+search+'")').length;

		    
		      // Searching text in columns and show match row
	       $('table tbody  td:contains("'+search+'")').each(function(){
	          $(this).closest('tr').show();
	       });
		    

		  });
		
		
		function goTo(){
			
			window.location = "/WebProject/";
		}
	

});


